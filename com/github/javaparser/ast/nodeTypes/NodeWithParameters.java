/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.ast.nodeTypes;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.nodeTypes.NodeWithSimpleName;
import com.github.javaparser.ast.type.Type;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public interface NodeWithParameters<N extends Node> {
    public NodeList<Parameter> getParameters();

    default public Parameter getParameter(int i) {
        return (Parameter)this.getParameters().get(i);
    }

    public void tryAddImportToParentCompilationUnit(Class<?> var1);

    default public N setParameter(int i, Parameter parameter) {
        this.getParameters().set(i, parameter);
        return (N)((Node)((Object)this));
    }

    public N setParameters(NodeList<Parameter> var1);

    default public N addParameter(Type type, String name) {
        return this.addParameter(new Parameter(type, name));
    }

    default public N addParameter(Class<?> paramClass, String name) {
        this.tryAddImportToParentCompilationUnit(paramClass);
        return this.addParameter(StaticJavaParser.parseType(paramClass.getSimpleName()), name);
    }

    default public N addParameter(String className, String name) {
        return this.addParameter(StaticJavaParser.parseType(className), name);
    }

    default public N addParameter(Parameter parameter) {
        this.getParameters().add(parameter);
        return (N)((Node)((Object)this));
    }

    default public Parameter addAndGetParameter(Type type, String name) {
        return this.addAndGetParameter(new Parameter(type, name));
    }

    default public Parameter addAndGetParameter(Class<?> paramClass, String name) {
        this.tryAddImportToParentCompilationUnit(paramClass);
        return this.addAndGetParameter(StaticJavaParser.parseType(paramClass.getSimpleName()), name);
    }

    default public Parameter addAndGetParameter(String className, String name) {
        return this.addAndGetParameter(StaticJavaParser.parseType(className), name);
    }

    default public Parameter addAndGetParameter(Parameter parameter) {
        this.getParameters().add(parameter);
        return parameter;
    }

    default public Optional<Parameter> getParameterByName(String name) {
        return this.getParameters().stream().filter(p -> p.getNameAsString().equals(name)).findFirst();
    }

    default public Optional<Parameter> getParameterByType(String type) {
        return this.getParameters().stream().filter(p -> p.getType().toString().equals(type)).findFirst();
    }

    default public Optional<Parameter> getParameterByType(Class<?> type) {
        return this.getParameters().stream().filter(p -> p.getType().toString().equals(type.getSimpleName())).findFirst();
    }

    default public boolean hasParametersOfType(String ... paramTypes) {
        return this.getParameters().stream().map(p -> p.getType().asString()).collect(Collectors.toList()).equals(Arrays.asList(paramTypes));
    }

    default public boolean hasParametersOfType(Class<?> ... paramTypes) {
        return this.getParameters().stream().map(p -> p.getType().toClassOrInterfaceType().map(NodeWithSimpleName::getNameAsString).orElse(p.getType().asString())).collect(Collectors.toList()).equals(Stream.of(paramTypes).map(Class::getSimpleName).collect(Collectors.toList()));
    }
}

