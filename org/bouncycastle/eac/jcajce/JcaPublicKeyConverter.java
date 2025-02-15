/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.eac.jcajce;

import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Provider;
import java.security.PublicKey;
import java.security.interfaces.ECPublicKey;
import java.security.spec.ECField;
import java.security.spec.ECFieldF2m;
import java.security.spec.ECFieldFp;
import java.security.spec.ECParameterSpec;
import java.security.spec.ECPoint;
import java.security.spec.ECPublicKeySpec;
import java.security.spec.EllipticCurve;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.eac.EACObjectIdentifiers;
import org.bouncycastle.asn1.eac.ECDSAPublicKey;
import org.bouncycastle.asn1.eac.PublicKeyDataObject;
import org.bouncycastle.asn1.eac.RSAPublicKey;
import org.bouncycastle.eac.EACException;
import org.bouncycastle.eac.jcajce.DefaultEACHelper;
import org.bouncycastle.eac.jcajce.EACHelper;
import org.bouncycastle.eac.jcajce.NamedEACHelper;
import org.bouncycastle.eac.jcajce.ProviderEACHelper;
import org.bouncycastle.math.ec.ECAlgorithms;
import org.bouncycastle.math.ec.ECCurve;
import org.bouncycastle.math.ec.ECPoint;
import org.bouncycastle.math.field.FiniteField;
import org.bouncycastle.math.field.Polynomial;
import org.bouncycastle.math.field.PolynomialExtensionField;
import org.bouncycastle.util.Arrays;

public class JcaPublicKeyConverter {
    private EACHelper helper = new DefaultEACHelper();

    public JcaPublicKeyConverter setProvider(String string) {
        this.helper = new NamedEACHelper(string);
        return this;
    }

    public JcaPublicKeyConverter setProvider(Provider provider) {
        this.helper = new ProviderEACHelper(provider);
        return this;
    }

    public PublicKey getKey(PublicKeyDataObject publicKeyDataObject) throws EACException, InvalidKeySpecException {
        if (publicKeyDataObject.getUsage().on(EACObjectIdentifiers.id_TA_ECDSA)) {
            return this.getECPublicKeyPublicKey((ECDSAPublicKey)publicKeyDataObject);
        }
        RSAPublicKey rSAPublicKey = (RSAPublicKey)publicKeyDataObject;
        RSAPublicKeySpec rSAPublicKeySpec = new RSAPublicKeySpec(rSAPublicKey.getModulus(), rSAPublicKey.getPublicExponent());
        try {
            KeyFactory keyFactory = this.helper.createKeyFactory("RSA");
            return keyFactory.generatePublic(rSAPublicKeySpec);
        } catch (NoSuchProviderException noSuchProviderException) {
            throw new EACException("cannot find provider: " + noSuchProviderException.getMessage(), noSuchProviderException);
        } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
            throw new EACException("cannot find algorithm ECDSA: " + noSuchAlgorithmException.getMessage(), noSuchAlgorithmException);
        }
    }

    private PublicKey getECPublicKeyPublicKey(ECDSAPublicKey eCDSAPublicKey) throws EACException, InvalidKeySpecException {
        KeyFactory keyFactory;
        ECParameterSpec eCParameterSpec = this.getParams(eCDSAPublicKey);
        ECPoint eCPoint = this.getPublicPoint(eCDSAPublicKey);
        ECPublicKeySpec eCPublicKeySpec = new ECPublicKeySpec(eCPoint, eCParameterSpec);
        try {
            keyFactory = this.helper.createKeyFactory("ECDSA");
        } catch (NoSuchProviderException noSuchProviderException) {
            throw new EACException("cannot find provider: " + noSuchProviderException.getMessage(), noSuchProviderException);
        } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
            throw new EACException("cannot find algorithm ECDSA: " + noSuchAlgorithmException.getMessage(), noSuchAlgorithmException);
        }
        return keyFactory.generatePublic(eCPublicKeySpec);
    }

    private ECPoint getPublicPoint(ECDSAPublicKey eCDSAPublicKey) {
        if (!eCDSAPublicKey.hasParameters()) {
            throw new IllegalArgumentException("Public key does not contains EC Params");
        }
        BigInteger bigInteger = eCDSAPublicKey.getPrimeModulusP();
        ECCurve.Fp fp = new ECCurve.Fp(bigInteger, eCDSAPublicKey.getFirstCoefA(), eCDSAPublicKey.getSecondCoefB(), eCDSAPublicKey.getOrderOfBasePointR(), eCDSAPublicKey.getCofactorF());
        ECPoint.Fp fp2 = (ECPoint.Fp)fp.decodePoint(eCDSAPublicKey.getPublicPointY());
        return new ECPoint(fp2.getAffineXCoord().toBigInteger(), fp2.getAffineYCoord().toBigInteger());
    }

    private ECParameterSpec getParams(ECDSAPublicKey eCDSAPublicKey) {
        if (!eCDSAPublicKey.hasParameters()) {
            throw new IllegalArgumentException("Public key does not contains EC Params");
        }
        BigInteger bigInteger = eCDSAPublicKey.getPrimeModulusP();
        ECCurve.Fp fp = new ECCurve.Fp(bigInteger, eCDSAPublicKey.getFirstCoefA(), eCDSAPublicKey.getSecondCoefB(), eCDSAPublicKey.getOrderOfBasePointR(), eCDSAPublicKey.getCofactorF());
        org.bouncycastle.math.ec.ECPoint eCPoint = fp.decodePoint(eCDSAPublicKey.getBasePointG());
        BigInteger bigInteger2 = eCDSAPublicKey.getOrderOfBasePointR();
        BigInteger bigInteger3 = eCDSAPublicKey.getCofactorF();
        EllipticCurve ellipticCurve = JcaPublicKeyConverter.convertCurve(fp);
        return new ECParameterSpec(ellipticCurve, new ECPoint(eCPoint.getAffineXCoord().toBigInteger(), eCPoint.getAffineYCoord().toBigInteger()), bigInteger2, bigInteger3.intValue());
    }

    public PublicKeyDataObject getPublicKeyDataObject(ASN1ObjectIdentifier aSN1ObjectIdentifier, PublicKey publicKey) {
        if (publicKey instanceof java.security.interfaces.RSAPublicKey) {
            java.security.interfaces.RSAPublicKey rSAPublicKey = (java.security.interfaces.RSAPublicKey)publicKey;
            return new RSAPublicKey(aSN1ObjectIdentifier, rSAPublicKey.getModulus(), rSAPublicKey.getPublicExponent());
        }
        ECPublicKey eCPublicKey = (ECPublicKey)publicKey;
        ECParameterSpec eCParameterSpec = eCPublicKey.getParams();
        return new ECDSAPublicKey(aSN1ObjectIdentifier, ((ECFieldFp)eCParameterSpec.getCurve().getField()).getP(), eCParameterSpec.getCurve().getA(), eCParameterSpec.getCurve().getB(), JcaPublicKeyConverter.convertPoint(JcaPublicKeyConverter.convertCurve(eCParameterSpec.getCurve(), eCParameterSpec.getOrder(), eCParameterSpec.getCofactor()), eCParameterSpec.getGenerator()).getEncoded(), eCParameterSpec.getOrder(), JcaPublicKeyConverter.convertPoint(JcaPublicKeyConverter.convertCurve(eCParameterSpec.getCurve(), eCParameterSpec.getOrder(), eCParameterSpec.getCofactor()), eCPublicKey.getW()).getEncoded(), eCParameterSpec.getCofactor());
    }

    private static org.bouncycastle.math.ec.ECPoint convertPoint(ECCurve eCCurve, ECPoint eCPoint) {
        return eCCurve.createPoint(eCPoint.getAffineX(), eCPoint.getAffineY());
    }

    private static ECCurve convertCurve(EllipticCurve ellipticCurve, BigInteger bigInteger, int n) {
        ECField eCField = ellipticCurve.getField();
        BigInteger bigInteger2 = ellipticCurve.getA();
        BigInteger bigInteger3 = ellipticCurve.getB();
        if (eCField instanceof ECFieldFp) {
            return new ECCurve.Fp(((ECFieldFp)eCField).getP(), bigInteger2, bigInteger3, bigInteger, BigInteger.valueOf(n));
        }
        throw new IllegalStateException("not implemented yet!!!");
    }

    private static EllipticCurve convertCurve(ECCurve eCCurve) {
        ECField eCField = JcaPublicKeyConverter.convertField(eCCurve.getField());
        BigInteger bigInteger = eCCurve.getA().toBigInteger();
        BigInteger bigInteger2 = eCCurve.getB().toBigInteger();
        return new EllipticCurve(eCField, bigInteger, bigInteger2, null);
    }

    private static ECField convertField(FiniteField finiteField) {
        if (ECAlgorithms.isFpField(finiteField)) {
            return new ECFieldFp(finiteField.getCharacteristic());
        }
        Polynomial polynomial = ((PolynomialExtensionField)finiteField).getMinimalPolynomial();
        int[] nArray = polynomial.getExponentsPresent();
        int[] nArray2 = Arrays.reverse(Arrays.copyOfRange(nArray, 1, nArray.length - 1));
        return new ECFieldF2m(polynomial.getDegree(), nArray2);
    }
}

