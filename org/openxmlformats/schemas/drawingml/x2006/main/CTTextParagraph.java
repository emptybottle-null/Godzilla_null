/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.drawingml.x2006.main;

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
import org.openxmlformats.schemas.drawingml.x2006.main.CTRegularTextRun;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextField;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextLineBreak;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties;
import org.w3c.dom.Node;

public interface CTTextParagraph
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTTextParagraph.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("cttextparagraphcaf2type");

    public CTTextParagraphProperties getPPr();

    public boolean isSetPPr();

    public void setPPr(CTTextParagraphProperties var1);

    public CTTextParagraphProperties addNewPPr();

    public void unsetPPr();

    public List<CTRegularTextRun> getRList();

    @Deprecated
    public CTRegularTextRun[] getRArray();

    public CTRegularTextRun getRArray(int var1);

    public int sizeOfRArray();

    public void setRArray(CTRegularTextRun[] var1);

    public void setRArray(int var1, CTRegularTextRun var2);

    public CTRegularTextRun insertNewR(int var1);

    public CTRegularTextRun addNewR();

    public void removeR(int var1);

    public List<CTTextLineBreak> getBrList();

    @Deprecated
    public CTTextLineBreak[] getBrArray();

    public CTTextLineBreak getBrArray(int var1);

    public int sizeOfBrArray();

    public void setBrArray(CTTextLineBreak[] var1);

    public void setBrArray(int var1, CTTextLineBreak var2);

    public CTTextLineBreak insertNewBr(int var1);

    public CTTextLineBreak addNewBr();

    public void removeBr(int var1);

    public List<CTTextField> getFldList();

    @Deprecated
    public CTTextField[] getFldArray();

    public CTTextField getFldArray(int var1);

    public int sizeOfFldArray();

    public void setFldArray(CTTextField[] var1);

    public void setFldArray(int var1, CTTextField var2);

    public CTTextField insertNewFld(int var1);

    public CTTextField addNewFld();

    public void removeFld(int var1);

    public CTTextCharacterProperties getEndParaRPr();

    public boolean isSetEndParaRPr();

    public void setEndParaRPr(CTTextCharacterProperties var1);

    public CTTextCharacterProperties addNewEndParaRPr();

    public void unsetEndParaRPr();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTTextParagraph.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTTextParagraph newInstance() {
            return (CTTextParagraph)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTTextParagraph newInstance(XmlOptions xmlOptions) {
            return (CTTextParagraph)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTTextParagraph parse(String string) throws XmlException {
            return (CTTextParagraph)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTTextParagraph parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTTextParagraph)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTTextParagraph parse(File file) throws XmlException, IOException {
            return (CTTextParagraph)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTTextParagraph parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTTextParagraph)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTTextParagraph parse(URL uRL) throws XmlException, IOException {
            return (CTTextParagraph)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTTextParagraph parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTTextParagraph)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTTextParagraph parse(InputStream inputStream) throws XmlException, IOException {
            return (CTTextParagraph)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTTextParagraph parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTTextParagraph)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTTextParagraph parse(Reader reader) throws XmlException, IOException {
            return (CTTextParagraph)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTTextParagraph parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTTextParagraph)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTTextParagraph parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTTextParagraph)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTTextParagraph parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTTextParagraph)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTTextParagraph parse(Node node) throws XmlException {
            return (CTTextParagraph)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTTextParagraph parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTTextParagraph)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTTextParagraph parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTTextParagraph)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTTextParagraph parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTTextParagraph)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

