/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript;

import java.io.CharArrayWriter;
import java.io.FilenameFilter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Evaluator;
import org.mozilla.javascript.Interpreter;
import org.mozilla.javascript.ScriptStackElement;
import org.mozilla.javascript.SecurityUtilities;
import org.mozilla.javascript.StackStyle;

public abstract class RhinoException
extends RuntimeException {
    private static final Pattern JAVA_STACK_PATTERN = Pattern.compile("_c_(.*)_\\d+");
    static final long serialVersionUID = 1883500631321581169L;
    private static StackStyle stackStyle = StackStyle.RHINO;
    private String sourceName;
    private int lineNumber;
    private String lineSource;
    private int columnNumber;
    Object interpreterStackInfo;
    int[] interpreterLineData;

    RhinoException() {
        Evaluator e = Context.createInterpreter();
        if (e != null) {
            e.captureStackInfo(this);
        }
    }

    RhinoException(String details) {
        super(details);
        Evaluator e = Context.createInterpreter();
        if (e != null) {
            e.captureStackInfo(this);
        }
    }

    @Override
    public final String getMessage() {
        String details = this.details();
        if (this.sourceName == null || this.lineNumber <= 0) {
            return details;
        }
        StringBuilder buf = new StringBuilder(details);
        buf.append(" (");
        if (this.sourceName != null) {
            buf.append(this.sourceName);
        }
        if (this.lineNumber > 0) {
            buf.append('#');
            buf.append(this.lineNumber);
        }
        buf.append(')');
        return buf.toString();
    }

    public String details() {
        return super.getMessage();
    }

    public final String sourceName() {
        return this.sourceName;
    }

    public final void initSourceName(String sourceName) {
        if (sourceName == null) {
            throw new IllegalArgumentException();
        }
        if (this.sourceName != null) {
            throw new IllegalStateException();
        }
        this.sourceName = sourceName;
    }

    public final int lineNumber() {
        return this.lineNumber;
    }

    public final void initLineNumber(int lineNumber) {
        if (lineNumber <= 0) {
            throw new IllegalArgumentException(String.valueOf(lineNumber));
        }
        if (this.lineNumber > 0) {
            throw new IllegalStateException();
        }
        this.lineNumber = lineNumber;
    }

    public final int columnNumber() {
        return this.columnNumber;
    }

    public final void initColumnNumber(int columnNumber) {
        if (columnNumber <= 0) {
            throw new IllegalArgumentException(String.valueOf(columnNumber));
        }
        if (this.columnNumber > 0) {
            throw new IllegalStateException();
        }
        this.columnNumber = columnNumber;
    }

    public final String lineSource() {
        return this.lineSource;
    }

    public final void initLineSource(String lineSource) {
        if (lineSource == null) {
            throw new IllegalArgumentException();
        }
        if (this.lineSource != null) {
            throw new IllegalStateException();
        }
        this.lineSource = lineSource;
    }

    final void recordErrorOrigin(String sourceName, int lineNumber, String lineSource, int columnNumber) {
        if (lineNumber == -1) {
            lineNumber = 0;
        }
        if (sourceName != null) {
            this.initSourceName(sourceName);
        }
        if (lineNumber != 0) {
            this.initLineNumber(lineNumber);
        }
        if (lineSource != null) {
            this.initLineSource(lineSource);
        }
        if (columnNumber != 0) {
            this.initColumnNumber(columnNumber);
        }
    }

    private String generateStackTrace() {
        CharArrayWriter writer = new CharArrayWriter();
        super.printStackTrace(new PrintWriter(writer));
        String origStackTrace = writer.toString();
        Evaluator e = Context.createInterpreter();
        if (e != null) {
            return e.getPatchedStack(this, origStackTrace);
        }
        return null;
    }

    public String getScriptStackTrace() {
        return this.getScriptStackTrace(-1, null);
    }

    public String getScriptStackTrace(int limit, String functionName) {
        ScriptStackElement[] stack = this.getScriptStack(limit, functionName);
        return RhinoException.formatStackTrace(stack, this.details());
    }

    static String formatStackTrace(ScriptStackElement[] stack, String message) {
        StringBuilder buffer = new StringBuilder();
        String lineSeparator = SecurityUtilities.getSystemProperty("line.separator");
        if (stackStyle == StackStyle.V8 && !"null".equals(message)) {
            buffer.append(message);
            buffer.append(lineSeparator);
        }
        for (ScriptStackElement elem : stack) {
            switch (stackStyle) {
                case MOZILLA: {
                    elem.renderMozillaStyle(buffer);
                    break;
                }
                case V8: {
                    elem.renderV8Style(buffer);
                    break;
                }
                case RHINO: {
                    elem.renderJavaStyle(buffer);
                }
            }
            buffer.append(lineSeparator);
        }
        return buffer.toString();
    }

    @Deprecated
    public String getScriptStackTrace(FilenameFilter filter) {
        return this.getScriptStackTrace();
    }

    public ScriptStackElement[] getScriptStack() {
        return this.getScriptStack(-1, null);
    }

    public ScriptStackElement[] getScriptStack(int limit, String hideFunction) {
        Evaluator interpreter;
        ArrayList<ScriptStackElement> list = new ArrayList<ScriptStackElement>();
        ScriptStackElement[][] interpreterStack = null;
        if (this.interpreterStackInfo != null && (interpreter = Context.createInterpreter()) instanceof Interpreter) {
            interpreterStack = ((Interpreter)interpreter).getScriptStackElements(this);
        }
        int interpreterStackIndex = 0;
        StackTraceElement[] stack = this.getStackTrace();
        int count = 0;
        boolean printStarted = hideFunction == null;
        for (StackTraceElement e : stack) {
            String fileName = e.getFileName();
            if (e.getMethodName().startsWith("_c_") && e.getLineNumber() > -1 && fileName != null && !fileName.endsWith(".java")) {
                String methodName = e.getMethodName();
                Matcher match = JAVA_STACK_PATTERN.matcher(methodName);
                String string = methodName = !"_c_script_0".equals(methodName) && match.find() ? match.group(1) : null;
                if (!printStarted && hideFunction.equals(methodName)) {
                    printStarted = true;
                    continue;
                }
                if (!printStarted || limit >= 0 && count >= limit) continue;
                list.add(new ScriptStackElement(fileName, methodName, e.getLineNumber()));
                ++count;
                continue;
            }
            if (!"org.mozilla.javascript.Interpreter".equals(e.getClassName()) || !"interpretLoop".equals(e.getMethodName()) || interpreterStack == null || interpreterStack.length <= interpreterStackIndex) continue;
            for (ScriptStackElement elem : interpreterStack[interpreterStackIndex++]) {
                if (!printStarted && hideFunction.equals(elem.functionName)) {
                    printStarted = true;
                    continue;
                }
                if (!printStarted || limit >= 0 && count >= limit) continue;
                list.add(elem);
                ++count;
            }
        }
        return list.toArray(new ScriptStackElement[list.size()]);
    }

    @Override
    public void printStackTrace(PrintWriter s) {
        if (this.interpreterStackInfo == null) {
            super.printStackTrace(s);
        } else {
            s.print(this.generateStackTrace());
        }
    }

    @Override
    public void printStackTrace(PrintStream s) {
        if (this.interpreterStackInfo == null) {
            super.printStackTrace(s);
        } else {
            s.print(this.generateStackTrace());
        }
    }

    public static boolean usesMozillaStackStyle() {
        return stackStyle == StackStyle.MOZILLA;
    }

    public static void useMozillaStackStyle(boolean flag) {
        stackStyle = flag ? StackStyle.MOZILLA : StackStyle.RHINO;
    }

    public static void setStackStyle(StackStyle style) {
        stackStyle = style;
    }

    public static StackStyle getStackStyle() {
        return stackStyle;
    }

    static {
        String style = System.getProperty("rhino.stack.style");
        if (style != null) {
            if ("Rhino".equalsIgnoreCase(style)) {
                stackStyle = StackStyle.RHINO;
            } else if ("Mozilla".equalsIgnoreCase(style)) {
                stackStyle = StackStyle.MOZILLA;
            } else if ("V8".equalsIgnoreCase(style)) {
                stackStyle = StackStyle.V8;
            }
        }
    }
}

