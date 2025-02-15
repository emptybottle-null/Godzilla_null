/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.i18n;

import java.net.URL;
import java.net.URLClassLoader;
import java.util.Locale;

public class MissingEntryException
extends RuntimeException {
    protected final String resource;
    protected final String key;
    protected final ClassLoader loader;
    protected final Locale locale;
    private String debugMsg;

    public MissingEntryException(String string, String string2, String string3, Locale locale, ClassLoader classLoader) {
        super(string);
        this.resource = string2;
        this.key = string3;
        this.locale = locale;
        this.loader = classLoader;
    }

    public MissingEntryException(String string, Throwable throwable, String string2, String string3, Locale locale, ClassLoader classLoader) {
        super(string, throwable);
        this.resource = string2;
        this.key = string3;
        this.locale = locale;
        this.loader = classLoader;
    }

    public String getKey() {
        return this.key;
    }

    public String getResource() {
        return this.resource;
    }

    public ClassLoader getClassLoader() {
        return this.loader;
    }

    public Locale getLocale() {
        return this.locale;
    }

    public String getDebugMsg() {
        if (this.debugMsg == null) {
            this.debugMsg = "Can not find entry " + this.key + " in resource file " + this.resource + " for the locale " + this.locale + ".";
            if (this.loader instanceof URLClassLoader) {
                URL[] uRLArray = ((URLClassLoader)this.loader).getURLs();
                this.debugMsg = this.debugMsg + " The following entries in the classpath were searched: ";
                for (int i = 0; i != uRLArray.length; ++i) {
                    this.debugMsg = this.debugMsg + uRLArray[i] + " ";
                }
            }
        }
        return this.debugMsg;
    }
}

