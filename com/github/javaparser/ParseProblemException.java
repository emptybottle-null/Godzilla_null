/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser;

import com.github.javaparser.Problem;
import com.github.javaparser.utils.Utils;
import java.util.Collections;
import java.util.List;

public class ParseProblemException
extends RuntimeException {
    private final List<Problem> problems;

    public ParseProblemException(List<Problem> problems) {
        super(ParseProblemException.createMessage(Utils.assertNotNull(problems)));
        this.problems = problems;
    }

    public ParseProblemException(Throwable throwable) {
        this(Collections.singletonList(new Problem(throwable.getMessage(), null, throwable)));
    }

    private static String createMessage(List<Problem> problems) {
        StringBuilder message = new StringBuilder();
        for (Problem problem : problems) {
            message.append(problem.toString()).append(Utils.SYSTEM_EOL);
        }
        return message.toString();
    }

    public List<Problem> getProblems() {
        return this.problems;
    }
}

