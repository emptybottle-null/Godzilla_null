/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.xb.xsdschema;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.math.BigInteger;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamReader;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlBeans;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlNCName;
import org.apache.xmlbeans.XmlNonNegativeInteger;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.XmlQName;
import org.apache.xmlbeans.impl.xb.xsdschema.All;
import org.apache.xmlbeans.impl.xb.xsdschema.AllNNI;
import org.apache.xmlbeans.impl.xb.xsdschema.Annotated;
import org.apache.xmlbeans.impl.xb.xsdschema.AnyDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.ExplicitGroup;
import org.apache.xmlbeans.impl.xb.xsdschema.GroupRef;
import org.apache.xmlbeans.impl.xb.xsdschema.LocalElement;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.w3c.dom.Node;

public interface Group
extends Annotated {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(Group.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sXMLSCHEMA").resolveHandle("group7ca6type");

    public LocalElement[] getElementArray();

    public LocalElement getElementArray(int var1);

    public int sizeOfElementArray();

    public void setElementArray(LocalElement[] var1);

    public void setElementArray(int var1, LocalElement var2);

    public LocalElement insertNewElement(int var1);

    public LocalElement addNewElement();

    public void removeElement(int var1);

    public GroupRef[] getGroupArray();

    public GroupRef getGroupArray(int var1);

    public int sizeOfGroupArray();

    public void setGroupArray(GroupRef[] var1);

    public void setGroupArray(int var1, GroupRef var2);

    public GroupRef insertNewGroup(int var1);

    public GroupRef addNewGroup();

    public void removeGroup(int var1);

    public All[] getAllArray();

    public All getAllArray(int var1);

    public int sizeOfAllArray();

    public void setAllArray(All[] var1);

    public void setAllArray(int var1, All var2);

    public All insertNewAll(int var1);

    public All addNewAll();

    public void removeAll(int var1);

    public ExplicitGroup[] getChoiceArray();

    public ExplicitGroup getChoiceArray(int var1);

    public int sizeOfChoiceArray();

    public void setChoiceArray(ExplicitGroup[] var1);

    public void setChoiceArray(int var1, ExplicitGroup var2);

    public ExplicitGroup insertNewChoice(int var1);

    public ExplicitGroup addNewChoice();

    public void removeChoice(int var1);

    public ExplicitGroup[] getSequenceArray();

    public ExplicitGroup getSequenceArray(int var1);

    public int sizeOfSequenceArray();

    public void setSequenceArray(ExplicitGroup[] var1);

    public void setSequenceArray(int var1, ExplicitGroup var2);

    public ExplicitGroup insertNewSequence(int var1);

    public ExplicitGroup addNewSequence();

    public void removeSequence(int var1);

    public AnyDocument.Any[] getAnyArray();

    public AnyDocument.Any getAnyArray(int var1);

    public int sizeOfAnyArray();

    public void setAnyArray(AnyDocument.Any[] var1);

    public void setAnyArray(int var1, AnyDocument.Any var2);

    public AnyDocument.Any insertNewAny(int var1);

    public AnyDocument.Any addNewAny();

    public void removeAny(int var1);

    public String getName();

    public XmlNCName xgetName();

    public boolean isSetName();

    public void setName(String var1);

    public void xsetName(XmlNCName var1);

    public void unsetName();

    public QName getRef();

    public XmlQName xgetRef();

    public boolean isSetRef();

    public void setRef(QName var1);

    public void xsetRef(XmlQName var1);

    public void unsetRef();

    public BigInteger getMinOccurs();

    public XmlNonNegativeInteger xgetMinOccurs();

    public boolean isSetMinOccurs();

    public void setMinOccurs(BigInteger var1);

    public void xsetMinOccurs(XmlNonNegativeInteger var1);

    public void unsetMinOccurs();

    public Object getMaxOccurs();

    public AllNNI xgetMaxOccurs();

    public boolean isSetMaxOccurs();

    public void setMaxOccurs(Object var1);

    public void xsetMaxOccurs(AllNNI var1);

    public void unsetMaxOccurs();

    public static final class Factory {
        public static Group newInstance() {
            return (Group)XmlBeans.getContextTypeLoader().newInstance(type, null);
        }

        public static Group newInstance(XmlOptions options) {
            return (Group)XmlBeans.getContextTypeLoader().newInstance(type, options);
        }

        public static Group parse(String xmlAsString) throws XmlException {
            return (Group)XmlBeans.getContextTypeLoader().parse(xmlAsString, type, null);
        }

        public static Group parse(String xmlAsString, XmlOptions options) throws XmlException {
            return (Group)XmlBeans.getContextTypeLoader().parse(xmlAsString, type, options);
        }

        public static Group parse(File file) throws XmlException, IOException {
            return (Group)XmlBeans.getContextTypeLoader().parse(file, type, null);
        }

        public static Group parse(File file, XmlOptions options) throws XmlException, IOException {
            return (Group)XmlBeans.getContextTypeLoader().parse(file, type, options);
        }

        public static Group parse(URL u) throws XmlException, IOException {
            return (Group)XmlBeans.getContextTypeLoader().parse(u, type, null);
        }

        public static Group parse(URL u, XmlOptions options) throws XmlException, IOException {
            return (Group)XmlBeans.getContextTypeLoader().parse(u, type, options);
        }

        public static Group parse(InputStream is) throws XmlException, IOException {
            return (Group)XmlBeans.getContextTypeLoader().parse(is, type, null);
        }

        public static Group parse(InputStream is, XmlOptions options) throws XmlException, IOException {
            return (Group)XmlBeans.getContextTypeLoader().parse(is, type, options);
        }

        public static Group parse(Reader r) throws XmlException, IOException {
            return (Group)XmlBeans.getContextTypeLoader().parse(r, type, null);
        }

        public static Group parse(Reader r, XmlOptions options) throws XmlException, IOException {
            return (Group)XmlBeans.getContextTypeLoader().parse(r, type, options);
        }

        public static Group parse(XMLStreamReader sr) throws XmlException {
            return (Group)XmlBeans.getContextTypeLoader().parse(sr, type, null);
        }

        public static Group parse(XMLStreamReader sr, XmlOptions options) throws XmlException {
            return (Group)XmlBeans.getContextTypeLoader().parse(sr, type, options);
        }

        public static Group parse(Node node) throws XmlException {
            return (Group)XmlBeans.getContextTypeLoader().parse(node, type, null);
        }

        public static Group parse(Node node, XmlOptions options) throws XmlException {
            return (Group)XmlBeans.getContextTypeLoader().parse(node, type, options);
        }

        public static Group parse(XMLInputStream xis) throws XmlException, XMLStreamException {
            return (Group)XmlBeans.getContextTypeLoader().parse(xis, type, null);
        }

        public static Group parse(XMLInputStream xis, XmlOptions options) throws XmlException, XMLStreamException {
            return (Group)XmlBeans.getContextTypeLoader().parse(xis, type, options);
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

