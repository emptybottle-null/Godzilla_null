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
import javax.xml.stream.XMLStreamReader;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SchemaTypeLoader;
import org.apache.xmlbeans.XmlBeans;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.openxmlformats.schemas.drawingml.x2006.main.CTDashStopList;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGradientFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTLineEndProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTLineJoinBevel;
import org.openxmlformats.schemas.drawingml.x2006.main.CTLineJoinMiterProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTLineJoinRound;
import org.openxmlformats.schemas.drawingml.x2006.main.CTNoFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTOfficeArtExtensionList;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPatternFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPresetLineDashProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTSolidColorFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.STCompoundLine;
import org.openxmlformats.schemas.drawingml.x2006.main.STLineCap;
import org.openxmlformats.schemas.drawingml.x2006.main.STLineWidth;
import org.openxmlformats.schemas.drawingml.x2006.main.STPenAlignment;
import org.w3c.dom.Node;

public interface CTLineProperties
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTLineProperties.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctlinepropertiesd5e2type");

    public CTNoFillProperties getNoFill();

    public boolean isSetNoFill();

    public void setNoFill(CTNoFillProperties var1);

    public CTNoFillProperties addNewNoFill();

    public void unsetNoFill();

    public CTSolidColorFillProperties getSolidFill();

    public boolean isSetSolidFill();

    public void setSolidFill(CTSolidColorFillProperties var1);

    public CTSolidColorFillProperties addNewSolidFill();

    public void unsetSolidFill();

    public CTGradientFillProperties getGradFill();

    public boolean isSetGradFill();

    public void setGradFill(CTGradientFillProperties var1);

    public CTGradientFillProperties addNewGradFill();

    public void unsetGradFill();

    public CTPatternFillProperties getPattFill();

    public boolean isSetPattFill();

    public void setPattFill(CTPatternFillProperties var1);

    public CTPatternFillProperties addNewPattFill();

    public void unsetPattFill();

    public CTPresetLineDashProperties getPrstDash();

    public boolean isSetPrstDash();

    public void setPrstDash(CTPresetLineDashProperties var1);

    public CTPresetLineDashProperties addNewPrstDash();

    public void unsetPrstDash();

    public CTDashStopList getCustDash();

    public boolean isSetCustDash();

    public void setCustDash(CTDashStopList var1);

    public CTDashStopList addNewCustDash();

    public void unsetCustDash();

    public CTLineJoinRound getRound();

    public boolean isSetRound();

    public void setRound(CTLineJoinRound var1);

    public CTLineJoinRound addNewRound();

    public void unsetRound();

    public CTLineJoinBevel getBevel();

    public boolean isSetBevel();

    public void setBevel(CTLineJoinBevel var1);

    public CTLineJoinBevel addNewBevel();

    public void unsetBevel();

    public CTLineJoinMiterProperties getMiter();

    public boolean isSetMiter();

    public void setMiter(CTLineJoinMiterProperties var1);

    public CTLineJoinMiterProperties addNewMiter();

    public void unsetMiter();

    public CTLineEndProperties getHeadEnd();

    public boolean isSetHeadEnd();

    public void setHeadEnd(CTLineEndProperties var1);

    public CTLineEndProperties addNewHeadEnd();

    public void unsetHeadEnd();

    public CTLineEndProperties getTailEnd();

    public boolean isSetTailEnd();

    public void setTailEnd(CTLineEndProperties var1);

    public CTLineEndProperties addNewTailEnd();

    public void unsetTailEnd();

    public CTOfficeArtExtensionList getExtLst();

    public boolean isSetExtLst();

    public void setExtLst(CTOfficeArtExtensionList var1);

    public CTOfficeArtExtensionList addNewExtLst();

    public void unsetExtLst();

    public int getW();

    public STLineWidth xgetW();

    public boolean isSetW();

    public void setW(int var1);

    public void xsetW(STLineWidth var1);

    public void unsetW();

    public STLineCap.Enum getCap();

    public STLineCap xgetCap();

    public boolean isSetCap();

    public void setCap(STLineCap.Enum var1);

    public void xsetCap(STLineCap var1);

    public void unsetCap();

    public STCompoundLine.Enum getCmpd();

    public STCompoundLine xgetCmpd();

    public boolean isSetCmpd();

    public void setCmpd(STCompoundLine.Enum var1);

    public void xsetCmpd(STCompoundLine var1);

    public void unsetCmpd();

    public STPenAlignment.Enum getAlgn();

    public STPenAlignment xgetAlgn();

    public boolean isSetAlgn();

    public void setAlgn(STPenAlignment.Enum var1);

    public void xsetAlgn(STPenAlignment var1);

    public void unsetAlgn();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTLineProperties.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTLineProperties newInstance() {
            return (CTLineProperties)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTLineProperties newInstance(XmlOptions xmlOptions) {
            return (CTLineProperties)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTLineProperties parse(String string) throws XmlException {
            return (CTLineProperties)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTLineProperties parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTLineProperties)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTLineProperties parse(File file) throws XmlException, IOException {
            return (CTLineProperties)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTLineProperties parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTLineProperties)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTLineProperties parse(URL uRL) throws XmlException, IOException {
            return (CTLineProperties)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTLineProperties parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTLineProperties)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTLineProperties parse(InputStream inputStream) throws XmlException, IOException {
            return (CTLineProperties)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTLineProperties parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTLineProperties)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTLineProperties parse(Reader reader) throws XmlException, IOException {
            return (CTLineProperties)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTLineProperties parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTLineProperties)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTLineProperties parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTLineProperties)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTLineProperties parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTLineProperties)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTLineProperties parse(Node node) throws XmlException {
            return (CTLineProperties)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTLineProperties parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTLineProperties)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTLineProperties parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTLineProperties)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTLineProperties parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTLineProperties)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

