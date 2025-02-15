/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.drawingml.x2006.main.CTBlurEffect
 *  org.openxmlformats.schemas.drawingml.x2006.main.CTFillOverlayEffect
 *  org.openxmlformats.schemas.drawingml.x2006.main.CTGlowEffect
 *  org.openxmlformats.schemas.drawingml.x2006.main.CTInnerShadowEffect
 *  org.openxmlformats.schemas.drawingml.x2006.main.CTPresetShadowEffect
 *  org.openxmlformats.schemas.drawingml.x2006.main.CTReflectionEffect
 *  org.openxmlformats.schemas.drawingml.x2006.main.CTSoftEdgesEffect
 */
package org.openxmlformats.schemas.drawingml.x2006.main;

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
import org.openxmlformats.schemas.drawingml.x2006.main.CTBlurEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTFillOverlayEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGlowEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTInnerShadowEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTOuterShadowEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPresetShadowEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTReflectionEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTSoftEdgesEffect;
import org.w3c.dom.Node;

public interface CTEffectList
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTEffectList.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("cteffectlist6featype");

    public CTBlurEffect getBlur();

    public boolean isSetBlur();

    public void setBlur(CTBlurEffect var1);

    public CTBlurEffect addNewBlur();

    public void unsetBlur();

    public CTFillOverlayEffect getFillOverlay();

    public boolean isSetFillOverlay();

    public void setFillOverlay(CTFillOverlayEffect var1);

    public CTFillOverlayEffect addNewFillOverlay();

    public void unsetFillOverlay();

    public CTGlowEffect getGlow();

    public boolean isSetGlow();

    public void setGlow(CTGlowEffect var1);

    public CTGlowEffect addNewGlow();

    public void unsetGlow();

    public CTInnerShadowEffect getInnerShdw();

    public boolean isSetInnerShdw();

    public void setInnerShdw(CTInnerShadowEffect var1);

    public CTInnerShadowEffect addNewInnerShdw();

    public void unsetInnerShdw();

    public CTOuterShadowEffect getOuterShdw();

    public boolean isSetOuterShdw();

    public void setOuterShdw(CTOuterShadowEffect var1);

    public CTOuterShadowEffect addNewOuterShdw();

    public void unsetOuterShdw();

    public CTPresetShadowEffect getPrstShdw();

    public boolean isSetPrstShdw();

    public void setPrstShdw(CTPresetShadowEffect var1);

    public CTPresetShadowEffect addNewPrstShdw();

    public void unsetPrstShdw();

    public CTReflectionEffect getReflection();

    public boolean isSetReflection();

    public void setReflection(CTReflectionEffect var1);

    public CTReflectionEffect addNewReflection();

    public void unsetReflection();

    public CTSoftEdgesEffect getSoftEdge();

    public boolean isSetSoftEdge();

    public void setSoftEdge(CTSoftEdgesEffect var1);

    public CTSoftEdgesEffect addNewSoftEdge();

    public void unsetSoftEdge();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTEffectList.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTEffectList newInstance() {
            return (CTEffectList)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTEffectList newInstance(XmlOptions xmlOptions) {
            return (CTEffectList)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTEffectList parse(String string) throws XmlException {
            return (CTEffectList)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTEffectList parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTEffectList)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTEffectList parse(File file) throws XmlException, IOException {
            return (CTEffectList)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTEffectList parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTEffectList)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTEffectList parse(URL uRL) throws XmlException, IOException {
            return (CTEffectList)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTEffectList parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTEffectList)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTEffectList parse(InputStream inputStream) throws XmlException, IOException {
            return (CTEffectList)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTEffectList parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTEffectList)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTEffectList parse(Reader reader) throws XmlException, IOException {
            return (CTEffectList)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTEffectList parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTEffectList)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTEffectList parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTEffectList)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTEffectList parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTEffectList)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTEffectList parse(Node node) throws XmlException {
            return (CTEffectList)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTEffectList parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTEffectList)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTEffectList parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTEffectList)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTEffectList parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTEffectList)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

