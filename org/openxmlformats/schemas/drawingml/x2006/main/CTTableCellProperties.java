/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.drawingml.x2006.main.CTCell3D
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
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.openxmlformats.schemas.drawingml.x2006.main.CTBlipFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTCell3D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGradientFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGroupFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTLineProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTNoFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTOfficeArtExtensionList;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPatternFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTSolidColorFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.STCoordinate32;
import org.openxmlformats.schemas.drawingml.x2006.main.STTextAnchoringType;
import org.openxmlformats.schemas.drawingml.x2006.main.STTextHorzOverflowType;
import org.openxmlformats.schemas.drawingml.x2006.main.STTextVerticalType;
import org.w3c.dom.Node;

public interface CTTableCellProperties
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTTableCellProperties.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("cttablecellproperties1614type");

    public CTLineProperties getLnL();

    public boolean isSetLnL();

    public void setLnL(CTLineProperties var1);

    public CTLineProperties addNewLnL();

    public void unsetLnL();

    public CTLineProperties getLnR();

    public boolean isSetLnR();

    public void setLnR(CTLineProperties var1);

    public CTLineProperties addNewLnR();

    public void unsetLnR();

    public CTLineProperties getLnT();

    public boolean isSetLnT();

    public void setLnT(CTLineProperties var1);

    public CTLineProperties addNewLnT();

    public void unsetLnT();

    public CTLineProperties getLnB();

    public boolean isSetLnB();

    public void setLnB(CTLineProperties var1);

    public CTLineProperties addNewLnB();

    public void unsetLnB();

    public CTLineProperties getLnTlToBr();

    public boolean isSetLnTlToBr();

    public void setLnTlToBr(CTLineProperties var1);

    public CTLineProperties addNewLnTlToBr();

    public void unsetLnTlToBr();

    public CTLineProperties getLnBlToTr();

    public boolean isSetLnBlToTr();

    public void setLnBlToTr(CTLineProperties var1);

    public CTLineProperties addNewLnBlToTr();

    public void unsetLnBlToTr();

    public CTCell3D getCell3D();

    public boolean isSetCell3D();

    public void setCell3D(CTCell3D var1);

    public CTCell3D addNewCell3D();

    public void unsetCell3D();

    public CTNoFillProperties getNoFill();

    public boolean isSetNoFill();

    public void setNoFill(CTNoFillProperties var1);

    public CTNoFillProperties addNewNoFill();

    public void unsetNoFill();

    public CTSolidColorFillProperties getSolidFill();

    public boolean isSetSolidFill();

    public void setSolidFill(CTSolidColorFillProperties var1);

    public CTSolidColorFillProperties addNewSolidFill();

    public void unsetSolidFill();

    public CTGradientFillProperties getGradFill();

    public boolean isSetGradFill();

    public void setGradFill(CTGradientFillProperties var1);

    public CTGradientFillProperties addNewGradFill();

    public void unsetGradFill();

    public CTBlipFillProperties getBlipFill();

    public boolean isSetBlipFill();

    public void setBlipFill(CTBlipFillProperties var1);

    public CTBlipFillProperties addNewBlipFill();

    public void unsetBlipFill();

    public CTPatternFillProperties getPattFill();

    public boolean isSetPattFill();

    public void setPattFill(CTPatternFillProperties var1);

    public CTPatternFillProperties addNewPattFill();

    public void unsetPattFill();

    public CTGroupFillProperties getGrpFill();

    public boolean isSetGrpFill();

    public void setGrpFill(CTGroupFillProperties var1);

    public CTGroupFillProperties addNewGrpFill();

    public void unsetGrpFill();

    public CTOfficeArtExtensionList getExtLst();

    public boolean isSetExtLst();

    public void setExtLst(CTOfficeArtExtensionList var1);

    public CTOfficeArtExtensionList addNewExtLst();

    public void unsetExtLst();

    public int getMarL();

    public STCoordinate32 xgetMarL();

    public boolean isSetMarL();

    public void setMarL(int var1);

    public void xsetMarL(STCoordinate32 var1);

    public void unsetMarL();

    public int getMarR();

    public STCoordinate32 xgetMarR();

    public boolean isSetMarR();

    public void setMarR(int var1);

    public void xsetMarR(STCoordinate32 var1);

    public void unsetMarR();

    public int getMarT();

    public STCoordinate32 xgetMarT();

    public boolean isSetMarT();

    public void setMarT(int var1);

    public void xsetMarT(STCoordinate32 var1);

    public void unsetMarT();

    public int getMarB();

    public STCoordinate32 xgetMarB();

    public boolean isSetMarB();

    public void setMarB(int var1);

    public void xsetMarB(STCoordinate32 var1);

    public void unsetMarB();

    public STTextVerticalType.Enum getVert();

    public STTextVerticalType xgetVert();

    public boolean isSetVert();

    public void setVert(STTextVerticalType.Enum var1);

    public void xsetVert(STTextVerticalType var1);

    public void unsetVert();

    public STTextAnchoringType.Enum getAnchor();

    public STTextAnchoringType xgetAnchor();

    public boolean isSetAnchor();

    public void setAnchor(STTextAnchoringType.Enum var1);

    public void xsetAnchor(STTextAnchoringType var1);

    public void unsetAnchor();

    public boolean getAnchorCtr();

    public XmlBoolean xgetAnchorCtr();

    public boolean isSetAnchorCtr();

    public void setAnchorCtr(boolean var1);

    public void xsetAnchorCtr(XmlBoolean var1);

    public void unsetAnchorCtr();

    public STTextHorzOverflowType.Enum getHorzOverflow();

    public STTextHorzOverflowType xgetHorzOverflow();

    public boolean isSetHorzOverflow();

    public void setHorzOverflow(STTextHorzOverflowType.Enum var1);

    public void xsetHorzOverflow(STTextHorzOverflowType var1);

    public void unsetHorzOverflow();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTTableCellProperties.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTTableCellProperties newInstance() {
            return (CTTableCellProperties)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTTableCellProperties newInstance(XmlOptions xmlOptions) {
            return (CTTableCellProperties)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTTableCellProperties parse(String string) throws XmlException {
            return (CTTableCellProperties)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTTableCellProperties parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTTableCellProperties)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTTableCellProperties parse(File file) throws XmlException, IOException {
            return (CTTableCellProperties)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTTableCellProperties parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTTableCellProperties)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTTableCellProperties parse(URL uRL) throws XmlException, IOException {
            return (CTTableCellProperties)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTTableCellProperties parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTTableCellProperties)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTTableCellProperties parse(InputStream inputStream) throws XmlException, IOException {
            return (CTTableCellProperties)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTTableCellProperties parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTTableCellProperties)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTTableCellProperties parse(Reader reader) throws XmlException, IOException {
            return (CTTableCellProperties)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTTableCellProperties parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTTableCellProperties)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTTableCellProperties parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTTableCellProperties)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTTableCellProperties parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTTableCellProperties)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTTableCellProperties parse(Node node) throws XmlException {
            return (CTTableCellProperties)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTTableCellProperties parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTTableCellProperties)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTTableCellProperties parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTTableCellProperties)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTTableCellProperties parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTTableCellProperties)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

