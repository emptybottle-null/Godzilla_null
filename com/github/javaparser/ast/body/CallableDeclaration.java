/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.ast.body;

import com.github.javaparser.TokenRange;
import com.github.javaparser.ast.AllFieldsConstructor;
import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.BodyDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.body.ReceiverParameter;
import com.github.javaparser.ast.expr.AnnotationExpr;
import com.github.javaparser.ast.expr.SimpleName;
import com.github.javaparser.ast.nodeTypes.NodeWithAnnotations;
import com.github.javaparser.ast.nodeTypes.NodeWithDeclaration;
import com.github.javaparser.ast.nodeTypes.NodeWithJavadoc;
import com.github.javaparser.ast.nodeTypes.NodeWithParameters;
import com.github.javaparser.ast.nodeTypes.NodeWithSimpleName;
import com.github.javaparser.ast.nodeTypes.NodeWithThrownExceptions;
import com.github.javaparser.ast.nodeTypes.NodeWithTypeArguments;
import com.github.javaparser.ast.nodeTypes.NodeWithTypeParameters;
import com.github.javaparser.ast.nodeTypes.modifiers.NodeWithAbstractModifier;
import com.github.javaparser.ast.nodeTypes.modifiers.NodeWithAccessModifiers;
import com.github.javaparser.ast.nodeTypes.modifiers.NodeWithFinalModifier;
import com.github.javaparser.ast.nodeTypes.modifiers.NodeWithStaticModifier;
import com.github.javaparser.ast.nodeTypes.modifiers.NodeWithStrictfpModifier;
import com.github.javaparser.ast.observer.ObservableProperty;
import com.github.javaparser.ast.type.ArrayType;
import com.github.javaparser.ast.type.ReferenceType;
import com.github.javaparser.ast.type.Type;
import com.github.javaparser.ast.type.TypeParameter;
import com.github.javaparser.ast.visitor.CloneVisitor;
import com.github.javaparser.metamodel.CallableDeclarationMetaModel;
import com.github.javaparser.metamodel.JavaParserMetaModel;
import com.github.javaparser.metamodel.OptionalProperty;
import com.github.javaparser.utils.Utils;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public abstract class CallableDeclaration<T extends CallableDeclaration<?>>
extends BodyDeclaration<T>
implements NodeWithAccessModifiers<T>,
NodeWithDeclaration,
NodeWithSimpleName<T>,
NodeWithParameters<T>,
NodeWithThrownExceptions<T>,
NodeWithTypeParameters<T>,
NodeWithJavadoc<T>,
NodeWithAbstractModifier<T>,
NodeWithStaticModifier<T>,
NodeWithFinalModifier<T>,
NodeWithStrictfpModifier<T> {
    private NodeList<Modifier> modifiers;
    private NodeList<TypeParameter> typeParameters;
    private SimpleName name;
    private NodeList<Parameter> parameters;
    private NodeList<ReferenceType> thrownExceptions;
    @OptionalProperty
    private ReceiverParameter receiverParameter;

    @AllFieldsConstructor
    CallableDeclaration(NodeList<Modifier> modifiers, NodeList<AnnotationExpr> annotations, NodeList<TypeParameter> typeParameters, SimpleName name, NodeList<Parameter> parameters, NodeList<ReferenceType> thrownExceptions, ReceiverParameter receiverParameter) {
        this(null, modifiers, annotations, typeParameters, name, parameters, thrownExceptions, receiverParameter);
    }

    public CallableDeclaration(TokenRange tokenRange, NodeList<Modifier> modifiers, NodeList<AnnotationExpr> annotations, NodeList<TypeParameter> typeParameters, SimpleName name, NodeList<Parameter> parameters, NodeList<ReferenceType> thrownExceptions, ReceiverParameter receiverParameter) {
        super(tokenRange, annotations);
        this.setModifiers((NodeList)modifiers);
        this.setTypeParameters((NodeList)typeParameters);
        this.setName(name);
        this.setParameters((NodeList)parameters);
        this.setThrownExceptions((NodeList)thrownExceptions);
        this.setReceiverParameter(receiverParameter);
        this.customInitialization();
    }

    @Override
    public NodeList<Modifier> getModifiers() {
        return this.modifiers;
    }

    @Override
    public T setModifiers(NodeList<Modifier> modifiers) {
        Utils.assertNotNull(modifiers);
        if (modifiers == this.modifiers) {
            return (T)this;
        }
        this.notifyPropertyChange(ObservableProperty.MODIFIERS, this.modifiers, modifiers);
        if (this.modifiers != null) {
            this.modifiers.setParentNode(null);
        }
        this.modifiers = modifiers;
        this.setAsParentNodeOf(modifiers);
        return (T)this;
    }

    @Override
    public SimpleName getName() {
        return this.name;
    }

    @Override
    public T setName(SimpleName name) {
        Utils.assertNotNull(name);
        if (name == this.name) {
            return (T)this;
        }
        this.notifyPropertyChange(ObservableProperty.NAME, this.name, name);
        if (this.name != null) {
            this.name.setParentNode(null);
        }
        this.name = name;
        this.setAsParentNodeOf(name);
        return (T)this;
    }

    @Override
    public NodeList<Parameter> getParameters() {
        return this.parameters;
    }

    @Override
    public T setParameters(NodeList<Parameter> parameters) {
        Utils.assertNotNull(parameters);
        if (parameters == this.parameters) {
            return (T)this;
        }
        this.notifyPropertyChange(ObservableProperty.PARAMETERS, this.parameters, parameters);
        if (this.parameters != null) {
            this.parameters.setParentNode(null);
        }
        this.parameters = parameters;
        this.setAsParentNodeOf(parameters);
        return (T)this;
    }

    @Override
    public NodeList<ReferenceType> getThrownExceptions() {
        return this.thrownExceptions;
    }

    @Override
    public T setThrownExceptions(NodeList<ReferenceType> thrownExceptions) {
        Utils.assertNotNull(thrownExceptions);
        if (thrownExceptions == this.thrownExceptions) {
            return (T)this;
        }
        this.notifyPropertyChange(ObservableProperty.THROWN_EXCEPTIONS, this.thrownExceptions, thrownExceptions);
        if (this.thrownExceptions != null) {
            this.thrownExceptions.setParentNode(null);
        }
        this.thrownExceptions = thrownExceptions;
        this.setAsParentNodeOf(thrownExceptions);
        return (T)this;
    }

    @Override
    public NodeList<TypeParameter> getTypeParameters() {
        return this.typeParameters;
    }

    @Override
    public T setTypeParameters(NodeList<TypeParameter> typeParameters) {
        Utils.assertNotNull(typeParameters);
        if (typeParameters == this.typeParameters) {
            return (T)this;
        }
        this.notifyPropertyChange(ObservableProperty.TYPE_PARAMETERS, this.typeParameters, typeParameters);
        if (this.typeParameters != null) {
            this.typeParameters.setParentNode(null);
        }
        this.typeParameters = typeParameters;
        this.setAsParentNodeOf(typeParameters);
        return (T)this;
    }

    protected String appendThrowsIfRequested(boolean includingThrows) {
        StringBuilder sb = new StringBuilder();
        if (includingThrows) {
            boolean firstThrow = true;
            for (ReferenceType thr : this.getThrownExceptions()) {
                if (firstThrow) {
                    firstThrow = false;
                    sb.append(" throws ");
                } else {
                    sb.append(", ");
                }
                sb.append(thr.toString(prettyPrinterNoCommentsConfiguration));
            }
        }
        return sb.toString();
    }

    @Override
    public boolean remove(Node node) {
        int i;
        if (node == null) {
            return false;
        }
        for (i = 0; i < this.modifiers.size(); ++i) {
            if (this.modifiers.get(i) != node) continue;
            this.modifiers.remove(i);
            return true;
        }
        for (i = 0; i < this.parameters.size(); ++i) {
            if (this.parameters.get(i) != node) continue;
            this.parameters.remove(i);
            return true;
        }
        if (this.receiverParameter != null && node == this.receiverParameter) {
            this.removeReceiverParameter();
            return true;
        }
        for (i = 0; i < this.thrownExceptions.size(); ++i) {
            if (this.thrownExceptions.get(i) != node) continue;
            this.thrownExceptions.remove(i);
            return true;
        }
        for (i = 0; i < this.typeParameters.size(); ++i) {
            if (this.typeParameters.get(i) != node) continue;
            this.typeParameters.remove(i);
            return true;
        }
        return super.remove(node);
    }

    public Signature getSignature() {
        return new Signature(this.getName().getIdentifier(), this.getParameters().stream().map(this::getTypeWithVarargsAsArray).map(this::stripGenerics).map(this::stripAnnotations).collect(Collectors.toList()));
    }

    private Type stripAnnotations(Type type) {
        if (type instanceof NodeWithAnnotations) {
            ((NodeWithAnnotations)((Object)type)).setAnnotations(new NodeList<AnnotationExpr>());
        }
        return type;
    }

    private Type stripGenerics(Type type) {
        if (type instanceof NodeWithTypeArguments) {
            ((NodeWithTypeArguments)((Object)type)).setTypeArguments((NodeList<Type>)null);
        }
        return type;
    }

    private Type getTypeWithVarargsAsArray(Parameter p) {
        Type t = p.getType().clone();
        if (p.isVarArgs()) {
            t = new ArrayType(t, new AnnotationExpr[0]);
        }
        return t;
    }

    @Override
    public CallableDeclaration<?> clone() {
        return (CallableDeclaration)this.accept(new CloneVisitor(), null);
    }

    @Override
    public CallableDeclarationMetaModel getMetaModel() {
        return JavaParserMetaModel.callableDeclarationMetaModel;
    }

    @Override
    public boolean replace(Node node, Node replacementNode) {
        int i;
        if (node == null) {
            return false;
        }
        for (i = 0; i < this.modifiers.size(); ++i) {
            if (this.modifiers.get(i) != node) continue;
            this.modifiers.set(i, (Modifier)replacementNode);
            return true;
        }
        if (node == this.name) {
            this.setName((SimpleName)replacementNode);
            return true;
        }
        for (i = 0; i < this.parameters.size(); ++i) {
            if (this.parameters.get(i) != node) continue;
            this.parameters.set(i, (Parameter)replacementNode);
            return true;
        }
        if (this.receiverParameter != null && node == this.receiverParameter) {
            this.setReceiverParameter((ReceiverParameter)replacementNode);
            return true;
        }
        for (i = 0; i < this.thrownExceptions.size(); ++i) {
            if (this.thrownExceptions.get(i) != node) continue;
            this.thrownExceptions.set(i, (ReferenceType)replacementNode);
            return true;
        }
        for (i = 0; i < this.typeParameters.size(); ++i) {
            if (this.typeParameters.get(i) != node) continue;
            this.typeParameters.set(i, (TypeParameter)replacementNode);
            return true;
        }
        return super.replace(node, replacementNode);
    }

    @Override
    public boolean isCallableDeclaration() {
        return true;
    }

    @Override
    public CallableDeclaration asCallableDeclaration() {
        return this;
    }

    @Override
    public void ifCallableDeclaration(Consumer<CallableDeclaration> action) {
        action.accept(this);
    }

    public Optional<ReceiverParameter> getReceiverParameter() {
        return Optional.ofNullable(this.receiverParameter);
    }

    public T setReceiverParameter(ReceiverParameter receiverParameter) {
        if (receiverParameter == this.receiverParameter) {
            return (T)this;
        }
        this.notifyPropertyChange(ObservableProperty.RECEIVER_PARAMETER, this.receiverParameter, receiverParameter);
        if (this.receiverParameter != null) {
            this.receiverParameter.setParentNode(null);
        }
        this.receiverParameter = receiverParameter;
        this.setAsParentNodeOf(receiverParameter);
        return (T)this;
    }

    public CallableDeclaration removeReceiverParameter() {
        return this.setReceiverParameter(null);
    }

    @Override
    public Optional<CallableDeclaration> toCallableDeclaration() {
        return Optional.of(this);
    }

    public static class Signature {
        private final String name;
        private final List<Type> parameterTypes;

        private Signature(String name, List<Type> parameterTypes) {
            this.name = name;
            this.parameterTypes = parameterTypes;
        }

        public String getName() {
            return this.name;
        }

        public List<Type> getParameterTypes() {
            return this.parameterTypes;
        }

        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || this.getClass() != o.getClass()) {
                return false;
            }
            Signature signature = (Signature)o;
            if (!this.name.equals(signature.name)) {
                return false;
            }
            return this.parameterTypes.equals(signature.parameterTypes);
        }

        public int hashCode() {
            int result = this.name.hashCode();
            result = 31 * result + this.parameterTypes.hashCode();
            return result;
        }

        public String asString() {
            return this.parameterTypes.stream().map(Type::asString).collect(Collectors.joining(", ", this.name + "(", ")"));
        }

        public String toString() {
            return this.asString();
        }
    }
}

