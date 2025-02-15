/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.presentationml.x2006.main.CTExtensionListModify
 *  org.openxmlformats.schemas.presentationml.x2006.main.CTSlideLayoutIdList
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
import org.openxmlformats.schemas.drawingml.x2006.main.CTColorMapping;
import org.openxmlformats.schemas.presentationml.x2006.main.CTCommonSlideData;
import org.openxmlformats.schemas.presentationml.x2006.main.CTExtensionListModify;
import org.openxmlformats.schemas.presentationml.x2006.main.CTHeaderFooter;
import org.openxmlformats.schemas.presentationml.x2006.main.CTSlideLayoutIdList;
import org.openxmlformats.schemas.presentationml.x2006.main.CTSlideMasterTextStyles;
import org.openxmlformats.schemas.presentationml.x2006.main.CTSlideTiming;
import org.openxmlformats.schemas.presentationml.x2006.main.CTSlideTransition;
import org.w3c.dom.Node;

public interface CTSlideMaster
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTSlideMaster.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctslidemasterd8fctype");

    public CTCommonSlideData getCSld();

    public void setCSld(CTCommonSlideData var1);

    public CTCommonSlideData addNewCSld();

    public CTColorMapping getClrMap();

    public void setClrMap(CTColorMapping var1);

    public CTColorMapping addNewClrMap();

    public CTSlideLayoutIdList getSldLayoutIdLst();

    public boolean isSetSldLayoutIdLst();

    public void setSldLayoutIdLst(CTSlideLayoutIdList var1);

    public CTSlideLayoutIdList addNewSldLayoutIdLst();

    public void unsetSldLayoutIdLst();

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

    public CTHeaderFooter getHf();

    public boolean isSetHf();

    public void setHf(CTHeaderFooter var1);

    public CTHeaderFooter addNewHf();

    public void unsetHf();

    public CTSlideMasterTextStyles getTxStyles();

    public boolean isSetTxStyles();

    public void setTxStyles(CTSlideMasterTextStyles var1);

    public CTSlideMasterTextStyles addNewTxStyles();

    public void unsetTxStyles();

    public CTExtensionListModify getExtLst();

    public boolean isSetExtLst();

    public void setExtLst(CTExtensionListModify var1);

    public CTExtensionListModify addNewExtLst();

    public void unsetExtLst();

    public boolean getPreserve();

    public XmlBoolean xgetPreserve();

    public boolean isSetPreserve();

    public void setPreserve(boolean var1);

    public void xsetPreserve(XmlBoolean var1);

    public void unsetPreserve();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTSlideMaster.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTSlideMaster newInstance() {
            return (CTSlideMaster)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTSlideMaster newInstance(XmlOptions xmlOptions) {
            return (CTSlideMaster)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTSlideMaster parse(String string) throws XmlException {
            return (CTSlideMaster)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTSlideMaster parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTSlideMaster)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTSlideMaster parse(File file) throws XmlException, IOException {
            return (CTSlideMaster)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTSlideMaster parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTSlideMaster)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTSlideMaster parse(URL uRL) throws XmlException, IOException {
            return (CTSlideMaster)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTSlideMaster parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTSlideMaster)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTSlideMaster parse(InputStream inputStream) throws XmlException, IOException {
            return (CTSlideMaster)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTSlideMaster parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTSlideMaster)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTSlideMaster parse(Reader reader) throws XmlException, IOException {
            return (CTSlideMaster)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTSlideMaster parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTSlideMaster)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTSlideMaster parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTSlideMaster)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTSlideMaster parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTSlideMaster)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTSlideMaster parse(Node node) throws XmlException {
            return (CTSlideMaster)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTSlideMaster parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTSlideMaster)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTSlideMaster parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTSlideMaster)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTSlideMaster parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTSlideMaster)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

