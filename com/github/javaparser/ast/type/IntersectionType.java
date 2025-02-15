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
import com.github.javaparser.ast.observer.ObservableProperty;
import com.github.javaparser.ast.type.ReferenceType;
import com.github.javaparser.ast.type.Type;
import com.github.javaparser.ast.visitor.CloneVisitor;
import com.github.javaparser.ast.visitor.GenericVisitor;
import com.github.javaparser.ast.visitor.VoidVisitor;
import com.github.javaparser.metamodel.IntersectionTypeMetaModel;
import com.github.javaparser.metamodel.JavaParserMetaModel;
import com.github.javaparser.metamodel.NonEmptyProperty;
import com.github.javaparser.resolution.types.ResolvedIntersectionType;
import com.github.javaparser.utils.Utils;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class IntersectionType
extends Type
implements NodeWithAnnotations<IntersectionType> {
    @NonEmptyProperty
    private NodeList<ReferenceType> elements;

    @AllFieldsConstructor
    public IntersectionType(NodeList<ReferenceType> elements) {
        this((TokenRange)null, elements);
    }

    public IntersectionType(TokenRange tokenRange, NodeList<ReferenceType> elements) {
        super(tokenRange);
        this.setElements(elements);
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

    public NodeList<ReferenceType> getElements() {
        return this.elements;
    }

    public IntersectionType setElements(NodeList<ReferenceType> elements) {
        Utils.assertNotNull(elements);
        if (elements == this.elements) {
            return this;
        }
        this.notifyPropertyChange(ObservableProperty.ELEMENTS, this.elements, elements);
        if (this.elements != null) {
            this.elements.setParentNode(null);
        }
        this.elements = elements;
        this.setAsParentNodeOf(elements);
        return this;
    }

    @Override
    public IntersectionType setAnnotations(NodeList<AnnotationExpr> annotations) {
        return (IntersectionType)super.setAnnotations(annotations);
    }

    @Override
    public boolean remove(Node node) {
        if (node == null) {
            return false;
        }
        for (int i = 0; i < this.elements.size(); ++i) {
            if (this.elements.get(i) != node) continue;
            this.elements.remove(i);
            return true;
        }
        return super.remove(node);
    }

    @Override
    public String asString() {
        return this.elements.stream().map(Type::asString).collect(Collectors.joining("&"));
    }

    @Override
    public IntersectionType clone() {
        return (IntersectionType)this.accept(new CloneVisitor(), null);
    }

    @Override
    public IntersectionTypeMetaModel getMetaModel() {
        return JavaParserMetaModel.intersectionTypeMetaModel;
    }

    @Override
    public boolean replace(Node node, Node replacementNode) {
        if (node == null) {
            return false;
        }
        for (int i = 0; i < this.elements.size(); ++i) {
            if (this.elements.get(i) != node) continue;
            this.elements.set(i, (ReferenceType)replacementNode);
            return true;
        }
        return super.replace(node, replacementNode);
    }

    @Override
    public boolean isIntersectionType() {
        return true;
    }

    @Override
    public IntersectionType asIntersectionType() {
        return this;
    }

    @Override
    public void ifIntersectionType(Consumer<IntersectionType> action) {
        action.accept(this);
    }

    @Override
    public ResolvedIntersectionType resolve() {
        return this.getSymbolResolver().toResolvedType(this, ResolvedIntersectionType.class);
    }

    @Override
    public Optional<IntersectionType> toIntersectionType() {
        return Optional.of(this);
    }
}

