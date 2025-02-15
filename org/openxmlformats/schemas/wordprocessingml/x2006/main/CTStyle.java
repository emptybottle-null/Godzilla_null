/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLongHexNumber
 *  org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblStylePr
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
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDecimalNumber;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLongHexNumber;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTOnOff;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTString;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPrBase;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblStylePr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STOnOff;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STString;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STStyleType;
import org.w3c.dom.Node;

public interface CTStyle
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTStyle.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctstyle41c1type");

    public CTString getName();

    public boolean isSetName();

    public void setName(CTString var1);

    public CTString addNewName();

    public void unsetName();

    public CTString getAliases();

    public boolean isSetAliases();

    public void setAliases(CTString var1);

    public CTString addNewAliases();

    public void unsetAliases();

    public CTString getBasedOn();

    public boolean isSetBasedOn();

    public void setBasedOn(CTString var1);

    public CTString addNewBasedOn();

    public void unsetBasedOn();

    public CTString getNext();

    public boolean isSetNext();

    public void setNext(CTString var1);

    public CTString addNewNext();

    public void unsetNext();

    public CTString getLink();

    public boolean isSetLink();

    public void setLink(CTString var1);

    public CTString addNewLink();

    public void unsetLink();

    public CTOnOff getAutoRedefine();

    public boolean isSetAutoRedefine();

    public void setAutoRedefine(CTOnOff var1);

    public CTOnOff addNewAutoRedefine();

    public void unsetAutoRedefine();

    public CTOnOff getHidden();

    public boolean isSetHidden();

    public void setHidden(CTOnOff var1);

    public CTOnOff addNewHidden();

    public void unsetHidden();

    public CTDecimalNumber getUiPriority();

    public boolean isSetUiPriority();

    public void setUiPriority(CTDecimalNumber var1);

    public CTDecimalNumber addNewUiPriority();

    public void unsetUiPriority();

    public CTOnOff getSemiHidden();

    public boolean isSetSemiHidden();

    public void setSemiHidden(CTOnOff var1);

    public CTOnOff addNewSemiHidden();

    public void unsetSemiHidden();

    public CTOnOff getUnhideWhenUsed();

    public boolean isSetUnhideWhenUsed();

    public void setUnhideWhenUsed(CTOnOff var1);

    public CTOnOff addNewUnhideWhenUsed();

    public void unsetUnhideWhenUsed();

    public CTOnOff getQFormat();

    public boolean isSetQFormat();

    public void setQFormat(CTOnOff var1);

    public CTOnOff addNewQFormat();

    public void unsetQFormat();

    public CTOnOff getLocked();

    public boolean isSetLocked();

    public void setLocked(CTOnOff var1);

    public CTOnOff addNewLocked();

    public void unsetLocked();

    public CTOnOff getPersonal();

    public boolean isSetPersonal();

    public void setPersonal(CTOnOff var1);

    public CTOnOff addNewPersonal();

    public void unsetPersonal();

    public CTOnOff getPersonalCompose();

    public boolean isSetPersonalCompose();

    public void setPersonalCompose(CTOnOff var1);

    public CTOnOff addNewPersonalCompose();

    public void unsetPersonalCompose();

    public CTOnOff getPersonalReply();

    public boolean isSetPersonalReply();

    public void setPersonalReply(CTOnOff var1);

    public CTOnOff addNewPersonalReply();

    public void unsetPersonalReply();

    public CTLongHexNumber getRsid();

    public boolean isSetRsid();

    public void setRsid(CTLongHexNumber var1);

    public CTLongHexNumber addNewRsid();

    public void unsetRsid();

    public CTPPr getPPr();

    public boolean isSetPPr();

    public void setPPr(CTPPr var1);

    public CTPPr addNewPPr();

    public void unsetPPr();

    public CTRPr getRPr();

    public boolean isSetRPr();

    public void setRPr(CTRPr var1);

    public CTRPr addNewRPr();

    public void unsetRPr();

    public CTTblPrBase getTblPr();

    public boolean isSetTblPr();

    public void setTblPr(CTTblPrBase var1);

    public CTTblPrBase addNewTblPr();

    public void unsetTblPr();

    public CTTrPr getTrPr();

    public boolean isSetTrPr();

    public void setTrPr(CTTrPr var1);

    public CTTrPr addNewTrPr();

    public void unsetTrPr();

    public CTTcPr getTcPr();

    public boolean isSetTcPr();

    public void setTcPr(CTTcPr var1);

    public CTTcPr addNewTcPr();

    public void unsetTcPr();

    public List<CTTblStylePr> getTblStylePrList();

    @Deprecated
    public CTTblStylePr[] getTblStylePrArray();

    public CTTblStylePr getTblStylePrArray(int var1);

    public int sizeOfTblStylePrArray();

    public void setTblStylePrArray(CTTblStylePr[] var1);

    public void setTblStylePrArray(int var1, CTTblStylePr var2);

    public CTTblStylePr insertNewTblStylePr(int var1);

    public CTTblStylePr addNewTblStylePr();

    public void removeTblStylePr(int var1);

    public STStyleType.Enum getType();

    public STStyleType xgetType();

    public boolean isSetType();

    public void setType(STStyleType.Enum var1);

    public void xsetType(STStyleType var1);

    public void unsetType();

    public String getStyleId();

    public STString xgetStyleId();

    public boolean isSetStyleId();

    public void setStyleId(String var1);

    public void xsetStyleId(STString var1);

    public void unsetStyleId();

    public STOnOff.Enum getDefault();

    public STOnOff xgetDefault();

    public boolean isSetDefault();

    public void setDefault(STOnOff.Enum var1);

    public void xsetDefault(STOnOff var1);

    public void unsetDefault();

    public STOnOff.Enum getCustomStyle();

    public STOnOff xgetCustomStyle();

    public boolean isSetCustomStyle();

    public void setCustomStyle(STOnOff.Enum var1);

    public void xsetCustomStyle(STOnOff var1);

    public void unsetCustomStyle();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTStyle.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTStyle newInstance() {
            return (CTStyle)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTStyle newInstance(XmlOptions xmlOptions) {
            return (CTStyle)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTStyle parse(String string) throws XmlException {
            return (CTStyle)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTStyle parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTStyle)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTStyle parse(File file) throws XmlException, IOException {
            return (CTStyle)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTStyle parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTStyle)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTStyle parse(URL uRL) throws XmlException, IOException {
            return (CTStyle)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTStyle parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTStyle)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTStyle parse(InputStream inputStream) throws XmlException, IOException {
            return (CTStyle)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTStyle parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTStyle)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTStyle parse(Reader reader) throws XmlException, IOException {
            return (CTStyle)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTStyle parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTStyle)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTStyle parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTStyle)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTStyle parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTStyle)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTStyle parse(Node node) throws XmlException {
            return (CTStyle)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTStyle parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTStyle)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTStyle parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTStyle)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTStyle parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTStyle)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

