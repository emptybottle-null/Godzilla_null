/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.presentationml.x2006.main.CTCustomerData
 */
package org.openxmlformats.schemas.presentationml.x2006.main;

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
import org.openxmlformats.schemas.presentationml.x2006.main.CTCustomerData;
import org.openxmlformats.schemas.presentationml.x2006.main.CTTagsData;
import org.w3c.dom.Node;

public interface CTCustomerDataList
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTCustomerDataList.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctcustomerdatalist8b7ftype");

    public List<CTCustomerData> getCustDataList();

    @Deprecated
    public CTCustomerData[] getCustDataArray();

    public CTCustomerData getCustDataArray(int var1);

    public int sizeOfCustDataArray();

    public void setCustDataArray(CTCustomerData[] var1);

    public void setCustDataArray(int var1, CTCustomerData var2);

    public CTCustomerData insertNewCustData(int var1);

    public CTCustomerData addNewCustData();

    public void removeCustData(int var1);

    public CTTagsData getTags();

    public boolean isSetTags();

    public void setTags(CTTagsData var1);

    public CTTagsData addNewTags();

    public void unsetTags();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTCustomerDataList.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTCustomerDataList newInstance() {
            return (CTCustomerDataList)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTCustomerDataList newInstance(XmlOptions xmlOptions) {
            return (CTCustomerDataList)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTCustomerDataList parse(String string) throws XmlException {
            return (CTCustomerDataList)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTCustomerDataList parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTCustomerDataList)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTCustomerDataList parse(File file) throws XmlException, IOException {
            return (CTCustomerDataList)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTCustomerDataList parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTCustomerDataList)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTCustomerDataList parse(URL uRL) throws XmlException, IOException {
            return (CTCustomerDataList)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTCustomerDataList parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTCustomerDataList)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTCustomerDataList parse(InputStream inputStream) throws XmlException, IOException {
            return (CTCustomerDataList)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTCustomerDataList parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTCustomerDataList)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTCustomerDataList parse(Reader reader) throws XmlException, IOException {
            return (CTCustomerDataList)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTCustomerDataList parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTCustomerDataList)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTCustomerDataList parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTCustomerDataList)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTCustomerDataList parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTCustomerDataList)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTCustomerDataList parse(Node node) throws XmlException {
            return (CTCustomerDataList)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTCustomerDataList parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTCustomerDataList)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTCustomerDataList parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTCustomerDataList)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTCustomerDataList parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTCustomerDataList)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

