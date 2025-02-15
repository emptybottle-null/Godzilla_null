/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.optimization.linear;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
@Deprecated
public enum Relationship {
    EQ("="),
    LEQ("<="),
    GEQ(">=");

    private final String stringValue;

    private Relationship(String stringValue) {
        this.stringValue = stringValue;
    }

    public String toString() {
        return this.stringValue;
    }

    public Relationship oppositeRelationship() {
        switch (this) {
            case LEQ: {
                return GEQ;
            }
            case GEQ: {
                return LEQ;
            }
        }
        return EQ;
    }
}

