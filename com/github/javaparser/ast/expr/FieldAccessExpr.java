/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.ast.expr;

import com.github.javaparser.TokenRange;
import com.github.javaparser.ast.AllFieldsConstructor;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.SimpleName;
import com.github.javaparser.ast.expr.ThisExpr;
import com.github.javaparser.ast.nodeTypes.NodeWithScope;
import com.github.javaparser.ast.nodeTypes.NodeWithSimpleName;
import com.github.javaparser.ast.nodeTypes.NodeWithTypeArguments;
import com.github.javaparser.ast.observer.ObservableProperty;
import com.github.javaparser.ast.type.Type;
import com.github.javaparser.ast.visitor.CloneVisitor;
import com.github.javaparser.ast.visitor.GenericVisitor;
import com.github.javaparser.ast.visitor.VoidVisitor;
import com.github.javaparser.metamodel.FieldAccessExprMetaModel;
import com.github.javaparser.metamodel.JavaParserMetaModel;
import com.github.javaparser.metamodel.OptionalProperty;
import com.github.javaparser.resolution.Resolvable;
import com.github.javaparser.resolution.declarations.ResolvedValueDeclaration;
import com.github.javaparser.utils.Utils;
import java.util.Optional;
import java.util.function.Consumer;

public class FieldAccessExpr
extends Expression
implements NodeWithSimpleName<FieldAccessExpr>,
NodeWithTypeArguments<FieldAccessExpr>,
NodeWithScope<FieldAccessExpr>,
Resolvable<ResolvedValueDeclaration> {
    private Expression scope;
    @OptionalProperty
    private NodeList<Type> typeArguments;
    private SimpleName name;

    public FieldAccessExpr() {
        this(null, new ThisExpr(), null, new SimpleName());
    }

    public FieldAccessExpr(Expression scope, String name) {
        this(null, scope, null, new SimpleName(name));
    }

    @AllFieldsConstructor
    public FieldAccessExpr(Expression scope, NodeList<Type> typeArguments, SimpleName name) {
        this(null, scope, typeArguments, name);
    }

    public FieldAccessExpr(TokenRange tokenRange, Expression scope, NodeList<Type> typeArguments, SimpleName name) {
        super(tokenRange);
        this.setScope(scope);
        this.setTypeArguments((NodeList)typeArguments);
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
    public FieldAccessExpr setName(SimpleName name) {
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
    public Expression getScope() {
        return this.scope;
    }

    @Override
    public FieldAccessExpr setScope(Expression scope) {
        Utils.assertNotNull(scope);
        if (scope == this.scope) {
            return this;
        }
        this.notifyPropertyChange(ObservableProperty.SCOPE, this.scope, scope);
        if (this.scope != null) {
            this.scope.setParentNode(null);
        }
        this.scope = scope;
        this.setAsParentNodeOf(scope);
        return this;
    }

    @Override
    public Optional<NodeList<Type>> getTypeArguments() {
        return Optional.ofNullable(this.typeArguments);
    }

    @Override
    public FieldAccessExpr setTypeArguments(NodeList<Type> typeArguments) {
        if (typeArguments == this.typeArguments) {
            return this;
        }
        this.notifyPropertyChange(ObservableProperty.TYPE_ARGUMENTS, this.typeArguments, typeArguments);
        if (this.typeArguments != null) {
            this.typeArguments.setParentNode(null);
        }
        this.typeArguments = typeArguments;
        this.setAsParentNodeOf(typeArguments);
        return this;
    }

    @Override
    public FieldAccessExpr clone() {
        return (FieldAccessExpr)this.accept(new CloneVisitor(), null);
    }

    @Override
    public FieldAccessExprMetaModel getMetaModel() {
        return JavaParserMetaModel.fieldAccessExprMetaModel;
    }

    @Override
    public boolean remove(Node node) {
        if (node == null) {
            return false;
        }
        if (this.typeArguments != null) {
            for (int i = 0; i < this.typeArguments.size(); ++i) {
                if (this.typeArguments.get(i) != node) continue;
                this.typeArguments.remove(i);
                return true;
            }
        }
        return super.remove(node);
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
        if (node == this.scope) {
            this.setScope((Expression)replacementNode);
            return true;
        }
        if (this.typeArguments != null) {
            for (int i = 0; i < this.typeArguments.size(); ++i) {
                if (this.typeArguments.get(i) != node) continue;
                this.typeArguments.set(i, (Type)replacementNode);
                return true;
            }
        }
        return super.replace(node, replacementNode);
    }

    @Override
    public boolean isFieldAccessExpr() {
        return true;
    }

    @Override
    public FieldAccessExpr asFieldAccessExpr() {
        return this;
    }

    @Override
    public void ifFieldAccessExpr(Consumer<FieldAccessExpr> action) {
        action.accept(this);
    }

    @Override
    public ResolvedValueDeclaration resolve() {
        return this.getSymbolResolver().resolveDeclaration(this, ResolvedValueDeclaration.class);
    }

    @Override
    public Optional<FieldAccessExpr> toFieldAccessExpr() {
        return Optional.of(this);
    }

    public boolean isInternal() {
        return this.getParentNode().isPresent() && this.getParentNode().get() instanceof FieldAccessExpr;
    }

    public boolean isTopLevel() {
        return !this.isInternal();
    }
}

