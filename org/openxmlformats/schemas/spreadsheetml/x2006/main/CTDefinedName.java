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
import javax.xml.stream.XMLStreamReader;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SchemaTypeLoader;
import org.apache.xmlbeans.XmlBeans;
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STFormula;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STXstring;
import org.w3c.dom.Node;

public interface CTDefinedName
extends STFormula {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTDefinedName.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctdefinedname9413type");

    public String getName();

    public STXstring xgetName();

    public void setName(String var1);

    public void xsetName(STXstring var1);

    public String getComment();

    public STXstring xgetComment();

    public boolean isSetComment();

    public void setComment(String var1);

    public void xsetComment(STXstring var1);

    public void unsetComment();

    public String getCustomMenu();

    public STXstring xgetCustomMenu();

    public boolean isSetCustomMenu();

    public void setCustomMenu(String var1);

    public void xsetCustomMenu(STXstring var1);

    public void unsetCustomMenu();

    public String getDescription();

    public STXstring xgetDescription();

    public boolean isSetDescription();

    public void setDescription(String var1);

    public void xsetDescription(STXstring var1);

    public void unsetDescription();

    public String getHelp();

    public STXstring xgetHelp();

    public boolean isSetHelp();

    public void setHelp(String var1);

    public void xsetHelp(STXstring var1);

    public void unsetHelp();

    public String getStatusBar();

    public STXstring xgetStatusBar();

    public boolean isSetStatusBar();

    public void setStatusBar(String var1);

    public void xsetStatusBar(STXstring var1);

    public void unsetStatusBar();

    public long getLocalSheetId();

    public XmlUnsignedInt xgetLocalSheetId();

    public boolean isSetLocalSheetId();

    public void setLocalSheetId(long var1);

    public void xsetLocalSheetId(XmlUnsignedInt var1);

    public void unsetLocalSheetId();

    public boolean getHidden();

    public XmlBoolean xgetHidden();

    public boolean isSetHidden();

    public void setHidden(boolean var1);

    public void xsetHidden(XmlBoolean var1);

    public void unsetHidden();

    public boolean getFunction();

    public XmlBoolean xgetFunction();

    public boolean isSetFunction();

    public void setFunction(boolean var1);

    public void xsetFunction(XmlBoolean var1);

    public void unsetFunction();

    public boolean getVbProcedure();

    public XmlBoolean xgetVbProcedure();

    public boolean isSetVbProcedure();

    public void setVbProcedure(boolean var1);

    public void xsetVbProcedure(XmlBoolean var1);

    public void unsetVbProcedure();

    public boolean getXlm();

    public XmlBoolean xgetXlm();

    public boolean isSetXlm();

    public void setXlm(boolean var1);

    public void xsetXlm(XmlBoolean var1);

    public void unsetXlm();

    public long getFunctionGroupId();

    public XmlUnsignedInt xgetFunctionGroupId();

    public boolean isSetFunctionGroupId();

    public void setFunctionGroupId(long var1);

    public void xsetFunctionGroupId(XmlUnsignedInt var1);

    public void unsetFunctionGroupId();

    public String getShortcutKey();

    public STXstring xgetShortcutKey();

    public boolean isSetShortcutKey();

    public void setShortcutKey(String var1);

    public void xsetShortcutKey(STXstring var1);

    public void unsetShortcutKey();

    public boolean getPublishToServer();

    public XmlBoolean xgetPublishToServer();

    public boolean isSetPublishToServer();

    public void setPublishToServer(boolean var1);

    public void xsetPublishToServer(XmlBoolean var1);

    public void unsetPublishToServer();

    public boolean getWorkbookParameter();

    public XmlBoolean xgetWorkbookParameter();

    public boolean isSetWorkbookParameter();

    public void setWorkbookParameter(boolean var1);

    public void xsetWorkbookParameter(XmlBoolean var1);

    public void unsetWorkbookParameter();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTDefinedName.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTDefinedName newInstance() {
            return (CTDefinedName)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTDefinedName newInstance(XmlOptions xmlOptions) {
            return (CTDefinedName)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTDefinedName parse(String string) throws XmlException {
            return (CTDefinedName)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTDefinedName parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTDefinedName)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTDefinedName parse(File file) throws XmlException, IOException {
            return (CTDefinedName)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTDefinedName parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTDefinedName)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTDefinedName parse(URL uRL) throws XmlException, IOException {
            return (CTDefinedName)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTDefinedName parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTDefinedName)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTDefinedName parse(InputStream inputStream) throws XmlException, IOException {
            return (CTDefinedName)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTDefinedName parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTDefinedName)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTDefinedName parse(Reader reader) throws XmlException, IOException {
            return (CTDefinedName)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTDefinedName parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTDefinedName)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTDefinedName parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTDefinedName)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTDefinedName parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTDefinedName)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTDefinedName parse(Node node) throws XmlException {
            return (CTDefinedName)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTDefinedName parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTDefinedName)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTDefinedName parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTDefinedName)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTDefinedName parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTDefinedName)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

