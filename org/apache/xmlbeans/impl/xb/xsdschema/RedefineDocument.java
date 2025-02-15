/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.xb.xsdschema;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.net.URL;
import javax.xml.stream.XMLStreamReader;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlAnyURI;
import org.apache.xmlbeans.XmlBeans;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlID;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.impl.xb.xsdschema.AnnotationDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.NamedAttributeGroup;
import org.apache.xmlbeans.impl.xb.xsdschema.NamedGroup;
import org.apache.xmlbeans.impl.xb.xsdschema.OpenAttrs;
import org.apache.xmlbeans.impl.xb.xsdschema.TopLevelComplexType;
import org.apache.xmlbeans.impl.xb.xsdschema.TopLevelSimpleType;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.w3c.dom.Node;

public interface RedefineDocument
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(RedefineDocument.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sXMLSCHEMA").resolveHandle("redefine3f55doctype");

    public Redefine getRedefine();

    public void setRedefine(Redefine var1);

    public Redefine addNewRedefine();

    public static final class Factory {
        public static RedefineDocument newInstance() {
            return (RedefineDocument)XmlBeans.getContextTypeLoader().newInstance(type, null);
        }

        public static RedefineDocument newInstance(XmlOptions options) {
            return (RedefineDocument)XmlBeans.getContextTypeLoader().newInstance(type, options);
        }

        public static RedefineDocument parse(String xmlAsString) throws XmlException {
            return (RedefineDocument)XmlBeans.getContextTypeLoader().parse(xmlAsString, type, null);
        }

        public static RedefineDocument parse(String xmlAsString, XmlOptions options) throws XmlException {
            return (RedefineDocument)XmlBeans.getContextTypeLoader().parse(xmlAsString, type, options);
        }

        public static RedefineDocument parse(File file) throws XmlException, IOException {
            return (RedefineDocument)XmlBeans.getContextTypeLoader().parse(file, type, null);
        }

        public static RedefineDocument parse(File file, XmlOptions options) throws XmlException, IOException {
            return (RedefineDocument)XmlBeans.getContextTypeLoader().parse(file, type, options);
        }

        public static RedefineDocument parse(URL u) throws XmlException, IOException {
            return (RedefineDocument)XmlBeans.getContextTypeLoader().parse(u, type, null);
        }

        public static RedefineDocument parse(URL u, XmlOptions options) throws XmlException, IOException {
            return (RedefineDocument)XmlBeans.getContextTypeLoader().parse(u, type, options);
        }

        public static RedefineDocument parse(InputStream is) throws XmlException, IOException {
            return (RedefineDocument)XmlBeans.getContextTypeLoader().parse(is, type, null);
        }

        public static RedefineDocument parse(InputStream is, XmlOptions options) throws XmlException, IOException {
            return (RedefineDocument)XmlBeans.getContextTypeLoader().parse(is, type, options);
        }

        public static RedefineDocument parse(Reader r) throws XmlException, IOException {
            return (RedefineDocument)XmlBeans.getContextTypeLoader().parse(r, type, null);
        }

        public static RedefineDocument parse(Reader r, XmlOptions options) throws XmlException, IOException {
            return (RedefineDocument)XmlBeans.getContextTypeLoader().parse(r, type, options);
        }

        public static RedefineDocument parse(XMLStreamReader sr) throws XmlException {
            return (RedefineDocument)XmlBeans.getContextTypeLoader().parse(sr, type, null);
        }

        public static RedefineDocument parse(XMLStreamReader sr, XmlOptions options) throws XmlException {
            return (RedefineDocument)XmlBeans.getContextTypeLoader().parse(sr, type, options);
        }

        public static RedefineDocument parse(Node node) throws XmlException {
            return (RedefineDocument)XmlBeans.getContextTypeLoader().parse(node, type, null);
        }

        public static RedefineDocument parse(Node node, XmlOptions options) throws XmlException {
            return (RedefineDocument)XmlBeans.getContextTypeLoader().parse(node, type, options);
        }

        public static RedefineDocument parse(XMLInputStream xis) throws XmlException, XMLStreamException {
            return (RedefineDocument)XmlBeans.getContextTypeLoader().parse(xis, type, null);
        }

        public static RedefineDocument parse(XMLInputStream xis, XmlOptions options) throws XmlException, XMLStreamException {
            return (RedefineDocument)XmlBeans.getContextTypeLoader().parse(xis, type, options);
        }

        public static XMLInputStream newValidatingXMLInputStream(XMLInputStream xis) throws XmlException, XMLStreamException {
            return XmlBeans.getContextTypeLoader().newValidatingXMLInputStream(xis, type, null);
        }

        public static XMLInputStream newValidatingXMLInputStream(XMLInputStream xis, XmlOptions options) throws XmlException, XMLStreamException {
            return XmlBeans.getContextTypeLoader().newValidatingXMLInputStream(xis, type, options);
        }

        private Factory() {
        }
    }

    public static interface Redefine
    extends OpenAttrs {
        public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(Redefine.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sXMLSCHEMA").resolveHandle("redefine9e9felemtype");

        public AnnotationDocument.Annotation[] getAnnotationArray();

        public AnnotationDocument.Annotation getAnnotationArray(int var1);

        public int sizeOfAnnotationArray();

        public void setAnnotationArray(AnnotationDocument.Annotation[] var1);

        public void setAnnotationArray(int var1, AnnotationDocument.Annotation var2);

        public AnnotationDocument.Annotation insertNewAnnotation(int var1);

        public AnnotationDocument.Annotation addNewAnnotation();

        public void removeAnnotation(int var1);

        public TopLevelSimpleType[] getSimpleTypeArray();

        public TopLevelSimpleType getSimpleTypeArray(int var1);

        public int sizeOfSimpleTypeArray();

        public void setSimpleTypeArray(TopLevelSimpleType[] var1);

        public void setSimpleTypeArray(int var1, TopLevelSimpleType var2);

        public TopLevelSimpleType insertNewSimpleType(int var1);

        public TopLevelSimpleType addNewSimpleType();

        public void removeSimpleType(int var1);

        public TopLevelComplexType[] getComplexTypeArray();

        public TopLevelComplexType getComplexTypeArray(int var1);

        public int sizeOfComplexTypeArray();

        public void setComplexTypeArray(TopLevelComplexType[] var1);

        public void setComplexTypeArray(int var1, TopLevelComplexType var2);

        public TopLevelComplexType insertNewComplexType(int var1);

        public TopLevelComplexType addNewComplexType();

        public void removeComplexType(int var1);

        public NamedGroup[] getGroupArray();

        public NamedGroup getGroupArray(int var1);

        public int sizeOfGroupArray();

        public void setGroupArray(NamedGroup[] var1);

        public void setGroupArray(int var1, NamedGroup var2);

        public NamedGroup insertNewGroup(int var1);

        public NamedGroup addNewGroup();

        public void removeGroup(int var1);

        public NamedAttributeGroup[] getAttributeGroupArray();

        public NamedAttributeGroup getAttributeGroupArray(int var1);

        public int sizeOfAttributeGroupArray();

        public void setAttributeGroupArray(NamedAttributeGroup[] var1);

        public void setAttributeGroupArray(int var1, NamedAttributeGroup var2);

        public NamedAttributeGroup insertNewAttributeGroup(int var1);

        public NamedAttributeGroup addNewAttributeGroup();

        public void removeAttributeGroup(int var1);

        public String getSchemaLocation();

        public XmlAnyURI xgetSchemaLocation();

        public void setSchemaLocation(String var1);

        public void xsetSchemaLocation(XmlAnyURI var1);

        public String getId();

        public XmlID xgetId();

        public boolean isSetId();

        public void setId(String var1);

        public void xsetId(XmlID var1);

        public void unsetId();

        public static final class Factory {
            public static Redefine newInstance() {
                return (Redefine)XmlBeans.getContextTypeLoader().newInstance(type, null);
            }

            public static Redefine newInstance(XmlOptions options) {
                return (Redefine)XmlBeans.getContextTypeLoader().newInstance(type, options);
            }

            private Factory() {
            }
        }
    }
}

