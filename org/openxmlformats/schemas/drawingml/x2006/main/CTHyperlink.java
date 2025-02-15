/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.drawingml.x2006.main.CTEmbeddedWAVAudioFile
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
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.openxmlformats.schemas.drawingml.x2006.main.CTEmbeddedWAVAudioFile;
import org.openxmlformats.schemas.drawingml.x2006.main.CTOfficeArtExtensionList;
import org.openxmlformats.schemas.officeDocument.x2006.relationships.STRelationshipId;
import org.w3c.dom.Node;

public interface CTHyperlink
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTHyperlink.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("cthyperlink4457type");

    public CTEmbeddedWAVAudioFile getSnd();

    public boolean isSetSnd();

    public void setSnd(CTEmbeddedWAVAudioFile var1);

    public CTEmbeddedWAVAudioFile addNewSnd();

    public void unsetSnd();

    public CTOfficeArtExtensionList getExtLst();

    public boolean isSetExtLst();

    public void setExtLst(CTOfficeArtExtensionList var1);

    public CTOfficeArtExtensionList addNewExtLst();

    public void unsetExtLst();

    public String getId();

    public STRelationshipId xgetId();

    public boolean isSetId();

    public void setId(String var1);

    public void xsetId(STRelationshipId var1);

    public void unsetId();

    public String getInvalidUrl();

    public XmlString xgetInvalidUrl();

    public boolean isSetInvalidUrl();

    public void setInvalidUrl(String var1);

    public void xsetInvalidUrl(XmlString var1);

    public void unsetInvalidUrl();

    public String getAction();

    public XmlString xgetAction();

    public boolean isSetAction();

    public void setAction(String var1);

    public void xsetAction(XmlString var1);

    public void unsetAction();

    public String getTgtFrame();

    public XmlString xgetTgtFrame();

    public boolean isSetTgtFrame();

    public void setTgtFrame(String var1);

    public void xsetTgtFrame(XmlString var1);

    public void unsetTgtFrame();

    public String getTooltip();

    public XmlString xgetTooltip();

    public boolean isSetTooltip();

    public void setTooltip(String var1);

    public void xsetTooltip(XmlString var1);

    public void unsetTooltip();

    public boolean getHistory();

    public XmlBoolean xgetHistory();

    public boolean isSetHistory();

    public void setHistory(boolean var1);

    public void xsetHistory(XmlBoolean var1);

    public void unsetHistory();

    public boolean getHighlightClick();

    public XmlBoolean xgetHighlightClick();

    public boolean isSetHighlightClick();

    public void setHighlightClick(boolean var1);

    public void xsetHighlightClick(XmlBoolean var1);

    public void unsetHighlightClick();

    public boolean getEndSnd();

    public XmlBoolean xgetEndSnd();

    public boolean isSetEndSnd();

    public void setEndSnd(boolean var1);

    public void xsetEndSnd(XmlBoolean var1);

    public void unsetEndSnd();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTHyperlink.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTHyperlink newInstance() {
            return (CTHyperlink)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTHyperlink newInstance(XmlOptions xmlOptions) {
            return (CTHyperlink)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTHyperlink parse(String string) throws XmlException {
            return (CTHyperlink)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTHyperlink parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTHyperlink)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTHyperlink parse(File file) throws XmlException, IOException {
            return (CTHyperlink)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTHyperlink parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTHyperlink)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTHyperlink parse(URL uRL) throws XmlException, IOException {
            return (CTHyperlink)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTHyperlink parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTHyperlink)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTHyperlink parse(InputStream inputStream) throws XmlException, IOException {
            return (CTHyperlink)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTHyperlink parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTHyperlink)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTHyperlink parse(Reader reader) throws XmlException, IOException {
            return (CTHyperlink)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTHyperlink parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTHyperlink)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTHyperlink parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTHyperlink)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTHyperlink parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTHyperlink)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTHyperlink parse(Node node) throws XmlException {
            return (CTHyperlink)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTHyperlink parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTHyperlink)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTHyperlink parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTHyperlink)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTHyperlink parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTHyperlink)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

