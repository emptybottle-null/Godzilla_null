/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.java.rjc.lang;

import java.util.ArrayList;
import java.util.List;
import org.fife.rsta.ac.java.rjc.lang.TypeArgument;

public class Type {
    private List<String> identifiers = new ArrayList<String>(1);
    private List<List<TypeArgument>> typeArguments = new ArrayList<List<TypeArgument>>(1);
    private int bracketPairCount;

    public Type() {
    }

    public Type(String identifier) {
        this();
        this.addIdentifier(identifier, null);
    }

    public Type(String identifier, int bracketPairCount) {
        this();
        this.addIdentifier(identifier, null);
        this.setBracketPairCount(bracketPairCount);
    }

    public void addIdentifier(String identifier, List<TypeArgument> typeArgs) {
        this.identifiers.add(identifier);
        this.typeArguments.add(typeArgs);
    }

    public int getIdentifierCount() {
        return this.identifiers.size();
    }

    public String getName(boolean fullyQualified) {
        return this.getName(fullyQualified, true);
    }

    public String getName(boolean fullyQualified, boolean addTypeArgs) {
        int start;
        int i;
        StringBuilder sb = new StringBuilder();
        int count = this.identifiers.size();
        for (i = start = fullyQualified ? 0 : count - 1; i < count; ++i) {
            List<TypeArgument> typeArgs;
            int typeArgCount;
            sb.append(this.identifiers.get(i));
            if (addTypeArgs && this.typeArguments.get(i) != null && (typeArgCount = (typeArgs = this.typeArguments.get(i)).size()) > 0) {
                sb.append('<');
                for (int j = 0; j < typeArgCount; ++j) {
                    TypeArgument typeArg = typeArgs.get(j);
                    sb.append(typeArg.toString());
                    if (j >= typeArgCount - 1) continue;
                    sb.append(", ");
                }
                sb.append('>');
            }
            if (i >= count - 1) continue;
            sb.append('.');
        }
        for (i = 0; i < this.bracketPairCount; ++i) {
            sb.append("[]");
        }
        return sb.toString();
    }

    public List<TypeArgument> getTypeArguments(int index) {
        return this.typeArguments.get(index);
    }

    public void incrementBracketPairCount(int count) {
        this.bracketPairCount += count;
    }

    public boolean isArray() {
        return this.bracketPairCount > 0;
    }

    public boolean isBasicType() {
        boolean basicType = false;
        if (!this.isArray() && this.identifiers.size() == 1 && this.typeArguments.get(0) == null) {
            String str = this.identifiers.get(0);
            basicType = "byte".equals(str) || "float".equals(str) || "double".equals(str) || "int".equals(str) || "short".equals(str) || "long".equals(str) || "boolean".equals(str);
        }
        return basicType;
    }

    public void setBracketPairCount(int count) {
        this.bracketPairCount = count;
    }

    public String toString() {
        return this.getName(true);
    }
}

