/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.ast.modules;

import com.github.javaparser.StaticJavaParser;
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
import com.github.javaparser.metamodel.ModuleExportsDirectiveMetaModel;
import com.github.javaparser.utils.Utils;
import java.util.Optional;
import java.util.function.Consumer;

public class ModuleExportsDirective
extends ModuleDirective
implements NodeWithName<ModuleExportsDirective> {
    private Name name;
    private NodeList<Name> moduleNames;

    public ModuleExportsDirective() {
        this(null, new Name(), new NodeList<Name>());
    }

    @AllFieldsConstructor
    public ModuleExportsDirective(Name name, NodeList<Name> moduleNames) {
        this(null, name, moduleNames);
    }

    public ModuleExportsDirective(TokenRange tokenRange, Name name, NodeList<Name> moduleNames) {
        super(tokenRange);
        this.setName(name);
        this.setModuleNames(moduleNames);
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
        for (int i = 0; i < this.moduleNames.size(); ++i) {
            if (this.moduleNames.get(i) != node) continue;
            this.moduleNames.remove(i);
            return true;
        }
        return super.remove(node);
    }

    @Override
    public Name getName() {
        return this.name;
    }

    @Override
    public ModuleExportsDirective setName(Name name) {
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

    public NodeList<Name> getModuleNames() {
        return this.moduleNames;
    }

    public ModuleExportsDirective setModuleNames(NodeList<Name> moduleNames) {
        Utils.assertNotNull(moduleNames);
        if (moduleNames == this.moduleNames) {
            return this;
        }
        this.notifyPropertyChange(ObservableProperty.MODULE_NAMES, this.moduleNames, moduleNames);
        if (this.moduleNames != null) {
            this.moduleNames.setParentNode(null);
        }
        this.moduleNames = moduleNames;
        this.setAsParentNodeOf(moduleNames);
        return this;
    }

    @Override
    public ModuleExportsDirective clone() {
        return (ModuleExportsDirective)this.accept(new CloneVisitor(), null);
    }

    @Override
    public boolean replace(Node node, Node replacementNode) {
        if (node == null) {
            return false;
        }
        for (int i = 0; i < this.moduleNames.size(); ++i) {
            if (this.moduleNames.get(i) != node) continue;
            this.moduleNames.set(i, (Name)replacementNode);
            return true;
        }
        if (node == this.name) {
            this.setName((Name)replacementNode);
            return true;
        }
        return super.replace(node, replacementNode);
    }

    @Override
    public boolean isModuleExportsStmt() {
        return true;
    }

    @Override
    public ModuleExportsDirective asModuleExportsStmt() {
        return this;
    }

    @Override
    public void ifModuleExportsStmt(Consumer<ModuleExportsDirective> action) {
        action.accept(this);
    }

    @Override
    public Optional<ModuleExportsDirective> toModuleExportsStmt() {
        return Optional.of(this);
    }

    public ModuleExportsDirective addModuleName(String name) {
        this.moduleNames.add(StaticJavaParser.parseName(name));
        return this;
    }

    @Override
    public boolean isModuleExportsDirective() {
        return true;
    }

    @Override
    public ModuleExportsDirective asModuleExportsDirective() {
        return this;
    }

    @Override
    public Optional<ModuleExportsDirective> toModuleExportsDirective() {
        return Optional.of(this);
    }

    @Override
    public void ifModuleExportsDirective(Consumer<ModuleExportsDirective> action) {
        action.accept(this);
    }

    @Override
    public ModuleExportsDirectiveMetaModel getMetaModel() {
        return JavaParserMetaModel.moduleExportsDirectiveMetaModel;
    }
}

