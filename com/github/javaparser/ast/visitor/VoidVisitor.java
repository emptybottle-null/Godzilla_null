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

public interface VoidVisitor<A> {
    public void visit(NodeList var1, A var2);

    public void visit(AnnotationDeclaration var1, A var2);

    public void visit(AnnotationMemberDeclaration var1, A var2);

    public void visit(ArrayAccessExpr var1, A var2);

    public void visit(ArrayCreationExpr var1, A var2);

    public void visit(ArrayCreationLevel var1, A var2);

    public void visit(ArrayInitializerExpr var1, A var2);

    public void visit(ArrayType var1, A var2);

    public void visit(AssertStmt var1, A var2);

    public void visit(AssignExpr var1, A var2);

    public void visit(BinaryExpr var1, A var2);

    public void visit(BlockComment var1, A var2);

    public void visit(BlockStmt var1, A var2);

    public void visit(BooleanLiteralExpr var1, A var2);

    public void visit(BreakStmt var1, A var2);

    public void visit(CastExpr var1, A var2);

    public void visit(CatchClause var1, A var2);

    public void visit(CharLiteralExpr var1, A var2);

    public void visit(ClassExpr var1, A var2);

    public void visit(ClassOrInterfaceDeclaration var1, A var2);

    public void visit(ClassOrInterfaceType var1, A var2);

    public void visit(CompilationUnit var1, A var2);

    public void visit(ConditionalExpr var1, A var2);

    public void visit(ConstructorDeclaration var1, A var2);

    public void visit(ContinueStmt var1, A var2);

    public void visit(DoStmt var1, A var2);

    public void visit(DoubleLiteralExpr var1, A var2);

    public void visit(EmptyStmt var1, A var2);

    public void visit(EnclosedExpr var1, A var2);

    public void visit(EnumConstantDeclaration var1, A var2);

    public void visit(EnumDeclaration var1, A var2);

    public void visit(ExplicitConstructorInvocationStmt var1, A var2);

    public void visit(ExpressionStmt var1, A var2);

    public void visit(FieldAccessExpr var1, A var2);

    public void visit(FieldDeclaration var1, A var2);

    public void visit(ForStmt var1, A var2);

    public void visit(ForEachStmt var1, A var2);

    public void visit(IfStmt var1, A var2);

    public void visit(ImportDeclaration var1, A var2);

    public void visit(InitializerDeclaration var1, A var2);

    public void visit(InstanceOfExpr var1, A var2);

    public void visit(IntegerLiteralExpr var1, A var2);

    public void visit(IntersectionType var1, A var2);

    public void visit(JavadocComment var1, A var2);

    public void visit(LabeledStmt var1, A var2);

    public void visit(LambdaExpr var1, A var2);

    public void visit(LineComment var1, A var2);

    public void visit(LocalClassDeclarationStmt var1, A var2);

    public void visit(LongLiteralExpr var1, A var2);

    public void visit(MarkerAnnotationExpr var1, A var2);

    public void visit(MemberValuePair var1, A var2);

    public void visit(MethodCallExpr var1, A var2);

    public void visit(MethodDeclaration var1, A var2);

    public void visit(MethodReferenceExpr var1, A var2);

    public void visit(NameExpr var1, A var2);

    public void visit(Name var1, A var2);

    public void visit(NormalAnnotationExpr var1, A var2);

    public void visit(NullLiteralExpr var1, A var2);

    public void visit(ObjectCreationExpr var1, A var2);

    public void visit(PackageDeclaration var1, A var2);

    public void visit(Parameter var1, A var2);

    public void visit(PrimitiveType var1, A var2);

    public void visit(ReturnStmt var1, A var2);

    public void visit(SimpleName var1, A var2);

    public void visit(SingleMemberAnnotationExpr var1, A var2);

    public void visit(StringLiteralExpr var1, A var2);

    public void visit(SuperExpr var1, A var2);

    public void visit(SwitchEntry var1, A var2);

    public void visit(SwitchStmt var1, A var2);

    public void visit(SynchronizedStmt var1, A var2);

    public void visit(ThisExpr var1, A var2);

    public void visit(ThrowStmt var1, A var2);

    public void visit(TryStmt var1, A var2);

    public void visit(TypeExpr var1, A var2);

    public void visit(TypeParameter var1, A var2);

    public void visit(UnaryExpr var1, A var2);

    public void visit(UnionType var1, A var2);

    public void visit(UnknownType var1, A var2);

    public void visit(VariableDeclarationExpr var1, A var2);

    public void visit(VariableDeclarator var1, A var2);

    public void visit(VoidType var1, A var2);

    public void visit(WhileStmt var1, A var2);

    public void visit(WildcardType var1, A var2);

    public void visit(ModuleDeclaration var1, A var2);

    public void visit(ModuleRequiresDirective var1, A var2);

    public void visit(ModuleExportsDirective var1, A var2);

    public void visit(ModuleProvidesDirective var1, A var2);

    public void visit(ModuleUsesDirective var1, A var2);

    public void visit(ModuleOpensDirective var1, A var2);

    public void visit(UnparsableStmt var1, A var2);

    public void visit(ReceiverParameter var1, A var2);

    public void visit(VarType var1, A var2);

    public void visit(Modifier var1, A var2);

    public void visit(SwitchExpr var1, A var2);

    public void visit(TextBlockLiteralExpr var1, A var2);

    public void visit(YieldStmt var1, A var2);

    public void visit(PatternExpr var1, A var2);
}

