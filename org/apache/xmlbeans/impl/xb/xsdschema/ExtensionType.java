/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.xb.xsdschema;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamReader;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlBeans;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.XmlQName;
import org.apache.xmlbeans.impl.xb.xsdschema.All;
import org.apache.xmlbeans.impl.xb.xsdschema.Annotated;
import org.apache.xmlbeans.impl.xb.xsdschema.Attribute;
import org.apache.xmlbeans.impl.xb.xsdschema.AttributeGroupRef;
import org.apache.xmlbeans.impl.xb.xsdschema.ExplicitGroup;
import org.apache.xmlbeans.impl.xb.xsdschema.GroupRef;
import org.apache.xmlbeans.impl.xb.xsdschema.Wildcard;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.w3c.dom.Node;

public interface ExtensionType
extends Annotated {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(ExtensionType.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sXMLSCHEMA").resolveHandle("extensiontypeed4ctype");

    public GroupRef getGroup();

    public boolean isSetGroup();

    public void setGroup(GroupRef var1);

    public GroupRef addNewGroup();

    public void unsetGroup();

    public All getAll();

    public boolean isSetAll();

    public void setAll(All var1);

    public All addNewAll();

    public void unsetAll();

    public ExplicitGroup getChoice();

    public boolean isSetChoice();

    public void setChoice(ExplicitGroup var1);

    public ExplicitGroup addNewChoice();

    public void unsetChoice();

    public ExplicitGroup getSequence();

    public boolean isSetSequence();

    public void setSequence(ExplicitGroup var1);

    public ExplicitGroup addNewSequence();

    public void unsetSequence();

    public Attribute[] getAttributeArray();

    public Attribute getAttributeArray(int var1);

    public int sizeOfAttributeArray();

    public void setAttributeArray(Attribute[] var1);

    public void setAttributeArray(int var1, Attribute var2);

    public Attribute insertNewAttribute(int var1);

    public Attribute addNewAttribute();

    public void removeAttribute(int var1);

    public AttributeGroupRef[] getAttributeGroupArray();

    public AttributeGroupRef getAttributeGroupArray(int var1);

    public int sizeOfAttributeGroupArray();

    public void setAttributeGroupArray(AttributeGroupRef[] var1);

    public void setAttributeGroupArray(int var1, AttributeGroupRef var2);

    public AttributeGroupRef insertNewAttributeGroup(int var1);

    public AttributeGroupRef addNewAttributeGroup();

    public void removeAttributeGroup(int var1);

    public Wildcard getAnyAttribute();

    public boolean isSetAnyAttribute();

    public void setAnyAttribute(Wildcard var1);

    public Wildcard addNewAnyAttribute();

    public void unsetAnyAttribute();

    public QName getBase();

    public XmlQName xgetBase();

    public void setBase(QName var1);

    public void xsetBase(XmlQName var1);

    public static final class Factory {
        public static ExtensionType newInstance() {
            return (ExtensionType)XmlBeans.getContextTypeLoader().newInstance(type, null);
        }

        public static ExtensionType newInstance(XmlOptions options) {
            return (ExtensionType)XmlBeans.getContextTypeLoader().newInstance(type, options);
        }

        public static ExtensionType parse(String xmlAsString) throws XmlException {
            return (ExtensionType)XmlBeans.getContextTypeLoader().parse(xmlAsString, type, null);
        }

        public static ExtensionType parse(String xmlAsString, XmlOptions options) throws XmlException {
            return (ExtensionType)XmlBeans.getContextTypeLoader().parse(xmlAsString, type, options);
        }

        public static ExtensionType parse(File file) throws XmlException, IOException {
            return (ExtensionType)XmlBeans.getContextTypeLoader().parse(file, type, null);
        }

        public static ExtensionType parse(File file, XmlOptions options) throws XmlException, IOException {
            return (ExtensionType)XmlBeans.getContextTypeLoader().parse(file, type, options);
        }

        public static ExtensionType parse(URL u) throws XmlException, IOException {
            return (ExtensionType)XmlBeans.getContextTypeLoader().parse(u, type, null);
        }

        public static ExtensionType parse(URL u, XmlOptions options) throws XmlException, IOException {
            return (ExtensionType)XmlBeans.getContextTypeLoader().parse(u, type, options);
        }

        public static ExtensionType parse(InputStream is) throws XmlException, IOException {
            return (ExtensionType)XmlBeans.getContextTypeLoader().parse(is, type, null);
        }

        public static ExtensionType parse(InputStream is, XmlOptions options) throws XmlException, IOException {
            return (ExtensionType)XmlBeans.getContextTypeLoader().parse(is, type, options);
        }

        public static ExtensionType parse(Reader r) throws XmlException, IOException {
            return (ExtensionType)XmlBeans.getContextTypeLoader().parse(r, type, null);
        }

        public static ExtensionType parse(Reader r, XmlOptions options) throws XmlException, IOException {
            return (ExtensionType)XmlBeans.getContextTypeLoader().parse(r, type, options);
        }

        public static ExtensionType parse(XMLStreamReader sr) throws XmlException {
            return (ExtensionType)XmlBeans.getContextTypeLoader().parse(sr, type, null);
        }

        public static ExtensionType parse(XMLStreamReader sr, XmlOptions options) throws XmlException {
            return (ExtensionType)XmlBeans.getContextTypeLoader().parse(sr, type, options);
        }

        public static ExtensionType parse(Node node) throws XmlException {
            return (ExtensionType)XmlBeans.getContextTypeLoader().parse(node, type, null);
        }

        public static ExtensionType parse(Node node, XmlOptions options) throws XmlException {
            return (ExtensionType)XmlBeans.getContextTypeLoader().parse(node, type, options);
        }

        public static ExtensionType parse(XMLInputStream xis) throws XmlException, XMLStreamException {
            return (ExtensionType)XmlBeans.getContextTypeLoader().parse(xis, type, null);
        }

        public static ExtensionType parse(XMLInputStream xis, XmlOptions options) throws XmlException, XMLStreamException {
            return (ExtensionType)XmlBeans.getContextTypeLoader().parse(xis, type, options);
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
}

