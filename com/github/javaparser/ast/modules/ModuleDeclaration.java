/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.ast.modules;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.TokenRange;
import com.github.javaparser.ast.AllFieldsConstructor;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.expr.AnnotationExpr;
import com.github.javaparser.ast.expr.Name;
import com.github.javaparser.ast.modules.ModuleDirective;
import com.github.javaparser.ast.nodeTypes.NodeWithAnnotations;
import com.github.javaparser.ast.nodeTypes.NodeWithName;
import com.github.javaparser.ast.observer.ObservableProperty;
import com.github.javaparser.ast.visitor.CloneVisitor;
import com.github.javaparser.ast.visitor.GenericVisitor;
import com.github.javaparser.ast.visitor.VoidVisitor;
import com.github.javaparser.metamodel.JavaParserMetaModel;
import com.github.javaparser.metamodel.ModuleDeclarationMetaModel;
import com.github.javaparser.utils.Utils;

public class ModuleDeclaration
extends Node
implements NodeWithName<ModuleDeclaration>,
NodeWithAnnotations<ModuleDeclaration> {
    private Name name;
    private NodeList<AnnotationExpr> annotations;
    private boolean isOpen;
    private NodeList<ModuleDirective> directives;

    public ModuleDeclaration() {
        this(null, new NodeList<AnnotationExpr>(), new Name(), false, new NodeList<ModuleDirective>());
    }

    public ModuleDeclaration(Name name, boolean isOpen) {
        this(null, new NodeList<AnnotationExpr>(), name, isOpen, new NodeList<ModuleDirective>());
    }

    @AllFieldsConstructor
    public ModuleDeclaration(NodeList<AnnotationExpr> annotations, Name name, boolean isOpen, NodeList<ModuleDirective> directives) {
        this(null, annotations, name, isOpen, directives);
    }

    public ModuleDeclaration(TokenRange tokenRange, NodeList<AnnotationExpr> annotations, Name name, boolean isOpen, NodeList<ModuleDirective> directives) {
        super(tokenRange);
        this.setAnnotations((NodeList)annotations);
        this.setName(name);
        this.setOpen(isOpen);
        this.setDirectives(directives);
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

    @Override
    public ModuleDeclaration setName(Name name) {
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

    @Override
    public NodeList<AnnotationExpr> getAnnotations() {
        return this.annotations;
    }

    @Override
    public ModuleDeclaration setAnnotations(NodeList<AnnotationExpr> annotations) {
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

    @Override
    public boolean remove(Node node) {
        int i;
        if (node == null) {
            return false;
        }
        for (i = 0; i < this.annotations.size(); ++i) {
            if (this.annotations.get(i) != node) continue;
            this.annotations.remove(i);
            return true;
        }
        for (i = 0; i < this.directives.size(); ++i) {
            if (this.directives.get(i) != node) continue;
            this.directives.remove(i);
            return true;
        }
        return super.remove(node);
    }

    public boolean isOpen() {
        return this.isOpen;
    }

    public ModuleDeclaration setOpen(boolean isOpen) {
        if (isOpen == this.isOpen) {
            return this;
        }
        this.notifyPropertyChange(ObservableProperty.OPEN, this.isOpen, isOpen);
        this.isOpen = isOpen;
        return this;
    }

    public NodeList<ModuleDirective> getDirectives() {
        return this.directives;
    }

    public ModuleDeclaration setDirectives(NodeList<ModuleDirective> directives) {
        Utils.assertNotNull(directives);
        if (directives == this.directives) {
            return this;
        }
        this.notifyPropertyChange(ObservableProperty.DIRECTIVES, this.directives, directives);
        if (this.directives != null) {
            this.directives.setParentNode(null);
        }
        this.directives = directives;
        this.setAsParentNodeOf(directives);
        return this;
    }

    @Override
    public ModuleDeclaration clone() {
        return (ModuleDeclaration)this.accept(new CloneVisitor(), null);
    }

    @Override
    public ModuleDeclarationMetaModel getMetaModel() {
        return JavaParserMetaModel.moduleDeclarationMetaModel;
    }

    @Override
    public boolean replace(Node node, Node replacementNode) {
        int i;
        if (node == null) {
            return false;
        }
        for (i = 0; i < this.annotations.size(); ++i) {
            if (this.annotations.get(i) != node) continue;
            this.annotations.set(i, (AnnotationExpr)replacementNode);
            return true;
        }
        for (i = 0; i < this.directives.size(); ++i) {
            if (this.directives.get(i) != node) continue;
            this.directives.set(i, (ModuleDirective)replacementNode);
            return true;
        }
        if (node == this.name) {
            this.setName((Name)replacementNode);
            return true;
        }
        return super.replace(node, replacementNode);
    }

    public ModuleDeclaration addDirective(String directive) {
        return this.addDirective(StaticJavaParser.parseModuleDirective(directive));
    }

    public ModuleDeclaration addDirective(ModuleDirective directive) {
        this.getDirectives().add(directive);
        return this;
    }
}

