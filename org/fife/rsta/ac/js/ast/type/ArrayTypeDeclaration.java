/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.js.ast.type;

import org.fife.rsta.ac.js.ast.type.TypeDeclaration;

public class ArrayTypeDeclaration
extends TypeDeclaration {
    private TypeDeclaration arrayType;

    public ArrayTypeDeclaration(String pkg, String apiName, String jsName, boolean staticsOnly) {
        super(pkg, apiName, jsName, staticsOnly);
    }

    public ArrayTypeDeclaration(String pkg, String apiName, String jsName) {
        super(pkg, apiName, jsName);
    }

    public TypeDeclaration getArrayType() {
        return this.arrayType;
    }

    public void setArrayType(TypeDeclaration containerType) {
        this.arrayType = containerType;
    }

    @Override
    public boolean equals(Object obj) {
        boolean equals = super.equals(obj);
        if (equals) {
            ArrayTypeDeclaration objArrayType = (ArrayTypeDeclaration)obj;
            if (this.getArrayType() == null && objArrayType.getArrayType() == null) {
                return false;
            }
            if (this.getArrayType() == null && objArrayType.getArrayType() != null) {
                return false;
            }
            if (this.getArrayType() != null && objArrayType.getArrayType() == null) {
                return false;
            }
            return this.getArrayType().equals(((ArrayTypeDeclaration)obj).getArrayType());
        }
        return equals;
    }
}

