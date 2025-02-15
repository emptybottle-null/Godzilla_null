/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.schema;

import java.io.File;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
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
import org.apache.xmlbeans.SchemaParticle;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SchemaTypeSystem;
import org.apache.xmlbeans.impl.schema.BuiltinSchemaTypeSystem;
import org.apache.xmlbeans.impl.schema.SchemaAttributeModelImpl;
import org.apache.xmlbeans.impl.schema.SchemaContainer;
import org.apache.xmlbeans.impl.schema.SchemaGlobalAttributeImpl;
import org.apache.xmlbeans.impl.schema.SchemaLocalAttributeImpl;
import org.apache.xmlbeans.impl.schema.SchemaParticleImpl;
import org.apache.xmlbeans.impl.schema.SchemaTypeImpl;
import org.apache.xmlbeans.impl.schema.SchemaTypeLoaderBase;

public class SoapEncSchemaTypeSystem
extends SchemaTypeLoaderBase
implements SchemaTypeSystem {
    public static final String SOAPENC = "http://schemas.xmlsoap.org/soap/encoding/";
    public static final String SOAP_ARRAY = "Array";
    public static final String ARRAY_TYPE = "arrayType";
    private static final String ATTR_ID = "id";
    private static final String ATTR_HREF = "href";
    private static final String ATTR_OFFSET = "offset";
    private static final SchemaType[] EMPTY_SCHEMATYPE_ARRAY = new SchemaType[0];
    private static final SchemaGlobalElement[] EMPTY_SCHEMAELEMENT_ARRAY = new SchemaGlobalElement[0];
    private static final SchemaModelGroup[] EMPTY_SCHEMAMODELGROUP_ARRAY = new SchemaModelGroup[0];
    private static final SchemaAttributeGroup[] EMPTY_SCHEMAATTRIBUTEGROUP_ARRAY = new SchemaAttributeGroup[0];
    private static final SchemaAnnotation[] EMPTY_SCHEMAANNOTATION_ARRAY = new SchemaAnnotation[0];
    private static SoapEncSchemaTypeSystem _global = new SoapEncSchemaTypeSystem();
    private SchemaTypeImpl soapArray;
    private SchemaGlobalAttributeImpl arrayType;
    private Map _handlesToObjects = new HashMap();
    private String soapArrayHandle;
    private SchemaContainer _container = new SchemaContainer("http://schemas.xmlsoap.org/soap/encoding/");

    public static SchemaTypeSystem get() {
        return _global;
    }

    private SoapEncSchemaTypeSystem() {
        this._container.setTypeSystem(this);
        this.soapArray = new SchemaTypeImpl(this._container, true);
        this._container.addGlobalType(this.soapArray.getRef());
        this.soapArray.setName(new QName(SOAPENC, SOAP_ARRAY));
        this.soapArrayHandle = SOAP_ARRAY.toLowerCase() + "type";
        this.soapArray.setComplexTypeVariety(3);
        this.soapArray.setBaseTypeRef(BuiltinSchemaTypeSystem.ST_ANY_TYPE.getRef());
        this.soapArray.setBaseDepth(1);
        this.soapArray.setDerivationType(2);
        this.soapArray.setSimpleTypeVariety(0);
        SchemaParticleImpl contentModel = new SchemaParticleImpl();
        contentModel.setParticleType(3);
        contentModel.setMinOccurs(BigInteger.ZERO);
        contentModel.setMaxOccurs(BigInteger.ONE);
        contentModel.setTransitionRules(QNameSet.ALL, true);
        SchemaParticle[] children = new SchemaParticleImpl[1];
        contentModel.setParticleChildren(children);
        SchemaParticleImpl contentModel2 = new SchemaParticleImpl();
        contentModel2.setParticleType(5);
        contentModel2.setWildcardSet(QNameSet.ALL);
        contentModel2.setWildcardProcess(2);
        contentModel2.setMinOccurs(BigInteger.ZERO);
        contentModel2.setMaxOccurs(null);
        contentModel2.setTransitionRules(QNameSet.ALL, true);
        children[0] = contentModel2;
        SchemaAttributeModelImpl attrModel = new SchemaAttributeModelImpl();
        attrModel.setWildcardProcess(2);
        HashSet<String> excludedURI = new HashSet<String>();
        excludedURI.add(SOAPENC);
        attrModel.setWildcardSet(QNameSet.forSets(excludedURI, null, Collections.EMPTY_SET, Collections.EMPTY_SET));
        SchemaLocalAttributeImpl attr = new SchemaLocalAttributeImpl();
        attr.init(new QName("", ATTR_ID), BuiltinSchemaTypeSystem.ST_ID.getRef(), 2, null, null, null, false, null, null, null);
        attrModel.addAttribute(attr);
        attr = new SchemaLocalAttributeImpl();
        attr.init(new QName("", ATTR_HREF), BuiltinSchemaTypeSystem.ST_ANY_URI.getRef(), 2, null, null, null, false, null, null, null);
        attrModel.addAttribute(attr);
        attr = new SchemaLocalAttributeImpl();
        attr.init(new QName(SOAPENC, ARRAY_TYPE), BuiltinSchemaTypeSystem.ST_STRING.getRef(), 2, null, null, null, false, null, null, null);
        attrModel.addAttribute(attr);
        attr = new SchemaLocalAttributeImpl();
        attr.init(new QName(SOAPENC, ATTR_OFFSET), BuiltinSchemaTypeSystem.ST_STRING.getRef(), 2, null, null, null, false, null, null, null);
        attrModel.addAttribute(attr);
        this.soapArray.setContentModel(contentModel, attrModel, Collections.EMPTY_MAP, Collections.EMPTY_MAP, false);
        this.arrayType = new SchemaGlobalAttributeImpl(this._container);
        this._container.addGlobalAttribute(this.arrayType.getRef());
        this.arrayType.init(new QName(SOAPENC, ARRAY_TYPE), BuiltinSchemaTypeSystem.ST_STRING.getRef(), 2, null, null, null, false, null, null, null);
        this._handlesToObjects.put(this.soapArrayHandle, this.soapArray);
        this._handlesToObjects.put(ARRAY_TYPE.toLowerCase() + "attribute", this.arrayType);
        this._container.setImmutable();
    }

    @Override
    public String getName() {
        return "schema.typesystem.soapenc.builtin";
    }

    @Override
    public SchemaType findType(QName qName) {
        if (SOAPENC.equals(qName.getNamespaceURI()) && SOAP_ARRAY.equals(qName.getLocalPart())) {
            return this.soapArray;
        }
        return null;
    }

    @Override
    public SchemaType findDocumentType(QName qName) {
        return null;
    }

    @Override
    public SchemaType findAttributeType(QName qName) {
        return null;
    }

    @Override
    public SchemaGlobalElement findElement(QName qName) {
        return null;
    }

    @Override
    public SchemaGlobalAttribute findAttribute(QName qName) {
        if (SOAPENC.equals(qName.getNamespaceURI()) && ARRAY_TYPE.equals(qName.getLocalPart())) {
            return this.arrayType;
        }
        return null;
    }

    @Override
    public SchemaModelGroup findModelGroup(QName qName) {
        return null;
    }

    @Override
    public SchemaAttributeGroup findAttributeGroup(QName qName) {
        return null;
    }

    @Override
    public boolean isNamespaceDefined(String string) {
        return SOAPENC.equals(string);
    }

    @Override
    public SchemaType.Ref findTypeRef(QName qName) {
        SchemaType type = this.findType(qName);
        return type == null ? null : type.getRef();
    }

    @Override
    public SchemaType.Ref findDocumentTypeRef(QName qName) {
        return null;
    }

    @Override
    public SchemaType.Ref findAttributeTypeRef(QName qName) {
        return null;
    }

    @Override
    public SchemaGlobalElement.Ref findElementRef(QName qName) {
        return null;
    }

    @Override
    public SchemaGlobalAttribute.Ref findAttributeRef(QName qName) {
        SchemaGlobalAttribute attr = this.findAttribute(qName);
        return attr == null ? null : attr.getRef();
    }

    @Override
    public SchemaModelGroup.Ref findModelGroupRef(QName qName) {
        return null;
    }

    @Override
    public SchemaAttributeGroup.Ref findAttributeGroupRef(QName qName) {
        return null;
    }

    @Override
    public SchemaIdentityConstraint.Ref findIdentityConstraintRef(QName qName) {
        return null;
    }

    @Override
    public SchemaType typeForClassname(String string) {
        return null;
    }

    @Override
    public InputStream getSourceAsStream(String string) {
        return null;
    }

    @Override
    public ClassLoader getClassLoader() {
        return SoapEncSchemaTypeSystem.class.getClassLoader();
    }

    @Override
    public void resolve() {
    }

    @Override
    public SchemaType[] globalTypes() {
        return new SchemaType[]{this.soapArray};
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
        return new SchemaGlobalAttribute[]{this.arrayType};
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
        if (this.soapArray.equals(type)) {
            return this.soapArrayHandle;
        }
        return null;
    }

    @Override
    public SchemaComponent resolveHandle(String string) {
        return (SchemaComponent)this._handlesToObjects.get(string);
    }

    @Override
    public SchemaType typeForHandle(String string) {
        return (SchemaType)this._handlesToObjects.get(string);
    }

    @Override
    public void saveToDirectory(File file) {
        throw new UnsupportedOperationException("The builtin soap encoding schema type system cannot be saved.");
    }

    @Override
    public void save(Filer filer) {
        throw new UnsupportedOperationException("The builtin soap encoding schema type system cannot be saved.");
    }
}

