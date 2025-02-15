/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.printer.lexicalpreservation;

import com.github.javaparser.JavaToken;
import com.github.javaparser.TokenRange;
import com.github.javaparser.TokenTypes;
import com.github.javaparser.ast.Node;
import com.github.javaparser.printer.concretesyntaxmodel.CsmToken;
import com.github.javaparser.printer.lexicalpreservation.LexicalDifferenceCalculator;
import com.github.javaparser.printer.lexicalpreservation.Removed;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

final class RemovedGroup
implements Iterable<Removed> {
    private final Integer firstElementIndex;
    private final List<Removed> removedList;
    private boolean isProcessed = false;
    private final Function<JavaToken, Boolean> hasOnlyWhitespaceJavaTokenInFrontFunction = begin -> this.hasOnlyWhiteSpaceForTokenFunction((JavaToken)begin, token -> token.getPreviousToken());
    private final Function<JavaToken, Boolean> hasOnlyWhitespaceJavaTokenBehindFunction = end -> this.hasOnlyWhiteSpaceForTokenFunction((JavaToken)end, token -> token.getNextToken());
    private final Function<TokenRange, Boolean> hasOnlyWhitespaceInFrontFunction = tokenRange -> this.hasOnlyWhitespaceJavaTokenInFrontFunction.apply(tokenRange.getBegin());
    private final Function<TokenRange, Boolean> hasOnlyWhitespaceBehindFunction = tokenRange -> this.hasOnlyWhitespaceJavaTokenBehindFunction.apply(tokenRange.getEnd());

    private RemovedGroup(Integer firstElementIndex, List<Removed> removedList) {
        if (firstElementIndex == null) {
            throw new IllegalArgumentException("firstElementIndex should not be null");
        }
        if (removedList == null || removedList.isEmpty()) {
            throw new IllegalArgumentException("removedList should not be null or empty");
        }
        this.firstElementIndex = firstElementIndex;
        this.removedList = removedList;
    }

    public static RemovedGroup of(Integer firstElementIndex, List<Removed> removedList) {
        return new RemovedGroup(firstElementIndex, removedList);
    }

    final void processed() {
        this.isProcessed = true;
    }

    final boolean isProcessed() {
        return this.isProcessed;
    }

    private List<Integer> getIndicesBeingRemoved() {
        return IntStream.range(this.firstElementIndex, this.firstElementIndex + this.removedList.size()).boxed().collect(Collectors.toList());
    }

    final Integer getLastElementIndex() {
        List<Integer> indicesBeingRemoved = this.getIndicesBeingRemoved();
        return indicesBeingRemoved.get(indicesBeingRemoved.size() - 1);
    }

    final Removed getFirstElement() {
        return this.removedList.get(0);
    }

    final Removed getLastElement() {
        return this.removedList.get(this.removedList.size() - 1);
    }

    final boolean isACompleteLine() {
        return this.hasOnlyWhitespace(this.getFirstElement(), this.hasOnlyWhitespaceInFrontFunction) && this.hasOnlyWhitespace(this.getLastElement(), this.hasOnlyWhitespaceBehindFunction);
    }

    private boolean hasOnlyWhitespace(Removed startElement, Function<TokenRange, Boolean> hasOnlyWhitespaceFunction) {
        CsmToken token;
        boolean hasOnlyWhitespace = false;
        if (startElement.isChild()) {
            LexicalDifferenceCalculator.CsmChild csmChild = (LexicalDifferenceCalculator.CsmChild)startElement.getElement();
            Node child = csmChild.getChild();
            Optional<TokenRange> tokenRange = child.getTokenRange();
            if (tokenRange.isPresent()) {
                hasOnlyWhitespace = hasOnlyWhitespaceFunction.apply(tokenRange.get());
            }
        } else if (startElement.isToken() && TokenTypes.isEndOfLineToken((token = (CsmToken)startElement.getElement()).getTokenType())) {
            hasOnlyWhitespace = true;
        }
        return hasOnlyWhitespace;
    }

    private boolean hasOnlyWhiteSpaceForTokenFunction(JavaToken token, Function<JavaToken, Optional<JavaToken>> tokenFunction) {
        Optional<JavaToken> tokenResult = tokenFunction.apply(token);
        if (tokenResult.isPresent()) {
            if (TokenTypes.isWhitespaceButNotEndOfLine(tokenResult.get().getKind())) {
                return this.hasOnlyWhiteSpaceForTokenFunction(tokenResult.get(), tokenFunction);
            }
            return TokenTypes.isEndOfLineToken(tokenResult.get().getKind());
        }
        return true;
    }

    final Optional<Integer> getIndentation() {
        JavaToken begin;
        LexicalDifferenceCalculator.CsmChild csmChild;
        Node child;
        Optional<TokenRange> tokenRange;
        Removed firstElement = this.getFirstElement();
        int indentation = 0;
        if (firstElement.isChild() && (tokenRange = (child = (csmChild = (LexicalDifferenceCalculator.CsmChild)firstElement.getElement()).getChild()).getTokenRange()).isPresent() && this.hasOnlyWhitespaceJavaTokenInFrontFunction.apply(begin = tokenRange.get().getBegin()).booleanValue()) {
            Optional<JavaToken> previousToken = begin.getPreviousToken();
            while (previousToken.isPresent() && TokenTypes.isWhitespaceButNotEndOfLine(previousToken.get().getKind())) {
                ++indentation;
                previousToken = previousToken.get().getPreviousToken();
            }
            if (previousToken.isPresent()) {
                if (TokenTypes.isEndOfLineToken(previousToken.get().getKind())) {
                    return Optional.of(indentation);
                }
                return Optional.empty();
            }
            return Optional.of(indentation);
        }
        return Optional.empty();
    }

    @Override
    public final Iterator<Removed> iterator() {
        return new Iterator<Removed>(){
            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return this.currentIndex < RemovedGroup.this.removedList.size() && RemovedGroup.this.removedList.get(this.currentIndex) != null;
            }

            @Override
            public Removed next() {
                return (Removed)RemovedGroup.this.removedList.get(this.currentIndex++);
            }
        };
    }
}

