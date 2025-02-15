/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.schema;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.QNameSet;
import org.apache.xmlbeans.QNameSetBuilder;
import org.apache.xmlbeans.SchemaAttributeModel;
import org.apache.xmlbeans.SchemaBookmark;
import org.apache.xmlbeans.SchemaGlobalElement;
import org.apache.xmlbeans.SchemaIdentityConstraint;
import org.apache.xmlbeans.SchemaParticle;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlAnySimpleType;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlNonNegativeInteger;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlPositiveInteger;
import org.apache.xmlbeans.impl.common.QNameHelper;
import org.apache.xmlbeans.impl.common.XMLChar;
import org.apache.xmlbeans.impl.common.XPath;
import org.apache.xmlbeans.impl.regex.RegularExpression;
import org.apache.xmlbeans.impl.schema.BuiltinSchemaTypeSystem;
import org.apache.xmlbeans.impl.schema.SchemaAnnotationImpl;
import org.apache.xmlbeans.impl.schema.SchemaAttributeGroupImpl;
import org.apache.xmlbeans.impl.schema.SchemaContainer;
import org.apache.xmlbeans.impl.schema.SchemaGlobalAttributeImpl;
import org.apache.xmlbeans.impl.schema.SchemaGlobalElementImpl;
import org.apache.xmlbeans.impl.schema.SchemaIdentityConstraintImpl;
import org.apache.xmlbeans.impl.schema.SchemaLocalAttributeImpl;
import org.apache.xmlbeans.impl.schema.SchemaLocalElementImpl;
import org.apache.xmlbeans.impl.schema.SchemaModelGroupImpl;
import org.apache.xmlbeans.impl.schema.SchemaTypeImpl;
import org.apache.xmlbeans.impl.schema.StscImporter;
import org.apache.xmlbeans.impl.schema.StscResolver;
import org.apache.xmlbeans.impl.schema.StscState;
import org.apache.xmlbeans.impl.values.NamespaceContext;
import org.apache.xmlbeans.impl.values.XmlNonNegativeIntegerImpl;
import org.apache.xmlbeans.impl.values.XmlPositiveIntegerImpl;
import org.apache.xmlbeans.impl.values.XmlValueOutOfRangeException;
import org.apache.xmlbeans.impl.xb.xsdschema.Annotated;
import org.apache.xmlbeans.impl.xb.xsdschema.AnnotationDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.Attribute;
import org.apache.xmlbeans.impl.xb.xsdschema.AttributeGroup;
import org.apache.xmlbeans.impl.xb.xsdschema.Element;
import org.apache.xmlbeans.impl.xb.xsdschema.FieldDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.FormChoice;
import org.apache.xmlbeans.impl.xb.xsdschema.Keybase;
import org.apache.xmlbeans.impl.xb.xsdschema.KeyrefDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.LocalElement;
import org.apache.xmlbeans.impl.xb.xsdschema.LocalSimpleType;
import org.apache.xmlbeans.impl.xb.xsdschema.NamedAttributeGroup;
import org.apache.xmlbeans.impl.xb.xsdschema.NamedGroup;
import org.apache.xmlbeans.impl.xb.xsdschema.RedefineDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.SimpleType;
import org.apache.xmlbeans.impl.xb.xsdschema.TopLevelAttribute;
import org.apache.xmlbeans.impl.xb.xsdschema.TopLevelComplexType;
import org.apache.xmlbeans.impl.xb.xsdschema.TopLevelElement;
import org.apache.xmlbeans.impl.xb.xsdschema.TopLevelSimpleType;
import org.apache.xmlbeans.soap.SOAPArrayType;

public class StscTranslator {
    private static final QName WSDL_ARRAYTYPE_NAME = QNameHelper.forLNS("arrayType", "http://schemas.xmlsoap.org/wsdl/");
    private static final String FORM_QUALIFIED = "qualified";
    public static final RegularExpression XPATH_REGEXP = new RegularExpression("(\\.//)?((((child::)?((\\i\\c*:)?(\\i\\c*|\\*)))|\\.)/)*((((child::)?((\\i\\c*:)?(\\i\\c*|\\*)))|\\.)|((attribute::|@)((\\i\\c*:)?(\\i\\c*|\\*))))(\\|(\\.//)?((((child::)?((\\i\\c*:)?(\\i\\c*|\\*)))|\\.)/)*((((child::)?((\\i\\c*:)?(\\i\\c*|\\*)))|\\.)|((attribute::|@)((\\i\\c*:)?(\\i\\c*|\\*)))))*", "X");

    public static void addAllDefinitions(StscImporter.SchemaToProcess[] schemasAndChameleons) {
        ArrayList<RedefinitionHolder> redefinitions = new ArrayList<RedefinitionHolder>();
        for (int i = 0; i < schemasAndChameleons.length; ++i) {
            List redefines = schemasAndChameleons[i].getRedefines();
            if (redefines == null) continue;
            List redefineObjects = schemasAndChameleons[i].getRedefineObjects();
            Iterator it = redefines.iterator();
            Iterator ito = redefineObjects.iterator();
            while (it.hasNext()) {
                assert (ito.hasNext()) : "The array of redefines and redefine objects have to have the same length";
                redefinitions.add(new RedefinitionHolder((StscImporter.SchemaToProcess)it.next(), (RedefineDocument.Redefine)ito.next()));
            }
        }
        RedefinitionMaster globalRedefinitions = new RedefinitionMaster(redefinitions.toArray(new RedefinitionHolder[redefinitions.size()]));
        StscState state = StscState.get();
        for (int j = 0; j < schemasAndChameleons.length; ++j) {
            SchemaDocument.Schema schema = schemasAndChameleons[j].getSchema();
            String givenTargetNamespace = schemasAndChameleons[j].getChameleonNamespace();
            if (schema.sizeOfNotationArray() > 0) {
                state.warning("Schema <notation> is not yet supported for this release.", 51, (XmlObject)schema.getNotationArray(0));
            }
            String targetNamespace = schema.getTargetNamespace();
            boolean chameleon = false;
            if (givenTargetNamespace != null && targetNamespace == null) {
                targetNamespace = givenTargetNamespace;
                chameleon = true;
            }
            if (targetNamespace == null) {
                targetNamespace = "";
            }
            if (targetNamespace.length() > 0 || !StscTranslator.isEmptySchema(schema)) {
                state.registerContribution(targetNamespace, schema.documentProperties().getSourceName());
                state.addNewContainer(targetNamespace);
            }
            ArrayList<Annotated> redefChain = new ArrayList<Annotated>();
            TopLevelComplexType[] complexTypes = schema.getComplexTypeArray();
            for (int i = 0; i < complexTypes.length; ++i) {
                TopLevelComplexType redef;
                TopLevelComplexType type = complexTypes[i];
                RedefinitionHolder[] rhArray = globalRedefinitions.getComplexTypeRedefinitions(type.getName(), schemasAndChameleons[j]);
                for (int k = 0; k < rhArray.length; ++k) {
                    if (rhArray[k] == null) continue;
                    redef = rhArray[k].redefineComplexType(type.getName());
                    assert (redef != null);
                    redefChain.add(type);
                    type = redef;
                }
                SchemaTypeImpl t = StscTranslator.translateGlobalComplexType(type, targetNamespace, chameleon, redefChain.size() > 0);
                state.addGlobalType(t, null);
                for (int k = redefChain.size() - 1; k >= 0; --k) {
                    redef = (TopLevelComplexType)redefChain.remove(k);
                    SchemaTypeImpl r = StscTranslator.translateGlobalComplexType(redef, targetNamespace, chameleon, k > 0);
                    state.addGlobalType(r, t);
                    t = r;
                }
            }
            TopLevelSimpleType[] simpleTypes = schema.getSimpleTypeArray();
            for (int i = 0; i < simpleTypes.length; ++i) {
                TopLevelSimpleType redef;
                TopLevelSimpleType type = simpleTypes[i];
                RedefinitionHolder[] rhArray = globalRedefinitions.getSimpleTypeRedefinitions(type.getName(), schemasAndChameleons[j]);
                for (int k = 0; k < rhArray.length; ++k) {
                    if (rhArray[k] == null) continue;
                    redef = rhArray[k].redefineSimpleType(type.getName());
                    assert (redef != null);
                    redefChain.add(type);
                    type = redef;
                }
                SchemaTypeImpl t = StscTranslator.translateGlobalSimpleType(type, targetNamespace, chameleon, redefChain.size() > 0);
                state.addGlobalType(t, null);
                for (int k = redefChain.size() - 1; k >= 0; --k) {
                    redef = (TopLevelSimpleType)redefChain.remove(k);
                    SchemaTypeImpl r = StscTranslator.translateGlobalSimpleType(redef, targetNamespace, chameleon, k > 0);
                    state.addGlobalType(r, t);
                    t = r;
                }
            }
            TopLevelElement[] elements = schema.getElementArray();
            for (int i = 0; i < elements.length; ++i) {
                TopLevelElement element = elements[i];
                state.addDocumentType(StscTranslator.translateDocumentType(element, targetNamespace, chameleon), QNameHelper.forLNS(element.getName(), targetNamespace));
            }
            TopLevelAttribute[] attributes = schema.getAttributeArray();
            for (int i = 0; i < attributes.length; ++i) {
                TopLevelAttribute attribute = attributes[i];
                state.addAttributeType(StscTranslator.translateAttributeType(attribute, targetNamespace, chameleon), QNameHelper.forLNS(attribute.getName(), targetNamespace));
            }
            NamedGroup[] modelgroups = schema.getGroupArray();
            for (int i = 0; i < modelgroups.length; ++i) {
                NamedGroup redef;
                NamedGroup group = modelgroups[i];
                RedefinitionHolder[] rhArray = globalRedefinitions.getModelGroupRedefinitions(group.getName(), schemasAndChameleons[j]);
                for (int k = 0; k < rhArray.length; ++k) {
                    if (rhArray[k] == null) continue;
                    redef = rhArray[k].redefineModelGroup(group.getName());
                    assert (redef != null);
                    redefChain.add(group);
                    group = redef;
                }
                SchemaModelGroupImpl g = StscTranslator.translateModelGroup(group, targetNamespace, chameleon, redefChain.size() > 0);
                state.addModelGroup(g, null);
                for (int k = redefChain.size() - 1; k >= 0; --k) {
                    redef = (NamedGroup)redefChain.remove(k);
                    SchemaModelGroupImpl r = StscTranslator.translateModelGroup(redef, targetNamespace, chameleon, k > 0);
                    state.addModelGroup(r, g);
                    g = r;
                }
            }
            NamedAttributeGroup[] attrgroups = schema.getAttributeGroupArray();
            for (int i = 0; i < attrgroups.length; ++i) {
                NamedAttributeGroup redef;
                NamedAttributeGroup group = attrgroups[i];
                RedefinitionHolder[] rhArray = globalRedefinitions.getAttributeGroupRedefinitions(group.getName(), schemasAndChameleons[j]);
                for (int k = 0; k < rhArray.length; ++k) {
                    if (rhArray[k] == null) continue;
                    redef = rhArray[k].redefineAttributeGroup(group.getName());
                    assert (redef != null);
                    redefChain.add(group);
                    group = redef;
                }
                SchemaAttributeGroupImpl g = StscTranslator.translateAttributeGroup(group, targetNamespace, chameleon, redefChain.size() > 0);
                state.addAttributeGroup(g, null);
                for (int k = redefChain.size() - 1; k >= 0; --k) {
                    redef = (NamedAttributeGroup)redefChain.remove(k);
                    SchemaAttributeGroupImpl r = StscTranslator.translateAttributeGroup(redef, targetNamespace, chameleon, k > 0);
                    state.addAttributeGroup(r, g);
                    g = r;
                }
            }
            AnnotationDocument.Annotation[] annotations = schema.getAnnotationArray();
            for (int i = 0; i < annotations.length; ++i) {
                state.addAnnotation(SchemaAnnotationImpl.getAnnotation(state.getContainer(targetNamespace), schema, annotations[i]), targetNamespace);
            }
        }
        for (int i = 0; i < redefinitions.size(); ++i) {
            ((RedefinitionHolder)redefinitions.get(i)).complainAboutMissingDefinitions();
        }
    }

    private static String findFilename(XmlObject xobj) {
        return StscState.get().sourceNameForUri(xobj.documentProperties().getSourceName());
    }

    private static SchemaTypeImpl translateDocumentType(TopLevelElement xsdType, String targetNamespace, boolean chameleon) {
        SchemaTypeImpl sType = new SchemaTypeImpl(StscState.get().getContainer(targetNamespace));
        sType.setDocumentType(true);
        sType.setParseContext(xsdType, targetNamespace, chameleon, null, null, false);
        sType.setFilename(StscTranslator.findFilename(xsdType));
        return sType;
    }

    private static SchemaTypeImpl translateAttributeType(TopLevelAttribute xsdType, String targetNamespace, boolean chameleon) {
        SchemaTypeImpl sType = new SchemaTypeImpl(StscState.get().getContainer(targetNamespace));
        sType.setAttributeType(true);
        sType.setParseContext(xsdType, targetNamespace, chameleon, null, null, false);
        sType.setFilename(StscTranslator.findFilename(xsdType));
        return sType;
    }

    private static SchemaTypeImpl translateGlobalComplexType(TopLevelComplexType xsdType, String targetNamespace, boolean chameleon, boolean redefinition) {
        QName name;
        StscState state = StscState.get();
        String localname = xsdType.getName();
        if (localname == null) {
            state.error("missing-name", new Object[]{"global type"}, (XmlObject)xsdType);
            return null;
        }
        if (!XMLChar.isValidNCName(localname)) {
            state.error("invalid-value", new Object[]{localname, "name"}, (XmlObject)xsdType.xgetName());
        }
        if (StscTranslator.isReservedTypeName(name = QNameHelper.forLNS(localname, targetNamespace))) {
            state.warning("reserved-type-name", new Object[]{QNameHelper.pretty(name)}, (XmlObject)xsdType);
            return null;
        }
        SchemaTypeImpl sType = new SchemaTypeImpl(state.getContainer(targetNamespace));
        sType.setParseContext(xsdType, targetNamespace, chameleon, null, null, redefinition);
        sType.setFilename(StscTranslator.findFilename(xsdType));
        sType.setName(QNameHelper.forLNS(localname, targetNamespace));
        sType.setAnnotation(SchemaAnnotationImpl.getAnnotation(state.getContainer(targetNamespace), xsdType));
        sType.setUserData(StscTranslator.getUserData(xsdType));
        return sType;
    }

    private static SchemaTypeImpl translateGlobalSimpleType(TopLevelSimpleType xsdType, String targetNamespace, boolean chameleon, boolean redefinition) {
        QName name;
        StscState state = StscState.get();
        String localname = xsdType.getName();
        if (localname == null) {
            state.error("missing-name", new Object[]{"global type"}, (XmlObject)xsdType);
            return null;
        }
        if (!XMLChar.isValidNCName(localname)) {
            state.error("invalid-value", new Object[]{localname, "name"}, (XmlObject)xsdType.xgetName());
        }
        if (StscTranslator.isReservedTypeName(name = QNameHelper.forLNS(localname, targetNamespace))) {
            state.warning("reserved-type-name", new Object[]{QNameHelper.pretty(name)}, (XmlObject)xsdType);
            return null;
        }
        SchemaTypeImpl sType = new SchemaTypeImpl(state.getContainer(targetNamespace));
        sType.setSimpleType(true);
        sType.setParseContext(xsdType, targetNamespace, chameleon, null, null, redefinition);
        sType.setFilename(StscTranslator.findFilename(xsdType));
        sType.setName(name);
        sType.setAnnotation(SchemaAnnotationImpl.getAnnotation(state.getContainer(targetNamespace), xsdType));
        sType.setUserData(StscTranslator.getUserData(xsdType));
        return sType;
    }

    static SchemaTypeImpl translateAnonymousSimpleType(SimpleType typedef, String targetNamespace, boolean chameleon, String elemFormDefault, String attFormDefault, List anonymousTypes, SchemaType outerType) {
        StscState state = StscState.get();
        SchemaTypeImpl sType = new SchemaTypeImpl(state.getContainer(targetNamespace));
        sType.setSimpleType(true);
        sType.setParseContext(typedef, targetNamespace, chameleon, elemFormDefault, attFormDefault, false);
        sType.setOuterSchemaTypeRef(outerType.getRef());
        sType.setAnnotation(SchemaAnnotationImpl.getAnnotation(state.getContainer(targetNamespace), typedef));
        sType.setUserData(StscTranslator.getUserData(typedef));
        anonymousTypes.add(sType);
        return sType;
    }

    static FormChoice findElementFormDefault(XmlObject obj) {
        XmlCursor cur = obj.newCursor();
        while (cur.getObject().schemaType() != SchemaDocument.Schema.type) {
            if (cur.toParent()) continue;
            return null;
        }
        return ((SchemaDocument.Schema)cur.getObject()).xgetElementFormDefault();
    }

    public static boolean uriMatch(String s1, String s2) {
        if (s1 == null) {
            return s2 == null || s2.equals("");
        }
        if (s2 == null) {
            return s1.equals("");
        }
        return s1.equals(s2);
    }

    public static void copyGlobalElementToLocalElement(SchemaGlobalElement referenced, SchemaLocalElementImpl target) {
        target.setNameAndTypeRef(referenced.getName(), referenced.getType().getRef());
        target.setNillable(referenced.isNillable());
        target.setDefault(referenced.getDefaultText(), referenced.isFixed(), ((SchemaGlobalElementImpl)referenced).getParseObject());
        target.setIdentityConstraints(((SchemaLocalElementImpl)((Object)referenced)).getIdentityConstraintRefs());
        target.setBlock(referenced.blockExtension(), referenced.blockRestriction(), referenced.blockSubstitution());
        target.setAbstract(referenced.isAbstract());
        target.setTransitionRules(((SchemaParticle)((Object)referenced)).acceptedStartNames(), ((SchemaParticle)((Object)referenced)).isSkippable());
        target.setAnnotation(referenced.getAnnotation());
    }

    public static void copyGlobalAttributeToLocalAttribute(SchemaGlobalAttributeImpl referenced, SchemaLocalAttributeImpl target) {
        target.init(referenced.getName(), referenced.getTypeRef(), referenced.getUse(), referenced.getDefaultText(), referenced.getParseObject(), referenced._defaultValue, referenced.isFixed(), referenced.getWSDLArrayType(), referenced.getAnnotation(), null);
    }

    public static SchemaLocalElementImpl translateElement(Element xsdElt, String targetNamespace, boolean chameleon, String elemFormDefault, String attFormDefault, List anonymousTypes, SchemaType outerType) {
        SchemaGlobalElementImpl head;
        QName qname;
        SchemaLocalElementImpl impl;
        StscState state = StscState.get();
        SchemaTypeImpl sgHead = null;
        if (xsdElt.isSetSubstitutionGroup() && (sgHead = state.findDocumentType(xsdElt.getSubstitutionGroup(), ((SchemaTypeImpl)outerType).getChameleonNamespace(), targetNamespace)) != null) {
            StscResolver.resolveType(sgHead);
        }
        String name = xsdElt.getName();
        QName ref = xsdElt.getRef();
        if (ref != null && name != null) {
            state.error("src-element.2.1a", new Object[]{name}, (XmlObject)xsdElt.xgetRef());
            name = null;
        }
        if (ref == null && name == null) {
            state.error("src-element.2.1b", null, (XmlObject)xsdElt);
            return null;
        }
        if (name != null && !XMLChar.isValidNCName(name)) {
            state.error("invalid-value", new Object[]{name, "name"}, (XmlObject)xsdElt.xgetName());
        }
        if (ref != null) {
            if (xsdElt.getType() != null) {
                state.error("src-element.2.2", new Object[]{"type"}, (XmlObject)xsdElt.xgetType());
            }
            if (xsdElt.getSimpleType() != null) {
                state.error("src-element.2.2", new Object[]{"<simpleType>"}, (XmlObject)xsdElt.getSimpleType());
            }
            if (xsdElt.getComplexType() != null) {
                state.error("src-element.2.2", new Object[]{"<complexType>"}, (XmlObject)xsdElt.getComplexType());
            }
            if (xsdElt.getForm() != null) {
                state.error("src-element.2.2", new Object[]{"form"}, (XmlObject)xsdElt.xgetForm());
            }
            if (xsdElt.sizeOfKeyArray() > 0) {
                state.warning("src-element.2.2", new Object[]{"<key>"}, (XmlObject)xsdElt);
            }
            if (xsdElt.sizeOfKeyrefArray() > 0) {
                state.warning("src-element.2.2", new Object[]{"<keyref>"}, (XmlObject)xsdElt);
            }
            if (xsdElt.sizeOfUniqueArray() > 0) {
                state.warning("src-element.2.2", new Object[]{"<unique>"}, (XmlObject)xsdElt);
            }
            if (xsdElt.isSetDefault()) {
                state.warning("src-element.2.2", new Object[]{"default"}, (XmlObject)xsdElt.xgetDefault());
            }
            if (xsdElt.isSetFixed()) {
                state.warning("src-element.2.2", new Object[]{"fixed"}, (XmlObject)xsdElt.xgetFixed());
            }
            if (xsdElt.isSetBlock()) {
                state.warning("src-element.2.2", new Object[]{"block"}, (XmlObject)xsdElt.xgetBlock());
            }
            if (xsdElt.isSetNillable()) {
                state.warning("src-element.2.2", new Object[]{"nillable"}, (XmlObject)xsdElt.xgetNillable());
            }
            assert (xsdElt instanceof LocalElement);
            SchemaGlobalElementImpl referenced = state.findGlobalElement(ref, chameleon ? targetNamespace : null, targetNamespace);
            if (referenced == null) {
                state.notFoundError(ref, 1, xsdElt.xgetRef(), true);
                return null;
            }
            SchemaLocalElementImpl target = new SchemaLocalElementImpl();
            target.setParticleType(4);
            target.setUserData(StscTranslator.getUserData(xsdElt));
            StscTranslator.copyGlobalElementToLocalElement(referenced, target);
            return target;
        }
        SchemaType sType = null;
        if (xsdElt instanceof LocalElement) {
            impl = new SchemaLocalElementImpl();
            boolean qualified = false;
            FormChoice form = xsdElt.xgetForm();
            qualified = form != null ? form.getStringValue().equals(FORM_QUALIFIED) : (elemFormDefault != null ? elemFormDefault.equals(FORM_QUALIFIED) : (form = StscTranslator.findElementFormDefault(xsdElt)) != null && form.getStringValue().equals(FORM_QUALIFIED));
            qname = qualified ? QNameHelper.forLNS(name, targetNamespace) : QNameHelper.forLN(name);
        } else {
            SchemaGlobalElementImpl head2;
            SchemaGlobalElementImpl gelt = new SchemaGlobalElementImpl(state.getContainer(targetNamespace));
            impl = gelt;
            if (sgHead != null && (head2 = state.findGlobalElement(xsdElt.getSubstitutionGroup(), chameleon ? targetNamespace : null, targetNamespace)) != null) {
                gelt.setSubstitutionGroup(head2.getRef());
            }
            qname = QNameHelper.forLNS(name, targetNamespace);
            SchemaTypeImpl docType = (SchemaTypeImpl)outerType;
            QName[] sgMembers = docType.getSubstitutionGroupMembers();
            QNameSetBuilder transitionRules = new QNameSetBuilder();
            transitionRules.add(qname);
            for (int i = 0; i < sgMembers.length; ++i) {
                gelt.addSubstitutionGroupMember(sgMembers[i]);
                transitionRules.add(sgMembers[i]);
            }
            impl.setTransitionRules(QNameSet.forSpecification(transitionRules), false);
            impl.setTransitionNotes(QNameSet.EMPTY, true);
            boolean finalExt = false;
            boolean finalRest = false;
            Object ds = xsdElt.getFinal();
            if (ds != null) {
                if (ds instanceof String && ds.equals("#all")) {
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
            gelt.setFinal(finalExt, finalRest);
            gelt.setAbstract(xsdElt.getAbstract());
            gelt.setFilename(StscTranslator.findFilename(xsdElt));
            gelt.setParseContext(xsdElt, targetNamespace, chameleon);
        }
        SchemaAnnotationImpl ann = SchemaAnnotationImpl.getAnnotation(state.getContainer(targetNamespace), xsdElt);
        impl.setAnnotation(ann);
        impl.setUserData(StscTranslator.getUserData(xsdElt));
        if (xsdElt.getType() != null && (sType = state.findGlobalType(xsdElt.getType(), chameleon ? targetNamespace : null, targetNamespace)) == null) {
            state.notFoundError(xsdElt.getType(), 0, xsdElt.xgetType(), true);
        }
        boolean simpleTypedef = false;
        Annotated typedef = xsdElt.getComplexType();
        if (typedef == null) {
            typedef = xsdElt.getSimpleType();
            simpleTypedef = true;
        }
        if (sType != null && typedef != null) {
            state.error("src-element.3", null, (XmlObject)typedef);
            typedef = null;
        }
        if (typedef != null) {
            Object[] grps = state.getCurrentProcessing();
            QName[] context = new QName[grps.length];
            for (int i = 0; i < context.length; ++i) {
                if (!(grps[i] instanceof SchemaModelGroupImpl)) continue;
                context[i] = ((SchemaModelGroupImpl)grps[i]).getName();
            }
            SchemaType repeat = StscTranslator.checkRecursiveGroupReference(context, qname, (SchemaTypeImpl)outerType);
            if (repeat != null) {
                sType = repeat;
            } else {
                SchemaTypeImpl sTypeImpl = new SchemaTypeImpl(state.getContainer(targetNamespace));
                sType = sTypeImpl;
                sTypeImpl.setContainerField(impl);
                sTypeImpl.setOuterSchemaTypeRef(outerType == null ? null : outerType.getRef());
                sTypeImpl.setGroupReferenceContext(context);
                anonymousTypes.add(sType);
                sTypeImpl.setSimpleType(simpleTypedef);
                sTypeImpl.setParseContext(typedef, targetNamespace, chameleon, elemFormDefault, attFormDefault, false);
                sTypeImpl.setAnnotation(SchemaAnnotationImpl.getAnnotation(state.getContainer(targetNamespace), typedef));
                sTypeImpl.setUserData(StscTranslator.getUserData(typedef));
            }
        }
        if (sType == null && sgHead != null && (head = state.findGlobalElement(xsdElt.getSubstitutionGroup(), chameleon ? targetNamespace : null, targetNamespace)) != null) {
            sType = head.getType();
        }
        if (sType == null) {
            sType = BuiltinSchemaTypeSystem.ST_ANY_TYPE;
        }
        SOAPArrayType wat = null;
        XmlCursor c = xsdElt.newCursor();
        String arrayType = c.getAttributeText(WSDL_ARRAYTYPE_NAME);
        c.dispose();
        if (arrayType != null) {
            try {
                wat = new SOAPArrayType(arrayType, new NamespaceContext(xsdElt));
            } catch (XmlValueOutOfRangeException e) {
                state.error("soaparray", new Object[]{arrayType}, (XmlObject)xsdElt);
            }
        }
        impl.setWsdlArrayType(wat);
        boolean isFixed = xsdElt.isSetFixed();
        if (xsdElt.isSetDefault() && isFixed) {
            state.error("src-element.1", null, (XmlObject)xsdElt.xgetFixed());
            isFixed = false;
        }
        impl.setParticleType(4);
        impl.setNameAndTypeRef(qname, sType.getRef());
        impl.setNillable(xsdElt.getNillable());
        impl.setDefault(isFixed ? xsdElt.getFixed() : xsdElt.getDefault(), isFixed, xsdElt);
        Object block = xsdElt.getBlock();
        boolean blockExt = false;
        boolean blockRest = false;
        boolean blockSubst = false;
        if (block != null) {
            if (block instanceof String && block.equals("#all")) {
                blockSubst = true;
                blockRest = true;
                blockExt = true;
            } else if (block instanceof List) {
                if (((List)block).contains("extension")) {
                    blockExt = true;
                }
                if (((List)block).contains("restriction")) {
                    blockRest = true;
                }
                if (((List)block).contains("substitution")) {
                    blockSubst = true;
                }
            }
        }
        impl.setBlock(blockExt, blockRest, blockSubst);
        boolean constraintFailed = false;
        int length = xsdElt.sizeOfKeyArray() + xsdElt.sizeOfKeyrefArray() + xsdElt.sizeOfUniqueArray();
        SchemaIdentityConstraintImpl[] constraints = new SchemaIdentityConstraintImpl[length];
        int cur = 0;
        Keybase[] keys = xsdElt.getKeyArray();
        int i = 0;
        while (i < keys.length) {
            constraints[cur] = StscTranslator.translateIdentityConstraint(keys[i], targetNamespace, chameleon);
            if (constraints[cur] != null) {
                constraints[cur].setConstraintCategory(1);
            } else {
                constraintFailed = true;
            }
            ++i;
            ++cur;
        }
        Keybase[] uc = xsdElt.getUniqueArray();
        int i2 = 0;
        while (i2 < uc.length) {
            constraints[cur] = StscTranslator.translateIdentityConstraint(uc[i2], targetNamespace, chameleon);
            if (constraints[cur] != null) {
                constraints[cur].setConstraintCategory(3);
            } else {
                constraintFailed = true;
            }
            ++i2;
            ++cur;
        }
        KeyrefDocument.Keyref[] krs = xsdElt.getKeyrefArray();
        int i3 = 0;
        while (i3 < krs.length) {
            constraints[cur] = StscTranslator.translateIdentityConstraint(krs[i3], targetNamespace, chameleon);
            if (constraints[cur] != null) {
                constraints[cur].setConstraintCategory(2);
            } else {
                constraintFailed = true;
            }
            ++i3;
            ++cur;
        }
        if (!constraintFailed) {
            SchemaIdentityConstraint.Ref[] refs = new SchemaIdentityConstraint.Ref[length];
            for (int i4 = 0; i4 < refs.length; ++i4) {
                refs[i4] = constraints[i4].getRef();
            }
            impl.setIdentityConstraints(refs);
        }
        return impl;
    }

    private static SchemaType checkRecursiveGroupReference(QName[] context, QName containingElement, SchemaTypeImpl outerType) {
        if (context.length < 1) {
            return null;
        }
        for (SchemaTypeImpl type = outerType; type != null; type = (SchemaTypeImpl)type.getOuterType()) {
            QName[] outerContext;
            if (type.getName() != null || type.isDocumentType()) {
                return null;
            }
            if (!containingElement.equals(type.getContainerField().getName()) || (outerContext = type.getGroupReferenceContext()) == null || outerContext.length != context.length) continue;
            boolean equal = true;
            for (int i = 0; i < context.length; ++i) {
                if (context[i] == null && outerContext[i] == null || context[i] != null && context[i].equals(outerContext[i])) continue;
                equal = false;
                break;
            }
            if (!equal) continue;
            return type;
        }
        return null;
    }

    private static String removeWhitespace(String xpath) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < xpath.length(); ++i) {
            char ch = xpath.charAt(i);
            if (XMLChar.isSpace(ch)) continue;
            sb.append(ch);
        }
        return sb.toString();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private static boolean checkXPathSyntax(String xpath) {
        if (xpath == null) {
            return false;
        }
        xpath = StscTranslator.removeWhitespace(xpath);
        RegularExpression regularExpression = XPATH_REGEXP;
        synchronized (regularExpression) {
            return XPATH_REGEXP.matches(xpath);
        }
    }

    private static SchemaIdentityConstraintImpl translateIdentityConstraint(Keybase parseIC, String targetNamespace, boolean chameleon) {
        String selector;
        StscState state = StscState.get();
        String string = selector = parseIC.getSelector() == null ? null : parseIC.getSelector().getXpath();
        if (!StscTranslator.checkXPathSyntax(selector)) {
            state.error("c-selector-xpath", new Object[]{selector}, (XmlObject)parseIC.getSelector().xgetXpath());
            return null;
        }
        FieldDocument.Field[] fieldElts = parseIC.getFieldArray();
        for (int j = 0; j < fieldElts.length; ++j) {
            if (StscTranslator.checkXPathSyntax(fieldElts[j].getXpath())) continue;
            state.error("c-fields-xpaths", new Object[]{fieldElts[j].getXpath()}, (XmlObject)fieldElts[j].xgetXpath());
            return null;
        }
        SchemaIdentityConstraintImpl ic = new SchemaIdentityConstraintImpl(state.getContainer(targetNamespace));
        ic.setName(QNameHelper.forLNS(parseIC.getName(), targetNamespace));
        ic.setSelector(parseIC.getSelector().getXpath());
        ic.setParseContext(parseIC, targetNamespace, chameleon);
        SchemaAnnotationImpl ann = SchemaAnnotationImpl.getAnnotation(state.getContainer(targetNamespace), parseIC);
        ic.setAnnotation(ann);
        ic.setUserData(StscTranslator.getUserData(parseIC));
        XmlCursor c = parseIC.newCursor();
        HashMap nsMap = new HashMap();
        c.getAllNamespaces(nsMap);
        nsMap.remove("");
        ic.setNSMap(nsMap);
        c.dispose();
        String[] fields = new String[fieldElts.length];
        for (int j = 0; j < fields.length; ++j) {
            fields[j] = fieldElts[j].getXpath();
        }
        ic.setFields(fields);
        try {
            ic.buildPaths();
        } catch (XPath.XPathCompileException e) {
            state.error("invalid-xpath", new Object[]{e.getMessage()}, (XmlObject)parseIC);
            return null;
        }
        state.addIdConstraint(ic);
        ic.setFilename(StscTranslator.findFilename(parseIC));
        return state.findIdConstraint(ic.getName(), targetNamespace, null);
    }

    public static SchemaModelGroupImpl translateModelGroup(NamedGroup namedGroup, String targetNamespace, boolean chameleon, boolean redefinition) {
        String name = namedGroup.getName();
        if (name == null) {
            StscState.get().error("missing-name", new Object[]{"model group"}, (XmlObject)namedGroup);
            return null;
        }
        SchemaContainer c = StscState.get().getContainer(targetNamespace);
        SchemaModelGroupImpl result = new SchemaModelGroupImpl(c);
        SchemaAnnotationImpl ann = SchemaAnnotationImpl.getAnnotation(c, namedGroup);
        FormChoice elemFormDefault = StscTranslator.findElementFormDefault(namedGroup);
        FormChoice attFormDefault = StscTranslator.findAttributeFormDefault(namedGroup);
        result.init(QNameHelper.forLNS(name, targetNamespace), targetNamespace, chameleon, elemFormDefault == null ? null : elemFormDefault.getStringValue(), attFormDefault == null ? null : attFormDefault.getStringValue(), redefinition, namedGroup, ann, StscTranslator.getUserData(namedGroup));
        result.setFilename(StscTranslator.findFilename(namedGroup));
        return result;
    }

    public static SchemaAttributeGroupImpl translateAttributeGroup(AttributeGroup attrGroup, String targetNamespace, boolean chameleon, boolean redefinition) {
        String name = attrGroup.getName();
        if (name == null) {
            StscState.get().error("missing-name", new Object[]{"attribute group"}, (XmlObject)attrGroup);
            return null;
        }
        SchemaContainer c = StscState.get().getContainer(targetNamespace);
        SchemaAttributeGroupImpl result = new SchemaAttributeGroupImpl(c);
        SchemaAnnotationImpl ann = SchemaAnnotationImpl.getAnnotation(c, attrGroup);
        FormChoice formDefault = StscTranslator.findAttributeFormDefault(attrGroup);
        result.init(QNameHelper.forLNS(name, targetNamespace), targetNamespace, chameleon, formDefault == null ? null : formDefault.getStringValue(), redefinition, attrGroup, ann, StscTranslator.getUserData(attrGroup));
        result.setFilename(StscTranslator.findFilename(attrGroup));
        return result;
    }

    static FormChoice findAttributeFormDefault(XmlObject obj) {
        XmlCursor cur = obj.newCursor();
        while (cur.getObject().schemaType() != SchemaDocument.Schema.type) {
            if (cur.toParent()) continue;
            return null;
        }
        return ((SchemaDocument.Schema)cur.getObject()).xgetAttributeFormDefault();
    }

    static SchemaLocalAttributeImpl translateAttribute(Attribute xsdAttr, String targetNamespace, String formDefault, boolean chameleon, List anonymousTypes, SchemaType outerType, SchemaAttributeModel baseModel, boolean local) {
        QName qname;
        SchemaLocalAttributeImpl sAttr;
        StscState state = StscState.get();
        String name = xsdAttr.getName();
        QName ref = xsdAttr.getRef();
        if (ref != null && name != null) {
            if (name.equals(ref.getLocalPart()) && StscTranslator.uriMatch(targetNamespace, ref.getNamespaceURI())) {
                state.warning("src-attribute.3.1a", new Object[]{name}, (XmlObject)xsdAttr.xgetRef());
            } else {
                state.error("src-attribute.3.1a", new Object[]{name}, (XmlObject)xsdAttr.xgetRef());
            }
            name = null;
        }
        if (ref == null && name == null) {
            state.error("src-attribute.3.1b", null, (XmlObject)xsdAttr);
            return null;
        }
        if (name != null && !XMLChar.isValidNCName(name)) {
            state.error("invalid-value", new Object[]{name, "name"}, (XmlObject)xsdAttr.xgetName());
        }
        boolean isFixed = false;
        String deftext = null;
        String fmrfixedtext = null;
        SchemaType sType = null;
        int use = 2;
        if (local) {
            sAttr = new SchemaLocalAttributeImpl();
        } else {
            sAttr = new SchemaGlobalAttributeImpl(state.get().getContainer(targetNamespace));
            ((SchemaGlobalAttributeImpl)sAttr).setParseContext(xsdAttr, targetNamespace, chameleon);
        }
        if (ref != null) {
            SchemaGlobalAttributeImpl referenced;
            if (xsdAttr.getType() != null) {
                state.error("src-attribute.3.2", new Object[]{"type"}, (XmlObject)xsdAttr.xgetType());
            }
            if (xsdAttr.getSimpleType() != null) {
                state.error("src-attribute.3.2", new Object[]{"<simpleType>"}, (XmlObject)xsdAttr.getSimpleType());
            }
            if (xsdAttr.getForm() != null) {
                state.error("src-attribute.3.2", new Object[]{"form"}, (XmlObject)xsdAttr.xgetForm());
            }
            if ((referenced = state.findGlobalAttribute(ref, chameleon ? targetNamespace : null, targetNamespace)) == null) {
                state.notFoundError(ref, 3, xsdAttr.xgetRef(), true);
                return null;
            }
            qname = ref;
            use = referenced.getUse();
            sType = referenced.getType();
            deftext = referenced.getDefaultText();
            if (deftext != null && (isFixed = referenced.isFixed())) {
                fmrfixedtext = deftext;
            }
        } else {
            if (local) {
                boolean qualified = false;
                FormChoice form = xsdAttr.xgetForm();
                qualified = form != null ? form.getStringValue().equals(FORM_QUALIFIED) : (formDefault != null ? formDefault.equals(FORM_QUALIFIED) : (form = StscTranslator.findAttributeFormDefault(xsdAttr)) != null && form.getStringValue().equals(FORM_QUALIFIED));
                qname = qualified ? QNameHelper.forLNS(name, targetNamespace) : QNameHelper.forLN(name);
            } else {
                qname = QNameHelper.forLNS(name, targetNamespace);
            }
            if (xsdAttr.getType() != null && (sType = state.findGlobalType(xsdAttr.getType(), chameleon ? targetNamespace : null, targetNamespace)) == null) {
                state.notFoundError(xsdAttr.getType(), 0, xsdAttr.xgetType(), true);
            }
            if (qname.getNamespaceURI().equals("http://www.w3.org/2001/XMLSchema-instance")) {
                state.error("no-xsi", new Object[]{"http://www.w3.org/2001/XMLSchema-instance"}, (XmlObject)xsdAttr.xgetName());
            }
            if (qname.getNamespaceURI().length() == 0 && qname.getLocalPart().equals("xmlns")) {
                state.error("no-xmlns", null, (XmlObject)xsdAttr.xgetName());
            }
            LocalSimpleType typedef = xsdAttr.getSimpleType();
            if (sType != null && typedef != null) {
                state.error("src-attribute.4", null, (XmlObject)typedef);
                typedef = null;
            }
            if (typedef != null) {
                SchemaTypeImpl sTypeImpl = new SchemaTypeImpl(state.getContainer(targetNamespace));
                sType = sTypeImpl;
                sTypeImpl.setContainerField(sAttr);
                sTypeImpl.setOuterSchemaTypeRef(outerType == null ? null : outerType.getRef());
                anonymousTypes.add(sType);
                sTypeImpl.setSimpleType(true);
                sTypeImpl.setParseContext(typedef, targetNamespace, chameleon, null, null, false);
                sTypeImpl.setAnnotation(SchemaAnnotationImpl.getAnnotation(state.getContainer(targetNamespace), typedef));
                sTypeImpl.setUserData(StscTranslator.getUserData(typedef));
            }
            if (sType == null && baseModel != null && baseModel.getAttribute(qname) != null) {
                sType = baseModel.getAttribute(qname).getType();
            }
        }
        if (sType == null) {
            sType = BuiltinSchemaTypeSystem.ST_ANY_SIMPLE;
        }
        if (!sType.isSimpleType()) {
            state.error("Attributes must have a simple type (not complex).", 46, (XmlObject)xsdAttr);
            sType = BuiltinSchemaTypeSystem.ST_ANY_SIMPLE;
        }
        if (xsdAttr.isSetUse() && (use = StscTranslator.translateUseCode(xsdAttr.xgetUse())) != 2 && !isFixed) {
            deftext = null;
        }
        if (xsdAttr.isSetDefault() || xsdAttr.isSetFixed()) {
            if (isFixed && !xsdAttr.isSetFixed()) {
                state.error("A use of a fixed attribute definition must also be fixed", 9, (XmlObject)xsdAttr.xgetFixed());
            }
            isFixed = xsdAttr.isSetFixed();
            if (xsdAttr.isSetDefault() && isFixed) {
                state.error("src-attribute.1", null, (XmlObject)xsdAttr.xgetFixed());
                isFixed = false;
            }
            String string = deftext = isFixed ? xsdAttr.getFixed() : xsdAttr.getDefault();
            if (fmrfixedtext != null && !fmrfixedtext.equals(deftext)) {
                state.error("au-value_constraint", null, (XmlObject)xsdAttr.xgetFixed());
                deftext = fmrfixedtext;
            }
        }
        if (!local) {
            ((SchemaGlobalAttributeImpl)sAttr).setFilename(StscTranslator.findFilename(xsdAttr));
        }
        SOAPArrayType wat = null;
        XmlCursor c = xsdAttr.newCursor();
        String arrayType = c.getAttributeText(WSDL_ARRAYTYPE_NAME);
        c.dispose();
        if (arrayType != null) {
            try {
                wat = new SOAPArrayType(arrayType, new NamespaceContext(xsdAttr));
            } catch (XmlValueOutOfRangeException e) {
                state.error("soaparray", new Object[]{arrayType}, (XmlObject)xsdAttr);
            }
        }
        SchemaAnnotationImpl ann = SchemaAnnotationImpl.getAnnotation(state.getContainer(targetNamespace), xsdAttr);
        sAttr.init(qname, sType.getRef(), use, deftext, xsdAttr, null, isFixed, wat, ann, StscTranslator.getUserData(xsdAttr));
        return sAttr;
    }

    static int translateUseCode(Attribute.Use attruse) {
        if (attruse == null) {
            return 2;
        }
        String val = attruse.getStringValue();
        if (val.equals("optional")) {
            return 2;
        }
        if (val.equals("required")) {
            return 3;
        }
        if (val.equals("prohibited")) {
            return 1;
        }
        return 2;
    }

    static BigInteger buildBigInt(XmlAnySimpleType value) {
        BigInteger bigInt;
        if (value == null) {
            return null;
        }
        String text = value.getStringValue();
        try {
            bigInt = new BigInteger(text);
        } catch (NumberFormatException e) {
            StscState.get().error("invalid-value-detail", new Object[]{text, "nonNegativeInteger", e.getMessage()}, (XmlObject)value);
            return null;
        }
        if (bigInt.signum() < 0) {
            StscState.get().error("invalid-value", new Object[]{text, "nonNegativeInteger"}, (XmlObject)value);
            return null;
        }
        return bigInt;
    }

    static XmlNonNegativeInteger buildNnInteger(XmlAnySimpleType value) {
        BigInteger bigInt = StscTranslator.buildBigInt(value);
        try {
            XmlNonNegativeIntegerImpl i = new XmlNonNegativeIntegerImpl();
            i.set(bigInt);
            i.setImmutable();
            return i;
        } catch (XmlValueOutOfRangeException e) {
            StscState.get().error("Internal error processing number", 21, (XmlObject)value);
            return null;
        }
    }

    static XmlPositiveInteger buildPosInteger(XmlAnySimpleType value) {
        BigInteger bigInt = StscTranslator.buildBigInt(value);
        try {
            XmlPositiveIntegerImpl i = new XmlPositiveIntegerImpl();
            i.set(bigInt);
            i.setImmutable();
            return i;
        } catch (XmlValueOutOfRangeException e) {
            StscState.get().error("Internal error processing number", 21, (XmlObject)value);
            return null;
        }
    }

    private static Object getUserData(XmlObject pos) {
        XmlCursor.XmlBookmark b = pos.newCursor().getBookmark(SchemaBookmark.class);
        if (b != null && b instanceof SchemaBookmark) {
            return ((SchemaBookmark)b).getValue();
        }
        return null;
    }

    private static boolean isEmptySchema(SchemaDocument.Schema schema) {
        XmlCursor cursor = schema.newCursor();
        boolean result = !cursor.toFirstChild();
        cursor.dispose();
        return result;
    }

    private static boolean isReservedTypeName(QName name) {
        return BuiltinSchemaTypeSystem.get().findType(name) != null;
    }

    private static class RedefinitionMaster {
        private Map stRedefinitions = Collections.EMPTY_MAP;
        private Map ctRedefinitions = Collections.EMPTY_MAP;
        private Map agRedefinitions = Collections.EMPTY_MAP;
        private Map mgRedefinitions = Collections.EMPTY_MAP;
        private static final RedefinitionHolder[] EMPTY_REDEFINTION_HOLDER_ARRAY = new RedefinitionHolder[0];
        private static final short SIMPLE_TYPE = 1;
        private static final short COMPLEX_TYPE = 2;
        private static final short MODEL_GROUP = 3;
        private static final short ATTRIBUTE_GROUP = 4;

        RedefinitionMaster(RedefinitionHolder[] redefHolders) {
            if (redefHolders.length > 0) {
                this.stRedefinitions = new HashMap();
                this.ctRedefinitions = new HashMap();
                this.agRedefinitions = new HashMap();
                this.mgRedefinitions = new HashMap();
                for (int i = 0; i < redefHolders.length; ++i) {
                    List<RedefinitionHolder> redefinedIn;
                    RedefinitionHolder redefHolder = redefHolders[i];
                    for (Object key : redefHolder.stRedefinitions.keySet()) {
                        redefinedIn = (ArrayList<RedefinitionHolder>)this.stRedefinitions.get(key);
                        if (redefinedIn == null) {
                            redefinedIn = new ArrayList<RedefinitionHolder>();
                            this.stRedefinitions.put(key, redefinedIn);
                        }
                        redefinedIn.add(redefHolders[i]);
                    }
                    for (Object key : redefHolder.ctRedefinitions.keySet()) {
                        redefinedIn = (List)this.ctRedefinitions.get(key);
                        if (redefinedIn == null) {
                            redefinedIn = new ArrayList();
                            this.ctRedefinitions.put(key, redefinedIn);
                        }
                        redefinedIn.add(redefHolders[i]);
                    }
                    for (Object key : redefHolder.agRedefinitions.keySet()) {
                        redefinedIn = (List)this.agRedefinitions.get(key);
                        if (redefinedIn == null) {
                            redefinedIn = new ArrayList();
                            this.agRedefinitions.put(key, redefinedIn);
                        }
                        redefinedIn.add(redefHolders[i]);
                    }
                    for (Object key : redefHolder.mgRedefinitions.keySet()) {
                        redefinedIn = (List)this.mgRedefinitions.get(key);
                        if (redefinedIn == null) {
                            redefinedIn = new ArrayList();
                            this.mgRedefinitions.put(key, redefinedIn);
                        }
                        redefinedIn.add(redefHolders[i]);
                    }
                }
            }
        }

        RedefinitionHolder[] getSimpleTypeRedefinitions(String name, StscImporter.SchemaToProcess schema) {
            List redefines = (List)this.stRedefinitions.get(name);
            if (redefines == null) {
                return EMPTY_REDEFINTION_HOLDER_ARRAY;
            }
            return this.doTopologicalSort(redefines, schema, name, (short)1);
        }

        RedefinitionHolder[] getComplexTypeRedefinitions(String name, StscImporter.SchemaToProcess schema) {
            List redefines = (List)this.ctRedefinitions.get(name);
            if (redefines == null) {
                return EMPTY_REDEFINTION_HOLDER_ARRAY;
            }
            return this.doTopologicalSort(redefines, schema, name, (short)2);
        }

        RedefinitionHolder[] getAttributeGroupRedefinitions(String name, StscImporter.SchemaToProcess schema) {
            List redefines = (List)this.agRedefinitions.get(name);
            if (redefines == null) {
                return EMPTY_REDEFINTION_HOLDER_ARRAY;
            }
            return this.doTopologicalSort(redefines, schema, name, (short)4);
        }

        RedefinitionHolder[] getModelGroupRedefinitions(String name, StscImporter.SchemaToProcess schema) {
            List redefines = (List)this.mgRedefinitions.get(name);
            if (redefines == null) {
                return EMPTY_REDEFINTION_HOLDER_ARRAY;
            }
            return this.doTopologicalSort(redefines, schema, name, (short)3);
        }

        private RedefinitionHolder[] doTopologicalSort(List genericRedefines, StscImporter.SchemaToProcess schema, String name, short componentType) {
            RedefinitionHolder[] specificRedefines = new RedefinitionHolder[genericRedefines.size()];
            int n = 0;
            for (int i = 0; i < genericRedefines.size(); ++i) {
                RedefinitionHolder h = (RedefinitionHolder)genericRedefines.get(i);
                if (h.schemaRedefined != schema && !h.schemaRedefined.indirectIncludes(schema)) continue;
                specificRedefines[n++] = h;
            }
            RedefinitionHolder[] sortedRedefines = new RedefinitionHolder[n];
            int[] numberOfIncludes = new int[n];
            for (int i = 0; i < n - 1; ++i) {
                RedefinitionHolder current = specificRedefines[i];
                for (int j = i + 1; j < n; ++j) {
                    if (current.schemaRedefined.indirectIncludes(specificRedefines[j].schemaRedefined)) {
                        int n2 = i;
                        numberOfIncludes[n2] = numberOfIncludes[n2] + 1;
                    }
                    if (!specificRedefines[j].schemaRedefined.indirectIncludes(current.schemaRedefined)) continue;
                    int n3 = j;
                    numberOfIncludes[n3] = numberOfIncludes[n3] + 1;
                }
            }
            int position = 0;
            boolean errorReported = false;
            while (position < n) {
                int i;
                int index = -1;
                for (i = 0; i < numberOfIncludes.length; ++i) {
                    if (numberOfIncludes[i] != 0 || index >= 0) continue;
                    index = i;
                }
                if (index < 0) {
                    if (!errorReported) {
                        StringBuffer fileNameList = new StringBuffer();
                        XmlObject location = null;
                        for (int i2 = 0; i2 < n; ++i2) {
                            if (specificRedefines[i2] == null) continue;
                            fileNameList.append(specificRedefines[i2].schemaLocation).append(',').append(' ');
                            if (location != null) continue;
                            location = this.locationFromRedefinitionAndCode(specificRedefines[i2], name, componentType);
                        }
                        StscState.get().error("Detected circular redefinition of " + this.componentNameFromCode(componentType) + " \"" + name + "\"; Files involved: " + fileNameList.toString(), 60, location);
                        errorReported = true;
                    }
                    int min = n;
                    for (int i3 = 0; i3 < n; ++i3) {
                        if (numberOfIncludes[i3] <= 0 || numberOfIncludes[i3] >= min) continue;
                        min = numberOfIncludes[i3];
                        index = i3;
                    }
                    int n4 = index;
                    numberOfIncludes[n4] = numberOfIncludes[n4] - 1;
                    continue;
                }
                assert (specificRedefines[index] != null);
                sortedRedefines[position++] = specificRedefines[index];
                for (i = 0; i < n; ++i) {
                    if (specificRedefines[i] == null || !specificRedefines[i].schemaRedefined.indirectIncludes(specificRedefines[index].schemaRedefined)) continue;
                    int n5 = i;
                    numberOfIncludes[n5] = numberOfIncludes[n5] - 1;
                }
                specificRedefines[index] = null;
                int n6 = index;
                numberOfIncludes[n6] = numberOfIncludes[n6] - 1;
            }
            for (int i = 1; i < n; ++i) {
                int j;
                for (j = i - 1; j >= 0 && sortedRedefines[j] == null; --j) {
                }
                if (sortedRedefines[i].schemaRedefined.indirectIncludes(sortedRedefines[j].schemaRedefined)) continue;
                StscState.get().error("Detected multiple redefinitions of " + this.componentNameFromCode(componentType) + " \"" + name + "\"; Files involved: " + sortedRedefines[j].schemaRedefined.getSourceName() + ", " + sortedRedefines[i].schemaRedefined.getSourceName(), 49, this.locationFromRedefinitionAndCode(sortedRedefines[i], name, componentType));
                switch (componentType) {
                    case 1: {
                        sortedRedefines[i].redefineSimpleType(name);
                        break;
                    }
                    case 2: {
                        sortedRedefines[i].redefineComplexType(name);
                        break;
                    }
                    case 4: {
                        sortedRedefines[i].redefineAttributeGroup(name);
                        break;
                    }
                    case 3: {
                        sortedRedefines[i].redefineModelGroup(name);
                    }
                }
                sortedRedefines[i] = null;
            }
            return sortedRedefines;
        }

        private String componentNameFromCode(short code) {
            String componentName;
            switch (code) {
                case 1: {
                    componentName = "simple type";
                    break;
                }
                case 2: {
                    componentName = "complex type";
                    break;
                }
                case 3: {
                    componentName = "model group";
                    break;
                }
                case 4: {
                    componentName = "attribute group";
                    break;
                }
                default: {
                    componentName = "";
                }
            }
            return componentName;
        }

        private XmlObject locationFromRedefinitionAndCode(RedefinitionHolder redefinition, String name, short code) {
            XmlObject location;
            switch (code) {
                case 1: {
                    location = (XmlObject)redefinition.stRedefinitions.get(name);
                    break;
                }
                case 2: {
                    location = (XmlObject)redefinition.ctRedefinitions.get(name);
                    break;
                }
                case 3: {
                    location = (XmlObject)redefinition.mgRedefinitions.get(name);
                    break;
                }
                case 4: {
                    location = (XmlObject)redefinition.agRedefinitions.get(name);
                    break;
                }
                default: {
                    location = null;
                }
            }
            return location;
        }
    }

    private static class RedefinitionHolder {
        private Map stRedefinitions = Collections.EMPTY_MAP;
        private Map ctRedefinitions = Collections.EMPTY_MAP;
        private Map agRedefinitions = Collections.EMPTY_MAP;
        private Map mgRedefinitions = Collections.EMPTY_MAP;
        private String schemaLocation = "";
        private StscImporter.SchemaToProcess schemaRedefined;

        RedefinitionHolder(StscImporter.SchemaToProcess schemaToProcess, RedefineDocument.Redefine redefine) {
            this.schemaRedefined = schemaToProcess;
            if (redefine != null) {
                StscState state = StscState.get();
                this.stRedefinitions = new HashMap();
                this.ctRedefinitions = new HashMap();
                this.agRedefinitions = new HashMap();
                this.mgRedefinitions = new HashMap();
                if (redefine.getSchemaLocation() != null) {
                    this.schemaLocation = redefine.getSchemaLocation();
                }
                TopLevelComplexType[] complexTypes = redefine.getComplexTypeArray();
                for (int i = 0; i < complexTypes.length; ++i) {
                    if (complexTypes[i].getName() == null) continue;
                    if (this.ctRedefinitions.containsKey(complexTypes[i].getName())) {
                        state.error("Duplicate type redefinition: " + complexTypes[i].getName(), 49, null);
                        continue;
                    }
                    this.ctRedefinitions.put(complexTypes[i].getName(), complexTypes[i]);
                }
                TopLevelSimpleType[] simpleTypes = redefine.getSimpleTypeArray();
                for (int i = 0; i < simpleTypes.length; ++i) {
                    if (simpleTypes[i].getName() == null) continue;
                    if (this.stRedefinitions.containsKey(simpleTypes[i].getName())) {
                        state.error("Duplicate type redefinition: " + simpleTypes[i].getName(), 49, null);
                        continue;
                    }
                    this.stRedefinitions.put(simpleTypes[i].getName(), simpleTypes[i]);
                }
                NamedGroup[] modelgroups = redefine.getGroupArray();
                for (int i = 0; i < modelgroups.length; ++i) {
                    if (modelgroups[i].getName() == null) continue;
                    if (this.mgRedefinitions.containsKey(modelgroups[i].getName())) {
                        state.error("Duplicate type redefinition: " + modelgroups[i].getName(), 49, null);
                        continue;
                    }
                    this.mgRedefinitions.put(modelgroups[i].getName(), modelgroups[i]);
                }
                NamedAttributeGroup[] attrgroups = redefine.getAttributeGroupArray();
                for (int i = 0; i < attrgroups.length; ++i) {
                    if (attrgroups[i].getName() == null) continue;
                    if (this.agRedefinitions.containsKey(attrgroups[i].getName())) {
                        state.error("Duplicate type redefinition: " + attrgroups[i].getName(), 49, null);
                        continue;
                    }
                    this.agRedefinitions.put(attrgroups[i].getName(), attrgroups[i]);
                }
            }
        }

        public TopLevelSimpleType redefineSimpleType(String name) {
            if (name == null || !this.stRedefinitions.containsKey(name)) {
                return null;
            }
            return (TopLevelSimpleType)this.stRedefinitions.remove(name);
        }

        public TopLevelComplexType redefineComplexType(String name) {
            if (name == null || !this.ctRedefinitions.containsKey(name)) {
                return null;
            }
            return (TopLevelComplexType)this.ctRedefinitions.remove(name);
        }

        public NamedGroup redefineModelGroup(String name) {
            if (name == null || !this.mgRedefinitions.containsKey(name)) {
                return null;
            }
            return (NamedGroup)this.mgRedefinitions.remove(name);
        }

        public NamedAttributeGroup redefineAttributeGroup(String name) {
            if (name == null || !this.agRedefinitions.containsKey(name)) {
                return null;
            }
            return (NamedAttributeGroup)this.agRedefinitions.remove(name);
        }

        public void complainAboutMissingDefinitions() {
            if (this.stRedefinitions.isEmpty() && this.ctRedefinitions.isEmpty() && this.agRedefinitions.isEmpty() && this.mgRedefinitions.isEmpty()) {
                return;
            }
            StscState state = StscState.get();
            for (String name : this.stRedefinitions.keySet()) {
                state.error("Redefined simple type " + name + " not found in " + this.schemaLocation, 60, (XmlObject)this.stRedefinitions.get(name));
            }
            for (String name : this.ctRedefinitions.keySet()) {
                state.error("Redefined complex type " + name + " not found in " + this.schemaLocation, 60, (XmlObject)this.ctRedefinitions.get(name));
            }
            for (String name : this.agRedefinitions.keySet()) {
                state.error("Redefined attribute group " + name + " not found in " + this.schemaLocation, 60, (XmlObject)this.agRedefinitions.get(name));
            }
            for (String name : this.mgRedefinitions.keySet()) {
                state.error("Redefined model group " + name + " not found in " + this.schemaLocation, 60, (XmlObject)this.mgRedefinitions.get(name));
            }
        }
    }
}

