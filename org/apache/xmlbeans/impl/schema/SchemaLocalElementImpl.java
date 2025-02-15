/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.schema;

import org.apache.xmlbeans.SchemaAnnotation;
import org.apache.xmlbeans.SchemaIdentityConstraint;
import org.apache.xmlbeans.SchemaLocalElement;
import org.apache.xmlbeans.impl.schema.SchemaParticleImpl;
import org.apache.xmlbeans.soap.SOAPArrayType;
import org.apache.xmlbeans.soap.SchemaWSDLArrayType;

public class SchemaLocalElementImpl
extends SchemaParticleImpl
implements SchemaLocalElement,
SchemaWSDLArrayType {
    private boolean _blockExt;
    private boolean _blockRest;
    private boolean _blockSubst;
    protected boolean _abs;
    private SchemaAnnotation _annotation;
    private SOAPArrayType _wsdlArrayType;
    private SchemaIdentityConstraint.Ref[] _constraints = new SchemaIdentityConstraint.Ref[0];

    public SchemaLocalElementImpl() {
        this.setParticleType(4);
    }

    @Override
    public boolean blockExtension() {
        return this._blockExt;
    }

    @Override
    public boolean blockRestriction() {
        return this._blockRest;
    }

    @Override
    public boolean blockSubstitution() {
        return this._blockSubst;
    }

    @Override
    public boolean isAbstract() {
        return this._abs;
    }

    public void setAbstract(boolean abs) {
        this._abs = abs;
    }

    public void setBlock(boolean extension, boolean restriction, boolean substitution) {
        this.mutate();
        this._blockExt = extension;
        this._blockRest = restriction;
        this._blockSubst = substitution;
    }

    public void setAnnotation(SchemaAnnotation ann) {
        this._annotation = ann;
    }

    public void setWsdlArrayType(SOAPArrayType arrayType) {
        this._wsdlArrayType = arrayType;
    }

    @Override
    public SchemaAnnotation getAnnotation() {
        return this._annotation;
    }

    @Override
    public SOAPArrayType getWSDLArrayType() {
        return this._wsdlArrayType;
    }

    public void setIdentityConstraints(SchemaIdentityConstraint.Ref[] constraints) {
        this.mutate();
        this._constraints = constraints;
    }

    @Override
    public SchemaIdentityConstraint[] getIdentityConstraints() {
        SchemaIdentityConstraint[] result = new SchemaIdentityConstraint[this._constraints.length];
        for (int i = 0; i < result.length; ++i) {
            result[i] = this._constraints[i].get();
        }
        return result;
    }

    public SchemaIdentityConstraint.Ref[] getIdentityConstraintRefs() {
        SchemaIdentityConstraint.Ref[] result = new SchemaIdentityConstraint.Ref[this._constraints.length];
        System.arraycopy(this._constraints, 0, result, 0, result.length);
        return result;
    }
}

