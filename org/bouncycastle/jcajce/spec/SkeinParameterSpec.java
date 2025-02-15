/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.jcajce.spec;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.security.spec.AlgorithmParameterSpec;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Integers;

public class SkeinParameterSpec
implements AlgorithmParameterSpec {
    public static final int PARAM_TYPE_KEY = 0;
    public static final int PARAM_TYPE_CONFIG = 4;
    public static final int PARAM_TYPE_PERSONALISATION = 8;
    public static final int PARAM_TYPE_PUBLIC_KEY = 12;
    public static final int PARAM_TYPE_KEY_IDENTIFIER = 16;
    public static final int PARAM_TYPE_NONCE = 20;
    public static final int PARAM_TYPE_MESSAGE = 48;
    public static final int PARAM_TYPE_OUTPUT = 63;
    private Map parameters;

    public SkeinParameterSpec() {
        this(new HashMap());
    }

    private SkeinParameterSpec(Map map) {
        this.parameters = Collections.unmodifiableMap(map);
    }

    public Map getParameters() {
        return this.parameters;
    }

    public byte[] getKey() {
        return Arrays.clone((byte[])this.parameters.get(Integers.valueOf(0)));
    }

    public byte[] getPersonalisation() {
        return Arrays.clone((byte[])this.parameters.get(Integers.valueOf(8)));
    }

    public byte[] getPublicKey() {
        return Arrays.clone((byte[])this.parameters.get(Integers.valueOf(12)));
    }

    public byte[] getKeyIdentifier() {
        return Arrays.clone((byte[])this.parameters.get(Integers.valueOf(16)));
    }

    public byte[] getNonce() {
        return Arrays.clone((byte[])this.parameters.get(Integers.valueOf(20)));
    }

    public static class Builder {
        private Map parameters = new HashMap();

        public Builder() {
        }

        public Builder(SkeinParameterSpec skeinParameterSpec) {
            for (Integer n : skeinParameterSpec.parameters.keySet()) {
                this.parameters.put(n, skeinParameterSpec.parameters.get(n));
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

        public SkeinParameterSpec build() {
            return new SkeinParameterSpec(this.parameters);
        }
    }
}

