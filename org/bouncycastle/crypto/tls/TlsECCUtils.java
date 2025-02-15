/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.tls;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Hashtable;
import org.bouncycastle.asn1.x9.ECNamedCurveTable;
import org.bouncycastle.asn1.x9.X9ECParameters;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.agreement.ECDHBasicAgreement;
import org.bouncycastle.crypto.ec.CustomNamedCurves;
import org.bouncycastle.crypto.generators.ECKeyPairGenerator;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.params.ECKeyGenerationParameters;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.crypto.params.ECPublicKeyParameters;
import org.bouncycastle.crypto.tls.ECBasisType;
import org.bouncycastle.crypto.tls.NamedCurve;
import org.bouncycastle.crypto.tls.TlsFatalAlert;
import org.bouncycastle.crypto.tls.TlsProtocol;
import org.bouncycastle.crypto.tls.TlsUtils;
import org.bouncycastle.math.ec.ECAlgorithms;
import org.bouncycastle.math.ec.ECCurve;
import org.bouncycastle.math.ec.ECFieldElement;
import org.bouncycastle.math.ec.ECPoint;
import org.bouncycastle.math.field.PolynomialExtensionField;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.BigIntegers;
import org.bouncycastle.util.Integers;

public class TlsECCUtils {
    public static final Integer EXT_elliptic_curves = Integers.valueOf(10);
    public static final Integer EXT_ec_point_formats = Integers.valueOf(11);
    private static final String[] CURVE_NAMES = new String[]{"sect163k1", "sect163r1", "sect163r2", "sect193r1", "sect193r2", "sect233k1", "sect233r1", "sect239k1", "sect283k1", "sect283r1", "sect409k1", "sect409r1", "sect571k1", "sect571r1", "secp160k1", "secp160r1", "secp160r2", "secp192k1", "secp192r1", "secp224k1", "secp224r1", "secp256k1", "secp256r1", "secp384r1", "secp521r1", "brainpoolP256r1", "brainpoolP384r1", "brainpoolP512r1"};

    public static void addSupportedEllipticCurvesExtension(Hashtable hashtable, int[] nArray) throws IOException {
        hashtable.put(EXT_elliptic_curves, TlsECCUtils.createSupportedEllipticCurvesExtension(nArray));
    }

    public static void addSupportedPointFormatsExtension(Hashtable hashtable, short[] sArray) throws IOException {
        hashtable.put(EXT_ec_point_formats, TlsECCUtils.createSupportedPointFormatsExtension(sArray));
    }

    public static int[] getSupportedEllipticCurvesExtension(Hashtable hashtable) throws IOException {
        byte[] byArray = TlsUtils.getExtensionData(hashtable, EXT_elliptic_curves);
        return byArray == null ? null : TlsECCUtils.readSupportedEllipticCurvesExtension(byArray);
    }

    public static short[] getSupportedPointFormatsExtension(Hashtable hashtable) throws IOException {
        byte[] byArray = TlsUtils.getExtensionData(hashtable, EXT_ec_point_formats);
        return byArray == null ? null : TlsECCUtils.readSupportedPointFormatsExtension(byArray);
    }

    public static byte[] createSupportedEllipticCurvesExtension(int[] nArray) throws IOException {
        if (nArray == null || nArray.length < 1) {
            throw new TlsFatalAlert(80);
        }
        return TlsUtils.encodeUint16ArrayWithUint16Length(nArray);
    }

    public static byte[] createSupportedPointFormatsExtension(short[] sArray) throws IOException {
        if (sArray == null || !Arrays.contains(sArray, (short)0)) {
            sArray = Arrays.append(sArray, (short)0);
        }
        return TlsUtils.encodeUint8ArrayWithUint8Length(sArray);
    }

    public static int[] readSupportedEllipticCurvesExtension(byte[] byArray) throws IOException {
        if (byArray == null) {
            throw new IllegalArgumentException("'extensionData' cannot be null");
        }
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byArray);
        int n = TlsUtils.readUint16(byteArrayInputStream);
        if (n < 2 || (n & 1) != 0) {
            throw new TlsFatalAlert(50);
        }
        int[] nArray = TlsUtils.readUint16Array(n / 2, byteArrayInputStream);
        TlsProtocol.assertEmpty(byteArrayInputStream);
        return nArray;
    }

    public static short[] readSupportedPointFormatsExtension(byte[] byArray) throws IOException {
        if (byArray == null) {
            throw new IllegalArgumentException("'extensionData' cannot be null");
        }
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byArray);
        short s = TlsUtils.readUint8(byteArrayInputStream);
        if (s < 1) {
            throw new TlsFatalAlert(50);
        }
        short[] sArray = TlsUtils.readUint8Array(s, byteArrayInputStream);
        TlsProtocol.assertEmpty(byteArrayInputStream);
        if (!Arrays.contains(sArray, (short)0)) {
            throw new TlsFatalAlert(47);
        }
        return sArray;
    }

    public static String getNameOfNamedCurve(int n) {
        return TlsECCUtils.isSupportedNamedCurve(n) ? CURVE_NAMES[n - 1] : null;
    }

    public static ECDomainParameters getParametersForNamedCurve(int n) {
        String string = TlsECCUtils.getNameOfNamedCurve(n);
        if (string == null) {
            return null;
        }
        X9ECParameters x9ECParameters = CustomNamedCurves.getByName(string);
        if (x9ECParameters == null && (x9ECParameters = ECNamedCurveTable.getByName(string)) == null) {
            return null;
        }
        return new ECDomainParameters(x9ECParameters.getCurve(), x9ECParameters.getG(), x9ECParameters.getN(), x9ECParameters.getH(), x9ECParameters.getSeed());
    }

    public static boolean hasAnySupportedNamedCurves() {
        return CURVE_NAMES.length > 0;
    }

    public static boolean containsECCCipherSuites(int[] nArray) {
        for (int i = 0; i < nArray.length; ++i) {
            if (!TlsECCUtils.isECCCipherSuite(nArray[i])) continue;
            return true;
        }
        return false;
    }

    public static boolean isECCCipherSuite(int n) {
        switch (n) {
            case 49153: 
            case 49154: 
            case 49155: 
            case 49156: 
            case 49157: 
            case 49158: 
            case 49159: 
            case 49160: 
            case 49161: 
            case 49162: 
            case 49163: 
            case 49164: 
            case 49165: 
            case 49166: 
            case 49167: 
            case 49168: 
            case 49169: 
            case 49170: 
            case 49171: 
            case 49172: 
            case 49173: 
            case 49174: 
            case 49175: 
            case 49176: 
            case 49177: 
            case 49187: 
            case 49188: 
            case 49189: 
            case 49190: 
            case 49191: 
            case 49192: 
            case 49193: 
            case 49194: 
            case 49195: 
            case 49196: 
            case 49197: 
            case 49198: 
            case 49199: 
            case 49200: 
            case 49201: 
            case 49202: 
            case 49203: 
            case 49204: 
            case 49205: 
            case 49206: 
            case 49207: 
            case 49208: 
            case 49209: 
            case 49210: 
            case 49211: 
            case 49266: 
            case 49267: 
            case 49268: 
            case 49269: 
            case 49270: 
            case 49271: 
            case 49272: 
            case 49273: 
            case 49286: 
            case 49287: 
            case 49288: 
            case 49289: 
            case 49290: 
            case 49291: 
            case 49292: 
            case 49293: 
            case 49306: 
            case 49307: 
            case 49324: 
            case 49325: 
            case 49326: 
            case 49327: 
            case 52392: 
            case 52393: 
            case 52396: 
            case 65282: 
            case 65283: 
            case 65284: 
            case 65285: 
            case 65300: 
            case 65301: {
                return true;
            }
        }
        return false;
    }

    public static boolean areOnSameCurve(ECDomainParameters eCDomainParameters, ECDomainParameters eCDomainParameters2) {
        return eCDomainParameters != null && eCDomainParameters.equals(eCDomainParameters2);
    }

    public static boolean isSupportedNamedCurve(int n) {
        return n > 0 && n <= CURVE_NAMES.length;
    }

    public static boolean isCompressionPreferred(short[] sArray, short s) {
        if (sArray == null) {
            return false;
        }
        for (int i = 0; i < sArray.length; ++i) {
            short s2 = sArray[i];
            if (s2 == 0) {
                return false;
            }
            if (s2 != s) continue;
            return true;
        }
        return false;
    }

    public static byte[] serializeECFieldElement(int n, BigInteger bigInteger) throws IOException {
        return BigIntegers.asUnsignedByteArray((n + 7) / 8, bigInteger);
    }

    public static byte[] serializeECPoint(short[] sArray, ECPoint eCPoint) throws IOException {
        ECCurve eCCurve = eCPoint.getCurve();
        boolean bl = false;
        if (ECAlgorithms.isFpCurve(eCCurve)) {
            bl = TlsECCUtils.isCompressionPreferred(sArray, (short)1);
        } else if (ECAlgorithms.isF2mCurve(eCCurve)) {
            bl = TlsECCUtils.isCompressionPreferred(sArray, (short)2);
        }
        return eCPoint.getEncoded(bl);
    }

    public static byte[] serializeECPublicKey(short[] sArray, ECPublicKeyParameters eCPublicKeyParameters) throws IOException {
        return TlsECCUtils.serializeECPoint(sArray, eCPublicKeyParameters.getQ());
    }

    public static BigInteger deserializeECFieldElement(int n, byte[] byArray) throws IOException {
        int n2 = (n + 7) / 8;
        if (byArray.length != n2) {
            throw new TlsFatalAlert(50);
        }
        return new BigInteger(1, byArray);
    }

    public static ECPoint deserializeECPoint(short[] sArray, ECCurve eCCurve, byte[] byArray) throws IOException {
        short s;
        if (byArray == null || byArray.length < 1) {
            throw new TlsFatalAlert(47);
        }
        switch (byArray[0]) {
            case 2: 
            case 3: {
                if (ECAlgorithms.isF2mCurve(eCCurve)) {
                    s = 2;
                    break;
                }
                if (ECAlgorithms.isFpCurve(eCCurve)) {
                    s = 1;
                    break;
                }
                throw new TlsFatalAlert(47);
            }
            case 4: {
                s = 0;
                break;
            }
            default: {
                throw new TlsFatalAlert(47);
            }
        }
        if (!(s == 0 || sArray != null && Arrays.contains(sArray, s))) {
            throw new TlsFatalAlert(47);
        }
        return eCCurve.decodePoint(byArray);
    }

    public static ECPublicKeyParameters deserializeECPublicKey(short[] sArray, ECDomainParameters eCDomainParameters, byte[] byArray) throws IOException {
        try {
            ECPoint eCPoint = TlsECCUtils.deserializeECPoint(sArray, eCDomainParameters.getCurve(), byArray);
            return new ECPublicKeyParameters(eCPoint, eCDomainParameters);
        } catch (RuntimeException runtimeException) {
            throw new TlsFatalAlert(47, (Throwable)runtimeException);
        }
    }

    public static byte[] calculateECDHBasicAgreement(ECPublicKeyParameters eCPublicKeyParameters, ECPrivateKeyParameters eCPrivateKeyParameters) {
        ECDHBasicAgreement eCDHBasicAgreement = new ECDHBasicAgreement();
        eCDHBasicAgreement.init(eCPrivateKeyParameters);
        BigInteger bigInteger = eCDHBasicAgreement.calculateAgreement(eCPublicKeyParameters);
        return BigIntegers.asUnsignedByteArray(eCDHBasicAgreement.getFieldSize(), bigInteger);
    }

    public static AsymmetricCipherKeyPair generateECKeyPair(SecureRandom secureRandom, ECDomainParameters eCDomainParameters) {
        ECKeyPairGenerator eCKeyPairGenerator = new ECKeyPairGenerator();
        eCKeyPairGenerator.init(new ECKeyGenerationParameters(eCDomainParameters, secureRandom));
        return eCKeyPairGenerator.generateKeyPair();
    }

    public static ECPrivateKeyParameters generateEphemeralClientKeyExchange(SecureRandom secureRandom, short[] sArray, ECDomainParameters eCDomainParameters, OutputStream outputStream) throws IOException {
        AsymmetricCipherKeyPair asymmetricCipherKeyPair = TlsECCUtils.generateECKeyPair(secureRandom, eCDomainParameters);
        ECPublicKeyParameters eCPublicKeyParameters = (ECPublicKeyParameters)asymmetricCipherKeyPair.getPublic();
        TlsECCUtils.writeECPoint(sArray, eCPublicKeyParameters.getQ(), outputStream);
        return (ECPrivateKeyParameters)asymmetricCipherKeyPair.getPrivate();
    }

    static ECPrivateKeyParameters generateEphemeralServerKeyExchange(SecureRandom secureRandom, int[] nArray, short[] sArray, OutputStream outputStream) throws IOException {
        int n = -1;
        if (nArray == null) {
            n = 23;
        } else {
            for (int i = 0; i < nArray.length; ++i) {
                int n2 = nArray[i];
                if (!NamedCurve.isValid(n2) || !TlsECCUtils.isSupportedNamedCurve(n2)) continue;
                n = n2;
                break;
            }
        }
        ECDomainParameters eCDomainParameters = null;
        if (n >= 0) {
            eCDomainParameters = TlsECCUtils.getParametersForNamedCurve(n);
        } else if (Arrays.contains(nArray, 65281)) {
            eCDomainParameters = TlsECCUtils.getParametersForNamedCurve(23);
        } else if (Arrays.contains(nArray, 65282)) {
            eCDomainParameters = TlsECCUtils.getParametersForNamedCurve(10);
        }
        if (eCDomainParameters == null) {
            throw new TlsFatalAlert(80);
        }
        if (n < 0) {
            TlsECCUtils.writeExplicitECParameters(sArray, eCDomainParameters, outputStream);
        } else {
            TlsECCUtils.writeNamedECParameters(n, outputStream);
        }
        return TlsECCUtils.generateEphemeralClientKeyExchange(secureRandom, sArray, eCDomainParameters, outputStream);
    }

    public static ECPublicKeyParameters validateECPublicKey(ECPublicKeyParameters eCPublicKeyParameters) throws IOException {
        return eCPublicKeyParameters;
    }

    public static int readECExponent(int n, InputStream inputStream) throws IOException {
        int n2;
        BigInteger bigInteger = TlsECCUtils.readECParameter(inputStream);
        if (bigInteger.bitLength() < 32 && (n2 = bigInteger.intValue()) > 0 && n2 < n) {
            return n2;
        }
        throw new TlsFatalAlert(47);
    }

    public static BigInteger readECFieldElement(int n, InputStream inputStream) throws IOException {
        return TlsECCUtils.deserializeECFieldElement(n, TlsUtils.readOpaque8(inputStream));
    }

    public static BigInteger readECParameter(InputStream inputStream) throws IOException {
        return new BigInteger(1, TlsUtils.readOpaque8(inputStream));
    }

    public static ECDomainParameters readECParameters(int[] nArray, short[] sArray, InputStream inputStream) throws IOException {
        try {
            short s = TlsUtils.readUint8(inputStream);
            switch (s) {
                case 1: {
                    TlsECCUtils.checkNamedCurve(nArray, 65281);
                    BigInteger bigInteger = TlsECCUtils.readECParameter(inputStream);
                    BigInteger bigInteger2 = TlsECCUtils.readECFieldElement(bigInteger.bitLength(), inputStream);
                    BigInteger bigInteger3 = TlsECCUtils.readECFieldElement(bigInteger.bitLength(), inputStream);
                    byte[] byArray = TlsUtils.readOpaque8(inputStream);
                    BigInteger bigInteger4 = TlsECCUtils.readECParameter(inputStream);
                    BigInteger bigInteger5 = TlsECCUtils.readECParameter(inputStream);
                    ECCurve.Fp fp = new ECCurve.Fp(bigInteger, bigInteger2, bigInteger3, bigInteger4, bigInteger5);
                    ECPoint eCPoint = TlsECCUtils.deserializeECPoint(sArray, fp, byArray);
                    return new ECDomainParameters(fp, eCPoint, bigInteger4, bigInteger5);
                }
                case 2: {
                    TlsECCUtils.checkNamedCurve(nArray, 65282);
                    int n = TlsUtils.readUint16(inputStream);
                    short s2 = TlsUtils.readUint8(inputStream);
                    if (!ECBasisType.isValid(s2)) {
                        throw new TlsFatalAlert(47);
                    }
                    int n2 = TlsECCUtils.readECExponent(n, inputStream);
                    int n3 = -1;
                    int n4 = -1;
                    if (s2 == 2) {
                        n3 = TlsECCUtils.readECExponent(n, inputStream);
                        n4 = TlsECCUtils.readECExponent(n, inputStream);
                    }
                    BigInteger bigInteger = TlsECCUtils.readECFieldElement(n, inputStream);
                    BigInteger bigInteger6 = TlsECCUtils.readECFieldElement(n, inputStream);
                    byte[] byArray = TlsUtils.readOpaque8(inputStream);
                    BigInteger bigInteger7 = TlsECCUtils.readECParameter(inputStream);
                    BigInteger bigInteger8 = TlsECCUtils.readECParameter(inputStream);
                    ECCurve.F2m f2m = s2 == 2 ? new ECCurve.F2m(n, n2, n3, n4, bigInteger, bigInteger6, bigInteger7, bigInteger8) : new ECCurve.F2m(n, n2, bigInteger, bigInteger6, bigInteger7, bigInteger8);
                    ECPoint eCPoint = TlsECCUtils.deserializeECPoint(sArray, f2m, byArray);
                    return new ECDomainParameters(f2m, eCPoint, bigInteger7, bigInteger8);
                }
                case 3: {
                    int n = TlsUtils.readUint16(inputStream);
                    if (!NamedCurve.refersToASpecificNamedCurve(n)) {
                        throw new TlsFatalAlert(47);
                    }
                    TlsECCUtils.checkNamedCurve(nArray, n);
                    return TlsECCUtils.getParametersForNamedCurve(n);
                }
            }
            throw new TlsFatalAlert(47);
        } catch (RuntimeException runtimeException) {
            throw new TlsFatalAlert(47, (Throwable)runtimeException);
        }
    }

    private static void checkNamedCurve(int[] nArray, int n) throws IOException {
        if (nArray != null && !Arrays.contains(nArray, n)) {
            throw new TlsFatalAlert(47);
        }
    }

    public static void writeECExponent(int n, OutputStream outputStream) throws IOException {
        BigInteger bigInteger = BigInteger.valueOf(n);
        TlsECCUtils.writeECParameter(bigInteger, outputStream);
    }

    public static void writeECFieldElement(ECFieldElement eCFieldElement, OutputStream outputStream) throws IOException {
        TlsUtils.writeOpaque8(eCFieldElement.getEncoded(), outputStream);
    }

    public static void writeECFieldElement(int n, BigInteger bigInteger, OutputStream outputStream) throws IOException {
        TlsUtils.writeOpaque8(TlsECCUtils.serializeECFieldElement(n, bigInteger), outputStream);
    }

    public static void writeECParameter(BigInteger bigInteger, OutputStream outputStream) throws IOException {
        TlsUtils.writeOpaque8(BigIntegers.asUnsignedByteArray(bigInteger), outputStream);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static void writeExplicitECParameters(short[] sArray, ECDomainParameters eCDomainParameters, OutputStream outputStream) throws IOException {
        ECCurve eCCurve = eCDomainParameters.getCurve();
        if (ECAlgorithms.isFpCurve(eCCurve)) {
            TlsUtils.writeUint8((short)1, outputStream);
            TlsECCUtils.writeECParameter(eCCurve.getField().getCharacteristic(), outputStream);
        } else {
            if (!ECAlgorithms.isF2mCurve(eCCurve)) throw new IllegalArgumentException("'ecParameters' not a known curve type");
            PolynomialExtensionField polynomialExtensionField = (PolynomialExtensionField)eCCurve.getField();
            int[] nArray = polynomialExtensionField.getMinimalPolynomial().getExponentsPresent();
            TlsUtils.writeUint8((short)2, outputStream);
            int n = nArray[nArray.length - 1];
            TlsUtils.checkUint16(n);
            TlsUtils.writeUint16(n, outputStream);
            if (nArray.length == 3) {
                TlsUtils.writeUint8((short)1, outputStream);
                TlsECCUtils.writeECExponent(nArray[1], outputStream);
            } else {
                if (nArray.length != 5) throw new IllegalArgumentException("Only trinomial and pentomial curves are supported");
                TlsUtils.writeUint8((short)2, outputStream);
                TlsECCUtils.writeECExponent(nArray[1], outputStream);
                TlsECCUtils.writeECExponent(nArray[2], outputStream);
                TlsECCUtils.writeECExponent(nArray[3], outputStream);
            }
        }
        TlsECCUtils.writeECFieldElement(eCCurve.getA(), outputStream);
        TlsECCUtils.writeECFieldElement(eCCurve.getB(), outputStream);
        TlsUtils.writeOpaque8(TlsECCUtils.serializeECPoint(sArray, eCDomainParameters.getG()), outputStream);
        TlsECCUtils.writeECParameter(eCDomainParameters.getN(), outputStream);
        TlsECCUtils.writeECParameter(eCDomainParameters.getH(), outputStream);
    }

    public static void writeECPoint(short[] sArray, ECPoint eCPoint, OutputStream outputStream) throws IOException {
        TlsUtils.writeOpaque8(TlsECCUtils.serializeECPoint(sArray, eCPoint), outputStream);
    }

    public static void writeNamedECParameters(int n, OutputStream outputStream) throws IOException {
        if (!NamedCurve.refersToASpecificNamedCurve(n)) {
            throw new TlsFatalAlert(80);
        }
        TlsUtils.writeUint8((short)3, outputStream);
        TlsUtils.checkUint16(n);
        TlsUtils.writeUint16(n, outputStream);
    }
}

