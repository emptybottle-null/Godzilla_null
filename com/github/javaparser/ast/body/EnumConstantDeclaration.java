/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.ast.body;

import com.github.javaparser.TokenRange;
import com.github.javaparser.ast.AllFieldsConstructor;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.BodyDeclaration;
import com.github.javaparser.ast.expr.AnnotationExpr;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.SimpleName;
import com.github.javaparser.ast.nodeTypes.NodeWithArguments;
import com.github.javaparser.ast.nodeTypes.NodeWithJavadoc;
import com.github.javaparser.ast.nodeTypes.NodeWithSimpleName;
import com.github.javaparser.ast.observer.ObservableProperty;
import com.github.javaparser.ast.visitor.CloneVisitor;
import com.github.javaparser.ast.visitor.GenericVisitor;
import com.github.javaparser.ast.visitor.VoidVisitor;
import com.github.javaparser.metamodel.EnumConstantDeclarationMetaModel;
import com.github.javaparser.metamodel.JavaParserMetaModel;
import com.github.javaparser.resolution.Resolvable;
import com.github.javaparser.resolution.declarations.ResolvedEnumConstantDeclaration;
import com.github.javaparser.utils.Utils;
import java.util.Optional;
import java.util.function.Consumer;

public class EnumConstantDeclaration
extends BodyDeclaration<EnumConstantDeclaration>
implements NodeWithJavadoc<EnumConstantDeclaration>,
NodeWithSimpleName<EnumConstantDeclaration>,
NodeWithArguments<EnumConstantDeclaration>,
Resolvable<ResolvedEnumConstantDeclaration> {
    private SimpleName name;
    private NodeList<Expression> arguments;
    private NodeList<BodyDeclaration<?>> classBody;

    public EnumConstantDeclaration() {
        this(null, new NodeList<AnnotationExpr>(), new SimpleName(), new NodeList<Expression>(), new NodeList());
    }

    public EnumConstantDeclaration(String name) {
        this(null, new NodeList<AnnotationExpr>(), new SimpleName(name), new NodeList<Expression>(), new NodeList());
    }

    @AllFieldsConstructor
    public EnumConstantDeclaration(NodeList<AnnotationExpr> annotations, SimpleName name, NodeList<Expression> arguments, NodeList<BodyDeclaration<?>> classBody) {
        this(null, annotations, name, arguments, classBody);
    }

    public EnumConstantDeclaration(TokenRange tokenRange, NodeList<AnnotationExpr> annotations, SimpleName name, NodeList<Expression> arguments, NodeList<BodyDeclaration<?>> classBody) {
        super(tokenRange, annotations);
        this.setName(name);
        this.setArguments((NodeList)arguments);
        this.setClassBody(classBody);
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
    public NodeList<Expression> getArguments() {
        return this.arguments;
    }

    public NodeList<BodyDeclaration<?>> getClassBody() {
        return this.classBody;
    }

    @Override
    public SimpleName getName() {
        return this.name;
    }

    @Override
    public EnumConstantDeclaration setArguments(NodeList<Expression> arguments) {
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

    public EnumConstantDeclaration setClassBody(NodeList<BodyDeclaration<?>> classBody) {
        Utils.assertNotNull(classBody);
        if (classBody == this.classBody) {
            return this;
        }
        this.notifyPropertyChange(ObservableProperty.CLASS_BODY, this.classBody, classBody);
        if (this.classBody != null) {
            this.classBody.setParentNode(null);
        }
        this.classBody = classBody;
        this.setAsParentNodeOf(classBody);
        return this;
    }

    @Override
    public EnumConstantDeclaration setName(SimpleName name) {
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
        int i;
        if (node == null) {
            return false;
        }
        for (i = 0; i < this.arguments.size(); ++i) {
            if (this.arguments.get(i) != node) continue;
            this.arguments.remove(i);
            return true;
        }
        for (i = 0; i < this.classBody.size(); ++i) {
            if (this.classBody.get(i) != node) continue;
            this.classBody.remove(i);
            return true;
        }
        return super.remove(node);
    }

    @Override
    public EnumConstantDeclaration clone() {
        return (EnumConstantDeclaration)this.accept(new CloneVisitor(), null);
    }

    @Override
    public EnumConstantDeclarationMetaModel getMetaModel() {
        return JavaParserMetaModel.enumConstantDeclarationMetaModel;
    }

    @Override
    public boolean replace(Node node, Node replacementNode) {
        int i;
        if (node == null) {
            return false;
        }
        for (i = 0; i < this.arguments.size(); ++i) {
            if (this.arguments.get(i) != node) continue;
            this.arguments.set(i, (Expression)replacementNode);
            return true;
        }
        for (i = 0; i < this.classBody.size(); ++i) {
            if (this.classBody.get(i) != node) continue;
            this.classBody.set(i, (BodyDeclaration)replacementNode);
            return true;
        }
        if (node == this.name) {
            this.setName((SimpleName)replacementNode);
            return true;
        }
        return super.replace(node, replacementNode);
    }

    @Override
    public boolean isEnumConstantDeclaration() {
        return true;
    }

    @Override
    public EnumConstantDeclaration asEnumConstantDeclaration() {
        return this;
    }

    @Override
    public void ifEnumConstantDeclaration(Consumer<EnumConstantDeclaration> action) {
        action.accept(this);
    }

    @Override
    public ResolvedEnumConstantDeclaration resolve() {
        return this.getSymbolResolver().resolveDeclaration(this, ResolvedEnumConstantDeclaration.class);
    }

    @Override
    public Optional<EnumConstantDeclaration> toEnumConstantDeclaration() {
        return Optional.of(this);
    }
}

