/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.ast.body;

import com.github.javaparser.TokenRange;
import com.github.javaparser.ast.AllFieldsConstructor;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.BodyDeclaration;
import com.github.javaparser.ast.body.CallableDeclaration;
import com.github.javaparser.ast.expr.AnnotationExpr;
import com.github.javaparser.ast.expr.SimpleName;
import com.github.javaparser.ast.nodeTypes.NodeWithJavadoc;
import com.github.javaparser.ast.nodeTypes.NodeWithMembers;
import com.github.javaparser.ast.nodeTypes.NodeWithSimpleName;
import com.github.javaparser.ast.nodeTypes.modifiers.NodeWithAccessModifiers;
import com.github.javaparser.ast.nodeTypes.modifiers.NodeWithStaticModifier;
import com.github.javaparser.ast.nodeTypes.modifiers.NodeWithStrictfpModifier;
import com.github.javaparser.ast.observer.ObservableProperty;
import com.github.javaparser.ast.visitor.CloneVisitor;
import com.github.javaparser.metamodel.JavaParserMetaModel;
import com.github.javaparser.metamodel.TypeDeclarationMetaModel;
import com.github.javaparser.resolution.declarations.ResolvedReferenceTypeDeclaration;
import com.github.javaparser.utils.Utils;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public abstract class TypeDeclaration<T extends TypeDeclaration<?>>
extends BodyDeclaration<T>
implements NodeWithSimpleName<T>,
NodeWithJavadoc<T>,
NodeWithMembers<T>,
NodeWithAccessModifiers<T>,
NodeWithStaticModifier<T>,
NodeWithStrictfpModifier<T> {
    private SimpleName name;
    private NodeList<Modifier> modifiers;
    private NodeList<BodyDeclaration<?>> members;

    public TypeDeclaration() {
        this(null, new NodeList<Modifier>(), new NodeList<AnnotationExpr>(), new SimpleName(), new NodeList());
    }

    public TypeDeclaration(NodeList<Modifier> modifiers, String name) {
        this(null, modifiers, new NodeList<AnnotationExpr>(), new SimpleName(name), new NodeList());
    }

    @AllFieldsConstructor
    public TypeDeclaration(NodeList<Modifier> modifiers, NodeList<AnnotationExpr> annotations, SimpleName name, NodeList<BodyDeclaration<?>> members) {
        this(null, modifiers, annotations, name, members);
    }

    public TypeDeclaration(TokenRange tokenRange, NodeList<Modifier> modifiers, NodeList<AnnotationExpr> annotations, SimpleName name, NodeList<BodyDeclaration<?>> members) {
        super(tokenRange, annotations);
        this.setModifiers((NodeList)modifiers);
        this.setName(name);
        this.setMembers((NodeList)members);
        this.customInitialization();
    }

    @Override
    public T addMember(BodyDeclaration<?> decl) {
        NodeList<BodyDeclaration<?>> members = this.getMembers();
        members.add(decl);
        return (T)this;
    }

    @Override
    public NodeList<BodyDeclaration<?>> getMembers() {
        return this.members;
    }

    @Override
    public NodeList<Modifier> getModifiers() {
        return this.modifiers;
    }

    @Override
    public T setMembers(NodeList<BodyDeclaration<?>> members) {
        Utils.assertNotNull(members);
        if (members == this.members) {
            return (T)this;
        }
        this.notifyPropertyChange(ObservableProperty.MEMBERS, this.members, members);
        if (this.members != null) {
            this.members.setParentNode(null);
        }
        this.members = members;
        this.setAsParentNodeOf(members);
        return (T)this;
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
    public SimpleName getName() {
        return this.name;
    }

    @Override
    public boolean remove(Node node) {
        int i;
        if (node == null) {
            return false;
        }
        for (i = 0; i < this.members.size(); ++i) {
            if (this.members.get(i) != node) continue;
            this.members.remove(i);
            return true;
        }
        for (i = 0; i < this.modifiers.size(); ++i) {
            if (this.modifiers.get(i) != node) continue;
            this.modifiers.remove(i);
            return true;
        }
        return super.remove(node);
    }

    public boolean isTopLevelType() {
        return this.getParentNode().map(p -> p instanceof CompilationUnit).orElse(false);
    }

    public List<CallableDeclaration<?>> getCallablesWithSignature(CallableDeclaration.Signature signature) {
        return this.getMembers().stream().filter(m -> m instanceof CallableDeclaration).map(m -> (CallableDeclaration)m).filter(m -> m.getSignature().equals(signature)).collect(Collectors.toList());
    }

    public Optional<String> getFullyQualifiedName() {
        if (this.isTopLevelType()) {
            return this.findCompilationUnit().map(cu -> cu.getPackageDeclaration().map(pd -> pd.getNameAsString()).map(pkg -> pkg + "." + this.getNameAsString()).orElse(this.getNameAsString()));
        }
        return this.findAncestor(TypeDeclaration.class).map(td -> td).flatMap(td -> td.getFullyQualifiedName().map(fqn -> fqn + "." + this.getNameAsString()));
    }

    public boolean isNestedType() {
        return this.getParentNode().map(p -> p instanceof TypeDeclaration).orElse(false);
    }

    @Override
    public TypeDeclaration<?> clone() {
        return (TypeDeclaration)this.accept(new CloneVisitor(), null);
    }

    @Override
    public TypeDeclarationMetaModel getMetaModel() {
        return JavaParserMetaModel.typeDeclarationMetaModel;
    }

    @Override
    public boolean replace(Node node, Node replacementNode) {
        int i;
        if (node == null) {
            return false;
        }
        for (i = 0; i < this.members.size(); ++i) {
            if (this.members.get(i) != node) continue;
            this.members.set(i, (BodyDeclaration)replacementNode);
            return true;
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
        return super.replace(node, replacementNode);
    }

    @Override
    public boolean isTypeDeclaration() {
        return true;
    }

    @Override
    public TypeDeclaration asTypeDeclaration() {
        return this;
    }

    @Override
    public void ifTypeDeclaration(Consumer<TypeDeclaration> action) {
        action.accept(this);
    }

    @Override
    public Optional<TypeDeclaration> toTypeDeclaration() {
        return Optional.of(this);
    }

    public abstract ResolvedReferenceTypeDeclaration resolve();
}

