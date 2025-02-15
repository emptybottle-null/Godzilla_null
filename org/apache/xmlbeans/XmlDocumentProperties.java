/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans;

public abstract class XmlDocumentProperties {
    public static final Object SOURCE_NAME = new Object();
    public static final Object ENCODING = new Object();
    public static final Object VERSION = new Object();
    public static final Object STANDALONE = new Object();
    public static final Object DOCTYPE_NAME = new Object();
    public static final Object DOCTYPE_PUBLIC_ID = new Object();
    public static final Object DOCTYPE_SYSTEM_ID = new Object();
    public static final Object MESSAGE_DIGEST = new Object();

    public void setSourceName(String sourceName) {
        this.put(SOURCE_NAME, sourceName);
    }

    public String getSourceName() {
        return (String)this.get(SOURCE_NAME);
    }

    public void setEncoding(String encoding) {
        this.put(ENCODING, encoding);
    }

    public String getEncoding() {
        return (String)this.get(ENCODING);
    }

    public void setVersion(String version) {
        this.put(VERSION, version);
    }

    public String getVersion() {
        return (String)this.get(VERSION);
    }

    public void setStandalone(boolean standalone) {
        this.put(STANDALONE, standalone ? "true" : null);
    }

    public boolean getStandalone() {
        return this.get(STANDALONE) != null;
    }

    public void setDoctypeName(String doctypename) {
        this.put(DOCTYPE_NAME, doctypename);
    }

    public String getDoctypeName() {
        return (String)this.get(DOCTYPE_NAME);
    }

    public void setDoctypePublicId(String publicid) {
        this.put(DOCTYPE_PUBLIC_ID, publicid);
    }

    public String getDoctypePublicId() {
        return (String)this.get(DOCTYPE_PUBLIC_ID);
    }

    public void setDoctypeSystemId(String systemid) {
        this.put(DOCTYPE_SYSTEM_ID, systemid);
    }

    public String getDoctypeSystemId() {
        return (String)this.get(DOCTYPE_SYSTEM_ID);
    }

    public void setMessageDigest(byte[] digest) {
        this.put(MESSAGE_DIGEST, digest);
    }

    public byte[] getMessageDigest() {
        return (byte[])this.get(MESSAGE_DIGEST);
    }

    public abstract Object put(Object var1, Object var2);

    public abstract Object get(Object var1);

    public abstract Object remove(Object var1);
}

