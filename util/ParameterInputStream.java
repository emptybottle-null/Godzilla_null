/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package util;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import util.http.Parameter;

public class ParameterInputStream {
    private InputStream in;

    public ParameterInputStream(byte[] data) {
        this(new ByteArrayInputStream(data));
    }

    public ParameterInputStream(InputStream inputStream) {
        this.in = inputStream;
    }

    public Parameter readParameter() {
        return Parameter.unSerialize(this.in);
    }

    public static ParameterInputStream asParameterInputStream(byte[] data) {
        return new ParameterInputStream(new ByteArrayInputStream(data));
    }
}

