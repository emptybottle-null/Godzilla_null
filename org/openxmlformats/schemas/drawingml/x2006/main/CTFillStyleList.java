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
import org.openxmlformats.schemas.drawingml.x2006.main.CTBlipFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGradientFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGroupFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTNoFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPatternFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTSolidColorFillProperties;
import org.w3c.dom.Node;

public interface CTFillStyleList
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTFillStyleList.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctfillstylelist959dtype");

    public List<CTNoFillProperties> getNoFillList();

    @Deprecated
    public CTNoFillProperties[] getNoFillArray();

    public CTNoFillProperties getNoFillArray(int var1);

    public int sizeOfNoFillArray();

    public void setNoFillArray(CTNoFillProperties[] var1);

    public void setNoFillArray(int var1, CTNoFillProperties var2);

    public CTNoFillProperties insertNewNoFill(int var1);

    public CTNoFillProperties addNewNoFill();

    public void removeNoFill(int var1);

    public List<CTSolidColorFillProperties> getSolidFillList();

    @Deprecated
    public CTSolidColorFillProperties[] getSolidFillArray();

    public CTSolidColorFillProperties getSolidFillArray(int var1);

    public int sizeOfSolidFillArray();

    public void setSolidFillArray(CTSolidColorFillProperties[] var1);

    public void setSolidFillArray(int var1, CTSolidColorFillProperties var2);

    public CTSolidColorFillProperties insertNewSolidFill(int var1);

    public CTSolidColorFillProperties addNewSolidFill();

    public void removeSolidFill(int var1);

    public List<CTGradientFillProperties> getGradFillList();

    @Deprecated
    public CTGradientFillProperties[] getGradFillArray();

    public CTGradientFillProperties getGradFillArray(int var1);

    public int sizeOfGradFillArray();

    public void setGradFillArray(CTGradientFillProperties[] var1);

    public void setGradFillArray(int var1, CTGradientFillProperties var2);

    public CTGradientFillProperties insertNewGradFill(int var1);

    public CTGradientFillProperties addNewGradFill();

    public void removeGradFill(int var1);

    public List<CTBlipFillProperties> getBlipFillList();

    @Deprecated
    public CTBlipFillProperties[] getBlipFillArray();

    public CTBlipFillProperties getBlipFillArray(int var1);

    public int sizeOfBlipFillArray();

    public void setBlipFillArray(CTBlipFillProperties[] var1);

    public void setBlipFillArray(int var1, CTBlipFillProperties var2);

    public CTBlipFillProperties insertNewBlipFill(int var1);

    public CTBlipFillProperties addNewBlipFill();

    public void removeBlipFill(int var1);

    public List<CTPatternFillProperties> getPattFillList();

    @Deprecated
    public CTPatternFillProperties[] getPattFillArray();

    public CTPatternFillProperties getPattFillArray(int var1);

    public int sizeOfPattFillArray();

    public void setPattFillArray(CTPatternFillProperties[] var1);

    public void setPattFillArray(int var1, CTPatternFillProperties var2);

    public CTPatternFillProperties insertNewPattFill(int var1);

    public CTPatternFillProperties addNewPattFill();

    public void removePattFill(int var1);

    public List<CTGroupFillProperties> getGrpFillList();

    @Deprecated
    public CTGroupFillProperties[] getGrpFillArray();

    public CTGroupFillProperties getGrpFillArray(int var1);

    public int sizeOfGrpFillArray();

    public void setGrpFillArray(CTGroupFillProperties[] var1);

    public void setGrpFillArray(int var1, CTGroupFillProperties var2);

    public CTGroupFillProperties insertNewGrpFill(int var1);

    public CTGroupFillProperties addNewGrpFill();

    public void removeGrpFill(int var1);

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTFillStyleList.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTFillStyleList newInstance() {
            return (CTFillStyleList)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTFillStyleList newInstance(XmlOptions xmlOptions) {
            return (CTFillStyleList)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTFillStyleList parse(String string) throws XmlException {
            return (CTFillStyleList)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTFillStyleList parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTFillStyleList)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTFillStyleList parse(File file) throws XmlException, IOException {
            return (CTFillStyleList)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTFillStyleList parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTFillStyleList)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTFillStyleList parse(URL uRL) throws XmlException, IOException {
            return (CTFillStyleList)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTFillStyleList parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTFillStyleList)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTFillStyleList parse(InputStream inputStream) throws XmlException, IOException {
            return (CTFillStyleList)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTFillStyleList parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTFillStyleList)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTFillStyleList parse(Reader reader) throws XmlException, IOException {
            return (CTFillStyleList)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTFillStyleList parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTFillStyleList)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTFillStyleList parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTFillStyleList)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTFillStyleList parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTFillStyleList)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTFillStyleList parse(Node node) throws XmlException {
            return (CTFillStyleList)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTFillStyleList parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTFillStyleList)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTFillStyleList parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTFillStyleList)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTFillStyleList parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTFillStyleList)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

