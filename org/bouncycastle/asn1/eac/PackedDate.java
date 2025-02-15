/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.eac;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.SimpleTimeZone;
import org.bouncycastle.util.Arrays;

public class PackedDate {
    private byte[] time;

    public PackedDate(String string) {
        this.time = this.convert(string);
    }

    public PackedDate(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyMMdd'Z'");
        simpleDateFormat.setTimeZone(new SimpleTimeZone(0, "Z"));
        this.time = this.convert(simpleDateFormat.format(date));
    }

    public PackedDate(Date date, Locale locale) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyMMdd'Z'", locale);
        simpleDateFormat.setTimeZone(new SimpleTimeZone(0, "Z"));
        this.time = this.convert(simpleDateFormat.format(date));
    }

    private byte[] convert(String string) {
        char[] cArray = string.toCharArray();
        byte[] byArray = new byte[6];
        for (int i = 0; i != 6; ++i) {
            byArray[i] = (byte)(cArray[i] - 48);
        }
        return byArray;
    }

    PackedDate(byte[] byArray) {
        this.time = byArray;
    }

    public Date getDate() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        return simpleDateFormat.parse("20" + this.toString());
    }

    public int hashCode() {
        return Arrays.hashCode(this.time);
    }

    public boolean equals(Object object) {
        if (!(object instanceof PackedDate)) {
            return false;
        }
        PackedDate packedDate = (PackedDate)object;
        return Arrays.areEqual(this.time, packedDate.time);
    }

    public String toString() {
        char[] cArray = new char[this.time.length];
        for (int i = 0; i != cArray.length; ++i) {
            cArray[i] = (char)((this.time[i] & 0xFF) + 48);
        }
        return new String(cArray);
    }

    public byte[] getEncoding() {
        return Arrays.clone(this.time);
    }
}

