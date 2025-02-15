/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.ast.expr;

import com.github.javaparser.TokenRange;
import com.github.javaparser.ast.AllFieldsConstructor;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.nodeTypes.NodeWithParameters;
import com.github.javaparser.ast.observer.ObservableProperty;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.stmt.ExpressionStmt;
import com.github.javaparser.ast.stmt.ReturnStmt;
import com.github.javaparser.ast.stmt.Statement;
import com.github.javaparser.ast.visitor.CloneVisitor;
import com.github.javaparser.ast.visitor.GenericVisitor;
import com.github.javaparser.ast.visitor.VoidVisitor;
import com.github.javaparser.metamodel.DerivedProperty;
import com.github.javaparser.metamodel.JavaParserMetaModel;
import com.github.javaparser.metamodel.LambdaExprMetaModel;
import com.github.javaparser.utils.Utils;
import java.util.Optional;
import java.util.function.Consumer;

public class LambdaExpr
extends Expression
implements NodeWithParameters<LambdaExpr> {
    private NodeList<Parameter> parameters;
    private boolean isEnclosingParameters;
    private Statement body;

    public LambdaExpr() {
        this(null, new NodeList<Parameter>(), new ReturnStmt(), false);
    }

    public LambdaExpr(Parameter parameter, BlockStmt body) {
        this(null, new NodeList((Node[])new Parameter[]{parameter}), body, false);
    }

    public LambdaExpr(NodeList<Parameter> parameters, BlockStmt body) {
        this(null, parameters, body, true);
    }

    public LambdaExpr(Parameter parameter, Expression body) {
        this(null, new NodeList((Node[])new Parameter[]{parameter}), new ExpressionStmt(body), false);
    }

    public LambdaExpr(NodeList<Parameter> parameters, Expression body) {
        this(null, parameters, new ExpressionStmt(body), true);
    }

    @AllFieldsConstructor
    public LambdaExpr(NodeList<Parameter> parameters, Statement body, boolean isEnclosingParameters) {
        this(null, parameters, body, isEnclosingParameters);
    }

    public LambdaExpr(TokenRange tokenRange, NodeList<Parameter> parameters, Statement body, boolean isEnclosingParameters) {
        super(tokenRange);
        this.setParameters((NodeList)parameters);
        this.setBody(body);
        this.setEnclosingParameters(isEnclosingParameters);
        this.customInitialization();
    }

    @Override
    public NodeList<Parameter> getParameters() {
        return this.parameters;
    }

    @Override
    public LambdaExpr setParameters(NodeList<Parameter> parameters) {
        Utils.assertNotNull(parameters);
        if (parameters == this.parameters) {
            return this;
        }
        this.notifyPropertyChange(ObservableProperty.PARAMETERS, this.parameters, parameters);
        if (this.parameters != null) {
            this.parameters.setParentNode(null);
        }
        this.parameters = parameters;
        this.setAsParentNodeOf(parameters);
        return this;
    }

    public Statement getBody() {
        return this.body;
    }

    public LambdaExpr setBody(Statement body) {
        Utils.assertNotNull(body);
        if (body == this.body) {
            return this;
        }
        this.notifyPropertyChange(ObservableProperty.BODY, this.body, body);
        if (this.body != null) {
            this.body.setParentNode(null);
        }
        this.body = body;
        this.setAsParentNodeOf(body);
        return this;
    }

    @Override
    public <R, A> R accept(GenericVisitor<R, A> v, A arg) {
        return v.visit(this, arg);
    }

    @Override
    public <A> void accept(VoidVisitor<A> v, A arg) {
        v.visit(this, arg);
    }

    public boolean isEnclosingParameters() {
        return this.isEnclosingParameters;
    }

    public LambdaExpr setEnclosingParameters(boolean isEnclosingParameters) {
        if (isEnclosingParameters == this.isEnclosingParameters) {
            return this;
        }
        this.notifyPropertyChange(ObservableProperty.ENCLOSING_PARAMETERS, this.isEnclosingParameters, isEnclosingParameters);
        this.isEnclosingParameters = isEnclosingParameters;
        return this;
    }

    @Override
    public boolean remove(Node node) {
        if (node == null) {
            return false;
        }
        for (int i = 0; i < this.parameters.size(); ++i) {
            if (this.parameters.get(i) != node) continue;
            this.parameters.remove(i);
            return true;
        }
        return super.remove(node);
    }

    @DerivedProperty
    public Optional<Expression> getExpressionBody() {
        if (this.body.isExpressionStmt()) {
            return Optional.of(this.body.asExpressionStmt().getExpression());
        }
        return Optional.empty();
    }

    @Override
    public LambdaExpr clone() {
        return (LambdaExpr)this.accept(new CloneVisitor(), null);
    }

    @Override
    public LambdaExprMetaModel getMetaModel() {
        return JavaParserMetaModel.lambdaExprMetaModel;
    }

    @Override
    public boolean replace(Node node, Node replacementNode) {
        if (node == null) {
            return false;
        }
        if (node == this.body) {
            this.setBody((Statement)replacementNode);
            return true;
        }
        for (int i = 0; i < this.parameters.size(); ++i) {
            if (this.parameters.get(i) != node) continue;
            this.parameters.set(i, (Parameter)replacementNode);
            return true;
        }
        return super.replace(node, replacementNode);
    }

    @Override
    public boolean isLambdaExpr() {
        return true;
    }

    @Override
    public LambdaExpr asLambdaExpr() {
        return this;
    }

    @Override
    public void ifLambdaExpr(Consumer<LambdaExpr> action) {
        action.accept(this);
    }

    @Override
    public Optional<LambdaExpr> toLambdaExpr() {
        return Optional.of(this);
    }

    @Override
    public boolean isPolyExpression() {
        return true;
    }
}

