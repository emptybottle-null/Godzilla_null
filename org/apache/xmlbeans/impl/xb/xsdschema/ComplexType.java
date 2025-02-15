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
import org.apache.xmlbeans.XmlBeans;
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlNCName;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.impl.xb.xsdschema.All;
import org.apache.xmlbeans.impl.xb.xsdschema.Annotated;
import org.apache.xmlbeans.impl.xb.xsdschema.Attribute;
import org.apache.xmlbeans.impl.xb.xsdschema.AttributeGroupRef;
import org.apache.xmlbeans.impl.xb.xsdschema.ComplexContentDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.DerivationSet;
import org.apache.xmlbeans.impl.xb.xsdschema.ExplicitGroup;
import org.apache.xmlbeans.impl.xb.xsdschema.GroupRef;
import org.apache.xmlbeans.impl.xb.xsdschema.SimpleContentDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.Wildcard;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.w3c.dom.Node;

public interface ComplexType
extends Annotated {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(ComplexType.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sXMLSCHEMA").resolveHandle("complextype5dbbtype");

    public SimpleContentDocument.SimpleContent getSimpleContent();

    public boolean isSetSimpleContent();

    public void setSimpleContent(SimpleContentDocument.SimpleContent var1);

    public SimpleContentDocument.SimpleContent addNewSimpleContent();

    public void unsetSimpleContent();

    public ComplexContentDocument.ComplexContent getComplexContent();

    public boolean isSetComplexContent();

    public void setComplexContent(ComplexContentDocument.ComplexContent var1);

    public ComplexContentDocument.ComplexContent addNewComplexContent();

    public void unsetComplexContent();

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

    public String getName();

    public XmlNCName xgetName();

    public boolean isSetName();

    public void setName(String var1);

    public void xsetName(XmlNCName var1);

    public void unsetName();

    public boolean getMixed();

    public XmlBoolean xgetMixed();

    public boolean isSetMixed();

    public void setMixed(boolean var1);

    public void xsetMixed(XmlBoolean var1);

    public void unsetMixed();

    public boolean getAbstract();

    public XmlBoolean xgetAbstract();

    public boolean isSetAbstract();

    public void setAbstract(boolean var1);

    public void xsetAbstract(XmlBoolean var1);

    public void unsetAbstract();

    public Object getFinal();

    public DerivationSet xgetFinal();

    public boolean isSetFinal();

    public void setFinal(Object var1);

    public void xsetFinal(DerivationSet var1);

    public void unsetFinal();

    public Object getBlock();

    public DerivationSet xgetBlock();

    public boolean isSetBlock();

    public void setBlock(Object var1);

    public void xsetBlock(DerivationSet var1);

    public void unsetBlock();

    public static final class Factory {
        public static ComplexType newInstance() {
            return (ComplexType)XmlBeans.getContextTypeLoader().newInstance(type, null);
        }

        public static ComplexType newInstance(XmlOptions options) {
            return (ComplexType)XmlBeans.getContextTypeLoader().newInstance(type, options);
        }

        public static ComplexType parse(String xmlAsString) throws XmlException {
            return (ComplexType)XmlBeans.getContextTypeLoader().parse(xmlAsString, type, null);
        }

        public static ComplexType parse(String xmlAsString, XmlOptions options) throws XmlException {
            return (ComplexType)XmlBeans.getContextTypeLoader().parse(xmlAsString, type, options);
        }

        public static ComplexType parse(File file) throws XmlException, IOException {
            return (ComplexType)XmlBeans.getContextTypeLoader().parse(file, type, null);
        }

        public static ComplexType parse(File file, XmlOptions options) throws XmlException, IOException {
            return (ComplexType)XmlBeans.getContextTypeLoader().parse(file, type, options);
        }

        public static ComplexType parse(URL u) throws XmlException, IOException {
            return (ComplexType)XmlBeans.getContextTypeLoader().parse(u, type, null);
        }

        public static ComplexType parse(URL u, XmlOptions options) throws XmlException, IOException {
            return (ComplexType)XmlBeans.getContextTypeLoader().parse(u, type, options);
        }

        public static ComplexType parse(InputStream is) throws XmlException, IOException {
            return (ComplexType)XmlBeans.getContextTypeLoader().parse(is, type, null);
        }

        public static ComplexType parse(InputStream is, XmlOptions options) throws XmlException, IOException {
            return (ComplexType)XmlBeans.getContextTypeLoader().parse(is, type, options);
        }

        public static ComplexType parse(Reader r) throws XmlException, IOException {
            return (ComplexType)XmlBeans.getContextTypeLoader().parse(r, type, null);
        }

        public static ComplexType parse(Reader r, XmlOptions options) throws XmlException, IOException {
            return (ComplexType)XmlBeans.getContextTypeLoader().parse(r, type, options);
        }

        public static ComplexType parse(XMLStreamReader sr) throws XmlException {
            return (ComplexType)XmlBeans.getContextTypeLoader().parse(sr, type, null);
        }

        public static ComplexType parse(XMLStreamReader sr, XmlOptions options) throws XmlException {
            return (ComplexType)XmlBeans.getContextTypeLoader().parse(sr, type, options);
        }

        public static ComplexType parse(Node node) throws XmlException {
            return (ComplexType)XmlBeans.getContextTypeLoader().parse(node, type, null);
        }

        public static ComplexType parse(Node node, XmlOptions options) throws XmlException {
            return (ComplexType)XmlBeans.getContextTypeLoader().parse(node, type, options);
        }

        public static ComplexType parse(XMLInputStream xis) throws XmlException, XMLStreamException {
            return (ComplexType)XmlBeans.getContextTypeLoader().parse(xis, type, null);
        }

        public static ComplexType parse(XMLInputStream xis, XmlOptions options) throws XmlException, XMLStreamException {
            return (ComplexType)XmlBeans.getContextTypeLoader().parse(xis, type, options);
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

