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
import com.github.javaparser.metamodel.ModuleOpensDirectiveMetaModel;
import com.github.javaparser.utils.Utils;
import java.util.Optional;
import java.util.function.Consumer;

public class ModuleOpensDirective
extends ModuleDirective
implements NodeWithName<ModuleOpensDirective> {
    private Name name;
    private NodeList<Name> moduleNames;

    public ModuleOpensDirective() {
        this(null, new Name(), new NodeList<Name>());
    }

    @AllFieldsConstructor
    public ModuleOpensDirective(Name name, NodeList<Name> moduleNames) {
        this(null, name, moduleNames);
    }

    public ModuleOpensDirective(TokenRange tokenRange, Name name, NodeList<Name> moduleNames) {
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
    public ModuleOpensDirective setName(Name name) {
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

    public ModuleOpensDirective setModuleNames(NodeList<Name> moduleNames) {
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
    public ModuleOpensDirective clone() {
        return (ModuleOpensDirective)this.accept(new CloneVisitor(), null);
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
    public boolean isModuleOpensStmt() {
        return true;
    }

    @Override
    public ModuleOpensDirective asModuleOpensStmt() {
        return this;
    }

    @Override
    public void ifModuleOpensStmt(Consumer<ModuleOpensDirective> action) {
        action.accept(this);
    }

    @Override
    public Optional<ModuleOpensDirective> toModuleOpensStmt() {
        return Optional.of(this);
    }

    @Override
    public boolean isModuleOpensDirective() {
        return true;
    }

    @Override
    public ModuleOpensDirective asModuleOpensDirective() {
        return this;
    }

    @Override
    public Optional<ModuleOpensDirective> toModuleOpensDirective() {
        return Optional.of(this);
    }

    @Override
    public void ifModuleOpensDirective(Consumer<ModuleOpensDirective> action) {
        action.accept(this);
    }

    @Override
    public ModuleOpensDirectiveMetaModel getMetaModel() {
        return JavaParserMetaModel.moduleOpensDirectiveMetaModel;
    }
}

