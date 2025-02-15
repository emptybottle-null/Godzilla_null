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
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlNCName;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.impl.xb.xsdschema.Annotated;
import org.apache.xmlbeans.impl.xb.xsdschema.ListDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.RestrictionDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.SimpleDerivationSet;
import org.apache.xmlbeans.impl.xb.xsdschema.UnionDocument;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.w3c.dom.Node;

public interface SimpleType
extends Annotated {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(SimpleType.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sXMLSCHEMA").resolveHandle("simpletype0707type");

    public RestrictionDocument.Restriction getRestriction();

    public boolean isSetRestriction();

    public void setRestriction(RestrictionDocument.Restriction var1);

    public RestrictionDocument.Restriction addNewRestriction();

    public void unsetRestriction();

    public ListDocument.List getList();

    public boolean isSetList();

    public void setList(ListDocument.List var1);

    public ListDocument.List addNewList();

    public void unsetList();

    public UnionDocument.Union getUnion();

    public boolean isSetUnion();

    public void setUnion(UnionDocument.Union var1);

    public UnionDocument.Union addNewUnion();

    public void unsetUnion();

    public Object getFinal();

    public SimpleDerivationSet xgetFinal();

    public boolean isSetFinal();

    public void setFinal(Object var1);

    public void xsetFinal(SimpleDerivationSet var1);

    public void unsetFinal();

    public String getName();

    public XmlNCName xgetName();

    public boolean isSetName();

    public void setName(String var1);

    public void xsetName(XmlNCName var1);

    public void unsetName();

    public static final class Factory {
        public static SimpleType newInstance() {
            return (SimpleType)XmlBeans.getContextTypeLoader().newInstance(type, null);
        }

        public static SimpleType newInstance(XmlOptions options) {
            return (SimpleType)XmlBeans.getContextTypeLoader().newInstance(type, options);
        }

        public static SimpleType parse(String xmlAsString) throws XmlException {
            return (SimpleType)XmlBeans.getContextTypeLoader().parse(xmlAsString, type, null);
        }

        public static SimpleType parse(String xmlAsString, XmlOptions options) throws XmlException {
            return (SimpleType)XmlBeans.getContextTypeLoader().parse(xmlAsString, type, options);
        }

        public static SimpleType parse(File file) throws XmlException, IOException {
            return (SimpleType)XmlBeans.getContextTypeLoader().parse(file, type, null);
        }

        public static SimpleType parse(File file, XmlOptions options) throws XmlException, IOException {
            return (SimpleType)XmlBeans.getContextTypeLoader().parse(file, type, options);
        }

        public static SimpleType parse(URL u) throws XmlException, IOException {
            return (SimpleType)XmlBeans.getContextTypeLoader().parse(u, type, null);
        }

        public static SimpleType parse(URL u, XmlOptions options) throws XmlException, IOException {
            return (SimpleType)XmlBeans.getContextTypeLoader().parse(u, type, options);
        }

        public static SimpleType parse(InputStream is) throws XmlException, IOException {
            return (SimpleType)XmlBeans.getContextTypeLoader().parse(is, type, null);
        }

        public static SimpleType parse(InputStream is, XmlOptions options) throws XmlException, IOException {
            return (SimpleType)XmlBeans.getContextTypeLoader().parse(is, type, options);
        }

        public static SimpleType parse(Reader r) throws XmlException, IOException {
            return (SimpleType)XmlBeans.getContextTypeLoader().parse(r, type, null);
        }

        public static SimpleType parse(Reader r, XmlOptions options) throws XmlException, IOException {
            return (SimpleType)XmlBeans.getContextTypeLoader().parse(r, type, options);
        }

        public static SimpleType parse(XMLStreamReader sr) throws XmlException {
            return (SimpleType)XmlBeans.getContextTypeLoader().parse(sr, type, null);
        }

        public static SimpleType parse(XMLStreamReader sr, XmlOptions options) throws XmlException {
            return (SimpleType)XmlBeans.getContextTypeLoader().parse(sr, type, options);
        }

        public static SimpleType parse(Node node) throws XmlException {
            return (SimpleType)XmlBeans.getContextTypeLoader().parse(node, type, null);
        }

        public static SimpleType parse(Node node, XmlOptions options) throws XmlException {
            return (SimpleType)XmlBeans.getContextTypeLoader().parse(node, type, options);
        }

        public static SimpleType parse(XMLInputStream xis) throws XmlException, XMLStreamException {
            return (SimpleType)XmlBeans.getContextTypeLoader().parse(xis, type, null);
        }

        public static SimpleType parse(XMLInputStream xis, XmlOptions options) throws XmlException, XMLStreamException {
            return (SimpleType)XmlBeans.getContextTypeLoader().parse(xis, type, options);
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

