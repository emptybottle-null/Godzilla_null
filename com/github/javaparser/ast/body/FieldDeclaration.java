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
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.EnumDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.expr.AnnotationExpr;
import com.github.javaparser.ast.expr.AssignExpr;
import com.github.javaparser.ast.expr.NameExpr;
import com.github.javaparser.ast.nodeTypes.NodeWithJavadoc;
import com.github.javaparser.ast.nodeTypes.NodeWithVariables;
import com.github.javaparser.ast.nodeTypes.modifiers.NodeWithAccessModifiers;
import com.github.javaparser.ast.nodeTypes.modifiers.NodeWithFinalModifier;
import com.github.javaparser.ast.nodeTypes.modifiers.NodeWithStaticModifier;
import com.github.javaparser.ast.observer.ObservableProperty;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.stmt.ReturnStmt;
import com.github.javaparser.ast.type.Type;
import com.github.javaparser.ast.type.VoidType;
import com.github.javaparser.ast.visitor.CloneVisitor;
import com.github.javaparser.ast.visitor.GenericVisitor;
import com.github.javaparser.ast.visitor.VoidVisitor;
import com.github.javaparser.metamodel.FieldDeclarationMetaModel;
import com.github.javaparser.metamodel.JavaParserMetaModel;
import com.github.javaparser.metamodel.NonEmptyProperty;
import com.github.javaparser.resolution.Resolvable;
import com.github.javaparser.resolution.declarations.ResolvedFieldDeclaration;
import com.github.javaparser.utils.Utils;
import java.util.Optional;
import java.util.function.Consumer;

public class FieldDeclaration
extends BodyDeclaration<FieldDeclaration>
implements NodeWithJavadoc<FieldDeclaration>,
NodeWithVariables<FieldDeclaration>,
NodeWithAccessModifiers<FieldDeclaration>,
NodeWithStaticModifier<FieldDeclaration>,
NodeWithFinalModifier<FieldDeclaration>,
Resolvable<ResolvedFieldDeclaration> {
    private NodeList<Modifier> modifiers;
    @NonEmptyProperty
    private NodeList<VariableDeclarator> variables;

    public FieldDeclaration() {
        this(null, new NodeList<Modifier>(), new NodeList<AnnotationExpr>(), new NodeList<VariableDeclarator>());
    }

    public FieldDeclaration(NodeList<Modifier> modifiers, VariableDeclarator variable) {
        this(null, modifiers, new NodeList<AnnotationExpr>(), NodeList.nodeList((Node[])new VariableDeclarator[]{variable}));
    }

    public FieldDeclaration(NodeList<Modifier> modifiers, NodeList<VariableDeclarator> variables) {
        this(null, modifiers, new NodeList<AnnotationExpr>(), variables);
    }

    @AllFieldsConstructor
    public FieldDeclaration(NodeList<Modifier> modifiers, NodeList<AnnotationExpr> annotations, NodeList<VariableDeclarator> variables) {
        this(null, modifiers, annotations, variables);
    }

    public FieldDeclaration(TokenRange tokenRange, NodeList<Modifier> modifiers, NodeList<AnnotationExpr> annotations, NodeList<VariableDeclarator> variables) {
        super(tokenRange, annotations);
        this.setModifiers((NodeList)modifiers);
        this.setVariables((NodeList)variables);
        this.customInitialization();
    }

    public FieldDeclaration(NodeList<Modifier> modifiers, Type type, String name) {
        this(Utils.assertNotNull(modifiers), new VariableDeclarator(type, Utils.assertNotNull(name)));
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
    public NodeList<Modifier> getModifiers() {
        return this.modifiers;
    }

    @Override
    public NodeList<VariableDeclarator> getVariables() {
        return this.variables;
    }

    @Override
    public FieldDeclaration setModifiers(NodeList<Modifier> modifiers) {
        Utils.assertNotNull(modifiers);
        if (modifiers == this.modifiers) {
            return this;
        }
        this.notifyPropertyChange(ObservableProperty.MODIFIERS, this.modifiers, modifiers);
        if (this.modifiers != null) {
            this.modifiers.setParentNode(null);
        }
        this.modifiers = modifiers;
        this.setAsParentNodeOf(modifiers);
        return this;
    }

    @Override
    public FieldDeclaration setVariables(NodeList<VariableDeclarator> variables) {
        Utils.assertNotNull(variables);
        if (variables == this.variables) {
            return this;
        }
        this.notifyPropertyChange(ObservableProperty.VARIABLES, this.variables, variables);
        if (this.variables != null) {
            this.variables.setParentNode(null);
        }
        this.variables = variables;
        this.setAsParentNodeOf(variables);
        return this;
    }

    public MethodDeclaration createGetter() {
        if (this.getVariables().size() != 1) {
            throw new IllegalStateException("You can use this only when the field declares only 1 variable name");
        }
        Optional<ClassOrInterfaceDeclaration> parentClass = this.findAncestor(ClassOrInterfaceDeclaration.class);
        Optional<EnumDeclaration> parentEnum = this.findAncestor(EnumDeclaration.class);
        if (!parentClass.isPresent() && !parentEnum.isPresent() || parentClass.isPresent() && parentClass.get().isInterface()) {
            throw new IllegalStateException("You can use this only when the field is attached to a class or an enum");
        }
        VariableDeclarator variable = this.getVariable(0);
        String fieldName = variable.getNameAsString();
        String fieldNameUpper = fieldName.toUpperCase().substring(0, 1) + fieldName.substring(1, fieldName.length());
        MethodDeclaration getter = parentClass.map(clazz -> clazz.addMethod("get" + fieldNameUpper, Modifier.Keyword.PUBLIC)).orElseGet(() -> ((EnumDeclaration)parentEnum.get()).addMethod("get" + fieldNameUpper, Modifier.Keyword.PUBLIC));
        getter.setType(variable.getType());
        BlockStmt blockStmt = new BlockStmt();
        getter.setBody(blockStmt);
        blockStmt.addStatement(new ReturnStmt(fieldName));
        return getter;
    }

    public MethodDeclaration createSetter() {
        if (this.getVariables().size() != 1) {
            throw new IllegalStateException("You can use this only when the field declares only 1 variable name");
        }
        Optional<ClassOrInterfaceDeclaration> parentClass = this.findAncestor(ClassOrInterfaceDeclaration.class);
        Optional<EnumDeclaration> parentEnum = this.findAncestor(EnumDeclaration.class);
        if (!parentClass.isPresent() && !parentEnum.isPresent() || parentClass.isPresent() && parentClass.get().isInterface()) {
            throw new IllegalStateException("You can use this only when the field is attached to a class or an enum");
        }
        VariableDeclarator variable = this.getVariable(0);
        String fieldName = variable.getNameAsString();
        String fieldNameUpper = fieldName.toUpperCase().substring(0, 1) + fieldName.substring(1, fieldName.length());
        MethodDeclaration setter = parentClass.map(clazz -> clazz.addMethod("set" + fieldNameUpper, Modifier.Keyword.PUBLIC)).orElseGet(() -> ((EnumDeclaration)parentEnum.get()).addMethod("set" + fieldNameUpper, Modifier.Keyword.PUBLIC));
        setter.setType(new VoidType());
        setter.getParameters().add(new Parameter(variable.getType(), fieldName));
        BlockStmt blockStmt2 = new BlockStmt();
        setter.setBody(blockStmt2);
        blockStmt2.addStatement(new AssignExpr(new NameExpr("this." + fieldName), new NameExpr(fieldName), AssignExpr.Operator.ASSIGN));
        return setter;
    }

    public boolean isTransient() {
        return this.hasModifier(Modifier.Keyword.TRANSIENT);
    }

    public boolean isVolatile() {
        return this.hasModifier(Modifier.Keyword.VOLATILE);
    }

    public FieldDeclaration setTransient(boolean set) {
        return (FieldDeclaration)this.setModifier(Modifier.Keyword.TRANSIENT, set);
    }

    public FieldDeclaration setVolatile(boolean set) {
        return (FieldDeclaration)this.setModifier(Modifier.Keyword.VOLATILE, set);
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
        for (i = 0; i < this.variables.size(); ++i) {
            if (this.variables.get(i) != node) continue;
            this.variables.remove(i);
            return true;
        }
        return super.remove(node);
    }

    @Override
    public FieldDeclaration clone() {
        return (FieldDeclaration)this.accept(new CloneVisitor(), null);
    }

    @Override
    public FieldDeclarationMetaModel getMetaModel() {
        return JavaParserMetaModel.fieldDeclarationMetaModel;
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
        for (i = 0; i < this.variables.size(); ++i) {
            if (this.variables.get(i) != node) continue;
            this.variables.set(i, (VariableDeclarator)replacementNode);
            return true;
        }
        return super.replace(node, replacementNode);
    }

    @Override
    public boolean isFieldDeclaration() {
        return true;
    }

    @Override
    public FieldDeclaration asFieldDeclaration() {
        return this;
    }

    @Override
    public void ifFieldDeclaration(Consumer<FieldDeclaration> action) {
        action.accept(this);
    }

    @Override
    public ResolvedFieldDeclaration resolve() {
        return this.getSymbolResolver().resolveDeclaration(this, ResolvedFieldDeclaration.class);
    }

    @Override
    public Optional<FieldDeclaration> toFieldDeclaration() {
        return Optional.of(this);
    }
}

