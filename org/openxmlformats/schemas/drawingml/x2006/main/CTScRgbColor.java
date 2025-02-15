/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.drawingml.x2006.main.CTAngle
 *  org.openxmlformats.schemas.drawingml.x2006.main.CTComplementTransform
 *  org.openxmlformats.schemas.drawingml.x2006.main.CTGammaTransform
 *  org.openxmlformats.schemas.drawingml.x2006.main.CTGrayscaleTransform
 *  org.openxmlformats.schemas.drawingml.x2006.main.CTInverseGammaTransform
 *  org.openxmlformats.schemas.drawingml.x2006.main.CTInverseTransform
 *  org.openxmlformats.schemas.drawingml.x2006.main.CTPositiveFixedAngle
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
import org.openxmlformats.schemas.drawingml.x2006.main.CTAngle;
import org.openxmlformats.schemas.drawingml.x2006.main.CTComplementTransform;
import org.openxmlformats.schemas.drawingml.x2006.main.CTFixedPercentage;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGammaTransform;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGrayscaleTransform;
import org.openxmlformats.schemas.drawingml.x2006.main.CTInverseGammaTransform;
import org.openxmlformats.schemas.drawingml.x2006.main.CTInverseTransform;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPercentage;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPositiveFixedAngle;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPositiveFixedPercentage;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPositivePercentage;
import org.openxmlformats.schemas.drawingml.x2006.main.STPercentage;
import org.w3c.dom.Node;

public interface CTScRgbColor
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTScRgbColor.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctscrgbcolorf3e1type");

    public List<CTPositiveFixedPercentage> getTintList();

    @Deprecated
    public CTPositiveFixedPercentage[] getTintArray();

    public CTPositiveFixedPercentage getTintArray(int var1);

    public int sizeOfTintArray();

    public void setTintArray(CTPositiveFixedPercentage[] var1);

    public void setTintArray(int var1, CTPositiveFixedPercentage var2);

    public CTPositiveFixedPercentage insertNewTint(int var1);

    public CTPositiveFixedPercentage addNewTint();

    public void removeTint(int var1);

    public List<CTPositiveFixedPercentage> getShadeList();

    @Deprecated
    public CTPositiveFixedPercentage[] getShadeArray();

    public CTPositiveFixedPercentage getShadeArray(int var1);

    public int sizeOfShadeArray();

    public void setShadeArray(CTPositiveFixedPercentage[] var1);

    public void setShadeArray(int var1, CTPositiveFixedPercentage var2);

    public CTPositiveFixedPercentage insertNewShade(int var1);

    public CTPositiveFixedPercentage addNewShade();

    public void removeShade(int var1);

    public List<CTComplementTransform> getCompList();

    @Deprecated
    public CTComplementTransform[] getCompArray();

    public CTComplementTransform getCompArray(int var1);

    public int sizeOfCompArray();

    public void setCompArray(CTComplementTransform[] var1);

    public void setCompArray(int var1, CTComplementTransform var2);

    public CTComplementTransform insertNewComp(int var1);

    public CTComplementTransform addNewComp();

    public void removeComp(int var1);

    public List<CTInverseTransform> getInvList();

    @Deprecated
    public CTInverseTransform[] getInvArray();

    public CTInverseTransform getInvArray(int var1);

    public int sizeOfInvArray();

    public void setInvArray(CTInverseTransform[] var1);

    public void setInvArray(int var1, CTInverseTransform var2);

    public CTInverseTransform insertNewInv(int var1);

    public CTInverseTransform addNewInv();

    public void removeInv(int var1);

    public List<CTGrayscaleTransform> getGrayList();

    @Deprecated
    public CTGrayscaleTransform[] getGrayArray();

    public CTGrayscaleTransform getGrayArray(int var1);

    public int sizeOfGrayArray();

    public void setGrayArray(CTGrayscaleTransform[] var1);

    public void setGrayArray(int var1, CTGrayscaleTransform var2);

    public CTGrayscaleTransform insertNewGray(int var1);

    public CTGrayscaleTransform addNewGray();

    public void removeGray(int var1);

    public List<CTPositiveFixedPercentage> getAlphaList();

    @Deprecated
    public CTPositiveFixedPercentage[] getAlphaArray();

    public CTPositiveFixedPercentage getAlphaArray(int var1);

    public int sizeOfAlphaArray();

    public void setAlphaArray(CTPositiveFixedPercentage[] var1);

    public void setAlphaArray(int var1, CTPositiveFixedPercentage var2);

    public CTPositiveFixedPercentage insertNewAlpha(int var1);

    public CTPositiveFixedPercentage addNewAlpha();

    public void removeAlpha(int var1);

    public List<CTFixedPercentage> getAlphaOffList();

    @Deprecated
    public CTFixedPercentage[] getAlphaOffArray();

    public CTFixedPercentage getAlphaOffArray(int var1);

    public int sizeOfAlphaOffArray();

    public void setAlphaOffArray(CTFixedPercentage[] var1);

    public void setAlphaOffArray(int var1, CTFixedPercentage var2);

    public CTFixedPercentage insertNewAlphaOff(int var1);

    public CTFixedPercentage addNewAlphaOff();

    public void removeAlphaOff(int var1);

    public List<CTPositivePercentage> getAlphaModList();

    @Deprecated
    public CTPositivePercentage[] getAlphaModArray();

    public CTPositivePercentage getAlphaModArray(int var1);

    public int sizeOfAlphaModArray();

    public void setAlphaModArray(CTPositivePercentage[] var1);

    public void setAlphaModArray(int var1, CTPositivePercentage var2);

    public CTPositivePercentage insertNewAlphaMod(int var1);

    public CTPositivePercentage addNewAlphaMod();

    public void removeAlphaMod(int var1);

    public List<CTPositiveFixedAngle> getHueList();

    @Deprecated
    public CTPositiveFixedAngle[] getHueArray();

    public CTPositiveFixedAngle getHueArray(int var1);

    public int sizeOfHueArray();

    public void setHueArray(CTPositiveFixedAngle[] var1);

    public void setHueArray(int var1, CTPositiveFixedAngle var2);

    public CTPositiveFixedAngle insertNewHue(int var1);

    public CTPositiveFixedAngle addNewHue();

    public void removeHue(int var1);

    public List<CTAngle> getHueOffList();

    @Deprecated
    public CTAngle[] getHueOffArray();

    public CTAngle getHueOffArray(int var1);

    public int sizeOfHueOffArray();

    public void setHueOffArray(CTAngle[] var1);

    public void setHueOffArray(int var1, CTAngle var2);

    public CTAngle insertNewHueOff(int var1);

    public CTAngle addNewHueOff();

    public void removeHueOff(int var1);

    public List<CTPositivePercentage> getHueModList();

    @Deprecated
    public CTPositivePercentage[] getHueModArray();

    public CTPositivePercentage getHueModArray(int var1);

    public int sizeOfHueModArray();

    public void setHueModArray(CTPositivePercentage[] var1);

    public void setHueModArray(int var1, CTPositivePercentage var2);

    public CTPositivePercentage insertNewHueMod(int var1);

    public CTPositivePercentage addNewHueMod();

    public void removeHueMod(int var1);

    public List<CTPercentage> getSatList();

    @Deprecated
    public CTPercentage[] getSatArray();

    public CTPercentage getSatArray(int var1);

    public int sizeOfSatArray();

    public void setSatArray(CTPercentage[] var1);

    public void setSatArray(int var1, CTPercentage var2);

    public CTPercentage insertNewSat(int var1);

    public CTPercentage addNewSat();

    public void removeSat(int var1);

    public List<CTPercentage> getSatOffList();

    @Deprecated
    public CTPercentage[] getSatOffArray();

    public CTPercentage getSatOffArray(int var1);

    public int sizeOfSatOffArray();

    public void setSatOffArray(CTPercentage[] var1);

    public void setSatOffArray(int var1, CTPercentage var2);

    public CTPercentage insertNewSatOff(int var1);

    public CTPercentage addNewSatOff();

    public void removeSatOff(int var1);

    public List<CTPercentage> getSatModList();

    @Deprecated
    public CTPercentage[] getSatModArray();

    public CTPercentage getSatModArray(int var1);

    public int sizeOfSatModArray();

    public void setSatModArray(CTPercentage[] var1);

    public void setSatModArray(int var1, CTPercentage var2);

    public CTPercentage insertNewSatMod(int var1);

    public CTPercentage addNewSatMod();

    public void removeSatMod(int var1);

    public List<CTPercentage> getLumList();

    @Deprecated
    public CTPercentage[] getLumArray();

    public CTPercentage getLumArray(int var1);

    public int sizeOfLumArray();

    public void setLumArray(CTPercentage[] var1);

    public void setLumArray(int var1, CTPercentage var2);

    public CTPercentage insertNewLum(int var1);

    public CTPercentage addNewLum();

    public void removeLum(int var1);

    public List<CTPercentage> getLumOffList();

    @Deprecated
    public CTPercentage[] getLumOffArray();

    public CTPercentage getLumOffArray(int var1);

    public int sizeOfLumOffArray();

    public void setLumOffArray(CTPercentage[] var1);

    public void setLumOffArray(int var1, CTPercentage var2);

    public CTPercentage insertNewLumOff(int var1);

    public CTPercentage addNewLumOff();

    public void removeLumOff(int var1);

    public List<CTPercentage> getLumModList();

    @Deprecated
    public CTPercentage[] getLumModArray();

    public CTPercentage getLumModArray(int var1);

    public int sizeOfLumModArray();

    public void setLumModArray(CTPercentage[] var1);

    public void setLumModArray(int var1, CTPercentage var2);

    public CTPercentage insertNewLumMod(int var1);

    public CTPercentage addNewLumMod();

    public void removeLumMod(int var1);

    public List<CTPercentage> getRedList();

    @Deprecated
    public CTPercentage[] getRedArray();

    public CTPercentage getRedArray(int var1);

    public int sizeOfRedArray();

    public void setRedArray(CTPercentage[] var1);

    public void setRedArray(int var1, CTPercentage var2);

    public CTPercentage insertNewRed(int var1);

    public CTPercentage addNewRed();

    public void removeRed(int var1);

    public List<CTPercentage> getRedOffList();

    @Deprecated
    public CTPercentage[] getRedOffArray();

    public CTPercentage getRedOffArray(int var1);

    public int sizeOfRedOffArray();

    public void setRedOffArray(CTPercentage[] var1);

    public void setRedOffArray(int var1, CTPercentage var2);

    public CTPercentage insertNewRedOff(int var1);

    public CTPercentage addNewRedOff();

    public void removeRedOff(int var1);

    public List<CTPercentage> getRedModList();

    @Deprecated
    public CTPercentage[] getRedModArray();

    public CTPercentage getRedModArray(int var1);

    public int sizeOfRedModArray();

    public void setRedModArray(CTPercentage[] var1);

    public void setRedModArray(int var1, CTPercentage var2);

    public CTPercentage insertNewRedMod(int var1);

    public CTPercentage addNewRedMod();

    public void removeRedMod(int var1);

    public List<CTPercentage> getGreenList();

    @Deprecated
    public CTPercentage[] getGreenArray();

    public CTPercentage getGreenArray(int var1);

    public int sizeOfGreenArray();

    public void setGreenArray(CTPercentage[] var1);

    public void setGreenArray(int var1, CTPercentage var2);

    public CTPercentage insertNewGreen(int var1);

    public CTPercentage addNewGreen();

    public void removeGreen(int var1);

    public List<CTPercentage> getGreenOffList();

    @Deprecated
    public CTPercentage[] getGreenOffArray();

    public CTPercentage getGreenOffArray(int var1);

    public int sizeOfGreenOffArray();

    public void setGreenOffArray(CTPercentage[] var1);

    public void setGreenOffArray(int var1, CTPercentage var2);

    public CTPercentage insertNewGreenOff(int var1);

    public CTPercentage addNewGreenOff();

    public void removeGreenOff(int var1);

    public List<CTPercentage> getGreenModList();

    @Deprecated
    public CTPercentage[] getGreenModArray();

    public CTPercentage getGreenModArray(int var1);

    public int sizeOfGreenModArray();

    public void setGreenModArray(CTPercentage[] var1);

    public void setGreenModArray(int var1, CTPercentage var2);

    public CTPercentage insertNewGreenMod(int var1);

    public CTPercentage addNewGreenMod();

    public void removeGreenMod(int var1);

    public List<CTPercentage> getBlueList();

    @Deprecated
    public CTPercentage[] getBlueArray();

    public CTPercentage getBlueArray(int var1);

    public int sizeOfBlueArray();

    public void setBlueArray(CTPercentage[] var1);

    public void setBlueArray(int var1, CTPercentage var2);

    public CTPercentage insertNewBlue(int var1);

    public CTPercentage addNewBlue();

    public void removeBlue(int var1);

    public List<CTPercentage> getBlueOffList();

    @Deprecated
    public CTPercentage[] getBlueOffArray();

    public CTPercentage getBlueOffArray(int var1);

    public int sizeOfBlueOffArray();

    public void setBlueOffArray(CTPercentage[] var1);

    public void setBlueOffArray(int var1, CTPercentage var2);

    public CTPercentage insertNewBlueOff(int var1);

    public CTPercentage addNewBlueOff();

    public void removeBlueOff(int var1);

    public List<CTPercentage> getBlueModList();

    @Deprecated
    public CTPercentage[] getBlueModArray();

    public CTPercentage getBlueModArray(int var1);

    public int sizeOfBlueModArray();

    public void setBlueModArray(CTPercentage[] var1);

    public void setBlueModArray(int var1, CTPercentage var2);

    public CTPercentage insertNewBlueMod(int var1);

    public CTPercentage addNewBlueMod();

    public void removeBlueMod(int var1);

    public List<CTGammaTransform> getGammaList();

    @Deprecated
    public CTGammaTransform[] getGammaArray();

    public CTGammaTransform getGammaArray(int var1);

    public int sizeOfGammaArray();

    public void setGammaArray(CTGammaTransform[] var1);

    public void setGammaArray(int var1, CTGammaTransform var2);

    public CTGammaTransform insertNewGamma(int var1);

    public CTGammaTransform addNewGamma();

    public void removeGamma(int var1);

    public List<CTInverseGammaTransform> getInvGammaList();

    @Deprecated
    public CTInverseGammaTransform[] getInvGammaArray();

    public CTInverseGammaTransform getInvGammaArray(int var1);

    public int sizeOfInvGammaArray();

    public void setInvGammaArray(CTInverseGammaTransform[] var1);

    public void setInvGammaArray(int var1, CTInverseGammaTransform var2);

    public CTInverseGammaTransform insertNewInvGamma(int var1);

    public CTInverseGammaTransform addNewInvGamma();

    public void removeInvGamma(int var1);

    public int getR();

    public STPercentage xgetR();

    public void setR(int var1);

    public void xsetR(STPercentage var1);

    public int getG();

    public STPercentage xgetG();

    public void setG(int var1);

    public void xsetG(STPercentage var1);

    public int getB();

    public STPercentage xgetB();

    public void setB(int var1);

    public void xsetB(STPercentage var1);

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTScRgbColor.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTScRgbColor newInstance() {
            return (CTScRgbColor)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTScRgbColor newInstance(XmlOptions xmlOptions) {
            return (CTScRgbColor)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTScRgbColor parse(String string) throws XmlException {
            return (CTScRgbColor)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTScRgbColor parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTScRgbColor)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTScRgbColor parse(File file) throws XmlException, IOException {
            return (CTScRgbColor)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTScRgbColor parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTScRgbColor)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTScRgbColor parse(URL uRL) throws XmlException, IOException {
            return (CTScRgbColor)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTScRgbColor parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTScRgbColor)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTScRgbColor parse(InputStream inputStream) throws XmlException, IOException {
            return (CTScRgbColor)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTScRgbColor parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTScRgbColor)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTScRgbColor parse(Reader reader) throws XmlException, IOException {
            return (CTScRgbColor)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTScRgbColor parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTScRgbColor)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTScRgbColor parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTScRgbColor)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTScRgbColor parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTScRgbColor)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTScRgbColor parse(Node node) throws XmlException {
            return (CTScRgbColor)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTScRgbColor parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTScRgbColor)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTScRgbColor parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTScRgbColor)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTScRgbColor parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTScRgbColor)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

