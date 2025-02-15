/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.SimpleTimeZone;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1OutputStream;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.StreamUtil;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Strings;

public class ASN1UTCTime
extends ASN1Primitive {
    private byte[] time;

    public static ASN1UTCTime getInstance(Object object) {
        if (object == null || object instanceof ASN1UTCTime) {
            return (ASN1UTCTime)object;
        }
        if (object instanceof byte[]) {
            try {
                return (ASN1UTCTime)ASN1UTCTime.fromByteArray((byte[])object);
            } catch (Exception exception) {
                throw new IllegalArgumentException("encoding error in getInstance: " + exception.toString());
            }
        }
        throw new IllegalArgumentException("illegal object in getInstance: " + object.getClass().getName());
    }

    public static ASN1UTCTime getInstance(ASN1TaggedObject aSN1TaggedObject, boolean bl) {
        ASN1Primitive aSN1Primitive = aSN1TaggedObject.getObject();
        if (bl || aSN1Primitive instanceof ASN1UTCTime) {
            return ASN1UTCTime.getInstance(aSN1Primitive);
        }
        return new ASN1UTCTime(((ASN1OctetString)aSN1Primitive).getOctets());
    }

    public ASN1UTCTime(String string) {
        this.time = Strings.toByteArray(string);
        try {
            this.getDate();
        } catch (ParseException parseException) {
            throw new IllegalArgumentException("invalid date string: " + parseException.getMessage());
        }
    }

    public ASN1UTCTime(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyMMddHHmmss'Z'");
        simpleDateFormat.setTimeZone(new SimpleTimeZone(0, "Z"));
        this.time = Strings.toByteArray(simpleDateFormat.format(date));
    }

    public ASN1UTCTime(Date date, Locale locale) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyMMddHHmmss'Z'", locale);
        simpleDateFormat.setTimeZone(new SimpleTimeZone(0, "Z"));
        this.time = Strings.toByteArray(simpleDateFormat.format(date));
    }

    ASN1UTCTime(byte[] byArray) {
        this.time = byArray;
    }

    public Date getDate() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyMMddHHmmssz");
        return simpleDateFormat.parse(this.getTime());
    }

    public Date getAdjustedDate() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmssz");
        simpleDateFormat.setTimeZone(new SimpleTimeZone(0, "Z"));
        return simpleDateFormat.parse(this.getAdjustedTime());
    }

    public String getTime() {
        String string = Strings.fromByteArray(this.time);
        if (string.indexOf(45) < 0 && string.indexOf(43) < 0) {
            if (string.length() == 11) {
                return string.substring(0, 10) + "00GMT+00:00";
            }
            return string.substring(0, 12) + "GMT+00:00";
        }
        int n = string.indexOf(45);
        if (n < 0) {
            n = string.indexOf(43);
        }
        String string2 = string;
        if (n == string.length() - 3) {
            string2 = string2 + "00";
        }
        if (n == 10) {
            return string2.substring(0, 10) + "00GMT" + string2.substring(10, 13) + ":" + string2.substring(13, 15);
        }
        return string2.substring(0, 12) + "GMT" + string2.substring(12, 15) + ":" + string2.substring(15, 17);
    }

    public String getAdjustedTime() {
        String string = this.getTime();
        if (string.charAt(0) < '5') {
            return "20" + string;
        }
        return "19" + string;
    }

    boolean isConstructed() {
        return false;
    }

    int encodedLength() {
        int n = this.time.length;
        return 1 + StreamUtil.calculateBodyLength(n) + n;
    }

    void encode(ASN1OutputStream aSN1OutputStream) throws IOException {
        aSN1OutputStream.write(23);
        int n = this.time.length;
        aSN1OutputStream.writeLength(n);
        for (int i = 0; i != n; ++i) {
            aSN1OutputStream.write(this.time[i]);
        }
    }

    boolean asn1Equals(ASN1Primitive aSN1Primitive) {
        if (!(aSN1Primitive instanceof ASN1UTCTime)) {
            return false;
        }
        return Arrays.areEqual(this.time, ((ASN1UTCTime)aSN1Primitive).time);
    }

    public int hashCode() {
        return Arrays.hashCode(this.time);
    }

    public String toString() {
        return Strings.fromByteArray(this.time);
    }
}

