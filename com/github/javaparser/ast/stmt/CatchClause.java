/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.ast.stmt;

import com.github.javaparser.TokenRange;
import com.github.javaparser.ast.AllFieldsConstructor;
import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.expr.AnnotationExpr;
import com.github.javaparser.ast.expr.SimpleName;
import com.github.javaparser.ast.nodeTypes.NodeWithBlockStmt;
import com.github.javaparser.ast.observer.ObservableProperty;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.ast.visitor.CloneVisitor;
import com.github.javaparser.ast.visitor.GenericVisitor;
import com.github.javaparser.ast.visitor.VoidVisitor;
import com.github.javaparser.metamodel.CatchClauseMetaModel;
import com.github.javaparser.metamodel.JavaParserMetaModel;
import com.github.javaparser.utils.Utils;

public class CatchClause
extends Node
implements NodeWithBlockStmt<CatchClause> {
    private Parameter parameter;
    private BlockStmt body;

    public CatchClause() {
        this(null, new Parameter(), new BlockStmt());
    }

    public CatchClause(NodeList<Modifier> exceptModifier, NodeList<AnnotationExpr> exceptAnnotations, ClassOrInterfaceType exceptType, SimpleName exceptName, BlockStmt body) {
        this(null, new Parameter(null, exceptModifier, exceptAnnotations, exceptType, false, new NodeList<AnnotationExpr>(), exceptName), body);
    }

    @AllFieldsConstructor
    public CatchClause(Parameter parameter, BlockStmt body) {
        this(null, parameter, body);
    }

    public CatchClause(TokenRange tokenRange, Parameter parameter, BlockStmt body) {
        super(tokenRange);
        this.setParameter(parameter);
        this.setBody(body);
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

    public Parameter getParameter() {
        return this.parameter;
    }

    public CatchClause setParameter(Parameter parameter) {
        Utils.assertNotNull(parameter);
        if (parameter == this.parameter) {
            return this;
        }
        this.notifyPropertyChange(ObservableProperty.PARAMETER, this.parameter, parameter);
        if (this.parameter != null) {
            this.parameter.setParentNode(null);
        }
        this.parameter = parameter;
        this.setAsParentNodeOf(parameter);
        return this;
    }

    @Override
    public BlockStmt getBody() {
        return this.body;
    }

    @Override
    public CatchClause setBody(BlockStmt body) {
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
    public boolean remove(Node node) {
        if (node == null) {
            return false;
        }
        return super.remove(node);
    }

    @Override
    public CatchClause clone() {
        return (CatchClause)this.accept(new CloneVisitor(), null);
    }

    @Override
    public CatchClauseMetaModel getMetaModel() {
        return JavaParserMetaModel.catchClauseMetaModel;
    }

    @Override
    public boolean replace(Node node, Node replacementNode) {
        if (node == null) {
            return false;
        }
        if (node == this.body) {
            this.setBody((BlockStmt)replacementNode);
            return true;
        }
        if (node == this.parameter) {
            this.setParameter((Parameter)replacementNode);
            return true;
        }
        return super.replace(node, replacementNode);
    }
}

