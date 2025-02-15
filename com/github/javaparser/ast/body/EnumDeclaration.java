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
import com.github.javaparser.ast.body.EnumConstantDeclaration;
import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.ast.expr.AnnotationExpr;
import com.github.javaparser.ast.expr.SimpleName;
import com.github.javaparser.ast.nodeTypes.NodeWithImplements;
import com.github.javaparser.ast.observer.ObservableProperty;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.ast.visitor.CloneVisitor;
import com.github.javaparser.ast.visitor.GenericVisitor;
import com.github.javaparser.ast.visitor.VoidVisitor;
import com.github.javaparser.metamodel.EnumDeclarationMetaModel;
import com.github.javaparser.metamodel.JavaParserMetaModel;
import com.github.javaparser.resolution.Resolvable;
import com.github.javaparser.resolution.declarations.ResolvedEnumDeclaration;
import com.github.javaparser.utils.Utils;
import java.util.Optional;
import java.util.function.Consumer;

public class EnumDeclaration
extends TypeDeclaration<EnumDeclaration>
implements NodeWithImplements<EnumDeclaration>,
Resolvable<ResolvedEnumDeclaration> {
    private NodeList<ClassOrInterfaceType> implementedTypes;
    private NodeList<EnumConstantDeclaration> entries;

    public EnumDeclaration() {
        this(null, new NodeList<Modifier>(), new NodeList<AnnotationExpr>(), new SimpleName(), new NodeList<ClassOrInterfaceType>(), new NodeList<EnumConstantDeclaration>(), new NodeList());
    }

    public EnumDeclaration(NodeList<Modifier> modifiers, String name) {
        this(null, modifiers, new NodeList<AnnotationExpr>(), new SimpleName(name), new NodeList<ClassOrInterfaceType>(), new NodeList<EnumConstantDeclaration>(), new NodeList());
    }

    @AllFieldsConstructor
    public EnumDeclaration(NodeList<Modifier> modifiers, NodeList<AnnotationExpr> annotations, SimpleName name, NodeList<ClassOrInterfaceType> implementedTypes, NodeList<EnumConstantDeclaration> entries, NodeList<BodyDeclaration<?>> members) {
        this(null, modifiers, annotations, name, implementedTypes, entries, members);
    }

    public EnumDeclaration(TokenRange tokenRange, NodeList<Modifier> modifiers, NodeList<AnnotationExpr> annotations, SimpleName name, NodeList<ClassOrInterfaceType> implementedTypes, NodeList<EnumConstantDeclaration> entries, NodeList<BodyDeclaration<?>> members) {
        super(tokenRange, modifiers, annotations, name, members);
        this.setImplementedTypes((NodeList)implementedTypes);
        this.setEntries(entries);
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

    public NodeList<EnumConstantDeclaration> getEntries() {
        return this.entries;
    }

    public EnumConstantDeclaration getEntry(int i) {
        return (EnumConstantDeclaration)this.getEntries().get(i);
    }

    public EnumDeclaration setEntry(int i, EnumConstantDeclaration element) {
        this.getEntries().set(i, element);
        return this;
    }

    public EnumDeclaration addEntry(EnumConstantDeclaration element) {
        this.getEntries().add(element);
        return this;
    }

    @Override
    public NodeList<ClassOrInterfaceType> getImplementedTypes() {
        return this.implementedTypes;
    }

    public EnumDeclaration setEntries(NodeList<EnumConstantDeclaration> entries) {
        Utils.assertNotNull(entries);
        if (entries == this.entries) {
            return this;
        }
        this.notifyPropertyChange(ObservableProperty.ENTRIES, this.entries, entries);
        if (this.entries != null) {
            this.entries.setParentNode(null);
        }
        this.entries = entries;
        this.setAsParentNodeOf(entries);
        return this;
    }

    @Override
    public EnumDeclaration setImplementedTypes(NodeList<ClassOrInterfaceType> implementedTypes) {
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

    public EnumConstantDeclaration addEnumConstant(String name) {
        Utils.assertNonEmpty(name);
        EnumConstantDeclaration enumConstant = new EnumConstantDeclaration(name);
        this.getEntries().add(enumConstant);
        return enumConstant;
    }

    @Override
    public boolean remove(Node node) {
        int i;
        if (node == null) {
            return false;
        }
        for (i = 0; i < this.entries.size(); ++i) {
            if (this.entries.get(i) != node) continue;
            this.entries.remove(i);
            return true;
        }
        for (i = 0; i < this.implementedTypes.size(); ++i) {
            if (this.implementedTypes.get(i) != node) continue;
            this.implementedTypes.remove(i);
            return true;
        }
        return super.remove(node);
    }

    @Override
    public EnumDeclaration clone() {
        return (EnumDeclaration)this.accept(new CloneVisitor(), null);
    }

    @Override
    public EnumDeclarationMetaModel getMetaModel() {
        return JavaParserMetaModel.enumDeclarationMetaModel;
    }

    @Override
    public boolean replace(Node node, Node replacementNode) {
        int i;
        if (node == null) {
            return false;
        }
        for (i = 0; i < this.entries.size(); ++i) {
            if (this.entries.get(i) != node) continue;
            this.entries.set(i, (EnumConstantDeclaration)replacementNode);
            return true;
        }
        for (i = 0; i < this.implementedTypes.size(); ++i) {
            if (this.implementedTypes.get(i) != node) continue;
            this.implementedTypes.set(i, (ClassOrInterfaceType)replacementNode);
            return true;
        }
        return super.replace(node, replacementNode);
    }

    @Override
    public boolean isEnumDeclaration() {
        return true;
    }

    @Override
    public EnumDeclaration asEnumDeclaration() {
        return this;
    }

    @Override
    public void ifEnumDeclaration(Consumer<EnumDeclaration> action) {
        action.accept(this);
    }

    @Override
    public ResolvedEnumDeclaration resolve() {
        return this.getSymbolResolver().resolveDeclaration(this, ResolvedEnumDeclaration.class);
    }

    @Override
    public Optional<EnumDeclaration> toEnumDeclaration() {
        return Optional.of(this);
    }
}

