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

public interface STNumberFormat
extends XmlString {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(STNumberFormat.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("stnumberformat0fb8type");
    public static final Enum DECIMAL = Enum.forString("decimal");
    public static final Enum UPPER_ROMAN = Enum.forString("upperRoman");
    public static final Enum LOWER_ROMAN = Enum.forString("lowerRoman");
    public static final Enum UPPER_LETTER = Enum.forString("upperLetter");
    public static final Enum LOWER_LETTER = Enum.forString("lowerLetter");
    public static final Enum ORDINAL = Enum.forString("ordinal");
    public static final Enum CARDINAL_TEXT = Enum.forString("cardinalText");
    public static final Enum ORDINAL_TEXT = Enum.forString("ordinalText");
    public static final Enum HEX = Enum.forString("hex");
    public static final Enum CHICAGO = Enum.forString("chicago");
    public static final Enum IDEOGRAPH_DIGITAL = Enum.forString("ideographDigital");
    public static final Enum JAPANESE_COUNTING = Enum.forString("japaneseCounting");
    public static final Enum AIUEO = Enum.forString("aiueo");
    public static final Enum IROHA = Enum.forString("iroha");
    public static final Enum DECIMAL_FULL_WIDTH = Enum.forString("decimalFullWidth");
    public static final Enum DECIMAL_HALF_WIDTH = Enum.forString("decimalHalfWidth");
    public static final Enum JAPANESE_LEGAL = Enum.forString("japaneseLegal");
    public static final Enum JAPANESE_DIGITAL_TEN_THOUSAND = Enum.forString("japaneseDigitalTenThousand");
    public static final Enum DECIMAL_ENCLOSED_CIRCLE = Enum.forString("decimalEnclosedCircle");
    public static final Enum DECIMAL_FULL_WIDTH_2 = Enum.forString("decimalFullWidth2");
    public static final Enum AIUEO_FULL_WIDTH = Enum.forString("aiueoFullWidth");
    public static final Enum IROHA_FULL_WIDTH = Enum.forString("irohaFullWidth");
    public static final Enum DECIMAL_ZERO = Enum.forString("decimalZero");
    public static final Enum BULLET = Enum.forString("bullet");
    public static final Enum GANADA = Enum.forString("ganada");
    public static final Enum CHOSUNG = Enum.forString("chosung");
    public static final Enum DECIMAL_ENCLOSED_FULLSTOP = Enum.forString("decimalEnclosedFullstop");
    public static final Enum DECIMAL_ENCLOSED_PAREN = Enum.forString("decimalEnclosedParen");
    public static final Enum DECIMAL_ENCLOSED_CIRCLE_CHINESE = Enum.forString("decimalEnclosedCircleChinese");
    public static final Enum IDEOGRAPH_ENCLOSED_CIRCLE = Enum.forString("ideographEnclosedCircle");
    public static final Enum IDEOGRAPH_TRADITIONAL = Enum.forString("ideographTraditional");
    public static final Enum IDEOGRAPH_ZODIAC = Enum.forString("ideographZodiac");
    public static final Enum IDEOGRAPH_ZODIAC_TRADITIONAL = Enum.forString("ideographZodiacTraditional");
    public static final Enum TAIWANESE_COUNTING = Enum.forString("taiwaneseCounting");
    public static final Enum IDEOGRAPH_LEGAL_TRADITIONAL = Enum.forString("ideographLegalTraditional");
    public static final Enum TAIWANESE_COUNTING_THOUSAND = Enum.forString("taiwaneseCountingThousand");
    public static final Enum TAIWANESE_DIGITAL = Enum.forString("taiwaneseDigital");
    public static final Enum CHINESE_COUNTING = Enum.forString("chineseCounting");
    public static final Enum CHINESE_LEGAL_SIMPLIFIED = Enum.forString("chineseLegalSimplified");
    public static final Enum CHINESE_COUNTING_THOUSAND = Enum.forString("chineseCountingThousand");
    public static final Enum KOREAN_DIGITAL = Enum.forString("koreanDigital");
    public static final Enum KOREAN_COUNTING = Enum.forString("koreanCounting");
    public static final Enum KOREAN_LEGAL = Enum.forString("koreanLegal");
    public static final Enum KOREAN_DIGITAL_2 = Enum.forString("koreanDigital2");
    public static final Enum VIETNAMESE_COUNTING = Enum.forString("vietnameseCounting");
    public static final Enum RUSSIAN_LOWER = Enum.forString("russianLower");
    public static final Enum RUSSIAN_UPPER = Enum.forString("russianUpper");
    public static final Enum NONE = Enum.forString("none");
    public static final Enum NUMBER_IN_DASH = Enum.forString("numberInDash");
    public static final Enum HEBREW_1 = Enum.forString("hebrew1");
    public static final Enum HEBREW_2 = Enum.forString("hebrew2");
    public static final Enum ARABIC_ALPHA = Enum.forString("arabicAlpha");
    public static final Enum ARABIC_ABJAD = Enum.forString("arabicAbjad");
    public static final Enum HINDI_VOWELS = Enum.forString("hindiVowels");
    public static final Enum HINDI_CONSONANTS = Enum.forString("hindiConsonants");
    public static final Enum HINDI_NUMBERS = Enum.forString("hindiNumbers");
    public static final Enum HINDI_COUNTING = Enum.forString("hindiCounting");
    public static final Enum THAI_LETTERS = Enum.forString("thaiLetters");
    public static final Enum THAI_NUMBERS = Enum.forString("thaiNumbers");
    public static final Enum THAI_COUNTING = Enum.forString("thaiCounting");
    public static final int INT_DECIMAL = 1;
    public static final int INT_UPPER_ROMAN = 2;
    public static final int INT_LOWER_ROMAN = 3;
    public static final int INT_UPPER_LETTER = 4;
    public static final int INT_LOWER_LETTER = 5;
    public static final int INT_ORDINAL = 6;
    public static final int INT_CARDINAL_TEXT = 7;
    public static final int INT_ORDINAL_TEXT = 8;
    public static final int INT_HEX = 9;
    public static final int INT_CHICAGO = 10;
    public static final int INT_IDEOGRAPH_DIGITAL = 11;
    public static final int INT_JAPANESE_COUNTING = 12;
    public static final int INT_AIUEO = 13;
    public static final int INT_IROHA = 14;
    public static final int INT_DECIMAL_FULL_WIDTH = 15;
    public static final int INT_DECIMAL_HALF_WIDTH = 16;
    public static final int INT_JAPANESE_LEGAL = 17;
    public static final int INT_JAPANESE_DIGITAL_TEN_THOUSAND = 18;
    public static final int INT_DECIMAL_ENCLOSED_CIRCLE = 19;
    public static final int INT_DECIMAL_FULL_WIDTH_2 = 20;
    public static final int INT_AIUEO_FULL_WIDTH = 21;
    public static final int INT_IROHA_FULL_WIDTH = 22;
    public static final int INT_DECIMAL_ZERO = 23;
    public static final int INT_BULLET = 24;
    public static final int INT_GANADA = 25;
    public static final int INT_CHOSUNG = 26;
    public static final int INT_DECIMAL_ENCLOSED_FULLSTOP = 27;
    public static final int INT_DECIMAL_ENCLOSED_PAREN = 28;
    public static final int INT_DECIMAL_ENCLOSED_CIRCLE_CHINESE = 29;
    public static final int INT_IDEOGRAPH_ENCLOSED_CIRCLE = 30;
    public static final int INT_IDEOGRAPH_TRADITIONAL = 31;
    public static final int INT_IDEOGRAPH_ZODIAC = 32;
    public static final int INT_IDEOGRAPH_ZODIAC_TRADITIONAL = 33;
    public static final int INT_TAIWANESE_COUNTING = 34;
    public static final int INT_IDEOGRAPH_LEGAL_TRADITIONAL = 35;
    public static final int INT_TAIWANESE_COUNTING_THOUSAND = 36;
    public static final int INT_TAIWANESE_DIGITAL = 37;
    public static final int INT_CHINESE_COUNTING = 38;
    public static final int INT_CHINESE_LEGAL_SIMPLIFIED = 39;
    public static final int INT_CHINESE_COUNTING_THOUSAND = 40;
    public static final int INT_KOREAN_DIGITAL = 41;
    public static final int INT_KOREAN_COUNTING = 42;
    public static final int INT_KOREAN_LEGAL = 43;
    public static final int INT_KOREAN_DIGITAL_2 = 44;
    public static final int INT_VIETNAMESE_COUNTING = 45;
    public static final int INT_RUSSIAN_LOWER = 46;
    public static final int INT_RUSSIAN_UPPER = 47;
    public static final int INT_NONE = 48;
    public static final int INT_NUMBER_IN_DASH = 49;
    public static final int INT_HEBREW_1 = 50;
    public static final int INT_HEBREW_2 = 51;
    public static final int INT_ARABIC_ALPHA = 52;
    public static final int INT_ARABIC_ABJAD = 53;
    public static final int INT_HINDI_VOWELS = 54;
    public static final int INT_HINDI_CONSONANTS = 55;
    public static final int INT_HINDI_NUMBERS = 56;
    public static final int INT_HINDI_COUNTING = 57;
    public static final int INT_THAI_LETTERS = 58;
    public static final int INT_THAI_NUMBERS = 59;
    public static final int INT_THAI_COUNTING = 60;

    public StringEnumAbstractBase enumValue();

    public void set(StringEnumAbstractBase var1);

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        public static STNumberFormat newValue(Object object) {
            return (STNumberFormat)type.newValue(object);
        }

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(STNumberFormat.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static STNumberFormat newInstance() {
            return (STNumberFormat)Factory.getTypeLoader().newInstance(type, null);
        }

        public static STNumberFormat newInstance(XmlOptions xmlOptions) {
            return (STNumberFormat)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static STNumberFormat parse(String string) throws XmlException {
            return (STNumberFormat)Factory.getTypeLoader().parse(string, type, null);
        }

        public static STNumberFormat parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (STNumberFormat)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static STNumberFormat parse(File file) throws XmlException, IOException {
            return (STNumberFormat)Factory.getTypeLoader().parse(file, type, null);
        }

        public static STNumberFormat parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STNumberFormat)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static STNumberFormat parse(URL uRL) throws XmlException, IOException {
            return (STNumberFormat)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static STNumberFormat parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STNumberFormat)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static STNumberFormat parse(InputStream inputStream) throws XmlException, IOException {
            return (STNumberFormat)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static STNumberFormat parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STNumberFormat)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static STNumberFormat parse(Reader reader) throws XmlException, IOException {
            return (STNumberFormat)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static STNumberFormat parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STNumberFormat)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static STNumberFormat parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (STNumberFormat)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static STNumberFormat parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (STNumberFormat)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static STNumberFormat parse(Node node) throws XmlException {
            return (STNumberFormat)Factory.getTypeLoader().parse(node, type, null);
        }

        public static STNumberFormat parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (STNumberFormat)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static STNumberFormat parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (STNumberFormat)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static STNumberFormat parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (STNumberFormat)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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
        static final int INT_DECIMAL = 1;
        static final int INT_UPPER_ROMAN = 2;
        static final int INT_LOWER_ROMAN = 3;
        static final int INT_UPPER_LETTER = 4;
        static final int INT_LOWER_LETTER = 5;
        static final int INT_ORDINAL = 6;
        static final int INT_CARDINAL_TEXT = 7;
        static final int INT_ORDINAL_TEXT = 8;
        static final int INT_HEX = 9;
        static final int INT_CHICAGO = 10;
        static final int INT_IDEOGRAPH_DIGITAL = 11;
        static final int INT_JAPANESE_COUNTING = 12;
        static final int INT_AIUEO = 13;
        static final int INT_IROHA = 14;
        static final int INT_DECIMAL_FULL_WIDTH = 15;
        static final int INT_DECIMAL_HALF_WIDTH = 16;
        static final int INT_JAPANESE_LEGAL = 17;
        static final int INT_JAPANESE_DIGITAL_TEN_THOUSAND = 18;
        static final int INT_DECIMAL_ENCLOSED_CIRCLE = 19;
        static final int INT_DECIMAL_FULL_WIDTH_2 = 20;
        static final int INT_AIUEO_FULL_WIDTH = 21;
        static final int INT_IROHA_FULL_WIDTH = 22;
        static final int INT_DECIMAL_ZERO = 23;
        static final int INT_BULLET = 24;
        static final int INT_GANADA = 25;
        static final int INT_CHOSUNG = 26;
        static final int INT_DECIMAL_ENCLOSED_FULLSTOP = 27;
        static final int INT_DECIMAL_ENCLOSED_PAREN = 28;
        static final int INT_DECIMAL_ENCLOSED_CIRCLE_CHINESE = 29;
        static final int INT_IDEOGRAPH_ENCLOSED_CIRCLE = 30;
        static final int INT_IDEOGRAPH_TRADITIONAL = 31;
        static final int INT_IDEOGRAPH_ZODIAC = 32;
        static final int INT_IDEOGRAPH_ZODIAC_TRADITIONAL = 33;
        static final int INT_TAIWANESE_COUNTING = 34;
        static final int INT_IDEOGRAPH_LEGAL_TRADITIONAL = 35;
        static final int INT_TAIWANESE_COUNTING_THOUSAND = 36;
        static final int INT_TAIWANESE_DIGITAL = 37;
        static final int INT_CHINESE_COUNTING = 38;
        static final int INT_CHINESE_LEGAL_SIMPLIFIED = 39;
        static final int INT_CHINESE_COUNTING_THOUSAND = 40;
        static final int INT_KOREAN_DIGITAL = 41;
        static final int INT_KOREAN_COUNTING = 42;
        static final int INT_KOREAN_LEGAL = 43;
        static final int INT_KOREAN_DIGITAL_2 = 44;
        static final int INT_VIETNAMESE_COUNTING = 45;
        static final int INT_RUSSIAN_LOWER = 46;
        static final int INT_RUSSIAN_UPPER = 47;
        static final int INT_NONE = 48;
        static final int INT_NUMBER_IN_DASH = 49;
        static final int INT_HEBREW_1 = 50;
        static final int INT_HEBREW_2 = 51;
        static final int INT_ARABIC_ALPHA = 52;
        static final int INT_ARABIC_ABJAD = 53;
        static final int INT_HINDI_VOWELS = 54;
        static final int INT_HINDI_CONSONANTS = 55;
        static final int INT_HINDI_NUMBERS = 56;
        static final int INT_HINDI_COUNTING = 57;
        static final int INT_THAI_LETTERS = 58;
        static final int INT_THAI_NUMBERS = 59;
        static final int INT_THAI_COUNTING = 60;
        public static final StringEnumAbstractBase.Table table = new StringEnumAbstractBase.Table(new Enum[]{new Enum("decimal", 1), new Enum("upperRoman", 2), new Enum("lowerRoman", 3), new Enum("upperLetter", 4), new Enum("lowerLetter", 5), new Enum("ordinal", 6), new Enum("cardinalText", 7), new Enum("ordinalText", 8), new Enum("hex", 9), new Enum("chicago", 10), new Enum("ideographDigital", 11), new Enum("japaneseCounting", 12), new Enum("aiueo", 13), new Enum("iroha", 14), new Enum("decimalFullWidth", 15), new Enum("decimalHalfWidth", 16), new Enum("japaneseLegal", 17), new Enum("japaneseDigitalTenThousand", 18), new Enum("decimalEnclosedCircle", 19), new Enum("decimalFullWidth2", 20), new Enum("aiueoFullWidth", 21), new Enum("irohaFullWidth", 22), new Enum("decimalZero", 23), new Enum("bullet", 24), new Enum("ganada", 25), new Enum("chosung", 26), new Enum("decimalEnclosedFullstop", 27), new Enum("decimalEnclosedParen", 28), new Enum("decimalEnclosedCircleChinese", 29), new Enum("ideographEnclosedCircle", 30), new Enum("ideographTraditional", 31), new Enum("ideographZodiac", 32), new Enum("ideographZodiacTraditional", 33), new Enum("taiwaneseCounting", 34), new Enum("ideographLegalTraditional", 35), new Enum("taiwaneseCountingThousand", 36), new Enum("taiwaneseDigital", 37), new Enum("chineseCounting", 38), new Enum("chineseLegalSimplified", 39), new Enum("chineseCountingThousand", 40), new Enum("koreanDigital", 41), new Enum("koreanCounting", 42), new Enum("koreanLegal", 43), new Enum("koreanDigital2", 44), new Enum("vietnameseCounting", 45), new Enum("russianLower", 46), new Enum("russianUpper", 47), new Enum("none", 48), new Enum("numberInDash", 49), new Enum("hebrew1", 50), new Enum("hebrew2", 51), new Enum("arabicAlpha", 52), new Enum("arabicAbjad", 53), new Enum("hindiVowels", 54), new Enum("hindiConsonants", 55), new Enum("hindiNumbers", 56), new Enum("hindiCounting", 57), new Enum("thaiLetters", 58), new Enum("thaiNumbers", 59), new Enum("thaiCounting", 60)});
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

