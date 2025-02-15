/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser;

import com.github.javaparser.GeneratedJavaParserTokenManager;
import com.github.javaparser.JavaToken;
import com.github.javaparser.ParseException;
import com.github.javaparser.Problem;
import com.github.javaparser.Provider;
import com.github.javaparser.Token;
import com.github.javaparser.TokenRange;
import com.github.javaparser.ast.ArrayCreationLevel;
import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.comments.CommentsCollection;
import com.github.javaparser.ast.expr.AnnotationExpr;
import com.github.javaparser.ast.expr.ArrayCreationExpr;
import com.github.javaparser.ast.expr.ArrayInitializerExpr;
import com.github.javaparser.ast.expr.CastExpr;
import com.github.javaparser.ast.expr.EnclosedExpr;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.FieldAccessExpr;
import com.github.javaparser.ast.expr.LambdaExpr;
import com.github.javaparser.ast.expr.Name;
import com.github.javaparser.ast.expr.NameExpr;
import com.github.javaparser.ast.expr.SimpleName;
import com.github.javaparser.ast.stmt.Statement;
import com.github.javaparser.ast.type.ArrayType;
import com.github.javaparser.ast.type.Type;
import com.github.javaparser.ast.type.UnknownType;
import com.github.javaparser.utils.Pair;
import com.github.javaparser.utils.Utils;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;

abstract class GeneratedJavaParserBase {
    List<Problem> problems = new ArrayList<Problem>();
    boolean storeTokens;

    GeneratedJavaParserBase() {
    }

    abstract GeneratedJavaParserTokenManager getTokenSource();

    abstract void ReInit(Provider var1);

    abstract JavaToken token();

    abstract Token getNextToken();

    void reset(Provider provider) {
        this.ReInit(provider);
        this.problems = new ArrayList<Problem>();
        this.getTokenSource().reset();
    }

    public List<JavaToken> getTokens() {
        return this.getTokenSource().getTokens();
    }

    CommentsCollection getCommentsCollection() {
        return this.getTokenSource().getCommentsCollection();
    }

    void addProblem(String message) {
        this.problems.add(new Problem(message, this.tokenRange(), null));
    }

    TokenRange tokenRange() {
        if (this.storeTokens) {
            return new TokenRange(this.token(), this.token());
        }
        return null;
    }

    TokenRange range(JavaToken begin, JavaToken end) {
        if (this.storeTokens) {
            return new TokenRange(begin, end);
        }
        return null;
    }

    TokenRange range(Node begin, JavaToken end) {
        if (this.storeTokens) {
            return new TokenRange(begin.getTokenRange().get().getBegin(), end);
        }
        return null;
    }

    TokenRange range(JavaToken begin, Node end) {
        if (this.storeTokens) {
            return new TokenRange(begin, end.getTokenRange().get().getEnd());
        }
        return null;
    }

    TokenRange range(Node begin, Node end) {
        if (this.storeTokens) {
            return new TokenRange(begin.getTokenRange().get().getBegin(), end.getTokenRange().get().getEnd());
        }
        return null;
    }

    JavaToken orIfInvalid(JavaToken firstChoice, JavaToken secondChoice) {
        if (this.storeTokens) {
            Utils.assertNotNull(firstChoice);
            Utils.assertNotNull(secondChoice);
            if (firstChoice.valid() || secondChoice.invalid()) {
                return firstChoice;
            }
            return secondChoice;
        }
        return null;
    }

    JavaToken orIfInvalid(JavaToken firstChoice, Node secondChoice) {
        if (this.storeTokens) {
            return this.orIfInvalid(firstChoice, secondChoice.getTokenRange().get().getBegin());
        }
        return null;
    }

    JavaToken nodeListBegin(NodeList<?> l) {
        if (!this.storeTokens || l.isEmpty()) {
            return JavaToken.INVALID;
        }
        return ((Node)l.get(0)).getTokenRange().get().getBegin();
    }

    void setTokenKind(int newKind) {
        this.token().setKind(newKind);
    }

    void setStoreTokens(boolean storeTokens) {
        this.storeTokens = storeTokens;
        this.getTokenSource().setStoreTokens(storeTokens);
    }

    TokenRange recover(int recoveryTokenType, ParseException p) {
        Token t;
        JavaToken begin = null;
        if (p.currentToken != null) {
            begin = this.token();
        }
        do {
            t = this.getNextToken();
        } while (t.kind != recoveryTokenType && t.kind != 0);
        JavaToken end = this.token();
        TokenRange tokenRange = null;
        if (begin != null && end != null) {
            tokenRange = this.range(begin, end);
        }
        this.problems.add(new Problem(this.makeMessageForParseException(p), tokenRange, p));
        return tokenRange;
    }

    <T extends Node> NodeList<T> emptyNodeList() {
        return new NodeList();
    }

    <T extends Node> NodeList<T> add(NodeList<T> list, T obj) {
        if (list == null) {
            list = new NodeList();
        }
        list.add((Object)obj);
        return list;
    }

    <T extends Node> NodeList<T> addWhenNotNull(NodeList<T> list, T obj) {
        if (obj == null) {
            return list;
        }
        return this.add(list, obj);
    }

    <T extends Node> NodeList<T> prepend(NodeList<T> list, T obj) {
        if (list == null) {
            list = new NodeList();
        }
        list.addFirst(obj);
        return list;
    }

    <T> List<T> add(List<T> list, T obj) {
        if (list == null) {
            list = new LinkedList<T>();
        }
        list.add(obj);
        return list;
    }

    private void propagateRangeGrowthOnRight(Node node, Node endNode) {
        if (this.storeTokens) {
            node.getParentNode().ifPresent(nodeParent -> {
                boolean isChildOnTheRightBorderOfParent = node.getTokenRange().get().getEnd().equals(nodeParent.getTokenRange().get().getEnd());
                if (isChildOnTheRightBorderOfParent) {
                    this.propagateRangeGrowthOnRight((Node)nodeParent, endNode);
                }
            });
            node.setTokenRange(this.range(node, endNode));
        }
    }

    Expression generateLambda(Expression ret, Statement lambdaBody) {
        if (ret instanceof EnclosedExpr) {
            Expression inner = ((EnclosedExpr)ret).getInner();
            SimpleName id = ((NameExpr)inner).getName();
            NodeList<Parameter> params = this.add(new NodeList(), (Node)new Parameter(ret.getTokenRange().orElse(null), new NodeList<Modifier>(), new NodeList<AnnotationExpr>(), new UnknownType(), false, new NodeList<AnnotationExpr>(), id));
            ret = new LambdaExpr(this.range((Node)ret, (Node)lambdaBody), params, lambdaBody, true);
        } else if (ret instanceof NameExpr) {
            SimpleName id = ((NameExpr)ret).getName();
            NodeList<Parameter> params = this.add(new NodeList(), (Node)new Parameter(ret.getTokenRange().orElse(null), new NodeList<Modifier>(), new NodeList<AnnotationExpr>(), new UnknownType(), false, new NodeList<AnnotationExpr>(), id));
            ret = new LambdaExpr(this.range((Node)ret, (Node)lambdaBody), params, lambdaBody, false);
        } else if (ret instanceof LambdaExpr) {
            ((LambdaExpr)ret).setBody(lambdaBody);
            this.propagateRangeGrowthOnRight(ret, lambdaBody);
        } else if (ret instanceof CastExpr) {
            CastExpr castExpr = (CastExpr)ret;
            Expression inner = this.generateLambda(castExpr.getExpression(), lambdaBody);
            castExpr.setExpression(inner);
        } else {
            this.addProblem("Failed to parse lambda expression! Please create an issue at https://github.com/javaparser/javaparser/issues");
        }
        return ret;
    }

    ArrayCreationExpr juggleArrayCreation(TokenRange range, List<TokenRange> levelRanges, Type type, NodeList<Expression> dimensions, List<NodeList<AnnotationExpr>> arrayAnnotations, ArrayInitializerExpr arrayInitializerExpr) {
        NodeList<ArrayCreationLevel> levels = new NodeList<ArrayCreationLevel>();
        for (int i = 0; i < arrayAnnotations.size(); ++i) {
            levels.add(new ArrayCreationLevel(levelRanges.get(i), (Expression)dimensions.get(i), arrayAnnotations.get(i)));
        }
        return new ArrayCreationExpr(range, type, levels, arrayInitializerExpr);
    }

    Type juggleArrayType(Type partialType, List<ArrayType.ArrayBracketPair> additionalBrackets) {
        Pair<Type, List<ArrayType.ArrayBracketPair>> partialParts = ArrayType.unwrapArrayTypes(partialType);
        Type elementType = (Type)partialParts.a;
        List leftMostBrackets = (List)partialParts.b;
        return ArrayType.wrapInArrayTypes(elementType, additionalBrackets, leftMostBrackets).clone();
    }

    private String makeMessageForParseException(ParseException exception) {
        StringBuilder sb = new StringBuilder("Parse error. Found ");
        StringBuilder expected = new StringBuilder();
        int maxExpectedTokenSequenceLength = 0;
        TreeSet<String> sortedOptions = new TreeSet<String>();
        for (int i = 0; i < exception.expectedTokenSequences.length; ++i) {
            if (maxExpectedTokenSequenceLength < exception.expectedTokenSequences[i].length) {
                maxExpectedTokenSequenceLength = exception.expectedTokenSequences[i].length;
            }
            for (int j = 0; j < exception.expectedTokenSequences[i].length; ++j) {
                sortedOptions.add(exception.tokenImage[exception.expectedTokenSequences[i][j]]);
            }
        }
        for (String option : sortedOptions) {
            expected.append(" ").append(option);
        }
        Token token = exception.currentToken.next;
        for (int i = 0; i < maxExpectedTokenSequenceLength; ++i) {
            String tokenText = token.image;
            String escapedTokenText = ParseException.add_escapes(tokenText);
            if (i != 0) {
                sb.append(" ");
            }
            if (token.kind == 0) {
                sb.append(exception.tokenImage[0]);
                break;
            }
            String image = exception.tokenImage[token.kind];
            escapedTokenText = "\"" + escapedTokenText + "\"";
            if (image.equals(escapedTokenText)) {
                sb.append(image);
            } else {
                sb.append(" ").append(escapedTokenText).append(" ").append(image);
            }
            token = token.next;
        }
        if (exception.expectedTokenSequences.length != 0) {
            int numExpectedTokens = exception.expectedTokenSequences.length;
            sb.append(", expected").append(numExpectedTokens == 1 ? "" : " one of ").append(expected.toString());
        }
        return sb.toString();
    }

    Name scopeToName(Expression scope) {
        if (scope.isNameExpr()) {
            SimpleName simpleName = scope.asNameExpr().getName();
            return new Name(simpleName.getTokenRange().get(), null, simpleName.getIdentifier());
        }
        if (scope.isFieldAccessExpr()) {
            FieldAccessExpr fieldAccessExpr = scope.asFieldAccessExpr();
            return new Name(fieldAccessExpr.getTokenRange().get(), this.scopeToName(fieldAccessExpr.getScope()), fieldAccessExpr.getName().getIdentifier());
        }
        throw new IllegalStateException("Unexpected expression type: " + scope.getClass().getSimpleName());
    }

    String unquote(String s) {
        return s.substring(1, s.length() - 1);
    }

    String unTripleQuote(String s) {
        int start = 3;
        if (s.charAt(start) == '\r') {
            ++start;
        }
        if (s.charAt(start) == '\n') {
            ++start;
        }
        return s.substring(start, s.length() - 3);
    }

    void setYieldSupported() {
        this.getTokenSource().setYieldSupported();
    }
}

