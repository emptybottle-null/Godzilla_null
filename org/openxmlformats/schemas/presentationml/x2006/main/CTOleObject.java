/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.drawingml.x2006.main.STShapeID
 *  org.openxmlformats.schemas.presentationml.x2006.main.CTOleObjectEmbed
 *  org.openxmlformats.schemas.presentationml.x2006.main.CTOleObjectLink
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
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.openxmlformats.schemas.drawingml.x2006.main.STPositiveCoordinate32;
import org.openxmlformats.schemas.drawingml.x2006.main.STShapeID;
import org.openxmlformats.schemas.officeDocument.x2006.relationships.STRelationshipId;
import org.openxmlformats.schemas.presentationml.x2006.main.CTOleObjectEmbed;
import org.openxmlformats.schemas.presentationml.x2006.main.CTOleObjectLink;
import org.w3c.dom.Node;

public interface CTOleObject
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTOleObject.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctoleobject5da8type");

    public CTOleObjectEmbed getEmbed();

    public boolean isSetEmbed();

    public void setEmbed(CTOleObjectEmbed var1);

    public CTOleObjectEmbed addNewEmbed();

    public void unsetEmbed();

    public CTOleObjectLink getLink();

    public boolean isSetLink();

    public void setLink(CTOleObjectLink var1);

    public CTOleObjectLink addNewLink();

    public void unsetLink();

    public String getSpid();

    public STShapeID xgetSpid();

    public void setSpid(String var1);

    public void xsetSpid(STShapeID var1);

    public String getName();

    public XmlString xgetName();

    public boolean isSetName();

    public void setName(String var1);

    public void xsetName(XmlString var1);

    public void unsetName();

    public boolean getShowAsIcon();

    public XmlBoolean xgetShowAsIcon();

    public boolean isSetShowAsIcon();

    public void setShowAsIcon(boolean var1);

    public void xsetShowAsIcon(XmlBoolean var1);

    public void unsetShowAsIcon();

    public String getId();

    public STRelationshipId xgetId();

    public boolean isSetId();

    public void setId(String var1);

    public void xsetId(STRelationshipId var1);

    public void unsetId();

    public int getImgW();

    public STPositiveCoordinate32 xgetImgW();

    public boolean isSetImgW();

    public void setImgW(int var1);

    public void xsetImgW(STPositiveCoordinate32 var1);

    public void unsetImgW();

    public int getImgH();

    public STPositiveCoordinate32 xgetImgH();

    public boolean isSetImgH();

    public void setImgH(int var1);

    public void xsetImgH(STPositiveCoordinate32 var1);

    public void unsetImgH();

    public String getProgId();

    public XmlString xgetProgId();

    public boolean isSetProgId();

    public void setProgId(String var1);

    public void xsetProgId(XmlString var1);

    public void unsetProgId();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTOleObject.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTOleObject newInstance() {
            return (CTOleObject)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTOleObject newInstance(XmlOptions xmlOptions) {
            return (CTOleObject)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTOleObject parse(String string) throws XmlException {
            return (CTOleObject)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTOleObject parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTOleObject)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTOleObject parse(File file) throws XmlException, IOException {
            return (CTOleObject)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTOleObject parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTOleObject)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTOleObject parse(URL uRL) throws XmlException, IOException {
            return (CTOleObject)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTOleObject parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTOleObject)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTOleObject parse(InputStream inputStream) throws XmlException, IOException {
            return (CTOleObject)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTOleObject parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTOleObject)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTOleObject parse(Reader reader) throws XmlException, IOException {
            return (CTOleObject)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTOleObject parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTOleObject)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTOleObject parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTOleObject)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTOleObject parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTOleObject)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTOleObject parse(Node node) throws XmlException {
            return (CTOleObject)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTOleObject parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTOleObject)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTOleObject parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTOleObject)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTOleObject parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTOleObject)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

