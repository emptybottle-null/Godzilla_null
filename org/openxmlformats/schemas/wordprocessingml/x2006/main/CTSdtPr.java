/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDataBinding
 *  org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLock
 *  org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPlaceholder
 *  org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtComboBox
 *  org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtDate
 *  org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtDropDownList
 *  org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtText
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
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDataBinding;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDecimalNumber;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTEmpty;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLock;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTOnOff;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPlaceholder;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtComboBox;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtDate;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtDocPart;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtDropDownList;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtText;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTString;
import org.w3c.dom.Node;

public interface CTSdtPr
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTSdtPr.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctsdtpre24dtype");

    public List<CTRPr> getRPrList();

    @Deprecated
    public CTRPr[] getRPrArray();

    public CTRPr getRPrArray(int var1);

    public int sizeOfRPrArray();

    public void setRPrArray(CTRPr[] var1);

    public void setRPrArray(int var1, CTRPr var2);

    public CTRPr insertNewRPr(int var1);

    public CTRPr addNewRPr();

    public void removeRPr(int var1);

    public List<CTString> getAliasList();

    @Deprecated
    public CTString[] getAliasArray();

    public CTString getAliasArray(int var1);

    public int sizeOfAliasArray();

    public void setAliasArray(CTString[] var1);

    public void setAliasArray(int var1, CTString var2);

    public CTString insertNewAlias(int var1);

    public CTString addNewAlias();

    public void removeAlias(int var1);

    public List<CTLock> getLockList();

    @Deprecated
    public CTLock[] getLockArray();

    public CTLock getLockArray(int var1);

    public int sizeOfLockArray();

    public void setLockArray(CTLock[] var1);

    public void setLockArray(int var1, CTLock var2);

    public CTLock insertNewLock(int var1);

    public CTLock addNewLock();

    public void removeLock(int var1);

    public List<CTPlaceholder> getPlaceholderList();

    @Deprecated
    public CTPlaceholder[] getPlaceholderArray();

    public CTPlaceholder getPlaceholderArray(int var1);

    public int sizeOfPlaceholderArray();

    public void setPlaceholderArray(CTPlaceholder[] var1);

    public void setPlaceholderArray(int var1, CTPlaceholder var2);

    public CTPlaceholder insertNewPlaceholder(int var1);

    public CTPlaceholder addNewPlaceholder();

    public void removePlaceholder(int var1);

    public List<CTOnOff> getShowingPlcHdrList();

    @Deprecated
    public CTOnOff[] getShowingPlcHdrArray();

    public CTOnOff getShowingPlcHdrArray(int var1);

    public int sizeOfShowingPlcHdrArray();

    public void setShowingPlcHdrArray(CTOnOff[] var1);

    public void setShowingPlcHdrArray(int var1, CTOnOff var2);

    public CTOnOff insertNewShowingPlcHdr(int var1);

    public CTOnOff addNewShowingPlcHdr();

    public void removeShowingPlcHdr(int var1);

    public List<CTDataBinding> getDataBindingList();

    @Deprecated
    public CTDataBinding[] getDataBindingArray();

    public CTDataBinding getDataBindingArray(int var1);

    public int sizeOfDataBindingArray();

    public void setDataBindingArray(CTDataBinding[] var1);

    public void setDataBindingArray(int var1, CTDataBinding var2);

    public CTDataBinding insertNewDataBinding(int var1);

    public CTDataBinding addNewDataBinding();

    public void removeDataBinding(int var1);

    public List<CTOnOff> getTemporaryList();

    @Deprecated
    public CTOnOff[] getTemporaryArray();

    public CTOnOff getTemporaryArray(int var1);

    public int sizeOfTemporaryArray();

    public void setTemporaryArray(CTOnOff[] var1);

    public void setTemporaryArray(int var1, CTOnOff var2);

    public CTOnOff insertNewTemporary(int var1);

    public CTOnOff addNewTemporary();

    public void removeTemporary(int var1);

    public List<CTDecimalNumber> getIdList();

    @Deprecated
    public CTDecimalNumber[] getIdArray();

    public CTDecimalNumber getIdArray(int var1);

    public int sizeOfIdArray();

    public void setIdArray(CTDecimalNumber[] var1);

    public void setIdArray(int var1, CTDecimalNumber var2);

    public CTDecimalNumber insertNewId(int var1);

    public CTDecimalNumber addNewId();

    public void removeId(int var1);

    public List<CTString> getTagList();

    @Deprecated
    public CTString[] getTagArray();

    public CTString getTagArray(int var1);

    public int sizeOfTagArray();

    public void setTagArray(CTString[] var1);

    public void setTagArray(int var1, CTString var2);

    public CTString insertNewTag(int var1);

    public CTString addNewTag();

    public void removeTag(int var1);

    public List<CTEmpty> getEquationList();

    @Deprecated
    public CTEmpty[] getEquationArray();

    public CTEmpty getEquationArray(int var1);

    public int sizeOfEquationArray();

    public void setEquationArray(CTEmpty[] var1);

    public void setEquationArray(int var1, CTEmpty var2);

    public CTEmpty insertNewEquation(int var1);

    public CTEmpty addNewEquation();

    public void removeEquation(int var1);

    public List<CTSdtComboBox> getComboBoxList();

    @Deprecated
    public CTSdtComboBox[] getComboBoxArray();

    public CTSdtComboBox getComboBoxArray(int var1);

    public int sizeOfComboBoxArray();

    public void setComboBoxArray(CTSdtComboBox[] var1);

    public void setComboBoxArray(int var1, CTSdtComboBox var2);

    public CTSdtComboBox insertNewComboBox(int var1);

    public CTSdtComboBox addNewComboBox();

    public void removeComboBox(int var1);

    public List<CTSdtDate> getDateList();

    @Deprecated
    public CTSdtDate[] getDateArray();

    public CTSdtDate getDateArray(int var1);

    public int sizeOfDateArray();

    public void setDateArray(CTSdtDate[] var1);

    public void setDateArray(int var1, CTSdtDate var2);

    public CTSdtDate insertNewDate(int var1);

    public CTSdtDate addNewDate();

    public void removeDate(int var1);

    public List<CTSdtDocPart> getDocPartObjList();

    @Deprecated
    public CTSdtDocPart[] getDocPartObjArray();

    public CTSdtDocPart getDocPartObjArray(int var1);

    public int sizeOfDocPartObjArray();

    public void setDocPartObjArray(CTSdtDocPart[] var1);

    public void setDocPartObjArray(int var1, CTSdtDocPart var2);

    public CTSdtDocPart insertNewDocPartObj(int var1);

    public CTSdtDocPart addNewDocPartObj();

    public void removeDocPartObj(int var1);

    public List<CTSdtDocPart> getDocPartListList();

    @Deprecated
    public CTSdtDocPart[] getDocPartListArray();

    public CTSdtDocPart getDocPartListArray(int var1);

    public int sizeOfDocPartListArray();

    public void setDocPartListArray(CTSdtDocPart[] var1);

    public void setDocPartListArray(int var1, CTSdtDocPart var2);

    public CTSdtDocPart insertNewDocPartList(int var1);

    public CTSdtDocPart addNewDocPartList();

    public void removeDocPartList(int var1);

    public List<CTSdtDropDownList> getDropDownListList();

    @Deprecated
    public CTSdtDropDownList[] getDropDownListArray();

    public CTSdtDropDownList getDropDownListArray(int var1);

    public int sizeOfDropDownListArray();

    public void setDropDownListArray(CTSdtDropDownList[] var1);

    public void setDropDownListArray(int var1, CTSdtDropDownList var2);

    public CTSdtDropDownList insertNewDropDownList(int var1);

    public CTSdtDropDownList addNewDropDownList();

    public void removeDropDownList(int var1);

    public List<CTEmpty> getPictureList();

    @Deprecated
    public CTEmpty[] getPictureArray();

    public CTEmpty getPictureArray(int var1);

    public int sizeOfPictureArray();

    public void setPictureArray(CTEmpty[] var1);

    public void setPictureArray(int var1, CTEmpty var2);

    public CTEmpty insertNewPicture(int var1);

    public CTEmpty addNewPicture();

    public void removePicture(int var1);

    public List<CTEmpty> getRichTextList();

    @Deprecated
    public CTEmpty[] getRichTextArray();

    public CTEmpty getRichTextArray(int var1);

    public int sizeOfRichTextArray();

    public void setRichTextArray(CTEmpty[] var1);

    public void setRichTextArray(int var1, CTEmpty var2);

    public CTEmpty insertNewRichText(int var1);

    public CTEmpty addNewRichText();

    public void removeRichText(int var1);

    public List<CTSdtText> getTextList();

    @Deprecated
    public CTSdtText[] getTextArray();

    public CTSdtText getTextArray(int var1);

    public int sizeOfTextArray();

    public void setTextArray(CTSdtText[] var1);

    public void setTextArray(int var1, CTSdtText var2);

    public CTSdtText insertNewText(int var1);

    public CTSdtText addNewText();

    public void removeText(int var1);

    public List<CTEmpty> getCitationList();

    @Deprecated
    public CTEmpty[] getCitationArray();

    public CTEmpty getCitationArray(int var1);

    public int sizeOfCitationArray();

    public void setCitationArray(CTEmpty[] var1);

    public void setCitationArray(int var1, CTEmpty var2);

    public CTEmpty insertNewCitation(int var1);

    public CTEmpty addNewCitation();

    public void removeCitation(int var1);

    public List<CTEmpty> getGroupList();

    @Deprecated
    public CTEmpty[] getGroupArray();

    public CTEmpty getGroupArray(int var1);

    public int sizeOfGroupArray();

    public void setGroupArray(CTEmpty[] var1);

    public void setGroupArray(int var1, CTEmpty var2);

    public CTEmpty insertNewGroup(int var1);

    public CTEmpty addNewGroup();

    public void removeGroup(int var1);

    public List<CTEmpty> getBibliographyList();

    @Deprecated
    public CTEmpty[] getBibliographyArray();

    public CTEmpty getBibliographyArray(int var1);

    public int sizeOfBibliographyArray();

    public void setBibliographyArray(CTEmpty[] var1);

    public void setBibliographyArray(int var1, CTEmpty var2);

    public CTEmpty insertNewBibliography(int var1);

    public CTEmpty addNewBibliography();

    public void removeBibliography(int var1);

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTSdtPr.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTSdtPr newInstance() {
            return (CTSdtPr)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTSdtPr newInstance(XmlOptions xmlOptions) {
            return (CTSdtPr)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTSdtPr parse(String string) throws XmlException {
            return (CTSdtPr)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTSdtPr parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTSdtPr)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTSdtPr parse(File file) throws XmlException, IOException {
            return (CTSdtPr)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTSdtPr parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTSdtPr)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTSdtPr parse(URL uRL) throws XmlException, IOException {
            return (CTSdtPr)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTSdtPr parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTSdtPr)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTSdtPr parse(InputStream inputStream) throws XmlException, IOException {
            return (CTSdtPr)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTSdtPr parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTSdtPr)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTSdtPr parse(Reader reader) throws XmlException, IOException {
            return (CTSdtPr)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTSdtPr parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTSdtPr)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTSdtPr parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTSdtPr)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTSdtPr parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTSdtPr)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTSdtPr parse(Node node) throws XmlException {
            return (CTSdtPr)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTSdtPr parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTSdtPr)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTSdtPr parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTSdtPr)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTSdtPr parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTSdtPr)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

