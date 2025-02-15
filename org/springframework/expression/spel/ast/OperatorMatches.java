/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.expression.spel.ast;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import org.springframework.expression.EvaluationException;
import org.springframework.expression.spel.ExpressionState;
import org.springframework.expression.spel.SpelEvaluationException;
import org.springframework.expression.spel.SpelMessage;
import org.springframework.expression.spel.ast.Operator;
import org.springframework.expression.spel.ast.SpelNodeImpl;
import org.springframework.expression.spel.support.BooleanTypedValue;

public class OperatorMatches
extends Operator {
    private static final int PATTERN_ACCESS_THRESHOLD = 1000000;
    private final ConcurrentMap<String, Pattern> patternCache = new ConcurrentHashMap<String, Pattern>();

    public OperatorMatches(int startPos, int endPos, SpelNodeImpl ... operands) {
        super("matches", startPos, endPos, operands);
    }

    @Override
    public BooleanTypedValue getValueInternal(ExpressionState state) throws EvaluationException {
        SpelNodeImpl leftOp = this.getLeftOperand();
        SpelNodeImpl rightOp = this.getRightOperand();
        String left = leftOp.getValue(state, String.class);
        Object right = this.getRightOperand().getValue(state);
        if (left == null) {
            throw new SpelEvaluationException(leftOp.getStartPosition(), SpelMessage.INVALID_FIRST_OPERAND_FOR_MATCHES_OPERATOR, new Object[]{null});
        }
        if (!(right instanceof String)) {
            throw new SpelEvaluationException(rightOp.getStartPosition(), SpelMessage.INVALID_SECOND_OPERAND_FOR_MATCHES_OPERATOR, right);
        }
        try {
            String rightString = (String)right;
            Pattern pattern = (Pattern)this.patternCache.get(rightString);
            if (pattern == null) {
                pattern = Pattern.compile(rightString);
                this.patternCache.putIfAbsent(rightString, pattern);
            }
            Matcher matcher = pattern.matcher(new MatcherInput(left, new AccessCount()));
            return BooleanTypedValue.forValue(matcher.matches());
        } catch (PatternSyntaxException ex) {
            throw new SpelEvaluationException(rightOp.getStartPosition(), (Throwable)ex, SpelMessage.INVALID_PATTERN, right);
        } catch (IllegalStateException ex) {
            throw new SpelEvaluationException(rightOp.getStartPosition(), (Throwable)ex, SpelMessage.FLAWED_PATTERN, right);
        }
    }

    private static class MatcherInput
    implements CharSequence {
        private final CharSequence value;
        private AccessCount access;

        public MatcherInput(CharSequence value, AccessCount access) {
            this.value = value;
            this.access = access;
        }

        @Override
        public char charAt(int index) {
            this.access.check();
            return this.value.charAt(index);
        }

        @Override
        public CharSequence subSequence(int start, int end) {
            return new MatcherInput(this.value.subSequence(start, end), this.access);
        }

        @Override
        public int length() {
            return this.value.length();
        }

        @Override
        public String toString() {
            return this.value.toString();
        }
    }

    private static class AccessCount {
        private int count;

        private AccessCount() {
        }

        public void check() throws IllegalStateException {
            if (this.count++ > 1000000) {
                throw new IllegalStateException("Pattern access threshold exceeded");
            }
        }
    }
}

