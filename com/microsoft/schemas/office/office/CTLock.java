/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  com.microsoft.schemas.office.office.STTrueFalse
 */
package com.microsoft.schemas.office.office;

import com.microsoft.schemas.office.office.STTrueFalse;
import com.microsoft.schemas.vml.STExt;
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
import org.w3c.dom.Node;

public interface CTLock
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTLock.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctlock6b8etype");

    public STExt.Enum getExt();

    public STExt xgetExt();

    public boolean isSetExt();

    public void setExt(STExt.Enum var1);

    public void xsetExt(STExt var1);

    public void unsetExt();

    public STTrueFalse.Enum getPosition();

    public STTrueFalse xgetPosition();

    public boolean isSetPosition();

    public void setPosition(STTrueFalse.Enum var1);

    public void xsetPosition(STTrueFalse var1);

    public void unsetPosition();

    public STTrueFalse.Enum getSelection();

    public STTrueFalse xgetSelection();

    public boolean isSetSelection();

    public void setSelection(STTrueFalse.Enum var1);

    public void xsetSelection(STTrueFalse var1);

    public void unsetSelection();

    public STTrueFalse.Enum getGrouping();

    public STTrueFalse xgetGrouping();

    public boolean isSetGrouping();

    public void setGrouping(STTrueFalse.Enum var1);

    public void xsetGrouping(STTrueFalse var1);

    public void unsetGrouping();

    public STTrueFalse.Enum getUngrouping();

    public STTrueFalse xgetUngrouping();

    public boolean isSetUngrouping();

    public void setUngrouping(STTrueFalse.Enum var1);

    public void xsetUngrouping(STTrueFalse var1);

    public void unsetUngrouping();

    public STTrueFalse.Enum getRotation();

    public STTrueFalse xgetRotation();

    public boolean isSetRotation();

    public void setRotation(STTrueFalse.Enum var1);

    public void xsetRotation(STTrueFalse var1);

    public void unsetRotation();

    public STTrueFalse.Enum getCropping();

    public STTrueFalse xgetCropping();

    public boolean isSetCropping();

    public void setCropping(STTrueFalse.Enum var1);

    public void xsetCropping(STTrueFalse var1);

    public void unsetCropping();

    public STTrueFalse.Enum getVerticies();

    public STTrueFalse xgetVerticies();

    public boolean isSetVerticies();

    public void setVerticies(STTrueFalse.Enum var1);

    public void xsetVerticies(STTrueFalse var1);

    public void unsetVerticies();

    public STTrueFalse.Enum getAdjusthandles();

    public STTrueFalse xgetAdjusthandles();

    public boolean isSetAdjusthandles();

    public void setAdjusthandles(STTrueFalse.Enum var1);

    public void xsetAdjusthandles(STTrueFalse var1);

    public void unsetAdjusthandles();

    public STTrueFalse.Enum getText();

    public STTrueFalse xgetText();

    public boolean isSetText();

    public void setText(STTrueFalse.Enum var1);

    public void xsetText(STTrueFalse var1);

    public void unsetText();

    public STTrueFalse.Enum getAspectratio();

    public STTrueFalse xgetAspectratio();

    public boolean isSetAspectratio();

    public void setAspectratio(STTrueFalse.Enum var1);

    public void xsetAspectratio(STTrueFalse var1);

    public void unsetAspectratio();

    public STTrueFalse.Enum getShapetype();

    public STTrueFalse xgetShapetype();

    public boolean isSetShapetype();

    public void setShapetype(STTrueFalse.Enum var1);

    public void xsetShapetype(STTrueFalse var1);

    public void unsetShapetype();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTLock.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTLock newInstance() {
            return (CTLock)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTLock newInstance(XmlOptions xmlOptions) {
            return (CTLock)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTLock parse(String string) throws XmlException {
            return (CTLock)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTLock parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTLock)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTLock parse(File file) throws XmlException, IOException {
            return (CTLock)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTLock parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTLock)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTLock parse(URL uRL) throws XmlException, IOException {
            return (CTLock)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTLock parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTLock)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTLock parse(InputStream inputStream) throws XmlException, IOException {
            return (CTLock)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTLock parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTLock)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTLock parse(Reader reader) throws XmlException, IOException {
            return (CTLock)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTLock parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTLock)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTLock parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTLock)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTLock parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTLock)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTLock parse(Node node) throws XmlException {
            return (CTLock)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTLock parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTLock)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTLock parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTLock)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTLock parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTLock)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

