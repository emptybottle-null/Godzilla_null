/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.java;

import org.fife.rsta.ac.java.MemberCompletion;
import org.fife.rsta.ac.java.rjc.ast.Method;
import org.fife.rsta.ac.java.rjc.ast.TypeDeclaration;
import org.fife.rsta.ac.java.rjc.lang.Modifiers;
import org.fife.rsta.ac.java.rjc.lang.Type;

class MethodData
implements MemberCompletion.Data {
    private Method method;

    public MethodData(Method method) {
        this.method = method;
    }

    @Override
    public String getEnclosingClassName(boolean fullyQualified) {
        TypeDeclaration td = this.method.getParentTypeDeclaration();
        if (td == null) {
            new Exception("No parent type declaration for: " + this.getSignature()).printStackTrace();
            return "";
        }
        return td.getName(fullyQualified);
    }

    @Override
    public String getIcon() {
        Modifiers mod = this.method.getModifiers();
        String key = mod == null ? "methodDefaultIcon" : (mod.isPrivate() ? "methodPrivateIcon" : (mod.isProtected() ? "methodProtectedIcon" : (mod.isPublic() ? "methodPublicIcon" : "methodDefaultIcon")));
        return key;
    }

    @Override
    public String getSignature() {
        return this.method.getNameAndParameters();
    }

    @Override
    public String getSummary() {
        String docComment = this.method.getDocComment();
        return docComment != null ? docComment : this.method.toString();
    }

    @Override
    public String getType() {
        Type type = this.method.getType();
        return type == null ? "void" : type.toString();
    }

    @Override
    public boolean isAbstract() {
        return this.method.getModifiers().isAbstract();
    }

    @Override
    public boolean isConstructor() {
        return this.method.isConstructor();
    }

    @Override
    public boolean isDeprecated() {
        return this.method.isDeprecated();
    }

    @Override
    public boolean isFinal() {
        return this.method.getModifiers().isFinal();
    }

    @Override
    public boolean isStatic() {
        return this.method.getModifiers().isStatic();
    }
}

