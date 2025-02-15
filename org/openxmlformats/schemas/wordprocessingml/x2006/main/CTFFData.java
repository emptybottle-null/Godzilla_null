/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFFDDList
 *  org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFFHelpText
 *  org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFFName
 *  org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFFStatusText
 *  org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFFTextInput
 *  org.openxmlformats.schemas.wordprocessingml.x2006.main.CTMacroName
 */
package org.openxmlformats.schemas.wordprocessingml.x2006.main;

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
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFFCheckBox;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFFDDList;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFFHelpText;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFFName;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFFStatusText;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFFTextInput;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTMacroName;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTOnOff;
import org.w3c.dom.Node;

public interface CTFFData
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTFFData.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctffdataaa7etype");

    public List<CTFFName> getNameList();

    @Deprecated
    public CTFFName[] getNameArray();

    public CTFFName getNameArray(int var1);

    public int sizeOfNameArray();

    public void setNameArray(CTFFName[] var1);

    public void setNameArray(int var1, CTFFName var2);

    public CTFFName insertNewName(int var1);

    public CTFFName addNewName();

    public void removeName(int var1);

    public List<CTOnOff> getEnabledList();

    @Deprecated
    public CTOnOff[] getEnabledArray();

    public CTOnOff getEnabledArray(int var1);

    public int sizeOfEnabledArray();

    public void setEnabledArray(CTOnOff[] var1);

    public void setEnabledArray(int var1, CTOnOff var2);

    public CTOnOff insertNewEnabled(int var1);

    public CTOnOff addNewEnabled();

    public void removeEnabled(int var1);

    public List<CTOnOff> getCalcOnExitList();

    @Deprecated
    public CTOnOff[] getCalcOnExitArray();

    public CTOnOff getCalcOnExitArray(int var1);

    public int sizeOfCalcOnExitArray();

    public void setCalcOnExitArray(CTOnOff[] var1);

    public void setCalcOnExitArray(int var1, CTOnOff var2);

    public CTOnOff insertNewCalcOnExit(int var1);

    public CTOnOff addNewCalcOnExit();

    public void removeCalcOnExit(int var1);

    public List<CTMacroName> getEntryMacroList();

    @Deprecated
    public CTMacroName[] getEntryMacroArray();

    public CTMacroName getEntryMacroArray(int var1);

    public int sizeOfEntryMacroArray();

    public void setEntryMacroArray(CTMacroName[] var1);

    public void setEntryMacroArray(int var1, CTMacroName var2);

    public CTMacroName insertNewEntryMacro(int var1);

    public CTMacroName addNewEntryMacro();

    public void removeEntryMacro(int var1);

    public List<CTMacroName> getExitMacroList();

    @Deprecated
    public CTMacroName[] getExitMacroArray();

    public CTMacroName getExitMacroArray(int var1);

    public int sizeOfExitMacroArray();

    public void setExitMacroArray(CTMacroName[] var1);

    public void setExitMacroArray(int var1, CTMacroName var2);

    public CTMacroName insertNewExitMacro(int var1);

    public CTMacroName addNewExitMacro();

    public void removeExitMacro(int var1);

    public List<CTFFHelpText> getHelpTextList();

    @Deprecated
    public CTFFHelpText[] getHelpTextArray();

    public CTFFHelpText getHelpTextArray(int var1);

    public int sizeOfHelpTextArray();

    public void setHelpTextArray(CTFFHelpText[] var1);

    public void setHelpTextArray(int var1, CTFFHelpText var2);

    public CTFFHelpText insertNewHelpText(int var1);

    public CTFFHelpText addNewHelpText();

    public void removeHelpText(int var1);

    public List<CTFFStatusText> getStatusTextList();

    @Deprecated
    public CTFFStatusText[] getStatusTextArray();

    public CTFFStatusText getStatusTextArray(int var1);

    public int sizeOfStatusTextArray();

    public void setStatusTextArray(CTFFStatusText[] var1);

    public void setStatusTextArray(int var1, CTFFStatusText var2);

    public CTFFStatusText insertNewStatusText(int var1);

    public CTFFStatusText addNewStatusText();

    public void removeStatusText(int var1);

    public List<CTFFCheckBox> getCheckBoxList();

    @Deprecated
    public CTFFCheckBox[] getCheckBoxArray();

    public CTFFCheckBox getCheckBoxArray(int var1);

    public int sizeOfCheckBoxArray();

    public void setCheckBoxArray(CTFFCheckBox[] var1);

    public void setCheckBoxArray(int var1, CTFFCheckBox var2);

    public CTFFCheckBox insertNewCheckBox(int var1);

    public CTFFCheckBox addNewCheckBox();

    public void removeCheckBox(int var1);

    public List<CTFFDDList> getDdListList();

    @Deprecated
    public CTFFDDList[] getDdListArray();

    public CTFFDDList getDdListArray(int var1);

    public int sizeOfDdListArray();

    public void setDdListArray(CTFFDDList[] var1);

    public void setDdListArray(int var1, CTFFDDList var2);

    public CTFFDDList insertNewDdList(int var1);

    public CTFFDDList addNewDdList();

    public void removeDdList(int var1);

    public List<CTFFTextInput> getTextInputList();

    @Deprecated
    public CTFFTextInput[] getTextInputArray();

    public CTFFTextInput getTextInputArray(int var1);

    public int sizeOfTextInputArray();

    public void setTextInputArray(CTFFTextInput[] var1);

    public void setTextInputArray(int var1, CTFFTextInput var2);

    public CTFFTextInput insertNewTextInput(int var1);

    public CTFFTextInput addNewTextInput();

    public void removeTextInput(int var1);

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTFFData.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTFFData newInstance() {
            return (CTFFData)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTFFData newInstance(XmlOptions xmlOptions) {
            return (CTFFData)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTFFData parse(String string) throws XmlException {
            return (CTFFData)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTFFData parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTFFData)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTFFData parse(File file) throws XmlException, IOException {
            return (CTFFData)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTFFData parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTFFData)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTFFData parse(URL uRL) throws XmlException, IOException {
            return (CTFFData)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTFFData parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTFFData)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTFFData parse(InputStream inputStream) throws XmlException, IOException {
            return (CTFFData)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTFFData parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTFFData)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTFFData parse(Reader reader) throws XmlException, IOException {
            return (CTFFData)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTFFData parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTFFData)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTFFData parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTFFData)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTFFData parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTFFData)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTFFData parse(Node node) throws XmlException {
            return (CTFFData)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTFFData parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTFFData)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTFFData parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTFFData)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTFFData parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTFFData)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

