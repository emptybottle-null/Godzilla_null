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
import java.util.List;
import javax.xml.stream.XMLStreamReader;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SchemaTypeLoader;
import org.apache.xmlbeans.XmlBeans;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGroupShapeProperties;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTConnector;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTGraphicalObjectFrame;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTGroupShapeNonVisual;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTPicture;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTShape;
import org.w3c.dom.Node;

public interface CTGroupShape
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTGroupShape.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctgroupshape6c36type");

    public CTGroupShapeNonVisual getNvGrpSpPr();

    public void setNvGrpSpPr(CTGroupShapeNonVisual var1);

    public CTGroupShapeNonVisual addNewNvGrpSpPr();

    public CTGroupShapeProperties getGrpSpPr();

    public void setGrpSpPr(CTGroupShapeProperties var1);

    public CTGroupShapeProperties addNewGrpSpPr();

    public List<CTShape> getSpList();

    @Deprecated
    public CTShape[] getSpArray();

    public CTShape getSpArray(int var1);

    public int sizeOfSpArray();

    public void setSpArray(CTShape[] var1);

    public void setSpArray(int var1, CTShape var2);

    public CTShape insertNewSp(int var1);

    public CTShape addNewSp();

    public void removeSp(int var1);

    public List<CTGroupShape> getGrpSpList();

    @Deprecated
    public CTGroupShape[] getGrpSpArray();

    public CTGroupShape getGrpSpArray(int var1);

    public int sizeOfGrpSpArray();

    public void setGrpSpArray(CTGroupShape[] var1);

    public void setGrpSpArray(int var1, CTGroupShape var2);

    public CTGroupShape insertNewGrpSp(int var1);

    public CTGroupShape addNewGrpSp();

    public void removeGrpSp(int var1);

    public List<CTGraphicalObjectFrame> getGraphicFrameList();

    @Deprecated
    public CTGraphicalObjectFrame[] getGraphicFrameArray();

    public CTGraphicalObjectFrame getGraphicFrameArray(int var1);

    public int sizeOfGraphicFrameArray();

    public void setGraphicFrameArray(CTGraphicalObjectFrame[] var1);

    public void setGraphicFrameArray(int var1, CTGraphicalObjectFrame var2);

    public CTGraphicalObjectFrame insertNewGraphicFrame(int var1);

    public CTGraphicalObjectFrame addNewGraphicFrame();

    public void removeGraphicFrame(int var1);

    public List<CTConnector> getCxnSpList();

    @Deprecated
    public CTConnector[] getCxnSpArray();

    public CTConnector getCxnSpArray(int var1);

    public int sizeOfCxnSpArray();

    public void setCxnSpArray(CTConnector[] var1);

    public void setCxnSpArray(int var1, CTConnector var2);

    public CTConnector insertNewCxnSp(int var1);

    public CTConnector addNewCxnSp();

    public void removeCxnSp(int var1);

    public List<CTPicture> getPicList();

    @Deprecated
    public CTPicture[] getPicArray();

    public CTPicture getPicArray(int var1);

    public int sizeOfPicArray();

    public void setPicArray(CTPicture[] var1);

    public void setPicArray(int var1, CTPicture var2);

    public CTPicture insertNewPic(int var1);

    public CTPicture addNewPic();

    public void removePic(int var1);

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTGroupShape.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTGroupShape newInstance() {
            return (CTGroupShape)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTGroupShape newInstance(XmlOptions xmlOptions) {
            return (CTGroupShape)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTGroupShape parse(String string) throws XmlException {
            return (CTGroupShape)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTGroupShape parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTGroupShape)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTGroupShape parse(File file) throws XmlException, IOException {
            return (CTGroupShape)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTGroupShape parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTGroupShape)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTGroupShape parse(URL uRL) throws XmlException, IOException {
            return (CTGroupShape)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTGroupShape parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTGroupShape)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTGroupShape parse(InputStream inputStream) throws XmlException, IOException {
            return (CTGroupShape)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTGroupShape parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTGroupShape)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTGroupShape parse(Reader reader) throws XmlException, IOException {
            return (CTGroupShape)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTGroupShape parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTGroupShape)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTGroupShape parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTGroupShape)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTGroupShape parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTGroupShape)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTGroupShape parse(Node node) throws XmlException {
            return (CTGroupShape)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTGroupShape parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTGroupShape)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTGroupShape parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTGroupShape)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTGroupShape parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTGroupShape)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

