/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.ast.visitor;

import com.github.javaparser.ast.ArrayCreationLevel;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.ImportDeclaration;
import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.PackageDeclaration;
import com.github.javaparser.ast.body.AnnotationDeclaration;
import com.github.javaparser.ast.body.AnnotationMemberDeclaration;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.body.EnumConstantDeclaration;
import com.github.javaparser.ast.body.EnumDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.InitializerDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.body.ReceiverParameter;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.comments.BlockComment;
import com.github.javaparser.ast.comments.JavadocComment;
import com.github.javaparser.ast.comments.LineComment;
import com.github.javaparser.ast.expr.ArrayAccessExpr;
import com.github.javaparser.ast.expr.ArrayCreationExpr;
import com.github.javaparser.ast.expr.ArrayInitializerExpr;
import com.github.javaparser.ast.expr.AssignExpr;
import com.github.javaparser.ast.expr.BinaryExpr;
import com.github.javaparser.ast.expr.BooleanLiteralExpr;
import com.github.javaparser.ast.expr.CastExpr;
import com.github.javaparser.ast.expr.CharLiteralExpr;
import com.github.javaparser.ast.expr.ClassExpr;
import com.github.javaparser.ast.expr.ConditionalExpr;
import com.github.javaparser.ast.expr.DoubleLiteralExpr;
import com.github.javaparser.ast.expr.EnclosedExpr;
import com.github.javaparser.ast.expr.FieldAccessExpr;
import com.github.javaparser.ast.expr.InstanceOfExpr;
import com.github.javaparser.ast.expr.IntegerLiteralExpr;
import com.github.javaparser.ast.expr.LambdaExpr;
import com.github.javaparser.ast.expr.LongLiteralExpr;
import com.github.javaparser.ast.expr.MarkerAnnotationExpr;
import com.github.javaparser.ast.expr.MemberValuePair;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.expr.MethodReferenceExpr;
import com.github.javaparser.ast.expr.Name;
import com.github.javaparser.ast.expr.NameExpr;
import com.github.javaparser.ast.expr.NormalAnnotationExpr;
import com.github.javaparser.ast.expr.NullLiteralExpr;
import com.github.javaparser.ast.expr.ObjectCreationExpr;
import com.github.javaparser.ast.expr.PatternExpr;
import com.github.javaparser.ast.expr.SimpleName;
import com.github.javaparser.ast.expr.SingleMemberAnnotationExpr;
import com.github.javaparser.ast.expr.StringLiteralExpr;
import com.github.javaparser.ast.expr.SuperExpr;
import com.github.javaparser.ast.expr.SwitchExpr;
import com.github.javaparser.ast.expr.TextBlockLiteralExpr;
import com.github.javaparser.ast.expr.ThisExpr;
import com.github.javaparser.ast.expr.TypeExpr;
import com.github.javaparser.ast.expr.UnaryExpr;
import com.github.javaparser.ast.expr.VariableDeclarationExpr;
import com.github.javaparser.ast.modules.ModuleDeclaration;
import com.github.javaparser.ast.modules.ModuleExportsDirective;
import com.github.javaparser.ast.modules.ModuleOpensDirective;
import com.github.javaparser.ast.modules.ModuleProvidesDirective;
import com.github.javaparser.ast.modules.ModuleRequiresDirective;
import com.github.javaparser.ast.modules.ModuleUsesDirective;
import com.github.javaparser.ast.stmt.AssertStmt;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.stmt.BreakStmt;
import com.github.javaparser.ast.stmt.CatchClause;
import com.github.javaparser.ast.stmt.ContinueStmt;
import com.github.javaparser.ast.stmt.DoStmt;
import com.github.javaparser.ast.stmt.EmptyStmt;
import com.github.javaparser.ast.stmt.ExplicitConstructorInvocationStmt;
import com.github.javaparser.ast.stmt.ExpressionStmt;
import com.github.javaparser.ast.stmt.ForEachStmt;
import com.github.javaparser.ast.stmt.ForStmt;
import com.github.javaparser.ast.stmt.IfStmt;
import com.github.javaparser.ast.stmt.LabeledStmt;
import com.github.javaparser.ast.stmt.LocalClassDeclarationStmt;
import com.github.javaparser.ast.stmt.ReturnStmt;
import com.github.javaparser.ast.stmt.SwitchEntry;
import com.github.javaparser.ast.stmt.SwitchStmt;
import com.github.javaparser.ast.stmt.SynchronizedStmt;
import com.github.javaparser.ast.stmt.ThrowStmt;
import com.github.javaparser.ast.stmt.TryStmt;
import com.github.javaparser.ast.stmt.UnparsableStmt;
import com.github.javaparser.ast.stmt.WhileStmt;
import com.github.javaparser.ast.stmt.YieldStmt;
import com.github.javaparser.ast.type.ArrayType;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.ast.type.IntersectionType;
import com.github.javaparser.ast.type.PrimitiveType;
import com.github.javaparser.ast.type.TypeParameter;
import com.github.javaparser.ast.type.UnionType;
import com.github.javaparser.ast.type.UnknownType;
import com.github.javaparser.ast.type.VarType;
import com.github.javaparser.ast.type.VoidType;
import com.github.javaparser.ast.type.WildcardType;
import com.github.javaparser.ast.visitor.GenericVisitor;

public abstract class GenericVisitorAdapter<R, A>
implements GenericVisitor<R, A> {
    @Override
    public R visit(AnnotationDeclaration n, A arg) {
        Object result = n.getMembers().accept(this, arg);
        if (result != null) {
            return result;
        }
        result = n.getModifiers().accept(this, arg);
        if (result != null) {
            return result;
        }
        result = n.getName().accept(this, arg);
        if (result != null) {
            return result;
        }
        result = n.getAnnotations().accept(this, arg);
        if (result != null) {
            return result;
        }
        if (n.getComment().isPresent() && (result = n.getComment().get().accept(this, arg)) != null) {
            return result;
        }
        return null;
    }

    @Override
    public R visit(AnnotationMemberDeclaration n, A arg) {
        Object result;
        if (n.getDefaultValue().isPresent() && (result = n.getDefaultValue().get().accept(this, arg)) != null) {
            return result;
        }
        result = n.getModifiers().accept(this, arg);
        if (result != null) {
            return result;
        }
        result = n.getName().accept(this, arg);
        if (result != null) {
            return result;
        }
        result = n.getType().accept(this, arg);
        if (result != null) {
            return result;
        }
        result = n.getAnnotations().accept(this, arg);
        if (result != null) {
            return result;
        }
        if (n.getComment().isPresent() && (result = n.getComment().get().accept(this, arg)) != null) {
            return result;
        }
        return null;
    }

    @Override
    public R visit(ArrayAccessExpr n, A arg) {
        Object result = n.getIndex().accept(this, arg);
        if (result != null) {
            return result;
        }
        result = n.getName().accept(this, arg);
        if (result != null) {
            return result;
        }
        if (n.getComment().isPresent() && (result = n.getComment().get().accept(this, arg)) != null) {
            return result;
        }
        return null;
    }

    @Override
    public R visit(ArrayCreationExpr n, A arg) {
        Object result = n.getElementType().accept(this, arg);
        if (result != null) {
            return result;
        }
        if (n.getInitializer().isPresent() && (result = n.getInitializer().get().accept(this, arg)) != null) {
            return result;
        }
        result = n.getLevels().accept(this, arg);
        if (result != null) {
            return result;
        }
        if (n.getComment().isPresent() && (result = n.getComment().get().accept(this, arg)) != null) {
            return result;
        }
        return null;
    }

    @Override
    public R visit(ArrayInitializerExpr n, A arg) {
        Object result = n.getValues().accept(this, arg);
        if (result != null) {
            return result;
        }
        if (n.getComment().isPresent() && (result = n.getComment().get().accept(this, arg)) != null) {
            return result;
        }
        return null;
    }

    @Override
    public R visit(AssertStmt n, A arg) {
        Object result = n.getCheck().accept(this, arg);
        if (result != null) {
            return result;
        }
        if (n.getMessage().isPresent() && (result = n.getMessage().get().accept(this, arg)) != null) {
            return result;
        }
        if (n.getComment().isPresent() && (result = n.getComment().get().accept(this, arg)) != null) {
            return result;
        }
        return null;
    }

    @Override
    public R visit(AssignExpr n, A arg) {
        Object result = n.getTarget().accept(this, arg);
        if (result != null) {
            return result;
        }
        result = n.getValue().accept(this, arg);
        if (result != null) {
            return result;
        }
        if (n.getComment().isPresent() && (result = n.getComment().get().accept(this, arg)) != null) {
            return result;
        }
        return null;
    }

    @Override
    public R visit(BinaryExpr n, A arg) {
        Object result = n.getLeft().accept(this, arg);
        if (result != null) {
            return result;
        }
        result = n.getRight().accept(this, arg);
        if (result != null) {
            return result;
        }
        if (n.getComment().isPresent() && (result = n.getComment().get().accept(this, arg)) != null) {
            return result;
        }
        return null;
    }

    @Override
    public R visit(BlockStmt n, A arg) {
        Object result = n.getStatements().accept(this, arg);
        if (result != null) {
            return result;
        }
        if (n.getComment().isPresent() && (result = n.getComment().get().accept(this, arg)) != null) {
            return result;
        }
        return null;
    }

    @Override
    public R visit(BooleanLiteralExpr n, A arg) {
        Object result;
        if (n.getComment().isPresent() && (result = n.getComment().get().accept(this, arg)) != null) {
            return result;
        }
        return null;
    }

    @Override
    public R visit(BreakStmt n, A arg) {
        Object result;
        if (n.getLabel().isPresent() && (result = n.getLabel().get().accept(this, arg)) != null) {
            return result;
        }
        if (n.getComment().isPresent() && (result = n.getComment().get().accept(this, arg)) != null) {
            return result;
        }
        return null;
    }

    @Override
    public R visit(CastExpr n, A arg) {
        Object result = n.getExpression().accept(this, arg);
        if (result != null) {
            return result;
        }
        result = n.getType().accept(this, arg);
        if (result != null) {
            return result;
        }
        if (n.getComment().isPresent() && (result = n.getComment().get().accept(this, arg)) != null) {
            return result;
        }
        return null;
    }

    @Override
    public R visit(CatchClause n, A arg) {
        Object result = n.getBody().accept(this, arg);
        if (result != null) {
            return result;
        }
        result = n.getParameter().accept(this, arg);
        if (result != null) {
            return result;
        }
        if (n.getComment().isPresent() && (result = n.getComment().get().accept(this, arg)) != null) {
            return result;
        }
        return null;
    }

    @Override
    public R visit(CharLiteralExpr n, A arg) {
        Object result;
        if (n.getComment().isPresent() && (result = n.getComment().get().accept(this, arg)) != null) {
            return result;
        }
        return null;
    }

    @Override
    public R visit(ClassExpr n, A arg) {
        Object result = n.getType().accept(this, arg);
        if (result != null) {
            return result;
        }
        if (n.getComment().isPresent() && (result = n.getComment().get().accept(this, arg)) != null) {
            return result;
        }
        return null;
    }

    @Override
    public R visit(ClassOrInterfaceDeclaration n, A arg) {
        Object result = n.getExtendedTypes().accept(this, arg);
        if (result != null) {
            return result;
        }
        result = n.getImplementedTypes().accept(this, arg);
        if (result != null) {
            return result;
        }
        result = n.getTypeParameters().accept(this, arg);
        if (result != null) {
            return result;
        }
        result = n.getMembers().accept(this, arg);
        if (result != null) {
            return result;
        }
        result = n.getModifiers().accept(this, arg);
        if (result != null) {
            return result;
        }
        result = n.getName().accept(this, arg);
        if (result != null) {
            return result;
        }
        result = n.getAnnotations().accept(this, arg);
        if (result != null) {
            return result;
        }
        if (n.getComment().isPresent() && (result = n.getComment().get().accept(this, arg)) != null) {
            return result;
        }
        return null;
    }

    @Override
    public R visit(ClassOrInterfaceType n, A arg) {
        Object result = n.getName().accept(this, arg);
        if (result != null) {
            return result;
        }
        if (n.getScope().isPresent() && (result = n.getScope().get().accept(this, arg)) != null) {
            return result;
        }
        if (n.getTypeArguments().isPresent() && (result = n.getTypeArguments().get().accept(this, arg)) != null) {
            return result;
        }
        result = n.getAnnotations().accept(this, arg);
        if (result != null) {
            return result;
        }
        if (n.getComment().isPresent() && (result = n.getComment().get().accept(this, arg)) != null) {
            return result;
        }
        return null;
    }

    @Override
    public R visit(CompilationUnit n, A arg) {
        Object result = n.getImports().accept(this, arg);
        if (result != null) {
            return result;
        }
        if (n.getModule().isPresent() && (result = n.getModule().get().accept(this, arg)) != null) {
            return result;
        }
        if (n.getPackageDeclaration().isPresent() && (result = n.getPackageDeclaration().get().accept(this, arg)) != null) {
            return result;
        }
        result = n.getTypes().accept(this, arg);
        if (result != null) {
            return result;
        }
        if (n.getComment().isPresent() && (result = n.getComment().get().accept(this, arg)) != null) {
            return result;
        }
        return null;
    }

    @Override
    public R visit(ConditionalExpr n, A arg) {
        Object result = n.getCondition().accept(this, arg);
        if (result != null) {
            return result;
        }
        result = n.getElseExpr().accept(this, arg);
        if (result != null) {
            return result;
        }
        result = n.getThenExpr().accept(this, arg);
        if (result != null) {
            return result;
        }
        if (n.getComment().isPresent() && (result = n.getComment().get().accept(this, arg)) != null) {
            return result;
        }
        return null;
    }

    @Override
    public R visit(ConstructorDeclaration n, A arg) {
        Object result = n.getBody().accept(this, arg);
        if (result != null) {
            return result;
        }
        result = n.getModifiers().accept(this, arg);
        if (result != null) {
            return result;
        }
        result = n.getName().accept(this, arg);
        if (result != null) {
            return result;
        }
        result = n.getParameters().accept(this, arg);
        if (result != null) {
            return result;
        }
        if (n.getReceiverParameter().isPresent() && (result = n.getReceiverParameter().get().accept(this, arg)) != null) {
            return result;
        }
        result = n.getThrownExceptions().accept(this, arg);
        if (result != null) {
            return result;
        }
        result = n.getTypeParameters().accept(this, arg);
        if (result != null) {
            return result;
        }
        result = n.getAnnotations().accept(this, arg);
        if (result != null) {
            return result;
        }
        if (n.getComment().isPresent() && (result = n.getComment().get().accept(this, arg)) != null) {
            return result;
        }
        return null;
    }

    @Override
    public R visit(ContinueStmt n, A arg) {
        Object result;
        if (n.getLabel().isPresent() && (result = n.getLabel().get().accept(this, arg)) != null) {
            return result;
        }
        if (n.getComment().isPresent() && (result = n.getComment().get().accept(this, arg)) != null) {
            return result;
        }
        return null;
    }

    @Override
    public R visit(DoStmt n, A arg) {
        Object result = n.getBody().accept(this, arg);
        if (result != null) {
            return result;
        }
        result = n.getCondition().accept(this, arg);
        if (result != null) {
            return result;
        }
        if (n.getComment().isPresent() && (result = n.getComment().get().accept(this, arg)) != null) {
            return result;
        }
        return null;
    }

    @Override
    public R visit(DoubleLiteralExpr n, A arg) {
        Object result;
        if (n.getComment().isPresent() && (result = n.getComment().get().accept(this, arg)) != null) {
            return result;
        }
        return null;
    }

    @Override
    public R visit(EmptyStmt n, A arg) {
        Object result;
        if (n.getComment().isPresent() && (result = n.getComment().get().accept(this, arg)) != null) {
            return result;
        }
        return null;
    }

    @Override
    public R visit(EnclosedExpr n, A arg) {
        Object result = n.getInner().accept(this, arg);
        if (result != null) {
            return result;
        }
        if (n.getComment().isPresent() && (result = n.getComment().get().accept(this, arg)) != null) {
            return result;
        }
        return null;
    }

    @Override
    public R visit(EnumConstantDeclaration n, A arg) {
        Object result = n.getArguments().accept(this, arg);
        if (result != null) {
            return result;
        }
        result = n.getClassBody().accept(this, arg);
        if (result != null) {
            return result;
        }
        result = n.getName().accept(this, arg);
        if (result != null) {
            return result;
        }
        result = n.getAnnotations().accept(this, arg);
        if (result != null) {
            return result;
        }
        if (n.getComment().isPresent() && (result = n.getComment().get().accept(this, arg)) != null) {
            return result;
        }
        return null;
    }

    @Override
    public R visit(EnumDeclaration n, A arg) {
        Object result = n.getEntries().accept(this, arg);
        if (result != null) {
            return result;
        }
        result = n.getImplementedTypes().accept(this, arg);
        if (result != null) {
            return result;
        }
        result = n.getMembers().accept(this, arg);
        if (result != null) {
            return result;
        }
        result = n.getModifiers().accept(this, arg);
        if (result != null) {
            return result;
        }
        result = n.getName().accept(this, arg);
        if (result != null) {
            return result;
        }
        result = n.getAnnotations().accept(this, arg);
        if (result != null) {
            return result;
        }
        if (n.getComment().isPresent() && (result = n.getComment().get().accept(this, arg)) != null) {
            return result;
        }
        return null;
    }

    @Override
    public R visit(ExplicitConstructorInvocationStmt n, A arg) {
        Object result = n.getArguments().accept(this, arg);
        if (result != null) {
            return result;
        }
        if (n.getExpression().isPresent() && (result = n.getExpression().get().accept(this, arg)) != null) {
            return result;
        }
        if (n.getTypeArguments().isPresent() && (result = n.getTypeArguments().get().accept(this, arg)) != null) {
            return result;
        }
        if (n.getComment().isPresent() && (result = n.getComment().get().accept(this, arg)) != null) {
            return result;
        }
        return null;
    }

    @Override
    public R visit(ExpressionStmt n, A arg) {
        Object result = n.getExpression().accept(this, arg);
        if (result != null) {
            return result;
        }
        if (n.getComment().isPresent() && (result = n.getComment().get().accept(this, arg)) != null) {
            return result;
        }
        return null;
    }

    @Override
    public R visit(FieldAccessExpr n, A arg) {
        Object result = n.getName().accept(this, arg);
        if (result != null) {
            return result;
        }
        result = n.getScope().accept(this, arg);
        if (result != null) {
            return result;
        }
        if (n.getTypeArguments().isPresent() && (result = n.getTypeArguments().get().accept(this, arg)) != null) {
            return result;
        }
        if (n.getComment().isPresent() && (result = n.getComment().get().accept(this, arg)) != null) {
            return result;
        }
        return null;
    }

    @Override
    public R visit(FieldDeclaration n, A arg) {
        Object result = n.getModifiers().accept(this, arg);
        if (result != null) {
            return result;
        }
        result = n.getVariables().accept(this, arg);
        if (result != null) {
            return result;
        }
        result = n.getAnnotations().accept(this, arg);
        if (result != null) {
            return result;
        }
        if (n.getComment().isPresent() && (result = n.getComment().get().accept(this, arg)) != null) {
            return result;
        }
        return null;
    }

    @Override
    public R visit(ForEachStmt n, A arg) {
        Object result = n.getBody().accept(this, arg);
        if (result != null) {
            return result;
        }
        result = n.getIterable().accept(this, arg);
        if (result != null) {
            return result;
        }
        result = n.getVariable().accept(this, arg);
        if (result != null) {
            return result;
        }
        if (n.getComment().isPresent() && (result = n.getComment().get().accept(this, arg)) != null) {
            return result;
        }
        return null;
    }

    @Override
    public R visit(ForStmt n, A arg) {
        Object result = n.getBody().accept(this, arg);
        if (result != null) {
            return result;
        }
        if (n.getCompare().isPresent() && (result = n.getCompare().get().accept(this, arg)) != null) {
            return result;
        }
        result = n.getInitialization().accept(this, arg);
        if (result != null) {
            return result;
        }
        result = n.getUpdate().accept(this, arg);
        if (result != null) {
            return result;
        }
        if (n.getComment().isPresent() && (result = n.getComment().get().accept(this, arg)) != null) {
            return result;
        }
        return null;
    }

    @Override
    public R visit(IfStmt n, A arg) {
        Object result = n.getCondition().accept(this, arg);
        if (result != null) {
            return result;
        }
        if (n.getElseStmt().isPresent() && (result = n.getElseStmt().get().accept(this, arg)) != null) {
            return result;
        }
        result = n.getThenStmt().accept(this, arg);
        if (result != null) {
            return result;
        }
        if (n.getComment().isPresent() && (result = n.getComment().get().accept(this, arg)) != null) {
            return result;
        }
        return null;
    }

    @Override
    public R visit(InitializerDeclaration n, A arg) {
        Object result = n.getBody().accept(this, arg);
        if (result != null) {
            return result;
        }
        result = n.getAnnotations().accept(this, arg);
        if (result != null) {
            return result;
        }
        if (n.getComment().isPresent() && (result = n.getComment().get().accept(this, arg)) != null) {
            return result;
        }
        return null;
    }

    @Override
    public R visit(InstanceOfExpr n, A arg) {
        Object result = n.getExpression().accept(this, arg);
        if (result != null) {
            return result;
        }
        if (n.getPattern().isPresent() && (result = n.getPattern().get().accept(this, arg)) != null) {
            return result;
        }
        result = n.getType().accept(this, arg);
        if (result != null) {
            return result;
        }
        if (n.getComment().isPresent() && (result = n.getComment().get().accept(this, arg)) != null) {
            return result;
        }
        return null;
    }

    @Override
    public R visit(IntegerLiteralExpr n, A arg) {
        Object result;
        if (n.getComment().isPresent() && (result = n.getComment().get().accept(this, arg)) != null) {
            return result;
        }
        return null;
    }

    @Override
    public R visit(JavadocComment n, A arg) {
        Object result;
        if (n.getComment().isPresent() && (result = n.getComment().get().accept(this, arg)) != null) {
            return result;
        }
        return null;
    }

    @Override
    public R visit(LabeledStmt n, A arg) {
        Object result = n.getLabel().accept(this, arg);
        if (result != null) {
            return result;
        }
        result = n.getStatement().accept(this, arg);
        if (result != null) {
            return result;
        }
        if (n.getComment().isPresent() && (result = n.getComment().get().accept(this, arg)) != null) {
            return result;
        }
        return null;
    }

    @Override
    public R visit(LongLiteralExpr n, A arg) {
        Object result;
        if (n.getComment().isPresent() && (result = n.getComment().get().accept(this, arg)) != null) {
            return result;
        }
        return null;
    }

    @Override
    public R visit(MarkerAnnotationExpr n, A arg) {
        Object result = n.getName().accept(this, arg);
        if (result != null) {
            return result;
        }
        if (n.getComment().isPresent() && (result = n.getComment().get().accept(this, arg)) != null) {
            return result;
        }
        return null;
    }

    @Override
    public R visit(MemberValuePair n, A arg) {
        Object result = n.getName().accept(this, arg);
        if (result != null) {
            return result;
        }
        result = n.getValue().accept(this, arg);
        if (result != null) {
            return result;
        }
        if (n.getComment().isPresent() && (result = n.getComment().get().accept(this, arg)) != null) {
            return result;
        }
        return null;
    }

    @Override
    public R visit(MethodCallExpr n, A arg) {
        Object result = n.getArguments().accept(this, arg);
        if (result != null) {
            return result;
        }
        result = n.getName().accept(this, arg);
        if (result != null) {
            return result;
        }
        if (n.hasScope() && (result = n.getScope().get().accept(this, arg)) != null) {
            return result;
        }
        if (n.getTypeArguments().isPresent() && (result = n.getTypeArguments().get().accept(this, arg)) != null) {
            return result;
        }
        if (n.getComment().isPresent() && (result = n.getComment().get().accept(this, arg)) != null) {
            return result;
        }
        return null;
    }

    @Override
    public R visit(MethodDeclaration n, A arg) {
        Object result;
        if (n.getBody().isPresent() && (result = n.getBody().get().accept(this, arg)) != null) {
            return result;
        }
        result = n.getType().accept(this, arg);
        if (result != null) {
            return result;
        }
        result = n.getModifiers().accept(this, arg);
        if (result != null) {
            return result;
        }
        result = n.getName().accept(this, arg);
        if (result != null) {
            return result;
        }
        result = n.getParameters().accept(this, arg);
        if (result != null) {
            return result;
        }
        if (n.getReceiverParameter().isPresent() && (result = n.getReceiverParameter().get().accept(this, arg)) != null) {
            return result;
        }
        result = n.getThrownExceptions().accept(this, arg);
        if (result != null) {
            return result;
        }
        result = n.getTypeParameters().accept(this, arg);
        if (result != null) {
            return result;
        }
        result = n.getAnnotations().accept(this, arg);
        if (result != null) {
            return result;
        }
        if (n.getComment().isPresent() && (result = n.getComment().get().accept(this, arg)) != null) {
            return result;
        }
        return null;
    }

    @Override
    public R visit(NameExpr n, A arg) {
        Object result = n.getName().accept(this, arg);
        if (result != null) {
            return result;
        }
        if (n.getComment().isPresent() && (result = n.getComment().get().accept(this, arg)) != null) {
            return result;
        }
        return null;
    }

    @Override
    public R visit(NormalAnnotationExpr n, A arg) {
        Object result = n.getPairs().accept(this, arg);
        if (result != null) {
            return result;
        }
        result = n.getName().accept(this, arg);
        if (result != null) {
            return result;
        }
        if (n.getComment().isPresent() && (result = n.getComment().get().accept(this, arg)) != null) {
            return result;
        }
        return null;
    }

    @Override
    public R visit(NullLiteralExpr n, A arg) {
        Object result;
        if (n.getComment().isPresent() && (result = n.getComment().get().accept(this, arg)) != null) {
            return result;
        }
        return null;
    }

    @Override
    public R visit(ObjectCreationExpr n, A arg) {
        Object result;
        if (n.getAnonymousClassBody().isPresent() && (result = n.getAnonymousClassBody().get().accept(this, arg)) != null) {
            return result;
        }
        result = n.getArguments().accept(this, arg);
        if (result != null) {
            return result;
        }
        if (n.hasScope() && (result = n.getScope().get().accept(this, arg)) != null) {
            return result;
        }
        result = n.getType().accept(this, arg);
        if (result != null) {
            return result;
        }
        if (n.getTypeArguments().isPresent() && (result = n.getTypeArguments().get().accept(this, arg)) != null) {
            return result;
        }
        if (n.getComment().isPresent() && (result = n.getComment().get().accept(this, arg)) != null) {
            return result;
        }
        return null;
    }

    @Override
    public R visit(PackageDeclaration n, A arg) {
        Object result = n.getAnnotations().accept(this, arg);
        if (result != null) {
            return result;
        }
        result = n.getName().accept(this, arg);
        if (result != null) {
            return result;
        }
        if (n.getComment().isPresent() && (result = n.getComment().get().accept(this, arg)) != null) {
            return result;
        }
        return null;
    }

    @Override
    public R visit(Parameter n, A arg) {
        Object result = n.getAnnotations().accept(this, arg);
        if (result != null) {
            return result;
        }
        result = n.getModifiers().accept(this, arg);
        if (result != null) {
            return result;
        }
        result = n.getName().accept(this, arg);
        if (result != null) {
            return result;
        }
        result = n.getType().accept(this, arg);
        if (result != null) {
            return result;
        }
        result = n.getVarArgsAnnotations().accept(this, arg);
        if (result != null) {
            return result;
        }
        if (n.getComment().isPresent() && (result = n.getComment().get().accept(this, arg)) != null) {
            return result;
        }
        return null;
    }

    @Override
    public R visit(PrimitiveType n, A arg) {
        Object result = n.getAnnotations().accept(this, arg);
        if (result != null) {
            return result;
        }
        if (n.getComment().isPresent() && (result = n.getComment().get().accept(this, arg)) != null) {
            return result;
        }
        return null;
    }

    @Override
    public R visit(Name n, A arg) {
        Object result;
        if (n.getQualifier().isPresent() && (result = n.getQualifier().get().accept(this, arg)) != null) {
            return result;
        }
        if (n.getComment().isPresent() && (result = n.getComment().get().accept(this, arg)) != null) {
            return result;
        }
        return null;
    }

    @Override
    public R visit(SimpleName n, A arg) {
        Object result;
        if (n.getComment().isPresent() && (result = n.getComment().get().accept(this, arg)) != null) {
            return result;
        }
        return null;
    }

    @Override
    public R visit(ArrayType n, A arg) {
        Object result = n.getComponentType().accept(this, arg);
        if (result != null) {
            return result;
        }
        result = n.getAnnotations().accept(this, arg);
        if (result != null) {
            return result;
        }
        if (n.getComment().isPresent() && (result = n.getComment().get().accept(this, arg)) != null) {
            return result;
        }
        return null;
    }

    @Override
    public R visit(ArrayCreationLevel n, A arg) {
        Object result = n.getAnnotations().accept(this, arg);
        if (result != null) {
            return result;
        }
        if (n.getDimension().isPresent() && (result = n.getDimension().get().accept(this, arg)) != null) {
            return result;
        }
        if (n.getComment().isPresent() && (result = n.getComment().get().accept(this, arg)) != null) {
            return result;
        }
        return null;
    }

    @Override
    public R visit(IntersectionType n, A arg) {
        Object result = n.getElements().accept(this, arg);
        if (result != null) {
            return result;
        }
        result = n.getAnnotations().accept(this, arg);
        if (result != null) {
            return result;
        }
        if (n.getComment().isPresent() && (result = n.getComment().get().accept(this, arg)) != null) {
            return result;
        }
        return null;
    }

    @Override
    public R visit(UnionType n, A arg) {
        Object result = n.getElements().accept(this, arg);
        if (result != null) {
            return result;
        }
        result = n.getAnnotations().accept(this, arg);
        if (result != null) {
            return result;
        }
        if (n.getComment().isPresent() && (result = n.getComment().get().accept(this, arg)) != null) {
            return result;
        }
        return null;
    }

    @Override
    public R visit(ReturnStmt n, A arg) {
        Object result;
        if (n.getExpression().isPresent() && (result = n.getExpression().get().accept(this, arg)) != null) {
            return result;
        }
        if (n.getComment().isPresent() && (result = n.getComment().get().accept(this, arg)) != null) {
            return result;
        }
        return null;
    }

    @Override
    public R visit(SingleMemberAnnotationExpr n, A arg) {
        Object result = n.getMemberValue().accept(this, arg);
        if (result != null) {
            return result;
        }
        result = n.getName().accept(this, arg);
        if (result != null) {
            return result;
        }
        if (n.getComment().isPresent() && (result = n.getComment().get().accept(this, arg)) != null) {
            return result;
        }
        return null;
    }

    @Override
    public R visit(StringLiteralExpr n, A arg) {
        Object result;
        if (n.getComment().isPresent() && (result = n.getComment().get().accept(this, arg)) != null) {
            return result;
        }
        return null;
    }

    @Override
    public R visit(SuperExpr n, A arg) {
        Object result;
        if (n.getTypeName().isPresent() && (result = n.getTypeName().get().accept(this, arg)) != null) {
            return result;
        }
        if (n.getComment().isPresent() && (result = n.getComment().get().accept(this, arg)) != null) {
            return result;
        }
        return null;
    }

    @Override
    public R visit(SwitchEntry n, A arg) {
        Object result = n.getLabels().accept(this, arg);
        if (result != null) {
            return result;
        }
        result = n.getStatements().accept(this, arg);
        if (result != null) {
            return result;
        }
        if (n.getComment().isPresent() && (result = n.getComment().get().accept(this, arg)) != null) {
            return result;
        }
        return null;
    }

    @Override
    public R visit(SwitchStmt n, A arg) {
        Object result = n.getEntries().accept(this, arg);
        if (result != null) {
            return result;
        }
        result = n.getSelector().accept(this, arg);
        if (result != null) {
            return result;
        }
        if (n.getComment().isPresent() && (result = n.getComment().get().accept(this, arg)) != null) {
            return result;
        }
        return null;
    }

    @Override
    public R visit(SynchronizedStmt n, A arg) {
        Object result = n.getBody().accept(this, arg);
        if (result != null) {
            return result;
        }
        result = n.getExpression().accept(this, arg);
        if (result != null) {
            return result;
        }
        if (n.getComment().isPresent() && (result = n.getComment().get().accept(this, arg)) != null) {
            return result;
        }
        return null;
    }

    @Override
    public R visit(ThisExpr n, A arg) {
        Object result;
        if (n.getTypeName().isPresent() && (result = n.getTypeName().get().accept(this, arg)) != null) {
            return result;
        }
        if (n.getComment().isPresent() && (result = n.getComment().get().accept(this, arg)) != null) {
            return result;
        }
        return null;
    }

    @Override
    public R visit(ThrowStmt n, A arg) {
        Object result = n.getExpression().accept(this, arg);
        if (result != null) {
            return result;
        }
        if (n.getComment().isPresent() && (result = n.getComment().get().accept(this, arg)) != null) {
            return result;
        }
        return null;
    }

    @Override
    public R visit(TryStmt n, A arg) {
        Object result = n.getCatchClauses().accept(this, arg);
        if (result != null) {
            return result;
        }
        if (n.getFinallyBlock().isPresent() && (result = n.getFinallyBlock().get().accept(this, arg)) != null) {
            return result;
        }
        result = n.getResources().accept(this, arg);
        if (result != null) {
            return result;
        }
        result = n.getTryBlock().accept(this, arg);
        if (result != null) {
            return result;
        }
        if (n.getComment().isPresent() && (result = n.getComment().get().accept(this, arg)) != null) {
            return result;
        }
        return null;
    }

    @Override
    public R visit(LocalClassDeclarationStmt n, A arg) {
        Object result = n.getClassDeclaration().accept(this, arg);
        if (result != null) {
            return result;
        }
        if (n.getComment().isPresent() && (result = n.getComment().get().accept(this, arg)) != null) {
            return result;
        }
        return null;
    }

    @Override
    public R visit(TypeParameter n, A arg) {
        Object result = n.getName().accept(this, arg);
        if (result != null) {
            return result;
        }
        result = n.getTypeBound().accept(this, arg);
        if (result != null) {
            return result;
        }
        result = n.getAnnotations().accept(this, arg);
        if (result != null) {
            return result;
        }
        if (n.getComment().isPresent() && (result = n.getComment().get().accept(this, arg)) != null) {
            return result;
        }
        return null;
    }

    @Override
    public R visit(UnaryExpr n, A arg) {
        Object result = n.getExpression().accept(this, arg);
        if (result != null) {
            return result;
        }
        if (n.getComment().isPresent() && (result = n.getComment().get().accept(this, arg)) != null) {
            return result;
        }
        return null;
    }

    @Override
    public R visit(UnknownType n, A arg) {
        Object result = n.getAnnotations().accept(this, arg);
        if (result != null) {
            return result;
        }
        if (n.getComment().isPresent() && (result = n.getComment().get().accept(this, arg)) != null) {
            return result;
        }
        return null;
    }

    @Override
    public R visit(VariableDeclarationExpr n, A arg) {
        Object result = n.getAnnotations().accept(this, arg);
        if (result != null) {
            return result;
        }
        result = n.getModifiers().accept(this, arg);
        if (result != null) {
            return result;
        }
        result = n.getVariables().accept(this, arg);
        if (result != null) {
            return result;
        }
        if (n.getComment().isPresent() && (result = n.getComment().get().accept(this, arg)) != null) {
            return result;
        }
        return null;
    }

    @Override
    public R visit(VariableDeclarator n, A arg) {
        Object result;
        if (n.getInitializer().isPresent() && (result = n.getInitializer().get().accept(this, arg)) != null) {
            return result;
        }
        result = n.getName().accept(this, arg);
        if (result != null) {
            return result;
        }
        result = n.getType().accept(this, arg);
        if (result != null) {
            return result;
        }
        if (n.getComment().isPresent() && (result = n.getComment().get().accept(this, arg)) != null) {
            return result;
        }
        return null;
    }

    @Override
    public R visit(VoidType n, A arg) {
        Object result = n.getAnnotations().accept(this, arg);
        if (result != null) {
            return result;
        }
        if (n.getComment().isPresent() && (result = n.getComment().get().accept(this, arg)) != null) {
            return result;
        }
        return null;
    }

    @Override
    public R visit(WhileStmt n, A arg) {
        Object result = n.getBody().accept(this, arg);
        if (result != null) {
            return result;
        }
        result = n.getCondition().accept(this, arg);
        if (result != null) {
            return result;
        }
        if (n.getComment().isPresent() && (result = n.getComment().get().accept(this, arg)) != null) {
            return result;
        }
        return null;
    }

    @Override
    public R visit(WildcardType n, A arg) {
        Object result;
        if (n.getExtendedType().isPresent() && (result = n.getExtendedType().get().accept(this, arg)) != null) {
            return result;
        }
        if (n.getSuperType().isPresent() && (result = n.getSuperType().get().accept(this, arg)) != null) {
            return result;
        }
        result = n.getAnnotations().accept(this, arg);
        if (result != null) {
            return result;
        }
        if (n.getComment().isPresent() && (result = n.getComment().get().accept(this, arg)) != null) {
            return result;
        }
        return null;
    }

    @Override
    public R visit(LambdaExpr n, A arg) {
        Object result = n.getBody().accept(this, arg);
        if (result != null) {
            return result;
        }
        result = n.getParameters().accept(this, arg);
        if (result != null) {
            return result;
        }
        if (n.getComment().isPresent() && (result = n.getComment().get().accept(this, arg)) != null) {
            return result;
        }
        return null;
    }

    @Override
    public R visit(MethodReferenceExpr n, A arg) {
        Object result = n.getScope().accept(this, arg);
        if (result != null) {
            return result;
        }
        if (n.getTypeArguments().isPresent() && (result = n.getTypeArguments().get().accept(this, arg)) != null) {
            return result;
        }
        if (n.getComment().isPresent() && (result = n.getComment().get().accept(this, arg)) != null) {
            return result;
        }
        return null;
    }

    @Override
    public R visit(TypeExpr n, A arg) {
        Object result = n.getType().accept(this, arg);
        if (result != null) {
            return result;
        }
        if (n.getComment().isPresent() && (result = n.getComment().get().accept(this, arg)) != null) {
            return result;
        }
        return null;
    }

    @Override
    public R visit(ImportDeclaration n, A arg) {
        Object result = n.getName().accept(this, arg);
        if (result != null) {
            return result;
        }
        if (n.getComment().isPresent() && (result = n.getComment().get().accept(this, arg)) != null) {
            return result;
        }
        return null;
    }

    @Override
    public R visit(BlockComment n, A arg) {
        Object result;
        if (n.getComment().isPresent() && (result = n.getComment().get().accept(this, arg)) != null) {
            return result;
        }
        return null;
    }

    @Override
    public R visit(LineComment n, A arg) {
        Object result;
        if (n.getComment().isPresent() && (result = n.getComment().get().accept(this, arg)) != null) {
            return result;
        }
        return null;
    }

    @Override
    public R visit(NodeList n, A arg) {
        for (Object v : n) {
            Object result = ((Node)v).accept(this, arg);
            if (result == null) continue;
            return result;
        }
        return null;
    }

    @Override
    public R visit(ModuleDeclaration n, A arg) {
        Object result = n.getAnnotations().accept(this, arg);
        if (result != null) {
            return result;
        }
        result = n.getDirectives().accept(this, arg);
        if (result != null) {
            return result;
        }
        result = n.getName().accept(this, arg);
        if (result != null) {
            return result;
        }
        if (n.getComment().isPresent() && (result = n.getComment().get().accept(this, arg)) != null) {
            return result;
        }
        return null;
    }

    @Override
    public R visit(ModuleRequiresDirective n, A arg) {
        Object result = n.getModifiers().accept(this, arg);
        if (result != null) {
            return result;
        }
        result = n.getName().accept(this, arg);
        if (result != null) {
            return result;
        }
        if (n.getComment().isPresent() && (result = n.getComment().get().accept(this, arg)) != null) {
            return result;
        }
        return null;
    }

    @Override
    public R visit(ModuleExportsDirective n, A arg) {
        Object result = n.getModuleNames().accept(this, arg);
        if (result != null) {
            return result;
        }
        result = n.getName().accept(this, arg);
        if (result != null) {
            return result;
        }
        if (n.getComment().isPresent() && (result = n.getComment().get().accept(this, arg)) != null) {
            return result;
        }
        return null;
    }

    @Override
    public R visit(ModuleProvidesDirective n, A arg) {
        Object result = n.getName().accept(this, arg);
        if (result != null) {
            return result;
        }
        result = n.getWith().accept(this, arg);
        if (result != null) {
            return result;
        }
        if (n.getComment().isPresent() && (result = n.getComment().get().accept(this, arg)) != null) {
            return result;
        }
        return null;
    }

    @Override
    public R visit(ModuleUsesDirective n, A arg) {
        Object result = n.getName().accept(this, arg);
        if (result != null) {
            return result;
        }
        if (n.getComment().isPresent() && (result = n.getComment().get().accept(this, arg)) != null) {
            return result;
        }
        return null;
    }

    @Override
    public R visit(ModuleOpensDirective n, A arg) {
        Object result = n.getModuleNames().accept(this, arg);
        if (result != null) {
            return result;
        }
        result = n.getName().accept(this, arg);
        if (result != null) {
            return result;
        }
        if (n.getComment().isPresent() && (result = n.getComment().get().accept(this, arg)) != null) {
            return result;
        }
        return null;
    }

    @Override
    public R visit(UnparsableStmt n, A arg) {
        Object result;
        if (n.getComment().isPresent() && (result = n.getComment().get().accept(this, arg)) != null) {
            return result;
        }
        return null;
    }

    @Override
    public R visit(ReceiverParameter n, A arg) {
        Object result = n.getAnnotations().accept(this, arg);
        if (result != null) {
            return result;
        }
        result = n.getName().accept(this, arg);
        if (result != null) {
            return result;
        }
        result = n.getType().accept(this, arg);
        if (result != null) {
            return result;
        }
        if (n.getComment().isPresent() && (result = n.getComment().get().accept(this, arg)) != null) {
            return result;
        }
        return null;
    }

    @Override
    public R visit(VarType n, A arg) {
        Object result = n.getAnnotations().accept(this, arg);
        if (result != null) {
            return result;
        }
        if (n.getComment().isPresent() && (result = n.getComment().get().accept(this, arg)) != null) {
            return result;
        }
        return null;
    }

    @Override
    public R visit(Modifier n, A arg) {
        Object result;
        if (n.getComment().isPresent() && (result = n.getComment().get().accept(this, arg)) != null) {
            return result;
        }
        return null;
    }

    @Override
    public R visit(SwitchExpr n, A arg) {
        Object result = n.getEntries().accept(this, arg);
        if (result != null) {
            return result;
        }
        result = n.getSelector().accept(this, arg);
        if (result != null) {
            return result;
        }
        if (n.getComment().isPresent() && (result = n.getComment().get().accept(this, arg)) != null) {
            return result;
        }
        return null;
    }

    @Override
    public R visit(YieldStmt n, A arg) {
        Object result = n.getExpression().accept(this, arg);
        if (result != null) {
            return result;
        }
        if (n.getComment().isPresent() && (result = n.getComment().get().accept(this, arg)) != null) {
            return result;
        }
        return null;
    }

    @Override
    public R visit(TextBlockLiteralExpr n, A arg) {
        Object result;
        if (n.getComment().isPresent() && (result = n.getComment().get().accept(this, arg)) != null) {
            return result;
        }
        return null;
    }

    @Override
    public R visit(PatternExpr n, A arg) {
        Object result = n.getName().accept(this, arg);
        if (result != null) {
            return result;
        }
        result = n.getType().accept(this, arg);
        if (result != null) {
            return result;
        }
        if (n.getComment().isPresent() && (result = n.getComment().get().accept(this, arg)) != null) {
            return result;
        }
        return null;
    }
}

