/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.etsi.uri.x01903.v13;

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
import org.etsi.uri.x01903.v13.AnyType;
import org.w3c.dom.Node;

public interface ClaimedRolesListType
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(ClaimedRolesListType.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s8C3F193EE11A2F798ACF65489B9E6078").resolveHandle("claimedroleslisttypef16etype");

    public List<AnyType> getClaimedRoleList();

    @Deprecated
    public AnyType[] getClaimedRoleArray();

    public AnyType getClaimedRoleArray(int var1);

    public int sizeOfClaimedRoleArray();

    public void setClaimedRoleArray(AnyType[] var1);

    public void setClaimedRoleArray(int var1, AnyType var2);

    public AnyType insertNewClaimedRole(int var1);

    public AnyType addNewClaimedRole();

    public void removeClaimedRole(int var1);

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(ClaimedRolesListType.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static ClaimedRolesListType newInstance() {
            return (ClaimedRolesListType)Factory.getTypeLoader().newInstance(type, null);
        }

        public static ClaimedRolesListType newInstance(XmlOptions xmlOptions) {
            return (ClaimedRolesListType)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static ClaimedRolesListType parse(String string) throws XmlException {
            return (ClaimedRolesListType)Factory.getTypeLoader().parse(string, type, null);
        }

        public static ClaimedRolesListType parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (ClaimedRolesListType)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static ClaimedRolesListType parse(File file) throws XmlException, IOException {
            return (ClaimedRolesListType)Factory.getTypeLoader().parse(file, type, null);
        }

        public static ClaimedRolesListType parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (ClaimedRolesListType)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static ClaimedRolesListType parse(URL uRL) throws XmlException, IOException {
            return (ClaimedRolesListType)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static ClaimedRolesListType parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (ClaimedRolesListType)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static ClaimedRolesListType parse(InputStream inputStream) throws XmlException, IOException {
            return (ClaimedRolesListType)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static ClaimedRolesListType parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (ClaimedRolesListType)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static ClaimedRolesListType parse(Reader reader) throws XmlException, IOException {
            return (ClaimedRolesListType)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static ClaimedRolesListType parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (ClaimedRolesListType)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static ClaimedRolesListType parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (ClaimedRolesListType)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static ClaimedRolesListType parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (ClaimedRolesListType)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static ClaimedRolesListType parse(Node node) throws XmlException {
            return (ClaimedRolesListType)Factory.getTypeLoader().parse(node, type, null);
        }

        public static ClaimedRolesListType parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (ClaimedRolesListType)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static ClaimedRolesListType parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (ClaimedRolesListType)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static ClaimedRolesListType parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (ClaimedRolesListType)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

