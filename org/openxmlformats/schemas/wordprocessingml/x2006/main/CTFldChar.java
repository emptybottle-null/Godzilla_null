/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrackChangeNumbering
 */
package org.openxmlformats.schemas.wordprocessingml.x2006.main;

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
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFFData;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTText;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrackChangeNumbering;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STFldCharType;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STOnOff;
import org.w3c.dom.Node;

public interface CTFldChar
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTFldChar.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctfldchare83etype");

    public CTText getFldData();

    public boolean isSetFldData();

    public void setFldData(CTText var1);

    public CTText addNewFldData();

    public void unsetFldData();

    public CTFFData getFfData();

    public boolean isSetFfData();

    public void setFfData(CTFFData var1);

    public CTFFData addNewFfData();

    public void unsetFfData();

    public CTTrackChangeNumbering getNumberingChange();

    public boolean isSetNumberingChange();

    public void setNumberingChange(CTTrackChangeNumbering var1);

    public CTTrackChangeNumbering addNewNumberingChange();

    public void unsetNumberingChange();

    public STFldCharType.Enum getFldCharType();

    public STFldCharType xgetFldCharType();

    public void setFldCharType(STFldCharType.Enum var1);

    public void xsetFldCharType(STFldCharType var1);

    public STOnOff.Enum getFldLock();

    public STOnOff xgetFldLock();

    public boolean isSetFldLock();

    public void setFldLock(STOnOff.Enum var1);

    public void xsetFldLock(STOnOff var1);

    public void unsetFldLock();

    public STOnOff.Enum getDirty();

    public STOnOff xgetDirty();

    public boolean isSetDirty();

    public void setDirty(STOnOff.Enum var1);

    public void xsetDirty(STOnOff var1);

    public void unsetDirty();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTFldChar.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTFldChar newInstance() {
            return (CTFldChar)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTFldChar newInstance(XmlOptions xmlOptions) {
            return (CTFldChar)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTFldChar parse(String string) throws XmlException {
            return (CTFldChar)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTFldChar parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTFldChar)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTFldChar parse(File file) throws XmlException, IOException {
            return (CTFldChar)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTFldChar parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTFldChar)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTFldChar parse(URL uRL) throws XmlException, IOException {
            return (CTFldChar)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTFldChar parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTFldChar)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTFldChar parse(InputStream inputStream) throws XmlException, IOException {
            return (CTFldChar)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTFldChar parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTFldChar)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTFldChar parse(Reader reader) throws XmlException, IOException {
            return (CTFldChar)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTFldChar parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTFldChar)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTFldChar parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTFldChar)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTFldChar parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTFldChar)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTFldChar parse(Node node) throws XmlException {
            return (CTFldChar)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTFldChar parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTFldChar)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTFldChar parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTFldChar)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTFldChar parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTFldChar)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

