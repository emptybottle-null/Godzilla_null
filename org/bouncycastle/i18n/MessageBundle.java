/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.i18n;

import java.io.UnsupportedEncodingException;
import java.util.Locale;
import java.util.TimeZone;
import org.bouncycastle.i18n.MissingEntryException;
import org.bouncycastle.i18n.TextBundle;

public class MessageBundle
extends TextBundle {
    public static final String TITLE_ENTRY = "title";

    public MessageBundle(String string, String string2) throws NullPointerException {
        super(string, string2);
    }

    public MessageBundle(String string, String string2, String string3) throws NullPointerException, UnsupportedEncodingException {
        super(string, string2, string3);
    }

    public MessageBundle(String string, String string2, Object[] objectArray) throws NullPointerException {
        super(string, string2, objectArray);
    }

    public MessageBundle(String string, String string2, String string3, Object[] objectArray) throws NullPointerException, UnsupportedEncodingException {
        super(string, string2, string3, objectArray);
    }

    public String getTitle(Locale locale, TimeZone timeZone) throws MissingEntryException {
        return this.getEntry(TITLE_ENTRY, locale, timeZone);
    }

    public String getTitle(Locale locale) throws MissingEntryException {
        return this.getEntry(TITLE_ENTRY, locale, TimeZone.getDefault());
    }
}

