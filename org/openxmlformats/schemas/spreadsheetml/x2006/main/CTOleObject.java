/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.spreadsheetml.x2006.main.STDvAspect
 *  org.openxmlformats.schemas.spreadsheetml.x2006.main.STOleUpdate
 */
package org.openxmlformats.schemas.spreadsheetml.x2006.main;

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
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.openxmlformats.schemas.officeDocument.x2006.relationships.STRelationshipId;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STDvAspect;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STOleUpdate;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STXstring;
import org.w3c.dom.Node;

public interface CTOleObject
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTOleObject.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctoleobjectd866type");

    public String getProgId();

    public XmlString xgetProgId();

    public boolean isSetProgId();

    public void setProgId(String var1);

    public void xsetProgId(XmlString var1);

    public void unsetProgId();

    public STDvAspect.Enum getDvAspect();

    public STDvAspect xgetDvAspect();

    public boolean isSetDvAspect();

    public void setDvAspect(STDvAspect.Enum var1);

    public void xsetDvAspect(STDvAspect var1);

    public void unsetDvAspect();

    public String getLink();

    public STXstring xgetLink();

    public boolean isSetLink();

    public void setLink(String var1);

    public void xsetLink(STXstring var1);

    public void unsetLink();

    public STOleUpdate.Enum getOleUpdate();

    public STOleUpdate xgetOleUpdate();

    public boolean isSetOleUpdate();

    public void setOleUpdate(STOleUpdate.Enum var1);

    public void xsetOleUpdate(STOleUpdate var1);

    public void unsetOleUpdate();

    public boolean getAutoLoad();

    public XmlBoolean xgetAutoLoad();

    public boolean isSetAutoLoad();

    public void setAutoLoad(boolean var1);

    public void xsetAutoLoad(XmlBoolean var1);

    public void unsetAutoLoad();

    public long getShapeId();

    public XmlUnsignedInt xgetShapeId();

    public void setShapeId(long var1);

    public void xsetShapeId(XmlUnsignedInt var1);

    public String getId();

    public STRelationshipId xgetId();

    public boolean isSetId();

    public void setId(String var1);

    public void xsetId(STRelationshipId var1);

    public void unsetId();

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

