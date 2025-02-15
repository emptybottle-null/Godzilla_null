/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans;

public interface InterfaceExtension {
    public String getInterface();

    public String getStaticHandler();

    public MethodSignature[] getMethods();

    public static interface MethodSignature {
        public String getName();

        public String getReturnType();

        public String[] getParameterTypes();

        public String[] getExceptionTypes();
    }
}

