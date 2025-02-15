/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hpsf;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import org.apache.poi.hpsf.Filetime;
import org.apache.poi.hpsf.UnsupportedVariantTypeException;
import org.apache.poi.hpsf.Variant;
import org.apache.poi.hpsf.VariantSupport;
import org.apache.poi.hpsf.WritingNotSupportedException;
import org.apache.poi.hpsf.wellknown.PropertyIDMap;
import org.apache.poi.util.CodePageUtil;
import org.apache.poi.util.HexDump;
import org.apache.poi.util.LittleEndian;
import org.apache.poi.util.LittleEndianByteArrayInputStream;
import org.apache.poi.util.LocaleUtil;
import org.apache.poi.util.POILogFactory;
import org.apache.poi.util.POILogger;

public class Property {
    public static final int DEFAULT_CODEPAGE = 1252;
    private static final POILogger LOG = POILogFactory.getLogger(Property.class);
    private long id;
    private long type;
    private Object value;

    public Property() {
    }

    public Property(Property p) {
        this(p.id, p.type, p.value);
    }

    public Property(long id, long type, Object value) {
        this.id = id;
        this.type = type;
        this.value = value;
    }

    public Property(long id, byte[] src, long offset, int length, int codepage) throws UnsupportedEncodingException {
        this.id = id;
        if (id == 0L) {
            throw new UnsupportedEncodingException("Dictionary not allowed here");
        }
        int o = (int)offset;
        this.type = LittleEndian.getUInt(src, o);
        o += 4;
        try {
            this.value = VariantSupport.read(src, o, length, (int)this.type, codepage);
        } catch (UnsupportedVariantTypeException ex) {
            VariantSupport.writeUnsupportedTypeMessage(ex);
            this.value = ex.getValue();
        }
    }

    public Property(long id, LittleEndianByteArrayInputStream leis, int length, int codepage) throws UnsupportedEncodingException {
        this.id = id;
        if (id == 0L) {
            throw new UnsupportedEncodingException("Dictionary not allowed here");
        }
        this.type = leis.readUInt();
        try {
            this.value = VariantSupport.read(leis, length, (int)this.type, codepage);
        } catch (UnsupportedVariantTypeException ex) {
            VariantSupport.writeUnsupportedTypeMessage(ex);
            this.value = ex.getValue();
        }
    }

    public long getID() {
        return this.id;
    }

    public void setID(long id) {
        this.id = id;
    }

    public long getType() {
        return this.type;
    }

    public void setType(long type) {
        this.type = type;
    }

    public Object getValue() {
        return this.value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    protected int getSize(int property) throws WritingNotSupportedException {
        int length = Variant.getVariantLength(this.type);
        if (length >= 0 || this.type == 0L) {
            return length;
        }
        if (length == -2) {
            throw new WritingNotSupportedException(this.type, null);
        }
        if (this.type == 30L || this.type == 31L) {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            try {
                length = this.write(bos, property) - 8;
                length += 4 - (length & 3) & 3;
                return length;
            } catch (IOException e) {
                throw new WritingNotSupportedException(this.type, this.value);
            }
        }
        throw new WritingNotSupportedException(this.type, this.value);
    }

    public boolean equals(Object o) {
        Class<?> pValueClass;
        if (!(o instanceof Property)) {
            return false;
        }
        Property p = (Property)o;
        Object pValue = p.getValue();
        long pId = p.getID();
        if (this.id != pId || this.id != 0L && !this.typesAreEqual(this.type, p.getType())) {
            return false;
        }
        if (this.value == null && pValue == null) {
            return true;
        }
        if (this.value == null || pValue == null) {
            return false;
        }
        Class<?> valueClass = this.value.getClass();
        if (!valueClass.isAssignableFrom(pValueClass = pValue.getClass()) && !pValueClass.isAssignableFrom(valueClass)) {
            return false;
        }
        if (this.value instanceof byte[]) {
            byte[] thisVal = (byte[])this.value;
            byte[] otherVal = (byte[])pValue;
            int len = Property.unpaddedLength(thisVal);
            if (len != Property.unpaddedLength(otherVal)) {
                return false;
            }
            for (int i = 0; i < len; ++i) {
                if (thisVal[i] == otherVal[i]) continue;
                return false;
            }
            return true;
        }
        return this.value.equals(pValue);
    }

    private static int unpaddedLength(byte[] buf) {
        int end = buf.length - (buf.length + 3) % 4;
        for (int i = buf.length; i > end; --i) {
            if (buf[i - 1] == 0) continue;
            return i;
        }
        return end;
    }

    private boolean typesAreEqual(long t1, long t2) {
        return t1 == t2 || t1 == 30L && t2 == 31L || t2 == 30L && t1 == 31L;
    }

    public int hashCode() {
        return Objects.hash(this.id, this.type, this.value);
    }

    public String toString() {
        return this.toString(1252, null);
    }

    public String toString(int codepage, PropertyIDMap idMap) {
        String idName;
        StringBuilder b = new StringBuilder();
        b.append("Property[");
        b.append("id: ");
        b.append(this.id);
        String string = idName = idMap == null ? null : idMap.get(this.id);
        if (idName == null) {
            idName = PropertyIDMap.getFallbackProperties().get(this.id);
        }
        if (idName != null) {
            b.append(" (");
            b.append(idName);
            b.append(")");
        }
        b.append(", type: ");
        b.append(this.getType());
        b.append(" (");
        b.append(this.getVariantName());
        b.append(") ");
        Object value = this.getValue();
        b.append(", value: ");
        if (value instanceof String) {
            b.append((String)value);
            b.append("\n");
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            try {
                this.write(bos, codepage);
            } catch (Exception e) {
                LOG.log(5, "can't serialize string", e);
            }
            if (bos.size() > 8) {
                String hex = HexDump.dump(bos.toByteArray(), -8L, 8);
                b.append(hex);
            }
        } else if (value instanceof byte[]) {
            b.append("\n");
            byte[] bytes = (byte[])value;
            if (bytes.length > 0) {
                String hex = HexDump.dump(bytes, 0L, 0);
                b.append(hex);
            }
        } else if (value instanceof Date) {
            Date d = (Date)value;
            long filetime = Filetime.dateToFileTime(d);
            if (Filetime.isUndefined(d)) {
                b.append("<undefined>");
            } else if (filetime >>> 32 == 0L) {
                long l = filetime * 100L;
                TimeUnit tu = TimeUnit.NANOSECONDS;
                long hr = tu.toHours(l);
                long min = tu.toMinutes(l -= TimeUnit.HOURS.toNanos(hr));
                long sec = tu.toSeconds(l -= TimeUnit.MINUTES.toNanos(min));
                long ms = tu.toMillis(l -= TimeUnit.SECONDS.toNanos(sec));
                String str = String.format(Locale.ROOT, "%02d:%02d:%02d.%03d", hr, min, sec, ms);
                b.append(str);
            } else {
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ROOT);
                df.setTimeZone(LocaleUtil.TIMEZONE_UTC);
                b.append(df.format(d));
            }
        } else if (this.type == 0L || this.type == 1L || value == null) {
            b.append("null");
        } else {
            b.append(value);
            String decoded = this.decodeValueFromID();
            if (decoded != null) {
                b.append(" (");
                b.append(decoded);
                b.append(")");
            }
        }
        b.append(']');
        return b.toString();
    }

    private String getVariantName() {
        if (this.getID() == 0L) {
            return "dictionary";
        }
        return Variant.getVariantName(this.getType());
    }

    private String decodeValueFromID() {
        try {
            switch ((int)this.getID()) {
                case 1: {
                    return CodePageUtil.codepageToEncoding(((Number)this.value).intValue());
                }
                case -2147483648: {
                    return LocaleUtil.getLocaleFromLCID(((Number)this.value).intValue());
                }
            }
        } catch (Exception e) {
            LOG.log(5, "Can't decode id " + this.getID());
        }
        return null;
    }

    public int write(OutputStream out, int codepage) throws IOException, WritingNotSupportedException {
        String csStr;
        int length = 0;
        long variantType = this.getType();
        if (variantType == 30L && codepage != 1200 && !Charset.forName(csStr = CodePageUtil.codepageToEncoding(codepage > 0 ? codepage : 1252)).newEncoder().canEncode((String)this.value)) {
            variantType = 31L;
        }
        LittleEndian.putUInt(variantType, out);
        length += 4;
        return length += VariantSupport.write(out, variantType, this.getValue(), codepage);
    }
}

