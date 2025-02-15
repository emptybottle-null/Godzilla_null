/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.i18n;

import java.io.UnsupportedEncodingException;
import java.util.Locale;
import java.util.TimeZone;
import org.bouncycastle.i18n.MessageBundle;
import org.bouncycastle.i18n.MissingEntryException;

public class ErrorBundle
extends MessageBundle {
    public static final String SUMMARY_ENTRY = "summary";
    public static final String DETAIL_ENTRY = "details";

    public ErrorBundle(String string, String string2) throws NullPointerException {
        super(string, string2);
    }

    public ErrorBundle(String string, String string2, String string3) throws NullPointerException, UnsupportedEncodingException {
        super(string, string2, string3);
    }

    public ErrorBundle(String string, String string2, Object[] objectArray) throws NullPointerException {
        super(string, string2, objectArray);
    }

    public ErrorBundle(String string, String string2, String string3, Object[] objectArray) throws NullPointerException, UnsupportedEncodingException {
        super(string, string2, string3, objectArray);
    }

    public String getSummary(Locale locale, TimeZone timeZone) throws MissingEntryException {
        return this.getEntry(SUMMARY_ENTRY, locale, timeZone);
    }

    public String getSummary(Locale locale) throws MissingEntryException {
        return this.getEntry(SUMMARY_ENTRY, locale, TimeZone.getDefault());
    }

    public String getDetail(Locale locale, TimeZone timeZone) throws MissingEntryException {
        return this.getEntry(DETAIL_ENTRY, locale, timeZone);
    }

    public String getDetail(Locale locale) throws MissingEntryException {
        return this.getEntry(DETAIL_ENTRY, locale, TimeZone.getDefault());
    }
}

