/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.schema;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaAttributeModel;
import org.apache.xmlbeans.SchemaGlobalElement;
import org.apache.xmlbeans.SchemaIdentityConstraint;
import org.apache.xmlbeans.SchemaLocalAttribute;
import org.apache.xmlbeans.SchemaLocalElement;
import org.apache.xmlbeans.SchemaParticle;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlAnySimpleType;
import org.apache.xmlbeans.XmlError;
import org.apache.xmlbeans.XmlID;
import org.apache.xmlbeans.XmlNOTATION;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.common.QNameHelper;
import org.apache.xmlbeans.impl.common.XBeanDebug;
import org.apache.xmlbeans.impl.schema.BuiltinSchemaTypeSystem;
import org.apache.xmlbeans.impl.schema.SchemaGlobalElementImpl;
import org.apache.xmlbeans.impl.schema.SchemaLocalAttributeImpl;
import org.apache.xmlbeans.impl.schema.SchemaLocalElementImpl;
import org.apache.xmlbeans.impl.schema.SchemaParticleImpl;
import org.apache.xmlbeans.impl.schema.SchemaPropertyImpl;
import org.apache.xmlbeans.impl.schema.SchemaTypeImpl;
import org.apache.xmlbeans.impl.schema.StscState;
import org.apache.xmlbeans.impl.schema.XmlValueRef;

public class StscChecker {
    public static void checkAll() {
        StscState state = StscState.get();
        ArrayList<SchemaType> allSeenTypes = new ArrayList<SchemaType>();
        allSeenTypes.addAll(Arrays.asList(state.documentTypes()));
        allSeenTypes.addAll(Arrays.asList(state.attributeTypes()));
        allSeenTypes.addAll(Arrays.asList(state.redefinedGlobalTypes()));
        allSeenTypes.addAll(Arrays.asList(state.globalTypes()));
        for (int i = 0; i < allSeenTypes.size(); ++i) {
            SchemaType gType = (SchemaType)allSeenTypes.get(i);
            if (!state.noPvr() && !gType.isDocumentType()) {
                StscChecker.checkRestriction((SchemaTypeImpl)gType);
            }
            StscChecker.checkFields((SchemaTypeImpl)gType);
            allSeenTypes.addAll(Arrays.asList(gType.getAnonymousTypes()));
        }
        StscChecker.checkSubstitutionGroups(state.globalElements());
    }

    public static void checkFields(SchemaTypeImpl sType) {
        if (sType.isSimpleType()) {
            return;
        }
        XmlObject location = sType.getParseObject();
        SchemaAttributeModel sAttrModel = sType.getAttributeModel();
        if (sAttrModel != null) {
            SchemaLocalAttribute[] sAttrs = sAttrModel.getAttributes();
            QName idAttr = null;
            for (int i = 0; i < sAttrs.length; ++i) {
                XmlObject attrLocation = ((SchemaLocalAttributeImpl)sAttrs[i])._parseObject;
                if (XmlID.type.isAssignableFrom(sAttrs[i].getType())) {
                    if (idAttr == null) {
                        idAttr = sAttrs[i].getName();
                    } else {
                        StscState.get().error("ag-props-correct.3", new Object[]{QNameHelper.pretty(idAttr), sAttrs[i].getName()}, attrLocation != null ? attrLocation : location);
                    }
                    if (sAttrs[i].getDefaultText() == null) continue;
                    StscState.get().error("a-props-correct.3", null, attrLocation != null ? attrLocation : location);
                    continue;
                }
                if (XmlNOTATION.type.isAssignableFrom(sAttrs[i].getType())) {
                    boolean hasNS;
                    if (sAttrs[i].getType().getBuiltinTypeCode() == 8) {
                        StscState.get().recover("enumeration-required-notation-attr", new Object[]{QNameHelper.pretty(sAttrs[i].getName())}, attrLocation != null ? attrLocation : location);
                        continue;
                    }
                    if (sAttrs[i].getType().getSimpleVariety() == 2) {
                        SchemaType[] members = sAttrs[i].getType().getUnionConstituentTypes();
                        for (int j = 0; j < members.length; ++j) {
                            if (members[j].getBuiltinTypeCode() != 8) continue;
                            StscState.get().recover("enumeration-required-notation-attr", new Object[]{QNameHelper.pretty(sAttrs[i].getName())}, attrLocation != null ? attrLocation : location);
                        }
                    }
                    if (sType.isAttributeType()) {
                        hasNS = sAttrs[i].getName().getNamespaceURI().length() > 0;
                    } else {
                        SchemaType t = sType;
                        while (t.getOuterType() != null) {
                            t = t.getOuterType();
                        }
                        if (t.isDocumentType()) {
                            hasNS = t.getDocumentElementName().getNamespaceURI().length() > 0;
                        } else {
                            boolean bl = hasNS = t.getName().getNamespaceURI().length() > 0;
                        }
                    }
                    if (!hasNS) continue;
                    StscState.get().warning("notation-targetns-attr", new Object[]{QNameHelper.pretty(sAttrs[i].getName())}, attrLocation != null ? attrLocation : location);
                    continue;
                }
                String valueConstraint = sAttrs[i].getDefaultText();
                if (valueConstraint == null) continue;
                try {
                    XmlAnySimpleType val = sAttrs[i].getDefaultValue();
                    if (!val.validate()) {
                        throw new Exception();
                    }
                    SchemaPropertyImpl sProp = (SchemaPropertyImpl)sType.getAttributeProperty(sAttrs[i].getName());
                    if (sProp == null || sProp.getDefaultText() == null) continue;
                    sProp.setDefaultValue(new XmlValueRef(val));
                    continue;
                } catch (Exception e) {
                    String constraintName = sAttrs[i].isFixed() ? "fixed" : "default";
                    XmlObject constraintLocation = location;
                    if (attrLocation != null && (constraintLocation = attrLocation.selectAttribute("", constraintName)) == null) {
                        constraintLocation = attrLocation;
                    }
                    StscState.get().error("a-props-correct.2", new Object[]{QNameHelper.pretty(sAttrs[i].getName()), constraintName, valueConstraint, QNameHelper.pretty(sAttrs[i].getType().getName())}, constraintLocation);
                }
            }
        }
        StscChecker.checkElementDefaults(sType.getContentModel(), location, sType);
    }

    /*
     * Unable to fully structure code
     */
    private static void checkElementDefaults(SchemaParticle model, XmlObject location, SchemaType parentType) {
        if (model == null) {
            return;
        }
        switch (model.getParticleType()) {
            case 1: 
            case 2: 
            case 3: {
                children = model.getParticleChildren();
                for (i = 0; i < children.length; ++i) {
                    StscChecker.checkElementDefaults(children[i], location, parentType);
                }
                break;
            }
            case 4: {
                valueConstraint = model.getDefaultText();
                if (valueConstraint == null) ** GOTO lbl47
                if (!model.getType().isSimpleType() && model.getType().getContentType() != 2) ** GOTO lbl30
                try {
                    val = model.getDefaultValue();
                    opt = new XmlOptions();
                    opt.put("VALIDATE_TEXT_ONLY");
                    if (!val.validate(opt)) {
                        throw new Exception();
                    }
                    sProp = (SchemaPropertyImpl)parentType.getElementProperty(model.getName());
                    if (sProp != null && sProp.getDefaultText() != null) {
                        sProp.setDefaultValue(new XmlValueRef(val));
                    }
                    ** GOTO lbl47
                } catch (Exception e) {
                    constraintName = model.isFixed() != false ? "fixed" : "default";
                    constraintLocation = location.selectAttribute("", constraintName);
                    StscState.get().error("e-props-correct.2", new Object[]{QNameHelper.pretty(model.getName()), constraintName, valueConstraint, QNameHelper.pretty(model.getType().getName())}, constraintLocation == null ? location : constraintLocation);
                }
                ** GOTO lbl47
lbl30:
                // 1 sources

                if (model.getType().getContentType() == 4) {
                    if (!model.getType().getContentModel().isSkippable()) {
                        constraintName = model.isFixed() != false ? "fixed" : "default";
                        constraintLocation = location.selectAttribute("", constraintName);
                        StscState.get().error("cos-valid-default.2.2.2", new Object[]{QNameHelper.pretty(model.getName()), constraintName, valueConstraint}, constraintLocation == null ? location : constraintLocation);
                    } else {
                        sProp = (SchemaPropertyImpl)parentType.getElementProperty(model.getName());
                        if (sProp != null && sProp.getDefaultText() != null) {
                            sProp.setDefaultValue(new XmlValueRef(XmlString.type.newValue(valueConstraint)));
                        }
                    }
                } else if (model.getType().getContentType() == 3) {
                    constraintLocation = location.selectAttribute("", "default");
                    StscState.get().error("cos-valid-default.2.1", new Object[]{QNameHelper.pretty(model.getName()), valueConstraint, "element"}, constraintLocation == null ? location : constraintLocation);
                } else if (model.getType().getContentType() == 1) {
                    constraintLocation = location.selectAttribute("", "default");
                    StscState.get().error("cos-valid-default.2.1", new Object[]{QNameHelper.pretty(model.getName()), valueConstraint, "empty"}, constraintLocation == null ? location : constraintLocation);
                }
lbl47:
                // 10 sources

                warningType = null;
                if (BuiltinSchemaTypeSystem.ST_ID.isAssignableFrom(model.getType())) {
                    warningType = BuiltinSchemaTypeSystem.ST_ID.getName().getLocalPart();
                } else if (BuiltinSchemaTypeSystem.ST_IDREF.isAssignableFrom(model.getType())) {
                    warningType = BuiltinSchemaTypeSystem.ST_IDREF.getName().getLocalPart();
                } else if (BuiltinSchemaTypeSystem.ST_IDREFS.isAssignableFrom(model.getType())) {
                    warningType = BuiltinSchemaTypeSystem.ST_IDREFS.getName().getLocalPart();
                } else if (BuiltinSchemaTypeSystem.ST_ENTITY.isAssignableFrom(model.getType())) {
                    warningType = BuiltinSchemaTypeSystem.ST_ENTITY.getName().getLocalPart();
                } else if (BuiltinSchemaTypeSystem.ST_ENTITIES.isAssignableFrom(model.getType())) {
                    warningType = BuiltinSchemaTypeSystem.ST_ENTITIES.getName().getLocalPart();
                } else if (BuiltinSchemaTypeSystem.ST_NOTATION.isAssignableFrom(model.getType())) {
                    if (model.getType().getBuiltinTypeCode() == 8) {
                        StscState.get().recover("enumeration-required-notation-elem", new Object[]{QNameHelper.pretty(model.getName())}, ((SchemaLocalElementImpl)model)._parseObject == null ? location : ((SchemaLocalElementImpl)model)._parseObject.selectAttribute("", "type"));
                    } else {
                        if (model.getType().getSimpleVariety() == 2) {
                            members = model.getType().getUnionConstituentTypes();
                            for (i = 0; i < members.length; ++i) {
                                if (members[i].getBuiltinTypeCode() != 8) continue;
                                StscState.get().recover("enumeration-required-notation-elem", new Object[]{QNameHelper.pretty(model.getName())}, ((SchemaLocalElementImpl)model)._parseObject == null ? location : ((SchemaLocalElementImpl)model)._parseObject.selectAttribute("", "type"));
                            }
                        }
                        warningType = BuiltinSchemaTypeSystem.ST_NOTATION.getName().getLocalPart();
                    }
                    t = parentType;
                    while (t.getOuterType() != null) {
                        t = t.getOuterType();
                    }
                    if (t.isDocumentType()) {
                        hasNS = t.getDocumentElementName().getNamespaceURI().length() > 0;
                    } else {
                        v0 = hasNS = t.getName().getNamespaceURI().length() > 0;
                    }
                    if (hasNS) {
                        StscState.get().warning("notation-targetns-elem", new Object[]{QNameHelper.pretty(model.getName())}, ((SchemaLocalElementImpl)model)._parseObject == null ? location : ((SchemaLocalElementImpl)model)._parseObject);
                    }
                }
                if (warningType == null) break;
                StscState.get().warning("id-idref-idrefs-entity-entities-notation", new Object[]{QNameHelper.pretty(model.getName()), warningType}, ((SchemaLocalElementImpl)model)._parseObject == null ? location : ((SchemaLocalElementImpl)model)._parseObject.selectAttribute("", "type"));
                break;
            }
        }
    }

    public static boolean checkRestriction(SchemaTypeImpl sType) {
        if (sType.getDerivationType() == 1 && !sType.isSimpleType()) {
            StscState state = StscState.get();
            XmlObject location = sType.getParseObject();
            SchemaType baseType = sType.getBaseType();
            if (baseType.isSimpleType()) {
                state.error("src-ct.1", new Object[]{QNameHelper.pretty(baseType.getName())}, location);
                return false;
            }
            block0 : switch (sType.getContentType()) {
                case 2: {
                    switch (baseType.getContentType()) {
                        case 2: {
                            SchemaType bType;
                            SchemaType cType = sType.getContentBasedOnType();
                            if (cType == baseType) break block0;
                            for (bType = baseType; bType != null && !bType.isSimpleType(); bType = bType.getContentBasedOnType()) {
                            }
                            if (bType == null || bType.isAssignableFrom(cType)) break block0;
                            state.error("derivation-ok-restriction.5.2.2.1", null, location);
                            return false;
                        }
                        case 4: {
                            if (baseType.getContentModel() == null || baseType.getContentModel().isSkippable()) break block0;
                            state.error("derivation-ok-restriction.5.1.2", null, location);
                            return false;
                        }
                        default: {
                            state.error("derivation-ok-restriction.5.1", null, location);
                            return false;
                        }
                    }
                }
                case 1: {
                    switch (baseType.getContentType()) {
                        case 1: {
                            break block0;
                        }
                        case 3: 
                        case 4: {
                            if (baseType.getContentModel() == null || baseType.getContentModel().isSkippable()) break block0;
                            state.error("derivation-ok-restriction.5.2.2", null, location);
                            return false;
                        }
                    }
                    state.error("derivation-ok-restriction.5.2", null, location);
                    return false;
                }
                case 4: {
                    if (baseType.getContentType() != 4) {
                        state.error("derivation-ok-restriction.5.3a", null, location);
                        return false;
                    }
                }
                case 3: {
                    if (baseType.getContentType() == 1) {
                        state.error("derivation-ok-restriction.5.3b", null, location);
                        return false;
                    }
                    if (baseType.getContentType() == 2) {
                        state.error("derivation-ok-restriction.5.3c", null, location);
                        return false;
                    }
                    SchemaParticle baseModel = baseType.getContentModel();
                    SchemaParticle derivedModel = sType.getContentModel();
                    if (derivedModel == null && sType.getDerivationType() == 1) {
                        return true;
                    }
                    if (baseModel == null || derivedModel == null) {
                        XBeanDebug.logStackTrace("Null models that weren't caught by EMPTY_CONTENT: " + baseType + " (" + baseModel + "), " + sType + " (" + derivedModel + ")");
                        state.error("derivation-ok-restriction.5.3", null, location);
                        return false;
                    }
                    ArrayList errors = new ArrayList();
                    boolean isValid = StscChecker.isParticleValidRestriction(baseModel, derivedModel, errors, location);
                    if (isValid) break;
                    if (errors.size() == 0) {
                        state.error("derivation-ok-restriction.5.3", null, location);
                    } else {
                        state.getErrorListener().add(errors.get(errors.size() - 1));
                    }
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean isParticleValidRestriction(SchemaParticle baseModel, SchemaParticle derivedModel, Collection errors, XmlObject context) {
        boolean restrictionValid = false;
        if (baseModel.equals(derivedModel)) {
            restrictionValid = true;
        } else {
            block0 : switch (baseModel.getParticleType()) {
                case 4: {
                    switch (derivedModel.getParticleType()) {
                        case 4: {
                            restrictionValid = StscChecker.nameAndTypeOK((SchemaLocalElement)((Object)baseModel), (SchemaLocalElement)((Object)derivedModel), errors, context);
                            break block0;
                        }
                        case 1: 
                        case 2: 
                        case 3: 
                        case 5: {
                            errors.add(XmlError.forObject("cos-particle-restrict.2", new Object[]{StscChecker.printParticle(derivedModel), StscChecker.printParticle(baseModel)}, context));
                            restrictionValid = false;
                            break block0;
                        }
                    }
                    assert (false) : XBeanDebug.logStackTrace("Unknown schema type for Derived Type");
                    break;
                }
                case 5: {
                    switch (derivedModel.getParticleType()) {
                        case 4: {
                            restrictionValid = StscChecker.nsCompat(baseModel, (SchemaLocalElement)((Object)derivedModel), errors, context);
                            break block0;
                        }
                        case 5: {
                            restrictionValid = StscChecker.nsSubset(baseModel, derivedModel, errors, context);
                            break block0;
                        }
                        case 1: {
                            restrictionValid = StscChecker.nsRecurseCheckCardinality(baseModel, derivedModel, errors, context);
                            break block0;
                        }
                        case 2: {
                            restrictionValid = StscChecker.nsRecurseCheckCardinality(baseModel, derivedModel, errors, context);
                            break block0;
                        }
                        case 3: {
                            restrictionValid = StscChecker.nsRecurseCheckCardinality(baseModel, derivedModel, errors, context);
                            break block0;
                        }
                    }
                    assert (false) : XBeanDebug.logStackTrace("Unknown schema type for Derived Type");
                    break;
                }
                case 1: {
                    switch (derivedModel.getParticleType()) {
                        case 4: {
                            restrictionValid = StscChecker.recurseAsIfGroup(baseModel, derivedModel, errors, context);
                            break block0;
                        }
                        case 2: 
                        case 5: {
                            errors.add(XmlError.forObject("cos-particle-restrict.2", new Object[]{StscChecker.printParticle(derivedModel), StscChecker.printParticle(baseModel)}, context));
                            restrictionValid = false;
                            break block0;
                        }
                        case 1: {
                            restrictionValid = StscChecker.recurse(baseModel, derivedModel, errors, context);
                            break block0;
                        }
                        case 3: {
                            restrictionValid = StscChecker.recurseUnordered(baseModel, derivedModel, errors, context);
                            break block0;
                        }
                    }
                    assert (false) : XBeanDebug.logStackTrace("Unknown schema type for Derived Type");
                    break;
                }
                case 2: {
                    switch (derivedModel.getParticleType()) {
                        case 4: {
                            restrictionValid = StscChecker.recurseAsIfGroup(baseModel, derivedModel, errors, context);
                            break block0;
                        }
                        case 1: 
                        case 5: {
                            errors.add(XmlError.forObject("cos-particle-restrict.2", new Object[]{StscChecker.printParticle(derivedModel), StscChecker.printParticle(baseModel)}, context));
                            restrictionValid = false;
                            break block0;
                        }
                        case 2: {
                            restrictionValid = StscChecker.recurseLax(baseModel, derivedModel, errors, context);
                            break block0;
                        }
                        case 3: {
                            restrictionValid = StscChecker.mapAndSum(baseModel, derivedModel, errors, context);
                            break block0;
                        }
                    }
                    assert (false) : XBeanDebug.logStackTrace("Unknown schema type for Derived Type");
                    break;
                }
                case 3: {
                    switch (derivedModel.getParticleType()) {
                        case 4: {
                            restrictionValid = StscChecker.recurseAsIfGroup(baseModel, derivedModel, errors, context);
                            break block0;
                        }
                        case 1: 
                        case 2: 
                        case 5: {
                            errors.add(XmlError.forObject("cos-particle-restrict.2", new Object[]{StscChecker.printParticle(derivedModel), StscChecker.printParticle(baseModel)}, context));
                            restrictionValid = false;
                            break block0;
                        }
                        case 3: {
                            restrictionValid = StscChecker.recurse(baseModel, derivedModel, errors, context);
                            break block0;
                        }
                    }
                    assert (false) : XBeanDebug.logStackTrace("Unknown schema type for Derived Type");
                    break;
                }
                default: {
                    assert (false) : XBeanDebug.logStackTrace("Unknown schema type for Base Type");
                    break;
                }
            }
        }
        return restrictionValid;
    }

    private static boolean mapAndSum(SchemaParticle baseModel, SchemaParticle derivedModel, Collection errors, XmlObject context) {
        assert (baseModel.getParticleType() == 2);
        assert (derivedModel.getParticleType() == 3);
        boolean mapAndSumValid = true;
        SchemaParticle[] derivedParticleArray = derivedModel.getParticleChildren();
        SchemaParticle[] baseParticleArray = baseModel.getParticleChildren();
        for (int i = 0; i < derivedParticleArray.length; ++i) {
            SchemaParticle derivedParticle = derivedParticleArray[i];
            boolean foundMatch = false;
            for (int j = 0; j < baseParticleArray.length; ++j) {
                SchemaParticle baseParticle = baseParticleArray[j];
                if (!StscChecker.isParticleValidRestriction(baseParticle, derivedParticle, errors, context)) continue;
                foundMatch = true;
                break;
            }
            if (foundMatch) continue;
            mapAndSumValid = false;
            errors.add(XmlError.forObject("rcase-MapAndSum.1", new Object[]{StscChecker.printParticle(derivedParticle)}, context));
            return false;
        }
        BigInteger derivedRangeMin = derivedModel.getMinOccurs().multiply(BigInteger.valueOf(derivedModel.getParticleChildren().length));
        BigInteger derivedRangeMax = null;
        Object UNBOUNDED = null;
        derivedRangeMax = derivedModel.getMaxOccurs() == UNBOUNDED ? null : derivedModel.getMaxOccurs().multiply(BigInteger.valueOf(derivedModel.getParticleChildren().length));
        if (derivedRangeMin.compareTo(baseModel.getMinOccurs()) < 0) {
            mapAndSumValid = false;
            errors.add(XmlError.forObject("rcase-MapAndSum.2a", new Object[]{derivedRangeMin.toString(), baseModel.getMinOccurs().toString()}, context));
        } else if (baseModel.getMaxOccurs() != UNBOUNDED && (derivedRangeMax == UNBOUNDED || derivedRangeMax.compareTo(baseModel.getMaxOccurs()) > 0)) {
            mapAndSumValid = false;
            errors.add(XmlError.forObject("rcase-MapAndSum.2b", new Object[]{derivedRangeMax == UNBOUNDED ? "unbounded" : derivedRangeMax.toString(), baseModel.getMaxOccurs().toString()}, context));
        }
        return mapAndSumValid;
    }

    private static boolean recurseAsIfGroup(SchemaParticle baseModel, SchemaParticle derivedModel, Collection errors, XmlObject context) {
        assert (baseModel.getParticleType() == 1 && derivedModel.getParticleType() == 4 || baseModel.getParticleType() == 2 && derivedModel.getParticleType() == 4 || baseModel.getParticleType() == 3 && derivedModel.getParticleType() == 4);
        SchemaParticleImpl asIfPart = new SchemaParticleImpl();
        asIfPart.setParticleType(baseModel.getParticleType());
        asIfPart.setMinOccurs(BigInteger.ONE);
        asIfPart.setMaxOccurs(BigInteger.ONE);
        asIfPart.setParticleChildren(new SchemaParticle[]{derivedModel});
        return StscChecker.isParticleValidRestriction(baseModel, asIfPart, errors, context);
    }

    private static boolean recurseLax(SchemaParticle baseModel, SchemaParticle derivedModel, Collection errors, XmlObject context) {
        assert (baseModel.getParticleType() == 2 && derivedModel.getParticleType() == 2);
        boolean recurseLaxValid = true;
        if (!StscChecker.occurrenceRangeOK(baseModel, derivedModel, errors, context)) {
            return false;
        }
        SchemaParticle[] derivedParticleArray = derivedModel.getParticleChildren();
        SchemaParticle[] baseParticleArray = baseModel.getParticleChildren();
        int i = 0;
        int j = 0;
        while (i < derivedParticleArray.length && j < baseParticleArray.length) {
            SchemaParticle baseParticle = baseParticleArray[j];
            SchemaParticle derivedParticle = derivedParticleArray[i];
            if (StscChecker.isParticleValidRestriction(baseParticle, derivedParticle, errors, context)) {
                ++i;
                ++j;
                continue;
            }
            ++j;
        }
        if (i < derivedParticleArray.length) {
            recurseLaxValid = false;
            errors.add(XmlError.forObject("rcase-RecurseLax.2", new Object[]{StscChecker.printParticles(baseParticleArray, i)}, context));
        }
        return recurseLaxValid;
    }

    private static boolean recurseUnordered(SchemaParticle baseModel, SchemaParticle derivedModel, Collection errors, XmlObject context) {
        assert (baseModel.getParticleType() == 1 && derivedModel.getParticleType() == 3);
        boolean recurseUnorderedValid = true;
        if (!StscChecker.occurrenceRangeOK(baseModel, derivedModel, errors, context)) {
            return false;
        }
        SchemaParticle[] baseParticles = baseModel.getParticleChildren();
        HashMap<QName, Object> baseParticleMap = new HashMap<QName, Object>(10);
        Object MAPPED = new Object();
        for (int i = 0; i < baseParticles.length; ++i) {
            baseParticleMap.put(baseParticles[i].getName(), baseParticles[i]);
        }
        SchemaParticle[] derivedParticles = derivedModel.getParticleChildren();
        for (int i = 0; i < derivedParticles.length; ++i) {
            Object baseParticle = baseParticleMap.get(derivedParticles[i].getName());
            if (baseParticle == null) {
                recurseUnorderedValid = false;
                errors.add(XmlError.forObject("rcase-RecurseUnordered.2", new Object[]{StscChecker.printParticle(derivedParticles[i])}, context));
                break;
            }
            if (baseParticle == MAPPED) {
                recurseUnorderedValid = false;
                errors.add(XmlError.forObject("rcase-RecurseUnordered.2.1", new Object[]{StscChecker.printParticle(derivedParticles[i])}, context));
                break;
            }
            SchemaParticle matchedBaseParticle = (SchemaParticle)baseParticle;
            if (derivedParticles[i].getMaxOccurs() == null || derivedParticles[i].getMaxOccurs().compareTo(BigInteger.ONE) > 0) {
                recurseUnorderedValid = false;
                errors.add(XmlError.forObject("rcase-RecurseUnordered.2.2a", new Object[]{StscChecker.printParticle(derivedParticles[i]), StscChecker.printMaxOccurs(derivedParticles[i].getMinOccurs())}, context));
                break;
            }
            if (!StscChecker.isParticleValidRestriction(matchedBaseParticle, derivedParticles[i], errors, context)) {
                recurseUnorderedValid = false;
                break;
            }
            baseParticleMap.put(derivedParticles[i].getName(), MAPPED);
        }
        if (recurseUnorderedValid) {
            Set baseParticleCollection = baseParticleMap.keySet();
            for (QName baseParticleQName : baseParticleCollection) {
                if (baseParticleMap.get(baseParticleQName) == MAPPED || ((SchemaParticle)baseParticleMap.get(baseParticleQName)).isSkippable()) continue;
                recurseUnorderedValid = false;
                errors.add(XmlError.forObject("rcase-RecurseUnordered.2.3", new Object[]{StscChecker.printParticle((SchemaParticle)baseParticleMap.get(baseParticleQName))}, context));
            }
        }
        return recurseUnorderedValid;
    }

    private static boolean recurse(SchemaParticle baseModel, SchemaParticle derivedModel, Collection errors, XmlObject context) {
        boolean recurseValid = true;
        if (!StscChecker.occurrenceRangeOK(baseModel, derivedModel, errors, context)) {
            return false;
        }
        SchemaParticle[] derivedParticleArray = derivedModel.getParticleChildren();
        SchemaParticle[] baseParticleArray = baseModel.getParticleChildren();
        int i = 0;
        int j = 0;
        while (i < derivedParticleArray.length && j < baseParticleArray.length) {
            SchemaParticle baseParticle = baseParticleArray[j];
            SchemaParticle derivedParticle = derivedParticleArray[i];
            if (StscChecker.isParticleValidRestriction(baseParticle, derivedParticle, errors, context)) {
                ++i;
                ++j;
                continue;
            }
            if (baseParticle.isSkippable()) {
                ++j;
                continue;
            }
            recurseValid = false;
            errors.add(XmlError.forObject("rcase-Recurse.2.1", new Object[]{StscChecker.printParticle(derivedParticle), StscChecker.printParticle(derivedModel), StscChecker.printParticle(baseParticle), StscChecker.printParticle(baseModel)}, context));
            break;
        }
        if (i < derivedParticleArray.length) {
            recurseValid = false;
            errors.add(XmlError.forObject("rcase-Recurse.2", new Object[]{StscChecker.printParticle(derivedModel), StscChecker.printParticle(baseModel), StscChecker.printParticles(derivedParticleArray, i)}, context));
        } else if (j < baseParticleArray.length) {
            ArrayList<SchemaParticle> particles = new ArrayList<SchemaParticle>(baseParticleArray.length);
            for (int k = j; k < baseParticleArray.length; ++k) {
                if (baseParticleArray[k].isSkippable()) continue;
                particles.add(baseParticleArray[k]);
            }
            if (particles.size() > 0) {
                recurseValid = false;
                errors.add(XmlError.forObject("rcase-Recurse.2.2", new Object[]{StscChecker.printParticle(baseModel), StscChecker.printParticle(derivedModel), StscChecker.printParticles(particles)}, context));
            }
        }
        return recurseValid;
    }

    private static boolean nsRecurseCheckCardinality(SchemaParticle baseModel, SchemaParticle derivedModel, Collection errors, XmlObject context) {
        assert (baseModel.getParticleType() == 5);
        assert (derivedModel.getParticleType() == 1 || derivedModel.getParticleType() == 2 || derivedModel.getParticleType() == 3);
        boolean nsRecurseCheckCardinality = true;
        SchemaParticleImpl asIfPart = new SchemaParticleImpl();
        asIfPart.setParticleType(baseModel.getParticleType());
        asIfPart.setWildcardProcess(baseModel.getWildcardProcess());
        asIfPart.setWildcardSet(baseModel.getWildcardSet());
        asIfPart.setMinOccurs(BigInteger.ZERO);
        asIfPart.setMaxOccurs(null);
        asIfPart.setTransitionRules(baseModel.getWildcardSet(), true);
        asIfPart.setTransitionNotes(baseModel.getWildcardSet(), true);
        SchemaParticle[] particleChildren = derivedModel.getParticleChildren();
        for (int i = 0; i < particleChildren.length; ++i) {
            SchemaParticle particle = particleChildren[i];
            switch (particle.getParticleType()) {
                case 4: {
                    nsRecurseCheckCardinality = StscChecker.nsCompat(asIfPart, (SchemaLocalElement)((Object)particle), errors, context);
                    break;
                }
                case 5: {
                    nsRecurseCheckCardinality = StscChecker.nsSubset(asIfPart, particle, errors, context);
                    break;
                }
                case 1: 
                case 2: 
                case 3: {
                    nsRecurseCheckCardinality = StscChecker.nsRecurseCheckCardinality(asIfPart, particle, errors, context);
                }
            }
            if (!nsRecurseCheckCardinality) break;
        }
        if (nsRecurseCheckCardinality) {
            nsRecurseCheckCardinality = StscChecker.checkGroupOccurrenceOK(baseModel, derivedModel, errors, context);
        }
        return nsRecurseCheckCardinality;
    }

    private static boolean checkGroupOccurrenceOK(SchemaParticle baseModel, SchemaParticle derivedModel, Collection errors, XmlObject context) {
        boolean groupOccurrenceOK = true;
        BigInteger minRange = BigInteger.ZERO;
        BigInteger maxRange = BigInteger.ZERO;
        switch (derivedModel.getParticleType()) {
            case 1: 
            case 3: {
                minRange = StscChecker.getEffectiveMinRangeAllSeq(derivedModel);
                maxRange = StscChecker.getEffectiveMaxRangeAllSeq(derivedModel);
                break;
            }
            case 2: {
                minRange = StscChecker.getEffectiveMinRangeChoice(derivedModel);
                maxRange = StscChecker.getEffectiveMaxRangeChoice(derivedModel);
            }
        }
        if (minRange.compareTo(baseModel.getMinOccurs()) < 0) {
            groupOccurrenceOK = false;
            errors.add(XmlError.forObject("range-ok.1", new Object[]{StscChecker.printParticle(derivedModel), StscChecker.printParticle(baseModel)}, context));
        }
        Object UNBOUNDED = null;
        if (baseModel.getMaxOccurs() != UNBOUNDED) {
            if (maxRange == UNBOUNDED) {
                groupOccurrenceOK = false;
                errors.add(XmlError.forObject("range-ok.2", new Object[]{StscChecker.printParticle(derivedModel), StscChecker.printParticle(baseModel)}, context));
            } else if (maxRange.compareTo(baseModel.getMaxOccurs()) > 0) {
                groupOccurrenceOK = false;
                errors.add(XmlError.forObject("range-ok.2", new Object[]{StscChecker.printParticle(derivedModel), StscChecker.printParticle(baseModel)}, context));
            }
        }
        return groupOccurrenceOK;
    }

    private static BigInteger getEffectiveMaxRangeChoice(SchemaParticle derivedModel) {
        BigInteger maxRange = BigInteger.ZERO;
        Object UNBOUNDED = null;
        boolean nonZeroParticleChildFound = false;
        BigInteger maxOccursInWildCardOrElement = BigInteger.ZERO;
        BigInteger maxOccursInGroup = BigInteger.ZERO;
        SchemaParticle[] particleChildren = derivedModel.getParticleChildren();
        for (int i = 0; i < particleChildren.length; ++i) {
            SchemaParticle particle = particleChildren[i];
            switch (particle.getParticleType()) {
                case 4: 
                case 5: {
                    if (particle.getMaxOccurs() == UNBOUNDED) {
                        maxRange = UNBOUNDED;
                        break;
                    }
                    if (particle.getIntMaxOccurs() <= 0) break;
                    nonZeroParticleChildFound = true;
                    if (particle.getMaxOccurs().compareTo(maxOccursInWildCardOrElement) <= 0) break;
                    maxOccursInWildCardOrElement = particle.getMaxOccurs();
                    break;
                }
                case 1: 
                case 3: {
                    maxRange = StscChecker.getEffectiveMaxRangeAllSeq(particle);
                    if (maxRange == UNBOUNDED || maxRange.compareTo(maxOccursInGroup) <= 0) break;
                    maxOccursInGroup = maxRange;
                    break;
                }
                case 2: {
                    maxRange = StscChecker.getEffectiveMaxRangeChoice(particle);
                    if (maxRange == UNBOUNDED || maxRange.compareTo(maxOccursInGroup) <= 0) break;
                    maxOccursInGroup = maxRange;
                }
            }
            if (maxRange == UNBOUNDED) break;
        }
        if (maxRange != UNBOUNDED) {
            maxRange = nonZeroParticleChildFound && derivedModel.getMaxOccurs() == UNBOUNDED ? UNBOUNDED : derivedModel.getMaxOccurs().multiply(maxOccursInWildCardOrElement.add(maxOccursInGroup));
        }
        return maxRange;
    }

    private static BigInteger getEffectiveMaxRangeAllSeq(SchemaParticle derivedModel) {
        BigInteger maxRange = BigInteger.ZERO;
        Object UNBOUNDED = null;
        boolean nonZeroParticleChildFound = false;
        BigInteger maxOccursTotal = BigInteger.ZERO;
        BigInteger maxOccursInGroup = BigInteger.ZERO;
        SchemaParticle[] particleChildren = derivedModel.getParticleChildren();
        for (int i = 0; i < particleChildren.length; ++i) {
            SchemaParticle particle = particleChildren[i];
            switch (particle.getParticleType()) {
                case 4: 
                case 5: {
                    if (particle.getMaxOccurs() == UNBOUNDED) {
                        maxRange = UNBOUNDED;
                        break;
                    }
                    if (particle.getIntMaxOccurs() <= 0) break;
                    nonZeroParticleChildFound = true;
                    maxOccursTotal = maxOccursTotal.add(particle.getMaxOccurs());
                    break;
                }
                case 1: 
                case 3: {
                    maxRange = StscChecker.getEffectiveMaxRangeAllSeq(particle);
                    if (maxRange == UNBOUNDED || maxRange.compareTo(maxOccursInGroup) <= 0) break;
                    maxOccursInGroup = maxRange;
                    break;
                }
                case 2: {
                    maxRange = StscChecker.getEffectiveMaxRangeChoice(particle);
                    if (maxRange == UNBOUNDED || maxRange.compareTo(maxOccursInGroup) <= 0) break;
                    maxOccursInGroup = maxRange;
                }
            }
            if (maxRange == UNBOUNDED) break;
        }
        if (maxRange != UNBOUNDED) {
            maxRange = nonZeroParticleChildFound && derivedModel.getMaxOccurs() == UNBOUNDED ? UNBOUNDED : derivedModel.getMaxOccurs().multiply(maxOccursTotal.add(maxOccursInGroup));
        }
        return maxRange;
    }

    private static BigInteger getEffectiveMinRangeChoice(SchemaParticle derivedModel) {
        SchemaParticle[] particleChildren = derivedModel.getParticleChildren();
        if (particleChildren.length == 0) {
            return BigInteger.ZERO;
        }
        BigInteger minRange = null;
        block5: for (int i = 0; i < particleChildren.length; ++i) {
            SchemaParticle particle = particleChildren[i];
            switch (particle.getParticleType()) {
                case 4: 
                case 5: {
                    if (minRange != null && minRange.compareTo(particle.getMinOccurs()) <= 0) continue block5;
                    minRange = particle.getMinOccurs();
                    continue block5;
                }
                case 1: 
                case 3: {
                    BigInteger mrs = StscChecker.getEffectiveMinRangeAllSeq(particle);
                    if (minRange != null && minRange.compareTo(mrs) <= 0) continue block5;
                    minRange = mrs;
                    continue block5;
                }
                case 2: {
                    BigInteger mrc = StscChecker.getEffectiveMinRangeChoice(particle);
                    if (minRange != null && minRange.compareTo(mrc) <= 0) continue block5;
                    minRange = mrc;
                }
            }
        }
        if (minRange == null) {
            minRange = BigInteger.ZERO;
        }
        minRange = derivedModel.getMinOccurs().multiply(minRange);
        return minRange;
    }

    private static BigInteger getEffectiveMinRangeAllSeq(SchemaParticle derivedModel) {
        BigInteger minRange = BigInteger.ZERO;
        SchemaParticle[] particleChildren = derivedModel.getParticleChildren();
        BigInteger particleTotalMinOccurs = BigInteger.ZERO;
        block5: for (int i = 0; i < particleChildren.length; ++i) {
            SchemaParticle particle = particleChildren[i];
            switch (particle.getParticleType()) {
                case 4: 
                case 5: {
                    particleTotalMinOccurs = particleTotalMinOccurs.add(particle.getMinOccurs());
                    continue block5;
                }
                case 1: 
                case 3: {
                    particleTotalMinOccurs = particleTotalMinOccurs.add(StscChecker.getEffectiveMinRangeAllSeq(particle));
                    continue block5;
                }
                case 2: {
                    particleTotalMinOccurs = particleTotalMinOccurs.add(StscChecker.getEffectiveMinRangeChoice(particle));
                }
            }
        }
        minRange = derivedModel.getMinOccurs().multiply(particleTotalMinOccurs);
        return minRange;
    }

    private static boolean nsSubset(SchemaParticle baseModel, SchemaParticle derivedModel, Collection errors, XmlObject context) {
        assert (baseModel.getParticleType() == 5);
        assert (derivedModel.getParticleType() == 5);
        boolean nsSubset = false;
        if (StscChecker.occurrenceRangeOK(baseModel, derivedModel, errors, context)) {
            if (baseModel.getWildcardSet().inverse().isDisjoint(derivedModel.getWildcardSet())) {
                nsSubset = true;
            } else {
                nsSubset = false;
                errors.add(XmlError.forObject("rcase-NSSubset.2", new Object[]{StscChecker.printParticle(derivedModel), StscChecker.printParticle(baseModel)}, context));
            }
        } else {
            nsSubset = false;
        }
        return nsSubset;
    }

    private static boolean nsCompat(SchemaParticle baseModel, SchemaLocalElement derivedElement, Collection errors, XmlObject context) {
        assert (baseModel.getParticleType() == 5);
        boolean nsCompat = false;
        if (baseModel.getWildcardSet().contains(derivedElement.getName())) {
            if (StscChecker.occurrenceRangeOK(baseModel, (SchemaParticle)((Object)derivedElement), errors, context)) {
                nsCompat = true;
            }
        } else {
            nsCompat = false;
            errors.add(XmlError.forObject("rcase-NSCompat.1", new Object[]{StscChecker.printParticle((SchemaParticle)((Object)derivedElement)), StscChecker.printParticle(baseModel)}, context));
        }
        return nsCompat;
    }

    private static boolean nameAndTypeOK(SchemaLocalElement baseElement, SchemaLocalElement derivedElement, Collection errors, XmlObject context) {
        if (!((SchemaParticle)((Object)baseElement)).canStartWithElement(derivedElement.getName())) {
            errors.add(XmlError.forObject("rcase-NameAndTypeOK.1", new Object[]{StscChecker.printParticle((SchemaParticle)((Object)derivedElement)), StscChecker.printParticle((SchemaParticle)((Object)baseElement))}, context));
            return false;
        }
        if (!baseElement.isNillable() && derivedElement.isNillable()) {
            errors.add(XmlError.forObject("rcase-NameAndTypeOK.2", new Object[]{StscChecker.printParticle((SchemaParticle)((Object)derivedElement)), StscChecker.printParticle((SchemaParticle)((Object)baseElement))}, context));
            return false;
        }
        if (!StscChecker.occurrenceRangeOK((SchemaParticle)((Object)baseElement), (SchemaParticle)((Object)derivedElement), errors, context)) {
            return false;
        }
        if (!StscChecker.checkFixed(baseElement, derivedElement, errors, context)) {
            return false;
        }
        if (!StscChecker.checkIdentityConstraints(baseElement, derivedElement, errors, context)) {
            return false;
        }
        if (!StscChecker.typeDerivationOK(baseElement.getType(), derivedElement.getType(), errors, context)) {
            return false;
        }
        return StscChecker.blockSetOK(baseElement, derivedElement, errors, context);
    }

    private static boolean blockSetOK(SchemaLocalElement baseElement, SchemaLocalElement derivedElement, Collection errors, XmlObject context) {
        if (baseElement.blockRestriction() && !derivedElement.blockRestriction()) {
            errors.add(XmlError.forObject("rcase-NameAndTypeOK.6", new Object[]{StscChecker.printParticle((SchemaParticle)((Object)derivedElement)), "restriction", StscChecker.printParticle((SchemaParticle)((Object)baseElement))}, context));
            return false;
        }
        if (baseElement.blockExtension() && !derivedElement.blockExtension()) {
            errors.add(XmlError.forObject("rcase-NameAndTypeOK.6", new Object[]{StscChecker.printParticle((SchemaParticle)((Object)derivedElement)), "extension", StscChecker.printParticle((SchemaParticle)((Object)baseElement))}, context));
            return false;
        }
        if (baseElement.blockSubstitution() && !derivedElement.blockSubstitution()) {
            errors.add(XmlError.forObject("rcase-NameAndTypeOK.6", new Object[]{StscChecker.printParticle((SchemaParticle)((Object)derivedElement)), "substitution", StscChecker.printParticle((SchemaParticle)((Object)baseElement))}, context));
            return false;
        }
        return true;
    }

    private static boolean typeDerivationOK(SchemaType baseType, SchemaType derivedType, Collection errors, XmlObject context) {
        boolean typeDerivationOK = false;
        if (baseType.isAssignableFrom(derivedType)) {
            typeDerivationOK = StscChecker.checkAllDerivationsForRestriction(baseType, derivedType, errors, context);
        } else {
            typeDerivationOK = false;
            errors.add(XmlError.forObject("rcase-NameAndTypeOK.7a", new Object[]{StscChecker.printType(derivedType), StscChecker.printType(baseType)}, context));
        }
        return typeDerivationOK;
    }

    private static boolean checkAllDerivationsForRestriction(SchemaType baseType, SchemaType derivedType, Collection errors, XmlObject context) {
        boolean allDerivationsAreRestrictions = true;
        SchemaType currentType = derivedType;
        HashSet<SchemaType> possibleTypes = null;
        if (baseType.getSimpleVariety() == 2) {
            possibleTypes = new HashSet<SchemaType>(Arrays.asList(baseType.getUnionConstituentTypes()));
        }
        while (!baseType.equals(currentType) && possibleTypes != null && !possibleTypes.contains(currentType)) {
            if (currentType.getDerivationType() == 1) {
                currentType = currentType.getBaseType();
                continue;
            }
            allDerivationsAreRestrictions = false;
            errors.add(XmlError.forObject("rcase-NameAndTypeOK.7b", new Object[]{StscChecker.printType(derivedType), StscChecker.printType(baseType), StscChecker.printType(currentType)}, context));
            break;
        }
        return allDerivationsAreRestrictions;
    }

    private static boolean checkIdentityConstraints(SchemaLocalElement baseElement, SchemaLocalElement derivedElement, Collection errors, XmlObject context) {
        boolean identityConstraintsOK = true;
        SchemaIdentityConstraint[] baseConstraints = baseElement.getIdentityConstraints();
        SchemaIdentityConstraint[] derivedConstraints = derivedElement.getIdentityConstraints();
        for (int i = 0; i < derivedConstraints.length; ++i) {
            SchemaIdentityConstraint derivedConstraint = derivedConstraints[i];
            if (!StscChecker.checkForIdentityConstraintExistence(baseConstraints, derivedConstraint)) continue;
            identityConstraintsOK = false;
            errors.add(XmlError.forObject("rcase-NameAndTypeOK.5", new Object[]{StscChecker.printParticle((SchemaParticle)((Object)derivedElement)), StscChecker.printParticle((SchemaParticle)((Object)baseElement))}, context));
            break;
        }
        return identityConstraintsOK;
    }

    private static boolean checkForIdentityConstraintExistence(SchemaIdentityConstraint[] baseConstraints, SchemaIdentityConstraint derivedConstraint) {
        boolean identityConstraintExists = false;
        for (int i = 0; i < baseConstraints.length; ++i) {
            SchemaIdentityConstraint baseConstraint = baseConstraints[i];
            if (!baseConstraint.getName().equals(derivedConstraint.getName())) continue;
            identityConstraintExists = true;
            break;
        }
        return identityConstraintExists;
    }

    private static boolean checkFixed(SchemaLocalElement baseModel, SchemaLocalElement derivedModel, Collection errors, XmlObject context) {
        boolean checkFixed = false;
        if (baseModel.isFixed()) {
            if (baseModel.getDefaultText().equals(derivedModel.getDefaultText())) {
                checkFixed = true;
            } else {
                errors.add(XmlError.forObject("rcase-NameAndTypeOK.4", new Object[]{StscChecker.printParticle((SchemaParticle)((Object)derivedModel)), derivedModel.getDefaultText(), StscChecker.printParticle((SchemaParticle)((Object)baseModel)), baseModel.getDefaultText()}, context));
                checkFixed = false;
            }
        } else {
            checkFixed = true;
        }
        return checkFixed;
    }

    private static boolean occurrenceRangeOK(SchemaParticle baseParticle, SchemaParticle derivedParticle, Collection errors, XmlObject context) {
        boolean occurrenceRangeOK = false;
        if (derivedParticle.getMinOccurs().compareTo(baseParticle.getMinOccurs()) >= 0) {
            if (baseParticle.getMaxOccurs() == null) {
                occurrenceRangeOK = true;
            } else if (derivedParticle.getMaxOccurs() != null && baseParticle.getMaxOccurs() != null && derivedParticle.getMaxOccurs().compareTo(baseParticle.getMaxOccurs()) <= 0) {
                occurrenceRangeOK = true;
            } else {
                occurrenceRangeOK = false;
                errors.add(XmlError.forObject("range-ok.2", new Object[]{StscChecker.printParticle(derivedParticle), StscChecker.printMaxOccurs(derivedParticle.getMaxOccurs()), StscChecker.printParticle(baseParticle), StscChecker.printMaxOccurs(baseParticle.getMaxOccurs())}, context));
            }
        } else {
            occurrenceRangeOK = false;
            errors.add(XmlError.forObject("range-ok.1", new Object[]{StscChecker.printParticle(derivedParticle), derivedParticle.getMinOccurs().toString(), StscChecker.printParticle(baseParticle), baseParticle.getMinOccurs().toString()}, context));
        }
        return occurrenceRangeOK;
    }

    private static String printParticles(List parts) {
        return StscChecker.printParticles(parts.toArray(new SchemaParticle[parts.size()]));
    }

    private static String printParticles(SchemaParticle[] parts) {
        return StscChecker.printParticles(parts, 0, parts.length);
    }

    private static String printParticles(SchemaParticle[] parts, int start) {
        return StscChecker.printParticles(parts, start, parts.length);
    }

    private static String printParticles(SchemaParticle[] parts, int start, int end) {
        StringBuffer buf = new StringBuffer(parts.length * 30);
        int i = start;
        while (i < end) {
            buf.append(StscChecker.printParticle(parts[i]));
            if (++i == end) continue;
            buf.append(", ");
        }
        return buf.toString();
    }

    private static String printParticle(SchemaParticle part) {
        switch (part.getParticleType()) {
            case 1: {
                return "<all>";
            }
            case 2: {
                return "<choice>";
            }
            case 4: {
                return "<element name=\"" + QNameHelper.pretty(part.getName()) + "\">";
            }
            case 3: {
                return "<sequence>";
            }
            case 5: {
                return "<any>";
            }
        }
        return "??";
    }

    private static String printMaxOccurs(BigInteger bi) {
        if (bi == null) {
            return "unbounded";
        }
        return bi.toString();
    }

    private static String printType(SchemaType type) {
        if (type.getName() != null) {
            return QNameHelper.pretty(type.getName());
        }
        return type.toString();
    }

    private static void checkSubstitutionGroups(SchemaGlobalElement[] elts) {
        StscState state = StscState.get();
        for (int i = 0; i < elts.length; ++i) {
            SchemaGlobalElement elt = elts[i];
            SchemaGlobalElement head = elt.substitutionGroup();
            if (head == null) continue;
            SchemaType headType = head.getType();
            SchemaType tailType = elt.getType();
            XmlObject parseTree = ((SchemaGlobalElementImpl)elt)._parseObject;
            if (!headType.isAssignableFrom(tailType)) {
                state.error("e-props-correct.4", new Object[]{QNameHelper.pretty(elt.getName()), QNameHelper.pretty(head.getName())}, parseTree);
                continue;
            }
            if (head.finalExtension() && head.finalRestriction()) {
                state.error("e-props-correct.4a", new Object[]{QNameHelper.pretty(elt.getName()), QNameHelper.pretty(head.getName()), "#all"}, parseTree);
                continue;
            }
            if (headType.equals(tailType)) continue;
            if (head.finalExtension() && tailType.getDerivationType() == 2) {
                state.error("e-props-correct.4a", new Object[]{QNameHelper.pretty(elt.getName()), QNameHelper.pretty(head.getName()), "extension"}, parseTree);
                continue;
            }
            if (!head.finalRestriction() || tailType.getDerivationType() != 1) continue;
            state.error("e-props-correct.4a", new Object[]{QNameHelper.pretty(elt.getName()), QNameHelper.pretty(head.getName()), "restriction"}, parseTree);
        }
    }
}

