/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans;

import java.math.BigInteger;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.QNameSet;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlAnySimpleType;

public interface SchemaParticle {
    public static final int ALL = 1;
    public static final int CHOICE = 2;
    public static final int SEQUENCE = 3;
    public static final int ELEMENT = 4;
    public static final int WILDCARD = 5;
    public static final int STRICT = 1;
    public static final int LAX = 2;
    public static final int SKIP = 3;

    public int getParticleType();

    public BigInteger getMinOccurs();

    public BigInteger getMaxOccurs();

    public int getIntMinOccurs();

    public int getIntMaxOccurs();

    public boolean isSingleton();

    public SchemaParticle[] getParticleChildren();

    public SchemaParticle getParticleChild(int var1);

    public int countOfParticleChild();

    public boolean canStartWithElement(QName var1);

    public QNameSet acceptedStartNames();

    public boolean isSkippable();

    public QNameSet getWildcardSet();

    public int getWildcardProcess();

    public QName getName();

    public SchemaType getType();

    public boolean isNillable();

    public String getDefaultText();

    public XmlAnySimpleType getDefaultValue();

    public boolean isDefault();

    public boolean isFixed();
}

