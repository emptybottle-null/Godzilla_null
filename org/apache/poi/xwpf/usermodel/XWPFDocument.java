/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xwpf.usermodel;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.xml.namespace.QName;
import org.apache.poi.ooxml.POIXMLDocument;
import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.ooxml.POIXMLException;
import org.apache.poi.ooxml.POIXMLProperties;
import org.apache.poi.ooxml.POIXMLRelation;
import org.apache.poi.ooxml.POIXMLTypeLoader;
import org.apache.poi.ooxml.util.IdentifierManager;
import org.apache.poi.ooxml.util.PackageHelper;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.openxml4j.opc.PackagePartName;
import org.apache.poi.openxml4j.opc.PackageRelationship;
import org.apache.poi.openxml4j.opc.PackagingURIHelper;
import org.apache.poi.openxml4j.opc.TargetMode;
import org.apache.poi.poifs.crypt.HashAlgorithm;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.Internal;
import org.apache.poi.util.POILogFactory;
import org.apache.poi.util.POILogger;
import org.apache.poi.wp.usermodel.HeaderFooterType;
import org.apache.poi.xwpf.model.XWPFHeaderFooterPolicy;
import org.apache.poi.xwpf.usermodel.BodyElementType;
import org.apache.poi.xwpf.usermodel.BodyType;
import org.apache.poi.xwpf.usermodel.Document;
import org.apache.poi.xwpf.usermodel.FootnoteEndnoteIdManager;
import org.apache.poi.xwpf.usermodel.IBody;
import org.apache.poi.xwpf.usermodel.IBodyElement;
import org.apache.poi.xwpf.usermodel.TOC;
import org.apache.poi.xwpf.usermodel.XWPFChart;
import org.apache.poi.xwpf.usermodel.XWPFComment;
import org.apache.poi.xwpf.usermodel.XWPFEndnote;
import org.apache.poi.xwpf.usermodel.XWPFEndnotes;
import org.apache.poi.xwpf.usermodel.XWPFFactory;
import org.apache.poi.xwpf.usermodel.XWPFFooter;
import org.apache.poi.xwpf.usermodel.XWPFFootnote;
import org.apache.poi.xwpf.usermodel.XWPFFootnotes;
import org.apache.poi.xwpf.usermodel.XWPFHeader;
import org.apache.poi.xwpf.usermodel.XWPFHyperlink;
import org.apache.poi.xwpf.usermodel.XWPFNumbering;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFPictureData;
import org.apache.poi.xwpf.usermodel.XWPFRelation;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFSDT;
import org.apache.poi.xwpf.usermodel.XWPFSettings;
import org.apache.poi.xwpf.usermodel.XWPFStyles;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBody;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTComment;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocument1;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFtnEdn;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTOnOff;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTP;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRow;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtBlock;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyles;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTc;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CommentsDocument;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.DocumentDocument;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.EndnotesDocument;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.FootnotesDocument;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.NumberingDocument;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STDocProtect;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STHdrFtr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STOnOff;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.StylesDocument;

public class XWPFDocument
extends POIXMLDocument
implements Document,
IBody {
    private static final POILogger LOG = POILogFactory.getLogger(XWPFDocument.class);
    protected List<XWPFFooter> footers = new ArrayList<XWPFFooter>();
    protected List<XWPFHeader> headers = new ArrayList<XWPFHeader>();
    protected List<XWPFComment> comments = new ArrayList<XWPFComment>();
    protected List<XWPFHyperlink> hyperlinks = new ArrayList<XWPFHyperlink>();
    protected List<XWPFParagraph> paragraphs = new ArrayList<XWPFParagraph>();
    protected List<XWPFTable> tables = new ArrayList<XWPFTable>();
    protected List<XWPFSDT> contentControls = new ArrayList<XWPFSDT>();
    protected List<IBodyElement> bodyElements = new ArrayList<IBodyElement>();
    protected List<XWPFPictureData> pictures = new ArrayList<XWPFPictureData>();
    protected Map<Long, List<XWPFPictureData>> packagePictures = new HashMap<Long, List<XWPFPictureData>>();
    protected XWPFEndnotes endnotes;
    protected XWPFNumbering numbering;
    protected XWPFStyles styles;
    protected XWPFFootnotes footnotes;
    private CTDocument1 ctDocument;
    private XWPFSettings settings;
    protected final List<XWPFChart> charts = new ArrayList<XWPFChart>();
    private IdentifierManager drawingIdManager = new IdentifierManager(0L, 0xFFFFFFFFL);
    private FootnoteEndnoteIdManager footnoteIdManager = new FootnoteEndnoteIdManager(this);
    private XWPFHeaderFooterPolicy headerFooterPolicy;

    public XWPFDocument(OPCPackage pkg) throws IOException {
        super(pkg);
        this.load(XWPFFactory.getInstance());
    }

    public XWPFDocument(InputStream is) throws IOException {
        super(PackageHelper.open(is));
        this.load(XWPFFactory.getInstance());
    }

    public XWPFDocument() {
        super(XWPFDocument.newPackage());
        this.onDocumentCreate();
    }

    protected static OPCPackage newPackage() {
        OPCPackage pkg = null;
        try {
            pkg = OPCPackage.create(new ByteArrayOutputStream());
            PackagePartName corePartName = PackagingURIHelper.createPartName(XWPFRelation.DOCUMENT.getDefaultFileName());
            pkg.addRelationship(corePartName, TargetMode.INTERNAL, "http://schemas.openxmlformats.org/officeDocument/2006/relationships/officeDocument");
            pkg.createPart(corePartName, XWPFRelation.DOCUMENT.getContentType());
            pkg.getPackageProperties().setCreatorProperty("Apache POI");
            return pkg;
        } catch (Exception e) {
            IOUtils.closeQuietly(pkg);
            throw new POIXMLException(e);
        }
    }

    @Override
    protected void onDocumentRead() throws IOException {
        try {
            DocumentDocument doc = DocumentDocument.Factory.parse(this.getPackagePart().getInputStream(), POIXMLTypeLoader.DEFAULT_XML_OPTIONS);
            this.ctDocument = doc.getDocument();
            this.initFootnotes();
            XmlCursor docCursor = this.ctDocument.newCursor();
            docCursor.selectPath("./*");
            while (docCursor.toNextSelection()) {
                XmlObject o = docCursor.getObject();
                if (!(o instanceof CTBody)) continue;
                XmlCursor bodyCursor = o.newCursor();
                bodyCursor.selectPath("./*");
                while (bodyCursor.toNextSelection()) {
                    XmlObject bodyObj = bodyCursor.getObject();
                    if (bodyObj instanceof CTP) {
                        XWPFParagraph p = new XWPFParagraph((CTP)bodyObj, this);
                        this.bodyElements.add(p);
                        this.paragraphs.add(p);
                        continue;
                    }
                    if (bodyObj instanceof CTTbl) {
                        XWPFTable t = new XWPFTable((CTTbl)bodyObj, this);
                        this.bodyElements.add(t);
                        this.tables.add(t);
                        continue;
                    }
                    if (!(bodyObj instanceof CTSdtBlock)) continue;
                    XWPFSDT c = new XWPFSDT((CTSdtBlock)bodyObj, (IBody)this);
                    this.bodyElements.add(c);
                    this.contentControls.add(c);
                }
                bodyCursor.dispose();
            }
            docCursor.dispose();
            if (doc.getDocument().getBody().getSectPr() != null) {
                this.headerFooterPolicy = new XWPFHeaderFooterPolicy(this);
            }
            for (POIXMLDocumentPart.RelationPart rp : this.getRelationParts()) {
                Object p = rp.getDocumentPart();
                String relation = rp.getRelationship().getRelationshipType();
                if (relation.equals(XWPFRelation.STYLES.getRelation())) {
                    this.styles = (XWPFStyles)p;
                    this.styles.onDocumentRead();
                    continue;
                }
                if (relation.equals(XWPFRelation.NUMBERING.getRelation())) {
                    this.numbering = (XWPFNumbering)p;
                    this.numbering.onDocumentRead();
                    continue;
                }
                if (relation.equals(XWPFRelation.FOOTER.getRelation())) {
                    XWPFFooter footer = (XWPFFooter)p;
                    this.footers.add(footer);
                    footer.onDocumentRead();
                    continue;
                }
                if (relation.equals(XWPFRelation.HEADER.getRelation())) {
                    XWPFHeader header = (XWPFHeader)p;
                    this.headers.add(header);
                    header.onDocumentRead();
                    continue;
                }
                if (relation.equals(XWPFRelation.COMMENT.getRelation())) {
                    CommentsDocument cmntdoc = CommentsDocument.Factory.parse(((POIXMLDocumentPart)p).getPackagePart().getInputStream(), POIXMLTypeLoader.DEFAULT_XML_OPTIONS);
                    for (CTComment ctcomment : cmntdoc.getComments().getCommentArray()) {
                        this.comments.add(new XWPFComment(ctcomment, this));
                    }
                    continue;
                }
                if (relation.equals(XWPFRelation.SETTINGS.getRelation())) {
                    this.settings = (XWPFSettings)p;
                    this.settings.onDocumentRead();
                    continue;
                }
                if (relation.equals(XWPFRelation.IMAGES.getRelation())) {
                    XWPFPictureData picData = (XWPFPictureData)p;
                    picData.onDocumentRead();
                    this.registerPackagePictureData(picData);
                    this.pictures.add(picData);
                    continue;
                }
                if (relation.equals(XWPFRelation.CHART.getRelation())) {
                    XWPFChart chartData = (XWPFChart)p;
                    this.charts.add(chartData);
                    continue;
                }
                if (!relation.equals(XWPFRelation.GLOSSARY_DOCUMENT.getRelation())) continue;
                for (POIXMLDocumentPart gp : ((POIXMLDocumentPart)p).getRelations()) {
                    POIXMLDocumentPart._invokeOnDocumentRead(gp);
                }
            }
            this.initHyperlinks();
        } catch (XmlException e) {
            throw new POIXMLException(e);
        }
    }

    private void initHyperlinks() {
        try {
            this.hyperlinks = new ArrayList<XWPFHyperlink>();
            for (PackageRelationship rel : this.getPackagePart().getRelationshipsByType(XWPFRelation.HYPERLINK.getRelation())) {
                this.hyperlinks.add(new XWPFHyperlink(rel.getId(), rel.getTargetURI().toString()));
            }
        } catch (InvalidFormatException e) {
            throw new POIXMLException(e);
        }
    }

    private void initFootnotes() throws XmlException, IOException {
        for (POIXMLDocumentPart.RelationPart rp : this.getRelationParts()) {
            Object p = rp.getDocumentPart();
            String relation = rp.getRelationship().getRelationshipType();
            if (relation.equals(XWPFRelation.FOOTNOTE.getRelation())) {
                this.footnotes = (XWPFFootnotes)p;
                this.footnotes.onDocumentRead();
                this.footnotes.setIdManager(this.footnoteIdManager);
                continue;
            }
            if (!relation.equals(XWPFRelation.ENDNOTE.getRelation())) continue;
            this.endnotes = (XWPFEndnotes)p;
            this.endnotes.onDocumentRead();
            this.endnotes.setIdManager(this.footnoteIdManager);
        }
    }

    @Override
    protected void onDocumentCreate() {
        this.ctDocument = CTDocument1.Factory.newInstance();
        this.ctDocument.addNewBody();
        this.settings = (XWPFSettings)this.createRelationship(XWPFRelation.SETTINGS, XWPFFactory.getInstance());
        POIXMLProperties.ExtendedProperties expProps = this.getProperties().getExtendedProperties();
        expProps.getUnderlyingProperties().setApplication("Apache POI");
    }

    @Internal
    public CTDocument1 getDocument() {
        return this.ctDocument;
    }

    IdentifierManager getDrawingIdManager() {
        return this.drawingIdManager;
    }

    @Override
    public List<IBodyElement> getBodyElements() {
        return Collections.unmodifiableList(this.bodyElements);
    }

    public Iterator<IBodyElement> getBodyElementsIterator() {
        return this.bodyElements.iterator();
    }

    @Override
    public List<XWPFParagraph> getParagraphs() {
        return Collections.unmodifiableList(this.paragraphs);
    }

    @Override
    public List<XWPFTable> getTables() {
        return Collections.unmodifiableList(this.tables);
    }

    public List<XWPFChart> getCharts() {
        return Collections.unmodifiableList(this.charts);
    }

    @Override
    public XWPFTable getTableArray(int pos) {
        if (pos >= 0 && pos < this.tables.size()) {
            return this.tables.get(pos);
        }
        return null;
    }

    public List<XWPFFooter> getFooterList() {
        return Collections.unmodifiableList(this.footers);
    }

    public XWPFFooter getFooterArray(int pos) {
        if (pos >= 0 && pos < this.footers.size()) {
            return this.footers.get(pos);
        }
        return null;
    }

    public List<XWPFHeader> getHeaderList() {
        return Collections.unmodifiableList(this.headers);
    }

    public XWPFHeader getHeaderArray(int pos) {
        if (pos >= 0 && pos < this.headers.size()) {
            return this.headers.get(pos);
        }
        return null;
    }

    public String getTblStyle(XWPFTable table) {
        return table.getStyleID();
    }

    public XWPFHyperlink getHyperlinkByID(String id) {
        for (XWPFHyperlink link : this.hyperlinks) {
            if (!link.getId().equals(id)) continue;
            return link;
        }
        this.initHyperlinks();
        for (XWPFHyperlink link : this.hyperlinks) {
            if (!link.getId().equals(id)) continue;
            return link;
        }
        return null;
    }

    public XWPFFootnote getFootnoteByID(int id) {
        if (this.footnotes == null) {
            return null;
        }
        return (XWPFFootnote)this.footnotes.getFootnoteById(id);
    }

    public XWPFEndnote getEndnoteByID(int id) {
        if (this.endnotes == null) {
            return null;
        }
        return this.endnotes.getFootnoteById(id);
    }

    public List<XWPFFootnote> getFootnotes() {
        if (this.footnotes == null) {
            return Collections.emptyList();
        }
        return this.footnotes.getFootnotesList();
    }

    public XWPFHyperlink[] getHyperlinks() {
        return this.hyperlinks.toArray(new XWPFHyperlink[0]);
    }

    public XWPFComment getCommentByID(String id) {
        for (XWPFComment comment : this.comments) {
            if (!comment.getId().equals(id)) continue;
            return comment;
        }
        return null;
    }

    public XWPFComment[] getComments() {
        return this.comments.toArray(new XWPFComment[0]);
    }

    public PackagePart getPartById(String id) {
        try {
            PackagePart corePart = this.getCorePart();
            return corePart.getRelatedPart(corePart.getRelationship(id));
        } catch (InvalidFormatException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public XWPFHeaderFooterPolicy getHeaderFooterPolicy() {
        return this.headerFooterPolicy;
    }

    public XWPFHeaderFooterPolicy createHeaderFooterPolicy() {
        if (this.headerFooterPolicy == null) {
            this.headerFooterPolicy = new XWPFHeaderFooterPolicy(this);
        }
        return this.headerFooterPolicy;
    }

    public XWPFHeader createHeader(HeaderFooterType type) {
        CTSectPr ctSectPr;
        XWPFHeaderFooterPolicy hfPolicy = this.createHeaderFooterPolicy();
        if (type == HeaderFooterType.FIRST && !(ctSectPr = this.getSection()).isSetTitlePg()) {
            CTOnOff titlePg = ctSectPr.addNewTitlePg();
            titlePg.setVal(STOnOff.ON);
        }
        return hfPolicy.createHeader(STHdrFtr.Enum.forInt(type.toInt()));
    }

    public XWPFFooter createFooter(HeaderFooterType type) {
        CTSectPr ctSectPr;
        XWPFHeaderFooterPolicy hfPolicy = this.createHeaderFooterPolicy();
        if (type == HeaderFooterType.FIRST && !(ctSectPr = this.getSection()).isSetTitlePg()) {
            CTOnOff titlePg = ctSectPr.addNewTitlePg();
            titlePg.setVal(STOnOff.ON);
        }
        return hfPolicy.createFooter(STHdrFtr.Enum.forInt(type.toInt()));
    }

    private CTSectPr getSection() {
        CTBody ctBody = this.getDocument().getBody();
        return ctBody.isSetSectPr() ? ctBody.getSectPr() : ctBody.addNewSectPr();
    }

    @Internal
    public CTStyles getStyle() throws XmlException, IOException {
        PackagePart[] parts;
        try {
            parts = this.getRelatedByType(XWPFRelation.STYLES.getRelation());
        } catch (InvalidFormatException e) {
            throw new IllegalStateException(e);
        }
        if (parts.length != 1) {
            throw new IllegalStateException("Expecting one Styles document part, but found " + parts.length);
        }
        StylesDocument sd = StylesDocument.Factory.parse(parts[0].getInputStream(), POIXMLTypeLoader.DEFAULT_XML_OPTIONS);
        return sd.getStyles();
    }

    @Override
    public List<PackagePart> getAllEmbeddedParts() throws OpenXML4JException {
        LinkedList<PackagePart> embedds = new LinkedList<PackagePart>();
        PackagePart part = this.getPackagePart();
        for (PackageRelationship rel : this.getPackagePart().getRelationshipsByType("http://schemas.openxmlformats.org/officeDocument/2006/relationships/oleObject")) {
            embedds.add(part.getRelatedPart(rel));
        }
        for (PackageRelationship rel : this.getPackagePart().getRelationshipsByType("http://schemas.openxmlformats.org/officeDocument/2006/relationships/package")) {
            embedds.add(part.getRelatedPart(rel));
        }
        return embedds;
    }

    private int getBodyElementSpecificPos(int pos, List<? extends IBodyElement> list) {
        if (list.size() == 0) {
            return -1;
        }
        if (pos >= 0 && pos < this.bodyElements.size()) {
            int startPos;
            IBodyElement needle = this.bodyElements.get(pos);
            if (needle.getElementType() != list.get(0).getElementType()) {
                return -1;
            }
            for (int i = startPos = Math.min(pos, list.size() - 1); i >= 0; --i) {
                if (list.get(i) != needle) continue;
                return i;
            }
        }
        return -1;
    }

    public int getParagraphPos(int pos) {
        return this.getBodyElementSpecificPos(pos, this.paragraphs);
    }

    public int getTablePos(int pos) {
        return this.getBodyElementSpecificPos(pos, this.tables);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XWPFParagraph insertNewParagraph(XmlCursor cursor) {
        if (this.isCursorInBody(cursor)) {
            String uri = CTP.type.getName().getNamespaceURI();
            String localPart = "p";
            cursor.beginElement(localPart, uri);
            cursor.toParent();
            CTP p = (CTP)cursor.getObject();
            XWPFParagraph newP = new XWPFParagraph(p, this);
            XmlObject o = null;
            while (!(o instanceof CTP) && cursor.toPrevSibling()) {
                o = cursor.getObject();
            }
            if (!(o instanceof CTP) || o == p) {
                this.paragraphs.add(0, newP);
            } else {
                int pos = this.paragraphs.indexOf(this.getParagraph((CTP)o)) + 1;
                this.paragraphs.add(pos, newP);
            }
            XmlCursor newParaPos = p.newCursor();
            try {
                int i = 0;
                cursor.toCursor(newParaPos);
                while (cursor.toPrevSibling()) {
                    o = cursor.getObject();
                    if (!(o instanceof CTP) && !(o instanceof CTTbl)) continue;
                    ++i;
                }
                this.bodyElements.add(i, newP);
                cursor.toCursor(newParaPos);
                cursor.toEndToken();
                XWPFParagraph xWPFParagraph = newP;
                return xWPFParagraph;
            } finally {
                newParaPos.dispose();
            }
        }
        return null;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XWPFTable insertNewTbl(XmlCursor cursor) {
        if (this.isCursorInBody(cursor)) {
            String uri = CTTbl.type.getName().getNamespaceURI();
            String localPart = "tbl";
            cursor.beginElement(localPart, uri);
            cursor.toParent();
            CTTbl t = (CTTbl)cursor.getObject();
            XWPFTable newT = new XWPFTable(t, this);
            XmlObject o = null;
            while (!(o instanceof CTTbl) && cursor.toPrevSibling()) {
                o = cursor.getObject();
            }
            if (!(o instanceof CTTbl)) {
                this.tables.add(0, newT);
            } else {
                int pos = this.tables.indexOf(this.getTable((CTTbl)o)) + 1;
                this.tables.add(pos, newT);
            }
            int i = 0;
            XmlCursor tableCursor = t.newCursor();
            try {
                cursor.toCursor(tableCursor);
                while (cursor.toPrevSibling()) {
                    o = cursor.getObject();
                    if (!(o instanceof CTP) && !(o instanceof CTTbl)) continue;
                    ++i;
                }
                this.bodyElements.add(i, newT);
                cursor.toCursor(tableCursor);
                cursor.toEndToken();
                XWPFTable xWPFTable = newT;
                return xWPFTable;
            } finally {
                tableCursor.dispose();
            }
        }
        return null;
    }

    private boolean isCursorInBody(XmlCursor cursor) {
        XmlCursor verify = cursor.newCursor();
        verify.toParent();
        boolean result = verify.getObject() == this.ctDocument.getBody();
        verify.dispose();
        return result;
    }

    private int getPosOfBodyElement(IBodyElement needle) {
        BodyElementType type = needle.getElementType();
        for (int i = 0; i < this.bodyElements.size(); ++i) {
            IBodyElement current = this.bodyElements.get(i);
            if (current.getElementType() != type || !current.equals(needle)) continue;
            return i;
        }
        return -1;
    }

    public int getPosOfParagraph(XWPFParagraph p) {
        return this.getPosOfBodyElement(p);
    }

    public int getPosOfTable(XWPFTable t) {
        return this.getPosOfBodyElement(t);
    }

    @Override
    protected void commit() throws IOException {
        XmlOptions xmlOptions = new XmlOptions(POIXMLTypeLoader.DEFAULT_XML_OPTIONS);
        xmlOptions.setSaveSyntheticDocumentElement(new QName(CTDocument1.type.getName().getNamespaceURI(), "document"));
        PackagePart part = this.getPackagePart();
        OutputStream out = part.getOutputStream();
        this.ctDocument.save(out, xmlOptions);
        out.close();
    }

    private int getRelationIndex(XWPFRelation relation) {
        int i = 1;
        for (POIXMLDocumentPart.RelationPart rp : this.getRelationParts()) {
            if (!rp.getRelationship().getRelationshipType().equals(relation.getRelation())) continue;
            ++i;
        }
        return i;
    }

    public XWPFParagraph createParagraph() {
        XWPFParagraph p = new XWPFParagraph(this.ctDocument.getBody().addNewP(), this);
        this.bodyElements.add(p);
        this.paragraphs.add(p);
        return p;
    }

    public XWPFNumbering createNumbering() {
        if (this.numbering == null) {
            NumberingDocument numberingDoc = NumberingDocument.Factory.newInstance();
            XWPFRelation relation = XWPFRelation.NUMBERING;
            int i = this.getRelationIndex(relation);
            XWPFNumbering wrapper = (XWPFNumbering)this.createRelationship(relation, XWPFFactory.getInstance(), i);
            wrapper.setNumbering(numberingDoc.addNewNumbering());
            this.numbering = wrapper;
        }
        return this.numbering;
    }

    public XWPFStyles createStyles() {
        if (this.styles == null) {
            StylesDocument stylesDoc = StylesDocument.Factory.newInstance();
            XWPFRelation relation = XWPFRelation.STYLES;
            int i = this.getRelationIndex(relation);
            XWPFStyles wrapper = (XWPFStyles)this.createRelationship(relation, XWPFFactory.getInstance(), i);
            wrapper.setStyles(stylesDoc.addNewStyles());
            this.styles = wrapper;
        }
        return this.styles;
    }

    public XWPFFootnotes createFootnotes() {
        if (this.footnotes == null) {
            FootnotesDocument footnotesDoc = FootnotesDocument.Factory.newInstance();
            XWPFRelation relation = XWPFRelation.FOOTNOTE;
            int i = this.getRelationIndex(relation);
            XWPFFootnotes wrapper = (XWPFFootnotes)this.createRelationship(relation, XWPFFactory.getInstance(), i);
            wrapper.setFootnotes(footnotesDoc.addNewFootnotes());
            wrapper.setIdManager(this.footnoteIdManager);
            this.footnotes = wrapper;
        }
        return this.footnotes;
    }

    @Internal
    public XWPFFootnote addFootnote(CTFtnEdn note) {
        return this.footnotes.addFootnote(note);
    }

    @Internal
    public XWPFEndnote addEndnote(CTFtnEdn note) {
        XWPFEndnote endnote = new XWPFEndnote(this, note);
        this.endnotes.addEndnote(note);
        return endnote;
    }

    public boolean removeBodyElement(int pos) {
        if (pos >= 0 && pos < this.bodyElements.size()) {
            BodyElementType type = this.bodyElements.get(pos).getElementType();
            if (type == BodyElementType.TABLE) {
                int tablePos = this.getTablePos(pos);
                this.tables.remove(tablePos);
                this.ctDocument.getBody().removeTbl(tablePos);
            }
            if (type == BodyElementType.PARAGRAPH) {
                int paraPos = this.getParagraphPos(pos);
                this.paragraphs.remove(paraPos);
                this.ctDocument.getBody().removeP(paraPos);
            }
            this.bodyElements.remove(pos);
            return true;
        }
        return false;
    }

    public void setParagraph(XWPFParagraph paragraph, int pos) {
        this.paragraphs.set(pos, paragraph);
        this.ctDocument.getBody().setPArray(pos, paragraph.getCTP());
    }

    public XWPFParagraph getLastParagraph() {
        int lastPos = this.paragraphs.toArray().length - 1;
        return this.paragraphs.get(lastPos);
    }

    public XWPFTable createTable() {
        XWPFTable table = new XWPFTable(this.ctDocument.getBody().addNewTbl(), this);
        this.bodyElements.add(table);
        this.tables.add(table);
        return table;
    }

    public XWPFTable createTable(int rows, int cols) {
        XWPFTable table = new XWPFTable(this.ctDocument.getBody().addNewTbl(), this, rows, cols);
        this.bodyElements.add(table);
        this.tables.add(table);
        return table;
    }

    public void createTOC() {
        CTSdtBlock block = this.getDocument().getBody().addNewSdt();
        TOC toc = new TOC(block);
        for (XWPFParagraph par : this.paragraphs) {
            String parStyle = par.getStyle();
            if (parStyle == null || !parStyle.startsWith("Heading")) continue;
            try {
                int level = Integer.parseInt(parStyle.substring("Heading".length()));
                toc.addRow(level, par.getText(), 1, "112723803");
            } catch (NumberFormatException e) {
                LOG.log(7, "can't format number in TOC heading", e);
            }
        }
    }

    public void setTable(int pos, XWPFTable table) {
        this.tables.set(pos, table);
        this.ctDocument.getBody().setTblArray(pos, table.getCTTbl());
    }

    public boolean isEnforcedProtection() {
        return this.settings.isEnforcedWith();
    }

    public boolean isEnforcedReadonlyProtection() {
        return this.settings.isEnforcedWith(STDocProtect.READ_ONLY);
    }

    public boolean isEnforcedFillingFormsProtection() {
        return this.settings.isEnforcedWith(STDocProtect.FORMS);
    }

    public boolean isEnforcedCommentsProtection() {
        return this.settings.isEnforcedWith(STDocProtect.COMMENTS);
    }

    public boolean isEnforcedTrackedChangesProtection() {
        return this.settings.isEnforcedWith(STDocProtect.TRACKED_CHANGES);
    }

    public boolean isEnforcedUpdateFields() {
        return this.settings.isUpdateFields();
    }

    public void enforceReadonlyProtection() {
        this.settings.setEnforcementEditValue(STDocProtect.READ_ONLY);
    }

    public void enforceReadonlyProtection(String password, HashAlgorithm hashAlgo) {
        this.settings.setEnforcementEditValue(STDocProtect.READ_ONLY, password, hashAlgo);
    }

    public void enforceFillingFormsProtection() {
        this.settings.setEnforcementEditValue(STDocProtect.FORMS);
    }

    public void enforceFillingFormsProtection(String password, HashAlgorithm hashAlgo) {
        this.settings.setEnforcementEditValue(STDocProtect.FORMS, password, hashAlgo);
    }

    public void enforceCommentsProtection() {
        this.settings.setEnforcementEditValue(STDocProtect.COMMENTS);
    }

    public void enforceCommentsProtection(String password, HashAlgorithm hashAlgo) {
        this.settings.setEnforcementEditValue(STDocProtect.COMMENTS, password, hashAlgo);
    }

    public void enforceTrackedChangesProtection() {
        this.settings.setEnforcementEditValue(STDocProtect.TRACKED_CHANGES);
    }

    public void enforceTrackedChangesProtection(String password, HashAlgorithm hashAlgo) {
        this.settings.setEnforcementEditValue(STDocProtect.TRACKED_CHANGES, password, hashAlgo);
    }

    public boolean validateProtectionPassword(String password) {
        return this.settings.validateProtectionPassword(password);
    }

    public void removeProtectionEnforcement() {
        this.settings.removeEnforcement();
    }

    public void enforceUpdateFields() {
        this.settings.setUpdateFields();
    }

    public boolean isTrackRevisions() {
        return this.settings.isTrackRevisions();
    }

    public void setTrackRevisions(boolean enable) {
        this.settings.setTrackRevisions(enable);
    }

    public long getZoomPercent() {
        return this.settings.getZoomPercent();
    }

    public void setZoomPercent(long zoomPercent) {
        this.settings.setZoomPercent(zoomPercent);
    }

    public boolean getEvenAndOddHeadings() {
        return this.settings.getEvenAndOddHeadings();
    }

    public void setEvenAndOddHeadings(boolean enable) {
        this.settings.setEvenAndOddHeadings(enable);
    }

    public boolean getMirrorMargins() {
        return this.settings.getMirrorMargins();
    }

    public void setMirrorMargins(boolean enable) {
        this.settings.setMirrorMargins(enable);
    }

    @Override
    public void insertTable(int pos, XWPFTable table) {
        this.bodyElements.add(pos, table);
        int i = 0;
        for (CTTbl tbl : this.ctDocument.getBody().getTblArray()) {
            if (tbl == table.getCTTbl()) break;
            ++i;
        }
        this.tables.add(i, table);
    }

    public List<XWPFPictureData> getAllPictures() {
        return Collections.unmodifiableList(this.pictures);
    }

    public List<XWPFPictureData> getAllPackagePictures() {
        ArrayList<XWPFPictureData> result = new ArrayList<XWPFPictureData>();
        Collection<List<XWPFPictureData>> values = this.packagePictures.values();
        for (List<XWPFPictureData> list : values) {
            result.addAll(list);
        }
        return Collections.unmodifiableList(result);
    }

    void registerPackagePictureData(XWPFPictureData picData) {
        List list = this.packagePictures.computeIfAbsent(picData.getChecksum(), k -> new ArrayList(1));
        if (!list.contains(picData)) {
            list.add(picData);
        }
    }

    XWPFPictureData findPackagePictureData(byte[] pictureData, int format) {
        long checksum = IOUtils.calculateChecksum(pictureData);
        XWPFPictureData xwpfPicData = null;
        List<XWPFPictureData> xwpfPicDataList = this.packagePictures.get(checksum);
        if (xwpfPicDataList != null) {
            Iterator<XWPFPictureData> iter = xwpfPicDataList.iterator();
            while (iter.hasNext() && xwpfPicData == null) {
                XWPFPictureData curElem = iter.next();
                if (!Arrays.equals(pictureData, curElem.getData())) continue;
                xwpfPicData = curElem;
            }
        }
        return xwpfPicData;
    }

    public String addPictureData(byte[] pictureData, int format) throws InvalidFormatException {
        XWPFPictureData xwpfPicData = this.findPackagePictureData(pictureData, format);
        POIXMLRelation relDesc = XWPFPictureData.RELATIONS[format];
        if (xwpfPicData == null) {
            int idx = this.getNextPicNameNumber(format);
            xwpfPicData = (XWPFPictureData)this.createRelationship(relDesc, XWPFFactory.getInstance(), idx);
            PackagePart picDataPart = xwpfPicData.getPackagePart();
            try (OutputStream out = picDataPart.getOutputStream();){
                out.write(pictureData);
            } catch (IOException e) {
                throw new POIXMLException(e);
            }
            this.registerPackagePictureData(xwpfPicData);
            this.pictures.add(xwpfPicData);
            return this.getRelationId(xwpfPicData);
        }
        if (!this.getRelations().contains(xwpfPicData)) {
            POIXMLDocumentPart.RelationPart rp = this.addRelation(null, XWPFRelation.IMAGES, xwpfPicData);
            return rp.getRelationship().getId();
        }
        return this.getRelationId(xwpfPicData);
    }

    public String addPictureData(InputStream is, int format) throws InvalidFormatException {
        try {
            byte[] data = IOUtils.toByteArray(is);
            return this.addPictureData(data, format);
        } catch (IOException e) {
            throw new POIXMLException(e);
        }
    }

    public int getNextPicNameNumber(int format) throws InvalidFormatException {
        int img = this.getAllPackagePictures().size() + 1;
        String proposal = XWPFPictureData.RELATIONS[format].getFileName(img);
        PackagePartName createPartName = PackagingURIHelper.createPartName(proposal);
        while (this.getPackage().getPart(createPartName) != null) {
            proposal = XWPFPictureData.RELATIONS[format].getFileName(++img);
            createPartName = PackagingURIHelper.createPartName(proposal);
        }
        return img;
    }

    public XWPFPictureData getPictureDataByID(String blipID) {
        POIXMLDocumentPart relatedPart = this.getRelationById(blipID);
        if (relatedPart instanceof XWPFPictureData) {
            return (XWPFPictureData)relatedPart;
        }
        return null;
    }

    public XWPFNumbering getNumbering() {
        return this.numbering;
    }

    public XWPFStyles getStyles() {
        return this.styles;
    }

    @Override
    public XWPFParagraph getParagraph(CTP p) {
        for (int i = 0; i < this.getParagraphs().size(); ++i) {
            if (this.getParagraphs().get(i).getCTP() != p) continue;
            return this.getParagraphs().get(i);
        }
        return null;
    }

    @Override
    public XWPFTable getTable(CTTbl ctTbl) {
        for (int i = 0; i < this.tables.size(); ++i) {
            if (this.getTables().get(i).getCTTbl() != ctTbl) continue;
            return this.getTables().get(i);
        }
        return null;
    }

    public Iterator<XWPFTable> getTablesIterator() {
        return this.tables.iterator();
    }

    public Iterator<XWPFParagraph> getParagraphsIterator() {
        return this.paragraphs.iterator();
    }

    @Override
    public XWPFParagraph getParagraphArray(int pos) {
        if (pos >= 0 && pos < this.paragraphs.size()) {
            return this.paragraphs.get(pos);
        }
        return null;
    }

    @Override
    public POIXMLDocumentPart getPart() {
        return this;
    }

    @Override
    public BodyType getPartType() {
        return BodyType.DOCUMENT;
    }

    @Override
    public XWPFTableCell getTableCell(CTTc cell) {
        XmlCursor cursor = cell.newCursor();
        cursor.toParent();
        XmlObject o = cursor.getObject();
        if (!(o instanceof CTRow)) {
            return null;
        }
        CTRow row = (CTRow)o;
        cursor.toParent();
        o = cursor.getObject();
        cursor.dispose();
        if (!(o instanceof CTTbl)) {
            return null;
        }
        CTTbl tbl = (CTTbl)o;
        XWPFTable table = this.getTable(tbl);
        if (table == null) {
            return null;
        }
        XWPFTableRow tableRow = table.getRow(row);
        if (tableRow == null) {
            return null;
        }
        return tableRow.getTableCell(cell);
    }

    @Override
    public XWPFDocument getXWPFDocument() {
        return this;
    }

    public XWPFChart createChart() throws InvalidFormatException, IOException {
        return this.createChart(500000, 500000);
    }

    public XWPFChart createChart(int width, int height) throws InvalidFormatException, IOException {
        return this.createChart(this.createParagraph().createRun(), width, height);
    }

    public XWPFChart createChart(XWPFRun run, int width, int height) throws InvalidFormatException, IOException {
        int chartNumber = this.getNextPartNumber(XWPFRelation.CHART, this.charts.size() + 1);
        POIXMLDocumentPart.RelationPart rp = this.createRelationship(XWPFRelation.CHART, XWPFFactory.getInstance(), chartNumber, false);
        XWPFChart xwpfChart = (XWPFChart)rp.getDocumentPart();
        xwpfChart.setChartIndex(chartNumber);
        xwpfChart.attach(rp.getRelationship().getId(), run);
        xwpfChart.setChartBoundingBox(width, height);
        this.charts.add(xwpfChart);
        return xwpfChart;
    }

    public XWPFFootnote createFootnote() {
        XWPFFootnotes footnotes = this.createFootnotes();
        XWPFFootnote footnote = footnotes.createFootnote();
        return footnote;
    }

    public boolean removeFootnote(int pos) {
        if (null != this.footnotes) {
            return this.footnotes.removeFootnote(pos);
        }
        return false;
    }

    public XWPFEndnote createEndnote() {
        XWPFEndnotes endnotes = this.createEndnotes();
        XWPFEndnote endnote = endnotes.createEndnote();
        return endnote;
    }

    public XWPFEndnotes createEndnotes() {
        if (this.endnotes == null) {
            EndnotesDocument endnotesDoc = EndnotesDocument.Factory.newInstance();
            XWPFRelation relation = XWPFRelation.ENDNOTE;
            int i = this.getRelationIndex(relation);
            XWPFEndnotes wrapper = (XWPFEndnotes)this.createRelationship(relation, XWPFFactory.getInstance(), i);
            wrapper.setEndnotes(endnotesDoc.addNewEndnotes());
            wrapper.setIdManager(this.footnoteIdManager);
            this.endnotes = wrapper;
        }
        return this.endnotes;
    }

    public List<XWPFEndnote> getEndnotes() {
        if (this.endnotes == null) {
            return Collections.emptyList();
        }
        return this.endnotes.getEndnotesList();
    }

    public boolean removeEndnote(int pos) {
        if (null != this.endnotes) {
            return this.endnotes.removeEndnote(pos);
        }
        return false;
    }
}

