/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans;

public interface PrePostExtension {
    public static final int OPERATION_SET = 1;
    public static final int OPERATION_INSERT = 2;
    public static final int OPERATION_REMOVE = 3;

    public String getStaticHandler();

    public boolean hasPreCall();

    public boolean hasPostCall();
}

