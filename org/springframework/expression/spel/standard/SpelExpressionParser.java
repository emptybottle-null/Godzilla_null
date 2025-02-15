/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.expression.spel.standard;

import org.springframework.expression.ParseException;
import org.springframework.expression.ParserContext;
import org.springframework.expression.common.TemplateAwareExpressionParser;
import org.springframework.expression.spel.SpelParserConfiguration;
import org.springframework.expression.spel.standard.InternalSpelExpressionParser;
import org.springframework.expression.spel.standard.SpelExpression;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

public class SpelExpressionParser
extends TemplateAwareExpressionParser {
    private final SpelParserConfiguration configuration;

    public SpelExpressionParser() {
        this.configuration = new SpelParserConfiguration();
    }

    public SpelExpressionParser(SpelParserConfiguration configuration) {
        Assert.notNull((Object)configuration, "SpelParserConfiguration must not be null");
        this.configuration = configuration;
    }

    public SpelExpression parseRaw(String expressionString) throws ParseException {
        return this.doParseExpression(expressionString, null);
    }

    @Override
    protected SpelExpression doParseExpression(String expressionString, @Nullable ParserContext context) throws ParseException {
        return new InternalSpelExpressionParser(this.configuration).doParseExpression(expressionString, context);
    }
}

