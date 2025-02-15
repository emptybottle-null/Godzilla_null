/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.ast.modules;

import com.github.javaparser.TokenRange;
import com.github.javaparser.ast.AllFieldsConstructor;
import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.expr.Name;
import com.github.javaparser.ast.modules.ModuleDirective;
import com.github.javaparser.ast.nodeTypes.NodeWithName;
import com.github.javaparser.ast.nodeTypes.modifiers.NodeWithStaticModifier;
import com.github.javaparser.ast.observer.ObservableProperty;
import com.github.javaparser.ast.visitor.CloneVisitor;
import com.github.javaparser.ast.visitor.GenericVisitor;
import com.github.javaparser.ast.visitor.VoidVisitor;
import com.github.javaparser.metamodel.JavaParserMetaModel;
import com.github.javaparser.metamodel.ModuleRequiresDirectiveMetaModel;
import com.github.javaparser.utils.Utils;
import java.util.Optional;
import java.util.function.Consumer;

public class ModuleRequiresDirective
extends ModuleDirective
implements NodeWithStaticModifier<ModuleRequiresDirective>,
NodeWithName<ModuleRequiresDirective> {
    private NodeList<Modifier> modifiers;
    private Name name;

    public ModuleRequiresDirective() {
        this(null, new NodeList<Modifier>(), new Name());
    }

    @AllFieldsConstructor
    public ModuleRequiresDirective(NodeList<Modifier> modifiers, Name name) {
        this(null, modifiers, name);
    }

    public ModuleRequiresDirective(TokenRange tokenRange, NodeList<Modifier> modifiers, Name name) {
        super(tokenRange);
        this.setModifiers((NodeList)modifiers);
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
    public NodeList<Modifier> getModifiers() {
        return this.modifiers;
    }

    @Override
    public ModuleRequiresDirective setModifiers(NodeList<Modifier> modifiers) {
        Utils.assertNotNull(modifiers);
        if (modifiers == this.modifiers) {
            return this;
        }
        this.notifyPropertyChange(ObservableProperty.MODIFIERS, this.modifiers, modifiers);
        if (this.modifiers != null) {
            this.modifiers.setParentNode(null);
        }
        this.modifiers = modifiers;
        this.setAsParentNodeOf(modifiers);
        return this;
    }

    @Override
    public Name getName() {
        return this.name;
    }

    @Override
    public ModuleRequiresDirective setName(Name name) {
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

    public boolean isTransitive() {
        return this.hasModifier(Modifier.Keyword.TRANSITIVE);
    }

    public ModuleRequiresDirective setTransitive(boolean set) {
        return (ModuleRequiresDirective)this.setModifier(Modifier.Keyword.TRANSITIVE, set);
    }

    @Override
    public boolean remove(Node node) {
        if (node == null) {
            return false;
        }
        for (int i = 0; i < this.modifiers.size(); ++i) {
            if (this.modifiers.get(i) != node) continue;
            this.modifiers.remove(i);
            return true;
        }
        return super.remove(node);
    }

    @Override
    public ModuleRequiresDirective clone() {
        return (ModuleRequiresDirective)this.accept(new CloneVisitor(), null);
    }

    @Override
    public boolean replace(Node node, Node replacementNode) {
        if (node == null) {
            return false;
        }
        for (int i = 0; i < this.modifiers.size(); ++i) {
            if (this.modifiers.get(i) != node) continue;
            this.modifiers.set(i, (Modifier)replacementNode);
            return true;
        }
        if (node == this.name) {
            this.setName((Name)replacementNode);
            return true;
        }
        return super.replace(node, replacementNode);
    }

    @Override
    public boolean isModuleRequiresStmt() {
        return true;
    }

    @Override
    public ModuleRequiresDirective asModuleRequiresStmt() {
        return this;
    }

    @Override
    public void ifModuleRequiresStmt(Consumer<ModuleRequiresDirective> action) {
        action.accept(this);
    }

    @Override
    public Optional<ModuleRequiresDirective> toModuleRequiresStmt() {
        return Optional.of(this);
    }

    @Override
    public boolean isModuleRequiresDirective() {
        return true;
    }

    @Override
    public ModuleRequiresDirective asModuleRequiresDirective() {
        return this;
    }

    @Override
    public Optional<ModuleRequiresDirective> toModuleRequiresDirective() {
        return Optional.of(this);
    }

    @Override
    public void ifModuleRequiresDirective(Consumer<ModuleRequiresDirective> action) {
        action.accept(this);
    }

    @Override
    public ModuleRequiresDirectiveMetaModel getMetaModel() {
        return JavaParserMetaModel.moduleRequiresDirectiveMetaModel;
    }
}

