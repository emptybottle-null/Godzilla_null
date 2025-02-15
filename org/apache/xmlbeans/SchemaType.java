/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.QNameSet;
import org.apache.xmlbeans.SchemaAnnotated;
import org.apache.xmlbeans.SchemaAttributeModel;
import org.apache.xmlbeans.SchemaComponent;
import org.apache.xmlbeans.SchemaField;
import org.apache.xmlbeans.SchemaParticle;
import org.apache.xmlbeans.SchemaProperty;
import org.apache.xmlbeans.SchemaStringEnumEntry;
import org.apache.xmlbeans.SchemaTypeElementSequencer;
import org.apache.xmlbeans.SchemaTypeLoader;
import org.apache.xmlbeans.SchemaTypeSystem;
import org.apache.xmlbeans.StringEnumAbstractBase;
import org.apache.xmlbeans.XmlAnySimpleType;

public interface SchemaType
extends SchemaComponent,
SchemaAnnotated {
    public static final int DT_NOT_DERIVED = 0;
    public static final int DT_RESTRICTION = 1;
    public static final int DT_EXTENSION = 2;
    public static final int BTC_NOT_BUILTIN = 0;
    public static final int BTC_ANY_TYPE = 1;
    public static final int BTC_FIRST_PRIMITIVE = 2;
    public static final int BTC_ANY_SIMPLE = 2;
    public static final int BTC_BOOLEAN = 3;
    public static final int BTC_BASE_64_BINARY = 4;
    public static final int BTC_HEX_BINARY = 5;
    public static final int BTC_ANY_URI = 6;
    public static final int BTC_QNAME = 7;
    public static final int BTC_NOTATION = 8;
    public static final int BTC_FLOAT = 9;
    public static final int BTC_DOUBLE = 10;
    public static final int BTC_DECIMAL = 11;
    public static final int BTC_STRING = 12;
    public static final int BTC_DURATION = 13;
    public static final int BTC_DATE_TIME = 14;
    public static final int BTC_TIME = 15;
    public static final int BTC_DATE = 16;
    public static final int BTC_G_YEAR_MONTH = 17;
    public static final int BTC_G_YEAR = 18;
    public static final int BTC_G_MONTH_DAY = 19;
    public static final int BTC_G_DAY = 20;
    public static final int BTC_G_MONTH = 21;
    public static final int BTC_LAST_PRIMITIVE = 21;
    public static final int BTC_INTEGER = 22;
    public static final int BTC_LONG = 23;
    public static final int BTC_INT = 24;
    public static final int BTC_SHORT = 25;
    public static final int BTC_BYTE = 26;
    public static final int BTC_NON_POSITIVE_INTEGER = 27;
    public static final int BTC_NEGATIVE_INTEGER = 28;
    public static final int BTC_NON_NEGATIVE_INTEGER = 29;
    public static final int BTC_POSITIVE_INTEGER = 30;
    public static final int BTC_UNSIGNED_LONG = 31;
    public static final int BTC_UNSIGNED_INT = 32;
    public static final int BTC_UNSIGNED_SHORT = 33;
    public static final int BTC_UNSIGNED_BYTE = 34;
    public static final int BTC_NORMALIZED_STRING = 35;
    public static final int BTC_TOKEN = 36;
    public static final int BTC_NAME = 37;
    public static final int BTC_NCNAME = 38;
    public static final int BTC_LANGUAGE = 39;
    public static final int BTC_ID = 40;
    public static final int BTC_IDREF = 41;
    public static final int BTC_IDREFS = 42;
    public static final int BTC_ENTITY = 43;
    public static final int BTC_ENTITIES = 44;
    public static final int BTC_NMTOKEN = 45;
    public static final int BTC_NMTOKENS = 46;
    public static final int BTC_LAST_BUILTIN = 46;
    public static final int NOT_COMPLEX_TYPE = 0;
    public static final int EMPTY_CONTENT = 1;
    public static final int SIMPLE_CONTENT = 2;
    public static final int ELEMENT_CONTENT = 3;
    public static final int MIXED_CONTENT = 4;
    public static final int FACET_LENGTH = 0;
    public static final int FACET_MIN_LENGTH = 1;
    public static final int FACET_MAX_LENGTH = 2;
    public static final int FACET_MIN_EXCLUSIVE = 3;
    public static final int FACET_MIN_INCLUSIVE = 4;
    public static final int FACET_MAX_INCLUSIVE = 5;
    public static final int FACET_MAX_EXCLUSIVE = 6;
    public static final int FACET_TOTAL_DIGITS = 7;
    public static final int FACET_FRACTION_DIGITS = 8;
    public static final int LAST_BASIC_FACET = 8;
    public static final int FACET_WHITE_SPACE = 9;
    public static final int FACET_PATTERN = 10;
    public static final int FACET_ENUMERATION = 11;
    public static final int LAST_FACET = 11;
    public static final int PROPERTY_ORDERED = 12;
    public static final int PROPERTY_BOUNDED = 13;
    public static final int PROPERTY_CARDINALITY = 14;
    public static final int PROPERTY_NUMERIC = 15;
    public static final int LAST_PROPERTY = 15;
    public static final int UNORDERED = 0;
    public static final int PARTIAL_ORDER = 1;
    public static final int TOTAL_ORDER = 2;
    public static final int NOT_SIMPLE = 0;
    public static final int ATOMIC = 1;
    public static final int UNION = 2;
    public static final int LIST = 3;
    public static final int NOT_DECIMAL = 0;
    public static final int SIZE_BYTE = 8;
    public static final int SIZE_SHORT = 16;
    public static final int SIZE_INT = 32;
    public static final int SIZE_LONG = 64;
    public static final int SIZE_BIG_INTEGER = 1000000;
    public static final int SIZE_BIG_DECIMAL = 1000001;
    public static final int WS_UNSPECIFIED = 0;
    public static final int WS_PRESERVE = 1;
    public static final int WS_REPLACE = 2;
    public static final int WS_COLLAPSE = 3;

    @Override
    public QName getName();

    public SchemaField getContainerField();

    public boolean isDocumentType();

    public boolean isAttributeType();

    public QName getDocumentElementName();

    public QName getAttributeTypeAttributeName();

    public SchemaType getOuterType();

    public boolean isSkippedAnonymousType();

    public boolean isCompiled();

    public String getFullJavaName();

    public String getShortJavaName();

    public String getFullJavaImplName();

    public String getShortJavaImplName();

    public Class getJavaClass();

    public Class getEnumJavaClass();

    public Object getUserData();

    public boolean isAnonymousType();

    public boolean isBuiltinType();

    public boolean isSimpleType();

    public SchemaType getBaseType();

    public SchemaType getCommonBaseType(SchemaType var1);

    public boolean isAssignableFrom(SchemaType var1);

    public int getDerivationType();

    public int getBuiltinTypeCode();

    public boolean isURType();

    public boolean isNoType();

    @Override
    public SchemaTypeSystem getTypeSystem();

    public boolean isAbstract();

    public boolean finalExtension();

    public boolean finalRestriction();

    public boolean finalList();

    public boolean finalUnion();

    public boolean blockExtension();

    public boolean blockRestriction();

    public int getContentType();

    public SchemaType getContentBasedOnType();

    public SchemaTypeElementSequencer getElementSequencer();

    public SchemaType[] getAnonymousTypes();

    public SchemaProperty getElementProperty(QName var1);

    public SchemaProperty[] getElementProperties();

    public SchemaProperty getAttributeProperty(QName var1);

    public SchemaProperty[] getAttributeProperties();

    public SchemaProperty[] getProperties();

    public SchemaProperty[] getDerivedProperties();

    public SchemaAttributeModel getAttributeModel();

    public boolean hasAttributeWildcards();

    public SchemaParticle getContentModel();

    public boolean hasElementWildcards();

    public boolean isValidSubstitution(QName var1);

    public boolean hasAllContent();

    public boolean isOrderSensitive();

    public SchemaType getElementType(QName var1, QName var2, SchemaTypeLoader var3);

    public SchemaType getAttributeType(QName var1, SchemaTypeLoader var2);

    public XmlAnySimpleType getFacet(int var1);

    public boolean isFacetFixed(int var1);

    public int ordered();

    public boolean isBounded();

    public boolean isFinite();

    public boolean isNumeric();

    public boolean hasPatternFacet();

    public String[] getPatterns();

    public boolean matchPatternFacet(String var1);

    public XmlAnySimpleType[] getEnumerationValues();

    public boolean hasStringEnumValues();

    public SchemaType getBaseEnumType();

    public SchemaStringEnumEntry[] getStringEnumEntries();

    public SchemaStringEnumEntry enumEntryForString(String var1);

    public StringEnumAbstractBase enumForString(String var1);

    public StringEnumAbstractBase enumForInt(int var1);

    public boolean isPrimitiveType();

    public int getSimpleVariety();

    public SchemaType getPrimitiveType();

    public int getDecimalSize();

    public SchemaType[] getUnionMemberTypes();

    public SchemaType[] getUnionSubTypes();

    public SchemaType[] getUnionConstituentTypes();

    public SchemaType getUnionCommonBaseType();

    public int getAnonymousUnionMemberOrdinal();

    public SchemaType getListItemType();

    public int getWhiteSpaceRule();

    public XmlAnySimpleType newValue(Object var1);

    public Ref getRef();

    public QNameSet qnameSetForWildcardElements();

    public QNameSet qnameSetForWildcardAttributes();

    public static final class Ref
    extends SchemaComponent.Ref {
        public Ref(SchemaType type) {
            super(type);
        }

        public Ref(SchemaTypeSystem system, String handle) {
            super(system, handle);
        }

        @Override
        public final int getComponentType() {
            return 0;
        }

        public final SchemaType get() {
            return (SchemaType)this.getComponent();
        }
    }
}

