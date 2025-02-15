/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.xml.stream.utils;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;

public interface NestedThrowable {
    public Throwable getNested();

    public String superToString();

    public void superPrintStackTrace(PrintStream var1);

    public void superPrintStackTrace(PrintWriter var1);

    public static class Util {
        private static String EOL = System.getProperty("line.separator");

        public static String toString(NestedThrowable nt) {
            Throwable nested = nt.getNested();
            if (nested == null) {
                return nt.superToString();
            }
            return nt.superToString() + " - with nested exception:" + EOL + "[" + Util.nestedToString(nested) + "]";
        }

        private static String nestedToString(Throwable nested) {
            if (nested instanceof InvocationTargetException) {
                InvocationTargetException ite = (InvocationTargetException)nested;
                return nested.toString() + " - with target exception:" + EOL + "[" + ite.getTargetException().toString() + "]";
            }
            return nested.toString();
        }

        public static void printStackTrace(NestedThrowable nt, PrintStream s) {
            Throwable nested = nt.getNested();
            if (nested != null) {
                nested.printStackTrace(s);
                s.println("--------------- nested within: ------------------");
            }
            nt.superPrintStackTrace(s);
        }

        public static void printStackTrace(NestedThrowable nt, PrintWriter w) {
            Throwable nested = nt.getNested();
            if (nested != null) {
                nested.printStackTrace(w);
                w.println("--------------- nested within: ------------------");
            }
            nt.superPrintStackTrace(w);
        }
    }
}

