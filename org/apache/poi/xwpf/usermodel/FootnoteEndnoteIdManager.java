/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xwpf.usermodel;

import java.math.BigInteger;
import java.util.ArrayList;
import org.apache.poi.xwpf.usermodel.XWPFAbstractFootnoteEndnote;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

public class FootnoteEndnoteIdManager {
    private XWPFDocument document;

    public FootnoteEndnoteIdManager(XWPFDocument document) {
        this.document = document;
    }

    /*
     * WARNING - void declaration
     */
    public BigInteger nextId() {
        void var3_8;
        ArrayList<BigInteger> ids = new ArrayList<BigInteger>();
        for (XWPFAbstractFootnoteEndnote xWPFAbstractFootnoteEndnote : this.document.getFootnotes()) {
            ids.add(xWPFAbstractFootnoteEndnote.getId());
        }
        for (XWPFAbstractFootnoteEndnote xWPFAbstractFootnoteEndnote : this.document.getEndnotes()) {
            ids.add(xWPFAbstractFootnoteEndnote.getId());
        }
        int cand = ids.size();
        BigInteger bigInteger = BigInteger.valueOf(cand);
        while (ids.contains(var3_8)) {
            BigInteger bigInteger2 = BigInteger.valueOf(++cand);
        }
        return var3_8;
    }
}

