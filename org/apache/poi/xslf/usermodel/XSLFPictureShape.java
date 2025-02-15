/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xslf.usermodel;

import java.awt.Insets;
import java.awt.geom.Dimension2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import javax.imageio.ImageIO;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamReader;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.openxml4j.opc.PackageRelationship;
import org.apache.poi.sl.usermodel.PictureData;
import org.apache.poi.sl.usermodel.PictureShape;
import org.apache.poi.sl.usermodel.Placeholder;
import org.apache.poi.util.POILogFactory;
import org.apache.poi.util.POILogger;
import org.apache.poi.util.Units;
import org.apache.poi.xslf.draw.SVGImageRenderer;
import org.apache.poi.xslf.usermodel.XSLFPictureData;
import org.apache.poi.xslf.usermodel.XSLFRelation;
import org.apache.poi.xslf.usermodel.XSLFShape;
import org.apache.poi.xslf.usermodel.XSLFSheet;
import org.apache.poi.xslf.usermodel.XSLFSimpleShape;
import org.apache.poi.xslf.usermodel.XSLFTextParagraph;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.openxmlformats.schemas.drawingml.x2006.main.CTBlip;
import org.openxmlformats.schemas.drawingml.x2006.main.CTBlipFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTNonVisualDrawingProps;
import org.openxmlformats.schemas.drawingml.x2006.main.CTOfficeArtExtension;
import org.openxmlformats.schemas.drawingml.x2006.main.CTOfficeArtExtensionList;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPresetGeometry2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTRelativeRect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.STShapeType;
import org.openxmlformats.schemas.presentationml.x2006.main.CTApplicationNonVisualDrawingProps;
import org.openxmlformats.schemas.presentationml.x2006.main.CTPicture;
import org.openxmlformats.schemas.presentationml.x2006.main.CTPictureNonVisual;

public class XSLFPictureShape
extends XSLFSimpleShape
implements PictureShape<XSLFShape, XSLFTextParagraph> {
    private static final POILogger LOG = POILogFactory.getLogger(XSLFPictureShape.class);
    private static final String MS_DML_NS = "http://schemas.microsoft.com/office/drawing/2010/main";
    private static final String MS_SVG_NS = "http://schemas.microsoft.com/office/drawing/2016/SVG/main";
    private static final String BITMAP_URI = "{28A0092B-C50C-407E-A947-70E740481C1C}";
    private static final String SVG_URI = "{96DAC541-7B7A-43D3-8B79-37D633B846F1}";
    private static final QName EMBED_TAG = new QName("http://schemas.openxmlformats.org/officeDocument/2006/relationships", "embed", "rel");
    private static final QName[] BLIP_FILL = new QName[]{new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "blipFill")};
    private XSLFPictureData _data;

    XSLFPictureShape(CTPicture shape, XSLFSheet sheet) {
        super(shape, sheet);
    }

    static CTPicture prototype(int shapeId, String rel) {
        CTPicture ct = CTPicture.Factory.newInstance();
        CTPictureNonVisual nvSpPr = ct.addNewNvPicPr();
        CTNonVisualDrawingProps cnv = nvSpPr.addNewCNvPr();
        cnv.setName("Picture " + shapeId);
        cnv.setId(shapeId);
        nvSpPr.addNewCNvPicPr().addNewPicLocks().setNoChangeAspect(true);
        nvSpPr.addNewNvPr();
        CTBlipFillProperties blipFill = ct.addNewBlipFill();
        CTBlip blip = blipFill.addNewBlip();
        blip.setEmbed(rel);
        blipFill.addNewStretch().addNewFillRect();
        CTShapeProperties spPr = ct.addNewSpPr();
        CTPresetGeometry2D prst = spPr.addNewPrstGeom();
        prst.setPrst(STShapeType.RECT);
        prst.addNewAvLst();
        return ct;
    }

    public boolean isExternalLinkedPicture() {
        return this.getBlipId() == null && this.getBlipLink() != null;
    }

    @Override
    public XSLFPictureData getPictureData() {
        if (this._data == null) {
            String blipId = this.getBlipId();
            if (blipId == null) {
                return null;
            }
            this._data = (XSLFPictureData)this.getSheet().getRelationById(blipId);
        }
        return this._data;
    }

    @Override
    public void setPlaceholder(Placeholder placeholder) {
        super.setPlaceholder(placeholder);
    }

    public URI getPictureLink() {
        if (this.getBlipId() != null) {
            return null;
        }
        String rId = this.getBlipLink();
        if (rId == null) {
            return null;
        }
        PackagePart p = this.getSheet().getPackagePart();
        PackageRelationship rel = p.getRelationship(rId);
        if (rel != null) {
            return rel.getTargetURI();
        }
        return null;
    }

    protected CTBlipFillProperties getBlipFill() {
        CTPicture ct = (CTPicture)this.getXmlObject();
        CTBlipFillProperties bfp = ct.getBlipFill();
        if (bfp != null) {
            return bfp;
        }
        try {
            return this.selectProperty(CTBlipFillProperties.class, XSLFPictureShape::parse, new QName[][]{BLIP_FILL});
        } catch (XmlException xe) {
            return null;
        }
    }

    private static CTBlipFillProperties parse(XMLStreamReader reader) throws XmlException {
        CTPicture pic = CTPicture.Factory.parse(reader);
        return pic != null ? pic.getBlipFill() : null;
    }

    protected CTBlip getBlip() {
        return this.getBlipFill().getBlip();
    }

    protected String getBlipLink() {
        CTBlip blip = this.getBlip();
        if (blip != null) {
            String link = blip.getLink();
            return link.isEmpty() ? null : link;
        }
        return null;
    }

    protected String getBlipId() {
        CTBlip blip = this.getBlip();
        if (blip != null) {
            String id = blip.getEmbed();
            return id.isEmpty() ? null : id;
        }
        return null;
    }

    @Override
    public Insets getClipping() {
        CTRelativeRect r = this.getBlipFill().getSrcRect();
        return r == null ? null : new Insets(r.getT(), r.getL(), r.getB(), r.getR());
    }

    public void setSvgImage(XSLFPictureData svgPic) {
        String svgRelId;
        int svgId;
        CTBlip blip = this.getBlip();
        CTOfficeArtExtensionList extLst = blip.isSetExtLst() ? blip.getExtLst() : blip.addNewExtLst();
        int bitmapId = this.getExt(extLst, BITMAP_URI);
        if (bitmapId == -1) {
            CTOfficeArtExtension extBitmap = extLst.addNewExt();
            extBitmap.setUri(BITMAP_URI);
            XmlCursor cur = extBitmap.newCursor();
            cur.toEndToken();
            cur.beginElement(new QName(MS_DML_NS, "useLocalDpi", "a14"));
            cur.insertNamespace("a14", MS_DML_NS);
            cur.insertAttributeWithValue("val", "0");
            cur.dispose();
        }
        if ((svgId = this.getExt(extLst, SVG_URI)) != -1) {
            extLst.removeExt(svgId);
        }
        if ((svgRelId = this.getSheet().getRelationId(svgPic)) == null) {
            svgRelId = this.getSheet().addRelation(null, XSLFRelation.IMAGE_SVG, svgPic).getRelationship().getId();
        }
        CTOfficeArtExtension svgBitmap = extLst.addNewExt();
        svgBitmap.setUri(SVG_URI);
        XmlCursor cur = svgBitmap.newCursor();
        cur.toEndToken();
        cur.beginElement(new QName(MS_SVG_NS, "svgBlip", "asvg"));
        cur.insertNamespace("asvg", MS_SVG_NS);
        cur.insertAttributeWithValue(EMBED_TAG, svgRelId);
        cur.dispose();
    }

    @Override
    public PictureData getAlternativePictureData() {
        return this.getSvgImage();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public XSLFPictureData getSvgImage() {
        CTBlip blip = this.getBlip();
        if (blip == null) {
            return null;
        }
        CTOfficeArtExtensionList extLst = blip.getExtLst();
        if (extLst == null) {
            return null;
        }
        int size = extLst.sizeOfExtArray();
        for (int i = 0; i < size; ++i) {
            XmlCursor cur = extLst.getExtArray(i).newCursor();
            try {
                if (!cur.toChild(MS_SVG_NS, "svgBlip")) continue;
                String svgRelId = cur.getAttributeText(EMBED_TAG);
                XSLFPictureData xSLFPictureData = svgRelId != null ? (XSLFPictureData)this.getSheet().getRelationById(svgRelId) : null;
                return xSLFPictureData;
            } finally {
                cur.dispose();
            }
        }
        return null;
    }

    public static XSLFPictureShape addSvgImage(XSLFSheet sheet, XSLFPictureData svgPic, PictureData.PictureType previewType, Rectangle2D anchor) throws IOException {
        PictureData.PictureType pt;
        SVGImageRenderer renderer = new SVGImageRenderer();
        try (InputStream is = svgPic.getInputStream();){
            renderer.loadImage(is, svgPic.getType().contentType);
        }
        Dimension2D dim = renderer.getDimension();
        Rectangle2D anc = anchor != null ? anchor : new Rectangle2D.Double(0.0, 0.0, Units.pixelToPoints((int)dim.getWidth()), Units.pixelToPoints((int)dim.getHeight()));
        PictureData.PictureType pictureType = pt = previewType != null ? previewType : PictureData.PictureType.PNG;
        if (pt != PictureData.PictureType.JPEG || pt != PictureData.PictureType.GIF || pt != PictureData.PictureType.PNG) {
            pt = PictureData.PictureType.PNG;
        }
        BufferedImage thmBI = renderer.getImage(dim);
        ByteArrayOutputStream bos = new ByteArrayOutputStream(100000);
        ImageIO.write((RenderedImage)thmBI, pt.extension.substring(1), bos);
        XSLFPictureData pngPic = sheet.getSlideShow().addPicture(new ByteArrayInputStream(bos.toByteArray()), pt);
        XSLFPictureShape shape = sheet.createPicture(pngPic);
        shape.setAnchor(anc);
        shape.setSvgImage(svgPic);
        return shape;
    }

    private int getExt(CTOfficeArtExtensionList extLst, String uri) {
        int size = extLst.sizeOfExtArray();
        for (int i = 0; i < size; ++i) {
            CTOfficeArtExtension ext = extLst.getExtArray(i);
            if (!uri.equals(ext.getUri())) continue;
            return i;
        }
        return -1;
    }

    @Override
    void copy(XSLFShape sh) {
        super.copy(sh);
        XSLFPictureShape p = (XSLFPictureShape)sh;
        String blipId = p.getBlipId();
        if (blipId == null) {
            LOG.log(5, "unable to copy invalid picture shape");
            return;
        }
        String relId = this.getSheet().importBlip(blipId, p.getSheet());
        CTPicture ct = (CTPicture)this.getXmlObject();
        CTBlip blip = this.getBlipFill().getBlip();
        blip.setEmbed(relId);
        CTApplicationNonVisualDrawingProps nvPr = ct.getNvPicPr().getNvPr();
        if (nvPr.isSetCustDataLst()) {
            nvPr.unsetCustDataLst();
        }
        if (blip.isSetExtLst()) {
            CTOfficeArtExtensionList extLst = blip.getExtLst();
            for (CTOfficeArtExtension ext : extLst.getExtArray()) {
                String xpath = "declare namespace a14='http://schemas.microsoft.com/office/drawing/2010/main' $this//a14:imgProps/a14:imgLayer";
                XmlObject[] obj = ext.selectPath(xpath);
                if (obj == null || obj.length != 1) continue;
                XmlCursor c = obj[0].newCursor();
                String id = c.getAttributeText(EMBED_TAG);
                String newId = this.getSheet().importBlip(id, p.getSheet());
                c.setAttributeText(EMBED_TAG, newId);
                c.dispose();
            }
        }
    }
}

