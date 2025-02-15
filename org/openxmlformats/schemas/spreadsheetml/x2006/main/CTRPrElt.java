/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.spreadsheetml.x2006.main;

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
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTBooleanProperty;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTColor;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFontName;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFontScheme;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFontSize;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTIntProperty;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTUnderlineProperty;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTVerticalAlignFontProperty;
import org.w3c.dom.Node;

public interface CTRPrElt
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTRPrElt.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctrpreltecc2type");

    public List<CTFontName> getRFontList();

    @Deprecated
    public CTFontName[] getRFontArray();

    public CTFontName getRFontArray(int var1);

    public int sizeOfRFontArray();

    public void setRFontArray(CTFontName[] var1);

    public void setRFontArray(int var1, CTFontName var2);

    public CTFontName insertNewRFont(int var1);

    public CTFontName addNewRFont();

    public void removeRFont(int var1);

    public List<CTIntProperty> getCharsetList();

    @Deprecated
    public CTIntProperty[] getCharsetArray();

    public CTIntProperty getCharsetArray(int var1);

    public int sizeOfCharsetArray();

    public void setCharsetArray(CTIntProperty[] var1);

    public void setCharsetArray(int var1, CTIntProperty var2);

    public CTIntProperty insertNewCharset(int var1);

    public CTIntProperty addNewCharset();

    public void removeCharset(int var1);

    public List<CTIntProperty> getFamilyList();

    @Deprecated
    public CTIntProperty[] getFamilyArray();

    public CTIntProperty getFamilyArray(int var1);

    public int sizeOfFamilyArray();

    public void setFamilyArray(CTIntProperty[] var1);

    public void setFamilyArray(int var1, CTIntProperty var2);

    public CTIntProperty insertNewFamily(int var1);

    public CTIntProperty addNewFamily();

    public void removeFamily(int var1);

    public List<CTBooleanProperty> getBList();

    @Deprecated
    public CTBooleanProperty[] getBArray();

    public CTBooleanProperty getBArray(int var1);

    public int sizeOfBArray();

    public void setBArray(CTBooleanProperty[] var1);

    public void setBArray(int var1, CTBooleanProperty var2);

    public CTBooleanProperty insertNewB(int var1);

    public CTBooleanProperty addNewB();

    public void removeB(int var1);

    public List<CTBooleanProperty> getIList();

    @Deprecated
    public CTBooleanProperty[] getIArray();

    public CTBooleanProperty getIArray(int var1);

    public int sizeOfIArray();

    public void setIArray(CTBooleanProperty[] var1);

    public void setIArray(int var1, CTBooleanProperty var2);

    public CTBooleanProperty insertNewI(int var1);

    public CTBooleanProperty addNewI();

    public void removeI(int var1);

    public List<CTBooleanProperty> getStrikeList();

    @Deprecated
    public CTBooleanProperty[] getStrikeArray();

    public CTBooleanProperty getStrikeArray(int var1);

    public int sizeOfStrikeArray();

    public void setStrikeArray(CTBooleanProperty[] var1);

    public void setStrikeArray(int var1, CTBooleanProperty var2);

    public CTBooleanProperty insertNewStrike(int var1);

    public CTBooleanProperty addNewStrike();

    public void removeStrike(int var1);

    public List<CTBooleanProperty> getOutlineList();

    @Deprecated
    public CTBooleanProperty[] getOutlineArray();

    public CTBooleanProperty getOutlineArray(int var1);

    public int sizeOfOutlineArray();

    public void setOutlineArray(CTBooleanProperty[] var1);

    public void setOutlineArray(int var1, CTBooleanProperty var2);

    public CTBooleanProperty insertNewOutline(int var1);

    public CTBooleanProperty addNewOutline();

    public void removeOutline(int var1);

    public List<CTBooleanProperty> getShadowList();

    @Deprecated
    public CTBooleanProperty[] getShadowArray();

    public CTBooleanProperty getShadowArray(int var1);

    public int sizeOfShadowArray();

    public void setShadowArray(CTBooleanProperty[] var1);

    public void setShadowArray(int var1, CTBooleanProperty var2);

    public CTBooleanProperty insertNewShadow(int var1);

    public CTBooleanProperty addNewShadow();

    public void removeShadow(int var1);

    public List<CTBooleanProperty> getCondenseList();

    @Deprecated
    public CTBooleanProperty[] getCondenseArray();

    public CTBooleanProperty getCondenseArray(int var1);

    public int sizeOfCondenseArray();

    public void setCondenseArray(CTBooleanProperty[] var1);

    public void setCondenseArray(int var1, CTBooleanProperty var2);

    public CTBooleanProperty insertNewCondense(int var1);

    public CTBooleanProperty addNewCondense();

    public void removeCondense(int var1);

    public List<CTBooleanProperty> getExtendList();

    @Deprecated
    public CTBooleanProperty[] getExtendArray();

    public CTBooleanProperty getExtendArray(int var1);

    public int sizeOfExtendArray();

    public void setExtendArray(CTBooleanProperty[] var1);

    public void setExtendArray(int var1, CTBooleanProperty var2);

    public CTBooleanProperty insertNewExtend(int var1);

    public CTBooleanProperty addNewExtend();

    public void removeExtend(int var1);

    public List<CTColor> getColorList();

    @Deprecated
    public CTColor[] getColorArray();

    public CTColor getColorArray(int var1);

    public int sizeOfColorArray();

    public void setColorArray(CTColor[] var1);

    public void setColorArray(int var1, CTColor var2);

    public CTColor insertNewColor(int var1);

    public CTColor addNewColor();

    public void removeColor(int var1);

    public List<CTFontSize> getSzList();

    @Deprecated
    public CTFontSize[] getSzArray();

    public CTFontSize getSzArray(int var1);

    public int sizeOfSzArray();

    public void setSzArray(CTFontSize[] var1);

    public void setSzArray(int var1, CTFontSize var2);

    public CTFontSize insertNewSz(int var1);

    public CTFontSize addNewSz();

    public void removeSz(int var1);

    public List<CTUnderlineProperty> getUList();

    @Deprecated
    public CTUnderlineProperty[] getUArray();

    public CTUnderlineProperty getUArray(int var1);

    public int sizeOfUArray();

    public void setUArray(CTUnderlineProperty[] var1);

    public void setUArray(int var1, CTUnderlineProperty var2);

    public CTUnderlineProperty insertNewU(int var1);

    public CTUnderlineProperty addNewU();

    public void removeU(int var1);

    public List<CTVerticalAlignFontProperty> getVertAlignList();

    @Deprecated
    public CTVerticalAlignFontProperty[] getVertAlignArray();

    public CTVerticalAlignFontProperty getVertAlignArray(int var1);

    public int sizeOfVertAlignArray();

    public void setVertAlignArray(CTVerticalAlignFontProperty[] var1);

    public void setVertAlignArray(int var1, CTVerticalAlignFontProperty var2);

    public CTVerticalAlignFontProperty insertNewVertAlign(int var1);

    public CTVerticalAlignFontProperty addNewVertAlign();

    public void removeVertAlign(int var1);

    public List<CTFontScheme> getSchemeList();

    @Deprecated
    public CTFontScheme[] getSchemeArray();

    public CTFontScheme getSchemeArray(int var1);

    public int sizeOfSchemeArray();

    public void setSchemeArray(CTFontScheme[] var1);

    public void setSchemeArray(int var1, CTFontScheme var2);

    public CTFontScheme insertNewScheme(int var1);

    public CTFontScheme addNewScheme();

    public void removeScheme(int var1);

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTRPrElt.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTRPrElt newInstance() {
            return (CTRPrElt)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTRPrElt newInstance(XmlOptions xmlOptions) {
            return (CTRPrElt)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTRPrElt parse(String string) throws XmlException {
            return (CTRPrElt)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTRPrElt parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTRPrElt)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTRPrElt parse(File file) throws XmlException, IOException {
            return (CTRPrElt)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTRPrElt parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTRPrElt)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTRPrElt parse(URL uRL) throws XmlException, IOException {
            return (CTRPrElt)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTRPrElt parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTRPrElt)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTRPrElt parse(InputStream inputStream) throws XmlException, IOException {
            return (CTRPrElt)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTRPrElt parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTRPrElt)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTRPrElt parse(Reader reader) throws XmlException, IOException {
            return (CTRPrElt)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTRPrElt parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTRPrElt)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTRPrElt parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTRPrElt)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTRPrElt parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTRPrElt)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTRPrElt parse(Node node) throws XmlException {
            return (CTRPrElt)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTRPrElt parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTRPrElt)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTRPrElt parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTRPrElt)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTRPrElt parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTRPrElt)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

