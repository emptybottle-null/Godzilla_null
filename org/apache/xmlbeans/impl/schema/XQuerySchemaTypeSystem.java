/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.schema;

import java.io.File;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.Filer;
import org.apache.xmlbeans.QNameSet;
import org.apache.xmlbeans.SchemaAnnotation;
import org.apache.xmlbeans.SchemaAttributeGroup;
import org.apache.xmlbeans.SchemaComponent;
import org.apache.xmlbeans.SchemaGlobalAttribute;
import org.apache.xmlbeans.SchemaGlobalElement;
import org.apache.xmlbeans.SchemaIdentityConstraint;
import org.apache.xmlbeans.SchemaModelGroup;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SchemaTypeSystem;
import org.apache.xmlbeans.impl.common.QNameHelper;
import org.apache.xmlbeans.impl.regex.ParseException;
import org.apache.xmlbeans.impl.regex.RegularExpression;
import org.apache.xmlbeans.impl.regex.SchemaRegularExpression;
import org.apache.xmlbeans.impl.schema.BuiltinSchemaTypeSystem;
import org.apache.xmlbeans.impl.schema.SchemaAttributeModelImpl;
import org.apache.xmlbeans.impl.schema.SchemaContainer;
import org.apache.xmlbeans.impl.schema.SchemaParticleImpl;
import org.apache.xmlbeans.impl.schema.SchemaTypeImpl;
import org.apache.xmlbeans.impl.schema.SchemaTypeLoaderBase;
import org.apache.xmlbeans.impl.schema.XmlValueRef;
import org.apache.xmlbeans.impl.values.XmlIntegerImpl;
import org.apache.xmlbeans.impl.values.XmlStringImpl;
import org.apache.xmlbeans.impl.values.XmlValueOutOfRangeException;

public class XQuerySchemaTypeSystem
extends SchemaTypeLoaderBase
implements SchemaTypeSystem {
    public static final int BTC_FIRST_XQUERY = 52;
    public static final int BTC_ANY_ATOMIC = 52;
    public static final int BTC_DAY_TIME_DURATION = 53;
    public static final int BTC_YEAR_MONTH_DURATION = 54;
    public static final int BTC_LAST_XQUERY = 54;
    private static final SchemaType[] EMPTY_SCHEMATYPE_ARRAY;
    private static final SchemaType.Ref[] EMPTY_SCHEMATYPEREF_ARRAY;
    private static final SchemaGlobalElement[] EMPTY_SCHEMAELEMENT_ARRAY;
    private static final SchemaGlobalAttribute[] EMPTY_SCHEMAATTRIBUTE_ARRAY;
    private static final SchemaModelGroup[] EMPTY_SCHEMAMODELGROUP_ARRAY;
    private static final SchemaAttributeGroup[] EMPTY_SCHEMAATTRIBUTEGROUP_ARRAY;
    private static final SchemaAnnotation[] EMPTY_SCHEMAANNOTATION_ARRAY;
    private static XQuerySchemaTypeSystem _global;
    public static final SchemaTypeImpl ST_ANY_TYPE;
    public static final SchemaTypeImpl ST_ANY_SIMPLE;
    public static final SchemaTypeImpl ST_ANY_ATOMIC;
    public static final SchemaTypeImpl ST_BOOLEAN;
    public static final SchemaTypeImpl ST_BASE_64_BINARY;
    public static final SchemaTypeImpl ST_HEX_BINARY;
    public static final SchemaTypeImpl ST_ANY_URI;
    public static final SchemaTypeImpl ST_QNAME;
    public static final SchemaTypeImpl ST_NOTATION;
    public static final SchemaTypeImpl ST_FLOAT;
    public static final SchemaTypeImpl ST_DOUBLE;
    public static final SchemaTypeImpl ST_DECIMAL;
    public static final SchemaTypeImpl ST_STRING;
    public static final SchemaTypeImpl ST_DURATION;
    public static final SchemaTypeImpl ST_DATE_TIME;
    public static final SchemaTypeImpl ST_TIME;
    public static final SchemaTypeImpl ST_DATE;
    public static final SchemaTypeImpl ST_G_YEAR_MONTH;
    public static final SchemaTypeImpl ST_G_YEAR;
    public static final SchemaTypeImpl ST_G_MONTH_DAY;
    public static final SchemaTypeImpl ST_G_DAY;
    public static final SchemaTypeImpl ST_G_MONTH;
    public static final SchemaTypeImpl ST_INTEGER;
    public static final SchemaTypeImpl ST_LONG;
    public static final SchemaTypeImpl ST_INT;
    public static final SchemaTypeImpl ST_SHORT;
    public static final SchemaTypeImpl ST_BYTE;
    public static final SchemaTypeImpl ST_NON_POSITIVE_INTEGER;
    public static final SchemaTypeImpl ST_NEGATIVE_INTEGER;
    public static final SchemaTypeImpl ST_NON_NEGATIVE_INTEGER;
    public static final SchemaTypeImpl ST_POSITIVE_INTEGER;
    public static final SchemaTypeImpl ST_UNSIGNED_LONG;
    public static final SchemaTypeImpl ST_UNSIGNED_INT;
    public static final SchemaTypeImpl ST_UNSIGNED_SHORT;
    public static final SchemaTypeImpl ST_UNSIGNED_BYTE;
    public static final SchemaTypeImpl ST_NORMALIZED_STRING;
    public static final SchemaTypeImpl ST_TOKEN;
    public static final SchemaTypeImpl ST_NAME;
    public static final SchemaTypeImpl ST_NCNAME;
    public static final SchemaTypeImpl ST_LANGUAGE;
    public static final SchemaTypeImpl ST_ID;
    public static final SchemaTypeImpl ST_IDREF;
    public static final SchemaTypeImpl ST_IDREFS;
    public static final SchemaTypeImpl ST_ENTITY;
    public static final SchemaTypeImpl ST_ENTITIES;
    public static final SchemaTypeImpl ST_NMTOKEN;
    public static final SchemaTypeImpl ST_NMTOKENS;
    public static final SchemaTypeImpl ST_DAY_TIME_DURATION;
    public static final SchemaTypeImpl ST_YEAR_MONTH_DURATION;
    public static final SchemaTypeImpl ST_NO_TYPE;
    private static final XmlValueRef XMLSTR_PRESERVE;
    private static final XmlValueRef XMLSTR_REPLACE;
    private static final XmlValueRef XMLSTR_COLLAPSE;
    private static final XmlValueRef[] FACETS_NONE;
    private static final boolean[] FIXED_FACETS_NONE;
    private static final XmlValueRef[] FACETS_WS_COLLAPSE;
    private static final XmlValueRef[] FACETS_WS_REPLACE;
    private static final XmlValueRef[] FACETS_WS_PRESERVE;
    private static final XmlValueRef[] FACETS_INTEGER;
    private static final XmlValueRef[] FACETS_LONG;
    private static final XmlValueRef[] FACETS_INT;
    private static final XmlValueRef[] FACETS_SHORT;
    private static final XmlValueRef[] FACETS_BYTE;
    private static final XmlValueRef[] FACETS_NONNEGATIVE;
    private static final XmlValueRef[] FACETS_POSITIVE;
    private static final XmlValueRef[] FACETS_NONPOSITIVE;
    private static final XmlValueRef[] FACETS_NEGATIVE;
    private static final XmlValueRef[] FACETS_UNSIGNED_LONG;
    private static final XmlValueRef[] FACETS_UNSIGNED_INT;
    private static final XmlValueRef[] FACETS_UNSIGNED_SHORT;
    private static final XmlValueRef[] FACETS_UNSIGNED_BYTE;
    private static final XmlValueRef[] FACETS_BUILTIN_LIST;
    private static final boolean[] FIXED_FACETS_WS;
    private static final boolean[] FIXED_FACETS_INTEGER;
    static final XmlValueRef[] FACETS_UNION;
    static final boolean[] FIXED_FACETS_UNION;
    static final XmlValueRef[] FACETS_LIST;
    static final boolean[] FIXED_FACETS_LIST;
    private Map _typeMap = new HashMap();
    private SchemaTypeImpl[] _typeArray = new SchemaTypeImpl[50];
    private Map _handlesToObjects = new HashMap();
    private Map _objectsToHandles = new HashMap();
    private Map _typesByClassname = new HashMap();
    private SchemaContainer _container = new SchemaContainer("http://www.w3.org/2001/XMLSchema");

    public static SchemaTypeSystem get() {
        return _global;
    }

    private SchemaTypeImpl getBuiltinType(int btc) {
        return this._typeArray[this.arrayIndexForBtc(btc)];
    }

    private XQuerySchemaTypeSystem() {
        this._container.setTypeSystem(this);
        this.setupType(1, "anyType", "org.apache.xmlbeans.XmlObject");
        this.setupType(2, "anySimpleType", "org.apache.xmlbeans.XmlAnySimpleType");
        this.setupType(52, "anyAtomicType", null);
        this.setupType(3, "boolean", "org.apache.xmlbeans.XmlBoolean");
        this.setupType(4, "base64Binary", "org.apache.xmlbeans.XmlBase64Binary");
        this.setupType(5, "hexBinary", "org.apache.xmlbeans.XmlHexBinary");
        this.setupType(6, "anyURI", "org.apache.xmlbeans.XmlAnyURI");
        this.setupType(7, "QName", "org.apache.xmlbeans.XmlQName");
        this.setupType(8, "NOTATION", "org.apache.xmlbeans.XmlNOTATION");
        this.setupType(9, "float", "org.apache.xmlbeans.XmlFloat");
        this.setupType(10, "double", "org.apache.xmlbeans.XmlDouble");
        this.setupType(11, "decimal", "org.apache.xmlbeans.XmlDecimal");
        this.setupType(12, "string", "org.apache.xmlbeans.XmlString");
        this.setupType(13, "duration", "org.apache.xmlbeans.XmlDuration");
        this.setupType(14, "dateTime", "org.apache.xmlbeans.XmlDateTime");
        this.setupType(15, "time", "org.apache.xmlbeans.XmlTime");
        this.setupType(16, "date", "org.apache.xmlbeans.XmlDate");
        this.setupType(17, "gYearMonth", "org.apache.xmlbeans.XmlGYearMonth");
        this.setupType(18, "gYear", "org.apache.xmlbeans.XmlGYear");
        this.setupType(19, "gMonthDay", "org.apache.xmlbeans.XmlGMonthDay");
        this.setupType(20, "gDay", "org.apache.xmlbeans.XmlGDay");
        this.setupType(21, "gMonth", "org.apache.xmlbeans.XmlGMonth");
        this.setupType(22, "integer", "org.apache.xmlbeans.XmlInteger");
        this.setupType(23, "long", "org.apache.xmlbeans.XmlLong");
        this.setupType(24, "int", "org.apache.xmlbeans.XmlInt");
        this.setupType(25, "short", "org.apache.xmlbeans.XmlShort");
        this.setupType(26, "byte", "org.apache.xmlbeans.XmlByte");
        this.setupType(27, "nonPositiveInteger", "org.apache.xmlbeans.XmlNonPositiveInteger");
        this.setupType(28, "negativeInteger", "org.apache.xmlbeans.XmlNegativeInteger");
        this.setupType(29, "nonNegativeInteger", "org.apache.xmlbeans.XmlNonNegativeInteger");
        this.setupType(30, "positiveInteger", "org.apache.xmlbeans.XmlPositiveInteger");
        this.setupType(31, "unsignedLong", "org.apache.xmlbeans.XmlUnsignedLong");
        this.setupType(32, "unsignedInt", "org.apache.xmlbeans.XmlUnsignedInt");
        this.setupType(33, "unsignedShort", "org.apache.xmlbeans.XmlUnsignedShort");
        this.setupType(34, "unsignedByte", "org.apache.xmlbeans.XmlUnsignedByte");
        this.setupType(35, "normalizedString", "org.apache.xmlbeans.XmlNormalizedString");
        this.setupType(36, "token", "org.apache.xmlbeans.XmlToken");
        this.setupType(37, "Name", "org.apache.xmlbeans.XmlName");
        this.setupType(38, "NCName", "org.apache.xmlbeans.XmlNCName");
        this.setupType(39, "language", "org.apache.xmlbeans.XmlLanguage");
        this.setupType(40, "ID", "org.apache.xmlbeans.XmlID");
        this.setupType(41, "IDREF", "org.apache.xmlbeans.XmlIDREF");
        this.setupType(42, "IDREFS", "org.apache.xmlbeans.XmlIDREFS");
        this.setupType(43, "ENTITY", "org.apache.xmlbeans.XmlENTITY");
        this.setupType(44, "ENTITIES", "org.apache.xmlbeans.XmlENTITIES");
        this.setupType(45, "NMTOKEN", "org.apache.xmlbeans.XmlNMTOKEN");
        this.setupType(46, "NMTOKENS", "org.apache.xmlbeans.XmlNMTOKENS");
        this.setupType(53, "dayTimeDuration", null);
        this.setupType(54, "yearMonthDuration", null);
        this.setupType(0, null, null);
        this._container.setImmutable();
    }

    @Override
    public String getName() {
        return "xquery.typesystem.builtin";
    }

    @Override
    public boolean isNamespaceDefined(String namespace) {
        return namespace.equals("http://www.w3.org/2001/XMLSchema");
    }

    @Override
    public SchemaType findType(QName name) {
        return (SchemaType)this._typeMap.get(name);
    }

    @Override
    public SchemaType findDocumentType(QName name) {
        return null;
    }

    @Override
    public SchemaType findAttributeType(QName name) {
        return null;
    }

    @Override
    public SchemaGlobalElement findElement(QName name) {
        return null;
    }

    @Override
    public SchemaGlobalAttribute findAttribute(QName name) {
        return null;
    }

    @Override
    public SchemaType.Ref findTypeRef(QName name) {
        SchemaType type = this.findType(name);
        return type == null ? null : type.getRef();
    }

    @Override
    public SchemaType.Ref findDocumentTypeRef(QName name) {
        return null;
    }

    @Override
    public SchemaType.Ref findAttributeTypeRef(QName name) {
        return null;
    }

    @Override
    public SchemaGlobalElement.Ref findElementRef(QName name) {
        return null;
    }

    @Override
    public SchemaGlobalAttribute.Ref findAttributeRef(QName name) {
        return null;
    }

    @Override
    public SchemaModelGroup.Ref findModelGroupRef(QName name) {
        return null;
    }

    @Override
    public SchemaAttributeGroup.Ref findAttributeGroupRef(QName name) {
        return null;
    }

    @Override
    public SchemaIdentityConstraint.Ref findIdentityConstraintRef(QName name) {
        return null;
    }

    @Override
    public SchemaType typeForClassname(String classname) {
        return (SchemaType)this._typesByClassname.get(classname);
    }

    @Override
    public InputStream getSourceAsStream(String sourceName) {
        return null;
    }

    @Override
    public SchemaType[] globalTypes() {
        SchemaType[] result = new SchemaType[this._typeArray.length - 1];
        System.arraycopy(this._typeArray, 1, result, 0, result.length);
        return result;
    }

    @Override
    public SchemaType[] documentTypes() {
        return EMPTY_SCHEMATYPE_ARRAY;
    }

    @Override
    public SchemaType[] attributeTypes() {
        return EMPTY_SCHEMATYPE_ARRAY;
    }

    @Override
    public SchemaGlobalElement[] globalElements() {
        return EMPTY_SCHEMAELEMENT_ARRAY;
    }

    @Override
    public SchemaGlobalAttribute[] globalAttributes() {
        return EMPTY_SCHEMAATTRIBUTE_ARRAY;
    }

    @Override
    public SchemaModelGroup[] modelGroups() {
        return EMPTY_SCHEMAMODELGROUP_ARRAY;
    }

    @Override
    public SchemaAttributeGroup[] attributeGroups() {
        return EMPTY_SCHEMAATTRIBUTEGROUP_ARRAY;
    }

    @Override
    public SchemaAnnotation[] annotations() {
        return EMPTY_SCHEMAANNOTATION_ARRAY;
    }

    public String handleForType(SchemaType type) {
        return (String)this._objectsToHandles.get(type);
    }

    @Override
    public ClassLoader getClassLoader() {
        return BuiltinSchemaTypeSystem.class.getClassLoader();
    }

    @Override
    public void saveToDirectory(File classDir) {
        throw new UnsupportedOperationException("The builtin schema type system cannot be saved.");
    }

    @Override
    public void save(Filer filer) {
        throw new UnsupportedOperationException("The builtin schema type system cannot be saved.");
    }

    private int arrayIndexForBtc(int btc) {
        return btc > 46 ? btc - 52 + 46 + 1 : btc;
    }

    private static XmlValueRef build_wsstring(int wsr) {
        switch (wsr) {
            case 1: {
                return XMLSTR_PRESERVE;
            }
            case 2: {
                return XMLSTR_REPLACE;
            }
            case 3: {
                return XMLSTR_COLLAPSE;
            }
        }
        return null;
    }

    private static XmlValueRef buildNnInteger(BigInteger bigInt) {
        if (bigInt == null) {
            return null;
        }
        if (bigInt.signum() < 0) {
            return null;
        }
        try {
            XmlIntegerImpl i = new XmlIntegerImpl();
            i.setBigIntegerValue(bigInt);
            i.setImmutable();
            return new XmlValueRef(i);
        } catch (XmlValueOutOfRangeException e) {
            return null;
        }
    }

    private static XmlValueRef buildInteger(BigInteger bigInt) {
        if (bigInt == null) {
            return null;
        }
        try {
            XmlIntegerImpl i = new XmlIntegerImpl();
            i.setBigIntegerValue(bigInt);
            i.setImmutable();
            return new XmlValueRef(i);
        } catch (XmlValueOutOfRangeException e) {
            return null;
        }
    }

    private static XmlValueRef buildString(String str) {
        if (str == null) {
            return null;
        }
        try {
            XmlStringImpl i = new XmlStringImpl();
            i.setStringValue(str);
            i.setImmutable();
            return new XmlValueRef(i);
        } catch (XmlValueOutOfRangeException e) {
            return null;
        }
    }

    private void setupType(int btc, String localname, String classname) {
        SchemaTypeImpl result = new SchemaTypeImpl(this._container, true);
        this._container.addGlobalType(result.getRef());
        QName name = localname == null ? null : QNameHelper.forLNS(localname, "http://www.w3.org/2001/XMLSchema");
        String handle = "_BI_" + (localname == null ? "NO_TYPE" : localname);
        result.setName(name);
        result.setBuiltinTypeCode(btc);
        if (classname != null) {
            result.setFullJavaName(classname);
        }
        this._typeArray[this.arrayIndexForBtc((int)btc)] = result;
        this._typeMap.put(name, result);
        this._handlesToObjects.put(handle, result);
        this._objectsToHandles.put(result, handle);
        if (classname != null) {
            this._typesByClassname.put(classname, result);
        }
    }

    @Override
    public void resolve() {
    }

    @Override
    public SchemaType typeForHandle(String handle) {
        return (SchemaType)this._handlesToObjects.get(handle);
    }

    @Override
    public SchemaComponent resolveHandle(String handle) {
        return (SchemaComponent)this._handlesToObjects.get(handle);
    }

    public void fillInType(int btc) {
        SchemaParticleImpl contentModel;
        boolean[] fixedf;
        XmlValueRef[] facets;
        SchemaTypeImpl base;
        SchemaTypeImpl result = this.getBuiltinType(btc);
        SchemaTypeImpl item = null;
        int variety = 1;
        int derivationType = 1;
        switch (btc) {
            case 0: {
                variety = 0;
                base = ST_ANY_TYPE;
                break;
            }
            case 1: {
                variety = 0;
                base = null;
                derivationType = 1;
                break;
            }
            default: {
                assert (false);
            }
            case 2: {
                base = ST_ANY_TYPE;
                break;
            }
            case 52: {
                base = ST_ANY_SIMPLE;
            }
            case 3: 
            case 4: 
            case 5: 
            case 6: 
            case 7: 
            case 8: 
            case 9: 
            case 10: 
            case 11: 
            case 12: 
            case 13: 
            case 14: 
            case 15: 
            case 16: 
            case 17: 
            case 18: 
            case 19: 
            case 20: 
            case 21: {
                base = ST_ANY_ATOMIC;
                break;
            }
            case 22: {
                base = ST_DECIMAL;
                break;
            }
            case 23: {
                base = ST_INTEGER;
                break;
            }
            case 24: {
                base = ST_LONG;
                break;
            }
            case 25: {
                base = ST_INT;
                break;
            }
            case 26: {
                base = ST_SHORT;
                break;
            }
            case 27: {
                base = ST_INTEGER;
                break;
            }
            case 28: {
                base = ST_NON_POSITIVE_INTEGER;
                break;
            }
            case 29: {
                base = ST_INTEGER;
                break;
            }
            case 30: {
                base = ST_NON_NEGATIVE_INTEGER;
                break;
            }
            case 31: {
                base = ST_NON_NEGATIVE_INTEGER;
                break;
            }
            case 32: {
                base = ST_UNSIGNED_LONG;
                break;
            }
            case 33: {
                base = ST_UNSIGNED_INT;
                break;
            }
            case 34: {
                base = ST_UNSIGNED_SHORT;
                break;
            }
            case 35: {
                base = ST_STRING;
                break;
            }
            case 36: {
                base = ST_NORMALIZED_STRING;
                break;
            }
            case 37: {
                base = ST_TOKEN;
                break;
            }
            case 38: {
                base = ST_NAME;
                break;
            }
            case 40: 
            case 41: 
            case 43: {
                base = ST_NCNAME;
                break;
            }
            case 39: 
            case 45: {
                base = ST_TOKEN;
                break;
            }
            case 42: 
            case 44: 
            case 46: {
                variety = 3;
                base = ST_ANY_SIMPLE;
                if (btc == 42) {
                    item = ST_IDREF;
                    break;
                }
                if (btc == 44) {
                    item = ST_ENTITY;
                    break;
                }
                item = ST_NMTOKEN;
                break;
            }
            case 53: 
            case 54: {
                base = ST_DURATION;
            }
        }
        result.setDerivationType(derivationType);
        result.setSimpleTypeVariety(variety);
        if (variety != 0) {
            result.setSimpleType(true);
        } else assert (btc == 1 || btc == 0);
        result.setBaseTypeRef(base == null ? null : base.getRef());
        result.setBaseDepth(base == null ? 0 : base.getBaseDepth() + 1);
        result.setListItemTypeRef(item == null ? null : item.getRef());
        if (btc >= 2 && btc <= 21 || btc == 52) {
            result.setPrimitiveTypeRef(result.getRef());
        } else if (variety == 1) {
            if (base == null) {
                throw new IllegalStateException("Base was null for " + btc);
            }
            if (base.getPrimitiveType() == null) {
                throw new IllegalStateException("Base.gpt was null for " + btc);
            }
            result.setPrimitiveTypeRef(base.getPrimitiveType().getRef());
        }
        int wsr = 3;
        int decimalSize = 0;
        switch (btc) {
            default: {
                assert (false);
            }
            case 0: 
            case 1: 
            case 2: 
            case 52: {
                facets = FACETS_NONE;
                fixedf = FIXED_FACETS_NONE;
                wsr = 0;
                break;
            }
            case 12: {
                facets = FACETS_WS_PRESERVE;
                fixedf = FIXED_FACETS_NONE;
                wsr = 1;
                break;
            }
            case 3: 
            case 4: 
            case 5: 
            case 6: 
            case 7: 
            case 8: 
            case 9: 
            case 10: 
            case 13: 
            case 14: 
            case 15: 
            case 16: 
            case 17: 
            case 18: 
            case 19: 
            case 20: 
            case 21: {
                facets = FACETS_WS_COLLAPSE;
                fixedf = FIXED_FACETS_WS;
                break;
            }
            case 11: {
                facets = FACETS_WS_COLLAPSE;
                fixedf = FIXED_FACETS_WS;
                decimalSize = 1000001;
                break;
            }
            case 22: {
                facets = FACETS_INTEGER;
                fixedf = FIXED_FACETS_INTEGER;
                decimalSize = 1000000;
                break;
            }
            case 23: {
                facets = FACETS_LONG;
                fixedf = FIXED_FACETS_INTEGER;
                decimalSize = 64;
                break;
            }
            case 24: {
                facets = FACETS_INT;
                fixedf = FIXED_FACETS_INTEGER;
                decimalSize = 32;
                break;
            }
            case 25: {
                facets = FACETS_SHORT;
                fixedf = FIXED_FACETS_INTEGER;
                decimalSize = 16;
                break;
            }
            case 26: {
                facets = FACETS_BYTE;
                fixedf = FIXED_FACETS_INTEGER;
                decimalSize = 8;
                break;
            }
            case 27: {
                facets = FACETS_NONPOSITIVE;
                fixedf = FIXED_FACETS_INTEGER;
                decimalSize = 1000000;
                break;
            }
            case 28: {
                facets = FACETS_NEGATIVE;
                fixedf = FIXED_FACETS_INTEGER;
                decimalSize = 1000000;
                break;
            }
            case 29: {
                facets = FACETS_NONNEGATIVE;
                fixedf = FIXED_FACETS_INTEGER;
                decimalSize = 1000000;
                break;
            }
            case 30: {
                facets = FACETS_POSITIVE;
                fixedf = FIXED_FACETS_INTEGER;
                decimalSize = 1000000;
                break;
            }
            case 31: {
                facets = FACETS_UNSIGNED_LONG;
                fixedf = FIXED_FACETS_INTEGER;
                decimalSize = 1000000;
                break;
            }
            case 32: {
                facets = FACETS_UNSIGNED_INT;
                fixedf = FIXED_FACETS_INTEGER;
                decimalSize = 64;
                break;
            }
            case 33: {
                facets = FACETS_UNSIGNED_SHORT;
                fixedf = FIXED_FACETS_INTEGER;
                decimalSize = 32;
                break;
            }
            case 34: {
                facets = FACETS_UNSIGNED_BYTE;
                fixedf = FIXED_FACETS_INTEGER;
                decimalSize = 16;
                break;
            }
            case 35: {
                facets = FACETS_WS_REPLACE;
                fixedf = FIXED_FACETS_NONE;
                wsr = 2;
                break;
            }
            case 36: 
            case 37: 
            case 38: 
            case 39: 
            case 40: 
            case 41: 
            case 42: 
            case 43: 
            case 45: {
                facets = FACETS_WS_COLLAPSE;
                fixedf = FIXED_FACETS_NONE;
                wsr = 3;
                break;
            }
            case 44: 
            case 46: {
                facets = FACETS_BUILTIN_LIST;
                fixedf = FIXED_FACETS_NONE;
                wsr = 0;
                break;
            }
            case 53: 
            case 54: {
                facets = FACETS_WS_COLLAPSE;
                fixedf = FIXED_FACETS_WS;
                wsr = 3;
            }
        }
        int ordered = 0;
        boolean isNumeric = false;
        boolean isFinite = false;
        boolean isBounded = false;
        switch (btc) {
            default: {
                assert (false);
            }
            case 0: 
            case 1: 
            case 2: 
            case 4: 
            case 5: 
            case 6: 
            case 7: 
            case 8: 
            case 12: 
            case 35: 
            case 36: 
            case 37: 
            case 38: 
            case 39: 
            case 40: 
            case 41: 
            case 42: 
            case 43: 
            case 44: 
            case 45: 
            case 46: 
            case 52: {
                break;
            }
            case 3: {
                isFinite = true;
                break;
            }
            case 9: 
            case 10: 
            case 11: 
            case 22: {
                isNumeric = true;
                ordered = 2;
                break;
            }
            case 13: 
            case 14: 
            case 15: 
            case 16: 
            case 17: 
            case 18: 
            case 19: 
            case 20: 
            case 21: 
            case 53: 
            case 54: {
                ordered = 1;
                break;
            }
            case 23: 
            case 24: 
            case 25: 
            case 26: 
            case 27: 
            case 28: 
            case 29: 
            case 30: 
            case 31: 
            case 32: 
            case 33: 
            case 34: {
                isNumeric = true;
                ordered = 2;
                isFinite = true;
                isBounded = true;
            }
        }
        result.setBasicFacets(facets, fixedf);
        result.setWhiteSpaceRule(wsr);
        result.setOrdered(ordered);
        result.setBounded(isBounded);
        result.setNumeric(isNumeric);
        result.setFinite(isFinite);
        result.setDecimalSize(decimalSize);
        result.setAnonymousTypeRefs(EMPTY_SCHEMATYPEREF_ARRAY);
        String pattern = null;
        boolean hasPattern = false;
        switch (btc) {
            case 39: {
                pattern = "[a-zA-Z]{1,8}(-[a-zA-Z0-9]{1,8})*";
                hasPattern = true;
                break;
            }
            case 45: {
                pattern = "\\c+";
                hasPattern = true;
                break;
            }
            case 37: {
                pattern = "\\i\\c*";
                hasPattern = true;
                break;
            }
            case 38: {
                pattern = "[\\i-[:]][\\c-[:]]*";
                hasPattern = true;
                break;
            }
            case 53: {
                pattern = "[^YM]*[DT].*";
                hasPattern = true;
                break;
            }
            case 54: {
                pattern = "[^DT]*";
                hasPattern = true;
                break;
            }
            case 40: 
            case 41: 
            case 43: {
                hasPattern = true;
            }
        }
        if (pattern != null) {
            RegularExpression p;
            block86: {
                p = null;
                try {
                    p = SchemaRegularExpression.forPattern(pattern);
                } catch (ParseException e) {
                    if ($assertionsDisabled) break block86;
                    throw new AssertionError();
                }
            }
            result.setPatterns(new RegularExpression[]{p});
        }
        result.setPatternFacet(hasPattern);
        if (btc == 1) {
            contentModel = new SchemaParticleImpl();
            contentModel.setParticleType(5);
            contentModel.setWildcardSet(QNameSet.ALL);
            contentModel.setWildcardProcess(2);
            contentModel.setMinOccurs(BigInteger.ZERO);
            contentModel.setMaxOccurs(null);
            contentModel.setTransitionRules(QNameSet.ALL, true);
            contentModel.setTransitionNotes(QNameSet.ALL, true);
            SchemaAttributeModelImpl attrModel = new SchemaAttributeModelImpl();
            attrModel.setWildcardProcess(2);
            attrModel.setWildcardSet(QNameSet.ALL);
            result.setComplexTypeVariety(4);
            result.setContentModel(contentModel, attrModel, Collections.EMPTY_MAP, Collections.EMPTY_MAP, false);
            result.setAnonymousTypeRefs(EMPTY_SCHEMATYPEREF_ARRAY);
            result.setWildcardSummary(QNameSet.ALL, true, QNameSet.ALL, true);
        } else if (btc == 0) {
            contentModel = null;
            SchemaAttributeModelImpl attrModel = new SchemaAttributeModelImpl();
            result.setComplexTypeVariety(1);
            result.setContentModel(contentModel, attrModel, Collections.EMPTY_MAP, Collections.EMPTY_MAP, false);
            result.setAnonymousTypeRefs(EMPTY_SCHEMATYPEREF_ARRAY);
            result.setWildcardSummary(QNameSet.EMPTY, false, QNameSet.EMPTY, false);
        }
        result.setOrderSensitive(false);
    }

    public static SchemaType getNoType() {
        return ST_NO_TYPE;
    }

    static {
        int i;
        EMPTY_SCHEMATYPE_ARRAY = new SchemaType[0];
        EMPTY_SCHEMATYPEREF_ARRAY = new SchemaType.Ref[0];
        EMPTY_SCHEMAELEMENT_ARRAY = new SchemaGlobalElement[0];
        EMPTY_SCHEMAATTRIBUTE_ARRAY = new SchemaGlobalAttribute[0];
        EMPTY_SCHEMAMODELGROUP_ARRAY = new SchemaModelGroup[0];
        EMPTY_SCHEMAATTRIBUTEGROUP_ARRAY = new SchemaAttributeGroup[0];
        EMPTY_SCHEMAANNOTATION_ARRAY = new SchemaAnnotation[0];
        _global = new XQuerySchemaTypeSystem();
        ST_ANY_TYPE = _global.getBuiltinType(1);
        ST_ANY_SIMPLE = _global.getBuiltinType(2);
        ST_ANY_ATOMIC = _global.getBuiltinType(52);
        ST_BOOLEAN = _global.getBuiltinType(3);
        ST_BASE_64_BINARY = _global.getBuiltinType(4);
        ST_HEX_BINARY = _global.getBuiltinType(5);
        ST_ANY_URI = _global.getBuiltinType(6);
        ST_QNAME = _global.getBuiltinType(7);
        ST_NOTATION = _global.getBuiltinType(8);
        ST_FLOAT = _global.getBuiltinType(9);
        ST_DOUBLE = _global.getBuiltinType(10);
        ST_DECIMAL = _global.getBuiltinType(11);
        ST_STRING = _global.getBuiltinType(12);
        ST_DURATION = _global.getBuiltinType(13);
        ST_DATE_TIME = _global.getBuiltinType(14);
        ST_TIME = _global.getBuiltinType(15);
        ST_DATE = _global.getBuiltinType(16);
        ST_G_YEAR_MONTH = _global.getBuiltinType(17);
        ST_G_YEAR = _global.getBuiltinType(18);
        ST_G_MONTH_DAY = _global.getBuiltinType(19);
        ST_G_DAY = _global.getBuiltinType(20);
        ST_G_MONTH = _global.getBuiltinType(21);
        ST_INTEGER = _global.getBuiltinType(22);
        ST_LONG = _global.getBuiltinType(23);
        ST_INT = _global.getBuiltinType(24);
        ST_SHORT = _global.getBuiltinType(25);
        ST_BYTE = _global.getBuiltinType(26);
        ST_NON_POSITIVE_INTEGER = _global.getBuiltinType(27);
        ST_NEGATIVE_INTEGER = _global.getBuiltinType(28);
        ST_NON_NEGATIVE_INTEGER = _global.getBuiltinType(29);
        ST_POSITIVE_INTEGER = _global.getBuiltinType(30);
        ST_UNSIGNED_LONG = _global.getBuiltinType(31);
        ST_UNSIGNED_INT = _global.getBuiltinType(32);
        ST_UNSIGNED_SHORT = _global.getBuiltinType(33);
        ST_UNSIGNED_BYTE = _global.getBuiltinType(34);
        ST_NORMALIZED_STRING = _global.getBuiltinType(35);
        ST_TOKEN = _global.getBuiltinType(36);
        ST_NAME = _global.getBuiltinType(37);
        ST_NCNAME = _global.getBuiltinType(38);
        ST_LANGUAGE = _global.getBuiltinType(39);
        ST_ID = _global.getBuiltinType(40);
        ST_IDREF = _global.getBuiltinType(41);
        ST_IDREFS = _global.getBuiltinType(42);
        ST_ENTITY = _global.getBuiltinType(43);
        ST_ENTITIES = _global.getBuiltinType(44);
        ST_NMTOKEN = _global.getBuiltinType(45);
        ST_NMTOKENS = _global.getBuiltinType(46);
        ST_DAY_TIME_DURATION = _global.getBuiltinType(53);
        ST_YEAR_MONTH_DURATION = _global.getBuiltinType(54);
        ST_NO_TYPE = _global.getBuiltinType(0);
        XMLSTR_PRESERVE = XQuerySchemaTypeSystem.buildString("preserve");
        XMLSTR_REPLACE = XQuerySchemaTypeSystem.buildString("preserve");
        XMLSTR_COLLAPSE = XQuerySchemaTypeSystem.buildString("preserve");
        FACETS_NONE = new XmlValueRef[]{null, null, null, null, null, null, null, null, null, null, null, null};
        FIXED_FACETS_NONE = new boolean[]{false, false, false, false, false, false, false, false, false, false, false, false};
        FACETS_WS_COLLAPSE = new XmlValueRef[]{null, null, null, null, null, null, null, null, null, XQuerySchemaTypeSystem.build_wsstring(3), null, null};
        FACETS_WS_REPLACE = new XmlValueRef[]{null, null, null, null, null, null, null, null, null, XQuerySchemaTypeSystem.build_wsstring(2), null, null};
        FACETS_WS_PRESERVE = new XmlValueRef[]{null, null, null, null, null, null, null, null, null, XQuerySchemaTypeSystem.build_wsstring(1), null, null};
        FACETS_INTEGER = new XmlValueRef[]{null, null, null, null, null, null, null, null, XQuerySchemaTypeSystem.buildNnInteger(BigInteger.ZERO), XQuerySchemaTypeSystem.build_wsstring(3), null, null};
        FACETS_LONG = new XmlValueRef[]{null, null, null, null, XQuerySchemaTypeSystem.buildInteger(BigInteger.valueOf(Long.MIN_VALUE)), XQuerySchemaTypeSystem.buildInteger(BigInteger.valueOf(Long.MAX_VALUE)), null, null, XQuerySchemaTypeSystem.buildNnInteger(BigInteger.ZERO), XQuerySchemaTypeSystem.build_wsstring(3), null, null};
        FACETS_INT = new XmlValueRef[]{null, null, null, null, XQuerySchemaTypeSystem.buildInteger(BigInteger.valueOf(Integer.MIN_VALUE)), XQuerySchemaTypeSystem.buildInteger(BigInteger.valueOf(Integer.MAX_VALUE)), null, null, XQuerySchemaTypeSystem.buildNnInteger(BigInteger.ZERO), XQuerySchemaTypeSystem.build_wsstring(3), null, null};
        FACETS_SHORT = new XmlValueRef[]{null, null, null, null, XQuerySchemaTypeSystem.buildInteger(BigInteger.valueOf(-32768L)), XQuerySchemaTypeSystem.buildInteger(BigInteger.valueOf(32767L)), null, null, XQuerySchemaTypeSystem.buildNnInteger(BigInteger.ZERO), XQuerySchemaTypeSystem.build_wsstring(3), null, null};
        FACETS_BYTE = new XmlValueRef[]{null, null, null, null, XQuerySchemaTypeSystem.buildInteger(BigInteger.valueOf(-128L)), XQuerySchemaTypeSystem.buildInteger(BigInteger.valueOf(127L)), null, null, XQuerySchemaTypeSystem.buildNnInteger(BigInteger.ZERO), XQuerySchemaTypeSystem.build_wsstring(3), null, null};
        FACETS_NONNEGATIVE = new XmlValueRef[]{null, null, null, null, XQuerySchemaTypeSystem.buildInteger(BigInteger.ZERO), null, null, null, XQuerySchemaTypeSystem.buildNnInteger(BigInteger.ZERO), XQuerySchemaTypeSystem.build_wsstring(3), null, null};
        FACETS_POSITIVE = new XmlValueRef[]{null, null, null, null, XQuerySchemaTypeSystem.buildInteger(BigInteger.ONE), null, null, null, XQuerySchemaTypeSystem.buildNnInteger(BigInteger.ZERO), XQuerySchemaTypeSystem.build_wsstring(3), null, null};
        FACETS_NONPOSITIVE = new XmlValueRef[]{null, null, null, null, null, XQuerySchemaTypeSystem.buildInteger(BigInteger.ZERO), null, null, XQuerySchemaTypeSystem.buildNnInteger(BigInteger.ZERO), XQuerySchemaTypeSystem.build_wsstring(3), null, null};
        FACETS_NEGATIVE = new XmlValueRef[]{null, null, null, null, null, XQuerySchemaTypeSystem.buildInteger(BigInteger.ONE.negate()), null, null, XQuerySchemaTypeSystem.buildNnInteger(BigInteger.ZERO), XQuerySchemaTypeSystem.build_wsstring(3), null, null};
        FACETS_UNSIGNED_LONG = new XmlValueRef[]{null, null, null, null, XQuerySchemaTypeSystem.buildInteger(BigInteger.ZERO), XQuerySchemaTypeSystem.buildInteger(new BigInteger("18446744073709551615")), null, null, XQuerySchemaTypeSystem.buildNnInteger(BigInteger.ZERO), XQuerySchemaTypeSystem.build_wsstring(3), null, null};
        FACETS_UNSIGNED_INT = new XmlValueRef[]{null, null, null, null, XQuerySchemaTypeSystem.buildInteger(BigInteger.ZERO), XQuerySchemaTypeSystem.buildInteger(BigInteger.valueOf(0xFFFFFFFFL)), null, null, XQuerySchemaTypeSystem.buildNnInteger(BigInteger.ZERO), XQuerySchemaTypeSystem.build_wsstring(3), null, null};
        FACETS_UNSIGNED_SHORT = new XmlValueRef[]{null, null, null, null, XQuerySchemaTypeSystem.buildInteger(BigInteger.ZERO), XQuerySchemaTypeSystem.buildInteger(BigInteger.valueOf(65535L)), null, null, XQuerySchemaTypeSystem.buildNnInteger(BigInteger.ZERO), XQuerySchemaTypeSystem.build_wsstring(3), null, null};
        FACETS_UNSIGNED_BYTE = new XmlValueRef[]{null, null, null, null, XQuerySchemaTypeSystem.buildInteger(BigInteger.ZERO), XQuerySchemaTypeSystem.buildInteger(BigInteger.valueOf(255L)), null, null, XQuerySchemaTypeSystem.buildNnInteger(BigInteger.ZERO), XQuerySchemaTypeSystem.build_wsstring(3), null, null};
        FACETS_BUILTIN_LIST = new XmlValueRef[]{null, XQuerySchemaTypeSystem.buildNnInteger(BigInteger.ONE), null, null, null, null, null, null, null, XQuerySchemaTypeSystem.build_wsstring(3), null, null};
        FIXED_FACETS_WS = new boolean[]{false, false, false, false, false, false, false, false, false, true, false, false};
        FIXED_FACETS_INTEGER = new boolean[]{false, false, false, false, false, false, false, false, true, true, false, false};
        FACETS_UNION = FACETS_NONE;
        FIXED_FACETS_UNION = FIXED_FACETS_NONE;
        FACETS_LIST = FACETS_WS_COLLAPSE;
        FIXED_FACETS_LIST = FIXED_FACETS_WS;
        for (i = 0; i <= 46; ++i) {
            _global.fillInType(i);
        }
        for (i = 52; i <= 54; ++i) {
            _global.fillInType(i);
        }
    }
}

