/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.ast.modules;

import com.github.javaparser.TokenRange;
import com.github.javaparser.ast.AllFieldsConstructor;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.expr.Name;
import com.github.javaparser.ast.modules.ModuleDirective;
import com.github.javaparser.ast.nodeTypes.NodeWithName;
import com.github.javaparser.ast.observer.ObservableProperty;
import com.github.javaparser.ast.visitor.CloneVisitor;
import com.github.javaparser.ast.visitor.GenericVisitor;
import com.github.javaparser.ast.visitor.VoidVisitor;
import com.github.javaparser.metamodel.JavaParserMetaModel;
import com.github.javaparser.metamodel.ModuleUsesDirectiveMetaModel;
import com.github.javaparser.utils.Utils;
import java.util.Optional;
import java.util.function.Consumer;

public class ModuleUsesDirective
extends ModuleDirective
implements NodeWithName<ModuleUsesDirective> {
    private Name name;

    public ModuleUsesDirective() {
        this(null, new Name());
    }

    @AllFieldsConstructor
    public ModuleUsesDirective(Name name) {
        this(null, name);
    }

    public ModuleUsesDirective(TokenRange tokenRange, Name name) {
        super(tokenRange);
        this.setName(name);
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
        return super.remove(node);
    }

    public ModuleUsesDirective setType(Name name) {
        Utils.assertNotNull(name);
        if (name == this.name) {
            return this;
        }
        this.notifyPropertyChange(ObservableProperty.TYPE, this.name, name);
        if (this.name != null) {
            this.name.setParentNode(null);
        }
        this.name = name;
        this.setAsParentNodeOf(name);
        return this;
    }

    @Override
    public ModuleUsesDirective clone() {
        return (ModuleUsesDirective)this.accept(new CloneVisitor(), null);
    }

    @Override
    public boolean isModuleUsesStmt() {
        return true;
    }

    @Override
    public ModuleUsesDirective asModuleUsesStmt() {
        return this;
    }

    @Override
    public void ifModuleUsesStmt(Consumer<ModuleUsesDirective> action) {
        action.accept(this);
    }

    @Override
    public Optional<ModuleUsesDirective> toModuleUsesStmt() {
        return Optional.of(this);
    }

    @Override
    public Name getName() {
        return this.name;
    }

    @Override
    public ModuleUsesDirective setName(Name name) {
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

    @Override
    public boolean isModuleUsesDirective() {
        return true;
    }

    @Override
    public ModuleUsesDirective asModuleUsesDirective() {
        return this;
    }

    @Override
    public Optional<ModuleUsesDirective> toModuleUsesDirective() {
        return Optional.of(this);
    }

    @Override
    public void ifModuleUsesDirective(Consumer<ModuleUsesDirective> action) {
        action.accept(this);
    }

    @Override
    public ModuleUsesDirectiveMetaModel getMetaModel() {
        return JavaParserMetaModel.moduleUsesDirectiveMetaModel;
    }
}

