/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.xml.stream;

public interface Location {
    public int getColumnNumber();

    public int getLineNumber();

    public String getPublicId();

    public String getSystemId();
}

