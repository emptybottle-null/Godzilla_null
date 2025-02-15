/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.spreadsheetml.x2006.main.CTBoolean
 *  org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDateTime
 *  org.openxmlformats.schemas.spreadsheetml.x2006.main.CTError
 *  org.openxmlformats.schemas.spreadsheetml.x2006.main.CTMissing
 *  org.openxmlformats.schemas.spreadsheetml.x2006.main.CTNumber
 *  org.openxmlformats.schemas.spreadsheetml.x2006.main.CTString
 */
package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.lang.ref.SoftReference;
import java.net.URL;
import java.util.Calendar;
import java.util.List;
import javax.xml.stream.XMLStreamReader;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SchemaTypeLoader;
import org.apache.xmlbeans.XmlBeans;
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.XmlDateTime;
import org.apache.xmlbeans.XmlDouble;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTBoolean;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDateTime;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTError;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTMissing;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTNumber;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTString;
import org.w3c.dom.Node;

public interface CTSharedItems
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTSharedItems.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctshareditems677atype");

    public List<CTMissing> getMList();

    @Deprecated
    public CTMissing[] getMArray();

    public CTMissing getMArray(int var1);

    public int sizeOfMArray();

    public void setMArray(CTMissing[] var1);

    public void setMArray(int var1, CTMissing var2);

    public CTMissing insertNewM(int var1);

    public CTMissing addNewM();

    public void removeM(int var1);

    public List<CTNumber> getNList();

    @Deprecated
    public CTNumber[] getNArray();

    public CTNumber getNArray(int var1);

    public int sizeOfNArray();

    public void setNArray(CTNumber[] var1);

    public void setNArray(int var1, CTNumber var2);

    public CTNumber insertNewN(int var1);

    public CTNumber addNewN();

    public void removeN(int var1);

    public List<CTBoolean> getBList();

    @Deprecated
    public CTBoolean[] getBArray();

    public CTBoolean getBArray(int var1);

    public int sizeOfBArray();

    public void setBArray(CTBoolean[] var1);

    public void setBArray(int var1, CTBoolean var2);

    public CTBoolean insertNewB(int var1);

    public CTBoolean addNewB();

    public void removeB(int var1);

    public List<CTError> getEList();

    @Deprecated
    public CTError[] getEArray();

    public CTError getEArray(int var1);

    public int sizeOfEArray();

    public void setEArray(CTError[] var1);

    public void setEArray(int var1, CTError var2);

    public CTError insertNewE(int var1);

    public CTError addNewE();

    public void removeE(int var1);

    public List<CTString> getSList();

    @Deprecated
    public CTString[] getSArray();

    public CTString getSArray(int var1);

    public int sizeOfSArray();

    public void setSArray(CTString[] var1);

    public void setSArray(int var1, CTString var2);

    public CTString insertNewS(int var1);

    public CTString addNewS();

    public void removeS(int var1);

    public List<CTDateTime> getDList();

    @Deprecated
    public CTDateTime[] getDArray();

    public CTDateTime getDArray(int var1);

    public int sizeOfDArray();

    public void setDArray(CTDateTime[] var1);

    public void setDArray(int var1, CTDateTime var2);

    public CTDateTime insertNewD(int var1);

    public CTDateTime addNewD();

    public void removeD(int var1);

    public boolean getContainsSemiMixedTypes();

    public XmlBoolean xgetContainsSemiMixedTypes();

    public boolean isSetContainsSemiMixedTypes();

    public void setContainsSemiMixedTypes(boolean var1);

    public void xsetContainsSemiMixedTypes(XmlBoolean var1);

    public void unsetContainsSemiMixedTypes();

    public boolean getContainsNonDate();

    public XmlBoolean xgetContainsNonDate();

    public boolean isSetContainsNonDate();

    public void setContainsNonDate(boolean var1);

    public void xsetContainsNonDate(XmlBoolean var1);

    public void unsetContainsNonDate();

    public boolean getContainsDate();

    public XmlBoolean xgetContainsDate();

    public boolean isSetContainsDate();

    public void setContainsDate(boolean var1);

    public void xsetContainsDate(XmlBoolean var1);

    public void unsetContainsDate();

    public boolean getContainsString();

    public XmlBoolean xgetContainsString();

    public boolean isSetContainsString();

    public void setContainsString(boolean var1);

    public void xsetContainsString(XmlBoolean var1);

    public void unsetContainsString();

    public boolean getContainsBlank();

    public XmlBoolean xgetContainsBlank();

    public boolean isSetContainsBlank();

    public void setContainsBlank(boolean var1);

    public void xsetContainsBlank(XmlBoolean var1);

    public void unsetContainsBlank();

    public boolean getContainsMixedTypes();

    public XmlBoolean xgetContainsMixedTypes();

    public boolean isSetContainsMixedTypes();

    public void setContainsMixedTypes(boolean var1);

    public void xsetContainsMixedTypes(XmlBoolean var1);

    public void unsetContainsMixedTypes();

    public boolean getContainsNumber();

    public XmlBoolean xgetContainsNumber();

    public boolean isSetContainsNumber();

    public void setContainsNumber(boolean var1);

    public void xsetContainsNumber(XmlBoolean var1);

    public void unsetContainsNumber();

    public boolean getContainsInteger();

    public XmlBoolean xgetContainsInteger();

    public boolean isSetContainsInteger();

    public void setContainsInteger(boolean var1);

    public void xsetContainsInteger(XmlBoolean var1);

    public void unsetContainsInteger();

    public double getMinValue();

    public XmlDouble xgetMinValue();

    public boolean isSetMinValue();

    public void setMinValue(double var1);

    public void xsetMinValue(XmlDouble var1);

    public void unsetMinValue();

    public double getMaxValue();

    public XmlDouble xgetMaxValue();

    public boolean isSetMaxValue();

    public void setMaxValue(double var1);

    public void xsetMaxValue(XmlDouble var1);

    public void unsetMaxValue();

    public Calendar getMinDate();

    public XmlDateTime xgetMinDate();

    public boolean isSetMinDate();

    public void setMinDate(Calendar var1);

    public void xsetMinDate(XmlDateTime var1);

    public void unsetMinDate();

    public Calendar getMaxDate();

    public XmlDateTime xgetMaxDate();

    public boolean isSetMaxDate();

    public void setMaxDate(Calendar var1);

    public void xsetMaxDate(XmlDateTime var1);

    public void unsetMaxDate();

    public long getCount();

    public XmlUnsignedInt xgetCount();

    public boolean isSetCount();

    public void setCount(long var1);

    public void xsetCount(XmlUnsignedInt var1);

    public void unsetCount();

    public boolean getLongText();

    public XmlBoolean xgetLongText();

    public boolean isSetLongText();

    public void setLongText(boolean var1);

    public void xsetLongText(XmlBoolean var1);

    public void unsetLongText();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTSharedItems.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTSharedItems newInstance() {
            return (CTSharedItems)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTSharedItems newInstance(XmlOptions xmlOptions) {
            return (CTSharedItems)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTSharedItems parse(String string) throws XmlException {
            return (CTSharedItems)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTSharedItems parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTSharedItems)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTSharedItems parse(File file) throws XmlException, IOException {
            return (CTSharedItems)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTSharedItems parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTSharedItems)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTSharedItems parse(URL uRL) throws XmlException, IOException {
            return (CTSharedItems)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTSharedItems parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTSharedItems)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTSharedItems parse(InputStream inputStream) throws XmlException, IOException {
            return (CTSharedItems)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTSharedItems parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTSharedItems)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTSharedItems parse(Reader reader) throws XmlException, IOException {
            return (CTSharedItems)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTSharedItems parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTSharedItems)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTSharedItems parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTSharedItems)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTSharedItems parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTSharedItems)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTSharedItems parse(Node node) throws XmlException {
            return (CTSharedItems)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTSharedItems parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTSharedItems)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTSharedItems parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTSharedItems)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTSharedItems parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTSharedItems)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

