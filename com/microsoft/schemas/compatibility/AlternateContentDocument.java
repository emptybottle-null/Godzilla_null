/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.microsoft.schemas.compatibility;

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
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.w3c.dom.Node;

public interface AlternateContentDocument
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(AlternateContentDocument.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("alternatecontentdd64doctype");

    public AlternateContent getAlternateContent();

    public void setAlternateContent(AlternateContent var1);

    public AlternateContent addNewAlternateContent();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(AlternateContentDocument.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static AlternateContentDocument newInstance() {
            return (AlternateContentDocument)Factory.getTypeLoader().newInstance(type, null);
        }

        public static AlternateContentDocument newInstance(XmlOptions xmlOptions) {
            return (AlternateContentDocument)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static AlternateContentDocument parse(String string) throws XmlException {
            return (AlternateContentDocument)Factory.getTypeLoader().parse(string, type, null);
        }

        public static AlternateContentDocument parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (AlternateContentDocument)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static AlternateContentDocument parse(File file) throws XmlException, IOException {
            return (AlternateContentDocument)Factory.getTypeLoader().parse(file, type, null);
        }

        public static AlternateContentDocument parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (AlternateContentDocument)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static AlternateContentDocument parse(URL uRL) throws XmlException, IOException {
            return (AlternateContentDocument)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static AlternateContentDocument parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (AlternateContentDocument)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static AlternateContentDocument parse(InputStream inputStream) throws XmlException, IOException {
            return (AlternateContentDocument)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static AlternateContentDocument parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (AlternateContentDocument)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static AlternateContentDocument parse(Reader reader) throws XmlException, IOException {
            return (AlternateContentDocument)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static AlternateContentDocument parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (AlternateContentDocument)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static AlternateContentDocument parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (AlternateContentDocument)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static AlternateContentDocument parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (AlternateContentDocument)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static AlternateContentDocument parse(Node node) throws XmlException {
            return (AlternateContentDocument)Factory.getTypeLoader().parse(node, type, null);
        }

        public static AlternateContentDocument parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (AlternateContentDocument)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static AlternateContentDocument parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (AlternateContentDocument)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static AlternateContentDocument parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (AlternateContentDocument)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

    public static interface AlternateContent
    extends XmlObject {
        public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(AlternateContent.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("alternatecontenta8a9elemtype");

        public List<Choice> getChoiceList();

        @Deprecated
        public Choice[] getChoiceArray();

        public Choice getChoiceArray(int var1);

        public int sizeOfChoiceArray();

        public void setChoiceArray(Choice[] var1);

        public void setChoiceArray(int var1, Choice var2);

        public Choice insertNewChoice(int var1);

        public Choice addNewChoice();

        public void removeChoice(int var1);

        public Fallback getFallback();

        public boolean isSetFallback();

        public void setFallback(Fallback var1);

        public Fallback addNewFallback();

        public void unsetFallback();

        public String getIgnorable();

        public XmlString xgetIgnorable();

        public boolean isSetIgnorable();

        public void setIgnorable(String var1);

        public void xsetIgnorable(XmlString var1);

        public void unsetIgnorable();

        public String getMustUnderstand();

        public XmlString xgetMustUnderstand();

        public boolean isSetMustUnderstand();

        public void setMustUnderstand(String var1);

        public void xsetMustUnderstand(XmlString var1);

        public void unsetMustUnderstand();

        public String getProcessContent();

        public XmlString xgetProcessContent();

        public boolean isSetProcessContent();

        public void setProcessContent(String var1);

        public void xsetProcessContent(XmlString var1);

        public void unsetProcessContent();

        public static final class Factory {
            private static SoftReference<SchemaTypeLoader> typeLoader;

            private static synchronized SchemaTypeLoader getTypeLoader() {
                SchemaTypeLoader schemaTypeLoader;
                SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
                if (schemaTypeLoader == null) {
                    schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(AlternateContent.class.getClassLoader());
                    typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
                }
                return schemaTypeLoader;
            }

            public static AlternateContent newInstance() {
                return (AlternateContent)Factory.getTypeLoader().newInstance(type, null);
            }

            public static AlternateContent newInstance(XmlOptions xmlOptions) {
                return (AlternateContent)Factory.getTypeLoader().newInstance(type, xmlOptions);
            }

            private Factory() {
            }
        }

        public static interface Fallback
        extends XmlObject {
            public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(Fallback.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("fallback4cc7elemtype");

            public String getIgnorable();

            public XmlString xgetIgnorable();

            public boolean isSetIgnorable();

            public void setIgnorable(String var1);

            public void xsetIgnorable(XmlString var1);

            public void unsetIgnorable();

            public String getMustUnderstand();

            public XmlString xgetMustUnderstand();

            public boolean isSetMustUnderstand();

            public void setMustUnderstand(String var1);

            public void xsetMustUnderstand(XmlString var1);

            public void unsetMustUnderstand();

            public String getProcessContent();

            public XmlString xgetProcessContent();

            public boolean isSetProcessContent();

            public void setProcessContent(String var1);

            public void xsetProcessContent(XmlString var1);

            public void unsetProcessContent();

            public static final class Factory {
                private static SoftReference<SchemaTypeLoader> typeLoader;

                private static synchronized SchemaTypeLoader getTypeLoader() {
                    SchemaTypeLoader schemaTypeLoader;
                    SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
                    if (schemaTypeLoader == null) {
                        schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(Fallback.class.getClassLoader());
                        typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
                    }
                    return schemaTypeLoader;
                }

                public static Fallback newInstance() {
                    return (Fallback)Factory.getTypeLoader().newInstance(type, null);
                }

                public static Fallback newInstance(XmlOptions xmlOptions) {
                    return (Fallback)Factory.getTypeLoader().newInstance(type, xmlOptions);
                }

                private Factory() {
                }
            }
        }

        public static interface Choice
        extends XmlObject {
            public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(Choice.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("choice69c6elemtype");

            public String getRequires();

            public XmlString xgetRequires();

            public void setRequires(String var1);

            public void xsetRequires(XmlString var1);

            public String getIgnorable();

            public XmlString xgetIgnorable();

            public boolean isSetIgnorable();

            public void setIgnorable(String var1);

            public void xsetIgnorable(XmlString var1);

            public void unsetIgnorable();

            public String getMustUnderstand();

            public XmlString xgetMustUnderstand();

            public boolean isSetMustUnderstand();

            public void setMustUnderstand(String var1);

            public void xsetMustUnderstand(XmlString var1);

            public void unsetMustUnderstand();

            public String getProcessContent();

            public XmlString xgetProcessContent();

            public boolean isSetProcessContent();

            public void setProcessContent(String var1);

            public void xsetProcessContent(XmlString var1);

            public void unsetProcessContent();

            public static final class Factory {
                private static SoftReference<SchemaTypeLoader> typeLoader;

                private static synchronized SchemaTypeLoader getTypeLoader() {
                    SchemaTypeLoader schemaTypeLoader;
                    SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
                    if (schemaTypeLoader == null) {
                        schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(Choice.class.getClassLoader());
                        typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
                    }
                    return schemaTypeLoader;
                }

                public static Choice newInstance() {
                    return (Choice)Factory.getTypeLoader().newInstance(type, null);
                }

                public static Choice newInstance(XmlOptions xmlOptions) {
                    return (Choice)Factory.getTypeLoader().newInstance(type, xmlOptions);
                }

                private Factory() {
                }
            }
        }
    }
}

