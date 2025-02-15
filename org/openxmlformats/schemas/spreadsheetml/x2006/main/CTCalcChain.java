/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExtensionList
 */
package org.openxmlformats.schemas.spreadsheetml.x2006.main;

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
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCalcCell;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExtensionList;
import org.w3c.dom.Node;

public interface CTCalcChain
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTCalcChain.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctcalcchain5a0btype");

    public List<CTCalcCell> getCList();

    @Deprecated
    public CTCalcCell[] getCArray();

    public CTCalcCell getCArray(int var1);

    public int sizeOfCArray();

    public void setCArray(CTCalcCell[] var1);

    public void setCArray(int var1, CTCalcCell var2);

    public CTCalcCell insertNewC(int var1);

    public CTCalcCell addNewC();

    public void removeC(int var1);

    public CTExtensionList getExtLst();

    public boolean isSetExtLst();

    public void setExtLst(CTExtensionList var1);

    public CTExtensionList addNewExtLst();

    public void unsetExtLst();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTCalcChain.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTCalcChain newInstance() {
            return (CTCalcChain)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTCalcChain newInstance(XmlOptions xmlOptions) {
            return (CTCalcChain)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTCalcChain parse(String string) throws XmlException {
            return (CTCalcChain)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTCalcChain parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTCalcChain)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTCalcChain parse(File file) throws XmlException, IOException {
            return (CTCalcChain)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTCalcChain parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTCalcChain)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTCalcChain parse(URL uRL) throws XmlException, IOException {
            return (CTCalcChain)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTCalcChain parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTCalcChain)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTCalcChain parse(InputStream inputStream) throws XmlException, IOException {
            return (CTCalcChain)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTCalcChain parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTCalcChain)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTCalcChain parse(Reader reader) throws XmlException, IOException {
            return (CTCalcChain)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTCalcChain parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTCalcChain)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTCalcChain parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTCalcChain)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTCalcChain parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTCalcChain)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTCalcChain parse(Node node) throws XmlException {
            return (CTCalcChain)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTCalcChain parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTCalcChain)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTCalcChain parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTCalcChain)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTCalcChain parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTCalcChain)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

