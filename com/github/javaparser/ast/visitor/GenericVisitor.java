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

public interface GenericVisitor<R, A> {
    public R visit(CompilationUnit var1, A var2);

    public R visit(PackageDeclaration var1, A var2);

    public R visit(TypeParameter var1, A var2);

    public R visit(LineComment var1, A var2);

    public R visit(BlockComment var1, A var2);

    public R visit(ClassOrInterfaceDeclaration var1, A var2);

    public R visit(EnumDeclaration var1, A var2);

    public R visit(EnumConstantDeclaration var1, A var2);

    public R visit(AnnotationDeclaration var1, A var2);

    public R visit(AnnotationMemberDeclaration var1, A var2);

    public R visit(FieldDeclaration var1, A var2);

    public R visit(VariableDeclarator var1, A var2);

    public R visit(ConstructorDeclaration var1, A var2);

    public R visit(MethodDeclaration var1, A var2);

    public R visit(Parameter var1, A var2);

    public R visit(InitializerDeclaration var1, A var2);

    public R visit(JavadocComment var1, A var2);

    public R visit(ClassOrInterfaceType var1, A var2);

    public R visit(PrimitiveType var1, A var2);

    public R visit(ArrayType var1, A var2);

    public R visit(ArrayCreationLevel var1, A var2);

    public R visit(IntersectionType var1, A var2);

    public R visit(UnionType var1, A var2);

    public R visit(VoidType var1, A var2);

    public R visit(WildcardType var1, A var2);

    public R visit(UnknownType var1, A var2);

    public R visit(ArrayAccessExpr var1, A var2);

    public R visit(ArrayCreationExpr var1, A var2);

    public R visit(ArrayInitializerExpr var1, A var2);

    public R visit(AssignExpr var1, A var2);

    public R visit(BinaryExpr var1, A var2);

    public R visit(CastExpr var1, A var2);

    public R visit(ClassExpr var1, A var2);

    public R visit(ConditionalExpr var1, A var2);

    public R visit(EnclosedExpr var1, A var2);

    public R visit(FieldAccessExpr var1, A var2);

    public R visit(InstanceOfExpr var1, A var2);

    public R visit(StringLiteralExpr var1, A var2);

    public R visit(IntegerLiteralExpr var1, A var2);

    public R visit(LongLiteralExpr var1, A var2);

    public R visit(CharLiteralExpr var1, A var2);

    public R visit(DoubleLiteralExpr var1, A var2);

    public R visit(BooleanLiteralExpr var1, A var2);

    public R visit(NullLiteralExpr var1, A var2);

    public R visit(MethodCallExpr var1, A var2);

    public R visit(NameExpr var1, A var2);

    public R visit(ObjectCreationExpr var1, A var2);

    public R visit(ThisExpr var1, A var2);

    public R visit(SuperExpr var1, A var2);

    public R visit(UnaryExpr var1, A var2);

    public R visit(VariableDeclarationExpr var1, A var2);

    public R visit(MarkerAnnotationExpr var1, A var2);

    public R visit(SingleMemberAnnotationExpr var1, A var2);

    public R visit(NormalAnnotationExpr var1, A var2);

    public R visit(MemberValuePair var1, A var2);

    public R visit(ExplicitConstructorInvocationStmt var1, A var2);

    public R visit(LocalClassDeclarationStmt var1, A var2);

    public R visit(AssertStmt var1, A var2);

    public R visit(BlockStmt var1, A var2);

    public R visit(LabeledStmt var1, A var2);

    public R visit(EmptyStmt var1, A var2);

    public R visit(ExpressionStmt var1, A var2);

    public R visit(SwitchStmt var1, A var2);

    public R visit(SwitchEntry var1, A var2);

    public R visit(BreakStmt var1, A var2);

    public R visit(ReturnStmt var1, A var2);

    public R visit(IfStmt var1, A var2);

    public R visit(WhileStmt var1, A var2);

    public R visit(ContinueStmt var1, A var2);

    public R visit(DoStmt var1, A var2);

    public R visit(ForEachStmt var1, A var2);

    public R visit(ForStmt var1, A var2);

    public R visit(ThrowStmt var1, A var2);

    public R visit(SynchronizedStmt var1, A var2);

    public R visit(TryStmt var1, A var2);

    public R visit(CatchClause var1, A var2);

    public R visit(LambdaExpr var1, A var2);

    public R visit(MethodReferenceExpr var1, A var2);

    public R visit(TypeExpr var1, A var2);

    public R visit(NodeList var1, A var2);

    public R visit(Name var1, A var2);

    public R visit(SimpleName var1, A var2);

    public R visit(ImportDeclaration var1, A var2);

    public R visit(ModuleDeclaration var1, A var2);

    public R visit(ModuleRequiresDirective var1, A var2);

    public R visit(ModuleExportsDirective var1, A var2);

    public R visit(ModuleProvidesDirective var1, A var2);

    public R visit(ModuleUsesDirective var1, A var2);

    public R visit(ModuleOpensDirective var1, A var2);

    public R visit(UnparsableStmt var1, A var2);

    public R visit(ReceiverParameter var1, A var2);

    public R visit(VarType var1, A var2);

    public R visit(Modifier var1, A var2);

    public R visit(SwitchExpr var1, A var2);

    public R visit(YieldStmt var1, A var2);

    public R visit(TextBlockLiteralExpr var1, A var2);

    public R visit(PatternExpr var1, A var2);
}

