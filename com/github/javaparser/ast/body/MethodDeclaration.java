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
import com.github.javaparser.ast.nodeTypes.NodeWithDeclaration;
import com.github.javaparser.ast.nodeTypes.NodeWithJavadoc;
import com.github.javaparser.ast.nodeTypes.NodeWithOptionalBlockStmt;
import com.github.javaparser.ast.nodeTypes.NodeWithParameters;
import com.github.javaparser.ast.nodeTypes.NodeWithSimpleName;
import com.github.javaparser.ast.nodeTypes.NodeWithThrownExceptions;
import com.github.javaparser.ast.nodeTypes.NodeWithType;
import com.github.javaparser.ast.nodeTypes.NodeWithTypeParameters;
import com.github.javaparser.ast.nodeTypes.modifiers.NodeWithAbstractModifier;
import com.github.javaparser.ast.nodeTypes.modifiers.NodeWithAccessModifiers;
import com.github.javaparser.ast.nodeTypes.modifiers.NodeWithFinalModifier;
import com.github.javaparser.ast.nodeTypes.modifiers.NodeWithStaticModifier;
import com.github.javaparser.ast.nodeTypes.modifiers.NodeWithStrictfpModifier;
import com.github.javaparser.ast.observer.ObservableProperty;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.ast.type.ReferenceType;
import com.github.javaparser.ast.type.Type;
import com.github.javaparser.ast.type.TypeParameter;
import com.github.javaparser.ast.visitor.CloneVisitor;
import com.github.javaparser.ast.visitor.GenericVisitor;
import com.github.javaparser.ast.visitor.VoidVisitor;
import com.github.javaparser.metamodel.JavaParserMetaModel;
import com.github.javaparser.metamodel.MethodDeclarationMetaModel;
import com.github.javaparser.metamodel.OptionalProperty;
import com.github.javaparser.resolution.Resolvable;
import com.github.javaparser.resolution.declarations.ResolvedMethodDeclaration;
import com.github.javaparser.utils.Utils;
import java.util.Optional;
import java.util.function.Consumer;

public class MethodDeclaration
extends CallableDeclaration<MethodDeclaration>
implements NodeWithType<MethodDeclaration, Type>,
NodeWithOptionalBlockStmt<MethodDeclaration>,
NodeWithJavadoc<MethodDeclaration>,
NodeWithDeclaration,
NodeWithSimpleName<MethodDeclaration>,
NodeWithParameters<MethodDeclaration>,
NodeWithThrownExceptions<MethodDeclaration>,
NodeWithTypeParameters<MethodDeclaration>,
NodeWithAccessModifiers<MethodDeclaration>,
NodeWithAbstractModifier<MethodDeclaration>,
NodeWithStaticModifier<MethodDeclaration>,
NodeWithFinalModifier<MethodDeclaration>,
NodeWithStrictfpModifier<MethodDeclaration>,
Resolvable<ResolvedMethodDeclaration> {
    private Type type;
    @OptionalProperty
    private BlockStmt body;

    public MethodDeclaration() {
        this(null, new NodeList<Modifier>(), new NodeList<AnnotationExpr>(), new NodeList<TypeParameter>(), new ClassOrInterfaceType(), new SimpleName(), new NodeList<Parameter>(), new NodeList<ReferenceType>(), new BlockStmt(), null);
    }

    public MethodDeclaration(NodeList<Modifier> modifiers, Type type, String name) {
        this(null, modifiers, new NodeList<AnnotationExpr>(), new NodeList<TypeParameter>(), type, new SimpleName(name), new NodeList<Parameter>(), new NodeList<ReferenceType>(), new BlockStmt(), null);
    }

    public MethodDeclaration(NodeList<Modifier> modifiers, String name, Type type, NodeList<Parameter> parameters) {
        this(null, modifiers, new NodeList<AnnotationExpr>(), new NodeList<TypeParameter>(), type, new SimpleName(name), parameters, new NodeList<ReferenceType>(), new BlockStmt(), null);
    }

    public MethodDeclaration(NodeList<Modifier> modifiers, NodeList<AnnotationExpr> annotations, NodeList<TypeParameter> typeParameters, Type type, SimpleName name, NodeList<Parameter> parameters, NodeList<ReferenceType> thrownExceptions, BlockStmt body) {
        this(null, modifiers, annotations, typeParameters, type, name, parameters, thrownExceptions, body, null);
    }

    @AllFieldsConstructor
    public MethodDeclaration(NodeList<Modifier> modifiers, NodeList<AnnotationExpr> annotations, NodeList<TypeParameter> typeParameters, Type type, SimpleName name, NodeList<Parameter> parameters, NodeList<ReferenceType> thrownExceptions, BlockStmt body, ReceiverParameter receiverParameter) {
        this(null, modifiers, annotations, typeParameters, type, name, parameters, thrownExceptions, body, receiverParameter);
    }

    public MethodDeclaration(TokenRange tokenRange, NodeList<Modifier> modifiers, NodeList<AnnotationExpr> annotations, NodeList<TypeParameter> typeParameters, Type type, SimpleName name, NodeList<Parameter> parameters, NodeList<ReferenceType> thrownExceptions, BlockStmt body, ReceiverParameter receiverParameter) {
        super(tokenRange, modifiers, annotations, typeParameters, name, parameters, thrownExceptions, receiverParameter);
        this.setType(type);
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
    public Optional<BlockStmt> getBody() {
        return Optional.ofNullable(this.body);
    }

    @Override
    public MethodDeclaration setBody(BlockStmt body) {
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
    public Type getType() {
        return this.type;
    }

    @Override
    public MethodDeclaration setType(Type type) {
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
    public MethodDeclaration setModifiers(NodeList<Modifier> modifiers) {
        return (MethodDeclaration)super.setModifiers((NodeList)modifiers);
    }

    @Override
    public MethodDeclaration setName(SimpleName name) {
        return (MethodDeclaration)super.setName(name);
    }

    @Override
    public MethodDeclaration setParameters(NodeList<Parameter> parameters) {
        return (MethodDeclaration)super.setParameters((NodeList)parameters);
    }

    @Override
    public MethodDeclaration setThrownExceptions(NodeList<ReferenceType> thrownExceptions) {
        return (MethodDeclaration)super.setThrownExceptions((NodeList)thrownExceptions);
    }

    @Override
    public MethodDeclaration setTypeParameters(NodeList<TypeParameter> typeParameters) {
        return (MethodDeclaration)super.setTypeParameters((NodeList)typeParameters);
    }

    @Override
    public String getDeclarationAsString(boolean includingModifiers, boolean includingThrows, boolean includingParameterName) {
        StringBuilder sb = new StringBuilder();
        if (includingModifiers) {
            AccessSpecifier accessSpecifier = this.getAccessSpecifier();
            sb.append(accessSpecifier.asString()).append(" ");
            if (this.isStatic()) {
                sb.append("static ");
            }
            if (this.isAbstract()) {
                sb.append("abstract ");
            }
            if (this.isFinal()) {
                sb.append("final ");
            }
            if (this.isNative()) {
                sb.append("native ");
            }
            if (this.isSynchronized()) {
                sb.append("synchronized ");
            }
        }
        sb.append(this.getType().toString(prettyPrinterNoCommentsConfiguration));
        sb.append(" ");
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
            if (!param.isVarArgs()) continue;
            sb.append("...");
        }
        sb.append(")");
        sb.append(this.appendThrowsIfRequested(includingThrows));
        return sb.toString();
    }

    public String toDescriptor() {
        StringBuilder sb = new StringBuilder();
        sb.append("(");
        for (int i = 0; i < this.getParameters().size(); ++i) {
            sb.append(this.getParameter(i).getType().toDescriptor());
        }
        sb.append(")");
        sb.append(this.getType().toDescriptor());
        return sb.toString();
    }

    public boolean isNative() {
        return this.hasModifier(Modifier.Keyword.NATIVE);
    }

    public boolean isSynchronized() {
        return this.hasModifier(Modifier.Keyword.SYNCHRONIZED);
    }

    public boolean isDefault() {
        return this.hasModifier(Modifier.Keyword.DEFAULT);
    }

    public MethodDeclaration setNative(boolean set) {
        return (MethodDeclaration)this.setModifier(Modifier.Keyword.NATIVE, set);
    }

    public MethodDeclaration setSynchronized(boolean set) {
        return (MethodDeclaration)this.setModifier(Modifier.Keyword.SYNCHRONIZED, set);
    }

    public MethodDeclaration setDefault(boolean set) {
        return (MethodDeclaration)this.setModifier(Modifier.Keyword.DEFAULT, set);
    }

    @Override
    public boolean remove(Node node) {
        if (node == null) {
            return false;
        }
        if (this.body != null && node == this.body) {
            this.removeBody();
            return true;
        }
        return super.remove(node);
    }

    @Override
    public MethodDeclaration removeBody() {
        return this.setBody(null);
    }

    @Override
    public MethodDeclaration clone() {
        return (MethodDeclaration)this.accept(new CloneVisitor(), null);
    }

    @Override
    public MethodDeclarationMetaModel getMetaModel() {
        return JavaParserMetaModel.methodDeclarationMetaModel;
    }

    @Override
    public boolean replace(Node node, Node replacementNode) {
        if (node == null) {
            return false;
        }
        if (this.body != null && node == this.body) {
            this.setBody((BlockStmt)replacementNode);
            return true;
        }
        if (node == this.type) {
            this.setType((Type)replacementNode);
            return true;
        }
        return super.replace(node, replacementNode);
    }

    @Override
    public boolean isMethodDeclaration() {
        return true;
    }

    @Override
    public MethodDeclaration asMethodDeclaration() {
        return this;
    }

    @Override
    public void ifMethodDeclaration(Consumer<MethodDeclaration> action) {
        action.accept(this);
    }

    @Override
    public ResolvedMethodDeclaration resolve() {
        return this.getSymbolResolver().resolveDeclaration(this, ResolvedMethodDeclaration.class);
    }

    @Override
    public Optional<MethodDeclaration> toMethodDeclaration() {
        return Optional.of(this);
    }
}

