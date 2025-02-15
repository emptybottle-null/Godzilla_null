/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.ast.modules;

import com.github.javaparser.TokenRange;
import com.github.javaparser.ast.AllFieldsConstructor;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.expr.Name;
import com.github.javaparser.ast.modules.ModuleDirective;
import com.github.javaparser.ast.nodeTypes.NodeWithName;
import com.github.javaparser.ast.observer.ObservableProperty;
import com.github.javaparser.ast.visitor.CloneVisitor;
import com.github.javaparser.ast.visitor.GenericVisitor;
import com.github.javaparser.ast.visitor.VoidVisitor;
import com.github.javaparser.metamodel.JavaParserMetaModel;
import com.github.javaparser.metamodel.ModuleProvidesDirectiveMetaModel;
import com.github.javaparser.utils.Utils;
import java.util.Optional;
import java.util.function.Consumer;

public class ModuleProvidesDirective
extends ModuleDirective
implements NodeWithName<ModuleProvidesDirective> {
    private Name name;
    private NodeList<Name> with;

    public ModuleProvidesDirective() {
        this(null, new Name(), new NodeList<Name>());
    }

    @AllFieldsConstructor
    public ModuleProvidesDirective(Name name, NodeList<Name> with) {
        this(null, name, with);
    }

    public ModuleProvidesDirective(TokenRange tokenRange, Name name, NodeList<Name> with) {
        super(tokenRange);
        this.setName(name);
        this.setWith(with);
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
    public boolean remove(Node node) {
        if (node == null) {
            return false;
        }
        for (int i = 0; i < this.with.size(); ++i) {
            if (this.with.get(i) != node) continue;
            this.with.remove(i);
            return true;
        }
        return super.remove(node);
    }

    @Override
    public ModuleProvidesDirective clone() {
        return (ModuleProvidesDirective)this.accept(new CloneVisitor(), null);
    }

    @Override
    public boolean isModuleProvidesStmt() {
        return true;
    }

    @Override
    public ModuleProvidesDirective asModuleProvidesStmt() {
        return this;
    }

    @Override
    public void ifModuleProvidesStmt(Consumer<ModuleProvidesDirective> action) {
        action.accept(this);
    }

    @Override
    public Optional<ModuleProvidesDirective> toModuleProvidesStmt() {
        return Optional.of(this);
    }

    @Override
    public Name getName() {
        return this.name;
    }

    @Override
    public ModuleProvidesDirective setName(Name name) {
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

    public ModuleProvidesDirective setWith(NodeList<Name> with) {
        Utils.assertNotNull(with);
        if (with == this.with) {
            return this;
        }
        this.notifyPropertyChange(ObservableProperty.WITH, this.with, with);
        if (this.with != null) {
            this.with.setParentNode(null);
        }
        this.with = with;
        this.setAsParentNodeOf(with);
        return this;
    }

    public NodeList<Name> getWith() {
        return this.with;
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
        for (int i = 0; i < this.with.size(); ++i) {
            if (this.with.get(i) != node) continue;
            this.with.set(i, (Name)replacementNode);
            return true;
        }
        return super.replace(node, replacementNode);
    }

    @Override
    public boolean isModuleProvidesDirective() {
        return true;
    }

    @Override
    public ModuleProvidesDirective asModuleProvidesDirective() {
        return this;
    }

    @Override
    public Optional<ModuleProvidesDirective> toModuleProvidesDirective() {
        return Optional.of(this);
    }

    @Override
    public void ifModuleProvidesDirective(Consumer<ModuleProvidesDirective> action) {
        action.accept(this);
    }

    @Override
    public ModuleProvidesDirectiveMetaModel getMetaModel() {
        return JavaParserMetaModel.moduleProvidesDirectiveMetaModel;
    }
}

