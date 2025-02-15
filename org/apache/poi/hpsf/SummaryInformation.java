/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hpsf;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import org.apache.poi.hpsf.ClassID;
import org.apache.poi.hpsf.ClassIDPredefined;
import org.apache.poi.hpsf.Filetime;
import org.apache.poi.hpsf.MarkUnsupportedException;
import org.apache.poi.hpsf.NoPropertySetStreamException;
import org.apache.poi.hpsf.PropertySet;
import org.apache.poi.hpsf.Section;
import org.apache.poi.hpsf.Thumbnail;
import org.apache.poi.hpsf.UnexpectedPropertySetTypeException;
import org.apache.poi.hpsf.wellknown.PropertyIDMap;

public final class SummaryInformation
extends PropertySet {
    public static final String DEFAULT_STREAM_NAME = "\u0005SummaryInformation";
    public static final ClassID FORMAT_ID = ClassIDPredefined.SUMMARY_PROPERTIES.getClassID();

    @Override
    public PropertyIDMap getPropertySetIDMap() {
        return PropertyIDMap.getSummaryInformationProperties();
    }

    public SummaryInformation() {
        this.getFirstSection().setFormatID(FORMAT_ID);
    }

    public SummaryInformation(PropertySet ps) throws UnexpectedPropertySetTypeException {
        super(ps);
        if (!this.isSummaryInformation()) {
            throw new UnexpectedPropertySetTypeException("Not a " + this.getClass().getName());
        }
    }

    public SummaryInformation(InputStream stream) throws NoPropertySetStreamException, MarkUnsupportedException, IOException, UnsupportedEncodingException {
        super(stream);
    }

    public String getTitle() {
        return this.getPropertyStringValue(2);
    }

    public void setTitle(String title) {
        this.set1stProperty(2L, title);
    }

    public void removeTitle() {
        this.remove1stProperty(2L);
    }

    public String getSubject() {
        return this.getPropertyStringValue(3);
    }

    public void setSubject(String subject) {
        this.set1stProperty(3L, subject);
    }

    public void removeSubject() {
        this.remove1stProperty(3L);
    }

    public String getAuthor() {
        return this.getPropertyStringValue(4);
    }

    public void setAuthor(String author) {
        this.set1stProperty(4L, author);
    }

    public void removeAuthor() {
        this.remove1stProperty(4L);
    }

    public String getKeywords() {
        return this.getPropertyStringValue(5);
    }

    public void setKeywords(String keywords) {
        this.set1stProperty(5L, keywords);
    }

    public void removeKeywords() {
        this.remove1stProperty(5L);
    }

    public String getComments() {
        return this.getPropertyStringValue(6);
    }

    public void setComments(String comments) {
        this.set1stProperty(6L, comments);
    }

    public void removeComments() {
        this.remove1stProperty(6L);
    }

    public String getTemplate() {
        return this.getPropertyStringValue(7);
    }

    public void setTemplate(String template) {
        this.set1stProperty(7L, template);
    }

    public void removeTemplate() {
        this.remove1stProperty(7L);
    }

    public String getLastAuthor() {
        return this.getPropertyStringValue(8);
    }

    public void setLastAuthor(String lastAuthor) {
        this.set1stProperty(8L, lastAuthor);
    }

    public void removeLastAuthor() {
        this.remove1stProperty(8L);
    }

    public String getRevNumber() {
        return this.getPropertyStringValue(9);
    }

    public void setRevNumber(String revNumber) {
        this.set1stProperty(9L, revNumber);
    }

    public void removeRevNumber() {
        this.remove1stProperty(9L);
    }

    public long getEditTime() {
        Date d = (Date)this.getProperty(10);
        if (d == null) {
            return 0L;
        }
        return Filetime.dateToFileTime(d);
    }

    public void setEditTime(long time) {
        Date d = Filetime.filetimeToDate(time);
        this.getFirstSection().setProperty(10, 64L, d);
    }

    public void removeEditTime() {
        this.remove1stProperty(10L);
    }

    public Date getLastPrinted() {
        return (Date)this.getProperty(11);
    }

    public void setLastPrinted(Date lastPrinted) {
        this.getFirstSection().setProperty(11, 64L, lastPrinted);
    }

    public void removeLastPrinted() {
        this.remove1stProperty(11L);
    }

    public Date getCreateDateTime() {
        return (Date)this.getProperty(12);
    }

    public void setCreateDateTime(Date createDateTime) {
        this.getFirstSection().setProperty(12, 64L, createDateTime);
    }

    public void removeCreateDateTime() {
        this.remove1stProperty(12L);
    }

    public Date getLastSaveDateTime() {
        return (Date)this.getProperty(13);
    }

    public void setLastSaveDateTime(Date time) {
        Section s = this.getFirstSection();
        s.setProperty(13, 64L, time);
    }

    public void removeLastSaveDateTime() {
        this.remove1stProperty(13L);
    }

    public int getPageCount() {
        return this.getPropertyIntValue(14);
    }

    public void setPageCount(int pageCount) {
        this.set1stProperty(14L, pageCount);
    }

    public void removePageCount() {
        this.remove1stProperty(14L);
    }

    public int getWordCount() {
        return this.getPropertyIntValue(15);
    }

    public void setWordCount(int wordCount) {
        this.set1stProperty(15L, wordCount);
    }

    public void removeWordCount() {
        this.remove1stProperty(15L);
    }

    public int getCharCount() {
        return this.getPropertyIntValue(16);
    }

    public void setCharCount(int charCount) {
        this.set1stProperty(16L, charCount);
    }

    public void removeCharCount() {
        this.remove1stProperty(16L);
    }

    public byte[] getThumbnail() {
        return (byte[])this.getProperty(17);
    }

    public Thumbnail getThumbnailThumbnail() {
        byte[] data = this.getThumbnail();
        if (data == null) {
            return null;
        }
        return new Thumbnail(data);
    }

    public void setThumbnail(byte[] thumbnail) {
        this.getFirstSection().setProperty(17, 30L, thumbnail);
    }

    public void removeThumbnail() {
        this.remove1stProperty(17L);
    }

    public String getApplicationName() {
        return this.getPropertyStringValue(18);
    }

    public void setApplicationName(String applicationName) {
        this.set1stProperty(18L, applicationName);
    }

    public void removeApplicationName() {
        this.remove1stProperty(18L);
    }

    public int getSecurity() {
        return this.getPropertyIntValue(19);
    }

    public void setSecurity(int security) {
        this.set1stProperty(19L, security);
    }

    public void removeSecurity() {
        this.remove1stProperty(19L);
    }
}

