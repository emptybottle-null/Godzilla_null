/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.resolution.types;

import com.github.javaparser.resolution.types.ResolvedType;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum ResolvedPrimitiveType implements ResolvedType
{
    BYTE("byte", Byte.class.getCanonicalName(), Collections.emptyList()),
    SHORT("short", Short.class.getCanonicalName(), Collections.singletonList(BYTE)),
    CHAR("char", Character.class.getCanonicalName(), Collections.emptyList()),
    INT("int", Integer.class.getCanonicalName(), Arrays.asList(BYTE, SHORT, CHAR)),
    LONG("long", Long.class.getCanonicalName(), Arrays.asList(BYTE, SHORT, INT, CHAR)),
    BOOLEAN("boolean", Boolean.class.getCanonicalName(), Collections.emptyList()),
    FLOAT("float", Float.class.getCanonicalName(), Arrays.asList(LONG, INT, SHORT, BYTE, CHAR)),
    DOUBLE("double", Double.class.getCanonicalName(), Arrays.asList(FLOAT, LONG, INT, SHORT, BYTE, CHAR));

    private String name;
    private String boxTypeQName;
    private List<ResolvedPrimitiveType> promotionTypes;

    private ResolvedPrimitiveType(String name, String boxTypeQName, List<ResolvedPrimitiveType> promotionTypes) {
        this.name = name;
        this.boxTypeQName = boxTypeQName;
        this.promotionTypes = promotionTypes;
    }

    public static ResolvedType byName(String name) {
        name = name.toLowerCase();
        for (ResolvedPrimitiveType ptu : ResolvedPrimitiveType.values()) {
            if (!ptu.describe().equals(name)) continue;
            return ptu;
        }
        throw new IllegalArgumentException("Name " + name);
    }

    public static ResolvedPrimitiveType[] getNumericPrimitiveTypes() {
        return new ResolvedPrimitiveType[]{BYTE, SHORT, CHAR, INT, LONG, FLOAT, DOUBLE};
    }

    public String toString() {
        return "PrimitiveTypeUsage{name='" + this.name + '\'' + '}';
    }

    @Override
    public ResolvedPrimitiveType asPrimitive() {
        return this;
    }

    @Override
    public boolean isArray() {
        return false;
    }

    @Override
    public boolean isPrimitive() {
        return true;
    }

    @Override
    public boolean isReferenceType() {
        return false;
    }

    @Override
    public String describe() {
        return this.name;
    }

    @Override
    public boolean isTypeVariable() {
        return false;
    }

    @Override
    public boolean isAssignableBy(ResolvedType other) {
        if (other.isPrimitive()) {
            return this == other || this.promotionTypes.contains(other);
        }
        if (other.isReferenceType()) {
            if (other.asReferenceType().getQualifiedName().equals(this.boxTypeQName)) {
                return true;
            }
            for (ResolvedPrimitiveType promotion : this.promotionTypes) {
                if (!other.asReferenceType().getQualifiedName().equals(promotion.boxTypeQName)) continue;
                return true;
            }
            return false;
        }
        return other.isConstraint() && this.isAssignableBy(other.asConstraintType().getBound());
    }

    public String getBoxTypeQName() {
        return this.boxTypeQName;
    }

    public boolean isNumeric() {
        return this != BOOLEAN;
    }

    public boolean isBoolean() {
        return this == BOOLEAN;
    }

    public ResolvedPrimitiveType bnp(ResolvedPrimitiveType other) {
        if (this == DOUBLE || other == DOUBLE) {
            return DOUBLE;
        }
        if (this == FLOAT || other == FLOAT) {
            return FLOAT;
        }
        if (this == LONG || other == LONG) {
            return LONG;
        }
        return INT;
    }

    public static ResolvedType unp(ResolvedType type) {
        boolean isUnboxable;
        boolean bl = isUnboxable = type.isReferenceType() && type.asReferenceType().isUnboxable();
        if (isUnboxable && type.asReferenceType().toUnboxedType().get().in(new ResolvedPrimitiveType[]{BYTE, SHORT, CHAR, INT})) {
            return INT;
        }
        if (isUnboxable && type.asReferenceType().toUnboxedType().get().in(new ResolvedPrimitiveType[]{LONG, FLOAT, DOUBLE})) {
            return type.asReferenceType().toUnboxedType().get();
        }
        if (type.isPrimitive() && type.asPrimitive().in(new ResolvedPrimitiveType[]{BYTE, CHAR, SHORT})) {
            return INT;
        }
        return type;
    }

    public boolean in(ResolvedPrimitiveType[] types) {
        return Arrays.stream(types).anyMatch(type -> this == type);
    }
}

