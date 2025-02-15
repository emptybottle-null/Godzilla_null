/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.printer;

import com.github.javaparser.ast.ArrayCreationLevel;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.ImportDeclaration;
import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.Node;
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
import com.github.javaparser.ast.comments.Comment;
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
import com.github.javaparser.ast.observer.ObservableProperty;
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
import com.github.javaparser.metamodel.JavaParserMetaModel;
import com.github.javaparser.printer.SourcePrinter;
import com.github.javaparser.printer.concretesyntaxmodel.CsmConditional;
import com.github.javaparser.printer.concretesyntaxmodel.CsmElement;
import com.github.javaparser.printer.concretesyntaxmodel.CsmMix;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class ConcreteSyntaxModel {
    private static final Map<Class, CsmElement> concreteSyntaxModelByClass = new HashMap<Class, CsmElement>();
    private static Optional<String> initializationError;

    private static CsmElement modifiers() {
        return CsmElement.list(ObservableProperty.MODIFIERS, CsmElement.space(), CsmElement.none(), CsmElement.space());
    }

    private static CsmElement mix(CsmElement ... elements) {
        return new CsmMix(Arrays.asList(elements));
    }

    private static CsmElement memberAnnotations() {
        return CsmElement.list(ObservableProperty.ANNOTATIONS, CsmElement.newline(), CsmElement.none(), CsmElement.newline());
    }

    private static CsmElement annotations() {
        return CsmElement.list(ObservableProperty.ANNOTATIONS, CsmElement.space(), CsmElement.none(), CsmElement.newline());
    }

    private static CsmElement typeParameters() {
        return CsmElement.list(ObservableProperty.TYPE_PARAMETERS, CsmElement.sequence(CsmElement.comma(), CsmElement.space()), CsmElement.token(107), CsmElement.sequence(CsmElement.token(145), CsmElement.space()));
    }

    private static CsmElement typeArguments() {
        return CsmElement.list(ObservableProperty.TYPE_ARGUMENTS, CsmElement.sequence(CsmElement.comma(), CsmElement.space()), CsmElement.token(107), CsmElement.sequence(CsmElement.token(145)));
    }

    private ConcreteSyntaxModel() {
    }

    public static void genericPrettyPrint(Node node, SourcePrinter printer) {
        ConcreteSyntaxModel.forClass(node.getClass()).prettyPrint(node, printer);
    }

    public static String genericPrettyPrint(Node node) {
        SourcePrinter sourcePrinter = new SourcePrinter();
        ConcreteSyntaxModel.forClass(node.getClass()).prettyPrint(node, sourcePrinter);
        return sourcePrinter.toString();
    }

    public static CsmElement forClass(Class<? extends Node> nodeClazz) {
        initializationError.ifPresent(s -> {
            throw new IllegalStateException((String)s);
        });
        if (!concreteSyntaxModelByClass.containsKey(nodeClazz)) {
            throw new UnsupportedOperationException(nodeClazz.getSimpleName());
        }
        return concreteSyntaxModelByClass.get(nodeClazz);
    }

    static {
        concreteSyntaxModelByClass.put(AnnotationDeclaration.class, CsmElement.sequence(CsmElement.comment(), ConcreteSyntaxModel.memberAnnotations(), ConcreteSyntaxModel.modifiers(), CsmElement.token(105), CsmElement.token(39), CsmElement.space(), CsmElement.child(ObservableProperty.NAME), CsmElement.space(), CsmElement.token(98), CsmElement.newline(), CsmElement.indent(), CsmElement.list(ObservableProperty.MEMBERS, CsmElement.newline(), CsmElement.none(), CsmElement.none(), CsmElement.newline()), CsmElement.unindent(), CsmElement.token(99)));
        concreteSyntaxModelByClass.put(AnnotationMemberDeclaration.class, CsmElement.sequence(CsmElement.comment(), ConcreteSyntaxModel.memberAnnotations(), ConcreteSyntaxModel.modifiers(), CsmElement.child(ObservableProperty.TYPE), CsmElement.space(), CsmElement.child(ObservableProperty.NAME), CsmElement.token(96), CsmElement.token(97), CsmElement.conditional(ObservableProperty.DEFAULT_VALUE, CsmConditional.Condition.IS_PRESENT, CsmElement.sequence(CsmElement.space(), CsmElement.token(22), CsmElement.space(), CsmElement.child(ObservableProperty.DEFAULT_VALUE))), CsmElement.semicolon()));
        concreteSyntaxModelByClass.put(ClassOrInterfaceDeclaration.class, CsmElement.sequence(CsmElement.comment(), ConcreteSyntaxModel.memberAnnotations(), ConcreteSyntaxModel.modifiers(), CsmElement.conditional(ObservableProperty.INTERFACE, CsmConditional.Condition.FLAG, CsmElement.token(39), CsmElement.token(19)), CsmElement.space(), CsmElement.child(ObservableProperty.NAME), CsmElement.list(ObservableProperty.TYPE_PARAMETERS, CsmElement.sequence(CsmElement.comma(), CsmElement.space()), CsmElement.string(107), CsmElement.string(145)), CsmElement.list(ObservableProperty.EXTENDED_TYPES, CsmElement.sequence(CsmElement.string(103), CsmElement.space()), CsmElement.sequence(CsmElement.space(), CsmElement.token(27), CsmElement.space()), CsmElement.none()), CsmElement.list(ObservableProperty.IMPLEMENTED_TYPES, CsmElement.sequence(CsmElement.string(103), CsmElement.space()), CsmElement.sequence(CsmElement.space(), CsmElement.token(35), CsmElement.space()), CsmElement.none()), CsmElement.space(), CsmElement.block(CsmElement.sequence(CsmElement.newline(), CsmElement.list(ObservableProperty.MEMBERS, CsmElement.sequence(CsmElement.newline(), CsmElement.newline()), CsmElement.newline(), CsmElement.newline())))));
        concreteSyntaxModelByClass.put(ConstructorDeclaration.class, CsmElement.sequence(CsmElement.comment(), ConcreteSyntaxModel.memberAnnotations(), ConcreteSyntaxModel.modifiers(), ConcreteSyntaxModel.typeParameters(), CsmElement.child(ObservableProperty.NAME), CsmElement.token(96), CsmElement.list(ObservableProperty.PARAMETERS, CsmElement.sequence(CsmElement.comma(), CsmElement.space()), CsmElement.none(), CsmElement.none()), CsmElement.token(97), CsmElement.list(ObservableProperty.THROWN_EXCEPTIONS, CsmElement.sequence(CsmElement.comma(), CsmElement.space()), CsmElement.sequence(CsmElement.space(), CsmElement.token(57), CsmElement.space()), CsmElement.none()), CsmElement.space(), CsmElement.child(ObservableProperty.BODY)));
        concreteSyntaxModelByClass.put(EnumConstantDeclaration.class, CsmElement.sequence(CsmElement.comment(), ConcreteSyntaxModel.memberAnnotations(), CsmElement.child(ObservableProperty.NAME), CsmElement.list(ObservableProperty.ARGUMENTS, CsmElement.sequence(CsmElement.comma(), CsmElement.space()), CsmElement.token(96), CsmElement.token(97)), CsmElement.conditional(ObservableProperty.CLASS_BODY, CsmConditional.Condition.IS_NOT_EMPTY, CsmElement.sequence(CsmElement.space(), CsmElement.token(98), CsmElement.newline(), CsmElement.indent(), CsmElement.newline(), CsmElement.list(ObservableProperty.CLASS_BODY, CsmElement.newline(), CsmElement.newline(), CsmElement.none(), CsmElement.newline()), CsmElement.unindent(), CsmElement.token(99), CsmElement.newline()))));
        concreteSyntaxModelByClass.put(EnumDeclaration.class, CsmElement.sequence(CsmElement.comment(), ConcreteSyntaxModel.annotations(), ConcreteSyntaxModel.modifiers(), CsmElement.token(26), CsmElement.space(), CsmElement.child(ObservableProperty.NAME), CsmElement.list(ObservableProperty.IMPLEMENTED_TYPES, CsmElement.sequence(CsmElement.comma(), CsmElement.space()), CsmElement.sequence(CsmElement.space(), CsmElement.token(35), CsmElement.space()), CsmElement.none()), CsmElement.space(), CsmElement.token(98), CsmElement.newline(), CsmElement.indent(), CsmElement.newline(), CsmElement.list(ObservableProperty.ENTRIES, CsmElement.sequence(CsmElement.comma(), CsmElement.newline()), CsmElement.none(), CsmElement.none()), CsmElement.conditional(ObservableProperty.MEMBERS, CsmConditional.Condition.IS_EMPTY, CsmElement.conditional(ObservableProperty.ENTRIES, CsmConditional.Condition.IS_NOT_EMPTY, CsmElement.newline()), CsmElement.sequence(CsmElement.semicolon(), CsmElement.newline(), CsmElement.newline(), CsmElement.list(ObservableProperty.MEMBERS, CsmElement.newline(), CsmElement.newline(), CsmElement.none(), CsmElement.newline()))), CsmElement.unindent(), CsmElement.token(99)));
        concreteSyntaxModelByClass.put(FieldDeclaration.class, CsmElement.sequence(CsmElement.orphanCommentsBeforeThis(), CsmElement.comment(), ConcreteSyntaxModel.annotations(), ConcreteSyntaxModel.modifiers(), CsmElement.conditional(ObservableProperty.VARIABLES, CsmConditional.Condition.IS_NOT_EMPTY, CsmElement.child(ObservableProperty.MAXIMUM_COMMON_TYPE)), CsmElement.space(), CsmElement.list(ObservableProperty.VARIABLES, CsmElement.sequence(CsmElement.comma(), CsmElement.space())), CsmElement.semicolon()));
        concreteSyntaxModelByClass.put(InitializerDeclaration.class, CsmElement.sequence(CsmElement.comment(), CsmElement.conditional(ObservableProperty.STATIC, CsmConditional.Condition.FLAG, CsmElement.sequence(CsmElement.token(50), CsmElement.space())), CsmElement.child(ObservableProperty.BODY)));
        concreteSyntaxModelByClass.put(MethodDeclaration.class, CsmElement.sequence(CsmElement.orphanCommentsBeforeThis(), CsmElement.comment(), ConcreteSyntaxModel.mix(ConcreteSyntaxModel.memberAnnotations(), ConcreteSyntaxModel.modifiers()), ConcreteSyntaxModel.typeParameters(), CsmElement.child(ObservableProperty.TYPE), CsmElement.space(), CsmElement.child(ObservableProperty.NAME), CsmElement.token(96), CsmElement.conditional(ObservableProperty.RECEIVER_PARAMETER, CsmConditional.Condition.IS_PRESENT, CsmElement.sequence(CsmElement.child(ObservableProperty.RECEIVER_PARAMETER), CsmElement.comma(), CsmElement.space())), CsmElement.list(ObservableProperty.PARAMETERS, CsmElement.sequence(CsmElement.comma(), CsmElement.space()), CsmElement.none(), CsmElement.none()), CsmElement.token(97), CsmElement.list(ObservableProperty.THROWN_EXCEPTIONS, CsmElement.sequence(CsmElement.comma(), CsmElement.space()), CsmElement.sequence(CsmElement.space(), CsmElement.token(57), CsmElement.space()), CsmElement.none()), CsmElement.conditional(ObservableProperty.BODY, CsmConditional.Condition.IS_PRESENT, CsmElement.sequence(CsmElement.space(), CsmElement.child(ObservableProperty.BODY)), CsmElement.semicolon())));
        concreteSyntaxModelByClass.put(Parameter.class, CsmElement.sequence(CsmElement.comment(), CsmElement.list(ObservableProperty.ANNOTATIONS, CsmElement.space(), CsmElement.none(), CsmElement.space()), ConcreteSyntaxModel.modifiers(), CsmElement.child(ObservableProperty.TYPE), CsmElement.conditional(ObservableProperty.VAR_ARGS, CsmConditional.Condition.FLAG, CsmElement.sequence(CsmElement.list(ObservableProperty.VAR_ARGS_ANNOTATIONS, CsmElement.space(), CsmElement.none(), CsmElement.none()), CsmElement.token(141))), CsmElement.space(), CsmElement.child(ObservableProperty.NAME)));
        concreteSyntaxModelByClass.put(ReceiverParameter.class, CsmElement.sequence(CsmElement.comment(), CsmElement.list(ObservableProperty.ANNOTATIONS, CsmElement.space(), CsmElement.none(), CsmElement.space()), CsmElement.child(ObservableProperty.TYPE), CsmElement.space(), CsmElement.child(ObservableProperty.NAME)));
        concreteSyntaxModelByClass.put(VariableDeclarator.class, CsmElement.sequence(CsmElement.comment(), CsmElement.child(ObservableProperty.NAME), CsmElement.conditional(ObservableProperty.INITIALIZER, CsmConditional.Condition.IS_PRESENT, CsmElement.sequence(CsmElement.space(), CsmElement.token(106), CsmElement.space(), CsmElement.child(ObservableProperty.INITIALIZER)))));
        concreteSyntaxModelByClass.put(ArrayAccessExpr.class, CsmElement.sequence(CsmElement.comment(), CsmElement.child(ObservableProperty.NAME), CsmElement.token(100), CsmElement.child(ObservableProperty.INDEX), CsmElement.token(101)));
        concreteSyntaxModelByClass.put(ArrayCreationExpr.class, CsmElement.sequence(CsmElement.comment(), CsmElement.token(42), CsmElement.space(), CsmElement.child(ObservableProperty.ELEMENT_TYPE), CsmElement.list(ObservableProperty.LEVELS), CsmElement.conditional(ObservableProperty.INITIALIZER, CsmConditional.Condition.IS_PRESENT, CsmElement.sequence(CsmElement.space(), CsmElement.child(ObservableProperty.INITIALIZER)))));
        concreteSyntaxModelByClass.put(ArrayInitializerExpr.class, CsmElement.sequence(CsmElement.comment(), CsmElement.token(98), CsmElement.list(ObservableProperty.VALUES, CsmElement.sequence(CsmElement.comma(), CsmElement.space()), CsmElement.space(), CsmElement.space()), CsmElement.orphanCommentsEnding(), CsmElement.token(99)));
        concreteSyntaxModelByClass.put(AssignExpr.class, CsmElement.sequence(CsmElement.comment(), CsmElement.child(ObservableProperty.TARGET), CsmElement.space(), CsmElement.attribute(ObservableProperty.OPERATOR), CsmElement.space(), CsmElement.child(ObservableProperty.VALUE)));
        concreteSyntaxModelByClass.put(BinaryExpr.class, CsmElement.sequence(CsmElement.comment(), CsmElement.child(ObservableProperty.LEFT), CsmElement.space(), CsmElement.attribute(ObservableProperty.OPERATOR), CsmElement.space(), CsmElement.child(ObservableProperty.RIGHT)));
        concreteSyntaxModelByClass.put(BooleanLiteralExpr.class, CsmElement.sequence(CsmElement.comment(), CsmElement.attribute(ObservableProperty.VALUE)));
        concreteSyntaxModelByClass.put(CastExpr.class, CsmElement.sequence(CsmElement.comment(), CsmElement.token(96), CsmElement.child(ObservableProperty.TYPE), CsmElement.token(97), CsmElement.space(), CsmElement.child(ObservableProperty.EXPRESSION)));
        concreteSyntaxModelByClass.put(CharLiteralExpr.class, CsmElement.sequence(CsmElement.comment(), CsmElement.charToken(ObservableProperty.VALUE)));
        concreteSyntaxModelByClass.put(ClassExpr.class, CsmElement.sequence(CsmElement.comment(), CsmElement.child(ObservableProperty.TYPE), CsmElement.token(104), CsmElement.token(19)));
        concreteSyntaxModelByClass.put(ConditionalExpr.class, CsmElement.sequence(CsmElement.comment(), CsmElement.child(ObservableProperty.CONDITION), CsmElement.space(), CsmElement.token(110), CsmElement.space(), CsmElement.child(ObservableProperty.THEN_EXPR), CsmElement.space(), CsmElement.token(111), CsmElement.space(), CsmElement.child(ObservableProperty.ELSE_EXPR)));
        concreteSyntaxModelByClass.put(DoubleLiteralExpr.class, CsmElement.sequence(CsmElement.comment(), CsmElement.attribute(ObservableProperty.VALUE)));
        concreteSyntaxModelByClass.put(EnclosedExpr.class, CsmElement.sequence(CsmElement.comment(), CsmElement.token(96), CsmElement.child(ObservableProperty.INNER), CsmElement.token(97)));
        concreteSyntaxModelByClass.put(FieldAccessExpr.class, CsmElement.sequence(CsmElement.comment(), CsmElement.child(ObservableProperty.SCOPE), CsmElement.token(104), CsmElement.child(ObservableProperty.NAME)));
        concreteSyntaxModelByClass.put(InstanceOfExpr.class, CsmElement.sequence(CsmElement.comment(), CsmElement.child(ObservableProperty.EXPRESSION), CsmElement.space(), CsmElement.token(37), CsmElement.space(), CsmElement.child(ObservableProperty.TYPE)));
        concreteSyntaxModelByClass.put(PatternExpr.class, CsmElement.sequence(CsmElement.child(ObservableProperty.TYPE), CsmElement.space(), CsmElement.child(ObservableProperty.NAME)));
        concreteSyntaxModelByClass.put(IntegerLiteralExpr.class, CsmElement.sequence(CsmElement.comment(), CsmElement.attribute(ObservableProperty.VALUE)));
        concreteSyntaxModelByClass.put(LambdaExpr.class, CsmElement.sequence(CsmElement.comment(), CsmElement.conditional(ObservableProperty.ENCLOSING_PARAMETERS, CsmConditional.Condition.FLAG, CsmElement.token(96)), CsmElement.list(ObservableProperty.PARAMETERS, CsmElement.sequence(CsmElement.comma(), CsmElement.space())), CsmElement.conditional(ObservableProperty.ENCLOSING_PARAMETERS, CsmConditional.Condition.FLAG, CsmElement.token(97)), CsmElement.space(), CsmElement.token(112), CsmElement.space(), CsmElement.conditional(ObservableProperty.EXPRESSION_BODY, CsmConditional.Condition.IS_PRESENT, CsmElement.child(ObservableProperty.EXPRESSION_BODY), CsmElement.child(ObservableProperty.BODY))));
        concreteSyntaxModelByClass.put(LongLiteralExpr.class, CsmElement.sequence(CsmElement.comment(), CsmElement.attribute(ObservableProperty.VALUE)));
        concreteSyntaxModelByClass.put(MarkerAnnotationExpr.class, CsmElement.sequence(CsmElement.comment(), CsmElement.token(105), CsmElement.attribute(ObservableProperty.NAME)));
        concreteSyntaxModelByClass.put(MemberValuePair.class, CsmElement.sequence(CsmElement.comment(), CsmElement.child(ObservableProperty.NAME), CsmElement.space(), CsmElement.token(106), CsmElement.space(), CsmElement.child(ObservableProperty.VALUE)));
        concreteSyntaxModelByClass.put(MethodCallExpr.class, CsmElement.sequence(CsmElement.comment(), CsmElement.conditional(ObservableProperty.SCOPE, CsmConditional.Condition.IS_PRESENT, CsmElement.sequence(CsmElement.child(ObservableProperty.SCOPE), CsmElement.token(104))), ConcreteSyntaxModel.typeArguments(), CsmElement.child(ObservableProperty.NAME), CsmElement.token(96), CsmElement.list(ObservableProperty.ARGUMENTS, CsmElement.sequence(CsmElement.comma(), CsmElement.space()), CsmElement.none(), CsmElement.none()), CsmElement.token(97)));
        concreteSyntaxModelByClass.put(MethodReferenceExpr.class, CsmElement.sequence(CsmElement.comment(), CsmElement.child(ObservableProperty.SCOPE), CsmElement.token(142), ConcreteSyntaxModel.typeArguments(), CsmElement.attribute(ObservableProperty.IDENTIFIER)));
        concreteSyntaxModelByClass.put(Modifier.class, CsmElement.attribute(ObservableProperty.KEYWORD));
        concreteSyntaxModelByClass.put(Name.class, CsmElement.sequence(CsmElement.comment(), CsmElement.conditional(ObservableProperty.QUALIFIER, CsmConditional.Condition.IS_PRESENT, CsmElement.sequence(CsmElement.child(ObservableProperty.QUALIFIER), CsmElement.token(104))), CsmElement.attribute(ObservableProperty.IDENTIFIER), CsmElement.orphanCommentsEnding()));
        concreteSyntaxModelByClass.put(NameExpr.class, CsmElement.sequence(CsmElement.comment(), CsmElement.child(ObservableProperty.NAME), CsmElement.orphanCommentsEnding()));
        concreteSyntaxModelByClass.put(NormalAnnotationExpr.class, CsmElement.sequence(CsmElement.comment(), CsmElement.token(105), CsmElement.child(ObservableProperty.NAME), CsmElement.token(96), CsmElement.list(ObservableProperty.PAIRS, CsmElement.sequence(CsmElement.comma(), CsmElement.space())), CsmElement.token(97)));
        concreteSyntaxModelByClass.put(NullLiteralExpr.class, CsmElement.sequence(CsmElement.comment(), CsmElement.token(43)));
        concreteSyntaxModelByClass.put(ObjectCreationExpr.class, CsmElement.sequence(CsmElement.comment(), CsmElement.conditional(ObservableProperty.SCOPE, CsmConditional.Condition.IS_PRESENT, CsmElement.sequence(CsmElement.child(ObservableProperty.SCOPE), CsmElement.token(104))), CsmElement.token(42), CsmElement.space(), CsmElement.list(ObservableProperty.TYPE_ARGUMENTS, CsmElement.sequence(CsmElement.comma(), CsmElement.space()), CsmElement.token(107), CsmElement.token(145)), CsmElement.conditional(ObservableProperty.TYPE_ARGUMENTS, CsmConditional.Condition.IS_NOT_EMPTY, CsmElement.space()), CsmElement.child(ObservableProperty.TYPE), CsmElement.token(96), CsmElement.list(ObservableProperty.ARGUMENTS, CsmElement.sequence(CsmElement.comma(), CsmElement.space()), CsmElement.none(), CsmElement.none()), CsmElement.token(97), CsmElement.conditional(ObservableProperty.ANONYMOUS_CLASS_BODY, CsmConditional.Condition.IS_PRESENT, CsmElement.sequence(CsmElement.space(), CsmElement.token(98), CsmElement.newline(), CsmElement.indent(), CsmElement.list(ObservableProperty.ANONYMOUS_CLASS_BODY, CsmElement.newline(), CsmElement.newline(), CsmElement.newline(), CsmElement.newline()), CsmElement.unindent(), CsmElement.token(99)))));
        concreteSyntaxModelByClass.put(SimpleName.class, CsmElement.attribute(ObservableProperty.IDENTIFIER));
        concreteSyntaxModelByClass.put(SingleMemberAnnotationExpr.class, CsmElement.sequence(CsmElement.comment(), CsmElement.token(105), CsmElement.child(ObservableProperty.NAME), CsmElement.token(96), CsmElement.child(ObservableProperty.MEMBER_VALUE), CsmElement.token(97)));
        concreteSyntaxModelByClass.put(StringLiteralExpr.class, CsmElement.sequence(CsmElement.comment(), CsmElement.stringToken(ObservableProperty.VALUE)));
        concreteSyntaxModelByClass.put(TextBlockLiteralExpr.class, CsmElement.sequence(CsmElement.comment(), CsmElement.textBlockToken(ObservableProperty.VALUE)));
        concreteSyntaxModelByClass.put(SuperExpr.class, CsmElement.sequence(CsmElement.comment(), CsmElement.conditional(ObservableProperty.TYPE_NAME, CsmConditional.Condition.IS_PRESENT, CsmElement.sequence(CsmElement.child(ObservableProperty.TYPE_NAME), CsmElement.token(104))), CsmElement.token(52)));
        concreteSyntaxModelByClass.put(ThisExpr.class, CsmElement.sequence(CsmElement.comment(), CsmElement.conditional(ObservableProperty.TYPE_NAME, CsmConditional.Condition.IS_PRESENT, CsmElement.sequence(CsmElement.child(ObservableProperty.TYPE_NAME), CsmElement.token(104))), CsmElement.token(55)));
        concreteSyntaxModelByClass.put(TypeExpr.class, CsmElement.sequence(CsmElement.comment(), CsmElement.child(ObservableProperty.TYPE)));
        concreteSyntaxModelByClass.put(UnaryExpr.class, CsmElement.sequence(CsmElement.conditional(ObservableProperty.PREFIX, CsmConditional.Condition.FLAG, CsmElement.attribute(ObservableProperty.OPERATOR)), CsmElement.child(ObservableProperty.EXPRESSION), CsmElement.conditional(ObservableProperty.POSTFIX, CsmConditional.Condition.FLAG, CsmElement.attribute(ObservableProperty.OPERATOR))));
        concreteSyntaxModelByClass.put(VariableDeclarationExpr.class, CsmElement.sequence(CsmElement.comment(), CsmElement.list(ObservableProperty.ANNOTATIONS, CsmElement.space(), CsmElement.none(), CsmElement.space()), ConcreteSyntaxModel.modifiers(), CsmElement.child(ObservableProperty.MAXIMUM_COMMON_TYPE), CsmElement.space(), CsmElement.list(ObservableProperty.VARIABLES, CsmElement.sequence(CsmElement.comma(), CsmElement.space()))));
        concreteSyntaxModelByClass.put(AssertStmt.class, CsmElement.sequence(CsmElement.comment(), CsmElement.token(12), CsmElement.space(), CsmElement.child(ObservableProperty.CHECK), CsmElement.conditional(ObservableProperty.MESSAGE, CsmConditional.Condition.IS_PRESENT, CsmElement.sequence(CsmElement.space(), CsmElement.token(111), CsmElement.space(), CsmElement.child(ObservableProperty.MESSAGE))), CsmElement.semicolon()));
        concreteSyntaxModelByClass.put(BlockStmt.class, CsmElement.sequence(CsmElement.orphanCommentsBeforeThis(), CsmElement.comment(), CsmElement.token(98), CsmElement.newline(), CsmElement.list(ObservableProperty.STATEMENTS, CsmElement.newline(), CsmElement.indent(), CsmElement.sequence(CsmElement.newline(), CsmElement.unindent())), CsmElement.orphanCommentsEnding(), CsmElement.token(99)));
        concreteSyntaxModelByClass.put(BreakStmt.class, CsmElement.sequence(CsmElement.comment(), CsmElement.token(14), CsmElement.conditional(ObservableProperty.LABEL, CsmConditional.Condition.IS_PRESENT, CsmElement.sequence(CsmElement.space(), CsmElement.child(ObservableProperty.LABEL))), CsmElement.semicolon()));
        concreteSyntaxModelByClass.put(CatchClause.class, CsmElement.sequence(CsmElement.comment(), CsmElement.space(), CsmElement.token(17), CsmElement.space(), CsmElement.token(96), CsmElement.child(ObservableProperty.PARAMETER), CsmElement.token(97), CsmElement.space(), CsmElement.child(ObservableProperty.BODY)));
        concreteSyntaxModelByClass.put(ContinueStmt.class, CsmElement.sequence(CsmElement.comment(), CsmElement.token(21), CsmElement.conditional(ObservableProperty.LABEL, CsmConditional.Condition.IS_PRESENT, CsmElement.sequence(CsmElement.space(), CsmElement.child(ObservableProperty.LABEL))), CsmElement.semicolon()));
        concreteSyntaxModelByClass.put(DoStmt.class, CsmElement.sequence(CsmElement.comment(), CsmElement.token(23), CsmElement.space(), CsmElement.child(ObservableProperty.BODY), CsmElement.space(), CsmElement.token(63), CsmElement.space(), CsmElement.token(96), CsmElement.child(ObservableProperty.CONDITION), CsmElement.token(97), CsmElement.semicolon()));
        concreteSyntaxModelByClass.put(EmptyStmt.class, CsmElement.sequence(CsmElement.comment(), CsmElement.token(102)));
        concreteSyntaxModelByClass.put(UnparsableStmt.class, CsmElement.sequence(CsmElement.comment(), CsmElement.token(102)));
        concreteSyntaxModelByClass.put(ExplicitConstructorInvocationStmt.class, CsmElement.sequence(CsmElement.comment(), CsmElement.conditional(ObservableProperty.THIS, CsmConditional.Condition.FLAG, CsmElement.sequence(ConcreteSyntaxModel.typeArguments(), CsmElement.token(55)), CsmElement.sequence(CsmElement.conditional(ObservableProperty.EXPRESSION, CsmConditional.Condition.IS_PRESENT, CsmElement.sequence(CsmElement.child(ObservableProperty.EXPRESSION), CsmElement.token(104))), ConcreteSyntaxModel.typeArguments(), CsmElement.token(52))), CsmElement.token(96), CsmElement.list(ObservableProperty.ARGUMENTS, CsmElement.sequence(CsmElement.comma(), CsmElement.space())), CsmElement.token(97), CsmElement.semicolon()));
        concreteSyntaxModelByClass.put(ExpressionStmt.class, CsmElement.sequence(CsmElement.orphanCommentsBeforeThis(), CsmElement.comment(), CsmElement.child(ObservableProperty.EXPRESSION), CsmElement.semicolon()));
        concreteSyntaxModelByClass.put(ForEachStmt.class, CsmElement.sequence(CsmElement.comment(), CsmElement.token(32), CsmElement.space(), CsmElement.token(96), CsmElement.child(ObservableProperty.VARIABLE), CsmElement.space(), CsmElement.token(111), CsmElement.space(), CsmElement.child(ObservableProperty.ITERABLE), CsmElement.token(97), CsmElement.space(), CsmElement.child(ObservableProperty.BODY)));
        concreteSyntaxModelByClass.put(ForStmt.class, CsmElement.sequence(CsmElement.comment(), CsmElement.token(32), CsmElement.space(), CsmElement.token(96), CsmElement.list(ObservableProperty.INITIALIZATION, CsmElement.sequence(CsmElement.comma(), CsmElement.space())), CsmElement.semicolon(), CsmElement.space(), CsmElement.child(ObservableProperty.COMPARE), CsmElement.semicolon(), CsmElement.space(), CsmElement.list(ObservableProperty.UPDATE, CsmElement.sequence(CsmElement.comma(), CsmElement.space())), CsmElement.token(97), CsmElement.space(), CsmElement.child(ObservableProperty.BODY)));
        concreteSyntaxModelByClass.put(IfStmt.class, CsmElement.sequence(CsmElement.comment(), CsmElement.token(34), CsmElement.space(), CsmElement.token(96), CsmElement.child(ObservableProperty.CONDITION), CsmElement.token(97), CsmElement.conditional(ObservableProperty.THEN_BLOCK, CsmConditional.Condition.FLAG, CsmElement.sequence(CsmElement.space(), CsmElement.child(ObservableProperty.THEN_STMT), CsmElement.conditional(ObservableProperty.ELSE_STMT, CsmConditional.Condition.IS_PRESENT, CsmElement.space())), CsmElement.sequence(CsmElement.newline(), CsmElement.indent(), CsmElement.child(ObservableProperty.THEN_STMT), CsmElement.conditional(ObservableProperty.ELSE_STMT, CsmConditional.Condition.IS_PRESENT, CsmElement.newline()), CsmElement.unindent())), CsmElement.conditional(ObservableProperty.ELSE_STMT, CsmConditional.Condition.IS_PRESENT, CsmElement.sequence(CsmElement.token(25), CsmElement.conditional(Arrays.asList(ObservableProperty.ELSE_BLOCK, ObservableProperty.CASCADING_IF_STMT), CsmConditional.Condition.FLAG, CsmElement.sequence(CsmElement.space(), CsmElement.child(ObservableProperty.ELSE_STMT)), CsmElement.sequence(CsmElement.newline(), CsmElement.indent(), CsmElement.child(ObservableProperty.ELSE_STMT), CsmElement.unindent()))))));
        concreteSyntaxModelByClass.put(LabeledStmt.class, CsmElement.sequence(CsmElement.comment(), CsmElement.child(ObservableProperty.LABEL), CsmElement.token(111), CsmElement.space(), CsmElement.child(ObservableProperty.STATEMENT)));
        concreteSyntaxModelByClass.put(LocalClassDeclarationStmt.class, CsmElement.sequence(CsmElement.comment(), CsmElement.child(ObservableProperty.CLASS_DECLARATION)));
        concreteSyntaxModelByClass.put(ReturnStmt.class, CsmElement.sequence(CsmElement.comment(), CsmElement.token(48), CsmElement.conditional(ObservableProperty.EXPRESSION, CsmConditional.Condition.IS_PRESENT, CsmElement.sequence(CsmElement.space(), CsmElement.child(ObservableProperty.EXPRESSION))), CsmElement.semicolon()));
        concreteSyntaxModelByClass.put(YieldStmt.class, CsmElement.sequence(CsmElement.comment(), CsmElement.token(64), CsmElement.conditional(ObservableProperty.EXPRESSION, CsmConditional.Condition.IS_PRESENT, CsmElement.sequence(CsmElement.space(), CsmElement.child(ObservableProperty.EXPRESSION))), CsmElement.semicolon()));
        concreteSyntaxModelByClass.put(SwitchEntry.class, CsmElement.sequence(CsmElement.comment(), CsmElement.conditional(ObservableProperty.LABELS, CsmConditional.Condition.IS_NOT_EMPTY, CsmElement.sequence(CsmElement.token(16), CsmElement.space(), CsmElement.list(ObservableProperty.LABELS), CsmElement.token(111)), CsmElement.sequence(CsmElement.token(22), CsmElement.token(111))), CsmElement.newline(), CsmElement.indent(), CsmElement.list(ObservableProperty.STATEMENTS, CsmElement.newline(), CsmElement.none(), CsmElement.newline()), CsmElement.unindent()));
        concreteSyntaxModelByClass.put(SwitchStmt.class, CsmElement.sequence(CsmElement.comment(), CsmElement.token(53), CsmElement.token(96), CsmElement.child(ObservableProperty.SELECTOR), CsmElement.token(97), CsmElement.space(), CsmElement.token(98), CsmElement.newline(), CsmElement.list(ObservableProperty.ENTRIES, CsmElement.none(), CsmElement.indent(), CsmElement.unindent()), CsmElement.token(99)));
        concreteSyntaxModelByClass.put(SwitchExpr.class, CsmElement.sequence(CsmElement.comment(), CsmElement.token(53), CsmElement.token(96), CsmElement.child(ObservableProperty.SELECTOR), CsmElement.token(97), CsmElement.space(), CsmElement.token(98), CsmElement.newline(), CsmElement.list(ObservableProperty.ENTRIES, CsmElement.none(), CsmElement.indent(), CsmElement.unindent()), CsmElement.token(99)));
        concreteSyntaxModelByClass.put(SynchronizedStmt.class, CsmElement.sequence(CsmElement.comment(), CsmElement.token(54), CsmElement.space(), CsmElement.token(96), CsmElement.child(ObservableProperty.EXPRESSION), CsmElement.token(97), CsmElement.space(), CsmElement.child(ObservableProperty.BODY)));
        concreteSyntaxModelByClass.put(ThrowStmt.class, CsmElement.sequence(CsmElement.comment(), CsmElement.token(56), CsmElement.space(), CsmElement.child(ObservableProperty.EXPRESSION), CsmElement.semicolon()));
        concreteSyntaxModelByClass.put(TryStmt.class, CsmElement.sequence(CsmElement.comment(), CsmElement.token(60), CsmElement.space(), CsmElement.conditional(ObservableProperty.RESOURCES, CsmConditional.Condition.IS_NOT_EMPTY, CsmElement.sequence(CsmElement.token(96), CsmElement.list(ObservableProperty.RESOURCES, CsmElement.sequence(CsmElement.semicolon(), CsmElement.newline()), CsmElement.indent(), CsmElement.unindent()), CsmElement.token(97), CsmElement.space())), CsmElement.child(ObservableProperty.TRY_BLOCK), CsmElement.list(ObservableProperty.CATCH_CLAUSES), CsmElement.conditional(ObservableProperty.FINALLY_BLOCK, CsmConditional.Condition.IS_PRESENT, CsmElement.sequence(CsmElement.space(), CsmElement.token(30), CsmElement.space(), CsmElement.child(ObservableProperty.FINALLY_BLOCK)))));
        concreteSyntaxModelByClass.put(WhileStmt.class, CsmElement.sequence(CsmElement.comment(), CsmElement.token(63), CsmElement.space(), CsmElement.token(96), CsmElement.child(ObservableProperty.CONDITION), CsmElement.token(97), CsmElement.space(), CsmElement.child(ObservableProperty.BODY)));
        concreteSyntaxModelByClass.put(ArrayType.class, CsmElement.sequence(CsmElement.child(ObservableProperty.COMPONENT_TYPE), CsmElement.list(ObservableProperty.ANNOTATIONS), CsmElement.string(100), CsmElement.string(101)));
        concreteSyntaxModelByClass.put(ClassOrInterfaceType.class, CsmElement.sequence(CsmElement.comment(), CsmElement.conditional(ObservableProperty.SCOPE, CsmConditional.Condition.IS_PRESENT, CsmElement.sequence(CsmElement.child(ObservableProperty.SCOPE), CsmElement.string(104))), CsmElement.list(ObservableProperty.ANNOTATIONS, CsmElement.space(), CsmElement.none(), CsmElement.space()), CsmElement.child(ObservableProperty.NAME), CsmElement.conditional(ObservableProperty.USING_DIAMOND_OPERATOR, CsmConditional.Condition.FLAG, CsmElement.sequence(CsmElement.string(107), CsmElement.string(145)), CsmElement.list(ObservableProperty.TYPE_ARGUMENTS, CsmElement.sequence(CsmElement.comma(), CsmElement.space()), CsmElement.string(107), CsmElement.string(145)))));
        concreteSyntaxModelByClass.put(IntersectionType.class, CsmElement.sequence(CsmElement.comment(), ConcreteSyntaxModel.annotations(), CsmElement.list(ObservableProperty.ELEMENTS, CsmElement.sequence(CsmElement.space(), CsmElement.token(125), CsmElement.space()))));
        concreteSyntaxModelByClass.put(PrimitiveType.class, CsmElement.sequence(CsmElement.comment(), CsmElement.list(ObservableProperty.ANNOTATIONS), CsmElement.attribute(ObservableProperty.TYPE)));
        concreteSyntaxModelByClass.put(TypeParameter.class, CsmElement.sequence(CsmElement.comment(), ConcreteSyntaxModel.annotations(), CsmElement.child(ObservableProperty.NAME), CsmElement.list(ObservableProperty.TYPE_BOUND, CsmElement.sequence(CsmElement.space(), CsmElement.token(125), CsmElement.space()), CsmElement.sequence(CsmElement.space(), CsmElement.token(27), CsmElement.space()), CsmElement.none())));
        concreteSyntaxModelByClass.put(UnionType.class, CsmElement.sequence(CsmElement.comment(), ConcreteSyntaxModel.annotations(), CsmElement.list(ObservableProperty.ELEMENTS, CsmElement.sequence(CsmElement.space(), CsmElement.token(126), CsmElement.space()))));
        concreteSyntaxModelByClass.put(UnknownType.class, CsmElement.none());
        concreteSyntaxModelByClass.put(VoidType.class, CsmElement.sequence(CsmElement.comment(), ConcreteSyntaxModel.annotations(), CsmElement.token(61)));
        concreteSyntaxModelByClass.put(VarType.class, CsmElement.sequence(CsmElement.comment(), ConcreteSyntaxModel.annotations(), CsmElement.string(93, "var")));
        concreteSyntaxModelByClass.put(WildcardType.class, CsmElement.sequence(CsmElement.comment(), ConcreteSyntaxModel.annotations(), CsmElement.token(110), CsmElement.conditional(ObservableProperty.EXTENDED_TYPE, CsmConditional.Condition.IS_PRESENT, CsmElement.sequence(CsmElement.space(), CsmElement.token(27), CsmElement.space(), CsmElement.child(ObservableProperty.EXTENDED_TYPE))), CsmElement.conditional(ObservableProperty.SUPER_TYPE, CsmConditional.Condition.IS_PRESENT, CsmElement.sequence(CsmElement.space(), CsmElement.token(52), CsmElement.space(), CsmElement.child(ObservableProperty.SUPER_TYPE)))));
        concreteSyntaxModelByClass.put(ArrayCreationLevel.class, CsmElement.sequence(ConcreteSyntaxModel.annotations(), CsmElement.token(100), CsmElement.child(ObservableProperty.DIMENSION), CsmElement.token(101)));
        concreteSyntaxModelByClass.put(CompilationUnit.class, CsmElement.sequence(CsmElement.comment(), CsmElement.child(ObservableProperty.PACKAGE_DECLARATION), CsmElement.list(ObservableProperty.IMPORTS, CsmElement.newline(), CsmElement.none(), CsmElement.sequence(CsmElement.newline(), CsmElement.newline())), CsmElement.list(ObservableProperty.TYPES, CsmElement.newline(), CsmElement.newline(), CsmElement.none(), CsmElement.newline()), CsmElement.child(ObservableProperty.MODULE), CsmElement.orphanCommentsEnding()));
        concreteSyntaxModelByClass.put(ImportDeclaration.class, CsmElement.sequence(CsmElement.comment(), CsmElement.token(36), CsmElement.space(), CsmElement.conditional(ObservableProperty.STATIC, CsmConditional.Condition.FLAG, CsmElement.sequence(CsmElement.token(50), CsmElement.space())), CsmElement.child(ObservableProperty.NAME), CsmElement.conditional(ObservableProperty.ASTERISK, CsmConditional.Condition.FLAG, CsmElement.sequence(CsmElement.token(104), CsmElement.token(123))), CsmElement.semicolon(), CsmElement.orphanCommentsEnding()));
        concreteSyntaxModelByClass.put(PackageDeclaration.class, CsmElement.sequence(CsmElement.comment(), ConcreteSyntaxModel.memberAnnotations(), CsmElement.token(44), CsmElement.space(), CsmElement.child(ObservableProperty.NAME), CsmElement.semicolon(), CsmElement.newline(), CsmElement.newline(), CsmElement.orphanCommentsEnding()));
        concreteSyntaxModelByClass.put(ModuleDeclaration.class, CsmElement.sequence(ConcreteSyntaxModel.memberAnnotations(), CsmElement.conditional(ObservableProperty.OPEN, CsmConditional.Condition.FLAG, CsmElement.sequence(CsmElement.token(68), CsmElement.space())), CsmElement.token(71), CsmElement.space(), CsmElement.child(ObservableProperty.NAME), CsmElement.space(), CsmElement.token(98), CsmElement.newline(), CsmElement.indent(), CsmElement.list(ObservableProperty.DIRECTIVES), CsmElement.unindent(), CsmElement.token(99), CsmElement.newline()));
        concreteSyntaxModelByClass.put(ModuleExportsDirective.class, CsmElement.sequence(CsmElement.token(72), CsmElement.space(), CsmElement.child(ObservableProperty.NAME), CsmElement.list(ObservableProperty.MODULE_NAMES, CsmElement.sequence(CsmElement.comma(), CsmElement.space()), CsmElement.sequence(CsmElement.space(), CsmElement.token(66), CsmElement.space()), CsmElement.none()), CsmElement.semicolon(), CsmElement.newline()));
        concreteSyntaxModelByClass.put(ModuleOpensDirective.class, CsmElement.sequence(CsmElement.token(69), CsmElement.space(), CsmElement.child(ObservableProperty.NAME), CsmElement.list(ObservableProperty.MODULE_NAMES, CsmElement.sequence(CsmElement.comma(), CsmElement.space()), CsmElement.sequence(CsmElement.space(), CsmElement.token(66), CsmElement.space()), CsmElement.none()), CsmElement.semicolon(), CsmElement.newline()));
        concreteSyntaxModelByClass.put(ModuleProvidesDirective.class, CsmElement.sequence(CsmElement.token(73), CsmElement.space(), CsmElement.child(ObservableProperty.NAME), CsmElement.list(ObservableProperty.WITH, CsmElement.sequence(CsmElement.comma(), CsmElement.space()), CsmElement.sequence(CsmElement.space(), CsmElement.token(67), CsmElement.space()), CsmElement.none()), CsmElement.semicolon(), CsmElement.newline()));
        concreteSyntaxModelByClass.put(ModuleRequiresDirective.class, CsmElement.sequence(CsmElement.token(65), CsmElement.space(), ConcreteSyntaxModel.modifiers(), CsmElement.child(ObservableProperty.NAME), CsmElement.semicolon(), CsmElement.newline()));
        concreteSyntaxModelByClass.put(ModuleUsesDirective.class, CsmElement.sequence(CsmElement.token(70), CsmElement.space(), CsmElement.child(ObservableProperty.NAME), CsmElement.semicolon(), CsmElement.newline()));
        List unsupportedNodeClassNames = JavaParserMetaModel.getNodeMetaModels().stream().filter(c -> !c.isAbstract() && !Comment.class.isAssignableFrom(c.getType()) && !concreteSyntaxModelByClass.containsKey(c.getType())).map(nm -> nm.getType().getSimpleName()).collect(Collectors.toList());
        initializationError = unsupportedNodeClassNames.isEmpty() ? Optional.empty() : Optional.of("The " + ConcreteSyntaxModel.class.getSimpleName() + " should include support for these classes: " + String.join((CharSequence)", ", unsupportedNodeClassNames));
    }
}

