/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.cms;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1Set;
import org.bouncycastle.asn1.BERSequence;
import org.bouncycastle.asn1.DERSet;
import org.bouncycastle.asn1.cms.ContentInfo;
import org.bouncycastle.asn1.cms.SignedData;
import org.bouncycastle.asn1.cms.SignerInfo;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.cert.X509AttributeCertificateHolder;
import org.bouncycastle.cert.X509CRLHolder;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cms.CMSException;
import org.bouncycastle.cms.CMSProcessable;
import org.bouncycastle.cms.CMSProcessableByteArray;
import org.bouncycastle.cms.CMSSignedHelper;
import org.bouncycastle.cms.CMSTypedData;
import org.bouncycastle.cms.CMSUtils;
import org.bouncycastle.cms.PKCS7ProcessableObject;
import org.bouncycastle.cms.SignerInformation;
import org.bouncycastle.cms.SignerInformationStore;
import org.bouncycastle.cms.SignerInformationVerifier;
import org.bouncycastle.cms.SignerInformationVerifierProvider;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.util.Encodable;
import org.bouncycastle.util.Store;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class CMSSignedData
implements Encodable {
    private static final CMSSignedHelper HELPER = CMSSignedHelper.INSTANCE;
    SignedData signedData;
    ContentInfo contentInfo;
    CMSTypedData signedContent;
    SignerInformationStore signerInfoStore;
    private Map hashes;

    private CMSSignedData(CMSSignedData cMSSignedData) {
        this.signedData = cMSSignedData.signedData;
        this.contentInfo = cMSSignedData.contentInfo;
        this.signedContent = cMSSignedData.signedContent;
        this.signerInfoStore = cMSSignedData.signerInfoStore;
    }

    public CMSSignedData(byte[] byArray) throws CMSException {
        this(CMSUtils.readContentInfo(byArray));
    }

    public CMSSignedData(CMSProcessable cMSProcessable, byte[] byArray) throws CMSException {
        this(cMSProcessable, CMSUtils.readContentInfo(byArray));
    }

    public CMSSignedData(Map map, byte[] byArray) throws CMSException {
        this(map, CMSUtils.readContentInfo(byArray));
    }

    public CMSSignedData(CMSProcessable cMSProcessable, InputStream inputStream) throws CMSException {
        this(cMSProcessable, CMSUtils.readContentInfo((InputStream)new ASN1InputStream(inputStream)));
    }

    public CMSSignedData(InputStream inputStream) throws CMSException {
        this(CMSUtils.readContentInfo(inputStream));
    }

    public CMSSignedData(final CMSProcessable cMSProcessable, ContentInfo contentInfo) throws CMSException {
        this.signedContent = cMSProcessable instanceof CMSTypedData ? (CMSTypedData)cMSProcessable : new CMSTypedData(){

            public ASN1ObjectIdentifier getContentType() {
                return CMSSignedData.this.signedData.getEncapContentInfo().getContentType();
            }

            public void write(OutputStream outputStream) throws IOException, CMSException {
                cMSProcessable.write(outputStream);
            }

            public Object getContent() {
                return cMSProcessable.getContent();
            }
        };
        this.contentInfo = contentInfo;
        this.signedData = this.getSignedData();
    }

    public CMSSignedData(Map map, ContentInfo contentInfo) throws CMSException {
        this.hashes = map;
        this.contentInfo = contentInfo;
        this.signedData = this.getSignedData();
    }

    public CMSSignedData(ContentInfo contentInfo) throws CMSException {
        this.contentInfo = contentInfo;
        this.signedData = this.getSignedData();
        ASN1Encodable aSN1Encodable = this.signedData.getEncapContentInfo().getContent();
        this.signedContent = aSN1Encodable != null ? (aSN1Encodable instanceof ASN1OctetString ? new CMSProcessableByteArray(this.signedData.getEncapContentInfo().getContentType(), ((ASN1OctetString)aSN1Encodable).getOctets()) : new PKCS7ProcessableObject(this.signedData.getEncapContentInfo().getContentType(), aSN1Encodable)) : null;
    }

    private SignedData getSignedData() throws CMSException {
        try {
            return SignedData.getInstance(this.contentInfo.getContent());
        } catch (ClassCastException classCastException) {
            throw new CMSException("Malformed content.", classCastException);
        } catch (IllegalArgumentException illegalArgumentException) {
            throw new CMSException("Malformed content.", illegalArgumentException);
        }
    }

    public int getVersion() {
        return this.signedData.getVersion().getValue().intValue();
    }

    public SignerInformationStore getSignerInfos() {
        if (this.signerInfoStore == null) {
            ASN1Set aSN1Set = this.signedData.getSignerInfos();
            ArrayList<SignerInformation> arrayList = new ArrayList<SignerInformation>();
            for (int i = 0; i != aSN1Set.size(); ++i) {
                SignerInfo signerInfo = SignerInfo.getInstance(aSN1Set.getObjectAt(i));
                ASN1ObjectIdentifier aSN1ObjectIdentifier = this.signedData.getEncapContentInfo().getContentType();
                if (this.hashes == null) {
                    arrayList.add(new SignerInformation(signerInfo, aSN1ObjectIdentifier, this.signedContent, null));
                    continue;
                }
                Object k = this.hashes.keySet().iterator().next();
                byte[] byArray = k instanceof String ? (byte[])this.hashes.get(signerInfo.getDigestAlgorithm().getAlgorithm().getId()) : (byte[])this.hashes.get(signerInfo.getDigestAlgorithm().getAlgorithm());
                arrayList.add(new SignerInformation(signerInfo, aSN1ObjectIdentifier, null, byArray));
            }
            this.signerInfoStore = new SignerInformationStore(arrayList);
        }
        return this.signerInfoStore;
    }

    public boolean isDetachedSignature() {
        return this.signedData.getEncapContentInfo().getContent() == null && this.signedData.getSignerInfos().size() > 0;
    }

    public boolean isCertificateManagementMessage() {
        return this.signedData.getEncapContentInfo().getContent() == null && this.signedData.getSignerInfos().size() == 0;
    }

    public Store<X509CertificateHolder> getCertificates() {
        return HELPER.getCertificates(this.signedData.getCertificates());
    }

    public Store<X509CRLHolder> getCRLs() {
        return HELPER.getCRLs(this.signedData.getCRLs());
    }

    public Store<X509AttributeCertificateHolder> getAttributeCertificates() {
        return HELPER.getAttributeCertificates(this.signedData.getCertificates());
    }

    public Store getOtherRevocationInfo(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        return HELPER.getOtherRevocationInfo(aSN1ObjectIdentifier, this.signedData.getCRLs());
    }

    public Set<AlgorithmIdentifier> getDigestAlgorithmIDs() {
        HashSet<AlgorithmIdentifier> hashSet = new HashSet<AlgorithmIdentifier>(this.signedData.getDigestAlgorithms().size());
        Enumeration enumeration = this.signedData.getDigestAlgorithms().getObjects();
        while (enumeration.hasMoreElements()) {
            hashSet.add(AlgorithmIdentifier.getInstance(enumeration.nextElement()));
        }
        return Collections.unmodifiableSet(hashSet);
    }

    public String getSignedContentTypeOID() {
        return this.signedData.getEncapContentInfo().getContentType().getId();
    }

    public CMSTypedData getSignedContent() {
        return this.signedContent;
    }

    public ContentInfo toASN1Structure() {
        return this.contentInfo;
    }

    @Override
    public byte[] getEncoded() throws IOException {
        return this.contentInfo.getEncoded();
    }

    public boolean verifySignatures(SignerInformationVerifierProvider signerInformationVerifierProvider) throws CMSException {
        return this.verifySignatures(signerInformationVerifierProvider, false);
    }

    public boolean verifySignatures(SignerInformationVerifierProvider signerInformationVerifierProvider, boolean bl) throws CMSException {
        Collection<SignerInformation> collection = this.getSignerInfos().getSigners();
        for (SignerInformation signerInformation : collection) {
            try {
                SignerInformationVerifier signerInformationVerifier = signerInformationVerifierProvider.get(signerInformation.getSID());
                if (!signerInformation.verify(signerInformationVerifier)) {
                    return false;
                }
                if (bl) continue;
                Collection<SignerInformation> collection2 = signerInformation.getCounterSignatures().getSigners();
                Iterator<SignerInformation> iterator = collection2.iterator();
                while (iterator.hasNext()) {
                    if (this.verifyCounterSignature(iterator.next(), signerInformationVerifierProvider)) continue;
                    return false;
                }
            } catch (OperatorCreationException operatorCreationException) {
                throw new CMSException("failure in verifier provider: " + operatorCreationException.getMessage(), operatorCreationException);
            }
        }
        return true;
    }

    private boolean verifyCounterSignature(SignerInformation signerInformation, SignerInformationVerifierProvider signerInformationVerifierProvider) throws OperatorCreationException, CMSException {
        SignerInformationVerifier signerInformationVerifier = signerInformationVerifierProvider.get(signerInformation.getSID());
        if (!signerInformation.verify(signerInformationVerifier)) {
            return false;
        }
        Collection<SignerInformation> collection = signerInformation.getCounterSignatures().getSigners();
        Iterator<SignerInformation> iterator = collection.iterator();
        while (iterator.hasNext()) {
            if (this.verifyCounterSignature(iterator.next(), signerInformationVerifierProvider)) continue;
            return false;
        }
        return true;
    }

    public static CMSSignedData replaceSigners(CMSSignedData cMSSignedData, SignerInformationStore signerInformationStore) {
        CMSSignedData cMSSignedData2 = new CMSSignedData(cMSSignedData);
        cMSSignedData2.signerInfoStore = signerInformationStore;
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        ASN1EncodableVector aSN1EncodableVector2 = new ASN1EncodableVector();
        for (SignerInformation object2 : signerInformationStore.getSigners()) {
            aSN1EncodableVector.add(CMSSignedHelper.INSTANCE.fixAlgID(object2.getDigestAlgorithmID()));
            aSN1EncodableVector2.add(object2.toASN1Structure());
        }
        DERSet dERSet = new DERSet(aSN1EncodableVector);
        DERSet dERSet2 = new DERSet(aSN1EncodableVector2);
        ASN1Sequence aSN1Sequence = (ASN1Sequence)cMSSignedData.signedData.toASN1Primitive();
        aSN1EncodableVector2 = new ASN1EncodableVector();
        aSN1EncodableVector2.add(aSN1Sequence.getObjectAt(0));
        aSN1EncodableVector2.add(dERSet);
        for (int i = 2; i != aSN1Sequence.size() - 1; ++i) {
            aSN1EncodableVector2.add(aSN1Sequence.getObjectAt(i));
        }
        aSN1EncodableVector2.add(dERSet2);
        cMSSignedData2.signedData = SignedData.getInstance(new BERSequence(aSN1EncodableVector2));
        cMSSignedData2.contentInfo = new ContentInfo(cMSSignedData2.contentInfo.getContentType(), cMSSignedData2.signedData);
        return cMSSignedData2;
    }

    public static CMSSignedData replaceCertificatesAndCRLs(CMSSignedData cMSSignedData, Store store, Store store2, Store store3) throws CMSException {
        Iterable iterable;
        CMSSignedData cMSSignedData2 = new CMSSignedData(cMSSignedData);
        ASN1Set aSN1Set = null;
        Iterable iterable2 = null;
        if (store != null || store2 != null) {
            ASN1Set aSN1Set2;
            iterable = new ArrayList();
            if (store != null) {
                iterable.addAll(CMSUtils.getCertificatesFromStore(store));
            }
            if (store2 != null) {
                iterable.addAll(CMSUtils.getAttributeCertificatesFromStore(store2));
            }
            if ((aSN1Set2 = CMSUtils.createBerSetFromList(iterable)).size() != 0) {
                aSN1Set = aSN1Set2;
            }
        }
        if (store3 != null && ((ASN1Set)(iterable = CMSUtils.createBerSetFromList(CMSUtils.getCRLsFromStore(store3)))).size() != 0) {
            iterable2 = iterable;
        }
        cMSSignedData2.signedData = new SignedData(cMSSignedData.signedData.getDigestAlgorithms(), cMSSignedData.signedData.getEncapContentInfo(), aSN1Set, (ASN1Set)iterable2, cMSSignedData.signedData.getSignerInfos());
        cMSSignedData2.contentInfo = new ContentInfo(cMSSignedData2.contentInfo.getContentType(), cMSSignedData2.signedData);
        return cMSSignedData2;
    }
}

