/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.schema;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlAnySimpleType;
import org.apache.xmlbeans.XmlByte;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlShort;
import org.apache.xmlbeans.XmlUnsignedByte;
import org.apache.xmlbeans.impl.common.QNameHelper;
import org.apache.xmlbeans.impl.regex.ParseException;
import org.apache.xmlbeans.impl.regex.RegularExpression;
import org.apache.xmlbeans.impl.schema.BuiltinSchemaTypeSystem;
import org.apache.xmlbeans.impl.schema.SchemaTypeImpl;
import org.apache.xmlbeans.impl.schema.StscComplexTypeResolver;
import org.apache.xmlbeans.impl.schema.StscResolver;
import org.apache.xmlbeans.impl.schema.StscState;
import org.apache.xmlbeans.impl.schema.StscTranslator;
import org.apache.xmlbeans.impl.schema.XmlValueRef;
import org.apache.xmlbeans.impl.values.XmlValueOutOfRangeException;
import org.apache.xmlbeans.impl.xb.xsdschema.Facet;
import org.apache.xmlbeans.impl.xb.xsdschema.ListDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.LocalSimpleType;
import org.apache.xmlbeans.impl.xb.xsdschema.RestrictionDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.SimpleType;
import org.apache.xmlbeans.impl.xb.xsdschema.UnionDocument;

public class StscSimpleTypeResolver {
    private static final RegularExpression[] EMPTY_REGEX_ARRAY = new RegularExpression[0];
    private static CodeForNameEntry[] facetCodes = new CodeForNameEntry[]{new CodeForNameEntry(QNameHelper.forLNS("length", "http://www.w3.org/2001/XMLSchema"), 0), new CodeForNameEntry(QNameHelper.forLNS("minLength", "http://www.w3.org/2001/XMLSchema"), 1), new CodeForNameEntry(QNameHelper.forLNS("maxLength", "http://www.w3.org/2001/XMLSchema"), 2), new CodeForNameEntry(QNameHelper.forLNS("pattern", "http://www.w3.org/2001/XMLSchema"), 10), new CodeForNameEntry(QNameHelper.forLNS("enumeration", "http://www.w3.org/2001/XMLSchema"), 11), new CodeForNameEntry(QNameHelper.forLNS("whiteSpace", "http://www.w3.org/2001/XMLSchema"), 9), new CodeForNameEntry(QNameHelper.forLNS("maxInclusive", "http://www.w3.org/2001/XMLSchema"), 5), new CodeForNameEntry(QNameHelper.forLNS("maxExclusive", "http://www.w3.org/2001/XMLSchema"), 6), new CodeForNameEntry(QNameHelper.forLNS("minInclusive", "http://www.w3.org/2001/XMLSchema"), 4), new CodeForNameEntry(QNameHelper.forLNS("minExclusive", "http://www.w3.org/2001/XMLSchema"), 3), new CodeForNameEntry(QNameHelper.forLNS("totalDigits", "http://www.w3.org/2001/XMLSchema"), 7), new CodeForNameEntry(QNameHelper.forLNS("fractionDigits", "http://www.w3.org/2001/XMLSchema"), 8)};
    private static final Map facetCodeMap = StscSimpleTypeResolver.buildFacetCodeMap();

    public static void resolveSimpleType(SchemaTypeImpl sImpl) {
        SimpleType parseSt = (SimpleType)sImpl.getParseObject();
        assert (sImpl.isSimpleType());
        SchemaDocument.Schema schema = StscComplexTypeResolver.getSchema(parseSt);
        int count = (parseSt.isSetList() ? 1 : 0) + (parseSt.isSetUnion() ? 1 : 0) + (parseSt.isSetRestriction() ? 1 : 0);
        if (count > 1) {
            StscState.get().error("A simple type must define either a list, a union, or a restriction: more than one found.", 52, (XmlObject)parseSt);
        } else if (count < 1) {
            StscState.get().error("A simple type must define either a list, a union, or a restriction: none was found.", 52, (XmlObject)parseSt);
            StscSimpleTypeResolver.resolveErrorSimpleType(sImpl);
            return;
        }
        boolean finalRest = false;
        boolean finalList = false;
        boolean finalUnion = false;
        Object finalValue = null;
        if (parseSt.isSetFinal()) {
            finalValue = parseSt.getFinal();
        } else if (schema != null && schema.isSetFinalDefault()) {
            finalValue = schema.getFinalDefault();
        }
        if (finalValue != null) {
            if (finalValue instanceof String) {
                if ("#all".equals((String)finalValue)) {
                    finalUnion = true;
                    finalList = true;
                    finalRest = true;
                }
            } else if (finalValue instanceof List) {
                List lFinalValue = (List)finalValue;
                if (lFinalValue.contains("restriction")) {
                    finalRest = true;
                }
                if (lFinalValue.contains("list")) {
                    finalList = true;
                }
                if (lFinalValue.contains("union")) {
                    finalUnion = true;
                }
            }
        }
        sImpl.setSimpleFinal(finalRest, finalList, finalUnion);
        ArrayList anonTypes = new ArrayList();
        if (parseSt.getList() != null) {
            StscSimpleTypeResolver.resolveListType(sImpl, parseSt.getList(), anonTypes);
        } else if (parseSt.getUnion() != null) {
            StscSimpleTypeResolver.resolveUnionType(sImpl, parseSt.getUnion(), anonTypes);
        } else if (parseSt.getRestriction() != null) {
            StscSimpleTypeResolver.resolveSimpleRestrictionType(sImpl, parseSt.getRestriction(), anonTypes);
        }
        sImpl.setAnonymousTypeRefs(StscSimpleTypeResolver.makeRefArray(anonTypes));
    }

    private static SchemaType.Ref[] makeRefArray(Collection typeList) {
        SchemaType.Ref[] result = new SchemaType.Ref[typeList.size()];
        int j = 0;
        Iterator i = typeList.iterator();
        while (i.hasNext()) {
            result[j] = ((SchemaType)i.next()).getRef();
            ++j;
        }
        return result;
    }

    static void resolveErrorSimpleType(SchemaTypeImpl sImpl) {
        sImpl.setSimpleTypeVariety(1);
        sImpl.setBaseTypeRef(BuiltinSchemaTypeSystem.ST_ANY_SIMPLE.getRef());
        sImpl.setBaseDepth(BuiltinSchemaTypeSystem.ST_ANY_SIMPLE.getBaseDepth() + 1);
        sImpl.setPrimitiveTypeRef(BuiltinSchemaTypeSystem.ST_ANY_SIMPLE.getRef());
    }

    static void resolveListType(SchemaTypeImpl sImpl, ListDocument.List parseList, List anonTypes) {
        XmlObject errorLoc;
        SchemaTypeImpl itemImpl;
        StscState state = StscState.get();
        sImpl.setSimpleTypeVariety(3);
        sImpl.setBaseTypeRef(BuiltinSchemaTypeSystem.ST_ANY_SIMPLE.getRef());
        sImpl.setBaseDepth(BuiltinSchemaTypeSystem.ST_ANY_SIMPLE.getBaseDepth() + 1);
        sImpl.setDerivationType(1);
        if (sImpl.isRedefinition()) {
            state.error("src-redefine.5a", new Object[]{"list"}, (XmlObject)parseList);
        }
        QName itemName = parseList.getItemType();
        LocalSimpleType parseInner = parseList.getSimpleType();
        if (itemName != null && parseInner != null) {
            state.error("src-simple-type.3a", null, (XmlObject)parseList);
            parseInner = null;
        }
        if (itemName != null) {
            itemImpl = state.findGlobalType(itemName, sImpl.getChameleonNamespace(), sImpl.getTargetNamespace());
            errorLoc = parseList.xgetItemType();
            if (itemImpl == null) {
                state.notFoundError(itemName, 0, parseList.xgetItemType(), true);
                itemImpl = BuiltinSchemaTypeSystem.ST_ANY_SIMPLE;
            }
        } else if (parseInner != null) {
            itemImpl = StscTranslator.translateAnonymousSimpleType(parseInner, sImpl.getTargetNamespace(), sImpl.getChameleonNamespace() != null, sImpl.getElemFormDefault(), sImpl.getAttFormDefault(), anonTypes, sImpl);
            errorLoc = parseInner;
        } else {
            state.error("src-simple-type.3b", null, (XmlObject)parseList);
            StscSimpleTypeResolver.resolveErrorSimpleType(sImpl);
            return;
        }
        if (itemImpl.finalList()) {
            state.error("st-props-correct.4.2.1", null, (XmlObject)parseList);
        }
        StscResolver.resolveType(itemImpl);
        if (!itemImpl.isSimpleType()) {
            state.error("cos-st-restricts.2.1a", null, errorLoc);
            sImpl = BuiltinSchemaTypeSystem.ST_ANY_SIMPLE;
        }
        switch (itemImpl.getSimpleVariety()) {
            case 3: {
                state.error("cos-st-restricts.2.1b", null, errorLoc);
                StscSimpleTypeResolver.resolveErrorSimpleType(sImpl);
                return;
            }
            case 2: {
                if (itemImpl.isUnionOfLists()) {
                    state.error("cos-st-restricts.2.1c", null, errorLoc);
                    StscSimpleTypeResolver.resolveErrorSimpleType(sImpl);
                    return;
                }
            }
            case 1: {
                sImpl.setListItemTypeRef(itemImpl.getRef());
                if (sImpl.getBuiltinTypeCode() != 8) break;
                state.recover("enumeration-required-notation", null, errorLoc);
                break;
            }
            default: {
                assert (false);
                sImpl.setListItemTypeRef(BuiltinSchemaTypeSystem.ST_ANY_SIMPLE.getRef());
            }
        }
        sImpl.setBasicFacets(StscState.FACETS_LIST, StscState.FIXED_FACETS_LIST);
        sImpl.setWhiteSpaceRule(3);
        StscSimpleTypeResolver.resolveFundamentalFacets(sImpl);
    }

    static void resolveUnionType(SchemaTypeImpl sImpl, UnionDocument.Union parseUnion, List anonTypes) {
        SchemaTypeImpl mImpl;
        SchemaTypeImpl mImpl2;
        sImpl.setSimpleTypeVariety(2);
        sImpl.setBaseTypeRef(BuiltinSchemaTypeSystem.ST_ANY_SIMPLE.getRef());
        sImpl.setBaseDepth(BuiltinSchemaTypeSystem.ST_ANY_SIMPLE.getBaseDepth() + 1);
        sImpl.setDerivationType(1);
        StscState state = StscState.get();
        if (sImpl.isRedefinition()) {
            state.error("src-redefine.5a", new Object[]{"union"}, (XmlObject)parseUnion);
        }
        List memberTypes = parseUnion.getMemberTypes();
        LocalSimpleType[] simpleTypes = parseUnion.getSimpleTypeArray();
        ArrayList<SchemaTypeImpl> memberImplList = new ArrayList<SchemaTypeImpl>();
        if (simpleTypes.length == 0 && (memberTypes == null || memberTypes.size() == 0)) {
            state.error("src-union-memberTypes-or-simpleTypes", null, (XmlObject)parseUnion);
        }
        if (memberTypes != null) {
            for (QName mName : memberTypes) {
                SchemaTypeImpl memberImpl = state.findGlobalType(mName, sImpl.getChameleonNamespace(), sImpl.getTargetNamespace());
                if (memberImpl == null) {
                    state.notFoundError(mName, 0, parseUnion.xgetMemberTypes(), true);
                    continue;
                }
                memberImplList.add(memberImpl);
            }
        }
        for (int i = 0; i < simpleTypes.length; ++i) {
            mImpl2 = StscTranslator.translateAnonymousSimpleType(simpleTypes[i], sImpl.getTargetNamespace(), sImpl.getChameleonNamespace() != null, sImpl.getElemFormDefault(), sImpl.getAttFormDefault(), anonTypes, sImpl);
            memberImplList.add(mImpl2);
            mImpl2.setAnonymousUnionMemberOrdinal(i + 1);
        }
        Iterator mImpls = memberImplList.iterator();
        while (mImpls.hasNext()) {
            XmlObject errorLoc;
            mImpl2 = (SchemaTypeImpl)mImpls.next();
            if (StscResolver.resolveType(mImpl2)) continue;
            String memberName = "";
            if (mImpl2.getOuterType().equals(sImpl)) {
                errorLoc = mImpl2.getParseObject();
            } else {
                memberName = QNameHelper.pretty(mImpl2.getName()) + " ";
                errorLoc = parseUnion.xgetMemberTypes();
            }
            state.error("src-simple-type.4", new Object[]{memberName}, errorLoc);
            mImpls.remove();
        }
        boolean isUnionOfLists = false;
        Iterator mImpls2 = memberImplList.iterator();
        while (mImpls2.hasNext()) {
            mImpl = (SchemaTypeImpl)mImpls2.next();
            if (!mImpl.isSimpleType()) {
                XmlObject errorLoc;
                String memberName = "";
                if (mImpl.getOuterType() != null && mImpl.getOuterType().equals(sImpl)) {
                    errorLoc = mImpl.getParseObject();
                } else {
                    memberName = QNameHelper.pretty(mImpl.getName()) + " ";
                    errorLoc = parseUnion.xgetMemberTypes();
                }
                state.error("cos-st-restricts.3.1", new Object[]{memberName}, errorLoc);
                mImpls2.remove();
                continue;
            }
            if (mImpl.getSimpleVariety() != 3 && (mImpl.getSimpleVariety() != 2 || !mImpl.isUnionOfLists())) continue;
            isUnionOfLists = true;
        }
        for (int i = 0; i < memberImplList.size(); ++i) {
            mImpl = (SchemaTypeImpl)memberImplList.get(i);
            if (!mImpl.finalUnion()) continue;
            state.error("st-props-correct.4.2.2", null, (XmlObject)parseUnion);
        }
        sImpl.setUnionOfLists(isUnionOfLists);
        sImpl.setUnionMemberTypeRefs(StscSimpleTypeResolver.makeRefArray(memberImplList));
        sImpl.setBasicFacets(StscState.FACETS_UNION, StscState.FIXED_FACETS_UNION);
        StscSimpleTypeResolver.resolveFundamentalFacets(sImpl);
    }

    static void resolveSimpleRestrictionType(SchemaTypeImpl sImpl, RestrictionDocument.Restriction parseRestr, List anonTypes) {
        SchemaTypeImpl baseImpl;
        QName baseName = parseRestr.getBase();
        LocalSimpleType parseInner = parseRestr.getSimpleType();
        StscState state = StscState.get();
        if (baseName != null && parseInner != null) {
            state.error("src-simple-type.2a", null, (XmlObject)parseRestr);
            parseInner = null;
        }
        if (baseName != null) {
            if (sImpl.isRedefinition()) {
                baseImpl = state.findRedefinedGlobalType(parseRestr.getBase(), sImpl.getChameleonNamespace(), sImpl);
                if (baseImpl != null && !baseImpl.getName().equals(sImpl.getName())) {
                    state.error("src-redefine.5b", new Object[]{"<simpleType>", QNameHelper.pretty(baseName), QNameHelper.pretty(sImpl.getName())}, (XmlObject)parseRestr);
                }
            } else {
                baseImpl = state.findGlobalType(baseName, sImpl.getChameleonNamespace(), sImpl.getTargetNamespace());
            }
            if (baseImpl == null) {
                state.notFoundError(baseName, 0, parseRestr.xgetBase(), true);
                baseImpl = BuiltinSchemaTypeSystem.ST_ANY_SIMPLE;
            }
        } else if (parseInner != null) {
            if (sImpl.isRedefinition()) {
                StscState.get().error("src-redefine.5a", new Object[]{"<simpleType>"}, (XmlObject)parseInner);
            }
            baseImpl = StscTranslator.translateAnonymousSimpleType(parseInner, sImpl.getTargetNamespace(), sImpl.getChameleonNamespace() != null, sImpl.getElemFormDefault(), sImpl.getAttFormDefault(), anonTypes, sImpl);
        } else {
            state.error("src-simple-type.2b", null, (XmlObject)parseRestr);
            baseImpl = BuiltinSchemaTypeSystem.ST_ANY_SIMPLE;
        }
        if (!StscResolver.resolveType(baseImpl)) {
            baseImpl = BuiltinSchemaTypeSystem.ST_ANY_SIMPLE;
        }
        if (baseImpl.finalRestriction()) {
            state.error("st-props-correct.3", null, (XmlObject)parseRestr);
        }
        sImpl.setBaseTypeRef(baseImpl.getRef());
        sImpl.setBaseDepth(baseImpl.getBaseDepth() + 1);
        sImpl.setDerivationType(1);
        if (!baseImpl.isSimpleType()) {
            state.error("cos-st-restricts.1.1", null, (XmlObject)parseRestr.xgetBase());
            StscSimpleTypeResolver.resolveErrorSimpleType(sImpl);
            return;
        }
        sImpl.setSimpleTypeVariety(baseImpl.getSimpleVariety());
        switch (baseImpl.getSimpleVariety()) {
            case 1: {
                sImpl.setPrimitiveTypeRef(baseImpl.getPrimitiveType().getRef());
                break;
            }
            case 2: {
                sImpl.setUnionOfLists(baseImpl.isUnionOfLists());
                sImpl.setUnionMemberTypeRefs(StscSimpleTypeResolver.makeRefArray(Arrays.asList(baseImpl.getUnionMemberTypes())));
                break;
            }
            case 3: {
                sImpl.setListItemTypeRef(baseImpl.getListItemType().getRef());
            }
        }
        StscSimpleTypeResolver.resolveFacets(sImpl, parseRestr, baseImpl);
        StscSimpleTypeResolver.resolveFundamentalFacets(sImpl);
    }

    static int translateWhitespaceCode(XmlAnySimpleType value) {
        String textval = value.getStringValue();
        if (textval.equals("collapse")) {
            return 3;
        }
        if (textval.equals("preserve")) {
            return 1;
        }
        if (textval.equals("replace")) {
            return 2;
        }
        StscState.get().error("Unrecognized whitespace value \"" + textval + "\"", 20, (XmlObject)value);
        return 0;
    }

    static boolean isMultipleFacet(int facetcode) {
        return facetcode == 11 || facetcode == 10;
    }

    static boolean facetAppliesToType(int facetCode, SchemaTypeImpl baseImpl) {
        switch (baseImpl.getSimpleVariety()) {
            case 3: {
                switch (facetCode) {
                    case 0: 
                    case 1: 
                    case 2: 
                    case 9: 
                    case 10: 
                    case 11: {
                        return true;
                    }
                }
                return false;
            }
            case 2: {
                switch (facetCode) {
                    case 10: 
                    case 11: {
                        return true;
                    }
                }
                return false;
            }
        }
        switch (baseImpl.getPrimitiveType().getBuiltinTypeCode()) {
            case 2: {
                return false;
            }
            case 3: {
                switch (facetCode) {
                    case 9: 
                    case 10: {
                        return true;
                    }
                }
                return false;
            }
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
                switch (facetCode) {
                    case 3: 
                    case 4: 
                    case 5: 
                    case 6: 
                    case 9: 
                    case 10: 
                    case 11: {
                        return true;
                    }
                }
                return false;
            }
            case 11: {
                switch (facetCode) {
                    case 3: 
                    case 4: 
                    case 5: 
                    case 6: 
                    case 7: 
                    case 8: 
                    case 9: 
                    case 10: 
                    case 11: {
                        return true;
                    }
                }
                return false;
            }
            case 4: 
            case 5: 
            case 6: 
            case 7: 
            case 8: 
            case 12: {
                switch (facetCode) {
                    case 0: 
                    case 1: 
                    case 2: 
                    case 9: 
                    case 10: 
                    case 11: {
                        return true;
                    }
                }
                return false;
            }
        }
        assert (false);
        return false;
    }

    private static int other_similar_limit(int facetcode) {
        switch (facetcode) {
            case 3: {
                return 4;
            }
            case 4: {
                return 3;
            }
            case 5: {
                return 6;
            }
            case 6: {
                return 5;
            }
        }
        assert (false);
        throw new IllegalStateException();
    }

    /*
     * Unable to fully structure code
     */
    static void resolveFacets(SchemaTypeImpl sImpl, XmlObject restriction, SchemaTypeImpl baseImpl) {
        block58: {
            state = StscState.get();
            seenFacet = new boolean[12];
            myFacets = baseImpl.getBasicFacets();
            fixedFacets = baseImpl.getFixedFacets();
            wsr = 0;
            enumeratedValues = null;
            patterns = null;
            if (restriction == null) break block58;
            cur = restriction.newCursor();
            more = cur.toFirstChild();
            while (more) {
                block59: {
                    block61: {
                        block60: {
                            facetQName = cur.getName();
                            facetName = facetQName.getLocalPart();
                            code = StscSimpleTypeResolver.translateFacetCode(facetQName);
                            if (code == -1) break block59;
                            facet = (Facet)cur.getObject();
                            if (StscSimpleTypeResolver.facetAppliesToType(code, baseImpl)) break block60;
                            state.error("cos-applicable-facets", new Object[]{facetName, QNameHelper.pretty(baseImpl.getName())}, (XmlObject)facet);
                            break block59;
                        }
                        if (baseImpl.getSimpleVariety() == 1 && baseImpl.getPrimitiveType().getBuiltinTypeCode() == 8 && (code == 0 || code == 1 || code == 2)) {
                            state.warning("notation-facets", new Object[]{facetName, QNameHelper.pretty(baseImpl.getName())}, (XmlObject)facet);
                        }
                        if (!seenFacet[code] || StscSimpleTypeResolver.isMultipleFacet(code)) break block61;
                        state.error("src-single-facet-value", null, (XmlObject)facet);
                        break block59;
                    }
                    seenFacet[code] = true;
                    switch (code) {
                        case 0: {
                            len = StscTranslator.buildNnInteger(facet.getValue());
                            if (len == null) {
                                state.error("Must be a nonnegative integer", 20, (XmlObject)facet);
                                break;
                            }
                            if (fixedFacets[code] && !myFacets[code].valueEquals(len)) {
                                state.error("facet-fixed", new Object[]{facetName}, (XmlObject)facet);
                                break;
                            }
                            if (!(myFacets[1] == null || (baseMinLength = baseImpl.getFacet(1)) != null && baseMinLength.valueEquals(myFacets[1]) && baseMinLength.compareValue(len) <= 0)) {
                                state.error("length-minLength-maxLength", null, (XmlObject)facet);
                                break;
                            }
                            if (!(myFacets[2] == null || (baseMaxLength = baseImpl.getFacet(2)) != null && baseMaxLength.valueEquals(myFacets[2]) && baseMaxLength.compareValue(len) >= 0)) {
                                state.error("length-minLength-maxLength", null, (XmlObject)facet);
                                break;
                            }
                            myFacets[code] = len;
                            ** GOTO lbl158
                        }
                        case 1: 
                        case 2: {
                            mlen = StscTranslator.buildNnInteger(facet.getValue());
                            if (mlen == null) {
                                state.error("Must be a nonnegative integer", 20, (XmlObject)facet);
                                break;
                            }
                            if (fixedFacets[code] && !myFacets[code].valueEquals(mlen)) {
                                state.error("facet-fixed", new Object[]{facetName}, (XmlObject)facet);
                                break;
                            }
                            if (!(myFacets[0] == null || (baseMinMaxLength = baseImpl.getFacet(code)) != null && baseMinMaxLength.valueEquals(mlen) && (code != 1 ? baseMinMaxLength.compareTo(myFacets[0]) >= 0 : baseMinMaxLength.compareTo(myFacets[0]) <= 0))) {
                                state.error("length-minLength-maxLength", null, (XmlObject)facet);
                                break;
                            }
                            if (myFacets[2] != null && mlen.compareValue(myFacets[2]) > 0) {
                                state.error("maxLength-valid-restriction", null, (XmlObject)facet);
                                break;
                            }
                            if (myFacets[1] != null && mlen.compareValue(myFacets[1]) < 0) {
                                state.error("minLength-valid-restriction", null, (XmlObject)facet);
                                break;
                            }
                            myFacets[code] = mlen;
                            ** GOTO lbl158
                        }
                        case 7: {
                            dig = StscTranslator.buildPosInteger(facet.getValue());
                            if (dig == null) {
                                state.error("Must be a positive integer", 20, (XmlObject)facet);
                            } else {
                                if (fixedFacets[code] && !myFacets[code].valueEquals(dig)) {
                                    state.error("facet-fixed", new Object[]{facetName}, (XmlObject)facet);
                                    break;
                                }
                                if (myFacets[7] != null && dig.compareValue(myFacets[7]) > 0) {
                                    state.error("totalDigits-valid-restriction", null, (XmlObject)facet);
                                }
                                myFacets[code] = dig;
                            }
                            ** GOTO lbl158
                        }
                        case 8: {
                            fdig = StscTranslator.buildNnInteger(facet.getValue());
                            if (fdig == null) {
                                state.error("Must be a nonnegative integer", 20, (XmlObject)facet);
                            } else {
                                if (fixedFacets[code] && !myFacets[code].valueEquals(fdig)) {
                                    state.error("facet-fixed", new Object[]{facetName}, (XmlObject)facet);
                                    break;
                                }
                                if (myFacets[8] != null && fdig.compareValue(myFacets[8]) > 0) {
                                    state.error("fractionDigits-valid-restriction", null, (XmlObject)facet);
                                }
                                if (myFacets[7] != null && fdig.compareValue(myFacets[7]) > 0) {
                                    state.error("fractionDigits-totalDigits", null, (XmlObject)facet);
                                }
                                myFacets[code] = fdig;
                            }
                            ** GOTO lbl158
                        }
                        case 3: 
                        case 4: 
                        case 5: 
                        case 6: {
                            if (seenFacet[StscSimpleTypeResolver.other_similar_limit(code)]) {
                                state.error("Cannot define both inclusive and exclusive limit in the same restriciton", 19, (XmlObject)facet);
                                break;
                            }
                            ismin = code == 3 || code == 4;
                            isexclusive = code == 3 || code == 6;
                            try {
                                limit = baseImpl.newValue(facet.getValue(), true);
                            } catch (XmlValueOutOfRangeException e) {
                                switch (code) {
                                    case 3: {
                                        state.error("minExclusive-valid-restriction", new Object[]{e.getMessage()}, (XmlObject)facet);
                                        break;
                                    }
                                    case 4: {
                                        state.error("minInclusive-valid-restriction", new Object[]{e.getMessage()}, (XmlObject)facet);
                                        break;
                                    }
                                    case 5: {
                                        state.error("maxInclusive-valid-restriction", new Object[]{e.getMessage()}, (XmlObject)facet);
                                        break;
                                    }
                                    case 6: {
                                        state.error("maxExclusive-valid-restriction", new Object[]{e.getMessage()}, (XmlObject)facet);
                                    }
                                }
                                break;
                            }
                            if (fixedFacets[code] && !myFacets[code].valueEquals(limit)) {
                                state.error("facet-fixed", new Object[]{facetName}, (XmlObject)facet);
                                break;
                            }
                            if (myFacets[code] != null) {
                                limitSType = limit.schemaType();
                                if (limitSType != null && !limitSType.isSimpleType() && limitSType.getContentType() == 2) {
                                    limit = baseImpl.getContentBasedOnType().newValue(facet.getValue());
                                }
                                if ((comparison = limit.compareValue(myFacets[code])) == 2 || comparison == (ismin != false ? -1 : 1)) {
                                    state.error(ismin ? (isexclusive ? "Must be greater than or equal to previous minExclusive" : "Must be greater than or equal to previous minInclusive") : (isexclusive != false ? "Must be less than or equal to previous maxExclusive" : "Must be less than or equal to previous maxInclusive"), 20, (XmlObject)facet);
                                    break;
                                }
                            }
                            myFacets[code] = limit;
                            myFacets[StscSimpleTypeResolver.other_similar_limit((int)code)] = null;
                            ** GOTO lbl158
                        }
                        case 9: {
                            wsr = StscSimpleTypeResolver.translateWhitespaceCode(facet.getValue());
                            if (baseImpl.getWhiteSpaceRule() > wsr) {
                                wsr = 0;
                                state.error("whiteSpace-valid-restriction", null, (XmlObject)facet);
                                break;
                            }
                            myFacets[code] = StscState.build_wsstring(wsr).get();
                            ** GOTO lbl158
                        }
                        case 11: {
                            try {
                                enumval = baseImpl.newValue(facet.getValue(), true);
                            } catch (XmlValueOutOfRangeException e) {
                                state.error("enumeration-valid-restriction", new Object[]{facet.getValue().getStringValue(), e.getMessage()}, (XmlObject)facet);
                                break;
                            }
                            if (enumeratedValues == null) {
                                enumeratedValues = new ArrayList<XmlAnySimpleType>();
                            }
                            enumeratedValues.add(enumval);
                            ** GOTO lbl158
                        }
                        case 10: {
                            try {
                                p = new RegularExpression(facet.getValue().getStringValue(), "X");
                            } catch (ParseException e) {
                                state.error("pattern-regex", new Object[]{facet.getValue().getStringValue(), e.getMessage()}, (XmlObject)facet);
                                break;
                            }
                            if (patterns == null) {
                                patterns = new ArrayList<RegularExpression>();
                            }
                            patterns.add(p);
                        }
lbl158:
                        // 11 sources

                        default: {
                            if (!facet.getFixed()) break;
                            fixedFacets[code] = true;
                        }
                    }
                }
                more = cur.toNextSibling();
            }
        }
        sImpl.setBasicFacets(StscSimpleTypeResolver.makeValueRefArray(myFacets), fixedFacets);
        if (wsr == 0) {
            wsr = baseImpl.getWhiteSpaceRule();
        }
        sImpl.setWhiteSpaceRule(wsr);
        if (enumeratedValues != null) {
            sImpl.setEnumerationValues(StscSimpleTypeResolver.makeValueRefArray(enumeratedValues.toArray(new XmlAnySimpleType[enumeratedValues.size()])));
            beType = sImpl;
            if (sImpl.isRedefinition()) {
                beType = sImpl.getBaseType().getBaseEnumType();
                if (beType == null || sImpl.getBaseType() == beType) {
                    beType = sImpl;
                }
            } else if (sImpl.getBaseType().getBaseEnumType() != null) {
                beType = sImpl.getBaseType().getBaseEnumType();
            }
            sImpl.setBaseEnumTypeRef(beType.getRef());
        } else {
            sImpl.copyEnumerationValues(baseImpl);
        }
        patternArray = patterns != null ? patterns.toArray(StscSimpleTypeResolver.EMPTY_REGEX_ARRAY) : StscSimpleTypeResolver.EMPTY_REGEX_ARRAY;
        sImpl.setPatternFacet(patternArray.length > 0 || baseImpl.hasPatternFacet() != false);
        sImpl.setPatterns(patternArray);
        if (baseImpl.getBuiltinTypeCode() == 8 && sImpl.getEnumerationValues() == null) {
            state.recover("enumeration-required-notation", null, restriction);
        }
    }

    private static XmlValueRef[] makeValueRefArray(XmlAnySimpleType[] source) {
        XmlValueRef[] result = new XmlValueRef[source.length];
        for (int i = 0; i < result.length; ++i) {
            result[i] = source[i] == null ? null : new XmlValueRef(source[i]);
        }
        return result;
    }

    private static boolean isDiscreteType(SchemaTypeImpl sImpl) {
        if (sImpl.getFacet(8) != null) {
            return true;
        }
        switch (sImpl.getPrimitiveType().getBuiltinTypeCode()) {
            case 3: 
            case 16: 
            case 17: 
            case 18: 
            case 19: 
            case 20: 
            case 21: {
                return true;
            }
        }
        return false;
    }

    private static boolean isNumericPrimitive(SchemaType sImpl) {
        switch (sImpl.getBuiltinTypeCode()) {
            case 9: 
            case 10: 
            case 11: {
                return true;
            }
        }
        return false;
    }

    private static int decimalSizeOfType(SchemaTypeImpl sImpl) {
        int size = StscSimpleTypeResolver.mathematicalSizeOfType(sImpl);
        if (size == 8 && !XmlByte.type.isAssignableFrom(sImpl)) {
            size = 16;
        }
        if (size == 16 && !XmlShort.type.isAssignableFrom(sImpl) && !XmlUnsignedByte.type.isAssignableFrom(sImpl)) {
            size = 32;
        }
        return size;
    }

    private static int mathematicalSizeOfType(SchemaTypeImpl sImpl) {
        if (sImpl.getPrimitiveType().getBuiltinTypeCode() != 11) {
            return 0;
        }
        if (sImpl.getFacet(8) == null || ((SimpleValue)((Object)sImpl.getFacet(8))).getBigIntegerValue().signum() != 0) {
            return 1000001;
        }
        BigInteger min = null;
        BigInteger max = null;
        if (sImpl.getFacet(3) != null) {
            min = ((SimpleValue)((Object)sImpl.getFacet(3))).getBigIntegerValue();
        }
        if (sImpl.getFacet(4) != null) {
            min = ((SimpleValue)((Object)sImpl.getFacet(4))).getBigIntegerValue();
        }
        if (sImpl.getFacet(5) != null) {
            max = ((SimpleValue)((Object)sImpl.getFacet(5))).getBigIntegerValue();
        }
        if (sImpl.getFacet(6) != null) {
            max = ((SimpleValue)((Object)sImpl.getFacet(6))).getBigIntegerValue();
        }
        if (sImpl.getFacet(7) != null) {
            BigInteger peg = null;
            try {
                BigInteger totalDigits = ((SimpleValue)((Object)sImpl.getFacet(7))).getBigIntegerValue();
                switch (totalDigits.intValue()) {
                    case 0: 
                    case 1: 
                    case 2: {
                        peg = BigInteger.valueOf(99L);
                        break;
                    }
                    case 3: 
                    case 4: {
                        peg = BigInteger.valueOf(9999L);
                        break;
                    }
                    case 5: 
                    case 6: 
                    case 7: 
                    case 8: 
                    case 9: {
                        peg = BigInteger.valueOf(999999999L);
                        break;
                    }
                    case 10: 
                    case 11: 
                    case 12: 
                    case 13: 
                    case 14: 
                    case 15: 
                    case 16: 
                    case 17: 
                    case 18: {
                        peg = BigInteger.valueOf(999999999999999999L);
                    }
                }
            } catch (XmlValueOutOfRangeException e) {
                // empty catch block
            }
            if (peg != null) {
                min = min == null ? peg.negate() : min.max(peg.negate());
                BigInteger bigInteger = max = max == null ? peg : max.min(peg);
            }
        }
        if (min != null && max != null) {
            if (min.signum() < 0) {
                min = min.negate().subtract(BigInteger.ONE);
            }
            if (max.signum() < 0) {
                max = max.negate().subtract(BigInteger.ONE);
            }
            if ((max = max.max(min)).compareTo(BigInteger.valueOf(127L)) <= 0) {
                return 8;
            }
            if (max.compareTo(BigInteger.valueOf(32767L)) <= 0) {
                return 16;
            }
            if (max.compareTo(BigInteger.valueOf(Integer.MAX_VALUE)) <= 0) {
                return 32;
            }
            if (max.compareTo(BigInteger.valueOf(Long.MAX_VALUE)) <= 0) {
                return 64;
            }
        }
        return 1000000;
    }

    static void resolveFundamentalFacets(SchemaTypeImpl sImpl) {
        switch (sImpl.getSimpleVariety()) {
            case 1: {
                SchemaTypeImpl baseImpl = (SchemaTypeImpl)sImpl.getBaseType();
                sImpl.setOrdered(baseImpl.ordered());
                sImpl.setBounded(!(sImpl.getFacet(3) == null && sImpl.getFacet(4) == null || sImpl.getFacet(5) == null && sImpl.getFacet(6) == null));
                sImpl.setFinite(baseImpl.isFinite() || sImpl.isBounded() && StscSimpleTypeResolver.isDiscreteType(sImpl));
                sImpl.setNumeric(baseImpl.isNumeric() || StscSimpleTypeResolver.isNumericPrimitive(sImpl.getPrimitiveType()));
                sImpl.setDecimalSize(StscSimpleTypeResolver.decimalSizeOfType(sImpl));
                break;
            }
            case 2: {
                SchemaType[] mTypes = sImpl.getUnionMemberTypes();
                int ordered = 0;
                boolean isBounded = true;
                boolean isFinite = true;
                boolean isNumeric = true;
                for (int i = 0; i < mTypes.length; ++i) {
                    if (mTypes[i].ordered() != 0) {
                        ordered = 1;
                    }
                    if (!mTypes[i].isBounded()) {
                        isBounded = false;
                    }
                    if (!mTypes[i].isFinite()) {
                        isFinite = false;
                    }
                    if (mTypes[i].isNumeric()) continue;
                    isNumeric = false;
                }
                sImpl.setOrdered(ordered);
                sImpl.setBounded(isBounded);
                sImpl.setFinite(isFinite);
                sImpl.setNumeric(isNumeric);
                sImpl.setDecimalSize(0);
                break;
            }
            case 3: {
                sImpl.setOrdered(0);
                sImpl.setBounded(sImpl.getFacet(0) != null || sImpl.getFacet(2) != null);
                sImpl.setFinite(sImpl.getListItemType().isFinite() && sImpl.isBounded());
                sImpl.setNumeric(false);
                sImpl.setDecimalSize(0);
            }
        }
    }

    private static Map buildFacetCodeMap() {
        HashMap<QName, Integer> result = new HashMap<QName, Integer>();
        for (int i = 0; i < facetCodes.length; ++i) {
            result.put(StscSimpleTypeResolver.facetCodes[i].name, new Integer(StscSimpleTypeResolver.facetCodes[i].code));
        }
        return result;
    }

    private static int translateFacetCode(QName name) {
        Integer result = (Integer)facetCodeMap.get(name);
        if (result == null) {
            return -1;
        }
        return result;
    }

    private static class CodeForNameEntry {
        public QName name;
        public int code;

        CodeForNameEntry(QName name, int code) {
            this.name = name;
            this.code = code;
        }
    }
}

