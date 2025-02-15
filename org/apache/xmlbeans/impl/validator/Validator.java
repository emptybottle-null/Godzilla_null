/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.validator;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.GDate;
import org.apache.xmlbeans.GDuration;
import org.apache.xmlbeans.QNameSet;
import org.apache.xmlbeans.SchemaAttributeModel;
import org.apache.xmlbeans.SchemaField;
import org.apache.xmlbeans.SchemaGlobalElement;
import org.apache.xmlbeans.SchemaLocalAttribute;
import org.apache.xmlbeans.SchemaLocalElement;
import org.apache.xmlbeans.SchemaParticle;
import org.apache.xmlbeans.SchemaProperty;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SchemaTypeLoader;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlAnySimpleType;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.XmlQName;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.XmlValidationError;
import org.apache.xmlbeans.impl.common.IdentityConstraint;
import org.apache.xmlbeans.impl.common.InvalidLexicalValueException;
import org.apache.xmlbeans.impl.common.QNameHelper;
import org.apache.xmlbeans.impl.common.ValidationContext;
import org.apache.xmlbeans.impl.common.ValidatorListener;
import org.apache.xmlbeans.impl.common.XmlWhitespace;
import org.apache.xmlbeans.impl.schema.SchemaTypeImpl;
import org.apache.xmlbeans.impl.schema.SchemaTypeVisitorImpl;
import org.apache.xmlbeans.impl.util.XsTypeConverter;
import org.apache.xmlbeans.impl.values.JavaBase64HolderEx;
import org.apache.xmlbeans.impl.values.JavaBooleanHolder;
import org.apache.xmlbeans.impl.values.JavaBooleanHolderEx;
import org.apache.xmlbeans.impl.values.JavaDecimalHolderEx;
import org.apache.xmlbeans.impl.values.JavaDoubleHolderEx;
import org.apache.xmlbeans.impl.values.JavaFloatHolderEx;
import org.apache.xmlbeans.impl.values.JavaHexBinaryHolderEx;
import org.apache.xmlbeans.impl.values.JavaNotationHolderEx;
import org.apache.xmlbeans.impl.values.JavaQNameHolderEx;
import org.apache.xmlbeans.impl.values.JavaStringEnumerationHolderEx;
import org.apache.xmlbeans.impl.values.JavaUriHolderEx;
import org.apache.xmlbeans.impl.values.NamespaceContext;
import org.apache.xmlbeans.impl.values.XmlDateImpl;
import org.apache.xmlbeans.impl.values.XmlDurationImpl;
import org.apache.xmlbeans.impl.values.XmlListImpl;
import org.apache.xmlbeans.impl.values.XmlQNameImpl;
import org.apache.xmlbeans.impl.values.XmlValueOutOfRangeException;

public final class Validator
implements ValidatorListener {
    private LinkedList _visitorPool = new LinkedList();
    private boolean _invalid;
    private SchemaType _rootType;
    private SchemaField _rootField;
    private SchemaTypeLoader _globalTypes;
    private State _stateStack;
    private int _errorState;
    private Collection _errorListener;
    private boolean _treatLaxAsSkip;
    private boolean _strict;
    private ValidatorVC _vc;
    private int _suspendErrors;
    private IdentityConstraint _constraintEngine;
    private int _eatContent;
    private SchemaLocalElement _localElement;
    private SchemaParticle _wildcardElement;
    private SchemaLocalAttribute _localAttribute;
    private SchemaAttributeModel _wildcardAttribute;
    private SchemaType _unionType;
    private String _stringValue;
    private BigDecimal _decimalValue;
    private boolean _booleanValue;
    private float _floatValue;
    private double _doubleValue;
    private QName _qnameValue;
    private GDate _gdateValue;
    private GDuration _gdurationValue;
    private byte[] _byteArrayValue;
    private List _listValue;
    private List _listTypes;

    public Validator(SchemaType type, SchemaField field, SchemaTypeLoader globalLoader, XmlOptions options, Collection defaultErrorListener) {
        options = XmlOptions.maskNull(options);
        this._errorListener = (Collection)options.get("ERROR_LISTENER");
        this._treatLaxAsSkip = options.hasOption("VALIDATE_TREAT_LAX_AS_SKIP");
        this._strict = options.hasOption("VALIDATE_STRICT");
        if (this._errorListener == null) {
            this._errorListener = defaultErrorListener;
        }
        this._constraintEngine = new IdentityConstraint(this._errorListener, type.isDocumentType());
        this._globalTypes = globalLoader;
        this._rootType = type;
        this._rootField = field;
        this._vc = new ValidatorVC();
    }

    public boolean isValid() {
        return !this._invalid && this._constraintEngine.isValid();
    }

    private void emitError(ValidatorListener.Event event, String message, QName offendingQName, SchemaType expectedSchemaType, List expectedQNames, int errorType, SchemaType badSchemaType) {
        this.emitError(event, message, null, null, 0, null, offendingQName, expectedSchemaType, expectedQNames, errorType, badSchemaType);
    }

    private void emitError(ValidatorListener.Event event, String code, Object[] args, QName offendingQName, SchemaType expectedSchemaType, List expectedQNames, int errorType, SchemaType badSchemaType) {
        this.emitError(event, null, code, args, 0, null, offendingQName, expectedSchemaType, expectedQNames, errorType, badSchemaType);
    }

    private void emitError(ValidatorListener.Event event, String message, String code, Object[] args, int severity, QName fieldName, QName offendingQName, SchemaType expectedSchemaType, List expectedQNames, int errorType, SchemaType badSchemaType) {
        ++this._errorState;
        if (this._suspendErrors == 0) {
            if (severity == 0) {
                this._invalid = true;
            }
            if (this._errorListener != null) {
                assert (event != null);
                XmlCursor curs = event.getLocationAsCursor();
                XmlValidationError error = curs != null ? XmlValidationError.forCursorWithDetails(message, code, args, severity, curs, fieldName, offendingQName, expectedSchemaType, expectedQNames, errorType, badSchemaType) : XmlValidationError.forLocationWithDetails(message, code, args, severity, event.getLocation(), fieldName, offendingQName, expectedSchemaType, expectedQNames, errorType, badSchemaType);
                this._errorListener.add(error);
            }
        }
    }

    private void emitFieldError(ValidatorListener.Event event, String code, Object[] args, QName offendingQName, SchemaType expectedSchemaType, List expectedQNames, int errorType, SchemaType badSchemaType) {
        this.emitFieldError(event, null, code, args, 0, offendingQName, expectedSchemaType, expectedQNames, errorType, badSchemaType);
    }

    private void emitFieldError(ValidatorListener.Event event, String message, String code, Object[] args, int severity, QName offendingQName, SchemaType expectedSchemaType, List expectedQNames, int errorType, SchemaType badSchemaType) {
        QName fieldName = null;
        if (this._stateStack != null && this._stateStack._field != null) {
            fieldName = this._stateStack._field.getName();
        }
        this.emitError(event, message, code, args, severity, fieldName, offendingQName, expectedSchemaType, expectedQNames, errorType, badSchemaType);
    }

    @Override
    public void nextEvent(int kind, ValidatorListener.Event event) {
        this.resetValues();
        if (this._eatContent > 0) {
            switch (kind) {
                case 2: {
                    --this._eatContent;
                    break;
                }
                case 1: {
                    ++this._eatContent;
                }
            }
        } else {
            assert (kind == 1 || kind == 4 || kind == 2 || kind == 3 || kind == 5);
            switch (kind) {
                case 1: {
                    this.beginEvent(event);
                    break;
                }
                case 4: {
                    this.attrEvent(event);
                    break;
                }
                case 5: {
                    this.endAttrsEvent(event);
                    break;
                }
                case 3: {
                    this.textEvent(event);
                    break;
                }
                case 2: {
                    this.endEvent(event);
                }
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void beginEvent(ValidatorListener.Event event) {
        SchemaLocalElement sle;
        this._localElement = null;
        this._wildcardElement = null;
        State state = this.topState();
        Object elementType = null;
        SchemaField elementField = null;
        if (state == null) {
            elementType = this._rootType;
            elementField = this._rootField;
        } else {
            SchemaParticle currentParticle;
            QName name = event.getName();
            assert (name != null);
            state._isEmpty = false;
            if (state._isNil) {
                this.emitFieldError(event, "cvc-elt.3.2.1", null, state._field.getName(), state._type, null, 4, state._type);
                this._eatContent = 1;
                return;
            }
            if (!state._isNil && state._field != null && state._field.isFixed()) {
                this.emitFieldError(event, "cvc-elt.5.2.2.1", new Object[]{QNameHelper.pretty(state._field.getName())}, state._field.getName(), state._type, null, 2, state._type);
            }
            if (!state.visit(name)) {
                this.findDetailedErrorBegin(event, state, name);
                this._eatContent = 1;
                return;
            }
            this._wildcardElement = currentParticle = state.currentParticle();
            if (currentParticle.getParticleType() == 5) {
                QNameSet elemWildcardSet = currentParticle.getWildcardSet();
                if (!elemWildcardSet.contains(name)) {
                    this.emitFieldError(event, "cvc-particle.1.3", new Object[]{QNameHelper.pretty(name)}, name, null, null, 2, state._type);
                    this._eatContent = 1;
                    return;
                }
                int wildcardProcess = currentParticle.getWildcardProcess();
                if (wildcardProcess == 3 || wildcardProcess == 2 && this._treatLaxAsSkip) {
                    this._eatContent = 1;
                    return;
                }
                this._localElement = this._globalTypes.findElement(name);
                elementField = this._localElement;
                if (elementField == null) {
                    if (wildcardProcess == 1) {
                        this.emitFieldError(event, "cvc-assess-elt.1.1.1.3.2", new Object[]{QNameHelper.pretty(name)}, name, state._type, null, 2, state._type);
                    }
                    this._eatContent = 1;
                    return;
                }
            } else {
                assert (currentParticle.getParticleType() == 4);
                if (!currentParticle.getName().equals(name)) {
                    if (((SchemaLocalElement)((Object)currentParticle)).blockSubstitution()) {
                        this.emitFieldError(event, "cvc-particle.2.3.3a", new Object[]{QNameHelper.pretty(name)}, name, state._type, null, 2, state._type);
                        this._eatContent = 1;
                        return;
                    }
                    SchemaGlobalElement newField = this._globalTypes.findElement(name);
                    assert (newField != null);
                    if (newField != null) {
                        elementField = newField;
                        this._localElement = newField;
                    }
                } else {
                    elementField = (SchemaField)((Object)currentParticle);
                }
            }
            elementType = elementField.getType();
        }
        assert (elementType != null);
        if (elementType.isNoType()) {
            this.emitFieldError(event, "cvc-elt.1", null, event.getName(), null, null, 3, null);
            this._eatContent = 1;
        }
        Object xsiType = null;
        String value = event.getXsiType();
        if (value != null) {
            int originalErrorState = this._errorState++;
            ++this._suspendErrors;
            try {
                this._vc._event = null;
                xsiType = this._globalTypes.findType(XmlQNameImpl.validateLexical(value, this._vc, event));
            } catch (Throwable t) {
            } finally {
                --this._suspendErrors;
            }
            if (originalErrorState != this._errorState) {
                this.emitFieldError(event, "cvc-elt.4.1", new Object[]{value}, event.getName(), (SchemaType)xsiType, null, 3, state._type);
                this._eatContent = 1;
                return;
            }
            if (xsiType == null) {
                this.emitFieldError(event, "cvc-elt.4.2", new Object[]{value}, event.getName(), null, null, 3, null);
                this._eatContent = 1;
                return;
            }
        }
        if (xsiType != null && !xsiType.equals(elementType)) {
            Object t;
            if (!elementType.isAssignableFrom((SchemaType)xsiType)) {
                this.emitFieldError(event, "cvc-elt.4.3a", new Object[]{xsiType, elementType}, event.getName(), (SchemaType)elementType, null, 3, state == null ? null : state._type);
                this._eatContent = 1;
                return;
            }
            if (elementType.blockExtension()) {
                t = xsiType;
                while (!t.equals(elementType)) {
                    if (t.getDerivationType() == 2) {
                        this.emitFieldError(event, "cvc-elt.4.3b", new Object[]{xsiType, elementType}, event.getName(), (SchemaType)elementType, null, 3, state == null ? null : state._type);
                        this._eatContent = 1;
                        return;
                    }
                    t = t.getBaseType();
                }
            }
            if (elementType.blockRestriction()) {
                t = xsiType;
                while (!t.equals(elementType)) {
                    if (t.getDerivationType() == 1) {
                        this.emitFieldError(event, "cvc-elt.4.3c", new Object[]{xsiType, elementType}, event.getName(), (SchemaType)elementType, null, 3, state == null ? null : state._type);
                        this._eatContent = 1;
                        return;
                    }
                    t = t.getBaseType();
                }
            }
            if (elementField instanceof SchemaLocalElement) {
                this._localElement = sle = (SchemaLocalElement)elementField;
                if (sle.blockExtension() || sle.blockRestriction()) {
                    Object t2 = xsiType;
                    while (!t2.equals(elementType)) {
                        if (t2.getDerivationType() == 1 && sle.blockRestriction() || t2.getDerivationType() == 2 && sle.blockExtension()) {
                            this.emitFieldError(event, "cvc-elt.4.3d", new Object[]{xsiType, QNameHelper.pretty(sle.getName())}, sle.getName(), null, null, 3, null);
                            this._eatContent = 1;
                            return;
                        }
                        t2 = t2.getBaseType();
                    }
                }
            }
            elementType = xsiType;
        }
        if (elementField instanceof SchemaLocalElement) {
            this._localElement = sle = (SchemaLocalElement)elementField;
            if (sle.isAbstract()) {
                this.emitError(event, "cvc-elt.2", new Object[]{QNameHelper.pretty(sle.getName())}, sle.getName(), null, null, 3, null);
                this._eatContent = 1;
                return;
            }
        }
        if (elementType != null && elementType.isAbstract()) {
            this.emitError(event, "cvc-elt.2", new Object[]{elementType}, event.getName(), (SchemaType)elementType, null, 3, state == null ? null : state._type);
            this._eatContent = 1;
            return;
        }
        boolean isNil = false;
        boolean hasNil = false;
        String nilValue = event.getXsiNil();
        if (nilValue != null) {
            this._vc._event = event;
            isNil = JavaBooleanHolder.validateLexical(nilValue, this._vc);
            hasNil = true;
        }
        if (hasNil && (elementField == null || !elementField.isNillable())) {
            this.emitFieldError(event, "cvc-elt.3.1", null, elementField == null ? null : elementField.getName(), (SchemaType)elementType, null, 3, state == null ? null : state._type);
            this._eatContent = 1;
            return;
        }
        if (isNil && elementField != null && elementField.isFixed()) {
            this.emitFieldError(event, "cvc-elt.3.2.2", null, elementField == null ? null : elementField.getName(), (SchemaType)elementType, null, 3, state == null ? null : state._type);
        }
        this.newState((SchemaType)elementType, elementField, isNil);
        this._constraintEngine.element(event, (SchemaType)elementType, elementField instanceof SchemaLocalElement ? ((SchemaLocalElement)elementField).getIdentityConstraints() : null);
    }

    private void attrEvent(ValidatorListener.Event event) {
        SchemaLocalAttribute attrSchema;
        QName attrName = event.getName();
        State state = this.topState();
        if (state._attrs == null) {
            state._attrs = new HashSet();
        }
        if (state._attrs.contains(attrName)) {
            this.emitFieldError(event, "uniqattspec", new Object[]{QNameHelper.pretty(attrName)}, attrName, null, null, 1000, state._type);
            return;
        }
        state._attrs.add(attrName);
        if (!state._canHaveAttrs) {
            this.emitFieldError(event, "cvc-complex-type.3.2.1", new Object[]{QNameHelper.pretty(attrName)}, attrName, null, null, 1000, state._type);
            return;
        }
        SchemaLocalAttribute schemaLocalAttribute = attrSchema = state._attrModel == null ? null : state._attrModel.getAttribute(attrName);
        if (attrSchema != null) {
            this._localAttribute = attrSchema;
            if (attrSchema.getUse() == 1) {
                this.emitFieldError(event, "cvc-complex-type.prohibited-attribute", new Object[]{QNameHelper.pretty(attrName)}, attrName, null, null, 1000, state._type);
                return;
            }
            String value = this.validateSimpleType(attrSchema.getType(), attrSchema, event, false, false);
            this._constraintEngine.attr(event, attrName, attrSchema.getType(), value);
            return;
        }
        int wildcardProcess = state._attrModel.getWildcardProcess();
        this._wildcardAttribute = state._attrModel;
        if (wildcardProcess == 0) {
            this.emitFieldError(event, "cvc-complex-type.3.2.1", new Object[]{QNameHelper.pretty(attrName)}, attrName, null, null, 1000, state._type);
            return;
        }
        QNameSet attrWildcardSet = state._attrModel.getWildcardSet();
        if (!attrWildcardSet.contains(attrName)) {
            this.emitFieldError(event, "cvc-complex-type.3.2.2", new Object[]{QNameHelper.pretty(attrName)}, attrName, null, null, 1000, state._type);
            return;
        }
        if (wildcardProcess == 3 || wildcardProcess == 2 && this._treatLaxAsSkip) {
            return;
        }
        this._localAttribute = attrSchema = this._globalTypes.findAttribute(attrName);
        if (attrSchema == null) {
            if (wildcardProcess == 2) {
                return;
            }
            assert (wildcardProcess == 1);
            this.emitFieldError(event, "cvc-assess-attr.1.2", new Object[]{QNameHelper.pretty(attrName)}, attrName, null, null, 1000, state._type);
            return;
        }
        String value = this.validateSimpleType(attrSchema.getType(), attrSchema, event, false, false);
        this._constraintEngine.attr(event, attrName, attrSchema.getType(), value);
    }

    private void endAttrsEvent(ValidatorListener.Event event) {
        State state = this.topState();
        if (state._attrModel != null) {
            SchemaLocalAttribute[] attrs = state._attrModel.getAttributes();
            for (int i = 0; i < attrs.length; ++i) {
                SchemaLocalAttribute sla = attrs[i];
                if (state._attrs != null && state._attrs.contains(sla.getName())) continue;
                if (sla.getUse() == 3) {
                    this.emitFieldError(event, "cvc-complex-type.4", new Object[]{QNameHelper.pretty(sla.getName())}, sla.getName(), null, null, 1000, state._type);
                    continue;
                }
                if (!sla.isDefault() && !sla.isFixed()) continue;
                this._constraintEngine.attr(event, sla.getName(), sla.getType(), sla.getDefaultText());
            }
        }
    }

    private void endEvent(ValidatorListener.Event event) {
        this._localElement = null;
        this._wildcardElement = null;
        State state = this.topState();
        if (!state._isNil) {
            if (!state.end()) {
                this.findDetailedErrorEnd(event, state);
            }
            if (state._isEmpty) {
                this.handleText(event, true, state._field);
            }
        }
        this.popState(event);
        this._constraintEngine.endElement(event);
    }

    private void textEvent(ValidatorListener.Event event) {
        State state = this.topState();
        if (state._isNil) {
            this.emitFieldError(event, "cvc-elt.3.2.1", null, state._field.getName(), state._type, null, 4, state._type);
        } else {
            this.handleText(event, false, state._field);
        }
        state._isEmpty = false;
    }

    private void handleText(ValidatorListener.Event event, boolean emptyContent, SchemaField field) {
        State state = this.topState();
        if (!state._sawText) {
            String value;
            if (state._hasSimpleContent) {
                value = this.validateSimpleType(state._type, field, event, emptyContent, true);
                this._constraintEngine.text(event, state._type, value, false);
            } else if (state._canHaveMixedContent) {
                value = this.validateSimpleType(XmlString.type, field, event, emptyContent, true);
                this._constraintEngine.text(event, XmlString.type, value, false);
            } else if (emptyContent) {
                this._constraintEngine.text(event, state._type, null, true);
            } else {
                this._constraintEngine.text(event, state._type, "", false);
            }
        }
        if (!(emptyContent || state._canHaveMixedContent || event.textIsWhitespace() || state._hasSimpleContent)) {
            if (field instanceof SchemaLocalElement) {
                SchemaLocalElement e = (SchemaLocalElement)field;
                assert (state._type.getContentType() == 1 || state._type.getContentType() == 3);
                String errorCode = state._type.getContentType() == 1 ? "cvc-complex-type.2.1" : "cvc-complex-type.2.3";
                this.emitError(event, errorCode, new Object[]{QNameHelper.pretty(e.getName())}, e.getName(), field.getType(), null, 3, null);
            } else {
                this.emitError(event, "Can't have mixed content", event.getName(), state._type, null, 3, null);
            }
        }
        if (!emptyContent) {
            state._sawText = true;
        }
    }

    private void findDetailedErrorBegin(ValidatorListener.Event event, State state, QName qName) {
        ArrayList<QName> names;
        ArrayList<QName> expectedNames = new ArrayList<QName>();
        ArrayList<QName> optionalNames = new ArrayList<QName>();
        SchemaProperty[] eltProperties = state._type.getElementProperties();
        for (int ii = 0; ii < eltProperties.length; ++ii) {
            SchemaProperty sProp = eltProperties[ii];
            if (!state.test(sProp.getName())) continue;
            if (0 == BigInteger.ZERO.compareTo(sProp.getMinOccurs())) {
                optionalNames.add(sProp.getName());
                continue;
            }
            expectedNames.add(sProp.getName());
        }
        ArrayList<QName> arrayList = names = expectedNames.size() > 0 ? expectedNames : optionalNames;
        if (names.size() > 0) {
            StringBuffer buf = new StringBuffer();
            Iterator iter = names.iterator();
            while (iter.hasNext()) {
                QName qname = (QName)iter.next();
                buf.append(QNameHelper.pretty(qname));
                if (!iter.hasNext()) continue;
                buf.append(" ");
            }
            this.emitFieldError(event, "cvc-complex-type.2.4a", new Object[]{new Integer(names.size()), buf.toString(), QNameHelper.pretty(qName)}, qName, null, names, 1, state._type);
        } else {
            this.emitFieldError(event, "cvc-complex-type.2.4b", new Object[]{QNameHelper.pretty(qName)}, qName, null, null, 1, state._type);
        }
    }

    private void findDetailedErrorEnd(ValidatorListener.Event event, State state) {
        ArrayList<QName> names;
        SchemaProperty[] eltProperties = state._type.getElementProperties();
        ArrayList<QName> expectedNames = new ArrayList<QName>();
        ArrayList<QName> optionalNames = new ArrayList<QName>();
        for (int ii = 0; ii < eltProperties.length; ++ii) {
            SchemaProperty sProp = eltProperties[ii];
            if (!state.test(sProp.getName())) continue;
            if (0 == BigInteger.ZERO.compareTo(sProp.getMinOccurs())) {
                optionalNames.add(sProp.getName());
                continue;
            }
            expectedNames.add(sProp.getName());
        }
        ArrayList<QName> arrayList = names = expectedNames.size() > 0 ? expectedNames : optionalNames;
        if (names.size() > 0) {
            StringBuffer buf = new StringBuffer();
            Iterator iter = names.iterator();
            while (iter.hasNext()) {
                QName qname = (QName)iter.next();
                buf.append(QNameHelper.pretty(qname));
                if (!iter.hasNext()) continue;
                buf.append(" ");
            }
            this.emitFieldError(event, "cvc-complex-type.2.4c", new Object[]{new Integer(names.size()), buf.toString()}, null, null, names, 1, state._type);
        } else {
            this.emitFieldError(event, "cvc-complex-type.2.4d", null, null, null, null, 2, state._type);
        }
    }

    private boolean derivedFromInteger(SchemaType type) {
        int btc = type.getBuiltinTypeCode();
        while (btc == 0) {
            type = type.getBaseType();
            btc = type.getBuiltinTypeCode();
        }
        return btc >= 22 && btc <= 34;
    }

    private void newState(SchemaType type, SchemaField field, boolean isNil) {
        State state = new State();
        state._type = type;
        state._field = field;
        state._isEmpty = true;
        state._isNil = isNil;
        if (type.isSimpleType()) {
            state._hasSimpleContent = true;
        } else {
            state._canHaveAttrs = true;
            state._attrModel = type.getAttributeModel();
            switch (type.getContentType()) {
                case 1: {
                    break;
                }
                case 2: {
                    state._hasSimpleContent = true;
                    break;
                }
                case 4: {
                    state._canHaveMixedContent = true;
                }
                case 3: {
                    SchemaParticle particle = type.getContentModel();
                    boolean bl = state._canHaveElements = particle != null;
                    if (!state._canHaveElements) break;
                    state._visitor = this.initVisitor(particle);
                    break;
                }
                default: {
                    throw new RuntimeException("Unexpected content type");
                }
            }
        }
        this.pushState(state);
    }

    private void popState(ValidatorListener.Event e) {
        if (this._stateStack._visitor != null) {
            this.poolVisitor(this._stateStack._visitor);
            this._stateStack._visitor = null;
        }
        this._stateStack = this._stateStack._next;
    }

    private void pushState(State state) {
        state._next = this._stateStack;
        this._stateStack = state;
    }

    private void poolVisitor(SchemaTypeVisitorImpl visitor) {
        this._visitorPool.add(visitor);
    }

    private SchemaTypeVisitorImpl initVisitor(SchemaParticle particle) {
        if (this._visitorPool.isEmpty()) {
            return new SchemaTypeVisitorImpl(particle);
        }
        SchemaTypeVisitorImpl result = (SchemaTypeVisitorImpl)this._visitorPool.removeLast();
        result.init(particle);
        return result;
    }

    private State topState() {
        return this._stateStack;
    }

    private String validateSimpleType(SchemaType type, SchemaField field, ValidatorListener.Event event, boolean emptyContent, boolean canApplyDefault) {
        if (!type.isSimpleType() && type.getContentType() != 2) {
            assert (false);
            return null;
        }
        if (type.isNoType()) {
            this.emitError(event, field.isAttribute() ? "cvc-attribute.1" : "cvc-elt.1", null, field.getName(), type, null, 3, null);
            return null;
        }
        String value = "";
        if (!emptyContent) {
            int wsr = type.getWhiteSpaceRule();
            String string = value = wsr == 1 ? event.getText() : event.getText(wsr);
        }
        if (value.length() == 0 && canApplyDefault && field != null && (field.isDefault() || field.isFixed())) {
            if (XmlQName.type.isAssignableFrom(type)) {
                this.emitError(event, "Default QName values are unsupported for " + QNameHelper.readable(type) + " - ignoring.", null, null, 2, field.getName(), null, type, null, 3, null);
                return null;
            }
            String defaultValue = XmlWhitespace.collapse(field.getDefaultText(), type.getWhiteSpaceRule());
            return this.validateSimpleType(type, defaultValue, event) ? defaultValue : null;
        }
        if (!this.validateSimpleType(type, value, event)) {
            return null;
        }
        if (field != null && field.isFixed()) {
            XmlAnySimpleType def;
            String fixedValue = XmlWhitespace.collapse(field.getDefaultText(), type.getWhiteSpaceRule());
            if (!this.validateSimpleType(type, fixedValue, event)) {
                return null;
            }
            XmlAnySimpleType val = type.newValue(value);
            if (!val.valueEquals(def = type.newValue(fixedValue))) {
                if (field.isAttribute()) {
                    this.emitError(event, "cvc-attribute.4", new Object[]{value, fixedValue, QNameHelper.pretty(event.getName())}, null, field.getType(), null, 3, null);
                } else {
                    String errorCode = null;
                    if (field.getType().getContentType() == 4) {
                        errorCode = "cvc-elt.5.2.2.2.1";
                    } else if (type.isSimpleType()) {
                        errorCode = "cvc-elt.5.2.2.2.2";
                    } else assert (false) : "Element with fixed may not be EMPTY or ELEMENT_ONLY";
                    this.emitError(event, errorCode, new Object[]{value, fixedValue}, field.getName(), field.getType(), null, 3, null);
                }
                return null;
            }
        }
        return value;
    }

    private boolean validateSimpleType(SchemaType type, String value, ValidatorListener.Event event) {
        if (!type.isSimpleType() && type.getContentType() != 2) {
            assert (false);
            throw new RuntimeException("Not a simple type");
        }
        int retState = this._errorState;
        switch (type.getSimpleVariety()) {
            case 1: {
                this.validateAtomicType(type, value, event);
                break;
            }
            case 2: {
                this.validateUnionType(type, value, event);
                break;
            }
            case 3: {
                this.validateListType(type, value, event);
                break;
            }
            default: {
                throw new RuntimeException("Unexpected simple variety");
            }
        }
        return retState == this._errorState;
    }

    private void validateAtomicType(SchemaType type, String value, ValidatorListener.Event event) {
        assert (type.getSimpleVariety() == 1);
        int errorState = this._errorState;
        this._vc._event = event;
        switch (type.getPrimitiveType().getBuiltinTypeCode()) {
            case 2: {
                this._stringValue = value;
                break;
            }
            case 12: {
                JavaStringEnumerationHolderEx.validateLexical(value, type, this._vc);
                this._stringValue = value;
                break;
            }
            case 11: {
                JavaDecimalHolderEx.validateLexical(value, type, this._vc);
                if (this.derivedFromInteger(type) && value.lastIndexOf(46) >= 0) {
                    this._vc.invalid("integer", new Object[]{value});
                }
                if (errorState != this._errorState) break;
                this._decimalValue = new BigDecimal(value);
                JavaDecimalHolderEx.validateValue(this._decimalValue, type, this._vc);
                break;
            }
            case 3: {
                this._booleanValue = JavaBooleanHolderEx.validateLexical(value, type, this._vc);
                break;
            }
            case 9: {
                float f = JavaFloatHolderEx.validateLexical(value, type, this._vc);
                if (errorState == this._errorState) {
                    JavaFloatHolderEx.validateValue(f, type, this._vc);
                }
                this._floatValue = f;
                break;
            }
            case 10: {
                double d = JavaDoubleHolderEx.validateLexical(value, type, this._vc);
                if (errorState == this._errorState) {
                    JavaDoubleHolderEx.validateValue(d, type, this._vc);
                }
                this._doubleValue = d;
                break;
            }
            case 7: {
                QName n = JavaQNameHolderEx.validateLexical(value, type, this._vc, event);
                if (errorState == this._errorState) {
                    JavaQNameHolderEx.validateValue(n, type, this._vc);
                }
                this._qnameValue = n;
                break;
            }
            case 6: {
                JavaUriHolderEx.validateLexical(value, type, this._vc);
                if (this._strict) {
                    try {
                        XsTypeConverter.lexAnyURI(value);
                    } catch (InvalidLexicalValueException ilve) {
                        this._vc.invalid("anyURI", new Object[]{value});
                    }
                }
                this._stringValue = value;
                break;
            }
            case 21: {
                if (this._strict && value.length() == 6 && value.charAt(4) == '-' && value.charAt(5) == '-') {
                    this._vc.invalid("date", new Object[]{value});
                }
            }
            case 14: 
            case 15: 
            case 16: 
            case 17: 
            case 18: 
            case 19: 
            case 20: {
                GDate d = XmlDateImpl.validateLexical(value, type, this._vc);
                if (d != null) {
                    XmlDateImpl.validateValue(d, type, this._vc);
                }
                this._gdateValue = d;
                break;
            }
            case 13: {
                GDuration d = XmlDurationImpl.validateLexical(value, type, this._vc);
                if (d != null) {
                    XmlDurationImpl.validateValue(d, type, this._vc);
                }
                this._gdurationValue = d;
                break;
            }
            case 4: {
                byte[] v = JavaBase64HolderEx.validateLexical(value, type, this._vc);
                if (v != null) {
                    JavaBase64HolderEx.validateValue(v, type, this._vc);
                }
                this._byteArrayValue = v;
                break;
            }
            case 5: {
                byte[] v = JavaHexBinaryHolderEx.validateLexical(value, type, this._vc);
                if (v != null) {
                    JavaHexBinaryHolderEx.validateValue(v, type, this._vc);
                }
                this._byteArrayValue = v;
                break;
            }
            case 8: {
                QName n = JavaNotationHolderEx.validateLexical(value, type, this._vc, event);
                if (errorState == this._errorState) {
                    JavaNotationHolderEx.validateValue(n, type, this._vc);
                }
                this._qnameValue = n;
                break;
            }
            default: {
                throw new RuntimeException("Unexpected primitive type code");
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void validateListType(SchemaType type, String value, ValidatorListener.Event event) {
        int i;
        int errorState = this._errorState;
        if (!type.matchPatternFacet(value)) {
            this.emitError(event, "cvc-datatype-valid.1.1", new Object[]{"list", value, QNameHelper.readable(type)}, null, type, null, 2000, null);
        }
        String[] items = XmlListImpl.split_list(value);
        XmlAnySimpleType o = type.getFacet(0);
        if (o != null && (i = ((SimpleValue)((Object)o)).getIntValue()) != items.length) {
            this.emitError(event, "cvc-length-valid.2", new Object[]{value, new Integer(items.length), new Integer(i), QNameHelper.readable(type)}, null, type, null, 2000, null);
        }
        if ((o = type.getFacet(1)) != null && (i = ((SimpleValue)((Object)o)).getIntValue()) > items.length) {
            this.emitError(event, "cvc-length-valid.2", new Object[]{value, new Integer(items.length), new Integer(i), QNameHelper.readable(type)}, null, type, null, 2000, null);
        }
        if ((o = type.getFacet(2)) != null && (i = ((SimpleValue)((Object)o)).getIntValue()) < items.length) {
            this.emitError(event, "cvc-length-valid.2", new Object[]{value, new Integer(items.length), new Integer(i), QNameHelper.readable(type)}, null, type, null, 2000, null);
        }
        SchemaType itemType = type.getListItemType();
        this._listValue = new ArrayList();
        this._listTypes = new ArrayList();
        for (i = 0; i < items.length; ++i) {
            this.validateSimpleType(itemType, items[i], event);
            this.addToList(itemType);
        }
        if (errorState == this._errorState && type.getEnumerationValues() != null) {
            NamespaceContext.push(new NamespaceContext(event));
            try {
                XmlAnySimpleType listValue = ((SchemaTypeImpl)type).newValidatingValue(value);
            } catch (XmlValueOutOfRangeException e) {
                this.emitError(event, "cvc-enumeration-valid", new Object[]{"list", value, QNameHelper.readable(type)}, null, type, null, 2000, null);
            } finally {
                NamespaceContext.pop();
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void validateUnionType(SchemaType type, String value, ValidatorListener.Event event) {
        int i;
        if (!type.matchPatternFacet(value)) {
            this.emitError(event, "cvc-datatype-valid.1.1", new Object[]{"union", value, QNameHelper.readable(type)}, null, type, null, 3000, null);
        }
        int currentWsr = 1;
        String currentValue = value;
        SchemaType[] types = type.getUnionMemberTypes();
        int originalState = this._errorState;
        for (i = 0; i < types.length; ++i) {
            int memberWsr = types[i].getWhiteSpaceRule();
            if (memberWsr == 0) {
                memberWsr = 1;
            }
            if (memberWsr != currentWsr) {
                currentWsr = memberWsr;
                currentValue = XmlWhitespace.collapse(value, currentWsr);
            }
            int originalErrorState = this._errorState;
            ++this._suspendErrors;
            try {
                this.validateSimpleType(types[i], currentValue, event);
            } finally {
                --this._suspendErrors;
            }
            if (originalErrorState != this._errorState) continue;
            this._unionType = types[i];
            break;
        }
        this._errorState = originalState;
        if (i >= types.length) {
            this.emitError(event, "cvc-datatype-valid.1.2.3", new Object[]{value, QNameHelper.readable(type)}, null, type, null, 3000, null);
        } else {
            XmlAnySimpleType[] unionEnumvals = type.getEnumerationValues();
            if (unionEnumvals != null) {
                NamespaceContext.push(new NamespaceContext(event));
                try {
                    XmlAnySimpleType unionValue = type.newValue(value);
                    for (i = 0; i < unionEnumvals.length && !unionValue.valueEquals(unionEnumvals[i]); ++i) {
                    }
                    if (i >= unionEnumvals.length) {
                        this.emitError(event, "cvc-enumeration-valid", new Object[]{"union", value, QNameHelper.readable(type)}, null, type, null, 3000, null);
                    }
                } catch (XmlValueOutOfRangeException e) {
                    this.emitError(event, "cvc-enumeration-valid", new Object[]{"union", value, QNameHelper.readable(type)}, null, type, null, 3000, null);
                } finally {
                    NamespaceContext.pop();
                }
            }
        }
    }

    private void addToList(SchemaType type) {
        if (type.getSimpleVariety() != 1 && type.getSimpleVariety() != 2) {
            return;
        }
        if (type.getUnionMemberTypes().length > 0 && this.getUnionType() != null) {
            type = this.getUnionType();
            this._unionType = null;
        }
        this._listTypes.add(type);
        if (type.getPrimitiveType() == null) {
            this._listValue.add(null);
            return;
        }
        switch (type.getPrimitiveType().getBuiltinTypeCode()) {
            case 2: {
                this._listValue.add(this._stringValue);
                break;
            }
            case 12: {
                this._listValue.add(this._stringValue);
                this._stringValue = null;
                break;
            }
            case 11: {
                this._listValue.add(this._decimalValue);
                this._decimalValue = null;
                break;
            }
            case 3: {
                this._listValue.add(this._booleanValue ? Boolean.TRUE : Boolean.FALSE);
                this._booleanValue = false;
                break;
            }
            case 9: {
                this._listValue.add(new Float(this._floatValue));
                this._floatValue = 0.0f;
                break;
            }
            case 10: {
                this._listValue.add(new Double(this._doubleValue));
                this._doubleValue = 0.0;
                break;
            }
            case 7: {
                this._listValue.add(this._qnameValue);
                this._qnameValue = null;
                break;
            }
            case 6: {
                this._listTypes.add(this._stringValue);
                break;
            }
            case 14: 
            case 15: 
            case 16: 
            case 17: 
            case 18: 
            case 19: 
            case 20: 
            case 21: {
                this._listValue.add(this._gdateValue);
                this._gdateValue = null;
                break;
            }
            case 13: {
                this._listValue.add(this._gdurationValue);
                this._gdurationValue = null;
                break;
            }
            case 4: {
                this._listValue.add(this._byteArrayValue);
                this._byteArrayValue = null;
                break;
            }
            case 5: {
                this._listValue.add(this._byteArrayValue);
                this._byteArrayValue = null;
                break;
            }
            case 8: {
                this._listValue.add(this._qnameValue);
                this._qnameValue = null;
                break;
            }
            default: {
                throw new RuntimeException("Unexpected primitive type code");
            }
        }
    }

    private void resetValues() {
        this._localAttribute = null;
        this._wildcardAttribute = null;
        this._stringValue = null;
        this._decimalValue = null;
        this._booleanValue = false;
        this._floatValue = 0.0f;
        this._doubleValue = 0.0;
        this._qnameValue = null;
        this._gdateValue = null;
        this._gdurationValue = null;
        this._byteArrayValue = null;
        this._listValue = null;
        this._listTypes = null;
        this._unionType = null;
        this._localAttribute = null;
    }

    public SchemaType getCurrentElementSchemaType() {
        State state = this.topState();
        if (state != null) {
            return state._type;
        }
        return null;
    }

    public SchemaLocalElement getCurrentElement() {
        if (this._localElement != null) {
            return this._localElement;
        }
        if (this._eatContent > 0) {
            return null;
        }
        if (this._stateStack != null && this._stateStack._field instanceof SchemaLocalElement) {
            return (SchemaLocalElement)this._stateStack._field;
        }
        return null;
    }

    public SchemaParticle getCurrentWildcardElement() {
        return this._wildcardElement;
    }

    public SchemaLocalAttribute getCurrentAttribute() {
        return this._localAttribute;
    }

    public SchemaAttributeModel getCurrentWildcardAttribute() {
        return this._wildcardAttribute;
    }

    public String getStringValue() {
        return this._stringValue;
    }

    public BigDecimal getDecimalValue() {
        return this._decimalValue;
    }

    public boolean getBooleanValue() {
        return this._booleanValue;
    }

    public float getFloatValue() {
        return this._floatValue;
    }

    public double getDoubleValue() {
        return this._doubleValue;
    }

    public QName getQNameValue() {
        return this._qnameValue;
    }

    public GDate getGDateValue() {
        return this._gdateValue;
    }

    public GDuration getGDurationValue() {
        return this._gdurationValue;
    }

    public byte[] getByteArrayValue() {
        return this._byteArrayValue;
    }

    public List getListValue() {
        return this._listValue;
    }

    public List getListTypes() {
        return this._listTypes;
    }

    public SchemaType getUnionType() {
        return this._unionType;
    }

    private final class State {
        SchemaType _type;
        SchemaField _field;
        boolean _canHaveAttrs;
        boolean _canHaveMixedContent;
        boolean _hasSimpleContent;
        boolean _sawText;
        boolean _isEmpty;
        boolean _isNil;
        SchemaTypeVisitorImpl _visitor;
        boolean _canHaveElements;
        SchemaAttributeModel _attrModel;
        HashSet _attrs;
        State _next;

        private State() {
        }

        boolean visit(QName name) {
            return this._canHaveElements && this._visitor.visit(name);
        }

        boolean test(QName name) {
            return this._canHaveElements && this._visitor.testValid(name);
        }

        boolean end() {
            return !this._canHaveElements || this._visitor.visit(null);
        }

        SchemaParticle currentParticle() {
            assert (this._visitor != null);
            return this._visitor.currentParticle();
        }
    }

    private class ValidatorVC
    implements ValidationContext {
        ValidatorListener.Event _event;

        private ValidatorVC() {
        }

        @Override
        public void invalid(String message) {
            Validator.this.emitError(this._event, message, null, null, null, 1001, null);
        }

        @Override
        public void invalid(String code, Object[] args) {
            Validator.this.emitError(this._event, code, args, null, null, null, 1001, null);
        }
    }
}

