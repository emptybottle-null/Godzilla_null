/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.ast.stmt;

import com.github.javaparser.TokenRange;
import com.github.javaparser.ast.AllFieldsConstructor;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.stmt.AssertStmt;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.stmt.BreakStmt;
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
import com.github.javaparser.ast.stmt.SwitchStmt;
import com.github.javaparser.ast.stmt.SynchronizedStmt;
import com.github.javaparser.ast.stmt.ThrowStmt;
import com.github.javaparser.ast.stmt.TryStmt;
import com.github.javaparser.ast.stmt.UnparsableStmt;
import com.github.javaparser.ast.stmt.WhileStmt;
import com.github.javaparser.ast.stmt.YieldStmt;
import com.github.javaparser.ast.visitor.CloneVisitor;
import com.github.javaparser.metamodel.JavaParserMetaModel;
import com.github.javaparser.metamodel.StatementMetaModel;
import com.github.javaparser.utils.CodeGenerationUtils;
import java.util.Optional;
import java.util.function.Consumer;

public abstract class Statement
extends Node {
    @AllFieldsConstructor
    public Statement() {
        this(null);
    }

    public Statement(TokenRange tokenRange) {
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
    public Statement clone() {
        return (Statement)this.accept(new CloneVisitor(), null);
    }

    @Override
    public StatementMetaModel getMetaModel() {
        return JavaParserMetaModel.statementMetaModel;
    }

    @Override
    public boolean replace(Node node, Node replacementNode) {
        if (node == null) {
            return false;
        }
        return super.replace(node, replacementNode);
    }

    public boolean isAssertStmt() {
        return false;
    }

    public AssertStmt asAssertStmt() {
        throw new IllegalStateException(CodeGenerationUtils.f("%s is not AssertStmt, it is %s", this, this.getClass().getSimpleName()));
    }

    public boolean isBlockStmt() {
        return false;
    }

    public BlockStmt asBlockStmt() {
        throw new IllegalStateException(CodeGenerationUtils.f("%s is not BlockStmt, it is %s", this, this.getClass().getSimpleName()));
    }

    public boolean isBreakStmt() {
        return false;
    }

    public BreakStmt asBreakStmt() {
        throw new IllegalStateException(CodeGenerationUtils.f("%s is not BreakStmt, it is %s", this, this.getClass().getSimpleName()));
    }

    public boolean isContinueStmt() {
        return false;
    }

    public ContinueStmt asContinueStmt() {
        throw new IllegalStateException(CodeGenerationUtils.f("%s is not ContinueStmt, it is %s", this, this.getClass().getSimpleName()));
    }

    public boolean isDoStmt() {
        return false;
    }

    public DoStmt asDoStmt() {
        throw new IllegalStateException(CodeGenerationUtils.f("%s is not DoStmt, it is %s", this, this.getClass().getSimpleName()));
    }

    public boolean isEmptyStmt() {
        return false;
    }

    public EmptyStmt asEmptyStmt() {
        throw new IllegalStateException(CodeGenerationUtils.f("%s is not EmptyStmt, it is %s", this, this.getClass().getSimpleName()));
    }

    public boolean isExplicitConstructorInvocationStmt() {
        return false;
    }

    public ExplicitConstructorInvocationStmt asExplicitConstructorInvocationStmt() {
        throw new IllegalStateException(CodeGenerationUtils.f("%s is not ExplicitConstructorInvocationStmt, it is %s", this, this.getClass().getSimpleName()));
    }

    public boolean isExpressionStmt() {
        return false;
    }

    public ExpressionStmt asExpressionStmt() {
        throw new IllegalStateException(CodeGenerationUtils.f("%s is not ExpressionStmt, it is %s", this, this.getClass().getSimpleName()));
    }

    public boolean isForStmt() {
        return false;
    }

    public ForStmt asForStmt() {
        throw new IllegalStateException(CodeGenerationUtils.f("%s is not ForStmt, it is %s", this, this.getClass().getSimpleName()));
    }

    public boolean isIfStmt() {
        return false;
    }

    public IfStmt asIfStmt() {
        throw new IllegalStateException(CodeGenerationUtils.f("%s is not IfStmt, it is %s", this, this.getClass().getSimpleName()));
    }

    public boolean isLabeledStmt() {
        return false;
    }

    public LabeledStmt asLabeledStmt() {
        throw new IllegalStateException(CodeGenerationUtils.f("%s is not LabeledStmt, it is %s", this, this.getClass().getSimpleName()));
    }

    public boolean isLocalClassDeclarationStmt() {
        return false;
    }

    public LocalClassDeclarationStmt asLocalClassDeclarationStmt() {
        throw new IllegalStateException(CodeGenerationUtils.f("%s is not LocalClassDeclarationStmt, it is %s", this, this.getClass().getSimpleName()));
    }

    public boolean isReturnStmt() {
        return false;
    }

    public ReturnStmt asReturnStmt() {
        throw new IllegalStateException(CodeGenerationUtils.f("%s is not ReturnStmt, it is %s", this, this.getClass().getSimpleName()));
    }

    public boolean isSwitchStmt() {
        return false;
    }

    public SwitchStmt asSwitchStmt() {
        throw new IllegalStateException(CodeGenerationUtils.f("%s is not SwitchStmt, it is %s", this, this.getClass().getSimpleName()));
    }

    public boolean isSynchronizedStmt() {
        return false;
    }

    public SynchronizedStmt asSynchronizedStmt() {
        throw new IllegalStateException(CodeGenerationUtils.f("%s is not SynchronizedStmt, it is %s", this, this.getClass().getSimpleName()));
    }

    public boolean isThrowStmt() {
        return false;
    }

    public ThrowStmt asThrowStmt() {
        throw new IllegalStateException(CodeGenerationUtils.f("%s is not ThrowStmt, it is %s", this, this.getClass().getSimpleName()));
    }

    public boolean isTryStmt() {
        return false;
    }

    public TryStmt asTryStmt() {
        throw new IllegalStateException(CodeGenerationUtils.f("%s is not TryStmt, it is %s", this, this.getClass().getSimpleName()));
    }

    public boolean isUnparsableStmt() {
        return false;
    }

    public UnparsableStmt asUnparsableStmt() {
        throw new IllegalStateException(CodeGenerationUtils.f("%s is not UnparsableStmt, it is %s", this, this.getClass().getSimpleName()));
    }

    public boolean isWhileStmt() {
        return false;
    }

    public WhileStmt asWhileStmt() {
        throw new IllegalStateException(CodeGenerationUtils.f("%s is not WhileStmt, it is %s", this, this.getClass().getSimpleName()));
    }

    public void ifAssertStmt(Consumer<AssertStmt> action) {
    }

    public void ifBlockStmt(Consumer<BlockStmt> action) {
    }

    public void ifBreakStmt(Consumer<BreakStmt> action) {
    }

    public void ifContinueStmt(Consumer<ContinueStmt> action) {
    }

    public void ifDoStmt(Consumer<DoStmt> action) {
    }

    public void ifEmptyStmt(Consumer<EmptyStmt> action) {
    }

    public void ifExplicitConstructorInvocationStmt(Consumer<ExplicitConstructorInvocationStmt> action) {
    }

    public void ifExpressionStmt(Consumer<ExpressionStmt> action) {
    }

    public void ifForStmt(Consumer<ForStmt> action) {
    }

    public void ifIfStmt(Consumer<IfStmt> action) {
    }

    public void ifLabeledStmt(Consumer<LabeledStmt> action) {
    }

    public void ifLocalClassDeclarationStmt(Consumer<LocalClassDeclarationStmt> action) {
    }

    public void ifReturnStmt(Consumer<ReturnStmt> action) {
    }

    public void ifSwitchStmt(Consumer<SwitchStmt> action) {
    }

    public void ifSynchronizedStmt(Consumer<SynchronizedStmt> action) {
    }

    public void ifThrowStmt(Consumer<ThrowStmt> action) {
    }

    public void ifTryStmt(Consumer<TryStmt> action) {
    }

    public void ifUnparsableStmt(Consumer<UnparsableStmt> action) {
    }

    public void ifWhileStmt(Consumer<WhileStmt> action) {
    }

    public Optional<AssertStmt> toAssertStmt() {
        return Optional.empty();
    }

    public Optional<BlockStmt> toBlockStmt() {
        return Optional.empty();
    }

    public Optional<BreakStmt> toBreakStmt() {
        return Optional.empty();
    }

    public Optional<ContinueStmt> toContinueStmt() {
        return Optional.empty();
    }

    public Optional<DoStmt> toDoStmt() {
        return Optional.empty();
    }

    public Optional<EmptyStmt> toEmptyStmt() {
        return Optional.empty();
    }

    public Optional<ExplicitConstructorInvocationStmt> toExplicitConstructorInvocationStmt() {
        return Optional.empty();
    }

    public Optional<ExpressionStmt> toExpressionStmt() {
        return Optional.empty();
    }

    public Optional<ForStmt> toForStmt() {
        return Optional.empty();
    }

    public Optional<IfStmt> toIfStmt() {
        return Optional.empty();
    }

    public Optional<LabeledStmt> toLabeledStmt() {
        return Optional.empty();
    }

    public Optional<LocalClassDeclarationStmt> toLocalClassDeclarationStmt() {
        return Optional.empty();
    }

    public Optional<ReturnStmt> toReturnStmt() {
        return Optional.empty();
    }

    public Optional<SwitchStmt> toSwitchStmt() {
        return Optional.empty();
    }

    public Optional<SynchronizedStmt> toSynchronizedStmt() {
        return Optional.empty();
    }

    public Optional<ThrowStmt> toThrowStmt() {
        return Optional.empty();
    }

    public Optional<TryStmt> toTryStmt() {
        return Optional.empty();
    }

    public Optional<UnparsableStmt> toUnparsableStmt() {
        return Optional.empty();
    }

    public Optional<WhileStmt> toWhileStmt() {
        return Optional.empty();
    }

    public boolean isForEachStmt() {
        return false;
    }

    public ForEachStmt asForEachStmt() {
        throw new IllegalStateException(CodeGenerationUtils.f("%s is not ForEachStmt, it is %s", this, this.getClass().getSimpleName()));
    }

    public Optional<ForEachStmt> toForEachStmt() {
        return Optional.empty();
    }

    public void ifForEachStmt(Consumer<ForEachStmt> action) {
    }

    public boolean isYieldStmt() {
        return false;
    }

    public YieldStmt asYieldStmt() {
        throw new IllegalStateException(CodeGenerationUtils.f("%s is not YieldStmt, it is %s", this, this.getClass().getSimpleName()));
    }

    public Optional<YieldStmt> toYieldStmt() {
        return Optional.empty();
    }

    public void ifYieldStmt(Consumer<YieldStmt> action) {
    }
}

