/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.lang3.exception;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.UndeclaredThrowableException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringTokenizer;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.StringUtils;

public class ExceptionUtils {
    private static final int NOT_FOUND = -1;
    private static final String[] CAUSE_METHOD_NAMES = new String[]{"getCause", "getNextException", "getTargetException", "getException", "getSourceException", "getRootCause", "getCausedByException", "getNested", "getLinkedException", "getNestedException", "getLinkedCause", "getThrowable"};
    static final String WRAPPED_MARKER = " [wrapped] ";

    @Deprecated
    public static Throwable getCause(Throwable throwable) {
        return ExceptionUtils.getCause(throwable, null);
    }

    @Deprecated
    public static Throwable getCause(Throwable throwable, String[] methodNames) {
        if (throwable == null) {
            return null;
        }
        if (methodNames == null) {
            Throwable cause = throwable.getCause();
            if (cause != null) {
                return cause;
            }
            methodNames = CAUSE_METHOD_NAMES;
        }
        for (String methodName : methodNames) {
            Throwable legacyCause;
            if (methodName == null || (legacyCause = ExceptionUtils.getCauseUsingMethodName(throwable, methodName)) == null) continue;
            return legacyCause;
        }
        return null;
    }

    private static Throwable getCauseUsingMethodName(Throwable throwable, String methodName) {
        Method method = null;
        try {
            method = throwable.getClass().getMethod(methodName, new Class[0]);
        } catch (NoSuchMethodException | SecurityException exception) {
            // empty catch block
        }
        if (method != null && Throwable.class.isAssignableFrom(method.getReturnType())) {
            try {
                return (Throwable)method.invoke(throwable, new Object[0]);
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException exception) {
                // empty catch block
            }
        }
        return null;
    }

    @Deprecated
    public static String[] getDefaultCauseMethodNames() {
        return ArrayUtils.clone(CAUSE_METHOD_NAMES);
    }

    public static String getMessage(Throwable th) {
        if (th == null) {
            return "";
        }
        String clsName = ClassUtils.getShortClassName(th, null);
        String msg = th.getMessage();
        return clsName + ": " + StringUtils.defaultString(msg);
    }

    public static Throwable getRootCause(Throwable throwable) {
        List<Throwable> list = ExceptionUtils.getThrowableList(throwable);
        return list.isEmpty() ? null : list.get(list.size() - 1);
    }

    public static String getRootCauseMessage(Throwable th) {
        Throwable root = ExceptionUtils.getRootCause(th);
        root = root == null ? th : root;
        return ExceptionUtils.getMessage(root);
    }

    public static String[] getRootCauseStackTrace(Throwable throwable) {
        if (throwable == null) {
            return ArrayUtils.EMPTY_STRING_ARRAY;
        }
        Throwable[] throwables = ExceptionUtils.getThrowables(throwable);
        int count = throwables.length;
        ArrayList<String> frames = new ArrayList<String>();
        List<String> nextTrace = ExceptionUtils.getStackFrameList(throwables[count - 1]);
        int i = count;
        while (--i >= 0) {
            List<String> trace = nextTrace;
            if (i != 0) {
                nextTrace = ExceptionUtils.getStackFrameList(throwables[i - 1]);
                ExceptionUtils.removeCommonFrames(trace, nextTrace);
            }
            if (i == count - 1) {
                frames.add(throwables[i].toString());
            } else {
                frames.add(WRAPPED_MARKER + throwables[i].toString());
            }
            frames.addAll(trace);
        }
        return frames.toArray(ArrayUtils.EMPTY_STRING_ARRAY);
    }

    static List<String> getStackFrameList(Throwable t) {
        String stackTrace = ExceptionUtils.getStackTrace(t);
        String linebreak = System.lineSeparator();
        StringTokenizer frames = new StringTokenizer(stackTrace, linebreak);
        ArrayList<String> list = new ArrayList<String>();
        boolean traceStarted = false;
        while (frames.hasMoreTokens()) {
            String token = frames.nextToken();
            int at = token.indexOf("at");
            if (at != -1 && token.substring(0, at).trim().isEmpty()) {
                traceStarted = true;
                list.add(token);
                continue;
            }
            if (!traceStarted) continue;
            break;
        }
        return list;
    }

    static String[] getStackFrames(String stackTrace) {
        String linebreak = System.lineSeparator();
        StringTokenizer frames = new StringTokenizer(stackTrace, linebreak);
        ArrayList<String> list = new ArrayList<String>();
        while (frames.hasMoreTokens()) {
            list.add(frames.nextToken());
        }
        return list.toArray(ArrayUtils.EMPTY_STRING_ARRAY);
    }

    public static String[] getStackFrames(Throwable throwable) {
        if (throwable == null) {
            return ArrayUtils.EMPTY_STRING_ARRAY;
        }
        return ExceptionUtils.getStackFrames(ExceptionUtils.getStackTrace(throwable));
    }

    public static String getStackTrace(Throwable throwable) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw, true);
        throwable.printStackTrace(pw);
        return sw.getBuffer().toString();
    }

    public static int getThrowableCount(Throwable throwable) {
        return ExceptionUtils.getThrowableList(throwable).size();
    }

    public static List<Throwable> getThrowableList(Throwable throwable) {
        ArrayList<Throwable> list = new ArrayList<Throwable>();
        while (throwable != null && !list.contains(throwable)) {
            list.add(throwable);
            throwable = throwable.getCause();
        }
        return list;
    }

    public static Throwable[] getThrowables(Throwable throwable) {
        List<Throwable> list = ExceptionUtils.getThrowableList(throwable);
        return list.toArray(ArrayUtils.EMPTY_THROWABLE_ARRAY);
    }

    public static boolean hasCause(Throwable chain, Class<? extends Throwable> type) {
        if (chain instanceof UndeclaredThrowableException) {
            chain = chain.getCause();
        }
        return type.isInstance(chain);
    }

    private static int indexOf(Throwable throwable, Class<? extends Throwable> type, int fromIndex, boolean subclass) {
        Throwable[] throwables;
        if (throwable == null || type == null) {
            return -1;
        }
        if (fromIndex < 0) {
            fromIndex = 0;
        }
        if (fromIndex >= (throwables = ExceptionUtils.getThrowables(throwable)).length) {
            return -1;
        }
        if (subclass) {
            for (int i = fromIndex; i < throwables.length; ++i) {
                if (!type.isAssignableFrom(throwables[i].getClass())) continue;
                return i;
            }
        } else {
            for (int i = fromIndex; i < throwables.length; ++i) {
                if (!type.equals(throwables[i].getClass())) continue;
                return i;
            }
        }
        return -1;
    }

    public static int indexOfThrowable(Throwable throwable, Class<? extends Throwable> clazz) {
        return ExceptionUtils.indexOf(throwable, clazz, 0, false);
    }

    public static int indexOfThrowable(Throwable throwable, Class<? extends Throwable> clazz, int fromIndex) {
        return ExceptionUtils.indexOf(throwable, clazz, fromIndex, false);
    }

    public static int indexOfType(Throwable throwable, Class<? extends Throwable> type) {
        return ExceptionUtils.indexOf(throwable, type, 0, true);
    }

    public static int indexOfType(Throwable throwable, Class<? extends Throwable> type, int fromIndex) {
        return ExceptionUtils.indexOf(throwable, type, fromIndex, true);
    }

    public static void printRootCauseStackTrace(Throwable throwable) {
        ExceptionUtils.printRootCauseStackTrace(throwable, System.err);
    }

    public static void printRootCauseStackTrace(Throwable throwable, PrintStream printStream) {
        String[] trace;
        if (throwable == null) {
            return;
        }
        Objects.requireNonNull(printStream, "printStream");
        for (String element : trace = ExceptionUtils.getRootCauseStackTrace(throwable)) {
            printStream.println(element);
        }
        printStream.flush();
    }

    public static void printRootCauseStackTrace(Throwable throwable, PrintWriter printWriter) {
        String[] trace;
        if (throwable == null) {
            return;
        }
        Objects.requireNonNull(printWriter, "printWriter");
        for (String element : trace = ExceptionUtils.getRootCauseStackTrace(throwable)) {
            printWriter.println(element);
        }
        printWriter.flush();
    }

    public static void removeCommonFrames(List<String> causeFrames, List<String> wrapperFrames) {
        if (causeFrames == null || wrapperFrames == null) {
            throw new IllegalArgumentException("The List must not be null");
        }
        int causeFrameIndex = causeFrames.size() - 1;
        for (int wrapperFrameIndex = wrapperFrames.size() - 1; causeFrameIndex >= 0 && wrapperFrameIndex >= 0; --causeFrameIndex, --wrapperFrameIndex) {
            String wrapperFrame;
            String causeFrame = causeFrames.get(causeFrameIndex);
            if (!causeFrame.equals(wrapperFrame = wrapperFrames.get(wrapperFrameIndex))) continue;
            causeFrames.remove(causeFrameIndex);
        }
    }

    public static <R> R rethrow(Throwable throwable) {
        return ExceptionUtils.typeErasure(throwable);
    }

    private static <T extends Throwable> T throwableOf(Throwable throwable, Class<T> type, int fromIndex, boolean subclass) {
        Throwable[] throwables;
        if (throwable == null || type == null) {
            return null;
        }
        if (fromIndex < 0) {
            fromIndex = 0;
        }
        if (fromIndex >= (throwables = ExceptionUtils.getThrowables(throwable)).length) {
            return null;
        }
        if (subclass) {
            for (int i = fromIndex; i < throwables.length; ++i) {
                if (!type.isAssignableFrom(throwables[i].getClass())) continue;
                return (T)((Throwable)type.cast(throwables[i]));
            }
        } else {
            for (int i = fromIndex; i < throwables.length; ++i) {
                if (!type.equals(throwables[i].getClass())) continue;
                return (T)((Throwable)type.cast(throwables[i]));
            }
        }
        return null;
    }

    public static <T extends Throwable> T throwableOfThrowable(Throwable throwable, Class<T> clazz) {
        return ExceptionUtils.throwableOf(throwable, clazz, 0, false);
    }

    public static <T extends Throwable> T throwableOfThrowable(Throwable throwable, Class<T> clazz, int fromIndex) {
        return ExceptionUtils.throwableOf(throwable, clazz, fromIndex, false);
    }

    public static <T extends Throwable> T throwableOfType(Throwable throwable, Class<T> type) {
        return ExceptionUtils.throwableOf(throwable, type, 0, true);
    }

    public static <T extends Throwable> T throwableOfType(Throwable throwable, Class<T> type, int fromIndex) {
        return ExceptionUtils.throwableOf(throwable, type, fromIndex, true);
    }

    private static <R, T extends Throwable> R typeErasure(Throwable throwable) throws T {
        throw throwable;
    }

    public static <R> R wrapAndThrow(Throwable throwable) {
        if (throwable instanceof RuntimeException) {
            throw (RuntimeException)throwable;
        }
        if (throwable instanceof Error) {
            throw (Error)throwable;
        }
        throw new UndeclaredThrowableException(throwable);
    }
}

