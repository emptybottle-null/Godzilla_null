/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser;

import com.github.javaparser.GeneratedJavaParser;
import com.github.javaparser.ParseResult;
import com.github.javaparser.ParseStart;
import com.github.javaparser.ParserConfiguration;
import com.github.javaparser.Problem;
import com.github.javaparser.Provider;
import com.github.javaparser.Providers;
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
import com.github.javaparser.utils.Utils;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.file.Path;

public final class JavaParser {
    private final ParserConfiguration configuration;
    private GeneratedJavaParser astParser = null;

    public JavaParser() {
        this(new ParserConfiguration());
    }

    public JavaParser(ParserConfiguration configuration) {
        this.configuration = configuration;
    }

    public ParserConfiguration getParserConfiguration() {
        return this.configuration;
    }

    private GeneratedJavaParser getParserForProvider(Provider provider) {
        if (this.astParser == null) {
            this.astParser = new GeneratedJavaParser(provider);
        } else {
            this.astParser.reset(provider);
        }
        this.astParser.setTabSize(this.configuration.getTabSize());
        this.astParser.setStoreTokens(this.configuration.isStoreTokens());
        ParserConfiguration.LanguageLevel languageLevel = this.configuration.getLanguageLevel();
        if (languageLevel != null && languageLevel.isYieldSupported()) {
            this.astParser.setYieldSupported();
        }
        return this.astParser;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public <N extends Node> ParseResult<N> parse(ParseStart<N> start, Provider provider) {
        Utils.assertNotNull(start);
        Utils.assertNotNull(provider);
        for (Providers.PreProcessor preProcessor : this.configuration.getPreProcessors()) {
            provider = preProcessor.process(provider);
        }
        GeneratedJavaParser parser = this.getParserForProvider(provider);
        try {
            Node resultNode = (Node)start.parse(parser);
            ParseResult<Node> result = new ParseResult<Node>(resultNode, parser.problems, parser.getCommentsCollection());
            this.configuration.getPostProcessors().forEach(postProcessor -> postProcessor.process(result, this.configuration));
            result.getProblems().sort(Problem.PROBLEM_BY_BEGIN_POSITION);
            ParseResult<Node> parseResult = result;
            return parseResult;
        } catch (Exception e) {
            String message = e.getMessage() == null ? "Unknown error" : e.getMessage();
            parser.problems.add(new Problem(message, null, e));
            ParseResult<Object> parseResult = new ParseResult<Object>(null, parser.problems, parser.getCommentsCollection());
            return parseResult;
        } finally {
            try {
                provider.close();
            } catch (IOException iOException) {}
        }
    }

    public ParseResult<CompilationUnit> parse(InputStream in, Charset encoding) {
        return this.parse(ParseStart.COMPILATION_UNIT, Providers.provider(in, encoding));
    }

    public ParseResult<CompilationUnit> parse(InputStream in) {
        return this.parse(in, this.configuration.getCharacterEncoding());
    }

    @Deprecated
    public ParseResult<CompilationUnit> parse(File file, Charset encoding) throws FileNotFoundException {
        ParseResult<CompilationUnit> result = this.parse(ParseStart.COMPILATION_UNIT, Providers.provider(file, encoding));
        result.getResult().ifPresent(cu -> cu.setStorage(file.toPath(), encoding));
        return result;
    }

    public ParseResult<CompilationUnit> parse(File file) throws FileNotFoundException {
        ParseResult<CompilationUnit> result = this.parse(ParseStart.COMPILATION_UNIT, Providers.provider(file, this.configuration.getCharacterEncoding()));
        result.getResult().ifPresent(cu -> cu.setStorage(file.toPath(), this.configuration.getCharacterEncoding()));
        return result;
    }

    @Deprecated
    public ParseResult<CompilationUnit> parse(Path path, Charset encoding) throws IOException {
        ParseResult<CompilationUnit> result = this.parse(ParseStart.COMPILATION_UNIT, Providers.provider(path, encoding));
        result.getResult().ifPresent(cu -> cu.setStorage(path, encoding));
        return result;
    }

    public ParseResult<CompilationUnit> parse(Path path) throws IOException {
        ParseResult<CompilationUnit> result = this.parse(ParseStart.COMPILATION_UNIT, Providers.provider(path, this.configuration.getCharacterEncoding()));
        result.getResult().ifPresent(cu -> cu.setStorage(path, this.configuration.getCharacterEncoding()));
        return result;
    }

    public ParseResult<CompilationUnit> parseResource(String path) throws IOException {
        return this.parse(ParseStart.COMPILATION_UNIT, Providers.resourceProvider(path, this.configuration.getCharacterEncoding()));
    }

    @Deprecated
    public ParseResult<CompilationUnit> parseResource(String path, Charset encoding) throws IOException {
        return this.parse(ParseStart.COMPILATION_UNIT, Providers.resourceProvider(path, encoding));
    }

    @Deprecated
    public ParseResult<CompilationUnit> parseResource(ClassLoader classLoader, String path, Charset encoding) throws IOException {
        return this.parse(ParseStart.COMPILATION_UNIT, Providers.resourceProvider(classLoader, path, encoding));
    }

    public ParseResult<CompilationUnit> parse(Reader reader) {
        return this.parse(ParseStart.COMPILATION_UNIT, Providers.provider(reader));
    }

    public ParseResult<CompilationUnit> parse(String code) {
        return this.parse(ParseStart.COMPILATION_UNIT, Providers.provider(code));
    }

    public ParseResult<BlockStmt> parseBlock(String blockStatement) {
        return this.parse(ParseStart.BLOCK, Providers.provider(blockStatement));
    }

    public ParseResult<Statement> parseStatement(String statement) {
        return this.parse(ParseStart.STATEMENT, Providers.provider(statement));
    }

    public ParseResult<ImportDeclaration> parseImport(String importDeclaration) {
        return this.parse(ParseStart.IMPORT_DECLARATION, Providers.provider(importDeclaration));
    }

    public <T extends Expression> ParseResult<T> parseExpression(String expression) {
        return this.parse(ParseStart.EXPRESSION, Providers.provider(expression));
    }

    public ParseResult<AnnotationExpr> parseAnnotation(String annotation) {
        return this.parse(ParseStart.ANNOTATION, Providers.provider(annotation));
    }

    public ParseResult<BodyDeclaration<?>> parseAnnotationBodyDeclaration(String body) {
        return this.parse(ParseStart.ANNOTATION_BODY, Providers.provider(body));
    }

    public <T extends BodyDeclaration<?>> ParseResult<T> parseBodyDeclaration(String body) {
        return this.parse(ParseStart.CLASS_BODY, Providers.provider(body));
    }

    public ParseResult<ClassOrInterfaceType> parseClassOrInterfaceType(String type) {
        return this.parse(ParseStart.CLASS_OR_INTERFACE_TYPE, Providers.provider(type));
    }

    public ParseResult<Type> parseType(String type) {
        return this.parse(ParseStart.TYPE, Providers.provider(type));
    }

    public ParseResult<VariableDeclarationExpr> parseVariableDeclarationExpr(String declaration) {
        return this.parse(ParseStart.VARIABLE_DECLARATION_EXPR, Providers.provider(declaration));
    }

    public ParseResult<ExplicitConstructorInvocationStmt> parseExplicitConstructorInvocationStmt(String statement) {
        return this.parse(ParseStart.EXPLICIT_CONSTRUCTOR_INVOCATION_STMT, Providers.provider(statement));
    }

    public ParseResult<Name> parseName(String qualifiedName) {
        return this.parse(ParseStart.NAME, Providers.provider(qualifiedName));
    }

    public ParseResult<SimpleName> parseSimpleName(String name) {
        return this.parse(ParseStart.SIMPLE_NAME, Providers.provider(name));
    }

    public ParseResult<Parameter> parseParameter(String parameter) {
        return this.parse(ParseStart.PARAMETER, Providers.provider(parameter));
    }

    public ParseResult<PackageDeclaration> parsePackageDeclaration(String packageDeclaration) {
        return this.parse(ParseStart.PACKAGE_DECLARATION, Providers.provider(packageDeclaration));
    }

    public ParseResult<TypeDeclaration<?>> parseTypeDeclaration(String typeDeclaration) {
        return this.parse(ParseStart.TYPE_DECLARATION, Providers.provider(typeDeclaration));
    }

    public ParseResult<ModuleDeclaration> parseModuleDeclaration(String moduleDeclaration) {
        return this.parse(ParseStart.MODULE_DECLARATION, Providers.provider(moduleDeclaration));
    }

    public ParseResult<ModuleDirective> parseModuleDirective(String moduleDirective) {
        return this.parse(ParseStart.MODULE_DIRECTIVE, Providers.provider(moduleDirective));
    }

    public ParseResult<TypeParameter> parseTypeParameter(String typeParameter) {
        return this.parse(ParseStart.TYPE_PARAMETER, Providers.provider(typeParameter));
    }

    public ParseResult<MethodDeclaration> parseMethodDeclaration(String methodDeclaration) {
        return this.parse(ParseStart.METHOD_DECLARATION, Providers.provider(methodDeclaration));
    }
}

