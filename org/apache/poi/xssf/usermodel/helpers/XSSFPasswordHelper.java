/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xssf.usermodel.helpers;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;
import java.util.Locale;
import javax.xml.namespace.QName;
import org.apache.poi.poifs.crypt.CryptoFunctions;
import org.apache.poi.poifs.crypt.HashAlgorithm;
import org.apache.poi.util.Internal;
import org.apache.poi.util.StringUtil;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlObject;

@Internal(since="3.15 beta 3")
public final class XSSFPasswordHelper {
    private XSSFPasswordHelper() {
    }

    public static void setPassword(XmlObject xobj, String password, HashAlgorithm hashAlgo, String prefix) {
        XmlCursor cur = xobj.newCursor();
        if (password == null) {
            cur.removeAttribute(XSSFPasswordHelper.getAttrName(prefix, "password"));
            cur.removeAttribute(XSSFPasswordHelper.getAttrName(prefix, "algorithmName"));
            cur.removeAttribute(XSSFPasswordHelper.getAttrName(prefix, "hashValue"));
            cur.removeAttribute(XSSFPasswordHelper.getAttrName(prefix, "saltValue"));
            cur.removeAttribute(XSSFPasswordHelper.getAttrName(prefix, "spinCount"));
            return;
        }
        cur.toFirstContentToken();
        if (hashAlgo == null) {
            int hash = CryptoFunctions.createXorVerifier1(password);
            cur.insertAttributeWithValue(XSSFPasswordHelper.getAttrName(prefix, "password"), String.format(Locale.ROOT, "%04X", hash).toUpperCase(Locale.ROOT));
        } else {
            SecureRandom random = new SecureRandom();
            byte[] salt = random.generateSeed(16);
            int spinCount = 100000;
            byte[] hash = CryptoFunctions.hashPassword(password, hashAlgo, salt, spinCount, false);
            Base64.Encoder enc64 = Base64.getEncoder();
            cur.insertAttributeWithValue(XSSFPasswordHelper.getAttrName(prefix, "algorithmName"), hashAlgo.jceId);
            cur.insertAttributeWithValue(XSSFPasswordHelper.getAttrName(prefix, "hashValue"), enc64.encodeToString(hash));
            cur.insertAttributeWithValue(XSSFPasswordHelper.getAttrName(prefix, "saltValue"), enc64.encodeToString(salt));
            cur.insertAttributeWithValue(XSSFPasswordHelper.getAttrName(prefix, "spinCount"), "" + spinCount);
        }
        cur.dispose();
    }

    public static boolean validatePassword(XmlObject xobj, String password, String prefix) {
        if (password == null) {
            return false;
        }
        XmlCursor cur = xobj.newCursor();
        String xorHashVal = cur.getAttributeText(XSSFPasswordHelper.getAttrName(prefix, "password"));
        String algoName = cur.getAttributeText(XSSFPasswordHelper.getAttrName(prefix, "algorithmName"));
        String hashVal = cur.getAttributeText(XSSFPasswordHelper.getAttrName(prefix, "hashValue"));
        String saltVal = cur.getAttributeText(XSSFPasswordHelper.getAttrName(prefix, "saltValue"));
        String spinCount = cur.getAttributeText(XSSFPasswordHelper.getAttrName(prefix, "spinCount"));
        cur.dispose();
        if (xorHashVal != null) {
            int hash2;
            int hash1 = Integer.parseInt(xorHashVal, 16);
            return hash1 == (hash2 = CryptoFunctions.createXorVerifier1(password));
        }
        if (hashVal == null || algoName == null || saltVal == null || spinCount == null) {
            return false;
        }
        Base64.Decoder dec64 = Base64.getDecoder();
        byte[] hash1 = dec64.decode(hashVal);
        HashAlgorithm hashAlgo = HashAlgorithm.fromString(algoName);
        byte[] salt = dec64.decode(saltVal);
        int spinCnt = Integer.parseInt(spinCount);
        byte[] hash2 = CryptoFunctions.hashPassword(password, hashAlgo, salt, spinCnt, false);
        return Arrays.equals(hash1, hash2);
    }

    private static QName getAttrName(String prefix, String name) {
        if (prefix == null || prefix.isEmpty()) {
            return new QName(name);
        }
        return new QName(prefix + StringUtil.toUpperCase(name.charAt(0)) + name.substring(1));
    }
}

