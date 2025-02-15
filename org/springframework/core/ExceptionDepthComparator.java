/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import org.springframework.util.Assert;

public class ExceptionDepthComparator
implements Comparator<Class<? extends Throwable>> {
    private final Class<? extends Throwable> targetException;

    public ExceptionDepthComparator(Throwable exception) {
        Assert.notNull((Object)exception, "Target exception must not be null");
        this.targetException = exception.getClass();
    }

    public ExceptionDepthComparator(Class<? extends Throwable> exceptionType) {
        Assert.notNull(exceptionType, "Target exception type must not be null");
        this.targetException = exceptionType;
    }

    @Override
    public int compare(Class<? extends Throwable> o1, Class<? extends Throwable> o2) {
        int depth1 = this.getDepth(o1, this.targetException, 0);
        int depth2 = this.getDepth(o2, this.targetException, 0);
        return depth1 - depth2;
    }

    private int getDepth(Class<?> declaredException, Class<?> exceptionToMatch, int depth) {
        if (exceptionToMatch.equals(declaredException)) {
            return depth;
        }
        if (exceptionToMatch == Throwable.class) {
            return Integer.MAX_VALUE;
        }
        return this.getDepth(declaredException, exceptionToMatch.getSuperclass(), depth + 1);
    }

    public static Class<? extends Throwable> findClosestMatch(Collection<Class<? extends Throwable>> exceptionTypes, Throwable targetException) {
        Assert.notEmpty(exceptionTypes, "Exception types must not be empty");
        if (exceptionTypes.size() == 1) {
            return exceptionTypes.iterator().next();
        }
        ArrayList<Class<? extends Throwable>> handledExceptions = new ArrayList<Class<? extends Throwable>>(exceptionTypes);
        handledExceptions.sort(new ExceptionDepthComparator(targetException));
        return (Class)handledExceptions.get(0);
    }
}

