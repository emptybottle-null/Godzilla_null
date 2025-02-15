/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xwpf.usermodel;

public class XWPFHyperlink {
    String id;
    String url;

    public XWPFHyperlink(String id, String url) {
        this.id = id;
        this.url = url;
    }

    public String getId() {
        return this.id;
    }

    public String getURL() {
        return this.url;
    }
}

