/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExtensionList
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
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCellAlignment;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCellProtection;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExtensionList;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STBorderId;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STCellStyleXfId;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STFillId;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STFontId;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STNumFmtId;
import org.w3c.dom.Node;

public interface CTXf
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTXf.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctxf97f7type");

    public CTCellAlignment getAlignment();

    public boolean isSetAlignment();

    public void setAlignment(CTCellAlignment var1);

    public CTCellAlignment addNewAlignment();

    public void unsetAlignment();

    public CTCellProtection getProtection();

    public boolean isSetProtection();

    public void setProtection(CTCellProtection var1);

    public CTCellProtection addNewProtection();

    public void unsetProtection();

    public CTExtensionList getExtLst();

    public boolean isSetExtLst();

    public void setExtLst(CTExtensionList var1);

    public CTExtensionList addNewExtLst();

    public void unsetExtLst();

    public long getNumFmtId();

    public STNumFmtId xgetNumFmtId();

    public boolean isSetNumFmtId();

    public void setNumFmtId(long var1);

    public void xsetNumFmtId(STNumFmtId var1);

    public void unsetNumFmtId();

    public long getFontId();

    public STFontId xgetFontId();

    public boolean isSetFontId();

    public void setFontId(long var1);

    public void xsetFontId(STFontId var1);

    public void unsetFontId();

    public long getFillId();

    public STFillId xgetFillId();

    public boolean isSetFillId();

    public void setFillId(long var1);

    public void xsetFillId(STFillId var1);

    public void unsetFillId();

    public long getBorderId();

    public STBorderId xgetBorderId();

    public boolean isSetBorderId();

    public void setBorderId(long var1);

    public void xsetBorderId(STBorderId var1);

    public void unsetBorderId();

    public long getXfId();

    public STCellStyleXfId xgetXfId();

    public boolean isSetXfId();

    public void setXfId(long var1);

    public void xsetXfId(STCellStyleXfId var1);

    public void unsetXfId();

    public boolean getQuotePrefix();

    public XmlBoolean xgetQuotePrefix();

    public boolean isSetQuotePrefix();

    public void setQuotePrefix(boolean var1);

    public void xsetQuotePrefix(XmlBoolean var1);

    public void unsetQuotePrefix();

    public boolean getPivotButton();

    public XmlBoolean xgetPivotButton();

    public boolean isSetPivotButton();

    public void setPivotButton(boolean var1);

    public void xsetPivotButton(XmlBoolean var1);

    public void unsetPivotButton();

    public boolean getApplyNumberFormat();

    public XmlBoolean xgetApplyNumberFormat();

    public boolean isSetApplyNumberFormat();

    public void setApplyNumberFormat(boolean var1);

    public void xsetApplyNumberFormat(XmlBoolean var1);

    public void unsetApplyNumberFormat();

    public boolean getApplyFont();

    public XmlBoolean xgetApplyFont();

    public boolean isSetApplyFont();

    public void setApplyFont(boolean var1);

    public void xsetApplyFont(XmlBoolean var1);

    public void unsetApplyFont();

    public boolean getApplyFill();

    public XmlBoolean xgetApplyFill();

    public boolean isSetApplyFill();

    public void setApplyFill(boolean var1);

    public void xsetApplyFill(XmlBoolean var1);

    public void unsetApplyFill();

    public boolean getApplyBorder();

    public XmlBoolean xgetApplyBorder();

    public boolean isSetApplyBorder();

    public void setApplyBorder(boolean var1);

    public void xsetApplyBorder(XmlBoolean var1);

    public void unsetApplyBorder();

    public boolean getApplyAlignment();

    public XmlBoolean xgetApplyAlignment();

    public boolean isSetApplyAlignment();

    public void setApplyAlignment(boolean var1);

    public void xsetApplyAlignment(XmlBoolean var1);

    public void unsetApplyAlignment();

    public boolean getApplyProtection();

    public XmlBoolean xgetApplyProtection();

    public boolean isSetApplyProtection();

    public void setApplyProtection(boolean var1);

    public void xsetApplyProtection(XmlBoolean var1);

    public void unsetApplyProtection();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTXf.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTXf newInstance() {
            return (CTXf)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTXf newInstance(XmlOptions xmlOptions) {
            return (CTXf)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTXf parse(String string) throws XmlException {
            return (CTXf)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTXf parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTXf)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTXf parse(File file) throws XmlException, IOException {
            return (CTXf)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTXf parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTXf)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTXf parse(URL uRL) throws XmlException, IOException {
            return (CTXf)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTXf parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTXf)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTXf parse(InputStream inputStream) throws XmlException, IOException {
            return (CTXf)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTXf parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTXf)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTXf parse(Reader reader) throws XmlException, IOException {
            return (CTXf)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTXf parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTXf)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTXf parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTXf)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTXf parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTXf)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTXf parse(Node node) throws XmlException {
            return (CTXf)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTXf parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTXf)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTXf parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTXf)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTXf parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTXf)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

