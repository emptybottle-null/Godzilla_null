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
import org.apache.xmlbeans.StringEnumAbstractBase;
import org.apache.xmlbeans.XmlBeans;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.XmlToken;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.w3c.dom.Node;

public interface STPresetPatternVal
extends XmlToken {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(STPresetPatternVal.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("stpresetpatternval1d5btype");
    public static final Enum PCT_5 = Enum.forString("pct5");
    public static final Enum PCT_10 = Enum.forString("pct10");
    public static final Enum PCT_20 = Enum.forString("pct20");
    public static final Enum PCT_25 = Enum.forString("pct25");
    public static final Enum PCT_30 = Enum.forString("pct30");
    public static final Enum PCT_40 = Enum.forString("pct40");
    public static final Enum PCT_50 = Enum.forString("pct50");
    public static final Enum PCT_60 = Enum.forString("pct60");
    public static final Enum PCT_70 = Enum.forString("pct70");
    public static final Enum PCT_75 = Enum.forString("pct75");
    public static final Enum PCT_80 = Enum.forString("pct80");
    public static final Enum PCT_90 = Enum.forString("pct90");
    public static final Enum HORZ = Enum.forString("horz");
    public static final Enum VERT = Enum.forString("vert");
    public static final Enum LT_HORZ = Enum.forString("ltHorz");
    public static final Enum LT_VERT = Enum.forString("ltVert");
    public static final Enum DK_HORZ = Enum.forString("dkHorz");
    public static final Enum DK_VERT = Enum.forString("dkVert");
    public static final Enum NAR_HORZ = Enum.forString("narHorz");
    public static final Enum NAR_VERT = Enum.forString("narVert");
    public static final Enum DASH_HORZ = Enum.forString("dashHorz");
    public static final Enum DASH_VERT = Enum.forString("dashVert");
    public static final Enum CROSS = Enum.forString("cross");
    public static final Enum DN_DIAG = Enum.forString("dnDiag");
    public static final Enum UP_DIAG = Enum.forString("upDiag");
    public static final Enum LT_DN_DIAG = Enum.forString("ltDnDiag");
    public static final Enum LT_UP_DIAG = Enum.forString("ltUpDiag");
    public static final Enum DK_DN_DIAG = Enum.forString("dkDnDiag");
    public static final Enum DK_UP_DIAG = Enum.forString("dkUpDiag");
    public static final Enum WD_DN_DIAG = Enum.forString("wdDnDiag");
    public static final Enum WD_UP_DIAG = Enum.forString("wdUpDiag");
    public static final Enum DASH_DN_DIAG = Enum.forString("dashDnDiag");
    public static final Enum DASH_UP_DIAG = Enum.forString("dashUpDiag");
    public static final Enum DIAG_CROSS = Enum.forString("diagCross");
    public static final Enum SM_CHECK = Enum.forString("smCheck");
    public static final Enum LG_CHECK = Enum.forString("lgCheck");
    public static final Enum SM_GRID = Enum.forString("smGrid");
    public static final Enum LG_GRID = Enum.forString("lgGrid");
    public static final Enum DOT_GRID = Enum.forString("dotGrid");
    public static final Enum SM_CONFETTI = Enum.forString("smConfetti");
    public static final Enum LG_CONFETTI = Enum.forString("lgConfetti");
    public static final Enum HORZ_BRICK = Enum.forString("horzBrick");
    public static final Enum DIAG_BRICK = Enum.forString("diagBrick");
    public static final Enum SOLID_DMND = Enum.forString("solidDmnd");
    public static final Enum OPEN_DMND = Enum.forString("openDmnd");
    public static final Enum DOT_DMND = Enum.forString("dotDmnd");
    public static final Enum PLAID = Enum.forString("plaid");
    public static final Enum SPHERE = Enum.forString("sphere");
    public static final Enum WEAVE = Enum.forString("weave");
    public static final Enum DIVOT = Enum.forString("divot");
    public static final Enum SHINGLE = Enum.forString("shingle");
    public static final Enum WAVE = Enum.forString("wave");
    public static final Enum TRELLIS = Enum.forString("trellis");
    public static final Enum ZIG_ZAG = Enum.forString("zigZag");
    public static final int INT_PCT_5 = 1;
    public static final int INT_PCT_10 = 2;
    public static final int INT_PCT_20 = 3;
    public static final int INT_PCT_25 = 4;
    public static final int INT_PCT_30 = 5;
    public static final int INT_PCT_40 = 6;
    public static final int INT_PCT_50 = 7;
    public static final int INT_PCT_60 = 8;
    public static final int INT_PCT_70 = 9;
    public static final int INT_PCT_75 = 10;
    public static final int INT_PCT_80 = 11;
    public static final int INT_PCT_90 = 12;
    public static final int INT_HORZ = 13;
    public static final int INT_VERT = 14;
    public static final int INT_LT_HORZ = 15;
    public static final int INT_LT_VERT = 16;
    public static final int INT_DK_HORZ = 17;
    public static final int INT_DK_VERT = 18;
    public static final int INT_NAR_HORZ = 19;
    public static final int INT_NAR_VERT = 20;
    public static final int INT_DASH_HORZ = 21;
    public static final int INT_DASH_VERT = 22;
    public static final int INT_CROSS = 23;
    public static final int INT_DN_DIAG = 24;
    public static final int INT_UP_DIAG = 25;
    public static final int INT_LT_DN_DIAG = 26;
    public static final int INT_LT_UP_DIAG = 27;
    public static final int INT_DK_DN_DIAG = 28;
    public static final int INT_DK_UP_DIAG = 29;
    public static final int INT_WD_DN_DIAG = 30;
    public static final int INT_WD_UP_DIAG = 31;
    public static final int INT_DASH_DN_DIAG = 32;
    public static final int INT_DASH_UP_DIAG = 33;
    public static final int INT_DIAG_CROSS = 34;
    public static final int INT_SM_CHECK = 35;
    public static final int INT_LG_CHECK = 36;
    public static final int INT_SM_GRID = 37;
    public static final int INT_LG_GRID = 38;
    public static final int INT_DOT_GRID = 39;
    public static final int INT_SM_CONFETTI = 40;
    public static final int INT_LG_CONFETTI = 41;
    public static final int INT_HORZ_BRICK = 42;
    public static final int INT_DIAG_BRICK = 43;
    public static final int INT_SOLID_DMND = 44;
    public static final int INT_OPEN_DMND = 45;
    public static final int INT_DOT_DMND = 46;
    public static final int INT_PLAID = 47;
    public static final int INT_SPHERE = 48;
    public static final int INT_WEAVE = 49;
    public static final int INT_DIVOT = 50;
    public static final int INT_SHINGLE = 51;
    public static final int INT_WAVE = 52;
    public static final int INT_TRELLIS = 53;
    public static final int INT_ZIG_ZAG = 54;

    public StringEnumAbstractBase enumValue();

    public void set(StringEnumAbstractBase var1);

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        public static STPresetPatternVal newValue(Object object) {
            return (STPresetPatternVal)type.newValue(object);
        }

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(STPresetPatternVal.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static STPresetPatternVal newInstance() {
            return (STPresetPatternVal)Factory.getTypeLoader().newInstance(type, null);
        }

        public static STPresetPatternVal newInstance(XmlOptions xmlOptions) {
            return (STPresetPatternVal)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static STPresetPatternVal parse(String string) throws XmlException {
            return (STPresetPatternVal)Factory.getTypeLoader().parse(string, type, null);
        }

        public static STPresetPatternVal parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (STPresetPatternVal)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static STPresetPatternVal parse(File file) throws XmlException, IOException {
            return (STPresetPatternVal)Factory.getTypeLoader().parse(file, type, null);
        }

        public static STPresetPatternVal parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STPresetPatternVal)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static STPresetPatternVal parse(URL uRL) throws XmlException, IOException {
            return (STPresetPatternVal)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static STPresetPatternVal parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STPresetPatternVal)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static STPresetPatternVal parse(InputStream inputStream) throws XmlException, IOException {
            return (STPresetPatternVal)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static STPresetPatternVal parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STPresetPatternVal)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static STPresetPatternVal parse(Reader reader) throws XmlException, IOException {
            return (STPresetPatternVal)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static STPresetPatternVal parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STPresetPatternVal)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static STPresetPatternVal parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (STPresetPatternVal)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static STPresetPatternVal parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (STPresetPatternVal)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static STPresetPatternVal parse(Node node) throws XmlException {
            return (STPresetPatternVal)Factory.getTypeLoader().parse(node, type, null);
        }

        public static STPresetPatternVal parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (STPresetPatternVal)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static STPresetPatternVal parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (STPresetPatternVal)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static STPresetPatternVal parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (STPresetPatternVal)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

    public static final class Enum
    extends StringEnumAbstractBase {
        static final int INT_PCT_5 = 1;
        static final int INT_PCT_10 = 2;
        static final int INT_PCT_20 = 3;
        static final int INT_PCT_25 = 4;
        static final int INT_PCT_30 = 5;
        static final int INT_PCT_40 = 6;
        static final int INT_PCT_50 = 7;
        static final int INT_PCT_60 = 8;
        static final int INT_PCT_70 = 9;
        static final int INT_PCT_75 = 10;
        static final int INT_PCT_80 = 11;
        static final int INT_PCT_90 = 12;
        static final int INT_HORZ = 13;
        static final int INT_VERT = 14;
        static final int INT_LT_HORZ = 15;
        static final int INT_LT_VERT = 16;
        static final int INT_DK_HORZ = 17;
        static final int INT_DK_VERT = 18;
        static final int INT_NAR_HORZ = 19;
        static final int INT_NAR_VERT = 20;
        static final int INT_DASH_HORZ = 21;
        static final int INT_DASH_VERT = 22;
        static final int INT_CROSS = 23;
        static final int INT_DN_DIAG = 24;
        static final int INT_UP_DIAG = 25;
        static final int INT_LT_DN_DIAG = 26;
        static final int INT_LT_UP_DIAG = 27;
        static final int INT_DK_DN_DIAG = 28;
        static final int INT_DK_UP_DIAG = 29;
        static final int INT_WD_DN_DIAG = 30;
        static final int INT_WD_UP_DIAG = 31;
        static final int INT_DASH_DN_DIAG = 32;
        static final int INT_DASH_UP_DIAG = 33;
        static final int INT_DIAG_CROSS = 34;
        static final int INT_SM_CHECK = 35;
        static final int INT_LG_CHECK = 36;
        static final int INT_SM_GRID = 37;
        static final int INT_LG_GRID = 38;
        static final int INT_DOT_GRID = 39;
        static final int INT_SM_CONFETTI = 40;
        static final int INT_LG_CONFETTI = 41;
        static final int INT_HORZ_BRICK = 42;
        static final int INT_DIAG_BRICK = 43;
        static final int INT_SOLID_DMND = 44;
        static final int INT_OPEN_DMND = 45;
        static final int INT_DOT_DMND = 46;
        static final int INT_PLAID = 47;
        static final int INT_SPHERE = 48;
        static final int INT_WEAVE = 49;
        static final int INT_DIVOT = 50;
        static final int INT_SHINGLE = 51;
        static final int INT_WAVE = 52;
        static final int INT_TRELLIS = 53;
        static final int INT_ZIG_ZAG = 54;
        public static final StringEnumAbstractBase.Table table = new StringEnumAbstractBase.Table(new Enum[]{new Enum("pct5", 1), new Enum("pct10", 2), new Enum("pct20", 3), new Enum("pct25", 4), new Enum("pct30", 5), new Enum("pct40", 6), new Enum("pct50", 7), new Enum("pct60", 8), new Enum("pct70", 9), new Enum("pct75", 10), new Enum("pct80", 11), new Enum("pct90", 12), new Enum("horz", 13), new Enum("vert", 14), new Enum("ltHorz", 15), new Enum("ltVert", 16), new Enum("dkHorz", 17), new Enum("dkVert", 18), new Enum("narHorz", 19), new Enum("narVert", 20), new Enum("dashHorz", 21), new Enum("dashVert", 22), new Enum("cross", 23), new Enum("dnDiag", 24), new Enum("upDiag", 25), new Enum("ltDnDiag", 26), new Enum("ltUpDiag", 27), new Enum("dkDnDiag", 28), new Enum("dkUpDiag", 29), new Enum("wdDnDiag", 30), new Enum("wdUpDiag", 31), new Enum("dashDnDiag", 32), new Enum("dashUpDiag", 33), new Enum("diagCross", 34), new Enum("smCheck", 35), new Enum("lgCheck", 36), new Enum("smGrid", 37), new Enum("lgGrid", 38), new Enum("dotGrid", 39), new Enum("smConfetti", 40), new Enum("lgConfetti", 41), new Enum("horzBrick", 42), new Enum("diagBrick", 43), new Enum("solidDmnd", 44), new Enum("openDmnd", 45), new Enum("dotDmnd", 46), new Enum("plaid", 47), new Enum("sphere", 48), new Enum("weave", 49), new Enum("divot", 50), new Enum("shingle", 51), new Enum("wave", 52), new Enum("trellis", 53), new Enum("zigZag", 54)});
        private static final long serialVersionUID = 1L;

        public static Enum forString(String string) {
            return (Enum)table.forString(string);
        }

        public static Enum forInt(int n) {
            return (Enum)table.forInt(n);
        }

        private Enum(String string, int n) {
            super(string, n);
        }

        private Object readResolve() {
            return Enum.forInt(this.intValue());
        }
    }
}

