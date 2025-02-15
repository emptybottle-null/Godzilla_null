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
import java.util.TimeZone;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1OutputStream;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.StreamUtil;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Strings;

public class ASN1GeneralizedTime
extends ASN1Primitive {
    private byte[] time;

    public static ASN1GeneralizedTime getInstance(Object object) {
        if (object == null || object instanceof ASN1GeneralizedTime) {
            return (ASN1GeneralizedTime)object;
        }
        if (object instanceof byte[]) {
            try {
                return (ASN1GeneralizedTime)ASN1GeneralizedTime.fromByteArray((byte[])object);
            } catch (Exception exception) {
                throw new IllegalArgumentException("encoding error in getInstance: " + exception.toString());
            }
        }
        throw new IllegalArgumentException("illegal object in getInstance: " + object.getClass().getName());
    }

    public static ASN1GeneralizedTime getInstance(ASN1TaggedObject aSN1TaggedObject, boolean bl) {
        ASN1Primitive aSN1Primitive = aSN1TaggedObject.getObject();
        if (bl || aSN1Primitive instanceof ASN1GeneralizedTime) {
            return ASN1GeneralizedTime.getInstance(aSN1Primitive);
        }
        return new ASN1GeneralizedTime(((ASN1OctetString)aSN1Primitive).getOctets());
    }

    public ASN1GeneralizedTime(String string) {
        this.time = Strings.toByteArray(string);
        try {
            this.getDate();
        } catch (ParseException parseException) {
            throw new IllegalArgumentException("invalid date string: " + parseException.getMessage());
        }
    }

    public ASN1GeneralizedTime(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss'Z'");
        simpleDateFormat.setTimeZone(new SimpleTimeZone(0, "Z"));
        this.time = Strings.toByteArray(simpleDateFormat.format(date));
    }

    public ASN1GeneralizedTime(Date date, Locale locale) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss'Z'", locale);
        simpleDateFormat.setTimeZone(new SimpleTimeZone(0, "Z"));
        this.time = Strings.toByteArray(simpleDateFormat.format(date));
    }

    ASN1GeneralizedTime(byte[] byArray) {
        this.time = byArray;
    }

    public String getTimeString() {
        return Strings.fromByteArray(this.time);
    }

    public String getTime() {
        String string = Strings.fromByteArray(this.time);
        if (string.charAt(string.length() - 1) == 'Z') {
            return string.substring(0, string.length() - 1) + "GMT+00:00";
        }
        int n = string.length() - 5;
        char c = string.charAt(n);
        if (c == '-' || c == '+') {
            return string.substring(0, n) + "GMT" + string.substring(n, n + 3) + ":" + string.substring(n + 3);
        }
        n = string.length() - 3;
        c = string.charAt(n);
        if (c == '-' || c == '+') {
            return string.substring(0, n) + "GMT" + string.substring(n) + ":00";
        }
        return string + this.calculateGMTOffset();
    }

    private String calculateGMTOffset() {
        String string = "+";
        TimeZone timeZone = TimeZone.getDefault();
        int n = timeZone.getRawOffset();
        if (n < 0) {
            string = "-";
            n = -n;
        }
        int n2 = n / 3600000;
        int n3 = (n - n2 * 60 * 60 * 1000) / 60000;
        try {
            if (timeZone.useDaylightTime() && timeZone.inDaylightTime(this.getDate())) {
                n2 += string.equals("+") ? 1 : -1;
            }
        } catch (ParseException parseException) {
            // empty catch block
        }
        return "GMT" + string + this.convert(n2) + ":" + this.convert(n3);
    }

    private String convert(int n) {
        if (n < 10) {
            return "0" + n;
        }
        return Integer.toString(n);
    }

    public Date getDate() throws ParseException {
        SimpleDateFormat simpleDateFormat;
        String string;
        String string2 = string = Strings.fromByteArray(this.time);
        if (string.endsWith("Z")) {
            simpleDateFormat = this.hasFractionalSeconds() ? new SimpleDateFormat("yyyyMMddHHmmss.SSS'Z'") : new SimpleDateFormat("yyyyMMddHHmmss'Z'");
            simpleDateFormat.setTimeZone(new SimpleTimeZone(0, "Z"));
        } else if (string.indexOf(45) > 0 || string.indexOf(43) > 0) {
            string2 = this.getTime();
            simpleDateFormat = this.hasFractionalSeconds() ? new SimpleDateFormat("yyyyMMddHHmmss.SSSz") : new SimpleDateFormat("yyyyMMddHHmmssz");
            simpleDateFormat.setTimeZone(new SimpleTimeZone(0, "Z"));
        } else {
            simpleDateFormat = this.hasFractionalSeconds() ? new SimpleDateFormat("yyyyMMddHHmmss.SSS") : new SimpleDateFormat("yyyyMMddHHmmss");
            simpleDateFormat.setTimeZone(new SimpleTimeZone(0, TimeZone.getDefault().getID()));
        }
        if (this.hasFractionalSeconds()) {
            char c;
            int n;
            String string3 = string2.substring(14);
            for (n = 1; n < string3.length() && '0' <= (c = string3.charAt(n)) && c <= '9'; ++n) {
            }
            if (n - 1 > 3) {
                string3 = string3.substring(0, 4) + string3.substring(n);
                string2 = string2.substring(0, 14) + string3;
            } else if (n - 1 == 1) {
                string3 = string3.substring(0, n) + "00" + string3.substring(n);
                string2 = string2.substring(0, 14) + string3;
            } else if (n - 1 == 2) {
                string3 = string3.substring(0, n) + "0" + string3.substring(n);
                string2 = string2.substring(0, 14) + string3;
            }
        }
        return simpleDateFormat.parse(string2);
    }

    private boolean hasFractionalSeconds() {
        for (int i = 0; i != this.time.length; ++i) {
            if (this.time[i] != 46 || i != 14) continue;
            return true;
        }
        return false;
    }

    boolean isConstructed() {
        return false;
    }

    int encodedLength() {
        int n = this.time.length;
        return 1 + StreamUtil.calculateBodyLength(n) + n;
    }

    void encode(ASN1OutputStream aSN1OutputStream) throws IOException {
        aSN1OutputStream.writeEncoded(24, this.time);
    }

    boolean asn1Equals(ASN1Primitive aSN1Primitive) {
        if (!(aSN1Primitive instanceof ASN1GeneralizedTime)) {
            return false;
        }
        return Arrays.areEqual(this.time, ((ASN1GeneralizedTime)aSN1Primitive).time);
    }

    public int hashCode() {
        return Arrays.hashCode(this.time);
    }
}

