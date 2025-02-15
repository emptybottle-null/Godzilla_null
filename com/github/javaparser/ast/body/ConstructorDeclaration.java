/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.ast.body;

import com.github.javaparser.TokenRange;
import com.github.javaparser.ast.AccessSpecifier;
import com.github.javaparser.ast.AllFieldsConstructor;
import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.CallableDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.body.ReceiverParameter;
import com.github.javaparser.ast.expr.AnnotationExpr;
import com.github.javaparser.ast.expr.SimpleName;
import com.github.javaparser.ast.nodeTypes.NodeWithBlockStmt;
import com.github.javaparser.ast.nodeTypes.NodeWithJavadoc;
import com.github.javaparser.ast.nodeTypes.NodeWithParameters;
import com.github.javaparser.ast.nodeTypes.NodeWithSimpleName;
import com.github.javaparser.ast.nodeTypes.NodeWithThrownExceptions;
import com.github.javaparser.ast.nodeTypes.NodeWithTypeParameters;
import com.github.javaparser.ast.nodeTypes.modifiers.NodeWithAccessModifiers;
import com.github.javaparser.ast.observer.ObservableProperty;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.type.ReferenceType;
import com.github.javaparser.ast.type.TypeParameter;
import com.github.javaparser.ast.visitor.CloneVisitor;
import com.github.javaparser.ast.visitor.GenericVisitor;
import com.github.javaparser.ast.visitor.VoidVisitor;
import com.github.javaparser.metamodel.ConstructorDeclarationMetaModel;
import com.github.javaparser.metamodel.JavaParserMetaModel;
import com.github.javaparser.resolution.Resolvable;
import com.github.javaparser.resolution.declarations.ResolvedConstructorDeclaration;
import com.github.javaparser.utils.Utils;
import java.util.Optional;
import java.util.function.Consumer;

public class ConstructorDeclaration
extends CallableDeclaration<ConstructorDeclaration>
implements NodeWithBlockStmt<ConstructorDeclaration>,
NodeWithAccessModifiers<ConstructorDeclaration>,
NodeWithJavadoc<ConstructorDeclaration>,
NodeWithSimpleName<ConstructorDeclaration>,
NodeWithParameters<ConstructorDeclaration>,
NodeWithThrownExceptions<ConstructorDeclaration>,
NodeWithTypeParameters<ConstructorDeclaration>,
Resolvable<ResolvedConstructorDeclaration> {
    private BlockStmt body;

    public ConstructorDeclaration() {
        this(null, new NodeList<Modifier>(), new NodeList<AnnotationExpr>(), new NodeList<TypeParameter>(), new SimpleName(), new NodeList<Parameter>(), new NodeList<ReferenceType>(), new BlockStmt(), null);
    }

    public ConstructorDeclaration(String name) {
        this(null, new NodeList((Node[])new Modifier[]{new Modifier()}), new NodeList<AnnotationExpr>(), new NodeList<TypeParameter>(), new SimpleName(name), new NodeList<Parameter>(), new NodeList<ReferenceType>(), new BlockStmt(), null);
    }

    public ConstructorDeclaration(NodeList<Modifier> modifiers, String name) {
        this(null, modifiers, new NodeList<AnnotationExpr>(), new NodeList<TypeParameter>(), new SimpleName(name), new NodeList<Parameter>(), new NodeList<ReferenceType>(), new BlockStmt(), null);
    }

    public ConstructorDeclaration(NodeList<Modifier> modifiers, NodeList<AnnotationExpr> annotations, NodeList<TypeParameter> typeParameters, SimpleName name, NodeList<Parameter> parameters, NodeList<ReferenceType> thrownExceptions, BlockStmt body) {
        this(null, modifiers, annotations, typeParameters, name, parameters, thrownExceptions, body, null);
    }

    @AllFieldsConstructor
    public ConstructorDeclaration(NodeList<Modifier> modifiers, NodeList<AnnotationExpr> annotations, NodeList<TypeParameter> typeParameters, SimpleName name, NodeList<Parameter> parameters, NodeList<ReferenceType> thrownExceptions, BlockStmt body, ReceiverParameter receiverParameter) {
        this(null, modifiers, annotations, typeParameters, name, parameters, thrownExceptions, body, receiverParameter);
    }

    public ConstructorDeclaration(TokenRange tokenRange, NodeList<Modifier> modifiers, NodeList<AnnotationExpr> annotations, NodeList<TypeParameter> typeParameters, SimpleName name, NodeList<Parameter> parameters, NodeList<ReferenceType> thrownExceptions, BlockStmt body, ReceiverParameter receiverParameter) {
        super(tokenRange, modifiers, annotations, typeParameters, name, parameters, thrownExceptions, receiverParameter);
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

    @Override
    public BlockStmt getBody() {
        return this.body;
    }

    @Override
    public ConstructorDeclaration setBody(BlockStmt body) {
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
    public ConstructorDeclaration setModifiers(NodeList<Modifier> modifiers) {
        return (ConstructorDeclaration)super.setModifiers((NodeList)modifiers);
    }

    @Override
    public ConstructorDeclaration setName(SimpleName name) {
        return (ConstructorDeclaration)super.setName(name);
    }

    @Override
    public ConstructorDeclaration setParameters(NodeList<Parameter> parameters) {
        return (ConstructorDeclaration)super.setParameters((NodeList)parameters);
    }

    @Override
    public ConstructorDeclaration setThrownExceptions(NodeList<ReferenceType> thrownExceptions) {
        return (ConstructorDeclaration)super.setThrownExceptions((NodeList)thrownExceptions);
    }

    @Override
    public ConstructorDeclaration setTypeParameters(NodeList<TypeParameter> typeParameters) {
        return (ConstructorDeclaration)super.setTypeParameters((NodeList)typeParameters);
    }

    @Override
    public String getDeclarationAsString(boolean includingModifiers, boolean includingThrows, boolean includingParameterName) {
        StringBuilder sb = new StringBuilder();
        if (includingModifiers) {
            AccessSpecifier accessSpecifier = this.getAccessSpecifier();
            sb.append(accessSpecifier.asString()).append(" ");
        }
        sb.append(this.getName());
        sb.append("(");
        boolean firstParam = true;
        for (Parameter param : this.getParameters()) {
            if (firstParam) {
                firstParam = false;
            } else {
                sb.append(", ");
            }
            if (includingParameterName) {
                sb.append(param.toString(prettyPrinterNoCommentsConfiguration));
                continue;
            }
            sb.append(param.getType().toString(prettyPrinterNoCommentsConfiguration));
        }
        sb.append(")");
        sb.append(this.appendThrowsIfRequested(includingThrows));
        return sb.toString();
    }

    @Override
    public boolean remove(Node node) {
        if (node == null) {
            return false;
        }
        return super.remove(node);
    }

    @Override
    public ConstructorDeclaration clone() {
        return (ConstructorDeclaration)this.accept(new CloneVisitor(), null);
    }

    @Override
    public ConstructorDeclarationMetaModel getMetaModel() {
        return JavaParserMetaModel.constructorDeclarationMetaModel;
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
        return super.replace(node, replacementNode);
    }

    @Override
    public boolean isConstructorDeclaration() {
        return true;
    }

    @Override
    public ConstructorDeclaration asConstructorDeclaration() {
        return this;
    }

    @Override
    public void ifConstructorDeclaration(Consumer<ConstructorDeclaration> action) {
        action.accept(this);
    }

    @Override
    public ResolvedConstructorDeclaration resolve() {
        return this.getSymbolResolver().resolveDeclaration(this, ResolvedConstructorDeclaration.class);
    }

    @Override
    public Optional<ConstructorDeclaration> toConstructorDeclaration() {
        return Optional.of(this);
    }
}

