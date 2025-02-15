/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.util.io.pem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.bouncycastle.util.io.pem.PemGenerationException;
import org.bouncycastle.util.io.pem.PemObjectGenerator;

public class PemObject
implements PemObjectGenerator {
    private static final List EMPTY_LIST = Collections.unmodifiableList(new ArrayList());
    private String type;
    private List headers;
    private byte[] content;

    public PemObject(String string, byte[] byArray) {
        this(string, EMPTY_LIST, byArray);
    }

    public PemObject(String string, List list, byte[] byArray) {
        this.type = string;
        this.headers = Collections.unmodifiableList(list);
        this.content = byArray;
    }

    public String getType() {
        return this.type;
    }

    public List getHeaders() {
        return this.headers;
    }

    public byte[] getContent() {
        return this.content;
    }

    public PemObject generate() throws PemGenerationException {
        return this;
    }
}

