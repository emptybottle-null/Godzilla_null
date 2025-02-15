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
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlNCName;
import org.apache.xmlbeans.XmlNonNegativeInteger;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.XmlQName;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.xb.xsdschema.AllNNI;
import org.apache.xmlbeans.impl.xb.xsdschema.Annotated;
import org.apache.xmlbeans.impl.xb.xsdschema.BlockSet;
import org.apache.xmlbeans.impl.xb.xsdschema.DerivationSet;
import org.apache.xmlbeans.impl.xb.xsdschema.FormChoice;
import org.apache.xmlbeans.impl.xb.xsdschema.Keybase;
import org.apache.xmlbeans.impl.xb.xsdschema.KeyrefDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.LocalComplexType;
import org.apache.xmlbeans.impl.xb.xsdschema.LocalSimpleType;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.w3c.dom.Node;

public interface Element
extends Annotated {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(Element.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sXMLSCHEMA").resolveHandle("elementd189type");

    public LocalSimpleType getSimpleType();

    public boolean isSetSimpleType();

    public void setSimpleType(LocalSimpleType var1);

    public LocalSimpleType addNewSimpleType();

    public void unsetSimpleType();

    public LocalComplexType getComplexType();

    public boolean isSetComplexType();

    public void setComplexType(LocalComplexType var1);

    public LocalComplexType addNewComplexType();

    public void unsetComplexType();

    public Keybase[] getUniqueArray();

    public Keybase getUniqueArray(int var1);

    public int sizeOfUniqueArray();

    public void setUniqueArray(Keybase[] var1);

    public void setUniqueArray(int var1, Keybase var2);

    public Keybase insertNewUnique(int var1);

    public Keybase addNewUnique();

    public void removeUnique(int var1);

    public Keybase[] getKeyArray();

    public Keybase getKeyArray(int var1);

    public int sizeOfKeyArray();

    public void setKeyArray(Keybase[] var1);

    public void setKeyArray(int var1, Keybase var2);

    public Keybase insertNewKey(int var1);

    public Keybase addNewKey();

    public void removeKey(int var1);

    public KeyrefDocument.Keyref[] getKeyrefArray();

    public KeyrefDocument.Keyref getKeyrefArray(int var1);

    public int sizeOfKeyrefArray();

    public void setKeyrefArray(KeyrefDocument.Keyref[] var1);

    public void setKeyrefArray(int var1, KeyrefDocument.Keyref var2);

    public KeyrefDocument.Keyref insertNewKeyref(int var1);

    public KeyrefDocument.Keyref addNewKeyref();

    public void removeKeyref(int var1);

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

    public QName getType();

    public XmlQName xgetType();

    public boolean isSetType();

    public void setType(QName var1);

    public void xsetType(XmlQName var1);

    public void unsetType();

    public QName getSubstitutionGroup();

    public XmlQName xgetSubstitutionGroup();

    public boolean isSetSubstitutionGroup();

    public void setSubstitutionGroup(QName var1);

    public void xsetSubstitutionGroup(XmlQName var1);

    public void unsetSubstitutionGroup();

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

    public String getDefault();

    public XmlString xgetDefault();

    public boolean isSetDefault();

    public void setDefault(String var1);

    public void xsetDefault(XmlString var1);

    public void unsetDefault();

    public String getFixed();

    public XmlString xgetFixed();

    public boolean isSetFixed();

    public void setFixed(String var1);

    public void xsetFixed(XmlString var1);

    public void unsetFixed();

    public boolean getNillable();

    public XmlBoolean xgetNillable();

    public boolean isSetNillable();

    public void setNillable(boolean var1);

    public void xsetNillable(XmlBoolean var1);

    public void unsetNillable();

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

    public BlockSet xgetBlock();

    public boolean isSetBlock();

    public void setBlock(Object var1);

    public void xsetBlock(BlockSet var1);

    public void unsetBlock();

    public FormChoice.Enum getForm();

    public FormChoice xgetForm();

    public boolean isSetForm();

    public void setForm(FormChoice.Enum var1);

    public void xsetForm(FormChoice var1);

    public void unsetForm();

    public static final class Factory {
        public static Element newInstance() {
            return (Element)XmlBeans.getContextTypeLoader().newInstance(type, null);
        }

        public static Element newInstance(XmlOptions options) {
            return (Element)XmlBeans.getContextTypeLoader().newInstance(type, options);
        }

        public static Element parse(String xmlAsString) throws XmlException {
            return (Element)XmlBeans.getContextTypeLoader().parse(xmlAsString, type, null);
        }

        public static Element parse(String xmlAsString, XmlOptions options) throws XmlException {
            return (Element)XmlBeans.getContextTypeLoader().parse(xmlAsString, type, options);
        }

        public static Element parse(File file) throws XmlException, IOException {
            return (Element)XmlBeans.getContextTypeLoader().parse(file, type, null);
        }

        public static Element parse(File file, XmlOptions options) throws XmlException, IOException {
            return (Element)XmlBeans.getContextTypeLoader().parse(file, type, options);
        }

        public static Element parse(URL u) throws XmlException, IOException {
            return (Element)XmlBeans.getContextTypeLoader().parse(u, type, null);
        }

        public static Element parse(URL u, XmlOptions options) throws XmlException, IOException {
            return (Element)XmlBeans.getContextTypeLoader().parse(u, type, options);
        }

        public static Element parse(InputStream is) throws XmlException, IOException {
            return (Element)XmlBeans.getContextTypeLoader().parse(is, type, null);
        }

        public static Element parse(InputStream is, XmlOptions options) throws XmlException, IOException {
            return (Element)XmlBeans.getContextTypeLoader().parse(is, type, options);
        }

        public static Element parse(Reader r) throws XmlException, IOException {
            return (Element)XmlBeans.getContextTypeLoader().parse(r, type, null);
        }

        public static Element parse(Reader r, XmlOptions options) throws XmlException, IOException {
            return (Element)XmlBeans.getContextTypeLoader().parse(r, type, options);
        }

        public static Element parse(XMLStreamReader sr) throws XmlException {
            return (Element)XmlBeans.getContextTypeLoader().parse(sr, type, null);
        }

        public static Element parse(XMLStreamReader sr, XmlOptions options) throws XmlException {
            return (Element)XmlBeans.getContextTypeLoader().parse(sr, type, options);
        }

        public static Element parse(Node node) throws XmlException {
            return (Element)XmlBeans.getContextTypeLoader().parse(node, type, null);
        }

        public static Element parse(Node node, XmlOptions options) throws XmlException {
            return (Element)XmlBeans.getContextTypeLoader().parse(node, type, options);
        }

        public static Element parse(XMLInputStream xis) throws XmlException, XMLStreamException {
            return (Element)XmlBeans.getContextTypeLoader().parse(xis, type, null);
        }

        public static Element parse(XMLInputStream xis, XmlOptions options) throws XmlException, XMLStreamException {
            return (Element)XmlBeans.getContextTypeLoader().parse(xis, type, options);
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

