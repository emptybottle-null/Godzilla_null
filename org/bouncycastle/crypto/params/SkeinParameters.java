/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.params;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Locale;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.util.Integers;

public class SkeinParameters
implements CipherParameters {
    public static final int PARAM_TYPE_KEY = 0;
    public static final int PARAM_TYPE_CONFIG = 4;
    public static final int PARAM_TYPE_PERSONALISATION = 8;
    public static final int PARAM_TYPE_PUBLIC_KEY = 12;
    public static final int PARAM_TYPE_KEY_IDENTIFIER = 16;
    public static final int PARAM_TYPE_NONCE = 20;
    public static final int PARAM_TYPE_MESSAGE = 48;
    public static final int PARAM_TYPE_OUTPUT = 63;
    private Hashtable parameters;

    public SkeinParameters() {
        this(new Hashtable());
    }

    private SkeinParameters(Hashtable hashtable) {
        this.parameters = hashtable;
    }

    public Hashtable getParameters() {
        return this.parameters;
    }

    public byte[] getKey() {
        return (byte[])this.parameters.get(Integers.valueOf(0));
    }

    public byte[] getPersonalisation() {
        return (byte[])this.parameters.get(Integers.valueOf(8));
    }

    public byte[] getPublicKey() {
        return (byte[])this.parameters.get(Integers.valueOf(12));
    }

    public byte[] getKeyIdentifier() {
        return (byte[])this.parameters.get(Integers.valueOf(16));
    }

    public byte[] getNonce() {
        return (byte[])this.parameters.get(Integers.valueOf(20));
    }

    public static class Builder {
        private Hashtable parameters = new Hashtable();

        public Builder() {
        }

        public Builder(Hashtable hashtable) {
            Enumeration enumeration = hashtable.keys();
            while (enumeration.hasMoreElements()) {
                Integer n = (Integer)enumeration.nextElement();
                this.parameters.put(n, hashtable.get(n));
            }
        }

        public Builder(SkeinParameters skeinParameters) {
            Enumeration enumeration = skeinParameters.parameters.keys();
            while (enumeration.hasMoreElements()) {
                Integer n = (Integer)enumeration.nextElement();
                this.parameters.put(n, skeinParameters.parameters.get(n));
            }
        }

        public Builder set(int n, byte[] byArray) {
            if (byArray == null) {
                throw new IllegalArgumentException("Parameter value must not be null.");
            }
            if (n != 0 && (n <= 4 || n >= 63 || n == 48)) {
                throw new IllegalArgumentException("Parameter types must be in the range 0,5..47,49..62.");
            }
            if (n == 4) {
                throw new IllegalArgumentException("Parameter type 4 is reserved for internal use.");
            }
            this.parameters.put(Integers.valueOf(n), byArray);
            return this;
        }

        public Builder setKey(byte[] byArray) {
            return this.set(0, byArray);
        }

        public Builder setPersonalisation(byte[] byArray) {
            return this.set(8, byArray);
        }

        public Builder setPersonalisation(Date date, String string, String string2) {
            try {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter((OutputStream)byteArrayOutputStream, "UTF-8");
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYYMMDD");
                outputStreamWriter.write(simpleDateFormat.format(date));
                outputStreamWriter.write(" ");
                outputStreamWriter.write(string);
                outputStreamWriter.write(" ");
                outputStreamWriter.write(string2);
                outputStreamWriter.close();
                return this.set(8, byteArrayOutputStream.toByteArray());
            } catch (IOException iOException) {
                throw new IllegalStateException("Byte I/O failed: " + iOException);
            }
        }

        public Builder setPersonalisation(Date date, Locale locale, String string, String string2) {
            try {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter((OutputStream)byteArrayOutputStream, "UTF-8");
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYYMMDD", locale);
                outputStreamWriter.write(simpleDateFormat.format(date));
                outputStreamWriter.write(" ");
                outputStreamWriter.write(string);
                outputStreamWriter.write(" ");
                outputStreamWriter.write(string2);
                outputStreamWriter.close();
                return this.set(8, byteArrayOutputStream.toByteArray());
            } catch (IOException iOException) {
                throw new IllegalStateException("Byte I/O failed: " + iOException);
            }
        }

        public Builder setPublicKey(byte[] byArray) {
            return this.set(12, byArray);
        }

        public Builder setKeyIdentifier(byte[] byArray) {
            return this.set(16, byArray);
        }

        public Builder setNonce(byte[] byArray) {
            return this.set(20, byArray);
        }

        public SkeinParameters build() {
            return new SkeinParameters(this.parameters);
        }
    }
}

