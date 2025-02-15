/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.expression.spel.standard;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.regex.Pattern;
import org.springframework.expression.ParseException;
import org.springframework.expression.ParserContext;
import org.springframework.expression.common.TemplateAwareExpressionParser;
import org.springframework.expression.spel.InternalParseException;
import org.springframework.expression.spel.SpelMessage;
import org.springframework.expression.spel.SpelParseException;
import org.springframework.expression.spel.SpelParserConfiguration;
import org.springframework.expression.spel.ast.Assign;
import org.springframework.expression.spel.ast.BeanReference;
import org.springframework.expression.spel.ast.BooleanLiteral;
import org.springframework.expression.spel.ast.CompoundExpression;
import org.springframework.expression.spel.ast.ConstructorReference;
import org.springframework.expression.spel.ast.Elvis;
import org.springframework.expression.spel.ast.FunctionReference;
import org.springframework.expression.spel.ast.Identifier;
import org.springframework.expression.spel.ast.Indexer;
import org.springframework.expression.spel.ast.InlineList;
import org.springframework.expression.spel.ast.InlineMap;
import org.springframework.expression.spel.ast.Literal;
import org.springframework.expression.spel.ast.MethodReference;
import org.springframework.expression.spel.ast.NullLiteral;
import org.springframework.expression.spel.ast.OpAnd;
import org.springframework.expression.spel.ast.OpDec;
import org.springframework.expression.spel.ast.OpDivide;
import org.springframework.expression.spel.ast.OpEQ;
import org.springframework.expression.spel.ast.OpGE;
import org.springframework.expression.spel.ast.OpGT;
import org.springframework.expression.spel.ast.OpInc;
import org.springframework.expression.spel.ast.OpLE;
import org.springframework.expression.spel.ast.OpLT;
import org.springframework.expression.spel.ast.OpMinus;
import org.springframework.expression.spel.ast.OpModulus;
import org.springframework.expression.spel.ast.OpMultiply;
import org.springframework.expression.spel.ast.OpNE;
import org.springframework.expression.spel.ast.OpOr;
import org.springframework.expression.spel.ast.OpPlus;
import org.springframework.expression.spel.ast.OperatorBetween;
import org.springframework.expression.spel.ast.OperatorInstanceof;
import org.springframework.expression.spel.ast.OperatorMatches;
import org.springframework.expression.spel.ast.OperatorNot;
import org.springframework.expression.spel.ast.OperatorPower;
import org.springframework.expression.spel.ast.Projection;
import org.springframework.expression.spel.ast.PropertyOrFieldReference;
import org.springframework.expression.spel.ast.QualifiedIdentifier;
import org.springframework.expression.spel.ast.Selection;
import org.springframework.expression.spel.ast.SpelNodeImpl;
import org.springframework.expression.spel.ast.StringLiteral;
import org.springframework.expression.spel.ast.Ternary;
import org.springframework.expression.spel.ast.TypeReference;
import org.springframework.expression.spel.ast.VariableReference;
import org.springframework.expression.spel.standard.SpelExpression;
import org.springframework.expression.spel.standard.Token;
import org.springframework.expression.spel.standard.TokenKind;
import org.springframework.expression.spel.standard.Tokenizer;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

class InternalSpelExpressionParser
extends TemplateAwareExpressionParser {
    private static final Pattern VALID_QUALIFIED_ID_PATTERN = Pattern.compile("[\\p{L}\\p{N}_$]+");
    private final SpelParserConfiguration configuration;
    private final Deque<SpelNodeImpl> constructedNodes = new ArrayDeque<SpelNodeImpl>();
    private String expressionString = "";
    private List<Token> tokenStream = Collections.emptyList();
    private int tokenStreamLength;
    private int tokenStreamPointer;

    public InternalSpelExpressionParser(SpelParserConfiguration configuration) {
        this.configuration = configuration;
    }

    @Override
    protected SpelExpression doParseExpression(String expressionString, @Nullable ParserContext context) throws ParseException {
        try {
            this.expressionString = expressionString;
            Tokenizer tokenizer = new Tokenizer(expressionString);
            this.tokenStream = tokenizer.process();
            this.tokenStreamLength = this.tokenStream.size();
            this.tokenStreamPointer = 0;
            this.constructedNodes.clear();
            SpelNodeImpl ast = this.eatExpression();
            Assert.state(ast != null, "No node");
            Token t = this.peekToken();
            if (t != null) {
                throw new SpelParseException(t.startPos, SpelMessage.MORE_INPUT, this.toString(this.nextToken()));
            }
            Assert.isTrue(this.constructedNodes.isEmpty(), "At least one node expected");
            return new SpelExpression(expressionString, ast, this.configuration);
        } catch (InternalParseException ex) {
            throw ex.getCause();
        }
    }

    @Nullable
    private SpelNodeImpl eatExpression() {
        SpelNodeImpl expr = this.eatLogicalOrExpression();
        Token t = this.peekToken();
        if (t != null) {
            if (t.kind == TokenKind.ASSIGN) {
                if (expr == null) {
                    expr = new NullLiteral(t.startPos - 1, t.endPos - 1);
                }
                this.nextToken();
                SpelNodeImpl assignedValue = this.eatLogicalOrExpression();
                return new Assign(t.startPos, t.endPos, expr, assignedValue);
            }
            if (t.kind == TokenKind.ELVIS) {
                if (expr == null) {
                    expr = new NullLiteral(t.startPos - 1, t.endPos - 2);
                }
                this.nextToken();
                SpelNodeImpl valueIfNull = this.eatExpression();
                if (valueIfNull == null) {
                    valueIfNull = new NullLiteral(t.startPos + 1, t.endPos + 1);
                }
                return new Elvis(t.startPos, t.endPos, expr, valueIfNull);
            }
            if (t.kind == TokenKind.QMARK) {
                if (expr == null) {
                    expr = new NullLiteral(t.startPos - 1, t.endPos - 1);
                }
                this.nextToken();
                SpelNodeImpl ifTrueExprValue = this.eatExpression();
                this.eatToken(TokenKind.COLON);
                SpelNodeImpl ifFalseExprValue = this.eatExpression();
                return new Ternary(t.startPos, t.endPos, expr, ifTrueExprValue, ifFalseExprValue);
            }
        }
        return expr;
    }

    @Nullable
    private SpelNodeImpl eatLogicalOrExpression() {
        SpelNodeImpl expr = this.eatLogicalAndExpression();
        while (this.peekIdentifierToken("or") || this.peekToken(TokenKind.SYMBOLIC_OR)) {
            Token t = this.takeToken();
            SpelNodeImpl rhExpr = this.eatLogicalAndExpression();
            this.checkOperands(t, expr, rhExpr);
            expr = new OpOr(t.startPos, t.endPos, expr, rhExpr);
        }
        return expr;
    }

    @Nullable
    private SpelNodeImpl eatLogicalAndExpression() {
        SpelNodeImpl expr = this.eatRelationalExpression();
        while (this.peekIdentifierToken("and") || this.peekToken(TokenKind.SYMBOLIC_AND)) {
            Token t = this.takeToken();
            SpelNodeImpl rhExpr = this.eatRelationalExpression();
            this.checkOperands(t, expr, rhExpr);
            expr = new OpAnd(t.startPos, t.endPos, expr, rhExpr);
        }
        return expr;
    }

    @Nullable
    private SpelNodeImpl eatRelationalExpression() {
        SpelNodeImpl expr = this.eatSumExpression();
        Token relationalOperatorToken = this.maybeEatRelationalOperator();
        if (relationalOperatorToken != null) {
            Token t = this.takeToken();
            SpelNodeImpl rhExpr = this.eatSumExpression();
            this.checkOperands(t, expr, rhExpr);
            TokenKind tk = relationalOperatorToken.kind;
            if (relationalOperatorToken.isNumericRelationalOperator()) {
                if (tk == TokenKind.GT) {
                    return new OpGT(t.startPos, t.endPos, expr, rhExpr);
                }
                if (tk == TokenKind.LT) {
                    return new OpLT(t.startPos, t.endPos, expr, rhExpr);
                }
                if (tk == TokenKind.LE) {
                    return new OpLE(t.startPos, t.endPos, expr, rhExpr);
                }
                if (tk == TokenKind.GE) {
                    return new OpGE(t.startPos, t.endPos, expr, rhExpr);
                }
                if (tk == TokenKind.EQ) {
                    return new OpEQ(t.startPos, t.endPos, expr, rhExpr);
                }
                Assert.isTrue(tk == TokenKind.NE, "Not-equals token expected");
                return new OpNE(t.startPos, t.endPos, expr, rhExpr);
            }
            if (tk == TokenKind.INSTANCEOF) {
                return new OperatorInstanceof(t.startPos, t.endPos, expr, rhExpr);
            }
            if (tk == TokenKind.MATCHES) {
                return new OperatorMatches(t.startPos, t.endPos, expr, rhExpr);
            }
            Assert.isTrue(tk == TokenKind.BETWEEN, "Between token expected");
            return new OperatorBetween(t.startPos, t.endPos, expr, rhExpr);
        }
        return expr;
    }

    @Nullable
    private SpelNodeImpl eatSumExpression() {
        SpelNodeImpl expr = this.eatProductExpression();
        while (this.peekToken(TokenKind.PLUS, TokenKind.MINUS, TokenKind.INC)) {
            Token t = this.takeToken();
            SpelNodeImpl rhExpr = this.eatProductExpression();
            this.checkRightOperand(t, rhExpr);
            if (t.kind == TokenKind.PLUS) {
                expr = new OpPlus(t.startPos, t.endPos, expr, rhExpr);
                continue;
            }
            if (t.kind != TokenKind.MINUS) continue;
            expr = new OpMinus(t.startPos, t.endPos, expr, rhExpr);
        }
        return expr;
    }

    @Nullable
    private SpelNodeImpl eatProductExpression() {
        SpelNodeImpl expr = this.eatPowerIncDecExpression();
        while (this.peekToken(TokenKind.STAR, TokenKind.DIV, TokenKind.MOD)) {
            Token t = this.takeToken();
            SpelNodeImpl rhExpr = this.eatPowerIncDecExpression();
            this.checkOperands(t, expr, rhExpr);
            if (t.kind == TokenKind.STAR) {
                expr = new OpMultiply(t.startPos, t.endPos, expr, rhExpr);
                continue;
            }
            if (t.kind == TokenKind.DIV) {
                expr = new OpDivide(t.startPos, t.endPos, expr, rhExpr);
                continue;
            }
            Assert.isTrue(t.kind == TokenKind.MOD, "Mod token expected");
            expr = new OpModulus(t.startPos, t.endPos, expr, rhExpr);
        }
        return expr;
    }

    @Nullable
    private SpelNodeImpl eatPowerIncDecExpression() {
        SpelNodeImpl expr = this.eatUnaryExpression();
        if (this.peekToken(TokenKind.POWER)) {
            Token t = this.takeToken();
            SpelNodeImpl rhExpr = this.eatUnaryExpression();
            this.checkRightOperand(t, rhExpr);
            return new OperatorPower(t.startPos, t.endPos, expr, rhExpr);
        }
        if (expr != null && this.peekToken(TokenKind.INC, TokenKind.DEC)) {
            Token t = this.takeToken();
            if (t.getKind() == TokenKind.INC) {
                return new OpInc(t.startPos, t.endPos, true, expr);
            }
            return new OpDec(t.startPos, t.endPos, true, expr);
        }
        return expr;
    }

    @Nullable
    private SpelNodeImpl eatUnaryExpression() {
        if (this.peekToken(TokenKind.PLUS, TokenKind.MINUS, TokenKind.NOT)) {
            Token t = this.takeToken();
            SpelNodeImpl expr = this.eatUnaryExpression();
            Assert.state(expr != null, "No node");
            if (t.kind == TokenKind.NOT) {
                return new OperatorNot(t.startPos, t.endPos, expr);
            }
            if (t.kind == TokenKind.PLUS) {
                return new OpPlus(t.startPos, t.endPos, expr);
            }
            Assert.isTrue(t.kind == TokenKind.MINUS, "Minus token expected");
            return new OpMinus(t.startPos, t.endPos, expr);
        }
        if (this.peekToken(TokenKind.INC, TokenKind.DEC)) {
            Token t = this.takeToken();
            SpelNodeImpl expr = this.eatUnaryExpression();
            if (t.getKind() == TokenKind.INC) {
                return new OpInc(t.startPos, t.endPos, false, expr);
            }
            return new OpDec(t.startPos, t.endPos, false, expr);
        }
        return this.eatPrimaryExpression();
    }

    @Nullable
    private SpelNodeImpl eatPrimaryExpression() {
        SpelNodeImpl start = this.eatStartNode();
        ArrayList<SpelNodeImpl> nodes = null;
        SpelNodeImpl node = this.eatNode();
        while (node != null) {
            if (nodes == null) {
                nodes = new ArrayList<SpelNodeImpl>(4);
                nodes.add(start);
            }
            nodes.add(node);
            node = this.eatNode();
        }
        if (start == null || nodes == null) {
            return start;
        }
        return new CompoundExpression(start.getStartPosition(), ((SpelNodeImpl)nodes.get(nodes.size() - 1)).getEndPosition(), nodes.toArray(new SpelNodeImpl[0]));
    }

    @Nullable
    private SpelNodeImpl eatNode() {
        return this.peekToken(TokenKind.DOT, TokenKind.SAFE_NAVI) ? this.eatDottedNode() : this.eatNonDottedNode();
    }

    @Nullable
    private SpelNodeImpl eatNonDottedNode() {
        if (this.peekToken(TokenKind.LSQUARE) && this.maybeEatIndexer()) {
            return this.pop();
        }
        return null;
    }

    private SpelNodeImpl eatDottedNode() {
        boolean nullSafeNavigation;
        Token t = this.takeToken();
        boolean bl = nullSafeNavigation = t.kind == TokenKind.SAFE_NAVI;
        if (this.maybeEatMethodOrProperty(nullSafeNavigation) || this.maybeEatFunctionOrVar() || this.maybeEatProjection(nullSafeNavigation) || this.maybeEatSelection(nullSafeNavigation)) {
            return this.pop();
        }
        if (this.peekToken() == null) {
            throw this.internalException(t.startPos, SpelMessage.OOD, new Object[0]);
        }
        throw this.internalException(t.startPos, SpelMessage.UNEXPECTED_DATA_AFTER_DOT, this.toString(this.peekToken()));
    }

    private boolean maybeEatFunctionOrVar() {
        if (!this.peekToken(TokenKind.HASH)) {
            return false;
        }
        Token t = this.takeToken();
        Token functionOrVariableName = this.eatToken(TokenKind.IDENTIFIER);
        SpelNodeImpl[] args = this.maybeEatMethodArgs();
        if (args == null) {
            this.push(new VariableReference(functionOrVariableName.stringValue(), t.startPos, functionOrVariableName.endPos));
            return true;
        }
        this.push(new FunctionReference(functionOrVariableName.stringValue(), t.startPos, functionOrVariableName.endPos, args));
        return true;
    }

    @Nullable
    private SpelNodeImpl[] maybeEatMethodArgs() {
        if (!this.peekToken(TokenKind.LPAREN)) {
            return null;
        }
        ArrayList<SpelNodeImpl> args = new ArrayList<SpelNodeImpl>();
        this.consumeArguments(args);
        this.eatToken(TokenKind.RPAREN);
        return args.toArray(new SpelNodeImpl[0]);
    }

    private void eatConstructorArgs(List<SpelNodeImpl> accumulatedArguments) {
        if (!this.peekToken(TokenKind.LPAREN)) {
            throw new InternalParseException(new SpelParseException(this.expressionString, this.positionOf(this.peekToken()), SpelMessage.MISSING_CONSTRUCTOR_ARGS, new Object[0]));
        }
        this.consumeArguments(accumulatedArguments);
        this.eatToken(TokenKind.RPAREN);
    }

    private void consumeArguments(List<SpelNodeImpl> accumulatedArguments) {
        Token next;
        Token t = this.peekToken();
        Assert.state(t != null, "Expected token");
        int pos = t.startPos;
        do {
            this.nextToken();
            t = this.peekToken();
            if (t == null) {
                throw this.internalException(pos, SpelMessage.RUN_OUT_OF_ARGUMENTS, new Object[0]);
            }
            if (t.kind == TokenKind.RPAREN) continue;
            accumulatedArguments.add(this.eatExpression());
        } while ((next = this.peekToken()) != null && next.kind == TokenKind.COMMA);
        if (next == null) {
            throw this.internalException(pos, SpelMessage.RUN_OUT_OF_ARGUMENTS, new Object[0]);
        }
    }

    private int positionOf(@Nullable Token t) {
        if (t == null) {
            return this.expressionString.length();
        }
        return t.startPos;
    }

    @Nullable
    private SpelNodeImpl eatStartNode() {
        if (this.maybeEatLiteral()) {
            return this.pop();
        }
        if (this.maybeEatParenExpression()) {
            return this.pop();
        }
        if (this.maybeEatTypeReference() || this.maybeEatNullReference() || this.maybeEatConstructorReference() || this.maybeEatMethodOrProperty(false) || this.maybeEatFunctionOrVar()) {
            return this.pop();
        }
        if (this.maybeEatBeanReference()) {
            return this.pop();
        }
        if (this.maybeEatProjection(false) || this.maybeEatSelection(false) || this.maybeEatIndexer()) {
            return this.pop();
        }
        if (this.maybeEatInlineListOrMap()) {
            return this.pop();
        }
        return null;
    }

    private boolean maybeEatBeanReference() {
        if (this.peekToken(TokenKind.BEAN_REF) || this.peekToken(TokenKind.FACTORY_BEAN_REF)) {
            BeanReference beanReference;
            Token beanRefToken = this.takeToken();
            Token beanNameToken = null;
            String beanName = null;
            if (this.peekToken(TokenKind.IDENTIFIER)) {
                beanNameToken = this.eatToken(TokenKind.IDENTIFIER);
                beanName = beanNameToken.stringValue();
            } else if (this.peekToken(TokenKind.LITERAL_STRING)) {
                beanNameToken = this.eatToken(TokenKind.LITERAL_STRING);
                beanName = beanNameToken.stringValue();
                beanName = beanName.substring(1, beanName.length() - 1);
            } else {
                throw this.internalException(beanRefToken.startPos, SpelMessage.INVALID_BEAN_REFERENCE, new Object[0]);
            }
            if (beanRefToken.getKind() == TokenKind.FACTORY_BEAN_REF) {
                String beanNameString = String.valueOf(TokenKind.FACTORY_BEAN_REF.tokenChars) + beanName;
                beanReference = new BeanReference(beanRefToken.startPos, beanNameToken.endPos, beanNameString);
            } else {
                beanReference = new BeanReference(beanNameToken.startPos, beanNameToken.endPos, beanName);
            }
            this.constructedNodes.push(beanReference);
            return true;
        }
        return false;
    }

    private boolean maybeEatTypeReference() {
        if (this.peekToken(TokenKind.IDENTIFIER)) {
            Token typeName = this.peekToken();
            Assert.state(typeName != null, "Expected token");
            if (!"T".equals(typeName.stringValue())) {
                return false;
            }
            Token t = this.takeToken();
            if (this.peekToken(TokenKind.RSQUARE)) {
                this.push(new PropertyOrFieldReference(false, t.stringValue(), t.startPos, t.endPos));
                return true;
            }
            this.eatToken(TokenKind.LPAREN);
            SpelNodeImpl node = this.eatPossiblyQualifiedId();
            int dims = 0;
            while (this.peekToken(TokenKind.LSQUARE, true)) {
                this.eatToken(TokenKind.RSQUARE);
                ++dims;
            }
            this.eatToken(TokenKind.RPAREN);
            this.constructedNodes.push(new TypeReference(typeName.startPos, typeName.endPos, node, dims));
            return true;
        }
        return false;
    }

    private boolean maybeEatNullReference() {
        if (this.peekToken(TokenKind.IDENTIFIER)) {
            Token nullToken = this.peekToken();
            Assert.state(nullToken != null, "Expected token");
            if (!"null".equalsIgnoreCase(nullToken.stringValue())) {
                return false;
            }
            this.nextToken();
            this.constructedNodes.push(new NullLiteral(nullToken.startPos, nullToken.endPos));
            return true;
        }
        return false;
    }

    private boolean maybeEatProjection(boolean nullSafeNavigation) {
        Token t = this.peekToken();
        if (!this.peekToken(TokenKind.PROJECT, true)) {
            return false;
        }
        Assert.state(t != null, "No token");
        SpelNodeImpl expr = this.eatExpression();
        Assert.state(expr != null, "No node");
        this.eatToken(TokenKind.RSQUARE);
        this.constructedNodes.push(new Projection(nullSafeNavigation, t.startPos, t.endPos, expr));
        return true;
    }

    private boolean maybeEatInlineListOrMap() {
        Token t = this.peekToken();
        if (!this.peekToken(TokenKind.LCURLY, true)) {
            return false;
        }
        Assert.state(t != null, "No token");
        SpelNodeImpl expr = null;
        Token closingCurly = this.peekToken();
        if (this.peekToken(TokenKind.RCURLY, true)) {
            Assert.state(closingCurly != null, "No token");
            expr = new InlineList(t.startPos, closingCurly.endPos, new SpelNodeImpl[0]);
        } else if (this.peekToken(TokenKind.COLON, true)) {
            closingCurly = this.eatToken(TokenKind.RCURLY);
            expr = new InlineMap(t.startPos, closingCurly.endPos, new SpelNodeImpl[0]);
        } else {
            SpelNodeImpl firstExpression = this.eatExpression();
            if (this.peekToken(TokenKind.RCURLY)) {
                ArrayList<SpelNodeImpl> elements = new ArrayList<SpelNodeImpl>();
                elements.add(firstExpression);
                closingCurly = this.eatToken(TokenKind.RCURLY);
                expr = new InlineList(t.startPos, closingCurly.endPos, elements.toArray(new SpelNodeImpl[0]));
            } else if (this.peekToken(TokenKind.COMMA, true)) {
                ArrayList<SpelNodeImpl> elements = new ArrayList<SpelNodeImpl>();
                elements.add(firstExpression);
                do {
                    elements.add(this.eatExpression());
                } while (this.peekToken(TokenKind.COMMA, true));
                closingCurly = this.eatToken(TokenKind.RCURLY);
                expr = new InlineList(t.startPos, closingCurly.endPos, elements.toArray(new SpelNodeImpl[0]));
            } else if (this.peekToken(TokenKind.COLON, true)) {
                ArrayList<SpelNodeImpl> elements = new ArrayList<SpelNodeImpl>();
                elements.add(firstExpression);
                elements.add(this.eatExpression());
                while (this.peekToken(TokenKind.COMMA, true)) {
                    elements.add(this.eatExpression());
                    this.eatToken(TokenKind.COLON);
                    elements.add(this.eatExpression());
                }
                closingCurly = this.eatToken(TokenKind.RCURLY);
                expr = new InlineMap(t.startPos, closingCurly.endPos, elements.toArray(new SpelNodeImpl[0]));
            } else {
                throw this.internalException(t.startPos, SpelMessage.OOD, new Object[0]);
            }
        }
        this.constructedNodes.push(expr);
        return true;
    }

    private boolean maybeEatIndexer() {
        Token t = this.peekToken();
        if (!this.peekToken(TokenKind.LSQUARE, true)) {
            return false;
        }
        Assert.state(t != null, "No token");
        SpelNodeImpl expr = this.eatExpression();
        Assert.state(expr != null, "No node");
        this.eatToken(TokenKind.RSQUARE);
        this.constructedNodes.push(new Indexer(t.startPos, t.endPos, expr));
        return true;
    }

    private boolean maybeEatSelection(boolean nullSafeNavigation) {
        Token t = this.peekToken();
        if (!this.peekSelectToken()) {
            return false;
        }
        Assert.state(t != null, "No token");
        this.nextToken();
        SpelNodeImpl expr = this.eatExpression();
        if (expr == null) {
            throw this.internalException(t.startPos, SpelMessage.MISSING_SELECTION_EXPRESSION, new Object[0]);
        }
        this.eatToken(TokenKind.RSQUARE);
        if (t.kind == TokenKind.SELECT_FIRST) {
            this.constructedNodes.push(new Selection(nullSafeNavigation, 1, t.startPos, t.endPos, expr));
        } else if (t.kind == TokenKind.SELECT_LAST) {
            this.constructedNodes.push(new Selection(nullSafeNavigation, 2, t.startPos, t.endPos, expr));
        } else {
            this.constructedNodes.push(new Selection(nullSafeNavigation, 0, t.startPos, t.endPos, expr));
        }
        return true;
    }

    private SpelNodeImpl eatPossiblyQualifiedId() {
        ArrayDeque<Identifier> qualifiedIdPieces = new ArrayDeque<Identifier>();
        Token node = this.peekToken();
        while (this.isValidQualifiedId(node)) {
            this.nextToken();
            if (node.kind != TokenKind.DOT) {
                qualifiedIdPieces.add(new Identifier(node.stringValue(), node.startPos, node.endPos));
            }
            node = this.peekToken();
        }
        if (qualifiedIdPieces.isEmpty()) {
            if (node == null) {
                throw this.internalException(this.expressionString.length(), SpelMessage.OOD, new Object[0]);
            }
            throw this.internalException(node.startPos, SpelMessage.NOT_EXPECTED_TOKEN, "qualified ID", node.getKind().toString().toLowerCase());
        }
        return new QualifiedIdentifier(((SpelNodeImpl)qualifiedIdPieces.getFirst()).getStartPosition(), ((SpelNodeImpl)qualifiedIdPieces.getLast()).getEndPosition(), qualifiedIdPieces.toArray(new SpelNodeImpl[0]));
    }

    private boolean isValidQualifiedId(@Nullable Token node) {
        if (node == null || node.kind == TokenKind.LITERAL_STRING) {
            return false;
        }
        if (node.kind == TokenKind.DOT || node.kind == TokenKind.IDENTIFIER) {
            return true;
        }
        String value = node.stringValue();
        return StringUtils.hasLength(value) && VALID_QUALIFIED_ID_PATTERN.matcher(value).matches();
    }

    private boolean maybeEatMethodOrProperty(boolean nullSafeNavigation) {
        if (this.peekToken(TokenKind.IDENTIFIER)) {
            Token methodOrPropertyName = this.takeToken();
            SpelNodeImpl[] args = this.maybeEatMethodArgs();
            if (args == null) {
                this.push(new PropertyOrFieldReference(nullSafeNavigation, methodOrPropertyName.stringValue(), methodOrPropertyName.startPos, methodOrPropertyName.endPos));
                return true;
            }
            this.push(new MethodReference(nullSafeNavigation, methodOrPropertyName.stringValue(), methodOrPropertyName.startPos, methodOrPropertyName.endPos, args));
            return true;
        }
        return false;
    }

    private boolean maybeEatConstructorReference() {
        if (this.peekIdentifierToken("new")) {
            Token newToken = this.takeToken();
            if (this.peekToken(TokenKind.RSQUARE)) {
                this.push(new PropertyOrFieldReference(false, newToken.stringValue(), newToken.startPos, newToken.endPos));
                return true;
            }
            SpelNodeImpl possiblyQualifiedConstructorName = this.eatPossiblyQualifiedId();
            ArrayList<SpelNodeImpl> nodes = new ArrayList<SpelNodeImpl>();
            nodes.add(possiblyQualifiedConstructorName);
            if (this.peekToken(TokenKind.LSQUARE)) {
                ArrayList<SpelNodeImpl> dimensions = new ArrayList<SpelNodeImpl>();
                while (this.peekToken(TokenKind.LSQUARE, true)) {
                    if (!this.peekToken(TokenKind.RSQUARE)) {
                        dimensions.add(this.eatExpression());
                    } else {
                        dimensions.add(null);
                    }
                    this.eatToken(TokenKind.RSQUARE);
                }
                if (this.maybeEatInlineListOrMap()) {
                    nodes.add(this.pop());
                }
                this.push(new ConstructorReference(newToken.startPos, newToken.endPos, dimensions.toArray(new SpelNodeImpl[0]), nodes.toArray(new SpelNodeImpl[0])));
            } else {
                this.eatConstructorArgs(nodes);
                this.push(new ConstructorReference(newToken.startPos, newToken.endPos, nodes.toArray(new SpelNodeImpl[0])));
            }
            return true;
        }
        return false;
    }

    private void push(SpelNodeImpl newNode) {
        this.constructedNodes.push(newNode);
    }

    private SpelNodeImpl pop() {
        return this.constructedNodes.pop();
    }

    private boolean maybeEatLiteral() {
        Token t = this.peekToken();
        if (t == null) {
            return false;
        }
        if (t.kind == TokenKind.LITERAL_INT) {
            this.push(Literal.getIntLiteral(t.stringValue(), t.startPos, t.endPos, 10));
        } else if (t.kind == TokenKind.LITERAL_LONG) {
            this.push(Literal.getLongLiteral(t.stringValue(), t.startPos, t.endPos, 10));
        } else if (t.kind == TokenKind.LITERAL_HEXINT) {
            this.push(Literal.getIntLiteral(t.stringValue(), t.startPos, t.endPos, 16));
        } else if (t.kind == TokenKind.LITERAL_HEXLONG) {
            this.push(Literal.getLongLiteral(t.stringValue(), t.startPos, t.endPos, 16));
        } else if (t.kind == TokenKind.LITERAL_REAL) {
            this.push(Literal.getRealLiteral(t.stringValue(), t.startPos, t.endPos, false));
        } else if (t.kind == TokenKind.LITERAL_REAL_FLOAT) {
            this.push(Literal.getRealLiteral(t.stringValue(), t.startPos, t.endPos, true));
        } else if (this.peekIdentifierToken("true")) {
            this.push(new BooleanLiteral(t.stringValue(), t.startPos, t.endPos, true));
        } else if (this.peekIdentifierToken("false")) {
            this.push(new BooleanLiteral(t.stringValue(), t.startPos, t.endPos, false));
        } else if (t.kind == TokenKind.LITERAL_STRING) {
            this.push(new StringLiteral(t.stringValue(), t.startPos, t.endPos, t.stringValue()));
        } else {
            return false;
        }
        this.nextToken();
        return true;
    }

    private boolean maybeEatParenExpression() {
        if (this.peekToken(TokenKind.LPAREN)) {
            this.nextToken();
            SpelNodeImpl expr = this.eatExpression();
            Assert.state(expr != null, "No node");
            this.eatToken(TokenKind.RPAREN);
            this.push(expr);
            return true;
        }
        return false;
    }

    @Nullable
    private Token maybeEatRelationalOperator() {
        Token t = this.peekToken();
        if (t == null) {
            return null;
        }
        if (t.isNumericRelationalOperator()) {
            return t;
        }
        if (t.isIdentifier()) {
            String idString = t.stringValue();
            if (idString.equalsIgnoreCase("instanceof")) {
                return t.asInstanceOfToken();
            }
            if (idString.equalsIgnoreCase("matches")) {
                return t.asMatchesToken();
            }
            if (idString.equalsIgnoreCase("between")) {
                return t.asBetweenToken();
            }
        }
        return null;
    }

    private Token eatToken(TokenKind expectedKind) {
        Token t = this.nextToken();
        if (t == null) {
            int pos = this.expressionString.length();
            throw this.internalException(pos, SpelMessage.OOD, new Object[0]);
        }
        if (t.kind != expectedKind) {
            throw this.internalException(t.startPos, SpelMessage.NOT_EXPECTED_TOKEN, expectedKind.toString().toLowerCase(), t.getKind().toString().toLowerCase());
        }
        return t;
    }

    private boolean peekToken(TokenKind desiredTokenKind) {
        return this.peekToken(desiredTokenKind, false);
    }

    private boolean peekToken(TokenKind desiredTokenKind, boolean consumeIfMatched) {
        Token t = this.peekToken();
        if (t == null) {
            return false;
        }
        if (t.kind == desiredTokenKind) {
            if (consumeIfMatched) {
                ++this.tokenStreamPointer;
            }
            return true;
        }
        return desiredTokenKind == TokenKind.IDENTIFIER && t.kind.ordinal() >= TokenKind.DIV.ordinal() && t.kind.ordinal() <= TokenKind.NOT.ordinal() && t.data != null;
    }

    private boolean peekToken(TokenKind possible1, TokenKind possible2) {
        Token t = this.peekToken();
        if (t == null) {
            return false;
        }
        return t.kind == possible1 || t.kind == possible2;
    }

    private boolean peekToken(TokenKind possible1, TokenKind possible2, TokenKind possible3) {
        Token t = this.peekToken();
        if (t == null) {
            return false;
        }
        return t.kind == possible1 || t.kind == possible2 || t.kind == possible3;
    }

    private boolean peekIdentifierToken(String identifierString) {
        Token t = this.peekToken();
        if (t == null) {
            return false;
        }
        return t.kind == TokenKind.IDENTIFIER && identifierString.equalsIgnoreCase(t.stringValue());
    }

    private boolean peekSelectToken() {
        Token t = this.peekToken();
        if (t == null) {
            return false;
        }
        return t.kind == TokenKind.SELECT || t.kind == TokenKind.SELECT_FIRST || t.kind == TokenKind.SELECT_LAST;
    }

    private Token takeToken() {
        if (this.tokenStreamPointer >= this.tokenStreamLength) {
            throw new IllegalStateException("No token");
        }
        return this.tokenStream.get(this.tokenStreamPointer++);
    }

    @Nullable
    private Token nextToken() {
        if (this.tokenStreamPointer >= this.tokenStreamLength) {
            return null;
        }
        return this.tokenStream.get(this.tokenStreamPointer++);
    }

    @Nullable
    private Token peekToken() {
        if (this.tokenStreamPointer >= this.tokenStreamLength) {
            return null;
        }
        return this.tokenStream.get(this.tokenStreamPointer);
    }

    public String toString(@Nullable Token t) {
        if (t == null) {
            return "";
        }
        if (t.getKind().hasPayload()) {
            return t.stringValue();
        }
        return t.kind.toString().toLowerCase();
    }

    private void checkOperands(Token token, @Nullable SpelNodeImpl left, @Nullable SpelNodeImpl right) {
        this.checkLeftOperand(token, left);
        this.checkRightOperand(token, right);
    }

    private void checkLeftOperand(Token token, @Nullable SpelNodeImpl operandExpression) {
        if (operandExpression == null) {
            throw this.internalException(token.startPos, SpelMessage.LEFT_OPERAND_PROBLEM, new Object[0]);
        }
    }

    private void checkRightOperand(Token token, @Nullable SpelNodeImpl operandExpression) {
        if (operandExpression == null) {
            throw this.internalException(token.startPos, SpelMessage.RIGHT_OPERAND_PROBLEM, new Object[0]);
        }
    }

    private InternalParseException internalException(int startPos, SpelMessage message, Object ... inserts) {
        return new InternalParseException(new SpelParseException(this.expressionString, startPos, message, inserts));
    }
}

