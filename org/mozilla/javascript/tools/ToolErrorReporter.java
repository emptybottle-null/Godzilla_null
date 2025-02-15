/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript.tools;

import java.io.PrintStream;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.EcmaError;
import org.mozilla.javascript.ErrorReporter;
import org.mozilla.javascript.EvaluatorException;
import org.mozilla.javascript.JavaScriptException;
import org.mozilla.javascript.RhinoException;
import org.mozilla.javascript.SecurityUtilities;
import org.mozilla.javascript.WrappedException;

public class ToolErrorReporter
implements ErrorReporter {
    private static final String messagePrefix = "js: ";
    private boolean hasReportedErrorFlag;
    private boolean reportWarnings;
    private PrintStream err;

    public ToolErrorReporter(boolean reportWarnings) {
        this(reportWarnings, System.err);
    }

    public ToolErrorReporter(boolean reportWarnings, PrintStream err) {
        this.reportWarnings = reportWarnings;
        this.err = err;
    }

    public static String getMessage(String messageId) {
        return ToolErrorReporter.getMessage(messageId, (Object[])null);
    }

    public static String getMessage(String messageId, String argument) {
        Object[] args = new Object[]{argument};
        return ToolErrorReporter.getMessage(messageId, args);
    }

    public static String getMessage(String messageId, Object arg1, Object arg2) {
        Object[] args = new Object[]{arg1, arg2};
        return ToolErrorReporter.getMessage(messageId, args);
    }

    public static String getMessage(String messageId, Object[] args) {
        String formatString;
        Context cx = Context.getCurrentContext();
        Locale locale = cx == null ? Locale.getDefault() : cx.getLocale();
        ResourceBundle rb = ResourceBundle.getBundle("org.mozilla.javascript.tools.resources.Messages", locale);
        try {
            formatString = rb.getString(messageId);
        } catch (MissingResourceException mre) {
            throw new RuntimeException("no message resource found for message property " + messageId);
        }
        if (args == null) {
            return formatString;
        }
        MessageFormat formatter = new MessageFormat(formatString);
        return formatter.format(args);
    }

    private static String getExceptionMessage(RhinoException ex) {
        String msg = ex instanceof JavaScriptException ? ToolErrorReporter.getMessage("msg.uncaughtJSException", ex.details()) : (ex instanceof EcmaError ? ToolErrorReporter.getMessage("msg.uncaughtEcmaError", ex.details()) : (ex instanceof EvaluatorException ? ex.details() : ex.toString()));
        return msg;
    }

    @Override
    public void warning(String message, String sourceName, int line, String lineSource, int lineOffset) {
        if (!this.reportWarnings) {
            return;
        }
        this.reportErrorMessage(message, sourceName, line, lineSource, lineOffset, true);
    }

    @Override
    public void error(String message, String sourceName, int line, String lineSource, int lineOffset) {
        this.hasReportedErrorFlag = true;
        this.reportErrorMessage(message, sourceName, line, lineSource, lineOffset, false);
    }

    @Override
    public EvaluatorException runtimeError(String message, String sourceName, int line, String lineSource, int lineOffset) {
        return new EvaluatorException(message, sourceName, line, lineSource, lineOffset);
    }

    public boolean hasReportedError() {
        return this.hasReportedErrorFlag;
    }

    public boolean isReportingWarnings() {
        return this.reportWarnings;
    }

    public void setIsReportingWarnings(boolean reportWarnings) {
        this.reportWarnings = reportWarnings;
    }

    public static void reportException(ErrorReporter er, RhinoException ex) {
        if (er instanceof ToolErrorReporter) {
            ((ToolErrorReporter)er).reportException(ex);
        } else {
            String msg = ToolErrorReporter.getExceptionMessage(ex);
            er.error(msg, ex.sourceName(), ex.lineNumber(), ex.lineSource(), ex.columnNumber());
        }
    }

    public void reportException(RhinoException ex) {
        if (ex instanceof WrappedException) {
            WrappedException we = (WrappedException)ex;
            we.printStackTrace(this.err);
        } else {
            String lineSeparator = SecurityUtilities.getSystemProperty("line.separator");
            String msg = ToolErrorReporter.getExceptionMessage(ex) + lineSeparator + ex.getScriptStackTrace();
            this.reportErrorMessage(msg, ex.sourceName(), ex.lineNumber(), ex.lineSource(), ex.columnNumber(), false);
        }
    }

    private void reportErrorMessage(String message, String sourceName, int line, String lineSource, int lineOffset, boolean justWarning) {
        if (line > 0) {
            String lineStr = String.valueOf(line);
            if (sourceName != null) {
                Object[] args = new Object[]{sourceName, lineStr, message};
                message = ToolErrorReporter.getMessage("msg.format3", args);
            } else {
                Object[] args = new Object[]{lineStr, message};
                message = ToolErrorReporter.getMessage("msg.format2", args);
            }
        } else {
            Object[] args = new Object[]{message};
            message = ToolErrorReporter.getMessage("msg.format1", args);
        }
        if (justWarning) {
            message = ToolErrorReporter.getMessage("msg.warning", message);
        }
        this.err.println(messagePrefix + message);
        if (null != lineSource) {
            this.err.println(messagePrefix + lineSource);
            this.err.println(messagePrefix + this.buildIndicator(lineOffset));
        }
    }

    private String buildIndicator(int offset) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < offset - 1; ++i) {
            sb.append(".");
        }
        sb.append("^");
        return sb.toString();
    }
}

