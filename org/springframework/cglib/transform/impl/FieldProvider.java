/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.cglib.transform.impl;

public interface FieldProvider {
    public String[] getFieldNames();

    public Class[] getFieldTypes();

    public void setField(int var1, Object var2);

    public Object getField(int var1);

    public void setField(String var1, Object var2);

    public Object getField(String var1);
}

