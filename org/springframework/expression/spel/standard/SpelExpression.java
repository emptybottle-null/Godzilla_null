/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.expression.spel.standard;

import java.util.concurrent.atomic.AtomicInteger;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.EvaluationException;
import org.springframework.expression.Expression;
import org.springframework.expression.TypedValue;
import org.springframework.expression.common.ExpressionUtils;
import org.springframework.expression.spel.CompiledExpression;
import org.springframework.expression.spel.ExpressionState;
import org.springframework.expression.spel.SpelCompilerMode;
import org.springframework.expression.spel.SpelEvaluationException;
import org.springframework.expression.spel.SpelMessage;
import org.springframework.expression.spel.SpelNode;
import org.springframework.expression.spel.SpelParserConfiguration;
import org.springframework.expression.spel.ast.SpelNodeImpl;
import org.springframework.expression.spel.standard.SpelCompiler;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

public class SpelExpression
implements Expression {
    private static final int INTERPRETED_COUNT_THRESHOLD = 100;
    private static final int FAILED_ATTEMPTS_THRESHOLD = 100;
    private final String expression;
    private final SpelNodeImpl ast;
    private final SpelParserConfiguration configuration;
    @Nullable
    private EvaluationContext evaluationContext;
    @Nullable
    private volatile CompiledExpression compiledAst;
    private final AtomicInteger interpretedCount = new AtomicInteger();
    private final AtomicInteger failedAttempts = new AtomicInteger();

    public SpelExpression(String expression, SpelNodeImpl ast, SpelParserConfiguration configuration) {
        this.expression = expression;
        this.ast = ast;
        this.configuration = configuration;
    }

    public void setEvaluationContext(EvaluationContext evaluationContext) {
        this.evaluationContext = evaluationContext;
    }

    public EvaluationContext getEvaluationContext() {
        if (this.evaluationContext == null) {
            this.evaluationContext = new StandardEvaluationContext();
        }
        return this.evaluationContext;
    }

    @Override
    public String getExpressionString() {
        return this.expression;
    }

    @Override
    @Nullable
    public Object getValue() throws EvaluationException {
        CompiledExpression compiledAst = this.compiledAst;
        if (compiledAst != null) {
            try {
                EvaluationContext context = this.getEvaluationContext();
                return compiledAst.getValue(context.getRootObject().getValue(), context);
            } catch (Throwable ex) {
                if (this.configuration.getCompilerMode() == SpelCompilerMode.MIXED) {
                    this.compiledAst = null;
                    this.interpretedCount.set(0);
                }
                throw new SpelEvaluationException(ex, SpelMessage.EXCEPTION_RUNNING_COMPILED_EXPRESSION, new Object[0]);
            }
        }
        ExpressionState expressionState = new ExpressionState(this.getEvaluationContext(), this.configuration);
        Object result = this.ast.getValue(expressionState);
        this.checkCompile(expressionState);
        return result;
    }

    @Override
    @Nullable
    public <T> T getValue(@Nullable Class<T> expectedResultType) throws EvaluationException {
        CompiledExpression compiledAst = this.compiledAst;
        if (compiledAst != null) {
            try {
                EvaluationContext context = this.getEvaluationContext();
                Object result = compiledAst.getValue(context.getRootObject().getValue(), context);
                if (expectedResultType == null) {
                    return (T)result;
                }
                return ExpressionUtils.convertTypedValue(this.getEvaluationContext(), new TypedValue(result), expectedResultType);
            } catch (Throwable ex) {
                if (this.configuration.getCompilerMode() == SpelCompilerMode.MIXED) {
                    this.compiledAst = null;
                    this.interpretedCount.set(0);
                }
                throw new SpelEvaluationException(ex, SpelMessage.EXCEPTION_RUNNING_COMPILED_EXPRESSION, new Object[0]);
            }
        }
        ExpressionState expressionState = new ExpressionState(this.getEvaluationContext(), this.configuration);
        TypedValue typedResultValue = this.ast.getTypedValue(expressionState);
        this.checkCompile(expressionState);
        return ExpressionUtils.convertTypedValue(expressionState.getEvaluationContext(), typedResultValue, expectedResultType);
    }

    @Override
    @Nullable
    public Object getValue(@Nullable Object rootObject) throws EvaluationException {
        CompiledExpression compiledAst = this.compiledAst;
        if (compiledAst != null) {
            try {
                return compiledAst.getValue(rootObject, this.getEvaluationContext());
            } catch (Throwable ex) {
                if (this.configuration.getCompilerMode() == SpelCompilerMode.MIXED) {
                    this.compiledAst = null;
                    this.interpretedCount.set(0);
                }
                throw new SpelEvaluationException(ex, SpelMessage.EXCEPTION_RUNNING_COMPILED_EXPRESSION, new Object[0]);
            }
        }
        ExpressionState expressionState = new ExpressionState(this.getEvaluationContext(), this.toTypedValue(rootObject), this.configuration);
        Object result = this.ast.getValue(expressionState);
        this.checkCompile(expressionState);
        return result;
    }

    @Override
    @Nullable
    public <T> T getValue(@Nullable Object rootObject, @Nullable Class<T> expectedResultType) throws EvaluationException {
        CompiledExpression compiledAst = this.compiledAst;
        if (compiledAst != null) {
            try {
                Object result = compiledAst.getValue(rootObject, this.getEvaluationContext());
                if (expectedResultType == null) {
                    return (T)result;
                }
                return ExpressionUtils.convertTypedValue(this.getEvaluationContext(), new TypedValue(result), expectedResultType);
            } catch (Throwable ex) {
                if (this.configuration.getCompilerMode() == SpelCompilerMode.MIXED) {
                    this.compiledAst = null;
                    this.interpretedCount.set(0);
                }
                throw new SpelEvaluationException(ex, SpelMessage.EXCEPTION_RUNNING_COMPILED_EXPRESSION, new Object[0]);
            }
        }
        ExpressionState expressionState = new ExpressionState(this.getEvaluationContext(), this.toTypedValue(rootObject), this.configuration);
        TypedValue typedResultValue = this.ast.getTypedValue(expressionState);
        this.checkCompile(expressionState);
        return ExpressionUtils.convertTypedValue(expressionState.getEvaluationContext(), typedResultValue, expectedResultType);
    }

    @Override
    @Nullable
    public Object getValue(EvaluationContext context) throws EvaluationException {
        Assert.notNull((Object)context, "EvaluationContext is required");
        CompiledExpression compiledAst = this.compiledAst;
        if (compiledAst != null) {
            try {
                return compiledAst.getValue(context.getRootObject().getValue(), context);
            } catch (Throwable ex) {
                if (this.configuration.getCompilerMode() == SpelCompilerMode.MIXED) {
                    this.compiledAst = null;
                    this.interpretedCount.set(0);
                }
                throw new SpelEvaluationException(ex, SpelMessage.EXCEPTION_RUNNING_COMPILED_EXPRESSION, new Object[0]);
            }
        }
        ExpressionState expressionState = new ExpressionState(context, this.configuration);
        Object result = this.ast.getValue(expressionState);
        this.checkCompile(expressionState);
        return result;
    }

    @Override
    @Nullable
    public <T> T getValue(EvaluationContext context, @Nullable Class<T> expectedResultType) throws EvaluationException {
        Assert.notNull((Object)context, "EvaluationContext is required");
        CompiledExpression compiledAst = this.compiledAst;
        if (compiledAst != null) {
            try {
                Object result = compiledAst.getValue(context.getRootObject().getValue(), context);
                if (expectedResultType != null) {
                    return ExpressionUtils.convertTypedValue(context, new TypedValue(result), expectedResultType);
                }
                return (T)result;
            } catch (Throwable ex) {
                if (this.configuration.getCompilerMode() == SpelCompilerMode.MIXED) {
                    this.compiledAst = null;
                    this.interpretedCount.set(0);
                }
                throw new SpelEvaluationException(ex, SpelMessage.EXCEPTION_RUNNING_COMPILED_EXPRESSION, new Object[0]);
            }
        }
        ExpressionState expressionState = new ExpressionState(context, this.configuration);
        TypedValue typedResultValue = this.ast.getTypedValue(expressionState);
        this.checkCompile(expressionState);
        return ExpressionUtils.convertTypedValue(context, typedResultValue, expectedResultType);
    }

    @Override
    @Nullable
    public Object getValue(EvaluationContext context, @Nullable Object rootObject) throws EvaluationException {
        Assert.notNull((Object)context, "EvaluationContext is required");
        CompiledExpression compiledAst = this.compiledAst;
        if (compiledAst != null) {
            try {
                return compiledAst.getValue(rootObject, context);
            } catch (Throwable ex) {
                if (this.configuration.getCompilerMode() == SpelCompilerMode.MIXED) {
                    this.compiledAst = null;
                    this.interpretedCount.set(0);
                }
                throw new SpelEvaluationException(ex, SpelMessage.EXCEPTION_RUNNING_COMPILED_EXPRESSION, new Object[0]);
            }
        }
        ExpressionState expressionState = new ExpressionState(context, this.toTypedValue(rootObject), this.configuration);
        Object result = this.ast.getValue(expressionState);
        this.checkCompile(expressionState);
        return result;
    }

    @Override
    @Nullable
    public <T> T getValue(EvaluationContext context, @Nullable Object rootObject, @Nullable Class<T> expectedResultType) throws EvaluationException {
        Assert.notNull((Object)context, "EvaluationContext is required");
        CompiledExpression compiledAst = this.compiledAst;
        if (compiledAst != null) {
            try {
                Object result = compiledAst.getValue(rootObject, context);
                if (expectedResultType != null) {
                    return ExpressionUtils.convertTypedValue(context, new TypedValue(result), expectedResultType);
                }
                return (T)result;
            } catch (Throwable ex) {
                if (this.configuration.getCompilerMode() == SpelCompilerMode.MIXED) {
                    this.compiledAst = null;
                    this.interpretedCount.set(0);
                }
                throw new SpelEvaluationException(ex, SpelMessage.EXCEPTION_RUNNING_COMPILED_EXPRESSION, new Object[0]);
            }
        }
        ExpressionState expressionState = new ExpressionState(context, this.toTypedValue(rootObject), this.configuration);
        TypedValue typedResultValue = this.ast.getTypedValue(expressionState);
        this.checkCompile(expressionState);
        return ExpressionUtils.convertTypedValue(context, typedResultValue, expectedResultType);
    }

    @Override
    @Nullable
    public Class<?> getValueType() throws EvaluationException {
        return this.getValueType(this.getEvaluationContext());
    }

    @Override
    @Nullable
    public Class<?> getValueType(@Nullable Object rootObject) throws EvaluationException {
        return this.getValueType(this.getEvaluationContext(), rootObject);
    }

    @Override
    @Nullable
    public Class<?> getValueType(EvaluationContext context) throws EvaluationException {
        Assert.notNull((Object)context, "EvaluationContext is required");
        ExpressionState expressionState = new ExpressionState(context, this.configuration);
        TypeDescriptor typeDescriptor = this.ast.getValueInternal(expressionState).getTypeDescriptor();
        return typeDescriptor != null ? typeDescriptor.getType() : null;
    }

    @Override
    @Nullable
    public Class<?> getValueType(EvaluationContext context, @Nullable Object rootObject) throws EvaluationException {
        ExpressionState expressionState = new ExpressionState(context, this.toTypedValue(rootObject), this.configuration);
        TypeDescriptor typeDescriptor = this.ast.getValueInternal(expressionState).getTypeDescriptor();
        return typeDescriptor != null ? typeDescriptor.getType() : null;
    }

    @Override
    @Nullable
    public TypeDescriptor getValueTypeDescriptor() throws EvaluationException {
        return this.getValueTypeDescriptor(this.getEvaluationContext());
    }

    @Override
    @Nullable
    public TypeDescriptor getValueTypeDescriptor(@Nullable Object rootObject) throws EvaluationException {
        ExpressionState expressionState = new ExpressionState(this.getEvaluationContext(), this.toTypedValue(rootObject), this.configuration);
        return this.ast.getValueInternal(expressionState).getTypeDescriptor();
    }

    @Override
    @Nullable
    public TypeDescriptor getValueTypeDescriptor(EvaluationContext context) throws EvaluationException {
        Assert.notNull((Object)context, "EvaluationContext is required");
        ExpressionState expressionState = new ExpressionState(context, this.configuration);
        return this.ast.getValueInternal(expressionState).getTypeDescriptor();
    }

    @Override
    @Nullable
    public TypeDescriptor getValueTypeDescriptor(EvaluationContext context, @Nullable Object rootObject) throws EvaluationException {
        Assert.notNull((Object)context, "EvaluationContext is required");
        ExpressionState expressionState = new ExpressionState(context, this.toTypedValue(rootObject), this.configuration);
        return this.ast.getValueInternal(expressionState).getTypeDescriptor();
    }

    @Override
    public boolean isWritable(@Nullable Object rootObject) throws EvaluationException {
        return this.ast.isWritable(new ExpressionState(this.getEvaluationContext(), this.toTypedValue(rootObject), this.configuration));
    }

    @Override
    public boolean isWritable(EvaluationContext context) throws EvaluationException {
        Assert.notNull((Object)context, "EvaluationContext is required");
        return this.ast.isWritable(new ExpressionState(context, this.configuration));
    }

    @Override
    public boolean isWritable(EvaluationContext context, @Nullable Object rootObject) throws EvaluationException {
        Assert.notNull((Object)context, "EvaluationContext is required");
        return this.ast.isWritable(new ExpressionState(context, this.toTypedValue(rootObject), this.configuration));
    }

    @Override
    public void setValue(@Nullable Object rootObject, @Nullable Object value) throws EvaluationException {
        this.ast.setValue(new ExpressionState(this.getEvaluationContext(), this.toTypedValue(rootObject), this.configuration), value);
    }

    @Override
    public void setValue(EvaluationContext context, @Nullable Object value) throws EvaluationException {
        Assert.notNull((Object)context, "EvaluationContext is required");
        this.ast.setValue(new ExpressionState(context, this.configuration), value);
    }

    @Override
    public void setValue(EvaluationContext context, @Nullable Object rootObject, @Nullable Object value) throws EvaluationException {
        Assert.notNull((Object)context, "EvaluationContext is required");
        this.ast.setValue(new ExpressionState(context, this.toTypedValue(rootObject), this.configuration), value);
    }

    private void checkCompile(ExpressionState expressionState) {
        this.interpretedCount.incrementAndGet();
        SpelCompilerMode compilerMode = expressionState.getConfiguration().getCompilerMode();
        if (compilerMode != SpelCompilerMode.OFF) {
            if (compilerMode == SpelCompilerMode.IMMEDIATE) {
                if (this.interpretedCount.get() > 1) {
                    this.compileExpression();
                }
            } else if (this.interpretedCount.get() > 100) {
                this.compileExpression();
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public boolean compileExpression() {
        CompiledExpression compiledAst = this.compiledAst;
        if (compiledAst != null) {
            return true;
        }
        if (this.failedAttempts.get() > 100) {
            return false;
        }
        SpelExpression spelExpression = this;
        synchronized (spelExpression) {
            if (this.compiledAst != null) {
                return true;
            }
            SpelCompiler compiler = SpelCompiler.getCompiler(this.configuration.getCompilerClassLoader());
            compiledAst = compiler.compile(this.ast);
            if (compiledAst != null) {
                this.compiledAst = compiledAst;
                return true;
            }
            this.failedAttempts.incrementAndGet();
            return false;
        }
    }

    public void revertToInterpreted() {
        this.compiledAst = null;
        this.interpretedCount.set(0);
        this.failedAttempts.set(0);
    }

    public SpelNode getAST() {
        return this.ast;
    }

    public String toStringAST() {
        return this.ast.toStringAST();
    }

    private TypedValue toTypedValue(@Nullable Object object) {
        return object != null ? new TypedValue(object) : TypedValue.NULL;
    }
}

