/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.ast.nodeTypes;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.expr.AnnotationExpr;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.MarkerAnnotationExpr;
import com.github.javaparser.ast.expr.MemberValuePair;
import com.github.javaparser.ast.expr.NormalAnnotationExpr;
import com.github.javaparser.ast.expr.SingleMemberAnnotationExpr;
import java.lang.annotation.Annotation;
import java.util.Optional;

public interface NodeWithAnnotations<N extends Node> {
    public NodeList<AnnotationExpr> getAnnotations();

    public N setAnnotations(NodeList<AnnotationExpr> var1);

    public void tryAddImportToParentCompilationUnit(Class<?> var1);

    default public AnnotationExpr getAnnotation(int i) {
        return (AnnotationExpr)this.getAnnotations().get(i);
    }

    default public N setAnnotation(int i, AnnotationExpr element) {
        this.getAnnotations().set(i, element);
        return (N)((Node)((Object)this));
    }

    default public N addAnnotation(AnnotationExpr element) {
        this.getAnnotations().add(element);
        return (N)((Node)((Object)this));
    }

    default public N addAnnotation(String name) {
        NormalAnnotationExpr annotation = new NormalAnnotationExpr(StaticJavaParser.parseName(name), new NodeList<MemberValuePair>());
        this.addAnnotation(annotation);
        return (N)((Node)((Object)this));
    }

    default public NormalAnnotationExpr addAndGetAnnotation(String name) {
        NormalAnnotationExpr annotation = new NormalAnnotationExpr(StaticJavaParser.parseName(name), new NodeList<MemberValuePair>());
        this.addAnnotation(annotation);
        return annotation;
    }

    default public N addAnnotation(Class<? extends Annotation> clazz) {
        this.tryAddImportToParentCompilationUnit(clazz);
        return this.addAnnotation(clazz.getSimpleName());
    }

    default public NormalAnnotationExpr addAndGetAnnotation(Class<? extends Annotation> clazz) {
        this.tryAddImportToParentCompilationUnit(clazz);
        return this.addAndGetAnnotation(clazz.getSimpleName());
    }

    default public N addMarkerAnnotation(String name) {
        MarkerAnnotationExpr markerAnnotationExpr = new MarkerAnnotationExpr(StaticJavaParser.parseName(name));
        this.addAnnotation(markerAnnotationExpr);
        return (N)((Node)((Object)this));
    }

    default public N addMarkerAnnotation(Class<? extends Annotation> clazz) {
        this.tryAddImportToParentCompilationUnit(clazz);
        return this.addMarkerAnnotation(clazz.getSimpleName());
    }

    default public N addSingleMemberAnnotation(String name, Expression expression) {
        SingleMemberAnnotationExpr singleMemberAnnotationExpr = new SingleMemberAnnotationExpr(StaticJavaParser.parseName(name), expression);
        return this.addAnnotation(singleMemberAnnotationExpr);
    }

    default public N addSingleMemberAnnotation(Class<? extends Annotation> clazz, Expression expression) {
        this.tryAddImportToParentCompilationUnit(clazz);
        return this.addSingleMemberAnnotation(clazz.getSimpleName(), expression);
    }

    default public N addSingleMemberAnnotation(String name, String value) {
        return this.addSingleMemberAnnotation(name, (Expression)StaticJavaParser.parseExpression(value));
    }

    default public N addSingleMemberAnnotation(Class<? extends Annotation> clazz, String value) {
        this.tryAddImportToParentCompilationUnit(clazz);
        return this.addSingleMemberAnnotation(clazz.getSimpleName(), value);
    }

    default public boolean isAnnotationPresent(String annotationName) {
        return this.getAnnotations().stream().anyMatch(a -> a.getName().getIdentifier().equals(annotationName));
    }

    default public boolean isAnnotationPresent(Class<? extends Annotation> annotationClass) {
        return this.isAnnotationPresent(annotationClass.getSimpleName());
    }

    default public Optional<AnnotationExpr> getAnnotationByName(String annotationName) {
        return this.getAnnotations().stream().filter(a -> a.getName().getIdentifier().equals(annotationName)).findFirst();
    }

    default public Optional<AnnotationExpr> getAnnotationByClass(Class<? extends Annotation> annotationClass) {
        return this.getAnnotationByName(annotationClass.getSimpleName());
    }
}

