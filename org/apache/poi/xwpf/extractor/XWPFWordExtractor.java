/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xwpf.extractor;

import java.io.IOException;
import java.util.List;
import org.apache.poi.extractor.POITextExtractor;
import org.apache.poi.ooxml.POIXMLDocument;
import org.apache.poi.ooxml.extractor.POIXMLTextExtractor;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.model.XWPFCommentsDecorator;
import org.apache.poi.xwpf.model.XWPFHeaderFooterPolicy;
import org.apache.poi.xwpf.usermodel.IBodyElement;
import org.apache.poi.xwpf.usermodel.ICell;
import org.apache.poi.xwpf.usermodel.IRunElement;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFHyperlink;
import org.apache.poi.xwpf.usermodel.XWPFHyperlinkRun;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRelation;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFSDT;
import org.apache.poi.xwpf.usermodel.XWPFSDTCell;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.apache.xmlbeans.XmlException;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr;

public class XWPFWordExtractor
extends POIXMLTextExtractor {
    public static final XWPFRelation[] SUPPORTED_TYPES = new XWPFRelation[]{XWPFRelation.DOCUMENT, XWPFRelation.TEMPLATE, XWPFRelation.MACRO_DOCUMENT, XWPFRelation.MACRO_TEMPLATE_DOCUMENT};
    private XWPFDocument document;
    private boolean fetchHyperlinks;
    private boolean concatenatePhoneticRuns = true;

    public XWPFWordExtractor(OPCPackage container) throws XmlException, OpenXML4JException, IOException {
        this(new XWPFDocument(container));
    }

    public XWPFWordExtractor(XWPFDocument document) {
        super(document);
        this.document = document;
    }

    public static void main(String[] args) throws Exception {
        if (args.length < 1) {
            System.err.println("Use:");
            System.err.println("  XWPFWordExtractor <filename.docx>");
            System.exit(1);
        }
        XWPFWordExtractor extractor = new XWPFWordExtractor(POIXMLDocument.openPackage(args[0]));
        System.out.println(((POITextExtractor)extractor).getText());
        extractor.close();
    }

    public void setFetchHyperlinks(boolean fetch) {
        this.fetchHyperlinks = fetch;
    }

    public void setConcatenatePhoneticRuns(boolean concatenatePhoneticRuns) {
        this.concatenatePhoneticRuns = concatenatePhoneticRuns;
    }

    @Override
    public String getText() {
        StringBuilder text = new StringBuilder(64);
        XWPFHeaderFooterPolicy hfPolicy = this.document.getHeaderFooterPolicy();
        this.extractHeaders(text, hfPolicy);
        for (IBodyElement e : this.document.getBodyElements()) {
            this.appendBodyElementText(text, e);
            text.append('\n');
        }
        this.extractFooters(text, hfPolicy);
        return text.toString();
    }

    public void appendBodyElementText(StringBuilder text, IBodyElement e) {
        if (e instanceof XWPFParagraph) {
            this.appendParagraphText(text, (XWPFParagraph)e);
        } else if (e instanceof XWPFTable) {
            this.appendTableText(text, (XWPFTable)e);
        } else if (e instanceof XWPFSDT) {
            text.append(((XWPFSDT)e).getContent().getText());
        }
    }

    public void appendParagraphText(StringBuilder text, XWPFParagraph paragraph) {
        String footnameText;
        CTSectPr ctSectPr = null;
        if (paragraph.getCTP().getPPr() != null) {
            ctSectPr = paragraph.getCTP().getPPr().getSectPr();
        }
        XWPFHeaderFooterPolicy headerFooterPolicy = null;
        if (ctSectPr != null) {
            headerFooterPolicy = new XWPFHeaderFooterPolicy(this.document, ctSectPr);
            this.extractHeaders(text, headerFooterPolicy);
        }
        for (IRunElement iRunElement : paragraph.getRuns()) {
            XWPFHyperlink link;
            if (!this.concatenatePhoneticRuns && iRunElement instanceof XWPFRun) {
                text.append(((XWPFRun)iRunElement).text());
            } else {
                text.append(iRunElement);
            }
            if (!(iRunElement instanceof XWPFHyperlinkRun) || !this.fetchHyperlinks || (link = ((XWPFHyperlinkRun)iRunElement).getHyperlink(this.document)) == null) continue;
            text.append(" <").append(link.getURL()).append(">");
        }
        XWPFCommentsDecorator decorator = new XWPFCommentsDecorator(paragraph, null);
        String string = decorator.getCommentText();
        if (string.length() > 0) {
            text.append(string).append('\n');
        }
        if ((footnameText = paragraph.getFootnoteText()) != null && footnameText.length() > 0) {
            text.append(footnameText).append('\n');
        }
        if (ctSectPr != null) {
            this.extractFooters(text, headerFooterPolicy);
        }
    }

    private void appendTableText(StringBuilder text, XWPFTable table) {
        for (XWPFTableRow row : table.getRows()) {
            List<ICell> cells = row.getTableICells();
            for (int i = 0; i < cells.size(); ++i) {
                ICell cell = cells.get(i);
                if (cell instanceof XWPFTableCell) {
                    text.append(((XWPFTableCell)cell).getTextRecursively());
                } else if (cell instanceof XWPFSDTCell) {
                    text.append(((XWPFSDTCell)cell).getContent().getText());
                }
                if (i >= cells.size() - 1) continue;
                text.append("\t");
            }
            text.append('\n');
        }
    }

    private void extractFooters(StringBuilder text, XWPFHeaderFooterPolicy hfPolicy) {
        if (hfPolicy == null) {
            return;
        }
        if (hfPolicy.getFirstPageFooter() != null) {
            text.append(hfPolicy.getFirstPageFooter().getText());
        }
        if (hfPolicy.getEvenPageFooter() != null) {
            text.append(hfPolicy.getEvenPageFooter().getText());
        }
        if (hfPolicy.getDefaultFooter() != null) {
            text.append(hfPolicy.getDefaultFooter().getText());
        }
    }

    private void extractHeaders(StringBuilder text, XWPFHeaderFooterPolicy hfPolicy) {
        if (hfPolicy == null) {
            return;
        }
        if (hfPolicy.getFirstPageHeader() != null) {
            text.append(hfPolicy.getFirstPageHeader().getText());
        }
        if (hfPolicy.getEvenPageHeader() != null) {
            text.append(hfPolicy.getEvenPageHeader().getText());
        }
        if (hfPolicy.getDefaultHeader() != null) {
            text.append(hfPolicy.getDefaultHeader().getText());
        }
    }
}

