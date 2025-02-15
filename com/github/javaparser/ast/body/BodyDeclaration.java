/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.ast.body;

import com.github.javaparser.TokenRange;
import com.github.javaparser.ast.AllFieldsConstructor;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.AnnotationDeclaration;
import com.github.javaparser.ast.body.AnnotationMemberDeclaration;
import com.github.javaparser.ast.body.CallableDeclaration;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.body.EnumConstantDeclaration;
import com.github.javaparser.ast.body.EnumDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.InitializerDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.ast.expr.AnnotationExpr;
import com.github.javaparser.ast.nodeTypes.NodeWithAnnotations;
import com.github.javaparser.ast.observer.ObservableProperty;
import com.github.javaparser.ast.visitor.CloneVisitor;
import com.github.javaparser.metamodel.BodyDeclarationMetaModel;
import com.github.javaparser.metamodel.JavaParserMetaModel;
import com.github.javaparser.utils.CodeGenerationUtils;
import com.github.javaparser.utils.Utils;
import java.util.Optional;
import java.util.function.Consumer;

public abstract class BodyDeclaration<T extends BodyDeclaration<?>>
extends Node
implements NodeWithAnnotations<T> {
    private NodeList<AnnotationExpr> annotations;

    public BodyDeclaration() {
        this(null, new NodeList<AnnotationExpr>());
    }

    @AllFieldsConstructor
    public BodyDeclaration(NodeList<AnnotationExpr> annotations) {
        this(null, annotations);
    }

    public BodyDeclaration(TokenRange tokenRange, NodeList<AnnotationExpr> annotations) {
        super(tokenRange);
        this.setAnnotations((NodeList)annotations);
        this.customInitialization();
    }

    protected BodyDeclaration(TokenRange range) {
        this(range, new NodeList<AnnotationExpr>());
    }

    @Override
    public NodeList<AnnotationExpr> getAnnotations() {
        return this.annotations;
    }

    @Override
    public T setAnnotations(NodeList<AnnotationExpr> annotations) {
        Utils.assertNotNull(annotations);
        if (annotations == this.annotations) {
            return (T)this;
        }
        this.notifyPropertyChange(ObservableProperty.ANNOTATIONS, this.annotations, annotations);
        if (this.annotations != null) {
            this.annotations.setParentNode(null);
        }
        this.annotations = annotations;
        this.setAsParentNodeOf(annotations);
        return (T)this;
    }

    @Override
    public boolean remove(Node node) {
        if (node == null) {
            return false;
        }
        for (int i = 0; i < this.annotations.size(); ++i) {
            if (this.annotations.get(i) != node) continue;
            this.annotations.remove(i);
            return true;
        }
        return super.remove(node);
    }

    @Override
    public BodyDeclaration<?> clone() {
        return (BodyDeclaration)this.accept(new CloneVisitor(), null);
    }

    @Override
    public BodyDeclarationMetaModel getMetaModel() {
        return JavaParserMetaModel.bodyDeclarationMetaModel;
    }

    @Override
    public boolean replace(Node node, Node replacementNode) {
        if (node == null) {
            return false;
        }
        for (int i = 0; i < this.annotations.size(); ++i) {
            if (this.annotations.get(i) != node) continue;
            this.annotations.set(i, (AnnotationExpr)replacementNode);
            return true;
        }
        return super.replace(node, replacementNode);
    }

    public boolean isAnnotationDeclaration() {
        return false;
    }

    public AnnotationDeclaration asAnnotationDeclaration() {
        throw new IllegalStateException(CodeGenerationUtils.f("%s is not AnnotationDeclaration, it is %s", this, this.getClass().getSimpleName()));
    }

    public boolean isAnnotationMemberDeclaration() {
        return false;
    }

    public AnnotationMemberDeclaration asAnnotationMemberDeclaration() {
        throw new IllegalStateException(CodeGenerationUtils.f("%s is not AnnotationMemberDeclaration, it is %s", this, this.getClass().getSimpleName()));
    }

    public boolean isCallableDeclaration() {
        return false;
    }

    public CallableDeclaration asCallableDeclaration() {
        throw new IllegalStateException(CodeGenerationUtils.f("%s is not CallableDeclaration, it is %s", this, this.getClass().getSimpleName()));
    }

    public boolean isClassOrInterfaceDeclaration() {
        return false;
    }

    public ClassOrInterfaceDeclaration asClassOrInterfaceDeclaration() {
        throw new IllegalStateException(CodeGenerationUtils.f("%s is not ClassOrInterfaceDeclaration, it is %s", this, this.getClass().getSimpleName()));
    }

    public boolean isConstructorDeclaration() {
        return false;
    }

    public ConstructorDeclaration asConstructorDeclaration() {
        throw new IllegalStateException(CodeGenerationUtils.f("%s is not ConstructorDeclaration, it is %s", this, this.getClass().getSimpleName()));
    }

    public boolean isEnumConstantDeclaration() {
        return false;
    }

    public EnumConstantDeclaration asEnumConstantDeclaration() {
        throw new IllegalStateException(CodeGenerationUtils.f("%s is not EnumConstantDeclaration, it is %s", this, this.getClass().getSimpleName()));
    }

    public boolean isEnumDeclaration() {
        return false;
    }

    public EnumDeclaration asEnumDeclaration() {
        throw new IllegalStateException(CodeGenerationUtils.f("%s is not EnumDeclaration, it is %s", this, this.getClass().getSimpleName()));
    }

    public boolean isFieldDeclaration() {
        return false;
    }

    public FieldDeclaration asFieldDeclaration() {
        throw new IllegalStateException(CodeGenerationUtils.f("%s is not FieldDeclaration, it is %s", this, this.getClass().getSimpleName()));
    }

    public boolean isInitializerDeclaration() {
        return false;
    }

    public InitializerDeclaration asInitializerDeclaration() {
        throw new IllegalStateException(CodeGenerationUtils.f("%s is not InitializerDeclaration, it is %s", this, this.getClass().getSimpleName()));
    }

    public boolean isMethodDeclaration() {
        return false;
    }

    public MethodDeclaration asMethodDeclaration() {
        throw new IllegalStateException(CodeGenerationUtils.f("%s is not MethodDeclaration, it is %s", this, this.getClass().getSimpleName()));
    }

    public boolean isTypeDeclaration() {
        return false;
    }

    public TypeDeclaration asTypeDeclaration() {
        throw new IllegalStateException(CodeGenerationUtils.f("%s is not TypeDeclaration, it is %s", this, this.getClass().getSimpleName()));
    }

    public void ifAnnotationDeclaration(Consumer<AnnotationDeclaration> action) {
    }

    public void ifAnnotationMemberDeclaration(Consumer<AnnotationMemberDeclaration> action) {
    }

    public void ifCallableDeclaration(Consumer<CallableDeclaration> action) {
    }

    public void ifClassOrInterfaceDeclaration(Consumer<ClassOrInterfaceDeclaration> action) {
    }

    public void ifConstructorDeclaration(Consumer<ConstructorDeclaration> action) {
    }

    public void ifEnumConstantDeclaration(Consumer<EnumConstantDeclaration> action) {
    }

    public void ifEnumDeclaration(Consumer<EnumDeclaration> action) {
    }

    public void ifFieldDeclaration(Consumer<FieldDeclaration> action) {
    }

    public void ifInitializerDeclaration(Consumer<InitializerDeclaration> action) {
    }

    public void ifMethodDeclaration(Consumer<MethodDeclaration> action) {
    }

    public void ifTypeDeclaration(Consumer<TypeDeclaration> action) {
    }

    public Optional<AnnotationDeclaration> toAnnotationDeclaration() {
        return Optional.empty();
    }

    public Optional<AnnotationMemberDeclaration> toAnnotationMemberDeclaration() {
        return Optional.empty();
    }

    public Optional<CallableDeclaration> toCallableDeclaration() {
        return Optional.empty();
    }

    public Optional<ClassOrInterfaceDeclaration> toClassOrInterfaceDeclaration() {
        return Optional.empty();
    }

    public Optional<ConstructorDeclaration> toConstructorDeclaration() {
        return Optional.empty();
    }

    public Optional<EnumConstantDeclaration> toEnumConstantDeclaration() {
        return Optional.empty();
    }

    public Optional<EnumDeclaration> toEnumDeclaration() {
        return Optional.empty();
    }

    public Optional<FieldDeclaration> toFieldDeclaration() {
        return Optional.empty();
    }

    public Optional<InitializerDeclaration> toInitializerDeclaration() {
        return Optional.empty();
    }

    public Optional<MethodDeclaration> toMethodDeclaration() {
        return Optional.empty();
    }

    public Optional<TypeDeclaration> toTypeDeclaration() {
        return Optional.empty();
    }
}

