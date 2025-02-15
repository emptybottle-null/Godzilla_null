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
import com.github.javaparser.utils.Pair;
import com.github.javaparser.utils.Utils;
import java.util.ArrayList;
import java.util.Optional;

public class ModifierVisitor<A>
implements GenericVisitor<Visitable, A> {
    @Override
    public Visitable visit(AnnotationDeclaration n, A arg) {
        NodeList<AnnotationExpr> annotations = this.modifyList(n.getAnnotations(), arg);
        NodeList<Modifier> modifiers = this.modifyList(n.getModifiers(), arg);
        SimpleName name = (SimpleName)n.getName().accept(this, arg);
        NodeList<BodyDeclaration<?>> members = this.modifyList(n.getMembers(), arg);
        Comment comment = n.getComment().map(s -> (Comment)s.accept(this, arg)).orElse(null);
        if (name == null) {
            return null;
        }
        n.setAnnotations((NodeList)annotations);
        n.setModifiers((NodeList)modifiers);
        n.setName(name);
        n.setMembers((NodeList)members);
        n.setComment(comment);
        return n;
    }

    @Override
    public Visitable visit(AnnotationMemberDeclaration n, A arg) {
        NodeList<AnnotationExpr> annotations = this.modifyList(n.getAnnotations(), arg);
        NodeList<Modifier> modifiers = this.modifyList(n.getModifiers(), arg);
        Expression defaultValue = n.getDefaultValue().map(s -> (Expression)s.accept(this, arg)).orElse(null);
        SimpleName name = (SimpleName)n.getName().accept(this, arg);
        Type type = (Type)n.getType().accept(this, arg);
        Comment comment = n.getComment().map(s -> (Comment)s.accept(this, arg)).orElse(null);
        if (name == null || type == null) {
            return null;
        }
        n.setAnnotations((NodeList)annotations);
        n.setModifiers((NodeList)modifiers);
        n.setDefaultValue(defaultValue);
        n.setName(name);
        n.setType(type);
        n.setComment(comment);
        return n;
    }

    @Override
    public Visitable visit(ArrayAccessExpr n, A arg) {
        Expression index = (Expression)n.getIndex().accept(this, arg);
        Expression name = (Expression)n.getName().accept(this, arg);
        Comment comment = n.getComment().map(s -> (Comment)s.accept(this, arg)).orElse(null);
        if (index == null || name == null) {
            return null;
        }
        n.setIndex(index);
        n.setName(name);
        n.setComment(comment);
        return n;
    }

    @Override
    public Visitable visit(ArrayCreationExpr n, A arg) {
        Type elementType = (Type)n.getElementType().accept(this, arg);
        ArrayInitializerExpr initializer = n.getInitializer().map(s -> (ArrayInitializerExpr)s.accept(this, arg)).orElse(null);
        NodeList<ArrayCreationLevel> levels = this.modifyList(n.getLevels(), arg);
        Comment comment = n.getComment().map(s -> (Comment)s.accept(this, arg)).orElse(null);
        if (elementType == null || levels.isEmpty()) {
            return null;
        }
        n.setElementType(elementType);
        n.setInitializer(initializer);
        n.setLevels(levels);
        n.setComment(comment);
        return n;
    }

    @Override
    public Visitable visit(ArrayInitializerExpr n, A arg) {
        NodeList<Expression> values = this.modifyList(n.getValues(), arg);
        Comment comment = n.getComment().map(s -> (Comment)s.accept(this, arg)).orElse(null);
        n.setValues(values);
        n.setComment(comment);
        return n;
    }

    @Override
    public Visitable visit(AssertStmt n, A arg) {
        Expression check = (Expression)n.getCheck().accept(this, arg);
        Expression message = n.getMessage().map(s -> (Expression)s.accept(this, arg)).orElse(null);
        Comment comment = n.getComment().map(s -> (Comment)s.accept(this, arg)).orElse(null);
        if (check == null) {
            return null;
        }
        n.setCheck(check);
        n.setMessage(message);
        n.setComment(comment);
        return n;
    }

    @Override
    public Visitable visit(AssignExpr n, A arg) {
        Expression target = (Expression)n.getTarget().accept(this, arg);
        Expression value = (Expression)n.getValue().accept(this, arg);
        Comment comment = n.getComment().map(s -> (Comment)s.accept(this, arg)).orElse(null);
        if (target == null || value == null) {
            return null;
        }
        n.setTarget(target);
        n.setValue(value);
        n.setComment(comment);
        return n;
    }

    @Override
    public Visitable visit(BinaryExpr n, A arg) {
        Expression left = (Expression)n.getLeft().accept(this, arg);
        Expression right = (Expression)n.getRight().accept(this, arg);
        Comment comment = n.getComment().map(s -> (Comment)s.accept(this, arg)).orElse(null);
        if (left == null) {
            return right;
        }
        if (right == null) {
            return left;
        }
        n.setLeft(left);
        n.setRight(right);
        n.setComment(comment);
        return n;
    }

    @Override
    public Visitable visit(BlockStmt n, A arg) {
        NodeList<Statement> statements = this.modifyList(n.getStatements(), arg);
        Comment comment = n.getComment().map(s -> (Comment)s.accept(this, arg)).orElse(null);
        n.setStatements((NodeList)statements);
        n.setComment(comment);
        return n;
    }

    @Override
    public Visitable visit(BooleanLiteralExpr n, A arg) {
        Comment comment = n.getComment().map(s -> (Comment)s.accept(this, arg)).orElse(null);
        n.setComment(comment);
        return n;
    }

    @Override
    public Visitable visit(BreakStmt n, A arg) {
        SimpleName label = n.getLabel().map(s -> (SimpleName)s.accept(this, arg)).orElse(null);
        Comment comment = n.getComment().map(s -> (Comment)s.accept(this, arg)).orElse(null);
        n.setLabel(label);
        n.setComment(comment);
        return n;
    }

    @Override
    public Visitable visit(CastExpr n, A arg) {
        Expression expression = (Expression)n.getExpression().accept(this, arg);
        Type type = (Type)n.getType().accept(this, arg);
        Comment comment = n.getComment().map(s -> (Comment)s.accept(this, arg)).orElse(null);
        if (expression == null || type == null) {
            return null;
        }
        n.setExpression(expression);
        n.setType(type);
        n.setComment(comment);
        return n;
    }

    @Override
    public Visitable visit(CatchClause n, A arg) {
        BlockStmt body = (BlockStmt)n.getBody().accept(this, arg);
        Parameter parameter = (Parameter)n.getParameter().accept(this, arg);
        Comment comment = n.getComment().map(s -> (Comment)s.accept(this, arg)).orElse(null);
        if (body == null || parameter == null) {
            return null;
        }
        n.setBody(body);
        n.setParameter(parameter);
        n.setComment(comment);
        return n;
    }

    @Override
    public Visitable visit(CharLiteralExpr n, A arg) {
        Comment comment = n.getComment().map(s -> (Comment)s.accept(this, arg)).orElse(null);
        n.setComment(comment);
        return n;
    }

    @Override
    public Visitable visit(ClassExpr n, A arg) {
        Type type = (Type)n.getType().accept(this, arg);
        Comment comment = n.getComment().map(s -> (Comment)s.accept(this, arg)).orElse(null);
        if (type == null) {
            return null;
        }
        n.setType(type);
        n.setComment(comment);
        return n;
    }

    @Override
    public Visitable visit(ClassOrInterfaceDeclaration n, A arg) {
        NodeList<AnnotationExpr> annotations = this.modifyList(n.getAnnotations(), arg);
        NodeList<Modifier> modifiers = this.modifyList(n.getModifiers(), arg);
        NodeList<ClassOrInterfaceType> extendedTypes = this.modifyList(n.getExtendedTypes(), arg);
        NodeList<ClassOrInterfaceType> implementedTypes = this.modifyList(n.getImplementedTypes(), arg);
        NodeList<TypeParameter> typeParameters = this.modifyList(n.getTypeParameters(), arg);
        NodeList<BodyDeclaration<?>> members = this.modifyList(n.getMembers(), arg);
        SimpleName name = (SimpleName)n.getName().accept(this, arg);
        Comment comment = n.getComment().map(s -> (Comment)s.accept(this, arg)).orElse(null);
        if (name == null) {
            return null;
        }
        n.setAnnotations((NodeList)annotations);
        n.setModifiers((NodeList)modifiers);
        n.setExtendedTypes((NodeList)extendedTypes);
        n.setImplementedTypes((NodeList)implementedTypes);
        n.setTypeParameters((NodeList)typeParameters);
        n.setMembers((NodeList)members);
        n.setName(name);
        n.setComment(comment);
        return n;
    }

    @Override
    public Visitable visit(ClassOrInterfaceType n, A arg) {
        NodeList<AnnotationExpr> annotations = this.modifyList(n.getAnnotations(), arg);
        SimpleName name = (SimpleName)n.getName().accept(this, arg);
        ClassOrInterfaceType scope = n.getScope().map(s -> (ClassOrInterfaceType)s.accept(this, arg)).orElse(null);
        NodeList typeArguments = this.modifyList(n.getTypeArguments(), arg);
        Comment comment = n.getComment().map(s -> (Comment)s.accept(this, arg)).orElse(null);
        if (name == null) {
            return null;
        }
        n.setAnnotations((NodeList)annotations);
        n.setName(name);
        n.setScope(scope);
        n.setTypeArguments(typeArguments);
        n.setComment(comment);
        return n;
    }

    @Override
    public Visitable visit(CompilationUnit n, A arg) {
        NodeList<ImportDeclaration> imports = this.modifyList(n.getImports(), arg);
        ModuleDeclaration module = n.getModule().map(s -> (ModuleDeclaration)s.accept(this, arg)).orElse(null);
        PackageDeclaration packageDeclaration = n.getPackageDeclaration().map(s -> (PackageDeclaration)s.accept(this, arg)).orElse(null);
        NodeList<TypeDeclaration<?>> types = this.modifyList(n.getTypes(), arg);
        Comment comment = n.getComment().map(s -> (Comment)s.accept(this, arg)).orElse(null);
        n.setImports(imports);
        n.setModule(module);
        n.setPackageDeclaration(packageDeclaration);
        n.setTypes(types);
        n.setComment(comment);
        return n;
    }

    @Override
    public Visitable visit(ConditionalExpr n, A arg) {
        Expression condition = (Expression)n.getCondition().accept(this, arg);
        Expression elseExpr = (Expression)n.getElseExpr().accept(this, arg);
        Expression thenExpr = (Expression)n.getThenExpr().accept(this, arg);
        Comment comment = n.getComment().map(s -> (Comment)s.accept(this, arg)).orElse(null);
        if (condition == null || elseExpr == null || thenExpr == null) {
            return null;
        }
        n.setCondition(condition);
        n.setElseExpr(elseExpr);
        n.setThenExpr(thenExpr);
        n.setComment(comment);
        return n;
    }

    @Override
    public Visitable visit(ConstructorDeclaration n, A arg) {
        NodeList<AnnotationExpr> annotations = this.modifyList(n.getAnnotations(), arg);
        NodeList<Modifier> modifiers = this.modifyList(n.getModifiers(), arg);
        BlockStmt body = (BlockStmt)n.getBody().accept(this, arg);
        SimpleName name = (SimpleName)n.getName().accept(this, arg);
        NodeList<Parameter> parameters = this.modifyList(n.getParameters(), arg);
        ReceiverParameter receiverParameter = n.getReceiverParameter().map(s -> (ReceiverParameter)s.accept(this, arg)).orElse(null);
        NodeList<ReferenceType> thrownExceptions = this.modifyList(n.getThrownExceptions(), arg);
        NodeList<TypeParameter> typeParameters = this.modifyList(n.getTypeParameters(), arg);
        Comment comment = n.getComment().map(s -> (Comment)s.accept(this, arg)).orElse(null);
        if (body == null || name == null) {
            return null;
        }
        n.setAnnotations((NodeList)annotations);
        n.setModifiers((NodeList)modifiers);
        n.setBody(body);
        n.setName(name);
        n.setParameters((NodeList)parameters);
        n.setReceiverParameter(receiverParameter);
        n.setThrownExceptions((NodeList)thrownExceptions);
        n.setTypeParameters((NodeList)typeParameters);
        n.setComment(comment);
        return n;
    }

    @Override
    public Visitable visit(ContinueStmt n, A arg) {
        SimpleName label = n.getLabel().map(s -> (SimpleName)s.accept(this, arg)).orElse(null);
        Comment comment = n.getComment().map(s -> (Comment)s.accept(this, arg)).orElse(null);
        n.setLabel(label);
        n.setComment(comment);
        return n;
    }

    @Override
    public Visitable visit(DoStmt n, A arg) {
        Statement body = (Statement)n.getBody().accept(this, arg);
        Expression condition = (Expression)n.getCondition().accept(this, arg);
        Comment comment = n.getComment().map(s -> (Comment)s.accept(this, arg)).orElse(null);
        if (body == null || condition == null) {
            return null;
        }
        n.setBody(body);
        n.setCondition(condition);
        n.setComment(comment);
        return n;
    }

    @Override
    public Visitable visit(DoubleLiteralExpr n, A arg) {
        Comment comment = n.getComment().map(s -> (Comment)s.accept(this, arg)).orElse(null);
        n.setComment(comment);
        return n;
    }

    @Override
    public Visitable visit(EmptyStmt n, A arg) {
        Comment comment = n.getComment().map(s -> (Comment)s.accept(this, arg)).orElse(null);
        n.setComment(comment);
        return n;
    }

    @Override
    public Visitable visit(EnclosedExpr n, A arg) {
        Expression inner = (Expression)n.getInner().accept(this, arg);
        Comment comment = n.getComment().map(s -> (Comment)s.accept(this, arg)).orElse(null);
        if (inner == null) {
            return null;
        }
        n.setInner(inner);
        n.setComment(comment);
        return n;
    }

    @Override
    public Visitable visit(EnumConstantDeclaration n, A arg) {
        NodeList<AnnotationExpr> annotations = this.modifyList(n.getAnnotations(), arg);
        NodeList<Expression> arguments = this.modifyList(n.getArguments(), arg);
        NodeList<BodyDeclaration<?>> classBody = this.modifyList(n.getClassBody(), arg);
        SimpleName name = (SimpleName)n.getName().accept(this, arg);
        Comment comment = n.getComment().map(s -> (Comment)s.accept(this, arg)).orElse(null);
        if (name == null) {
            return null;
        }
        n.setAnnotations((NodeList)annotations);
        n.setArguments((NodeList)arguments);
        n.setClassBody(classBody);
        n.setName(name);
        n.setComment(comment);
        return n;
    }

    @Override
    public Visitable visit(EnumDeclaration n, A arg) {
        NodeList<AnnotationExpr> annotations = this.modifyList(n.getAnnotations(), arg);
        NodeList<Modifier> modifiers = this.modifyList(n.getModifiers(), arg);
        NodeList<EnumConstantDeclaration> entries = this.modifyList(n.getEntries(), arg);
        NodeList<ClassOrInterfaceType> implementedTypes = this.modifyList(n.getImplementedTypes(), arg);
        NodeList<BodyDeclaration<?>> members = this.modifyList(n.getMembers(), arg);
        SimpleName name = (SimpleName)n.getName().accept(this, arg);
        Comment comment = n.getComment().map(s -> (Comment)s.accept(this, arg)).orElse(null);
        if (name == null) {
            return null;
        }
        n.setAnnotations((NodeList)annotations);
        n.setModifiers((NodeList)modifiers);
        n.setEntries(entries);
        n.setImplementedTypes((NodeList)implementedTypes);
        n.setMembers((NodeList)members);
        n.setName(name);
        n.setComment(comment);
        return n;
    }

    @Override
    public Visitable visit(ExplicitConstructorInvocationStmt n, A arg) {
        NodeList<Expression> arguments = this.modifyList(n.getArguments(), arg);
        Expression expression = n.getExpression().map(s -> (Expression)s.accept(this, arg)).orElse(null);
        NodeList typeArguments = this.modifyList(n.getTypeArguments(), arg);
        Comment comment = n.getComment().map(s -> (Comment)s.accept(this, arg)).orElse(null);
        n.setArguments((NodeList)arguments);
        n.setExpression(expression);
        n.setTypeArguments(typeArguments);
        n.setComment(comment);
        return n;
    }

    @Override
    public Visitable visit(ExpressionStmt n, A arg) {
        Expression expression = (Expression)n.getExpression().accept(this, arg);
        Comment comment = n.getComment().map(s -> (Comment)s.accept(this, arg)).orElse(null);
        if (expression == null) {
            return null;
        }
        n.setExpression(expression);
        n.setComment(comment);
        return n;
    }

    @Override
    public Visitable visit(FieldAccessExpr n, A arg) {
        SimpleName name = (SimpleName)n.getName().accept(this, arg);
        Expression scope = (Expression)n.getScope().accept(this, arg);
        NodeList typeArguments = this.modifyList(n.getTypeArguments(), arg);
        Comment comment = n.getComment().map(s -> (Comment)s.accept(this, arg)).orElse(null);
        if (name == null || scope == null) {
            return null;
        }
        n.setName(name);
        n.setScope(scope);
        n.setTypeArguments(typeArguments);
        n.setComment(comment);
        return n;
    }

    @Override
    public Visitable visit(FieldDeclaration n, A arg) {
        NodeList<AnnotationExpr> annotations = this.modifyList(n.getAnnotations(), arg);
        NodeList<Modifier> modifiers = this.modifyList(n.getModifiers(), arg);
        NodeList<VariableDeclarator> variables = this.modifyList(n.getVariables(), arg);
        Comment comment = n.getComment().map(s -> (Comment)s.accept(this, arg)).orElse(null);
        if (variables.isEmpty()) {
            return null;
        }
        n.setAnnotations((NodeList)annotations);
        n.setModifiers((NodeList)modifiers);
        n.setVariables((NodeList)variables);
        n.setComment(comment);
        return n;
    }

    @Override
    public Visitable visit(ForEachStmt n, A arg) {
        Statement body = (Statement)n.getBody().accept(this, arg);
        Expression iterable = (Expression)n.getIterable().accept(this, arg);
        VariableDeclarationExpr variable = (VariableDeclarationExpr)n.getVariable().accept(this, arg);
        Comment comment = n.getComment().map(s -> (Comment)s.accept(this, arg)).orElse(null);
        if (body == null || iterable == null || variable == null) {
            return null;
        }
        n.setBody(body);
        n.setIterable(iterable);
        n.setVariable(variable);
        n.setComment(comment);
        return n;
    }

    @Override
    public Visitable visit(ForStmt n, A arg) {
        Statement body = (Statement)n.getBody().accept(this, arg);
        Expression compare = n.getCompare().map(s -> (Expression)s.accept(this, arg)).orElse(null);
        NodeList<Expression> initialization = this.modifyList(n.getInitialization(), arg);
        NodeList<Expression> update = this.modifyList(n.getUpdate(), arg);
        Comment comment = n.getComment().map(s -> (Comment)s.accept(this, arg)).orElse(null);
        if (body == null) {
            return null;
        }
        n.setBody(body);
        n.setCompare(compare);
        n.setInitialization(initialization);
        n.setUpdate(update);
        n.setComment(comment);
        return n;
    }

    @Override
    public Visitable visit(IfStmt n, A arg) {
        Expression condition = (Expression)n.getCondition().accept(this, arg);
        Statement elseStmt = n.getElseStmt().map(s -> (Statement)s.accept(this, arg)).orElse(null);
        Statement thenStmt = (Statement)n.getThenStmt().accept(this, arg);
        Comment comment = n.getComment().map(s -> (Comment)s.accept(this, arg)).orElse(null);
        if (condition == null || thenStmt == null) {
            return null;
        }
        n.setCondition(condition);
        n.setElseStmt(elseStmt);
        n.setThenStmt(thenStmt);
        n.setComment(comment);
        return n;
    }

    @Override
    public Visitable visit(InitializerDeclaration n, A arg) {
        NodeList<AnnotationExpr> annotations = this.modifyList(n.getAnnotations(), arg);
        BlockStmt body = (BlockStmt)n.getBody().accept(this, arg);
        Comment comment = n.getComment().map(s -> (Comment)s.accept(this, arg)).orElse(null);
        if (body == null) {
            return null;
        }
        n.setAnnotations((NodeList)annotations);
        n.setBody(body);
        n.setComment(comment);
        return n;
    }

    @Override
    public Visitable visit(InstanceOfExpr n, A arg) {
        Expression expression = (Expression)n.getExpression().accept(this, arg);
        PatternExpr pattern = n.getPattern().map(s -> (PatternExpr)s.accept(this, arg)).orElse(null);
        ReferenceType type = (ReferenceType)n.getType().accept(this, arg);
        Comment comment = n.getComment().map(s -> (Comment)s.accept(this, arg)).orElse(null);
        if (expression == null || type == null) {
            return null;
        }
        n.setExpression(expression);
        n.setPattern(pattern);
        n.setType(type);
        n.setComment(comment);
        return n;
    }

    @Override
    public Visitable visit(IntegerLiteralExpr n, A arg) {
        Comment comment = n.getComment().map(s -> (Comment)s.accept(this, arg)).orElse(null);
        n.setComment(comment);
        return n;
    }

    @Override
    public Visitable visit(JavadocComment n, A arg) {
        Comment comment = n.getComment().map(s -> (Comment)s.accept(this, arg)).orElse(null);
        n.setComment(comment);
        return n;
    }

    @Override
    public Visitable visit(LabeledStmt n, A arg) {
        SimpleName label = (SimpleName)n.getLabel().accept(this, arg);
        Statement statement = (Statement)n.getStatement().accept(this, arg);
        Comment comment = n.getComment().map(s -> (Comment)s.accept(this, arg)).orElse(null);
        if (label == null || statement == null) {
            return null;
        }
        n.setLabel(label);
        n.setStatement(statement);
        n.setComment(comment);
        return n;
    }

    @Override
    public Visitable visit(LongLiteralExpr n, A arg) {
        Comment comment = n.getComment().map(s -> (Comment)s.accept(this, arg)).orElse(null);
        n.setComment(comment);
        return n;
    }

    @Override
    public Visitable visit(MarkerAnnotationExpr n, A arg) {
        Name name = (Name)n.getName().accept(this, arg);
        Comment comment = n.getComment().map(s -> (Comment)s.accept(this, arg)).orElse(null);
        if (name == null) {
            return null;
        }
        n.setName(name);
        n.setComment(comment);
        return n;
    }

    @Override
    public Visitable visit(MemberValuePair n, A arg) {
        SimpleName name = (SimpleName)n.getName().accept(this, arg);
        Expression value = (Expression)n.getValue().accept(this, arg);
        Comment comment = n.getComment().map(s -> (Comment)s.accept(this, arg)).orElse(null);
        if (name == null || value == null) {
            return null;
        }
        n.setName(name);
        n.setValue(value);
        n.setComment(comment);
        return n;
    }

    @Override
    public Visitable visit(MethodCallExpr n, A arg) {
        NodeList<Expression> arguments = this.modifyList(n.getArguments(), arg);
        SimpleName name = (SimpleName)n.getName().accept(this, arg);
        Expression scope = n.getScope().map(s -> (Expression)s.accept(this, arg)).orElse(null);
        NodeList typeArguments = this.modifyList(n.getTypeArguments(), arg);
        Comment comment = n.getComment().map(s -> (Comment)s.accept(this, arg)).orElse(null);
        if (name == null) {
            return null;
        }
        n.setArguments((NodeList)arguments);
        n.setName(name);
        n.setScope(scope);
        n.setTypeArguments(typeArguments);
        n.setComment(comment);
        return n;
    }

    @Override
    public Visitable visit(MethodDeclaration n, A arg) {
        NodeList<AnnotationExpr> annotations = this.modifyList(n.getAnnotations(), arg);
        NodeList<Modifier> modifiers = this.modifyList(n.getModifiers(), arg);
        BlockStmt body = n.getBody().map(s -> (BlockStmt)s.accept(this, arg)).orElse(null);
        Type type = (Type)n.getType().accept(this, arg);
        SimpleName name = (SimpleName)n.getName().accept(this, arg);
        NodeList<Parameter> parameters = this.modifyList(n.getParameters(), arg);
        ReceiverParameter receiverParameter = n.getReceiverParameter().map(s -> (ReceiverParameter)s.accept(this, arg)).orElse(null);
        NodeList<ReferenceType> thrownExceptions = this.modifyList(n.getThrownExceptions(), arg);
        NodeList<TypeParameter> typeParameters = this.modifyList(n.getTypeParameters(), arg);
        Comment comment = n.getComment().map(s -> (Comment)s.accept(this, arg)).orElse(null);
        if (type == null || name == null) {
            return null;
        }
        n.setAnnotations((NodeList)annotations);
        n.setModifiers((NodeList)modifiers);
        n.setBody(body);
        n.setType(type);
        n.setName(name);
        n.setParameters((NodeList)parameters);
        n.setReceiverParameter(receiverParameter);
        n.setThrownExceptions((NodeList)thrownExceptions);
        n.setTypeParameters((NodeList)typeParameters);
        n.setComment(comment);
        return n;
    }

    @Override
    public Visitable visit(NameExpr n, A arg) {
        SimpleName name = (SimpleName)n.getName().accept(this, arg);
        Comment comment = n.getComment().map(s -> (Comment)s.accept(this, arg)).orElse(null);
        if (name == null) {
            return null;
        }
        n.setName(name);
        n.setComment(comment);
        return n;
    }

    @Override
    public Visitable visit(NormalAnnotationExpr n, A arg) {
        NodeList<MemberValuePair> pairs = this.modifyList(n.getPairs(), arg);
        Name name = (Name)n.getName().accept(this, arg);
        Comment comment = n.getComment().map(s -> (Comment)s.accept(this, arg)).orElse(null);
        if (name == null) {
            return null;
        }
        n.setPairs(pairs);
        n.setName(name);
        n.setComment(comment);
        return n;
    }

    @Override
    public Visitable visit(NullLiteralExpr n, A arg) {
        Comment comment = n.getComment().map(s -> (Comment)s.accept(this, arg)).orElse(null);
        n.setComment(comment);
        return n;
    }

    @Override
    public Visitable visit(ObjectCreationExpr n, A arg) {
        NodeList<BodyDeclaration<?>> anonymousClassBody = this.modifyList(n.getAnonymousClassBody(), arg);
        NodeList<Expression> arguments = this.modifyList(n.getArguments(), arg);
        Expression scope = n.getScope().map(s -> (Expression)s.accept(this, arg)).orElse(null);
        ClassOrInterfaceType type = (ClassOrInterfaceType)n.getType().accept(this, arg);
        NodeList typeArguments = this.modifyList(n.getTypeArguments(), arg);
        Comment comment = n.getComment().map(s -> (Comment)s.accept(this, arg)).orElse(null);
        if (type == null) {
            return null;
        }
        n.setAnonymousClassBody(anonymousClassBody);
        n.setArguments((NodeList)arguments);
        n.setScope(scope);
        n.setType(type);
        n.setTypeArguments(typeArguments);
        n.setComment(comment);
        return n;
    }

    @Override
    public Visitable visit(PackageDeclaration n, A arg) {
        NodeList<AnnotationExpr> annotations = this.modifyList(n.getAnnotations(), arg);
        Name name = (Name)n.getName().accept(this, arg);
        Comment comment = n.getComment().map(s -> (Comment)s.accept(this, arg)).orElse(null);
        if (name == null) {
            return null;
        }
        n.setAnnotations((NodeList)annotations);
        n.setName(name);
        n.setComment(comment);
        return n;
    }

    @Override
    public Visitable visit(Parameter n, A arg) {
        NodeList<AnnotationExpr> annotations = this.modifyList(n.getAnnotations(), arg);
        NodeList<Modifier> modifiers = this.modifyList(n.getModifiers(), arg);
        SimpleName name = (SimpleName)n.getName().accept(this, arg);
        Type type = (Type)n.getType().accept(this, arg);
        NodeList<AnnotationExpr> varArgsAnnotations = this.modifyList(n.getVarArgsAnnotations(), arg);
        Comment comment = n.getComment().map(s -> (Comment)s.accept(this, arg)).orElse(null);
        if (name == null || type == null) {
            return null;
        }
        n.setAnnotations((NodeList)annotations);
        n.setModifiers((NodeList)modifiers);
        n.setName(name);
        n.setType(type);
        n.setVarArgsAnnotations(varArgsAnnotations);
        n.setComment(comment);
        return n;
    }

    @Override
    public Visitable visit(Name n, A arg) {
        Name qualifier = n.getQualifier().map(s -> (Name)s.accept(this, arg)).orElse(null);
        Comment comment = n.getComment().map(s -> (Comment)s.accept(this, arg)).orElse(null);
        n.setQualifier(qualifier);
        n.setComment(comment);
        return n;
    }

    @Override
    public Visitable visit(PrimitiveType n, A arg) {
        NodeList<AnnotationExpr> annotations = this.modifyList(n.getAnnotations(), arg);
        Comment comment = n.getComment().map(s -> (Comment)s.accept(this, arg)).orElse(null);
        n.setAnnotations((NodeList)annotations);
        n.setComment(comment);
        return n;
    }

    @Override
    public Visitable visit(SimpleName n, A arg) {
        Comment comment = n.getComment().map(s -> (Comment)s.accept(this, arg)).orElse(null);
        n.setComment(comment);
        return n;
    }

    @Override
    public Visitable visit(ArrayType n, A arg) {
        NodeList<AnnotationExpr> annotations = this.modifyList(n.getAnnotations(), arg);
        Type componentType = (Type)n.getComponentType().accept(this, arg);
        Comment comment = n.getComment().map(s -> (Comment)s.accept(this, arg)).orElse(null);
        if (componentType == null) {
            return null;
        }
        n.setAnnotations((NodeList)annotations);
        n.setComponentType(componentType);
        n.setComment(comment);
        return n;
    }

    @Override
    public Visitable visit(ArrayCreationLevel n, A arg) {
        NodeList<AnnotationExpr> annotations = this.modifyList(n.getAnnotations(), arg);
        Expression dimension = n.getDimension().map(s -> (Expression)s.accept(this, arg)).orElse(null);
        Comment comment = n.getComment().map(s -> (Comment)s.accept(this, arg)).orElse(null);
        n.setAnnotations((NodeList)annotations);
        n.setDimension(dimension);
        n.setComment(comment);
        return n;
    }

    @Override
    public Visitable visit(IntersectionType n, A arg) {
        NodeList<AnnotationExpr> annotations = this.modifyList(n.getAnnotations(), arg);
        NodeList<ReferenceType> elements = this.modifyList(n.getElements(), arg);
        Comment comment = n.getComment().map(s -> (Comment)s.accept(this, arg)).orElse(null);
        if (elements.isEmpty()) {
            return null;
        }
        n.setAnnotations((NodeList)annotations);
        n.setElements(elements);
        n.setComment(comment);
        return n;
    }

    @Override
    public Visitable visit(UnionType n, A arg) {
        NodeList<AnnotationExpr> annotations = this.modifyList(n.getAnnotations(), arg);
        NodeList<ReferenceType> elements = this.modifyList(n.getElements(), arg);
        Comment comment = n.getComment().map(s -> (Comment)s.accept(this, arg)).orElse(null);
        if (elements.isEmpty()) {
            return null;
        }
        n.setAnnotations((NodeList)annotations);
        n.setElements(elements);
        n.setComment(comment);
        return n;
    }

    @Override
    public Visitable visit(ReturnStmt n, A arg) {
        Expression expression = n.getExpression().map(s -> (Expression)s.accept(this, arg)).orElse(null);
        Comment comment = n.getComment().map(s -> (Comment)s.accept(this, arg)).orElse(null);
        n.setExpression(expression);
        n.setComment(comment);
        return n;
    }

    @Override
    public Visitable visit(SingleMemberAnnotationExpr n, A arg) {
        Expression memberValue = (Expression)n.getMemberValue().accept(this, arg);
        Name name = (Name)n.getName().accept(this, arg);
        Comment comment = n.getComment().map(s -> (Comment)s.accept(this, arg)).orElse(null);
        if (memberValue == null || name == null) {
            return null;
        }
        n.setMemberValue(memberValue);
        n.setName(name);
        n.setComment(comment);
        return n;
    }

    @Override
    public Visitable visit(StringLiteralExpr n, A arg) {
        Comment comment = n.getComment().map(s -> (Comment)s.accept(this, arg)).orElse(null);
        n.setComment(comment);
        return n;
    }

    @Override
    public Visitable visit(SuperExpr n, A arg) {
        Name typeName = n.getTypeName().map(s -> (Name)s.accept(this, arg)).orElse(null);
        Comment comment = n.getComment().map(s -> (Comment)s.accept(this, arg)).orElse(null);
        n.setTypeName(typeName);
        n.setComment(comment);
        return n;
    }

    @Override
    public Visitable visit(SwitchEntry n, A arg) {
        NodeList<Expression> labels = this.modifyList(n.getLabels(), arg);
        NodeList<Statement> statements = this.modifyList(n.getStatements(), arg);
        Comment comment = n.getComment().map(s -> (Comment)s.accept(this, arg)).orElse(null);
        n.setLabels(labels);
        n.setStatements((NodeList)statements);
        n.setComment(comment);
        return n;
    }

    @Override
    public Visitable visit(SwitchStmt n, A arg) {
        NodeList<SwitchEntry> entries = this.modifyList(n.getEntries(), arg);
        Expression selector = (Expression)n.getSelector().accept(this, arg);
        Comment comment = n.getComment().map(s -> (Comment)s.accept(this, arg)).orElse(null);
        if (selector == null) {
            return null;
        }
        n.setEntries((NodeList)entries);
        n.setSelector(selector);
        n.setComment(comment);
        return n;
    }

    @Override
    public Visitable visit(SynchronizedStmt n, A arg) {
        BlockStmt body = (BlockStmt)n.getBody().accept(this, arg);
        Expression expression = (Expression)n.getExpression().accept(this, arg);
        Comment comment = n.getComment().map(s -> (Comment)s.accept(this, arg)).orElse(null);
        if (body == null || expression == null) {
            return null;
        }
        n.setBody(body);
        n.setExpression(expression);
        n.setComment(comment);
        return n;
    }

    @Override
    public Visitable visit(ThisExpr n, A arg) {
        Name typeName = n.getTypeName().map(s -> (Name)s.accept(this, arg)).orElse(null);
        Comment comment = n.getComment().map(s -> (Comment)s.accept(this, arg)).orElse(null);
        n.setTypeName(typeName);
        n.setComment(comment);
        return n;
    }

    @Override
    public Visitable visit(ThrowStmt n, A arg) {
        Expression expression = (Expression)n.getExpression().accept(this, arg);
        Comment comment = n.getComment().map(s -> (Comment)s.accept(this, arg)).orElse(null);
        if (expression == null) {
            return null;
        }
        n.setExpression(expression);
        n.setComment(comment);
        return n;
    }

    @Override
    public Visitable visit(TryStmt n, A arg) {
        NodeList<CatchClause> catchClauses = this.modifyList(n.getCatchClauses(), arg);
        BlockStmt finallyBlock = n.getFinallyBlock().map(s -> (BlockStmt)s.accept(this, arg)).orElse(null);
        NodeList<Expression> resources = this.modifyList(n.getResources(), arg);
        BlockStmt tryBlock = (BlockStmt)n.getTryBlock().accept(this, arg);
        Comment comment = n.getComment().map(s -> (Comment)s.accept(this, arg)).orElse(null);
        if (tryBlock == null) {
            return null;
        }
        n.setCatchClauses(catchClauses);
        n.setFinallyBlock(finallyBlock);
        n.setResources(resources);
        n.setTryBlock(tryBlock);
        n.setComment(comment);
        return n;
    }

    @Override
    public Visitable visit(LocalClassDeclarationStmt n, A arg) {
        ClassOrInterfaceDeclaration classDeclaration = (ClassOrInterfaceDeclaration)n.getClassDeclaration().accept(this, arg);
        Comment comment = n.getComment().map(s -> (Comment)s.accept(this, arg)).orElse(null);
        if (classDeclaration == null) {
            return null;
        }
        n.setClassDeclaration(classDeclaration);
        n.setComment(comment);
        return n;
    }

    @Override
    public Visitable visit(TypeParameter n, A arg) {
        NodeList<AnnotationExpr> annotations = this.modifyList(n.getAnnotations(), arg);
        SimpleName name = (SimpleName)n.getName().accept(this, arg);
        NodeList<ClassOrInterfaceType> typeBound = this.modifyList(n.getTypeBound(), arg);
        Comment comment = n.getComment().map(s -> (Comment)s.accept(this, arg)).orElse(null);
        if (name == null) {
            return null;
        }
        n.setAnnotations((NodeList)annotations);
        n.setName(name);
        n.setTypeBound(typeBound);
        n.setComment(comment);
        return n;
    }

    @Override
    public Visitable visit(UnaryExpr n, A arg) {
        Expression expression = (Expression)n.getExpression().accept(this, arg);
        Comment comment = n.getComment().map(s -> (Comment)s.accept(this, arg)).orElse(null);
        if (expression == null) {
            return null;
        }
        n.setExpression(expression);
        n.setComment(comment);
        return n;
    }

    @Override
    public Visitable visit(UnknownType n, A arg) {
        NodeList<AnnotationExpr> annotations = this.modifyList(n.getAnnotations(), arg);
        Comment comment = n.getComment().map(s -> (Comment)s.accept(this, arg)).orElse(null);
        n.setAnnotations((NodeList)annotations);
        n.setComment(comment);
        return n;
    }

    @Override
    public Visitable visit(VariableDeclarationExpr n, A arg) {
        NodeList<AnnotationExpr> annotations = this.modifyList(n.getAnnotations(), arg);
        NodeList<Modifier> modifiers = this.modifyList(n.getModifiers(), arg);
        NodeList<VariableDeclarator> variables = this.modifyList(n.getVariables(), arg);
        Comment comment = n.getComment().map(s -> (Comment)s.accept(this, arg)).orElse(null);
        if (variables.isEmpty()) {
            return null;
        }
        n.setAnnotations((NodeList)annotations);
        n.setModifiers((NodeList)modifiers);
        n.setVariables((NodeList)variables);
        n.setComment(comment);
        return n;
    }

    @Override
    public Visitable visit(VariableDeclarator n, A arg) {
        Expression initializer = n.getInitializer().map(s -> (Expression)s.accept(this, arg)).orElse(null);
        SimpleName name = (SimpleName)n.getName().accept(this, arg);
        Type type = (Type)n.getType().accept(this, arg);
        Comment comment = n.getComment().map(s -> (Comment)s.accept(this, arg)).orElse(null);
        if (name == null || type == null) {
            return null;
        }
        n.setInitializer(initializer);
        n.setName(name);
        n.setType(type);
        n.setComment(comment);
        return n;
    }

    @Override
    public Visitable visit(VoidType n, A arg) {
        NodeList<AnnotationExpr> annotations = this.modifyList(n.getAnnotations(), arg);
        Comment comment = n.getComment().map(s -> (Comment)s.accept(this, arg)).orElse(null);
        n.setAnnotations((NodeList)annotations);
        n.setComment(comment);
        return n;
    }

    @Override
    public Visitable visit(WhileStmt n, A arg) {
        Statement body = (Statement)n.getBody().accept(this, arg);
        Expression condition = (Expression)n.getCondition().accept(this, arg);
        Comment comment = n.getComment().map(s -> (Comment)s.accept(this, arg)).orElse(null);
        if (body == null || condition == null) {
            return null;
        }
        n.setBody(body);
        n.setCondition(condition);
        n.setComment(comment);
        return n;
    }

    @Override
    public Visitable visit(WildcardType n, A arg) {
        NodeList<AnnotationExpr> annotations = this.modifyList(n.getAnnotations(), arg);
        ReferenceType extendedType = n.getExtendedType().map(s -> (ReferenceType)s.accept(this, arg)).orElse(null);
        ReferenceType superType = n.getSuperType().map(s -> (ReferenceType)s.accept(this, arg)).orElse(null);
        Comment comment = n.getComment().map(s -> (Comment)s.accept(this, arg)).orElse(null);
        n.setAnnotations((NodeList)annotations);
        n.setExtendedType(extendedType);
        n.setSuperType(superType);
        n.setComment(comment);
        return n;
    }

    @Override
    public Visitable visit(LambdaExpr n, A arg) {
        Statement body = (Statement)n.getBody().accept(this, arg);
        NodeList<Parameter> parameters = this.modifyList(n.getParameters(), arg);
        Comment comment = n.getComment().map(s -> (Comment)s.accept(this, arg)).orElse(null);
        if (body == null) {
            return null;
        }
        n.setBody(body);
        n.setParameters((NodeList)parameters);
        n.setComment(comment);
        return n;
    }

    @Override
    public Visitable visit(MethodReferenceExpr n, A arg) {
        Expression scope = (Expression)n.getScope().accept(this, arg);
        NodeList typeArguments = this.modifyList(n.getTypeArguments(), arg);
        Comment comment = n.getComment().map(s -> (Comment)s.accept(this, arg)).orElse(null);
        if (scope == null) {
            return null;
        }
        n.setScope(scope);
        n.setTypeArguments(typeArguments);
        n.setComment(comment);
        return n;
    }

    @Override
    public Visitable visit(TypeExpr n, A arg) {
        Type type = (Type)n.getType().accept(this, arg);
        Comment comment = n.getComment().map(s -> (Comment)s.accept(this, arg)).orElse(null);
        if (type == null) {
            return null;
        }
        n.setType(type);
        n.setComment(comment);
        return n;
    }

    @Override
    public Visitable visit(NodeList n, A arg) {
        if (n.isEmpty()) {
            return n;
        }
        ArrayList<Pair<Node, Node>> changeList = new ArrayList<Pair<Node, Node>>();
        ArrayList listCopy = new ArrayList(n);
        for (Node node : listCopy) {
            Node newNode = (Node)node.accept(this, arg);
            changeList.add(new Pair<Node, Node>(node, newNode));
        }
        for (Pair pair : changeList) {
            if (pair.b == null) {
                Utils.removeElementByObjectIdentity(n, pair.a);
                continue;
            }
            Utils.replaceElementByObjectIdentity(n, pair.a, pair.b);
        }
        return n;
    }

    @Override
    public Node visit(ImportDeclaration n, A arg) {
        Name name = (Name)n.getName().accept(this, arg);
        Comment comment = n.getComment().map(s -> (Comment)s.accept(this, arg)).orElse(null);
        if (name == null) {
            return null;
        }
        n.setName(name);
        n.setComment(comment);
        return n;
    }

    @Override
    public Visitable visit(BlockComment n, A arg) {
        Comment comment = n.getComment().map(s -> (Comment)s.accept(this, arg)).orElse(null);
        n.setComment(comment);
        return n;
    }

    @Override
    public Visitable visit(LineComment n, A arg) {
        Comment comment = n.getComment().map(s -> (Comment)s.accept(this, arg)).orElse(null);
        n.setComment(comment);
        return n;
    }

    private <N extends Node> NodeList<N> modifyList(NodeList<N> list, A arg) {
        return (NodeList)list.accept(this, arg);
    }

    private <N extends Node> NodeList<N> modifyList(Optional<NodeList<N>> list, A arg) {
        return list.map(ns -> this.modifyList((NodeList)ns, arg)).orElse(null);
    }

    @Override
    public Visitable visit(ModuleDeclaration n, A arg) {
        NodeList<AnnotationExpr> annotations = this.modifyList(n.getAnnotations(), arg);
        NodeList<ModuleDirective> directives = this.modifyList(n.getDirectives(), arg);
        Name name = (Name)n.getName().accept(this, arg);
        Comment comment = n.getComment().map(s -> (Comment)s.accept(this, arg)).orElse(null);
        if (name == null) {
            return null;
        }
        n.setAnnotations((NodeList)annotations);
        n.setDirectives(directives);
        n.setName(name);
        n.setComment(comment);
        return n;
    }

    @Override
    public Visitable visit(ModuleRequiresDirective n, A arg) {
        NodeList<Modifier> modifiers = this.modifyList(n.getModifiers(), arg);
        Name name = (Name)n.getName().accept(this, arg);
        Comment comment = n.getComment().map(s -> (Comment)s.accept(this, arg)).orElse(null);
        if (name == null) {
            return null;
        }
        n.setModifiers((NodeList)modifiers);
        n.setName(name);
        n.setComment(comment);
        return n;
    }

    @Override
    public Visitable visit(ModuleExportsDirective n, A arg) {
        NodeList<Name> moduleNames = this.modifyList(n.getModuleNames(), arg);
        Name name = (Name)n.getName().accept(this, arg);
        Comment comment = n.getComment().map(s -> (Comment)s.accept(this, arg)).orElse(null);
        if (name == null) {
            return null;
        }
        n.setModuleNames(moduleNames);
        n.setName(name);
        n.setComment(comment);
        return n;
    }

    @Override
    public Visitable visit(ModuleProvidesDirective n, A arg) {
        Name name = (Name)n.getName().accept(this, arg);
        NodeList<Name> with = this.modifyList(n.getWith(), arg);
        Comment comment = n.getComment().map(s -> (Comment)s.accept(this, arg)).orElse(null);
        if (name == null) {
            return null;
        }
        n.setName(name);
        n.setWith(with);
        n.setComment(comment);
        return n;
    }

    @Override
    public Visitable visit(ModuleUsesDirective n, A arg) {
        Name name = (Name)n.getName().accept(this, arg);
        Comment comment = n.getComment().map(s -> (Comment)s.accept(this, arg)).orElse(null);
        if (name == null) {
            return null;
        }
        n.setName(name);
        n.setComment(comment);
        return n;
    }

    @Override
    public Visitable visit(ModuleOpensDirective n, A arg) {
        NodeList<Name> moduleNames = this.modifyList(n.getModuleNames(), arg);
        Name name = (Name)n.getName().accept(this, arg);
        Comment comment = n.getComment().map(s -> (Comment)s.accept(this, arg)).orElse(null);
        if (name == null) {
            return null;
        }
        n.setModuleNames(moduleNames);
        n.setName(name);
        n.setComment(comment);
        return n;
    }

    @Override
    public Visitable visit(UnparsableStmt n, A arg) {
        Comment comment = n.getComment().map(s -> (Comment)s.accept(this, arg)).orElse(null);
        n.setComment(comment);
        return n;
    }

    @Override
    public Visitable visit(ReceiverParameter n, A arg) {
        NodeList<AnnotationExpr> annotations = this.modifyList(n.getAnnotations(), arg);
        Name name = (Name)n.getName().accept(this, arg);
        Type type = (Type)n.getType().accept(this, arg);
        Comment comment = n.getComment().map(s -> (Comment)s.accept(this, arg)).orElse(null);
        if (name == null || type == null) {
            return null;
        }
        n.setAnnotations((NodeList)annotations);
        n.setName(name);
        n.setType(type);
        n.setComment(comment);
        return n;
    }

    @Override
    public Visitable visit(VarType n, A arg) {
        NodeList<AnnotationExpr> annotations = this.modifyList(n.getAnnotations(), arg);
        Comment comment = n.getComment().map(s -> (Comment)s.accept(this, arg)).orElse(null);
        n.setAnnotations((NodeList)annotations);
        n.setComment(comment);
        return n;
    }

    @Override
    public Visitable visit(Modifier n, A arg) {
        Comment comment = n.getComment().map(s -> (Comment)s.accept(this, arg)).orElse(null);
        n.setComment(comment);
        return n;
    }

    @Override
    public Visitable visit(SwitchExpr n, A arg) {
        NodeList<SwitchEntry> entries = this.modifyList(n.getEntries(), arg);
        Expression selector = (Expression)n.getSelector().accept(this, arg);
        Comment comment = n.getComment().map(s -> (Comment)s.accept(this, arg)).orElse(null);
        if (selector == null) {
            return null;
        }
        n.setEntries((NodeList)entries);
        n.setSelector(selector);
        n.setComment(comment);
        return n;
    }

    @Override
    public Visitable visit(YieldStmt n, A arg) {
        Expression expression = (Expression)n.getExpression().accept(this, arg);
        Comment comment = n.getComment().map(s -> (Comment)s.accept(this, arg)).orElse(null);
        if (expression == null) {
            return null;
        }
        n.setExpression(expression);
        n.setComment(comment);
        return n;
    }

    @Override
    public Visitable visit(TextBlockLiteralExpr n, A arg) {
        Comment comment = n.getComment().map(s -> (Comment)s.accept(this, arg)).orElse(null);
        n.setComment(comment);
        return n;
    }

    @Override
    public Visitable visit(PatternExpr n, A arg) {
        SimpleName name = (SimpleName)n.getName().accept(this, arg);
        ReferenceType type = (ReferenceType)n.getType().accept(this, arg);
        Comment comment = n.getComment().map(s -> (Comment)s.accept(this, arg)).orElse(null);
        if (name == null || type == null) {
            return null;
        }
        n.setName(name);
        n.setType(type);
        n.setComment(comment);
        return n;
    }
}

