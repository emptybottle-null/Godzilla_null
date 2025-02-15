/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto;

import org.bouncycastle.crypto.CharToByteConverter;
import org.bouncycastle.crypto.PBEParametersGenerator;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public enum PasswordConverter implements CharToByteConverter
{
    ASCII{

        public String getType() {
            return "ASCII";
        }

        public byte[] convert(char[] cArray) {
            return PBEParametersGenerator.PKCS5PasswordToBytes(cArray);
        }
    }
    ,
    UTF8{

        public String getType() {
            return "UTF8";
        }

        public byte[] convert(char[] cArray) {
            return PBEParametersGenerator.PKCS5PasswordToUTF8Bytes(cArray);
        }
    }
    ,
    PKCS12{

        public String getType() {
            return "PKCS12";
        }

        public byte[] convert(char[] cArray) {
            return PBEParametersGenerator.PKCS12PasswordToBytes(cArray);
        }
    };

}

