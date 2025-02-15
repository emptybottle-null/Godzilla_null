/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.ast.validator.language_level_validations.chunks;

import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.body.AnnotationDeclaration;
import com.github.javaparser.ast.body.AnnotationMemberDeclaration;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.body.EnumDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.ast.expr.LambdaExpr;
import com.github.javaparser.ast.expr.VariableDeclarationExpr;
import com.github.javaparser.ast.modules.ModuleRequiresDirective;
import com.github.javaparser.ast.nodeTypes.NodeWithModifiers;
import com.github.javaparser.ast.nodeTypes.NodeWithTokenRange;
import com.github.javaparser.ast.stmt.CatchClause;
import com.github.javaparser.ast.validator.ProblemReporter;
import com.github.javaparser.ast.validator.VisitorValidator;
import com.github.javaparser.utils.SeparatedItemStringBuilder;
import java.util.ArrayList;
import java.util.Arrays;

public class ModifierValidator
extends VisitorValidator {
    private final Modifier.Keyword[] interfaceWithNothingSpecial = new Modifier.Keyword[]{Modifier.Keyword.PUBLIC, Modifier.Keyword.PROTECTED, Modifier.Keyword.ABSTRACT, Modifier.Keyword.FINAL, Modifier.Keyword.SYNCHRONIZED, Modifier.Keyword.NATIVE, Modifier.Keyword.STRICTFP};
    private final Modifier.Keyword[] interfaceWithStaticAndDefault = new Modifier.Keyword[]{Modifier.Keyword.PUBLIC, Modifier.Keyword.PROTECTED, Modifier.Keyword.ABSTRACT, Modifier.Keyword.STATIC, Modifier.Keyword.FINAL, Modifier.Keyword.SYNCHRONIZED, Modifier.Keyword.NATIVE, Modifier.Keyword.STRICTFP, Modifier.Keyword.DEFAULT};
    private final Modifier.Keyword[] interfaceWithStaticAndDefaultAndPrivate = new Modifier.Keyword[]{Modifier.Keyword.PUBLIC, Modifier.Keyword.PROTECTED, Modifier.Keyword.PRIVATE, Modifier.Keyword.ABSTRACT, Modifier.Keyword.STATIC, Modifier.Keyword.FINAL, Modifier.Keyword.SYNCHRONIZED, Modifier.Keyword.NATIVE, Modifier.Keyword.STRICTFP, Modifier.Keyword.DEFAULT};
    private final boolean hasStrictfp;
    private final boolean hasDefaultAndStaticInterfaceMethods;
    private final boolean hasPrivateInterfaceMethods;

    public ModifierValidator(boolean hasStrictfp, boolean hasDefaultAndStaticInterfaceMethods, boolean hasPrivateInterfaceMethods) {
        this.hasStrictfp = hasStrictfp;
        this.hasDefaultAndStaticInterfaceMethods = hasDefaultAndStaticInterfaceMethods;
        this.hasPrivateInterfaceMethods = hasPrivateInterfaceMethods;
    }

    @Override
    public void visit(ClassOrInterfaceDeclaration n, ProblemReporter reporter) {
        if (n.isInterface()) {
            this.validateInterfaceModifiers(n, reporter);
        } else {
            this.validateClassModifiers(n, reporter);
        }
        super.visit(n, reporter);
    }

    private void validateClassModifiers(ClassOrInterfaceDeclaration n, ProblemReporter reporter) {
        if (n.isTopLevelType()) {
            this.validateModifiers((NodeWithModifiers<?> & NodeWithTokenRange<?>)n, reporter, Modifier.Keyword.PUBLIC, Modifier.Keyword.ABSTRACT, Modifier.Keyword.FINAL, Modifier.Keyword.STRICTFP);
        } else if (n.isNestedType()) {
            this.validateModifiers((NodeWithModifiers<?> & NodeWithTokenRange<?>)n, reporter, Modifier.Keyword.PUBLIC, Modifier.Keyword.PROTECTED, Modifier.Keyword.PRIVATE, Modifier.Keyword.ABSTRACT, Modifier.Keyword.STATIC, Modifier.Keyword.FINAL, Modifier.Keyword.STRICTFP);
        } else if (n.isLocalClassDeclaration()) {
            this.validateModifiers((NodeWithModifiers<?> & NodeWithTokenRange<?>)n, reporter, Modifier.Keyword.ABSTRACT, Modifier.Keyword.FINAL, Modifier.Keyword.STRICTFP);
        }
    }

    private void validateInterfaceModifiers(TypeDeclaration<?> n, ProblemReporter reporter) {
        if (n.isTopLevelType()) {
            this.validateModifiers((NodeWithModifiers<?> & NodeWithTokenRange<?>)n, reporter, Modifier.Keyword.PUBLIC, Modifier.Keyword.ABSTRACT, Modifier.Keyword.STRICTFP);
        } else if (n.isNestedType()) {
            this.validateModifiers((NodeWithModifiers<?> & NodeWithTokenRange<?>)n, reporter, Modifier.Keyword.PUBLIC, Modifier.Keyword.PROTECTED, Modifier.Keyword.PRIVATE, Modifier.Keyword.ABSTRACT, Modifier.Keyword.STATIC, Modifier.Keyword.STRICTFP);
        }
    }

    @Override
    public void visit(EnumDeclaration n, ProblemReporter reporter) {
        if (n.isTopLevelType()) {
            this.validateModifiers((NodeWithModifiers<?> & NodeWithTokenRange<?>)n, reporter, Modifier.Keyword.PUBLIC, Modifier.Keyword.STRICTFP);
        } else if (n.isNestedType()) {
            this.validateModifiers((NodeWithModifiers<?> & NodeWithTokenRange<?>)n, reporter, Modifier.Keyword.PUBLIC, Modifier.Keyword.PROTECTED, Modifier.Keyword.PRIVATE, Modifier.Keyword.STATIC, Modifier.Keyword.STRICTFP);
        }
        super.visit(n, reporter);
    }

    @Override
    public void visit(AnnotationDeclaration n, ProblemReporter reporter) {
        this.validateInterfaceModifiers(n, reporter);
        super.visit(n, reporter);
    }

    @Override
    public void visit(AnnotationMemberDeclaration n, ProblemReporter reporter) {
        this.validateModifiers((NodeWithModifiers<?> & NodeWithTokenRange<?>)n, reporter, Modifier.Keyword.PUBLIC, Modifier.Keyword.ABSTRACT);
        super.visit(n, reporter);
    }

    @Override
    public void visit(ConstructorDeclaration n, ProblemReporter reporter) {
        this.validateModifiers((NodeWithModifiers<?> & NodeWithTokenRange<?>)n, reporter, Modifier.Keyword.PUBLIC, Modifier.Keyword.PROTECTED, Modifier.Keyword.PRIVATE);
        n.getParameters().forEach(p -> this.validateModifiers(p, reporter, Modifier.Keyword.FINAL));
        super.visit(n, reporter);
    }

    @Override
    public void visit(FieldDeclaration n, ProblemReporter reporter) {
        this.validateModifiers((NodeWithModifiers<?> & NodeWithTokenRange<?>)n, reporter, Modifier.Keyword.PUBLIC, Modifier.Keyword.PROTECTED, Modifier.Keyword.PRIVATE, Modifier.Keyword.STATIC, Modifier.Keyword.FINAL, Modifier.Keyword.TRANSIENT, Modifier.Keyword.VOLATILE);
        super.visit(n, reporter);
    }

    @Override
    public void visit(MethodDeclaration n, ProblemReporter reporter) {
        if (n.isAbstract()) {
            SeparatedItemStringBuilder builder = new SeparatedItemStringBuilder("Cannot be 'abstract' and also '", "', '", "'.");
            for (Modifier.Keyword m : Arrays.asList(Modifier.Keyword.PRIVATE, Modifier.Keyword.STATIC, Modifier.Keyword.FINAL, Modifier.Keyword.NATIVE, Modifier.Keyword.STRICTFP, Modifier.Keyword.SYNCHRONIZED)) {
                if (!n.hasModifier(m)) continue;
                builder.append(m.asString(), new Object[0]);
            }
            if (builder.hasItems()) {
                reporter.report(n, builder.toString(), new Object[0]);
            }
        }
        if (n.getParentNode().isPresent() && n.getParentNode().get() instanceof ClassOrInterfaceDeclaration) {
            if (((ClassOrInterfaceDeclaration)n.getParentNode().get()).isInterface()) {
                if (this.hasDefaultAndStaticInterfaceMethods) {
                    if (this.hasPrivateInterfaceMethods) {
                        this.validateModifiers(n, reporter, this.interfaceWithStaticAndDefaultAndPrivate);
                    } else {
                        this.validateModifiers(n, reporter, this.interfaceWithStaticAndDefault);
                    }
                } else {
                    this.validateModifiers(n, reporter, this.interfaceWithNothingSpecial);
                }
            } else {
                this.validateModifiers((NodeWithModifiers<?> & NodeWithTokenRange<?>)n, reporter, Modifier.Keyword.PUBLIC, Modifier.Keyword.PROTECTED, Modifier.Keyword.PRIVATE, Modifier.Keyword.ABSTRACT, Modifier.Keyword.STATIC, Modifier.Keyword.FINAL, Modifier.Keyword.SYNCHRONIZED, Modifier.Keyword.NATIVE, Modifier.Keyword.STRICTFP);
            }
        }
        n.getParameters().forEach(p -> this.validateModifiers(p, reporter, Modifier.Keyword.FINAL));
        super.visit(n, reporter);
    }

    @Override
    public void visit(LambdaExpr n, ProblemReporter reporter) {
        n.getParameters().forEach(p -> this.validateModifiers(p, reporter, Modifier.Keyword.FINAL));
        super.visit(n, reporter);
    }

    @Override
    public void visit(CatchClause n, ProblemReporter reporter) {
        this.validateModifiers(n.getParameter(), reporter, Modifier.Keyword.FINAL);
        super.visit(n, reporter);
    }

    @Override
    public void visit(VariableDeclarationExpr n, ProblemReporter reporter) {
        this.validateModifiers(n, reporter, Modifier.Keyword.FINAL);
        super.visit(n, reporter);
    }

    @Override
    public void visit(ModuleRequiresDirective n, ProblemReporter reporter) {
        this.validateModifiers((NodeWithModifiers<?> & NodeWithTokenRange<?>)n, reporter, Modifier.Keyword.TRANSITIVE, Modifier.Keyword.STATIC);
        super.visit(n, reporter);
    }

    private <T extends NodeWithModifiers<?> & NodeWithTokenRange<?>> void validateModifiers(T n, ProblemReporter reporter, Modifier.Keyword ... allowedModifiers) {
        this.validateAtMostOneOf(n, reporter, new Modifier.Keyword[]{Modifier.Keyword.PUBLIC, Modifier.Keyword.PROTECTED, Modifier.Keyword.PRIVATE});
        this.validateAtMostOneOf(n, reporter, new Modifier.Keyword[]{Modifier.Keyword.FINAL, Modifier.Keyword.ABSTRACT});
        if (this.hasStrictfp) {
            this.validateAtMostOneOf(n, reporter, new Modifier.Keyword[]{Modifier.Keyword.NATIVE, Modifier.Keyword.STRICTFP});
        } else {
            allowedModifiers = this.removeModifierFromArray(Modifier.Keyword.STRICTFP, allowedModifiers);
        }
        for (Modifier m : n.getModifiers()) {
            if (this.arrayContains((Object[])allowedModifiers, (Object)m.getKeyword())) continue;
            reporter.report(n, "'%s' is not allowed here.", m.getKeyword().asString());
        }
    }

    private Modifier.Keyword[] removeModifierFromArray(Modifier.Keyword m, Modifier.Keyword[] allowedModifiers) {
        ArrayList<Modifier.Keyword> newModifiers = new ArrayList<Modifier.Keyword>(Arrays.asList(allowedModifiers));
        newModifiers.remove((Object)m);
        allowedModifiers = newModifiers.toArray(new Modifier.Keyword[0]);
        return allowedModifiers;
    }

    private boolean arrayContains(Object[] items, Object searchItem) {
        for (Object o : items) {
            if (o != searchItem) continue;
            return true;
        }
        return false;
    }

    private <T extends NodeWithModifiers<?> & NodeWithTokenRange<?>> void validateAtMostOneOf(T t, ProblemReporter reporter, Modifier.Keyword ... modifiers) {
        ArrayList<Modifier.Keyword> foundModifiers = new ArrayList<Modifier.Keyword>();
        for (Modifier.Keyword m : modifiers) {
            if (!t.hasModifier(m)) continue;
            foundModifiers.add(m);
        }
        if (foundModifiers.size() > 1) {
            SeparatedItemStringBuilder builder = new SeparatedItemStringBuilder("Can have only one of '", "', '", "'.");
            for (Modifier.Keyword m : foundModifiers) {
                builder.append(m.asString(), new Object[0]);
            }
            reporter.report(t, builder.toString(), new Object[0]);
        }
    }
}

