/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
 *  org.openxmlformats.schemas.officeDocument.x2006.math.CTOMathPara
 *  org.openxmlformats.schemas.wordprocessingml.x2006.main.CTCustomXmlCell
 *  org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPerm
 *  org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPermStart
 */
package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.lang.ref.SoftReference;
import java.net.URL;
import java.util.List;
import javax.xml.stream.XMLStreamReader;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SchemaTypeLoader;
import org.apache.xmlbeans.XmlBeans;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath;
import org.openxmlformats.schemas.officeDocument.x2006.math.CTOMathPara;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBookmark;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTCustomXmlCell;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTMarkup;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTMarkupRange;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTMoveBookmark;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPerm;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPermStart;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTProofErr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtCell;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPrEx;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTc;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrackChange;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STLongHexNumber;
import org.w3c.dom.Node;

public interface CTRow
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTRow.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctrow3b78type");

    public CTTblPrEx getTblPrEx();

    public boolean isSetTblPrEx();

    public void setTblPrEx(CTTblPrEx var1);

    public CTTblPrEx addNewTblPrEx();

    public void unsetTblPrEx();

    public CTTrPr getTrPr();

    public boolean isSetTrPr();

    public void setTrPr(CTTrPr var1);

    public CTTrPr addNewTrPr();

    public void unsetTrPr();

    public List<CTTc> getTcList();

    @Deprecated
    public CTTc[] getTcArray();

    public CTTc getTcArray(int var1);

    public int sizeOfTcArray();

    public void setTcArray(CTTc[] var1);

    public void setTcArray(int var1, CTTc var2);

    public CTTc insertNewTc(int var1);

    public CTTc addNewTc();

    public void removeTc(int var1);

    public List<CTCustomXmlCell> getCustomXmlList();

    @Deprecated
    public CTCustomXmlCell[] getCustomXmlArray();

    public CTCustomXmlCell getCustomXmlArray(int var1);

    public int sizeOfCustomXmlArray();

    public void setCustomXmlArray(CTCustomXmlCell[] var1);

    public void setCustomXmlArray(int var1, CTCustomXmlCell var2);

    public CTCustomXmlCell insertNewCustomXml(int var1);

    public CTCustomXmlCell addNewCustomXml();

    public void removeCustomXml(int var1);

    public List<CTSdtCell> getSdtList();

    @Deprecated
    public CTSdtCell[] getSdtArray();

    public CTSdtCell getSdtArray(int var1);

    public int sizeOfSdtArray();

    public void setSdtArray(CTSdtCell[] var1);

    public void setSdtArray(int var1, CTSdtCell var2);

    public CTSdtCell insertNewSdt(int var1);

    public CTSdtCell addNewSdt();

    public void removeSdt(int var1);

    public List<CTProofErr> getProofErrList();

    @Deprecated
    public CTProofErr[] getProofErrArray();

    public CTProofErr getProofErrArray(int var1);

    public int sizeOfProofErrArray();

    public void setProofErrArray(CTProofErr[] var1);

    public void setProofErrArray(int var1, CTProofErr var2);

    public CTProofErr insertNewProofErr(int var1);

    public CTProofErr addNewProofErr();

    public void removeProofErr(int var1);

    public List<CTPermStart> getPermStartList();

    @Deprecated
    public CTPermStart[] getPermStartArray();

    public CTPermStart getPermStartArray(int var1);

    public int sizeOfPermStartArray();

    public void setPermStartArray(CTPermStart[] var1);

    public void setPermStartArray(int var1, CTPermStart var2);

    public CTPermStart insertNewPermStart(int var1);

    public CTPermStart addNewPermStart();

    public void removePermStart(int var1);

    public List<CTPerm> getPermEndList();

    @Deprecated
    public CTPerm[] getPermEndArray();

    public CTPerm getPermEndArray(int var1);

    public int sizeOfPermEndArray();

    public void setPermEndArray(CTPerm[] var1);

    public void setPermEndArray(int var1, CTPerm var2);

    public CTPerm insertNewPermEnd(int var1);

    public CTPerm addNewPermEnd();

    public void removePermEnd(int var1);

    public List<CTBookmark> getBookmarkStartList();

    @Deprecated
    public CTBookmark[] getBookmarkStartArray();

    public CTBookmark getBookmarkStartArray(int var1);

    public int sizeOfBookmarkStartArray();

    public void setBookmarkStartArray(CTBookmark[] var1);

    public void setBookmarkStartArray(int var1, CTBookmark var2);

    public CTBookmark insertNewBookmarkStart(int var1);

    public CTBookmark addNewBookmarkStart();

    public void removeBookmarkStart(int var1);

    public List<CTMarkupRange> getBookmarkEndList();

    @Deprecated
    public CTMarkupRange[] getBookmarkEndArray();

    public CTMarkupRange getBookmarkEndArray(int var1);

    public int sizeOfBookmarkEndArray();

    public void setBookmarkEndArray(CTMarkupRange[] var1);

    public void setBookmarkEndArray(int var1, CTMarkupRange var2);

    public CTMarkupRange insertNewBookmarkEnd(int var1);

    public CTMarkupRange addNewBookmarkEnd();

    public void removeBookmarkEnd(int var1);

    public List<CTMoveBookmark> getMoveFromRangeStartList();

    @Deprecated
    public CTMoveBookmark[] getMoveFromRangeStartArray();

    public CTMoveBookmark getMoveFromRangeStartArray(int var1);

    public int sizeOfMoveFromRangeStartArray();

    public void setMoveFromRangeStartArray(CTMoveBookmark[] var1);

    public void setMoveFromRangeStartArray(int var1, CTMoveBookmark var2);

    public CTMoveBookmark insertNewMoveFromRangeStart(int var1);

    public CTMoveBookmark addNewMoveFromRangeStart();

    public void removeMoveFromRangeStart(int var1);

    public List<CTMarkupRange> getMoveFromRangeEndList();

    @Deprecated
    public CTMarkupRange[] getMoveFromRangeEndArray();

    public CTMarkupRange getMoveFromRangeEndArray(int var1);

    public int sizeOfMoveFromRangeEndArray();

    public void setMoveFromRangeEndArray(CTMarkupRange[] var1);

    public void setMoveFromRangeEndArray(int var1, CTMarkupRange var2);

    public CTMarkupRange insertNewMoveFromRangeEnd(int var1);

    public CTMarkupRange addNewMoveFromRangeEnd();

    public void removeMoveFromRangeEnd(int var1);

    public List<CTMoveBookmark> getMoveToRangeStartList();

    @Deprecated
    public CTMoveBookmark[] getMoveToRangeStartArray();

    public CTMoveBookmark getMoveToRangeStartArray(int var1);

    public int sizeOfMoveToRangeStartArray();

    public void setMoveToRangeStartArray(CTMoveBookmark[] var1);

    public void setMoveToRangeStartArray(int var1, CTMoveBookmark var2);

    public CTMoveBookmark insertNewMoveToRangeStart(int var1);

    public CTMoveBookmark addNewMoveToRangeStart();

    public void removeMoveToRangeStart(int var1);

    public List<CTMarkupRange> getMoveToRangeEndList();

    @Deprecated
    public CTMarkupRange[] getMoveToRangeEndArray();

    public CTMarkupRange getMoveToRangeEndArray(int var1);

    public int sizeOfMoveToRangeEndArray();

    public void setMoveToRangeEndArray(CTMarkupRange[] var1);

    public void setMoveToRangeEndArray(int var1, CTMarkupRange var2);

    public CTMarkupRange insertNewMoveToRangeEnd(int var1);

    public CTMarkupRange addNewMoveToRangeEnd();

    public void removeMoveToRangeEnd(int var1);

    public List<CTMarkupRange> getCommentRangeStartList();

    @Deprecated
    public CTMarkupRange[] getCommentRangeStartArray();

    public CTMarkupRange getCommentRangeStartArray(int var1);

    public int sizeOfCommentRangeStartArray();

    public void setCommentRangeStartArray(CTMarkupRange[] var1);

    public void setCommentRangeStartArray(int var1, CTMarkupRange var2);

    public CTMarkupRange insertNewCommentRangeStart(int var1);

    public CTMarkupRange addNewCommentRangeStart();

    public void removeCommentRangeStart(int var1);

    public List<CTMarkupRange> getCommentRangeEndList();

    @Deprecated
    public CTMarkupRange[] getCommentRangeEndArray();

    public CTMarkupRange getCommentRangeEndArray(int var1);

    public int sizeOfCommentRangeEndArray();

    public void setCommentRangeEndArray(CTMarkupRange[] var1);

    public void setCommentRangeEndArray(int var1, CTMarkupRange var2);

    public CTMarkupRange insertNewCommentRangeEnd(int var1);

    public CTMarkupRange addNewCommentRangeEnd();

    public void removeCommentRangeEnd(int var1);

    public List<CTTrackChange> getCustomXmlInsRangeStartList();

    @Deprecated
    public CTTrackChange[] getCustomXmlInsRangeStartArray();

    public CTTrackChange getCustomXmlInsRangeStartArray(int var1);

    public int sizeOfCustomXmlInsRangeStartArray();

    public void setCustomXmlInsRangeStartArray(CTTrackChange[] var1);

    public void setCustomXmlInsRangeStartArray(int var1, CTTrackChange var2);

    public CTTrackChange insertNewCustomXmlInsRangeStart(int var1);

    public CTTrackChange addNewCustomXmlInsRangeStart();

    public void removeCustomXmlInsRangeStart(int var1);

    public List<CTMarkup> getCustomXmlInsRangeEndList();

    @Deprecated
    public CTMarkup[] getCustomXmlInsRangeEndArray();

    public CTMarkup getCustomXmlInsRangeEndArray(int var1);

    public int sizeOfCustomXmlInsRangeEndArray();

    public void setCustomXmlInsRangeEndArray(CTMarkup[] var1);

    public void setCustomXmlInsRangeEndArray(int var1, CTMarkup var2);

    public CTMarkup insertNewCustomXmlInsRangeEnd(int var1);

    public CTMarkup addNewCustomXmlInsRangeEnd();

    public void removeCustomXmlInsRangeEnd(int var1);

    public List<CTTrackChange> getCustomXmlDelRangeStartList();

    @Deprecated
    public CTTrackChange[] getCustomXmlDelRangeStartArray();

    public CTTrackChange getCustomXmlDelRangeStartArray(int var1);

    public int sizeOfCustomXmlDelRangeStartArray();

    public void setCustomXmlDelRangeStartArray(CTTrackChange[] var1);

    public void setCustomXmlDelRangeStartArray(int var1, CTTrackChange var2);

    public CTTrackChange insertNewCustomXmlDelRangeStart(int var1);

    public CTTrackChange addNewCustomXmlDelRangeStart();

    public void removeCustomXmlDelRangeStart(int var1);

    public List<CTMarkup> getCustomXmlDelRangeEndList();

    @Deprecated
    public CTMarkup[] getCustomXmlDelRangeEndArray();

    public CTMarkup getCustomXmlDelRangeEndArray(int var1);

    public int sizeOfCustomXmlDelRangeEndArray();

    public void setCustomXmlDelRangeEndArray(CTMarkup[] var1);

    public void setCustomXmlDelRangeEndArray(int var1, CTMarkup var2);

    public CTMarkup insertNewCustomXmlDelRangeEnd(int var1);

    public CTMarkup addNewCustomXmlDelRangeEnd();

    public void removeCustomXmlDelRangeEnd(int var1);

    public List<CTTrackChange> getCustomXmlMoveFromRangeStartList();

    @Deprecated
    public CTTrackChange[] getCustomXmlMoveFromRangeStartArray();

    public CTTrackChange getCustomXmlMoveFromRangeStartArray(int var1);

    public int sizeOfCustomXmlMoveFromRangeStartArray();

    public void setCustomXmlMoveFromRangeStartArray(CTTrackChange[] var1);

    public void setCustomXmlMoveFromRangeStartArray(int var1, CTTrackChange var2);

    public CTTrackChange insertNewCustomXmlMoveFromRangeStart(int var1);

    public CTTrackChange addNewCustomXmlMoveFromRangeStart();

    public void removeCustomXmlMoveFromRangeStart(int var1);

    public List<CTMarkup> getCustomXmlMoveFromRangeEndList();

    @Deprecated
    public CTMarkup[] getCustomXmlMoveFromRangeEndArray();

    public CTMarkup getCustomXmlMoveFromRangeEndArray(int var1);

    public int sizeOfCustomXmlMoveFromRangeEndArray();

    public void setCustomXmlMoveFromRangeEndArray(CTMarkup[] var1);

    public void setCustomXmlMoveFromRangeEndArray(int var1, CTMarkup var2);

    public CTMarkup insertNewCustomXmlMoveFromRangeEnd(int var1);

    public CTMarkup addNewCustomXmlMoveFromRangeEnd();

    public void removeCustomXmlMoveFromRangeEnd(int var1);

    public List<CTTrackChange> getCustomXmlMoveToRangeStartList();

    @Deprecated
    public CTTrackChange[] getCustomXmlMoveToRangeStartArray();

    public CTTrackChange getCustomXmlMoveToRangeStartArray(int var1);

    public int sizeOfCustomXmlMoveToRangeStartArray();

    public void setCustomXmlMoveToRangeStartArray(CTTrackChange[] var1);

    public void setCustomXmlMoveToRangeStartArray(int var1, CTTrackChange var2);

    public CTTrackChange insertNewCustomXmlMoveToRangeStart(int var1);

    public CTTrackChange addNewCustomXmlMoveToRangeStart();

    public void removeCustomXmlMoveToRangeStart(int var1);

    public List<CTMarkup> getCustomXmlMoveToRangeEndList();

    @Deprecated
    public CTMarkup[] getCustomXmlMoveToRangeEndArray();

    public CTMarkup getCustomXmlMoveToRangeEndArray(int var1);

    public int sizeOfCustomXmlMoveToRangeEndArray();

    public void setCustomXmlMoveToRangeEndArray(CTMarkup[] var1);

    public void setCustomXmlMoveToRangeEndArray(int var1, CTMarkup var2);

    public CTMarkup insertNewCustomXmlMoveToRangeEnd(int var1);

    public CTMarkup addNewCustomXmlMoveToRangeEnd();

    public void removeCustomXmlMoveToRangeEnd(int var1);

    public List<CTRunTrackChange> getInsList();

    @Deprecated
    public CTRunTrackChange[] getInsArray();

    public CTRunTrackChange getInsArray(int var1);

    public int sizeOfInsArray();

    public void setInsArray(CTRunTrackChange[] var1);

    public void setInsArray(int var1, CTRunTrackChange var2);

    public CTRunTrackChange insertNewIns(int var1);

    public CTRunTrackChange addNewIns();

    public void removeIns(int var1);

    public List<CTRunTrackChange> getDelList();

    @Deprecated
    public CTRunTrackChange[] getDelArray();

    public CTRunTrackChange getDelArray(int var1);

    public int sizeOfDelArray();

    public void setDelArray(CTRunTrackChange[] var1);

    public void setDelArray(int var1, CTRunTrackChange var2);

    public CTRunTrackChange insertNewDel(int var1);

    public CTRunTrackChange addNewDel();

    public void removeDel(int var1);

    public List<CTRunTrackChange> getMoveFromList();

    @Deprecated
    public CTRunTrackChange[] getMoveFromArray();

    public CTRunTrackChange getMoveFromArray(int var1);

    public int sizeOfMoveFromArray();

    public void setMoveFromArray(CTRunTrackChange[] var1);

    public void setMoveFromArray(int var1, CTRunTrackChange var2);

    public CTRunTrackChange insertNewMoveFrom(int var1);

    public CTRunTrackChange addNewMoveFrom();

    public void removeMoveFrom(int var1);

    public List<CTRunTrackChange> getMoveToList();

    @Deprecated
    public CTRunTrackChange[] getMoveToArray();

    public CTRunTrackChange getMoveToArray(int var1);

    public int sizeOfMoveToArray();

    public void setMoveToArray(CTRunTrackChange[] var1);

    public void setMoveToArray(int var1, CTRunTrackChange var2);

    public CTRunTrackChange insertNewMoveTo(int var1);

    public CTRunTrackChange addNewMoveTo();

    public void removeMoveTo(int var1);

    public List<CTOMathPara> getOMathParaList();

    @Deprecated
    public CTOMathPara[] getOMathParaArray();

    public CTOMathPara getOMathParaArray(int var1);

    public int sizeOfOMathParaArray();

    public void setOMathParaArray(CTOMathPara[] var1);

    public void setOMathParaArray(int var1, CTOMathPara var2);

    public CTOMathPara insertNewOMathPara(int var1);

    public CTOMathPara addNewOMathPara();

    public void removeOMathPara(int var1);

    public List<CTOMath> getOMathList();

    @Deprecated
    public CTOMath[] getOMathArray();

    public CTOMath getOMathArray(int var1);

    public int sizeOfOMathArray();

    public void setOMathArray(CTOMath[] var1);

    public void setOMathArray(int var1, CTOMath var2);

    public CTOMath insertNewOMath(int var1);

    public CTOMath addNewOMath();

    public void removeOMath(int var1);

    public byte[] getRsidRPr();

    public STLongHexNumber xgetRsidRPr();

    public boolean isSetRsidRPr();

    public void setRsidRPr(byte[] var1);

    public void xsetRsidRPr(STLongHexNumber var1);

    public void unsetRsidRPr();

    public byte[] getRsidR();

    public STLongHexNumber xgetRsidR();

    public boolean isSetRsidR();

    public void setRsidR(byte[] var1);

    public void xsetRsidR(STLongHexNumber var1);

    public void unsetRsidR();

    public byte[] getRsidDel();

    public STLongHexNumber xgetRsidDel();

    public boolean isSetRsidDel();

    public void setRsidDel(byte[] var1);

    public void xsetRsidDel(STLongHexNumber var1);

    public void unsetRsidDel();

    public byte[] getRsidTr();

    public STLongHexNumber xgetRsidTr();

    public boolean isSetRsidTr();

    public void setRsidTr(byte[] var1);

    public void xsetRsidTr(STLongHexNumber var1);

    public void unsetRsidTr();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTRow.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTRow newInstance() {
            return (CTRow)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTRow newInstance(XmlOptions xmlOptions) {
            return (CTRow)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTRow parse(String string) throws XmlException {
            return (CTRow)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTRow parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTRow)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTRow parse(File file) throws XmlException, IOException {
            return (CTRow)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTRow parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTRow)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTRow parse(URL uRL) throws XmlException, IOException {
            return (CTRow)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTRow parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTRow)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTRow parse(InputStream inputStream) throws XmlException, IOException {
            return (CTRow)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTRow parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTRow)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTRow parse(Reader reader) throws XmlException, IOException {
            return (CTRow)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTRow parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTRow)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTRow parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTRow)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTRow parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTRow)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTRow parse(Node node) throws XmlException {
            return (CTRow)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTRow parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTRow)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTRow parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTRow)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTRow parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTRow)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
        }

        @Deprecated
        public static XMLInputStream newValidatingXMLInputStream(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return Factory.getTypeLoader().newValidatingXMLInputStream(xMLInputStream, type, null);
        }

        @Deprecated
        public static XMLInputStream newValidatingXMLInputStream(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return Factory.getTypeLoader().newValidatingXMLInputStream(xMLInputStream, type, xmlOptions);
        }

        private Factory() {
        }
    }
}

