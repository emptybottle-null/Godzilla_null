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
import com.github.javaparser.ast.visitor.Visitable;

public class HashCodeVisitor
implements GenericVisitor<Integer, Void> {
    private static final HashCodeVisitor SINGLETON = new HashCodeVisitor();

    private HashCodeVisitor() {
    }

    public static int hashCode(Node node) {
        return node.accept(SINGLETON, null);
    }

    @Override
    public Integer visit(AnnotationDeclaration n, Void arg) {
        return n.getMembers().accept(this, arg) * 31 + n.getModifiers().accept(this, arg) * 31 + n.getName().accept(this, arg) * 31 + n.getAnnotations().accept(this, arg) * 31 + (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
    }

    @Override
    public Integer visit(AnnotationMemberDeclaration n, Void arg) {
        return (n.getDefaultValue().isPresent() ? n.getDefaultValue().get().accept(this, arg) : 0) * 31 + n.getModifiers().accept(this, arg) * 31 + n.getName().accept(this, arg) * 31 + n.getType().accept(this, arg) * 31 + n.getAnnotations().accept(this, arg) * 31 + (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
    }

    @Override
    public Integer visit(ArrayAccessExpr n, Void arg) {
        return n.getIndex().accept(this, arg) * 31 + n.getName().accept(this, arg) * 31 + (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
    }

    @Override
    public Integer visit(ArrayCreationExpr n, Void arg) {
        return n.getElementType().accept(this, arg) * 31 + (n.getInitializer().isPresent() ? n.getInitializer().get().accept(this, arg) : 0) * 31 + n.getLevels().accept(this, arg) * 31 + (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
    }

    @Override
    public Integer visit(ArrayCreationLevel n, Void arg) {
        return n.getAnnotations().accept(this, arg) * 31 + (n.getDimension().isPresent() ? n.getDimension().get().accept(this, arg) : 0) * 31 + (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
    }

    @Override
    public Integer visit(ArrayInitializerExpr n, Void arg) {
        return n.getValues().accept(this, arg) * 31 + (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
    }

    @Override
    public Integer visit(ArrayType n, Void arg) {
        return n.getComponentType().accept(this, arg) * 31 + n.getOrigin().hashCode() * 31 + n.getAnnotations().accept(this, arg) * 31 + (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
    }

    @Override
    public Integer visit(AssertStmt n, Void arg) {
        return n.getCheck().accept(this, arg) * 31 + (n.getMessage().isPresent() ? n.getMessage().get().accept(this, arg) : 0) * 31 + (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
    }

    @Override
    public Integer visit(AssignExpr n, Void arg) {
        return n.getOperator().hashCode() * 31 + n.getTarget().accept(this, arg) * 31 + n.getValue().accept(this, arg) * 31 + (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
    }

    @Override
    public Integer visit(BinaryExpr n, Void arg) {
        return n.getLeft().accept(this, arg) * 31 + n.getOperator().hashCode() * 31 + n.getRight().accept(this, arg) * 31 + (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
    }

    @Override
    public Integer visit(BlockComment n, Void arg) {
        return n.getContent().hashCode() * 31 + (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
    }

    @Override
    public Integer visit(BlockStmt n, Void arg) {
        return n.getStatements().accept(this, arg) * 31 + (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
    }

    @Override
    public Integer visit(BooleanLiteralExpr n, Void arg) {
        return (n.isValue() ? 1 : 0) * 31 + (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
    }

    @Override
    public Integer visit(BreakStmt n, Void arg) {
        return (n.getLabel().isPresent() ? n.getLabel().get().accept(this, arg) : 0) * 31 + (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
    }

    @Override
    public Integer visit(CastExpr n, Void arg) {
        return n.getExpression().accept(this, arg) * 31 + n.getType().accept(this, arg) * 31 + (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
    }

    @Override
    public Integer visit(CatchClause n, Void arg) {
        return n.getBody().accept(this, arg) * 31 + n.getParameter().accept(this, arg) * 31 + (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
    }

    @Override
    public Integer visit(CharLiteralExpr n, Void arg) {
        return n.getValue().hashCode() * 31 + (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
    }

    @Override
    public Integer visit(ClassExpr n, Void arg) {
        return n.getType().accept(this, arg) * 31 + (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
    }

    @Override
    public Integer visit(ClassOrInterfaceDeclaration n, Void arg) {
        return n.getExtendedTypes().accept(this, arg) * 31 + n.getImplementedTypes().accept(this, arg) * 31 + (n.isInterface() ? 1 : 0) * 31 + n.getTypeParameters().accept(this, arg) * 31 + n.getMembers().accept(this, arg) * 31 + n.getModifiers().accept(this, arg) * 31 + n.getName().accept(this, arg) * 31 + n.getAnnotations().accept(this, arg) * 31 + (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
    }

    @Override
    public Integer visit(ClassOrInterfaceType n, Void arg) {
        return n.getName().accept(this, arg) * 31 + (n.getScope().isPresent() ? n.getScope().get().accept(this, arg) : 0) * 31 + (n.getTypeArguments().isPresent() ? n.getTypeArguments().get().accept(this, arg) : 0) * 31 + n.getAnnotations().accept(this, arg) * 31 + (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
    }

    @Override
    public Integer visit(CompilationUnit n, Void arg) {
        return n.getImports().accept(this, arg) * 31 + (n.getModule().isPresent() ? n.getModule().get().accept(this, arg) : 0) * 31 + (n.getPackageDeclaration().isPresent() ? n.getPackageDeclaration().get().accept(this, arg) : 0) * 31 + n.getTypes().accept(this, arg) * 31 + (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
    }

    @Override
    public Integer visit(ConditionalExpr n, Void arg) {
        return n.getCondition().accept(this, arg) * 31 + n.getElseExpr().accept(this, arg) * 31 + n.getThenExpr().accept(this, arg) * 31 + (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
    }

    @Override
    public Integer visit(ConstructorDeclaration n, Void arg) {
        return n.getBody().accept(this, arg) * 31 + n.getModifiers().accept(this, arg) * 31 + n.getName().accept(this, arg) * 31 + n.getParameters().accept(this, arg) * 31 + (n.getReceiverParameter().isPresent() ? n.getReceiverParameter().get().accept(this, arg) : 0) * 31 + n.getThrownExceptions().accept(this, arg) * 31 + n.getTypeParameters().accept(this, arg) * 31 + n.getAnnotations().accept(this, arg) * 31 + (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
    }

    @Override
    public Integer visit(ContinueStmt n, Void arg) {
        return (n.getLabel().isPresent() ? n.getLabel().get().accept(this, arg) : 0) * 31 + (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
    }

    @Override
    public Integer visit(DoStmt n, Void arg) {
        return n.getBody().accept(this, arg) * 31 + n.getCondition().accept(this, arg) * 31 + (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
    }

    @Override
    public Integer visit(DoubleLiteralExpr n, Void arg) {
        return n.getValue().hashCode() * 31 + (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
    }

    @Override
    public Integer visit(EmptyStmt n, Void arg) {
        return n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : Integer.valueOf(0);
    }

    @Override
    public Integer visit(EnclosedExpr n, Void arg) {
        return n.getInner().accept(this, arg) * 31 + (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
    }

    @Override
    public Integer visit(EnumConstantDeclaration n, Void arg) {
        return n.getArguments().accept(this, arg) * 31 + n.getClassBody().accept(this, arg) * 31 + n.getName().accept(this, arg) * 31 + n.getAnnotations().accept(this, arg) * 31 + (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
    }

    @Override
    public Integer visit(EnumDeclaration n, Void arg) {
        return n.getEntries().accept(this, arg) * 31 + n.getImplementedTypes().accept(this, arg) * 31 + n.getMembers().accept(this, arg) * 31 + n.getModifiers().accept(this, arg) * 31 + n.getName().accept(this, arg) * 31 + n.getAnnotations().accept(this, arg) * 31 + (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
    }

    @Override
    public Integer visit(ExplicitConstructorInvocationStmt n, Void arg) {
        return n.getArguments().accept(this, arg) * 31 + (n.getExpression().isPresent() ? n.getExpression().get().accept(this, arg) : 0) * 31 + (n.isThis() ? 1 : 0) * 31 + (n.getTypeArguments().isPresent() ? n.getTypeArguments().get().accept(this, arg) : 0) * 31 + (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
    }

    @Override
    public Integer visit(ExpressionStmt n, Void arg) {
        return n.getExpression().accept(this, arg) * 31 + (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
    }

    @Override
    public Integer visit(FieldAccessExpr n, Void arg) {
        return n.getName().accept(this, arg) * 31 + n.getScope().accept(this, arg) * 31 + (n.getTypeArguments().isPresent() ? n.getTypeArguments().get().accept(this, arg) : 0) * 31 + (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
    }

    @Override
    public Integer visit(FieldDeclaration n, Void arg) {
        return n.getModifiers().accept(this, arg) * 31 + n.getVariables().accept(this, arg) * 31 + n.getAnnotations().accept(this, arg) * 31 + (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
    }

    @Override
    public Integer visit(ForStmt n, Void arg) {
        return n.getBody().accept(this, arg) * 31 + (n.getCompare().isPresent() ? n.getCompare().get().accept(this, arg) : 0) * 31 + n.getInitialization().accept(this, arg) * 31 + n.getUpdate().accept(this, arg) * 31 + (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
    }

    @Override
    public Integer visit(ForEachStmt n, Void arg) {
        return n.getBody().accept(this, arg) * 31 + n.getIterable().accept(this, arg) * 31 + n.getVariable().accept(this, arg) * 31 + (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
    }

    @Override
    public Integer visit(IfStmt n, Void arg) {
        return n.getCondition().accept(this, arg) * 31 + (n.getElseStmt().isPresent() ? n.getElseStmt().get().accept(this, arg) : 0) * 31 + n.getThenStmt().accept(this, arg) * 31 + (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
    }

    @Override
    public Integer visit(ImportDeclaration n, Void arg) {
        return (n.isAsterisk() ? 1 : 0) * 31 + (n.isStatic() ? 1 : 0) * 31 + n.getName().accept(this, arg) * 31 + (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
    }

    @Override
    public Integer visit(InitializerDeclaration n, Void arg) {
        return n.getBody().accept(this, arg) * 31 + (n.isStatic() ? 1 : 0) * 31 + n.getAnnotations().accept(this, arg) * 31 + (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
    }

    @Override
    public Integer visit(InstanceOfExpr n, Void arg) {
        return n.getExpression().accept(this, arg) * 31 + (n.getPattern().isPresent() ? n.getPattern().get().accept(this, arg) : 0) * 31 + n.getType().accept(this, arg) * 31 + (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
    }

    @Override
    public Integer visit(IntegerLiteralExpr n, Void arg) {
        return n.getValue().hashCode() * 31 + (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
    }

    @Override
    public Integer visit(IntersectionType n, Void arg) {
        return n.getElements().accept(this, arg) * 31 + n.getAnnotations().accept(this, arg) * 31 + (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
    }

    @Override
    public Integer visit(JavadocComment n, Void arg) {
        return n.getContent().hashCode() * 31 + (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
    }

    @Override
    public Integer visit(LabeledStmt n, Void arg) {
        return n.getLabel().accept(this, arg) * 31 + n.getStatement().accept(this, arg) * 31 + (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
    }

    @Override
    public Integer visit(LambdaExpr n, Void arg) {
        return n.getBody().accept(this, arg) * 31 + (n.isEnclosingParameters() ? 1 : 0) * 31 + n.getParameters().accept(this, arg) * 31 + (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
    }

    @Override
    public Integer visit(LineComment n, Void arg) {
        return n.getContent().hashCode() * 31 + (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
    }

    @Override
    public Integer visit(LocalClassDeclarationStmt n, Void arg) {
        return n.getClassDeclaration().accept(this, arg) * 31 + (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
    }

    @Override
    public Integer visit(LongLiteralExpr n, Void arg) {
        return n.getValue().hashCode() * 31 + (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
    }

    @Override
    public Integer visit(MarkerAnnotationExpr n, Void arg) {
        return n.getName().accept(this, arg) * 31 + (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
    }

    @Override
    public Integer visit(MemberValuePair n, Void arg) {
        return n.getName().accept(this, arg) * 31 + n.getValue().accept(this, arg) * 31 + (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
    }

    @Override
    public Integer visit(MethodCallExpr n, Void arg) {
        return n.getArguments().accept(this, arg) * 31 + n.getName().accept(this, arg) * 31 + (n.hasScope() ? n.getScope().get().accept(this, arg) : 0) * 31 + (n.getTypeArguments().isPresent() ? n.getTypeArguments().get().accept(this, arg) : 0) * 31 + (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
    }

    @Override
    public Integer visit(MethodDeclaration n, Void arg) {
        return (n.getBody().isPresent() ? n.getBody().get().accept(this, arg) : 0) * 31 + n.getType().accept(this, arg) * 31 + n.getModifiers().accept(this, arg) * 31 + n.getName().accept(this, arg) * 31 + n.getParameters().accept(this, arg) * 31 + (n.getReceiverParameter().isPresent() ? n.getReceiverParameter().get().accept(this, arg) : 0) * 31 + n.getThrownExceptions().accept(this, arg) * 31 + n.getTypeParameters().accept(this, arg) * 31 + n.getAnnotations().accept(this, arg) * 31 + (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
    }

    @Override
    public Integer visit(MethodReferenceExpr n, Void arg) {
        return n.getIdentifier().hashCode() * 31 + n.getScope().accept(this, arg) * 31 + (n.getTypeArguments().isPresent() ? n.getTypeArguments().get().accept(this, arg) : 0) * 31 + (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
    }

    @Override
    public Integer visit(NameExpr n, Void arg) {
        return n.getName().accept(this, arg) * 31 + (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
    }

    @Override
    public Integer visit(Name n, Void arg) {
        return n.getIdentifier().hashCode() * 31 + (n.getQualifier().isPresent() ? n.getQualifier().get().accept(this, arg) : 0) * 31 + (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
    }

    @Override
    public Integer visit(NodeList n, Void arg) {
        int result = 0;
        for (Object node : n) {
            result += 31 * ((Visitable)node).accept(this, arg);
        }
        return result;
    }

    @Override
    public Integer visit(NormalAnnotationExpr n, Void arg) {
        return n.getPairs().accept(this, arg) * 31 + n.getName().accept(this, arg) * 31 + (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
    }

    @Override
    public Integer visit(NullLiteralExpr n, Void arg) {
        return n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : Integer.valueOf(0);
    }

    @Override
    public Integer visit(ObjectCreationExpr n, Void arg) {
        return (n.getAnonymousClassBody().isPresent() ? n.getAnonymousClassBody().get().accept(this, arg) : 0) * 31 + n.getArguments().accept(this, arg) * 31 + (n.hasScope() ? n.getScope().get().accept(this, arg) : 0) * 31 + n.getType().accept(this, arg) * 31 + (n.getTypeArguments().isPresent() ? n.getTypeArguments().get().accept(this, arg) : 0) * 31 + (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
    }

    @Override
    public Integer visit(PackageDeclaration n, Void arg) {
        return n.getAnnotations().accept(this, arg) * 31 + n.getName().accept(this, arg) * 31 + (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
    }

    @Override
    public Integer visit(Parameter n, Void arg) {
        return n.getAnnotations().accept(this, arg) * 31 + (n.isVarArgs() ? 1 : 0) * 31 + n.getModifiers().accept(this, arg) * 31 + n.getName().accept(this, arg) * 31 + n.getType().accept(this, arg) * 31 + n.getVarArgsAnnotations().accept(this, arg) * 31 + (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
    }

    @Override
    public Integer visit(PrimitiveType n, Void arg) {
        return n.getType().hashCode() * 31 + n.getAnnotations().accept(this, arg) * 31 + (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
    }

    @Override
    public Integer visit(ReturnStmt n, Void arg) {
        return (n.getExpression().isPresent() ? n.getExpression().get().accept(this, arg) : 0) * 31 + (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
    }

    @Override
    public Integer visit(SimpleName n, Void arg) {
        return n.getIdentifier().hashCode() * 31 + (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
    }

    @Override
    public Integer visit(SingleMemberAnnotationExpr n, Void arg) {
        return n.getMemberValue().accept(this, arg) * 31 + n.getName().accept(this, arg) * 31 + (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
    }

    @Override
    public Integer visit(StringLiteralExpr n, Void arg) {
        return n.getValue().hashCode() * 31 + (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
    }

    @Override
    public Integer visit(SuperExpr n, Void arg) {
        return (n.getTypeName().isPresent() ? n.getTypeName().get().accept(this, arg) : 0) * 31 + (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
    }

    @Override
    public Integer visit(SwitchEntry n, Void arg) {
        return n.getLabels().accept(this, arg) * 31 + n.getStatements().accept(this, arg) * 31 + n.getType().hashCode() * 31 + (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
    }

    @Override
    public Integer visit(SwitchStmt n, Void arg) {
        return n.getEntries().accept(this, arg) * 31 + n.getSelector().accept(this, arg) * 31 + (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
    }

    @Override
    public Integer visit(SynchronizedStmt n, Void arg) {
        return n.getBody().accept(this, arg) * 31 + n.getExpression().accept(this, arg) * 31 + (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
    }

    @Override
    public Integer visit(ThisExpr n, Void arg) {
        return (n.getTypeName().isPresent() ? n.getTypeName().get().accept(this, arg) : 0) * 31 + (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
    }

    @Override
    public Integer visit(ThrowStmt n, Void arg) {
        return n.getExpression().accept(this, arg) * 31 + (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
    }

    @Override
    public Integer visit(TryStmt n, Void arg) {
        return n.getCatchClauses().accept(this, arg) * 31 + (n.getFinallyBlock().isPresent() ? n.getFinallyBlock().get().accept(this, arg) : 0) * 31 + n.getResources().accept(this, arg) * 31 + n.getTryBlock().accept(this, arg) * 31 + (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
    }

    @Override
    public Integer visit(TypeExpr n, Void arg) {
        return n.getType().accept(this, arg) * 31 + (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
    }

    @Override
    public Integer visit(TypeParameter n, Void arg) {
        return n.getName().accept(this, arg) * 31 + n.getTypeBound().accept(this, arg) * 31 + n.getAnnotations().accept(this, arg) * 31 + (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
    }

    @Override
    public Integer visit(UnaryExpr n, Void arg) {
        return n.getExpression().accept(this, arg) * 31 + n.getOperator().hashCode() * 31 + (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
    }

    @Override
    public Integer visit(UnionType n, Void arg) {
        return n.getElements().accept(this, arg) * 31 + n.getAnnotations().accept(this, arg) * 31 + (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
    }

    @Override
    public Integer visit(UnknownType n, Void arg) {
        return n.getAnnotations().accept(this, arg) * 31 + (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
    }

    @Override
    public Integer visit(VariableDeclarationExpr n, Void arg) {
        return n.getAnnotations().accept(this, arg) * 31 + n.getModifiers().accept(this, arg) * 31 + n.getVariables().accept(this, arg) * 31 + (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
    }

    @Override
    public Integer visit(VariableDeclarator n, Void arg) {
        return (n.getInitializer().isPresent() ? n.getInitializer().get().accept(this, arg) : 0) * 31 + n.getName().accept(this, arg) * 31 + n.getType().accept(this, arg) * 31 + (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
    }

    @Override
    public Integer visit(VoidType n, Void arg) {
        return n.getAnnotations().accept(this, arg) * 31 + (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
    }

    @Override
    public Integer visit(WhileStmt n, Void arg) {
        return n.getBody().accept(this, arg) * 31 + n.getCondition().accept(this, arg) * 31 + (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
    }

    @Override
    public Integer visit(WildcardType n, Void arg) {
        return (n.getExtendedType().isPresent() ? n.getExtendedType().get().accept(this, arg) : 0) * 31 + (n.getSuperType().isPresent() ? n.getSuperType().get().accept(this, arg) : 0) * 31 + n.getAnnotations().accept(this, arg) * 31 + (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
    }

    @Override
    public Integer visit(ModuleDeclaration n, Void arg) {
        return n.getAnnotations().accept(this, arg) * 31 + n.getDirectives().accept(this, arg) * 31 + (n.isOpen() ? 1 : 0) * 31 + n.getName().accept(this, arg) * 31 + (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
    }

    @Override
    public Integer visit(ModuleRequiresDirective n, Void arg) {
        return n.getModifiers().accept(this, arg) * 31 + n.getName().accept(this, arg) * 31 + (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
    }

    @Override
    public Integer visit(ModuleExportsDirective n, Void arg) {
        return n.getModuleNames().accept(this, arg) * 31 + n.getName().accept(this, arg) * 31 + (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
    }

    @Override
    public Integer visit(ModuleProvidesDirective n, Void arg) {
        return n.getName().accept(this, arg) * 31 + n.getWith().accept(this, arg) * 31 + (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
    }

    @Override
    public Integer visit(ModuleUsesDirective n, Void arg) {
        return n.getName().accept(this, arg) * 31 + (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
    }

    @Override
    public Integer visit(ModuleOpensDirective n, Void arg) {
        return n.getModuleNames().accept(this, arg) * 31 + n.getName().accept(this, arg) * 31 + (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
    }

    @Override
    public Integer visit(UnparsableStmt n, Void arg) {
        return n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : Integer.valueOf(0);
    }

    @Override
    public Integer visit(ReceiverParameter n, Void arg) {
        return n.getAnnotations().accept(this, arg) * 31 + n.getName().accept(this, arg) * 31 + n.getType().accept(this, arg) * 31 + (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
    }

    @Override
    public Integer visit(VarType n, Void arg) {
        return n.getAnnotations().accept(this, arg) * 31 + (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
    }

    @Override
    public Integer visit(Modifier n, Void arg) {
        return n.getKeyword().hashCode() * 31 + (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
    }

    @Override
    public Integer visit(SwitchExpr n, Void arg) {
        return n.getEntries().accept(this, arg) * 31 + n.getSelector().accept(this, arg) * 31 + (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
    }

    @Override
    public Integer visit(YieldStmt n, Void arg) {
        return n.getExpression().accept(this, arg) * 31 + (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
    }

    @Override
    public Integer visit(TextBlockLiteralExpr n, Void arg) {
        return n.getValue().hashCode() * 31 + (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
    }

    @Override
    public Integer visit(PatternExpr n, Void arg) {
        return n.getName().accept(this, arg) * 31 + n.getType().accept(this, arg) * 31 + (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
    }
}

