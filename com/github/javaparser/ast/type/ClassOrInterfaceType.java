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
import com.github.javaparser.ast.nodeTypes.NodeWithTypeArguments;
import com.github.javaparser.ast.observer.ObservableProperty;
import com.github.javaparser.ast.type.PrimitiveType;
import com.github.javaparser.ast.type.ReferenceType;
import com.github.javaparser.ast.type.Type;
import com.github.javaparser.ast.visitor.CloneVisitor;
import com.github.javaparser.ast.visitor.GenericVisitor;
import com.github.javaparser.ast.visitor.VoidVisitor;
import com.github.javaparser.metamodel.ClassOrInterfaceTypeMetaModel;
import com.github.javaparser.metamodel.JavaParserMetaModel;
import com.github.javaparser.metamodel.OptionalProperty;
import com.github.javaparser.resolution.types.ResolvedReferenceType;
import com.github.javaparser.utils.Utils;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class ClassOrInterfaceType
extends ReferenceType
implements NodeWithSimpleName<ClassOrInterfaceType>,
NodeWithAnnotations<ClassOrInterfaceType>,
NodeWithTypeArguments<ClassOrInterfaceType> {
    @OptionalProperty
    private ClassOrInterfaceType scope;
    private SimpleName name;
    @OptionalProperty
    private NodeList<Type> typeArguments;

    public ClassOrInterfaceType() {
        this(null, null, new SimpleName(), null, new NodeList<AnnotationExpr>());
    }

    public ClassOrInterfaceType(String name) {
        this(null, null, new SimpleName(name), null, new NodeList<AnnotationExpr>());
    }

    public ClassOrInterfaceType(ClassOrInterfaceType scope, String name) {
        this(null, scope, new SimpleName(name), null, new NodeList<AnnotationExpr>());
    }

    public ClassOrInterfaceType(ClassOrInterfaceType scope, SimpleName name, NodeList<Type> typeArguments) {
        this(null, scope, name, typeArguments, new NodeList<AnnotationExpr>());
    }

    @AllFieldsConstructor
    public ClassOrInterfaceType(ClassOrInterfaceType scope, SimpleName name, NodeList<Type> typeArguments, NodeList<AnnotationExpr> annotations) {
        this(null, scope, name, typeArguments, annotations);
    }

    public ClassOrInterfaceType(TokenRange tokenRange, ClassOrInterfaceType scope, SimpleName name, NodeList<Type> typeArguments, NodeList<AnnotationExpr> annotations) {
        super(tokenRange, annotations);
        this.setScope(scope);
        this.setName(name);
        this.setTypeArguments((NodeList)typeArguments);
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

    public String getNameWithScope() {
        StringBuilder str = new StringBuilder();
        this.getScope().ifPresent(s -> str.append(s.getNameWithScope()).append("."));
        str.append(this.name.asString());
        return str.toString();
    }

    public Optional<ClassOrInterfaceType> getScope() {
        return Optional.ofNullable(this.scope);
    }

    public boolean isBoxedType() {
        return PrimitiveType.unboxMap.containsKey(this.name.getIdentifier());
    }

    public PrimitiveType toUnboxedType() throws UnsupportedOperationException {
        if (!this.isBoxedType()) {
            throw new UnsupportedOperationException(this.name + " isn't a boxed type.");
        }
        return new PrimitiveType(PrimitiveType.unboxMap.get(this.name.getIdentifier()));
    }

    @Override
    public ClassOrInterfaceType setName(SimpleName name) {
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

    public ClassOrInterfaceType setScope(ClassOrInterfaceType scope) {
        if (scope == this.scope) {
            return this;
        }
        this.notifyPropertyChange(ObservableProperty.SCOPE, this.scope, scope);
        if (this.scope != null) {
            this.scope.setParentNode(null);
        }
        this.scope = scope;
        this.setAsParentNodeOf(scope);
        return this;
    }

    @Override
    public Optional<NodeList<Type>> getTypeArguments() {
        return Optional.ofNullable(this.typeArguments);
    }

    @Override
    public ClassOrInterfaceType setTypeArguments(NodeList<Type> typeArguments) {
        if (typeArguments == this.typeArguments) {
            return this;
        }
        this.notifyPropertyChange(ObservableProperty.TYPE_ARGUMENTS, this.typeArguments, typeArguments);
        if (this.typeArguments != null) {
            this.typeArguments.setParentNode(null);
        }
        this.typeArguments = typeArguments;
        this.setAsParentNodeOf(typeArguments);
        return this;
    }

    @Override
    public ClassOrInterfaceType setAnnotations(NodeList<AnnotationExpr> annotations) {
        return (ClassOrInterfaceType)super.setAnnotations(annotations);
    }

    @Override
    public boolean remove(Node node) {
        if (node == null) {
            return false;
        }
        if (this.scope != null && node == this.scope) {
            this.removeScope();
            return true;
        }
        if (this.typeArguments != null) {
            for (int i = 0; i < this.typeArguments.size(); ++i) {
                if (this.typeArguments.get(i) != node) continue;
                this.typeArguments.remove(i);
                return true;
            }
        }
        return super.remove(node);
    }

    @Override
    public String asString() {
        StringBuilder str = new StringBuilder();
        this.getScope().ifPresent(s -> str.append(s.asString()).append("."));
        str.append(this.name.asString());
        this.getTypeArguments().ifPresent(ta -> str.append(ta.stream().map(Type::asString).collect(Collectors.joining(",", "<", ">"))));
        return str.toString();
    }

    @Override
    public String toDescriptor() {
        return String.format("L%s;", this.resolve().getQualifiedName().replace(".", "/"));
    }

    public ClassOrInterfaceType removeScope() {
        return this.setScope(null);
    }

    @Override
    public ClassOrInterfaceType clone() {
        return (ClassOrInterfaceType)this.accept(new CloneVisitor(), null);
    }

    @Override
    public ClassOrInterfaceTypeMetaModel getMetaModel() {
        return JavaParserMetaModel.classOrInterfaceTypeMetaModel;
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
        if (this.scope != null && node == this.scope) {
            this.setScope((ClassOrInterfaceType)replacementNode);
            return true;
        }
        if (this.typeArguments != null) {
            for (int i = 0; i < this.typeArguments.size(); ++i) {
                if (this.typeArguments.get(i) != node) continue;
                this.typeArguments.set(i, (Type)replacementNode);
                return true;
            }
        }
        return super.replace(node, replacementNode);
    }

    @Override
    public boolean isClassOrInterfaceType() {
        return true;
    }

    @Override
    public ClassOrInterfaceType asClassOrInterfaceType() {
        return this;
    }

    @Override
    public void ifClassOrInterfaceType(Consumer<ClassOrInterfaceType> action) {
        action.accept(this);
    }

    @Override
    public ResolvedReferenceType resolve() {
        return this.getSymbolResolver().toResolvedType(this, ResolvedReferenceType.class);
    }

    @Override
    public Optional<ClassOrInterfaceType> toClassOrInterfaceType() {
        return Optional.of(this);
    }
}

