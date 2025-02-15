/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.i18n;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.Format;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.TimeZone;
import org.bouncycastle.i18n.LocaleString;
import org.bouncycastle.i18n.MissingEntryException;
import org.bouncycastle.i18n.filter.Filter;
import org.bouncycastle.i18n.filter.TrustedInput;
import org.bouncycastle.i18n.filter.UntrustedInput;
import org.bouncycastle.i18n.filter.UntrustedUrlInput;

public class LocalizedMessage {
    protected final String id;
    protected final String resource;
    public static final String DEFAULT_ENCODING = "ISO-8859-1";
    protected String encoding = "ISO-8859-1";
    protected FilteredArguments arguments;
    protected FilteredArguments extraArgs = null;
    protected Filter filter = null;
    protected ClassLoader loader = null;

    public LocalizedMessage(String string, String string2) throws NullPointerException {
        if (string == null || string2 == null) {
            throw new NullPointerException();
        }
        this.id = string2;
        this.resource = string;
        this.arguments = new FilteredArguments();
    }

    public LocalizedMessage(String string, String string2, String string3) throws NullPointerException, UnsupportedEncodingException {
        if (string == null || string2 == null) {
            throw new NullPointerException();
        }
        this.id = string2;
        this.resource = string;
        this.arguments = new FilteredArguments();
        if (!Charset.isSupported(string3)) {
            throw new UnsupportedEncodingException("The encoding \"" + string3 + "\" is not supported.");
        }
        this.encoding = string3;
    }

    public LocalizedMessage(String string, String string2, Object[] objectArray) throws NullPointerException {
        if (string == null || string2 == null || objectArray == null) {
            throw new NullPointerException();
        }
        this.id = string2;
        this.resource = string;
        this.arguments = new FilteredArguments(objectArray);
    }

    public LocalizedMessage(String string, String string2, String string3, Object[] objectArray) throws NullPointerException, UnsupportedEncodingException {
        if (string == null || string2 == null || objectArray == null) {
            throw new NullPointerException();
        }
        this.id = string2;
        this.resource = string;
        this.arguments = new FilteredArguments(objectArray);
        if (!Charset.isSupported(string3)) {
            throw new UnsupportedEncodingException("The encoding \"" + string3 + "\" is not supported.");
        }
        this.encoding = string3;
    }

    public String getEntry(String string, Locale locale, TimeZone timeZone) throws MissingEntryException {
        String string2 = this.id;
        if (string != null) {
            string2 = string2 + "." + string;
        }
        try {
            ResourceBundle resourceBundle = this.loader == null ? ResourceBundle.getBundle(this.resource, locale) : ResourceBundle.getBundle(this.resource, locale, this.loader);
            String string3 = resourceBundle.getString(string2);
            if (!this.encoding.equals(DEFAULT_ENCODING)) {
                string3 = new String(string3.getBytes(DEFAULT_ENCODING), this.encoding);
            }
            if (!this.arguments.isEmpty()) {
                string3 = this.formatWithTimeZone(string3, this.arguments.getFilteredArgs(locale), locale, timeZone);
            }
            string3 = this.addExtraArgs(string3, locale);
            return string3;
        } catch (MissingResourceException missingResourceException) {
            throw new MissingEntryException("Can't find entry " + string2 + " in resource file " + this.resource + ".", this.resource, string2, locale, this.loader != null ? this.loader : this.getClassLoader());
        } catch (UnsupportedEncodingException unsupportedEncodingException) {
            throw new RuntimeException(unsupportedEncodingException);
        }
    }

    protected String formatWithTimeZone(String string, Object[] objectArray, Locale locale, TimeZone timeZone) {
        MessageFormat messageFormat = new MessageFormat(" ");
        messageFormat.setLocale(locale);
        messageFormat.applyPattern(string);
        if (!timeZone.equals(TimeZone.getDefault())) {
            Format[] formatArray = messageFormat.getFormats();
            for (int i = 0; i < formatArray.length; ++i) {
                if (!(formatArray[i] instanceof DateFormat)) continue;
                DateFormat dateFormat = (DateFormat)formatArray[i];
                dateFormat.setTimeZone(timeZone);
                messageFormat.setFormat(i, dateFormat);
            }
        }
        return messageFormat.format(objectArray);
    }

    protected String addExtraArgs(String string, Locale locale) {
        if (this.extraArgs != null) {
            StringBuffer stringBuffer = new StringBuffer(string);
            Object[] objectArray = this.extraArgs.getFilteredArgs(locale);
            for (int i = 0; i < objectArray.length; ++i) {
                stringBuffer.append(objectArray[i]);
            }
            string = stringBuffer.toString();
        }
        return string;
    }

    public void setFilter(Filter filter) {
        this.arguments.setFilter(filter);
        if (this.extraArgs != null) {
            this.extraArgs.setFilter(filter);
        }
        this.filter = filter;
    }

    public Filter getFilter() {
        return this.filter;
    }

    public void setClassLoader(ClassLoader classLoader) {
        this.loader = classLoader;
    }

    public ClassLoader getClassLoader() {
        return this.loader;
    }

    public String getId() {
        return this.id;
    }

    public String getResource() {
        return this.resource;
    }

    public Object[] getArguments() {
        return this.arguments.getArguments();
    }

    public void setExtraArgument(Object object) {
        this.setExtraArguments(new Object[]{object});
    }

    public void setExtraArguments(Object[] objectArray) {
        if (objectArray != null) {
            this.extraArgs = new FilteredArguments(objectArray);
            this.extraArgs.setFilter(this.filter);
        } else {
            this.extraArgs = null;
        }
    }

    public Object[] getExtraArgs() {
        return this.extraArgs == null ? null : this.extraArgs.getArguments();
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Resource: \"").append(this.resource);
        stringBuffer.append("\" Id: \"").append(this.id).append("\"");
        stringBuffer.append(" Arguments: ").append(this.arguments.getArguments().length).append(" normal");
        if (this.extraArgs != null && this.extraArgs.getArguments().length > 0) {
            stringBuffer.append(", ").append(this.extraArgs.getArguments().length).append(" extra");
        }
        stringBuffer.append(" Encoding: ").append(this.encoding);
        stringBuffer.append(" ClassLoader: ").append(this.loader);
        return stringBuffer.toString();
    }

    protected class FilteredArguments {
        protected static final int NO_FILTER = 0;
        protected static final int FILTER = 1;
        protected static final int FILTER_URL = 2;
        protected Filter filter = null;
        protected boolean[] isLocaleSpecific;
        protected int[] argFilterType;
        protected Object[] arguments;
        protected Object[] unpackedArgs;
        protected Object[] filteredArgs;

        FilteredArguments() {
            this(new Object[0]);
        }

        FilteredArguments(Object[] objectArray) {
            this.arguments = objectArray;
            this.unpackedArgs = new Object[objectArray.length];
            this.filteredArgs = new Object[objectArray.length];
            this.isLocaleSpecific = new boolean[objectArray.length];
            this.argFilterType = new int[objectArray.length];
            for (int i = 0; i < objectArray.length; ++i) {
                if (objectArray[i] instanceof TrustedInput) {
                    this.unpackedArgs[i] = ((TrustedInput)objectArray[i]).getInput();
                    this.argFilterType[i] = 0;
                } else if (objectArray[i] instanceof UntrustedInput) {
                    this.unpackedArgs[i] = ((UntrustedInput)objectArray[i]).getInput();
                    this.argFilterType[i] = objectArray[i] instanceof UntrustedUrlInput ? 2 : 1;
                } else {
                    this.unpackedArgs[i] = objectArray[i];
                    this.argFilterType[i] = 1;
                }
                this.isLocaleSpecific[i] = this.unpackedArgs[i] instanceof LocaleString;
            }
        }

        public boolean isEmpty() {
            return this.unpackedArgs.length == 0;
        }

        public Object[] getArguments() {
            return this.arguments;
        }

        public Object[] getFilteredArgs(Locale locale) {
            Object[] objectArray = new Object[this.unpackedArgs.length];
            for (int i = 0; i < this.unpackedArgs.length; ++i) {
                Object object;
                if (this.filteredArgs[i] != null) {
                    object = this.filteredArgs[i];
                } else {
                    object = this.unpackedArgs[i];
                    if (this.isLocaleSpecific[i]) {
                        object = ((LocaleString)object).getLocaleString(locale);
                        object = this.filter(this.argFilterType[i], object);
                    } else {
                        this.filteredArgs[i] = object = this.filter(this.argFilterType[i], object);
                    }
                }
                objectArray[i] = object;
            }
            return objectArray;
        }

        private Object filter(int n, Object object) {
            if (this.filter != null) {
                Object object2 = null == object ? "null" : object;
                switch (n) {
                    case 0: {
                        return object2;
                    }
                    case 1: {
                        return this.filter.doFilter(object2.toString());
                    }
                    case 2: {
                        return this.filter.doFilterUrl(object2.toString());
                    }
                }
                return null;
            }
            return object;
        }

        public Filter getFilter() {
            return this.filter;
        }

        public void setFilter(Filter filter) {
            if (filter != this.filter) {
                for (int i = 0; i < this.unpackedArgs.length; ++i) {
                    this.filteredArgs[i] = null;
                }
            }
            this.filter = filter;
        }
    }
}

