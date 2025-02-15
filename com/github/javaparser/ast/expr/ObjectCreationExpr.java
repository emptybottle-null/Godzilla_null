/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.ast.expr;

import com.github.javaparser.TokenRange;
import com.github.javaparser.ast.AllFieldsConstructor;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.BodyDeclaration;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.nodeTypes.NodeWithArguments;
import com.github.javaparser.ast.nodeTypes.NodeWithOptionalScope;
import com.github.javaparser.ast.nodeTypes.NodeWithType;
import com.github.javaparser.ast.nodeTypes.NodeWithTypeArguments;
import com.github.javaparser.ast.observer.ObservableProperty;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.ast.type.Type;
import com.github.javaparser.ast.visitor.CloneVisitor;
import com.github.javaparser.ast.visitor.GenericVisitor;
import com.github.javaparser.ast.visitor.VoidVisitor;
import com.github.javaparser.metamodel.JavaParserMetaModel;
import com.github.javaparser.metamodel.ObjectCreationExprMetaModel;
import com.github.javaparser.metamodel.OptionalProperty;
import com.github.javaparser.resolution.Resolvable;
import com.github.javaparser.resolution.declarations.ResolvedConstructorDeclaration;
import com.github.javaparser.utils.Utils;
import java.util.Optional;
import java.util.function.Consumer;

public class ObjectCreationExpr
extends Expression
implements NodeWithTypeArguments<ObjectCreationExpr>,
NodeWithType<ObjectCreationExpr, ClassOrInterfaceType>,
NodeWithArguments<ObjectCreationExpr>,
NodeWithOptionalScope<ObjectCreationExpr>,
Resolvable<ResolvedConstructorDeclaration> {
    @OptionalProperty
    private Expression scope;
    private ClassOrInterfaceType type;
    @OptionalProperty
    private NodeList<Type> typeArguments;
    private NodeList<Expression> arguments;
    @OptionalProperty
    private NodeList<BodyDeclaration<?>> anonymousClassBody;

    public ObjectCreationExpr() {
        this(null, null, new ClassOrInterfaceType(), new NodeList<Type>(), new NodeList<Expression>(), null);
    }

    public ObjectCreationExpr(Expression scope, ClassOrInterfaceType type, NodeList<Expression> arguments) {
        this(null, scope, type, null, arguments, null);
    }

    @AllFieldsConstructor
    public ObjectCreationExpr(Expression scope, ClassOrInterfaceType type, NodeList<Type> typeArguments, NodeList<Expression> arguments, NodeList<BodyDeclaration<?>> anonymousClassBody) {
        this(null, scope, type, typeArguments, arguments, anonymousClassBody);
    }

    public ObjectCreationExpr(TokenRange tokenRange, Expression scope, ClassOrInterfaceType type, NodeList<Type> typeArguments, NodeList<Expression> arguments, NodeList<BodyDeclaration<?>> anonymousClassBody) {
        super(tokenRange);
        this.setScope(scope);
        this.setType(type);
        this.setTypeArguments((NodeList)typeArguments);
        this.setArguments((NodeList)arguments);
        this.setAnonymousClassBody(anonymousClassBody);
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

    public Optional<NodeList<BodyDeclaration<?>>> getAnonymousClassBody() {
        return Optional.ofNullable(this.anonymousClassBody);
    }

    public void addAnonymousClassBody(BodyDeclaration<?> body) {
        if (this.anonymousClassBody == null) {
            this.anonymousClassBody = new NodeList();
        }
        this.anonymousClassBody.add(body);
    }

    @Override
    public NodeList<Expression> getArguments() {
        return this.arguments;
    }

    @Override
    public Optional<Expression> getScope() {
        return Optional.ofNullable(this.scope);
    }

    @Override
    public ClassOrInterfaceType getType() {
        return this.type;
    }

    public ObjectCreationExpr setAnonymousClassBody(NodeList<BodyDeclaration<?>> anonymousClassBody) {
        if (anonymousClassBody == this.anonymousClassBody) {
            return this;
        }
        this.notifyPropertyChange(ObservableProperty.ANONYMOUS_CLASS_BODY, this.anonymousClassBody, anonymousClassBody);
        if (this.anonymousClassBody != null) {
            this.anonymousClassBody.setParentNode(null);
        }
        this.anonymousClassBody = anonymousClassBody;
        this.setAsParentNodeOf(anonymousClassBody);
        return this;
    }

    @Override
    public ObjectCreationExpr setArguments(NodeList<Expression> arguments) {
        Utils.assertNotNull(arguments);
        if (arguments == this.arguments) {
            return this;
        }
        this.notifyPropertyChange(ObservableProperty.ARGUMENTS, this.arguments, arguments);
        if (this.arguments != null) {
            this.arguments.setParentNode(null);
        }
        this.arguments = arguments;
        this.setAsParentNodeOf(arguments);
        return this;
    }

    @Override
    public ObjectCreationExpr setScope(Expression scope) {
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
    public ObjectCreationExpr setType(ClassOrInterfaceType type) {
        Utils.assertNotNull(type);
        if (type == this.type) {
            return this;
        }
        this.notifyPropertyChange(ObservableProperty.TYPE, this.type, type);
        if (this.type != null) {
            this.type.setParentNode(null);
        }
        this.type = type;
        this.setAsParentNodeOf(type);
        return this;
    }

    @Override
    public Optional<NodeList<Type>> getTypeArguments() {
        return Optional.ofNullable(this.typeArguments);
    }

    @Override
    public ObjectCreationExpr setTypeArguments(NodeList<Type> typeArguments) {
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
    public boolean remove(Node node) {
        int i;
        if (node == null) {
            return false;
        }
        if (this.anonymousClassBody != null) {
            for (i = 0; i < this.anonymousClassBody.size(); ++i) {
                if (this.anonymousClassBody.get(i) != node) continue;
                this.anonymousClassBody.remove(i);
                return true;
            }
        }
        for (i = 0; i < this.arguments.size(); ++i) {
            if (this.arguments.get(i) != node) continue;
            this.arguments.remove(i);
            return true;
        }
        if (this.scope != null && node == this.scope) {
            this.removeScope();
            return true;
        }
        if (this.typeArguments != null) {
            for (i = 0; i < this.typeArguments.size(); ++i) {
                if (this.typeArguments.get(i) != node) continue;
                this.typeArguments.remove(i);
                return true;
            }
        }
        return super.remove(node);
    }

    @Override
    public ObjectCreationExpr removeScope() {
        return this.setScope(null);
    }

    @Override
    public ObjectCreationExpr clone() {
        return (ObjectCreationExpr)this.accept(new CloneVisitor(), null);
    }

    @Override
    public ObjectCreationExprMetaModel getMetaModel() {
        return JavaParserMetaModel.objectCreationExprMetaModel;
    }

    @Override
    public boolean replace(Node node, Node replacementNode) {
        int i;
        if (node == null) {
            return false;
        }
        if (this.anonymousClassBody != null) {
            for (i = 0; i < this.anonymousClassBody.size(); ++i) {
                if (this.anonymousClassBody.get(i) != node) continue;
                this.anonymousClassBody.set(i, (BodyDeclaration)replacementNode);
                return true;
            }
        }
        for (i = 0; i < this.arguments.size(); ++i) {
            if (this.arguments.get(i) != node) continue;
            this.arguments.set(i, (Expression)replacementNode);
            return true;
        }
        if (this.scope != null && node == this.scope) {
            this.setScope((Expression)replacementNode);
            return true;
        }
        if (node == this.type) {
            this.setType((ClassOrInterfaceType)replacementNode);
            return true;
        }
        if (this.typeArguments != null) {
            for (i = 0; i < this.typeArguments.size(); ++i) {
                if (this.typeArguments.get(i) != node) continue;
                this.typeArguments.set(i, (Type)replacementNode);
                return true;
            }
        }
        return super.replace(node, replacementNode);
    }

    @Override
    public boolean isObjectCreationExpr() {
        return true;
    }

    @Override
    public ObjectCreationExpr asObjectCreationExpr() {
        return this;
    }

    @Override
    public void ifObjectCreationExpr(Consumer<ObjectCreationExpr> action) {
        action.accept(this);
    }

    @Override
    public ResolvedConstructorDeclaration resolve() {
        return this.getSymbolResolver().resolveDeclaration(this, ResolvedConstructorDeclaration.class);
    }

    @Override
    public Optional<ObjectCreationExpr> toObjectCreationExpr() {
        return Optional.of(this);
    }

    @Override
    public boolean isPolyExpression() {
        return this.isUsingDiamondOperator() && (this.appearsInInvocationContext() || this.appearsInAssignmentContext());
    }
}

