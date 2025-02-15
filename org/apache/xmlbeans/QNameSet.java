/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.QNameSetBuilder;
import org.apache.xmlbeans.QNameSetSpecification;

public final class QNameSet
implements QNameSetSpecification,
Serializable {
    private static final long serialVersionUID = 1L;
    private final boolean _inverted;
    private final Set _includedURIs;
    private final Set _excludedQNames;
    private final Set _includedQNames;
    public static final QNameSet EMPTY = new QNameSet(null, Collections.EMPTY_SET, Collections.EMPTY_SET, Collections.EMPTY_SET);
    public static final QNameSet ALL = new QNameSet(Collections.EMPTY_SET, null, Collections.EMPTY_SET, Collections.EMPTY_SET);
    public static final QNameSet LOCAL = new QNameSet(null, Collections.singleton(""), Collections.EMPTY_SET, Collections.EMPTY_SET);
    public static final QNameSet NONLOCAL = new QNameSet(Collections.singleton(""), null, Collections.EMPTY_SET, Collections.EMPTY_SET);

    private static Set minSetCopy(Set original) {
        if (original == null) {
            return null;
        }
        if (original.isEmpty()) {
            return Collections.EMPTY_SET;
        }
        if (original.size() == 1) {
            return Collections.singleton(original.iterator().next());
        }
        return new HashSet(original);
    }

    public static QNameSet forSets(Set excludedURIs, Set includedURIs, Set excludedQNamesInIncludedURIs, Set includedQNamesInExcludedURIs) {
        if (excludedURIs != null == (includedURIs != null)) {
            throw new IllegalArgumentException("Exactly one of excludedURIs and includedURIs must be null");
        }
        if (excludedURIs == null && includedURIs.isEmpty() && includedQNamesInExcludedURIs.isEmpty()) {
            return EMPTY;
        }
        if (includedURIs == null && excludedURIs.isEmpty() && excludedQNamesInIncludedURIs.isEmpty()) {
            return ALL;
        }
        if (excludedURIs == null && includedURIs.size() == 1 && includedURIs.contains("") && includedQNamesInExcludedURIs.isEmpty() && excludedQNamesInIncludedURIs.isEmpty()) {
            return LOCAL;
        }
        if (includedURIs == null && excludedURIs.size() == 1 && excludedURIs.contains("") && excludedQNamesInIncludedURIs.isEmpty() && includedQNamesInExcludedURIs.isEmpty()) {
            return NONLOCAL;
        }
        return new QNameSet(QNameSet.minSetCopy(excludedURIs), QNameSet.minSetCopy(includedURIs), QNameSet.minSetCopy(excludedQNamesInIncludedURIs), QNameSet.minSetCopy(includedQNamesInExcludedURIs));
    }

    public static QNameSet forArray(QName[] includedQNames) {
        if (includedQNames == null) {
            throw new IllegalArgumentException("includedQNames cannot be null");
        }
        return new QNameSet(null, Collections.EMPTY_SET, Collections.EMPTY_SET, new HashSet<QName>(Arrays.asList(includedQNames)));
    }

    public static QNameSet forSpecification(QNameSetSpecification spec) {
        if (spec instanceof QNameSet) {
            return (QNameSet)spec;
        }
        return QNameSet.forSets(spec.excludedURIs(), spec.includedURIs(), spec.excludedQNamesInIncludedURIs(), spec.includedQNamesInExcludedURIs());
    }

    public static QNameSet forWildcardNamespaceString(String wildcard, String targetURI) {
        return QNameSet.forSpecification(new QNameSetBuilder(wildcard, targetURI));
    }

    public static QNameSet singleton(QName name) {
        return new QNameSet(null, Collections.EMPTY_SET, Collections.EMPTY_SET, Collections.singleton(name));
    }

    private QNameSet(Set excludedURIs, Set includedURIs, Set excludedQNamesInIncludedURIs, Set includedQNamesInExcludedURIs) {
        if (includedURIs != null && excludedURIs == null) {
            this._inverted = false;
            this._includedURIs = includedURIs;
            this._excludedQNames = excludedQNamesInIncludedURIs;
            this._includedQNames = includedQNamesInExcludedURIs;
        } else if (excludedURIs != null && includedURIs == null) {
            this._inverted = true;
            this._includedURIs = excludedURIs;
            this._excludedQNames = includedQNamesInExcludedURIs;
            this._includedQNames = excludedQNamesInIncludedURIs;
        } else {
            throw new IllegalArgumentException("Exactly one of excludedURIs and includedURIs must be null");
        }
    }

    private static String nsFromName(QName xmlName) {
        String ns = xmlName.getNamespaceURI();
        return ns == null ? "" : ns;
    }

    @Override
    public boolean contains(QName name) {
        boolean in = this._includedURIs.contains(QNameSet.nsFromName(name)) ? !this._excludedQNames.contains(name) : this._includedQNames.contains(name);
        return this._inverted ^ in;
    }

    @Override
    public boolean isAll() {
        return this._inverted && this._includedURIs.isEmpty() && this._includedQNames.isEmpty();
    }

    @Override
    public boolean isEmpty() {
        return !this._inverted && this._includedURIs.isEmpty() && this._includedQNames.isEmpty();
    }

    @Override
    public QNameSet intersect(QNameSetSpecification set) {
        QNameSetBuilder result = new QNameSetBuilder(this);
        result.restrict(set);
        return result.toQNameSet();
    }

    @Override
    public QNameSet union(QNameSetSpecification set) {
        QNameSetBuilder result = new QNameSetBuilder(this);
        result.addAll(set);
        return result.toQNameSet();
    }

    @Override
    public QNameSet inverse() {
        if (this == EMPTY) {
            return ALL;
        }
        if (this == ALL) {
            return EMPTY;
        }
        if (this == LOCAL) {
            return NONLOCAL;
        }
        if (this == NONLOCAL) {
            return LOCAL;
        }
        return new QNameSet(this.includedURIs(), this.excludedURIs(), this.includedQNamesInExcludedURIs(), this.excludedQNamesInIncludedURIs());
    }

    @Override
    public boolean containsAll(QNameSetSpecification set) {
        if (!this._inverted && set.excludedURIs() != null) {
            return false;
        }
        return this.inverse().isDisjoint(set);
    }

    @Override
    public boolean isDisjoint(QNameSetSpecification set) {
        if (this._inverted && set.excludedURIs() != null) {
            return false;
        }
        if (this._inverted) {
            return this.isDisjointImpl(set, this);
        }
        return this.isDisjointImpl(this, set);
    }

    private boolean isDisjointImpl(QNameSetSpecification set1, QNameSetSpecification set2) {
        Iterator i;
        Set includeURIs = set1.includedURIs();
        Set otherIncludeURIs = set2.includedURIs();
        if (otherIncludeURIs != null) {
            i = includeURIs.iterator();
            while (i.hasNext()) {
                if (!otherIncludeURIs.contains(i.next())) continue;
                return false;
            }
        } else {
            Set otherExcludeURIs = set2.excludedURIs();
            Iterator i2 = includeURIs.iterator();
            while (i2.hasNext()) {
                if (otherExcludeURIs.contains(i2.next())) continue;
                return false;
            }
        }
        i = set1.includedQNamesInExcludedURIs().iterator();
        while (i.hasNext()) {
            if (!set2.contains((QName)i.next())) continue;
            return false;
        }
        if (includeURIs.size() > 0) {
            i = set2.includedQNamesInExcludedURIs().iterator();
            while (i.hasNext()) {
                if (!set1.contains((QName)i.next())) continue;
                return false;
            }
        }
        return true;
    }

    @Override
    public Set excludedURIs() {
        if (this._inverted) {
            return Collections.unmodifiableSet(this._includedURIs);
        }
        return null;
    }

    @Override
    public Set includedURIs() {
        if (!this._inverted) {
            return this._includedURIs;
        }
        return null;
    }

    @Override
    public Set excludedQNamesInIncludedURIs() {
        return Collections.unmodifiableSet(this._inverted ? this._includedQNames : this._excludedQNames);
    }

    @Override
    public Set includedQNamesInExcludedURIs() {
        return Collections.unmodifiableSet(this._inverted ? this._excludedQNames : this._includedQNames);
    }

    private String prettyQName(QName name) {
        if (name.getNamespaceURI() == null) {
            return name.getLocalPart();
        }
        return name.getLocalPart() + "@" + name.getNamespaceURI();
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("QNameSet");
        sb.append(this._inverted ? "-(" : "+(");
        Iterator i = this._includedURIs.iterator();
        while (i.hasNext()) {
            sb.append("+*@");
            sb.append(i.next());
            sb.append(", ");
        }
        i = this._excludedQNames.iterator();
        while (i.hasNext()) {
            sb.append("-");
            sb.append(this.prettyQName((QName)i.next()));
            sb.append(", ");
        }
        i = this._includedQNames.iterator();
        while (i.hasNext()) {
            sb.append("+");
            sb.append(this.prettyQName((QName)i.next()));
            sb.append(", ");
        }
        int index = sb.lastIndexOf(", ");
        if (index > 0) {
            sb.setLength(index);
        }
        sb.append(')');
        return sb.toString();
    }
}

