/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.ast.type;

import com.github.javaparser.TokenRange;
import com.github.javaparser.ast.AllFieldsConstructor;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.expr.AnnotationExpr;
import com.github.javaparser.ast.observer.ObservableProperty;
import com.github.javaparser.ast.type.ArrayType;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.ast.type.IntersectionType;
import com.github.javaparser.ast.type.PrimitiveType;
import com.github.javaparser.ast.type.ReferenceType;
import com.github.javaparser.ast.type.TypeParameter;
import com.github.javaparser.ast.type.UnionType;
import com.github.javaparser.ast.type.UnknownType;
import com.github.javaparser.ast.type.VarType;
import com.github.javaparser.ast.type.VoidType;
import com.github.javaparser.ast.type.WildcardType;
import com.github.javaparser.ast.visitor.CloneVisitor;
import com.github.javaparser.metamodel.JavaParserMetaModel;
import com.github.javaparser.metamodel.TypeMetaModel;
import com.github.javaparser.resolution.Resolvable;
import com.github.javaparser.resolution.types.ResolvedType;
import com.github.javaparser.utils.CodeGenerationUtils;
import com.github.javaparser.utils.Utils;
import java.util.Optional;
import java.util.function.Consumer;

public abstract class Type
extends Node
implements Resolvable<ResolvedType> {
    private NodeList<AnnotationExpr> annotations;

    protected Type(TokenRange range) {
        this(range, new NodeList<AnnotationExpr>());
    }

    @AllFieldsConstructor
    public Type(NodeList<AnnotationExpr> annotations) {
        this(null, annotations);
    }

    public Type(TokenRange tokenRange, NodeList<AnnotationExpr> annotations) {
        super(tokenRange);
        this.setAnnotations(annotations);
        this.customInitialization();
    }

    public NodeList<AnnotationExpr> getAnnotations() {
        return this.annotations;
    }

    public AnnotationExpr getAnnotation(int i) {
        return (AnnotationExpr)this.getAnnotations().get(i);
    }

    public Type setAnnotations(NodeList<AnnotationExpr> annotations) {
        Utils.assertNotNull(annotations);
        if (annotations == this.annotations) {
            return this;
        }
        this.notifyPropertyChange(ObservableProperty.ANNOTATIONS, this.annotations, annotations);
        if (this.annotations != null) {
            this.annotations.setParentNode(null);
        }
        this.annotations = annotations;
        this.setAsParentNodeOf(annotations);
        return this;
    }

    public Type getElementType() {
        if (this instanceof ArrayType) {
            return ((ArrayType)this).getComponentType().getElementType();
        }
        return this;
    }

    public int getArrayLevel() {
        if (this instanceof ArrayType) {
            return 1 + ((ArrayType)this).getComponentType().getArrayLevel();
        }
        return 0;
    }

    public String toDescriptor() {
        return "";
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

    public abstract String asString();

    @Override
    public Type clone() {
        return (Type)this.accept(new CloneVisitor(), null);
    }

    @Override
    public TypeMetaModel getMetaModel() {
        return JavaParserMetaModel.typeMetaModel;
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

    public boolean isArrayType() {
        return false;
    }

    public ArrayType asArrayType() {
        throw new IllegalStateException(CodeGenerationUtils.f("%s is not ArrayType, it is %s", this, this.getClass().getSimpleName()));
    }

    public boolean isClassOrInterfaceType() {
        return false;
    }

    public ClassOrInterfaceType asClassOrInterfaceType() {
        throw new IllegalStateException(CodeGenerationUtils.f("%s is not ClassOrInterfaceType, it is %s", this, this.getClass().getSimpleName()));
    }

    public boolean isIntersectionType() {
        return false;
    }

    public IntersectionType asIntersectionType() {
        throw new IllegalStateException(CodeGenerationUtils.f("%s is not IntersectionType, it is %s", this, this.getClass().getSimpleName()));
    }

    public boolean isPrimitiveType() {
        return false;
    }

    public PrimitiveType asPrimitiveType() {
        throw new IllegalStateException(CodeGenerationUtils.f("%s is not PrimitiveType, it is %s", this, this.getClass().getSimpleName()));
    }

    public boolean isReferenceType() {
        return false;
    }

    public ReferenceType asReferenceType() {
        throw new IllegalStateException(CodeGenerationUtils.f("%s is not ReferenceType, it is %s", this, this.getClass().getSimpleName()));
    }

    public boolean isTypeParameter() {
        return false;
    }

    public TypeParameter asTypeParameter() {
        throw new IllegalStateException(CodeGenerationUtils.f("%s is not TypeParameter, it is %s", this, this.getClass().getSimpleName()));
    }

    public boolean isUnionType() {
        return false;
    }

    public UnionType asUnionType() {
        throw new IllegalStateException(CodeGenerationUtils.f("%s is not UnionType, it is %s", this, this.getClass().getSimpleName()));
    }

    public boolean isUnknownType() {
        return false;
    }

    public UnknownType asUnknownType() {
        throw new IllegalStateException(CodeGenerationUtils.f("%s is not UnknownType, it is %s", this, this.getClass().getSimpleName()));
    }

    public boolean isVoidType() {
        return false;
    }

    public VoidType asVoidType() {
        throw new IllegalStateException(CodeGenerationUtils.f("%s is not VoidType, it is %s", this, this.getClass().getSimpleName()));
    }

    public boolean isWildcardType() {
        return false;
    }

    public WildcardType asWildcardType() {
        throw new IllegalStateException(CodeGenerationUtils.f("%s is not WildcardType, it is %s", this, this.getClass().getSimpleName()));
    }

    public void ifArrayType(Consumer<ArrayType> action) {
    }

    public void ifClassOrInterfaceType(Consumer<ClassOrInterfaceType> action) {
    }

    public void ifIntersectionType(Consumer<IntersectionType> action) {
    }

    public void ifPrimitiveType(Consumer<PrimitiveType> action) {
    }

    public void ifReferenceType(Consumer<ReferenceType> action) {
    }

    public void ifTypeParameter(Consumer<TypeParameter> action) {
    }

    public void ifUnionType(Consumer<UnionType> action) {
    }

    public void ifUnknownType(Consumer<UnknownType> action) {
    }

    public void ifVoidType(Consumer<VoidType> action) {
    }

    public void ifWildcardType(Consumer<WildcardType> action) {
    }

    @Override
    public abstract ResolvedType resolve();

    public Optional<ArrayType> toArrayType() {
        return Optional.empty();
    }

    public Optional<ClassOrInterfaceType> toClassOrInterfaceType() {
        return Optional.empty();
    }

    public Optional<IntersectionType> toIntersectionType() {
        return Optional.empty();
    }

    public Optional<PrimitiveType> toPrimitiveType() {
        return Optional.empty();
    }

    public Optional<ReferenceType> toReferenceType() {
        return Optional.empty();
    }

    public Optional<TypeParameter> toTypeParameter() {
        return Optional.empty();
    }

    public Optional<UnionType> toUnionType() {
        return Optional.empty();
    }

    public Optional<UnknownType> toUnknownType() {
        return Optional.empty();
    }

    public Optional<VoidType> toVoidType() {
        return Optional.empty();
    }

    public Optional<WildcardType> toWildcardType() {
        return Optional.empty();
    }

    public boolean isVarType() {
        return false;
    }

    public VarType asVarType() {
        throw new IllegalStateException(CodeGenerationUtils.f("%s is not VarType, it is %s", this, this.getClass().getSimpleName()));
    }

    public Optional<VarType> toVarType() {
        return Optional.empty();
    }

    public void ifVarType(Consumer<VarType> action) {
    }
}

