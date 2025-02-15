/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.ast.type;

import com.github.javaparser.TokenRange;
import com.github.javaparser.ast.AllFieldsConstructor;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.expr.AnnotationExpr;
import com.github.javaparser.ast.nodeTypes.NodeWithAnnotations;
import com.github.javaparser.ast.type.Type;
import com.github.javaparser.ast.visitor.CloneVisitor;
import com.github.javaparser.ast.visitor.GenericVisitor;
import com.github.javaparser.ast.visitor.VoidVisitor;
import com.github.javaparser.metamodel.JavaParserMetaModel;
import com.github.javaparser.metamodel.VoidTypeMetaModel;
import com.github.javaparser.resolution.types.ResolvedVoidType;
import java.util.Optional;
import java.util.function.Consumer;

public class VoidType
extends Type
implements NodeWithAnnotations<VoidType> {
    @AllFieldsConstructor
    public VoidType() {
        this((TokenRange)null);
    }

    public VoidType(TokenRange tokenRange) {
        super(tokenRange);
        this.customInitialization();
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
    public VoidType setAnnotations(NodeList<AnnotationExpr> annotations) {
        return (VoidType)super.setAnnotations(annotations);
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
        return "void";
    }

    @Override
    public String toDescriptor() {
        return "V";
    }

    @Override
    public VoidType clone() {
        return (VoidType)this.accept(new CloneVisitor(), null);
    }

    @Override
    public VoidTypeMetaModel getMetaModel() {
        return JavaParserMetaModel.voidTypeMetaModel;
    }

    @Override
    public boolean replace(Node node, Node replacementNode) {
        if (node == null) {
            return false;
        }
        return super.replace(node, replacementNode);
    }

    @Override
    public boolean isVoidType() {
        return true;
    }

    @Override
    public VoidType asVoidType() {
        return this;
    }

    @Override
    public void ifVoidType(Consumer<VoidType> action) {
        action.accept(this);
    }

    @Override
    public ResolvedVoidType resolve() {
        return this.getSymbolResolver().toResolvedType(this, ResolvedVoidType.class);
    }

    @Override
    public Optional<VoidType> toVoidType() {
        return Optional.of(this);
    }
}

