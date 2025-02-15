/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.presentationml.x2006.main.CTExtensionListModify
 *  org.openxmlformats.schemas.presentationml.x2006.main.CTSlideTiming
 *  org.openxmlformats.schemas.presentationml.x2006.main.CTSlideTransition
 */
package org.openxmlformats.schemas.presentationml.x2006.main;

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
import org.openxmlformats.schemas.drawingml.x2006.main.CTColorMappingOverride;
import org.openxmlformats.schemas.presentationml.x2006.main.CTCommonSlideData;
import org.openxmlformats.schemas.presentationml.x2006.main.CTExtensionListModify;
import org.openxmlformats.schemas.presentationml.x2006.main.CTSlideTiming;
import org.openxmlformats.schemas.presentationml.x2006.main.CTSlideTransition;
import org.w3c.dom.Node;

public interface CTSlide
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTSlide.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctslided7betype");

    public CTCommonSlideData getCSld();

    public void setCSld(CTCommonSlideData var1);

    public CTCommonSlideData addNewCSld();

    public CTColorMappingOverride getClrMapOvr();

    public boolean isSetClrMapOvr();

    public void setClrMapOvr(CTColorMappingOverride var1);

    public CTColorMappingOverride addNewClrMapOvr();

    public void unsetClrMapOvr();

    public CTSlideTransition getTransition();

    public boolean isSetTransition();

    public void setTransition(CTSlideTransition var1);

    public CTSlideTransition addNewTransition();

    public void unsetTransition();

    public CTSlideTiming getTiming();

    public boolean isSetTiming();

    public void setTiming(CTSlideTiming var1);

    public CTSlideTiming addNewTiming();

    public void unsetTiming();

    public CTExtensionListModify getExtLst();

    public boolean isSetExtLst();

    public void setExtLst(CTExtensionListModify var1);

    public CTExtensionListModify addNewExtLst();

    public void unsetExtLst();

    public boolean getShowMasterSp();

    public XmlBoolean xgetShowMasterSp();

    public boolean isSetShowMasterSp();

    public void setShowMasterSp(boolean var1);

    public void xsetShowMasterSp(XmlBoolean var1);

    public void unsetShowMasterSp();

    public boolean getShowMasterPhAnim();

    public XmlBoolean xgetShowMasterPhAnim();

    public boolean isSetShowMasterPhAnim();

    public void setShowMasterPhAnim(boolean var1);

    public void xsetShowMasterPhAnim(XmlBoolean var1);

    public void unsetShowMasterPhAnim();

    public boolean getShow();

    public XmlBoolean xgetShow();

    public boolean isSetShow();

    public void setShow(boolean var1);

    public void xsetShow(XmlBoolean var1);

    public void unsetShow();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTSlide.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTSlide newInstance() {
            return (CTSlide)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTSlide newInstance(XmlOptions xmlOptions) {
            return (CTSlide)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTSlide parse(String string) throws XmlException {
            return (CTSlide)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTSlide parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTSlide)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTSlide parse(File file) throws XmlException, IOException {
            return (CTSlide)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTSlide parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTSlide)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTSlide parse(URL uRL) throws XmlException, IOException {
            return (CTSlide)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTSlide parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTSlide)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTSlide parse(InputStream inputStream) throws XmlException, IOException {
            return (CTSlide)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTSlide parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTSlide)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTSlide parse(Reader reader) throws XmlException, IOException {
            return (CTSlide)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTSlide parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTSlide)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTSlide parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTSlide)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTSlide parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTSlide)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTSlide parse(Node node) throws XmlException {
            return (CTSlide)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTSlide parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTSlide)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTSlide parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTSlide)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTSlide parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTSlide)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

