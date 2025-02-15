/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser;

import com.github.javaparser.JavaParser;
import com.github.javaparser.JavadocParser;
import com.github.javaparser.ParseProblemException;
import com.github.javaparser.ParseResult;
import com.github.javaparser.ParserConfiguration;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.ImportDeclaration;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.PackageDeclaration;
import com.github.javaparser.ast.body.BodyDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.ast.expr.AnnotationExpr;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.Name;
import com.github.javaparser.ast.expr.SimpleName;
import com.github.javaparser.ast.expr.VariableDeclarationExpr;
import com.github.javaparser.ast.modules.ModuleDeclaration;
import com.github.javaparser.ast.modules.ModuleDirective;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.stmt.ExplicitConstructorInvocationStmt;
import com.github.javaparser.ast.stmt.Statement;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.ast.type.Type;
import com.github.javaparser.ast.type.TypeParameter;
import com.github.javaparser.javadoc.Javadoc;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.file.Path;

public final class StaticJavaParser {
    private static ThreadLocal<ParserConfiguration> localConfiguration = ThreadLocal.withInitial(() -> new ParserConfiguration());

    private StaticJavaParser() {
    }

    public static ParserConfiguration getConfiguration() {
        return localConfiguration.get();
    }

    public static void setConfiguration(ParserConfiguration configuration) {
        localConfiguration.set(configuration);
    }

    private static JavaParser newParser() {
        return new JavaParser(StaticJavaParser.getConfiguration());
    }

    @Deprecated
    public static CompilationUnit parse(InputStream in, Charset encoding) {
        return StaticJavaParser.handleResult(StaticJavaParser.newParser().parse(in, encoding));
    }

    public static CompilationUnit parse(InputStream in) {
        return StaticJavaParser.handleResult(StaticJavaParser.newParser().parse(in));
    }

    @Deprecated
    public static CompilationUnit parse(File file, Charset encoding) throws FileNotFoundException {
        return StaticJavaParser.handleResult(StaticJavaParser.newParser().parse(file, encoding));
    }

    public static CompilationUnit parse(File file) throws FileNotFoundException {
        return StaticJavaParser.handleResult(StaticJavaParser.newParser().parse(file));
    }

    @Deprecated
    public static CompilationUnit parse(Path path, Charset encoding) throws IOException {
        return StaticJavaParser.handleResult(StaticJavaParser.newParser().parse(path, encoding));
    }

    public static CompilationUnit parse(Path path) throws IOException {
        return StaticJavaParser.handleResult(StaticJavaParser.newParser().parse(path));
    }

    public static CompilationUnit parseResource(String path) throws IOException {
        return StaticJavaParser.handleResult(StaticJavaParser.newParser().parseResource(path));
    }

    @Deprecated
    public static CompilationUnit parseResource(String path, Charset encoding) throws IOException {
        return StaticJavaParser.handleResult(StaticJavaParser.newParser().parseResource(path, encoding));
    }

    @Deprecated
    public static CompilationUnit parseResource(ClassLoader classLoader, String path, Charset encoding) throws IOException {
        return StaticJavaParser.handleResult(StaticJavaParser.newParser().parseResource(classLoader, path, encoding));
    }

    public static CompilationUnit parse(Reader reader) {
        return StaticJavaParser.handleResult(StaticJavaParser.newParser().parse(reader));
    }

    public static CompilationUnit parse(String code) {
        return StaticJavaParser.handleResult(StaticJavaParser.newParser().parse(code));
    }

    public static BlockStmt parseBlock(String blockStatement) {
        return StaticJavaParser.handleResult(StaticJavaParser.newParser().parseBlock(blockStatement));
    }

    public static Statement parseStatement(String statement) {
        return StaticJavaParser.handleResult(StaticJavaParser.newParser().parseStatement(statement));
    }

    private static <T extends Node> T handleResult(ParseResult<T> result) {
        if (result.isSuccessful()) {
            return (T)((Node)result.getResult().get());
        }
        throw new ParseProblemException(result.getProblems());
    }

    public static ImportDeclaration parseImport(String importDeclaration) {
        return StaticJavaParser.handleResult(StaticJavaParser.newParser().parseImport(importDeclaration));
    }

    public static <T extends Expression> T parseExpression(String expression) {
        return (T)((Expression)StaticJavaParser.handleResult(StaticJavaParser.newParser().parseExpression(expression)));
    }

    public static AnnotationExpr parseAnnotation(String annotation) {
        return StaticJavaParser.handleResult(StaticJavaParser.newParser().parseAnnotation(annotation));
    }

    public static BodyDeclaration<?> parseAnnotationBodyDeclaration(String body) {
        return StaticJavaParser.handleResult(StaticJavaParser.newParser().parseAnnotationBodyDeclaration(body));
    }

    public static BodyDeclaration<?> parseBodyDeclaration(String body) {
        return (BodyDeclaration)StaticJavaParser.handleResult(StaticJavaParser.newParser().parseBodyDeclaration(body));
    }

    public static ClassOrInterfaceType parseClassOrInterfaceType(String type) {
        return StaticJavaParser.handleResult(StaticJavaParser.newParser().parseClassOrInterfaceType(type));
    }

    public static Type parseType(String type) {
        return StaticJavaParser.handleResult(StaticJavaParser.newParser().parseType(type));
    }

    public static VariableDeclarationExpr parseVariableDeclarationExpr(String declaration) {
        return StaticJavaParser.handleResult(StaticJavaParser.newParser().parseVariableDeclarationExpr(declaration));
    }

    public static Javadoc parseJavadoc(String content) {
        return JavadocParser.parse(content);
    }

    public static ExplicitConstructorInvocationStmt parseExplicitConstructorInvocationStmt(String statement) {
        return StaticJavaParser.handleResult(StaticJavaParser.newParser().parseExplicitConstructorInvocationStmt(statement));
    }

    public static Name parseName(String qualifiedName) {
        return StaticJavaParser.handleResult(StaticJavaParser.newParser().parseName(qualifiedName));
    }

    public static SimpleName parseSimpleName(String name) {
        return StaticJavaParser.handleResult(StaticJavaParser.newParser().parseSimpleName(name));
    }

    public static Parameter parseParameter(String parameter) {
        return StaticJavaParser.handleResult(StaticJavaParser.newParser().parseParameter(parameter));
    }

    public static PackageDeclaration parsePackageDeclaration(String packageDeclaration) {
        return StaticJavaParser.handleResult(StaticJavaParser.newParser().parsePackageDeclaration(packageDeclaration));
    }

    public static TypeDeclaration<?> parseTypeDeclaration(String typeDeclaration) {
        return StaticJavaParser.handleResult(StaticJavaParser.newParser().parseTypeDeclaration(typeDeclaration));
    }

    public static ModuleDeclaration parseModuleDeclaration(String moduleDeclaration) {
        return StaticJavaParser.handleResult(StaticJavaParser.newParser().parseModuleDeclaration(moduleDeclaration));
    }

    public static ModuleDirective parseModuleDirective(String moduleDirective) {
        return StaticJavaParser.handleResult(StaticJavaParser.newParser().parseModuleDirective(moduleDirective));
    }

    public static TypeParameter parseTypeParameter(String typeParameter) {
        return StaticJavaParser.handleResult(StaticJavaParser.newParser().parseTypeParameter(typeParameter));
    }

    public static MethodDeclaration parseMethodDeclaration(String methodDeclaration) {
        return StaticJavaParser.handleResult(StaticJavaParser.newParser().parseMethodDeclaration(methodDeclaration));
    }
}

