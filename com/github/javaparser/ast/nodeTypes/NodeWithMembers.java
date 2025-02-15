/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.ast.nodeTypes;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.BodyDeclaration;
import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.InitializerDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.nodeTypes.NodeWithSimpleName;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.type.Type;
import com.github.javaparser.ast.type.VoidType;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public interface NodeWithMembers<N extends Node>
extends NodeWithSimpleName<N> {
    public NodeList<BodyDeclaration<?>> getMembers();

    public void tryAddImportToParentCompilationUnit(Class<?> var1);

    default public BodyDeclaration<?> getMember(int i) {
        return (BodyDeclaration)this.getMembers().get(i);
    }

    default public N setMember(int i, BodyDeclaration<?> member) {
        this.getMembers().set(i, member);
        return (N)((Node)((Object)this));
    }

    default public N addMember(BodyDeclaration<?> member) {
        this.getMembers().add(member);
        return (N)((Node)((Object)this));
    }

    public N setMembers(NodeList<BodyDeclaration<?>> var1);

    default public FieldDeclaration addField(Class<?> typeClass, String name, Modifier.Keyword ... modifiers) {
        this.tryAddImportToParentCompilationUnit(typeClass);
        return this.addField(typeClass.getSimpleName(), name, modifiers);
    }

    default public FieldDeclaration addField(String type, String name, Modifier.Keyword ... modifiers) {
        return this.addField(StaticJavaParser.parseType(type), name, modifiers);
    }

    default public FieldDeclaration addField(Type type, String name, Modifier.Keyword ... modifiers) {
        FieldDeclaration fieldDeclaration = new FieldDeclaration();
        VariableDeclarator variable = new VariableDeclarator(type, name);
        fieldDeclaration.getVariables().add(variable);
        fieldDeclaration.setModifiers((NodeList)Modifier.createModifierList(modifiers));
        this.getMembers().add(fieldDeclaration);
        return fieldDeclaration;
    }

    default public FieldDeclaration addFieldWithInitializer(Class<?> typeClass, String name, Expression initializer, Modifier.Keyword ... modifiers) {
        this.tryAddImportToParentCompilationUnit(typeClass);
        return this.addFieldWithInitializer(typeClass.getSimpleName(), name, initializer, modifiers);
    }

    default public FieldDeclaration addFieldWithInitializer(String type, String name, Expression initializer, Modifier.Keyword ... modifiers) {
        return this.addFieldWithInitializer(StaticJavaParser.parseType(type), name, initializer, modifiers);
    }

    default public FieldDeclaration addFieldWithInitializer(Type type, String name, Expression initializer, Modifier.Keyword ... modifiers) {
        FieldDeclaration declaration = this.addField(type, name, modifiers);
        declaration.getVariables().iterator().next().setInitializer(initializer);
        return declaration;
    }

    default public FieldDeclaration addPrivateField(Class<?> typeClass, String name) {
        return this.addField(typeClass, name, Modifier.Keyword.PRIVATE);
    }

    default public FieldDeclaration addPrivateField(String type, String name) {
        return this.addField(type, name, Modifier.Keyword.PRIVATE);
    }

    default public FieldDeclaration addPrivateField(Type type, String name) {
        return this.addField(type, name, Modifier.Keyword.PRIVATE);
    }

    default public FieldDeclaration addPublicField(Class<?> typeClass, String name) {
        return this.addField(typeClass, name, Modifier.Keyword.PUBLIC);
    }

    default public FieldDeclaration addPublicField(String type, String name) {
        return this.addField(type, name, Modifier.Keyword.PUBLIC);
    }

    default public FieldDeclaration addPublicField(Type type, String name) {
        return this.addField(type, name, Modifier.Keyword.PUBLIC);
    }

    default public FieldDeclaration addProtectedField(Class<?> typeClass, String name) {
        return this.addField(typeClass, name, Modifier.Keyword.PROTECTED);
    }

    default public FieldDeclaration addProtectedField(String type, String name) {
        return this.addField(type, name, Modifier.Keyword.PROTECTED);
    }

    default public FieldDeclaration addProtectedField(Type type, String name) {
        return this.addField(type, name, Modifier.Keyword.PROTECTED);
    }

    default public MethodDeclaration addMethod(String methodName, Modifier.Keyword ... modifiers) {
        MethodDeclaration methodDeclaration = new MethodDeclaration();
        methodDeclaration.setName(methodName);
        methodDeclaration.setType(new VoidType());
        methodDeclaration.setModifiers((NodeList)Modifier.createModifierList(modifiers));
        this.getMembers().add(methodDeclaration);
        return methodDeclaration;
    }

    default public ConstructorDeclaration addConstructor(Modifier.Keyword ... modifiers) {
        ConstructorDeclaration constructorDeclaration = new ConstructorDeclaration();
        constructorDeclaration.setModifiers((NodeList)Modifier.createModifierList(modifiers));
        constructorDeclaration.setName(this.getName());
        this.getMembers().add(constructorDeclaration);
        return constructorDeclaration;
    }

    default public BlockStmt addInitializer() {
        BlockStmt block = new BlockStmt();
        InitializerDeclaration initializerDeclaration = new InitializerDeclaration(false, block);
        this.getMembers().add(initializerDeclaration);
        return block;
    }

    default public BlockStmt addStaticInitializer() {
        BlockStmt block = new BlockStmt();
        InitializerDeclaration initializerDeclaration = new InitializerDeclaration(true, block);
        this.getMembers().add(initializerDeclaration);
        return block;
    }

    default public List<MethodDeclaration> getMethodsByName(String name) {
        return Collections.unmodifiableList(this.getMethods().stream().filter(m -> m.getNameAsString().equals(name)).collect(Collectors.toList()));
    }

    default public List<MethodDeclaration> getMethods() {
        return Collections.unmodifiableList(this.getMembers().stream().filter(m -> m instanceof MethodDeclaration).map(m -> (MethodDeclaration)m).collect(Collectors.toList()));
    }

    default public List<MethodDeclaration> getMethodsByParameterTypes(String ... paramTypes) {
        return Collections.unmodifiableList(this.getMethods().stream().filter(m -> m.hasParametersOfType(paramTypes)).collect(Collectors.toList()));
    }

    default public List<MethodDeclaration> getMethodsBySignature(String name, String ... paramTypes) {
        return Collections.unmodifiableList(this.getMethodsByName(name).stream().filter(m -> m.hasParametersOfType(paramTypes)).collect(Collectors.toList()));
    }

    default public List<MethodDeclaration> getMethodsByParameterTypes(Class<?> ... paramTypes) {
        return Collections.unmodifiableList(this.getMethods().stream().filter(m -> m.hasParametersOfType(paramTypes)).collect(Collectors.toList()));
    }

    default public List<ConstructorDeclaration> getConstructors() {
        return Collections.unmodifiableList(this.getMembers().stream().filter(m -> m instanceof ConstructorDeclaration).map(m -> (ConstructorDeclaration)m).collect(Collectors.toList()));
    }

    default public Optional<ConstructorDeclaration> getDefaultConstructor() {
        return this.getMembers().stream().filter(m -> m instanceof ConstructorDeclaration).map(m -> (ConstructorDeclaration)m).filter(cd -> cd.getParameters().isEmpty()).findFirst();
    }

    default public Optional<ConstructorDeclaration> getConstructorByParameterTypes(String ... paramTypes) {
        return this.getConstructors().stream().filter(m -> m.hasParametersOfType(paramTypes)).findFirst();
    }

    default public Optional<ConstructorDeclaration> getConstructorByParameterTypes(Class<?> ... paramTypes) {
        return this.getConstructors().stream().filter(m -> m.hasParametersOfType(paramTypes)).findFirst();
    }

    default public Optional<FieldDeclaration> getFieldByName(String name) {
        return this.getMembers().stream().filter(m -> m instanceof FieldDeclaration).map(f -> (FieldDeclaration)f).filter(f -> f.getVariables().stream().anyMatch(var -> var.getNameAsString().equals(name))).findFirst();
    }

    default public List<FieldDeclaration> getFields() {
        return Collections.unmodifiableList(this.getMembers().stream().filter(m -> m instanceof FieldDeclaration).map(m -> (FieldDeclaration)m).collect(Collectors.toList()));
    }

    default public boolean isEmpty() {
        return this.getMembers().isEmpty();
    }
}

