/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.est;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class ESTException
extends IOException {
    private Throwable cause;
    private InputStream body;
    private int statusCode;
    private static final long MAX_ERROR_BODY = 8192L;

    public ESTException(String string) {
        this(string, null);
    }

    public ESTException(String string, Throwable throwable) {
        super(string);
        this.cause = throwable;
        this.body = null;
        this.statusCode = 0;
    }

    public ESTException(String string, Throwable throwable, int n, InputStream inputStream) {
        super(string);
        this.cause = throwable;
        this.statusCode = n;
        if (inputStream != null) {
            byte[] byArray = new byte[8192];
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                int n2 = inputStream.read(byArray);
                while (n2 >= 0) {
                    if ((long)(byteArrayOutputStream.size() + n2) > 8192L) {
                        n2 = 8192 - byteArrayOutputStream.size();
                        byteArrayOutputStream.write(byArray, 0, n2);
                        break;
                    }
                    byteArrayOutputStream.write(byArray, 0, n2);
                    n2 = inputStream.read(byArray);
                }
                byteArrayOutputStream.flush();
                byteArrayOutputStream.close();
                this.body = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
                inputStream.close();
            } catch (Exception exception) {}
        } else {
            this.body = null;
        }
    }

    public Throwable getCause() {
        return this.cause;
    }

    public String getMessage() {
        return super.getMessage() + " HTTP Status Code: " + this.statusCode;
    }

    public InputStream getBody() {
        return this.body;
    }

    public int getStatusCode() {
        return this.statusCode;
    }
}

