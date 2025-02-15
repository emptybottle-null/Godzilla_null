/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.ast.expr;

import com.github.javaparser.TokenRange;
import com.github.javaparser.ast.AllFieldsConstructor;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.expr.ClassExpr;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.nodeTypes.NodeWithIdentifier;
import com.github.javaparser.ast.nodeTypes.NodeWithTypeArguments;
import com.github.javaparser.ast.observer.ObservableProperty;
import com.github.javaparser.ast.type.Type;
import com.github.javaparser.ast.visitor.CloneVisitor;
import com.github.javaparser.ast.visitor.GenericVisitor;
import com.github.javaparser.ast.visitor.VoidVisitor;
import com.github.javaparser.metamodel.JavaParserMetaModel;
import com.github.javaparser.metamodel.MethodReferenceExprMetaModel;
import com.github.javaparser.metamodel.NonEmptyProperty;
import com.github.javaparser.metamodel.OptionalProperty;
import com.github.javaparser.resolution.Resolvable;
import com.github.javaparser.resolution.declarations.ResolvedMethodDeclaration;
import com.github.javaparser.utils.Utils;
import java.util.Optional;
import java.util.function.Consumer;

public class MethodReferenceExpr
extends Expression
implements NodeWithTypeArguments<MethodReferenceExpr>,
NodeWithIdentifier<MethodReferenceExpr>,
Resolvable<ResolvedMethodDeclaration> {
    private Expression scope;
    @OptionalProperty
    private NodeList<Type> typeArguments;
    @NonEmptyProperty
    private String identifier;

    public MethodReferenceExpr() {
        this(null, new ClassExpr(), null, "empty");
    }

    @AllFieldsConstructor
    public MethodReferenceExpr(Expression scope, NodeList<Type> typeArguments, String identifier) {
        this(null, scope, typeArguments, identifier);
    }

    public MethodReferenceExpr(TokenRange tokenRange, Expression scope, NodeList<Type> typeArguments, String identifier) {
        super(tokenRange);
        this.setScope(scope);
        this.setTypeArguments((NodeList)typeArguments);
        this.setIdentifier(identifier);
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

    public Expression getScope() {
        return this.scope;
    }

    public MethodReferenceExpr setScope(Expression scope) {
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
    public MethodReferenceExpr setTypeArguments(NodeList<Type> typeArguments) {
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
    public String getIdentifier() {
        return this.identifier;
    }

    @Override
    public MethodReferenceExpr setIdentifier(String identifier) {
        Utils.assertNonEmpty(identifier);
        if (identifier == this.identifier) {
            return this;
        }
        this.notifyPropertyChange(ObservableProperty.IDENTIFIER, this.identifier, identifier);
        this.identifier = identifier;
        return this;
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
    public MethodReferenceExpr clone() {
        return (MethodReferenceExpr)this.accept(new CloneVisitor(), null);
    }

    @Override
    public MethodReferenceExprMetaModel getMetaModel() {
        return JavaParserMetaModel.methodReferenceExprMetaModel;
    }

    @Override
    public boolean replace(Node node, Node replacementNode) {
        if (node == null) {
            return false;
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
    public boolean isMethodReferenceExpr() {
        return true;
    }

    @Override
    public MethodReferenceExpr asMethodReferenceExpr() {
        return this;
    }

    @Override
    public void ifMethodReferenceExpr(Consumer<MethodReferenceExpr> action) {
        action.accept(this);
    }

    @Override
    public Optional<MethodReferenceExpr> toMethodReferenceExpr() {
        return Optional.of(this);
    }

    @Override
    public ResolvedMethodDeclaration resolve() {
        return this.getSymbolResolver().resolveDeclaration(this, ResolvedMethodDeclaration.class);
    }

    @Override
    public boolean isPolyExpression() {
        return true;
    }
}

