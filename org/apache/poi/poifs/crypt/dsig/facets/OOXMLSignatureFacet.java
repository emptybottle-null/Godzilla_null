/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.poifs.crypt.dsig.facets;

import com.microsoft.schemas.office.x2006.digsig.CTSignatureInfoV1;
import com.microsoft.schemas.office.x2006.digsig.SignatureInfoV1Document;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.xml.crypto.XMLStructure;
import javax.xml.crypto.dom.DOMStructure;
import javax.xml.crypto.dsig.Manifest;
import javax.xml.crypto.dsig.Reference;
import javax.xml.crypto.dsig.SignatureProperties;
import javax.xml.crypto.dsig.SignatureProperty;
import javax.xml.crypto.dsig.Transform;
import javax.xml.crypto.dsig.XMLObject;
import javax.xml.crypto.dsig.XMLSignatureException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.openxml4j.opc.PackagePartName;
import org.apache.poi.openxml4j.opc.PackageRelationship;
import org.apache.poi.openxml4j.opc.PackageRelationshipCollection;
import org.apache.poi.openxml4j.opc.PackagingURIHelper;
import org.apache.poi.openxml4j.opc.TargetMode;
import org.apache.poi.poifs.crypt.dsig.facets.SignatureFacet;
import org.apache.poi.poifs.crypt.dsig.services.RelationshipTransformService;
import org.apache.poi.util.POILogFactory;
import org.apache.poi.util.POILogger;
import org.openxmlformats.schemas.xpackage.x2006.digitalSignature.CTSignatureTime;
import org.openxmlformats.schemas.xpackage.x2006.digitalSignature.SignatureTimeDocument;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class OOXMLSignatureFacet
extends SignatureFacet {
    private static final POILogger LOG = POILogFactory.getLogger(OOXMLSignatureFacet.class);
    private static final String ID_PACKAGE_OBJECT = "idPackageObject";
    private static final Set<String> signed = Collections.unmodifiableSet(new HashSet<String>(Arrays.asList("activeXControlBinary", "aFChunk", "attachedTemplate", "attachedToolbars", "audio", "calcChain", "chart", "chartColorStyle", "chartLayout", "chartsheet", "chartStyle", "chartUserShapes", "commentAuthors", "comments", "connections", "connectorXml", "control", "ctrlProp", "customData", "customData", "customProperty", "customXml", "diagram", "diagramColors", "diagramColorsHeader", "diagramData", "diagramDrawing", "diagramLayout", "diagramLayoutHeader", "diagramQuickStyle", "diagramQuickStyleHeader", "dialogsheet", "dictionary", "documentParts", "downRev", "drawing", "endnotes", "externalLink", "externalLinkPath", "font", "fontTable", "footer", "footnotes", "functionPrototypes", "glossaryDocument", "graphicFrameDoc", "groupShapeXml", "handoutMaster", "hdphoto", "header", "hyperlink", "image", "ink", "inkXml", "keyMapCustomizations", "legacyDiagramText", "legacyDocTextInfo", "mailMergeHeaderSource", "mailMergeRecipientData", "mailMergeSource", "media", "notesMaster", "notesSlide", "numbering", "officeDocument", "officeDocument", "oleObject", "package", "pictureXml", "pivotCacheDefinition", "pivotCacheRecords", "pivotTable", "powerPivotData", "presProps", "printerSettings", "queryTable", "recipientData", "settings", "shapeXml", "sharedStrings", "sheetMetadata", "slicer", "slicer", "slicerCache", "slicerCache", "slide", "slideLayout", "slideMaster", "slideUpdateInfo", "slideUpdateUrl", "smartTags", "styles", "stylesWithEffects", "table", "tableSingleCells", "tableStyles", "tags", "theme", "themeOverride", "timeline", "timelineCache", "transform", "ui/altText", "ui/buttonSize", "ui/controlID", "ui/description", "ui/enabled", "ui/extensibility", "ui/extensibility", "ui/helperText", "ui/imageID", "ui/imageMso", "ui/keyTip", "ui/label", "ui/lcid", "ui/loud", "ui/pressed", "ui/progID", "ui/ribbonID", "ui/showImage", "ui/showLabel", "ui/supertip", "ui/target", "ui/text", "ui/title", "ui/tooltip", "ui/userCustomization", "ui/visible", "userXmlData", "vbaProject", "video", "viewProps", "vmlDrawing", "volatileDependencies", "webSettings", "wordVbaData", "worksheet", "wsSortMap", "xlBinaryIndex", "xlExternalLinkPath/xlAlternateStartup", "xlExternalLinkPath/xlLibrary", "xlExternalLinkPath/xlPathMissing", "xlExternalLinkPath/xlStartup", "xlIntlMacrosheet", "xlMacrosheet", "xmlMaps")));

    @Override
    public void preSign(Document document, List<Reference> references, List<XMLObject> objects) throws XMLSignatureException {
        LOG.log(1, "pre sign");
        this.addManifestObject(document, references, objects);
        this.addSignatureInfo(document, references, objects);
    }

    protected void addManifestObject(Document document, List<Reference> references, List<XMLObject> objects) throws XMLSignatureException {
        ArrayList<Reference> manifestReferences = new ArrayList<Reference>();
        this.addManifestReferences(manifestReferences);
        Manifest manifest = this.getSignatureFactory().newManifest(manifestReferences);
        ArrayList<XMLStructure> objectContent = new ArrayList<XMLStructure>();
        objectContent.add(manifest);
        this.addSignatureTime(document, objectContent);
        XMLObject xo = this.getSignatureFactory().newXMLObject(objectContent, ID_PACKAGE_OBJECT, null, null);
        objects.add(xo);
        Reference reference = this.newReference("#idPackageObject", null, "http://www.w3.org/2000/09/xmldsig#Object", null, null);
        references.add(reference);
    }

    protected void addManifestReferences(List<Reference> manifestReferences) throws XMLSignatureException {
        OPCPackage ooxml = this.signatureConfig.getOpcPackage();
        ArrayList<PackagePart> relsEntryNames = ooxml.getPartsByContentType("application/vnd.openxmlformats-package.relationships+xml");
        HashSet<String> digestedPartNames = new HashSet<String>();
        for (PackagePart pp : relsEntryNames) {
            PackageRelationshipCollection prc;
            String baseUri = pp.getPartName().getName().replaceFirst("(.*)/_rels/.*", "$1");
            try {
                prc = new PackageRelationshipCollection(ooxml);
                prc.parseRelationshipsPart(pp);
            } catch (InvalidFormatException e) {
                throw new XMLSignatureException("Invalid relationship descriptor: " + pp.getPartName().getName(), e);
            }
            RelationshipTransformService.RelationshipTransformParameterSpec parameterSpec = new RelationshipTransformService.RelationshipTransformParameterSpec();
            for (PackageRelationship relationship : prc) {
                String contentType;
                String relationshipType = relationship.getRelationshipType();
                if (TargetMode.EXTERNAL == relationship.getTargetMode() || !OOXMLSignatureFacet.isSignedRelationship(relationshipType)) continue;
                parameterSpec.addRelationshipReference(relationship.getId());
                String partName = OOXMLSignatureFacet.normalizePartName(relationship.getTargetURI(), baseUri);
                if (digestedPartNames.contains(partName)) continue;
                digestedPartNames.add(partName);
                try {
                    PackagePartName relName = PackagingURIHelper.createPartName(partName);
                    PackagePart pp2 = ooxml.getPart(relName);
                    contentType = pp2.getContentType();
                } catch (InvalidFormatException e) {
                    throw new XMLSignatureException(e);
                }
                if (relationshipType.endsWith("customXml") && !contentType.equals("inkml+xml") && !contentType.equals("text/xml")) {
                    LOG.log(1, "skipping customXml with content type: " + contentType);
                    continue;
                }
                String uri = partName + "?ContentType=" + contentType;
                Reference reference = this.newReference(uri, null, null, null, null);
                manifestReferences.add(reference);
            }
            if (!parameterSpec.hasSourceIds()) continue;
            ArrayList<Transform> transforms = new ArrayList<Transform>();
            transforms.add(this.newTransform("http://schemas.openxmlformats.org/package/2006/RelationshipTransform", parameterSpec));
            transforms.add(this.newTransform("http://www.w3.org/TR/2001/REC-xml-c14n-20010315"));
            String uri = OOXMLSignatureFacet.normalizePartName(pp.getPartName().getURI(), baseUri) + "?ContentType=application/vnd.openxmlformats-package.relationships+xml";
            Reference reference = this.newReference(uri, transforms, null, null, null);
            manifestReferences.add(reference);
        }
        manifestReferences.sort(new Comparator<Reference>(){

            @Override
            public int compare(Reference o1, Reference o2) {
                return o1.getURI().compareTo(o2.getURI());
            }
        });
    }

    private static String normalizePartName(URI partName, String baseUri) throws XMLSignatureException {
        String pn = partName.toASCIIString();
        if (!pn.startsWith(baseUri)) {
            pn = baseUri + pn;
        }
        try {
            pn = new URI(pn).normalize().getPath().replace('\\', '/');
            LOG.log(1, "part name: " + pn);
        } catch (URISyntaxException e) {
            throw new XMLSignatureException(e);
        }
        return pn;
    }

    protected void addSignatureTime(Document document, List<XMLStructure> objectContent) {
        SignatureTimeDocument sigTime = SignatureTimeDocument.Factory.newInstance();
        CTSignatureTime ctTime = sigTime.addNewSignatureTime();
        ctTime.setFormat("YYYY-MM-DDThh:mm:ssTZD");
        ctTime.setValue(this.signatureConfig.formatExecutionTime());
        LOG.log(1, "execution time: " + ctTime.getValue());
        Element n = (Element)document.importNode(ctTime.getDomNode(), true);
        ArrayList<DOMStructure> signatureTimeContent = new ArrayList<DOMStructure>();
        signatureTimeContent.add(new DOMStructure(n));
        SignatureProperty signatureTimeSignatureProperty = this.getSignatureFactory().newSignatureProperty(signatureTimeContent, "#" + this.signatureConfig.getPackageSignatureId(), "idSignatureTime");
        ArrayList<SignatureProperty> signaturePropertyContent = new ArrayList<SignatureProperty>();
        signaturePropertyContent.add(signatureTimeSignatureProperty);
        SignatureProperties signatureProperties = this.getSignatureFactory().newSignatureProperties(signaturePropertyContent, null);
        objectContent.add(signatureProperties);
    }

    protected void addSignatureInfo(Document document, List<Reference> references, List<XMLObject> objects) throws XMLSignatureException {
        ArrayList<SignatureProperties> objectContent = new ArrayList<SignatureProperties>();
        SignatureInfoV1Document sigV1 = SignatureInfoV1Document.Factory.newInstance();
        CTSignatureInfoV1 ctSigV1 = sigV1.addNewSignatureInfoV1();
        ctSigV1.setManifestHashAlgorithm(this.signatureConfig.getDigestMethodUri());
        if (this.signatureConfig.getSignatureDescription() != null) {
            ctSigV1.setSignatureComments(this.signatureConfig.getSignatureDescription());
        }
        Element n = (Element)document.importNode(ctSigV1.getDomNode(), true);
        n.setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns", "http://schemas.microsoft.com/office/2006/digsig");
        ArrayList<DOMStructure> signatureInfoContent = new ArrayList<DOMStructure>();
        signatureInfoContent.add(new DOMStructure(n));
        SignatureProperty signatureInfoSignatureProperty = this.getSignatureFactory().newSignatureProperty(signatureInfoContent, "#" + this.signatureConfig.getPackageSignatureId(), "idOfficeV1Details");
        ArrayList<SignatureProperty> signaturePropertyContent = new ArrayList<SignatureProperty>();
        signaturePropertyContent.add(signatureInfoSignatureProperty);
        SignatureProperties signatureProperties = this.getSignatureFactory().newSignatureProperties(signaturePropertyContent, null);
        objectContent.add(signatureProperties);
        String objectId = "idOfficeObject";
        objects.add(this.getSignatureFactory().newXMLObject(objectContent, objectId, null, null));
        Reference reference = this.newReference("#" + objectId, null, "http://www.w3.org/2000/09/xmldsig#Object", null, null);
        references.add(reference);
    }

    protected static String getRelationshipReferenceURI(String zipEntryName) {
        return "/" + zipEntryName + "?ContentType=application/vnd.openxmlformats-package.relationships+xml";
    }

    protected static String getResourceReferenceURI(String resourceName, String contentType) {
        return "/" + resourceName + "?ContentType=" + contentType;
    }

    protected static boolean isSignedRelationship(String relationshipType) {
        LOG.log(1, "relationship type: " + relationshipType);
        String rt = relationshipType.replaceFirst(".*/relationships/", "");
        return signed.contains(rt) || rt.endsWith("customXml");
    }
}

