/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.drawingml.x2006.main.CTTableBackgroundStyle
 */
package org.openxmlformats.schemas.drawingml.x2006.main;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.lang.ref.SoftReference;
import java.net.URL;
import javax.xml.stream.XMLStreamReader;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SchemaTypeLoader;
import org.apache.xmlbeans.XmlBeans;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.openxmlformats.schemas.drawingml.x2006.main.CTOfficeArtExtensionList;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTableBackgroundStyle;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTablePartStyle;
import org.openxmlformats.schemas.drawingml.x2006.main.STGuid;
import org.w3c.dom.Node;

public interface CTTableStyle
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTTableStyle.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("cttablestyled59etype");

    public CTTableBackgroundStyle getTblBg();

    public boolean isSetTblBg();

    public void setTblBg(CTTableBackgroundStyle var1);

    public CTTableBackgroundStyle addNewTblBg();

    public void unsetTblBg();

    public CTTablePartStyle getWholeTbl();

    public boolean isSetWholeTbl();

    public void setWholeTbl(CTTablePartStyle var1);

    public CTTablePartStyle addNewWholeTbl();

    public void unsetWholeTbl();

    public CTTablePartStyle getBand1H();

    public boolean isSetBand1H();

    public void setBand1H(CTTablePartStyle var1);

    public CTTablePartStyle addNewBand1H();

    public void unsetBand1H();

    public CTTablePartStyle getBand2H();

    public boolean isSetBand2H();

    public void setBand2H(CTTablePartStyle var1);

    public CTTablePartStyle addNewBand2H();

    public void unsetBand2H();

    public CTTablePartStyle getBand1V();

    public boolean isSetBand1V();

    public void setBand1V(CTTablePartStyle var1);

    public CTTablePartStyle addNewBand1V();

    public void unsetBand1V();

    public CTTablePartStyle getBand2V();

    public boolean isSetBand2V();

    public void setBand2V(CTTablePartStyle var1);

    public CTTablePartStyle addNewBand2V();

    public void unsetBand2V();

    public CTTablePartStyle getLastCol();

    public boolean isSetLastCol();

    public void setLastCol(CTTablePartStyle var1);

    public CTTablePartStyle addNewLastCol();

    public void unsetLastCol();

    public CTTablePartStyle getFirstCol();

    public boolean isSetFirstCol();

    public void setFirstCol(CTTablePartStyle var1);

    public CTTablePartStyle addNewFirstCol();

    public void unsetFirstCol();

    public CTTablePartStyle getLastRow();

    public boolean isSetLastRow();

    public void setLastRow(CTTablePartStyle var1);

    public CTTablePartStyle addNewLastRow();

    public void unsetLastRow();

    public CTTablePartStyle getSeCell();

    public boolean isSetSeCell();

    public void setSeCell(CTTablePartStyle var1);

    public CTTablePartStyle addNewSeCell();

    public void unsetSeCell();

    public CTTablePartStyle getSwCell();

    public boolean isSetSwCell();

    public void setSwCell(CTTablePartStyle var1);

    public CTTablePartStyle addNewSwCell();

    public void unsetSwCell();

    public CTTablePartStyle getFirstRow();

    public boolean isSetFirstRow();

    public void setFirstRow(CTTablePartStyle var1);

    public CTTablePartStyle addNewFirstRow();

    public void unsetFirstRow();

    public CTTablePartStyle getNeCell();

    public boolean isSetNeCell();

    public void setNeCell(CTTablePartStyle var1);

    public CTTablePartStyle addNewNeCell();

    public void unsetNeCell();

    public CTTablePartStyle getNwCell();

    public boolean isSetNwCell();

    public void setNwCell(CTTablePartStyle var1);

    public CTTablePartStyle addNewNwCell();

    public void unsetNwCell();

    public CTOfficeArtExtensionList getExtLst();

    public boolean isSetExtLst();

    public void setExtLst(CTOfficeArtExtensionList var1);

    public CTOfficeArtExtensionList addNewExtLst();

    public void unsetExtLst();

    public String getStyleId();

    public STGuid xgetStyleId();

    public void setStyleId(String var1);

    public void xsetStyleId(STGuid var1);

    public String getStyleName();

    public XmlString xgetStyleName();

    public void setStyleName(String var1);

    public void xsetStyleName(XmlString var1);

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTTableStyle.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTTableStyle newInstance() {
            return (CTTableStyle)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTTableStyle newInstance(XmlOptions xmlOptions) {
            return (CTTableStyle)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTTableStyle parse(String string) throws XmlException {
            return (CTTableStyle)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTTableStyle parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTTableStyle)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTTableStyle parse(File file) throws XmlException, IOException {
            return (CTTableStyle)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTTableStyle parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTTableStyle)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTTableStyle parse(URL uRL) throws XmlException, IOException {
            return (CTTableStyle)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTTableStyle parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTTableStyle)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTTableStyle parse(InputStream inputStream) throws XmlException, IOException {
            return (CTTableStyle)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTTableStyle parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTTableStyle)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTTableStyle parse(Reader reader) throws XmlException, IOException {
            return (CTTableStyle)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTTableStyle parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTTableStyle)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTTableStyle parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTTableStyle)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTTableStyle parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTTableStyle)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTTableStyle parse(Node node) throws XmlException {
            return (CTTableStyle)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTTableStyle parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTTableStyle)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTTableStyle parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTTableStyle)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTTableStyle parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTTableStyle)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

