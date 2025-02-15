/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package core.imp;

import core.shell.ShellEntity;

public interface Cryption {
    public void init(ShellEntity var1);

    public byte[] encode(byte[] var1);

    public byte[] decode(byte[] var1);

    public boolean isSendRLData();

    public byte[] generate(String var1, String var2);

    public boolean check();
}

