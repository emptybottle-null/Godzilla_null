/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.ast.visitor;

import com.github.javaparser.TokenRange;
import com.github.javaparser.ast.ArrayCreationLevel;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.DataKey;
import com.github.javaparser.ast.ImportDeclaration;
import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.PackageDeclaration;
import com.github.javaparser.ast.body.AnnotationDeclaration;
import com.github.javaparser.ast.body.AnnotationMemberDeclaration;
import com.github.javaparser.ast.body.BodyDeclaration;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.body.EnumConstantDeclaration;
import com.github.javaparser.ast.body.EnumDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.InitializerDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.body.ReceiverParameter;
import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.comments.BlockComment;
import com.github.javaparser.ast.comments.Comment;
import com.github.javaparser.ast.comments.JavadocComment;
import com.github.javaparser.ast.comments.LineComment;
import com.github.javaparser.ast.expr.AnnotationExpr;
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
import com.github.javaparser.ast.expr.Expression;
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
import com.github.javaparser.ast.modules.ModuleDirective;
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
import com.github.javaparser.ast.stmt.Statement;
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
import com.github.javaparser.ast.type.ReferenceType;
import com.github.javaparser.ast.type.Type;
import com.github.javaparser.ast.type.TypeParameter;
import com.github.javaparser.ast.type.UnionType;
import com.github.javaparser.ast.type.UnknownType;
import com.github.javaparser.ast.type.VarType;
import com.github.javaparser.ast.type.VoidType;
import com.github.javaparser.ast.type.WildcardType;
import com.github.javaparser.ast.visitor.GenericVisitor;
import com.github.javaparser.ast.visitor.Visitable;
import java.util.Optional;

public class CloneVisitor
implements GenericVisitor<Visitable, Object> {
    @Override
    public Visitable visit(CompilationUnit n, Object arg) {
        NodeList<ImportDeclaration> imports = this.cloneList(n.getImports(), arg);
        ModuleDeclaration module = (ModuleDeclaration)((Object)this.cloneNode((Node)((Object)n.getModule()), arg));
        PackageDeclaration packageDeclaration = (PackageDeclaration)((Object)this.cloneNode((Node)((Object)n.getPackageDeclaration()), arg));
        NodeList<TypeDeclaration<?>> types = this.cloneList(n.getTypes(), arg);
        Comment comment = (Comment)((Object)this.cloneNode((Node)((Object)n.getComment()), arg));
        CompilationUnit r = new CompilationUnit(n.getTokenRange().orElse(null), packageDeclaration, imports, types, module);
        n.getStorage().ifPresent(s -> r.setStorage(s.getPath(), s.getEncoding()));
        r.setComment(comment);
        n.getOrphanComments().stream().map(Comment::clone).forEach(r::addOrphanComment);
        this.copyData(n, r);
        return r;
    }

    @Override
    public Visitable visit(PackageDeclaration n, Object arg) {
        NodeList<AnnotationExpr> annotations = this.cloneList(n.getAnnotations(), arg);
        Name name = this.cloneNode(n.getName(), arg);
        Comment comment = (Comment)((Object)this.cloneNode((Node)((Object)n.getComment()), arg));
        PackageDeclaration r = new PackageDeclaration(n.getTokenRange().orElse(null), annotations, name);
        r.setComment(comment);
        n.getOrphanComments().stream().map(Comment::clone).forEach(r::addOrphanComment);
        this.copyData(n, r);
        return r;
    }

    @Override
    public Visitable visit(TypeParameter n, Object arg) {
        SimpleName name = this.cloneNode(n.getName(), arg);
        NodeList<ClassOrInterfaceType> typeBound = this.cloneList(n.getTypeBound(), arg);
        NodeList<AnnotationExpr> annotations = this.cloneList(n.getAnnotations(), arg);
        Comment comment = (Comment)((Object)this.cloneNode((Node)((Object)n.getComment()), arg));
        TypeParameter r = new TypeParameter(n.getTokenRange().orElse(null), name, typeBound, annotations);
        r.setComment(comment);
        n.getOrphanComments().stream().map(Comment::clone).forEach(r::addOrphanComment);
        this.copyData(n, r);
        return r;
    }

    @Override
    public Visitable visit(LineComment n, Object arg) {
        Comment comment = (Comment)((Object)this.cloneNode((Node)((Object)n.getComment()), arg));
        LineComment r = new LineComment(n.getTokenRange().orElse(null), n.getContent());
        r.setComment(comment);
        n.getOrphanComments().stream().map(Comment::clone).forEach(r::addOrphanComment);
        this.copyData(n, r);
        return r;
    }

    @Override
    public Visitable visit(BlockComment n, Object arg) {
        Comment comment = (Comment)((Object)this.cloneNode((Node)((Object)n.getComment()), arg));
        BlockComment r = new BlockComment(n.getTokenRange().orElse(null), n.getContent());
        r.setComment(comment);
        n.getOrphanComments().stream().map(Comment::clone).forEach(r::addOrphanComment);
        this.copyData(n, r);
        return r;
    }

    @Override
    public Visitable visit(ClassOrInterfaceDeclaration n, Object arg) {
        NodeList<ClassOrInterfaceType> extendedTypes = this.cloneList(n.getExtendedTypes(), arg);
        NodeList<ClassOrInterfaceType> implementedTypes = this.cloneList(n.getImplementedTypes(), arg);
        NodeList<TypeParameter> typeParameters = this.cloneList(n.getTypeParameters(), arg);
        NodeList<BodyDeclaration<?>> members = this.cloneList(n.getMembers(), arg);
        NodeList<Modifier> modifiers = this.cloneList(n.getModifiers(), arg);
        SimpleName name = this.cloneNode(n.getName(), arg);
        NodeList<AnnotationExpr> annotations = this.cloneList(n.getAnnotations(), arg);
        Comment comment = (Comment)((Object)this.cloneNode((Node)((Object)n.getComment()), arg));
        ClassOrInterfaceDeclaration r = new ClassOrInterfaceDeclaration(n.getTokenRange().orElse(null), modifiers, annotations, n.isInterface(), name, typeParameters, extendedTypes, implementedTypes, members);
        r.setComment(comment);
        n.getOrphanComments().stream().map(Comment::clone).forEach(r::addOrphanComment);
        this.copyData(n, r);
        return r;
    }

    @Override
    public Visitable visit(EnumDeclaration n, Object arg) {
        NodeList<EnumConstantDeclaration> entries = this.cloneList(n.getEntries(), arg);
        NodeList<ClassOrInterfaceType> implementedTypes = this.cloneList(n.getImplementedTypes(), arg);
        NodeList<BodyDeclaration<?>> members = this.cloneList(n.getMembers(), arg);
        NodeList<Modifier> modifiers = this.cloneList(n.getModifiers(), arg);
        SimpleName name = this.cloneNode(n.getName(), arg);
        NodeList<AnnotationExpr> annotations = this.cloneList(n.getAnnotations(), arg);
        Comment comment = (Comment)((Object)this.cloneNode((Node)((Object)n.getComment()), arg));
        EnumDeclaration r = new EnumDeclaration(n.getTokenRange().orElse(null), modifiers, annotations, name, implementedTypes, entries, members);
        r.setComment(comment);
        n.getOrphanComments().stream().map(Comment::clone).forEach(r::addOrphanComment);
        this.copyData(n, r);
        return r;
    }

    @Override
    public Visitable visit(EnumConstantDeclaration n, Object arg) {
        NodeList<Expression> arguments = this.cloneList(n.getArguments(), arg);
        NodeList<BodyDeclaration<?>> classBody = this.cloneList(n.getClassBody(), arg);
        SimpleName name = this.cloneNode(n.getName(), arg);
        NodeList<AnnotationExpr> annotations = this.cloneList(n.getAnnotations(), arg);
        Comment comment = (Comment)((Object)this.cloneNode((Node)((Object)n.getComment()), arg));
        EnumConstantDeclaration r = new EnumConstantDeclaration(n.getTokenRange().orElse(null), annotations, name, arguments, classBody);
        r.setComment(comment);
        n.getOrphanComments().stream().map(Comment::clone).forEach(r::addOrphanComment);
        this.copyData(n, r);
        return r;
    }

    @Override
    public Visitable visit(AnnotationDeclaration n, Object arg) {
        NodeList<BodyDeclaration<?>> members = this.cloneList(n.getMembers(), arg);
        NodeList<Modifier> modifiers = this.cloneList(n.getModifiers(), arg);
        SimpleName name = this.cloneNode(n.getName(), arg);
        NodeList<AnnotationExpr> annotations = this.cloneList(n.getAnnotations(), arg);
        Comment comment = (Comment)((Object)this.cloneNode((Node)((Object)n.getComment()), arg));
        AnnotationDeclaration r = new AnnotationDeclaration(n.getTokenRange().orElse(null), modifiers, annotations, name, members);
        r.setComment(comment);
        n.getOrphanComments().stream().map(Comment::clone).forEach(r::addOrphanComment);
        this.copyData(n, r);
        return r;
    }

    @Override
    public Visitable visit(AnnotationMemberDeclaration n, Object arg) {
        Expression defaultValue = (Expression)((Object)this.cloneNode((Node)((Object)n.getDefaultValue()), arg));
        NodeList<Modifier> modifiers = this.cloneList(n.getModifiers(), arg);
        SimpleName name = this.cloneNode(n.getName(), arg);
        Type type = this.cloneNode(n.getType(), arg);
        NodeList<AnnotationExpr> annotations = this.cloneList(n.getAnnotations(), arg);
        Comment comment = (Comment)((Object)this.cloneNode((Node)((Object)n.getComment()), arg));
        AnnotationMemberDeclaration r = new AnnotationMemberDeclaration(n.getTokenRange().orElse(null), modifiers, annotations, type, name, defaultValue);
        r.setComment(comment);
        n.getOrphanComments().stream().map(Comment::clone).forEach(r::addOrphanComment);
        this.copyData(n, r);
        return r;
    }

    @Override
    public Visitable visit(FieldDeclaration n, Object arg) {
        NodeList<Modifier> modifiers = this.cloneList(n.getModifiers(), arg);
        NodeList<VariableDeclarator> variables = this.cloneList(n.getVariables(), arg);
        NodeList<AnnotationExpr> annotations = this.cloneList(n.getAnnotations(), arg);
        Comment comment = (Comment)((Object)this.cloneNode((Node)((Object)n.getComment()), arg));
        FieldDeclaration r = new FieldDeclaration(n.getTokenRange().orElse(null), modifiers, annotations, variables);
        r.setComment(comment);
        n.getOrphanComments().stream().map(Comment::clone).forEach(r::addOrphanComment);
        this.copyData(n, r);
        return r;
    }

    @Override
    public Visitable visit(VariableDeclarator n, Object arg) {
        Expression initializer = (Expression)((Object)this.cloneNode((Node)((Object)n.getInitializer()), arg));
        SimpleName name = this.cloneNode(n.getName(), arg);
        Type type = this.cloneNode(n.getType(), arg);
        Comment comment = (Comment)((Object)this.cloneNode((Node)((Object)n.getComment()), arg));
        VariableDeclarator r = new VariableDeclarator(n.getTokenRange().orElse(null), type, name, initializer);
        r.setComment(comment);
        n.getOrphanComments().stream().map(Comment::clone).forEach(r::addOrphanComment);
        this.copyData(n, r);
        return r;
    }

    @Override
    public Visitable visit(ConstructorDeclaration n, Object arg) {
        BlockStmt body = this.cloneNode(n.getBody(), arg);
        NodeList<Modifier> modifiers = this.cloneList(n.getModifiers(), arg);
        SimpleName name = this.cloneNode(n.getName(), arg);
        NodeList<Parameter> parameters = this.cloneList(n.getParameters(), arg);
        ReceiverParameter receiverParameter = (ReceiverParameter)((Object)this.cloneNode((Node)((Object)n.getReceiverParameter()), arg));
        NodeList<ReferenceType> thrownExceptions = this.cloneList(n.getThrownExceptions(), arg);
        NodeList<TypeParameter> typeParameters = this.cloneList(n.getTypeParameters(), arg);
        NodeList<AnnotationExpr> annotations = this.cloneList(n.getAnnotations(), arg);
        Comment comment = (Comment)((Object)this.cloneNode((Node)((Object)n.getComment()), arg));
        ConstructorDeclaration r = new ConstructorDeclaration(n.getTokenRange().orElse(null), modifiers, annotations, typeParameters, name, parameters, thrownExceptions, body, receiverParameter);
        r.setComment(comment);
        n.getOrphanComments().stream().map(Comment::clone).forEach(r::addOrphanComment);
        this.copyData(n, r);
        return r;
    }

    @Override
    public Visitable visit(MethodDeclaration n, Object arg) {
        BlockStmt body = (BlockStmt)((Object)this.cloneNode((Node)((Object)n.getBody()), arg));
        Type type = this.cloneNode(n.getType(), arg);
        NodeList<Modifier> modifiers = this.cloneList(n.getModifiers(), arg);
        SimpleName name = this.cloneNode(n.getName(), arg);
        NodeList<Parameter> parameters = this.cloneList(n.getParameters(), arg);
        ReceiverParameter receiverParameter = (ReceiverParameter)((Object)this.cloneNode((Node)((Object)n.getReceiverParameter()), arg));
        NodeList<ReferenceType> thrownExceptions = this.cloneList(n.getThrownExceptions(), arg);
        NodeList<TypeParameter> typeParameters = this.cloneList(n.getTypeParameters(), arg);
        NodeList<AnnotationExpr> annotations = this.cloneList(n.getAnnotations(), arg);
        Comment comment = (Comment)((Object)this.cloneNode((Node)((Object)n.getComment()), arg));
        MethodDeclaration r = new MethodDeclaration(n.getTokenRange().orElse(null), modifiers, annotations, typeParameters, type, name, parameters, thrownExceptions, body, receiverParameter);
        r.setComment(comment);
        n.getOrphanComments().stream().map(Comment::clone).forEach(r::addOrphanComment);
        this.copyData(n, r);
        return r;
    }

    @Override
    public Visitable visit(Parameter n, Object arg) {
        NodeList<AnnotationExpr> annotations = this.cloneList(n.getAnnotations(), arg);
        NodeList<Modifier> modifiers = this.cloneList(n.getModifiers(), arg);
        SimpleName name = this.cloneNode(n.getName(), arg);
        Type type = this.cloneNode(n.getType(), arg);
        NodeList<AnnotationExpr> varArgsAnnotations = this.cloneList(n.getVarArgsAnnotations(), arg);
        Comment comment = (Comment)((Object)this.cloneNode((Node)((Object)n.getComment()), arg));
        Parameter r = new Parameter(n.getTokenRange().orElse(null), modifiers, annotations, type, n.isVarArgs(), varArgsAnnotations, name);
        r.setComment(comment);
        n.getOrphanComments().stream().map(Comment::clone).forEach(r::addOrphanComment);
        this.copyData(n, r);
        return r;
    }

    @Override
    public Visitable visit(InitializerDeclaration n, Object arg) {
        BlockStmt body = this.cloneNode(n.getBody(), arg);
        NodeList<AnnotationExpr> annotations = this.cloneList(n.getAnnotations(), arg);
        Comment comment = (Comment)((Object)this.cloneNode((Node)((Object)n.getComment()), arg));
        InitializerDeclaration r = new InitializerDeclaration(n.getTokenRange().orElse(null), n.isStatic(), body);
        r.setComment(comment);
        n.getOrphanComments().stream().map(Comment::clone).forEach(r::addOrphanComment);
        this.copyData(n, r);
        return r;
    }

    @Override
    public Visitable visit(JavadocComment n, Object arg) {
        Comment comment = (Comment)((Object)this.cloneNode((Node)((Object)n.getComment()), arg));
        JavadocComment r = new JavadocComment(n.getTokenRange().orElse(null), n.getContent());
        r.setComment(comment);
        n.getOrphanComments().stream().map(Comment::clone).forEach(r::addOrphanComment);
        this.copyData(n, r);
        return r;
    }

    @Override
    public Visitable visit(ClassOrInterfaceType n, Object arg) {
        SimpleName name = this.cloneNode(n.getName(), arg);
        ClassOrInterfaceType scope = (ClassOrInterfaceType)((Object)this.cloneNode((Node)((Object)n.getScope()), arg));
        NodeList<Type> typeArguments = this.cloneList(n.getTypeArguments().orElse(null), arg);
        NodeList<AnnotationExpr> annotations = this.cloneList(n.getAnnotations(), arg);
        Comment comment = (Comment)((Object)this.cloneNode((Node)((Object)n.getComment()), arg));
        ClassOrInterfaceType r = new ClassOrInterfaceType(n.getTokenRange().orElse(null), scope, name, typeArguments, annotations);
        r.setComment(comment);
        n.getOrphanComments().stream().map(Comment::clone).forEach(r::addOrphanComment);
        this.copyData(n, r);
        return r;
    }

    @Override
    public Visitable visit(PrimitiveType n, Object arg) {
        NodeList<AnnotationExpr> annotations = this.cloneList(n.getAnnotations(), arg);
        Comment comment = (Comment)((Object)this.cloneNode((Node)((Object)n.getComment()), arg));
        PrimitiveType r = new PrimitiveType(n.getTokenRange().orElse(null), n.getType(), annotations);
        r.setComment(comment);
        n.getOrphanComments().stream().map(Comment::clone).forEach(r::addOrphanComment);
        this.copyData(n, r);
        return r;
    }

    @Override
    public Visitable visit(ArrayType n, Object arg) {
        Type componentType = this.cloneNode(n.getComponentType(), arg);
        NodeList<AnnotationExpr> annotations = this.cloneList(n.getAnnotations(), arg);
        Comment comment = (Comment)((Object)this.cloneNode((Node)((Object)n.getComment()), arg));
        ArrayType r = new ArrayType(n.getTokenRange().orElse(null), componentType, n.getOrigin(), annotations);
        r.setComment(comment);
        n.getOrphanComments().stream().map(Comment::clone).forEach(r::addOrphanComment);
        this.copyData(n, r);
        return r;
    }

    @Override
    public Visitable visit(ArrayCreationLevel n, Object arg) {
        NodeList<AnnotationExpr> annotations = this.cloneList(n.getAnnotations(), arg);
        Expression dimension = (Expression)((Object)this.cloneNode((Node)((Object)n.getDimension()), arg));
        Comment comment = (Comment)((Object)this.cloneNode((Node)((Object)n.getComment()), arg));
        ArrayCreationLevel r = new ArrayCreationLevel(n.getTokenRange().orElse(null), dimension, annotations);
        r.setComment(comment);
        n.getOrphanComments().stream().map(Comment::clone).forEach(r::addOrphanComment);
        this.copyData(n, r);
        return r;
    }

    @Override
    public Visitable visit(IntersectionType n, Object arg) {
        NodeList<ReferenceType> elements = this.cloneList(n.getElements(), arg);
        NodeList<AnnotationExpr> annotations = this.cloneList(n.getAnnotations(), arg);
        Comment comment = (Comment)((Object)this.cloneNode((Node)((Object)n.getComment()), arg));
        IntersectionType r = new IntersectionType((TokenRange)n.getTokenRange().orElse(null), elements);
        r.setComment(comment);
        n.getOrphanComments().stream().map(Comment::clone).forEach(r::addOrphanComment);
        this.copyData(n, r);
        return r;
    }

    @Override
    public Visitable visit(UnionType n, Object arg) {
        NodeList<ReferenceType> elements = this.cloneList(n.getElements(), arg);
        NodeList<AnnotationExpr> annotations = this.cloneList(n.getAnnotations(), arg);
        Comment comment = (Comment)((Object)this.cloneNode((Node)((Object)n.getComment()), arg));
        UnionType r = new UnionType((TokenRange)n.getTokenRange().orElse(null), elements);
        r.setComment(comment);
        n.getOrphanComments().stream().map(Comment::clone).forEach(r::addOrphanComment);
        this.copyData(n, r);
        return r;
    }

    @Override
    public Visitable visit(VoidType n, Object arg) {
        NodeList<AnnotationExpr> annotations = this.cloneList(n.getAnnotations(), arg);
        Comment comment = (Comment)((Object)this.cloneNode((Node)((Object)n.getComment()), arg));
        VoidType r = new VoidType(n.getTokenRange().orElse(null));
        r.setComment(comment);
        n.getOrphanComments().stream().map(Comment::clone).forEach(r::addOrphanComment);
        this.copyData(n, r);
        return r;
    }

    @Override
    public Visitable visit(WildcardType n, Object arg) {
        ReferenceType extendedType = (ReferenceType)((Object)this.cloneNode((Node)((Object)n.getExtendedType()), arg));
        ReferenceType superType = (ReferenceType)((Object)this.cloneNode((Node)((Object)n.getSuperType()), arg));
        NodeList<AnnotationExpr> annotations = this.cloneList(n.getAnnotations(), arg);
        Comment comment = (Comment)((Object)this.cloneNode((Node)((Object)n.getComment()), arg));
        WildcardType r = new WildcardType(n.getTokenRange().orElse(null), extendedType, superType, annotations);
        r.setComment(comment);
        n.getOrphanComments().stream().map(Comment::clone).forEach(r::addOrphanComment);
        this.copyData(n, r);
        return r;
    }

    @Override
    public Visitable visit(UnknownType n, Object arg) {
        NodeList<AnnotationExpr> annotations = this.cloneList(n.getAnnotations(), arg);
        Comment comment = (Comment)((Object)this.cloneNode((Node)((Object)n.getComment()), arg));
        UnknownType r = new UnknownType(n.getTokenRange().orElse(null));
        r.setComment(comment);
        n.getOrphanComments().stream().map(Comment::clone).forEach(r::addOrphanComment);
        this.copyData(n, r);
        return r;
    }

    @Override
    public Visitable visit(ArrayAccessExpr n, Object arg) {
        Expression index = this.cloneNode(n.getIndex(), arg);
        Expression name = this.cloneNode(n.getName(), arg);
        Comment comment = (Comment)((Object)this.cloneNode((Node)((Object)n.getComment()), arg));
        ArrayAccessExpr r = new ArrayAccessExpr(n.getTokenRange().orElse(null), name, index);
        r.setComment(comment);
        n.getOrphanComments().stream().map(Comment::clone).forEach(r::addOrphanComment);
        this.copyData(n, r);
        return r;
    }

    @Override
    public Visitable visit(ArrayCreationExpr n, Object arg) {
        Type elementType = this.cloneNode(n.getElementType(), arg);
        ArrayInitializerExpr initializer = (ArrayInitializerExpr)((Object)this.cloneNode((Node)((Object)n.getInitializer()), arg));
        NodeList<ArrayCreationLevel> levels = this.cloneList(n.getLevels(), arg);
        Comment comment = (Comment)((Object)this.cloneNode((Node)((Object)n.getComment()), arg));
        ArrayCreationExpr r = new ArrayCreationExpr(n.getTokenRange().orElse(null), elementType, levels, initializer);
        r.setComment(comment);
        n.getOrphanComments().stream().map(Comment::clone).forEach(r::addOrphanComment);
        this.copyData(n, r);
        return r;
    }

    @Override
    public Visitable visit(ArrayInitializerExpr n, Object arg) {
        NodeList<Expression> values = this.cloneList(n.getValues(), arg);
        Comment comment = (Comment)((Object)this.cloneNode((Node)((Object)n.getComment()), arg));
        ArrayInitializerExpr r = new ArrayInitializerExpr(n.getTokenRange().orElse(null), values);
        r.setComment(comment);
        n.getOrphanComments().stream().map(Comment::clone).forEach(r::addOrphanComment);
        this.copyData(n, r);
        return r;
    }

    @Override
    public Visitable visit(AssignExpr n, Object arg) {
        Expression target = this.cloneNode(n.getTarget(), arg);
        Expression value = this.cloneNode(n.getValue(), arg);
        Comment comment = (Comment)((Object)this.cloneNode((Node)((Object)n.getComment()), arg));
        AssignExpr r = new AssignExpr(n.getTokenRange().orElse(null), target, value, n.getOperator());
        r.setComment(comment);
        n.getOrphanComments().stream().map(Comment::clone).forEach(r::addOrphanComment);
        this.copyData(n, r);
        return r;
    }

    @Override
    public Visitable visit(BinaryExpr n, Object arg) {
        Expression left = this.cloneNode(n.getLeft(), arg);
        Expression right = this.cloneNode(n.getRight(), arg);
        Comment comment = (Comment)((Object)this.cloneNode((Node)((Object)n.getComment()), arg));
        BinaryExpr r = new BinaryExpr(n.getTokenRange().orElse(null), left, right, n.getOperator());
        r.setComment(comment);
        n.getOrphanComments().stream().map(Comment::clone).forEach(r::addOrphanComment);
        this.copyData(n, r);
        return r;
    }

    @Override
    public Visitable visit(CastExpr n, Object arg) {
        Expression expression = this.cloneNode(n.getExpression(), arg);
        Type type = this.cloneNode(n.getType(), arg);
        Comment comment = (Comment)((Object)this.cloneNode((Node)((Object)n.getComment()), arg));
        CastExpr r = new CastExpr(n.getTokenRange().orElse(null), type, expression);
        r.setComment(comment);
        n.getOrphanComments().stream().map(Comment::clone).forEach(r::addOrphanComment);
        this.copyData(n, r);
        return r;
    }

    @Override
    public Visitable visit(ClassExpr n, Object arg) {
        Type type = this.cloneNode(n.getType(), arg);
        Comment comment = (Comment)((Object)this.cloneNode((Node)((Object)n.getComment()), arg));
        ClassExpr r = new ClassExpr(n.getTokenRange().orElse(null), type);
        r.setComment(comment);
        n.getOrphanComments().stream().map(Comment::clone).forEach(r::addOrphanComment);
        this.copyData(n, r);
        return r;
    }

    @Override
    public Visitable visit(ConditionalExpr n, Object arg) {
        Expression condition = this.cloneNode(n.getCondition(), arg);
        Expression elseExpr = this.cloneNode(n.getElseExpr(), arg);
        Expression thenExpr = this.cloneNode(n.getThenExpr(), arg);
        Comment comment = (Comment)((Object)this.cloneNode((Node)((Object)n.getComment()), arg));
        ConditionalExpr r = new ConditionalExpr(n.getTokenRange().orElse(null), condition, thenExpr, elseExpr);
        r.setComment(comment);
        n.getOrphanComments().stream().map(Comment::clone).forEach(r::addOrphanComment);
        this.copyData(n, r);
        return r;
    }

    @Override
    public Visitable visit(EnclosedExpr n, Object arg) {
        Expression inner = this.cloneNode(n.getInner(), arg);
        Comment comment = (Comment)((Object)this.cloneNode((Node)((Object)n.getComment()), arg));
        EnclosedExpr r = new EnclosedExpr(n.getTokenRange().orElse(null), inner);
        r.setComment(comment);
        n.getOrphanComments().stream().map(Comment::clone).forEach(r::addOrphanComment);
        this.copyData(n, r);
        return r;
    }

    @Override
    public Visitable visit(FieldAccessExpr n, Object arg) {
        SimpleName name = this.cloneNode(n.getName(), arg);
        Expression scope = this.cloneNode(n.getScope(), arg);
        NodeList<Type> typeArguments = this.cloneList(n.getTypeArguments().orElse(null), arg);
        Comment comment = (Comment)((Object)this.cloneNode((Node)((Object)n.getComment()), arg));
        FieldAccessExpr r = new FieldAccessExpr(n.getTokenRange().orElse(null), scope, typeArguments, name);
        r.setComment(comment);
        n.getOrphanComments().stream().map(Comment::clone).forEach(r::addOrphanComment);
        this.copyData(n, r);
        return r;
    }

    @Override
    public Visitable visit(InstanceOfExpr n, Object arg) {
        Expression expression = this.cloneNode(n.getExpression(), arg);
        PatternExpr pattern = (PatternExpr)((Object)this.cloneNode((Node)((Object)n.getPattern()), arg));
        ReferenceType type = this.cloneNode(n.getType(), arg);
        Comment comment = (Comment)((Object)this.cloneNode((Node)((Object)n.getComment()), arg));
        InstanceOfExpr r = new InstanceOfExpr(n.getTokenRange().orElse(null), expression, type, pattern);
        r.setComment(comment);
        n.getOrphanComments().stream().map(Comment::clone).forEach(r::addOrphanComment);
        this.copyData(n, r);
        return r;
    }

    @Override
    public Visitable visit(StringLiteralExpr n, Object arg) {
        Comment comment = (Comment)((Object)this.cloneNode((Node)((Object)n.getComment()), arg));
        StringLiteralExpr r = new StringLiteralExpr(n.getTokenRange().orElse(null), n.getValue());
        r.setComment(comment);
        n.getOrphanComments().stream().map(Comment::clone).forEach(r::addOrphanComment);
        this.copyData(n, r);
        return r;
    }

    @Override
    public Visitable visit(IntegerLiteralExpr n, Object arg) {
        Comment comment = (Comment)((Object)this.cloneNode((Node)((Object)n.getComment()), arg));
        IntegerLiteralExpr r = new IntegerLiteralExpr(n.getTokenRange().orElse(null), n.getValue());
        r.setComment(comment);
        n.getOrphanComments().stream().map(Comment::clone).forEach(r::addOrphanComment);
        this.copyData(n, r);
        return r;
    }

    @Override
    public Visitable visit(LongLiteralExpr n, Object arg) {
        Comment comment = (Comment)((Object)this.cloneNode((Node)((Object)n.getComment()), arg));
        LongLiteralExpr r = new LongLiteralExpr(n.getTokenRange().orElse(null), n.getValue());
        r.setComment(comment);
        n.getOrphanComments().stream().map(Comment::clone).forEach(r::addOrphanComment);
        this.copyData(n, r);
        return r;
    }

    @Override
    public Visitable visit(CharLiteralExpr n, Object arg) {
        Comment comment = (Comment)((Object)this.cloneNode((Node)((Object)n.getComment()), arg));
        CharLiteralExpr r = new CharLiteralExpr(n.getTokenRange().orElse(null), n.getValue());
        r.setComment(comment);
        n.getOrphanComments().stream().map(Comment::clone).forEach(r::addOrphanComment);
        this.copyData(n, r);
        return r;
    }

    @Override
    public Visitable visit(DoubleLiteralExpr n, Object arg) {
        Comment comment = (Comment)((Object)this.cloneNode((Node)((Object)n.getComment()), arg));
        DoubleLiteralExpr r = new DoubleLiteralExpr(n.getTokenRange().orElse(null), n.getValue());
        r.setComment(comment);
        n.getOrphanComments().stream().map(Comment::clone).forEach(r::addOrphanComment);
        this.copyData(n, r);
        return r;
    }

    @Override
    public Visitable visit(BooleanLiteralExpr n, Object arg) {
        Comment comment = (Comment)((Object)this.cloneNode((Node)((Object)n.getComment()), arg));
        BooleanLiteralExpr r = new BooleanLiteralExpr(n.getTokenRange().orElse(null), n.isValue());
        r.setComment(comment);
        n.getOrphanComments().stream().map(Comment::clone).forEach(r::addOrphanComment);
        this.copyData(n, r);
        return r;
    }

    @Override
    public Visitable visit(NullLiteralExpr n, Object arg) {
        Comment comment = (Comment)((Object)this.cloneNode((Node)((Object)n.getComment()), arg));
        NullLiteralExpr r = new NullLiteralExpr(n.getTokenRange().orElse(null));
        r.setComment(comment);
        n.getOrphanComments().stream().map(Comment::clone).forEach(r::addOrphanComment);
        this.copyData(n, r);
        return r;
    }

    @Override
    public Visitable visit(MethodCallExpr n, Object arg) {
        NodeList<Expression> arguments = this.cloneList(n.getArguments(), arg);
        SimpleName name = this.cloneNode(n.getName(), arg);
        Expression scope = (Expression)((Object)this.cloneNode((Node)((Object)n.getScope()), arg));
        NodeList<Type> typeArguments = this.cloneList(n.getTypeArguments().orElse(null), arg);
        Comment comment = (Comment)((Object)this.cloneNode((Node)((Object)n.getComment()), arg));
        MethodCallExpr r = new MethodCallExpr(n.getTokenRange().orElse(null), scope, typeArguments, name, arguments);
        r.setComment(comment);
        n.getOrphanComments().stream().map(Comment::clone).forEach(r::addOrphanComment);
        this.copyData(n, r);
        return r;
    }

    @Override
    public Visitable visit(NameExpr n, Object arg) {
        SimpleName name = this.cloneNode(n.getName(), arg);
        Comment comment = (Comment)((Object)this.cloneNode((Node)((Object)n.getComment()), arg));
        NameExpr r = new NameExpr(n.getTokenRange().orElse(null), name);
        r.setComment(comment);
        n.getOrphanComments().stream().map(Comment::clone).forEach(r::addOrphanComment);
        this.copyData(n, r);
        return r;
    }

    @Override
    public Visitable visit(ObjectCreationExpr n, Object arg) {
        NodeList<BodyDeclaration<?>> anonymousClassBody = this.cloneList(n.getAnonymousClassBody().orElse(null), arg);
        NodeList<Expression> arguments = this.cloneList(n.getArguments(), arg);
        Expression scope = (Expression)((Object)this.cloneNode((Node)((Object)n.getScope()), arg));
        ClassOrInterfaceType type = this.cloneNode(n.getType(), arg);
        NodeList<Type> typeArguments = this.cloneList(n.getTypeArguments().orElse(null), arg);
        Comment comment = (Comment)((Object)this.cloneNode((Node)((Object)n.getComment()), arg));
        ObjectCreationExpr r = new ObjectCreationExpr(n.getTokenRange().orElse(null), scope, type, typeArguments, arguments, anonymousClassBody);
        r.setComment(comment);
        n.getOrphanComments().stream().map(Comment::clone).forEach(r::addOrphanComment);
        this.copyData(n, r);
        return r;
    }

    @Override
    public Visitable visit(Name n, Object arg) {
        Name qualifier = (Name)((Object)this.cloneNode((Node)((Object)n.getQualifier()), arg));
        Comment comment = (Comment)((Object)this.cloneNode((Node)((Object)n.getComment()), arg));
        Name r = new Name(n.getTokenRange().orElse(null), qualifier, n.getIdentifier());
        r.setComment(comment);
        n.getOrphanComments().stream().map(Comment::clone).forEach(r::addOrphanComment);
        this.copyData(n, r);
        return r;
    }

    @Override
    public Visitable visit(SimpleName n, Object arg) {
        Comment comment = (Comment)((Object)this.cloneNode((Node)((Object)n.getComment()), arg));
        SimpleName r = new SimpleName(n.getTokenRange().orElse(null), n.getIdentifier());
        r.setComment(comment);
        n.getOrphanComments().stream().map(Comment::clone).forEach(r::addOrphanComment);
        this.copyData(n, r);
        return r;
    }

    @Override
    public Visitable visit(ThisExpr n, Object arg) {
        Name typeName = (Name)((Object)this.cloneNode((Node)((Object)n.getTypeName()), arg));
        Comment comment = (Comment)((Object)this.cloneNode((Node)((Object)n.getComment()), arg));
        ThisExpr r = new ThisExpr(n.getTokenRange().orElse(null), typeName);
        r.setComment(comment);
        n.getOrphanComments().stream().map(Comment::clone).forEach(r::addOrphanComment);
        this.copyData(n, r);
        return r;
    }

    @Override
    public Visitable visit(SuperExpr n, Object arg) {
        Name typeName = (Name)((Object)this.cloneNode((Node)((Object)n.getTypeName()), arg));
        Comment comment = (Comment)((Object)this.cloneNode((Node)((Object)n.getComment()), arg));
        SuperExpr r = new SuperExpr(n.getTokenRange().orElse(null), typeName);
        r.setComment(comment);
        n.getOrphanComments().stream().map(Comment::clone).forEach(r::addOrphanComment);
        this.copyData(n, r);
        return r;
    }

    @Override
    public Visitable visit(UnaryExpr n, Object arg) {
        Expression expression = this.cloneNode(n.getExpression(), arg);
        Comment comment = (Comment)((Object)this.cloneNode((Node)((Object)n.getComment()), arg));
        UnaryExpr r = new UnaryExpr(n.getTokenRange().orElse(null), expression, n.getOperator());
        r.setComment(comment);
        n.getOrphanComments().stream().map(Comment::clone).forEach(r::addOrphanComment);
        this.copyData(n, r);
        return r;
    }

    @Override
    public Visitable visit(VariableDeclarationExpr n, Object arg) {
        NodeList<AnnotationExpr> annotations = this.cloneList(n.getAnnotations(), arg);
        NodeList<Modifier> modifiers = this.cloneList(n.getModifiers(), arg);
        NodeList<VariableDeclarator> variables = this.cloneList(n.getVariables(), arg);
        Comment comment = (Comment)((Object)this.cloneNode((Node)((Object)n.getComment()), arg));
        VariableDeclarationExpr r = new VariableDeclarationExpr(n.getTokenRange().orElse(null), modifiers, annotations, variables);
        r.setComment(comment);
        n.getOrphanComments().stream().map(Comment::clone).forEach(r::addOrphanComment);
        this.copyData(n, r);
        return r;
    }

    @Override
    public Visitable visit(MarkerAnnotationExpr n, Object arg) {
        Name name = this.cloneNode(n.getName(), arg);
        Comment comment = (Comment)((Object)this.cloneNode((Node)((Object)n.getComment()), arg));
        MarkerAnnotationExpr r = new MarkerAnnotationExpr(n.getTokenRange().orElse(null), name);
        r.setComment(comment);
        n.getOrphanComments().stream().map(Comment::clone).forEach(r::addOrphanComment);
        this.copyData(n, r);
        return r;
    }

    @Override
    public Visitable visit(SingleMemberAnnotationExpr n, Object arg) {
        Expression memberValue = this.cloneNode(n.getMemberValue(), arg);
        Name name = this.cloneNode(n.getName(), arg);
        Comment comment = (Comment)((Object)this.cloneNode((Node)((Object)n.getComment()), arg));
        SingleMemberAnnotationExpr r = new SingleMemberAnnotationExpr(n.getTokenRange().orElse(null), name, memberValue);
        r.setComment(comment);
        n.getOrphanComments().stream().map(Comment::clone).forEach(r::addOrphanComment);
        this.copyData(n, r);
        return r;
    }

    @Override
    public Visitable visit(NormalAnnotationExpr n, Object arg) {
        NodeList<MemberValuePair> pairs = this.cloneList(n.getPairs(), arg);
        Name name = this.cloneNode(n.getName(), arg);
        Comment comment = (Comment)((Object)this.cloneNode((Node)((Object)n.getComment()), arg));
        NormalAnnotationExpr r = new NormalAnnotationExpr(n.getTokenRange().orElse(null), name, pairs);
        r.setComment(comment);
        n.getOrphanComments().stream().map(Comment::clone).forEach(r::addOrphanComment);
        this.copyData(n, r);
        return r;
    }

    @Override
    public Visitable visit(MemberValuePair n, Object arg) {
        SimpleName name = this.cloneNode(n.getName(), arg);
        Expression value = this.cloneNode(n.getValue(), arg);
        Comment comment = (Comment)((Object)this.cloneNode((Node)((Object)n.getComment()), arg));
        MemberValuePair r = new MemberValuePair(n.getTokenRange().orElse(null), name, value);
        r.setComment(comment);
        n.getOrphanComments().stream().map(Comment::clone).forEach(r::addOrphanComment);
        this.copyData(n, r);
        return r;
    }

    @Override
    public Visitable visit(ExplicitConstructorInvocationStmt n, Object arg) {
        NodeList<Expression> arguments = this.cloneList(n.getArguments(), arg);
        Expression expression = (Expression)((Object)this.cloneNode((Node)((Object)n.getExpression()), arg));
        NodeList<Type> typeArguments = this.cloneList(n.getTypeArguments().orElse(null), arg);
        Comment comment = (Comment)((Object)this.cloneNode((Node)((Object)n.getComment()), arg));
        ExplicitConstructorInvocationStmt r = new ExplicitConstructorInvocationStmt(n.getTokenRange().orElse(null), typeArguments, n.isThis(), expression, arguments);
        r.setComment(comment);
        n.getOrphanComments().stream().map(Comment::clone).forEach(r::addOrphanComment);
        this.copyData(n, r);
        return r;
    }

    @Override
    public Visitable visit(LocalClassDeclarationStmt n, Object arg) {
        ClassOrInterfaceDeclaration classDeclaration = this.cloneNode(n.getClassDeclaration(), arg);
        Comment comment = (Comment)((Object)this.cloneNode((Node)((Object)n.getComment()), arg));
        LocalClassDeclarationStmt r = new LocalClassDeclarationStmt(n.getTokenRange().orElse(null), classDeclaration);
        r.setComment(comment);
        n.getOrphanComments().stream().map(Comment::clone).forEach(r::addOrphanComment);
        this.copyData(n, r);
        return r;
    }

    @Override
    public Visitable visit(AssertStmt n, Object arg) {
        Expression check = this.cloneNode(n.getCheck(), arg);
        Expression message = (Expression)((Object)this.cloneNode((Node)((Object)n.getMessage()), arg));
        Comment comment = (Comment)((Object)this.cloneNode((Node)((Object)n.getComment()), arg));
        AssertStmt r = new AssertStmt(n.getTokenRange().orElse(null), check, message);
        r.setComment(comment);
        n.getOrphanComments().stream().map(Comment::clone).forEach(r::addOrphanComment);
        this.copyData(n, r);
        return r;
    }

    @Override
    public Visitable visit(BlockStmt n, Object arg) {
        NodeList<Statement> statements = this.cloneList(n.getStatements(), arg);
        Comment comment = (Comment)((Object)this.cloneNode((Node)((Object)n.getComment()), arg));
        BlockStmt r = new BlockStmt(n.getTokenRange().orElse(null), statements);
        r.setComment(comment);
        n.getOrphanComments().stream().map(Comment::clone).forEach(r::addOrphanComment);
        this.copyData(n, r);
        return r;
    }

    @Override
    public Visitable visit(LabeledStmt n, Object arg) {
        SimpleName label = this.cloneNode(n.getLabel(), arg);
        Statement statement = this.cloneNode(n.getStatement(), arg);
        Comment comment = (Comment)((Object)this.cloneNode((Node)((Object)n.getComment()), arg));
        LabeledStmt r = new LabeledStmt(n.getTokenRange().orElse(null), label, statement);
        r.setComment(comment);
        n.getOrphanComments().stream().map(Comment::clone).forEach(r::addOrphanComment);
        this.copyData(n, r);
        return r;
    }

    @Override
    public Visitable visit(EmptyStmt n, Object arg) {
        Comment comment = (Comment)((Object)this.cloneNode((Node)((Object)n.getComment()), arg));
        EmptyStmt r = new EmptyStmt(n.getTokenRange().orElse(null));
        r.setComment(comment);
        n.getOrphanComments().stream().map(Comment::clone).forEach(r::addOrphanComment);
        this.copyData(n, r);
        return r;
    }

    @Override
    public Visitable visit(ExpressionStmt n, Object arg) {
        Expression expression = this.cloneNode(n.getExpression(), arg);
        Comment comment = (Comment)((Object)this.cloneNode((Node)((Object)n.getComment()), arg));
        ExpressionStmt r = new ExpressionStmt(n.getTokenRange().orElse(null), expression);
        r.setComment(comment);
        n.getOrphanComments().stream().map(Comment::clone).forEach(r::addOrphanComment);
        this.copyData(n, r);
        return r;
    }

    @Override
    public Visitable visit(SwitchStmt n, Object arg) {
        NodeList<SwitchEntry> entries = this.cloneList(n.getEntries(), arg);
        Expression selector = this.cloneNode(n.getSelector(), arg);
        Comment comment = (Comment)((Object)this.cloneNode((Node)((Object)n.getComment()), arg));
        SwitchStmt r = new SwitchStmt(n.getTokenRange().orElse(null), selector, entries);
        r.setComment(comment);
        n.getOrphanComments().stream().map(Comment::clone).forEach(r::addOrphanComment);
        this.copyData(n, r);
        return r;
    }

    @Override
    public Visitable visit(SwitchEntry n, Object arg) {
        NodeList<Expression> labels = this.cloneList(n.getLabels(), arg);
        NodeList<Statement> statements = this.cloneList(n.getStatements(), arg);
        Comment comment = (Comment)((Object)this.cloneNode((Node)((Object)n.getComment()), arg));
        SwitchEntry r = new SwitchEntry(n.getTokenRange().orElse(null), labels, n.getType(), statements);
        r.setComment(comment);
        n.getOrphanComments().stream().map(Comment::clone).forEach(r::addOrphanComment);
        this.copyData(n, r);
        return r;
    }

    @Override
    public Visitable visit(BreakStmt n, Object arg) {
        SimpleName label = (SimpleName)((Object)this.cloneNode((Node)((Object)n.getLabel()), arg));
        Comment comment = (Comment)((Object)this.cloneNode((Node)((Object)n.getComment()), arg));
        BreakStmt r = new BreakStmt(n.getTokenRange().orElse(null), label);
        r.setComment(comment);
        n.getOrphanComments().stream().map(Comment::clone).forEach(r::addOrphanComment);
        this.copyData(n, r);
        return r;
    }

    @Override
    public Visitable visit(ReturnStmt n, Object arg) {
        Expression expression = (Expression)((Object)this.cloneNode((Node)((Object)n.getExpression()), arg));
        Comment comment = (Comment)((Object)this.cloneNode((Node)((Object)n.getComment()), arg));
        ReturnStmt r = new ReturnStmt(n.getTokenRange().orElse(null), expression);
        r.setComment(comment);
        n.getOrphanComments().stream().map(Comment::clone).forEach(r::addOrphanComment);
        this.copyData(n, r);
        return r;
    }

    @Override
    public Visitable visit(IfStmt n, Object arg) {
        Expression condition = this.cloneNode(n.getCondition(), arg);
        Statement elseStmt = (Statement)((Object)this.cloneNode((Node)((Object)n.getElseStmt()), arg));
        Statement thenStmt = this.cloneNode(n.getThenStmt(), arg);
        Comment comment = (Comment)((Object)this.cloneNode((Node)((Object)n.getComment()), arg));
        IfStmt r = new IfStmt(n.getTokenRange().orElse(null), condition, thenStmt, elseStmt);
        r.setComment(comment);
        n.getOrphanComments().stream().map(Comment::clone).forEach(r::addOrphanComment);
        this.copyData(n, r);
        return r;
    }

    @Override
    public Visitable visit(WhileStmt n, Object arg) {
        Statement body = this.cloneNode(n.getBody(), arg);
        Expression condition = this.cloneNode(n.getCondition(), arg);
        Comment comment = (Comment)((Object)this.cloneNode((Node)((Object)n.getComment()), arg));
        WhileStmt r = new WhileStmt(n.getTokenRange().orElse(null), condition, body);
        r.setComment(comment);
        n.getOrphanComments().stream().map(Comment::clone).forEach(r::addOrphanComment);
        this.copyData(n, r);
        return r;
    }

    @Override
    public Visitable visit(ContinueStmt n, Object arg) {
        SimpleName label = (SimpleName)((Object)this.cloneNode((Node)((Object)n.getLabel()), arg));
        Comment comment = (Comment)((Object)this.cloneNode((Node)((Object)n.getComment()), arg));
        ContinueStmt r = new ContinueStmt(n.getTokenRange().orElse(null), label);
        r.setComment(comment);
        n.getOrphanComments().stream().map(Comment::clone).forEach(r::addOrphanComment);
        this.copyData(n, r);
        return r;
    }

    @Override
    public Visitable visit(DoStmt n, Object arg) {
        Statement body = this.cloneNode(n.getBody(), arg);
        Expression condition = this.cloneNode(n.getCondition(), arg);
        Comment comment = (Comment)((Object)this.cloneNode((Node)((Object)n.getComment()), arg));
        DoStmt r = new DoStmt(n.getTokenRange().orElse(null), body, condition);
        r.setComment(comment);
        n.getOrphanComments().stream().map(Comment::clone).forEach(r::addOrphanComment);
        this.copyData(n, r);
        return r;
    }

    @Override
    public Visitable visit(ForEachStmt n, Object arg) {
        Statement body = this.cloneNode(n.getBody(), arg);
        Expression iterable = this.cloneNode(n.getIterable(), arg);
        VariableDeclarationExpr variable = this.cloneNode(n.getVariable(), arg);
        Comment comment = (Comment)((Object)this.cloneNode((Node)((Object)n.getComment()), arg));
        ForEachStmt r = new ForEachStmt(n.getTokenRange().orElse(null), variable, iterable, body);
        r.setComment(comment);
        n.getOrphanComments().stream().map(Comment::clone).forEach(r::addOrphanComment);
        this.copyData(n, r);
        return r;
    }

    @Override
    public Visitable visit(ForStmt n, Object arg) {
        Statement body = this.cloneNode(n.getBody(), arg);
        Expression compare = (Expression)((Object)this.cloneNode((Node)((Object)n.getCompare()), arg));
        NodeList<Expression> initialization = this.cloneList(n.getInitialization(), arg);
        NodeList<Expression> update = this.cloneList(n.getUpdate(), arg);
        Comment comment = (Comment)((Object)this.cloneNode((Node)((Object)n.getComment()), arg));
        ForStmt r = new ForStmt(n.getTokenRange().orElse(null), initialization, compare, update, body);
        r.setComment(comment);
        n.getOrphanComments().stream().map(Comment::clone).forEach(r::addOrphanComment);
        this.copyData(n, r);
        return r;
    }

    @Override
    public Visitable visit(ThrowStmt n, Object arg) {
        Expression expression = this.cloneNode(n.getExpression(), arg);
        Comment comment = (Comment)((Object)this.cloneNode((Node)((Object)n.getComment()), arg));
        ThrowStmt r = new ThrowStmt(n.getTokenRange().orElse(null), expression);
        r.setComment(comment);
        n.getOrphanComments().stream().map(Comment::clone).forEach(r::addOrphanComment);
        this.copyData(n, r);
        return r;
    }

    @Override
    public Visitable visit(SynchronizedStmt n, Object arg) {
        BlockStmt body = this.cloneNode(n.getBody(), arg);
        Expression expression = this.cloneNode(n.getExpression(), arg);
        Comment comment = (Comment)((Object)this.cloneNode((Node)((Object)n.getComment()), arg));
        SynchronizedStmt r = new SynchronizedStmt(n.getTokenRange().orElse(null), expression, body);
        r.setComment(comment);
        n.getOrphanComments().stream().map(Comment::clone).forEach(r::addOrphanComment);
        this.copyData(n, r);
        return r;
    }

    @Override
    public Visitable visit(TryStmt n, Object arg) {
        NodeList<CatchClause> catchClauses = this.cloneList(n.getCatchClauses(), arg);
        BlockStmt finallyBlock = (BlockStmt)((Object)this.cloneNode((Node)((Object)n.getFinallyBlock()), arg));
        NodeList<Expression> resources = this.cloneList(n.getResources(), arg);
        BlockStmt tryBlock = this.cloneNode(n.getTryBlock(), arg);
        Comment comment = (Comment)((Object)this.cloneNode((Node)((Object)n.getComment()), arg));
        TryStmt r = new TryStmt(n.getTokenRange().orElse(null), resources, tryBlock, catchClauses, finallyBlock);
        r.setComment(comment);
        n.getOrphanComments().stream().map(Comment::clone).forEach(r::addOrphanComment);
        this.copyData(n, r);
        return r;
    }

    @Override
    public Visitable visit(CatchClause n, Object arg) {
        BlockStmt body = this.cloneNode(n.getBody(), arg);
        Parameter parameter = this.cloneNode(n.getParameter(), arg);
        Comment comment = (Comment)((Object)this.cloneNode((Node)((Object)n.getComment()), arg));
        CatchClause r = new CatchClause(n.getTokenRange().orElse(null), parameter, body);
        r.setComment(comment);
        n.getOrphanComments().stream().map(Comment::clone).forEach(r::addOrphanComment);
        this.copyData(n, r);
        return r;
    }

    @Override
    public Visitable visit(LambdaExpr n, Object arg) {
        Statement body = this.cloneNode(n.getBody(), arg);
        NodeList<Parameter> parameters = this.cloneList(n.getParameters(), arg);
        Comment comment = (Comment)((Object)this.cloneNode((Node)((Object)n.getComment()), arg));
        LambdaExpr r = new LambdaExpr(n.getTokenRange().orElse(null), parameters, body, n.isEnclosingParameters());
        r.setComment(comment);
        n.getOrphanComments().stream().map(Comment::clone).forEach(r::addOrphanComment);
        this.copyData(n, r);
        return r;
    }

    @Override
    public Visitable visit(MethodReferenceExpr n, Object arg) {
        Expression scope = this.cloneNode(n.getScope(), arg);
        NodeList<Type> typeArguments = this.cloneList(n.getTypeArguments().orElse(null), arg);
        Comment comment = (Comment)((Object)this.cloneNode((Node)((Object)n.getComment()), arg));
        MethodReferenceExpr r = new MethodReferenceExpr(n.getTokenRange().orElse(null), scope, typeArguments, n.getIdentifier());
        r.setComment(comment);
        n.getOrphanComments().stream().map(Comment::clone).forEach(r::addOrphanComment);
        this.copyData(n, r);
        return r;
    }

    @Override
    public Visitable visit(TypeExpr n, Object arg) {
        Type type = this.cloneNode(n.getType(), arg);
        Comment comment = (Comment)((Object)this.cloneNode((Node)((Object)n.getComment()), arg));
        TypeExpr r = new TypeExpr(n.getTokenRange().orElse(null), type);
        r.setComment(comment);
        n.getOrphanComments().stream().map(Comment::clone).forEach(r::addOrphanComment);
        this.copyData(n, r);
        return r;
    }

    @Override
    public Visitable visit(NodeList n, Object arg) {
        NodeList<Node> newNodes = new NodeList<Node>();
        for (Object node : n) {
            Node resultNode = (Node)((Node)node).accept(this, arg);
            if (resultNode == null) continue;
            newNodes.add(resultNode);
        }
        return newNodes;
    }

    @Override
    public Node visit(ImportDeclaration n, Object arg) {
        Name name = this.cloneNode(n.getName(), arg);
        Comment comment = (Comment)((Object)this.cloneNode((Node)((Object)n.getComment()), arg));
        ImportDeclaration r = new ImportDeclaration(n.getTokenRange().orElse(null), name, n.isStatic(), n.isAsterisk());
        r.setComment(comment);
        n.getOrphanComments().stream().map(Comment::clone).forEach(r::addOrphanComment);
        this.copyData(n, r);
        return r;
    }

    @Override
    public Visitable visit(ModuleDeclaration n, Object arg) {
        NodeList<AnnotationExpr> annotations = this.cloneList(n.getAnnotations(), arg);
        NodeList<ModuleDirective> directives = this.cloneList(n.getDirectives(), arg);
        Name name = this.cloneNode(n.getName(), arg);
        Comment comment = (Comment)((Object)this.cloneNode((Node)((Object)n.getComment()), arg));
        ModuleDeclaration r = new ModuleDeclaration(n.getTokenRange().orElse(null), annotations, name, n.isOpen(), directives);
        r.setComment(comment);
        n.getOrphanComments().stream().map(Comment::clone).forEach(r::addOrphanComment);
        this.copyData(n, r);
        return r;
    }

    @Override
    public Visitable visit(ModuleRequiresDirective n, Object arg) {
        NodeList<Modifier> modifiers = this.cloneList(n.getModifiers(), arg);
        Name name = this.cloneNode(n.getName(), arg);
        Comment comment = (Comment)((Object)this.cloneNode((Node)((Object)n.getComment()), arg));
        ModuleRequiresDirective r = new ModuleRequiresDirective(n.getTokenRange().orElse(null), modifiers, name);
        r.setComment(comment);
        n.getOrphanComments().stream().map(Comment::clone).forEach(r::addOrphanComment);
        this.copyData(n, r);
        return r;
    }

    protected <T extends Node> T cloneNode(Optional<T> node, Object arg) {
        if (!node.isPresent()) {
            return null;
        }
        Node r = (Node)((Node)node.get()).accept(this, arg);
        if (r == null) {
            return null;
        }
        return (T)r;
    }

    protected <T extends Node> T cloneNode(T node, Object arg) {
        if (node == null) {
            return null;
        }
        Node r = (Node)node.accept(this, (Object)arg);
        if (r == null) {
            return null;
        }
        return (T)r;
    }

    private <N extends Node> NodeList<N> cloneList(NodeList<N> list, Object arg) {
        if (list == null) {
            return null;
        }
        return (NodeList)list.accept(this, arg);
    }

    @Override
    public Visitable visit(ModuleExportsDirective n, Object arg) {
        NodeList<Name> moduleNames = this.cloneList(n.getModuleNames(), arg);
        Name name = this.cloneNode(n.getName(), arg);
        Comment comment = (Comment)((Object)this.cloneNode((Node)((Object)n.getComment()), arg));
        ModuleExportsDirective r = new ModuleExportsDirective(n.getTokenRange().orElse(null), name, moduleNames);
        r.setComment(comment);
        n.getOrphanComments().stream().map(Comment::clone).forEach(r::addOrphanComment);
        this.copyData(n, r);
        return r;
    }

    @Override
    public Visitable visit(ModuleProvidesDirective n, Object arg) {
        Name name = this.cloneNode(n.getName(), arg);
        NodeList<Name> with = this.cloneList(n.getWith(), arg);
        Comment comment = (Comment)((Object)this.cloneNode((Node)((Object)n.getComment()), arg));
        ModuleProvidesDirective r = new ModuleProvidesDirective(n.getTokenRange().orElse(null), name, with);
        r.setComment(comment);
        n.getOrphanComments().stream().map(Comment::clone).forEach(r::addOrphanComment);
        this.copyData(n, r);
        return r;
    }

    @Override
    public Visitable visit(ModuleUsesDirective n, Object arg) {
        Name name = this.cloneNode(n.getName(), arg);
        Comment comment = (Comment)((Object)this.cloneNode((Node)((Object)n.getComment()), arg));
        ModuleUsesDirective r = new ModuleUsesDirective(n.getTokenRange().orElse(null), name);
        r.setComment(comment);
        n.getOrphanComments().stream().map(Comment::clone).forEach(r::addOrphanComment);
        this.copyData(n, r);
        return r;
    }

    @Override
    public Visitable visit(ModuleOpensDirective n, Object arg) {
        NodeList<Name> moduleNames = this.cloneList(n.getModuleNames(), arg);
        Name name = this.cloneNode(n.getName(), arg);
        Comment comment = (Comment)((Object)this.cloneNode((Node)((Object)n.getComment()), arg));
        ModuleOpensDirective r = new ModuleOpensDirective(n.getTokenRange().orElse(null), name, moduleNames);
        r.setComment(comment);
        n.getOrphanComments().stream().map(Comment::clone).forEach(r::addOrphanComment);
        this.copyData(n, r);
        return r;
    }

    @Override
    public Visitable visit(UnparsableStmt n, Object arg) {
        Comment comment = (Comment)((Object)this.cloneNode((Node)((Object)n.getComment()), arg));
        UnparsableStmt r = new UnparsableStmt(n.getTokenRange().orElse(null));
        r.setComment(comment);
        n.getOrphanComments().stream().map(Comment::clone).forEach(r::addOrphanComment);
        this.copyData(n, r);
        return r;
    }

    @Override
    public Visitable visit(ReceiverParameter n, Object arg) {
        NodeList<AnnotationExpr> annotations = this.cloneList(n.getAnnotations(), arg);
        Name name = this.cloneNode(n.getName(), arg);
        Type type = this.cloneNode(n.getType(), arg);
        Comment comment = (Comment)((Object)this.cloneNode((Node)((Object)n.getComment()), arg));
        ReceiverParameter r = new ReceiverParameter(n.getTokenRange().orElse(null), annotations, type, name);
        r.setComment(comment);
        n.getOrphanComments().stream().map(Comment::clone).forEach(r::addOrphanComment);
        this.copyData(n, r);
        return r;
    }

    @Override
    public Visitable visit(VarType n, Object arg) {
        NodeList<AnnotationExpr> annotations = this.cloneList(n.getAnnotations(), arg);
        Comment comment = (Comment)((Object)this.cloneNode((Node)((Object)n.getComment()), arg));
        VarType r = new VarType(n.getTokenRange().orElse(null));
        r.setComment(comment);
        n.getOrphanComments().stream().map(Comment::clone).forEach(r::addOrphanComment);
        this.copyData(n, r);
        return r;
    }

    @Override
    public Visitable visit(Modifier n, Object arg) {
        Comment comment = (Comment)((Object)this.cloneNode((Node)((Object)n.getComment()), arg));
        Modifier r = new Modifier(n.getTokenRange().orElse(null), n.getKeyword());
        r.setComment(comment);
        n.getOrphanComments().stream().map(Comment::clone).forEach(r::addOrphanComment);
        this.copyData(n, r);
        return r;
    }

    @Override
    public Visitable visit(SwitchExpr n, Object arg) {
        NodeList<SwitchEntry> entries = this.cloneList(n.getEntries(), arg);
        Expression selector = this.cloneNode(n.getSelector(), arg);
        Comment comment = (Comment)((Object)this.cloneNode((Node)((Object)n.getComment()), arg));
        SwitchExpr r = new SwitchExpr(n.getTokenRange().orElse(null), selector, entries);
        r.setComment(comment);
        n.getOrphanComments().stream().map(Comment::clone).forEach(r::addOrphanComment);
        this.copyData(n, r);
        return r;
    }

    private void copyData(Node source, Node destination) {
        for (DataKey<?> dataKey : source.getDataKeys()) {
            destination.setData(dataKey, source.getData(dataKey));
        }
    }

    @Override
    public Visitable visit(YieldStmt n, Object arg) {
        Expression expression = this.cloneNode(n.getExpression(), arg);
        Comment comment = (Comment)((Object)this.cloneNode((Node)((Object)n.getComment()), arg));
        YieldStmt r = new YieldStmt(n.getTokenRange().orElse(null), expression);
        r.setComment(comment);
        n.getOrphanComments().stream().map(Comment::clone).forEach(r::addOrphanComment);
        this.copyData(n, r);
        return r;
    }

    @Override
    public Visitable visit(TextBlockLiteralExpr n, Object arg) {
        Comment comment = (Comment)((Object)this.cloneNode((Node)((Object)n.getComment()), arg));
        TextBlockLiteralExpr r = new TextBlockLiteralExpr(n.getTokenRange().orElse(null), n.getValue());
        r.setComment(comment);
        n.getOrphanComments().stream().map(Comment::clone).forEach(r::addOrphanComment);
        this.copyData(n, r);
        return r;
    }

    @Override
    public Visitable visit(PatternExpr n, Object arg) {
        SimpleName name = this.cloneNode(n.getName(), arg);
        ReferenceType type = this.cloneNode(n.getType(), arg);
        Comment comment = (Comment)((Object)this.cloneNode((Node)((Object)n.getComment()), arg));
        PatternExpr r = new PatternExpr(n.getTokenRange().orElse(null), type, name);
        r.setComment(comment);
        n.getOrphanComments().stream().map(Comment::clone).forEach(r::addOrphanComment);
        this.copyData(n, r);
        return r;
    }
}

