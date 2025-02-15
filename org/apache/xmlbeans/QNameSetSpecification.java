/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans;

import java.util.Set;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.QNameSet;

public interface QNameSetSpecification {
    public boolean contains(QName var1);

    public boolean isAll();

    public boolean isEmpty();

    public boolean containsAll(QNameSetSpecification var1);

    public boolean isDisjoint(QNameSetSpecification var1);

    public QNameSet intersect(QNameSetSpecification var1);

    public QNameSet union(QNameSetSpecification var1);

    public QNameSet inverse();

    public Set excludedURIs();

    public Set includedURIs();

    public Set excludedQNamesInIncludedURIs();

    public Set includedQNamesInExcludedURIs();
}

