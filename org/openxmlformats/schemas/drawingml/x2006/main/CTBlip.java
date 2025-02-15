/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.drawingml.x2006.main.CTAlphaBiLevelEffect
 *  org.openxmlformats.schemas.drawingml.x2006.main.CTAlphaCeilingEffect
 *  org.openxmlformats.schemas.drawingml.x2006.main.CTAlphaFloorEffect
 *  org.openxmlformats.schemas.drawingml.x2006.main.CTAlphaInverseEffect
 *  org.openxmlformats.schemas.drawingml.x2006.main.CTAlphaModulateEffect
 *  org.openxmlformats.schemas.drawingml.x2006.main.CTAlphaReplaceEffect
 *  org.openxmlformats.schemas.drawingml.x2006.main.CTBiLevelEffect
 *  org.openxmlformats.schemas.drawingml.x2006.main.CTBlurEffect
 *  org.openxmlformats.schemas.drawingml.x2006.main.CTColorChangeEffect
 *  org.openxmlformats.schemas.drawingml.x2006.main.CTColorReplaceEffect
 *  org.openxmlformats.schemas.drawingml.x2006.main.CTFillOverlayEffect
 *  org.openxmlformats.schemas.drawingml.x2006.main.CTGrayscaleEffect
 *  org.openxmlformats.schemas.drawingml.x2006.main.CTHSLEffect
 *  org.openxmlformats.schemas.drawingml.x2006.main.CTLuminanceEffect
 *  org.openxmlformats.schemas.drawingml.x2006.main.CTTintEffect
 *  org.openxmlformats.schemas.drawingml.x2006.main.STBlipCompression
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
import org.openxmlformats.schemas.drawingml.x2006.main.CTAlphaBiLevelEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTAlphaCeilingEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTAlphaFloorEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTAlphaInverseEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTAlphaModulateEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTAlphaModulateFixedEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTAlphaReplaceEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTBiLevelEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTBlurEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTColorChangeEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTColorReplaceEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTDuotoneEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTFillOverlayEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGrayscaleEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTHSLEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTLuminanceEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTOfficeArtExtensionList;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTintEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.STBlipCompression;
import org.openxmlformats.schemas.officeDocument.x2006.relationships.STRelationshipId;
import org.w3c.dom.Node;

public interface CTBlip
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTBlip.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctblip034ctype");

    public List<CTAlphaBiLevelEffect> getAlphaBiLevelList();

    @Deprecated
    public CTAlphaBiLevelEffect[] getAlphaBiLevelArray();

    public CTAlphaBiLevelEffect getAlphaBiLevelArray(int var1);

    public int sizeOfAlphaBiLevelArray();

    public void setAlphaBiLevelArray(CTAlphaBiLevelEffect[] var1);

    public void setAlphaBiLevelArray(int var1, CTAlphaBiLevelEffect var2);

    public CTAlphaBiLevelEffect insertNewAlphaBiLevel(int var1);

    public CTAlphaBiLevelEffect addNewAlphaBiLevel();

    public void removeAlphaBiLevel(int var1);

    public List<CTAlphaCeilingEffect> getAlphaCeilingList();

    @Deprecated
    public CTAlphaCeilingEffect[] getAlphaCeilingArray();

    public CTAlphaCeilingEffect getAlphaCeilingArray(int var1);

    public int sizeOfAlphaCeilingArray();

    public void setAlphaCeilingArray(CTAlphaCeilingEffect[] var1);

    public void setAlphaCeilingArray(int var1, CTAlphaCeilingEffect var2);

    public CTAlphaCeilingEffect insertNewAlphaCeiling(int var1);

    public CTAlphaCeilingEffect addNewAlphaCeiling();

    public void removeAlphaCeiling(int var1);

    public List<CTAlphaFloorEffect> getAlphaFloorList();

    @Deprecated
    public CTAlphaFloorEffect[] getAlphaFloorArray();

    public CTAlphaFloorEffect getAlphaFloorArray(int var1);

    public int sizeOfAlphaFloorArray();

    public void setAlphaFloorArray(CTAlphaFloorEffect[] var1);

    public void setAlphaFloorArray(int var1, CTAlphaFloorEffect var2);

    public CTAlphaFloorEffect insertNewAlphaFloor(int var1);

    public CTAlphaFloorEffect addNewAlphaFloor();

    public void removeAlphaFloor(int var1);

    public List<CTAlphaInverseEffect> getAlphaInvList();

    @Deprecated
    public CTAlphaInverseEffect[] getAlphaInvArray();

    public CTAlphaInverseEffect getAlphaInvArray(int var1);

    public int sizeOfAlphaInvArray();

    public void setAlphaInvArray(CTAlphaInverseEffect[] var1);

    public void setAlphaInvArray(int var1, CTAlphaInverseEffect var2);

    public CTAlphaInverseEffect insertNewAlphaInv(int var1);

    public CTAlphaInverseEffect addNewAlphaInv();

    public void removeAlphaInv(int var1);

    public List<CTAlphaModulateEffect> getAlphaModList();

    @Deprecated
    public CTAlphaModulateEffect[] getAlphaModArray();

    public CTAlphaModulateEffect getAlphaModArray(int var1);

    public int sizeOfAlphaModArray();

    public void setAlphaModArray(CTAlphaModulateEffect[] var1);

    public void setAlphaModArray(int var1, CTAlphaModulateEffect var2);

    public CTAlphaModulateEffect insertNewAlphaMod(int var1);

    public CTAlphaModulateEffect addNewAlphaMod();

    public void removeAlphaMod(int var1);

    public List<CTAlphaModulateFixedEffect> getAlphaModFixList();

    @Deprecated
    public CTAlphaModulateFixedEffect[] getAlphaModFixArray();

    public CTAlphaModulateFixedEffect getAlphaModFixArray(int var1);

    public int sizeOfAlphaModFixArray();

    public void setAlphaModFixArray(CTAlphaModulateFixedEffect[] var1);

    public void setAlphaModFixArray(int var1, CTAlphaModulateFixedEffect var2);

    public CTAlphaModulateFixedEffect insertNewAlphaModFix(int var1);

    public CTAlphaModulateFixedEffect addNewAlphaModFix();

    public void removeAlphaModFix(int var1);

    public List<CTAlphaReplaceEffect> getAlphaReplList();

    @Deprecated
    public CTAlphaReplaceEffect[] getAlphaReplArray();

    public CTAlphaReplaceEffect getAlphaReplArray(int var1);

    public int sizeOfAlphaReplArray();

    public void setAlphaReplArray(CTAlphaReplaceEffect[] var1);

    public void setAlphaReplArray(int var1, CTAlphaReplaceEffect var2);

    public CTAlphaReplaceEffect insertNewAlphaRepl(int var1);

    public CTAlphaReplaceEffect addNewAlphaRepl();

    public void removeAlphaRepl(int var1);

    public List<CTBiLevelEffect> getBiLevelList();

    @Deprecated
    public CTBiLevelEffect[] getBiLevelArray();

    public CTBiLevelEffect getBiLevelArray(int var1);

    public int sizeOfBiLevelArray();

    public void setBiLevelArray(CTBiLevelEffect[] var1);

    public void setBiLevelArray(int var1, CTBiLevelEffect var2);

    public CTBiLevelEffect insertNewBiLevel(int var1);

    public CTBiLevelEffect addNewBiLevel();

    public void removeBiLevel(int var1);

    public List<CTBlurEffect> getBlurList();

    @Deprecated
    public CTBlurEffect[] getBlurArray();

    public CTBlurEffect getBlurArray(int var1);

    public int sizeOfBlurArray();

    public void setBlurArray(CTBlurEffect[] var1);

    public void setBlurArray(int var1, CTBlurEffect var2);

    public CTBlurEffect insertNewBlur(int var1);

    public CTBlurEffect addNewBlur();

    public void removeBlur(int var1);

    public List<CTColorChangeEffect> getClrChangeList();

    @Deprecated
    public CTColorChangeEffect[] getClrChangeArray();

    public CTColorChangeEffect getClrChangeArray(int var1);

    public int sizeOfClrChangeArray();

    public void setClrChangeArray(CTColorChangeEffect[] var1);

    public void setClrChangeArray(int var1, CTColorChangeEffect var2);

    public CTColorChangeEffect insertNewClrChange(int var1);

    public CTColorChangeEffect addNewClrChange();

    public void removeClrChange(int var1);

    public List<CTColorReplaceEffect> getClrReplList();

    @Deprecated
    public CTColorReplaceEffect[] getClrReplArray();

    public CTColorReplaceEffect getClrReplArray(int var1);

    public int sizeOfClrReplArray();

    public void setClrReplArray(CTColorReplaceEffect[] var1);

    public void setClrReplArray(int var1, CTColorReplaceEffect var2);

    public CTColorReplaceEffect insertNewClrRepl(int var1);

    public CTColorReplaceEffect addNewClrRepl();

    public void removeClrRepl(int var1);

    public List<CTDuotoneEffect> getDuotoneList();

    @Deprecated
    public CTDuotoneEffect[] getDuotoneArray();

    public CTDuotoneEffect getDuotoneArray(int var1);

    public int sizeOfDuotoneArray();

    public void setDuotoneArray(CTDuotoneEffect[] var1);

    public void setDuotoneArray(int var1, CTDuotoneEffect var2);

    public CTDuotoneEffect insertNewDuotone(int var1);

    public CTDuotoneEffect addNewDuotone();

    public void removeDuotone(int var1);

    public List<CTFillOverlayEffect> getFillOverlayList();

    @Deprecated
    public CTFillOverlayEffect[] getFillOverlayArray();

    public CTFillOverlayEffect getFillOverlayArray(int var1);

    public int sizeOfFillOverlayArray();

    public void setFillOverlayArray(CTFillOverlayEffect[] var1);

    public void setFillOverlayArray(int var1, CTFillOverlayEffect var2);

    public CTFillOverlayEffect insertNewFillOverlay(int var1);

    public CTFillOverlayEffect addNewFillOverlay();

    public void removeFillOverlay(int var1);

    public List<CTGrayscaleEffect> getGraysclList();

    @Deprecated
    public CTGrayscaleEffect[] getGraysclArray();

    public CTGrayscaleEffect getGraysclArray(int var1);

    public int sizeOfGraysclArray();

    public void setGraysclArray(CTGrayscaleEffect[] var1);

    public void setGraysclArray(int var1, CTGrayscaleEffect var2);

    public CTGrayscaleEffect insertNewGrayscl(int var1);

    public CTGrayscaleEffect addNewGrayscl();

    public void removeGrayscl(int var1);

    public List<CTHSLEffect> getHslList();

    @Deprecated
    public CTHSLEffect[] getHslArray();

    public CTHSLEffect getHslArray(int var1);

    public int sizeOfHslArray();

    public void setHslArray(CTHSLEffect[] var1);

    public void setHslArray(int var1, CTHSLEffect var2);

    public CTHSLEffect insertNewHsl(int var1);

    public CTHSLEffect addNewHsl();

    public void removeHsl(int var1);

    public List<CTLuminanceEffect> getLumList();

    @Deprecated
    public CTLuminanceEffect[] getLumArray();

    public CTLuminanceEffect getLumArray(int var1);

    public int sizeOfLumArray();

    public void setLumArray(CTLuminanceEffect[] var1);

    public void setLumArray(int var1, CTLuminanceEffect var2);

    public CTLuminanceEffect insertNewLum(int var1);

    public CTLuminanceEffect addNewLum();

    public void removeLum(int var1);

    public List<CTTintEffect> getTintList();

    @Deprecated
    public CTTintEffect[] getTintArray();

    public CTTintEffect getTintArray(int var1);

    public int sizeOfTintArray();

    public void setTintArray(CTTintEffect[] var1);

    public void setTintArray(int var1, CTTintEffect var2);

    public CTTintEffect insertNewTint(int var1);

    public CTTintEffect addNewTint();

    public void removeTint(int var1);

    public CTOfficeArtExtensionList getExtLst();

    public boolean isSetExtLst();

    public void setExtLst(CTOfficeArtExtensionList var1);

    public CTOfficeArtExtensionList addNewExtLst();

    public void unsetExtLst();

    public String getEmbed();

    public STRelationshipId xgetEmbed();

    public boolean isSetEmbed();

    public void setEmbed(String var1);

    public void xsetEmbed(STRelationshipId var1);

    public void unsetEmbed();

    public String getLink();

    public STRelationshipId xgetLink();

    public boolean isSetLink();

    public void setLink(String var1);

    public void xsetLink(STRelationshipId var1);

    public void unsetLink();

    public STBlipCompression.Enum getCstate();

    public STBlipCompression xgetCstate();

    public boolean isSetCstate();

    public void setCstate(STBlipCompression.Enum var1);

    public void xsetCstate(STBlipCompression var1);

    public void unsetCstate();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTBlip.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTBlip newInstance() {
            return (CTBlip)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTBlip newInstance(XmlOptions xmlOptions) {
            return (CTBlip)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTBlip parse(String string) throws XmlException {
            return (CTBlip)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTBlip parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTBlip)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTBlip parse(File file) throws XmlException, IOException {
            return (CTBlip)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTBlip parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTBlip)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTBlip parse(URL uRL) throws XmlException, IOException {
            return (CTBlip)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTBlip parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTBlip)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTBlip parse(InputStream inputStream) throws XmlException, IOException {
            return (CTBlip)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTBlip parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTBlip)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTBlip parse(Reader reader) throws XmlException, IOException {
            return (CTBlip)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTBlip parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTBlip)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTBlip parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTBlip)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTBlip parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTBlip)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTBlip parse(Node node) throws XmlException {
            return (CTBlip)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTBlip parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTBlip)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTBlip parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTBlip)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTBlip parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTBlip)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

