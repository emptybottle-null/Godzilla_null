/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLevelSuffix
 *  org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLvlLegacy
 */
package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.lang.ref.SoftReference;
import java.math.BigInteger;
import java.net.URL;
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
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTJc;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLevelSuffix;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLevelText;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLvlLegacy;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTNumFmt;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTOnOff;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTString;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STDecimalNumber;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STLongHexNumber;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STOnOff;
import org.w3c.dom.Node;

public interface CTLvl
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTLvl.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctlvlf630type");

    public CTDecimalNumber getStart();

    public boolean isSetStart();

    public void setStart(CTDecimalNumber var1);

    public CTDecimalNumber addNewStart();

    public void unsetStart();

    public CTNumFmt getNumFmt();

    public boolean isSetNumFmt();

    public void setNumFmt(CTNumFmt var1);

    public CTNumFmt addNewNumFmt();

    public void unsetNumFmt();

    public CTDecimalNumber getLvlRestart();

    public boolean isSetLvlRestart();

    public void setLvlRestart(CTDecimalNumber var1);

    public CTDecimalNumber addNewLvlRestart();

    public void unsetLvlRestart();

    public CTString getPStyle();

    public boolean isSetPStyle();

    public void setPStyle(CTString var1);

    public CTString addNewPStyle();

    public void unsetPStyle();

    public CTOnOff getIsLgl();

    public boolean isSetIsLgl();

    public void setIsLgl(CTOnOff var1);

    public CTOnOff addNewIsLgl();

    public void unsetIsLgl();

    public CTLevelSuffix getSuff();

    public boolean isSetSuff();

    public void setSuff(CTLevelSuffix var1);

    public CTLevelSuffix addNewSuff();

    public void unsetSuff();

    public CTLevelText getLvlText();

    public boolean isSetLvlText();

    public void setLvlText(CTLevelText var1);

    public CTLevelText addNewLvlText();

    public void unsetLvlText();

    public CTDecimalNumber getLvlPicBulletId();

    public boolean isSetLvlPicBulletId();

    public void setLvlPicBulletId(CTDecimalNumber var1);

    public CTDecimalNumber addNewLvlPicBulletId();

    public void unsetLvlPicBulletId();

    public CTLvlLegacy getLegacy();

    public boolean isSetLegacy();

    public void setLegacy(CTLvlLegacy var1);

    public CTLvlLegacy addNewLegacy();

    public void unsetLegacy();

    public CTJc getLvlJc();

    public boolean isSetLvlJc();

    public void setLvlJc(CTJc var1);

    public CTJc addNewLvlJc();

    public void unsetLvlJc();

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

    public BigInteger getIlvl();

    public STDecimalNumber xgetIlvl();

    public void setIlvl(BigInteger var1);

    public void xsetIlvl(STDecimalNumber var1);

    public byte[] getTplc();

    public STLongHexNumber xgetTplc();

    public boolean isSetTplc();

    public void setTplc(byte[] var1);

    public void xsetTplc(STLongHexNumber var1);

    public void unsetTplc();

    public STOnOff.Enum getTentative();

    public STOnOff xgetTentative();

    public boolean isSetTentative();

    public void setTentative(STOnOff.Enum var1);

    public void xsetTentative(STOnOff var1);

    public void unsetTentative();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTLvl.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTLvl newInstance() {
            return (CTLvl)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTLvl newInstance(XmlOptions xmlOptions) {
            return (CTLvl)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTLvl parse(String string) throws XmlException {
            return (CTLvl)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTLvl parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTLvl)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTLvl parse(File file) throws XmlException, IOException {
            return (CTLvl)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTLvl parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTLvl)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTLvl parse(URL uRL) throws XmlException, IOException {
            return (CTLvl)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTLvl parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTLvl)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTLvl parse(InputStream inputStream) throws XmlException, IOException {
            return (CTLvl)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTLvl parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTLvl)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTLvl parse(Reader reader) throws XmlException, IOException {
            return (CTLvl)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTLvl parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTLvl)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTLvl parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTLvl)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTLvl parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTLvl)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTLvl parse(Node node) throws XmlException {
            return (CTLvl)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTLvl parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTLvl)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTLvl parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTLvl)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTLvl parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTLvl)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

