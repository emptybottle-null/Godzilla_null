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
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPhoneticPr;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPhoneticRun;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRElt;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STXstring;
import org.w3c.dom.Node;

public interface CTRst
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTRst.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctrsta472type");

    public String getT();

    public STXstring xgetT();

    public boolean isSetT();

    public void setT(String var1);

    public void xsetT(STXstring var1);

    public void unsetT();

    public List<CTRElt> getRList();

    @Deprecated
    public CTRElt[] getRArray();

    public CTRElt getRArray(int var1);

    public int sizeOfRArray();

    public void setRArray(CTRElt[] var1);

    public void setRArray(int var1, CTRElt var2);

    public CTRElt insertNewR(int var1);

    public CTRElt addNewR();

    public void removeR(int var1);

    public List<CTPhoneticRun> getRPhList();

    @Deprecated
    public CTPhoneticRun[] getRPhArray();

    public CTPhoneticRun getRPhArray(int var1);

    public int sizeOfRPhArray();

    public void setRPhArray(CTPhoneticRun[] var1);

    public void setRPhArray(int var1, CTPhoneticRun var2);

    public CTPhoneticRun insertNewRPh(int var1);

    public CTPhoneticRun addNewRPh();

    public void removeRPh(int var1);

    public CTPhoneticPr getPhoneticPr();

    public boolean isSetPhoneticPr();

    public void setPhoneticPr(CTPhoneticPr var1);

    public CTPhoneticPr addNewPhoneticPr();

    public void unsetPhoneticPr();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTRst.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTRst newInstance() {
            return (CTRst)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTRst newInstance(XmlOptions xmlOptions) {
            return (CTRst)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTRst parse(String string) throws XmlException {
            return (CTRst)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTRst parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTRst)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTRst parse(File file) throws XmlException, IOException {
            return (CTRst)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTRst parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTRst)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTRst parse(URL uRL) throws XmlException, IOException {
            return (CTRst)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTRst parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTRst)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTRst parse(InputStream inputStream) throws XmlException, IOException {
            return (CTRst)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTRst parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTRst)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTRst parse(Reader reader) throws XmlException, IOException {
            return (CTRst)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTRst parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTRst)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTRst parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTRst)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTRst parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTRst)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTRst parse(Node node) throws XmlException {
            return (CTRst)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTRst parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTRst)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTRst parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTRst)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTRst parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTRst)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

