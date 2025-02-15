/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xssf.usermodel;

import com.microsoft.schemas.office.excel.CTClientData;
import com.microsoft.schemas.vml.CTShape;
import java.math.BigInteger;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.Comment;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.util.CellAddress;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.model.CommentsTable;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTComment;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRst;

public class XSSFComment
implements Comment {
    private final CTComment _comment;
    private final CommentsTable _comments;
    private final CTShape _vmlShape;
    private XSSFRichTextString _str;

    public XSSFComment(CommentsTable comments, CTComment comment, CTShape vmlShape) {
        this._comment = comment;
        this._comments = comments;
        this._vmlShape = vmlShape;
        if (comment != null && vmlShape != null && vmlShape.sizeOfClientDataArray() > 0) {
            CellReference ref = new CellReference(comment.getRef());
            CTClientData clientData = vmlShape.getClientDataArray(0);
            clientData.setRowArray(0, new BigInteger(String.valueOf(ref.getRow())));
            clientData.setColumnArray(0, new BigInteger(String.valueOf(ref.getCol())));
            XSSFComment.avoidXmlbeansCorruptPointer(vmlShape);
        }
    }

    @Override
    public String getAuthor() {
        return this._comments.getAuthor(this._comment.getAuthorId());
    }

    @Override
    public void setAuthor(String author) {
        this._comment.setAuthorId(this._comments.findAuthor(author));
    }

    @Override
    public int getColumn() {
        return this.getAddress().getColumn();
    }

    @Override
    public int getRow() {
        return this.getAddress().getRow();
    }

    @Override
    public boolean isVisible() {
        boolean visible = false;
        if (this._vmlShape != null) {
            String style = this._vmlShape.getStyle();
            visible = style != null && style.contains("visibility:visible");
        }
        return visible;
    }

    @Override
    public void setVisible(boolean visible) {
        if (this._vmlShape != null) {
            String style = visible ? "position:absolute;visibility:visible" : "position:absolute;visibility:hidden";
            this._vmlShape.setStyle(style);
        }
    }

    @Override
    public CellAddress getAddress() {
        return new CellAddress(this._comment.getRef());
    }

    @Override
    public void setAddress(int row, int col) {
        this.setAddress(new CellAddress(row, col));
    }

    @Override
    public void setAddress(CellAddress address) {
        CellAddress oldRef = new CellAddress(this._comment.getRef());
        if (address.equals(oldRef)) {
            return;
        }
        this._comment.setRef(address.formatAsString());
        this._comments.referenceUpdated(oldRef, this._comment);
        if (this._vmlShape != null) {
            CTClientData clientData = this._vmlShape.getClientDataArray(0);
            clientData.setRowArray(0, new BigInteger(String.valueOf(address.getRow())));
            clientData.setColumnArray(0, new BigInteger(String.valueOf(address.getColumn())));
            XSSFComment.avoidXmlbeansCorruptPointer(this._vmlShape);
        }
    }

    @Override
    public void setColumn(int col) {
        this.setAddress(this.getRow(), col);
    }

    @Override
    public void setRow(int row) {
        this.setAddress(row, this.getColumn());
    }

    @Override
    public XSSFRichTextString getString() {
        CTRst rst;
        if (this._str == null && (rst = this._comment.getText()) != null) {
            this._str = new XSSFRichTextString(this._comment.getText());
        }
        return this._str;
    }

    @Override
    public void setString(RichTextString string) {
        if (!(string instanceof XSSFRichTextString)) {
            throw new IllegalArgumentException("Only XSSFRichTextString argument is supported");
        }
        this._str = (XSSFRichTextString)string;
        this._comment.setText(this._str.getCTRst());
    }

    public void setString(String string) {
        this.setString(new XSSFRichTextString(string));
    }

    @Override
    public ClientAnchor getClientAnchor() {
        if (this._vmlShape == null) {
            return null;
        }
        String position = this._vmlShape.getClientDataArray(0).getAnchorArray(0);
        int[] pos = new int[8];
        int i = 0;
        for (String s : position.split(",")) {
            pos[i++] = Integer.parseInt(s.trim());
        }
        return new XSSFClientAnchor(pos[1] * 9525, pos[3] * 9525, pos[5] * 9525, pos[7] * 9525, pos[0], pos[2], pos[4], pos[6]);
    }

    protected CTComment getCTComment() {
        return this._comment;
    }

    protected CTShape getCTShape() {
        return this._vmlShape;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof XSSFComment)) {
            return false;
        }
        XSSFComment other = (XSSFComment)obj;
        return this.getCTComment() == other.getCTComment() && this.getCTShape() == other.getCTShape();
    }

    public int hashCode() {
        return (this.getRow() * 17 + this.getColumn()) * 31;
    }

    private static void avoidXmlbeansCorruptPointer(CTShape vmlShape) {
        vmlShape.getClientDataList().toString();
    }
}

