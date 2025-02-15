/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xwpf.usermodel;

import java.util.ArrayList;
import java.util.List;
import org.apache.poi.xwpf.usermodel.IBody;
import org.apache.poi.xwpf.usermodel.ICell;
import org.apache.poi.xwpf.usermodel.IRunBody;
import org.apache.poi.xwpf.usermodel.IRunElement;
import org.apache.poi.xwpf.usermodel.ISDTContent;
import org.apache.poi.xwpf.usermodel.ISDTContents;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFSDT;
import org.apache.poi.xwpf.usermodel.XWPFSDTCell;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlObject;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTP;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtBlock;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtContentBlock;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtContentRun;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl;

public class XWPFSDTContent
implements ISDTContent {
    private List<ISDTContents> bodyElements = new ArrayList<ISDTContents>();

    public XWPFSDTContent(CTSdtContentRun sdtRun, IBody part, IRunBody parent) {
        if (sdtRun == null) {
            return;
        }
        for (CTR ctr : sdtRun.getRArray()) {
            XWPFRun run = new XWPFRun(ctr, parent);
            this.bodyElements.add(run);
        }
    }

    public XWPFSDTContent(CTSdtContentBlock block, IBody part, IRunBody parent) {
        if (block == null) {
            return;
        }
        XmlCursor cursor = block.newCursor();
        cursor.selectPath("./*");
        while (cursor.toNextSelection()) {
            XmlObject o = cursor.getObject();
            if (o instanceof CTP) {
                XWPFParagraph p = new XWPFParagraph((CTP)o, part);
                this.bodyElements.add(p);
                continue;
            }
            if (o instanceof CTTbl) {
                XWPFTable t = new XWPFTable((CTTbl)o, part);
                this.bodyElements.add(t);
                continue;
            }
            if (o instanceof CTSdtBlock) {
                XWPFSDT c = new XWPFSDT((CTSdtBlock)o, part);
                this.bodyElements.add(c);
                continue;
            }
            if (!(o instanceof CTR)) continue;
            XWPFRun run = new XWPFRun((CTR)o, parent);
            this.bodyElements.add(run);
        }
        cursor.dispose();
    }

    @Override
    public String getText() {
        StringBuilder text = new StringBuilder();
        boolean addNewLine = false;
        for (int i = 0; i < this.bodyElements.size(); ++i) {
            ISDTContents o = this.bodyElements.get(i);
            if (o instanceof XWPFParagraph) {
                this.appendParagraph((XWPFParagraph)o, text);
                addNewLine = true;
            } else if (o instanceof XWPFTable) {
                this.appendTable((XWPFTable)o, text);
                addNewLine = true;
            } else if (o instanceof XWPFSDT) {
                text.append(((XWPFSDT)o).getContent().getText());
                addNewLine = true;
            } else if (o instanceof XWPFRun) {
                text.append(o);
                addNewLine = false;
            }
            if (!addNewLine || i >= this.bodyElements.size() - 1) continue;
            text.append("\n");
        }
        return text.toString();
    }

    private void appendTable(XWPFTable table, StringBuilder text) {
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

    private void appendParagraph(XWPFParagraph paragraph, StringBuilder text) {
        for (IRunElement iRunElement : paragraph.getRuns()) {
            text.append(iRunElement);
        }
    }

    @Override
    public String toString() {
        return this.getText();
    }
}

