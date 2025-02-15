/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.gm;

import java.math.BigInteger;
import java.util.Enumeration;
import java.util.Hashtable;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.gm.GMObjectIdentifiers;
import org.bouncycastle.asn1.x9.X9ECParameters;
import org.bouncycastle.asn1.x9.X9ECParametersHolder;
import org.bouncycastle.asn1.x9.X9ECPoint;
import org.bouncycastle.math.ec.ECCurve;
import org.bouncycastle.util.Strings;
import org.bouncycastle.util.encoders.Hex;

public class GMNamedCurves {
    static X9ECParametersHolder sm2p256v1 = new X9ECParametersHolder(){

        protected X9ECParameters createParameters() {
            BigInteger bigInteger = GMNamedCurves.fromHex("FFFFFFFEFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF00000000FFFFFFFFFFFFFFFF");
            BigInteger bigInteger2 = GMNamedCurves.fromHex("FFFFFFFEFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF00000000FFFFFFFFFFFFFFFC");
            BigInteger bigInteger3 = GMNamedCurves.fromHex("28E9FA9E9D9F5E344D5A9E4BCF6509A7F39789F515AB8F92DDBCBD414D940E93");
            byte[] byArray = null;
            BigInteger bigInteger4 = GMNamedCurves.fromHex("FFFFFFFEFFFFFFFFFFFFFFFFFFFFFFFF7203DF6B21C6052B53BBF40939D54123");
            BigInteger bigInteger5 = BigInteger.valueOf(1L);
            ECCurve eCCurve = GMNamedCurves.configureCurve(new ECCurve.Fp(bigInteger, bigInteger2, bigInteger3, bigInteger4, bigInteger5));
            X9ECPoint x9ECPoint = new X9ECPoint(eCCurve, Hex.decode("0432C4AE2C1F1981195F9904466A39C9948FE30BBFF2660BE1715A4589334C74C7BC3736A2F4F6779C59BDCEE36B692153D0A9877CC62A474002DF32E52139F0A0"));
            return new X9ECParameters(eCCurve, x9ECPoint, bigInteger4, bigInteger5, byArray);
        }
    };
    static X9ECParametersHolder wapip192v1 = new X9ECParametersHolder(){

        protected X9ECParameters createParameters() {
            BigInteger bigInteger = GMNamedCurves.fromHex("BDB6F4FE3E8B1D9E0DA8C0D46F4C318CEFE4AFE3B6B8551F");
            BigInteger bigInteger2 = GMNamedCurves.fromHex("BB8E5E8FBC115E139FE6A814FE48AAA6F0ADA1AA5DF91985");
            BigInteger bigInteger3 = GMNamedCurves.fromHex("1854BEBDC31B21B7AEFC80AB0ECD10D5B1B3308E6DBF11C1");
            byte[] byArray = null;
            BigInteger bigInteger4 = GMNamedCurves.fromHex("BDB6F4FE3E8B1D9E0DA8C0D40FC962195DFAE76F56564677");
            BigInteger bigInteger5 = BigInteger.valueOf(1L);
            ECCurve eCCurve = GMNamedCurves.configureCurve(new ECCurve.Fp(bigInteger, bigInteger2, bigInteger3, bigInteger4, bigInteger5));
            X9ECPoint x9ECPoint = new X9ECPoint(eCCurve, Hex.decode("044AD5F7048DE709AD51236DE65E4D4B482C836DC6E410664002BB3A02D4AAADACAE24817A4CA3A1B014B5270432DB27D2"));
            return new X9ECParameters(eCCurve, x9ECPoint, bigInteger4, bigInteger5, byArray);
        }
    };
    static final Hashtable objIds = new Hashtable();
    static final Hashtable curves = new Hashtable();
    static final Hashtable names = new Hashtable();

    private static ECCurve configureCurve(ECCurve eCCurve) {
        return eCCurve;
    }

    private static BigInteger fromHex(String string) {
        return new BigInteger(1, Hex.decode(string));
    }

    static void defineCurve(String string, ASN1ObjectIdentifier aSN1ObjectIdentifier, X9ECParametersHolder x9ECParametersHolder) {
        objIds.put(Strings.toLowerCase(string), aSN1ObjectIdentifier);
        names.put(aSN1ObjectIdentifier, string);
        curves.put(aSN1ObjectIdentifier, x9ECParametersHolder);
    }

    public static X9ECParameters getByName(String string) {
        ASN1ObjectIdentifier aSN1ObjectIdentifier = GMNamedCurves.getOID(string);
        return aSN1ObjectIdentifier == null ? null : GMNamedCurves.getByOID(aSN1ObjectIdentifier);
    }

    public static X9ECParameters getByOID(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        X9ECParametersHolder x9ECParametersHolder = (X9ECParametersHolder)curves.get(aSN1ObjectIdentifier);
        return x9ECParametersHolder == null ? null : x9ECParametersHolder.getParameters();
    }

    public static ASN1ObjectIdentifier getOID(String string) {
        return (ASN1ObjectIdentifier)objIds.get(Strings.toLowerCase(string));
    }

    public static String getName(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        return (String)names.get(aSN1ObjectIdentifier);
    }

    public static Enumeration getNames() {
        return names.elements();
    }

    static {
        GMNamedCurves.defineCurve("wapip192v1", GMObjectIdentifiers.wapip192v1, wapip192v1);
        GMNamedCurves.defineCurve("sm2p256v1", GMObjectIdentifiers.sm2p256v1, sm2p256v1);
    }
}

