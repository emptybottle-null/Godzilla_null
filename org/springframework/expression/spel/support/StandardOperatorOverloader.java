/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.expression.spel.support;

import org.springframework.expression.EvaluationException;
import org.springframework.expression.Operation;
import org.springframework.expression.OperatorOverloader;
import org.springframework.lang.Nullable;

public class StandardOperatorOverloader
implements OperatorOverloader {
    @Override
    public boolean overridesOperation(Operation operation, @Nullable Object leftOperand, @Nullable Object rightOperand) throws EvaluationException {
        return false;
    }

    @Override
    public Object operate(Operation operation, @Nullable Object leftOperand, @Nullable Object rightOperand) throws EvaluationException {
        throw new EvaluationException("No operation overloaded by default");
    }
}

