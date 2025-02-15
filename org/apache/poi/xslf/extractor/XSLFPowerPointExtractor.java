/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xslf.extractor;

import java.io.IOException;
import org.apache.poi.extractor.POITextExtractor;
import org.apache.poi.ooxml.extractor.POIXMLTextExtractor;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.sl.extractor.SlideShowExtractor;
import org.apache.poi.util.Removal;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFRelation;
import org.apache.poi.xslf.usermodel.XSLFShape;
import org.apache.poi.xslf.usermodel.XSLFSlide;
import org.apache.poi.xslf.usermodel.XSLFSlideShow;
import org.apache.poi.xslf.usermodel.XSLFTextParagraph;
import org.apache.xmlbeans.XmlException;

@Deprecated
@Removal(version="5.0.0")
public class XSLFPowerPointExtractor
extends POIXMLTextExtractor {
    public static final XSLFRelation[] SUPPORTED_TYPES = new XSLFRelation[]{XSLFRelation.MAIN, XSLFRelation.MACRO, XSLFRelation.MACRO_TEMPLATE, XSLFRelation.PRESENTATIONML, XSLFRelation.PRESENTATIONML_TEMPLATE, XSLFRelation.PRESENTATION_MACRO};
    private final SlideShowExtractor<XSLFShape, XSLFTextParagraph> delegate;
    private boolean slidesByDefault = true;
    private boolean notesByDefault;
    private boolean commentsByDefault;
    private boolean masterByDefault;

    public XSLFPowerPointExtractor(XMLSlideShow slideShow) {
        super(slideShow);
        this.delegate = new SlideShowExtractor<XSLFShape, XSLFTextParagraph>(slideShow);
    }

    public XSLFPowerPointExtractor(XSLFSlideShow slideShow) {
        this(new XMLSlideShow(slideShow.getPackage()));
    }

    public XSLFPowerPointExtractor(OPCPackage container) throws XmlException, OpenXML4JException, IOException {
        this(new XSLFSlideShow(container));
    }

    public static void main(String[] args) throws Exception {
        if (args.length < 1) {
            System.err.println("Use:");
            System.err.println("  XSLFPowerPointExtractor <filename.pptx>");
            System.exit(1);
        }
        XSLFPowerPointExtractor extractor = new XSLFPowerPointExtractor(new XSLFSlideShow(args[0]));
        System.out.println(((POITextExtractor)extractor).getText());
        extractor.close();
    }

    public void setSlidesByDefault(boolean slidesByDefault) {
        this.slidesByDefault = slidesByDefault;
        this.delegate.setSlidesByDefault(slidesByDefault);
    }

    public void setNotesByDefault(boolean notesByDefault) {
        this.notesByDefault = notesByDefault;
        this.delegate.setNotesByDefault(notesByDefault);
    }

    public void setCommentsByDefault(boolean commentsByDefault) {
        this.commentsByDefault = commentsByDefault;
        this.delegate.setCommentsByDefault(commentsByDefault);
    }

    public void setMasterByDefault(boolean masterByDefault) {
        this.masterByDefault = masterByDefault;
        this.delegate.setMasterByDefault(masterByDefault);
    }

    @Override
    public String getText() {
        return this.delegate.getText();
    }

    public String getText(boolean slideText, boolean notesText) {
        return this.getText(slideText, notesText, this.commentsByDefault, this.masterByDefault);
    }

    public String getText(boolean slideText, boolean notesText, boolean masterText) {
        return this.getText(slideText, notesText, this.commentsByDefault, masterText);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public String getText(boolean slideText, boolean notesText, boolean commentText, boolean masterText) {
        this.delegate.setSlidesByDefault(slideText);
        this.delegate.setNotesByDefault(notesText);
        this.delegate.setCommentsByDefault(commentText);
        this.delegate.setMasterByDefault(masterText);
        try {
            String string = this.delegate.getText();
            return string;
        } finally {
            this.delegate.setSlidesByDefault(this.slidesByDefault);
            this.delegate.setNotesByDefault(this.notesByDefault);
            this.delegate.setCommentsByDefault(this.commentsByDefault);
            this.delegate.setMasterByDefault(this.masterByDefault);
        }
    }

    public static String getText(XSLFSlide slide, boolean slideText, boolean notesText, boolean masterText) {
        return XSLFPowerPointExtractor.getText(slide, slideText, notesText, false, masterText);
    }

    public static String getText(XSLFSlide slide, boolean slideText, boolean notesText, boolean commentText, boolean masterText) {
        SlideShowExtractor<XSLFShape, XSLFTextParagraph> ex = new SlideShowExtractor<XSLFShape, XSLFTextParagraph>(slide.getSlideShow());
        ex.setSlidesByDefault(slideText);
        ex.setNotesByDefault(notesText);
        ex.setCommentsByDefault(commentText);
        ex.setMasterByDefault(masterText);
        return ex.getText(slide);
    }
}

