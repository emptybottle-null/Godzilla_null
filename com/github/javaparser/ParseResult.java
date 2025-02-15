/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser;

import com.github.javaparser.ParserConfiguration;
import com.github.javaparser.Problem;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.comments.CommentsCollection;
import com.github.javaparser.utils.Utils;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public class ParseResult<T> {
    private final T result;
    private final List<Problem> problems;
    private final CommentsCollection commentsCollection;

    public ParseResult(T result, List<Problem> problems, CommentsCollection commentsCollection) {
        this.commentsCollection = commentsCollection;
        this.result = result;
        this.problems = problems;
    }

    public boolean isSuccessful() {
        return this.problems.isEmpty() && this.result != null;
    }

    public void ifSuccessful(Consumer<T> consumer) {
        if (this.isSuccessful()) {
            consumer.accept(this.result);
        }
    }

    public List<Problem> getProblems() {
        return this.problems;
    }

    public Problem getProblem(int i) {
        return this.getProblems().get(i);
    }

    public Optional<CommentsCollection> getCommentsCollection() {
        return Optional.ofNullable(this.commentsCollection);
    }

    public Optional<T> getResult() {
        return Optional.ofNullable(this.result);
    }

    public String toString() {
        if (this.isSuccessful()) {
            return "Parsing successful";
        }
        StringBuilder message = new StringBuilder("Parsing failed:").append(Utils.SYSTEM_EOL);
        for (Problem problem : this.problems) {
            message.append(problem.toString()).append(Utils.SYSTEM_EOL);
        }
        return message.toString();
    }

    public static interface PostProcessor {
        public void process(ParseResult<? extends Node> var1, ParserConfiguration var2);
    }
}

