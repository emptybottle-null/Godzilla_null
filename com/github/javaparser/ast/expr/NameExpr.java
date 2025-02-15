/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.ast.expr;

import com.github.javaparser.TokenRange;
import com.github.javaparser.ast.AllFieldsConstructor;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.SimpleName;
import com.github.javaparser.ast.nodeTypes.NodeWithSimpleName;
import com.github.javaparser.ast.observer.ObservableProperty;
import com.github.javaparser.ast.visitor.CloneVisitor;
import com.github.javaparser.ast.visitor.GenericVisitor;
import com.github.javaparser.ast.visitor.VoidVisitor;
import com.github.javaparser.metamodel.JavaParserMetaModel;
import com.github.javaparser.metamodel.NameExprMetaModel;
import com.github.javaparser.resolution.Resolvable;
import com.github.javaparser.resolution.declarations.ResolvedValueDeclaration;
import com.github.javaparser.utils.Utils;
import java.util.Optional;
import java.util.function.Consumer;

public class NameExpr
extends Expression
implements NodeWithSimpleName<NameExpr>,
Resolvable<ResolvedValueDeclaration> {
    private SimpleName name;

    public NameExpr() {
        this(null, new SimpleName());
    }

    public NameExpr(String name) {
        this(null, new SimpleName(name));
    }

    @AllFieldsConstructor
    public NameExpr(SimpleName name) {
        this(name.getTokenRange().orElse(null), name);
        this.setRange(name.getRange().orElse(null));
    }

    public NameExpr(TokenRange tokenRange, SimpleName name) {
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
    public SimpleName getName() {
        return this.name;
    }

    @Override
    public NameExpr setName(SimpleName name) {
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
    public boolean remove(Node node) {
        if (node == null) {
            return false;
        }
        return super.remove(node);
    }

    @Override
    public NameExpr clone() {
        return (NameExpr)this.accept(new CloneVisitor(), null);
    }

    @Override
    public NameExprMetaModel getMetaModel() {
        return JavaParserMetaModel.nameExprMetaModel;
    }

    @Override
    public boolean replace(Node node, Node replacementNode) {
        if (node == null) {
            return false;
        }
        if (node == this.name) {
            this.setName((SimpleName)replacementNode);
            return true;
        }
        return super.replace(node, replacementNode);
    }

    @Override
    public boolean isNameExpr() {
        return true;
    }

    @Override
    public NameExpr asNameExpr() {
        return this;
    }

    @Override
    public void ifNameExpr(Consumer<NameExpr> action) {
        action.accept(this);
    }

    @Override
    public ResolvedValueDeclaration resolve() {
        return this.getSymbolResolver().resolveDeclaration(this, ResolvedValueDeclaration.class);
    }

    @Override
    public Optional<NameExpr> toNameExpr() {
        return Optional.of(this);
    }
}

