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
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCfRule;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExtensionList;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STSqref;
import org.w3c.dom.Node;

public interface CTConditionalFormatting
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTConditionalFormatting.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctconditionalformatting0deatype");

    public List<CTCfRule> getCfRuleList();

    @Deprecated
    public CTCfRule[] getCfRuleArray();

    public CTCfRule getCfRuleArray(int var1);

    public int sizeOfCfRuleArray();

    public void setCfRuleArray(CTCfRule[] var1);

    public void setCfRuleArray(int var1, CTCfRule var2);

    public CTCfRule insertNewCfRule(int var1);

    public CTCfRule addNewCfRule();

    public void removeCfRule(int var1);

    public CTExtensionList getExtLst();

    public boolean isSetExtLst();

    public void setExtLst(CTExtensionList var1);

    public CTExtensionList addNewExtLst();

    public void unsetExtLst();

    public boolean getPivot();

    public XmlBoolean xgetPivot();

    public boolean isSetPivot();

    public void setPivot(boolean var1);

    public void xsetPivot(XmlBoolean var1);

    public void unsetPivot();

    public List getSqref();

    public STSqref xgetSqref();

    public boolean isSetSqref();

    public void setSqref(List var1);

    public void xsetSqref(STSqref var1);

    public void unsetSqref();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTConditionalFormatting.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTConditionalFormatting newInstance() {
            return (CTConditionalFormatting)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTConditionalFormatting newInstance(XmlOptions xmlOptions) {
            return (CTConditionalFormatting)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTConditionalFormatting parse(String string) throws XmlException {
            return (CTConditionalFormatting)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTConditionalFormatting parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTConditionalFormatting)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTConditionalFormatting parse(File file) throws XmlException, IOException {
            return (CTConditionalFormatting)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTConditionalFormatting parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTConditionalFormatting)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTConditionalFormatting parse(URL uRL) throws XmlException, IOException {
            return (CTConditionalFormatting)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTConditionalFormatting parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTConditionalFormatting)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTConditionalFormatting parse(InputStream inputStream) throws XmlException, IOException {
            return (CTConditionalFormatting)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTConditionalFormatting parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTConditionalFormatting)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTConditionalFormatting parse(Reader reader) throws XmlException, IOException {
            return (CTConditionalFormatting)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTConditionalFormatting parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTConditionalFormatting)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTConditionalFormatting parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTConditionalFormatting)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTConditionalFormatting parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTConditionalFormatting)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTConditionalFormatting parse(Node node) throws XmlException {
            return (CTConditionalFormatting)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTConditionalFormatting parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTConditionalFormatting)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTConditionalFormatting parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTConditionalFormatting)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTConditionalFormatting parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTConditionalFormatting)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

