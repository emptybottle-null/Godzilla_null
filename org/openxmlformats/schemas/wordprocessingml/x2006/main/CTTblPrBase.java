/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.wordprocessingml.x2006.main.CTShortHexNumber
 *  org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblOverlap
 *  org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPPr
 */
package org.openxmlformats.schemas.wordprocessingml.x2006.main;

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
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDecimalNumber;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTJc;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTOnOff;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTShd;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTShortHexNumber;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTString;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblBorders;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblCellMar;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblLayoutType;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblOverlap;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblWidth;
import org.w3c.dom.Node;

public interface CTTblPrBase
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTTblPrBase.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("cttblprbaseeba1type");

    public CTString getTblStyle();

    public boolean isSetTblStyle();

    public void setTblStyle(CTString var1);

    public CTString addNewTblStyle();

    public void unsetTblStyle();

    public CTTblPPr getTblpPr();

    public boolean isSetTblpPr();

    public void setTblpPr(CTTblPPr var1);

    public CTTblPPr addNewTblpPr();

    public void unsetTblpPr();

    public CTTblOverlap getTblOverlap();

    public boolean isSetTblOverlap();

    public void setTblOverlap(CTTblOverlap var1);

    public CTTblOverlap addNewTblOverlap();

    public void unsetTblOverlap();

    public CTOnOff getBidiVisual();

    public boolean isSetBidiVisual();

    public void setBidiVisual(CTOnOff var1);

    public CTOnOff addNewBidiVisual();

    public void unsetBidiVisual();

    public CTDecimalNumber getTblStyleRowBandSize();

    public boolean isSetTblStyleRowBandSize();

    public void setTblStyleRowBandSize(CTDecimalNumber var1);

    public CTDecimalNumber addNewTblStyleRowBandSize();

    public void unsetTblStyleRowBandSize();

    public CTDecimalNumber getTblStyleColBandSize();

    public boolean isSetTblStyleColBandSize();

    public void setTblStyleColBandSize(CTDecimalNumber var1);

    public CTDecimalNumber addNewTblStyleColBandSize();

    public void unsetTblStyleColBandSize();

    public CTTblWidth getTblW();

    public boolean isSetTblW();

    public void setTblW(CTTblWidth var1);

    public CTTblWidth addNewTblW();

    public void unsetTblW();

    public CTJc getJc();

    public boolean isSetJc();

    public void setJc(CTJc var1);

    public CTJc addNewJc();

    public void unsetJc();

    public CTTblWidth getTblCellSpacing();

    public boolean isSetTblCellSpacing();

    public void setTblCellSpacing(CTTblWidth var1);

    public CTTblWidth addNewTblCellSpacing();

    public void unsetTblCellSpacing();

    public CTTblWidth getTblInd();

    public boolean isSetTblInd();

    public void setTblInd(CTTblWidth var1);

    public CTTblWidth addNewTblInd();

    public void unsetTblInd();

    public CTTblBorders getTblBorders();

    public boolean isSetTblBorders();

    public void setTblBorders(CTTblBorders var1);

    public CTTblBorders addNewTblBorders();

    public void unsetTblBorders();

    public CTShd getShd();

    public boolean isSetShd();

    public void setShd(CTShd var1);

    public CTShd addNewShd();

    public void unsetShd();

    public CTTblLayoutType getTblLayout();

    public boolean isSetTblLayout();

    public void setTblLayout(CTTblLayoutType var1);

    public CTTblLayoutType addNewTblLayout();

    public void unsetTblLayout();

    public CTTblCellMar getTblCellMar();

    public boolean isSetTblCellMar();

    public void setTblCellMar(CTTblCellMar var1);

    public CTTblCellMar addNewTblCellMar();

    public void unsetTblCellMar();

    public CTShortHexNumber getTblLook();

    public boolean isSetTblLook();

    public void setTblLook(CTShortHexNumber var1);

    public CTShortHexNumber addNewTblLook();

    public void unsetTblLook();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTTblPrBase.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTTblPrBase newInstance() {
            return (CTTblPrBase)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTTblPrBase newInstance(XmlOptions xmlOptions) {
            return (CTTblPrBase)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTTblPrBase parse(String string) throws XmlException {
            return (CTTblPrBase)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTTblPrBase parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTTblPrBase)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTTblPrBase parse(File file) throws XmlException, IOException {
            return (CTTblPrBase)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTTblPrBase parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTTblPrBase)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTTblPrBase parse(URL uRL) throws XmlException, IOException {
            return (CTTblPrBase)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTTblPrBase parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTTblPrBase)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTTblPrBase parse(InputStream inputStream) throws XmlException, IOException {
            return (CTTblPrBase)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTTblPrBase parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTTblPrBase)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTTblPrBase parse(Reader reader) throws XmlException, IOException {
            return (CTTblPrBase)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTTblPrBase parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTTblPrBase)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTTblPrBase parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTTblPrBase)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTTblPrBase parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTTblPrBase)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTTblPrBase parse(Node node) throws XmlException {
            return (CTTblPrBase)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTTblPrBase parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTTblPrBase)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTTblPrBase parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTTblPrBase)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTTblPrBase parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTTblPrBase)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

