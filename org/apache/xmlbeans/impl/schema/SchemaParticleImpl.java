/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.schema;

import java.math.BigInteger;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.QNameSet;
import org.apache.xmlbeans.SchemaParticle;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlAnySimpleType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlQName;
import org.apache.xmlbeans.impl.schema.XmlValueRef;
import org.apache.xmlbeans.impl.values.NamespaceContext;

public class SchemaParticleImpl
implements SchemaParticle {
    private int _particleType;
    private BigInteger _minOccurs;
    private BigInteger _maxOccurs;
    private SchemaParticle[] _particleChildren;
    private boolean _isImmutable;
    private QNameSet _startSet;
    private QNameSet _excludeNextSet;
    private boolean _isSkippable;
    private boolean _isDeterministic;
    private int _intMinOccurs;
    private int _intMaxOccurs;
    private QNameSet _wildcardSet;
    private int _wildcardProcess;
    private String _defaultText;
    private boolean _isDefault;
    private boolean _isFixed;
    private QName _qName;
    private boolean _isNillable;
    private SchemaType.Ref _typeref;
    protected XmlObject _parseObject;
    private Object _userData;
    private XmlValueRef _defaultValue;
    private static final BigInteger _maxint = BigInteger.valueOf(Integer.MAX_VALUE);

    protected void mutate() {
        if (this._isImmutable) {
            throw new IllegalStateException();
        }
    }

    public void setImmutable() {
        this.mutate();
        this._isImmutable = true;
    }

    public boolean hasTransitionRules() {
        return this._startSet != null;
    }

    public boolean hasTransitionNotes() {
        return this._excludeNextSet != null;
    }

    public void setTransitionRules(QNameSet start, boolean isSkippable) {
        this._startSet = start;
        this._isSkippable = isSkippable;
    }

    public void setTransitionNotes(QNameSet excludeNext, boolean isDeterministic) {
        this._excludeNextSet = excludeNext;
        this._isDeterministic = isDeterministic;
    }

    @Override
    public boolean canStartWithElement(QName name) {
        return name != null && this._startSet.contains(name);
    }

    @Override
    public QNameSet acceptedStartNames() {
        return this._startSet;
    }

    public QNameSet getExcludeNextSet() {
        return this._excludeNextSet;
    }

    @Override
    public boolean isSkippable() {
        return this._isSkippable;
    }

    public boolean isDeterministic() {
        return this._isDeterministic;
    }

    @Override
    public int getParticleType() {
        return this._particleType;
    }

    public void setParticleType(int pType) {
        this.mutate();
        this._particleType = pType;
    }

    @Override
    public boolean isSingleton() {
        return this._maxOccurs != null && this._maxOccurs.compareTo(BigInteger.ONE) == 0 && this._minOccurs.compareTo(BigInteger.ONE) == 0;
    }

    @Override
    public BigInteger getMinOccurs() {
        return this._minOccurs;
    }

    public void setMinOccurs(BigInteger min) {
        this.mutate();
        this._minOccurs = min;
        this._intMinOccurs = SchemaParticleImpl.pegBigInteger(min);
    }

    @Override
    public int getIntMinOccurs() {
        return this._intMinOccurs;
    }

    @Override
    public BigInteger getMaxOccurs() {
        return this._maxOccurs;
    }

    @Override
    public int getIntMaxOccurs() {
        return this._intMaxOccurs;
    }

    public void setMaxOccurs(BigInteger max) {
        this.mutate();
        this._maxOccurs = max;
        this._intMaxOccurs = SchemaParticleImpl.pegBigInteger(max);
    }

    @Override
    public SchemaParticle[] getParticleChildren() {
        if (this._particleChildren == null) {
            assert (this._particleType != 1 && this._particleType != 3 && this._particleType != 2);
            return null;
        }
        SchemaParticle[] result = new SchemaParticle[this._particleChildren.length];
        System.arraycopy(this._particleChildren, 0, result, 0, this._particleChildren.length);
        return result;
    }

    public void setParticleChildren(SchemaParticle[] children) {
        this.mutate();
        this._particleChildren = children;
    }

    @Override
    public SchemaParticle getParticleChild(int i) {
        return this._particleChildren[i];
    }

    @Override
    public int countOfParticleChild() {
        return this._particleChildren == null ? 0 : this._particleChildren.length;
    }

    public void setWildcardSet(QNameSet set) {
        this.mutate();
        this._wildcardSet = set;
    }

    @Override
    public QNameSet getWildcardSet() {
        return this._wildcardSet;
    }

    public void setWildcardProcess(int process) {
        this.mutate();
        this._wildcardProcess = process;
    }

    @Override
    public int getWildcardProcess() {
        return this._wildcardProcess;
    }

    private static final int pegBigInteger(BigInteger bi) {
        if (bi == null) {
            return Integer.MAX_VALUE;
        }
        if (bi.signum() <= 0) {
            return 0;
        }
        if (bi.compareTo(_maxint) >= 0) {
            return Integer.MAX_VALUE;
        }
        return bi.intValue();
    }

    @Override
    public QName getName() {
        return this._qName;
    }

    public void setNameAndTypeRef(QName formname, SchemaType.Ref typeref) {
        this.mutate();
        this._qName = formname;
        this._typeref = typeref;
    }

    public boolean isTypeResolved() {
        return this._typeref != null;
    }

    public void resolveTypeRef(SchemaType.Ref typeref) {
        if (this._typeref != null) {
            throw new IllegalStateException();
        }
        this._typeref = typeref;
    }

    public boolean isAttribute() {
        return false;
    }

    @Override
    public SchemaType getType() {
        if (this._typeref == null) {
            return null;
        }
        return this._typeref.get();
    }

    @Override
    public String getDefaultText() {
        return this._defaultText;
    }

    @Override
    public boolean isDefault() {
        return this._isDefault;
    }

    @Override
    public boolean isFixed() {
        return this._isFixed;
    }

    public void setDefault(String deftext, boolean isFixed, XmlObject parseObject) {
        this.mutate();
        this._defaultText = deftext;
        this._isDefault = deftext != null;
        this._isFixed = isFixed;
        this._parseObject = parseObject;
    }

    @Override
    public boolean isNillable() {
        return this._isNillable;
    }

    public void setNillable(boolean nillable) {
        this.mutate();
        this._isNillable = nillable;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlAnySimpleType getDefaultValue() {
        if (this._defaultValue != null) {
            return this._defaultValue.get();
        }
        if (this._defaultText != null && XmlAnySimpleType.type.isAssignableFrom(this.getType())) {
            if (this._parseObject != null && XmlQName.type.isAssignableFrom(this.getType())) {
                try {
                    NamespaceContext.push(new NamespaceContext(this._parseObject));
                    XmlAnySimpleType xmlAnySimpleType = this.getType().newValue(this._defaultText);
                    return xmlAnySimpleType;
                } finally {
                    NamespaceContext.pop();
                }
            }
            return this.getType().newValue(this._defaultText);
        }
        return null;
    }

    public void setDefaultValue(XmlValueRef defaultRef) {
        this.mutate();
        this._defaultValue = defaultRef;
    }

    public Object getUserData() {
        return this._userData;
    }

    public void setUserData(Object data) {
        this._userData = data;
    }
}

