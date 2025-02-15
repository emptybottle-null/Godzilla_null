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

public class ObjectIdentityEqualsVisitor
implements GenericVisitor<Boolean, Visitable> {
    private static final ObjectIdentityEqualsVisitor SINGLETON = new ObjectIdentityEqualsVisitor();

    public static boolean equals(Node n, Node n2) {
        return n.accept(SINGLETON, n2);
    }

    @Override
    public Boolean visit(CompilationUnit n, Visitable arg) {
        return n == arg;
    }

    @Override
    public Boolean visit(PackageDeclaration n, Visitable arg) {
        return n == arg;
    }

    @Override
    public Boolean visit(TypeParameter n, Visitable arg) {
        return n == arg;
    }

    @Override
    public Boolean visit(LineComment n, Visitable arg) {
        return n == arg;
    }

    @Override
    public Boolean visit(BlockComment n, Visitable arg) {
        return n == arg;
    }

    @Override
    public Boolean visit(ClassOrInterfaceDeclaration n, Visitable arg) {
        return n == arg;
    }

    @Override
    public Boolean visit(EnumDeclaration n, Visitable arg) {
        return n == arg;
    }

    @Override
    public Boolean visit(EnumConstantDeclaration n, Visitable arg) {
        return n == arg;
    }

    @Override
    public Boolean visit(AnnotationDeclaration n, Visitable arg) {
        return n == arg;
    }

    @Override
    public Boolean visit(AnnotationMemberDeclaration n, Visitable arg) {
        return n == arg;
    }

    @Override
    public Boolean visit(FieldDeclaration n, Visitable arg) {
        return n == arg;
    }

    @Override
    public Boolean visit(VariableDeclarator n, Visitable arg) {
        return n == arg;
    }

    @Override
    public Boolean visit(ConstructorDeclaration n, Visitable arg) {
        return n == arg;
    }

    @Override
    public Boolean visit(MethodDeclaration n, Visitable arg) {
        return n == arg;
    }

    @Override
    public Boolean visit(Parameter n, Visitable arg) {
        return n == arg;
    }

    @Override
    public Boolean visit(InitializerDeclaration n, Visitable arg) {
        return n == arg;
    }

    @Override
    public Boolean visit(JavadocComment n, Visitable arg) {
        return n == arg;
    }

    @Override
    public Boolean visit(ClassOrInterfaceType n, Visitable arg) {
        return n == arg;
    }

    @Override
    public Boolean visit(PrimitiveType n, Visitable arg) {
        return n == arg;
    }

    @Override
    public Boolean visit(ArrayType n, Visitable arg) {
        return n == arg;
    }

    @Override
    public Boolean visit(ArrayCreationLevel n, Visitable arg) {
        return n == arg;
    }

    @Override
    public Boolean visit(IntersectionType n, Visitable arg) {
        return n == arg;
    }

    @Override
    public Boolean visit(UnionType n, Visitable arg) {
        return n == arg;
    }

    @Override
    public Boolean visit(VoidType n, Visitable arg) {
        return n == arg;
    }

    @Override
    public Boolean visit(WildcardType n, Visitable arg) {
        return n == arg;
    }

    @Override
    public Boolean visit(UnknownType n, Visitable arg) {
        return n == arg;
    }

    @Override
    public Boolean visit(ArrayAccessExpr n, Visitable arg) {
        return n == arg;
    }

    @Override
    public Boolean visit(ArrayCreationExpr n, Visitable arg) {
        return n == arg;
    }

    @Override
    public Boolean visit(ArrayInitializerExpr n, Visitable arg) {
        return n == arg;
    }

    @Override
    public Boolean visit(AssignExpr n, Visitable arg) {
        return n == arg;
    }

    @Override
    public Boolean visit(BinaryExpr n, Visitable arg) {
        return n == arg;
    }

    @Override
    public Boolean visit(CastExpr n, Visitable arg) {
        return n == arg;
    }

    @Override
    public Boolean visit(ClassExpr n, Visitable arg) {
        return n == arg;
    }

    @Override
    public Boolean visit(ConditionalExpr n, Visitable arg) {
        return n == arg;
    }

    @Override
    public Boolean visit(EnclosedExpr n, Visitable arg) {
        return n == arg;
    }

    @Override
    public Boolean visit(FieldAccessExpr n, Visitable arg) {
        return n == arg;
    }

    @Override
    public Boolean visit(InstanceOfExpr n, Visitable arg) {
        return n == arg;
    }

    @Override
    public Boolean visit(StringLiteralExpr n, Visitable arg) {
        return n == arg;
    }

    @Override
    public Boolean visit(IntegerLiteralExpr n, Visitable arg) {
        return n == arg;
    }

    @Override
    public Boolean visit(LongLiteralExpr n, Visitable arg) {
        return n == arg;
    }

    @Override
    public Boolean visit(CharLiteralExpr n, Visitable arg) {
        return n == arg;
    }

    @Override
    public Boolean visit(DoubleLiteralExpr n, Visitable arg) {
        return n == arg;
    }

    @Override
    public Boolean visit(BooleanLiteralExpr n, Visitable arg) {
        return n == arg;
    }

    @Override
    public Boolean visit(NullLiteralExpr n, Visitable arg) {
        return n == arg;
    }

    @Override
    public Boolean visit(MethodCallExpr n, Visitable arg) {
        return n == arg;
    }

    @Override
    public Boolean visit(NameExpr n, Visitable arg) {
        return n == arg;
    }

    @Override
    public Boolean visit(ObjectCreationExpr n, Visitable arg) {
        return n == arg;
    }

    @Override
    public Boolean visit(Name n, Visitable arg) {
        return n == arg;
    }

    @Override
    public Boolean visit(SimpleName n, Visitable arg) {
        return n == arg;
    }

    @Override
    public Boolean visit(ThisExpr n, Visitable arg) {
        return n == arg;
    }

    @Override
    public Boolean visit(SuperExpr n, Visitable arg) {
        return n == arg;
    }

    @Override
    public Boolean visit(UnaryExpr n, Visitable arg) {
        return n == arg;
    }

    @Override
    public Boolean visit(VariableDeclarationExpr n, Visitable arg) {
        return n == arg;
    }

    @Override
    public Boolean visit(MarkerAnnotationExpr n, Visitable arg) {
        return n == arg;
    }

    @Override
    public Boolean visit(SingleMemberAnnotationExpr n, Visitable arg) {
        return n == arg;
    }

    @Override
    public Boolean visit(NormalAnnotationExpr n, Visitable arg) {
        return n == arg;
    }

    @Override
    public Boolean visit(MemberValuePair n, Visitable arg) {
        return n == arg;
    }

    @Override
    public Boolean visit(ExplicitConstructorInvocationStmt n, Visitable arg) {
        return n == arg;
    }

    @Override
    public Boolean visit(LocalClassDeclarationStmt n, Visitable arg) {
        return n == arg;
    }

    @Override
    public Boolean visit(AssertStmt n, Visitable arg) {
        return n == arg;
    }

    @Override
    public Boolean visit(BlockStmt n, Visitable arg) {
        return n == arg;
    }

    @Override
    public Boolean visit(LabeledStmt n, Visitable arg) {
        return n == arg;
    }

    @Override
    public Boolean visit(EmptyStmt n, Visitable arg) {
        return n == arg;
    }

    @Override
    public Boolean visit(ExpressionStmt n, Visitable arg) {
        return n == arg;
    }

    @Override
    public Boolean visit(SwitchStmt n, Visitable arg) {
        return n == arg;
    }

    @Override
    public Boolean visit(SwitchEntry n, Visitable arg) {
        return n == arg;
    }

    @Override
    public Boolean visit(BreakStmt n, Visitable arg) {
        return n == arg;
    }

    @Override
    public Boolean visit(ReturnStmt n, Visitable arg) {
        return n == arg;
    }

    @Override
    public Boolean visit(IfStmt n, Visitable arg) {
        return n == arg;
    }

    @Override
    public Boolean visit(WhileStmt n, Visitable arg) {
        return n == arg;
    }

    @Override
    public Boolean visit(ContinueStmt n, Visitable arg) {
        return n == arg;
    }

    @Override
    public Boolean visit(DoStmt n, Visitable arg) {
        return n == arg;
    }

    @Override
    public Boolean visit(ForEachStmt n, Visitable arg) {
        return n == arg;
    }

    @Override
    public Boolean visit(ForStmt n, Visitable arg) {
        return n == arg;
    }

    @Override
    public Boolean visit(ThrowStmt n, Visitable arg) {
        return n == arg;
    }

    @Override
    public Boolean visit(SynchronizedStmt n, Visitable arg) {
        return n == arg;
    }

    @Override
    public Boolean visit(TryStmt n, Visitable arg) {
        return n == arg;
    }

    @Override
    public Boolean visit(CatchClause n, Visitable arg) {
        return n == arg;
    }

    @Override
    public Boolean visit(LambdaExpr n, Visitable arg) {
        return n == arg;
    }

    @Override
    public Boolean visit(MethodReferenceExpr n, Visitable arg) {
        return n == arg;
    }

    @Override
    public Boolean visit(TypeExpr n, Visitable arg) {
        return n == arg;
    }

    @Override
    public Boolean visit(ImportDeclaration n, Visitable arg) {
        return n == arg;
    }

    @Override
    public Boolean visit(NodeList n, Visitable arg) {
        return n == arg;
    }

    @Override
    public Boolean visit(ModuleDeclaration n, Visitable arg) {
        return n == arg;
    }

    @Override
    public Boolean visit(ModuleRequiresDirective n, Visitable arg) {
        return n == arg;
    }

    @Override
    public Boolean visit(ModuleExportsDirective n, Visitable arg) {
        return n == arg;
    }

    @Override
    public Boolean visit(ModuleProvidesDirective n, Visitable arg) {
        return n == arg;
    }

    @Override
    public Boolean visit(ModuleUsesDirective n, Visitable arg) {
        return n == arg;
    }

    @Override
    public Boolean visit(ModuleOpensDirective n, Visitable arg) {
        return n == arg;
    }

    @Override
    public Boolean visit(UnparsableStmt n, Visitable arg) {
        return n == arg;
    }

    @Override
    public Boolean visit(ReceiverParameter n, Visitable arg) {
        return n == arg;
    }

    @Override
    public Boolean visit(VarType n, Visitable arg) {
        return n == arg;
    }

    @Override
    public Boolean visit(Modifier n, Visitable arg) {
        return n == arg;
    }

    @Override
    public Boolean visit(SwitchExpr n, Visitable arg) {
        return n == arg;
    }

    @Override
    public Boolean visit(YieldStmt n, Visitable arg) {
        return n == arg;
    }

    @Override
    public Boolean visit(TextBlockLiteralExpr n, Visitable arg) {
        return n == arg;
    }

    @Override
    public Boolean visit(PatternExpr n, Visitable arg) {
        return n == arg;
    }
}

