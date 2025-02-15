/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
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
import org.openxmlformats.schemas.drawingml.x2006.main.CTOfficeArtExtensionList;
import org.w3c.dom.Node;

public interface CTPictureLocking
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTPictureLocking.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctpicturelockinga414type");

    public CTOfficeArtExtensionList getExtLst();

    public boolean isSetExtLst();

    public void setExtLst(CTOfficeArtExtensionList var1);

    public CTOfficeArtExtensionList addNewExtLst();

    public void unsetExtLst();

    public boolean getNoGrp();

    public XmlBoolean xgetNoGrp();

    public boolean isSetNoGrp();

    public void setNoGrp(boolean var1);

    public void xsetNoGrp(XmlBoolean var1);

    public void unsetNoGrp();

    public boolean getNoSelect();

    public XmlBoolean xgetNoSelect();

    public boolean isSetNoSelect();

    public void setNoSelect(boolean var1);

    public void xsetNoSelect(XmlBoolean var1);

    public void unsetNoSelect();

    public boolean getNoRot();

    public XmlBoolean xgetNoRot();

    public boolean isSetNoRot();

    public void setNoRot(boolean var1);

    public void xsetNoRot(XmlBoolean var1);

    public void unsetNoRot();

    public boolean getNoChangeAspect();

    public XmlBoolean xgetNoChangeAspect();

    public boolean isSetNoChangeAspect();

    public void setNoChangeAspect(boolean var1);

    public void xsetNoChangeAspect(XmlBoolean var1);

    public void unsetNoChangeAspect();

    public boolean getNoMove();

    public XmlBoolean xgetNoMove();

    public boolean isSetNoMove();

    public void setNoMove(boolean var1);

    public void xsetNoMove(XmlBoolean var1);

    public void unsetNoMove();

    public boolean getNoResize();

    public XmlBoolean xgetNoResize();

    public boolean isSetNoResize();

    public void setNoResize(boolean var1);

    public void xsetNoResize(XmlBoolean var1);

    public void unsetNoResize();

    public boolean getNoEditPoints();

    public XmlBoolean xgetNoEditPoints();

    public boolean isSetNoEditPoints();

    public void setNoEditPoints(boolean var1);

    public void xsetNoEditPoints(XmlBoolean var1);

    public void unsetNoEditPoints();

    public boolean getNoAdjustHandles();

    public XmlBoolean xgetNoAdjustHandles();

    public boolean isSetNoAdjustHandles();

    public void setNoAdjustHandles(boolean var1);

    public void xsetNoAdjustHandles(XmlBoolean var1);

    public void unsetNoAdjustHandles();

    public boolean getNoChangeArrowheads();

    public XmlBoolean xgetNoChangeArrowheads();

    public boolean isSetNoChangeArrowheads();

    public void setNoChangeArrowheads(boolean var1);

    public void xsetNoChangeArrowheads(XmlBoolean var1);

    public void unsetNoChangeArrowheads();

    public boolean getNoChangeShapeType();

    public XmlBoolean xgetNoChangeShapeType();

    public boolean isSetNoChangeShapeType();

    public void setNoChangeShapeType(boolean var1);

    public void xsetNoChangeShapeType(XmlBoolean var1);

    public void unsetNoChangeShapeType();

    public boolean getNoCrop();

    public XmlBoolean xgetNoCrop();

    public boolean isSetNoCrop();

    public void setNoCrop(boolean var1);

    public void xsetNoCrop(XmlBoolean var1);

    public void unsetNoCrop();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTPictureLocking.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTPictureLocking newInstance() {
            return (CTPictureLocking)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTPictureLocking newInstance(XmlOptions xmlOptions) {
            return (CTPictureLocking)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTPictureLocking parse(String string) throws XmlException {
            return (CTPictureLocking)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTPictureLocking parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTPictureLocking)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTPictureLocking parse(File file) throws XmlException, IOException {
            return (CTPictureLocking)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTPictureLocking parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTPictureLocking)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTPictureLocking parse(URL uRL) throws XmlException, IOException {
            return (CTPictureLocking)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTPictureLocking parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTPictureLocking)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTPictureLocking parse(InputStream inputStream) throws XmlException, IOException {
            return (CTPictureLocking)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTPictureLocking parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTPictureLocking)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTPictureLocking parse(Reader reader) throws XmlException, IOException {
            return (CTPictureLocking)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTPictureLocking parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTPictureLocking)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTPictureLocking parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTPictureLocking)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTPictureLocking parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTPictureLocking)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTPictureLocking parse(Node node) throws XmlException {
            return (CTPictureLocking)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTPictureLocking parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTPictureLocking)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTPictureLocking parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTPictureLocking)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTPictureLocking parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTPictureLocking)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

