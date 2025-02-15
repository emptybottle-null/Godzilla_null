/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.xml.namespace.QName;

public class NameUtil {
    public static final char HYPHEN = '-';
    public static final char PERIOD = '.';
    public static final char COLON = ':';
    public static final char USCORE = '_';
    public static final char DOT = '\u00b7';
    public static final char TELEIA = '\u0387';
    public static final char AYAH = '\u06dd';
    public static final char ELHIZB = '\u06de';
    private static final boolean DEBUG = false;
    private static final Set javaWords = new HashSet<String>(Arrays.asList("assert", "abstract", "boolean", "break", "byte", "case", "catch", "char", "class", "const", "continue", "default", "do", "double", "else", "enum", "extends", "false", "final", "finally", "float", "for", "goto", "if", "implements", "import", "instanceof", "int", "interface", "long", "native", "new", "null", "package", "private", "protected", "public", "return", "short", "static", "strictfp", "super", "switch", "synchronized", "this", "threadsafe", "throw", "throws", "transient", "true", "try", "void", "volatile", "while"));
    private static final Set extraWords = new HashSet<String>(Arrays.asList("i", "target", "org", "com"));
    private static final Set javaNames = new HashSet<String>(Arrays.asList("CharSequence", "Cloneable", "Comparable", "Runnable", "Boolean", "Byte", "Character", "Class", "ClassLoader", "Compiler", "Double", "Float", "InheritableThreadLocal", "Integer", "Long", "Math", "Number", "Object", "Package", "Process", "Runtime", "RuntimePermission", "SecurityManager", "Short", "StackTraceElement", "StrictMath", "String", "StringBuffer", "System", "Thread", "ThreadGroup", "ThreadLocal", "Throwable", "Void", "ArithmeticException", "ArrayIndexOutOfBoundsException", "ArrayStoreException", "ClassCastException", "ClassNotFoundException", "CloneNotSupportedException", "Exception", "IllegalAccessException", "IllegalArgumentException", "IllegalMonitorStateException", "IllegalStateException", "IllegalThreadStateException", "IndexOutOfBoundsException", "InstantiationException", "InterruptedException", "NegativeArraySizeException", "NoSuchFieldException", "NoSuchMethodException", "NullPointerException", "NumberFormatException", "RuntimeException", "SecurityException", "StringIndexOutOfBoundsException", "UnsupportedOperationException", "AbstractMethodError", "AssertionError", "ClassCircularityError", "ClassFormatError", "Error", "ExceptionInInitializerError", "IllegalAccessError", "IncompatibleClassChangeError", "InstantiationError", "InternalError", "LinkageError", "NoClassDefFoundError", "NoSuchFieldError", "NoSuchMethodError", "OutOfMemoryError", "StackOverflowError", "ThreadDeath", "UnknownError", "UnsatisfiedLinkError", "UnsupportedClassVersionError", "VerifyError", "VirtualMachineError", "BigInteger", "BigDecimal", "Enum", "Date", "GDate", "GDuration", "QName", "List", "XmlObject", "XmlCursor", "XmlBeans", "SchemaType"));
    private static final String JAVA_NS_PREFIX = "java:";
    private static final String LANG_PREFIX = "java.";
    private static final int START = 0;
    private static final int PUNCT = 1;
    private static final int DIGIT = 2;
    private static final int MARK = 3;
    private static final int UPPER = 4;
    private static final int LOWER = 5;
    private static final int NOCASE = 6;

    public static boolean isValidJavaIdentifier(String id) {
        if (id == null) {
            throw new IllegalArgumentException("id cannot be null");
        }
        int len = id.length();
        if (len == 0) {
            return false;
        }
        if (javaWords.contains(id)) {
            return false;
        }
        if (!Character.isJavaIdentifierStart(id.charAt(0))) {
            return false;
        }
        for (int i = 1; i < len; ++i) {
            if (Character.isJavaIdentifierPart(id.charAt(i))) continue;
            return false;
        }
        return true;
    }

    public static String getClassNameFromQName(QName qname) {
        return NameUtil.getClassNameFromQName(qname, false);
    }

    public static String getClassNameFromQName(QName qname, boolean useJaxRpcRules) {
        String java_type = NameUtil.upperCamelCase(qname.getLocalPart(), useJaxRpcRules);
        String uri = qname.getNamespaceURI();
        String java_pkg = null;
        java_pkg = NameUtil.getPackageFromNamespace(uri, useJaxRpcRules);
        if (java_pkg != null) {
            return java_pkg + "." + java_type;
        }
        return java_type;
    }

    public static String getNamespaceFromPackage(Class clazz) {
        Class<?> curr_clazz = clazz;
        while (curr_clazz.isArray()) {
            curr_clazz = curr_clazz.getComponentType();
        }
        String fullname = clazz.getName();
        int lastdot = fullname.lastIndexOf(46);
        String pkg_name = lastdot < 0 ? "" : fullname.substring(0, lastdot);
        return JAVA_NS_PREFIX + pkg_name;
    }

    private static boolean isUriSchemeChar(char ch) {
        return ch >= 'a' && ch <= 'z' || ch >= 'A' && ch <= 'Z' || ch >= '0' && ch <= '9' || ch == '-' || ch == '.' || ch == '+';
    }

    private static boolean isUriAlphaChar(char ch) {
        return ch >= 'a' && ch <= 'z' || ch >= 'A' && ch <= 'Z';
    }

    private static int findSchemeColon(String uri) {
        int i;
        int len = uri.length();
        if (len == 0) {
            return -1;
        }
        if (!NameUtil.isUriAlphaChar(uri.charAt(0))) {
            return -1;
        }
        for (i = 1; i < len && NameUtil.isUriSchemeChar(uri.charAt(i)); ++i) {
        }
        if (i == len) {
            return -1;
        }
        if (uri.charAt(i) != ':') {
            return -1;
        }
        while (i < len && uri.charAt(i) == ':') {
            ++i;
        }
        return i - 1;
    }

    private static String jls77String(String name) {
        StringBuffer buf = new StringBuffer(name);
        for (int i = 0; i < name.length(); ++i) {
            if (Character.isJavaIdentifierPart(buf.charAt(i)) && '$' != buf.charAt(i)) continue;
            buf.setCharAt(i, '_');
        }
        if (buf.length() == 0 || !Character.isJavaIdentifierStart(buf.charAt(0))) {
            buf.insert(0, '_');
        }
        if (NameUtil.isJavaReservedWord(name)) {
            buf.append('_');
        }
        return buf.toString();
    }

    private static List splitDNS(String dns) {
        ArrayList<String> result = new ArrayList<String>();
        int end = dns.length();
        for (int begin = dns.lastIndexOf(46); begin != -1; --begin) {
            if (dns.charAt(begin) != '.') continue;
            result.add(NameUtil.jls77String(dns.substring(begin + 1, end)));
            end = begin;
        }
        result.add(NameUtil.jls77String(dns.substring(0, end)));
        if (result.size() >= 3 && ((String)result.get(result.size() - 1)).toLowerCase().equals("www")) {
            result.remove(result.size() - 1);
        }
        return result;
    }

    private static String processFilename(String filename) {
        int i = filename.lastIndexOf(46);
        if (i > 0 && (i + 1 + 2 == filename.length() || i + 1 + 3 == filename.length() || filename.substring(i + 1).toLowerCase() == "html")) {
            return filename.substring(0, i);
        }
        return filename;
    }

    public static String getPackageFromNamespace(String uri) {
        return NameUtil.getPackageFromNamespace(uri, false);
    }

    public static String getPackageFromNamespace(String uri, boolean useJaxRpcRules) {
        if (uri == null || uri.length() == 0) {
            return "noNamespace";
        }
        int len = uri.length();
        int i = NameUtil.findSchemeColon(uri);
        List<Object> result = null;
        if (i == len - 1) {
            result = new ArrayList<String>();
            result.add(uri.substring(0, i));
        } else if (i >= 0 && uri.substring(0, i).equals("java")) {
            result = Arrays.asList(uri.substring(i + 1).split("\\."));
        } else {
            result = new ArrayList();
            ++i;
            block0: while (i < len) {
                while (uri.charAt(i) == '/') {
                    if (++i < len) continue;
                    break block0;
                }
                int start = i;
                while (uri.charAt(i) != '/' && ++i < len) {
                }
                int end = i;
                result.add(uri.substring(start, end));
            }
            if (result.size() > 1) {
                result.set(result.size() - 1, NameUtil.processFilename((String)result.get(result.size() - 1)));
            }
            if (result.size() > 0) {
                List splitdns = NameUtil.splitDNS((String)result.get(0));
                result.remove(0);
                result.addAll(0, splitdns);
            }
        }
        StringBuffer buf = new StringBuffer();
        Iterator it = result.iterator();
        while (it.hasNext()) {
            String part = NameUtil.nonJavaKeyword(NameUtil.lowerCamelCase((String)it.next(), useJaxRpcRules, true));
            if (part.length() <= 0) continue;
            buf.append(part);
            buf.append('.');
        }
        if (buf.length() == 0) {
            return "noNamespace";
        }
        if (useJaxRpcRules) {
            return buf.substring(0, buf.length() - 1).toLowerCase();
        }
        return buf.substring(0, buf.length() - 1);
    }

    public static void main(String[] args) {
        for (int i = 0; i < args.length; ++i) {
            System.out.println(NameUtil.upperCaseUnderbar(args[i]));
        }
    }

    public static String upperCaseUnderbar(String xml_name) {
        StringBuffer buf = new StringBuffer();
        List words = NameUtil.splitWords(xml_name, false);
        int sz = words.size() - 1;
        if (sz >= 0 && !Character.isJavaIdentifierStart(((String)words.get(0)).charAt(0))) {
            buf.append("X_");
        }
        for (int i = 0; i < sz; ++i) {
            buf.append((String)words.get(i));
            buf.append('_');
        }
        if (sz >= 0) {
            buf.append((String)words.get(sz));
        }
        int len = buf.length();
        for (int j = 0; j < len; ++j) {
            char c = buf.charAt(j);
            buf.setCharAt(j, Character.toUpperCase(c));
        }
        return buf.toString();
    }

    public static String upperCamelCase(String xml_name) {
        return NameUtil.upperCamelCase(xml_name, false);
    }

    public static String upperCamelCase(String xml_name, boolean useJaxRpcRules) {
        StringBuffer buf = new StringBuffer();
        List words = NameUtil.splitWords(xml_name, useJaxRpcRules);
        if (words.size() > 0) {
            if (!Character.isJavaIdentifierStart(((String)words.get(0)).charAt(0))) {
                buf.append("X");
            }
            Iterator itr = words.iterator();
            while (itr.hasNext()) {
                buf.append((String)itr.next());
            }
        }
        return buf.toString();
    }

    public static String lowerCamelCase(String xml_name) {
        return NameUtil.lowerCamelCase(xml_name, false, true);
    }

    public static String lowerCamelCase(String xml_name, boolean useJaxRpcRules, boolean fixGeneratedName) {
        StringBuffer buf = new StringBuffer();
        List words = NameUtil.splitWords(xml_name, useJaxRpcRules);
        if (words.size() > 0) {
            String first = ((String)words.get(0)).toLowerCase();
            char f = first.charAt(0);
            if (!Character.isJavaIdentifierStart(f) && fixGeneratedName) {
                buf.append("x");
            }
            buf.append(first);
            Iterator itr = words.iterator();
            itr.next();
            while (itr.hasNext()) {
                buf.append((String)itr.next());
            }
        }
        return buf.toString();
    }

    public static String upperCaseFirstLetter(String s) {
        if (s.length() == 0 || Character.isUpperCase(s.charAt(0))) {
            return s;
        }
        StringBuffer buf = new StringBuffer(s);
        buf.setCharAt(0, Character.toUpperCase(buf.charAt(0)));
        return buf.toString();
    }

    private static void addCapped(List list, String str) {
        if (str.length() > 0) {
            list.add(NameUtil.upperCaseFirstLetter(str));
        }
    }

    public static List splitWords(String name, boolean useJaxRpcRules) {
        ArrayList list = new ArrayList();
        int len = name.length();
        int start = 0;
        int prefix = 0;
        for (int i = 0; i < len; ++i) {
            int current = NameUtil.getCharClass(name.charAt(i), useJaxRpcRules);
            if (prefix != 1 && current == 1) {
                NameUtil.addCapped(list, name.substring(start, i));
                while ((current = NameUtil.getCharClass(name.charAt(i), useJaxRpcRules)) == 1) {
                    if (++i < len) continue;
                    return list;
                }
                start = i;
            } else if (prefix == 2 != (current == 2) || prefix == 5 && current != 5 || NameUtil.isLetter(prefix) != NameUtil.isLetter(current)) {
                NameUtil.addCapped(list, name.substring(start, i));
                start = i;
            } else if (prefix == 4 && current == 5 && i > start + 1) {
                NameUtil.addCapped(list, name.substring(start, i - 1));
                start = i - 1;
            }
            prefix = current;
        }
        NameUtil.addCapped(list, name.substring(start));
        return list;
    }

    public static int getCharClass(char c, boolean useJaxRpcRules) {
        if (NameUtil.isPunctuation(c, useJaxRpcRules)) {
            return 1;
        }
        if (Character.isDigit(c)) {
            return 2;
        }
        if (Character.isUpperCase(c)) {
            return 4;
        }
        if (Character.isLowerCase(c)) {
            return 5;
        }
        if (Character.isLetter(c)) {
            return 6;
        }
        if (Character.isJavaIdentifierPart(c)) {
            return 3;
        }
        return 1;
    }

    private static boolean isLetter(int state) {
        return state == 4 || state == 5 || state == 6;
    }

    public static boolean isPunctuation(char c, boolean useJaxRpcRules) {
        return c == '-' || c == '.' || c == ':' || c == '\u00b7' || c == '_' && !useJaxRpcRules || c == '\u0387' || c == '\u06dd' || c == '\u06de';
    }

    public static String nonJavaKeyword(String word) {
        if (NameUtil.isJavaReservedWord(word)) {
            return 'x' + word;
        }
        return word;
    }

    public static String nonExtraKeyword(String word) {
        if (NameUtil.isExtraReservedWord(word, true)) {
            return word + "Value";
        }
        return word;
    }

    public static String nonJavaCommonClassName(String name) {
        if (NameUtil.isJavaCommonClassName(name)) {
            return "X" + name;
        }
        return name;
    }

    private static boolean isJavaReservedWord(String word) {
        return NameUtil.isJavaReservedWord(word, true);
    }

    private static boolean isJavaReservedWord(String word, boolean ignore_case) {
        if (ignore_case) {
            word = word.toLowerCase();
        }
        return javaWords.contains(word);
    }

    private static boolean isExtraReservedWord(String word, boolean ignore_case) {
        if (ignore_case) {
            word = word.toLowerCase();
        }
        return extraWords.contains(word);
    }

    public static boolean isJavaCommonClassName(String word) {
        return javaNames.contains(word);
    }
}

