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

public interface STPresetColorVal
extends XmlToken {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(STPresetColorVal.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("stpresetcolorval55e8type");
    public static final Enum ALICE_BLUE = Enum.forString("aliceBlue");
    public static final Enum ANTIQUE_WHITE = Enum.forString("antiqueWhite");
    public static final Enum AQUA = Enum.forString("aqua");
    public static final Enum AQUAMARINE = Enum.forString("aquamarine");
    public static final Enum AZURE = Enum.forString("azure");
    public static final Enum BEIGE = Enum.forString("beige");
    public static final Enum BISQUE = Enum.forString("bisque");
    public static final Enum BLACK = Enum.forString("black");
    public static final Enum BLANCHED_ALMOND = Enum.forString("blanchedAlmond");
    public static final Enum BLUE = Enum.forString("blue");
    public static final Enum BLUE_VIOLET = Enum.forString("blueViolet");
    public static final Enum BROWN = Enum.forString("brown");
    public static final Enum BURLY_WOOD = Enum.forString("burlyWood");
    public static final Enum CADET_BLUE = Enum.forString("cadetBlue");
    public static final Enum CHARTREUSE = Enum.forString("chartreuse");
    public static final Enum CHOCOLATE = Enum.forString("chocolate");
    public static final Enum CORAL = Enum.forString("coral");
    public static final Enum CORNFLOWER_BLUE = Enum.forString("cornflowerBlue");
    public static final Enum CORNSILK = Enum.forString("cornsilk");
    public static final Enum CRIMSON = Enum.forString("crimson");
    public static final Enum CYAN = Enum.forString("cyan");
    public static final Enum DK_BLUE = Enum.forString("dkBlue");
    public static final Enum DK_CYAN = Enum.forString("dkCyan");
    public static final Enum DK_GOLDENROD = Enum.forString("dkGoldenrod");
    public static final Enum DK_GRAY = Enum.forString("dkGray");
    public static final Enum DK_GREEN = Enum.forString("dkGreen");
    public static final Enum DK_KHAKI = Enum.forString("dkKhaki");
    public static final Enum DK_MAGENTA = Enum.forString("dkMagenta");
    public static final Enum DK_OLIVE_GREEN = Enum.forString("dkOliveGreen");
    public static final Enum DK_ORANGE = Enum.forString("dkOrange");
    public static final Enum DK_ORCHID = Enum.forString("dkOrchid");
    public static final Enum DK_RED = Enum.forString("dkRed");
    public static final Enum DK_SALMON = Enum.forString("dkSalmon");
    public static final Enum DK_SEA_GREEN = Enum.forString("dkSeaGreen");
    public static final Enum DK_SLATE_BLUE = Enum.forString("dkSlateBlue");
    public static final Enum DK_SLATE_GRAY = Enum.forString("dkSlateGray");
    public static final Enum DK_TURQUOISE = Enum.forString("dkTurquoise");
    public static final Enum DK_VIOLET = Enum.forString("dkViolet");
    public static final Enum DEEP_PINK = Enum.forString("deepPink");
    public static final Enum DEEP_SKY_BLUE = Enum.forString("deepSkyBlue");
    public static final Enum DIM_GRAY = Enum.forString("dimGray");
    public static final Enum DODGER_BLUE = Enum.forString("dodgerBlue");
    public static final Enum FIREBRICK = Enum.forString("firebrick");
    public static final Enum FLORAL_WHITE = Enum.forString("floralWhite");
    public static final Enum FOREST_GREEN = Enum.forString("forestGreen");
    public static final Enum FUCHSIA = Enum.forString("fuchsia");
    public static final Enum GAINSBORO = Enum.forString("gainsboro");
    public static final Enum GHOST_WHITE = Enum.forString("ghostWhite");
    public static final Enum GOLD = Enum.forString("gold");
    public static final Enum GOLDENROD = Enum.forString("goldenrod");
    public static final Enum GRAY = Enum.forString("gray");
    public static final Enum GREEN = Enum.forString("green");
    public static final Enum GREEN_YELLOW = Enum.forString("greenYellow");
    public static final Enum HONEYDEW = Enum.forString("honeydew");
    public static final Enum HOT_PINK = Enum.forString("hotPink");
    public static final Enum INDIAN_RED = Enum.forString("indianRed");
    public static final Enum INDIGO = Enum.forString("indigo");
    public static final Enum IVORY = Enum.forString("ivory");
    public static final Enum KHAKI = Enum.forString("khaki");
    public static final Enum LAVENDER = Enum.forString("lavender");
    public static final Enum LAVENDER_BLUSH = Enum.forString("lavenderBlush");
    public static final Enum LAWN_GREEN = Enum.forString("lawnGreen");
    public static final Enum LEMON_CHIFFON = Enum.forString("lemonChiffon");
    public static final Enum LT_BLUE = Enum.forString("ltBlue");
    public static final Enum LT_CORAL = Enum.forString("ltCoral");
    public static final Enum LT_CYAN = Enum.forString("ltCyan");
    public static final Enum LT_GOLDENROD_YELLOW = Enum.forString("ltGoldenrodYellow");
    public static final Enum LT_GRAY = Enum.forString("ltGray");
    public static final Enum LT_GREEN = Enum.forString("ltGreen");
    public static final Enum LT_PINK = Enum.forString("ltPink");
    public static final Enum LT_SALMON = Enum.forString("ltSalmon");
    public static final Enum LT_SEA_GREEN = Enum.forString("ltSeaGreen");
    public static final Enum LT_SKY_BLUE = Enum.forString("ltSkyBlue");
    public static final Enum LT_SLATE_GRAY = Enum.forString("ltSlateGray");
    public static final Enum LT_STEEL_BLUE = Enum.forString("ltSteelBlue");
    public static final Enum LT_YELLOW = Enum.forString("ltYellow");
    public static final Enum LIME = Enum.forString("lime");
    public static final Enum LIME_GREEN = Enum.forString("limeGreen");
    public static final Enum LINEN = Enum.forString("linen");
    public static final Enum MAGENTA = Enum.forString("magenta");
    public static final Enum MAROON = Enum.forString("maroon");
    public static final Enum MED_AQUAMARINE = Enum.forString("medAquamarine");
    public static final Enum MED_BLUE = Enum.forString("medBlue");
    public static final Enum MED_ORCHID = Enum.forString("medOrchid");
    public static final Enum MED_PURPLE = Enum.forString("medPurple");
    public static final Enum MED_SEA_GREEN = Enum.forString("medSeaGreen");
    public static final Enum MED_SLATE_BLUE = Enum.forString("medSlateBlue");
    public static final Enum MED_SPRING_GREEN = Enum.forString("medSpringGreen");
    public static final Enum MED_TURQUOISE = Enum.forString("medTurquoise");
    public static final Enum MED_VIOLET_RED = Enum.forString("medVioletRed");
    public static final Enum MIDNIGHT_BLUE = Enum.forString("midnightBlue");
    public static final Enum MINT_CREAM = Enum.forString("mintCream");
    public static final Enum MISTY_ROSE = Enum.forString("mistyRose");
    public static final Enum MOCCASIN = Enum.forString("moccasin");
    public static final Enum NAVAJO_WHITE = Enum.forString("navajoWhite");
    public static final Enum NAVY = Enum.forString("navy");
    public static final Enum OLD_LACE = Enum.forString("oldLace");
    public static final Enum OLIVE = Enum.forString("olive");
    public static final Enum OLIVE_DRAB = Enum.forString("oliveDrab");
    public static final Enum ORANGE = Enum.forString("orange");
    public static final Enum ORANGE_RED = Enum.forString("orangeRed");
    public static final Enum ORCHID = Enum.forString("orchid");
    public static final Enum PALE_GOLDENROD = Enum.forString("paleGoldenrod");
    public static final Enum PALE_GREEN = Enum.forString("paleGreen");
    public static final Enum PALE_TURQUOISE = Enum.forString("paleTurquoise");
    public static final Enum PALE_VIOLET_RED = Enum.forString("paleVioletRed");
    public static final Enum PAPAYA_WHIP = Enum.forString("papayaWhip");
    public static final Enum PEACH_PUFF = Enum.forString("peachPuff");
    public static final Enum PERU = Enum.forString("peru");
    public static final Enum PINK = Enum.forString("pink");
    public static final Enum PLUM = Enum.forString("plum");
    public static final Enum POWDER_BLUE = Enum.forString("powderBlue");
    public static final Enum PURPLE = Enum.forString("purple");
    public static final Enum RED = Enum.forString("red");
    public static final Enum ROSY_BROWN = Enum.forString("rosyBrown");
    public static final Enum ROYAL_BLUE = Enum.forString("royalBlue");
    public static final Enum SADDLE_BROWN = Enum.forString("saddleBrown");
    public static final Enum SALMON = Enum.forString("salmon");
    public static final Enum SANDY_BROWN = Enum.forString("sandyBrown");
    public static final Enum SEA_GREEN = Enum.forString("seaGreen");
    public static final Enum SEA_SHELL = Enum.forString("seaShell");
    public static final Enum SIENNA = Enum.forString("sienna");
    public static final Enum SILVER = Enum.forString("silver");
    public static final Enum SKY_BLUE = Enum.forString("skyBlue");
    public static final Enum SLATE_BLUE = Enum.forString("slateBlue");
    public static final Enum SLATE_GRAY = Enum.forString("slateGray");
    public static final Enum SNOW = Enum.forString("snow");
    public static final Enum SPRING_GREEN = Enum.forString("springGreen");
    public static final Enum STEEL_BLUE = Enum.forString("steelBlue");
    public static final Enum TAN = Enum.forString("tan");
    public static final Enum TEAL = Enum.forString("teal");
    public static final Enum THISTLE = Enum.forString("thistle");
    public static final Enum TOMATO = Enum.forString("tomato");
    public static final Enum TURQUOISE = Enum.forString("turquoise");
    public static final Enum VIOLET = Enum.forString("violet");
    public static final Enum WHEAT = Enum.forString("wheat");
    public static final Enum WHITE = Enum.forString("white");
    public static final Enum WHITE_SMOKE = Enum.forString("whiteSmoke");
    public static final Enum YELLOW = Enum.forString("yellow");
    public static final Enum YELLOW_GREEN = Enum.forString("yellowGreen");
    public static final int INT_ALICE_BLUE = 1;
    public static final int INT_ANTIQUE_WHITE = 2;
    public static final int INT_AQUA = 3;
    public static final int INT_AQUAMARINE = 4;
    public static final int INT_AZURE = 5;
    public static final int INT_BEIGE = 6;
    public static final int INT_BISQUE = 7;
    public static final int INT_BLACK = 8;
    public static final int INT_BLANCHED_ALMOND = 9;
    public static final int INT_BLUE = 10;
    public static final int INT_BLUE_VIOLET = 11;
    public static final int INT_BROWN = 12;
    public static final int INT_BURLY_WOOD = 13;
    public static final int INT_CADET_BLUE = 14;
    public static final int INT_CHARTREUSE = 15;
    public static final int INT_CHOCOLATE = 16;
    public static final int INT_CORAL = 17;
    public static final int INT_CORNFLOWER_BLUE = 18;
    public static final int INT_CORNSILK = 19;
    public static final int INT_CRIMSON = 20;
    public static final int INT_CYAN = 21;
    public static final int INT_DK_BLUE = 22;
    public static final int INT_DK_CYAN = 23;
    public static final int INT_DK_GOLDENROD = 24;
    public static final int INT_DK_GRAY = 25;
    public static final int INT_DK_GREEN = 26;
    public static final int INT_DK_KHAKI = 27;
    public static final int INT_DK_MAGENTA = 28;
    public static final int INT_DK_OLIVE_GREEN = 29;
    public static final int INT_DK_ORANGE = 30;
    public static final int INT_DK_ORCHID = 31;
    public static final int INT_DK_RED = 32;
    public static final int INT_DK_SALMON = 33;
    public static final int INT_DK_SEA_GREEN = 34;
    public static final int INT_DK_SLATE_BLUE = 35;
    public static final int INT_DK_SLATE_GRAY = 36;
    public static final int INT_DK_TURQUOISE = 37;
    public static final int INT_DK_VIOLET = 38;
    public static final int INT_DEEP_PINK = 39;
    public static final int INT_DEEP_SKY_BLUE = 40;
    public static final int INT_DIM_GRAY = 41;
    public static final int INT_DODGER_BLUE = 42;
    public static final int INT_FIREBRICK = 43;
    public static final int INT_FLORAL_WHITE = 44;
    public static final int INT_FOREST_GREEN = 45;
    public static final int INT_FUCHSIA = 46;
    public static final int INT_GAINSBORO = 47;
    public static final int INT_GHOST_WHITE = 48;
    public static final int INT_GOLD = 49;
    public static final int INT_GOLDENROD = 50;
    public static final int INT_GRAY = 51;
    public static final int INT_GREEN = 52;
    public static final int INT_GREEN_YELLOW = 53;
    public static final int INT_HONEYDEW = 54;
    public static final int INT_HOT_PINK = 55;
    public static final int INT_INDIAN_RED = 56;
    public static final int INT_INDIGO = 57;
    public static final int INT_IVORY = 58;
    public static final int INT_KHAKI = 59;
    public static final int INT_LAVENDER = 60;
    public static final int INT_LAVENDER_BLUSH = 61;
    public static final int INT_LAWN_GREEN = 62;
    public static final int INT_LEMON_CHIFFON = 63;
    public static final int INT_LT_BLUE = 64;
    public static final int INT_LT_CORAL = 65;
    public static final int INT_LT_CYAN = 66;
    public static final int INT_LT_GOLDENROD_YELLOW = 67;
    public static final int INT_LT_GRAY = 68;
    public static final int INT_LT_GREEN = 69;
    public static final int INT_LT_PINK = 70;
    public static final int INT_LT_SALMON = 71;
    public static final int INT_LT_SEA_GREEN = 72;
    public static final int INT_LT_SKY_BLUE = 73;
    public static final int INT_LT_SLATE_GRAY = 74;
    public static final int INT_LT_STEEL_BLUE = 75;
    public static final int INT_LT_YELLOW = 76;
    public static final int INT_LIME = 77;
    public static final int INT_LIME_GREEN = 78;
    public static final int INT_LINEN = 79;
    public static final int INT_MAGENTA = 80;
    public static final int INT_MAROON = 81;
    public static final int INT_MED_AQUAMARINE = 82;
    public static final int INT_MED_BLUE = 83;
    public static final int INT_MED_ORCHID = 84;
    public static final int INT_MED_PURPLE = 85;
    public static final int INT_MED_SEA_GREEN = 86;
    public static final int INT_MED_SLATE_BLUE = 87;
    public static final int INT_MED_SPRING_GREEN = 88;
    public static final int INT_MED_TURQUOISE = 89;
    public static final int INT_MED_VIOLET_RED = 90;
    public static final int INT_MIDNIGHT_BLUE = 91;
    public static final int INT_MINT_CREAM = 92;
    public static final int INT_MISTY_ROSE = 93;
    public static final int INT_MOCCASIN = 94;
    public static final int INT_NAVAJO_WHITE = 95;
    public static final int INT_NAVY = 96;
    public static final int INT_OLD_LACE = 97;
    public static final int INT_OLIVE = 98;
    public static final int INT_OLIVE_DRAB = 99;
    public static final int INT_ORANGE = 100;
    public static final int INT_ORANGE_RED = 101;
    public static final int INT_ORCHID = 102;
    public static final int INT_PALE_GOLDENROD = 103;
    public static final int INT_PALE_GREEN = 104;
    public static final int INT_PALE_TURQUOISE = 105;
    public static final int INT_PALE_VIOLET_RED = 106;
    public static final int INT_PAPAYA_WHIP = 107;
    public static final int INT_PEACH_PUFF = 108;
    public static final int INT_PERU = 109;
    public static final int INT_PINK = 110;
    public static final int INT_PLUM = 111;
    public static final int INT_POWDER_BLUE = 112;
    public static final int INT_PURPLE = 113;
    public static final int INT_RED = 114;
    public static final int INT_ROSY_BROWN = 115;
    public static final int INT_ROYAL_BLUE = 116;
    public static final int INT_SADDLE_BROWN = 117;
    public static final int INT_SALMON = 118;
    public static final int INT_SANDY_BROWN = 119;
    public static final int INT_SEA_GREEN = 120;
    public static final int INT_SEA_SHELL = 121;
    public static final int INT_SIENNA = 122;
    public static final int INT_SILVER = 123;
    public static final int INT_SKY_BLUE = 124;
    public static final int INT_SLATE_BLUE = 125;
    public static final int INT_SLATE_GRAY = 126;
    public static final int INT_SNOW = 127;
    public static final int INT_SPRING_GREEN = 128;
    public static final int INT_STEEL_BLUE = 129;
    public static final int INT_TAN = 130;
    public static final int INT_TEAL = 131;
    public static final int INT_THISTLE = 132;
    public static final int INT_TOMATO = 133;
    public static final int INT_TURQUOISE = 134;
    public static final int INT_VIOLET = 135;
    public static final int INT_WHEAT = 136;
    public static final int INT_WHITE = 137;
    public static final int INT_WHITE_SMOKE = 138;
    public static final int INT_YELLOW = 139;
    public static final int INT_YELLOW_GREEN = 140;

    public StringEnumAbstractBase enumValue();

    public void set(StringEnumAbstractBase var1);

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        public static STPresetColorVal newValue(Object object) {
            return (STPresetColorVal)type.newValue(object);
        }

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(STPresetColorVal.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static STPresetColorVal newInstance() {
            return (STPresetColorVal)Factory.getTypeLoader().newInstance(type, null);
        }

        public static STPresetColorVal newInstance(XmlOptions xmlOptions) {
            return (STPresetColorVal)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static STPresetColorVal parse(String string) throws XmlException {
            return (STPresetColorVal)Factory.getTypeLoader().parse(string, type, null);
        }

        public static STPresetColorVal parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (STPresetColorVal)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static STPresetColorVal parse(File file) throws XmlException, IOException {
            return (STPresetColorVal)Factory.getTypeLoader().parse(file, type, null);
        }

        public static STPresetColorVal parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STPresetColorVal)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static STPresetColorVal parse(URL uRL) throws XmlException, IOException {
            return (STPresetColorVal)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static STPresetColorVal parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STPresetColorVal)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static STPresetColorVal parse(InputStream inputStream) throws XmlException, IOException {
            return (STPresetColorVal)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static STPresetColorVal parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STPresetColorVal)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static STPresetColorVal parse(Reader reader) throws XmlException, IOException {
            return (STPresetColorVal)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static STPresetColorVal parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STPresetColorVal)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static STPresetColorVal parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (STPresetColorVal)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static STPresetColorVal parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (STPresetColorVal)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static STPresetColorVal parse(Node node) throws XmlException {
            return (STPresetColorVal)Factory.getTypeLoader().parse(node, type, null);
        }

        public static STPresetColorVal parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (STPresetColorVal)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static STPresetColorVal parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (STPresetColorVal)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static STPresetColorVal parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (STPresetColorVal)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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
        static final int INT_ALICE_BLUE = 1;
        static final int INT_ANTIQUE_WHITE = 2;
        static final int INT_AQUA = 3;
        static final int INT_AQUAMARINE = 4;
        static final int INT_AZURE = 5;
        static final int INT_BEIGE = 6;
        static final int INT_BISQUE = 7;
        static final int INT_BLACK = 8;
        static final int INT_BLANCHED_ALMOND = 9;
        static final int INT_BLUE = 10;
        static final int INT_BLUE_VIOLET = 11;
        static final int INT_BROWN = 12;
        static final int INT_BURLY_WOOD = 13;
        static final int INT_CADET_BLUE = 14;
        static final int INT_CHARTREUSE = 15;
        static final int INT_CHOCOLATE = 16;
        static final int INT_CORAL = 17;
        static final int INT_CORNFLOWER_BLUE = 18;
        static final int INT_CORNSILK = 19;
        static final int INT_CRIMSON = 20;
        static final int INT_CYAN = 21;
        static final int INT_DK_BLUE = 22;
        static final int INT_DK_CYAN = 23;
        static final int INT_DK_GOLDENROD = 24;
        static final int INT_DK_GRAY = 25;
        static final int INT_DK_GREEN = 26;
        static final int INT_DK_KHAKI = 27;
        static final int INT_DK_MAGENTA = 28;
        static final int INT_DK_OLIVE_GREEN = 29;
        static final int INT_DK_ORANGE = 30;
        static final int INT_DK_ORCHID = 31;
        static final int INT_DK_RED = 32;
        static final int INT_DK_SALMON = 33;
        static final int INT_DK_SEA_GREEN = 34;
        static final int INT_DK_SLATE_BLUE = 35;
        static final int INT_DK_SLATE_GRAY = 36;
        static final int INT_DK_TURQUOISE = 37;
        static final int INT_DK_VIOLET = 38;
        static final int INT_DEEP_PINK = 39;
        static final int INT_DEEP_SKY_BLUE = 40;
        static final int INT_DIM_GRAY = 41;
        static final int INT_DODGER_BLUE = 42;
        static final int INT_FIREBRICK = 43;
        static final int INT_FLORAL_WHITE = 44;
        static final int INT_FOREST_GREEN = 45;
        static final int INT_FUCHSIA = 46;
        static final int INT_GAINSBORO = 47;
        static final int INT_GHOST_WHITE = 48;
        static final int INT_GOLD = 49;
        static final int INT_GOLDENROD = 50;
        static final int INT_GRAY = 51;
        static final int INT_GREEN = 52;
        static final int INT_GREEN_YELLOW = 53;
        static final int INT_HONEYDEW = 54;
        static final int INT_HOT_PINK = 55;
        static final int INT_INDIAN_RED = 56;
        static final int INT_INDIGO = 57;
        static final int INT_IVORY = 58;
        static final int INT_KHAKI = 59;
        static final int INT_LAVENDER = 60;
        static final int INT_LAVENDER_BLUSH = 61;
        static final int INT_LAWN_GREEN = 62;
        static final int INT_LEMON_CHIFFON = 63;
        static final int INT_LT_BLUE = 64;
        static final int INT_LT_CORAL = 65;
        static final int INT_LT_CYAN = 66;
        static final int INT_LT_GOLDENROD_YELLOW = 67;
        static final int INT_LT_GRAY = 68;
        static final int INT_LT_GREEN = 69;
        static final int INT_LT_PINK = 70;
        static final int INT_LT_SALMON = 71;
        static final int INT_LT_SEA_GREEN = 72;
        static final int INT_LT_SKY_BLUE = 73;
        static final int INT_LT_SLATE_GRAY = 74;
        static final int INT_LT_STEEL_BLUE = 75;
        static final int INT_LT_YELLOW = 76;
        static final int INT_LIME = 77;
        static final int INT_LIME_GREEN = 78;
        static final int INT_LINEN = 79;
        static final int INT_MAGENTA = 80;
        static final int INT_MAROON = 81;
        static final int INT_MED_AQUAMARINE = 82;
        static final int INT_MED_BLUE = 83;
        static final int INT_MED_ORCHID = 84;
        static final int INT_MED_PURPLE = 85;
        static final int INT_MED_SEA_GREEN = 86;
        static final int INT_MED_SLATE_BLUE = 87;
        static final int INT_MED_SPRING_GREEN = 88;
        static final int INT_MED_TURQUOISE = 89;
        static final int INT_MED_VIOLET_RED = 90;
        static final int INT_MIDNIGHT_BLUE = 91;
        static final int INT_MINT_CREAM = 92;
        static final int INT_MISTY_ROSE = 93;
        static final int INT_MOCCASIN = 94;
        static final int INT_NAVAJO_WHITE = 95;
        static final int INT_NAVY = 96;
        static final int INT_OLD_LACE = 97;
        static final int INT_OLIVE = 98;
        static final int INT_OLIVE_DRAB = 99;
        static final int INT_ORANGE = 100;
        static final int INT_ORANGE_RED = 101;
        static final int INT_ORCHID = 102;
        static final int INT_PALE_GOLDENROD = 103;
        static final int INT_PALE_GREEN = 104;
        static final int INT_PALE_TURQUOISE = 105;
        static final int INT_PALE_VIOLET_RED = 106;
        static final int INT_PAPAYA_WHIP = 107;
        static final int INT_PEACH_PUFF = 108;
        static final int INT_PERU = 109;
        static final int INT_PINK = 110;
        static final int INT_PLUM = 111;
        static final int INT_POWDER_BLUE = 112;
        static final int INT_PURPLE = 113;
        static final int INT_RED = 114;
        static final int INT_ROSY_BROWN = 115;
        static final int INT_ROYAL_BLUE = 116;
        static final int INT_SADDLE_BROWN = 117;
        static final int INT_SALMON = 118;
        static final int INT_SANDY_BROWN = 119;
        static final int INT_SEA_GREEN = 120;
        static final int INT_SEA_SHELL = 121;
        static final int INT_SIENNA = 122;
        static final int INT_SILVER = 123;
        static final int INT_SKY_BLUE = 124;
        static final int INT_SLATE_BLUE = 125;
        static final int INT_SLATE_GRAY = 126;
        static final int INT_SNOW = 127;
        static final int INT_SPRING_GREEN = 128;
        static final int INT_STEEL_BLUE = 129;
        static final int INT_TAN = 130;
        static final int INT_TEAL = 131;
        static final int INT_THISTLE = 132;
        static final int INT_TOMATO = 133;
        static final int INT_TURQUOISE = 134;
        static final int INT_VIOLET = 135;
        static final int INT_WHEAT = 136;
        static final int INT_WHITE = 137;
        static final int INT_WHITE_SMOKE = 138;
        static final int INT_YELLOW = 139;
        static final int INT_YELLOW_GREEN = 140;
        public static final StringEnumAbstractBase.Table table = new StringEnumAbstractBase.Table(new Enum[]{new Enum("aliceBlue", 1), new Enum("antiqueWhite", 2), new Enum("aqua", 3), new Enum("aquamarine", 4), new Enum("azure", 5), new Enum("beige", 6), new Enum("bisque", 7), new Enum("black", 8), new Enum("blanchedAlmond", 9), new Enum("blue", 10), new Enum("blueViolet", 11), new Enum("brown", 12), new Enum("burlyWood", 13), new Enum("cadetBlue", 14), new Enum("chartreuse", 15), new Enum("chocolate", 16), new Enum("coral", 17), new Enum("cornflowerBlue", 18), new Enum("cornsilk", 19), new Enum("crimson", 20), new Enum("cyan", 21), new Enum("dkBlue", 22), new Enum("dkCyan", 23), new Enum("dkGoldenrod", 24), new Enum("dkGray", 25), new Enum("dkGreen", 26), new Enum("dkKhaki", 27), new Enum("dkMagenta", 28), new Enum("dkOliveGreen", 29), new Enum("dkOrange", 30), new Enum("dkOrchid", 31), new Enum("dkRed", 32), new Enum("dkSalmon", 33), new Enum("dkSeaGreen", 34), new Enum("dkSlateBlue", 35), new Enum("dkSlateGray", 36), new Enum("dkTurquoise", 37), new Enum("dkViolet", 38), new Enum("deepPink", 39), new Enum("deepSkyBlue", 40), new Enum("dimGray", 41), new Enum("dodgerBlue", 42), new Enum("firebrick", 43), new Enum("floralWhite", 44), new Enum("forestGreen", 45), new Enum("fuchsia", 46), new Enum("gainsboro", 47), new Enum("ghostWhite", 48), new Enum("gold", 49), new Enum("goldenrod", 50), new Enum("gray", 51), new Enum("green", 52), new Enum("greenYellow", 53), new Enum("honeydew", 54), new Enum("hotPink", 55), new Enum("indianRed", 56), new Enum("indigo", 57), new Enum("ivory", 58), new Enum("khaki", 59), new Enum("lavender", 60), new Enum("lavenderBlush", 61), new Enum("lawnGreen", 62), new Enum("lemonChiffon", 63), new Enum("ltBlue", 64), new Enum("ltCoral", 65), new Enum("ltCyan", 66), new Enum("ltGoldenrodYellow", 67), new Enum("ltGray", 68), new Enum("ltGreen", 69), new Enum("ltPink", 70), new Enum("ltSalmon", 71), new Enum("ltSeaGreen", 72), new Enum("ltSkyBlue", 73), new Enum("ltSlateGray", 74), new Enum("ltSteelBlue", 75), new Enum("ltYellow", 76), new Enum("lime", 77), new Enum("limeGreen", 78), new Enum("linen", 79), new Enum("magenta", 80), new Enum("maroon", 81), new Enum("medAquamarine", 82), new Enum("medBlue", 83), new Enum("medOrchid", 84), new Enum("medPurple", 85), new Enum("medSeaGreen", 86), new Enum("medSlateBlue", 87), new Enum("medSpringGreen", 88), new Enum("medTurquoise", 89), new Enum("medVioletRed", 90), new Enum("midnightBlue", 91), new Enum("mintCream", 92), new Enum("mistyRose", 93), new Enum("moccasin", 94), new Enum("navajoWhite", 95), new Enum("navy", 96), new Enum("oldLace", 97), new Enum("olive", 98), new Enum("oliveDrab", 99), new Enum("orange", 100), new Enum("orangeRed", 101), new Enum("orchid", 102), new Enum("paleGoldenrod", 103), new Enum("paleGreen", 104), new Enum("paleTurquoise", 105), new Enum("paleVioletRed", 106), new Enum("papayaWhip", 107), new Enum("peachPuff", 108), new Enum("peru", 109), new Enum("pink", 110), new Enum("plum", 111), new Enum("powderBlue", 112), new Enum("purple", 113), new Enum("red", 114), new Enum("rosyBrown", 115), new Enum("royalBlue", 116), new Enum("saddleBrown", 117), new Enum("salmon", 118), new Enum("sandyBrown", 119), new Enum("seaGreen", 120), new Enum("seaShell", 121), new Enum("sienna", 122), new Enum("silver", 123), new Enum("skyBlue", 124), new Enum("slateBlue", 125), new Enum("slateGray", 126), new Enum("snow", 127), new Enum("springGreen", 128), new Enum("steelBlue", 129), new Enum("tan", 130), new Enum("teal", 131), new Enum("thistle", 132), new Enum("tomato", 133), new Enum("turquoise", 134), new Enum("violet", 135), new Enum("wheat", 136), new Enum("white", 137), new Enum("whiteSmoke", 138), new Enum("yellow", 139), new Enum("yellowGreen", 140)});
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

