/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.apache.jcp.xml.dsig.internal.dom.DOMReference
 *  org.apache.jcp.xml.dsig.internal.dom.DOMSignedInfo
 *  org.apache.jcp.xml.dsig.internal.dom.DOMSubTreeData
 *  org.apache.xml.security.Init
 *  org.apache.xml.security.utils.XMLUtils
 */
package org.apache.poi.poifs.crypt.dsig;

import java.io.IOException;
import java.io.OutputStream;
import java.security.GeneralSecurityException;
import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import javax.xml.crypto.Data;
import javax.xml.crypto.MarshalException;
import javax.xml.crypto.URIDereferencer;
import javax.xml.crypto.XMLStructure;
import javax.xml.crypto.dsig.CanonicalizationMethod;
import javax.xml.crypto.dsig.Manifest;
import javax.xml.crypto.dsig.Reference;
import javax.xml.crypto.dsig.SignatureMethod;
import javax.xml.crypto.dsig.SignedInfo;
import javax.xml.crypto.dsig.TransformException;
import javax.xml.crypto.dsig.XMLObject;
import javax.xml.crypto.dsig.XMLSignContext;
import javax.xml.crypto.dsig.XMLSignature;
import javax.xml.crypto.dsig.XMLSignatureException;
import javax.xml.crypto.dsig.XMLSignatureFactory;
import javax.xml.crypto.dsig.dom.DOMSignContext;
import javax.xml.crypto.dsig.spec.C14NMethodParameterSpec;
import org.apache.jcp.xml.dsig.internal.dom.DOMReference;
import org.apache.jcp.xml.dsig.internal.dom.DOMSignedInfo;
import org.apache.jcp.xml.dsig.internal.dom.DOMSubTreeData;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ooxml.POIXMLTypeLoader;
import org.apache.poi.ooxml.util.DocumentHelper;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.openxml4j.opc.PackagePartName;
import org.apache.poi.openxml4j.opc.PackageRelationship;
import org.apache.poi.openxml4j.opc.PackageRelationshipCollection;
import org.apache.poi.openxml4j.opc.PackagingURIHelper;
import org.apache.poi.openxml4j.opc.TargetMode;
import org.apache.poi.poifs.crypt.CryptoFunctions;
import org.apache.poi.poifs.crypt.HashAlgorithm;
import org.apache.poi.poifs.crypt.dsig.DSigRelation;
import org.apache.poi.poifs.crypt.dsig.DigestOutputStream;
import org.apache.poi.poifs.crypt.dsig.SignatureConfig;
import org.apache.poi.poifs.crypt.dsig.SignatureMarshalListener;
import org.apache.poi.poifs.crypt.dsig.SignatureOutputStream;
import org.apache.poi.poifs.crypt.dsig.SignaturePart;
import org.apache.poi.poifs.crypt.dsig.facets.SignatureFacet;
import org.apache.poi.poifs.crypt.dsig.services.RelationshipTransformService;
import org.apache.poi.util.POILogFactory;
import org.apache.poi.util.POILogger;
import org.apache.xml.security.Init;
import org.apache.xml.security.utils.XMLUtils;
import org.apache.xmlbeans.XmlOptions;
import org.w3.x2000.x09.xmldsig.SignatureDocument;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.events.EventListener;
import org.w3c.dom.events.EventTarget;

public class SignatureInfo
implements SignatureConfig.SignatureConfigurable {
    private static final POILogger LOG = POILogFactory.getLogger(SignatureInfo.class);
    private static boolean isInitialized;
    private SignatureConfig signatureConfig;

    public SignatureInfo() {
        SignatureInfo.initXmlProvider();
    }

    public SignatureConfig getSignatureConfig() {
        return this.signatureConfig;
    }

    @Override
    public void setSignatureConfig(SignatureConfig signatureConfig) {
        this.signatureConfig = signatureConfig;
    }

    public boolean verifySignature() {
        Iterator<SignaturePart> iterator = this.getSignatureParts().iterator();
        if (iterator.hasNext()) {
            SignaturePart sp = iterator.next();
            return sp.validate();
        }
        return false;
    }

    public void confirmSignature() throws XMLSignatureException, MarshalException {
        Document document = DocumentHelper.createDocument();
        DOMSignContext xmlSignContext = this.createXMLSignContext(document);
        DOMSignedInfo signedInfo = this.preSign(xmlSignContext);
        String signatureValue = this.signDigest(xmlSignContext, signedInfo);
        this.postSign(xmlSignContext, signatureValue);
    }

    public DOMSignContext createXMLSignContext(Document document) {
        return new DOMSignContext(this.signatureConfig.getKey(), (Node)document);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public String signDigest(DOMSignContext xmlSignContext, DOMSignedInfo signedInfo) {
        PrivateKey key = this.signatureConfig.getKey();
        HashAlgorithm algo = this.signatureConfig.getDigestAlgo();
        int BASE64DEFAULTLENGTH = 76;
        if (algo.hashSize * 4 / 3 > 76 && !XMLUtils.ignoreLineBreaks()) {
            throw new EncryptedDocumentException("The hash size of the choosen hash algorithm (" + (Object)((Object)algo) + " = " + algo.hashSize + " bytes), will motivate XmlSec to add linebreaks to the generated digest, which results in an invalid signature (... at least for Office) - please persuade it otherwise by adding '-Dorg.apache.xml.security.ignoreLineBreaks=true' to the JVM system properties.");
        }
        try (DigestOutputStream dos = SignatureInfo.getDigestStream(algo, key);){
            dos.init();
            Document document = (Document)xmlSignContext.getParent();
            Element el = this.getDsigElement(document, "SignedInfo");
            DOMSubTreeData subTree = new DOMSubTreeData((Node)el, true);
            signedInfo.getCanonicalizationMethod().transform((Data)subTree, xmlSignContext, dos);
            String string = Base64.getEncoder().encodeToString(dos.sign());
            return string;
        } catch (IOException | GeneralSecurityException | TransformException e) {
            throw new EncryptedDocumentException(e);
        }
    }

    private static DigestOutputStream getDigestStream(HashAlgorithm algo, PrivateKey key) {
        switch (algo) {
            case md2: 
            case md5: 
            case sha1: 
            case sha256: 
            case sha384: 
            case sha512: {
                return new SignatureOutputStream(algo, key);
            }
        }
        return new DigestOutputStream(algo, key);
    }

    public Iterable<SignaturePart> getSignatureParts() {
        this.signatureConfig.init(true);
        return new Iterable<SignaturePart>(){

            @Override
            public Iterator<SignaturePart> iterator() {
                return new Iterator<SignaturePart>(){
                    OPCPackage pkg;
                    Iterator<PackageRelationship> sigOrigRels;
                    Iterator<PackageRelationship> sigRels;
                    PackagePart sigPart;
                    {
                        this.pkg = SignatureInfo.this.signatureConfig.getOpcPackage();
                        this.sigOrigRels = this.pkg.getRelationshipsByType("http://schemas.openxmlformats.org/package/2006/relationships/digital-signature/origin").iterator();
                    }

                    @Override
                    public boolean hasNext() {
                        while (this.sigRels == null || !this.sigRels.hasNext()) {
                            if (!this.sigOrigRels.hasNext()) {
                                return false;
                            }
                            this.sigPart = this.pkg.getPart(this.sigOrigRels.next());
                            LOG.log(1, "Digital Signature Origin part", this.sigPart);
                            try {
                                this.sigRels = this.sigPart.getRelationshipsByType("http://schemas.openxmlformats.org/package/2006/relationships/digital-signature/signature").iterator();
                            } catch (InvalidFormatException e) {
                                LOG.log(5, "Reference to signature is invalid.", e);
                            }
                        }
                        return true;
                    }

                    @Override
                    public SignaturePart next() {
                        PackagePart sigRelPart = null;
                        do {
                            try {
                                if (!this.hasNext()) {
                                    throw new NoSuchElementException();
                                }
                                sigRelPart = this.sigPart.getRelatedPart(this.sigRels.next());
                                LOG.log(1, "XML Signature part", sigRelPart);
                            } catch (InvalidFormatException e) {
                                LOG.log(5, "Reference to signature is invalid.", e);
                            }
                        } while (sigRelPart == null);
                        return new SignaturePart(sigRelPart, SignatureInfo.this.signatureConfig);
                    }

                    @Override
                    public void remove() {
                        throw new UnsupportedOperationException();
                    }
                };
            }
        };
    }

    protected static synchronized void initXmlProvider() {
        if (isInitialized) {
            return;
        }
        isInitialized = true;
        try {
            Init.init();
            RelationshipTransformService.registerDsigProvider();
            CryptoFunctions.registerBouncyCastle();
        } catch (Exception e) {
            throw new RuntimeException("Xml & BouncyCastle-Provider initialization failed", e);
        }
    }

    public DOMSignedInfo preSign(DOMSignContext xmlSignContext) throws XMLSignatureException, MarshalException {
        SignedInfo signedInfo;
        URIDereferencer uriDereferencer;
        this.signatureConfig.init(false);
        Document document = (Document)xmlSignContext.getParent();
        EventTarget target = (EventTarget)((Object)document);
        EventListener creationListener = this.signatureConfig.getSignatureMarshalListener();
        if (creationListener != null) {
            if (creationListener instanceof SignatureMarshalListener) {
                ((SignatureMarshalListener)creationListener).setEventTarget(target);
            }
            SignatureMarshalListener.setListener(target, creationListener, true);
        }
        if (null != (uriDereferencer = this.signatureConfig.getUriDereferencer())) {
            xmlSignContext.setURIDereferencer(uriDereferencer);
        }
        this.signatureConfig.getNamespacePrefixes().forEach(xmlSignContext::putNamespacePrefix);
        xmlSignContext.setDefaultNamespacePrefix("");
        XMLSignatureFactory signatureFactory = this.signatureConfig.getSignatureFactory();
        ArrayList<Reference> references = new ArrayList<Reference>();
        ArrayList<XMLObject> objects = new ArrayList<XMLObject>();
        for (SignatureFacet signatureFacet : this.signatureConfig.getSignatureFacets()) {
            LOG.log(1, "invoking signature facet: " + signatureFacet.getClass().getSimpleName());
            signatureFacet.preSign(document, references, objects);
        }
        try {
            SignatureMethod signatureMethod = signatureFactory.newSignatureMethod(this.signatureConfig.getSignatureMethodUri(), null);
            CanonicalizationMethod canonicalizationMethod = signatureFactory.newCanonicalizationMethod(this.signatureConfig.getCanonicalizationMethod(), (C14NMethodParameterSpec)null);
            signedInfo = signatureFactory.newSignedInfo(canonicalizationMethod, signatureMethod, references);
        } catch (GeneralSecurityException e) {
            throw new XMLSignatureException(e);
        }
        String signatureValueId = this.signatureConfig.getPackageSignatureId() + "-signature-value";
        XMLSignature xmlSignature = signatureFactory.newXMLSignature(signedInfo, null, objects, this.signatureConfig.getPackageSignatureId(), signatureValueId);
        xmlSignature.sign(xmlSignContext);
        for (XMLObject object : objects) {
            LOG.log(1, "object java type: " + object.getClass().getName());
            List objectContentList = object.getContent();
            for (XMLStructure objectContent : objectContentList) {
                LOG.log(1, "object content java type: " + objectContent.getClass().getName());
                if (!(objectContent instanceof Manifest)) continue;
                Manifest manifest = (Manifest)objectContent;
                List manifestReferences = manifest.getReferences();
                for (Reference manifestReference : manifestReferences) {
                    if (manifestReference.getDigestValue() != null) continue;
                    DOMReference manifestDOMReference = (DOMReference)manifestReference;
                    manifestDOMReference.digest((XMLSignContext)xmlSignContext);
                }
            }
        }
        List signedInfoReferences = signedInfo.getReferences();
        for (Reference signedInfoReference : signedInfoReferences) {
            DOMReference domReference = (DOMReference)signedInfoReference;
            if (domReference.getDigestValue() != null) continue;
            domReference.digest((XMLSignContext)xmlSignContext);
        }
        return (DOMSignedInfo)signedInfo;
    }

    public void postSign(DOMSignContext xmlSignContext, String signatureValue) throws MarshalException {
        LOG.log(1, "postSign");
        Document document = (Document)xmlSignContext.getParent();
        String signatureId = this.signatureConfig.getPackageSignatureId();
        if (!signatureId.equals(document.getDocumentElement().getAttribute("Id"))) {
            throw new RuntimeException("ds:Signature not found for @Id: " + signatureId);
        }
        Element signatureNode = this.getDsigElement(document, "SignatureValue");
        if (signatureNode == null) {
            throw new RuntimeException("preSign has to be called before postSign");
        }
        signatureNode.setTextContent(signatureValue);
        for (SignatureFacet signatureFacet : this.signatureConfig.getSignatureFacets()) {
            signatureFacet.postSign(document);
        }
        this.writeDocument(document);
    }

    protected void writeDocument(Document document) throws MarshalException {
        XmlOptions xo = new XmlOptions();
        HashMap namespaceMap = new HashMap();
        this.signatureConfig.getNamespacePrefixes().forEach((k, v) -> namespaceMap.put(v, k));
        xo.setSaveSuggestedPrefixes(namespaceMap);
        xo.setUseDefaultNamespace();
        LOG.log(1, "output signed Office OpenXML document");
        OPCPackage pkg = this.signatureConfig.getOpcPackage();
        try {
            PackagePartName sigPartName;
            PackagePart sigPart;
            DSigRelation originDesc = DSigRelation.ORIGIN_SIGS;
            PackagePartName originPartName = PackagingURIHelper.createPartName(originDesc.getFileName(0));
            PackagePart originPart = pkg.getPart(originPartName);
            if (originPart == null) {
                originPart = pkg.createPart(originPartName, originDesc.getContentType());
                pkg.addRelationship(originPartName, TargetMode.INTERNAL, originDesc.getRelation());
            }
            DSigRelation sigDesc = DSigRelation.SIG;
            int nextSigIdx = pkg.getUnusedPartIndex(sigDesc.getDefaultFileName());
            if (!this.signatureConfig.isAllowMultipleSignatures()) {
                PackageRelationshipCollection prc = originPart.getRelationshipsByType(sigDesc.getRelation());
                for (int i = 2; i < nextSigIdx; ++i) {
                    PackagePartName pn = PackagingURIHelper.createPartName(sigDesc.getFileName(i));
                    for (PackageRelationship rel : prc) {
                        PackagePart pp = originPart.getRelatedPart(rel);
                        if (!pp.getPartName().equals(pn)) continue;
                        originPart.removeRelationship(rel.getId());
                        prc.removeRelationship(rel.getId());
                        break;
                    }
                    pkg.removePart(pkg.getPart(pn));
                }
                nextSigIdx = 1;
            }
            if ((sigPart = pkg.getPart(sigPartName = PackagingURIHelper.createPartName(sigDesc.getFileName(nextSigIdx)))) == null) {
                sigPart = pkg.createPart(sigPartName, sigDesc.getContentType());
                originPart.addRelationship(sigPartName, TargetMode.INTERNAL, sigDesc.getRelation());
            } else {
                sigPart.clear();
            }
            OutputStream os = sigPart.getOutputStream();
            Object object = null;
            try {
                SignatureDocument sigDoc = SignatureDocument.Factory.parse(document, POIXMLTypeLoader.DEFAULT_XML_OPTIONS);
                sigDoc.save(os, xo);
            } catch (Throwable throwable) {
                object = throwable;
                throw throwable;
            } finally {
                if (os != null) {
                    if (object != null) {
                        try {
                            os.close();
                        } catch (Throwable throwable) {
                            ((Throwable)object).addSuppressed(throwable);
                        }
                    } else {
                        os.close();
                    }
                }
            }
        } catch (Exception e) {
            throw new MarshalException("Unable to write signature document", e);
        }
    }

    private Element getDsigElement(Document document, String localName) {
        NodeList sigValNl = document.getElementsByTagNameNS("http://www.w3.org/2000/09/xmldsig#", localName);
        if (sigValNl.getLength() == 1) {
            return (Element)sigValNl.item(0);
        }
        LOG.log(5, "Signature element '" + localName + "' was " + (sigValNl.getLength() == 0 ? "not found" : "multiple times"));
        return null;
    }
}

