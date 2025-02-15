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
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.impl.xb.xsdschema.All;
import org.apache.xmlbeans.impl.xb.xsdschema.ExplicitGroup;
import org.apache.xmlbeans.impl.xb.xsdschema.Group;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.w3c.dom.Node;

public interface RealGroup
extends Group {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(RealGroup.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sXMLSCHEMA").resolveHandle("realgroup1f64type");

    @Override
    public All[] getAllArray();

    @Override
    public All getAllArray(int var1);

    @Override
    public int sizeOfAllArray();

    @Override
    public void setAllArray(All[] var1);

    @Override
    public void setAllArray(int var1, All var2);

    @Override
    public All insertNewAll(int var1);

    @Override
    public All addNewAll();

    @Override
    public void removeAll(int var1);

    @Override
    public ExplicitGroup[] getChoiceArray();

    @Override
    public ExplicitGroup getChoiceArray(int var1);

    @Override
    public int sizeOfChoiceArray();

    @Override
    public void setChoiceArray(ExplicitGroup[] var1);

    @Override
    public void setChoiceArray(int var1, ExplicitGroup var2);

    @Override
    public ExplicitGroup insertNewChoice(int var1);

    @Override
    public ExplicitGroup addNewChoice();

    @Override
    public void removeChoice(int var1);

    @Override
    public ExplicitGroup[] getSequenceArray();

    @Override
    public ExplicitGroup getSequenceArray(int var1);

    @Override
    public int sizeOfSequenceArray();

    @Override
    public void setSequenceArray(ExplicitGroup[] var1);

    @Override
    public void setSequenceArray(int var1, ExplicitGroup var2);

    @Override
    public ExplicitGroup insertNewSequence(int var1);

    @Override
    public ExplicitGroup addNewSequence();

    @Override
    public void removeSequence(int var1);

    public static final class Factory {
        public static RealGroup newInstance() {
            return (RealGroup)XmlBeans.getContextTypeLoader().newInstance(type, null);
        }

        public static RealGroup newInstance(XmlOptions options) {
            return (RealGroup)XmlBeans.getContextTypeLoader().newInstance(type, options);
        }

        public static RealGroup parse(String xmlAsString) throws XmlException {
            return (RealGroup)XmlBeans.getContextTypeLoader().parse(xmlAsString, type, null);
        }

        public static RealGroup parse(String xmlAsString, XmlOptions options) throws XmlException {
            return (RealGroup)XmlBeans.getContextTypeLoader().parse(xmlAsString, type, options);
        }

        public static RealGroup parse(File file) throws XmlException, IOException {
            return (RealGroup)XmlBeans.getContextTypeLoader().parse(file, type, null);
        }

        public static RealGroup parse(File file, XmlOptions options) throws XmlException, IOException {
            return (RealGroup)XmlBeans.getContextTypeLoader().parse(file, type, options);
        }

        public static RealGroup parse(URL u) throws XmlException, IOException {
            return (RealGroup)XmlBeans.getContextTypeLoader().parse(u, type, null);
        }

        public static RealGroup parse(URL u, XmlOptions options) throws XmlException, IOException {
            return (RealGroup)XmlBeans.getContextTypeLoader().parse(u, type, options);
        }

        public static RealGroup parse(InputStream is) throws XmlException, IOException {
            return (RealGroup)XmlBeans.getContextTypeLoader().parse(is, type, null);
        }

        public static RealGroup parse(InputStream is, XmlOptions options) throws XmlException, IOException {
            return (RealGroup)XmlBeans.getContextTypeLoader().parse(is, type, options);
        }

        public static RealGroup parse(Reader r) throws XmlException, IOException {
            return (RealGroup)XmlBeans.getContextTypeLoader().parse(r, type, null);
        }

        public static RealGroup parse(Reader r, XmlOptions options) throws XmlException, IOException {
            return (RealGroup)XmlBeans.getContextTypeLoader().parse(r, type, options);
        }

        public static RealGroup parse(XMLStreamReader sr) throws XmlException {
            return (RealGroup)XmlBeans.getContextTypeLoader().parse(sr, type, null);
        }

        public static RealGroup parse(XMLStreamReader sr, XmlOptions options) throws XmlException {
            return (RealGroup)XmlBeans.getContextTypeLoader().parse(sr, type, options);
        }

        public static RealGroup parse(Node node) throws XmlException {
            return (RealGroup)XmlBeans.getContextTypeLoader().parse(node, type, null);
        }

        public static RealGroup parse(Node node, XmlOptions options) throws XmlException {
            return (RealGroup)XmlBeans.getContextTypeLoader().parse(node, type, options);
        }

        public static RealGroup parse(XMLInputStream xis) throws XmlException, XMLStreamException {
            return (RealGroup)XmlBeans.getContextTypeLoader().parse(xis, type, null);
        }

        public static RealGroup parse(XMLInputStream xis, XmlOptions options) throws XmlException, XMLStreamException {
            return (RealGroup)XmlBeans.getContextTypeLoader().parse(xis, type, options);
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

