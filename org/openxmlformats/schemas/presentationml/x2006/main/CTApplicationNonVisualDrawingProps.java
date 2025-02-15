/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.drawingml.x2006.main.CTAudioCD
 *  org.openxmlformats.schemas.drawingml.x2006.main.CTAudioFile
 *  org.openxmlformats.schemas.drawingml.x2006.main.CTEmbeddedWAVAudioFile
 *  org.openxmlformats.schemas.drawingml.x2006.main.CTQuickTimeFile
 *  org.openxmlformats.schemas.drawingml.x2006.main.CTVideoFile
 *  org.openxmlformats.schemas.presentationml.x2006.main.CTExtensionList
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
import org.openxmlformats.schemas.drawingml.x2006.main.CTAudioCD;
import org.openxmlformats.schemas.drawingml.x2006.main.CTAudioFile;
import org.openxmlformats.schemas.drawingml.x2006.main.CTEmbeddedWAVAudioFile;
import org.openxmlformats.schemas.drawingml.x2006.main.CTQuickTimeFile;
import org.openxmlformats.schemas.drawingml.x2006.main.CTVideoFile;
import org.openxmlformats.schemas.presentationml.x2006.main.CTCustomerDataList;
import org.openxmlformats.schemas.presentationml.x2006.main.CTExtensionList;
import org.openxmlformats.schemas.presentationml.x2006.main.CTPlaceholder;
import org.w3c.dom.Node;

public interface CTApplicationNonVisualDrawingProps
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTApplicationNonVisualDrawingProps.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctapplicationnonvisualdrawingprops2fb6type");

    public CTPlaceholder getPh();

    public boolean isSetPh();

    public void setPh(CTPlaceholder var1);

    public CTPlaceholder addNewPh();

    public void unsetPh();

    public CTAudioCD getAudioCd();

    public boolean isSetAudioCd();

    public void setAudioCd(CTAudioCD var1);

    public CTAudioCD addNewAudioCd();

    public void unsetAudioCd();

    public CTEmbeddedWAVAudioFile getWavAudioFile();

    public boolean isSetWavAudioFile();

    public void setWavAudioFile(CTEmbeddedWAVAudioFile var1);

    public CTEmbeddedWAVAudioFile addNewWavAudioFile();

    public void unsetWavAudioFile();

    public CTAudioFile getAudioFile();

    public boolean isSetAudioFile();

    public void setAudioFile(CTAudioFile var1);

    public CTAudioFile addNewAudioFile();

    public void unsetAudioFile();

    public CTVideoFile getVideoFile();

    public boolean isSetVideoFile();

    public void setVideoFile(CTVideoFile var1);

    public CTVideoFile addNewVideoFile();

    public void unsetVideoFile();

    public CTQuickTimeFile getQuickTimeFile();

    public boolean isSetQuickTimeFile();

    public void setQuickTimeFile(CTQuickTimeFile var1);

    public CTQuickTimeFile addNewQuickTimeFile();

    public void unsetQuickTimeFile();

    public CTCustomerDataList getCustDataLst();

    public boolean isSetCustDataLst();

    public void setCustDataLst(CTCustomerDataList var1);

    public CTCustomerDataList addNewCustDataLst();

    public void unsetCustDataLst();

    public CTExtensionList getExtLst();

    public boolean isSetExtLst();

    public void setExtLst(CTExtensionList var1);

    public CTExtensionList addNewExtLst();

    public void unsetExtLst();

    public boolean getIsPhoto();

    public XmlBoolean xgetIsPhoto();

    public boolean isSetIsPhoto();

    public void setIsPhoto(boolean var1);

    public void xsetIsPhoto(XmlBoolean var1);

    public void unsetIsPhoto();

    public boolean getUserDrawn();

    public XmlBoolean xgetUserDrawn();

    public boolean isSetUserDrawn();

    public void setUserDrawn(boolean var1);

    public void xsetUserDrawn(XmlBoolean var1);

    public void unsetUserDrawn();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTApplicationNonVisualDrawingProps.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTApplicationNonVisualDrawingProps newInstance() {
            return (CTApplicationNonVisualDrawingProps)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTApplicationNonVisualDrawingProps newInstance(XmlOptions xmlOptions) {
            return (CTApplicationNonVisualDrawingProps)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTApplicationNonVisualDrawingProps parse(String string) throws XmlException {
            return (CTApplicationNonVisualDrawingProps)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTApplicationNonVisualDrawingProps parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTApplicationNonVisualDrawingProps)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTApplicationNonVisualDrawingProps parse(File file) throws XmlException, IOException {
            return (CTApplicationNonVisualDrawingProps)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTApplicationNonVisualDrawingProps parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTApplicationNonVisualDrawingProps)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTApplicationNonVisualDrawingProps parse(URL uRL) throws XmlException, IOException {
            return (CTApplicationNonVisualDrawingProps)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTApplicationNonVisualDrawingProps parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTApplicationNonVisualDrawingProps)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTApplicationNonVisualDrawingProps parse(InputStream inputStream) throws XmlException, IOException {
            return (CTApplicationNonVisualDrawingProps)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTApplicationNonVisualDrawingProps parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTApplicationNonVisualDrawingProps)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTApplicationNonVisualDrawingProps parse(Reader reader) throws XmlException, IOException {
            return (CTApplicationNonVisualDrawingProps)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTApplicationNonVisualDrawingProps parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTApplicationNonVisualDrawingProps)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTApplicationNonVisualDrawingProps parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTApplicationNonVisualDrawingProps)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTApplicationNonVisualDrawingProps parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTApplicationNonVisualDrawingProps)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTApplicationNonVisualDrawingProps parse(Node node) throws XmlException {
            return (CTApplicationNonVisualDrawingProps)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTApplicationNonVisualDrawingProps parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTApplicationNonVisualDrawingProps)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTApplicationNonVisualDrawingProps parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTApplicationNonVisualDrawingProps)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTApplicationNonVisualDrawingProps parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTApplicationNonVisualDrawingProps)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

