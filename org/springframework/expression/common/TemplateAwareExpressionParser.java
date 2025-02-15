/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.expression.common;

import java.util.ArrayDeque;
import java.util.ArrayList;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.ParseException;
import org.springframework.expression.ParserContext;
import org.springframework.expression.common.CompositeStringExpression;
import org.springframework.expression.common.LiteralExpression;
import org.springframework.lang.Nullable;

public abstract class TemplateAwareExpressionParser
implements ExpressionParser {
    @Override
    public Expression parseExpression(String expressionString) throws ParseException {
        return this.parseExpression(expressionString, null);
    }

    @Override
    public Expression parseExpression(String expressionString, @Nullable ParserContext context) throws ParseException {
        if (context != null && context.isTemplate()) {
            return this.parseTemplate(expressionString, context);
        }
        return this.doParseExpression(expressionString, context);
    }

    private Expression parseTemplate(String expressionString, ParserContext context) throws ParseException {
        if (expressionString.isEmpty()) {
            return new LiteralExpression("");
        }
        Expression[] expressions = this.parseExpressions(expressionString, context);
        if (expressions.length == 1) {
            return expressions[0];
        }
        return new CompositeStringExpression(expressionString, expressions);
    }

    private Expression[] parseExpressions(String expressionString, ParserContext context) throws ParseException {
        ArrayList<Expression> expressions = new ArrayList<Expression>();
        String prefix = context.getExpressionPrefix();
        String suffix = context.getExpressionSuffix();
        int startIdx = 0;
        while (startIdx < expressionString.length()) {
            int prefixIndex = expressionString.indexOf(prefix, startIdx);
            if (prefixIndex >= startIdx) {
                int afterPrefixIndex;
                int suffixIndex;
                if (prefixIndex > startIdx) {
                    expressions.add(new LiteralExpression(expressionString.substring(startIdx, prefixIndex)));
                }
                if ((suffixIndex = this.skipToCorrectEndSuffix(suffix, expressionString, afterPrefixIndex = prefixIndex + prefix.length())) == -1) {
                    throw new ParseException(expressionString, prefixIndex, "No ending suffix '" + suffix + "' for expression starting at character " + prefixIndex + ": " + expressionString.substring(prefixIndex));
                }
                if (suffixIndex == afterPrefixIndex) {
                    throw new ParseException(expressionString, prefixIndex, "No expression defined within delimiter '" + prefix + suffix + "' at character " + prefixIndex);
                }
                String expr = expressionString.substring(prefixIndex + prefix.length(), suffixIndex);
                if ((expr = expr.trim()).isEmpty()) {
                    throw new ParseException(expressionString, prefixIndex, "No expression defined within delimiter '" + prefix + suffix + "' at character " + prefixIndex);
                }
                expressions.add(this.doParseExpression(expr, context));
                startIdx = suffixIndex + suffix.length();
                continue;
            }
            expressions.add(new LiteralExpression(expressionString.substring(startIdx)));
            startIdx = expressionString.length();
        }
        return expressions.toArray(new Expression[0]);
    }

    private boolean isSuffixHere(String expressionString, int pos, String suffix) {
        int suffixPosition = 0;
        for (int i = 0; i < suffix.length() && pos < expressionString.length(); ++i) {
            if (expressionString.charAt(pos++) == suffix.charAt(suffixPosition++)) continue;
            return false;
        }
        return suffixPosition == suffix.length();
    }

    private int skipToCorrectEndSuffix(String suffix, String expressionString, int afterPrefixIndex) throws ParseException {
        int pos;
        int maxlen = expressionString.length();
        int nextSuffix = expressionString.indexOf(suffix, afterPrefixIndex);
        if (nextSuffix == -1) {
            return -1;
        }
        ArrayDeque<Bracket> stack = new ArrayDeque<Bracket>();
        block5: for (pos = afterPrefixIndex; !(pos >= maxlen || this.isSuffixHere(expressionString, pos, suffix) && stack.isEmpty()); ++pos) {
            char ch = expressionString.charAt(pos);
            switch (ch) {
                case '(': 
                case '[': 
                case '{': {
                    stack.push(new Bracket(ch, pos));
                    continue block5;
                }
                case ')': 
                case ']': 
                case '}': {
                    if (stack.isEmpty()) {
                        throw new ParseException(expressionString, pos, "Found closing '" + ch + "' at position " + pos + " without an opening '" + Bracket.theOpenBracketFor(ch) + "'");
                    }
                    Bracket p = (Bracket)stack.pop();
                    if (p.compatibleWithCloseBracket(ch)) continue block5;
                    throw new ParseException(expressionString, pos, "Found closing '" + ch + "' at position " + pos + " but most recent opening is '" + p.bracket + "' at position " + p.pos);
                }
                case '\"': 
                case '\'': {
                    int endLiteral = expressionString.indexOf(ch, pos + 1);
                    if (endLiteral == -1) {
                        throw new ParseException(expressionString, pos, "Found non terminating string literal starting at position " + pos);
                    }
                    pos = endLiteral;
                }
            }
        }
        if (!stack.isEmpty()) {
            Bracket p = (Bracket)stack.pop();
            throw new ParseException(expressionString, p.pos, "Missing closing '" + Bracket.theCloseBracketFor(p.bracket) + "' for '" + p.bracket + "' at position " + p.pos);
        }
        if (!this.isSuffixHere(expressionString, pos, suffix)) {
            return -1;
        }
        return pos;
    }

    protected abstract Expression doParseExpression(String var1, @Nullable ParserContext var2) throws ParseException;

    private static class Bracket {
        char bracket;
        int pos;

        Bracket(char bracket, int pos) {
            this.bracket = bracket;
            this.pos = pos;
        }

        boolean compatibleWithCloseBracket(char closeBracket) {
            if (this.bracket == '{') {
                return closeBracket == '}';
            }
            if (this.bracket == '[') {
                return closeBracket == ']';
            }
            return closeBracket == ')';
        }

        static char theOpenBracketFor(char closeBracket) {
            if (closeBracket == '}') {
                return '{';
            }
            if (closeBracket == ']') {
                return '[';
            }
            return '(';
        }

        static char theCloseBracketFor(char openBracket) {
            if (openBracket == '{') {
                return '}';
            }
            if (openBracket == '[') {
                return ']';
            }
            return ')';
        }
    }
}

