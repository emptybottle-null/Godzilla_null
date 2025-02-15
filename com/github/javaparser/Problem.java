/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser;

import com.github.javaparser.Position;
import com.github.javaparser.TokenRange;
import com.github.javaparser.utils.Utils;
import java.util.Comparator;
import java.util.Optional;

public class Problem {
    private final String message;
    private final TokenRange location;
    private final Throwable cause;
    public static Comparator<Problem> PROBLEM_BY_BEGIN_POSITION = (a, b) -> {
        Optional aBegin = a.getLocation().flatMap(l -> l.getBegin().getRange().map(r -> r.begin));
        Optional bBegin = b.getLocation().flatMap(l -> l.getBegin().getRange().map(r -> r.begin));
        if (aBegin.isPresent() && bBegin.isPresent()) {
            return ((Position)aBegin.get()).compareTo((Position)bBegin.get());
        }
        if (a.getLocation().isPresent() || b.getLocation().isPresent()) {
            if (a.getLocation().isPresent()) {
                return 1;
            }
            return -1;
        }
        return 0;
    };

    public Problem(String message, TokenRange location, Throwable cause) {
        Utils.assertNotNull(message);
        this.message = message;
        this.location = location;
        this.cause = cause;
    }

    public String toString() {
        StringBuilder str = new StringBuilder(this.getVerboseMessage());
        if (this.cause != null) {
            str.append(Utils.SYSTEM_EOL).append("Problem stacktrace : ").append(Utils.SYSTEM_EOL);
            for (int i = 0; i < this.cause.getStackTrace().length; ++i) {
                StackTraceElement ste = this.cause.getStackTrace()[i];
                str.append("  ").append(ste.toString());
                if (i + 1 == this.cause.getStackTrace().length) continue;
                str.append(Utils.SYSTEM_EOL);
            }
        }
        return str.toString();
    }

    public String getMessage() {
        return this.message;
    }

    public String getVerboseMessage() {
        return this.getLocation().map(l -> l.getBegin().getRange().map(r -> r.begin.toString()).orElse("(line ?,col ?)") + " " + this.message).orElse(this.message);
    }

    public Optional<TokenRange> getLocation() {
        return Optional.ofNullable(this.location);
    }

    public Optional<Throwable> getCause() {
        return Optional.ofNullable(this.cause);
    }
}

