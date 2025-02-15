/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xssf.binary;

import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.util.CellAddress;
import org.apache.poi.util.Internal;
import org.apache.poi.xssf.binary.XSSFBRichTextString;
import org.apache.poi.xssf.usermodel.XSSFComment;

@Internal
class XSSFBComment
extends XSSFComment {
    private final CellAddress cellAddress;
    private final String author;
    private final XSSFBRichTextString comment;
    private boolean visible = true;

    XSSFBComment(CellAddress cellAddress, String author, String comment) {
        super(null, null, null);
        this.cellAddress = cellAddress;
        this.author = author;
        this.comment = new XSSFBRichTextString(comment);
    }

    @Override
    public void setVisible(boolean visible) {
        throw new IllegalArgumentException("XSSFBComment is read only.");
    }

    @Override
    public boolean isVisible() {
        return this.visible;
    }

    @Override
    public CellAddress getAddress() {
        return this.cellAddress;
    }

    @Override
    public void setAddress(CellAddress addr) {
        throw new IllegalArgumentException("XSSFBComment is read only");
    }

    @Override
    public void setAddress(int row, int col) {
        throw new IllegalArgumentException("XSSFBComment is read only");
    }

    @Override
    public int getRow() {
        return this.cellAddress.getRow();
    }

    @Override
    public void setRow(int row) {
        throw new IllegalArgumentException("XSSFBComment is read only");
    }

    @Override
    public int getColumn() {
        return this.cellAddress.getColumn();
    }

    @Override
    public void setColumn(int col) {
        throw new IllegalArgumentException("XSSFBComment is read only");
    }

    @Override
    public String getAuthor() {
        return this.author;
    }

    @Override
    public void setAuthor(String author) {
        throw new IllegalArgumentException("XSSFBComment is read only");
    }

    @Override
    public XSSFBRichTextString getString() {
        return this.comment;
    }

    @Override
    public void setString(RichTextString string) {
        throw new IllegalArgumentException("XSSFBComment is read only");
    }

    @Override
    public ClientAnchor getClientAnchor() {
        return null;
    }
}

