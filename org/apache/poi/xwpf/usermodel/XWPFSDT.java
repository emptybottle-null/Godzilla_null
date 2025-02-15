/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xwpf.usermodel;

import org.apache.poi.xwpf.usermodel.IBody;
import org.apache.poi.xwpf.usermodel.IBodyElement;
import org.apache.poi.xwpf.usermodel.IRunBody;
import org.apache.poi.xwpf.usermodel.IRunElement;
import org.apache.poi.xwpf.usermodel.ISDTContent;
import org.apache.poi.xwpf.usermodel.ISDTContents;
import org.apache.poi.xwpf.usermodel.XWPFAbstractSDT;
import org.apache.poi.xwpf.usermodel.XWPFSDTContent;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtBlock;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtRun;

public class XWPFSDT
extends XWPFAbstractSDT
implements IBodyElement,
IRunBody,
ISDTContents,
IRunElement {
    private final ISDTContent content;

    public XWPFSDT(CTSdtRun sdtRun, IBody part) {
        super(sdtRun.getSdtPr(), part);
        this.content = new XWPFSDTContent(sdtRun.getSdtContent(), part, (IRunBody)this);
    }

    public XWPFSDT(CTSdtBlock block, IBody part) {
        super(block.getSdtPr(), part);
        this.content = new XWPFSDTContent(block.getSdtContent(), part, (IRunBody)this);
    }

    @Override
    public ISDTContent getContent() {
        return this.content;
    }
}

