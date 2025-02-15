/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser;

import com.github.javaparser.JavaToken;
import com.github.javaparser.Range;
import com.github.javaparser.utils.Utils;
import java.util.Iterator;
import java.util.Optional;

public class TokenRange
implements Iterable<JavaToken> {
    public static final TokenRange INVALID = new TokenRange(JavaToken.INVALID, JavaToken.INVALID);
    private final JavaToken begin;
    private final JavaToken end;

    public TokenRange(JavaToken begin, JavaToken end) {
        this.begin = Utils.assertNotNull(begin);
        this.end = Utils.assertNotNull(end);
    }

    public JavaToken getBegin() {
        return this.begin;
    }

    public JavaToken getEnd() {
        return this.end;
    }

    public Optional<Range> toRange() {
        if (this.begin.hasRange() && this.end.hasRange()) {
            return Optional.of(new Range(this.begin.getRange().get().begin, this.end.getRange().get().end));
        }
        return Optional.empty();
    }

    public TokenRange withBegin(JavaToken begin) {
        return new TokenRange(Utils.assertNotNull(begin), this.end);
    }

    public TokenRange withEnd(JavaToken end) {
        return new TokenRange(this.begin, Utils.assertNotNull(end));
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        for (JavaToken t : this) {
            result.append(t.getText());
        }
        return result.toString();
    }

    @Override
    public Iterator<JavaToken> iterator() {
        return new Iterator<JavaToken>(){
            private boolean hasNext = true;
            private JavaToken current = TokenRange.access$000(TokenRange.this);

            @Override
            public boolean hasNext() {
                return this.hasNext;
            }

            @Override
            public JavaToken next() {
                JavaToken retval = this.current;
                if (this.current == null) {
                    throw new IllegalStateException("Attempting to move past end of range.");
                }
                if (this.current == TokenRange.this.end) {
                    this.hasNext = false;
                }
                this.current = this.current.getNextToken().orElse(null);
                if (this.current == null && this.hasNext) {
                    throw new IllegalStateException("End token is not linked to begin token.");
                }
                return retval;
            }
        };
    }

    static /* synthetic */ JavaToken access$000(TokenRange x0) {
        return x0.begin;
    }
}

