/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.ast.expr;

import com.github.javaparser.TokenRange;
import com.github.javaparser.ast.AllFieldsConstructor;
import com.github.javaparser.ast.Node;
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
import com.github.javaparser.ast.expr.FieldAccessExpr;
import com.github.javaparser.ast.expr.InstanceOfExpr;
import com.github.javaparser.ast.expr.IntegerLiteralExpr;
import com.github.javaparser.ast.expr.LambdaExpr;
import com.github.javaparser.ast.expr.LiteralExpr;
import com.github.javaparser.ast.expr.LiteralStringValueExpr;
import com.github.javaparser.ast.expr.LongLiteralExpr;
import com.github.javaparser.ast.expr.MarkerAnnotationExpr;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.expr.MethodReferenceExpr;
import com.github.javaparser.ast.expr.NameExpr;
import com.github.javaparser.ast.expr.NormalAnnotationExpr;
import com.github.javaparser.ast.expr.NullLiteralExpr;
import com.github.javaparser.ast.expr.ObjectCreationExpr;
import com.github.javaparser.ast.expr.PatternExpr;
import com.github.javaparser.ast.expr.SingleMemberAnnotationExpr;
import com.github.javaparser.ast.expr.StringLiteralExpr;
import com.github.javaparser.ast.expr.SuperExpr;
import com.github.javaparser.ast.expr.SwitchExpr;
import com.github.javaparser.ast.expr.TextBlockLiteralExpr;
import com.github.javaparser.ast.expr.ThisExpr;
import com.github.javaparser.ast.expr.TypeExpr;
import com.github.javaparser.ast.expr.UnaryExpr;
import com.github.javaparser.ast.expr.VariableDeclarationExpr;
import com.github.javaparser.ast.nodeTypes.NodeWithOptionalScope;
import com.github.javaparser.ast.nodeTypes.NodeWithTypeArguments;
import com.github.javaparser.ast.visitor.CloneVisitor;
import com.github.javaparser.metamodel.ExpressionMetaModel;
import com.github.javaparser.metamodel.JavaParserMetaModel;
import com.github.javaparser.resolution.types.ResolvedType;
import com.github.javaparser.utils.CodeGenerationUtils;
import java.util.Optional;
import java.util.function.Consumer;

public abstract class Expression
extends Node {
    @AllFieldsConstructor
    public Expression() {
        this(null);
    }

    public Expression(TokenRange tokenRange) {
        super(tokenRange);
        this.customInitialization();
    }

    @Override
    public boolean remove(Node node) {
        if (node == null) {
            return false;
        }
        return super.remove(node);
    }

    @Override
    public Expression clone() {
        return (Expression)this.accept(new CloneVisitor(), null);
    }

    @Override
    public ExpressionMetaModel getMetaModel() {
        return JavaParserMetaModel.expressionMetaModel;
    }

    @Override
    public boolean replace(Node node, Node replacementNode) {
        if (node == null) {
            return false;
        }
        return super.replace(node, replacementNode);
    }

    public boolean isAnnotationExpr() {
        return false;
    }

    public AnnotationExpr asAnnotationExpr() {
        throw new IllegalStateException(CodeGenerationUtils.f("%s is not AnnotationExpr, it is %s", this, this.getClass().getSimpleName()));
    }

    public boolean isArrayAccessExpr() {
        return false;
    }

    public ArrayAccessExpr asArrayAccessExpr() {
        throw new IllegalStateException(CodeGenerationUtils.f("%s is not ArrayAccessExpr, it is %s", this, this.getClass().getSimpleName()));
    }

    public boolean isArrayCreationExpr() {
        return false;
    }

    public ArrayCreationExpr asArrayCreationExpr() {
        throw new IllegalStateException(CodeGenerationUtils.f("%s is not ArrayCreationExpr, it is %s", this, this.getClass().getSimpleName()));
    }

    public boolean isArrayInitializerExpr() {
        return false;
    }

    public ArrayInitializerExpr asArrayInitializerExpr() {
        throw new IllegalStateException(CodeGenerationUtils.f("%s is not ArrayInitializerExpr, it is %s", this, this.getClass().getSimpleName()));
    }

    public boolean isAssignExpr() {
        return false;
    }

    public AssignExpr asAssignExpr() {
        throw new IllegalStateException(CodeGenerationUtils.f("%s is not AssignExpr, it is %s", this, this.getClass().getSimpleName()));
    }

    public boolean isBinaryExpr() {
        return false;
    }

    public BinaryExpr asBinaryExpr() {
        throw new IllegalStateException(CodeGenerationUtils.f("%s is not BinaryExpr, it is %s", this, this.getClass().getSimpleName()));
    }

    public boolean isBooleanLiteralExpr() {
        return false;
    }

    public BooleanLiteralExpr asBooleanLiteralExpr() {
        throw new IllegalStateException(CodeGenerationUtils.f("%s is not BooleanLiteralExpr, it is %s", this, this.getClass().getSimpleName()));
    }

    public boolean isCastExpr() {
        return false;
    }

    public CastExpr asCastExpr() {
        throw new IllegalStateException(CodeGenerationUtils.f("%s is not CastExpr, it is %s", this, this.getClass().getSimpleName()));
    }

    public boolean isCharLiteralExpr() {
        return false;
    }

    public CharLiteralExpr asCharLiteralExpr() {
        throw new IllegalStateException(CodeGenerationUtils.f("%s is not CharLiteralExpr, it is %s", this, this.getClass().getSimpleName()));
    }

    public boolean isClassExpr() {
        return false;
    }

    public ClassExpr asClassExpr() {
        throw new IllegalStateException(CodeGenerationUtils.f("%s is not ClassExpr, it is %s", this, this.getClass().getSimpleName()));
    }

    public boolean isConditionalExpr() {
        return false;
    }

    public ConditionalExpr asConditionalExpr() {
        throw new IllegalStateException(CodeGenerationUtils.f("%s is not ConditionalExpr, it is %s", this, this.getClass().getSimpleName()));
    }

    public boolean isDoubleLiteralExpr() {
        return false;
    }

    public DoubleLiteralExpr asDoubleLiteralExpr() {
        throw new IllegalStateException(CodeGenerationUtils.f("%s is not DoubleLiteralExpr, it is %s", this, this.getClass().getSimpleName()));
    }

    public boolean isEnclosedExpr() {
        return false;
    }

    public EnclosedExpr asEnclosedExpr() {
        throw new IllegalStateException(CodeGenerationUtils.f("%s is not EnclosedExpr, it is %s", this, this.getClass().getSimpleName()));
    }

    public boolean isFieldAccessExpr() {
        return false;
    }

    public FieldAccessExpr asFieldAccessExpr() {
        throw new IllegalStateException(CodeGenerationUtils.f("%s is not FieldAccessExpr, it is %s", this, this.getClass().getSimpleName()));
    }

    public boolean isInstanceOfExpr() {
        return false;
    }

    public InstanceOfExpr asInstanceOfExpr() {
        throw new IllegalStateException(CodeGenerationUtils.f("%s is not InstanceOfExpr, it is %s", this, this.getClass().getSimpleName()));
    }

    public boolean isIntegerLiteralExpr() {
        return false;
    }

    public IntegerLiteralExpr asIntegerLiteralExpr() {
        throw new IllegalStateException(CodeGenerationUtils.f("%s is not IntegerLiteralExpr, it is %s", this, this.getClass().getSimpleName()));
    }

    public boolean isLambdaExpr() {
        return false;
    }

    public LambdaExpr asLambdaExpr() {
        throw new IllegalStateException(CodeGenerationUtils.f("%s is not LambdaExpr, it is %s", this, this.getClass().getSimpleName()));
    }

    public boolean isLiteralExpr() {
        return false;
    }

    public LiteralExpr asLiteralExpr() {
        throw new IllegalStateException(CodeGenerationUtils.f("%s is not LiteralExpr, it is %s", this, this.getClass().getSimpleName()));
    }

    public boolean isLiteralStringValueExpr() {
        return false;
    }

    public LiteralStringValueExpr asLiteralStringValueExpr() {
        throw new IllegalStateException(CodeGenerationUtils.f("%s is not LiteralStringValueExpr, it is %s", this, this.getClass().getSimpleName()));
    }

    public boolean isLongLiteralExpr() {
        return false;
    }

    public LongLiteralExpr asLongLiteralExpr() {
        throw new IllegalStateException(CodeGenerationUtils.f("%s is not LongLiteralExpr, it is %s", this, this.getClass().getSimpleName()));
    }

    public boolean isMarkerAnnotationExpr() {
        return false;
    }

    public MarkerAnnotationExpr asMarkerAnnotationExpr() {
        throw new IllegalStateException(CodeGenerationUtils.f("%s is not MarkerAnnotationExpr, it is %s", this, this.getClass().getSimpleName()));
    }

    public boolean isMethodCallExpr() {
        return false;
    }

    public MethodCallExpr asMethodCallExpr() {
        throw new IllegalStateException(CodeGenerationUtils.f("%s is not MethodCallExpr, it is %s", this, this.getClass().getSimpleName()));
    }

    public boolean isMethodReferenceExpr() {
        return false;
    }

    public MethodReferenceExpr asMethodReferenceExpr() {
        throw new IllegalStateException(CodeGenerationUtils.f("%s is not MethodReferenceExpr, it is %s", this, this.getClass().getSimpleName()));
    }

    public boolean isNameExpr() {
        return false;
    }

    public NameExpr asNameExpr() {
        throw new IllegalStateException(CodeGenerationUtils.f("%s is not NameExpr, it is %s", this, this.getClass().getSimpleName()));
    }

    public boolean isNormalAnnotationExpr() {
        return false;
    }

    public NormalAnnotationExpr asNormalAnnotationExpr() {
        throw new IllegalStateException(CodeGenerationUtils.f("%s is not NormalAnnotationExpr, it is %s", this, this.getClass().getSimpleName()));
    }

    public boolean isNullLiteralExpr() {
        return false;
    }

    public NullLiteralExpr asNullLiteralExpr() {
        throw new IllegalStateException(CodeGenerationUtils.f("%s is not NullLiteralExpr, it is %s", this, this.getClass().getSimpleName()));
    }

    public boolean isObjectCreationExpr() {
        return false;
    }

    public ObjectCreationExpr asObjectCreationExpr() {
        throw new IllegalStateException(CodeGenerationUtils.f("%s is not ObjectCreationExpr, it is %s", this, this.getClass().getSimpleName()));
    }

    public boolean isSingleMemberAnnotationExpr() {
        return false;
    }

    public SingleMemberAnnotationExpr asSingleMemberAnnotationExpr() {
        throw new IllegalStateException(CodeGenerationUtils.f("%s is not SingleMemberAnnotationExpr, it is %s", this, this.getClass().getSimpleName()));
    }

    public boolean isStringLiteralExpr() {
        return false;
    }

    public StringLiteralExpr asStringLiteralExpr() {
        throw new IllegalStateException(CodeGenerationUtils.f("%s is not StringLiteralExpr, it is %s", this, this.getClass().getSimpleName()));
    }

    public boolean isSuperExpr() {
        return false;
    }

    public SuperExpr asSuperExpr() {
        throw new IllegalStateException(CodeGenerationUtils.f("%s is not SuperExpr, it is %s", this, this.getClass().getSimpleName()));
    }

    public boolean isThisExpr() {
        return false;
    }

    public ThisExpr asThisExpr() {
        throw new IllegalStateException(CodeGenerationUtils.f("%s is not ThisExpr, it is %s", this, this.getClass().getSimpleName()));
    }

    public boolean isTypeExpr() {
        return false;
    }

    public TypeExpr asTypeExpr() {
        throw new IllegalStateException(CodeGenerationUtils.f("%s is not TypeExpr, it is %s", this, this.getClass().getSimpleName()));
    }

    public boolean isUnaryExpr() {
        return false;
    }

    public UnaryExpr asUnaryExpr() {
        throw new IllegalStateException(CodeGenerationUtils.f("%s is not UnaryExpr, it is %s", this, this.getClass().getSimpleName()));
    }

    public boolean isVariableDeclarationExpr() {
        return false;
    }

    public VariableDeclarationExpr asVariableDeclarationExpr() {
        throw new IllegalStateException(CodeGenerationUtils.f("%s is not VariableDeclarationExpr, it is %s", this, this.getClass().getSimpleName()));
    }

    public void ifAnnotationExpr(Consumer<AnnotationExpr> action) {
    }

    public void ifArrayAccessExpr(Consumer<ArrayAccessExpr> action) {
    }

    public void ifArrayCreationExpr(Consumer<ArrayCreationExpr> action) {
    }

    public void ifArrayInitializerExpr(Consumer<ArrayInitializerExpr> action) {
    }

    public void ifAssignExpr(Consumer<AssignExpr> action) {
    }

    public void ifBinaryExpr(Consumer<BinaryExpr> action) {
    }

    public void ifBooleanLiteralExpr(Consumer<BooleanLiteralExpr> action) {
    }

    public void ifCastExpr(Consumer<CastExpr> action) {
    }

    public void ifCharLiteralExpr(Consumer<CharLiteralExpr> action) {
    }

    public void ifClassExpr(Consumer<ClassExpr> action) {
    }

    public void ifConditionalExpr(Consumer<ConditionalExpr> action) {
    }

    public void ifDoubleLiteralExpr(Consumer<DoubleLiteralExpr> action) {
    }

    public void ifEnclosedExpr(Consumer<EnclosedExpr> action) {
    }

    public void ifFieldAccessExpr(Consumer<FieldAccessExpr> action) {
    }

    public void ifInstanceOfExpr(Consumer<InstanceOfExpr> action) {
    }

    public void ifIntegerLiteralExpr(Consumer<IntegerLiteralExpr> action) {
    }

    public void ifLambdaExpr(Consumer<LambdaExpr> action) {
    }

    public void ifLiteralExpr(Consumer<LiteralExpr> action) {
    }

    public void ifLiteralStringValueExpr(Consumer<LiteralStringValueExpr> action) {
    }

    public void ifLongLiteralExpr(Consumer<LongLiteralExpr> action) {
    }

    public void ifMarkerAnnotationExpr(Consumer<MarkerAnnotationExpr> action) {
    }

    public void ifMethodCallExpr(Consumer<MethodCallExpr> action) {
    }

    public void ifMethodReferenceExpr(Consumer<MethodReferenceExpr> action) {
    }

    public void ifNameExpr(Consumer<NameExpr> action) {
    }

    public void ifNormalAnnotationExpr(Consumer<NormalAnnotationExpr> action) {
    }

    public void ifNullLiteralExpr(Consumer<NullLiteralExpr> action) {
    }

    public void ifObjectCreationExpr(Consumer<ObjectCreationExpr> action) {
    }

    public void ifSingleMemberAnnotationExpr(Consumer<SingleMemberAnnotationExpr> action) {
    }

    public void ifStringLiteralExpr(Consumer<StringLiteralExpr> action) {
    }

    public void ifSuperExpr(Consumer<SuperExpr> action) {
    }

    public void ifThisExpr(Consumer<ThisExpr> action) {
    }

    public void ifTypeExpr(Consumer<TypeExpr> action) {
    }

    public void ifUnaryExpr(Consumer<UnaryExpr> action) {
    }

    public void ifVariableDeclarationExpr(Consumer<VariableDeclarationExpr> action) {
    }

    public ResolvedType calculateResolvedType() {
        return this.getSymbolResolver().calculateType(this);
    }

    public Optional<AnnotationExpr> toAnnotationExpr() {
        return Optional.empty();
    }

    public Optional<ArrayAccessExpr> toArrayAccessExpr() {
        return Optional.empty();
    }

    public Optional<ArrayCreationExpr> toArrayCreationExpr() {
        return Optional.empty();
    }

    public Optional<ArrayInitializerExpr> toArrayInitializerExpr() {
        return Optional.empty();
    }

    public Optional<AssignExpr> toAssignExpr() {
        return Optional.empty();
    }

    public Optional<BinaryExpr> toBinaryExpr() {
        return Optional.empty();
    }

    public Optional<BooleanLiteralExpr> toBooleanLiteralExpr() {
        return Optional.empty();
    }

    public Optional<CastExpr> toCastExpr() {
        return Optional.empty();
    }

    public Optional<CharLiteralExpr> toCharLiteralExpr() {
        return Optional.empty();
    }

    public Optional<ClassExpr> toClassExpr() {
        return Optional.empty();
    }

    public Optional<ConditionalExpr> toConditionalExpr() {
        return Optional.empty();
    }

    public Optional<DoubleLiteralExpr> toDoubleLiteralExpr() {
        return Optional.empty();
    }

    public Optional<EnclosedExpr> toEnclosedExpr() {
        return Optional.empty();
    }

    public Optional<FieldAccessExpr> toFieldAccessExpr() {
        return Optional.empty();
    }

    public Optional<InstanceOfExpr> toInstanceOfExpr() {
        return Optional.empty();
    }

    public Optional<IntegerLiteralExpr> toIntegerLiteralExpr() {
        return Optional.empty();
    }

    public Optional<LambdaExpr> toLambdaExpr() {
        return Optional.empty();
    }

    public Optional<LiteralExpr> toLiteralExpr() {
        return Optional.empty();
    }

    public Optional<LiteralStringValueExpr> toLiteralStringValueExpr() {
        return Optional.empty();
    }

    public Optional<LongLiteralExpr> toLongLiteralExpr() {
        return Optional.empty();
    }

    public Optional<MarkerAnnotationExpr> toMarkerAnnotationExpr() {
        return Optional.empty();
    }

    public Optional<MethodCallExpr> toMethodCallExpr() {
        return Optional.empty();
    }

    public Optional<MethodReferenceExpr> toMethodReferenceExpr() {
        return Optional.empty();
    }

    public Optional<NameExpr> toNameExpr() {
        return Optional.empty();
    }

    public Optional<NormalAnnotationExpr> toNormalAnnotationExpr() {
        return Optional.empty();
    }

    public Optional<NullLiteralExpr> toNullLiteralExpr() {
        return Optional.empty();
    }

    public Optional<ObjectCreationExpr> toObjectCreationExpr() {
        return Optional.empty();
    }

    public Optional<SingleMemberAnnotationExpr> toSingleMemberAnnotationExpr() {
        return Optional.empty();
    }

    public Optional<StringLiteralExpr> toStringLiteralExpr() {
        return Optional.empty();
    }

    public Optional<SuperExpr> toSuperExpr() {
        return Optional.empty();
    }

    public Optional<ThisExpr> toThisExpr() {
        return Optional.empty();
    }

    public Optional<TypeExpr> toTypeExpr() {
        return Optional.empty();
    }

    public Optional<UnaryExpr> toUnaryExpr() {
        return Optional.empty();
    }

    public Optional<VariableDeclarationExpr> toVariableDeclarationExpr() {
        return Optional.empty();
    }

    public boolean isSwitchExpr() {
        return false;
    }

    public SwitchExpr asSwitchExpr() {
        throw new IllegalStateException(CodeGenerationUtils.f("%s is not SwitchExpr, it is %s", this, this.getClass().getSimpleName()));
    }

    public Optional<SwitchExpr> toSwitchExpr() {
        return Optional.empty();
    }

    public void ifSwitchExpr(Consumer<SwitchExpr> action) {
    }

    public boolean isTextBlockLiteralExpr() {
        return false;
    }

    public TextBlockLiteralExpr asTextBlockLiteralExpr() {
        throw new IllegalStateException(CodeGenerationUtils.f("%s is not TextBlockLiteralExpr, it is %s", this, this.getClass().getSimpleName()));
    }

    public Optional<TextBlockLiteralExpr> toTextBlockLiteralExpr() {
        return Optional.empty();
    }

    public void ifTextBlockLiteralExpr(Consumer<TextBlockLiteralExpr> action) {
    }

    public boolean isPatternExpr() {
        return false;
    }

    public PatternExpr asPatternExpr() {
        throw new IllegalStateException(CodeGenerationUtils.f("%s is not PatternExpr, it is %s", this, this.getClass().getSimpleName()));
    }

    public Optional<PatternExpr> toPatternExpr() {
        return Optional.empty();
    }

    public void ifPatternExpr(Consumer<PatternExpr> action) {
    }

    public boolean isStandaloneExpression() {
        return !this.isPolyExpression();
    }

    public boolean isPolyExpression() {
        return false;
    }

    public boolean isQualified() {
        return this.hasScope();
    }

    public final boolean appearsInAssignmentContext() {
        if (this.getParentNode().isPresent() && this.getParentNode().get() instanceof Expression) {
            return ((Expression)this.getParentNode().get()).isAssignmentContext();
        }
        return false;
    }

    protected boolean isAssignmentContext() {
        return false;
    }

    public final boolean appearsInInvocationContext() {
        if (this.getParentNode().isPresent() && this.getParentNode().get() instanceof Expression) {
            return ((Expression)this.getParentNode().get()).isInvocationContext();
        }
        return false;
    }

    protected boolean isInvocationContext() {
        return false;
    }

    public final boolean elidesTypeArguments() {
        if (!this.hasScope() || !(this instanceof NodeWithTypeArguments)) {
            return true;
        }
        Expression scope = ((NodeWithOptionalScope)((Object)this)).getScope().get();
        NodeWithTypeArguments nwta = (NodeWithTypeArguments)((Object)this);
        return scope.elidesTypeArguments() && (!nwta.getTypeArguments().isPresent() || nwta.isUsingDiamondOperator());
    }
}

