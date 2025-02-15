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
import com.github.javaparser.metamodel.JavaParserMetaModel;
import com.github.javaparser.metamodel.NonEmptyProperty;
import com.github.javaparser.metamodel.UnionTypeMetaModel;
import com.github.javaparser.resolution.types.ResolvedUnionType;
import com.github.javaparser.utils.Utils;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class UnionType
extends Type
implements NodeWithAnnotations<UnionType> {
    @NonEmptyProperty
    private NodeList<ReferenceType> elements;

    public UnionType() {
        this((TokenRange)null, new NodeList<ReferenceType>());
    }

    public UnionType(TokenRange tokenRange, NodeList<ReferenceType> elements) {
        super(tokenRange);
        this.setElements(elements);
        this.customInitialization();
    }

    @AllFieldsConstructor
    public UnionType(NodeList<ReferenceType> elements) {
        this((TokenRange)null, elements);
    }

    public NodeList<ReferenceType> getElements() {
        return this.elements;
    }

    public UnionType setElements(NodeList<ReferenceType> elements) {
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
    public UnionType setAnnotations(NodeList<AnnotationExpr> annotations) {
        return (UnionType)super.setAnnotations(annotations);
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
        for (int i = 0; i < this.elements.size(); ++i) {
            if (this.elements.get(i) != node) continue;
            this.elements.remove(i);
            return true;
        }
        return super.remove(node);
    }

    @Override
    public String asString() {
        return this.elements.stream().map(Type::asString).collect(Collectors.joining("|"));
    }

    @Override
    public UnionType clone() {
        return (UnionType)this.accept(new CloneVisitor(), null);
    }

    @Override
    public UnionTypeMetaModel getMetaModel() {
        return JavaParserMetaModel.unionTypeMetaModel;
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
    public boolean isUnionType() {
        return true;
    }

    @Override
    public UnionType asUnionType() {
        return this;
    }

    @Override
    public void ifUnionType(Consumer<UnionType> action) {
        action.accept(this);
    }

    @Override
    public ResolvedUnionType resolve() {
        return this.getSymbolResolver().toResolvedType(this, ResolvedUnionType.class);
    }

    @Override
    public Optional<UnionType> toUnionType() {
        return Optional.of(this);
    }
}

