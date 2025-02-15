/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.wordprocessingml.x2006.main;

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
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.w3c.dom.Node;

public interface STBorder
extends XmlString {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(STBorder.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("stborderd7ectype");
    public static final Enum NIL = Enum.forString("nil");
    public static final Enum NONE = Enum.forString("none");
    public static final Enum SINGLE = Enum.forString("single");
    public static final Enum THICK = Enum.forString("thick");
    public static final Enum DOUBLE = Enum.forString("double");
    public static final Enum DOTTED = Enum.forString("dotted");
    public static final Enum DASHED = Enum.forString("dashed");
    public static final Enum DOT_DASH = Enum.forString("dotDash");
    public static final Enum DOT_DOT_DASH = Enum.forString("dotDotDash");
    public static final Enum TRIPLE = Enum.forString("triple");
    public static final Enum THIN_THICK_SMALL_GAP = Enum.forString("thinThickSmallGap");
    public static final Enum THICK_THIN_SMALL_GAP = Enum.forString("thickThinSmallGap");
    public static final Enum THIN_THICK_THIN_SMALL_GAP = Enum.forString("thinThickThinSmallGap");
    public static final Enum THIN_THICK_MEDIUM_GAP = Enum.forString("thinThickMediumGap");
    public static final Enum THICK_THIN_MEDIUM_GAP = Enum.forString("thickThinMediumGap");
    public static final Enum THIN_THICK_THIN_MEDIUM_GAP = Enum.forString("thinThickThinMediumGap");
    public static final Enum THIN_THICK_LARGE_GAP = Enum.forString("thinThickLargeGap");
    public static final Enum THICK_THIN_LARGE_GAP = Enum.forString("thickThinLargeGap");
    public static final Enum THIN_THICK_THIN_LARGE_GAP = Enum.forString("thinThickThinLargeGap");
    public static final Enum WAVE = Enum.forString("wave");
    public static final Enum DOUBLE_WAVE = Enum.forString("doubleWave");
    public static final Enum DASH_SMALL_GAP = Enum.forString("dashSmallGap");
    public static final Enum DASH_DOT_STROKED = Enum.forString("dashDotStroked");
    public static final Enum THREE_D_EMBOSS = Enum.forString("threeDEmboss");
    public static final Enum THREE_D_ENGRAVE = Enum.forString("threeDEngrave");
    public static final Enum OUTSET = Enum.forString("outset");
    public static final Enum INSET = Enum.forString("inset");
    public static final Enum APPLES = Enum.forString("apples");
    public static final Enum ARCHED_SCALLOPS = Enum.forString("archedScallops");
    public static final Enum BABY_PACIFIER = Enum.forString("babyPacifier");
    public static final Enum BABY_RATTLE = Enum.forString("babyRattle");
    public static final Enum BALLOONS_3_COLORS = Enum.forString("balloons3Colors");
    public static final Enum BALLOONS_HOT_AIR = Enum.forString("balloonsHotAir");
    public static final Enum BASIC_BLACK_DASHES = Enum.forString("basicBlackDashes");
    public static final Enum BASIC_BLACK_DOTS = Enum.forString("basicBlackDots");
    public static final Enum BASIC_BLACK_SQUARES = Enum.forString("basicBlackSquares");
    public static final Enum BASIC_THIN_LINES = Enum.forString("basicThinLines");
    public static final Enum BASIC_WHITE_DASHES = Enum.forString("basicWhiteDashes");
    public static final Enum BASIC_WHITE_DOTS = Enum.forString("basicWhiteDots");
    public static final Enum BASIC_WHITE_SQUARES = Enum.forString("basicWhiteSquares");
    public static final Enum BASIC_WIDE_INLINE = Enum.forString("basicWideInline");
    public static final Enum BASIC_WIDE_MIDLINE = Enum.forString("basicWideMidline");
    public static final Enum BASIC_WIDE_OUTLINE = Enum.forString("basicWideOutline");
    public static final Enum BATS = Enum.forString("bats");
    public static final Enum BIRDS = Enum.forString("birds");
    public static final Enum BIRDS_FLIGHT = Enum.forString("birdsFlight");
    public static final Enum CABINS = Enum.forString("cabins");
    public static final Enum CAKE_SLICE = Enum.forString("cakeSlice");
    public static final Enum CANDY_CORN = Enum.forString("candyCorn");
    public static final Enum CELTIC_KNOTWORK = Enum.forString("celticKnotwork");
    public static final Enum CERTIFICATE_BANNER = Enum.forString("certificateBanner");
    public static final Enum CHAIN_LINK = Enum.forString("chainLink");
    public static final Enum CHAMPAGNE_BOTTLE = Enum.forString("champagneBottle");
    public static final Enum CHECKED_BAR_BLACK = Enum.forString("checkedBarBlack");
    public static final Enum CHECKED_BAR_COLOR = Enum.forString("checkedBarColor");
    public static final Enum CHECKERED = Enum.forString("checkered");
    public static final Enum CHRISTMAS_TREE = Enum.forString("christmasTree");
    public static final Enum CIRCLES_LINES = Enum.forString("circlesLines");
    public static final Enum CIRCLES_RECTANGLES = Enum.forString("circlesRectangles");
    public static final Enum CLASSICAL_WAVE = Enum.forString("classicalWave");
    public static final Enum CLOCKS = Enum.forString("clocks");
    public static final Enum COMPASS = Enum.forString("compass");
    public static final Enum CONFETTI = Enum.forString("confetti");
    public static final Enum CONFETTI_GRAYS = Enum.forString("confettiGrays");
    public static final Enum CONFETTI_OUTLINE = Enum.forString("confettiOutline");
    public static final Enum CONFETTI_STREAMERS = Enum.forString("confettiStreamers");
    public static final Enum CONFETTI_WHITE = Enum.forString("confettiWhite");
    public static final Enum CORNER_TRIANGLES = Enum.forString("cornerTriangles");
    public static final Enum COUPON_CUTOUT_DASHES = Enum.forString("couponCutoutDashes");
    public static final Enum COUPON_CUTOUT_DOTS = Enum.forString("couponCutoutDots");
    public static final Enum CRAZY_MAZE = Enum.forString("crazyMaze");
    public static final Enum CREATURES_BUTTERFLY = Enum.forString("creaturesButterfly");
    public static final Enum CREATURES_FISH = Enum.forString("creaturesFish");
    public static final Enum CREATURES_INSECTS = Enum.forString("creaturesInsects");
    public static final Enum CREATURES_LADY_BUG = Enum.forString("creaturesLadyBug");
    public static final Enum CROSS_STITCH = Enum.forString("crossStitch");
    public static final Enum CUP = Enum.forString("cup");
    public static final Enum DECO_ARCH = Enum.forString("decoArch");
    public static final Enum DECO_ARCH_COLOR = Enum.forString("decoArchColor");
    public static final Enum DECO_BLOCKS = Enum.forString("decoBlocks");
    public static final Enum DIAMONDS_GRAY = Enum.forString("diamondsGray");
    public static final Enum DOUBLE_D = Enum.forString("doubleD");
    public static final Enum DOUBLE_DIAMONDS = Enum.forString("doubleDiamonds");
    public static final Enum EARTH_1 = Enum.forString("earth1");
    public static final Enum EARTH_2 = Enum.forString("earth2");
    public static final Enum ECLIPSING_SQUARES_1 = Enum.forString("eclipsingSquares1");
    public static final Enum ECLIPSING_SQUARES_2 = Enum.forString("eclipsingSquares2");
    public static final Enum EGGS_BLACK = Enum.forString("eggsBlack");
    public static final Enum FANS = Enum.forString("fans");
    public static final Enum FILM = Enum.forString("film");
    public static final Enum FIRECRACKERS = Enum.forString("firecrackers");
    public static final Enum FLOWERS_BLOCK_PRINT = Enum.forString("flowersBlockPrint");
    public static final Enum FLOWERS_DAISIES = Enum.forString("flowersDaisies");
    public static final Enum FLOWERS_MODERN_1 = Enum.forString("flowersModern1");
    public static final Enum FLOWERS_MODERN_2 = Enum.forString("flowersModern2");
    public static final Enum FLOWERS_PANSY = Enum.forString("flowersPansy");
    public static final Enum FLOWERS_RED_ROSE = Enum.forString("flowersRedRose");
    public static final Enum FLOWERS_ROSES = Enum.forString("flowersRoses");
    public static final Enum FLOWERS_TEACUP = Enum.forString("flowersTeacup");
    public static final Enum FLOWERS_TINY = Enum.forString("flowersTiny");
    public static final Enum GEMS = Enum.forString("gems");
    public static final Enum GINGERBREAD_MAN = Enum.forString("gingerbreadMan");
    public static final Enum GRADIENT = Enum.forString("gradient");
    public static final Enum HANDMADE_1 = Enum.forString("handmade1");
    public static final Enum HANDMADE_2 = Enum.forString("handmade2");
    public static final Enum HEART_BALLOON = Enum.forString("heartBalloon");
    public static final Enum HEART_GRAY = Enum.forString("heartGray");
    public static final Enum HEARTS = Enum.forString("hearts");
    public static final Enum HEEBIE_JEEBIES = Enum.forString("heebieJeebies");
    public static final Enum HOLLY = Enum.forString("holly");
    public static final Enum HOUSE_FUNKY = Enum.forString("houseFunky");
    public static final Enum HYPNOTIC = Enum.forString("hypnotic");
    public static final Enum ICE_CREAM_CONES = Enum.forString("iceCreamCones");
    public static final Enum LIGHT_BULB = Enum.forString("lightBulb");
    public static final Enum LIGHTNING_1 = Enum.forString("lightning1");
    public static final Enum LIGHTNING_2 = Enum.forString("lightning2");
    public static final Enum MAP_PINS = Enum.forString("mapPins");
    public static final Enum MAPLE_LEAF = Enum.forString("mapleLeaf");
    public static final Enum MAPLE_MUFFINS = Enum.forString("mapleMuffins");
    public static final Enum MARQUEE = Enum.forString("marquee");
    public static final Enum MARQUEE_TOOTHED = Enum.forString("marqueeToothed");
    public static final Enum MOONS = Enum.forString("moons");
    public static final Enum MOSAIC = Enum.forString("mosaic");
    public static final Enum MUSIC_NOTES = Enum.forString("musicNotes");
    public static final Enum NORTHWEST = Enum.forString("northwest");
    public static final Enum OVALS = Enum.forString("ovals");
    public static final Enum PACKAGES = Enum.forString("packages");
    public static final Enum PALMS_BLACK = Enum.forString("palmsBlack");
    public static final Enum PALMS_COLOR = Enum.forString("palmsColor");
    public static final Enum PAPER_CLIPS = Enum.forString("paperClips");
    public static final Enum PAPYRUS = Enum.forString("papyrus");
    public static final Enum PARTY_FAVOR = Enum.forString("partyFavor");
    public static final Enum PARTY_GLASS = Enum.forString("partyGlass");
    public static final Enum PENCILS = Enum.forString("pencils");
    public static final Enum PEOPLE = Enum.forString("people");
    public static final Enum PEOPLE_WAVING = Enum.forString("peopleWaving");
    public static final Enum PEOPLE_HATS = Enum.forString("peopleHats");
    public static final Enum POINSETTIAS = Enum.forString("poinsettias");
    public static final Enum POSTAGE_STAMP = Enum.forString("postageStamp");
    public static final Enum PUMPKIN_1 = Enum.forString("pumpkin1");
    public static final Enum PUSH_PIN_NOTE_2 = Enum.forString("pushPinNote2");
    public static final Enum PUSH_PIN_NOTE_1 = Enum.forString("pushPinNote1");
    public static final Enum PYRAMIDS = Enum.forString("pyramids");
    public static final Enum PYRAMIDS_ABOVE = Enum.forString("pyramidsAbove");
    public static final Enum QUADRANTS = Enum.forString("quadrants");
    public static final Enum RINGS = Enum.forString("rings");
    public static final Enum SAFARI = Enum.forString("safari");
    public static final Enum SAWTOOTH = Enum.forString("sawtooth");
    public static final Enum SAWTOOTH_GRAY = Enum.forString("sawtoothGray");
    public static final Enum SCARED_CAT = Enum.forString("scaredCat");
    public static final Enum SEATTLE = Enum.forString("seattle");
    public static final Enum SHADOWED_SQUARES = Enum.forString("shadowedSquares");
    public static final Enum SHARKS_TEETH = Enum.forString("sharksTeeth");
    public static final Enum SHOREBIRD_TRACKS = Enum.forString("shorebirdTracks");
    public static final Enum SKYROCKET = Enum.forString("skyrocket");
    public static final Enum SNOWFLAKE_FANCY = Enum.forString("snowflakeFancy");
    public static final Enum SNOWFLAKES = Enum.forString("snowflakes");
    public static final Enum SOMBRERO = Enum.forString("sombrero");
    public static final Enum SOUTHWEST = Enum.forString("southwest");
    public static final Enum STARS = Enum.forString("stars");
    public static final Enum STARS_TOP = Enum.forString("starsTop");
    public static final Enum STARS_3_D = Enum.forString("stars3d");
    public static final Enum STARS_BLACK = Enum.forString("starsBlack");
    public static final Enum STARS_SHADOWED = Enum.forString("starsShadowed");
    public static final Enum SUN = Enum.forString("sun");
    public static final Enum SWIRLIGIG = Enum.forString("swirligig");
    public static final Enum TORN_PAPER = Enum.forString("tornPaper");
    public static final Enum TORN_PAPER_BLACK = Enum.forString("tornPaperBlack");
    public static final Enum TREES = Enum.forString("trees");
    public static final Enum TRIANGLE_PARTY = Enum.forString("triangleParty");
    public static final Enum TRIANGLES = Enum.forString("triangles");
    public static final Enum TRIBAL_1 = Enum.forString("tribal1");
    public static final Enum TRIBAL_2 = Enum.forString("tribal2");
    public static final Enum TRIBAL_3 = Enum.forString("tribal3");
    public static final Enum TRIBAL_4 = Enum.forString("tribal4");
    public static final Enum TRIBAL_5 = Enum.forString("tribal5");
    public static final Enum TRIBAL_6 = Enum.forString("tribal6");
    public static final Enum TWISTED_LINES_1 = Enum.forString("twistedLines1");
    public static final Enum TWISTED_LINES_2 = Enum.forString("twistedLines2");
    public static final Enum VINE = Enum.forString("vine");
    public static final Enum WAVELINE = Enum.forString("waveline");
    public static final Enum WEAVING_ANGLES = Enum.forString("weavingAngles");
    public static final Enum WEAVING_BRAID = Enum.forString("weavingBraid");
    public static final Enum WEAVING_RIBBON = Enum.forString("weavingRibbon");
    public static final Enum WEAVING_STRIPS = Enum.forString("weavingStrips");
    public static final Enum WHITE_FLOWERS = Enum.forString("whiteFlowers");
    public static final Enum WOODWORK = Enum.forString("woodwork");
    public static final Enum X_ILLUSIONS = Enum.forString("xIllusions");
    public static final Enum ZANY_TRIANGLES = Enum.forString("zanyTriangles");
    public static final Enum ZIG_ZAG = Enum.forString("zigZag");
    public static final Enum ZIG_ZAG_STITCH = Enum.forString("zigZagStitch");
    public static final int INT_NIL = 1;
    public static final int INT_NONE = 2;
    public static final int INT_SINGLE = 3;
    public static final int INT_THICK = 4;
    public static final int INT_DOUBLE = 5;
    public static final int INT_DOTTED = 6;
    public static final int INT_DASHED = 7;
    public static final int INT_DOT_DASH = 8;
    public static final int INT_DOT_DOT_DASH = 9;
    public static final int INT_TRIPLE = 10;
    public static final int INT_THIN_THICK_SMALL_GAP = 11;
    public static final int INT_THICK_THIN_SMALL_GAP = 12;
    public static final int INT_THIN_THICK_THIN_SMALL_GAP = 13;
    public static final int INT_THIN_THICK_MEDIUM_GAP = 14;
    public static final int INT_THICK_THIN_MEDIUM_GAP = 15;
    public static final int INT_THIN_THICK_THIN_MEDIUM_GAP = 16;
    public static final int INT_THIN_THICK_LARGE_GAP = 17;
    public static final int INT_THICK_THIN_LARGE_GAP = 18;
    public static final int INT_THIN_THICK_THIN_LARGE_GAP = 19;
    public static final int INT_WAVE = 20;
    public static final int INT_DOUBLE_WAVE = 21;
    public static final int INT_DASH_SMALL_GAP = 22;
    public static final int INT_DASH_DOT_STROKED = 23;
    public static final int INT_THREE_D_EMBOSS = 24;
    public static final int INT_THREE_D_ENGRAVE = 25;
    public static final int INT_OUTSET = 26;
    public static final int INT_INSET = 27;
    public static final int INT_APPLES = 28;
    public static final int INT_ARCHED_SCALLOPS = 29;
    public static final int INT_BABY_PACIFIER = 30;
    public static final int INT_BABY_RATTLE = 31;
    public static final int INT_BALLOONS_3_COLORS = 32;
    public static final int INT_BALLOONS_HOT_AIR = 33;
    public static final int INT_BASIC_BLACK_DASHES = 34;
    public static final int INT_BASIC_BLACK_DOTS = 35;
    public static final int INT_BASIC_BLACK_SQUARES = 36;
    public static final int INT_BASIC_THIN_LINES = 37;
    public static final int INT_BASIC_WHITE_DASHES = 38;
    public static final int INT_BASIC_WHITE_DOTS = 39;
    public static final int INT_BASIC_WHITE_SQUARES = 40;
    public static final int INT_BASIC_WIDE_INLINE = 41;
    public static final int INT_BASIC_WIDE_MIDLINE = 42;
    public static final int INT_BASIC_WIDE_OUTLINE = 43;
    public static final int INT_BATS = 44;
    public static final int INT_BIRDS = 45;
    public static final int INT_BIRDS_FLIGHT = 46;
    public static final int INT_CABINS = 47;
    public static final int INT_CAKE_SLICE = 48;
    public static final int INT_CANDY_CORN = 49;
    public static final int INT_CELTIC_KNOTWORK = 50;
    public static final int INT_CERTIFICATE_BANNER = 51;
    public static final int INT_CHAIN_LINK = 52;
    public static final int INT_CHAMPAGNE_BOTTLE = 53;
    public static final int INT_CHECKED_BAR_BLACK = 54;
    public static final int INT_CHECKED_BAR_COLOR = 55;
    public static final int INT_CHECKERED = 56;
    public static final int INT_CHRISTMAS_TREE = 57;
    public static final int INT_CIRCLES_LINES = 58;
    public static final int INT_CIRCLES_RECTANGLES = 59;
    public static final int INT_CLASSICAL_WAVE = 60;
    public static final int INT_CLOCKS = 61;
    public static final int INT_COMPASS = 62;
    public static final int INT_CONFETTI = 63;
    public static final int INT_CONFETTI_GRAYS = 64;
    public static final int INT_CONFETTI_OUTLINE = 65;
    public static final int INT_CONFETTI_STREAMERS = 66;
    public static final int INT_CONFETTI_WHITE = 67;
    public static final int INT_CORNER_TRIANGLES = 68;
    public static final int INT_COUPON_CUTOUT_DASHES = 69;
    public static final int INT_COUPON_CUTOUT_DOTS = 70;
    public static final int INT_CRAZY_MAZE = 71;
    public static final int INT_CREATURES_BUTTERFLY = 72;
    public static final int INT_CREATURES_FISH = 73;
    public static final int INT_CREATURES_INSECTS = 74;
    public static final int INT_CREATURES_LADY_BUG = 75;
    public static final int INT_CROSS_STITCH = 76;
    public static final int INT_CUP = 77;
    public static final int INT_DECO_ARCH = 78;
    public static final int INT_DECO_ARCH_COLOR = 79;
    public static final int INT_DECO_BLOCKS = 80;
    public static final int INT_DIAMONDS_GRAY = 81;
    public static final int INT_DOUBLE_D = 82;
    public static final int INT_DOUBLE_DIAMONDS = 83;
    public static final int INT_EARTH_1 = 84;
    public static final int INT_EARTH_2 = 85;
    public static final int INT_ECLIPSING_SQUARES_1 = 86;
    public static final int INT_ECLIPSING_SQUARES_2 = 87;
    public static final int INT_EGGS_BLACK = 88;
    public static final int INT_FANS = 89;
    public static final int INT_FILM = 90;
    public static final int INT_FIRECRACKERS = 91;
    public static final int INT_FLOWERS_BLOCK_PRINT = 92;
    public static final int INT_FLOWERS_DAISIES = 93;
    public static final int INT_FLOWERS_MODERN_1 = 94;
    public static final int INT_FLOWERS_MODERN_2 = 95;
    public static final int INT_FLOWERS_PANSY = 96;
    public static final int INT_FLOWERS_RED_ROSE = 97;
    public static final int INT_FLOWERS_ROSES = 98;
    public static final int INT_FLOWERS_TEACUP = 99;
    public static final int INT_FLOWERS_TINY = 100;
    public static final int INT_GEMS = 101;
    public static final int INT_GINGERBREAD_MAN = 102;
    public static final int INT_GRADIENT = 103;
    public static final int INT_HANDMADE_1 = 104;
    public static final int INT_HANDMADE_2 = 105;
    public static final int INT_HEART_BALLOON = 106;
    public static final int INT_HEART_GRAY = 107;
    public static final int INT_HEARTS = 108;
    public static final int INT_HEEBIE_JEEBIES = 109;
    public static final int INT_HOLLY = 110;
    public static final int INT_HOUSE_FUNKY = 111;
    public static final int INT_HYPNOTIC = 112;
    public static final int INT_ICE_CREAM_CONES = 113;
    public static final int INT_LIGHT_BULB = 114;
    public static final int INT_LIGHTNING_1 = 115;
    public static final int INT_LIGHTNING_2 = 116;
    public static final int INT_MAP_PINS = 117;
    public static final int INT_MAPLE_LEAF = 118;
    public static final int INT_MAPLE_MUFFINS = 119;
    public static final int INT_MARQUEE = 120;
    public static final int INT_MARQUEE_TOOTHED = 121;
    public static final int INT_MOONS = 122;
    public static final int INT_MOSAIC = 123;
    public static final int INT_MUSIC_NOTES = 124;
    public static final int INT_NORTHWEST = 125;
    public static final int INT_OVALS = 126;
    public static final int INT_PACKAGES = 127;
    public static final int INT_PALMS_BLACK = 128;
    public static final int INT_PALMS_COLOR = 129;
    public static final int INT_PAPER_CLIPS = 130;
    public static final int INT_PAPYRUS = 131;
    public static final int INT_PARTY_FAVOR = 132;
    public static final int INT_PARTY_GLASS = 133;
    public static final int INT_PENCILS = 134;
    public static final int INT_PEOPLE = 135;
    public static final int INT_PEOPLE_WAVING = 136;
    public static final int INT_PEOPLE_HATS = 137;
    public static final int INT_POINSETTIAS = 138;
    public static final int INT_POSTAGE_STAMP = 139;
    public static final int INT_PUMPKIN_1 = 140;
    public static final int INT_PUSH_PIN_NOTE_2 = 141;
    public static final int INT_PUSH_PIN_NOTE_1 = 142;
    public static final int INT_PYRAMIDS = 143;
    public static final int INT_PYRAMIDS_ABOVE = 144;
    public static final int INT_QUADRANTS = 145;
    public static final int INT_RINGS = 146;
    public static final int INT_SAFARI = 147;
    public static final int INT_SAWTOOTH = 148;
    public static final int INT_SAWTOOTH_GRAY = 149;
    public static final int INT_SCARED_CAT = 150;
    public static final int INT_SEATTLE = 151;
    public static final int INT_SHADOWED_SQUARES = 152;
    public static final int INT_SHARKS_TEETH = 153;
    public static final int INT_SHOREBIRD_TRACKS = 154;
    public static final int INT_SKYROCKET = 155;
    public static final int INT_SNOWFLAKE_FANCY = 156;
    public static final int INT_SNOWFLAKES = 157;
    public static final int INT_SOMBRERO = 158;
    public static final int INT_SOUTHWEST = 159;
    public static final int INT_STARS = 160;
    public static final int INT_STARS_TOP = 161;
    public static final int INT_STARS_3_D = 162;
    public static final int INT_STARS_BLACK = 163;
    public static final int INT_STARS_SHADOWED = 164;
    public static final int INT_SUN = 165;
    public static final int INT_SWIRLIGIG = 166;
    public static final int INT_TORN_PAPER = 167;
    public static final int INT_TORN_PAPER_BLACK = 168;
    public static final int INT_TREES = 169;
    public static final int INT_TRIANGLE_PARTY = 170;
    public static final int INT_TRIANGLES = 171;
    public static final int INT_TRIBAL_1 = 172;
    public static final int INT_TRIBAL_2 = 173;
    public static final int INT_TRIBAL_3 = 174;
    public static final int INT_TRIBAL_4 = 175;
    public static final int INT_TRIBAL_5 = 176;
    public static final int INT_TRIBAL_6 = 177;
    public static final int INT_TWISTED_LINES_1 = 178;
    public static final int INT_TWISTED_LINES_2 = 179;
    public static final int INT_VINE = 180;
    public static final int INT_WAVELINE = 181;
    public static final int INT_WEAVING_ANGLES = 182;
    public static final int INT_WEAVING_BRAID = 183;
    public static final int INT_WEAVING_RIBBON = 184;
    public static final int INT_WEAVING_STRIPS = 185;
    public static final int INT_WHITE_FLOWERS = 186;
    public static final int INT_WOODWORK = 187;
    public static final int INT_X_ILLUSIONS = 188;
    public static final int INT_ZANY_TRIANGLES = 189;
    public static final int INT_ZIG_ZAG = 190;
    public static final int INT_ZIG_ZAG_STITCH = 191;

    public StringEnumAbstractBase enumValue();

    public void set(StringEnumAbstractBase var1);

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        public static STBorder newValue(Object object) {
            return (STBorder)type.newValue(object);
        }

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(STBorder.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static STBorder newInstance() {
            return (STBorder)Factory.getTypeLoader().newInstance(type, null);
        }

        public static STBorder newInstance(XmlOptions xmlOptions) {
            return (STBorder)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static STBorder parse(String string) throws XmlException {
            return (STBorder)Factory.getTypeLoader().parse(string, type, null);
        }

        public static STBorder parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (STBorder)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static STBorder parse(File file) throws XmlException, IOException {
            return (STBorder)Factory.getTypeLoader().parse(file, type, null);
        }

        public static STBorder parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STBorder)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static STBorder parse(URL uRL) throws XmlException, IOException {
            return (STBorder)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static STBorder parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STBorder)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static STBorder parse(InputStream inputStream) throws XmlException, IOException {
            return (STBorder)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static STBorder parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STBorder)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static STBorder parse(Reader reader) throws XmlException, IOException {
            return (STBorder)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static STBorder parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STBorder)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static STBorder parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (STBorder)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static STBorder parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (STBorder)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static STBorder parse(Node node) throws XmlException {
            return (STBorder)Factory.getTypeLoader().parse(node, type, null);
        }

        public static STBorder parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (STBorder)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static STBorder parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (STBorder)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static STBorder parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (STBorder)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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
        static final int INT_NIL = 1;
        static final int INT_NONE = 2;
        static final int INT_SINGLE = 3;
        static final int INT_THICK = 4;
        static final int INT_DOUBLE = 5;
        static final int INT_DOTTED = 6;
        static final int INT_DASHED = 7;
        static final int INT_DOT_DASH = 8;
        static final int INT_DOT_DOT_DASH = 9;
        static final int INT_TRIPLE = 10;
        static final int INT_THIN_THICK_SMALL_GAP = 11;
        static final int INT_THICK_THIN_SMALL_GAP = 12;
        static final int INT_THIN_THICK_THIN_SMALL_GAP = 13;
        static final int INT_THIN_THICK_MEDIUM_GAP = 14;
        static final int INT_THICK_THIN_MEDIUM_GAP = 15;
        static final int INT_THIN_THICK_THIN_MEDIUM_GAP = 16;
        static final int INT_THIN_THICK_LARGE_GAP = 17;
        static final int INT_THICK_THIN_LARGE_GAP = 18;
        static final int INT_THIN_THICK_THIN_LARGE_GAP = 19;
        static final int INT_WAVE = 20;
        static final int INT_DOUBLE_WAVE = 21;
        static final int INT_DASH_SMALL_GAP = 22;
        static final int INT_DASH_DOT_STROKED = 23;
        static final int INT_THREE_D_EMBOSS = 24;
        static final int INT_THREE_D_ENGRAVE = 25;
        static final int INT_OUTSET = 26;
        static final int INT_INSET = 27;
        static final int INT_APPLES = 28;
        static final int INT_ARCHED_SCALLOPS = 29;
        static final int INT_BABY_PACIFIER = 30;
        static final int INT_BABY_RATTLE = 31;
        static final int INT_BALLOONS_3_COLORS = 32;
        static final int INT_BALLOONS_HOT_AIR = 33;
        static final int INT_BASIC_BLACK_DASHES = 34;
        static final int INT_BASIC_BLACK_DOTS = 35;
        static final int INT_BASIC_BLACK_SQUARES = 36;
        static final int INT_BASIC_THIN_LINES = 37;
        static final int INT_BASIC_WHITE_DASHES = 38;
        static final int INT_BASIC_WHITE_DOTS = 39;
        static final int INT_BASIC_WHITE_SQUARES = 40;
        static final int INT_BASIC_WIDE_INLINE = 41;
        static final int INT_BASIC_WIDE_MIDLINE = 42;
        static final int INT_BASIC_WIDE_OUTLINE = 43;
        static final int INT_BATS = 44;
        static final int INT_BIRDS = 45;
        static final int INT_BIRDS_FLIGHT = 46;
        static final int INT_CABINS = 47;
        static final int INT_CAKE_SLICE = 48;
        static final int INT_CANDY_CORN = 49;
        static final int INT_CELTIC_KNOTWORK = 50;
        static final int INT_CERTIFICATE_BANNER = 51;
        static final int INT_CHAIN_LINK = 52;
        static final int INT_CHAMPAGNE_BOTTLE = 53;
        static final int INT_CHECKED_BAR_BLACK = 54;
        static final int INT_CHECKED_BAR_COLOR = 55;
        static final int INT_CHECKERED = 56;
        static final int INT_CHRISTMAS_TREE = 57;
        static final int INT_CIRCLES_LINES = 58;
        static final int INT_CIRCLES_RECTANGLES = 59;
        static final int INT_CLASSICAL_WAVE = 60;
        static final int INT_CLOCKS = 61;
        static final int INT_COMPASS = 62;
        static final int INT_CONFETTI = 63;
        static final int INT_CONFETTI_GRAYS = 64;
        static final int INT_CONFETTI_OUTLINE = 65;
        static final int INT_CONFETTI_STREAMERS = 66;
        static final int INT_CONFETTI_WHITE = 67;
        static final int INT_CORNER_TRIANGLES = 68;
        static final int INT_COUPON_CUTOUT_DASHES = 69;
        static final int INT_COUPON_CUTOUT_DOTS = 70;
        static final int INT_CRAZY_MAZE = 71;
        static final int INT_CREATURES_BUTTERFLY = 72;
        static final int INT_CREATURES_FISH = 73;
        static final int INT_CREATURES_INSECTS = 74;
        static final int INT_CREATURES_LADY_BUG = 75;
        static final int INT_CROSS_STITCH = 76;
        static final int INT_CUP = 77;
        static final int INT_DECO_ARCH = 78;
        static final int INT_DECO_ARCH_COLOR = 79;
        static final int INT_DECO_BLOCKS = 80;
        static final int INT_DIAMONDS_GRAY = 81;
        static final int INT_DOUBLE_D = 82;
        static final int INT_DOUBLE_DIAMONDS = 83;
        static final int INT_EARTH_1 = 84;
        static final int INT_EARTH_2 = 85;
        static final int INT_ECLIPSING_SQUARES_1 = 86;
        static final int INT_ECLIPSING_SQUARES_2 = 87;
        static final int INT_EGGS_BLACK = 88;
        static final int INT_FANS = 89;
        static final int INT_FILM = 90;
        static final int INT_FIRECRACKERS = 91;
        static final int INT_FLOWERS_BLOCK_PRINT = 92;
        static final int INT_FLOWERS_DAISIES = 93;
        static final int INT_FLOWERS_MODERN_1 = 94;
        static final int INT_FLOWERS_MODERN_2 = 95;
        static final int INT_FLOWERS_PANSY = 96;
        static final int INT_FLOWERS_RED_ROSE = 97;
        static final int INT_FLOWERS_ROSES = 98;
        static final int INT_FLOWERS_TEACUP = 99;
        static final int INT_FLOWERS_TINY = 100;
        static final int INT_GEMS = 101;
        static final int INT_GINGERBREAD_MAN = 102;
        static final int INT_GRADIENT = 103;
        static final int INT_HANDMADE_1 = 104;
        static final int INT_HANDMADE_2 = 105;
        static final int INT_HEART_BALLOON = 106;
        static final int INT_HEART_GRAY = 107;
        static final int INT_HEARTS = 108;
        static final int INT_HEEBIE_JEEBIES = 109;
        static final int INT_HOLLY = 110;
        static final int INT_HOUSE_FUNKY = 111;
        static final int INT_HYPNOTIC = 112;
        static final int INT_ICE_CREAM_CONES = 113;
        static final int INT_LIGHT_BULB = 114;
        static final int INT_LIGHTNING_1 = 115;
        static final int INT_LIGHTNING_2 = 116;
        static final int INT_MAP_PINS = 117;
        static final int INT_MAPLE_LEAF = 118;
        static final int INT_MAPLE_MUFFINS = 119;
        static final int INT_MARQUEE = 120;
        static final int INT_MARQUEE_TOOTHED = 121;
        static final int INT_MOONS = 122;
        static final int INT_MOSAIC = 123;
        static final int INT_MUSIC_NOTES = 124;
        static final int INT_NORTHWEST = 125;
        static final int INT_OVALS = 126;
        static final int INT_PACKAGES = 127;
        static final int INT_PALMS_BLACK = 128;
        static final int INT_PALMS_COLOR = 129;
        static final int INT_PAPER_CLIPS = 130;
        static final int INT_PAPYRUS = 131;
        static final int INT_PARTY_FAVOR = 132;
        static final int INT_PARTY_GLASS = 133;
        static final int INT_PENCILS = 134;
        static final int INT_PEOPLE = 135;
        static final int INT_PEOPLE_WAVING = 136;
        static final int INT_PEOPLE_HATS = 137;
        static final int INT_POINSETTIAS = 138;
        static final int INT_POSTAGE_STAMP = 139;
        static final int INT_PUMPKIN_1 = 140;
        static final int INT_PUSH_PIN_NOTE_2 = 141;
        static final int INT_PUSH_PIN_NOTE_1 = 142;
        static final int INT_PYRAMIDS = 143;
        static final int INT_PYRAMIDS_ABOVE = 144;
        static final int INT_QUADRANTS = 145;
        static final int INT_RINGS = 146;
        static final int INT_SAFARI = 147;
        static final int INT_SAWTOOTH = 148;
        static final int INT_SAWTOOTH_GRAY = 149;
        static final int INT_SCARED_CAT = 150;
        static final int INT_SEATTLE = 151;
        static final int INT_SHADOWED_SQUARES = 152;
        static final int INT_SHARKS_TEETH = 153;
        static final int INT_SHOREBIRD_TRACKS = 154;
        static final int INT_SKYROCKET = 155;
        static final int INT_SNOWFLAKE_FANCY = 156;
        static final int INT_SNOWFLAKES = 157;
        static final int INT_SOMBRERO = 158;
        static final int INT_SOUTHWEST = 159;
        static final int INT_STARS = 160;
        static final int INT_STARS_TOP = 161;
        static final int INT_STARS_3_D = 162;
        static final int INT_STARS_BLACK = 163;
        static final int INT_STARS_SHADOWED = 164;
        static final int INT_SUN = 165;
        static final int INT_SWIRLIGIG = 166;
        static final int INT_TORN_PAPER = 167;
        static final int INT_TORN_PAPER_BLACK = 168;
        static final int INT_TREES = 169;
        static final int INT_TRIANGLE_PARTY = 170;
        static final int INT_TRIANGLES = 171;
        static final int INT_TRIBAL_1 = 172;
        static final int INT_TRIBAL_2 = 173;
        static final int INT_TRIBAL_3 = 174;
        static final int INT_TRIBAL_4 = 175;
        static final int INT_TRIBAL_5 = 176;
        static final int INT_TRIBAL_6 = 177;
        static final int INT_TWISTED_LINES_1 = 178;
        static final int INT_TWISTED_LINES_2 = 179;
        static final int INT_VINE = 180;
        static final int INT_WAVELINE = 181;
        static final int INT_WEAVING_ANGLES = 182;
        static final int INT_WEAVING_BRAID = 183;
        static final int INT_WEAVING_RIBBON = 184;
        static final int INT_WEAVING_STRIPS = 185;
        static final int INT_WHITE_FLOWERS = 186;
        static final int INT_WOODWORK = 187;
        static final int INT_X_ILLUSIONS = 188;
        static final int INT_ZANY_TRIANGLES = 189;
        static final int INT_ZIG_ZAG = 190;
        static final int INT_ZIG_ZAG_STITCH = 191;
        public static final StringEnumAbstractBase.Table table = new StringEnumAbstractBase.Table(new Enum[]{new Enum("nil", 1), new Enum("none", 2), new Enum("single", 3), new Enum("thick", 4), new Enum("double", 5), new Enum("dotted", 6), new Enum("dashed", 7), new Enum("dotDash", 8), new Enum("dotDotDash", 9), new Enum("triple", 10), new Enum("thinThickSmallGap", 11), new Enum("thickThinSmallGap", 12), new Enum("thinThickThinSmallGap", 13), new Enum("thinThickMediumGap", 14), new Enum("thickThinMediumGap", 15), new Enum("thinThickThinMediumGap", 16), new Enum("thinThickLargeGap", 17), new Enum("thickThinLargeGap", 18), new Enum("thinThickThinLargeGap", 19), new Enum("wave", 20), new Enum("doubleWave", 21), new Enum("dashSmallGap", 22), new Enum("dashDotStroked", 23), new Enum("threeDEmboss", 24), new Enum("threeDEngrave", 25), new Enum("outset", 26), new Enum("inset", 27), new Enum("apples", 28), new Enum("archedScallops", 29), new Enum("babyPacifier", 30), new Enum("babyRattle", 31), new Enum("balloons3Colors", 32), new Enum("balloonsHotAir", 33), new Enum("basicBlackDashes", 34), new Enum("basicBlackDots", 35), new Enum("basicBlackSquares", 36), new Enum("basicThinLines", 37), new Enum("basicWhiteDashes", 38), new Enum("basicWhiteDots", 39), new Enum("basicWhiteSquares", 40), new Enum("basicWideInline", 41), new Enum("basicWideMidline", 42), new Enum("basicWideOutline", 43), new Enum("bats", 44), new Enum("birds", 45), new Enum("birdsFlight", 46), new Enum("cabins", 47), new Enum("cakeSlice", 48), new Enum("candyCorn", 49), new Enum("celticKnotwork", 50), new Enum("certificateBanner", 51), new Enum("chainLink", 52), new Enum("champagneBottle", 53), new Enum("checkedBarBlack", 54), new Enum("checkedBarColor", 55), new Enum("checkered", 56), new Enum("christmasTree", 57), new Enum("circlesLines", 58), new Enum("circlesRectangles", 59), new Enum("classicalWave", 60), new Enum("clocks", 61), new Enum("compass", 62), new Enum("confetti", 63), new Enum("confettiGrays", 64), new Enum("confettiOutline", 65), new Enum("confettiStreamers", 66), new Enum("confettiWhite", 67), new Enum("cornerTriangles", 68), new Enum("couponCutoutDashes", 69), new Enum("couponCutoutDots", 70), new Enum("crazyMaze", 71), new Enum("creaturesButterfly", 72), new Enum("creaturesFish", 73), new Enum("creaturesInsects", 74), new Enum("creaturesLadyBug", 75), new Enum("crossStitch", 76), new Enum("cup", 77), new Enum("decoArch", 78), new Enum("decoArchColor", 79), new Enum("decoBlocks", 80), new Enum("diamondsGray", 81), new Enum("doubleD", 82), new Enum("doubleDiamonds", 83), new Enum("earth1", 84), new Enum("earth2", 85), new Enum("eclipsingSquares1", 86), new Enum("eclipsingSquares2", 87), new Enum("eggsBlack", 88), new Enum("fans", 89), new Enum("film", 90), new Enum("firecrackers", 91), new Enum("flowersBlockPrint", 92), new Enum("flowersDaisies", 93), new Enum("flowersModern1", 94), new Enum("flowersModern2", 95), new Enum("flowersPansy", 96), new Enum("flowersRedRose", 97), new Enum("flowersRoses", 98), new Enum("flowersTeacup", 99), new Enum("flowersTiny", 100), new Enum("gems", 101), new Enum("gingerbreadMan", 102), new Enum("gradient", 103), new Enum("handmade1", 104), new Enum("handmade2", 105), new Enum("heartBalloon", 106), new Enum("heartGray", 107), new Enum("hearts", 108), new Enum("heebieJeebies", 109), new Enum("holly", 110), new Enum("houseFunky", 111), new Enum("hypnotic", 112), new Enum("iceCreamCones", 113), new Enum("lightBulb", 114), new Enum("lightning1", 115), new Enum("lightning2", 116), new Enum("mapPins", 117), new Enum("mapleLeaf", 118), new Enum("mapleMuffins", 119), new Enum("marquee", 120), new Enum("marqueeToothed", 121), new Enum("moons", 122), new Enum("mosaic", 123), new Enum("musicNotes", 124), new Enum("northwest", 125), new Enum("ovals", 126), new Enum("packages", 127), new Enum("palmsBlack", 128), new Enum("palmsColor", 129), new Enum("paperClips", 130), new Enum("papyrus", 131), new Enum("partyFavor", 132), new Enum("partyGlass", 133), new Enum("pencils", 134), new Enum("people", 135), new Enum("peopleWaving", 136), new Enum("peopleHats", 137), new Enum("poinsettias", 138), new Enum("postageStamp", 139), new Enum("pumpkin1", 140), new Enum("pushPinNote2", 141), new Enum("pushPinNote1", 142), new Enum("pyramids", 143), new Enum("pyramidsAbove", 144), new Enum("quadrants", 145), new Enum("rings", 146), new Enum("safari", 147), new Enum("sawtooth", 148), new Enum("sawtoothGray", 149), new Enum("scaredCat", 150), new Enum("seattle", 151), new Enum("shadowedSquares", 152), new Enum("sharksTeeth", 153), new Enum("shorebirdTracks", 154), new Enum("skyrocket", 155), new Enum("snowflakeFancy", 156), new Enum("snowflakes", 157), new Enum("sombrero", 158), new Enum("southwest", 159), new Enum("stars", 160), new Enum("starsTop", 161), new Enum("stars3d", 162), new Enum("starsBlack", 163), new Enum("starsShadowed", 164), new Enum("sun", 165), new Enum("swirligig", 166), new Enum("tornPaper", 167), new Enum("tornPaperBlack", 168), new Enum("trees", 169), new Enum("triangleParty", 170), new Enum("triangles", 171), new Enum("tribal1", 172), new Enum("tribal2", 173), new Enum("tribal3", 174), new Enum("tribal4", 175), new Enum("tribal5", 176), new Enum("tribal6", 177), new Enum("twistedLines1", 178), new Enum("twistedLines2", 179), new Enum("vine", 180), new Enum("waveline", 181), new Enum("weavingAngles", 182), new Enum("weavingBraid", 183), new Enum("weavingRibbon", 184), new Enum("weavingStrips", 185), new Enum("whiteFlowers", 186), new Enum("woodwork", 187), new Enum("xIllusions", 188), new Enum("zanyTriangles", 189), new Enum("zigZag", 190), new Enum("zigZagStitch", 191)});
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

