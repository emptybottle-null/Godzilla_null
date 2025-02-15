/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.examples;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.SecureRandom;
import org.bouncycastle.crypto.CryptoException;
import org.bouncycastle.crypto.KeyGenerationParameters;
import org.bouncycastle.crypto.engines.DESedeEngine;
import org.bouncycastle.crypto.generators.DESedeKeyGenerator;
import org.bouncycastle.crypto.modes.CBCBlockCipher;
import org.bouncycastle.crypto.paddings.PaddedBufferedBlockCipher;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.util.encoders.Hex;

public class DESExample {
    private boolean encrypt = true;
    private PaddedBufferedBlockCipher cipher = null;
    private BufferedInputStream in = null;
    private BufferedOutputStream out = null;
    private byte[] key = null;

    public static void main(String[] stringArray) {
        DESExample dESExample;
        boolean bl = true;
        String string = null;
        String string2 = null;
        String string3 = null;
        if (stringArray.length < 2) {
            dESExample = new DESExample();
            System.err.println("Usage: java " + dESExample.getClass().getName() + " infile outfile [keyfile]");
            System.exit(1);
        }
        string3 = "deskey.dat";
        string = stringArray[0];
        string2 = stringArray[1];
        if (stringArray.length > 2) {
            bl = false;
            string3 = stringArray[2];
        }
        dESExample = new DESExample(string, string2, string3, bl);
        dESExample.process();
    }

    public DESExample() {
    }

    public DESExample(String string, String string2, String string3, boolean bl) {
        Object object;
        this.encrypt = bl;
        try {
            this.in = new BufferedInputStream(new FileInputStream(string));
        } catch (FileNotFoundException fileNotFoundException) {
            System.err.println("Input file not found [" + string + "]");
            System.exit(1);
        }
        try {
            this.out = new BufferedOutputStream(new FileOutputStream(string2));
        } catch (IOException iOException) {
            System.err.println("Output file not created [" + string2 + "]");
            System.exit(1);
        }
        if (bl) {
            try {
                object = null;
                try {
                    object = new SecureRandom();
                    ((SecureRandom)object).setSeed("www.bouncycastle.org".getBytes());
                } catch (Exception exception) {
                    System.err.println("Hmmm, no SHA1PRNG, you need the Sun implementation");
                    System.exit(1);
                }
                KeyGenerationParameters keyGenerationParameters = new KeyGenerationParameters((SecureRandom)object, 192);
                DESedeKeyGenerator dESedeKeyGenerator = new DESedeKeyGenerator();
                dESedeKeyGenerator.init(keyGenerationParameters);
                this.key = dESedeKeyGenerator.generateKey();
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(string3));
                byte[] byArray = Hex.encode(this.key);
                bufferedOutputStream.write(byArray, 0, byArray.length);
                bufferedOutputStream.flush();
                bufferedOutputStream.close();
            } catch (IOException iOException) {
                System.err.println("Could not decryption create key file [" + string3 + "]");
                System.exit(1);
            }
        } else {
            try {
                object = new BufferedInputStream(new FileInputStream(string3));
                int n = ((BufferedInputStream)object).available();
                byte[] byArray = new byte[n];
                ((BufferedInputStream)object).read(byArray, 0, n);
                this.key = Hex.decode(byArray);
            } catch (IOException iOException) {
                System.err.println("Decryption key file not found, or not valid [" + string3 + "]");
                System.exit(1);
            }
        }
    }

    private void process() {
        this.cipher = new PaddedBufferedBlockCipher(new CBCBlockCipher(new DESedeEngine()));
        if (this.encrypt) {
            this.performEncrypt(this.key);
        } else {
            this.performDecrypt(this.key);
        }
        try {
            this.in.close();
            this.out.flush();
            this.out.close();
        } catch (IOException iOException) {
            System.err.println("exception closing resources: " + iOException.getMessage());
        }
    }

    private void performEncrypt(byte[] byArray) {
        this.cipher.init(true, new KeyParameter(byArray));
        int n = 47;
        int n2 = this.cipher.getOutputSize(n);
        byte[] byArray2 = new byte[n];
        byte[] byArray3 = new byte[n2];
        try {
            int n3;
            int n4;
            byte[] byArray4 = null;
            while ((n4 = this.in.read(byArray2, 0, n)) > 0) {
                n3 = this.cipher.processBytes(byArray2, 0, n4, byArray3, 0);
                if (n3 <= 0) continue;
                byArray4 = Hex.encode(byArray3, 0, n3);
                this.out.write(byArray4, 0, byArray4.length);
                this.out.write(10);
            }
            try {
                n3 = this.cipher.doFinal(byArray3, 0);
                if (n3 > 0) {
                    byArray4 = Hex.encode(byArray3, 0, n3);
                    this.out.write(byArray4, 0, byArray4.length);
                    this.out.write(10);
                }
            } catch (CryptoException cryptoException) {}
        } catch (IOException iOException) {
            iOException.printStackTrace();
        }
    }

    private void performDecrypt(byte[] byArray) {
        this.cipher.init(false, new KeyParameter(byArray));
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(this.in));
        try {
            int n;
            byte[] byArray2 = null;
            byte[] byArray3 = null;
            String string = null;
            while ((string = bufferedReader.readLine()) != null) {
                byArray2 = Hex.decode(string);
                n = this.cipher.processBytes(byArray2, 0, byArray2.length, byArray3 = new byte[this.cipher.getOutputSize(byArray2.length)], 0);
                if (n <= 0) continue;
                this.out.write(byArray3, 0, n);
            }
            try {
                n = this.cipher.doFinal(byArray3, 0);
                if (n > 0) {
                    this.out.write(byArray3, 0, n);
                }
            } catch (CryptoException cryptoException) {}
        } catch (IOException iOException) {
            iOException.printStackTrace();
        }
    }
}

