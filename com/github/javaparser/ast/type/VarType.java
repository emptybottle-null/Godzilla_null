/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.ast.type;

import com.github.javaparser.TokenRange;
import com.github.javaparser.ast.AllFieldsConstructor;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.expr.AnnotationExpr;
import com.github.javaparser.ast.type.Type;
import com.github.javaparser.ast.visitor.CloneVisitor;
import com.github.javaparser.ast.visitor.GenericVisitor;
import com.github.javaparser.ast.visitor.VoidVisitor;
import com.github.javaparser.metamodel.JavaParserMetaModel;
import com.github.javaparser.metamodel.VarTypeMetaModel;
import com.github.javaparser.resolution.types.ResolvedType;
import java.util.Optional;
import java.util.function.Consumer;

public class VarType
extends Type {
    @AllFieldsConstructor
    public VarType() {
        this((TokenRange)null);
    }

    public VarType(TokenRange tokenRange) {
        super(tokenRange);
        this.customInitialization();
    }

    @Override
    public VarType setAnnotations(NodeList<AnnotationExpr> annotations) {
        return (VarType)super.setAnnotations(annotations);
    }

    @Override
    public boolean remove(Node node) {
        if (node == null) {
            return false;
        }
        return super.remove(node);
    }

    @Override
    public String asString() {
        return "var";
    }

    @Override
    public VarType clone() {
        return (VarType)this.accept(new CloneVisitor(), null);
    }

    @Override
    public VarTypeMetaModel getMetaModel() {
        return JavaParserMetaModel.varTypeMetaModel;
    }

    @Override
    public boolean replace(Node node, Node replacementNode) {
        if (node == null) {
            return false;
        }
        return super.replace(node, replacementNode);
    }

    @Override
    public ResolvedType resolve() {
        return this.getSymbolResolver().toResolvedType(this, ResolvedType.class);
    }

    @Override
    public <R, A> R accept(GenericVisitor<R, A> v, A arg) {
        return v.visit(this, arg);
    }

    @Override
    public <A> void accept(VoidVisitor<A> v, A arg) {
        v.visit(this, arg);
    }

    @Override
    public boolean isVarType() {
        return true;
    }

    @Override
    public VarType asVarType() {
        return this;
    }

    @Override
    public Optional<VarType> toVarType() {
        return Optional.of(this);
    }

    @Override
    public void ifVarType(Consumer<VarType> action) {
        action.accept(this);
    }
}

