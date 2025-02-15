/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xssf.usermodel;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.xml.namespace.QName;
import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.ooxml.POIXMLException;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.poifs.filesystem.DirectoryEntry;
import org.apache.poi.poifs.filesystem.DirectoryNode;
import org.apache.poi.poifs.filesystem.FileMagic;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.ObjectData;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.POILogFactory;
import org.apache.poi.util.POILogger;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFPictureData;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFSimpleShape;
import org.apache.xmlbeans.XmlCursor;
import org.openxmlformats.schemas.drawingml.x2006.main.CTNonVisualDrawingProps;
import org.openxmlformats.schemas.drawingml.x2006.main.CTOfficeArtExtension;
import org.openxmlformats.schemas.drawingml.x2006.main.CTOfficeArtExtensionList;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPoint2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPositiveSize2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPresetGeometry2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTransform2D;
import org.openxmlformats.schemas.drawingml.x2006.main.STShapeType;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTShape;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTShapeNonVisual;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTOleObject;

public class XSSFObjectData
extends XSSFSimpleShape
implements ObjectData {
    private static final POILogger LOG = POILogFactory.getLogger(XSSFObjectData.class);
    private static CTShape prototype;
    private CTOleObject oleObject;

    protected XSSFObjectData(XSSFDrawing drawing, CTShape ctShape) {
        super(drawing, ctShape);
    }

    protected static CTShape prototype() {
        String drawNS = "http://schemas.microsoft.com/office/drawing/2010/main";
        if (prototype == null) {
            CTShape shape = CTShape.Factory.newInstance();
            CTShapeNonVisual nv = shape.addNewNvSpPr();
            CTNonVisualDrawingProps nvp = nv.addNewCNvPr();
            nvp.setId(1L);
            nvp.setName("Shape 1");
            CTOfficeArtExtensionList extLst = nvp.addNewExtLst();
            CTOfficeArtExtension ext = extLst.addNewExt();
            ext.setUri("{63B3BB69-23CF-44E3-9099-C40C66FF867C}");
            XmlCursor cur = ext.newCursor();
            cur.toEndToken();
            cur.beginElement(new QName("http://schemas.microsoft.com/office/drawing/2010/main", "compatExt", "a14"));
            cur.insertNamespace("a14", "http://schemas.microsoft.com/office/drawing/2010/main");
            cur.insertAttributeWithValue("spid", "_x0000_s1");
            cur.dispose();
            nv.addNewCNvSpPr();
            CTShapeProperties sp = shape.addNewSpPr();
            CTTransform2D t2d = sp.addNewXfrm();
            CTPositiveSize2D p1 = t2d.addNewExt();
            p1.setCx(0L);
            p1.setCy(0L);
            CTPoint2D p2 = t2d.addNewOff();
            p2.setX(0L);
            p2.setY(0L);
            CTPresetGeometry2D geom = sp.addNewPrstGeom();
            geom.setPrst(STShapeType.RECT);
            geom.addNewAvLst();
            prototype = shape;
        }
        return prototype;
    }

    @Override
    public String getOLE2ClassName() {
        return this.getOleObject().getProgId();
    }

    public CTOleObject getOleObject() {
        if (this.oleObject == null) {
            long shapeId = this.getCTShape().getNvSpPr().getCNvPr().getId();
            this.oleObject = this.getSheet().readOleObject(shapeId);
            if (this.oleObject == null) {
                throw new POIXMLException("Ole object not found in sheet container - it's probably a control element");
            }
        }
        return this.oleObject;
    }

    @Override
    public byte[] getObjectData() throws IOException {
        InputStream is = this.getObjectPart().getInputStream();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        IOUtils.copy(is, bos);
        is.close();
        return bos.toByteArray();
    }

    public PackagePart getObjectPart() {
        if (!this.getOleObject().isSetId()) {
            throw new POIXMLException("Invalid ole object found in sheet container");
        }
        POIXMLDocumentPart pdp = this.getSheet().getRelationById(this.getOleObject().getId());
        return pdp == null ? null : pdp.getPackagePart();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean hasDirectoryEntry() {
        InputStream is = null;
        try {
            is = this.getObjectPart().getInputStream();
            is = FileMagic.prepareToCheckMagic(is);
            boolean bl = FileMagic.valueOf(is) == FileMagic.OLE2;
            return bl;
        } catch (IOException e) {
            LOG.log(5, "can't determine if directory entry exists", e);
            boolean bl = false;
            return bl;
        } finally {
            IOUtils.closeQuietly(is);
        }
    }

    @Override
    public DirectoryEntry getDirectory() throws IOException {
        try (InputStream is = this.getObjectPart().getInputStream();){
            DirectoryNode directoryNode = new POIFSFileSystem(is).getRoot();
            return directoryNode;
        }
    }

    @Override
    public String getFileName() {
        return this.getObjectPart().getPartName().getName();
    }

    protected XSSFSheet getSheet() {
        return (XSSFSheet)this.getDrawing().getParent();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XSSFPictureData getPictureData() {
        XmlCursor cur = this.getOleObject().newCursor();
        try {
            if (cur.toChild("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "objectPr")) {
                String blipId = cur.getAttributeText(new QName("http://schemas.openxmlformats.org/officeDocument/2006/relationships", "id"));
                XSSFPictureData xSSFPictureData = (XSSFPictureData)this.getSheet().getRelationById(blipId);
                return xSSFPictureData;
            }
            XSSFPictureData xSSFPictureData = null;
            return xSSFPictureData;
        } finally {
            cur.dispose();
        }
    }

    @Override
    public String getContentType() {
        return this.getObjectPart().getContentType();
    }
}

