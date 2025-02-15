/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.ast.expr;

import com.github.javaparser.TokenRange;
import com.github.javaparser.ast.AllFieldsConstructor;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.expr.LiteralStringValueExpr;
import com.github.javaparser.ast.visitor.CloneVisitor;
import com.github.javaparser.ast.visitor.GenericVisitor;
import com.github.javaparser.ast.visitor.VoidVisitor;
import com.github.javaparser.metamodel.JavaParserMetaModel;
import com.github.javaparser.metamodel.TextBlockLiteralExprMetaModel;
import com.github.javaparser.utils.Pair;
import com.github.javaparser.utils.StringEscapeUtils;
import java.util.Arrays;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class TextBlockLiteralExpr
extends LiteralStringValueExpr {
    public TextBlockLiteralExpr() {
        this(null, "empty");
    }

    @AllFieldsConstructor
    public TextBlockLiteralExpr(String value) {
        this(null, value);
    }

    public TextBlockLiteralExpr(TokenRange tokenRange, String value) {
        super(tokenRange, value);
        this.customInitialization();
    }

    @Override
    public <R, A> R accept(GenericVisitor<R, A> v, A arg) {
        return v.visit(this, arg);
    }

    @Override
    public <A> void accept(VoidVisitor<A> v, A arg) {
        v.visit(this, arg);
    }

    @Override
    public boolean remove(Node node) {
        if (node == null) {
            return false;
        }
        return super.remove(node);
    }

    @Override
    public boolean isTextBlockLiteralExpr() {
        return true;
    }

    @Override
    public TextBlockLiteralExpr asTextBlockLiteralExpr() {
        return this;
    }

    @Override
    public Optional<TextBlockLiteralExpr> toTextBlockLiteralExpr() {
        return Optional.of(this);
    }

    @Override
    public void ifTextBlockLiteralExpr(Consumer<TextBlockLiteralExpr> action) {
        action.accept(this);
    }

    @Override
    public boolean replace(Node node, Node replacementNode) {
        if (node == null) {
            return false;
        }
        return super.replace(node, replacementNode);
    }

    @Override
    public TextBlockLiteralExpr clone() {
        return (TextBlockLiteralExpr)this.accept(new CloneVisitor(), null);
    }

    @Override
    public TextBlockLiteralExprMetaModel getMetaModel() {
        return JavaParserMetaModel.textBlockLiteralExprMetaModel;
    }

    public Stream<String> stripIndentOfLines() {
        String[] rawLines = this.getValue().split("\\R", -1);
        int commonWhiteSpacePrefixSize = IntStream.range(0, rawLines.length).mapToObj(nr -> new Pair<Integer, String>(nr, rawLines[nr])).filter(l -> !this.emptyOrWhitespace((String)l.b) || this.isLastLine(rawLines, (Integer)l.a)).map(l -> this.indentSize((String)l.b)).min(Integer::compare).orElse(0);
        return Arrays.stream(rawLines).map(l -> l.substring(commonWhiteSpacePrefixSize)).map(this::trimTrailing);
    }

    public String stripIndent() {
        return this.stripIndentOfLines().collect(Collectors.joining("\n"));
    }

    public String translateEscapes() {
        return StringEscapeUtils.unescapeJavaTextBlock(this.stripIndent());
    }

    public String asString() {
        return this.translateEscapes();
    }

    private boolean isLastLine(String[] rawLines, Integer lineNr) {
        return lineNr == rawLines.length - 1;
    }

    private boolean emptyOrWhitespace(String rawLine) {
        return rawLine.trim().isEmpty();
    }

    private int indentSize(String s) {
        String content = s.trim();
        if (content.isEmpty()) {
            return s.length();
        }
        return s.indexOf(content);
    }

    private String trimTrailing(String source) {
        int pos;
        for (pos = source.length() - 1; pos >= 0 && Character.isWhitespace(source.charAt(pos)); --pos) {
        }
        return ++pos < source.length() ? source.substring(0, pos) : source;
    }
}

