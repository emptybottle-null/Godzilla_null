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
import java.util.List;
import java.util.Optional;

public class EqualsVisitor
implements GenericVisitor<Boolean, Visitable> {
    private static final EqualsVisitor SINGLETON = new EqualsVisitor();

    public static boolean equals(Node n, Node n2) {
        return SINGLETON.nodeEquals(n, n2);
    }

    private EqualsVisitor() {
    }

    private boolean commonNodeEquality(Node n, Node n2) {
        if (!this.nodeEquals(n.getComment(), n2.getComment())) {
            return false;
        }
        return this.nodesEquals(n.getOrphanComments(), n2.getOrphanComments());
    }

    private <T extends Node> boolean nodesEquals(List<T> nodes1, List<T> nodes2) {
        if (nodes1 == null) {
            return nodes2 == null;
        }
        if (nodes2 == null) {
            return false;
        }
        if (nodes1.size() != nodes2.size()) {
            return false;
        }
        for (int i = 0; i < nodes1.size(); ++i) {
            if (this.nodeEquals((Node)nodes1.get(i), (Node)nodes2.get(i))) continue;
            return false;
        }
        return true;
    }

    private <N extends Node> boolean nodesEquals(NodeList<N> n, NodeList<N> n2) {
        if (n == n2) {
            return true;
        }
        if (n == null || n2 == null) {
            return false;
        }
        if (n.size() != n2.size()) {
            return false;
        }
        for (int i = 0; i < n.size(); ++i) {
            if (this.nodeEquals(n.get(i), n2.get(i))) continue;
            return false;
        }
        return true;
    }

    private <T extends Node> boolean nodeEquals(T n, T n2) {
        if (n == n2) {
            return true;
        }
        if (n == null || n2 == null) {
            return false;
        }
        if (n.getClass() != n2.getClass()) {
            return false;
        }
        if (!this.commonNodeEquality(n, n2)) {
            return false;
        }
        return n.accept(this, n2);
    }

    private <T extends Node> boolean nodeEquals(Optional<T> n, Optional<T> n2) {
        return this.nodeEquals((T)n.orElse(null), (T)n2.orElse(null));
    }

    private <T extends Node> boolean nodesEquals(Optional<NodeList<T>> n, Optional<NodeList<T>> n2) {
        return this.nodesEquals((NodeList<N>)n.orElse(null), (NodeList<N>)n2.orElse(null));
    }

    private boolean objEquals(Object n, Object n2) {
        if (n == n2) {
            return true;
        }
        if (n == null || n2 == null) {
            return false;
        }
        return n.equals(n2);
    }

    @Override
    public Boolean visit(CompilationUnit n, Visitable arg) {
        CompilationUnit n2 = (CompilationUnit)arg;
        if (!this.nodesEquals(n.getImports(), n2.getImports())) {
            return false;
        }
        if (!this.nodeEquals(n.getModule(), n2.getModule())) {
            return false;
        }
        if (!this.nodeEquals(n.getPackageDeclaration(), n2.getPackageDeclaration())) {
            return false;
        }
        if (!this.nodesEquals(n.getTypes(), n2.getTypes())) {
            return false;
        }
        if (!this.nodeEquals(n.getComment(), n2.getComment())) {
            return false;
        }
        return true;
    }

    @Override
    public Boolean visit(PackageDeclaration n, Visitable arg) {
        PackageDeclaration n2 = (PackageDeclaration)arg;
        if (!this.nodesEquals(n.getAnnotations(), n2.getAnnotations())) {
            return false;
        }
        if (!this.nodeEquals(n.getName(), n2.getName())) {
            return false;
        }
        if (!this.nodeEquals(n.getComment(), n2.getComment())) {
            return false;
        }
        return true;
    }

    @Override
    public Boolean visit(TypeParameter n, Visitable arg) {
        TypeParameter n2 = (TypeParameter)arg;
        if (!this.nodeEquals(n.getName(), n2.getName())) {
            return false;
        }
        if (!this.nodesEquals(n.getTypeBound(), n2.getTypeBound())) {
            return false;
        }
        if (!this.nodesEquals(n.getAnnotations(), n2.getAnnotations())) {
            return false;
        }
        if (!this.nodeEquals(n.getComment(), n2.getComment())) {
            return false;
        }
        return true;
    }

    @Override
    public Boolean visit(LineComment n, Visitable arg) {
        LineComment n2 = (LineComment)arg;
        if (!this.objEquals(n.getContent(), n2.getContent())) {
            return false;
        }
        if (!this.nodeEquals(n.getComment(), n2.getComment())) {
            return false;
        }
        return true;
    }

    @Override
    public Boolean visit(BlockComment n, Visitable arg) {
        BlockComment n2 = (BlockComment)arg;
        if (!this.objEquals(n.getContent(), n2.getContent())) {
            return false;
        }
        if (!this.nodeEquals(n.getComment(), n2.getComment())) {
            return false;
        }
        return true;
    }

    @Override
    public Boolean visit(ClassOrInterfaceDeclaration n, Visitable arg) {
        ClassOrInterfaceDeclaration n2 = (ClassOrInterfaceDeclaration)arg;
        if (!this.nodesEquals(n.getExtendedTypes(), n2.getExtendedTypes())) {
            return false;
        }
        if (!this.nodesEquals(n.getImplementedTypes(), n2.getImplementedTypes())) {
            return false;
        }
        if (!this.objEquals(n.isInterface(), n2.isInterface())) {
            return false;
        }
        if (!this.nodesEquals(n.getTypeParameters(), n2.getTypeParameters())) {
            return false;
        }
        if (!this.nodesEquals(n.getMembers(), n2.getMembers())) {
            return false;
        }
        if (!this.nodesEquals(n.getModifiers(), n2.getModifiers())) {
            return false;
        }
        if (!this.nodeEquals(n.getName(), n2.getName())) {
            return false;
        }
        if (!this.nodesEquals(n.getAnnotations(), n2.getAnnotations())) {
            return false;
        }
        if (!this.nodeEquals(n.getComment(), n2.getComment())) {
            return false;
        }
        return true;
    }

    @Override
    public Boolean visit(EnumDeclaration n, Visitable arg) {
        EnumDeclaration n2 = (EnumDeclaration)arg;
        if (!this.nodesEquals(n.getEntries(), n2.getEntries())) {
            return false;
        }
        if (!this.nodesEquals(n.getImplementedTypes(), n2.getImplementedTypes())) {
            return false;
        }
        if (!this.nodesEquals(n.getMembers(), n2.getMembers())) {
            return false;
        }
        if (!this.nodesEquals(n.getModifiers(), n2.getModifiers())) {
            return false;
        }
        if (!this.nodeEquals(n.getName(), n2.getName())) {
            return false;
        }
        if (!this.nodesEquals(n.getAnnotations(), n2.getAnnotations())) {
            return false;
        }
        if (!this.nodeEquals(n.getComment(), n2.getComment())) {
            return false;
        }
        return true;
    }

    @Override
    public Boolean visit(EnumConstantDeclaration n, Visitable arg) {
        EnumConstantDeclaration n2 = (EnumConstantDeclaration)arg;
        if (!this.nodesEquals(n.getArguments(), n2.getArguments())) {
            return false;
        }
        if (!this.nodesEquals(n.getClassBody(), n2.getClassBody())) {
            return false;
        }
        if (!this.nodeEquals(n.getName(), n2.getName())) {
            return false;
        }
        if (!this.nodesEquals(n.getAnnotations(), n2.getAnnotations())) {
            return false;
        }
        if (!this.nodeEquals(n.getComment(), n2.getComment())) {
            return false;
        }
        return true;
    }

    @Override
    public Boolean visit(AnnotationDeclaration n, Visitable arg) {
        AnnotationDeclaration n2 = (AnnotationDeclaration)arg;
        if (!this.nodesEquals(n.getMembers(), n2.getMembers())) {
            return false;
        }
        if (!this.nodesEquals(n.getModifiers(), n2.getModifiers())) {
            return false;
        }
        if (!this.nodeEquals(n.getName(), n2.getName())) {
            return false;
        }
        if (!this.nodesEquals(n.getAnnotations(), n2.getAnnotations())) {
            return false;
        }
        if (!this.nodeEquals(n.getComment(), n2.getComment())) {
            return false;
        }
        return true;
    }

    @Override
    public Boolean visit(AnnotationMemberDeclaration n, Visitable arg) {
        AnnotationMemberDeclaration n2 = (AnnotationMemberDeclaration)arg;
        if (!this.nodeEquals(n.getDefaultValue(), n2.getDefaultValue())) {
            return false;
        }
        if (!this.nodesEquals(n.getModifiers(), n2.getModifiers())) {
            return false;
        }
        if (!this.nodeEquals(n.getName(), n2.getName())) {
            return false;
        }
        if (!this.nodeEquals(n.getType(), n2.getType())) {
            return false;
        }
        if (!this.nodesEquals(n.getAnnotations(), n2.getAnnotations())) {
            return false;
        }
        if (!this.nodeEquals(n.getComment(), n2.getComment())) {
            return false;
        }
        return true;
    }

    @Override
    public Boolean visit(FieldDeclaration n, Visitable arg) {
        FieldDeclaration n2 = (FieldDeclaration)arg;
        if (!this.nodesEquals(n.getModifiers(), n2.getModifiers())) {
            return false;
        }
        if (!this.nodesEquals(n.getVariables(), n2.getVariables())) {
            return false;
        }
        if (!this.nodesEquals(n.getAnnotations(), n2.getAnnotations())) {
            return false;
        }
        if (!this.nodeEquals(n.getComment(), n2.getComment())) {
            return false;
        }
        return true;
    }

    @Override
    public Boolean visit(VariableDeclarator n, Visitable arg) {
        VariableDeclarator n2 = (VariableDeclarator)arg;
        if (!this.nodeEquals(n.getInitializer(), n2.getInitializer())) {
            return false;
        }
        if (!this.nodeEquals(n.getName(), n2.getName())) {
            return false;
        }
        if (!this.nodeEquals(n.getType(), n2.getType())) {
            return false;
        }
        if (!this.nodeEquals(n.getComment(), n2.getComment())) {
            return false;
        }
        return true;
    }

    @Override
    public Boolean visit(ConstructorDeclaration n, Visitable arg) {
        ConstructorDeclaration n2 = (ConstructorDeclaration)arg;
        if (!this.nodeEquals(n.getBody(), n2.getBody())) {
            return false;
        }
        if (!this.nodesEquals(n.getModifiers(), n2.getModifiers())) {
            return false;
        }
        if (!this.nodeEquals(n.getName(), n2.getName())) {
            return false;
        }
        if (!this.nodesEquals(n.getParameters(), n2.getParameters())) {
            return false;
        }
        if (!this.nodeEquals(n.getReceiverParameter(), n2.getReceiverParameter())) {
            return false;
        }
        if (!this.nodesEquals(n.getThrownExceptions(), n2.getThrownExceptions())) {
            return false;
        }
        if (!this.nodesEquals(n.getTypeParameters(), n2.getTypeParameters())) {
            return false;
        }
        if (!this.nodesEquals(n.getAnnotations(), n2.getAnnotations())) {
            return false;
        }
        if (!this.nodeEquals(n.getComment(), n2.getComment())) {
            return false;
        }
        return true;
    }

    @Override
    public Boolean visit(MethodDeclaration n, Visitable arg) {
        MethodDeclaration n2 = (MethodDeclaration)arg;
        if (!this.nodeEquals(n.getBody(), n2.getBody())) {
            return false;
        }
        if (!this.nodeEquals(n.getType(), n2.getType())) {
            return false;
        }
        if (!this.nodesEquals(n.getModifiers(), n2.getModifiers())) {
            return false;
        }
        if (!this.nodeEquals(n.getName(), n2.getName())) {
            return false;
        }
        if (!this.nodesEquals(n.getParameters(), n2.getParameters())) {
            return false;
        }
        if (!this.nodeEquals(n.getReceiverParameter(), n2.getReceiverParameter())) {
            return false;
        }
        if (!this.nodesEquals(n.getThrownExceptions(), n2.getThrownExceptions())) {
            return false;
        }
        if (!this.nodesEquals(n.getTypeParameters(), n2.getTypeParameters())) {
            return false;
        }
        if (!this.nodesEquals(n.getAnnotations(), n2.getAnnotations())) {
            return false;
        }
        if (!this.nodeEquals(n.getComment(), n2.getComment())) {
            return false;
        }
        return true;
    }

    @Override
    public Boolean visit(Parameter n, Visitable arg) {
        Parameter n2 = (Parameter)arg;
        if (!this.nodesEquals(n.getAnnotations(), n2.getAnnotations())) {
            return false;
        }
        if (!this.objEquals(n.isVarArgs(), n2.isVarArgs())) {
            return false;
        }
        if (!this.nodesEquals(n.getModifiers(), n2.getModifiers())) {
            return false;
        }
        if (!this.nodeEquals(n.getName(), n2.getName())) {
            return false;
        }
        if (!this.nodeEquals(n.getType(), n2.getType())) {
            return false;
        }
        if (!this.nodesEquals(n.getVarArgsAnnotations(), n2.getVarArgsAnnotations())) {
            return false;
        }
        if (!this.nodeEquals(n.getComment(), n2.getComment())) {
            return false;
        }
        return true;
    }

    @Override
    public Boolean visit(InitializerDeclaration n, Visitable arg) {
        InitializerDeclaration n2 = (InitializerDeclaration)arg;
        if (!this.nodeEquals(n.getBody(), n2.getBody())) {
            return false;
        }
        if (!this.objEquals(n.isStatic(), n2.isStatic())) {
            return false;
        }
        if (!this.nodesEquals(n.getAnnotations(), n2.getAnnotations())) {
            return false;
        }
        if (!this.nodeEquals(n.getComment(), n2.getComment())) {
            return false;
        }
        return true;
    }

    @Override
    public Boolean visit(JavadocComment n, Visitable arg) {
        JavadocComment n2 = (JavadocComment)arg;
        if (!this.objEquals(n.getContent(), n2.getContent())) {
            return false;
        }
        if (!this.nodeEquals(n.getComment(), n2.getComment())) {
            return false;
        }
        return true;
    }

    @Override
    public Boolean visit(ClassOrInterfaceType n, Visitable arg) {
        ClassOrInterfaceType n2 = (ClassOrInterfaceType)arg;
        if (!this.nodeEquals(n.getName(), n2.getName())) {
            return false;
        }
        if (!this.nodeEquals(n.getScope(), n2.getScope())) {
            return false;
        }
        if (!this.nodesEquals(n.getTypeArguments(), n2.getTypeArguments())) {
            return false;
        }
        if (!this.nodesEquals(n.getAnnotations(), n2.getAnnotations())) {
            return false;
        }
        if (!this.nodeEquals(n.getComment(), n2.getComment())) {
            return false;
        }
        return true;
    }

    @Override
    public Boolean visit(PrimitiveType n, Visitable arg) {
        PrimitiveType n2 = (PrimitiveType)arg;
        if (!this.objEquals((Object)n.getType(), (Object)n2.getType())) {
            return false;
        }
        if (!this.nodesEquals(n.getAnnotations(), n2.getAnnotations())) {
            return false;
        }
        if (!this.nodeEquals(n.getComment(), n2.getComment())) {
            return false;
        }
        return true;
    }

    @Override
    public Boolean visit(ArrayType n, Visitable arg) {
        ArrayType n2 = (ArrayType)arg;
        if (!this.nodeEquals(n.getComponentType(), n2.getComponentType())) {
            return false;
        }
        if (!this.objEquals((Object)n.getOrigin(), (Object)n2.getOrigin())) {
            return false;
        }
        if (!this.nodesEquals(n.getAnnotations(), n2.getAnnotations())) {
            return false;
        }
        if (!this.nodeEquals(n.getComment(), n2.getComment())) {
            return false;
        }
        return true;
    }

    @Override
    public Boolean visit(ArrayCreationLevel n, Visitable arg) {
        ArrayCreationLevel n2 = (ArrayCreationLevel)arg;
        if (!this.nodesEquals(n.getAnnotations(), n2.getAnnotations())) {
            return false;
        }
        if (!this.nodeEquals(n.getDimension(), n2.getDimension())) {
            return false;
        }
        if (!this.nodeEquals(n.getComment(), n2.getComment())) {
            return false;
        }
        return true;
    }

    @Override
    public Boolean visit(IntersectionType n, Visitable arg) {
        IntersectionType n2 = (IntersectionType)arg;
        if (!this.nodesEquals(n.getElements(), n2.getElements())) {
            return false;
        }
        if (!this.nodesEquals(n.getAnnotations(), n2.getAnnotations())) {
            return false;
        }
        if (!this.nodeEquals(n.getComment(), n2.getComment())) {
            return false;
        }
        return true;
    }

    @Override
    public Boolean visit(UnionType n, Visitable arg) {
        UnionType n2 = (UnionType)arg;
        if (!this.nodesEquals(n.getElements(), n2.getElements())) {
            return false;
        }
        if (!this.nodesEquals(n.getAnnotations(), n2.getAnnotations())) {
            return false;
        }
        if (!this.nodeEquals(n.getComment(), n2.getComment())) {
            return false;
        }
        return true;
    }

    @Override
    public Boolean visit(VoidType n, Visitable arg) {
        VoidType n2 = (VoidType)arg;
        if (!this.nodesEquals(n.getAnnotations(), n2.getAnnotations())) {
            return false;
        }
        if (!this.nodeEquals(n.getComment(), n2.getComment())) {
            return false;
        }
        return true;
    }

    @Override
    public Boolean visit(WildcardType n, Visitable arg) {
        WildcardType n2 = (WildcardType)arg;
        if (!this.nodeEquals(n.getExtendedType(), n2.getExtendedType())) {
            return false;
        }
        if (!this.nodeEquals(n.getSuperType(), n2.getSuperType())) {
            return false;
        }
        if (!this.nodesEquals(n.getAnnotations(), n2.getAnnotations())) {
            return false;
        }
        if (!this.nodeEquals(n.getComment(), n2.getComment())) {
            return false;
        }
        return true;
    }

    @Override
    public Boolean visit(UnknownType n, Visitable arg) {
        UnknownType n2 = (UnknownType)arg;
        if (!this.nodesEquals(n.getAnnotations(), n2.getAnnotations())) {
            return false;
        }
        if (!this.nodeEquals(n.getComment(), n2.getComment())) {
            return false;
        }
        return true;
    }

    @Override
    public Boolean visit(ArrayAccessExpr n, Visitable arg) {
        ArrayAccessExpr n2 = (ArrayAccessExpr)arg;
        if (!this.nodeEquals(n.getIndex(), n2.getIndex())) {
            return false;
        }
        if (!this.nodeEquals(n.getName(), n2.getName())) {
            return false;
        }
        if (!this.nodeEquals(n.getComment(), n2.getComment())) {
            return false;
        }
        return true;
    }

    @Override
    public Boolean visit(ArrayCreationExpr n, Visitable arg) {
        ArrayCreationExpr n2 = (ArrayCreationExpr)arg;
        if (!this.nodeEquals(n.getElementType(), n2.getElementType())) {
            return false;
        }
        if (!this.nodeEquals(n.getInitializer(), n2.getInitializer())) {
            return false;
        }
        if (!this.nodesEquals(n.getLevels(), n2.getLevels())) {
            return false;
        }
        if (!this.nodeEquals(n.getComment(), n2.getComment())) {
            return false;
        }
        return true;
    }

    @Override
    public Boolean visit(ArrayInitializerExpr n, Visitable arg) {
        ArrayInitializerExpr n2 = (ArrayInitializerExpr)arg;
        if (!this.nodesEquals(n.getValues(), n2.getValues())) {
            return false;
        }
        if (!this.nodeEquals(n.getComment(), n2.getComment())) {
            return false;
        }
        return true;
    }

    @Override
    public Boolean visit(AssignExpr n, Visitable arg) {
        AssignExpr n2 = (AssignExpr)arg;
        if (!this.objEquals(n.getOperator(), n2.getOperator())) {
            return false;
        }
        if (!this.nodeEquals(n.getTarget(), n2.getTarget())) {
            return false;
        }
        if (!this.nodeEquals(n.getValue(), n2.getValue())) {
            return false;
        }
        if (!this.nodeEquals(n.getComment(), n2.getComment())) {
            return false;
        }
        return true;
    }

    @Override
    public Boolean visit(BinaryExpr n, Visitable arg) {
        BinaryExpr n2 = (BinaryExpr)arg;
        if (!this.nodeEquals(n.getLeft(), n2.getLeft())) {
            return false;
        }
        if (!this.objEquals(n.getOperator(), n2.getOperator())) {
            return false;
        }
        if (!this.nodeEquals(n.getRight(), n2.getRight())) {
            return false;
        }
        if (!this.nodeEquals(n.getComment(), n2.getComment())) {
            return false;
        }
        return true;
    }

    @Override
    public Boolean visit(CastExpr n, Visitable arg) {
        CastExpr n2 = (CastExpr)arg;
        if (!this.nodeEquals(n.getExpression(), n2.getExpression())) {
            return false;
        }
        if (!this.nodeEquals(n.getType(), n2.getType())) {
            return false;
        }
        if (!this.nodeEquals(n.getComment(), n2.getComment())) {
            return false;
        }
        return true;
    }

    @Override
    public Boolean visit(ClassExpr n, Visitable arg) {
        ClassExpr n2 = (ClassExpr)arg;
        if (!this.nodeEquals(n.getType(), n2.getType())) {
            return false;
        }
        if (!this.nodeEquals(n.getComment(), n2.getComment())) {
            return false;
        }
        return true;
    }

    @Override
    public Boolean visit(ConditionalExpr n, Visitable arg) {
        ConditionalExpr n2 = (ConditionalExpr)arg;
        if (!this.nodeEquals(n.getCondition(), n2.getCondition())) {
            return false;
        }
        if (!this.nodeEquals(n.getElseExpr(), n2.getElseExpr())) {
            return false;
        }
        if (!this.nodeEquals(n.getThenExpr(), n2.getThenExpr())) {
            return false;
        }
        if (!this.nodeEquals(n.getComment(), n2.getComment())) {
            return false;
        }
        return true;
    }

    @Override
    public Boolean visit(EnclosedExpr n, Visitable arg) {
        EnclosedExpr n2 = (EnclosedExpr)arg;
        if (!this.nodeEquals(n.getInner(), n2.getInner())) {
            return false;
        }
        if (!this.nodeEquals(n.getComment(), n2.getComment())) {
            return false;
        }
        return true;
    }

    @Override
    public Boolean visit(FieldAccessExpr n, Visitable arg) {
        FieldAccessExpr n2 = (FieldAccessExpr)arg;
        if (!this.nodeEquals(n.getName(), n2.getName())) {
            return false;
        }
        if (!this.nodeEquals(n.getScope(), n2.getScope())) {
            return false;
        }
        if (!this.nodesEquals(n.getTypeArguments(), n2.getTypeArguments())) {
            return false;
        }
        if (!this.nodeEquals(n.getComment(), n2.getComment())) {
            return false;
        }
        return true;
    }

    @Override
    public Boolean visit(InstanceOfExpr n, Visitable arg) {
        InstanceOfExpr n2 = (InstanceOfExpr)arg;
        if (!this.nodeEquals(n.getExpression(), n2.getExpression())) {
            return false;
        }
        if (!this.nodeEquals(n.getPattern(), n2.getPattern())) {
            return false;
        }
        if (!this.nodeEquals(n.getType(), n2.getType())) {
            return false;
        }
        if (!this.nodeEquals(n.getComment(), n2.getComment())) {
            return false;
        }
        return true;
    }

    @Override
    public Boolean visit(StringLiteralExpr n, Visitable arg) {
        StringLiteralExpr n2 = (StringLiteralExpr)arg;
        if (!this.objEquals(n.getValue(), n2.getValue())) {
            return false;
        }
        if (!this.nodeEquals(n.getComment(), n2.getComment())) {
            return false;
        }
        return true;
    }

    @Override
    public Boolean visit(IntegerLiteralExpr n, Visitable arg) {
        IntegerLiteralExpr n2 = (IntegerLiteralExpr)arg;
        if (!this.objEquals(n.getValue(), n2.getValue())) {
            return false;
        }
        if (!this.nodeEquals(n.getComment(), n2.getComment())) {
            return false;
        }
        return true;
    }

    @Override
    public Boolean visit(LongLiteralExpr n, Visitable arg) {
        LongLiteralExpr n2 = (LongLiteralExpr)arg;
        if (!this.objEquals(n.getValue(), n2.getValue())) {
            return false;
        }
        if (!this.nodeEquals(n.getComment(), n2.getComment())) {
            return false;
        }
        return true;
    }

    @Override
    public Boolean visit(CharLiteralExpr n, Visitable arg) {
        CharLiteralExpr n2 = (CharLiteralExpr)arg;
        if (!this.objEquals(n.getValue(), n2.getValue())) {
            return false;
        }
        if (!this.nodeEquals(n.getComment(), n2.getComment())) {
            return false;
        }
        return true;
    }

    @Override
    public Boolean visit(DoubleLiteralExpr n, Visitable arg) {
        DoubleLiteralExpr n2 = (DoubleLiteralExpr)arg;
        if (!this.objEquals(n.getValue(), n2.getValue())) {
            return false;
        }
        if (!this.nodeEquals(n.getComment(), n2.getComment())) {
            return false;
        }
        return true;
    }

    @Override
    public Boolean visit(BooleanLiteralExpr n, Visitable arg) {
        BooleanLiteralExpr n2 = (BooleanLiteralExpr)arg;
        if (!this.objEquals(n.isValue(), n2.isValue())) {
            return false;
        }
        if (!this.nodeEquals(n.getComment(), n2.getComment())) {
            return false;
        }
        return true;
    }

    @Override
    public Boolean visit(NullLiteralExpr n, Visitable arg) {
        NullLiteralExpr n2 = (NullLiteralExpr)arg;
        if (!this.nodeEquals(n.getComment(), n2.getComment())) {
            return false;
        }
        return true;
    }

    @Override
    public Boolean visit(MethodCallExpr n, Visitable arg) {
        MethodCallExpr n2 = (MethodCallExpr)arg;
        if (!this.nodesEquals(n.getArguments(), n2.getArguments())) {
            return false;
        }
        if (!this.nodeEquals(n.getName(), n2.getName())) {
            return false;
        }
        if (!this.nodeEquals(n.getScope(), n2.getScope())) {
            return false;
        }
        if (!this.nodesEquals(n.getTypeArguments(), n2.getTypeArguments())) {
            return false;
        }
        if (!this.nodeEquals(n.getComment(), n2.getComment())) {
            return false;
        }
        return true;
    }

    @Override
    public Boolean visit(NameExpr n, Visitable arg) {
        NameExpr n2 = (NameExpr)arg;
        if (!this.nodeEquals(n.getName(), n2.getName())) {
            return false;
        }
        if (!this.nodeEquals(n.getComment(), n2.getComment())) {
            return false;
        }
        return true;
    }

    @Override
    public Boolean visit(ObjectCreationExpr n, Visitable arg) {
        ObjectCreationExpr n2 = (ObjectCreationExpr)arg;
        if (!this.nodesEquals(n.getAnonymousClassBody(), n2.getAnonymousClassBody())) {
            return false;
        }
        if (!this.nodesEquals(n.getArguments(), n2.getArguments())) {
            return false;
        }
        if (!this.nodeEquals(n.getScope(), n2.getScope())) {
            return false;
        }
        if (!this.nodeEquals(n.getType(), n2.getType())) {
            return false;
        }
        if (!this.nodesEquals(n.getTypeArguments(), n2.getTypeArguments())) {
            return false;
        }
        if (!this.nodeEquals(n.getComment(), n2.getComment())) {
            return false;
        }
        return true;
    }

    @Override
    public Boolean visit(Name n, Visitable arg) {
        Name n2 = (Name)arg;
        if (!this.objEquals(n.getIdentifier(), n2.getIdentifier())) {
            return false;
        }
        if (!this.nodeEquals(n.getQualifier(), n2.getQualifier())) {
            return false;
        }
        if (!this.nodeEquals(n.getComment(), n2.getComment())) {
            return false;
        }
        return true;
    }

    @Override
    public Boolean visit(SimpleName n, Visitable arg) {
        SimpleName n2 = (SimpleName)arg;
        if (!this.objEquals(n.getIdentifier(), n2.getIdentifier())) {
            return false;
        }
        if (!this.nodeEquals(n.getComment(), n2.getComment())) {
            return false;
        }
        return true;
    }

    @Override
    public Boolean visit(ThisExpr n, Visitable arg) {
        ThisExpr n2 = (ThisExpr)arg;
        if (!this.nodeEquals(n.getTypeName(), n2.getTypeName())) {
            return false;
        }
        if (!this.nodeEquals(n.getComment(), n2.getComment())) {
            return false;
        }
        return true;
    }

    @Override
    public Boolean visit(SuperExpr n, Visitable arg) {
        SuperExpr n2 = (SuperExpr)arg;
        if (!this.nodeEquals(n.getTypeName(), n2.getTypeName())) {
            return false;
        }
        if (!this.nodeEquals(n.getComment(), n2.getComment())) {
            return false;
        }
        return true;
    }

    @Override
    public Boolean visit(UnaryExpr n, Visitable arg) {
        UnaryExpr n2 = (UnaryExpr)arg;
        if (!this.nodeEquals(n.getExpression(), n2.getExpression())) {
            return false;
        }
        if (!this.objEquals(n.getOperator(), n2.getOperator())) {
            return false;
        }
        if (!this.nodeEquals(n.getComment(), n2.getComment())) {
            return false;
        }
        return true;
    }

    @Override
    public Boolean visit(VariableDeclarationExpr n, Visitable arg) {
        VariableDeclarationExpr n2 = (VariableDeclarationExpr)arg;
        if (!this.nodesEquals(n.getAnnotations(), n2.getAnnotations())) {
            return false;
        }
        if (!this.nodesEquals(n.getModifiers(), n2.getModifiers())) {
            return false;
        }
        if (!this.nodesEquals(n.getVariables(), n2.getVariables())) {
            return false;
        }
        if (!this.nodeEquals(n.getComment(), n2.getComment())) {
            return false;
        }
        return true;
    }

    @Override
    public Boolean visit(MarkerAnnotationExpr n, Visitable arg) {
        MarkerAnnotationExpr n2 = (MarkerAnnotationExpr)arg;
        if (!this.nodeEquals(n.getName(), n2.getName())) {
            return false;
        }
        if (!this.nodeEquals(n.getComment(), n2.getComment())) {
            return false;
        }
        return true;
    }

    @Override
    public Boolean visit(SingleMemberAnnotationExpr n, Visitable arg) {
        SingleMemberAnnotationExpr n2 = (SingleMemberAnnotationExpr)arg;
        if (!this.nodeEquals(n.getMemberValue(), n2.getMemberValue())) {
            return false;
        }
        if (!this.nodeEquals(n.getName(), n2.getName())) {
            return false;
        }
        if (!this.nodeEquals(n.getComment(), n2.getComment())) {
            return false;
        }
        return true;
    }

    @Override
    public Boolean visit(NormalAnnotationExpr n, Visitable arg) {
        NormalAnnotationExpr n2 = (NormalAnnotationExpr)arg;
        if (!this.nodesEquals(n.getPairs(), n2.getPairs())) {
            return false;
        }
        if (!this.nodeEquals(n.getName(), n2.getName())) {
            return false;
        }
        if (!this.nodeEquals(n.getComment(), n2.getComment())) {
            return false;
        }
        return true;
    }

    @Override
    public Boolean visit(MemberValuePair n, Visitable arg) {
        MemberValuePair n2 = (MemberValuePair)arg;
        if (!this.nodeEquals(n.getName(), n2.getName())) {
            return false;
        }
        if (!this.nodeEquals(n.getValue(), n2.getValue())) {
            return false;
        }
        if (!this.nodeEquals(n.getComment(), n2.getComment())) {
            return false;
        }
        return true;
    }

    @Override
    public Boolean visit(ExplicitConstructorInvocationStmt n, Visitable arg) {
        ExplicitConstructorInvocationStmt n2 = (ExplicitConstructorInvocationStmt)arg;
        if (!this.nodesEquals(n.getArguments(), n2.getArguments())) {
            return false;
        }
        if (!this.nodeEquals(n.getExpression(), n2.getExpression())) {
            return false;
        }
        if (!this.objEquals(n.isThis(), n2.isThis())) {
            return false;
        }
        if (!this.nodesEquals(n.getTypeArguments(), n2.getTypeArguments())) {
            return false;
        }
        if (!this.nodeEquals(n.getComment(), n2.getComment())) {
            return false;
        }
        return true;
    }

    @Override
    public Boolean visit(LocalClassDeclarationStmt n, Visitable arg) {
        LocalClassDeclarationStmt n2 = (LocalClassDeclarationStmt)arg;
        if (!this.nodeEquals(n.getClassDeclaration(), n2.getClassDeclaration())) {
            return false;
        }
        if (!this.nodeEquals(n.getComment(), n2.getComment())) {
            return false;
        }
        return true;
    }

    @Override
    public Boolean visit(AssertStmt n, Visitable arg) {
        AssertStmt n2 = (AssertStmt)arg;
        if (!this.nodeEquals(n.getCheck(), n2.getCheck())) {
            return false;
        }
        if (!this.nodeEquals(n.getMessage(), n2.getMessage())) {
            return false;
        }
        if (!this.nodeEquals(n.getComment(), n2.getComment())) {
            return false;
        }
        return true;
    }

    @Override
    public Boolean visit(BlockStmt n, Visitable arg) {
        BlockStmt n2 = (BlockStmt)arg;
        if (!this.nodesEquals(n.getStatements(), n2.getStatements())) {
            return false;
        }
        if (!this.nodeEquals(n.getComment(), n2.getComment())) {
            return false;
        }
        return true;
    }

    @Override
    public Boolean visit(LabeledStmt n, Visitable arg) {
        LabeledStmt n2 = (LabeledStmt)arg;
        if (!this.nodeEquals(n.getLabel(), n2.getLabel())) {
            return false;
        }
        if (!this.nodeEquals(n.getStatement(), n2.getStatement())) {
            return false;
        }
        if (!this.nodeEquals(n.getComment(), n2.getComment())) {
            return false;
        }
        return true;
    }

    @Override
    public Boolean visit(EmptyStmt n, Visitable arg) {
        EmptyStmt n2 = (EmptyStmt)arg;
        if (!this.nodeEquals(n.getComment(), n2.getComment())) {
            return false;
        }
        return true;
    }

    @Override
    public Boolean visit(ExpressionStmt n, Visitable arg) {
        ExpressionStmt n2 = (ExpressionStmt)arg;
        if (!this.nodeEquals(n.getExpression(), n2.getExpression())) {
            return false;
        }
        if (!this.nodeEquals(n.getComment(), n2.getComment())) {
            return false;
        }
        return true;
    }

    @Override
    public Boolean visit(SwitchStmt n, Visitable arg) {
        SwitchStmt n2 = (SwitchStmt)arg;
        if (!this.nodesEquals(n.getEntries(), n2.getEntries())) {
            return false;
        }
        if (!this.nodeEquals(n.getSelector(), n2.getSelector())) {
            return false;
        }
        if (!this.nodeEquals(n.getComment(), n2.getComment())) {
            return false;
        }
        return true;
    }

    @Override
    public Boolean visit(SwitchEntry n, Visitable arg) {
        SwitchEntry n2 = (SwitchEntry)arg;
        if (!this.nodesEquals(n.getLabels(), n2.getLabels())) {
            return false;
        }
        if (!this.nodesEquals(n.getStatements(), n2.getStatements())) {
            return false;
        }
        if (!this.objEquals((Object)n.getType(), (Object)n2.getType())) {
            return false;
        }
        if (!this.nodeEquals(n.getComment(), n2.getComment())) {
            return false;
        }
        return true;
    }

    @Override
    public Boolean visit(BreakStmt n, Visitable arg) {
        BreakStmt n2 = (BreakStmt)arg;
        if (!this.nodeEquals(n.getLabel(), n2.getLabel())) {
            return false;
        }
        if (!this.nodeEquals(n.getComment(), n2.getComment())) {
            return false;
        }
        return true;
    }

    @Override
    public Boolean visit(ReturnStmt n, Visitable arg) {
        ReturnStmt n2 = (ReturnStmt)arg;
        if (!this.nodeEquals(n.getExpression(), n2.getExpression())) {
            return false;
        }
        if (!this.nodeEquals(n.getComment(), n2.getComment())) {
            return false;
        }
        return true;
    }

    @Override
    public Boolean visit(IfStmt n, Visitable arg) {
        IfStmt n2 = (IfStmt)arg;
        if (!this.nodeEquals(n.getCondition(), n2.getCondition())) {
            return false;
        }
        if (!this.nodeEquals(n.getElseStmt(), n2.getElseStmt())) {
            return false;
        }
        if (!this.nodeEquals(n.getThenStmt(), n2.getThenStmt())) {
            return false;
        }
        if (!this.nodeEquals(n.getComment(), n2.getComment())) {
            return false;
        }
        return true;
    }

    @Override
    public Boolean visit(WhileStmt n, Visitable arg) {
        WhileStmt n2 = (WhileStmt)arg;
        if (!this.nodeEquals(n.getBody(), n2.getBody())) {
            return false;
        }
        if (!this.nodeEquals(n.getCondition(), n2.getCondition())) {
            return false;
        }
        if (!this.nodeEquals(n.getComment(), n2.getComment())) {
            return false;
        }
        return true;
    }

    @Override
    public Boolean visit(ContinueStmt n, Visitable arg) {
        ContinueStmt n2 = (ContinueStmt)arg;
        if (!this.nodeEquals(n.getLabel(), n2.getLabel())) {
            return false;
        }
        if (!this.nodeEquals(n.getComment(), n2.getComment())) {
            return false;
        }
        return true;
    }

    @Override
    public Boolean visit(DoStmt n, Visitable arg) {
        DoStmt n2 = (DoStmt)arg;
        if (!this.nodeEquals(n.getBody(), n2.getBody())) {
            return false;
        }
        if (!this.nodeEquals(n.getCondition(), n2.getCondition())) {
            return false;
        }
        if (!this.nodeEquals(n.getComment(), n2.getComment())) {
            return false;
        }
        return true;
    }

    @Override
    public Boolean visit(ForEachStmt n, Visitable arg) {
        ForEachStmt n2 = (ForEachStmt)arg;
        if (!this.nodeEquals(n.getBody(), n2.getBody())) {
            return false;
        }
        if (!this.nodeEquals(n.getIterable(), n2.getIterable())) {
            return false;
        }
        if (!this.nodeEquals(n.getVariable(), n2.getVariable())) {
            return false;
        }
        if (!this.nodeEquals(n.getComment(), n2.getComment())) {
            return false;
        }
        return true;
    }

    @Override
    public Boolean visit(ForStmt n, Visitable arg) {
        ForStmt n2 = (ForStmt)arg;
        if (!this.nodeEquals(n.getBody(), n2.getBody())) {
            return false;
        }
        if (!this.nodeEquals(n.getCompare(), n2.getCompare())) {
            return false;
        }
        if (!this.nodesEquals(n.getInitialization(), n2.getInitialization())) {
            return false;
        }
        if (!this.nodesEquals(n.getUpdate(), n2.getUpdate())) {
            return false;
        }
        if (!this.nodeEquals(n.getComment(), n2.getComment())) {
            return false;
        }
        return true;
    }

    @Override
    public Boolean visit(ThrowStmt n, Visitable arg) {
        ThrowStmt n2 = (ThrowStmt)arg;
        if (!this.nodeEquals(n.getExpression(), n2.getExpression())) {
            return false;
        }
        if (!this.nodeEquals(n.getComment(), n2.getComment())) {
            return false;
        }
        return true;
    }

    @Override
    public Boolean visit(SynchronizedStmt n, Visitable arg) {
        SynchronizedStmt n2 = (SynchronizedStmt)arg;
        if (!this.nodeEquals(n.getBody(), n2.getBody())) {
            return false;
        }
        if (!this.nodeEquals(n.getExpression(), n2.getExpression())) {
            return false;
        }
        if (!this.nodeEquals(n.getComment(), n2.getComment())) {
            return false;
        }
        return true;
    }

    @Override
    public Boolean visit(TryStmt n, Visitable arg) {
        TryStmt n2 = (TryStmt)arg;
        if (!this.nodesEquals(n.getCatchClauses(), n2.getCatchClauses())) {
            return false;
        }
        if (!this.nodeEquals(n.getFinallyBlock(), n2.getFinallyBlock())) {
            return false;
        }
        if (!this.nodesEquals(n.getResources(), n2.getResources())) {
            return false;
        }
        if (!this.nodeEquals(n.getTryBlock(), n2.getTryBlock())) {
            return false;
        }
        if (!this.nodeEquals(n.getComment(), n2.getComment())) {
            return false;
        }
        return true;
    }

    @Override
    public Boolean visit(CatchClause n, Visitable arg) {
        CatchClause n2 = (CatchClause)arg;
        if (!this.nodeEquals(n.getBody(), n2.getBody())) {
            return false;
        }
        if (!this.nodeEquals(n.getParameter(), n2.getParameter())) {
            return false;
        }
        if (!this.nodeEquals(n.getComment(), n2.getComment())) {
            return false;
        }
        return true;
    }

    @Override
    public Boolean visit(LambdaExpr n, Visitable arg) {
        LambdaExpr n2 = (LambdaExpr)arg;
        if (!this.nodeEquals(n.getBody(), n2.getBody())) {
            return false;
        }
        if (!this.objEquals(n.isEnclosingParameters(), n2.isEnclosingParameters())) {
            return false;
        }
        if (!this.nodesEquals(n.getParameters(), n2.getParameters())) {
            return false;
        }
        if (!this.nodeEquals(n.getComment(), n2.getComment())) {
            return false;
        }
        return true;
    }

    @Override
    public Boolean visit(MethodReferenceExpr n, Visitable arg) {
        MethodReferenceExpr n2 = (MethodReferenceExpr)arg;
        if (!this.objEquals(n.getIdentifier(), n2.getIdentifier())) {
            return false;
        }
        if (!this.nodeEquals(n.getScope(), n2.getScope())) {
            return false;
        }
        if (!this.nodesEquals(n.getTypeArguments(), n2.getTypeArguments())) {
            return false;
        }
        if (!this.nodeEquals(n.getComment(), n2.getComment())) {
            return false;
        }
        return true;
    }

    @Override
    public Boolean visit(TypeExpr n, Visitable arg) {
        TypeExpr n2 = (TypeExpr)arg;
        if (!this.nodeEquals(n.getType(), n2.getType())) {
            return false;
        }
        if (!this.nodeEquals(n.getComment(), n2.getComment())) {
            return false;
        }
        return true;
    }

    @Override
    public Boolean visit(ImportDeclaration n, Visitable arg) {
        ImportDeclaration n2 = (ImportDeclaration)arg;
        if (!this.objEquals(n.isAsterisk(), n2.isAsterisk())) {
            return false;
        }
        if (!this.objEquals(n.isStatic(), n2.isStatic())) {
            return false;
        }
        if (!this.nodeEquals(n.getName(), n2.getName())) {
            return false;
        }
        if (!this.nodeEquals(n.getComment(), n2.getComment())) {
            return false;
        }
        return true;
    }

    @Override
    public Boolean visit(NodeList n, Visitable arg) {
        return this.nodesEquals(n, (NodeList)arg);
    }

    @Override
    public Boolean visit(ModuleDeclaration n, Visitable arg) {
        ModuleDeclaration n2 = (ModuleDeclaration)arg;
        if (!this.nodesEquals(n.getAnnotations(), n2.getAnnotations())) {
            return false;
        }
        if (!this.nodesEquals(n.getDirectives(), n2.getDirectives())) {
            return false;
        }
        if (!this.objEquals(n.isOpen(), n2.isOpen())) {
            return false;
        }
        if (!this.nodeEquals(n.getName(), n2.getName())) {
            return false;
        }
        if (!this.nodeEquals(n.getComment(), n2.getComment())) {
            return false;
        }
        return true;
    }

    @Override
    public Boolean visit(ModuleRequiresDirective n, Visitable arg) {
        ModuleRequiresDirective n2 = (ModuleRequiresDirective)arg;
        if (!this.nodesEquals(n.getModifiers(), n2.getModifiers())) {
            return false;
        }
        if (!this.nodeEquals(n.getName(), n2.getName())) {
            return false;
        }
        if (!this.nodeEquals(n.getComment(), n2.getComment())) {
            return false;
        }
        return true;
    }

    @Override
    public Boolean visit(ModuleExportsDirective n, Visitable arg) {
        ModuleExportsDirective n2 = (ModuleExportsDirective)arg;
        if (!this.nodesEquals(n.getModuleNames(), n2.getModuleNames())) {
            return false;
        }
        if (!this.nodeEquals(n.getName(), n2.getName())) {
            return false;
        }
        if (!this.nodeEquals(n.getComment(), n2.getComment())) {
            return false;
        }
        return true;
    }

    @Override
    public Boolean visit(ModuleProvidesDirective n, Visitable arg) {
        ModuleProvidesDirective n2 = (ModuleProvidesDirective)arg;
        if (!this.nodeEquals(n.getName(), n2.getName())) {
            return false;
        }
        if (!this.nodesEquals(n.getWith(), n2.getWith())) {
            return false;
        }
        if (!this.nodeEquals(n.getComment(), n2.getComment())) {
            return false;
        }
        return true;
    }

    @Override
    public Boolean visit(ModuleUsesDirective n, Visitable arg) {
        ModuleUsesDirective n2 = (ModuleUsesDirective)arg;
        if (!this.nodeEquals(n.getName(), n2.getName())) {
            return false;
        }
        if (!this.nodeEquals(n.getComment(), n2.getComment())) {
            return false;
        }
        return true;
    }

    @Override
    public Boolean visit(ModuleOpensDirective n, Visitable arg) {
        ModuleOpensDirective n2 = (ModuleOpensDirective)arg;
        if (!this.nodesEquals(n.getModuleNames(), n2.getModuleNames())) {
            return false;
        }
        if (!this.nodeEquals(n.getName(), n2.getName())) {
            return false;
        }
        if (!this.nodeEquals(n.getComment(), n2.getComment())) {
            return false;
        }
        return true;
    }

    @Override
    public Boolean visit(UnparsableStmt n, Visitable arg) {
        UnparsableStmt n2 = (UnparsableStmt)arg;
        if (!this.nodeEquals(n.getComment(), n2.getComment())) {
            return false;
        }
        return true;
    }

    @Override
    public Boolean visit(ReceiverParameter n, Visitable arg) {
        ReceiverParameter n2 = (ReceiverParameter)arg;
        if (!this.nodesEquals(n.getAnnotations(), n2.getAnnotations())) {
            return false;
        }
        if (!this.nodeEquals(n.getName(), n2.getName())) {
            return false;
        }
        if (!this.nodeEquals(n.getType(), n2.getType())) {
            return false;
        }
        if (!this.nodeEquals(n.getComment(), n2.getComment())) {
            return false;
        }
        return true;
    }

    @Override
    public Boolean visit(VarType n, Visitable arg) {
        VarType n2 = (VarType)arg;
        if (!this.nodesEquals(n.getAnnotations(), n2.getAnnotations())) {
            return false;
        }
        if (!this.nodeEquals(n.getComment(), n2.getComment())) {
            return false;
        }
        return true;
    }

    @Override
    public Boolean visit(Modifier n, Visitable arg) {
        Modifier n2 = (Modifier)arg;
        if (!this.objEquals((Object)n.getKeyword(), (Object)n2.getKeyword())) {
            return false;
        }
        if (!this.nodeEquals(n.getComment(), n2.getComment())) {
            return false;
        }
        return true;
    }

    @Override
    public Boolean visit(SwitchExpr n, Visitable arg) {
        SwitchExpr n2 = (SwitchExpr)arg;
        if (!this.nodesEquals(n.getEntries(), n2.getEntries())) {
            return false;
        }
        if (!this.nodeEquals(n.getSelector(), n2.getSelector())) {
            return false;
        }
        if (!this.nodeEquals(n.getComment(), n2.getComment())) {
            return false;
        }
        return true;
    }

    @Override
    public Boolean visit(YieldStmt n, Visitable arg) {
        YieldStmt n2 = (YieldStmt)arg;
        if (!this.nodeEquals(n.getExpression(), n2.getExpression())) {
            return false;
        }
        if (!this.nodeEquals(n.getComment(), n2.getComment())) {
            return false;
        }
        return true;
    }

    @Override
    public Boolean visit(TextBlockLiteralExpr n, Visitable arg) {
        TextBlockLiteralExpr n2 = (TextBlockLiteralExpr)arg;
        if (!this.objEquals(n.getValue(), n2.getValue())) {
            return false;
        }
        if (!this.nodeEquals(n.getComment(), n2.getComment())) {
            return false;
        }
        return true;
    }

    @Override
    public Boolean visit(PatternExpr n, Visitable arg) {
        PatternExpr n2 = (PatternExpr)arg;
        if (!this.nodeEquals(n.getName(), n2.getName())) {
            return false;
        }
        if (!this.nodeEquals(n.getType(), n2.getType())) {
            return false;
        }
        if (!this.nodeEquals(n.getComment(), n2.getComment())) {
            return false;
        }
        return true;
    }
}

