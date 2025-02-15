/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.ast;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.TokenRange;
import com.github.javaparser.ast.AllFieldsConstructor;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.expr.Name;
import com.github.javaparser.ast.nodeTypes.NodeWithName;
import com.github.javaparser.ast.observer.ObservableProperty;
import com.github.javaparser.ast.visitor.CloneVisitor;
import com.github.javaparser.ast.visitor.GenericVisitor;
import com.github.javaparser.ast.visitor.VoidVisitor;
import com.github.javaparser.metamodel.ImportDeclarationMetaModel;
import com.github.javaparser.metamodel.JavaParserMetaModel;
import com.github.javaparser.utils.Utils;

public class ImportDeclaration
extends Node
implements NodeWithName<ImportDeclaration> {
    private Name name;
    private boolean isStatic;
    private boolean isAsterisk;

    private ImportDeclaration() {
        this(null, new Name(), false, false);
    }

    public ImportDeclaration(String name, boolean isStatic, boolean isAsterisk) {
        this(null, StaticJavaParser.parseName(name), isStatic, isAsterisk);
    }

    @AllFieldsConstructor
    public ImportDeclaration(Name name, boolean isStatic, boolean isAsterisk) {
        this(null, name, isStatic, isAsterisk);
    }

    public ImportDeclaration(TokenRange tokenRange, Name name, boolean isStatic, boolean isAsterisk) {
        super(tokenRange);
        this.setName(name);
        this.setStatic(isStatic);
        this.setAsterisk(isAsterisk);
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
    public Name getName() {
        return this.name;
    }

    public boolean isAsterisk() {
        return this.isAsterisk;
    }

    public boolean isStatic() {
        return this.isStatic;
    }

    public ImportDeclaration setAsterisk(boolean isAsterisk) {
        if (isAsterisk == this.isAsterisk) {
            return this;
        }
        this.notifyPropertyChange(ObservableProperty.ASTERISK, this.isAsterisk, isAsterisk);
        this.isAsterisk = isAsterisk;
        return this;
    }

    @Override
    public ImportDeclaration setName(Name name) {
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

    public ImportDeclaration setStatic(boolean isStatic) {
        if (isStatic == this.isStatic) {
            return this;
        }
        this.notifyPropertyChange(ObservableProperty.STATIC, this.isStatic, isStatic);
        this.isStatic = isStatic;
        return this;
    }

    @Override
    public boolean remove(Node node) {
        if (node == null) {
            return false;
        }
        return super.remove(node);
    }

    @Override
    public ImportDeclaration clone() {
        return (ImportDeclaration)this.accept(new CloneVisitor(), null);
    }

    @Override
    public ImportDeclarationMetaModel getMetaModel() {
        return JavaParserMetaModel.importDeclarationMetaModel;
    }

    @Override
    public boolean replace(Node node, Node replacementNode) {
        if (node == null) {
            return false;
        }
        if (node == this.name) {
            this.setName((Name)replacementNode);
            return true;
        }
        return super.replace(node, replacementNode);
    }
}

