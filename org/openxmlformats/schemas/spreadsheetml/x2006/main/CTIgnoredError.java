/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
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
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STSqref;
import org.w3c.dom.Node;

public interface CTIgnoredError
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTIgnoredError.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctignorederrorc51ftype");

    public List getSqref();

    public STSqref xgetSqref();

    public void setSqref(List var1);

    public void xsetSqref(STSqref var1);

    public boolean getEvalError();

    public XmlBoolean xgetEvalError();

    public boolean isSetEvalError();

    public void setEvalError(boolean var1);

    public void xsetEvalError(XmlBoolean var1);

    public void unsetEvalError();

    public boolean getTwoDigitTextYear();

    public XmlBoolean xgetTwoDigitTextYear();

    public boolean isSetTwoDigitTextYear();

    public void setTwoDigitTextYear(boolean var1);

    public void xsetTwoDigitTextYear(XmlBoolean var1);

    public void unsetTwoDigitTextYear();

    public boolean getNumberStoredAsText();

    public XmlBoolean xgetNumberStoredAsText();

    public boolean isSetNumberStoredAsText();

    public void setNumberStoredAsText(boolean var1);

    public void xsetNumberStoredAsText(XmlBoolean var1);

    public void unsetNumberStoredAsText();

    public boolean getFormula();

    public XmlBoolean xgetFormula();

    public boolean isSetFormula();

    public void setFormula(boolean var1);

    public void xsetFormula(XmlBoolean var1);

    public void unsetFormula();

    public boolean getFormulaRange();

    public XmlBoolean xgetFormulaRange();

    public boolean isSetFormulaRange();

    public void setFormulaRange(boolean var1);

    public void xsetFormulaRange(XmlBoolean var1);

    public void unsetFormulaRange();

    public boolean getUnlockedFormula();

    public XmlBoolean xgetUnlockedFormula();

    public boolean isSetUnlockedFormula();

    public void setUnlockedFormula(boolean var1);

    public void xsetUnlockedFormula(XmlBoolean var1);

    public void unsetUnlockedFormula();

    public boolean getEmptyCellReference();

    public XmlBoolean xgetEmptyCellReference();

    public boolean isSetEmptyCellReference();

    public void setEmptyCellReference(boolean var1);

    public void xsetEmptyCellReference(XmlBoolean var1);

    public void unsetEmptyCellReference();

    public boolean getListDataValidation();

    public XmlBoolean xgetListDataValidation();

    public boolean isSetListDataValidation();

    public void setListDataValidation(boolean var1);

    public void xsetListDataValidation(XmlBoolean var1);

    public void unsetListDataValidation();

    public boolean getCalculatedColumn();

    public XmlBoolean xgetCalculatedColumn();

    public boolean isSetCalculatedColumn();

    public void setCalculatedColumn(boolean var1);

    public void xsetCalculatedColumn(XmlBoolean var1);

    public void unsetCalculatedColumn();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTIgnoredError.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTIgnoredError newInstance() {
            return (CTIgnoredError)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTIgnoredError newInstance(XmlOptions xmlOptions) {
            return (CTIgnoredError)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTIgnoredError parse(String string) throws XmlException {
            return (CTIgnoredError)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTIgnoredError parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTIgnoredError)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTIgnoredError parse(File file) throws XmlException, IOException {
            return (CTIgnoredError)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTIgnoredError parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTIgnoredError)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTIgnoredError parse(URL uRL) throws XmlException, IOException {
            return (CTIgnoredError)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTIgnoredError parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTIgnoredError)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTIgnoredError parse(InputStream inputStream) throws XmlException, IOException {
            return (CTIgnoredError)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTIgnoredError parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTIgnoredError)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTIgnoredError parse(Reader reader) throws XmlException, IOException {
            return (CTIgnoredError)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTIgnoredError parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTIgnoredError)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTIgnoredError parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTIgnoredError)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTIgnoredError parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTIgnoredError)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTIgnoredError parse(Node node) throws XmlException {
            return (CTIgnoredError)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTIgnoredError parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTIgnoredError)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTIgnoredError parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTIgnoredError)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTIgnoredError parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTIgnoredError)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

