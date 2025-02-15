/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xwpf.usermodel;

import org.apache.poi.xwpf.usermodel.IBody;
import org.apache.poi.xwpf.usermodel.ICell;
import org.apache.poi.xwpf.usermodel.ISDTContent;
import org.apache.poi.xwpf.usermodel.XWPFAbstractSDT;
import org.apache.poi.xwpf.usermodel.XWPFSDTContentCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtCell;

public class XWPFSDTCell
extends XWPFAbstractSDT
implements ICell {
    private final XWPFSDTContentCell cellContent;

    public XWPFSDTCell(CTSdtCell sdtCell, XWPFTableRow xwpfTableRow, IBody part) {
        super(sdtCell.getSdtPr(), part);
        this.cellContent = new XWPFSDTContentCell(sdtCell.getSdtContent(), xwpfTableRow, part);
    }

    @Override
    public ISDTContent getContent() {
        return this.cellContent;
    }
}

