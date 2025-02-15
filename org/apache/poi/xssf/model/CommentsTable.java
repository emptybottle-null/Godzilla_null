/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xssf.model;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.ooxml.POIXMLTypeLoader;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.ss.util.CellAddress;
import org.apache.poi.util.Internal;
import org.apache.poi.util.Removal;
import org.apache.poi.xssf.model.Comments;
import org.apache.poi.xssf.usermodel.XSSFComment;
import org.apache.xmlbeans.XmlException;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTComment;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCommentList;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTComments;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CommentsDocument;

@Internal
public class CommentsTable
extends POIXMLDocumentPart
implements Comments {
    public static final String DEFAULT_AUTHOR = "";
    public static final int DEFAULT_AUTHOR_ID = 0;
    private CTComments comments;
    private Map<CellAddress, CTComment> commentRefs;

    public CommentsTable() {
        this.comments = CTComments.Factory.newInstance();
        this.comments.addNewCommentList();
        this.comments.addNewAuthors().addAuthor(DEFAULT_AUTHOR);
    }

    public CommentsTable(PackagePart part) throws IOException {
        super(part);
        this.readFrom(part.getInputStream());
    }

    public void readFrom(InputStream is) throws IOException {
        try {
            CommentsDocument doc = CommentsDocument.Factory.parse(is, POIXMLTypeLoader.DEFAULT_XML_OPTIONS);
            this.comments = doc.getComments();
        } catch (XmlException e) {
            throw new IOException(e.getLocalizedMessage());
        }
    }

    public void writeTo(OutputStream out) throws IOException {
        CommentsDocument doc = CommentsDocument.Factory.newInstance();
        doc.setComments(this.comments);
        doc.save(out, POIXMLTypeLoader.DEFAULT_XML_OPTIONS);
    }

    @Override
    protected void commit() throws IOException {
        PackagePart part = this.getPackagePart();
        OutputStream out = part.getOutputStream();
        this.writeTo(out);
        out.close();
    }

    public void referenceUpdated(CellAddress oldReference, CTComment comment) {
        if (this.commentRefs != null) {
            this.commentRefs.remove(oldReference);
            this.commentRefs.put(new CellAddress(comment.getRef()), comment);
        }
    }

    @Override
    public int getNumberOfComments() {
        return this.comments.getCommentList().sizeOfCommentArray();
    }

    @Override
    public int getNumberOfAuthors() {
        return this.comments.getAuthors().sizeOfAuthorArray();
    }

    @Override
    public String getAuthor(long authorId) {
        return this.comments.getAuthors().getAuthorArray(Math.toIntExact(authorId));
    }

    @Override
    public int findAuthor(String author) {
        String[] authorArray = this.comments.getAuthors().getAuthorArray();
        for (int i = 0; i < authorArray.length; ++i) {
            if (!authorArray[i].equals(author)) continue;
            return i;
        }
        return this.addNewAuthor(author);
    }

    @Override
    public XSSFComment findCellComment(CellAddress cellAddress) {
        CTComment ct = this.getCTComment(cellAddress);
        return ct == null ? null : new XSSFComment(this, ct, null);
    }

    @Internal
    public CTComment getCTComment(CellAddress cellRef) {
        this.prepareCTCommentCache();
        return this.commentRefs.get(cellRef);
    }

    @Override
    public Iterator<CellAddress> getCellAddresses() {
        this.prepareCTCommentCache();
        return this.commentRefs.keySet().iterator();
    }

    @Removal(version="4.2")
    @Deprecated
    public Map<CellAddress, XSSFComment> getCellComments() {
        this.prepareCTCommentCache();
        TreeMap<CellAddress, XSSFComment> map = new TreeMap<CellAddress, XSSFComment>();
        for (Map.Entry<CellAddress, CTComment> e : this.commentRefs.entrySet()) {
            map.put(e.getKey(), new XSSFComment(this, e.getValue(), null));
        }
        return map;
    }

    private void prepareCTCommentCache() {
        if (this.commentRefs == null) {
            this.commentRefs = new HashMap<CellAddress, CTComment>();
            for (CTComment comment : this.comments.getCommentList().getCommentArray()) {
                this.commentRefs.put(new CellAddress(comment.getRef()), comment);
            }
        }
    }

    @Internal
    public CTComment newComment(CellAddress ref) {
        CTComment ct = this.comments.getCommentList().addNewComment();
        ct.setRef(ref.formatAsString());
        ct.setAuthorId(0L);
        if (this.commentRefs != null) {
            this.commentRefs.put(ref, ct);
        }
        return ct;
    }

    @Override
    public boolean removeComment(CellAddress cellRef) {
        String stringRef = cellRef.formatAsString();
        CTCommentList lst = this.comments.getCommentList();
        if (lst != null) {
            CTComment[] commentArray = lst.getCommentArray();
            for (int i = 0; i < commentArray.length; ++i) {
                CTComment comment = commentArray[i];
                if (!stringRef.equals(comment.getRef())) continue;
                lst.removeComment(i);
                if (this.commentRefs != null) {
                    this.commentRefs.remove(cellRef);
                }
                return true;
            }
        }
        return false;
    }

    private int addNewAuthor(String author) {
        int index = this.comments.getAuthors().sizeOfAuthorArray();
        this.comments.getAuthors().insertAuthor(index, author);
        return index;
    }

    @Internal
    public CTComments getCTComments() {
        return this.comments;
    }
}

