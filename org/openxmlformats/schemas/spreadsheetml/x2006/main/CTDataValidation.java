/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.spreadsheetml.x2006.main.STDataValidationImeMode
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
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STDataValidationErrorStyle;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STDataValidationImeMode;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STDataValidationOperator;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STDataValidationType;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STFormula;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STSqref;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STXstring;
import org.w3c.dom.Node;

public interface CTDataValidation
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTDataValidation.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctdatavalidation9d0ctype");

    public String getFormula1();

    public STFormula xgetFormula1();

    public boolean isSetFormula1();

    public void setFormula1(String var1);

    public void xsetFormula1(STFormula var1);

    public void unsetFormula1();

    public String getFormula2();

    public STFormula xgetFormula2();

    public boolean isSetFormula2();

    public void setFormula2(String var1);

    public void xsetFormula2(STFormula var1);

    public void unsetFormula2();

    public STDataValidationType.Enum getType();

    public STDataValidationType xgetType();

    public boolean isSetType();

    public void setType(STDataValidationType.Enum var1);

    public void xsetType(STDataValidationType var1);

    public void unsetType();

    public STDataValidationErrorStyle.Enum getErrorStyle();

    public STDataValidationErrorStyle xgetErrorStyle();

    public boolean isSetErrorStyle();

    public void setErrorStyle(STDataValidationErrorStyle.Enum var1);

    public void xsetErrorStyle(STDataValidationErrorStyle var1);

    public void unsetErrorStyle();

    public STDataValidationImeMode.Enum getImeMode();

    public STDataValidationImeMode xgetImeMode();

    public boolean isSetImeMode();

    public void setImeMode(STDataValidationImeMode.Enum var1);

    public void xsetImeMode(STDataValidationImeMode var1);

    public void unsetImeMode();

    public STDataValidationOperator.Enum getOperator();

    public STDataValidationOperator xgetOperator();

    public boolean isSetOperator();

    public void setOperator(STDataValidationOperator.Enum var1);

    public void xsetOperator(STDataValidationOperator var1);

    public void unsetOperator();

    public boolean getAllowBlank();

    public XmlBoolean xgetAllowBlank();

    public boolean isSetAllowBlank();

    public void setAllowBlank(boolean var1);

    public void xsetAllowBlank(XmlBoolean var1);

    public void unsetAllowBlank();

    public boolean getShowDropDown();

    public XmlBoolean xgetShowDropDown();

    public boolean isSetShowDropDown();

    public void setShowDropDown(boolean var1);

    public void xsetShowDropDown(XmlBoolean var1);

    public void unsetShowDropDown();

    public boolean getShowInputMessage();

    public XmlBoolean xgetShowInputMessage();

    public boolean isSetShowInputMessage();

    public void setShowInputMessage(boolean var1);

    public void xsetShowInputMessage(XmlBoolean var1);

    public void unsetShowInputMessage();

    public boolean getShowErrorMessage();

    public XmlBoolean xgetShowErrorMessage();

    public boolean isSetShowErrorMessage();

    public void setShowErrorMessage(boolean var1);

    public void xsetShowErrorMessage(XmlBoolean var1);

    public void unsetShowErrorMessage();

    public String getErrorTitle();

    public STXstring xgetErrorTitle();

    public boolean isSetErrorTitle();

    public void setErrorTitle(String var1);

    public void xsetErrorTitle(STXstring var1);

    public void unsetErrorTitle();

    public String getError();

    public STXstring xgetError();

    public boolean isSetError();

    public void setError(String var1);

    public void xsetError(STXstring var1);

    public void unsetError();

    public String getPromptTitle();

    public STXstring xgetPromptTitle();

    public boolean isSetPromptTitle();

    public void setPromptTitle(String var1);

    public void xsetPromptTitle(STXstring var1);

    public void unsetPromptTitle();

    public String getPrompt();

    public STXstring xgetPrompt();

    public boolean isSetPrompt();

    public void setPrompt(String var1);

    public void xsetPrompt(STXstring var1);

    public void unsetPrompt();

    public List getSqref();

    public STSqref xgetSqref();

    public void setSqref(List var1);

    public void xsetSqref(STSqref var1);

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTDataValidation.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTDataValidation newInstance() {
            return (CTDataValidation)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTDataValidation newInstance(XmlOptions xmlOptions) {
            return (CTDataValidation)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTDataValidation parse(String string) throws XmlException {
            return (CTDataValidation)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTDataValidation parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTDataValidation)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTDataValidation parse(File file) throws XmlException, IOException {
            return (CTDataValidation)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTDataValidation parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTDataValidation)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTDataValidation parse(URL uRL) throws XmlException, IOException {
            return (CTDataValidation)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTDataValidation parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTDataValidation)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTDataValidation parse(InputStream inputStream) throws XmlException, IOException {
            return (CTDataValidation)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTDataValidation parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTDataValidation)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTDataValidation parse(Reader reader) throws XmlException, IOException {
            return (CTDataValidation)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTDataValidation parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTDataValidation)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTDataValidation parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTDataValidation)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTDataValidation parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTDataValidation)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTDataValidation parse(Node node) throws XmlException {
            return (CTDataValidation)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTDataValidation parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTDataValidation)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTDataValidation parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTDataValidation)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTDataValidation parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTDataValidation)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

