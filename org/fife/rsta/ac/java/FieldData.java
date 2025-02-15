/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.java;

import org.fife.rsta.ac.java.MemberCompletion;
import org.fife.rsta.ac.java.rjc.ast.Field;
import org.fife.rsta.ac.java.rjc.ast.TypeDeclaration;
import org.fife.rsta.ac.java.rjc.lang.Modifiers;

class FieldData
implements MemberCompletion.Data {
    private Field field;

    public FieldData(Field field) {
        this.field = field;
    }

    @Override
    public String getEnclosingClassName(boolean fullyQualified) {
        TypeDeclaration td = this.field.getParentTypeDeclaration();
        if (td == null) {
            new Exception("No parent type declaration for: " + this.getSignature()).printStackTrace();
            return "";
        }
        return td.getName(fullyQualified);
    }

    @Override
    public String getIcon() {
        Modifiers mod = this.field.getModifiers();
        String key = mod == null ? "fieldDefaultIcon" : (mod.isPrivate() ? "fieldPrivateIcon" : (mod.isProtected() ? "fieldProtectedIcon" : (mod.isPublic() ? "fieldPublicIcon" : "fieldDefaultIcon")));
        return key;
    }

    @Override
    public String getSignature() {
        return this.field.getName();
    }

    @Override
    public String getSummary() {
        String docComment = this.field.getDocComment();
        return docComment != null ? docComment : this.field.toString();
    }

    @Override
    public String getType() {
        return this.field.getType().toString();
    }

    @Override
    public boolean isAbstract() {
        return this.field.getModifiers().isAbstract();
    }

    @Override
    public boolean isConstructor() {
        return false;
    }

    @Override
    public boolean isDeprecated() {
        return this.field.isDeprecated();
    }

    @Override
    public boolean isFinal() {
        return this.field.getModifiers().isFinal();
    }

    @Override
    public boolean isStatic() {
        return this.field.getModifiers().isStatic();
    }
}

