/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  com.microsoft.schemas.office.office.STTrueFalse
 */
package com.microsoft.schemas.vml;

import com.microsoft.schemas.office.office.STConnectType;
import com.microsoft.schemas.office.office.STTrueFalse;
import com.microsoft.schemas.vml.STTrueFalse;
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
import org.w3c.dom.Node;

public interface CTPath
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTPath.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctpath5963type");

    public String getId();

    public XmlString xgetId();

    public boolean isSetId();

    public void setId(String var1);

    public void xsetId(XmlString var1);

    public void unsetId();

    public String getV();

    public XmlString xgetV();

    public boolean isSetV();

    public void setV(String var1);

    public void xsetV(XmlString var1);

    public void unsetV();

    public String getLimo();

    public XmlString xgetLimo();

    public boolean isSetLimo();

    public void setLimo(String var1);

    public void xsetLimo(XmlString var1);

    public void unsetLimo();

    public String getTextboxrect();

    public XmlString xgetTextboxrect();

    public boolean isSetTextboxrect();

    public void setTextboxrect(String var1);

    public void xsetTextboxrect(XmlString var1);

    public void unsetTextboxrect();

    public STTrueFalse.Enum getFillok();

    public STTrueFalse xgetFillok();

    public boolean isSetFillok();

    public void setFillok(STTrueFalse.Enum var1);

    public void xsetFillok(STTrueFalse var1);

    public void unsetFillok();

    public STTrueFalse.Enum getStrokeok();

    public STTrueFalse xgetStrokeok();

    public boolean isSetStrokeok();

    public void setStrokeok(STTrueFalse.Enum var1);

    public void xsetStrokeok(STTrueFalse var1);

    public void unsetStrokeok();

    public STTrueFalse.Enum getShadowok();

    public STTrueFalse xgetShadowok();

    public boolean isSetShadowok();

    public void setShadowok(STTrueFalse.Enum var1);

    public void xsetShadowok(STTrueFalse var1);

    public void unsetShadowok();

    public STTrueFalse.Enum getArrowok();

    public STTrueFalse xgetArrowok();

    public boolean isSetArrowok();

    public void setArrowok(STTrueFalse.Enum var1);

    public void xsetArrowok(STTrueFalse var1);

    public void unsetArrowok();

    public STTrueFalse.Enum getGradientshapeok();

    public STTrueFalse xgetGradientshapeok();

    public boolean isSetGradientshapeok();

    public void setGradientshapeok(STTrueFalse.Enum var1);

    public void xsetGradientshapeok(STTrueFalse var1);

    public void unsetGradientshapeok();

    public STTrueFalse.Enum getTextpathok();

    public STTrueFalse xgetTextpathok();

    public boolean isSetTextpathok();

    public void setTextpathok(STTrueFalse.Enum var1);

    public void xsetTextpathok(STTrueFalse var1);

    public void unsetTextpathok();

    public STTrueFalse.Enum getInsetpenok();

    public STTrueFalse xgetInsetpenok();

    public boolean isSetInsetpenok();

    public void setInsetpenok(STTrueFalse.Enum var1);

    public void xsetInsetpenok(STTrueFalse var1);

    public void unsetInsetpenok();

    public STConnectType.Enum getConnecttype();

    public STConnectType xgetConnecttype();

    public boolean isSetConnecttype();

    public void setConnecttype(STConnectType.Enum var1);

    public void xsetConnecttype(STConnectType var1);

    public void unsetConnecttype();

    public String getConnectlocs();

    public XmlString xgetConnectlocs();

    public boolean isSetConnectlocs();

    public void setConnectlocs(String var1);

    public void xsetConnectlocs(XmlString var1);

    public void unsetConnectlocs();

    public String getConnectangles();

    public XmlString xgetConnectangles();

    public boolean isSetConnectangles();

    public void setConnectangles(String var1);

    public void xsetConnectangles(XmlString var1);

    public void unsetConnectangles();

    public STTrueFalse.Enum getExtrusionok();

    public com.microsoft.schemas.office.office.STTrueFalse xgetExtrusionok();

    public boolean isSetExtrusionok();

    public void setExtrusionok(STTrueFalse.Enum var1);

    public void xsetExtrusionok(com.microsoft.schemas.office.office.STTrueFalse var1);

    public void unsetExtrusionok();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTPath.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTPath newInstance() {
            return (CTPath)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTPath newInstance(XmlOptions xmlOptions) {
            return (CTPath)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTPath parse(String string) throws XmlException {
            return (CTPath)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTPath parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTPath)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTPath parse(File file) throws XmlException, IOException {
            return (CTPath)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTPath parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTPath)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTPath parse(URL uRL) throws XmlException, IOException {
            return (CTPath)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTPath parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTPath)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTPath parse(InputStream inputStream) throws XmlException, IOException {
            return (CTPath)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTPath parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTPath)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTPath parse(Reader reader) throws XmlException, IOException {
            return (CTPath)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTPath parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTPath)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTPath parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTPath)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTPath parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTPath)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTPath parse(Node node) throws XmlException {
            return (CTPath)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTPath parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTPath)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTPath parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTPath)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTPath parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTPath)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

