/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.ast.visitor;

import com.github.javaparser.ast.ArrayCreationLevel;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.ImportDeclaration;
import com.github.javaparser.ast.Modifier;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public abstract class GenericListVisitorAdapter<R, A>
implements GenericVisitor<List<R>, A> {
    @Override
    public List<R> visit(AnnotationDeclaration n, A arg) {
        ArrayList result = new ArrayList();
        List tmp = (List)n.getMembers().accept(this, arg);
        if (tmp != null) {
            result.addAll(tmp);
        }
        if ((tmp = (List)n.getModifiers().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        if ((tmp = (List)n.getName().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        if ((tmp = (List)n.getAnnotations().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        if (n.getComment().isPresent() && (tmp = (List)n.getComment().get().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        return result;
    }

    @Override
    public List<R> visit(AnnotationMemberDeclaration n, A arg) {
        List tmp;
        ArrayList result = new ArrayList();
        if (n.getDefaultValue().isPresent() && (tmp = (List)n.getDefaultValue().get().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        if ((tmp = (List)n.getModifiers().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        if ((tmp = (List)n.getName().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        if ((tmp = (List)n.getType().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        if ((tmp = (List)n.getAnnotations().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        if (n.getComment().isPresent() && (tmp = (List)n.getComment().get().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        return result;
    }

    @Override
    public List<R> visit(ArrayAccessExpr n, A arg) {
        ArrayList result = new ArrayList();
        List tmp = (List)n.getIndex().accept(this, arg);
        if (tmp != null) {
            result.addAll(tmp);
        }
        if ((tmp = (List)n.getName().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        if (n.getComment().isPresent() && (tmp = (List)n.getComment().get().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        return result;
    }

    @Override
    public List<R> visit(ArrayCreationExpr n, A arg) {
        ArrayList result = new ArrayList();
        List tmp = (List)n.getElementType().accept(this, arg);
        if (tmp != null) {
            result.addAll(tmp);
        }
        if (n.getInitializer().isPresent() && (tmp = (List)n.getInitializer().get().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        if ((tmp = (List)n.getLevels().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        if (n.getComment().isPresent() && (tmp = (List)n.getComment().get().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        return result;
    }

    @Override
    public List<R> visit(ArrayCreationLevel n, A arg) {
        ArrayList result = new ArrayList();
        List tmp = (List)n.getAnnotations().accept(this, arg);
        if (tmp != null) {
            result.addAll(tmp);
        }
        if (n.getDimension().isPresent() && (tmp = (List)n.getDimension().get().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        if (n.getComment().isPresent() && (tmp = (List)n.getComment().get().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        return result;
    }

    @Override
    public List<R> visit(ArrayInitializerExpr n, A arg) {
        ArrayList result = new ArrayList();
        List tmp = (List)n.getValues().accept(this, arg);
        if (tmp != null) {
            result.addAll(tmp);
        }
        if (n.getComment().isPresent() && (tmp = (List)n.getComment().get().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        return result;
    }

    @Override
    public List<R> visit(ArrayType n, A arg) {
        ArrayList result = new ArrayList();
        List tmp = (List)n.getComponentType().accept(this, arg);
        if (tmp != null) {
            result.addAll(tmp);
        }
        if ((tmp = (List)n.getAnnotations().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        if (n.getComment().isPresent() && (tmp = (List)n.getComment().get().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        return result;
    }

    @Override
    public List<R> visit(AssertStmt n, A arg) {
        ArrayList result = new ArrayList();
        List tmp = (List)n.getCheck().accept(this, arg);
        if (tmp != null) {
            result.addAll(tmp);
        }
        if (n.getMessage().isPresent() && (tmp = (List)n.getMessage().get().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        if (n.getComment().isPresent() && (tmp = (List)n.getComment().get().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        return result;
    }

    @Override
    public List<R> visit(AssignExpr n, A arg) {
        ArrayList result = new ArrayList();
        List tmp = (List)n.getTarget().accept(this, arg);
        if (tmp != null) {
            result.addAll(tmp);
        }
        if ((tmp = (List)n.getValue().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        if (n.getComment().isPresent() && (tmp = (List)n.getComment().get().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        return result;
    }

    @Override
    public List<R> visit(BinaryExpr n, A arg) {
        ArrayList result = new ArrayList();
        List tmp = (List)n.getLeft().accept(this, arg);
        if (tmp != null) {
            result.addAll(tmp);
        }
        if ((tmp = (List)n.getRight().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        if (n.getComment().isPresent() && (tmp = (List)n.getComment().get().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        return result;
    }

    @Override
    public List<R> visit(BlockComment n, A arg) {
        List tmp;
        ArrayList result = new ArrayList();
        if (n.getComment().isPresent() && (tmp = (List)n.getComment().get().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        return result;
    }

    @Override
    public List<R> visit(BlockStmt n, A arg) {
        ArrayList result = new ArrayList();
        List tmp = (List)n.getStatements().accept(this, arg);
        if (tmp != null) {
            result.addAll(tmp);
        }
        if (n.getComment().isPresent() && (tmp = (List)n.getComment().get().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        return result;
    }

    @Override
    public List<R> visit(BooleanLiteralExpr n, A arg) {
        List tmp;
        ArrayList result = new ArrayList();
        if (n.getComment().isPresent() && (tmp = (List)n.getComment().get().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        return result;
    }

    @Override
    public List<R> visit(BreakStmt n, A arg) {
        List tmp;
        ArrayList result = new ArrayList();
        if (n.getLabel().isPresent() && (tmp = (List)n.getLabel().get().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        if (n.getComment().isPresent() && (tmp = (List)n.getComment().get().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        return result;
    }

    @Override
    public List<R> visit(CastExpr n, A arg) {
        ArrayList result = new ArrayList();
        List tmp = (List)n.getExpression().accept(this, arg);
        if (tmp != null) {
            result.addAll(tmp);
        }
        if ((tmp = (List)n.getType().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        if (n.getComment().isPresent() && (tmp = (List)n.getComment().get().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        return result;
    }

    @Override
    public List<R> visit(CatchClause n, A arg) {
        ArrayList result = new ArrayList();
        List tmp = (List)n.getBody().accept(this, arg);
        if (tmp != null) {
            result.addAll(tmp);
        }
        if ((tmp = (List)n.getParameter().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        if (n.getComment().isPresent() && (tmp = (List)n.getComment().get().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        return result;
    }

    @Override
    public List<R> visit(CharLiteralExpr n, A arg) {
        List tmp;
        ArrayList result = new ArrayList();
        if (n.getComment().isPresent() && (tmp = (List)n.getComment().get().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        return result;
    }

    @Override
    public List<R> visit(ClassExpr n, A arg) {
        ArrayList result = new ArrayList();
        List tmp = (List)n.getType().accept(this, arg);
        if (tmp != null) {
            result.addAll(tmp);
        }
        if (n.getComment().isPresent() && (tmp = (List)n.getComment().get().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        return result;
    }

    @Override
    public List<R> visit(ClassOrInterfaceDeclaration n, A arg) {
        ArrayList result = new ArrayList();
        List tmp = (List)n.getExtendedTypes().accept(this, arg);
        if (tmp != null) {
            result.addAll(tmp);
        }
        if ((tmp = (List)n.getImplementedTypes().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        if ((tmp = (List)n.getTypeParameters().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        if ((tmp = (List)n.getMembers().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        if ((tmp = (List)n.getModifiers().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        if ((tmp = (List)n.getName().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        if ((tmp = (List)n.getAnnotations().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        if (n.getComment().isPresent() && (tmp = (List)n.getComment().get().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        return result;
    }

    @Override
    public List<R> visit(ClassOrInterfaceType n, A arg) {
        ArrayList result = new ArrayList();
        List tmp = (List)n.getName().accept(this, arg);
        if (tmp != null) {
            result.addAll(tmp);
        }
        if (n.getScope().isPresent() && (tmp = (List)n.getScope().get().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        if (n.getTypeArguments().isPresent() && (tmp = (List)n.getTypeArguments().get().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        if ((tmp = (List)n.getAnnotations().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        if (n.getComment().isPresent() && (tmp = (List)n.getComment().get().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        return result;
    }

    @Override
    public List<R> visit(CompilationUnit n, A arg) {
        ArrayList result = new ArrayList();
        List tmp = (List)n.getImports().accept(this, arg);
        if (tmp != null) {
            result.addAll(tmp);
        }
        if (n.getModule().isPresent() && (tmp = (List)n.getModule().get().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        if (n.getPackageDeclaration().isPresent() && (tmp = (List)n.getPackageDeclaration().get().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        if ((tmp = (List)n.getTypes().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        if (n.getComment().isPresent() && (tmp = (List)n.getComment().get().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        return result;
    }

    @Override
    public List<R> visit(ConditionalExpr n, A arg) {
        ArrayList result = new ArrayList();
        List tmp = (List)n.getCondition().accept(this, arg);
        if (tmp != null) {
            result.addAll(tmp);
        }
        if ((tmp = (List)n.getElseExpr().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        if ((tmp = (List)n.getThenExpr().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        if (n.getComment().isPresent() && (tmp = (List)n.getComment().get().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        return result;
    }

    @Override
    public List<R> visit(ConstructorDeclaration n, A arg) {
        ArrayList result = new ArrayList();
        List tmp = (List)n.getBody().accept(this, arg);
        if (tmp != null) {
            result.addAll(tmp);
        }
        if ((tmp = (List)n.getModifiers().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        if ((tmp = (List)n.getName().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        if ((tmp = (List)n.getParameters().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        if (n.getReceiverParameter().isPresent() && (tmp = (List)n.getReceiverParameter().get().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        if ((tmp = (List)n.getThrownExceptions().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        if ((tmp = (List)n.getTypeParameters().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        if ((tmp = (List)n.getAnnotations().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        if (n.getComment().isPresent() && (tmp = (List)n.getComment().get().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        return result;
    }

    @Override
    public List<R> visit(ContinueStmt n, A arg) {
        List tmp;
        ArrayList result = new ArrayList();
        if (n.getLabel().isPresent() && (tmp = (List)n.getLabel().get().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        if (n.getComment().isPresent() && (tmp = (List)n.getComment().get().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        return result;
    }

    @Override
    public List<R> visit(DoStmt n, A arg) {
        ArrayList result = new ArrayList();
        List tmp = (List)n.getBody().accept(this, arg);
        if (tmp != null) {
            result.addAll(tmp);
        }
        if ((tmp = (List)n.getCondition().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        if (n.getComment().isPresent() && (tmp = (List)n.getComment().get().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        return result;
    }

    @Override
    public List<R> visit(DoubleLiteralExpr n, A arg) {
        List tmp;
        ArrayList result = new ArrayList();
        if (n.getComment().isPresent() && (tmp = (List)n.getComment().get().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        return result;
    }

    @Override
    public List<R> visit(EmptyStmt n, A arg) {
        List tmp;
        ArrayList result = new ArrayList();
        if (n.getComment().isPresent() && (tmp = (List)n.getComment().get().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        return result;
    }

    @Override
    public List<R> visit(EnclosedExpr n, A arg) {
        ArrayList result = new ArrayList();
        List tmp = (List)n.getInner().accept(this, arg);
        if (tmp != null) {
            result.addAll(tmp);
        }
        if (n.getComment().isPresent() && (tmp = (List)n.getComment().get().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        return result;
    }

    @Override
    public List<R> visit(EnumConstantDeclaration n, A arg) {
        ArrayList result = new ArrayList();
        List tmp = (List)n.getArguments().accept(this, arg);
        if (tmp != null) {
            result.addAll(tmp);
        }
        if ((tmp = (List)n.getClassBody().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        if ((tmp = (List)n.getName().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        if ((tmp = (List)n.getAnnotations().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        if (n.getComment().isPresent() && (tmp = (List)n.getComment().get().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        return result;
    }

    @Override
    public List<R> visit(EnumDeclaration n, A arg) {
        ArrayList result = new ArrayList();
        List tmp = (List)n.getEntries().accept(this, arg);
        if (tmp != null) {
            result.addAll(tmp);
        }
        if ((tmp = (List)n.getImplementedTypes().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        if ((tmp = (List)n.getMembers().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        if ((tmp = (List)n.getModifiers().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        if ((tmp = (List)n.getName().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        if ((tmp = (List)n.getAnnotations().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        if (n.getComment().isPresent() && (tmp = (List)n.getComment().get().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        return result;
    }

    @Override
    public List<R> visit(ExplicitConstructorInvocationStmt n, A arg) {
        ArrayList result = new ArrayList();
        List tmp = (List)n.getArguments().accept(this, arg);
        if (tmp != null) {
            result.addAll(tmp);
        }
        if (n.getExpression().isPresent() && (tmp = (List)n.getExpression().get().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        if (n.getTypeArguments().isPresent() && (tmp = (List)n.getTypeArguments().get().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        if (n.getComment().isPresent() && (tmp = (List)n.getComment().get().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        return result;
    }

    @Override
    public List<R> visit(ExpressionStmt n, A arg) {
        ArrayList result = new ArrayList();
        List tmp = (List)n.getExpression().accept(this, arg);
        if (tmp != null) {
            result.addAll(tmp);
        }
        if (n.getComment().isPresent() && (tmp = (List)n.getComment().get().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        return result;
    }

    @Override
    public List<R> visit(FieldAccessExpr n, A arg) {
        ArrayList result = new ArrayList();
        List tmp = (List)n.getName().accept(this, arg);
        if (tmp != null) {
            result.addAll(tmp);
        }
        if ((tmp = (List)n.getScope().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        if (n.getTypeArguments().isPresent() && (tmp = (List)n.getTypeArguments().get().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        if (n.getComment().isPresent() && (tmp = (List)n.getComment().get().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        return result;
    }

    @Override
    public List<R> visit(FieldDeclaration n, A arg) {
        ArrayList result = new ArrayList();
        List tmp = (List)n.getModifiers().accept(this, arg);
        if (tmp != null) {
            result.addAll(tmp);
        }
        if ((tmp = (List)n.getVariables().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        if ((tmp = (List)n.getAnnotations().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        if (n.getComment().isPresent() && (tmp = (List)n.getComment().get().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        return result;
    }

    @Override
    public List<R> visit(ForStmt n, A arg) {
        ArrayList result = new ArrayList();
        List tmp = (List)n.getBody().accept(this, arg);
        if (tmp != null) {
            result.addAll(tmp);
        }
        if (n.getCompare().isPresent() && (tmp = (List)n.getCompare().get().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        if ((tmp = (List)n.getInitialization().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        if ((tmp = (List)n.getUpdate().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        if (n.getComment().isPresent() && (tmp = (List)n.getComment().get().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        return result;
    }

    @Override
    public List<R> visit(ForEachStmt n, A arg) {
        ArrayList result = new ArrayList();
        List tmp = (List)n.getBody().accept(this, arg);
        if (tmp != null) {
            result.addAll(tmp);
        }
        if ((tmp = (List)n.getIterable().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        if ((tmp = (List)n.getVariable().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        if (n.getComment().isPresent() && (tmp = (List)n.getComment().get().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        return result;
    }

    @Override
    public List<R> visit(IfStmt n, A arg) {
        ArrayList result = new ArrayList();
        List tmp = (List)n.getCondition().accept(this, arg);
        if (tmp != null) {
            result.addAll(tmp);
        }
        if (n.getElseStmt().isPresent() && (tmp = (List)n.getElseStmt().get().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        if ((tmp = (List)n.getThenStmt().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        if (n.getComment().isPresent() && (tmp = (List)n.getComment().get().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        return result;
    }

    @Override
    public List<R> visit(ImportDeclaration n, A arg) {
        ArrayList result = new ArrayList();
        List tmp = (List)n.getName().accept(this, arg);
        if (tmp != null) {
            result.addAll(tmp);
        }
        if (n.getComment().isPresent() && (tmp = (List)n.getComment().get().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        return result;
    }

    @Override
    public List<R> visit(InitializerDeclaration n, A arg) {
        ArrayList result = new ArrayList();
        List tmp = (List)n.getBody().accept(this, arg);
        if (tmp != null) {
            result.addAll(tmp);
        }
        if ((tmp = (List)n.getAnnotations().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        if (n.getComment().isPresent() && (tmp = (List)n.getComment().get().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        return result;
    }

    @Override
    public List<R> visit(InstanceOfExpr n, A arg) {
        ArrayList result = new ArrayList();
        List tmp = (List)n.getExpression().accept(this, arg);
        if (tmp != null) {
            result.addAll(tmp);
        }
        if (n.getPattern().isPresent() && (tmp = (List)n.getPattern().get().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        if ((tmp = (List)n.getType().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        if (n.getComment().isPresent() && (tmp = (List)n.getComment().get().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        return result;
    }

    @Override
    public List<R> visit(IntegerLiteralExpr n, A arg) {
        List tmp;
        ArrayList result = new ArrayList();
        if (n.getComment().isPresent() && (tmp = (List)n.getComment().get().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        return result;
    }

    @Override
    public List<R> visit(IntersectionType n, A arg) {
        ArrayList result = new ArrayList();
        List tmp = (List)n.getElements().accept(this, arg);
        if (tmp != null) {
            result.addAll(tmp);
        }
        if ((tmp = (List)n.getAnnotations().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        if (n.getComment().isPresent() && (tmp = (List)n.getComment().get().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        return result;
    }

    @Override
    public List<R> visit(JavadocComment n, A arg) {
        List tmp;
        ArrayList result = new ArrayList();
        if (n.getComment().isPresent() && (tmp = (List)n.getComment().get().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        return result;
    }

    @Override
    public List<R> visit(LabeledStmt n, A arg) {
        ArrayList result = new ArrayList();
        List tmp = (List)n.getLabel().accept(this, arg);
        if (tmp != null) {
            result.addAll(tmp);
        }
        if ((tmp = (List)n.getStatement().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        if (n.getComment().isPresent() && (tmp = (List)n.getComment().get().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        return result;
    }

    @Override
    public List<R> visit(LambdaExpr n, A arg) {
        ArrayList result = new ArrayList();
        List tmp = (List)n.getBody().accept(this, arg);
        if (tmp != null) {
            result.addAll(tmp);
        }
        if ((tmp = (List)n.getParameters().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        if (n.getComment().isPresent() && (tmp = (List)n.getComment().get().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        return result;
    }

    @Override
    public List<R> visit(LineComment n, A arg) {
        List tmp;
        ArrayList result = new ArrayList();
        if (n.getComment().isPresent() && (tmp = (List)n.getComment().get().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        return result;
    }

    @Override
    public List<R> visit(LocalClassDeclarationStmt n, A arg) {
        ArrayList result = new ArrayList();
        List tmp = (List)n.getClassDeclaration().accept(this, arg);
        if (tmp != null) {
            result.addAll(tmp);
        }
        if (n.getComment().isPresent() && (tmp = (List)n.getComment().get().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        return result;
    }

    @Override
    public List<R> visit(LongLiteralExpr n, A arg) {
        List tmp;
        ArrayList result = new ArrayList();
        if (n.getComment().isPresent() && (tmp = (List)n.getComment().get().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        return result;
    }

    @Override
    public List<R> visit(MarkerAnnotationExpr n, A arg) {
        ArrayList result = new ArrayList();
        List tmp = (List)n.getName().accept(this, arg);
        if (tmp != null) {
            result.addAll(tmp);
        }
        if (n.getComment().isPresent() && (tmp = (List)n.getComment().get().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        return result;
    }

    @Override
    public List<R> visit(MemberValuePair n, A arg) {
        ArrayList result = new ArrayList();
        List tmp = (List)n.getName().accept(this, arg);
        if (tmp != null) {
            result.addAll(tmp);
        }
        if ((tmp = (List)n.getValue().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        if (n.getComment().isPresent() && (tmp = (List)n.getComment().get().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        return result;
    }

    @Override
    public List<R> visit(MethodCallExpr n, A arg) {
        ArrayList result = new ArrayList();
        List tmp = (List)n.getArguments().accept(this, arg);
        if (tmp != null) {
            result.addAll(tmp);
        }
        if ((tmp = (List)n.getName().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        if (n.hasScope() && (tmp = (List)n.getScope().get().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        if (n.getTypeArguments().isPresent() && (tmp = (List)n.getTypeArguments().get().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        if (n.getComment().isPresent() && (tmp = (List)n.getComment().get().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        return result;
    }

    @Override
    public List<R> visit(MethodDeclaration n, A arg) {
        List tmp;
        ArrayList result = new ArrayList();
        if (n.getBody().isPresent() && (tmp = (List)n.getBody().get().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        if ((tmp = (List)n.getType().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        if ((tmp = (List)n.getModifiers().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        if ((tmp = (List)n.getName().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        if ((tmp = (List)n.getParameters().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        if (n.getReceiverParameter().isPresent() && (tmp = (List)n.getReceiverParameter().get().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        if ((tmp = (List)n.getThrownExceptions().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        if ((tmp = (List)n.getTypeParameters().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        if ((tmp = (List)n.getAnnotations().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        if (n.getComment().isPresent() && (tmp = (List)n.getComment().get().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        return result;
    }

    @Override
    public List<R> visit(MethodReferenceExpr n, A arg) {
        ArrayList result = new ArrayList();
        List tmp = (List)n.getScope().accept(this, arg);
        if (tmp != null) {
            result.addAll(tmp);
        }
        if (n.getTypeArguments().isPresent() && (tmp = (List)n.getTypeArguments().get().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        if (n.getComment().isPresent() && (tmp = (List)n.getComment().get().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        return result;
    }

    @Override
    public List<R> visit(NameExpr n, A arg) {
        ArrayList result = new ArrayList();
        List tmp = (List)n.getName().accept(this, arg);
        if (tmp != null) {
            result.addAll(tmp);
        }
        if (n.getComment().isPresent() && (tmp = (List)n.getComment().get().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        return result;
    }

    @Override
    public List<R> visit(Name n, A arg) {
        List tmp;
        ArrayList result = new ArrayList();
        if (n.getQualifier().isPresent() && (tmp = (List)n.getQualifier().get().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        if (n.getComment().isPresent() && (tmp = (List)n.getComment().get().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        return result;
    }

    @Override
    public List<R> visit(NormalAnnotationExpr n, A arg) {
        ArrayList result = new ArrayList();
        List tmp = (List)n.getPairs().accept(this, arg);
        if (tmp != null) {
            result.addAll(tmp);
        }
        if ((tmp = (List)n.getName().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        if (n.getComment().isPresent() && (tmp = (List)n.getComment().get().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        return result;
    }

    @Override
    public List<R> visit(NullLiteralExpr n, A arg) {
        List tmp;
        ArrayList result = new ArrayList();
        if (n.getComment().isPresent() && (tmp = (List)n.getComment().get().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        return result;
    }

    @Override
    public List<R> visit(ObjectCreationExpr n, A arg) {
        List tmp;
        ArrayList result = new ArrayList();
        if (n.getAnonymousClassBody().isPresent() && (tmp = (List)n.getAnonymousClassBody().get().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        if ((tmp = (List)n.getArguments().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        if (n.hasScope() && (tmp = (List)n.getScope().get().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        if ((tmp = (List)n.getType().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        if (n.getTypeArguments().isPresent() && (tmp = (List)n.getTypeArguments().get().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        if (n.getComment().isPresent() && (tmp = (List)n.getComment().get().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        return result;
    }

    @Override
    public List<R> visit(PackageDeclaration n, A arg) {
        ArrayList result = new ArrayList();
        List tmp = (List)n.getAnnotations().accept(this, arg);
        if (tmp != null) {
            result.addAll(tmp);
        }
        if ((tmp = (List)n.getName().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        if (n.getComment().isPresent() && (tmp = (List)n.getComment().get().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        return result;
    }

    @Override
    public List<R> visit(Parameter n, A arg) {
        ArrayList result = new ArrayList();
        List tmp = (List)n.getAnnotations().accept(this, arg);
        if (tmp != null) {
            result.addAll(tmp);
        }
        if ((tmp = (List)n.getModifiers().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        if ((tmp = (List)n.getName().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        if ((tmp = (List)n.getType().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        if ((tmp = (List)n.getVarArgsAnnotations().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        if (n.getComment().isPresent() && (tmp = (List)n.getComment().get().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        return result;
    }

    @Override
    public List<R> visit(PrimitiveType n, A arg) {
        ArrayList result = new ArrayList();
        List tmp = (List)n.getAnnotations().accept(this, arg);
        if (tmp != null) {
            result.addAll(tmp);
        }
        if (n.getComment().isPresent() && (tmp = (List)n.getComment().get().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        return result;
    }

    @Override
    public List<R> visit(ReturnStmt n, A arg) {
        List tmp;
        ArrayList result = new ArrayList();
        if (n.getExpression().isPresent() && (tmp = (List)n.getExpression().get().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        if (n.getComment().isPresent() && (tmp = (List)n.getComment().get().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        return result;
    }

    @Override
    public List<R> visit(SimpleName n, A arg) {
        List tmp;
        ArrayList result = new ArrayList();
        if (n.getComment().isPresent() && (tmp = (List)n.getComment().get().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        return result;
    }

    @Override
    public List<R> visit(SingleMemberAnnotationExpr n, A arg) {
        ArrayList result = new ArrayList();
        List tmp = (List)n.getMemberValue().accept(this, arg);
        if (tmp != null) {
            result.addAll(tmp);
        }
        if ((tmp = (List)n.getName().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        if (n.getComment().isPresent() && (tmp = (List)n.getComment().get().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        return result;
    }

    @Override
    public List<R> visit(StringLiteralExpr n, A arg) {
        List tmp;
        ArrayList result = new ArrayList();
        if (n.getComment().isPresent() && (tmp = (List)n.getComment().get().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        return result;
    }

    @Override
    public List<R> visit(SuperExpr n, A arg) {
        List tmp;
        ArrayList result = new ArrayList();
        if (n.getTypeName().isPresent() && (tmp = (List)n.getTypeName().get().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        if (n.getComment().isPresent() && (tmp = (List)n.getComment().get().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        return result;
    }

    @Override
    public List<R> visit(SwitchEntry n, A arg) {
        ArrayList result = new ArrayList();
        List tmp = (List)n.getLabels().accept(this, arg);
        if (tmp != null) {
            result.addAll(tmp);
        }
        if ((tmp = (List)n.getStatements().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        if (n.getComment().isPresent() && (tmp = (List)n.getComment().get().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        return result;
    }

    @Override
    public List<R> visit(SwitchStmt n, A arg) {
        ArrayList result = new ArrayList();
        List tmp = (List)n.getEntries().accept(this, arg);
        if (tmp != null) {
            result.addAll(tmp);
        }
        if ((tmp = (List)n.getSelector().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        if (n.getComment().isPresent() && (tmp = (List)n.getComment().get().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        return result;
    }

    @Override
    public List<R> visit(SynchronizedStmt n, A arg) {
        ArrayList result = new ArrayList();
        List tmp = (List)n.getBody().accept(this, arg);
        if (tmp != null) {
            result.addAll(tmp);
        }
        if ((tmp = (List)n.getExpression().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        if (n.getComment().isPresent() && (tmp = (List)n.getComment().get().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        return result;
    }

    @Override
    public List<R> visit(ThisExpr n, A arg) {
        List tmp;
        ArrayList result = new ArrayList();
        if (n.getTypeName().isPresent() && (tmp = (List)n.getTypeName().get().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        if (n.getComment().isPresent() && (tmp = (List)n.getComment().get().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        return result;
    }

    @Override
    public List<R> visit(ThrowStmt n, A arg) {
        ArrayList result = new ArrayList();
        List tmp = (List)n.getExpression().accept(this, arg);
        if (tmp != null) {
            result.addAll(tmp);
        }
        if (n.getComment().isPresent() && (tmp = (List)n.getComment().get().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        return result;
    }

    @Override
    public List<R> visit(TryStmt n, A arg) {
        ArrayList result = new ArrayList();
        List tmp = (List)n.getCatchClauses().accept(this, arg);
        if (tmp != null) {
            result.addAll(tmp);
        }
        if (n.getFinallyBlock().isPresent() && (tmp = (List)n.getFinallyBlock().get().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        if ((tmp = (List)n.getResources().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        if ((tmp = (List)n.getTryBlock().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        if (n.getComment().isPresent() && (tmp = (List)n.getComment().get().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        return result;
    }

    @Override
    public List<R> visit(TypeExpr n, A arg) {
        ArrayList result = new ArrayList();
        List tmp = (List)n.getType().accept(this, arg);
        if (tmp != null) {
            result.addAll(tmp);
        }
        if (n.getComment().isPresent() && (tmp = (List)n.getComment().get().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        return result;
    }

    @Override
    public List<R> visit(TypeParameter n, A arg) {
        ArrayList result = new ArrayList();
        List tmp = (List)n.getName().accept(this, arg);
        if (tmp != null) {
            result.addAll(tmp);
        }
        if ((tmp = (List)n.getTypeBound().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        if ((tmp = (List)n.getAnnotations().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        if (n.getComment().isPresent() && (tmp = (List)n.getComment().get().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        return result;
    }

    @Override
    public List<R> visit(UnaryExpr n, A arg) {
        ArrayList result = new ArrayList();
        List tmp = (List)n.getExpression().accept(this, arg);
        if (tmp != null) {
            result.addAll(tmp);
        }
        if (n.getComment().isPresent() && (tmp = (List)n.getComment().get().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        return result;
    }

    @Override
    public List<R> visit(UnionType n, A arg) {
        ArrayList result = new ArrayList();
        List tmp = (List)n.getElements().accept(this, arg);
        if (tmp != null) {
            result.addAll(tmp);
        }
        if ((tmp = (List)n.getAnnotations().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        if (n.getComment().isPresent() && (tmp = (List)n.getComment().get().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        return result;
    }

    @Override
    public List<R> visit(UnknownType n, A arg) {
        ArrayList result = new ArrayList();
        List tmp = (List)n.getAnnotations().accept(this, arg);
        if (tmp != null) {
            result.addAll(tmp);
        }
        if (n.getComment().isPresent() && (tmp = (List)n.getComment().get().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        return result;
    }

    @Override
    public List<R> visit(VariableDeclarationExpr n, A arg) {
        ArrayList result = new ArrayList();
        List tmp = (List)n.getAnnotations().accept(this, arg);
        if (tmp != null) {
            result.addAll(tmp);
        }
        if ((tmp = (List)n.getModifiers().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        if ((tmp = (List)n.getVariables().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        if (n.getComment().isPresent() && (tmp = (List)n.getComment().get().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        return result;
    }

    @Override
    public List<R> visit(VariableDeclarator n, A arg) {
        List tmp;
        ArrayList result = new ArrayList();
        if (n.getInitializer().isPresent() && (tmp = (List)n.getInitializer().get().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        if ((tmp = (List)n.getName().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        if ((tmp = (List)n.getType().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        if (n.getComment().isPresent() && (tmp = (List)n.getComment().get().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        return result;
    }

    @Override
    public List<R> visit(VoidType n, A arg) {
        ArrayList result = new ArrayList();
        List tmp = (List)n.getAnnotations().accept(this, arg);
        if (tmp != null) {
            result.addAll(tmp);
        }
        if (n.getComment().isPresent() && (tmp = (List)n.getComment().get().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        return result;
    }

    @Override
    public List<R> visit(WhileStmt n, A arg) {
        ArrayList result = new ArrayList();
        List tmp = (List)n.getBody().accept(this, arg);
        if (tmp != null) {
            result.addAll(tmp);
        }
        if ((tmp = (List)n.getCondition().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        if (n.getComment().isPresent() && (tmp = (List)n.getComment().get().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        return result;
    }

    @Override
    public List<R> visit(WildcardType n, A arg) {
        List tmp;
        ArrayList result = new ArrayList();
        if (n.getExtendedType().isPresent() && (tmp = (List)n.getExtendedType().get().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        if (n.getSuperType().isPresent() && (tmp = (List)n.getSuperType().get().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        if ((tmp = (List)n.getAnnotations().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        if (n.getComment().isPresent() && (tmp = (List)n.getComment().get().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        return result;
    }

    @Override
    public List<R> visit(NodeList n, A arg) {
        return n.stream().filter(Objects::nonNull).flatMap(v -> ((List)v.accept(this, arg)).stream()).collect(Collectors.toList());
    }

    @Override
    public List<R> visit(ModuleDeclaration n, A arg) {
        ArrayList result = new ArrayList();
        List tmp = (List)n.getAnnotations().accept(this, arg);
        if (tmp != null) {
            result.addAll(tmp);
        }
        if ((tmp = (List)n.getDirectives().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        if ((tmp = (List)n.getName().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        if (n.getComment().isPresent() && (tmp = (List)n.getComment().get().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        return result;
    }

    @Override
    public List<R> visit(ModuleExportsDirective n, A arg) {
        ArrayList result = new ArrayList();
        List tmp = (List)n.getModuleNames().accept(this, arg);
        if (tmp != null) {
            result.addAll(tmp);
        }
        if ((tmp = (List)n.getName().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        if (n.getComment().isPresent() && (tmp = (List)n.getComment().get().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        return result;
    }

    @Override
    public List<R> visit(ModuleOpensDirective n, A arg) {
        ArrayList result = new ArrayList();
        List tmp = (List)n.getModuleNames().accept(this, arg);
        if (tmp != null) {
            result.addAll(tmp);
        }
        if ((tmp = (List)n.getName().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        if (n.getComment().isPresent() && (tmp = (List)n.getComment().get().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        return result;
    }

    @Override
    public List<R> visit(ModuleProvidesDirective n, A arg) {
        ArrayList result = new ArrayList();
        List tmp = (List)n.getName().accept(this, arg);
        if (tmp != null) {
            result.addAll(tmp);
        }
        if ((tmp = (List)n.getWith().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        if (n.getComment().isPresent() && (tmp = (List)n.getComment().get().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        return result;
    }

    @Override
    public List<R> visit(ModuleRequiresDirective n, A arg) {
        ArrayList result = new ArrayList();
        List tmp = (List)n.getModifiers().accept(this, arg);
        if (tmp != null) {
            result.addAll(tmp);
        }
        if ((tmp = (List)n.getName().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        if (n.getComment().isPresent() && (tmp = (List)n.getComment().get().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        return result;
    }

    @Override
    public List<R> visit(ModuleUsesDirective n, A arg) {
        ArrayList result = new ArrayList();
        List tmp = (List)n.getName().accept(this, arg);
        if (tmp != null) {
            result.addAll(tmp);
        }
        if (n.getComment().isPresent() && (tmp = (List)n.getComment().get().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        return result;
    }

    @Override
    public List<R> visit(UnparsableStmt n, A arg) {
        List tmp;
        ArrayList result = new ArrayList();
        if (n.getComment().isPresent() && (tmp = (List)n.getComment().get().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        return result;
    }

    @Override
    public List<R> visit(ReceiverParameter n, A arg) {
        ArrayList result = new ArrayList();
        List tmp = (List)n.getAnnotations().accept(this, arg);
        if (tmp != null) {
            result.addAll(tmp);
        }
        if ((tmp = (List)n.getName().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        if ((tmp = (List)n.getType().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        if (n.getComment().isPresent() && (tmp = (List)n.getComment().get().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        return result;
    }

    @Override
    public List<R> visit(VarType n, A arg) {
        ArrayList result = new ArrayList();
        List tmp = (List)n.getAnnotations().accept(this, arg);
        if (tmp != null) {
            result.addAll(tmp);
        }
        if (n.getComment().isPresent() && (tmp = (List)n.getComment().get().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        return result;
    }

    @Override
    public List<R> visit(Modifier n, A arg) {
        List tmp;
        ArrayList result = new ArrayList();
        if (n.getComment().isPresent() && (tmp = (List)n.getComment().get().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        return result;
    }

    @Override
    public List<R> visit(SwitchExpr n, A arg) {
        ArrayList result = new ArrayList();
        List tmp = (List)n.getEntries().accept(this, arg);
        if (tmp != null) {
            result.addAll(tmp);
        }
        if ((tmp = (List)n.getSelector().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        if (n.getComment().isPresent() && (tmp = (List)n.getComment().get().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        return result;
    }

    @Override
    public List<R> visit(YieldStmt n, A arg) {
        ArrayList result = new ArrayList();
        List tmp = (List)n.getExpression().accept(this, arg);
        if (tmp != null) {
            result.addAll(tmp);
        }
        if (n.getComment().isPresent() && (tmp = (List)n.getComment().get().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        return result;
    }

    @Override
    public List<R> visit(TextBlockLiteralExpr n, A arg) {
        List tmp;
        ArrayList result = new ArrayList();
        if (n.getComment().isPresent() && (tmp = (List)n.getComment().get().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        return result;
    }

    @Override
    public List<R> visit(PatternExpr n, A arg) {
        ArrayList result = new ArrayList();
        List tmp = (List)n.getName().accept(this, arg);
        if (tmp != null) {
            result.addAll(tmp);
        }
        if ((tmp = (List)n.getType().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        if (n.getComment().isPresent() && (tmp = (List)n.getComment().get().accept(this, arg)) != null) {
            result.addAll(tmp);
        }
        return result;
    }
}

