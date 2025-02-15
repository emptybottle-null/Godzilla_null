/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.drawingml.x2006.main.CTBackdrop
 *  org.openxmlformats.schemas.drawingml.x2006.main.CTCamera
 *  org.openxmlformats.schemas.drawingml.x2006.main.CTLightRig
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
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.openxmlformats.schemas.drawingml.x2006.main.CTBackdrop;
import org.openxmlformats.schemas.drawingml.x2006.main.CTCamera;
import org.openxmlformats.schemas.drawingml.x2006.main.CTLightRig;
import org.openxmlformats.schemas.drawingml.x2006.main.CTOfficeArtExtensionList;
import org.w3c.dom.Node;

public interface CTScene3D
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTScene3D.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctscene3d736etype");

    public CTCamera getCamera();

    public void setCamera(CTCamera var1);

    public CTCamera addNewCamera();

    public CTLightRig getLightRig();

    public void setLightRig(CTLightRig var1);

    public CTLightRig addNewLightRig();

    public CTBackdrop getBackdrop();

    public boolean isSetBackdrop();

    public void setBackdrop(CTBackdrop var1);

    public CTBackdrop addNewBackdrop();

    public void unsetBackdrop();

    public CTOfficeArtExtensionList getExtLst();

    public boolean isSetExtLst();

    public void setExtLst(CTOfficeArtExtensionList var1);

    public CTOfficeArtExtensionList addNewExtLst();

    public void unsetExtLst();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTScene3D.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTScene3D newInstance() {
            return (CTScene3D)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTScene3D newInstance(XmlOptions xmlOptions) {
            return (CTScene3D)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTScene3D parse(String string) throws XmlException {
            return (CTScene3D)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTScene3D parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTScene3D)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTScene3D parse(File file) throws XmlException, IOException {
            return (CTScene3D)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTScene3D parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTScene3D)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTScene3D parse(URL uRL) throws XmlException, IOException {
            return (CTScene3D)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTScene3D parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTScene3D)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTScene3D parse(InputStream inputStream) throws XmlException, IOException {
            return (CTScene3D)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTScene3D parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTScene3D)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTScene3D parse(Reader reader) throws XmlException, IOException {
            return (CTScene3D)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTScene3D parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTScene3D)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTScene3D parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTScene3D)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTScene3D parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTScene3D)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTScene3D parse(Node node) throws XmlException {
            return (CTScene3D)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTScene3D parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTScene3D)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTScene3D parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTScene3D)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTScene3D parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTScene3D)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

