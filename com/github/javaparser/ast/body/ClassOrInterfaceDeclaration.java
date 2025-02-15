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
import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.ast.expr.AnnotationExpr;
import com.github.javaparser.ast.expr.SimpleName;
import com.github.javaparser.ast.nodeTypes.NodeWithExtends;
import com.github.javaparser.ast.nodeTypes.NodeWithImplements;
import com.github.javaparser.ast.nodeTypes.NodeWithTypeParameters;
import com.github.javaparser.ast.nodeTypes.modifiers.NodeWithAbstractModifier;
import com.github.javaparser.ast.nodeTypes.modifiers.NodeWithFinalModifier;
import com.github.javaparser.ast.observer.ObservableProperty;
import com.github.javaparser.ast.stmt.LocalClassDeclarationStmt;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.ast.type.TypeParameter;
import com.github.javaparser.ast.visitor.CloneVisitor;
import com.github.javaparser.ast.visitor.GenericVisitor;
import com.github.javaparser.ast.visitor.VoidVisitor;
import com.github.javaparser.metamodel.ClassOrInterfaceDeclarationMetaModel;
import com.github.javaparser.metamodel.JavaParserMetaModel;
import com.github.javaparser.resolution.Resolvable;
import com.github.javaparser.resolution.declarations.ResolvedReferenceTypeDeclaration;
import com.github.javaparser.utils.Utils;
import java.util.Optional;
import java.util.function.Consumer;

public class ClassOrInterfaceDeclaration
extends TypeDeclaration<ClassOrInterfaceDeclaration>
implements NodeWithImplements<ClassOrInterfaceDeclaration>,
NodeWithExtends<ClassOrInterfaceDeclaration>,
NodeWithTypeParameters<ClassOrInterfaceDeclaration>,
NodeWithAbstractModifier<ClassOrInterfaceDeclaration>,
NodeWithFinalModifier<ClassOrInterfaceDeclaration>,
Resolvable<ResolvedReferenceTypeDeclaration> {
    private boolean isInterface;
    private NodeList<TypeParameter> typeParameters;
    private NodeList<ClassOrInterfaceType> extendedTypes;
    private NodeList<ClassOrInterfaceType> implementedTypes;

    public ClassOrInterfaceDeclaration() {
        this(null, new NodeList<Modifier>(), new NodeList<AnnotationExpr>(), false, new SimpleName(), new NodeList<TypeParameter>(), new NodeList<ClassOrInterfaceType>(), new NodeList<ClassOrInterfaceType>(), new NodeList());
    }

    public ClassOrInterfaceDeclaration(NodeList<Modifier> modifiers, boolean isInterface, String name) {
        this(null, modifiers, new NodeList<AnnotationExpr>(), isInterface, new SimpleName(name), new NodeList<TypeParameter>(), new NodeList<ClassOrInterfaceType>(), new NodeList<ClassOrInterfaceType>(), new NodeList());
    }

    @AllFieldsConstructor
    public ClassOrInterfaceDeclaration(NodeList<Modifier> modifiers, NodeList<AnnotationExpr> annotations, boolean isInterface, SimpleName name, NodeList<TypeParameter> typeParameters, NodeList<ClassOrInterfaceType> extendedTypes, NodeList<ClassOrInterfaceType> implementedTypes, NodeList<BodyDeclaration<?>> members) {
        this(null, modifiers, annotations, isInterface, name, typeParameters, extendedTypes, implementedTypes, members);
    }

    public ClassOrInterfaceDeclaration(TokenRange tokenRange, NodeList<Modifier> modifiers, NodeList<AnnotationExpr> annotations, boolean isInterface, SimpleName name, NodeList<TypeParameter> typeParameters, NodeList<ClassOrInterfaceType> extendedTypes, NodeList<ClassOrInterfaceType> implementedTypes, NodeList<BodyDeclaration<?>> members) {
        super(tokenRange, modifiers, annotations, name, members);
        this.setInterface(isInterface);
        this.setTypeParameters((NodeList)typeParameters);
        this.setExtendedTypes((NodeList)extendedTypes);
        this.setImplementedTypes((NodeList)implementedTypes);
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
    public NodeList<ClassOrInterfaceType> getExtendedTypes() {
        return this.extendedTypes;
    }

    @Override
    public NodeList<ClassOrInterfaceType> getImplementedTypes() {
        return this.implementedTypes;
    }

    @Override
    public NodeList<TypeParameter> getTypeParameters() {
        return this.typeParameters;
    }

    public boolean isInterface() {
        return this.isInterface;
    }

    @Override
    public ClassOrInterfaceDeclaration setExtendedTypes(NodeList<ClassOrInterfaceType> extendedTypes) {
        Utils.assertNotNull(extendedTypes);
        if (extendedTypes == this.extendedTypes) {
            return this;
        }
        this.notifyPropertyChange(ObservableProperty.EXTENDED_TYPES, this.extendedTypes, extendedTypes);
        if (this.extendedTypes != null) {
            this.extendedTypes.setParentNode(null);
        }
        this.extendedTypes = extendedTypes;
        this.setAsParentNodeOf(extendedTypes);
        return this;
    }

    @Override
    public ClassOrInterfaceDeclaration setImplementedTypes(NodeList<ClassOrInterfaceType> implementedTypes) {
        Utils.assertNotNull(implementedTypes);
        if (implementedTypes == this.implementedTypes) {
            return this;
        }
        this.notifyPropertyChange(ObservableProperty.IMPLEMENTED_TYPES, this.implementedTypes, implementedTypes);
        if (this.implementedTypes != null) {
            this.implementedTypes.setParentNode(null);
        }
        this.implementedTypes = implementedTypes;
        this.setAsParentNodeOf(implementedTypes);
        return this;
    }

    public ClassOrInterfaceDeclaration setInterface(boolean isInterface) {
        if (isInterface == this.isInterface) {
            return this;
        }
        this.notifyPropertyChange(ObservableProperty.INTERFACE, this.isInterface, isInterface);
        this.isInterface = isInterface;
        return this;
    }

    @Override
    public ClassOrInterfaceDeclaration setTypeParameters(NodeList<TypeParameter> typeParameters) {
        Utils.assertNotNull(typeParameters);
        if (typeParameters == this.typeParameters) {
            return this;
        }
        this.notifyPropertyChange(ObservableProperty.TYPE_PARAMETERS, this.typeParameters, typeParameters);
        if (this.typeParameters != null) {
            this.typeParameters.setParentNode(null);
        }
        this.typeParameters = typeParameters;
        this.setAsParentNodeOf(typeParameters);
        return this;
    }

    @Override
    public boolean remove(Node node) {
        int i;
        if (node == null) {
            return false;
        }
        for (i = 0; i < this.extendedTypes.size(); ++i) {
            if (this.extendedTypes.get(i) != node) continue;
            this.extendedTypes.remove(i);
            return true;
        }
        for (i = 0; i < this.implementedTypes.size(); ++i) {
            if (this.implementedTypes.get(i) != node) continue;
            this.implementedTypes.remove(i);
            return true;
        }
        for (i = 0; i < this.typeParameters.size(); ++i) {
            if (this.typeParameters.get(i) != node) continue;
            this.typeParameters.remove(i);
            return true;
        }
        return super.remove(node);
    }

    public boolean isLocalClassDeclaration() {
        return this.getParentNode().map(p -> p instanceof LocalClassDeclarationStmt).orElse(false);
    }

    @Override
    public Optional<String> getFullyQualifiedName() {
        if (this.isLocalClassDeclaration()) {
            return Optional.empty();
        }
        return super.getFullyQualifiedName();
    }

    public boolean isInnerClass() {
        return this.isNestedType() && !this.isInterface && !this.isStatic();
    }

    @Override
    public ClassOrInterfaceDeclaration clone() {
        return (ClassOrInterfaceDeclaration)this.accept(new CloneVisitor(), null);
    }

    @Override
    public ClassOrInterfaceDeclarationMetaModel getMetaModel() {
        return JavaParserMetaModel.classOrInterfaceDeclarationMetaModel;
    }

    @Override
    public boolean replace(Node node, Node replacementNode) {
        int i;
        if (node == null) {
            return false;
        }
        for (i = 0; i < this.extendedTypes.size(); ++i) {
            if (this.extendedTypes.get(i) != node) continue;
            this.extendedTypes.set(i, (ClassOrInterfaceType)replacementNode);
            return true;
        }
        for (i = 0; i < this.implementedTypes.size(); ++i) {
            if (this.implementedTypes.get(i) != node) continue;
            this.implementedTypes.set(i, (ClassOrInterfaceType)replacementNode);
            return true;
        }
        for (i = 0; i < this.typeParameters.size(); ++i) {
            if (this.typeParameters.get(i) != node) continue;
            this.typeParameters.set(i, (TypeParameter)replacementNode);
            return true;
        }
        return super.replace(node, replacementNode);
    }

    @Override
    public boolean isClassOrInterfaceDeclaration() {
        return true;
    }

    @Override
    public ClassOrInterfaceDeclaration asClassOrInterfaceDeclaration() {
        return this;
    }

    @Override
    public void ifClassOrInterfaceDeclaration(Consumer<ClassOrInterfaceDeclaration> action) {
        action.accept(this);
    }

    @Override
    public ResolvedReferenceTypeDeclaration resolve() {
        return this.getSymbolResolver().resolveDeclaration(this, ResolvedReferenceTypeDeclaration.class);
    }

    @Override
    public Optional<ClassOrInterfaceDeclaration> toClassOrInterfaceDeclaration() {
        return Optional.of(this);
    }
}

