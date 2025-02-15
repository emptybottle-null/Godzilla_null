/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing;

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
import org.openxmlformats.schemas.drawingml.x2006.main.CTPoint2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPositiveSize2D;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTAnchorClientData;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTConnector;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTGraphicalObjectFrame;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTGroupShape;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTPicture;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTShape;
import org.w3c.dom.Node;

public interface CTAbsoluteAnchor
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTAbsoluteAnchor.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctabsoluteanchore360type");

    public CTPoint2D getPos();

    public void setPos(CTPoint2D var1);

    public CTPoint2D addNewPos();

    public CTPositiveSize2D getExt();

    public void setExt(CTPositiveSize2D var1);

    public CTPositiveSize2D addNewExt();

    public CTShape getSp();

    public boolean isSetSp();

    public void setSp(CTShape var1);

    public CTShape addNewSp();

    public void unsetSp();

    public CTGroupShape getGrpSp();

    public boolean isSetGrpSp();

    public void setGrpSp(CTGroupShape var1);

    public CTGroupShape addNewGrpSp();

    public void unsetGrpSp();

    public CTGraphicalObjectFrame getGraphicFrame();

    public boolean isSetGraphicFrame();

    public void setGraphicFrame(CTGraphicalObjectFrame var1);

    public CTGraphicalObjectFrame addNewGraphicFrame();

    public void unsetGraphicFrame();

    public CTConnector getCxnSp();

    public boolean isSetCxnSp();

    public void setCxnSp(CTConnector var1);

    public CTConnector addNewCxnSp();

    public void unsetCxnSp();

    public CTPicture getPic();

    public boolean isSetPic();

    public void setPic(CTPicture var1);

    public CTPicture addNewPic();

    public void unsetPic();

    public CTAnchorClientData getClientData();

    public void setClientData(CTAnchorClientData var1);

    public CTAnchorClientData addNewClientData();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTAbsoluteAnchor.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTAbsoluteAnchor newInstance() {
            return (CTAbsoluteAnchor)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTAbsoluteAnchor newInstance(XmlOptions xmlOptions) {
            return (CTAbsoluteAnchor)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTAbsoluteAnchor parse(String string) throws XmlException {
            return (CTAbsoluteAnchor)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTAbsoluteAnchor parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTAbsoluteAnchor)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTAbsoluteAnchor parse(File file) throws XmlException, IOException {
            return (CTAbsoluteAnchor)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTAbsoluteAnchor parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTAbsoluteAnchor)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTAbsoluteAnchor parse(URL uRL) throws XmlException, IOException {
            return (CTAbsoluteAnchor)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTAbsoluteAnchor parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTAbsoluteAnchor)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTAbsoluteAnchor parse(InputStream inputStream) throws XmlException, IOException {
            return (CTAbsoluteAnchor)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTAbsoluteAnchor parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTAbsoluteAnchor)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTAbsoluteAnchor parse(Reader reader) throws XmlException, IOException {
            return (CTAbsoluteAnchor)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTAbsoluteAnchor parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTAbsoluteAnchor)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTAbsoluteAnchor parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTAbsoluteAnchor)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTAbsoluteAnchor parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTAbsoluteAnchor)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTAbsoluteAnchor parse(Node node) throws XmlException {
            return (CTAbsoluteAnchor)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTAbsoluteAnchor parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTAbsoluteAnchor)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTAbsoluteAnchor parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTAbsoluteAnchor)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTAbsoluteAnchor parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTAbsoluteAnchor)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

