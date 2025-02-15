/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xslf.usermodel;

import java.awt.geom.Point2D;
import java.util.Calendar;
import java.util.Date;
import org.apache.poi.sl.usermodel.Comment;
import org.apache.poi.util.LocaleUtil;
import org.apache.poi.util.Units;
import org.apache.poi.xslf.usermodel.XSLFCommentAuthors;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPoint2D;
import org.openxmlformats.schemas.presentationml.x2006.main.CTComment;
import org.openxmlformats.schemas.presentationml.x2006.main.CTCommentAuthor;
import org.openxmlformats.schemas.presentationml.x2006.main.CTCommentAuthorList;

public class XSLFComment
implements Comment {
    final CTComment comment;
    final XSLFCommentAuthors authors;

    XSLFComment(CTComment comment, XSLFCommentAuthors authors) {
        this.comment = comment;
        this.authors = authors;
    }

    @Override
    public String getAuthor() {
        return this.authors.getAuthorById(this.comment.getAuthorId()).getName();
    }

    @Override
    public void setAuthor(String author) {
        if (author == null) {
            throw new IllegalArgumentException("author must not be null");
        }
        CTCommentAuthorList list = this.authors.getCTCommentAuthorsList();
        long maxId = -1L;
        for (CTCommentAuthor aut : list.getCmAuthorArray()) {
            maxId = Math.max(aut.getId(), maxId);
            if (!author.equals(aut.getName())) continue;
            this.comment.setAuthorId(aut.getId());
            return;
        }
        CTCommentAuthor newAuthor = list.addNewCmAuthor();
        newAuthor.setName(author);
        newAuthor.setId(maxId + 1L);
        newAuthor.setInitials(author.replaceAll("\\s*(\\w)\\S*", "$1").toUpperCase(LocaleUtil.getUserLocale()));
        this.comment.setAuthorId(maxId + 1L);
    }

    @Override
    public String getAuthorInitials() {
        CTCommentAuthor aut = this.authors.getAuthorById(this.comment.getAuthorId());
        return aut == null ? null : aut.getInitials();
    }

    @Override
    public void setAuthorInitials(String initials) {
        CTCommentAuthor aut = this.authors.getAuthorById(this.comment.getAuthorId());
        if (aut != null) {
            aut.setInitials(initials);
        }
    }

    @Override
    public String getText() {
        return this.comment.getText();
    }

    @Override
    public void setText(String text) {
        this.comment.setText(text);
    }

    @Override
    public Date getDate() {
        Calendar cal = this.comment.getDt();
        return cal == null ? null : cal.getTime();
    }

    @Override
    public void setDate(Date date) {
        Calendar cal = LocaleUtil.getLocaleCalendar();
        cal.setTime(date);
        this.comment.setDt(cal);
    }

    @Override
    public Point2D getOffset() {
        CTPoint2D pos = this.comment.getPos();
        return new Point2D.Double(Units.toPoints(pos.getX()), Units.toPoints(pos.getY()));
    }

    @Override
    public void setOffset(Point2D offset) {
        CTPoint2D pos = this.comment.getPos();
        if (pos == null) {
            pos = this.comment.addNewPos();
        }
        pos.setX(Units.toEMU(offset.getX()));
        pos.setY(Units.toEMU(offset.getY()));
    }
}

