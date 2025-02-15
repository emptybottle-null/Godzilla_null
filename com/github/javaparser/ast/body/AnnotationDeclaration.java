/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.ast.body;

import com.github.javaparser.TokenRange;
import com.github.javaparser.ast.AllFieldsConstructor;
import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.BodyDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.ast.expr.AnnotationExpr;
import com.github.javaparser.ast.expr.SimpleName;
import com.github.javaparser.ast.nodeTypes.modifiers.NodeWithAbstractModifier;
import com.github.javaparser.ast.type.Type;
import com.github.javaparser.ast.visitor.CloneVisitor;
import com.github.javaparser.ast.visitor.GenericVisitor;
import com.github.javaparser.ast.visitor.VoidVisitor;
import com.github.javaparser.metamodel.AnnotationDeclarationMetaModel;
import com.github.javaparser.metamodel.JavaParserMetaModel;
import com.github.javaparser.resolution.Resolvable;
import com.github.javaparser.resolution.declarations.ResolvedAnnotationDeclaration;
import java.util.Optional;
import java.util.function.Consumer;

public class AnnotationDeclaration
extends TypeDeclaration<AnnotationDeclaration>
implements NodeWithAbstractModifier<AnnotationDeclaration>,
Resolvable<ResolvedAnnotationDeclaration> {
    public AnnotationDeclaration() {
        this(null, new NodeList<Modifier>(), new NodeList<AnnotationExpr>(), new SimpleName(), new NodeList());
    }

    public AnnotationDeclaration(NodeList<Modifier> modifiers, String name) {
        this(null, modifiers, new NodeList<AnnotationExpr>(), new SimpleName(name), new NodeList());
    }

    @AllFieldsConstructor
    public AnnotationDeclaration(NodeList<Modifier> modifiers, NodeList<AnnotationExpr> annotations, SimpleName name, NodeList<BodyDeclaration<?>> members) {
        this(null, modifiers, annotations, name, members);
    }

    public AnnotationDeclaration(TokenRange tokenRange, NodeList<Modifier> modifiers, NodeList<AnnotationExpr> annotations, SimpleName name, NodeList<BodyDeclaration<?>> members) {
        super(tokenRange, modifiers, annotations, name, members);
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
    public boolean remove(Node node) {
        if (node == null) {
            return false;
        }
        return super.remove(node);
    }

    @Override
    public AnnotationDeclaration clone() {
        return (AnnotationDeclaration)this.accept(new CloneVisitor(), null);
    }

    @Override
    public AnnotationDeclarationMetaModel getMetaModel() {
        return JavaParserMetaModel.annotationDeclarationMetaModel;
    }

    @Override
    public boolean replace(Node node, Node replacementNode) {
        if (node == null) {
            return false;
        }
        return super.replace(node, replacementNode);
    }

    @Override
    public boolean isAnnotationDeclaration() {
        return true;
    }

    @Override
    public AnnotationDeclaration asAnnotationDeclaration() {
        return this;
    }

    @Override
    public void ifAnnotationDeclaration(Consumer<AnnotationDeclaration> action) {
        action.accept(this);
    }

    @Override
    public ResolvedAnnotationDeclaration resolve() {
        return this.getSymbolResolver().resolveDeclaration(this, ResolvedAnnotationDeclaration.class);
    }

    @Override
    public Optional<AnnotationDeclaration> toAnnotationDeclaration() {
        return Optional.of(this);
    }

    @Override
    public FieldDeclaration addField(Type type, String name, Modifier.Keyword ... modifiers) {
        throw new IllegalStateException("Cannot add a field to an annotation declaration.");
    }
}

