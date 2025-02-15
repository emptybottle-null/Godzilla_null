/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.ast.validator.language_level_validations;

import com.github.javaparser.ast.ImportDeclaration;
import com.github.javaparser.ast.body.AnnotationDeclaration;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.EnumDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.expr.AnnotationExpr;
import com.github.javaparser.ast.expr.ClassExpr;
import com.github.javaparser.ast.expr.InstanceOfExpr;
import com.github.javaparser.ast.expr.LambdaExpr;
import com.github.javaparser.ast.expr.SwitchExpr;
import com.github.javaparser.ast.expr.TextBlockLiteralExpr;
import com.github.javaparser.ast.modules.ModuleDeclaration;
import com.github.javaparser.ast.nodeTypes.NodeWithTokenRange;
import com.github.javaparser.ast.nodeTypes.NodeWithTypeArguments;
import com.github.javaparser.ast.nodeTypes.NodeWithTypeParameters;
import com.github.javaparser.ast.stmt.AssertStmt;
import com.github.javaparser.ast.stmt.ForEachStmt;
import com.github.javaparser.ast.stmt.SwitchEntry;
import com.github.javaparser.ast.stmt.TryStmt;
import com.github.javaparser.ast.stmt.YieldStmt;
import com.github.javaparser.ast.type.UnionType;
import com.github.javaparser.ast.validator.SimpleValidator;
import com.github.javaparser.ast.validator.SingleNodeTypeValidator;
import com.github.javaparser.ast.validator.TreeVisitorValidator;
import com.github.javaparser.ast.validator.Validator;
import com.github.javaparser.ast.validator.Validators;
import com.github.javaparser.ast.validator.language_level_validations.chunks.CommonValidators;
import com.github.javaparser.ast.validator.language_level_validations.chunks.ModifierValidator;
import com.github.javaparser.ast.validator.language_level_validations.chunks.NoBinaryIntegerLiteralsValidator;
import com.github.javaparser.ast.validator.language_level_validations.chunks.NoUnderscoresInIntegerLiteralsValidator;

public class Java1_0Validator
extends Validators {
    final Validator modifiersWithoutStrictfpAndDefaultAndStaticInterfaceMethodsAndPrivateInterfaceMethods = new ModifierValidator(false, false, false);
    final Validator noAssertKeyword = new SimpleValidator<AssertStmt>(AssertStmt.class, n -> true, (n, reporter) -> reporter.report((NodeWithTokenRange<?>)n, "'assert' keyword is not supported.", new Object[0]));
    final Validator noInnerClasses = new SimpleValidator<ClassOrInterfaceDeclaration>(ClassOrInterfaceDeclaration.class, n -> !n.isTopLevelType(), (n, reporter) -> reporter.report((NodeWithTokenRange<?>)n, "inner classes or interfaces are not supported.", new Object[0]));
    final Validator noReflection = new SimpleValidator<ClassExpr>(ClassExpr.class, n -> true, (n, reporter) -> reporter.report((NodeWithTokenRange<?>)n, "Reflection is not supported.", new Object[0]));
    final Validator noGenerics = new TreeVisitorValidator((node, reporter) -> {
        if (node instanceof NodeWithTypeArguments && ((NodeWithTypeArguments)((Object)node)).getTypeArguments().isPresent()) {
            reporter.report(node, "Generics are not supported.", new Object[0]);
        }
        if (node instanceof NodeWithTypeParameters && ((NodeWithTypeParameters)((Object)node)).getTypeParameters().isNonEmpty()) {
            reporter.report(node, "Generics are not supported.", new Object[0]);
        }
    });
    final SingleNodeTypeValidator<TryStmt> tryWithoutResources = new SingleNodeTypeValidator<TryStmt>(TryStmt.class, (n, reporter) -> {
        if (n.getCatchClauses().isEmpty() && !n.getFinallyBlock().isPresent()) {
            reporter.report(n, "Try has no finally and no catch.", new Object[0]);
        }
        if (n.getResources().isNonEmpty()) {
            reporter.report(n, "Catch with resource is not supported.", new Object[0]);
        }
    });
    final Validator noAnnotations = new TreeVisitorValidator((node, reporter) -> {
        if (node instanceof AnnotationExpr || node instanceof AnnotationDeclaration) {
            reporter.report(node, "Annotations are not supported.", new Object[0]);
        }
    });
    final Validator noEnums = new SimpleValidator<EnumDeclaration>(EnumDeclaration.class, n -> true, (n, reporter) -> reporter.report((NodeWithTokenRange<?>)n, "Enumerations are not supported.", new Object[0]));
    final Validator noVarargs = new SimpleValidator<Parameter>(Parameter.class, Parameter::isVarArgs, (n, reporter) -> reporter.report((NodeWithTokenRange<?>)n, "Varargs are not supported.", new Object[0]));
    final Validator noForEach = new SimpleValidator<ForEachStmt>(ForEachStmt.class, n -> true, (n, reporter) -> reporter.report((NodeWithTokenRange<?>)n, "For-each loops are not supported.", new Object[0]));
    final Validator noStaticImports = new SimpleValidator<ImportDeclaration>(ImportDeclaration.class, ImportDeclaration::isStatic, (n, reporter) -> reporter.report((NodeWithTokenRange<?>)n, "Static imports are not supported.", new Object[0]));
    final Validator onlyOneLabelInSwitchCase = new SimpleValidator<SwitchEntry>(SwitchEntry.class, n -> n.getLabels().size() > 1, (n, reporter) -> reporter.report(n.getLabels().getParentNode().get(), "Only one label allowed in a switch-case.", new Object[0]));
    final Validator noYield = new SimpleValidator<YieldStmt>(YieldStmt.class, n -> true, (n, reporter) -> reporter.report((NodeWithTokenRange<?>)n, "Only labels allowed in break statements.", new Object[0]));
    final Validator noBinaryIntegerLiterals = new NoBinaryIntegerLiteralsValidator();
    final Validator noUnderscoresInIntegerLiterals = new NoUnderscoresInIntegerLiteralsValidator();
    final Validator noMultiCatch = new SimpleValidator<UnionType>(UnionType.class, n -> true, (n, reporter) -> reporter.report((NodeWithTokenRange<?>)n, "Multi-catch is not supported.", new Object[0]));
    final Validator noLambdas = new SimpleValidator<LambdaExpr>(LambdaExpr.class, n -> true, (n, reporter) -> reporter.report((NodeWithTokenRange<?>)n, "Lambdas are not supported.", new Object[0]));
    final Validator noModules = new SimpleValidator<ModuleDeclaration>(ModuleDeclaration.class, n -> true, (n, reporter) -> reporter.report((NodeWithTokenRange<?>)n, "Modules are not supported.", new Object[0]));
    final Validator noSwitchExpressions = new SimpleValidator<SwitchExpr>(SwitchExpr.class, n -> true, (n, reporter) -> reporter.report((NodeWithTokenRange<?>)n, "Switch expressions are not supported.", new Object[0]));
    final Validator noPatternMatchingInstanceOf = new SimpleValidator<InstanceOfExpr>(InstanceOfExpr.class, n -> n.getPattern().isPresent(), (n, reporter) -> reporter.report((NodeWithTokenRange<?>)n, "Use of patterns with instanceof is not supported.", new Object[0]));
    final Validator noTextBlockLiteral = new SimpleValidator<TextBlockLiteralExpr>(TextBlockLiteralExpr.class, n -> true, (n, reporter) -> reporter.report((NodeWithTokenRange<?>)n, "Text Block Literals are not supported.", new Object[0]));

    public Java1_0Validator() {
        super(new CommonValidators());
        this.add(this.modifiersWithoutStrictfpAndDefaultAndStaticInterfaceMethodsAndPrivateInterfaceMethods);
        this.add(this.noAssertKeyword);
        this.add(this.noInnerClasses);
        this.add(this.noReflection);
        this.add(this.noGenerics);
        this.add(this.tryWithoutResources);
        this.add(this.noAnnotations);
        this.add(this.noEnums);
        this.add(this.noVarargs);
        this.add(this.noForEach);
        this.add(this.noStaticImports);
        this.add(this.noYield);
        this.add(this.onlyOneLabelInSwitchCase);
        this.add(this.noBinaryIntegerLiterals);
        this.add(this.noUnderscoresInIntegerLiterals);
        this.add(this.noMultiCatch);
        this.add(this.noLambdas);
        this.add(this.noModules);
        this.add(this.noSwitchExpressions);
        this.add(this.noPatternMatchingInstanceOf);
        this.add(this.noTextBlockLiteral);
    }
}

