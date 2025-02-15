/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package core.c2profile.cryption;

import core.Encoding;
import core.imp.Cryption;
import core.imp.Payload;
import core.shell.ShellEntity;

public class C2Channel
implements Cryption {
    public ShellEntity shellEntity;
    public Payload payload;
    public Encoding encoding;

    @Override
    public void init(ShellEntity context) {
        this.shellEntity = context;
        this.shellEntity.getEncodingModule();
        this.payload = this.shellEntity.getPayloadModule();
        this.encoding = this.shellEntity.getEncodingModule();
    }

    @Override
    public byte[] encode(byte[] data) {
        return new byte[0];
    }

    @Override
    public byte[] decode(byte[] data) {
        return new byte[0];
    }

    @Override
    public boolean isSendRLData() {
        return false;
    }

    @Override
    public byte[] generate(String password, String secretKey) {
        return new byte[0];
    }

    @Override
    public boolean check() {
        return false;
    }
}

