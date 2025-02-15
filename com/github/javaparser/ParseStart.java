/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser;

import com.github.javaparser.GeneratedJavaParser;
import com.github.javaparser.ParseException;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.ImportDeclaration;
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

@FunctionalInterface
public interface ParseStart<R> {
    public static final ParseStart<CompilationUnit> COMPILATION_UNIT = GeneratedJavaParser::CompilationUnit;
    public static final ParseStart<BlockStmt> BLOCK = GeneratedJavaParser::BlockParseStart;
    public static final ParseStart<Statement> STATEMENT = GeneratedJavaParser::BlockStatementParseStart;
    public static final ParseStart<ImportDeclaration> IMPORT_DECLARATION = GeneratedJavaParser::ImportDeclarationParseStart;
    public static final ParseStart<Expression> EXPRESSION = GeneratedJavaParser::ExpressionParseStart;
    public static final ParseStart<AnnotationExpr> ANNOTATION = GeneratedJavaParser::AnnotationParseStart;
    public static final ParseStart<BodyDeclaration<?>> ANNOTATION_BODY = GeneratedJavaParser::AnnotationBodyDeclarationParseStart;
    public static final ParseStart<BodyDeclaration<?>> CLASS_BODY = GeneratedJavaParser::ClassOrInterfaceBodyDeclarationParseStart;
    public static final ParseStart<ClassOrInterfaceType> CLASS_OR_INTERFACE_TYPE = GeneratedJavaParser::ClassOrInterfaceTypeParseStart;
    public static final ParseStart<Type> TYPE = GeneratedJavaParser::ResultTypeParseStart;
    public static final ParseStart<TypeParameter> TYPE_PARAMETER = GeneratedJavaParser::TypeParameterParseStart;
    public static final ParseStart<VariableDeclarationExpr> VARIABLE_DECLARATION_EXPR = GeneratedJavaParser::VariableDeclarationExpressionParseStart;
    public static final ParseStart<ExplicitConstructorInvocationStmt> EXPLICIT_CONSTRUCTOR_INVOCATION_STMT = GeneratedJavaParser::ExplicitConstructorInvocationParseStart;
    public static final ParseStart<Name> NAME = GeneratedJavaParser::NameParseStart;
    public static final ParseStart<SimpleName> SIMPLE_NAME = GeneratedJavaParser::SimpleNameParseStart;
    public static final ParseStart<Parameter> PARAMETER = GeneratedJavaParser::ParameterParseStart;
    public static final ParseStart<PackageDeclaration> PACKAGE_DECLARATION = GeneratedJavaParser::PackageDeclarationParseStart;
    public static final ParseStart<TypeDeclaration<?>> TYPE_DECLARATION = GeneratedJavaParser::TypeDeclarationParseStart;
    public static final ParseStart<ModuleDeclaration> MODULE_DECLARATION = GeneratedJavaParser::ModuleDeclarationParseStart;
    public static final ParseStart<ModuleDirective> MODULE_DIRECTIVE = GeneratedJavaParser::ModuleDirectiveParseStart;
    public static final ParseStart<MethodDeclaration> METHOD_DECLARATION = GeneratedJavaParser::MethodDeclarationParseStart;

    public R parse(GeneratedJavaParser var1) throws ParseException;
}

