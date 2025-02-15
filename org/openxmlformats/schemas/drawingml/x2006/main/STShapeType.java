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

public interface STShapeType
extends XmlToken {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(STShapeType.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("stshapetype069ctype");
    public static final Enum LINE = Enum.forString("line");
    public static final Enum LINE_INV = Enum.forString("lineInv");
    public static final Enum TRIANGLE = Enum.forString("triangle");
    public static final Enum RT_TRIANGLE = Enum.forString("rtTriangle");
    public static final Enum RECT = Enum.forString("rect");
    public static final Enum DIAMOND = Enum.forString("diamond");
    public static final Enum PARALLELOGRAM = Enum.forString("parallelogram");
    public static final Enum TRAPEZOID = Enum.forString("trapezoid");
    public static final Enum NON_ISOSCELES_TRAPEZOID = Enum.forString("nonIsoscelesTrapezoid");
    public static final Enum PENTAGON = Enum.forString("pentagon");
    public static final Enum HEXAGON = Enum.forString("hexagon");
    public static final Enum HEPTAGON = Enum.forString("heptagon");
    public static final Enum OCTAGON = Enum.forString("octagon");
    public static final Enum DECAGON = Enum.forString("decagon");
    public static final Enum DODECAGON = Enum.forString("dodecagon");
    public static final Enum STAR_4 = Enum.forString("star4");
    public static final Enum STAR_5 = Enum.forString("star5");
    public static final Enum STAR_6 = Enum.forString("star6");
    public static final Enum STAR_7 = Enum.forString("star7");
    public static final Enum STAR_8 = Enum.forString("star8");
    public static final Enum STAR_10 = Enum.forString("star10");
    public static final Enum STAR_12 = Enum.forString("star12");
    public static final Enum STAR_16 = Enum.forString("star16");
    public static final Enum STAR_24 = Enum.forString("star24");
    public static final Enum STAR_32 = Enum.forString("star32");
    public static final Enum ROUND_RECT = Enum.forString("roundRect");
    public static final Enum ROUND_1_RECT = Enum.forString("round1Rect");
    public static final Enum ROUND_2_SAME_RECT = Enum.forString("round2SameRect");
    public static final Enum ROUND_2_DIAG_RECT = Enum.forString("round2DiagRect");
    public static final Enum SNIP_ROUND_RECT = Enum.forString("snipRoundRect");
    public static final Enum SNIP_1_RECT = Enum.forString("snip1Rect");
    public static final Enum SNIP_2_SAME_RECT = Enum.forString("snip2SameRect");
    public static final Enum SNIP_2_DIAG_RECT = Enum.forString("snip2DiagRect");
    public static final Enum PLAQUE = Enum.forString("plaque");
    public static final Enum ELLIPSE = Enum.forString("ellipse");
    public static final Enum TEARDROP = Enum.forString("teardrop");
    public static final Enum HOME_PLATE = Enum.forString("homePlate");
    public static final Enum CHEVRON = Enum.forString("chevron");
    public static final Enum PIE_WEDGE = Enum.forString("pieWedge");
    public static final Enum PIE = Enum.forString("pie");
    public static final Enum BLOCK_ARC = Enum.forString("blockArc");
    public static final Enum DONUT = Enum.forString("donut");
    public static final Enum NO_SMOKING = Enum.forString("noSmoking");
    public static final Enum RIGHT_ARROW = Enum.forString("rightArrow");
    public static final Enum LEFT_ARROW = Enum.forString("leftArrow");
    public static final Enum UP_ARROW = Enum.forString("upArrow");
    public static final Enum DOWN_ARROW = Enum.forString("downArrow");
    public static final Enum STRIPED_RIGHT_ARROW = Enum.forString("stripedRightArrow");
    public static final Enum NOTCHED_RIGHT_ARROW = Enum.forString("notchedRightArrow");
    public static final Enum BENT_UP_ARROW = Enum.forString("bentUpArrow");
    public static final Enum LEFT_RIGHT_ARROW = Enum.forString("leftRightArrow");
    public static final Enum UP_DOWN_ARROW = Enum.forString("upDownArrow");
    public static final Enum LEFT_UP_ARROW = Enum.forString("leftUpArrow");
    public static final Enum LEFT_RIGHT_UP_ARROW = Enum.forString("leftRightUpArrow");
    public static final Enum QUAD_ARROW = Enum.forString("quadArrow");
    public static final Enum LEFT_ARROW_CALLOUT = Enum.forString("leftArrowCallout");
    public static final Enum RIGHT_ARROW_CALLOUT = Enum.forString("rightArrowCallout");
    public static final Enum UP_ARROW_CALLOUT = Enum.forString("upArrowCallout");
    public static final Enum DOWN_ARROW_CALLOUT = Enum.forString("downArrowCallout");
    public static final Enum LEFT_RIGHT_ARROW_CALLOUT = Enum.forString("leftRightArrowCallout");
    public static final Enum UP_DOWN_ARROW_CALLOUT = Enum.forString("upDownArrowCallout");
    public static final Enum QUAD_ARROW_CALLOUT = Enum.forString("quadArrowCallout");
    public static final Enum BENT_ARROW = Enum.forString("bentArrow");
    public static final Enum UTURN_ARROW = Enum.forString("uturnArrow");
    public static final Enum CIRCULAR_ARROW = Enum.forString("circularArrow");
    public static final Enum LEFT_CIRCULAR_ARROW = Enum.forString("leftCircularArrow");
    public static final Enum LEFT_RIGHT_CIRCULAR_ARROW = Enum.forString("leftRightCircularArrow");
    public static final Enum CURVED_RIGHT_ARROW = Enum.forString("curvedRightArrow");
    public static final Enum CURVED_LEFT_ARROW = Enum.forString("curvedLeftArrow");
    public static final Enum CURVED_UP_ARROW = Enum.forString("curvedUpArrow");
    public static final Enum CURVED_DOWN_ARROW = Enum.forString("curvedDownArrow");
    public static final Enum SWOOSH_ARROW = Enum.forString("swooshArrow");
    public static final Enum CUBE = Enum.forString("cube");
    public static final Enum CAN = Enum.forString("can");
    public static final Enum LIGHTNING_BOLT = Enum.forString("lightningBolt");
    public static final Enum HEART = Enum.forString("heart");
    public static final Enum SUN = Enum.forString("sun");
    public static final Enum MOON = Enum.forString("moon");
    public static final Enum SMILEY_FACE = Enum.forString("smileyFace");
    public static final Enum IRREGULAR_SEAL_1 = Enum.forString("irregularSeal1");
    public static final Enum IRREGULAR_SEAL_2 = Enum.forString("irregularSeal2");
    public static final Enum FOLDED_CORNER = Enum.forString("foldedCorner");
    public static final Enum BEVEL = Enum.forString("bevel");
    public static final Enum FRAME = Enum.forString("frame");
    public static final Enum HALF_FRAME = Enum.forString("halfFrame");
    public static final Enum CORNER = Enum.forString("corner");
    public static final Enum DIAG_STRIPE = Enum.forString("diagStripe");
    public static final Enum CHORD = Enum.forString("chord");
    public static final Enum ARC = Enum.forString("arc");
    public static final Enum LEFT_BRACKET = Enum.forString("leftBracket");
    public static final Enum RIGHT_BRACKET = Enum.forString("rightBracket");
    public static final Enum LEFT_BRACE = Enum.forString("leftBrace");
    public static final Enum RIGHT_BRACE = Enum.forString("rightBrace");
    public static final Enum BRACKET_PAIR = Enum.forString("bracketPair");
    public static final Enum BRACE_PAIR = Enum.forString("bracePair");
    public static final Enum STRAIGHT_CONNECTOR_1 = Enum.forString("straightConnector1");
    public static final Enum BENT_CONNECTOR_2 = Enum.forString("bentConnector2");
    public static final Enum BENT_CONNECTOR_3 = Enum.forString("bentConnector3");
    public static final Enum BENT_CONNECTOR_4 = Enum.forString("bentConnector4");
    public static final Enum BENT_CONNECTOR_5 = Enum.forString("bentConnector5");
    public static final Enum CURVED_CONNECTOR_2 = Enum.forString("curvedConnector2");
    public static final Enum CURVED_CONNECTOR_3 = Enum.forString("curvedConnector3");
    public static final Enum CURVED_CONNECTOR_4 = Enum.forString("curvedConnector4");
    public static final Enum CURVED_CONNECTOR_5 = Enum.forString("curvedConnector5");
    public static final Enum CALLOUT_1 = Enum.forString("callout1");
    public static final Enum CALLOUT_2 = Enum.forString("callout2");
    public static final Enum CALLOUT_3 = Enum.forString("callout3");
    public static final Enum ACCENT_CALLOUT_1 = Enum.forString("accentCallout1");
    public static final Enum ACCENT_CALLOUT_2 = Enum.forString("accentCallout2");
    public static final Enum ACCENT_CALLOUT_3 = Enum.forString("accentCallout3");
    public static final Enum BORDER_CALLOUT_1 = Enum.forString("borderCallout1");
    public static final Enum BORDER_CALLOUT_2 = Enum.forString("borderCallout2");
    public static final Enum BORDER_CALLOUT_3 = Enum.forString("borderCallout3");
    public static final Enum ACCENT_BORDER_CALLOUT_1 = Enum.forString("accentBorderCallout1");
    public static final Enum ACCENT_BORDER_CALLOUT_2 = Enum.forString("accentBorderCallout2");
    public static final Enum ACCENT_BORDER_CALLOUT_3 = Enum.forString("accentBorderCallout3");
    public static final Enum WEDGE_RECT_CALLOUT = Enum.forString("wedgeRectCallout");
    public static final Enum WEDGE_ROUND_RECT_CALLOUT = Enum.forString("wedgeRoundRectCallout");
    public static final Enum WEDGE_ELLIPSE_CALLOUT = Enum.forString("wedgeEllipseCallout");
    public static final Enum CLOUD_CALLOUT = Enum.forString("cloudCallout");
    public static final Enum CLOUD = Enum.forString("cloud");
    public static final Enum RIBBON = Enum.forString("ribbon");
    public static final Enum RIBBON_2 = Enum.forString("ribbon2");
    public static final Enum ELLIPSE_RIBBON = Enum.forString("ellipseRibbon");
    public static final Enum ELLIPSE_RIBBON_2 = Enum.forString("ellipseRibbon2");
    public static final Enum LEFT_RIGHT_RIBBON = Enum.forString("leftRightRibbon");
    public static final Enum VERTICAL_SCROLL = Enum.forString("verticalScroll");
    public static final Enum HORIZONTAL_SCROLL = Enum.forString("horizontalScroll");
    public static final Enum WAVE = Enum.forString("wave");
    public static final Enum DOUBLE_WAVE = Enum.forString("doubleWave");
    public static final Enum PLUS = Enum.forString("plus");
    public static final Enum FLOW_CHART_PROCESS = Enum.forString("flowChartProcess");
    public static final Enum FLOW_CHART_DECISION = Enum.forString("flowChartDecision");
    public static final Enum FLOW_CHART_INPUT_OUTPUT = Enum.forString("flowChartInputOutput");
    public static final Enum FLOW_CHART_PREDEFINED_PROCESS = Enum.forString("flowChartPredefinedProcess");
    public static final Enum FLOW_CHART_INTERNAL_STORAGE = Enum.forString("flowChartInternalStorage");
    public static final Enum FLOW_CHART_DOCUMENT = Enum.forString("flowChartDocument");
    public static final Enum FLOW_CHART_MULTIDOCUMENT = Enum.forString("flowChartMultidocument");
    public static final Enum FLOW_CHART_TERMINATOR = Enum.forString("flowChartTerminator");
    public static final Enum FLOW_CHART_PREPARATION = Enum.forString("flowChartPreparation");
    public static final Enum FLOW_CHART_MANUAL_INPUT = Enum.forString("flowChartManualInput");
    public static final Enum FLOW_CHART_MANUAL_OPERATION = Enum.forString("flowChartManualOperation");
    public static final Enum FLOW_CHART_CONNECTOR = Enum.forString("flowChartConnector");
    public static final Enum FLOW_CHART_PUNCHED_CARD = Enum.forString("flowChartPunchedCard");
    public static final Enum FLOW_CHART_PUNCHED_TAPE = Enum.forString("flowChartPunchedTape");
    public static final Enum FLOW_CHART_SUMMING_JUNCTION = Enum.forString("flowChartSummingJunction");
    public static final Enum FLOW_CHART_OR = Enum.forString("flowChartOr");
    public static final Enum FLOW_CHART_COLLATE = Enum.forString("flowChartCollate");
    public static final Enum FLOW_CHART_SORT = Enum.forString("flowChartSort");
    public static final Enum FLOW_CHART_EXTRACT = Enum.forString("flowChartExtract");
    public static final Enum FLOW_CHART_MERGE = Enum.forString("flowChartMerge");
    public static final Enum FLOW_CHART_OFFLINE_STORAGE = Enum.forString("flowChartOfflineStorage");
    public static final Enum FLOW_CHART_ONLINE_STORAGE = Enum.forString("flowChartOnlineStorage");
    public static final Enum FLOW_CHART_MAGNETIC_TAPE = Enum.forString("flowChartMagneticTape");
    public static final Enum FLOW_CHART_MAGNETIC_DISK = Enum.forString("flowChartMagneticDisk");
    public static final Enum FLOW_CHART_MAGNETIC_DRUM = Enum.forString("flowChartMagneticDrum");
    public static final Enum FLOW_CHART_DISPLAY = Enum.forString("flowChartDisplay");
    public static final Enum FLOW_CHART_DELAY = Enum.forString("flowChartDelay");
    public static final Enum FLOW_CHART_ALTERNATE_PROCESS = Enum.forString("flowChartAlternateProcess");
    public static final Enum FLOW_CHART_OFFPAGE_CONNECTOR = Enum.forString("flowChartOffpageConnector");
    public static final Enum ACTION_BUTTON_BLANK = Enum.forString("actionButtonBlank");
    public static final Enum ACTION_BUTTON_HOME = Enum.forString("actionButtonHome");
    public static final Enum ACTION_BUTTON_HELP = Enum.forString("actionButtonHelp");
    public static final Enum ACTION_BUTTON_INFORMATION = Enum.forString("actionButtonInformation");
    public static final Enum ACTION_BUTTON_FORWARD_NEXT = Enum.forString("actionButtonForwardNext");
    public static final Enum ACTION_BUTTON_BACK_PREVIOUS = Enum.forString("actionButtonBackPrevious");
    public static final Enum ACTION_BUTTON_END = Enum.forString("actionButtonEnd");
    public static final Enum ACTION_BUTTON_BEGINNING = Enum.forString("actionButtonBeginning");
    public static final Enum ACTION_BUTTON_RETURN = Enum.forString("actionButtonReturn");
    public static final Enum ACTION_BUTTON_DOCUMENT = Enum.forString("actionButtonDocument");
    public static final Enum ACTION_BUTTON_SOUND = Enum.forString("actionButtonSound");
    public static final Enum ACTION_BUTTON_MOVIE = Enum.forString("actionButtonMovie");
    public static final Enum GEAR_6 = Enum.forString("gear6");
    public static final Enum GEAR_9 = Enum.forString("gear9");
    public static final Enum FUNNEL = Enum.forString("funnel");
    public static final Enum MATH_PLUS = Enum.forString("mathPlus");
    public static final Enum MATH_MINUS = Enum.forString("mathMinus");
    public static final Enum MATH_MULTIPLY = Enum.forString("mathMultiply");
    public static final Enum MATH_DIVIDE = Enum.forString("mathDivide");
    public static final Enum MATH_EQUAL = Enum.forString("mathEqual");
    public static final Enum MATH_NOT_EQUAL = Enum.forString("mathNotEqual");
    public static final Enum CORNER_TABS = Enum.forString("cornerTabs");
    public static final Enum SQUARE_TABS = Enum.forString("squareTabs");
    public static final Enum PLAQUE_TABS = Enum.forString("plaqueTabs");
    public static final Enum CHART_X = Enum.forString("chartX");
    public static final Enum CHART_STAR = Enum.forString("chartStar");
    public static final Enum CHART_PLUS = Enum.forString("chartPlus");
    public static final int INT_LINE = 1;
    public static final int INT_LINE_INV = 2;
    public static final int INT_TRIANGLE = 3;
    public static final int INT_RT_TRIANGLE = 4;
    public static final int INT_RECT = 5;
    public static final int INT_DIAMOND = 6;
    public static final int INT_PARALLELOGRAM = 7;
    public static final int INT_TRAPEZOID = 8;
    public static final int INT_NON_ISOSCELES_TRAPEZOID = 9;
    public static final int INT_PENTAGON = 10;
    public static final int INT_HEXAGON = 11;
    public static final int INT_HEPTAGON = 12;
    public static final int INT_OCTAGON = 13;
    public static final int INT_DECAGON = 14;
    public static final int INT_DODECAGON = 15;
    public static final int INT_STAR_4 = 16;
    public static final int INT_STAR_5 = 17;
    public static final int INT_STAR_6 = 18;
    public static final int INT_STAR_7 = 19;
    public static final int INT_STAR_8 = 20;
    public static final int INT_STAR_10 = 21;
    public static final int INT_STAR_12 = 22;
    public static final int INT_STAR_16 = 23;
    public static final int INT_STAR_24 = 24;
    public static final int INT_STAR_32 = 25;
    public static final int INT_ROUND_RECT = 26;
    public static final int INT_ROUND_1_RECT = 27;
    public static final int INT_ROUND_2_SAME_RECT = 28;
    public static final int INT_ROUND_2_DIAG_RECT = 29;
    public static final int INT_SNIP_ROUND_RECT = 30;
    public static final int INT_SNIP_1_RECT = 31;
    public static final int INT_SNIP_2_SAME_RECT = 32;
    public static final int INT_SNIP_2_DIAG_RECT = 33;
    public static final int INT_PLAQUE = 34;
    public static final int INT_ELLIPSE = 35;
    public static final int INT_TEARDROP = 36;
    public static final int INT_HOME_PLATE = 37;
    public static final int INT_CHEVRON = 38;
    public static final int INT_PIE_WEDGE = 39;
    public static final int INT_PIE = 40;
    public static final int INT_BLOCK_ARC = 41;
    public static final int INT_DONUT = 42;
    public static final int INT_NO_SMOKING = 43;
    public static final int INT_RIGHT_ARROW = 44;
    public static final int INT_LEFT_ARROW = 45;
    public static final int INT_UP_ARROW = 46;
    public static final int INT_DOWN_ARROW = 47;
    public static final int INT_STRIPED_RIGHT_ARROW = 48;
    public static final int INT_NOTCHED_RIGHT_ARROW = 49;
    public static final int INT_BENT_UP_ARROW = 50;
    public static final int INT_LEFT_RIGHT_ARROW = 51;
    public static final int INT_UP_DOWN_ARROW = 52;
    public static final int INT_LEFT_UP_ARROW = 53;
    public static final int INT_LEFT_RIGHT_UP_ARROW = 54;
    public static final int INT_QUAD_ARROW = 55;
    public static final int INT_LEFT_ARROW_CALLOUT = 56;
    public static final int INT_RIGHT_ARROW_CALLOUT = 57;
    public static final int INT_UP_ARROW_CALLOUT = 58;
    public static final int INT_DOWN_ARROW_CALLOUT = 59;
    public static final int INT_LEFT_RIGHT_ARROW_CALLOUT = 60;
    public static final int INT_UP_DOWN_ARROW_CALLOUT = 61;
    public static final int INT_QUAD_ARROW_CALLOUT = 62;
    public static final int INT_BENT_ARROW = 63;
    public static final int INT_UTURN_ARROW = 64;
    public static final int INT_CIRCULAR_ARROW = 65;
    public static final int INT_LEFT_CIRCULAR_ARROW = 66;
    public static final int INT_LEFT_RIGHT_CIRCULAR_ARROW = 67;
    public static final int INT_CURVED_RIGHT_ARROW = 68;
    public static final int INT_CURVED_LEFT_ARROW = 69;
    public static final int INT_CURVED_UP_ARROW = 70;
    public static final int INT_CURVED_DOWN_ARROW = 71;
    public static final int INT_SWOOSH_ARROW = 72;
    public static final int INT_CUBE = 73;
    public static final int INT_CAN = 74;
    public static final int INT_LIGHTNING_BOLT = 75;
    public static final int INT_HEART = 76;
    public static final int INT_SUN = 77;
    public static final int INT_MOON = 78;
    public static final int INT_SMILEY_FACE = 79;
    public static final int INT_IRREGULAR_SEAL_1 = 80;
    public static final int INT_IRREGULAR_SEAL_2 = 81;
    public static final int INT_FOLDED_CORNER = 82;
    public static final int INT_BEVEL = 83;
    public static final int INT_FRAME = 84;
    public static final int INT_HALF_FRAME = 85;
    public static final int INT_CORNER = 86;
    public static final int INT_DIAG_STRIPE = 87;
    public static final int INT_CHORD = 88;
    public static final int INT_ARC = 89;
    public static final int INT_LEFT_BRACKET = 90;
    public static final int INT_RIGHT_BRACKET = 91;
    public static final int INT_LEFT_BRACE = 92;
    public static final int INT_RIGHT_BRACE = 93;
    public static final int INT_BRACKET_PAIR = 94;
    public static final int INT_BRACE_PAIR = 95;
    public static final int INT_STRAIGHT_CONNECTOR_1 = 96;
    public static final int INT_BENT_CONNECTOR_2 = 97;
    public static final int INT_BENT_CONNECTOR_3 = 98;
    public static final int INT_BENT_CONNECTOR_4 = 99;
    public static final int INT_BENT_CONNECTOR_5 = 100;
    public static final int INT_CURVED_CONNECTOR_2 = 101;
    public static final int INT_CURVED_CONNECTOR_3 = 102;
    public static final int INT_CURVED_CONNECTOR_4 = 103;
    public static final int INT_CURVED_CONNECTOR_5 = 104;
    public static final int INT_CALLOUT_1 = 105;
    public static final int INT_CALLOUT_2 = 106;
    public static final int INT_CALLOUT_3 = 107;
    public static final int INT_ACCENT_CALLOUT_1 = 108;
    public static final int INT_ACCENT_CALLOUT_2 = 109;
    public static final int INT_ACCENT_CALLOUT_3 = 110;
    public static final int INT_BORDER_CALLOUT_1 = 111;
    public static final int INT_BORDER_CALLOUT_2 = 112;
    public static final int INT_BORDER_CALLOUT_3 = 113;
    public static final int INT_ACCENT_BORDER_CALLOUT_1 = 114;
    public static final int INT_ACCENT_BORDER_CALLOUT_2 = 115;
    public static final int INT_ACCENT_BORDER_CALLOUT_3 = 116;
    public static final int INT_WEDGE_RECT_CALLOUT = 117;
    public static final int INT_WEDGE_ROUND_RECT_CALLOUT = 118;
    public static final int INT_WEDGE_ELLIPSE_CALLOUT = 119;
    public static final int INT_CLOUD_CALLOUT = 120;
    public static final int INT_CLOUD = 121;
    public static final int INT_RIBBON = 122;
    public static final int INT_RIBBON_2 = 123;
    public static final int INT_ELLIPSE_RIBBON = 124;
    public static final int INT_ELLIPSE_RIBBON_2 = 125;
    public static final int INT_LEFT_RIGHT_RIBBON = 126;
    public static final int INT_VERTICAL_SCROLL = 127;
    public static final int INT_HORIZONTAL_SCROLL = 128;
    public static final int INT_WAVE = 129;
    public static final int INT_DOUBLE_WAVE = 130;
    public static final int INT_PLUS = 131;
    public static final int INT_FLOW_CHART_PROCESS = 132;
    public static final int INT_FLOW_CHART_DECISION = 133;
    public static final int INT_FLOW_CHART_INPUT_OUTPUT = 134;
    public static final int INT_FLOW_CHART_PREDEFINED_PROCESS = 135;
    public static final int INT_FLOW_CHART_INTERNAL_STORAGE = 136;
    public static final int INT_FLOW_CHART_DOCUMENT = 137;
    public static final int INT_FLOW_CHART_MULTIDOCUMENT = 138;
    public static final int INT_FLOW_CHART_TERMINATOR = 139;
    public static final int INT_FLOW_CHART_PREPARATION = 140;
    public static final int INT_FLOW_CHART_MANUAL_INPUT = 141;
    public static final int INT_FLOW_CHART_MANUAL_OPERATION = 142;
    public static final int INT_FLOW_CHART_CONNECTOR = 143;
    public static final int INT_FLOW_CHART_PUNCHED_CARD = 144;
    public static final int INT_FLOW_CHART_PUNCHED_TAPE = 145;
    public static final int INT_FLOW_CHART_SUMMING_JUNCTION = 146;
    public static final int INT_FLOW_CHART_OR = 147;
    public static final int INT_FLOW_CHART_COLLATE = 148;
    public static final int INT_FLOW_CHART_SORT = 149;
    public static final int INT_FLOW_CHART_EXTRACT = 150;
    public static final int INT_FLOW_CHART_MERGE = 151;
    public static final int INT_FLOW_CHART_OFFLINE_STORAGE = 152;
    public static final int INT_FLOW_CHART_ONLINE_STORAGE = 153;
    public static final int INT_FLOW_CHART_MAGNETIC_TAPE = 154;
    public static final int INT_FLOW_CHART_MAGNETIC_DISK = 155;
    public static final int INT_FLOW_CHART_MAGNETIC_DRUM = 156;
    public static final int INT_FLOW_CHART_DISPLAY = 157;
    public static final int INT_FLOW_CHART_DELAY = 158;
    public static final int INT_FLOW_CHART_ALTERNATE_PROCESS = 159;
    public static final int INT_FLOW_CHART_OFFPAGE_CONNECTOR = 160;
    public static final int INT_ACTION_BUTTON_BLANK = 161;
    public static final int INT_ACTION_BUTTON_HOME = 162;
    public static final int INT_ACTION_BUTTON_HELP = 163;
    public static final int INT_ACTION_BUTTON_INFORMATION = 164;
    public static final int INT_ACTION_BUTTON_FORWARD_NEXT = 165;
    public static final int INT_ACTION_BUTTON_BACK_PREVIOUS = 166;
    public static final int INT_ACTION_BUTTON_END = 167;
    public static final int INT_ACTION_BUTTON_BEGINNING = 168;
    public static final int INT_ACTION_BUTTON_RETURN = 169;
    public static final int INT_ACTION_BUTTON_DOCUMENT = 170;
    public static final int INT_ACTION_BUTTON_SOUND = 171;
    public static final int INT_ACTION_BUTTON_MOVIE = 172;
    public static final int INT_GEAR_6 = 173;
    public static final int INT_GEAR_9 = 174;
    public static final int INT_FUNNEL = 175;
    public static final int INT_MATH_PLUS = 176;
    public static final int INT_MATH_MINUS = 177;
    public static final int INT_MATH_MULTIPLY = 178;
    public static final int INT_MATH_DIVIDE = 179;
    public static final int INT_MATH_EQUAL = 180;
    public static final int INT_MATH_NOT_EQUAL = 181;
    public static final int INT_CORNER_TABS = 182;
    public static final int INT_SQUARE_TABS = 183;
    public static final int INT_PLAQUE_TABS = 184;
    public static final int INT_CHART_X = 185;
    public static final int INT_CHART_STAR = 186;
    public static final int INT_CHART_PLUS = 187;

    public StringEnumAbstractBase enumValue();

    public void set(StringEnumAbstractBase var1);

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        public static STShapeType newValue(Object object) {
            return (STShapeType)type.newValue(object);
        }

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(STShapeType.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static STShapeType newInstance() {
            return (STShapeType)Factory.getTypeLoader().newInstance(type, null);
        }

        public static STShapeType newInstance(XmlOptions xmlOptions) {
            return (STShapeType)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static STShapeType parse(String string) throws XmlException {
            return (STShapeType)Factory.getTypeLoader().parse(string, type, null);
        }

        public static STShapeType parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (STShapeType)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static STShapeType parse(File file) throws XmlException, IOException {
            return (STShapeType)Factory.getTypeLoader().parse(file, type, null);
        }

        public static STShapeType parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STShapeType)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static STShapeType parse(URL uRL) throws XmlException, IOException {
            return (STShapeType)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static STShapeType parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STShapeType)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static STShapeType parse(InputStream inputStream) throws XmlException, IOException {
            return (STShapeType)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static STShapeType parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STShapeType)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static STShapeType parse(Reader reader) throws XmlException, IOException {
            return (STShapeType)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static STShapeType parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STShapeType)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static STShapeType parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (STShapeType)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static STShapeType parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (STShapeType)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static STShapeType parse(Node node) throws XmlException {
            return (STShapeType)Factory.getTypeLoader().parse(node, type, null);
        }

        public static STShapeType parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (STShapeType)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static STShapeType parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (STShapeType)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static STShapeType parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (STShapeType)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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
        static final int INT_LINE = 1;
        static final int INT_LINE_INV = 2;
        static final int INT_TRIANGLE = 3;
        static final int INT_RT_TRIANGLE = 4;
        static final int INT_RECT = 5;
        static final int INT_DIAMOND = 6;
        static final int INT_PARALLELOGRAM = 7;
        static final int INT_TRAPEZOID = 8;
        static final int INT_NON_ISOSCELES_TRAPEZOID = 9;
        static final int INT_PENTAGON = 10;
        static final int INT_HEXAGON = 11;
        static final int INT_HEPTAGON = 12;
        static final int INT_OCTAGON = 13;
        static final int INT_DECAGON = 14;
        static final int INT_DODECAGON = 15;
        static final int INT_STAR_4 = 16;
        static final int INT_STAR_5 = 17;
        static final int INT_STAR_6 = 18;
        static final int INT_STAR_7 = 19;
        static final int INT_STAR_8 = 20;
        static final int INT_STAR_10 = 21;
        static final int INT_STAR_12 = 22;
        static final int INT_STAR_16 = 23;
        static final int INT_STAR_24 = 24;
        static final int INT_STAR_32 = 25;
        static final int INT_ROUND_RECT = 26;
        static final int INT_ROUND_1_RECT = 27;
        static final int INT_ROUND_2_SAME_RECT = 28;
        static final int INT_ROUND_2_DIAG_RECT = 29;
        static final int INT_SNIP_ROUND_RECT = 30;
        static final int INT_SNIP_1_RECT = 31;
        static final int INT_SNIP_2_SAME_RECT = 32;
        static final int INT_SNIP_2_DIAG_RECT = 33;
        static final int INT_PLAQUE = 34;
        static final int INT_ELLIPSE = 35;
        static final int INT_TEARDROP = 36;
        static final int INT_HOME_PLATE = 37;
        static final int INT_CHEVRON = 38;
        static final int INT_PIE_WEDGE = 39;
        static final int INT_PIE = 40;
        static final int INT_BLOCK_ARC = 41;
        static final int INT_DONUT = 42;
        static final int INT_NO_SMOKING = 43;
        static final int INT_RIGHT_ARROW = 44;
        static final int INT_LEFT_ARROW = 45;
        static final int INT_UP_ARROW = 46;
        static final int INT_DOWN_ARROW = 47;
        static final int INT_STRIPED_RIGHT_ARROW = 48;
        static final int INT_NOTCHED_RIGHT_ARROW = 49;
        static final int INT_BENT_UP_ARROW = 50;
        static final int INT_LEFT_RIGHT_ARROW = 51;
        static final int INT_UP_DOWN_ARROW = 52;
        static final int INT_LEFT_UP_ARROW = 53;
        static final int INT_LEFT_RIGHT_UP_ARROW = 54;
        static final int INT_QUAD_ARROW = 55;
        static final int INT_LEFT_ARROW_CALLOUT = 56;
        static final int INT_RIGHT_ARROW_CALLOUT = 57;
        static final int INT_UP_ARROW_CALLOUT = 58;
        static final int INT_DOWN_ARROW_CALLOUT = 59;
        static final int INT_LEFT_RIGHT_ARROW_CALLOUT = 60;
        static final int INT_UP_DOWN_ARROW_CALLOUT = 61;
        static final int INT_QUAD_ARROW_CALLOUT = 62;
        static final int INT_BENT_ARROW = 63;
        static final int INT_UTURN_ARROW = 64;
        static final int INT_CIRCULAR_ARROW = 65;
        static final int INT_LEFT_CIRCULAR_ARROW = 66;
        static final int INT_LEFT_RIGHT_CIRCULAR_ARROW = 67;
        static final int INT_CURVED_RIGHT_ARROW = 68;
        static final int INT_CURVED_LEFT_ARROW = 69;
        static final int INT_CURVED_UP_ARROW = 70;
        static final int INT_CURVED_DOWN_ARROW = 71;
        static final int INT_SWOOSH_ARROW = 72;
        static final int INT_CUBE = 73;
        static final int INT_CAN = 74;
        static final int INT_LIGHTNING_BOLT = 75;
        static final int INT_HEART = 76;
        static final int INT_SUN = 77;
        static final int INT_MOON = 78;
        static final int INT_SMILEY_FACE = 79;
        static final int INT_IRREGULAR_SEAL_1 = 80;
        static final int INT_IRREGULAR_SEAL_2 = 81;
        static final int INT_FOLDED_CORNER = 82;
        static final int INT_BEVEL = 83;
        static final int INT_FRAME = 84;
        static final int INT_HALF_FRAME = 85;
        static final int INT_CORNER = 86;
        static final int INT_DIAG_STRIPE = 87;
        static final int INT_CHORD = 88;
        static final int INT_ARC = 89;
        static final int INT_LEFT_BRACKET = 90;
        static final int INT_RIGHT_BRACKET = 91;
        static final int INT_LEFT_BRACE = 92;
        static final int INT_RIGHT_BRACE = 93;
        static final int INT_BRACKET_PAIR = 94;
        static final int INT_BRACE_PAIR = 95;
        static final int INT_STRAIGHT_CONNECTOR_1 = 96;
        static final int INT_BENT_CONNECTOR_2 = 97;
        static final int INT_BENT_CONNECTOR_3 = 98;
        static final int INT_BENT_CONNECTOR_4 = 99;
        static final int INT_BENT_CONNECTOR_5 = 100;
        static final int INT_CURVED_CONNECTOR_2 = 101;
        static final int INT_CURVED_CONNECTOR_3 = 102;
        static final int INT_CURVED_CONNECTOR_4 = 103;
        static final int INT_CURVED_CONNECTOR_5 = 104;
        static final int INT_CALLOUT_1 = 105;
        static final int INT_CALLOUT_2 = 106;
        static final int INT_CALLOUT_3 = 107;
        static final int INT_ACCENT_CALLOUT_1 = 108;
        static final int INT_ACCENT_CALLOUT_2 = 109;
        static final int INT_ACCENT_CALLOUT_3 = 110;
        static final int INT_BORDER_CALLOUT_1 = 111;
        static final int INT_BORDER_CALLOUT_2 = 112;
        static final int INT_BORDER_CALLOUT_3 = 113;
        static final int INT_ACCENT_BORDER_CALLOUT_1 = 114;
        static final int INT_ACCENT_BORDER_CALLOUT_2 = 115;
        static final int INT_ACCENT_BORDER_CALLOUT_3 = 116;
        static final int INT_WEDGE_RECT_CALLOUT = 117;
        static final int INT_WEDGE_ROUND_RECT_CALLOUT = 118;
        static final int INT_WEDGE_ELLIPSE_CALLOUT = 119;
        static final int INT_CLOUD_CALLOUT = 120;
        static final int INT_CLOUD = 121;
        static final int INT_RIBBON = 122;
        static final int INT_RIBBON_2 = 123;
        static final int INT_ELLIPSE_RIBBON = 124;
        static final int INT_ELLIPSE_RIBBON_2 = 125;
        static final int INT_LEFT_RIGHT_RIBBON = 126;
        static final int INT_VERTICAL_SCROLL = 127;
        static final int INT_HORIZONTAL_SCROLL = 128;
        static final int INT_WAVE = 129;
        static final int INT_DOUBLE_WAVE = 130;
        static final int INT_PLUS = 131;
        static final int INT_FLOW_CHART_PROCESS = 132;
        static final int INT_FLOW_CHART_DECISION = 133;
        static final int INT_FLOW_CHART_INPUT_OUTPUT = 134;
        static final int INT_FLOW_CHART_PREDEFINED_PROCESS = 135;
        static final int INT_FLOW_CHART_INTERNAL_STORAGE = 136;
        static final int INT_FLOW_CHART_DOCUMENT = 137;
        static final int INT_FLOW_CHART_MULTIDOCUMENT = 138;
        static final int INT_FLOW_CHART_TERMINATOR = 139;
        static final int INT_FLOW_CHART_PREPARATION = 140;
        static final int INT_FLOW_CHART_MANUAL_INPUT = 141;
        static final int INT_FLOW_CHART_MANUAL_OPERATION = 142;
        static final int INT_FLOW_CHART_CONNECTOR = 143;
        static final int INT_FLOW_CHART_PUNCHED_CARD = 144;
        static final int INT_FLOW_CHART_PUNCHED_TAPE = 145;
        static final int INT_FLOW_CHART_SUMMING_JUNCTION = 146;
        static final int INT_FLOW_CHART_OR = 147;
        static final int INT_FLOW_CHART_COLLATE = 148;
        static final int INT_FLOW_CHART_SORT = 149;
        static final int INT_FLOW_CHART_EXTRACT = 150;
        static final int INT_FLOW_CHART_MERGE = 151;
        static final int INT_FLOW_CHART_OFFLINE_STORAGE = 152;
        static final int INT_FLOW_CHART_ONLINE_STORAGE = 153;
        static final int INT_FLOW_CHART_MAGNETIC_TAPE = 154;
        static final int INT_FLOW_CHART_MAGNETIC_DISK = 155;
        static final int INT_FLOW_CHART_MAGNETIC_DRUM = 156;
        static final int INT_FLOW_CHART_DISPLAY = 157;
        static final int INT_FLOW_CHART_DELAY = 158;
        static final int INT_FLOW_CHART_ALTERNATE_PROCESS = 159;
        static final int INT_FLOW_CHART_OFFPAGE_CONNECTOR = 160;
        static final int INT_ACTION_BUTTON_BLANK = 161;
        static final int INT_ACTION_BUTTON_HOME = 162;
        static final int INT_ACTION_BUTTON_HELP = 163;
        static final int INT_ACTION_BUTTON_INFORMATION = 164;
        static final int INT_ACTION_BUTTON_FORWARD_NEXT = 165;
        static final int INT_ACTION_BUTTON_BACK_PREVIOUS = 166;
        static final int INT_ACTION_BUTTON_END = 167;
        static final int INT_ACTION_BUTTON_BEGINNING = 168;
        static final int INT_ACTION_BUTTON_RETURN = 169;
        static final int INT_ACTION_BUTTON_DOCUMENT = 170;
        static final int INT_ACTION_BUTTON_SOUND = 171;
        static final int INT_ACTION_BUTTON_MOVIE = 172;
        static final int INT_GEAR_6 = 173;
        static final int INT_GEAR_9 = 174;
        static final int INT_FUNNEL = 175;
        static final int INT_MATH_PLUS = 176;
        static final int INT_MATH_MINUS = 177;
        static final int INT_MATH_MULTIPLY = 178;
        static final int INT_MATH_DIVIDE = 179;
        static final int INT_MATH_EQUAL = 180;
        static final int INT_MATH_NOT_EQUAL = 181;
        static final int INT_CORNER_TABS = 182;
        static final int INT_SQUARE_TABS = 183;
        static final int INT_PLAQUE_TABS = 184;
        static final int INT_CHART_X = 185;
        static final int INT_CHART_STAR = 186;
        static final int INT_CHART_PLUS = 187;
        public static final StringEnumAbstractBase.Table table = new StringEnumAbstractBase.Table(new Enum[]{new Enum("line", 1), new Enum("lineInv", 2), new Enum("triangle", 3), new Enum("rtTriangle", 4), new Enum("rect", 5), new Enum("diamond", 6), new Enum("parallelogram", 7), new Enum("trapezoid", 8), new Enum("nonIsoscelesTrapezoid", 9), new Enum("pentagon", 10), new Enum("hexagon", 11), new Enum("heptagon", 12), new Enum("octagon", 13), new Enum("decagon", 14), new Enum("dodecagon", 15), new Enum("star4", 16), new Enum("star5", 17), new Enum("star6", 18), new Enum("star7", 19), new Enum("star8", 20), new Enum("star10", 21), new Enum("star12", 22), new Enum("star16", 23), new Enum("star24", 24), new Enum("star32", 25), new Enum("roundRect", 26), new Enum("round1Rect", 27), new Enum("round2SameRect", 28), new Enum("round2DiagRect", 29), new Enum("snipRoundRect", 30), new Enum("snip1Rect", 31), new Enum("snip2SameRect", 32), new Enum("snip2DiagRect", 33), new Enum("plaque", 34), new Enum("ellipse", 35), new Enum("teardrop", 36), new Enum("homePlate", 37), new Enum("chevron", 38), new Enum("pieWedge", 39), new Enum("pie", 40), new Enum("blockArc", 41), new Enum("donut", 42), new Enum("noSmoking", 43), new Enum("rightArrow", 44), new Enum("leftArrow", 45), new Enum("upArrow", 46), new Enum("downArrow", 47), new Enum("stripedRightArrow", 48), new Enum("notchedRightArrow", 49), new Enum("bentUpArrow", 50), new Enum("leftRightArrow", 51), new Enum("upDownArrow", 52), new Enum("leftUpArrow", 53), new Enum("leftRightUpArrow", 54), new Enum("quadArrow", 55), new Enum("leftArrowCallout", 56), new Enum("rightArrowCallout", 57), new Enum("upArrowCallout", 58), new Enum("downArrowCallout", 59), new Enum("leftRightArrowCallout", 60), new Enum("upDownArrowCallout", 61), new Enum("quadArrowCallout", 62), new Enum("bentArrow", 63), new Enum("uturnArrow", 64), new Enum("circularArrow", 65), new Enum("leftCircularArrow", 66), new Enum("leftRightCircularArrow", 67), new Enum("curvedRightArrow", 68), new Enum("curvedLeftArrow", 69), new Enum("curvedUpArrow", 70), new Enum("curvedDownArrow", 71), new Enum("swooshArrow", 72), new Enum("cube", 73), new Enum("can", 74), new Enum("lightningBolt", 75), new Enum("heart", 76), new Enum("sun", 77), new Enum("moon", 78), new Enum("smileyFace", 79), new Enum("irregularSeal1", 80), new Enum("irregularSeal2", 81), new Enum("foldedCorner", 82), new Enum("bevel", 83), new Enum("frame", 84), new Enum("halfFrame", 85), new Enum("corner", 86), new Enum("diagStripe", 87), new Enum("chord", 88), new Enum("arc", 89), new Enum("leftBracket", 90), new Enum("rightBracket", 91), new Enum("leftBrace", 92), new Enum("rightBrace", 93), new Enum("bracketPair", 94), new Enum("bracePair", 95), new Enum("straightConnector1", 96), new Enum("bentConnector2", 97), new Enum("bentConnector3", 98), new Enum("bentConnector4", 99), new Enum("bentConnector5", 100), new Enum("curvedConnector2", 101), new Enum("curvedConnector3", 102), new Enum("curvedConnector4", 103), new Enum("curvedConnector5", 104), new Enum("callout1", 105), new Enum("callout2", 106), new Enum("callout3", 107), new Enum("accentCallout1", 108), new Enum("accentCallout2", 109), new Enum("accentCallout3", 110), new Enum("borderCallout1", 111), new Enum("borderCallout2", 112), new Enum("borderCallout3", 113), new Enum("accentBorderCallout1", 114), new Enum("accentBorderCallout2", 115), new Enum("accentBorderCallout3", 116), new Enum("wedgeRectCallout", 117), new Enum("wedgeRoundRectCallout", 118), new Enum("wedgeEllipseCallout", 119), new Enum("cloudCallout", 120), new Enum("cloud", 121), new Enum("ribbon", 122), new Enum("ribbon2", 123), new Enum("ellipseRibbon", 124), new Enum("ellipseRibbon2", 125), new Enum("leftRightRibbon", 126), new Enum("verticalScroll", 127), new Enum("horizontalScroll", 128), new Enum("wave", 129), new Enum("doubleWave", 130), new Enum("plus", 131), new Enum("flowChartProcess", 132), new Enum("flowChartDecision", 133), new Enum("flowChartInputOutput", 134), new Enum("flowChartPredefinedProcess", 135), new Enum("flowChartInternalStorage", 136), new Enum("flowChartDocument", 137), new Enum("flowChartMultidocument", 138), new Enum("flowChartTerminator", 139), new Enum("flowChartPreparation", 140), new Enum("flowChartManualInput", 141), new Enum("flowChartManualOperation", 142), new Enum("flowChartConnector", 143), new Enum("flowChartPunchedCard", 144), new Enum("flowChartPunchedTape", 145), new Enum("flowChartSummingJunction", 146), new Enum("flowChartOr", 147), new Enum("flowChartCollate", 148), new Enum("flowChartSort", 149), new Enum("flowChartExtract", 150), new Enum("flowChartMerge", 151), new Enum("flowChartOfflineStorage", 152), new Enum("flowChartOnlineStorage", 153), new Enum("flowChartMagneticTape", 154), new Enum("flowChartMagneticDisk", 155), new Enum("flowChartMagneticDrum", 156), new Enum("flowChartDisplay", 157), new Enum("flowChartDelay", 158), new Enum("flowChartAlternateProcess", 159), new Enum("flowChartOffpageConnector", 160), new Enum("actionButtonBlank", 161), new Enum("actionButtonHome", 162), new Enum("actionButtonHelp", 163), new Enum("actionButtonInformation", 164), new Enum("actionButtonForwardNext", 165), new Enum("actionButtonBackPrevious", 166), new Enum("actionButtonEnd", 167), new Enum("actionButtonBeginning", 168), new Enum("actionButtonReturn", 169), new Enum("actionButtonDocument", 170), new Enum("actionButtonSound", 171), new Enum("actionButtonMovie", 172), new Enum("gear6", 173), new Enum("gear9", 174), new Enum("funnel", 175), new Enum("mathPlus", 176), new Enum("mathMinus", 177), new Enum("mathMultiply", 178), new Enum("mathDivide", 179), new Enum("mathEqual", 180), new Enum("mathNotEqual", 181), new Enum("cornerTabs", 182), new Enum("squareTabs", 183), new Enum("plaqueTabs", 184), new Enum("chartX", 185), new Enum("chartStar", 186), new Enum("chartPlus", 187)});
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

