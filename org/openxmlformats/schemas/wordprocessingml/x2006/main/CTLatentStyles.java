/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.lang.ref.SoftReference;
import java.math.BigInteger;
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
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLsdException;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STDecimalNumber;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STOnOff;
import org.w3c.dom.Node;

public interface CTLatentStyles
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTLatentStyles.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctlatentstyles2e3atype");

    public List<CTLsdException> getLsdExceptionList();

    @Deprecated
    public CTLsdException[] getLsdExceptionArray();

    public CTLsdException getLsdExceptionArray(int var1);

    public int sizeOfLsdExceptionArray();

    public void setLsdExceptionArray(CTLsdException[] var1);

    public void setLsdExceptionArray(int var1, CTLsdException var2);

    public CTLsdException insertNewLsdException(int var1);

    public CTLsdException addNewLsdException();

    public void removeLsdException(int var1);

    public STOnOff.Enum getDefLockedState();

    public STOnOff xgetDefLockedState();

    public boolean isSetDefLockedState();

    public void setDefLockedState(STOnOff.Enum var1);

    public void xsetDefLockedState(STOnOff var1);

    public void unsetDefLockedState();

    public BigInteger getDefUIPriority();

    public STDecimalNumber xgetDefUIPriority();

    public boolean isSetDefUIPriority();

    public void setDefUIPriority(BigInteger var1);

    public void xsetDefUIPriority(STDecimalNumber var1);

    public void unsetDefUIPriority();

    public STOnOff.Enum getDefSemiHidden();

    public STOnOff xgetDefSemiHidden();

    public boolean isSetDefSemiHidden();

    public void setDefSemiHidden(STOnOff.Enum var1);

    public void xsetDefSemiHidden(STOnOff var1);

    public void unsetDefSemiHidden();

    public STOnOff.Enum getDefUnhideWhenUsed();

    public STOnOff xgetDefUnhideWhenUsed();

    public boolean isSetDefUnhideWhenUsed();

    public void setDefUnhideWhenUsed(STOnOff.Enum var1);

    public void xsetDefUnhideWhenUsed(STOnOff var1);

    public void unsetDefUnhideWhenUsed();

    public STOnOff.Enum getDefQFormat();

    public STOnOff xgetDefQFormat();

    public boolean isSetDefQFormat();

    public void setDefQFormat(STOnOff.Enum var1);

    public void xsetDefQFormat(STOnOff var1);

    public void unsetDefQFormat();

    public BigInteger getCount();

    public STDecimalNumber xgetCount();

    public boolean isSetCount();

    public void setCount(BigInteger var1);

    public void xsetCount(STDecimalNumber var1);

    public void unsetCount();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTLatentStyles.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTLatentStyles newInstance() {
            return (CTLatentStyles)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTLatentStyles newInstance(XmlOptions xmlOptions) {
            return (CTLatentStyles)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTLatentStyles parse(String string) throws XmlException {
            return (CTLatentStyles)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTLatentStyles parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTLatentStyles)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTLatentStyles parse(File file) throws XmlException, IOException {
            return (CTLatentStyles)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTLatentStyles parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTLatentStyles)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTLatentStyles parse(URL uRL) throws XmlException, IOException {
            return (CTLatentStyles)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTLatentStyles parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTLatentStyles)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTLatentStyles parse(InputStream inputStream) throws XmlException, IOException {
            return (CTLatentStyles)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTLatentStyles parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTLatentStyles)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTLatentStyles parse(Reader reader) throws XmlException, IOException {
            return (CTLatentStyles)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTLatentStyles parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTLatentStyles)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTLatentStyles parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTLatentStyles)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTLatentStyles parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTLatentStyles)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTLatentStyles parse(Node node) throws XmlException {
            return (CTLatentStyles)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTLatentStyles parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTLatentStyles)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTLatentStyles parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTLatentStyles)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTLatentStyles parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTLatentStyles)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

