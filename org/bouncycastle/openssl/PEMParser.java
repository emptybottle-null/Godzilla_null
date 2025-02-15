/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.openssl;

import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERNull;
import org.bouncycastle.asn1.cms.ContentInfo;
import org.bouncycastle.asn1.pkcs.EncryptedPrivateKeyInfo;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.asn1.pkcs.RSAPrivateKey;
import org.bouncycastle.asn1.pkcs.RSAPublicKey;
import org.bouncycastle.asn1.sec.ECPrivateKey;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.DSAParameter;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.asn1.x9.X9ECParameters;
import org.bouncycastle.asn1.x9.X9ObjectIdentifiers;
import org.bouncycastle.cert.X509AttributeCertificateHolder;
import org.bouncycastle.cert.X509CRLHolder;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.openssl.PEMEncryptedKeyPair;
import org.bouncycastle.openssl.PEMException;
import org.bouncycastle.openssl.PEMKeyPair;
import org.bouncycastle.openssl.PEMKeyPairParser;
import org.bouncycastle.openssl.X509TrustedCertificateBlock;
import org.bouncycastle.pkcs.PKCS10CertificationRequest;
import org.bouncycastle.pkcs.PKCS8EncryptedPrivateKeyInfo;
import org.bouncycastle.util.encoders.Hex;
import org.bouncycastle.util.io.pem.PemHeader;
import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemObjectParser;
import org.bouncycastle.util.io.pem.PemReader;

public class PEMParser
extends PemReader {
    private final Map parsers = new HashMap();

    public PEMParser(Reader reader) {
        super(reader);
        this.parsers.put("CERTIFICATE REQUEST", new PKCS10CertificationRequestParser());
        this.parsers.put("NEW CERTIFICATE REQUEST", new PKCS10CertificationRequestParser());
        this.parsers.put("CERTIFICATE", new X509CertificateParser());
        this.parsers.put("TRUSTED CERTIFICATE", new X509TrustedCertificateParser());
        this.parsers.put("X509 CERTIFICATE", new X509CertificateParser());
        this.parsers.put("X509 CRL", new X509CRLParser());
        this.parsers.put("PKCS7", new PKCS7Parser());
        this.parsers.put("CMS", new PKCS7Parser());
        this.parsers.put("ATTRIBUTE CERTIFICATE", new X509AttributeCertificateParser());
        this.parsers.put("EC PARAMETERS", new ECCurveParamsParser());
        this.parsers.put("PUBLIC KEY", new PublicKeyParser());
        this.parsers.put("RSA PUBLIC KEY", new RSAPublicKeyParser());
        this.parsers.put("RSA PRIVATE KEY", new KeyPairParser(new RSAKeyPairParser()));
        this.parsers.put("DSA PRIVATE KEY", new KeyPairParser(new DSAKeyPairParser()));
        this.parsers.put("EC PRIVATE KEY", new KeyPairParser(new ECDSAKeyPairParser()));
        this.parsers.put("ENCRYPTED PRIVATE KEY", new EncryptedPrivateKeyParser());
        this.parsers.put("PRIVATE KEY", new PrivateKeyParser());
    }

    public Object readObject() throws IOException {
        PemObject pemObject = this.readPemObject();
        if (pemObject != null) {
            String string = pemObject.getType();
            if (this.parsers.containsKey(string)) {
                return ((PemObjectParser)this.parsers.get(string)).parseObject(pemObject);
            }
            throw new IOException("unrecognised object: " + string);
        }
        return null;
    }

    private class DSAKeyPairParser
    implements PEMKeyPairParser {
        private DSAKeyPairParser() {
        }

        public PEMKeyPair parse(byte[] byArray) throws IOException {
            try {
                ASN1Sequence aSN1Sequence = ASN1Sequence.getInstance(byArray);
                if (aSN1Sequence.size() != 6) {
                    throw new PEMException("malformed sequence in DSA private key");
                }
                ASN1Integer aSN1Integer = ASN1Integer.getInstance(aSN1Sequence.getObjectAt(1));
                ASN1Integer aSN1Integer2 = ASN1Integer.getInstance(aSN1Sequence.getObjectAt(2));
                ASN1Integer aSN1Integer3 = ASN1Integer.getInstance(aSN1Sequence.getObjectAt(3));
                ASN1Integer aSN1Integer4 = ASN1Integer.getInstance(aSN1Sequence.getObjectAt(4));
                ASN1Integer aSN1Integer5 = ASN1Integer.getInstance(aSN1Sequence.getObjectAt(5));
                return new PEMKeyPair(new SubjectPublicKeyInfo(new AlgorithmIdentifier(X9ObjectIdentifiers.id_dsa, new DSAParameter(aSN1Integer.getValue(), aSN1Integer2.getValue(), aSN1Integer3.getValue())), aSN1Integer4), new PrivateKeyInfo(new AlgorithmIdentifier(X9ObjectIdentifiers.id_dsa, new DSAParameter(aSN1Integer.getValue(), aSN1Integer2.getValue(), aSN1Integer3.getValue())), aSN1Integer5));
            } catch (IOException iOException) {
                throw iOException;
            } catch (Exception exception) {
                throw new PEMException("problem creating DSA private key: " + exception.toString(), exception);
            }
        }
    }

    private class ECCurveParamsParser
    implements PemObjectParser {
        private ECCurveParamsParser() {
        }

        public Object parseObject(PemObject pemObject) throws IOException {
            try {
                ASN1Primitive aSN1Primitive = ASN1Primitive.fromByteArray(pemObject.getContent());
                if (aSN1Primitive instanceof ASN1ObjectIdentifier) {
                    return ASN1Primitive.fromByteArray(pemObject.getContent());
                }
                if (aSN1Primitive instanceof ASN1Sequence) {
                    return X9ECParameters.getInstance(aSN1Primitive);
                }
                return null;
            } catch (IOException iOException) {
                throw iOException;
            } catch (Exception exception) {
                throw new PEMException("exception extracting EC named curve: " + exception.toString());
            }
        }
    }

    private class ECDSAKeyPairParser
    implements PEMKeyPairParser {
        private ECDSAKeyPairParser() {
        }

        public PEMKeyPair parse(byte[] byArray) throws IOException {
            try {
                ASN1Sequence aSN1Sequence = ASN1Sequence.getInstance(byArray);
                ECPrivateKey eCPrivateKey = ECPrivateKey.getInstance(aSN1Sequence);
                AlgorithmIdentifier algorithmIdentifier = new AlgorithmIdentifier(X9ObjectIdentifiers.id_ecPublicKey, eCPrivateKey.getParameters());
                PrivateKeyInfo privateKeyInfo = new PrivateKeyInfo(algorithmIdentifier, eCPrivateKey);
                SubjectPublicKeyInfo subjectPublicKeyInfo = new SubjectPublicKeyInfo(algorithmIdentifier, eCPrivateKey.getPublicKey().getBytes());
                return new PEMKeyPair(subjectPublicKeyInfo, privateKeyInfo);
            } catch (IOException iOException) {
                throw iOException;
            } catch (Exception exception) {
                throw new PEMException("problem creating EC private key: " + exception.toString(), exception);
            }
        }
    }

    private class EncryptedPrivateKeyParser
    implements PemObjectParser {
        public Object parseObject(PemObject pemObject) throws IOException {
            try {
                return new PKCS8EncryptedPrivateKeyInfo(EncryptedPrivateKeyInfo.getInstance(pemObject.getContent()));
            } catch (Exception exception) {
                throw new PEMException("problem parsing ENCRYPTED PRIVATE KEY: " + exception.toString(), exception);
            }
        }
    }

    private class KeyPairParser
    implements PemObjectParser {
        private final PEMKeyPairParser pemKeyPairParser;

        public KeyPairParser(PEMKeyPairParser pEMKeyPairParser) {
            this.pemKeyPairParser = pEMKeyPairParser;
        }

        public Object parseObject(PemObject pemObject) throws IOException {
            boolean bl = false;
            String string = null;
            List list = pemObject.getHeaders();
            for (Object object2 : list) {
                if (((PemHeader)object2).getName().equals("Proc-Type") && ((PemHeader)object2).getValue().equals("4,ENCRYPTED")) {
                    bl = true;
                    continue;
                }
                if (!((PemHeader)object2).getName().equals("DEK-Info")) continue;
                string = ((PemHeader)object2).getValue();
            }
            Object object = pemObject.getContent();
            try {
                if (bl) {
                    Object object2;
                    object2 = new StringTokenizer(string, ",");
                    String string2 = ((StringTokenizer)object2).nextToken();
                    byte[] byArray = Hex.decode(((StringTokenizer)object2).nextToken());
                    return new PEMEncryptedKeyPair(string2, byArray, (byte[])object, this.pemKeyPairParser);
                }
                return this.pemKeyPairParser.parse((byte[])object);
            } catch (IOException iOException) {
                if (bl) {
                    throw new PEMException("exception decoding - please check password and data.", iOException);
                }
                throw new PEMException(iOException.getMessage(), iOException);
            } catch (IllegalArgumentException illegalArgumentException) {
                if (bl) {
                    throw new PEMException("exception decoding - please check password and data.", illegalArgumentException);
                }
                throw new PEMException(illegalArgumentException.getMessage(), illegalArgumentException);
            }
        }
    }

    private class PKCS10CertificationRequestParser
    implements PemObjectParser {
        private PKCS10CertificationRequestParser() {
        }

        public Object parseObject(PemObject pemObject) throws IOException {
            try {
                return new PKCS10CertificationRequest(pemObject.getContent());
            } catch (Exception exception) {
                throw new PEMException("problem parsing certrequest: " + exception.toString(), exception);
            }
        }
    }

    private class PKCS7Parser
    implements PemObjectParser {
        private PKCS7Parser() {
        }

        public Object parseObject(PemObject pemObject) throws IOException {
            try {
                ASN1InputStream aSN1InputStream = new ASN1InputStream(pemObject.getContent());
                return ContentInfo.getInstance(aSN1InputStream.readObject());
            } catch (Exception exception) {
                throw new PEMException("problem parsing PKCS7 object: " + exception.toString(), exception);
            }
        }
    }

    private class PrivateKeyParser
    implements PemObjectParser {
        public Object parseObject(PemObject pemObject) throws IOException {
            try {
                return PrivateKeyInfo.getInstance(pemObject.getContent());
            } catch (Exception exception) {
                throw new PEMException("problem parsing PRIVATE KEY: " + exception.toString(), exception);
            }
        }
    }

    private class PublicKeyParser
    implements PemObjectParser {
        public Object parseObject(PemObject pemObject) throws IOException {
            return SubjectPublicKeyInfo.getInstance(pemObject.getContent());
        }
    }

    private class RSAKeyPairParser
    implements PEMKeyPairParser {
        private RSAKeyPairParser() {
        }

        public PEMKeyPair parse(byte[] byArray) throws IOException {
            try {
                ASN1Sequence aSN1Sequence = ASN1Sequence.getInstance(byArray);
                if (aSN1Sequence.size() != 9) {
                    throw new PEMException("malformed sequence in RSA private key");
                }
                RSAPrivateKey rSAPrivateKey = RSAPrivateKey.getInstance(aSN1Sequence);
                RSAPublicKey rSAPublicKey = new RSAPublicKey(rSAPrivateKey.getModulus(), rSAPrivateKey.getPublicExponent());
                AlgorithmIdentifier algorithmIdentifier = new AlgorithmIdentifier(PKCSObjectIdentifiers.rsaEncryption, DERNull.INSTANCE);
                return new PEMKeyPair(new SubjectPublicKeyInfo(algorithmIdentifier, rSAPublicKey), new PrivateKeyInfo(algorithmIdentifier, rSAPrivateKey));
            } catch (IOException iOException) {
                throw iOException;
            } catch (Exception exception) {
                throw new PEMException("problem creating RSA private key: " + exception.toString(), exception);
            }
        }
    }

    private class RSAPublicKeyParser
    implements PemObjectParser {
        public Object parseObject(PemObject pemObject) throws IOException {
            try {
                RSAPublicKey rSAPublicKey = RSAPublicKey.getInstance(pemObject.getContent());
                return new SubjectPublicKeyInfo(new AlgorithmIdentifier(PKCSObjectIdentifiers.rsaEncryption, DERNull.INSTANCE), rSAPublicKey);
            } catch (IOException iOException) {
                throw iOException;
            } catch (Exception exception) {
                throw new PEMException("problem extracting key: " + exception.toString(), exception);
            }
        }
    }

    private class X509AttributeCertificateParser
    implements PemObjectParser {
        private X509AttributeCertificateParser() {
        }

        public Object parseObject(PemObject pemObject) throws IOException {
            return new X509AttributeCertificateHolder(pemObject.getContent());
        }
    }

    private class X509CRLParser
    implements PemObjectParser {
        private X509CRLParser() {
        }

        public Object parseObject(PemObject pemObject) throws IOException {
            try {
                return new X509CRLHolder(pemObject.getContent());
            } catch (Exception exception) {
                throw new PEMException("problem parsing cert: " + exception.toString(), exception);
            }
        }
    }

    private class X509CertificateParser
    implements PemObjectParser {
        private X509CertificateParser() {
        }

        public Object parseObject(PemObject pemObject) throws IOException {
            try {
                return new X509CertificateHolder(pemObject.getContent());
            } catch (Exception exception) {
                throw new PEMException("problem parsing cert: " + exception.toString(), exception);
            }
        }
    }

    private class X509TrustedCertificateParser
    implements PemObjectParser {
        private X509TrustedCertificateParser() {
        }

        public Object parseObject(PemObject pemObject) throws IOException {
            try {
                return new X509TrustedCertificateBlock(pemObject.getContent());
            } catch (Exception exception) {
                throw new PEMException("problem parsing cert: " + exception.toString(), exception);
            }
        }
    }
}

