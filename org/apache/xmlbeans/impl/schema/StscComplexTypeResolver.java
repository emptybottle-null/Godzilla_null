/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.schema;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.QNameSet;
import org.apache.xmlbeans.QNameSetBuilder;
import org.apache.xmlbeans.QNameSetSpecification;
import org.apache.xmlbeans.SchemaAttributeModel;
import org.apache.xmlbeans.SchemaField;
import org.apache.xmlbeans.SchemaLocalAttribute;
import org.apache.xmlbeans.SchemaLocalElement;
import org.apache.xmlbeans.SchemaParticle;
import org.apache.xmlbeans.SchemaProperty;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlInteger;
import org.apache.xmlbeans.XmlNonNegativeInteger;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.common.QNameHelper;
import org.apache.xmlbeans.impl.schema.BuiltinSchemaTypeSystem;
import org.apache.xmlbeans.impl.schema.SchemaAttributeGroupImpl;
import org.apache.xmlbeans.impl.schema.SchemaAttributeModelImpl;
import org.apache.xmlbeans.impl.schema.SchemaLocalAttributeImpl;
import org.apache.xmlbeans.impl.schema.SchemaLocalElementImpl;
import org.apache.xmlbeans.impl.schema.SchemaModelGroupImpl;
import org.apache.xmlbeans.impl.schema.SchemaParticleImpl;
import org.apache.xmlbeans.impl.schema.SchemaPropertyImpl;
import org.apache.xmlbeans.impl.schema.SchemaTypeImpl;
import org.apache.xmlbeans.impl.schema.StscResolver;
import org.apache.xmlbeans.impl.schema.StscSimpleTypeResolver;
import org.apache.xmlbeans.impl.schema.StscState;
import org.apache.xmlbeans.impl.schema.StscTranslator;
import org.apache.xmlbeans.impl.xb.xsdschema.AllNNI;
import org.apache.xmlbeans.impl.xb.xsdschema.AnyDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.Attribute;
import org.apache.xmlbeans.impl.xb.xsdschema.AttributeGroupRef;
import org.apache.xmlbeans.impl.xb.xsdschema.ComplexContentDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.ComplexRestrictionType;
import org.apache.xmlbeans.impl.xb.xsdschema.ComplexType;
import org.apache.xmlbeans.impl.xb.xsdschema.ExtensionType;
import org.apache.xmlbeans.impl.xb.xsdschema.Group;
import org.apache.xmlbeans.impl.xb.xsdschema.LocalElement;
import org.apache.xmlbeans.impl.xb.xsdschema.LocalSimpleType;
import org.apache.xmlbeans.impl.xb.xsdschema.NamespaceList;
import org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.SimpleContentDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.SimpleExtensionType;
import org.apache.xmlbeans.impl.xb.xsdschema.SimpleRestrictionType;
import org.apache.xmlbeans.impl.xb.xsdschema.Wildcard;

public class StscComplexTypeResolver {
    private static final int MODEL_GROUP_CODE = 100;
    private static CodeForNameEntry[] particleCodes = new CodeForNameEntry[]{new CodeForNameEntry(QNameHelper.forLNS("all", "http://www.w3.org/2001/XMLSchema"), 1), new CodeForNameEntry(QNameHelper.forLNS("sequence", "http://www.w3.org/2001/XMLSchema"), 3), new CodeForNameEntry(QNameHelper.forLNS("choice", "http://www.w3.org/2001/XMLSchema"), 2), new CodeForNameEntry(QNameHelper.forLNS("element", "http://www.w3.org/2001/XMLSchema"), 4), new CodeForNameEntry(QNameHelper.forLNS("any", "http://www.w3.org/2001/XMLSchema"), 5), new CodeForNameEntry(QNameHelper.forLNS("group", "http://www.w3.org/2001/XMLSchema"), 100)};
    private static Map particleCodeMap = StscComplexTypeResolver.buildParticleCodeMap();
    private static final int ATTRIBUTE_CODE = 100;
    private static final int ATTRIBUTE_GROUP_CODE = 101;
    private static final int ANY_ATTRIBUTE_CODE = 102;
    private static CodeForNameEntry[] attributeCodes = new CodeForNameEntry[]{new CodeForNameEntry(QNameHelper.forLNS("attribute", "http://www.w3.org/2001/XMLSchema"), 100), new CodeForNameEntry(QNameHelper.forLNS("attributeGroup", "http://www.w3.org/2001/XMLSchema"), 101), new CodeForNameEntry(QNameHelper.forLNS("anyAttribute", "http://www.w3.org/2001/XMLSchema"), 102)};
    private static Map attributeCodeMap = StscComplexTypeResolver.buildAttributeCodeMap();

    public static Group getContentModel(ComplexType parseCt) {
        if (parseCt.getAll() != null) {
            return parseCt.getAll();
        }
        if (parseCt.getSequence() != null) {
            return parseCt.getSequence();
        }
        if (parseCt.getChoice() != null) {
            return parseCt.getChoice();
        }
        if (parseCt.getGroup() != null) {
            return parseCt.getGroup();
        }
        return null;
    }

    public static Group getContentModel(ComplexRestrictionType parseRest) {
        if (parseRest.getAll() != null) {
            return parseRest.getAll();
        }
        if (parseRest.getSequence() != null) {
            return parseRest.getSequence();
        }
        if (parseRest.getChoice() != null) {
            return parseRest.getChoice();
        }
        if (parseRest.getGroup() != null) {
            return parseRest.getGroup();
        }
        return null;
    }

    public static Group getContentModel(ExtensionType parseExt) {
        if (parseExt.getAll() != null) {
            return parseExt.getAll();
        }
        if (parseExt.getSequence() != null) {
            return parseExt.getSequence();
        }
        if (parseExt.getChoice() != null) {
            return parseExt.getChoice();
        }
        if (parseExt.getGroup() != null) {
            return parseExt.getGroup();
        }
        return null;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    static SchemaDocument.Schema getSchema(XmlObject o) {
        XmlCursor c = o.newCursor();
        try {
            while (c.toParent()) {
                o = c.getObject();
                if (!o.schemaType().equals(SchemaDocument.Schema.type)) continue;
                SchemaDocument.Schema schema = (SchemaDocument.Schema)o;
                return schema;
            }
        } finally {
            c.dispose();
        }
        return null;
    }

    public static void resolveComplexType(SchemaTypeImpl sImpl) {
        ComplexType parseCt = (ComplexType)sImpl.getParseObject();
        StscState state = StscState.get();
        SchemaDocument.Schema schema = StscComplexTypeResolver.getSchema(parseCt);
        boolean abs = parseCt.isSetAbstract() ? parseCt.getAbstract() : false;
        boolean finalExt = false;
        boolean finalRest = false;
        boolean finalList = false;
        boolean finalUnion = false;
        Object ds = null;
        if (parseCt.isSetFinal()) {
            ds = parseCt.getFinal();
        } else if (schema != null && schema.isSetFinalDefault()) {
            ds = schema.getFinalDefault();
        }
        if (ds != null) {
            if (ds instanceof String && ds.equals("#all")) {
                finalUnion = true;
                finalList = true;
                finalRest = true;
                finalExt = true;
            } else if (ds instanceof List) {
                if (((List)ds).contains("extension")) {
                    finalExt = true;
                }
                if (((List)ds).contains("restriction")) {
                    finalRest = true;
                }
            }
        }
        sImpl.setAbstractFinal(abs, finalExt, finalRest, finalList, finalUnion);
        boolean blockExt = false;
        boolean blockRest = false;
        Object block = null;
        if (parseCt.isSetBlock()) {
            block = parseCt.getBlock();
        } else if (schema != null && schema.isSetBlockDefault()) {
            block = schema.getBlockDefault();
        }
        if (block != null) {
            if (block instanceof String && block.equals("#all")) {
                blockRest = true;
                blockExt = true;
            } else if (block instanceof List) {
                if (((List)block).contains("extension")) {
                    blockExt = true;
                }
                if (((List)block).contains("restriction")) {
                    blockRest = true;
                }
            }
        }
        sImpl.setBlock(blockExt, blockRest);
        ComplexContentDocument.ComplexContent parseCc = parseCt.getComplexContent();
        SimpleContentDocument.SimpleContent parseSc = parseCt.getSimpleContent();
        Group parseGroup = StscComplexTypeResolver.getContentModel(parseCt);
        int count = (parseCc != null ? 1 : 0) + (parseSc != null ? 1 : 0) + (parseGroup != null ? 1 : 0);
        if (count > 1) {
            state.error("A complex type must define either a content model, or a simpleContent or complexContent derivation: more than one found.", 26, (XmlObject)parseCt);
            parseGroup = null;
            if (parseCc != null && parseSc != null) {
                parseSc = null;
            }
        }
        if (parseCc != null) {
            boolean mixed;
            if (parseCc.getExtension() != null && parseCc.getRestriction() != null) {
                state.error("Restriction conflicts with extension", 26, (XmlObject)parseCc.getRestriction());
            }
            boolean bl = mixed = parseCc.isSetMixed() ? parseCc.getMixed() : parseCt.getMixed();
            if (parseCc.getExtension() != null) {
                StscComplexTypeResolver.resolveCcExtension(sImpl, parseCc.getExtension(), mixed);
            } else if (parseCc.getRestriction() != null) {
                StscComplexTypeResolver.resolveCcRestriction(sImpl, parseCc.getRestriction(), mixed);
            } else {
                state.error("Missing restriction or extension", 27, (XmlObject)parseCc);
                StscComplexTypeResolver.resolveErrorType(sImpl);
            }
            return;
        }
        if (parseSc != null) {
            if (parseSc.getExtension() != null && parseSc.getRestriction() != null) {
                state.error("Restriction conflicts with extension", 26, (XmlObject)parseSc.getRestriction());
            }
            if (parseSc.getExtension() != null) {
                StscComplexTypeResolver.resolveScExtension(sImpl, parseSc.getExtension());
            } else if (parseSc.getRestriction() != null) {
                StscComplexTypeResolver.resolveScRestriction(sImpl, parseSc.getRestriction());
            } else {
                state.error("Missing restriction or extension", 27, (XmlObject)parseSc);
                StscComplexTypeResolver.resolveErrorType(sImpl);
            }
            return;
        }
        StscComplexTypeResolver.resolveBasicComplexType(sImpl);
    }

    static void resolveErrorType(SchemaTypeImpl sImpl) {
        throw new RuntimeException("This type of error recovery not yet implemented.");
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

    static void resolveBasicComplexType(SchemaTypeImpl sImpl) {
        ArrayList anonymousTypes = new ArrayList();
        ComplexType parseTree = (ComplexType)sImpl.getParseObject();
        String targetNamespace = sImpl.getTargetNamespace();
        boolean chameleon = sImpl.getChameleonNamespace() != null;
        Group parseGroup = StscComplexTypeResolver.getContentModel(parseTree);
        if (sImpl.isRedefinition()) {
            StscState.get().error("src-redefine.5a", new Object[]{"<complexType>"}, (XmlObject)parseTree);
        }
        int particleCode = StscComplexTypeResolver.translateParticleCode(parseGroup);
        LinkedHashMap elementModel = new LinkedHashMap();
        SchemaParticle contentModel = StscComplexTypeResolver.translateContentModel(sImpl, parseGroup, targetNamespace, chameleon, sImpl.getElemFormDefault(), sImpl.getAttFormDefault(), particleCode, anonymousTypes, elementModel, false, null);
        boolean isAll = contentModel != null && contentModel.getParticleType() == 1;
        SchemaAttributeModelImpl attrModel = new SchemaAttributeModelImpl();
        StscComplexTypeResolver.translateAttributeModel(parseTree, targetNamespace, chameleon, sImpl.getAttFormDefault(), anonymousTypes, sImpl, null, attrModel, null, true, null);
        WildcardResult wcElt = StscComplexTypeResolver.summarizeEltWildcards(contentModel);
        WildcardResult wcAttr = StscComplexTypeResolver.summarizeAttrWildcards(attrModel);
        if (contentModel != null) {
            StscComplexTypeResolver.buildStateMachine(contentModel);
            if (!StscState.get().noUpa() && !((SchemaParticleImpl)contentModel).isDeterministic()) {
                StscState.get().error("cos-nonambig", null, (XmlObject)parseGroup);
            }
        }
        Map elementPropertyModel = StscComplexTypeResolver.buildContentPropertyModelByQName(contentModel, sImpl);
        Map attributePropertyModel = StscComplexTypeResolver.buildAttributePropertyModelByQName(attrModel, sImpl);
        int complexVariety = parseTree.getMixed() ? 4 : (contentModel == null ? 1 : 3);
        sImpl.setBaseTypeRef(BuiltinSchemaTypeSystem.ST_ANY_TYPE.getRef());
        sImpl.setBaseDepth(BuiltinSchemaTypeSystem.ST_ANY_TYPE.getBaseDepth() + 1);
        sImpl.setDerivationType(2);
        sImpl.setComplexTypeVariety(complexVariety);
        sImpl.setContentModel(contentModel, attrModel, elementPropertyModel, attributePropertyModel, isAll);
        sImpl.setAnonymousTypeRefs(StscComplexTypeResolver.makeRefArray(anonymousTypes));
        sImpl.setWildcardSummary(wcElt.typedWildcards, wcElt.hasWildcards, wcAttr.typedWildcards, wcAttr.hasWildcards);
    }

    static void resolveCcRestriction(SchemaTypeImpl sImpl, ComplexRestrictionType parseTree, boolean mixed) {
        SchemaTypeImpl baseType;
        boolean chameleon;
        StscState state = StscState.get();
        String targetNamespace = sImpl.getTargetNamespace();
        boolean bl = chameleon = sImpl.getChameleonNamespace() != null;
        if (parseTree.getBase() == null) {
            state.error("A complexContent must define a base type", 28, (XmlObject)parseTree);
            baseType = null;
        } else {
            if (sImpl.isRedefinition()) {
                baseType = state.findRedefinedGlobalType(parseTree.getBase(), sImpl.getChameleonNamespace(), sImpl);
                if (baseType != null && !baseType.getName().equals(sImpl.getName())) {
                    state.error("src-redefine.5b", new Object[]{"<complexType>", QNameHelper.pretty(baseType.getName()), QNameHelper.pretty(sImpl.getName())}, (XmlObject)parseTree);
                }
            } else {
                baseType = state.findGlobalType(parseTree.getBase(), sImpl.getChameleonNamespace(), targetNamespace);
            }
            if (baseType == null) {
                state.notFoundError(parseTree.getBase(), 0, parseTree.xgetBase(), true);
            }
        }
        if (baseType == null) {
            baseType = BuiltinSchemaTypeSystem.ST_ANY_TYPE;
        }
        if (baseType != null && baseType.finalRestriction()) {
            state.error("derivation-ok-restriction.1", new Object[]{QNameHelper.pretty(baseType.getName()), QNameHelper.pretty(sImpl.getName())}, (XmlObject)parseTree.xgetBase());
        }
        if (baseType != null && !StscResolver.resolveType(baseType)) {
            baseType = null;
        }
        ArrayList anonymousTypes = new ArrayList();
        Group parseEg = StscComplexTypeResolver.getContentModel(parseTree);
        int particleCode = StscComplexTypeResolver.translateParticleCode(parseEg);
        LinkedHashMap elementModel = new LinkedHashMap();
        SchemaParticle contentModel = StscComplexTypeResolver.translateContentModel(sImpl, parseEg, targetNamespace, chameleon, sImpl.getElemFormDefault(), sImpl.getAttFormDefault(), particleCode, anonymousTypes, elementModel, false, null);
        boolean isAll = contentModel != null && contentModel.getParticleType() == 1;
        SchemaAttributeModelImpl attrModel = baseType == null ? new SchemaAttributeModelImpl() : new SchemaAttributeModelImpl(baseType.getAttributeModel());
        StscComplexTypeResolver.translateAttributeModel(parseTree, targetNamespace, chameleon, sImpl.getAttFormDefault(), anonymousTypes, sImpl, null, attrModel, baseType, false, null);
        WildcardResult wcElt = StscComplexTypeResolver.summarizeEltWildcards(contentModel);
        WildcardResult wcAttr = StscComplexTypeResolver.summarizeAttrWildcards(attrModel);
        if (contentModel != null) {
            StscComplexTypeResolver.buildStateMachine(contentModel);
            if (!StscState.get().noUpa() && !((SchemaParticleImpl)contentModel).isDeterministic()) {
                StscState.get().error("cos-nonambig", null, (XmlObject)parseEg);
            }
        }
        Map elementPropertyModel = StscComplexTypeResolver.buildContentPropertyModelByQName(contentModel, sImpl);
        Map attributePropertyModel = StscComplexTypeResolver.buildAttributePropertyModelByQName(attrModel, sImpl);
        int complexVariety = mixed ? 4 : (contentModel == null ? 1 : 3);
        sImpl.setBaseTypeRef(baseType.getRef());
        sImpl.setBaseDepth(baseType.getBaseDepth() + 1);
        sImpl.setDerivationType(1);
        sImpl.setComplexTypeVariety(complexVariety);
        sImpl.setContentModel(contentModel, attrModel, elementPropertyModel, attributePropertyModel, isAll);
        sImpl.setAnonymousTypeRefs(StscComplexTypeResolver.makeRefArray(anonymousTypes));
        sImpl.setWildcardSummary(wcElt.typedWildcards, wcElt.hasWildcards, wcAttr.typedWildcards, wcAttr.hasWildcards);
    }

    static Map extractElementModel(SchemaType sType) {
        HashMap<QName, SchemaType> elementModel = new HashMap<QName, SchemaType>();
        if (sType != null) {
            SchemaProperty[] sProps = sType.getProperties();
            for (int i = 0; i < sProps.length; ++i) {
                if (sProps[i].isAttribute()) continue;
                elementModel.put(sProps[i].getName(), sProps[i].getType());
            }
        }
        return elementModel;
    }

    static void resolveCcExtension(SchemaTypeImpl sImpl, ExtensionType parseTree, boolean mixed) {
        int complexVariety;
        SchemaParticle contentModel;
        SchemaParticle extensionModel;
        SchemaType baseType;
        boolean chameleon;
        StscState state = StscState.get();
        String targetNamespace = sImpl.getTargetNamespace();
        boolean bl = chameleon = sImpl.getChameleonNamespace() != null;
        if (parseTree.getBase() == null) {
            state.error("A complexContent must define a base type", 28, (XmlObject)parseTree);
            baseType = null;
        } else {
            if (sImpl.isRedefinition()) {
                baseType = state.findRedefinedGlobalType(parseTree.getBase(), sImpl.getChameleonNamespace(), sImpl);
                if (baseType != null && !baseType.getName().equals(sImpl.getName())) {
                    state.error("src-redefine.5b", new Object[]{"<complexType>", QNameHelper.pretty(baseType.getName()), QNameHelper.pretty(sImpl.getName())}, (XmlObject)parseTree);
                }
            } else {
                baseType = state.findGlobalType(parseTree.getBase(), sImpl.getChameleonNamespace(), targetNamespace);
            }
            if (baseType == null) {
                state.notFoundError(parseTree.getBase(), 0, parseTree.xgetBase(), true);
            }
        }
        if (baseType != null && !StscResolver.resolveType((SchemaTypeImpl)baseType)) {
            baseType = null;
        }
        if (baseType != null && baseType.isSimpleType()) {
            state.recover("src-ct.1", new Object[]{QNameHelper.pretty(baseType.getName())}, parseTree.xgetBase());
            baseType = null;
        }
        if (baseType != null && baseType.finalExtension()) {
            state.error("cos-ct-extends.1.1", new Object[]{QNameHelper.pretty(baseType.getName()), QNameHelper.pretty(sImpl.getName())}, (XmlObject)parseTree.xgetBase());
        }
        SchemaParticle baseContentModel = baseType == null ? null : baseType.getContentModel();
        ArrayList anonymousTypes = new ArrayList();
        Map baseElementModel = StscComplexTypeResolver.extractElementModel(baseType);
        Group parseEg = StscComplexTypeResolver.getContentModel(parseTree);
        if (baseType != null && baseType.getContentType() == 2) {
            if (parseEg != null) {
                state.recover("cos-ct-extends.1.4.1", new Object[]{QNameHelper.pretty(baseType.getName())}, parseTree.xgetBase());
                baseType = null;
            } else {
                StscComplexTypeResolver.resolveScExtensionPart2(sImpl, baseType, parseTree, targetNamespace, chameleon);
                return;
            }
        }
        if ((extensionModel = StscComplexTypeResolver.translateContentModel(sImpl, parseEg, targetNamespace, chameleon, sImpl.getElemFormDefault(), sImpl.getAttFormDefault(), StscComplexTypeResolver.translateParticleCode(parseEg), anonymousTypes, baseElementModel, false, null)) == null && !mixed) {
            boolean bl2 = mixed = baseType != null && baseType.getContentType() == 4;
        }
        if (baseType != null && baseType.getContentType() != 1 && baseType.getContentType() == 4 != mixed) {
            state.error("cos-ct-extends.1.4.2.2", null, (XmlObject)parseTree.xgetBase());
        }
        if (baseType != null && baseType.hasAllContent() && extensionModel != null) {
            state.error("Cannot extend a type with 'all' content model", 42, (XmlObject)parseTree.xgetBase());
            extensionModel = null;
        }
        boolean isAll = (contentModel = StscComplexTypeResolver.extendContentModel(baseContentModel, extensionModel, parseTree)) != null && contentModel.getParticleType() == 1;
        SchemaAttributeModelImpl attrModel = baseType == null ? new SchemaAttributeModelImpl() : new SchemaAttributeModelImpl(baseType.getAttributeModel());
        StscComplexTypeResolver.translateAttributeModel(parseTree, targetNamespace, chameleon, sImpl.getAttFormDefault(), anonymousTypes, sImpl, null, attrModel, baseType, true, null);
        WildcardResult wcElt = StscComplexTypeResolver.summarizeEltWildcards(contentModel);
        WildcardResult wcAttr = StscComplexTypeResolver.summarizeAttrWildcards(attrModel);
        if (contentModel != null) {
            StscComplexTypeResolver.buildStateMachine(contentModel);
            if (!StscState.get().noUpa() && !((SchemaParticleImpl)contentModel).isDeterministic()) {
                StscState.get().error("cos-nonambig", null, (XmlObject)parseEg);
            }
        }
        Map elementPropertyModel = StscComplexTypeResolver.buildContentPropertyModelByQName(contentModel, sImpl);
        Map attributePropertyModel = StscComplexTypeResolver.buildAttributePropertyModelByQName(attrModel, sImpl);
        if (contentModel == null && baseType != null && baseType.getContentType() == 2) {
            complexVariety = 2;
            sImpl.setContentBasedOnTypeRef(baseType.getContentBasedOnType().getRef());
        } else {
            int n = mixed ? 4 : (complexVariety = contentModel == null ? 1 : 3);
        }
        if (baseType == null) {
            baseType = XmlObject.type;
        }
        sImpl.setBaseTypeRef(baseType.getRef());
        sImpl.setBaseDepth(((SchemaTypeImpl)baseType).getBaseDepth() + 1);
        sImpl.setDerivationType(2);
        sImpl.setComplexTypeVariety(complexVariety);
        sImpl.setContentModel(contentModel, attrModel, elementPropertyModel, attributePropertyModel, isAll);
        sImpl.setAnonymousTypeRefs(StscComplexTypeResolver.makeRefArray(anonymousTypes));
        sImpl.setWildcardSummary(wcElt.typedWildcards, wcElt.hasWildcards, wcAttr.typedWildcards, wcAttr.hasWildcards);
    }

    static void resolveScRestriction(SchemaTypeImpl sImpl, SimpleRestrictionType parseTree) {
        SchemaTypeImpl baseType;
        SchemaTypeImpl contentType = null;
        StscState state = StscState.get();
        String targetNamespace = sImpl.getTargetNamespace();
        boolean chameleon = sImpl.getChameleonNamespace() != null;
        ArrayList anonymousTypes = new ArrayList();
        if (parseTree.getSimpleType() != null) {
            SchemaTypeImpl anonType;
            LocalSimpleType typedef = parseTree.getSimpleType();
            contentType = anonType = StscTranslator.translateAnonymousSimpleType(typedef, targetNamespace, chameleon, sImpl.getElemFormDefault(), sImpl.getAttFormDefault(), anonymousTypes, sImpl);
        }
        if (parseTree.getBase() == null) {
            state.error("A simpleContent restriction must define a base type", 28, (XmlObject)parseTree);
            baseType = BuiltinSchemaTypeSystem.ST_ANY_SIMPLE;
        } else {
            if (sImpl.isRedefinition()) {
                baseType = state.findRedefinedGlobalType(parseTree.getBase(), sImpl.getChameleonNamespace(), sImpl);
                if (baseType != null && !baseType.getName().equals(sImpl.getName())) {
                    state.error("src-redefine.5b", new Object[]{"<simpleType>", QNameHelper.pretty(baseType.getName()), QNameHelper.pretty(sImpl.getName())}, (XmlObject)parseTree);
                }
            } else {
                baseType = state.findGlobalType(parseTree.getBase(), sImpl.getChameleonNamespace(), targetNamespace);
            }
            if (baseType == null) {
                state.notFoundError(parseTree.getBase(), 0, parseTree.xgetBase(), true);
                baseType = BuiltinSchemaTypeSystem.ST_ANY_SIMPLE;
            }
        }
        StscResolver.resolveType(baseType);
        if (contentType != null) {
            StscResolver.resolveType(contentType);
        } else {
            contentType = baseType;
        }
        if (baseType.isSimpleType()) {
            state.recover("ct-props-correct.2", new Object[]{QNameHelper.pretty(baseType.getName())}, parseTree);
            baseType = BuiltinSchemaTypeSystem.ST_ANY_SIMPLE;
        } else if (baseType.getContentType() != 2 && contentType == null) {
            baseType = BuiltinSchemaTypeSystem.ST_ANY_SIMPLE;
        }
        if (baseType != null && baseType.finalRestriction()) {
            state.error("derivation-ok-restriction.1", new Object[]{QNameHelper.pretty(baseType.getName()), QNameHelper.pretty(sImpl.getName())}, (XmlObject)parseTree.xgetBase());
        }
        SchemaAttributeModelImpl attrModel = baseType == null ? new SchemaAttributeModelImpl() : new SchemaAttributeModelImpl(baseType.getAttributeModel());
        StscComplexTypeResolver.translateAttributeModel(parseTree, targetNamespace, chameleon, sImpl.getAttFormDefault(), anonymousTypes, sImpl, null, attrModel, baseType, false, null);
        WildcardResult wcAttr = StscComplexTypeResolver.summarizeAttrWildcards(attrModel);
        Map attributePropertyModel = StscComplexTypeResolver.buildAttributePropertyModelByQName(attrModel, sImpl);
        sImpl.setBaseTypeRef(baseType.getRef());
        sImpl.setBaseDepth(baseType.getBaseDepth() + 1);
        sImpl.setContentBasedOnTypeRef(contentType.getRef());
        sImpl.setDerivationType(1);
        sImpl.setAnonymousTypeRefs(StscComplexTypeResolver.makeRefArray(anonymousTypes));
        sImpl.setWildcardSummary(QNameSet.EMPTY, false, wcAttr.typedWildcards, wcAttr.hasWildcards);
        sImpl.setComplexTypeVariety(2);
        sImpl.setContentModel(null, attrModel, null, attributePropertyModel, false);
        sImpl.setSimpleTypeVariety(contentType.getSimpleVariety());
        sImpl.setPrimitiveTypeRef(contentType.getPrimitiveType() == null ? null : contentType.getPrimitiveType().getRef());
        switch (sImpl.getSimpleVariety()) {
            case 3: {
                sImpl.setListItemTypeRef(contentType.getListItemType().getRef());
                break;
            }
            case 2: {
                sImpl.setUnionMemberTypeRefs(StscComplexTypeResolver.makeRefArray(Arrays.asList(contentType.getUnionMemberTypes())));
            }
        }
        StscSimpleTypeResolver.resolveFacets(sImpl, parseTree, contentType);
        StscSimpleTypeResolver.resolveFundamentalFacets(sImpl);
    }

    static void resolveScExtension(SchemaTypeImpl sImpl, SimpleExtensionType parseTree) {
        SchemaTypeImpl baseType;
        boolean chameleon;
        StscState state = StscState.get();
        String targetNamespace = sImpl.getTargetNamespace();
        boolean bl = chameleon = sImpl.getChameleonNamespace() != null;
        if (parseTree.getBase() == null) {
            state.error("A simpleContent extension must define a base type", 28, (XmlObject)parseTree);
            baseType = BuiltinSchemaTypeSystem.ST_ANY_SIMPLE;
        } else {
            if (sImpl.isRedefinition()) {
                baseType = state.findRedefinedGlobalType(parseTree.getBase(), sImpl.getChameleonNamespace(), sImpl);
                if (baseType != null && !baseType.getName().equals(sImpl.getName())) {
                    state.error("src-redefine.5b", new Object[]{"<simpleType>", QNameHelper.pretty(baseType.getName()), QNameHelper.pretty(sImpl.getName())}, (XmlObject)parseTree);
                }
            } else {
                baseType = state.findGlobalType(parseTree.getBase(), sImpl.getChameleonNamespace(), targetNamespace);
            }
            if (baseType == null) {
                state.notFoundError(parseTree.getBase(), 0, parseTree.xgetBase(), true);
                baseType = BuiltinSchemaTypeSystem.ST_ANY_SIMPLE;
            }
        }
        StscResolver.resolveType(baseType);
        if (!baseType.isSimpleType() && baseType.getContentType() != 2) {
            state.error("src-ct.2", new Object[]{QNameHelper.pretty(baseType.getName())}, (XmlObject)parseTree);
            baseType = BuiltinSchemaTypeSystem.ST_ANY_SIMPLE;
        }
        if (baseType != null && baseType.finalExtension()) {
            state.error("cos-ct-extends.1.1", new Object[]{QNameHelper.pretty(baseType.getName()), QNameHelper.pretty(sImpl.getName())}, (XmlObject)parseTree.xgetBase());
        }
        StscComplexTypeResolver.resolveScExtensionPart2(sImpl, baseType, parseTree, targetNamespace, chameleon);
    }

    static void resolveScExtensionPart2(SchemaTypeImpl sImpl, SchemaType baseType, ExtensionType parseTree, String targetNamespace, boolean chameleon) {
        ArrayList anonymousTypes = new ArrayList();
        SchemaAttributeModelImpl attrModel = new SchemaAttributeModelImpl(baseType.getAttributeModel());
        StscComplexTypeResolver.translateAttributeModel(parseTree, targetNamespace, chameleon, sImpl.getAttFormDefault(), anonymousTypes, sImpl, null, attrModel, baseType, true, null);
        WildcardResult wcAttr = StscComplexTypeResolver.summarizeAttrWildcards(attrModel);
        Map attributePropertyModel = StscComplexTypeResolver.buildAttributePropertyModelByQName(attrModel, sImpl);
        sImpl.setBaseTypeRef(baseType.getRef());
        sImpl.setBaseDepth(((SchemaTypeImpl)baseType).getBaseDepth() + 1);
        sImpl.setContentBasedOnTypeRef(baseType.getRef());
        sImpl.setDerivationType(2);
        sImpl.setAnonymousTypeRefs(StscComplexTypeResolver.makeRefArray(anonymousTypes));
        sImpl.setWildcardSummary(QNameSet.EMPTY, false, wcAttr.typedWildcards, wcAttr.hasWildcards);
        sImpl.setComplexTypeVariety(2);
        sImpl.setContentModel(null, attrModel, null, attributePropertyModel, false);
        sImpl.setSimpleTypeVariety(baseType.getSimpleVariety());
        sImpl.setPrimitiveTypeRef(baseType.getPrimitiveType() == null ? null : baseType.getPrimitiveType().getRef());
        switch (sImpl.getSimpleVariety()) {
            case 3: {
                sImpl.setListItemTypeRef(baseType.getListItemType().getRef());
                break;
            }
            case 2: {
                sImpl.setUnionMemberTypeRefs(StscComplexTypeResolver.makeRefArray(Arrays.asList(baseType.getUnionMemberTypes())));
            }
        }
        StscSimpleTypeResolver.resolveFacets(sImpl, null, (SchemaTypeImpl)baseType);
        StscSimpleTypeResolver.resolveFundamentalFacets(sImpl);
    }

    static WildcardResult summarizeAttrWildcards(SchemaAttributeModel attrModel) {
        if (attrModel.getWildcardProcess() == 0) {
            return new WildcardResult(QNameSet.EMPTY, false);
        }
        if (attrModel.getWildcardProcess() == 3) {
            return new WildcardResult(QNameSet.EMPTY, true);
        }
        return new WildcardResult(attrModel.getWildcardSet(), true);
    }

    static WildcardResult summarizeEltWildcards(SchemaParticle contentModel) {
        if (contentModel == null) {
            return new WildcardResult(QNameSet.EMPTY, false);
        }
        switch (contentModel.getParticleType()) {
            case 1: 
            case 2: 
            case 3: {
                QNameSetBuilder set = new QNameSetBuilder();
                boolean hasWildcards = false;
                for (int i = 0; i < contentModel.countOfParticleChild(); ++i) {
                    WildcardResult inner = StscComplexTypeResolver.summarizeEltWildcards(contentModel.getParticleChild(i));
                    set.addAll(inner.typedWildcards);
                    hasWildcards |= inner.hasWildcards;
                }
                return new WildcardResult(set.toQNameSet(), hasWildcards);
            }
            case 5: {
                return new WildcardResult(contentModel.getWildcardProcess() == 3 ? QNameSet.EMPTY : contentModel.getWildcardSet(), true);
            }
        }
        return new WildcardResult(QNameSet.EMPTY, false);
    }

    static void translateAttributeModel(XmlObject parseTree, String targetNamespace, boolean chameleon, String formDefault, List anonymousTypes, SchemaType outerType, Set seenAttributes, SchemaAttributeModelImpl result, SchemaType baseType, boolean extension, SchemaAttributeGroupImpl redefinitionFor) {
        StscState state = StscState.get();
        if (seenAttributes == null) {
            seenAttributes = new HashSet<QName>();
        }
        boolean seenWildcard = false;
        boolean seenRedefinition = false;
        SchemaAttributeModel baseModel = null;
        if (baseType != null) {
            baseModel = baseType.getAttributeModel();
        }
        XmlCursor cur = parseTree.newCursor();
        boolean more = cur.toFirstChild();
        while (more) {
            switch (StscComplexTypeResolver.translateAttributeCode(cur.getName())) {
                case 100: {
                    Attribute xsdattr = (Attribute)cur.getObject();
                    SchemaLocalAttributeImpl sAttr = StscTranslator.translateAttribute(xsdattr, targetNamespace, formDefault, chameleon, anonymousTypes, outerType, baseModel, true);
                    if (sAttr == null) break;
                    if (seenAttributes.contains(sAttr.getName())) {
                        state.error("ct-props-correct.4", new Object[]{QNameHelper.pretty(sAttr.getName()), QNameHelper.pretty(outerType.getName())}, (XmlObject)xsdattr.xgetName());
                        break;
                    }
                    seenAttributes.add(sAttr.getName());
                    if (baseModel != null) {
                        SchemaLocalAttribute baseAttr = baseModel.getAttribute(sAttr.getName());
                        if (baseAttr == null) {
                            if (!extension && !baseModel.getWildcardSet().contains(sAttr.getName())) {
                                state.error("derivation-ok-restriction.2.2", new Object[]{QNameHelper.pretty(sAttr.getName()), QNameHelper.pretty(outerType.getName())}, (XmlObject)xsdattr);
                            }
                        } else if (extension) {
                            if (sAttr.getUse() == 1) {
                                state.error("An extension cannot prohibit an attribute from the base type; use restriction instead.", 37, (XmlObject)xsdattr.xgetUse());
                            }
                        } else if (sAttr.getUse() != 3) {
                            if (baseAttr.getUse() == 3) {
                                state.error("derivation-ok-restriction.2.1.1", new Object[]{QNameHelper.pretty(sAttr.getName()), QNameHelper.pretty(outerType.getName())}, (XmlObject)xsdattr);
                            }
                            if (sAttr.getUse() == 1) {
                                result.removeProhibitedAttribute(sAttr.getName());
                            }
                        }
                    }
                    if (sAttr.getUse() != 1) {
                        result.addAttribute(sAttr);
                    } else {
                        SchemaType attrType = sAttr.getType();
                        if (anonymousTypes != null && anonymousTypes.contains(attrType)) {
                            anonymousTypes.remove(attrType);
                        }
                    }
                    if (sAttr.getDefaultText() == null || sAttr.isFixed() || sAttr.getUse() == 2) break;
                    state.error("src-attribute.2", new Object[]{QNameHelper.pretty(sAttr.getName())}, (XmlObject)xsdattr);
                    break;
                }
                case 102: {
                    Wildcard xsdwc = (Wildcard)cur.getObject();
                    if (seenWildcard) {
                        state.error("Only one attribute wildcard allowed", 38, (XmlObject)xsdwc);
                        break;
                    }
                    seenWildcard = true;
                    NamespaceList nsList = xsdwc.xgetNamespace();
                    String nsText = nsList == null ? "##any" : nsList.getStringValue();
                    QNameSet wcset = QNameSet.forWildcardNamespaceString(nsText, targetNamespace);
                    if (baseModel != null && !extension) {
                        if (baseModel.getWildcardSet() == null) {
                            state.error("derivation-ok-restriction.4.1", null, (XmlObject)xsdwc);
                            break;
                        }
                        if (!baseModel.getWildcardSet().containsAll(wcset)) {
                            state.error("derivation-ok-restriction.4.2", new Object[]{nsText}, (XmlObject)xsdwc);
                            break;
                        }
                    }
                    int wcprocess = StscComplexTypeResolver.translateWildcardProcess(xsdwc.xgetProcessContents());
                    if (result.getWildcardProcess() == 0) {
                        result.setWildcardSet(wcset);
                        result.setWildcardProcess(wcprocess);
                        break;
                    }
                    if (extension) {
                        result.setWildcardSet(wcset.union(result.getWildcardSet()));
                        result.setWildcardProcess(wcprocess);
                        break;
                    }
                    result.setWildcardSet(wcset.intersect(result.getWildcardSet()));
                    break;
                }
                case 101: {
                    SchemaAttributeGroupImpl group;
                    AttributeGroupRef xsdag = (AttributeGroupRef)cur.getObject();
                    QName ref = xsdag.getRef();
                    if (ref == null) {
                        state.error("Attribute group reference must have a ref attribute", 39, (XmlObject)xsdag);
                        break;
                    }
                    if (redefinitionFor != null) {
                        group = state.findRedefinedAttributeGroup(ref, chameleon ? targetNamespace : null, redefinitionFor);
                        if (group != null && redefinitionFor.getName().equals(group.getName())) {
                            if (seenRedefinition) {
                                state.error("src-redefine.7.1", new Object[]{QNameHelper.pretty(redefinitionFor.getName())}, (XmlObject)xsdag);
                            }
                            seenRedefinition = true;
                        }
                    } else {
                        group = state.findAttributeGroup(ref, chameleon ? targetNamespace : null, targetNamespace);
                    }
                    if (group == null) {
                        state.notFoundError(ref, 4, xsdag.xgetRef(), true);
                        break;
                    }
                    if (state.isProcessing(group)) {
                        state.error("src-attribute_group.3", new Object[]{QNameHelper.pretty(group.getName())}, group.getParseObject());
                        break;
                    }
                    String subTargetNamespace = targetNamespace;
                    if (group.getTargetNamespace() != null) {
                        subTargetNamespace = group.getTargetNamespace();
                        chameleon = group.getChameleonNamespace() != null;
                    }
                    state.startProcessing(group);
                    SchemaAttributeGroupImpl nestedRedefinitionFor = null;
                    if (group.isRedefinition()) {
                        nestedRedefinitionFor = group;
                    }
                    StscComplexTypeResolver.translateAttributeModel(group.getParseObject(), subTargetNamespace, chameleon, group.getFormDefault(), anonymousTypes, outerType, seenAttributes, result, baseType, extension, nestedRedefinitionFor);
                    state.finishProcessing(group);
                    break;
                }
            }
            more = cur.toNextSibling();
        }
        if (!extension && !seenWildcard) {
            result.setWildcardSet(null);
            result.setWildcardProcess(0);
        }
    }

    static SchemaParticle extendContentModel(SchemaParticle baseContentModel, SchemaParticle extendedContentModel, XmlObject parseTree) {
        if (extendedContentModel == null) {
            return baseContentModel;
        }
        if (baseContentModel == null) {
            return extendedContentModel;
        }
        SchemaParticleImpl sPart = new SchemaParticleImpl();
        sPart.setParticleType(3);
        ArrayList accumulate = new ArrayList();
        StscComplexTypeResolver.addMinusPointlessParticles(accumulate, baseContentModel, 3);
        StscComplexTypeResolver.addMinusPointlessParticles(accumulate, extendedContentModel, 3);
        sPart.setMinOccurs(BigInteger.ONE);
        sPart.setMaxOccurs(BigInteger.ONE);
        sPart.setParticleChildren(accumulate.toArray(new SchemaParticle[accumulate.size()]));
        return StscComplexTypeResolver.filterPointlessParticlesAndVerifyAllParticles(sPart, parseTree);
    }

    static BigInteger extractMinOccurs(XmlNonNegativeInteger nni) {
        if (nni == null) {
            return BigInteger.ONE;
        }
        BigInteger result = nni.getBigIntegerValue();
        if (result == null) {
            return BigInteger.ONE;
        }
        return result;
    }

    static BigInteger extractMaxOccurs(AllNNI allNNI) {
        if (allNNI == null) {
            return BigInteger.ONE;
        }
        if (allNNI.instanceType().getPrimitiveType().getBuiltinTypeCode() == 11) {
            return ((XmlInteger)((Object)allNNI)).getBigIntegerValue();
        }
        return null;
    }

    static SchemaParticle translateContentModel(SchemaType outerType, XmlObject parseTree, String targetNamespace, boolean chameleon, String elemFormDefault, String attFormDefault, int particleCode, List anonymousTypes, Map elementModel, boolean allowElt, RedefinitionForGroup redefinitionFor) {
        BigInteger maxOccurs;
        BigInteger minOccurs;
        SchemaParticleImpl sPart;
        if (parseTree == null || particleCode == 0) {
            return null;
        }
        StscState state = StscState.get();
        assert (particleCode != 0);
        boolean hasChildren = false;
        SchemaModelGroupImpl group = null;
        if (particleCode == 4) {
            LocalElement parseElt;
            if (!allowElt) {
                state.error("Must be a sequence, choice or all here", 32, parseTree);
            }
            if ((sPart = StscTranslator.translateElement(parseElt = (LocalElement)parseTree, targetNamespace, chameleon, elemFormDefault, attFormDefault, anonymousTypes, outerType)) == null) {
                return null;
            }
            minOccurs = StscComplexTypeResolver.extractMinOccurs(parseElt.xgetMinOccurs());
            maxOccurs = StscComplexTypeResolver.extractMaxOccurs(parseElt.xgetMaxOccurs());
            SchemaType oldType = (SchemaType)elementModel.get(sPart.getName());
            if (oldType == null) {
                elementModel.put(sPart.getName(), sPart.getType());
            } else if (!sPart.getType().equals(oldType)) {
                state.error("cos-element-consistent", new Object[]{QNameHelper.pretty(sPart.getName())}, parseTree);
                return null;
            }
        } else if (particleCode == 5) {
            if (!allowElt) {
                state.error("Must be a sequence, choice or all here", 32, parseTree);
            }
            AnyDocument.Any parseAny = (AnyDocument.Any)parseTree;
            sPart = new SchemaParticleImpl();
            sPart.setParticleType(5);
            NamespaceList nslist = parseAny.xgetNamespace();
            QNameSet wcset = nslist == null ? QNameSet.ALL : QNameSet.forWildcardNamespaceString(nslist.getStringValue(), targetNamespace);
            sPart.setWildcardSet(wcset);
            sPart.setWildcardProcess(StscComplexTypeResolver.translateWildcardProcess(parseAny.xgetProcessContents()));
            minOccurs = StscComplexTypeResolver.extractMinOccurs(parseAny.xgetMinOccurs());
            maxOccurs = StscComplexTypeResolver.extractMaxOccurs(parseAny.xgetMaxOccurs());
        } else {
            Group parseGroup = (Group)parseTree;
            sPart = new SchemaParticleImpl();
            minOccurs = StscComplexTypeResolver.extractMinOccurs(parseGroup.xgetMinOccurs());
            maxOccurs = StscComplexTypeResolver.extractMaxOccurs(parseGroup.xgetMaxOccurs());
            if (particleCode == 100) {
                String newTargetNamespace;
                QName ref = parseGroup.getRef();
                if (ref == null) {
                    state.error("Group reference must have a ref attribute", 33, parseTree);
                    return null;
                }
                if (redefinitionFor != null) {
                    group = state.findRedefinedModelGroup(ref, chameleon ? targetNamespace : null, redefinitionFor.getGroup());
                    if (group != null && group.getName().equals(redefinitionFor.getGroup().getName())) {
                        if (redefinitionFor.isSeenRedefinition()) {
                            state.error("src-redefine.6.1.1", new Object[]{QNameHelper.pretty(group.getName())}, parseTree);
                        }
                        if (!BigInteger.ONE.equals(maxOccurs) || !BigInteger.ONE.equals(minOccurs)) {
                            state.error("src-redefine.6.1.2", new Object[]{QNameHelper.pretty(group.getName())}, parseTree);
                        }
                        redefinitionFor.setSeenRedefinition(true);
                    }
                } else {
                    group = state.findModelGroup(ref, chameleon ? targetNamespace : null, targetNamespace);
                }
                if (group == null) {
                    state.notFoundError(ref, 6, ((Group)parseTree).xgetRef(), true);
                    return null;
                }
                if (state.isProcessing(group)) {
                    state.error("mg-props-correct.2", new Object[]{QNameHelper.pretty(group.getName())}, group.getParseObject());
                    return null;
                }
                XmlCursor cur = group.getParseObject().newCursor();
                boolean more = cur.toFirstChild();
                while (more) {
                    particleCode = StscComplexTypeResolver.translateParticleCode(cur.getName());
                    if (particleCode != 0) {
                        parseGroup = (Group)cur.getObject();
                        parseTree = parseGroup;
                        break;
                    }
                    more = cur.toNextSibling();
                }
                if (particleCode == 0) {
                    state.error("Model group " + QNameHelper.pretty(group.getName()) + " is empty", 32, group.getParseObject());
                    return null;
                }
                if (particleCode != 1 && particleCode != 3 && particleCode != 2) {
                    state.error("Model group " + QNameHelper.pretty(group.getName()) + " is not a sequence, all, or choice", 32, group.getParseObject());
                }
                if ((newTargetNamespace = group.getTargetNamespace()) != null) {
                    targetNamespace = newTargetNamespace;
                }
                elemFormDefault = group.getElemFormDefault();
                attFormDefault = group.getAttFormDefault();
                chameleon = group.getChameleonNamespace() != null;
            }
            switch (particleCode) {
                case 1: 
                case 2: 
                case 3: {
                    sPart.setParticleType(particleCode);
                    hasChildren = true;
                    break;
                }
                default: {
                    assert (false);
                    throw new IllegalStateException();
                }
            }
        }
        if (maxOccurs != null && minOccurs.compareTo(maxOccurs) > 0) {
            state.error("p-props-correct.2.1", null, parseTree);
            maxOccurs = minOccurs;
        }
        if (maxOccurs != null && maxOccurs.compareTo(BigInteger.ONE) < 0) {
            state.warning("p-props-correct.2.2", null, parseTree);
            anonymousTypes.remove(sPart.getType());
            return null;
        }
        sPart.setMinOccurs(minOccurs);
        sPart.setMaxOccurs(maxOccurs);
        if (group != null) {
            state.startProcessing(group);
            redefinitionFor = null;
            if (group.isRedefinition()) {
                redefinitionFor = new RedefinitionForGroup(group);
            }
        }
        if (hasChildren) {
            XmlCursor cur = parseTree.newCursor();
            ArrayList accumulate = new ArrayList();
            boolean more = cur.toFirstChild();
            while (more) {
                int code = StscComplexTypeResolver.translateParticleCode(cur.getName());
                if (code != 0) {
                    StscComplexTypeResolver.addMinusPointlessParticles(accumulate, StscComplexTypeResolver.translateContentModel(outerType, cur.getObject(), targetNamespace, chameleon, elemFormDefault, attFormDefault, code, anonymousTypes, elementModel, true, redefinitionFor), sPart.getParticleType());
                }
                more = cur.toNextSibling();
            }
            sPart.setParticleChildren(accumulate.toArray(new SchemaParticle[accumulate.size()]));
            cur.dispose();
        }
        SchemaParticle result = StscComplexTypeResolver.filterPointlessParticlesAndVerifyAllParticles(sPart, parseTree);
        if (group != null) {
            state.finishProcessing(group);
        }
        return result;
    }

    static int translateWildcardProcess(Wildcard.ProcessContents process) {
        if (process == null) {
            return 1;
        }
        String processValue = process.getStringValue();
        if ("lax".equals(processValue)) {
            return 2;
        }
        if ("skip".equals(processValue)) {
            return 3;
        }
        return 1;
    }

    static SchemaParticle filterPointlessParticlesAndVerifyAllParticles(SchemaParticle part, XmlObject parseTree) {
        boolean isAll;
        if (part.getMaxOccurs() != null && part.getMaxOccurs().signum() == 0) {
            return null;
        }
        switch (part.getParticleType()) {
            case 1: 
            case 3: {
                if (part.getParticleChildren().length == 0) {
                    return null;
                }
                if (!part.isSingleton() || part.countOfParticleChild() != 1) break;
                return part.getParticleChild(0);
            }
            case 2: {
                if (part.getParticleChildren().length == 0 && part.getMinOccurs().compareTo(BigInteger.ZERO) == 0) {
                    return null;
                }
                if (!part.isSingleton() || part.countOfParticleChild() != 1) break;
                return part.getParticleChild(0);
            }
            case 4: 
            case 5: {
                return part;
            }
            default: {
                assert (false);
                throw new IllegalStateException();
            }
        }
        boolean bl = isAll = part.getParticleType() == 1;
        if (isAll && (part.getMaxOccurs() == null || part.getMaxOccurs().compareTo(BigInteger.ONE) > 0)) {
            StscState.get().error("cos-all-limited.1.2a", null, parseTree);
        }
        for (int i = 0; i < part.countOfParticleChild(); ++i) {
            SchemaParticle child = part.getParticleChild(i);
            if (child.getParticleType() == 1) {
                StscState.get().error("cos-all-limited.1.2b", null, parseTree);
                continue;
            }
            if (!isAll || child.getParticleType() == 4 && child.getMaxOccurs() != null && child.getMaxOccurs().compareTo(BigInteger.ONE) <= 0) continue;
            StscState.get().error("cos-all-limited.2", null, parseTree);
        }
        return part;
    }

    static void addMinusPointlessParticles(List list, SchemaParticle part, int parentParticleType) {
        if (part == null) {
            return;
        }
        switch (part.getParticleType()) {
            case 3: {
                if (parentParticleType != 3 || !part.isSingleton()) break;
                list.addAll(Arrays.asList(part.getParticleChildren()));
                return;
            }
            case 2: {
                if (parentParticleType != 2 || !part.isSingleton()) break;
                list.addAll(Arrays.asList(part.getParticleChildren()));
                return;
            }
        }
        list.add(part);
    }

    static Map buildAttributePropertyModelByQName(SchemaAttributeModel attrModel, SchemaType owner) {
        LinkedHashMap<QName, SchemaProperty> result = new LinkedHashMap<QName, SchemaProperty>();
        SchemaLocalAttribute[] attruses = attrModel.getAttributes();
        for (int i = 0; i < attruses.length; ++i) {
            result.put(attruses[i].getName(), StscComplexTypeResolver.buildUseProperty(attruses[i], owner));
        }
        return result;
    }

    static Map buildContentPropertyModelByQName(SchemaParticle part, SchemaType owner) {
        if (part == null) {
            return Collections.EMPTY_MAP;
        }
        boolean asSequence = false;
        LinkedHashMap<QName, SchemaProperty> model = null;
        switch (part.getParticleType()) {
            case 1: 
            case 3: {
                asSequence = true;
                break;
            }
            case 2: {
                asSequence = false;
                break;
            }
            case 4: {
                model = StscComplexTypeResolver.buildElementPropertyModel((SchemaLocalElement)((Object)part), owner);
                break;
            }
            case 5: {
                model = Collections.EMPTY_MAP;
                break;
            }
            default: {
                assert (false);
                throw new IllegalStateException();
            }
        }
        if (model == null) {
            model = new LinkedHashMap<QName, SchemaProperty>();
            SchemaParticle[] children = part.getParticleChildren();
            for (int i = 0; i < children.length; ++i) {
                Map childModel = StscComplexTypeResolver.buildContentPropertyModelByQName(children[i], owner);
                for (SchemaProperty iProp : childModel.values()) {
                    SchemaPropertyImpl oProp = (SchemaPropertyImpl)model.get(iProp.getName());
                    if (oProp == null) {
                        if (!asSequence) {
                            ((SchemaPropertyImpl)iProp).setMinOccurs(BigInteger.ZERO);
                        }
                        model.put(iProp.getName(), iProp);
                        continue;
                    }
                    assert (oProp.getType().equals(iProp.getType()));
                    StscComplexTypeResolver.mergeProperties(oProp, iProp, asSequence);
                }
            }
            BigInteger min = part.getMinOccurs();
            BigInteger max = part.getMaxOccurs();
            for (SchemaProperty oProp : model.values()) {
                BigInteger minOccurs = oProp.getMinOccurs();
                BigInteger maxOccurs = oProp.getMaxOccurs();
                minOccurs = minOccurs.multiply(min);
                if (max != null && max.equals(BigInteger.ZERO)) {
                    maxOccurs = BigInteger.ZERO;
                } else if (maxOccurs != null && !maxOccurs.equals(BigInteger.ZERO)) {
                    maxOccurs = max == null ? null : maxOccurs.multiply(max);
                }
                ((SchemaPropertyImpl)oProp).setMinOccurs(minOccurs);
                ((SchemaPropertyImpl)oProp).setMaxOccurs(maxOccurs);
            }
        }
        return model;
    }

    static Map buildElementPropertyModel(SchemaLocalElement epart, SchemaType owner) {
        HashMap<QName, SchemaProperty> result = new HashMap<QName, SchemaProperty>(1);
        SchemaProperty sProp = StscComplexTypeResolver.buildUseProperty(epart, owner);
        result.put(sProp.getName(), sProp);
        return result;
    }

    static SchemaProperty buildUseProperty(SchemaField use, SchemaType owner) {
        SchemaPropertyImpl sPropImpl = new SchemaPropertyImpl();
        sPropImpl.setName(use.getName());
        sPropImpl.setContainerTypeRef(owner.getRef());
        sPropImpl.setTypeRef(use.getType().getRef());
        sPropImpl.setAttribute(use.isAttribute());
        sPropImpl.setDefault(use.isDefault() ? 2 : 0);
        sPropImpl.setFixed(use.isFixed() ? 2 : 0);
        sPropImpl.setNillable(use.isNillable() ? 2 : 0);
        sPropImpl.setDefaultText(use.getDefaultText());
        sPropImpl.setMinOccurs(use.getMinOccurs());
        sPropImpl.setMaxOccurs(use.getMaxOccurs());
        if (use instanceof SchemaLocalElementImpl) {
            SchemaLocalElementImpl elt = (SchemaLocalElementImpl)use;
            sPropImpl.setAcceptedNames(elt.acceptedStartNames());
        }
        return sPropImpl;
    }

    static void mergeProperties(SchemaPropertyImpl into, SchemaProperty from, boolean asSequence) {
        BigInteger minOccurs = into.getMinOccurs();
        BigInteger maxOccurs = into.getMaxOccurs();
        if (asSequence) {
            minOccurs = minOccurs.add(from.getMinOccurs());
            if (maxOccurs != null) {
                maxOccurs = from.getMaxOccurs() == null ? null : maxOccurs.add(from.getMaxOccurs());
            }
        } else {
            minOccurs = minOccurs.min(from.getMinOccurs());
            if (maxOccurs != null) {
                maxOccurs = from.getMaxOccurs() == null ? null : maxOccurs.max(from.getMaxOccurs());
            }
        }
        into.setMinOccurs(minOccurs);
        into.setMaxOccurs(maxOccurs);
        if (from.hasNillable() != into.hasNillable()) {
            into.setNillable(1);
        }
        if (from.hasDefault() != into.hasDefault()) {
            into.setDefault(1);
        }
        if (from.hasFixed() != into.hasFixed()) {
            into.setFixed(1);
        }
        if (!(into.getDefaultText() == null || from.getDefaultText() != null && into.getDefaultText().equals(from.getDefaultText()))) {
            into.setDefaultText(null);
        }
    }

    static SchemaParticle[] ensureStateMachine(SchemaParticle[] children) {
        for (int i = 0; i < children.length; ++i) {
            StscComplexTypeResolver.buildStateMachine(children[i]);
        }
        return children;
    }

    static void buildStateMachine(SchemaParticle contentModel) {
        boolean varloop;
        if (contentModel == null) {
            return;
        }
        SchemaParticleImpl partImpl = (SchemaParticleImpl)contentModel;
        if (partImpl.hasTransitionNotes()) {
            return;
        }
        QNameSetBuilder start = new QNameSetBuilder();
        QNameSetBuilder excludenext = new QNameSetBuilder();
        boolean deterministic = true;
        SchemaParticle[] children = null;
        boolean canskip = partImpl.getMinOccurs().signum() == 0;
        block0 : switch (partImpl.getParticleType()) {
            case 4: {
                if (partImpl.hasTransitionRules()) {
                    start.addAll(partImpl.acceptedStartNames());
                    break;
                }
                start.add(partImpl.getName());
                break;
            }
            case 5: {
                start.addAll(partImpl.getWildcardSet());
                break;
            }
            case 3: {
                int i;
                children = StscComplexTypeResolver.ensureStateMachine(partImpl.getParticleChildren());
                canskip = true;
                for (i = 0; canskip && i < children.length; ++i) {
                    if (children[i].isSkippable()) continue;
                    canskip = false;
                }
                for (i = 0; deterministic && i < children.length; ++i) {
                    if (((SchemaParticleImpl)children[i]).isDeterministic()) continue;
                    deterministic = false;
                }
                for (i = 1; i < children.length; ++i) {
                    excludenext.addAll(((SchemaParticleImpl)children[i - 1]).getExcludeNextSet());
                    if (deterministic && !excludenext.isDisjoint(children[i].acceptedStartNames())) {
                        deterministic = false;
                    }
                    if (children[i].isSkippable()) {
                        excludenext.addAll(children[i].acceptedStartNames());
                        continue;
                    }
                    excludenext.clear();
                }
                for (i = 0; i < children.length; ++i) {
                    start.addAll(children[i].acceptedStartNames());
                    if (!children[i].isSkippable()) break block0;
                }
                break;
            }
            case 2: {
                int i;
                children = StscComplexTypeResolver.ensureStateMachine(partImpl.getParticleChildren());
                canskip = false;
                for (i = 0; !canskip && i < children.length; ++i) {
                    if (!children[i].isSkippable()) continue;
                    canskip = true;
                }
                for (i = 0; deterministic && i < children.length; ++i) {
                    if (((SchemaParticleImpl)children[i]).isDeterministic()) continue;
                    deterministic = false;
                }
                for (i = 0; i < children.length; ++i) {
                    if (deterministic && !start.isDisjoint(children[i].acceptedStartNames())) {
                        deterministic = false;
                    }
                    start.addAll(children[i].acceptedStartNames());
                    excludenext.addAll(((SchemaParticleImpl)children[i]).getExcludeNextSet());
                }
                break;
            }
            case 1: {
                int i;
                children = StscComplexTypeResolver.ensureStateMachine(partImpl.getParticleChildren());
                canskip = true;
                for (i = 0; !canskip && i < children.length; ++i) {
                    if (children[i].isSkippable()) continue;
                    canskip = false;
                }
                for (i = 0; deterministic && i < children.length; ++i) {
                    if (((SchemaParticleImpl)children[i]).isDeterministic()) continue;
                    deterministic = false;
                }
                for (i = 0; i < children.length; ++i) {
                    if (deterministic && !start.isDisjoint(children[i].acceptedStartNames())) {
                        deterministic = false;
                    }
                    start.addAll(children[i].acceptedStartNames());
                    excludenext.addAll(((SchemaParticleImpl)children[i]).getExcludeNextSet());
                }
                if (!canskip) break;
                excludenext.addAll(start);
                break;
            }
            default: {
                throw new IllegalStateException("Unrecognized schema particle");
            }
        }
        BigInteger minOccurs = partImpl.getMinOccurs();
        BigInteger maxOccurs = partImpl.getMaxOccurs();
        boolean canloop = maxOccurs == null || maxOccurs.compareTo(BigInteger.ONE) > 0;
        boolean bl = varloop = maxOccurs == null || minOccurs.compareTo(maxOccurs) < 0;
        if (canloop && deterministic && !excludenext.isDisjoint(start)) {
            QNameSet suspectSet = excludenext.intersect(start);
            HashMap startMap = new HashMap();
            StscComplexTypeResolver.particlesMatchingStart(partImpl, suspectSet, startMap, new QNameSetBuilder());
            HashMap afterMap = new HashMap();
            StscComplexTypeResolver.particlesMatchingAfter(partImpl, suspectSet, afterMap, new QNameSetBuilder(), true);
            deterministic = StscComplexTypeResolver.afterMapSubsumedByStartMap(startMap, afterMap);
        }
        if (varloop) {
            excludenext.addAll(start);
        }
        canskip = canskip || minOccurs.signum() == 0;
        partImpl.setTransitionRules(start.toQNameSet(), canskip);
        partImpl.setTransitionNotes(excludenext.toQNameSet(), deterministic);
    }

    private static boolean afterMapSubsumedByStartMap(Map startMap, Map afterMap) {
        if (afterMap.size() > startMap.size()) {
            return false;
        }
        if (afterMap.isEmpty()) {
            return true;
        }
        for (SchemaParticle part : startMap.keySet()) {
            QNameSet afterSet;
            QNameSet startSet;
            if (part.getParticleType() == 5 && afterMap.containsKey(part) && !(startSet = (QNameSet)startMap.get(part)).containsAll(afterSet = (QNameSet)afterMap.get(part))) {
                return false;
            }
            afterMap.remove(part);
            if (!afterMap.isEmpty()) continue;
            return true;
        }
        return afterMap.isEmpty();
    }

    private static void particlesMatchingStart(SchemaParticle part, QNameSetSpecification suspectSet, Map result, QNameSetBuilder eliminate) {
        switch (part.getParticleType()) {
            case 4: {
                if (!suspectSet.contains(part.getName())) {
                    return;
                }
                result.put(part, null);
                eliminate.add(part.getName());
                return;
            }
            case 5: {
                if (suspectSet.isDisjoint(part.getWildcardSet())) {
                    return;
                }
                result.put(part, part.getWildcardSet().intersect(suspectSet));
                eliminate.addAll(part.getWildcardSet());
                return;
            }
            case 1: 
            case 2: {
                SchemaParticle[] children = part.getParticleChildren();
                for (int i = 0; i < children.length; ++i) {
                    StscComplexTypeResolver.particlesMatchingStart(children[i], suspectSet, result, eliminate);
                }
                return;
            }
            case 3: {
                SchemaParticle[] children = part.getParticleChildren();
                if (children.length == 0) {
                    return;
                }
                if (!children[0].isSkippable()) {
                    StscComplexTypeResolver.particlesMatchingStart(children[0], suspectSet, result, eliminate);
                    return;
                }
                QNameSetBuilder remainingSuspects = new QNameSetBuilder(suspectSet);
                QNameSetBuilder suspectsToEliminate = new QNameSetBuilder();
                for (int i = 0; i < children.length; ++i) {
                    StscComplexTypeResolver.particlesMatchingStart(children[i], remainingSuspects, result, suspectsToEliminate);
                    eliminate.addAll(suspectsToEliminate);
                    if (!children[i].isSkippable()) {
                        return;
                    }
                    remainingSuspects.removeAll(suspectsToEliminate);
                    if (remainingSuspects.isEmpty()) {
                        return;
                    }
                    suspectsToEliminate.clear();
                }
                return;
            }
        }
    }

    private static void particlesMatchingAfter(SchemaParticle part, QNameSetSpecification suspectSet, Map result, QNameSetBuilder eliminate, boolean top) {
        block0 : switch (part.getParticleType()) {
            case 1: 
            case 2: {
                SchemaParticle[] children = part.getParticleChildren();
                for (int i = 0; i < children.length; ++i) {
                    StscComplexTypeResolver.particlesMatchingAfter(children[i], suspectSet, result, eliminate, false);
                }
                break;
            }
            case 3: {
                SchemaParticle[] children = part.getParticleChildren();
                if (children.length == 0) break;
                if (!children[children.length - 1].isSkippable()) {
                    StscComplexTypeResolver.particlesMatchingAfter(children[0], suspectSet, result, eliminate, false);
                    break;
                }
                QNameSetBuilder remainingSuspects = new QNameSetBuilder(suspectSet);
                QNameSetBuilder suspectsToEliminate = new QNameSetBuilder();
                for (int i = children.length - 1; i >= 0; --i) {
                    StscComplexTypeResolver.particlesMatchingAfter(children[i], remainingSuspects, result, suspectsToEliminate, false);
                    eliminate.addAll(suspectsToEliminate);
                    if (!children[i].isSkippable()) break block0;
                    remainingSuspects.removeAll(suspectsToEliminate);
                    if (remainingSuspects.isEmpty()) break block0;
                    suspectsToEliminate.clear();
                }
                break;
            }
        }
        if (!top) {
            boolean varloop;
            BigInteger minOccurs = part.getMinOccurs();
            BigInteger maxOccurs = part.getMaxOccurs();
            boolean bl = varloop = maxOccurs == null || minOccurs.compareTo(maxOccurs) < 0;
            if (varloop) {
                StscComplexTypeResolver.particlesMatchingStart(part, suspectSet, result, eliminate);
            }
        }
    }

    private static Map buildParticleCodeMap() {
        HashMap<QName, Integer> result = new HashMap<QName, Integer>();
        for (int i = 0; i < particleCodes.length; ++i) {
            result.put(StscComplexTypeResolver.particleCodes[i].name, new Integer(StscComplexTypeResolver.particleCodes[i].code));
        }
        return result;
    }

    private static int translateParticleCode(Group parseEg) {
        if (parseEg == null) {
            return 0;
        }
        return StscComplexTypeResolver.translateParticleCode(parseEg.newCursor().getName());
    }

    private static int translateParticleCode(QName name) {
        Integer result = (Integer)particleCodeMap.get(name);
        if (result == null) {
            return 0;
        }
        return result;
    }

    private static Map buildAttributeCodeMap() {
        HashMap<QName, Integer> result = new HashMap<QName, Integer>();
        for (int i = 0; i < attributeCodes.length; ++i) {
            result.put(StscComplexTypeResolver.attributeCodes[i].name, new Integer(StscComplexTypeResolver.attributeCodes[i].code));
        }
        return result;
    }

    static int translateAttributeCode(QName currentName) {
        Integer result = (Integer)attributeCodeMap.get(currentName);
        if (result == null) {
            return 0;
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

    private static class RedefinitionForGroup {
        private SchemaModelGroupImpl group;
        private boolean seenRedefinition = false;

        public RedefinitionForGroup(SchemaModelGroupImpl group) {
            this.group = group;
        }

        public SchemaModelGroupImpl getGroup() {
            return this.group;
        }

        public boolean isSeenRedefinition() {
            return this.seenRedefinition;
        }

        public void setSeenRedefinition(boolean seenRedefinition) {
            this.seenRedefinition = seenRedefinition;
        }
    }

    static class WildcardResult {
        QNameSet typedWildcards;
        boolean hasWildcards;

        WildcardResult(QNameSet typedWildcards, boolean hasWildcards) {
            this.typedWildcards = typedWildcards;
            this.hasWildcards = hasWildcards;
        }
    }
}

