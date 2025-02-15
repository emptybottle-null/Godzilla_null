/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.ast.expr;

import com.github.javaparser.TokenRange;
import com.github.javaparser.ast.AllFieldsConstructor;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.Name;
import com.github.javaparser.ast.nodeTypes.NodeWithName;
import com.github.javaparser.ast.observer.ObservableProperty;
import com.github.javaparser.ast.visitor.CloneVisitor;
import com.github.javaparser.metamodel.AnnotationExprMetaModel;
import com.github.javaparser.metamodel.JavaParserMetaModel;
import com.github.javaparser.resolution.Resolvable;
import com.github.javaparser.resolution.declarations.ResolvedAnnotationDeclaration;
import com.github.javaparser.utils.Utils;
import java.util.Optional;
import java.util.function.Consumer;

public abstract class AnnotationExpr
extends Expression
implements NodeWithName<AnnotationExpr>,
Resolvable<ResolvedAnnotationDeclaration> {
    protected Name name;

    public AnnotationExpr() {
        this(null, new Name());
    }

    @AllFieldsConstructor
    public AnnotationExpr(Name name) {
        this(null, name);
    }

    public AnnotationExpr(TokenRange tokenRange, Name name) {
        super(tokenRange);
        this.setName(name);
        this.customInitialization();
    }

    @Override
    public Name getName() {
        return this.name;
    }

    @Override
    public AnnotationExpr setName(Name name) {
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
    public AnnotationExpr clone() {
        return (AnnotationExpr)this.accept(new CloneVisitor(), null);
    }

    @Override
    public AnnotationExprMetaModel getMetaModel() {
        return JavaParserMetaModel.annotationExprMetaModel;
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
    public boolean isAnnotationExpr() {
        return true;
    }

    @Override
    public AnnotationExpr asAnnotationExpr() {
        return this;
    }

    @Override
    public void ifAnnotationExpr(Consumer<AnnotationExpr> action) {
        action.accept(this);
    }

    @Override
    public ResolvedAnnotationDeclaration resolve() {
        return this.getSymbolResolver().resolveDeclaration(this, ResolvedAnnotationDeclaration.class);
    }

    @Override
    public Optional<AnnotationExpr> toAnnotationExpr() {
        return Optional.of(this);
    }
}

