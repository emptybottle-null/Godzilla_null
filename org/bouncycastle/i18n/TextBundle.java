/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.i18n;

import java.io.UnsupportedEncodingException;
import java.util.Locale;
import java.util.TimeZone;
import org.bouncycastle.i18n.LocalizedMessage;
import org.bouncycastle.i18n.MissingEntryException;

public class TextBundle
extends LocalizedMessage {
    public static final String TEXT_ENTRY = "text";

    public TextBundle(String string, String string2) throws NullPointerException {
        super(string, string2);
    }

    public TextBundle(String string, String string2, String string3) throws NullPointerException, UnsupportedEncodingException {
        super(string, string2, string3);
    }

    public TextBundle(String string, String string2, Object[] objectArray) throws NullPointerException {
        super(string, string2, objectArray);
    }

    public TextBundle(String string, String string2, String string3, Object[] objectArray) throws NullPointerException, UnsupportedEncodingException {
        super(string, string2, string3, objectArray);
    }

    public String getText(Locale locale, TimeZone timeZone) throws MissingEntryException {
        return this.getEntry(TEXT_ENTRY, locale, timeZone);
    }

    public String getText(Locale locale) throws MissingEntryException {
        return this.getEntry(TEXT_ENTRY, locale, TimeZone.getDefault());
    }
}

