/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xslf.usermodel;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamReader;
import org.apache.poi.hpsf.ClassID;
import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.ooxml.POIXMLException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.openxml4j.opc.PackageRelationship;
import org.apache.poi.poifs.filesystem.FileMagic;
import org.apache.poi.poifs.filesystem.Ole10Native;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.sl.usermodel.ObjectMetaData;
import org.apache.poi.sl.usermodel.ObjectShape;
import org.apache.poi.util.Internal;
import org.apache.poi.xslf.usermodel.XSLFFactory;
import org.apache.poi.xslf.usermodel.XSLFGraphicFrame;
import org.apache.poi.xslf.usermodel.XSLFObjectData;
import org.apache.poi.xslf.usermodel.XSLFPictureData;
import org.apache.poi.xslf.usermodel.XSLFRelation;
import org.apache.poi.xslf.usermodel.XSLFShape;
import org.apache.poi.xslf.usermodel.XSLFSheet;
import org.apache.poi.xslf.usermodel.XSLFTextParagraph;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlException;
import org.openxmlformats.schemas.drawingml.x2006.main.CTBlip;
import org.openxmlformats.schemas.drawingml.x2006.main.CTBlipFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGraphicalObjectData;
import org.openxmlformats.schemas.drawingml.x2006.main.CTNonVisualDrawingProps;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPoint2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPositiveSize2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTransform2D;
import org.openxmlformats.schemas.drawingml.x2006.main.STShapeType;
import org.openxmlformats.schemas.presentationml.x2006.main.CTGraphicalObjectFrame;
import org.openxmlformats.schemas.presentationml.x2006.main.CTGraphicalObjectFrameNonVisual;
import org.openxmlformats.schemas.presentationml.x2006.main.CTGroupShape;
import org.openxmlformats.schemas.presentationml.x2006.main.CTOleObject;
import org.openxmlformats.schemas.presentationml.x2006.main.CTPicture;
import org.openxmlformats.schemas.presentationml.x2006.main.CTPictureNonVisual;

public class XSLFObjectShape
extends XSLFGraphicFrame
implements ObjectShape<XSLFShape, XSLFTextParagraph> {
    static final String OLE_URI = "http://schemas.openxmlformats.org/presentationml/2006/ole";
    private static final QName[] GRAPHIC = new QName[]{new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "graphic")};
    private static final QName[] GRAPHIC_DATA = new QName[]{new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "graphicData")};
    private static final QName[] OLE_OBJ = new QName[]{new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "oleObj")};
    private static final QName[] CT_PICTURE = new QName[]{new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "pic")};
    private CTOleObject _oleObject;
    private XSLFPictureData _data;

    XSLFObjectShape(CTGraphicalObjectFrame shape, XSLFSheet sheet) {
        super(shape, sheet);
        try {
            this._oleObject = this.selectProperty(CTOleObject.class, null, GRAPHIC, GRAPHIC_DATA, OLE_OBJ);
        } catch (XmlException e) {
            throw new IllegalStateException(e);
        }
    }

    @Internal
    public CTOleObject getCTOleObject() {
        return this._oleObject;
    }

    @Override
    public XSLFObjectData getObjectData() {
        String oleRel = this.getCTOleObject().getId();
        return (XSLFObjectData)this.getSheet().getRelationPartById(oleRel).getDocumentPart();
    }

    @Override
    public String getProgId() {
        return this._oleObject == null ? null : this._oleObject.getProgId();
    }

    @Override
    public String getFullName() {
        return this._oleObject == null ? null : this._oleObject.getName();
    }

    @Override
    public XSLFPictureData getPictureData() {
        if (this._data == null) {
            String blipId = this.getBlipId();
            if (blipId == null) {
                return null;
            }
            PackagePart p = this.getSheet().getPackagePart();
            PackageRelationship rel = p.getRelationship(blipId);
            if (rel != null) {
                try {
                    PackagePart imgPart = p.getRelatedPart(rel);
                    this._data = new XSLFPictureData(imgPart);
                } catch (Exception e) {
                    throw new POIXMLException(e);
                }
            }
        }
        return this._data;
    }

    protected CTBlip getBlip() {
        return this.getBlipFill().getBlip();
    }

    protected String getBlipId() {
        String id = this.getBlip().getEmbed();
        if (id.isEmpty()) {
            return null;
        }
        return id;
    }

    protected CTBlipFillProperties getBlipFill() {
        try {
            CTPicture pic = this.selectProperty(CTPicture.class, XSLFObjectShape::parse, GRAPHIC, GRAPHIC_DATA, OLE_OBJ, CT_PICTURE);
            return pic != null ? pic.getBlipFill() : null;
        } catch (XmlException e) {
            return null;
        }
    }

    private static CTPicture parse(XMLStreamReader reader) throws XmlException {
        CTGroupShape gs = CTGroupShape.Factory.parse(reader);
        return gs.sizeOfPicArray() > 0 ? gs.getPicArray(0) : null;
    }

    @Override
    public OutputStream updateObjectData(ObjectMetaData.Application application, ObjectMetaData metaData) throws IOException {
        POIXMLDocumentPart.RelationPart rp;
        ObjectMetaData md;
        ObjectMetaData objectMetaData = md = application != null ? application.getMetaData() : metaData;
        if (md == null || md.getClassID() == null) {
            throw new IllegalArgumentException("either application and/or metaData needs to be set.");
        }
        XSLFSheet sheet = this.getSheet();
        if (this._oleObject.isSetId()) {
            rp = sheet.getRelationPartById(this._oleObject.getId());
        } else {
            try {
                XSLFRelation descriptor = XSLFRelation.OLE_OBJECT;
                OPCPackage pack = sheet.getPackagePart().getPackage();
                int nextIdx = pack.getUnusedPartIndex(descriptor.getDefaultFileName());
                rp = sheet.createRelationship(descriptor, XSLFFactory.getInstance(), nextIdx, false);
                this._oleObject.setId(rp.getRelationship().getId());
            } catch (InvalidFormatException e) {
                throw new IOException("Unable to add new ole embedding", e);
            }
        }
        this._oleObject.setProgId(md.getProgId());
        this._oleObject.setName(md.getObjectName());
        return new XSLFObjectOutputStream(((POIXMLDocumentPart)rp.getDocumentPart()).getPackagePart(), md);
    }

    static CTGraphicalObjectFrame prototype(int shapeId, String picRel) {
        CTGraphicalObjectFrame frame = CTGraphicalObjectFrame.Factory.newInstance();
        CTGraphicalObjectFrameNonVisual nvGr = frame.addNewNvGraphicFramePr();
        CTNonVisualDrawingProps cnv = nvGr.addNewCNvPr();
        cnv.setName("Object " + shapeId);
        cnv.setId(shapeId);
        nvGr.addNewCNvGraphicFramePr();
        nvGr.addNewNvPr();
        frame.addNewXfrm();
        CTGraphicalObjectData gr = frame.addNewGraphic().addNewGraphicData();
        gr.setUri(OLE_URI);
        XmlCursor grCur = gr.newCursor();
        grCur.toEndToken();
        grCur.beginElement(new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "oleObj"));
        grCur.insertElement(new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "embed"));
        CTGroupShape grpShp = CTGroupShape.Factory.newInstance();
        CTPicture pic = grpShp.addNewPic();
        CTPictureNonVisual nvPicPr = pic.addNewNvPicPr();
        CTNonVisualDrawingProps cNvPr = nvPicPr.addNewCNvPr();
        cNvPr.setName("");
        cNvPr.setId(0L);
        nvPicPr.addNewCNvPicPr();
        nvPicPr.addNewNvPr();
        CTBlipFillProperties blip = pic.addNewBlipFill();
        blip.addNewBlip().setEmbed(picRel);
        blip.addNewStretch().addNewFillRect();
        CTShapeProperties spPr = pic.addNewSpPr();
        CTTransform2D xfrm = spPr.addNewXfrm();
        CTPoint2D off = xfrm.addNewOff();
        off.setX(1270000L);
        off.setY(1270000L);
        CTPositiveSize2D xext = xfrm.addNewExt();
        xext.setCx(1270000L);
        xext.setCy(1270000L);
        spPr.addNewPrstGeom().setPrst(STShapeType.RECT);
        XmlCursor picCur = grpShp.newCursor();
        picCur.toStartDoc();
        picCur.moveXmlContents(grCur);
        picCur.dispose();
        grCur.dispose();
        return frame;
    }

    private static class XSLFObjectOutputStream
    extends ByteArrayOutputStream {
        final PackagePart objectPart;
        final ObjectMetaData metaData;

        private XSLFObjectOutputStream(PackagePart objectPart, ObjectMetaData metaData) {
            super(100000);
            this.objectPart = objectPart;
            this.metaData = metaData;
        }

        @Override
        public void close() throws IOException {
            block39: {
                this.objectPart.clear();
                try (OutputStream os = this.objectPart.getOutputStream();){
                    ByteArrayInputStream bis = new ByteArrayInputStream(this.buf, 0, this.size());
                    FileMagic fm = FileMagic.valueOf(this.buf);
                    if (fm == FileMagic.OLE2) {
                        try (POIFSFileSystem poifs = new POIFSFileSystem(bis);){
                            poifs.getRoot().setStorageClsid(this.metaData.getClassID());
                            poifs.writeFilesystem(os);
                            break block39;
                        }
                    }
                    if (this.metaData.getOleEntry() == null) {
                        os.write(this.buf, 0, this.size());
                        break block39;
                    }
                    try (POIFSFileSystem poifs = new POIFSFileSystem();){
                        ClassID clsId = this.metaData.getClassID();
                        if (clsId != null) {
                            poifs.getRoot().setStorageClsid(clsId);
                        }
                        poifs.createDocument(bis, this.metaData.getOleEntry());
                        Ole10Native.createOleMarkerEntry(poifs);
                        poifs.writeFilesystem(os);
                    }
                }
            }
        }
    }
}

