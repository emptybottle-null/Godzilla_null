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
import com.github.javaparser.ast.visitor.VoidVisitor;

public abstract class VoidVisitorWithDefaults<A>
implements VoidVisitor<A> {
    public void defaultAction(Node n, A arg) {
    }

    public void defaultAction(NodeList n, A arg) {
    }

    @Override
    public void visit(AnnotationDeclaration n, A arg) {
        this.defaultAction(n, arg);
    }

    @Override
    public void visit(AnnotationMemberDeclaration n, A arg) {
        this.defaultAction(n, arg);
    }

    @Override
    public void visit(ArrayAccessExpr n, A arg) {
        this.defaultAction(n, arg);
    }

    @Override
    public void visit(ArrayCreationExpr n, A arg) {
        this.defaultAction(n, arg);
    }

    @Override
    public void visit(ArrayInitializerExpr n, A arg) {
        this.defaultAction(n, arg);
    }

    @Override
    public void visit(AssertStmt n, A arg) {
        this.defaultAction(n, arg);
    }

    @Override
    public void visit(AssignExpr n, A arg) {
        this.defaultAction(n, arg);
    }

    @Override
    public void visit(BinaryExpr n, A arg) {
        this.defaultAction(n, arg);
    }

    @Override
    public void visit(BlockComment n, A arg) {
        this.defaultAction(n, arg);
    }

    @Override
    public void visit(BlockStmt n, A arg) {
        this.defaultAction(n, arg);
    }

    @Override
    public void visit(BooleanLiteralExpr n, A arg) {
        this.defaultAction(n, arg);
    }

    @Override
    public void visit(BreakStmt n, A arg) {
        this.defaultAction(n, arg);
    }

    @Override
    public void visit(CastExpr n, A arg) {
        this.defaultAction(n, arg);
    }

    @Override
    public void visit(CatchClause n, A arg) {
        this.defaultAction(n, arg);
    }

    @Override
    public void visit(CharLiteralExpr n, A arg) {
        this.defaultAction(n, arg);
    }

    @Override
    public void visit(ClassExpr n, A arg) {
        this.defaultAction(n, arg);
    }

    @Override
    public void visit(ClassOrInterfaceDeclaration n, A arg) {
        this.defaultAction(n, arg);
    }

    @Override
    public void visit(ClassOrInterfaceType n, A arg) {
        this.defaultAction(n, arg);
    }

    @Override
    public void visit(CompilationUnit n, A arg) {
        this.defaultAction(n, arg);
    }

    @Override
    public void visit(ConditionalExpr n, A arg) {
        this.defaultAction(n, arg);
    }

    @Override
    public void visit(ConstructorDeclaration n, A arg) {
        this.defaultAction(n, arg);
    }

    @Override
    public void visit(ContinueStmt n, A arg) {
        this.defaultAction(n, arg);
    }

    @Override
    public void visit(DoStmt n, A arg) {
        this.defaultAction(n, arg);
    }

    @Override
    public void visit(DoubleLiteralExpr n, A arg) {
        this.defaultAction(n, arg);
    }

    @Override
    public void visit(EmptyStmt n, A arg) {
        this.defaultAction(n, arg);
    }

    @Override
    public void visit(EnclosedExpr n, A arg) {
        this.defaultAction(n, arg);
    }

    @Override
    public void visit(EnumConstantDeclaration n, A arg) {
        this.defaultAction(n, arg);
    }

    @Override
    public void visit(EnumDeclaration n, A arg) {
        this.defaultAction(n, arg);
    }

    @Override
    public void visit(ExplicitConstructorInvocationStmt n, A arg) {
        this.defaultAction(n, arg);
    }

    @Override
    public void visit(ExpressionStmt n, A arg) {
        this.defaultAction(n, arg);
    }

    @Override
    public void visit(FieldAccessExpr n, A arg) {
        this.defaultAction(n, arg);
    }

    @Override
    public void visit(FieldDeclaration n, A arg) {
        this.defaultAction(n, arg);
    }

    @Override
    public void visit(ForEachStmt n, A arg) {
        this.defaultAction(n, arg);
    }

    @Override
    public void visit(ForStmt n, A arg) {
        this.defaultAction(n, arg);
    }

    @Override
    public void visit(IfStmt n, A arg) {
        this.defaultAction(n, arg);
    }

    @Override
    public void visit(InitializerDeclaration n, A arg) {
        this.defaultAction(n, arg);
    }

    @Override
    public void visit(InstanceOfExpr n, A arg) {
        this.defaultAction(n, arg);
    }

    @Override
    public void visit(IntegerLiteralExpr n, A arg) {
        this.defaultAction(n, arg);
    }

    @Override
    public void visit(JavadocComment n, A arg) {
        this.defaultAction(n, arg);
    }

    @Override
    public void visit(LabeledStmt n, A arg) {
        this.defaultAction(n, arg);
    }

    @Override
    public void visit(LineComment n, A arg) {
        this.defaultAction(n, arg);
    }

    @Override
    public void visit(LongLiteralExpr n, A arg) {
        this.defaultAction(n, arg);
    }

    @Override
    public void visit(MarkerAnnotationExpr n, A arg) {
        this.defaultAction(n, arg);
    }

    @Override
    public void visit(MemberValuePair n, A arg) {
        this.defaultAction(n, arg);
    }

    @Override
    public void visit(MethodCallExpr n, A arg) {
        this.defaultAction(n, arg);
    }

    @Override
    public void visit(MethodDeclaration n, A arg) {
        this.defaultAction(n, arg);
    }

    @Override
    public void visit(NameExpr n, A arg) {
        this.defaultAction(n, arg);
    }

    @Override
    public void visit(NormalAnnotationExpr n, A arg) {
        this.defaultAction(n, arg);
    }

    @Override
    public void visit(NullLiteralExpr n, A arg) {
        this.defaultAction(n, arg);
    }

    @Override
    public void visit(ObjectCreationExpr n, A arg) {
        this.defaultAction(n, arg);
    }

    @Override
    public void visit(PackageDeclaration n, A arg) {
        this.defaultAction(n, arg);
    }

    @Override
    public void visit(Parameter n, A arg) {
        this.defaultAction(n, arg);
    }

    @Override
    public void visit(PrimitiveType n, A arg) {
        this.defaultAction(n, arg);
    }

    @Override
    public void visit(Name n, A arg) {
        this.defaultAction(n, arg);
    }

    @Override
    public void visit(SimpleName n, A arg) {
        this.defaultAction(n, arg);
    }

    @Override
    public void visit(ArrayType n, A arg) {
        this.defaultAction(n, arg);
    }

    @Override
    public void visit(ArrayCreationLevel n, A arg) {
        this.defaultAction(n, arg);
    }

    @Override
    public void visit(IntersectionType n, A arg) {
        this.defaultAction(n, arg);
    }

    @Override
    public void visit(UnionType n, A arg) {
        this.defaultAction(n, arg);
    }

    @Override
    public void visit(ReturnStmt n, A arg) {
        this.defaultAction(n, arg);
    }

    @Override
    public void visit(SingleMemberAnnotationExpr n, A arg) {
        this.defaultAction(n, arg);
    }

    @Override
    public void visit(StringLiteralExpr n, A arg) {
        this.defaultAction(n, arg);
    }

    @Override
    public void visit(SuperExpr n, A arg) {
        this.defaultAction(n, arg);
    }

    @Override
    public void visit(SwitchEntry n, A arg) {
        this.defaultAction(n, arg);
    }

    @Override
    public void visit(SwitchStmt n, A arg) {
        this.defaultAction(n, arg);
    }

    @Override
    public void visit(SynchronizedStmt n, A arg) {
        this.defaultAction(n, arg);
    }

    @Override
    public void visit(ThisExpr n, A arg) {
        this.defaultAction(n, arg);
    }

    @Override
    public void visit(ThrowStmt n, A arg) {
        this.defaultAction(n, arg);
    }

    @Override
    public void visit(TryStmt n, A arg) {
        this.defaultAction(n, arg);
    }

    @Override
    public void visit(LocalClassDeclarationStmt n, A arg) {
        this.defaultAction(n, arg);
    }

    @Override
    public void visit(TypeParameter n, A arg) {
        this.defaultAction(n, arg);
    }

    @Override
    public void visit(UnaryExpr n, A arg) {
        this.defaultAction(n, arg);
    }

    @Override
    public void visit(UnknownType n, A arg) {
        this.defaultAction(n, arg);
    }

    @Override
    public void visit(VariableDeclarationExpr n, A arg) {
        this.defaultAction(n, arg);
    }

    @Override
    public void visit(VariableDeclarator n, A arg) {
        this.defaultAction(n, arg);
    }

    @Override
    public void visit(VoidType n, A arg) {
        this.defaultAction(n, arg);
    }

    @Override
    public void visit(WhileStmt n, A arg) {
        this.defaultAction(n, arg);
    }

    @Override
    public void visit(WildcardType n, A arg) {
        this.defaultAction(n, arg);
    }

    @Override
    public void visit(LambdaExpr n, A arg) {
        this.defaultAction(n, arg);
    }

    @Override
    public void visit(MethodReferenceExpr n, A arg) {
        this.defaultAction(n, arg);
    }

    @Override
    public void visit(TypeExpr n, A arg) {
        this.defaultAction(n, arg);
    }

    @Override
    public void visit(NodeList n, A arg) {
        this.defaultAction(n, arg);
    }

    @Override
    public void visit(ImportDeclaration n, A arg) {
        this.defaultAction(n, arg);
    }

    @Override
    public void visit(ModuleDeclaration n, A arg) {
        this.defaultAction(n, arg);
    }

    @Override
    public void visit(ModuleRequiresDirective n, A arg) {
        this.defaultAction(n, arg);
    }

    @Override
    public void visit(ModuleExportsDirective n, A arg) {
        this.defaultAction(n, arg);
    }

    @Override
    public void visit(ModuleProvidesDirective n, A arg) {
        this.defaultAction(n, arg);
    }

    @Override
    public void visit(ModuleUsesDirective n, A arg) {
        this.defaultAction(n, arg);
    }

    @Override
    public void visit(ModuleOpensDirective n, A arg) {
        this.defaultAction(n, arg);
    }

    @Override
    public void visit(UnparsableStmt n, A arg) {
        this.defaultAction(n, arg);
    }

    @Override
    public void visit(ReceiverParameter n, A arg) {
        this.defaultAction(n, arg);
    }

    @Override
    public void visit(Modifier n, A arg) {
        this.defaultAction(n, arg);
    }

    @Override
    public void visit(VarType n, A arg) {
        this.defaultAction(n, arg);
    }

    @Override
    public void visit(SwitchExpr n, A arg) {
        this.defaultAction(n, arg);
    }

    @Override
    public void visit(TextBlockLiteralExpr n, A arg) {
        this.defaultAction(n, arg);
    }

    @Override
    public void visit(YieldStmt n, A arg) {
        this.defaultAction(n, arg);
    }

    @Override
    public void visit(PatternExpr n, A arg) {
        this.defaultAction(n, arg);
    }
}

