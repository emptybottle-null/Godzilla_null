/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.ast.type;

import com.github.javaparser.TokenRange;
import com.github.javaparser.ast.AllFieldsConstructor;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.expr.AnnotationExpr;
import com.github.javaparser.ast.expr.SimpleName;
import com.github.javaparser.ast.nodeTypes.NodeWithAnnotations;
import com.github.javaparser.ast.nodeTypes.NodeWithSimpleName;
import com.github.javaparser.ast.observer.ObservableProperty;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.ast.type.ReferenceType;
import com.github.javaparser.ast.visitor.CloneVisitor;
import com.github.javaparser.ast.visitor.GenericVisitor;
import com.github.javaparser.ast.visitor.VoidVisitor;
import com.github.javaparser.metamodel.JavaParserMetaModel;
import com.github.javaparser.metamodel.TypeParameterMetaModel;
import com.github.javaparser.resolution.types.ResolvedTypeVariable;
import com.github.javaparser.utils.Utils;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class TypeParameter
extends ReferenceType
implements NodeWithSimpleName<TypeParameter>,
NodeWithAnnotations<TypeParameter> {
    private SimpleName name;
    private NodeList<ClassOrInterfaceType> typeBound;

    public TypeParameter() {
        this(null, new SimpleName(), new NodeList<ClassOrInterfaceType>(), new NodeList<AnnotationExpr>());
    }

    public TypeParameter(String name) {
        this(null, new SimpleName(name), new NodeList<ClassOrInterfaceType>(), new NodeList<AnnotationExpr>());
    }

    public TypeParameter(String name, NodeList<ClassOrInterfaceType> typeBound) {
        this(null, new SimpleName(name), typeBound, new NodeList<AnnotationExpr>());
    }

    @AllFieldsConstructor
    public TypeParameter(SimpleName name, NodeList<ClassOrInterfaceType> typeBound, NodeList<AnnotationExpr> annotations) {
        this(null, name, typeBound, annotations);
    }

    public TypeParameter(TokenRange tokenRange, SimpleName name, NodeList<ClassOrInterfaceType> typeBound, NodeList<AnnotationExpr> annotations) {
        super(tokenRange, annotations);
        this.setName(name);
        this.setTypeBound(typeBound);
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
    public SimpleName getName() {
        return this.name;
    }

    public NodeList<ClassOrInterfaceType> getTypeBound() {
        return this.typeBound;
    }

    @Override
    public TypeParameter setName(SimpleName name) {
        Utils.assertNotNull(name);
        if (name == this.name) {
            return this;
        }
        this.notifyPropertyChange(ObservableProperty.NAME, this.name, name);
        if (this.name != null) {
            this.name.setParentNode(null);
        }
        this.name = name;
        this.setAsParentNodeOf(name);
        return this;
    }

    public TypeParameter setTypeBound(NodeList<ClassOrInterfaceType> typeBound) {
        Utils.assertNotNull(typeBound);
        if (typeBound == this.typeBound) {
            return this;
        }
        this.notifyPropertyChange(ObservableProperty.TYPE_BOUND, this.typeBound, typeBound);
        if (this.typeBound != null) {
            this.typeBound.setParentNode(null);
        }
        this.typeBound = typeBound;
        this.setAsParentNodeOf(typeBound);
        return this;
    }

    @Override
    public TypeParameter setAnnotations(NodeList<AnnotationExpr> annotations) {
        super.setAnnotations(annotations);
        return this;
    }

    @Override
    public boolean remove(Node node) {
        if (node == null) {
            return false;
        }
        for (int i = 0; i < this.typeBound.size(); ++i) {
            if (this.typeBound.get(i) != node) continue;
            this.typeBound.remove(i);
            return true;
        }
        return super.remove(node);
    }

    @Override
    public String asString() {
        StringBuilder str = new StringBuilder(this.getNameAsString());
        this.getTypeBound().ifNonEmpty(l -> str.append(l.stream().map(ClassOrInterfaceType::asString).collect(Collectors.joining("&", " extends ", ""))));
        return str.toString();
    }

    @Override
    public String toDescriptor() {
        return String.format("L%s;", this.resolve().qualifiedName());
    }

    @Override
    public TypeParameter clone() {
        return (TypeParameter)this.accept(new CloneVisitor(), null);
    }

    @Override
    public TypeParameterMetaModel getMetaModel() {
        return JavaParserMetaModel.typeParameterMetaModel;
    }

    @Override
    public boolean replace(Node node, Node replacementNode) {
        if (node == null) {
            return false;
        }
        if (node == this.name) {
            this.setName((SimpleName)replacementNode);
            return true;
        }
        for (int i = 0; i < this.typeBound.size(); ++i) {
            if (this.typeBound.get(i) != node) continue;
            this.typeBound.set(i, (ClassOrInterfaceType)replacementNode);
            return true;
        }
        return super.replace(node, replacementNode);
    }

    @Override
    public boolean isTypeParameter() {
        return true;
    }

    @Override
    public TypeParameter asTypeParameter() {
        return this;
    }

    @Override
    public void ifTypeParameter(Consumer<TypeParameter> action) {
        action.accept(this);
    }

    @Override
    public ResolvedTypeVariable resolve() {
        return this.getSymbolResolver().toResolvedType(this, ResolvedTypeVariable.class);
    }

    @Override
    public Optional<TypeParameter> toTypeParameter() {
        return Optional.of(this);
    }
}

