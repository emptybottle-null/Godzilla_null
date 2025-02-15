/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package galaxy.jsp.encounter.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

public class Utils {
    private static int hexEntityHelp = 0;

    public static String StrToUnicode(String string, boolean halfWith) {
        if (string == null || string.isEmpty()) {
            return string;
        }
        StringBuilder value = new StringBuilder(string.length() << 3);
        String prefix = "\\u";
        String zerofix = "0";
        for (int i = 0; i < string.length(); ++i) {
            char c = string.charAt(i);
            if (!halfWith && c > '\u001f' && c < '\u007f') {
                value.append(c);
                continue;
            }
            value.append(prefix);
            int j = c >>> 8;
            String unicode2 = Integer.toHexString(j);
            if (unicode2.length() == 1) {
                value.append(zerofix);
            }
            value.append(unicode2);
            j = c & 0xFF;
            unicode2 = Integer.toHexString(j);
            if (unicode2.length() == 1) {
                value.append(zerofix);
            }
            value.append(unicode2);
        }
        return value.toString();
    }

    public static String RStrip(String s) {
        int len;
        int st = 0;
        char[] val = s.toCharArray();
        for (len = s.length(); st < len && val[len - 1] <= ' '; --len) {
        }
        return s.substring(st, len);
    }

    public static Method GetMethod(Class<?> clazz, String name, Class<?> ... clazzs) throws NoSuchMethodException {
        Method m = clazz.getDeclaredMethod(name, clazzs);
        m.setAccessible(true);
        return m;
    }

    public static Type GetFieldType(Object obj, String name) throws NoSuchFieldException {
        Field f = obj.getClass().getDeclaredField(name);
        f.setAccessible(true);
        return f.getGenericType();
    }

    public static void SetFieldValue(Object obj, String name, Object value) throws Exception {
        Field f = obj.getClass().getDeclaredField(name);
        f.setAccessible(true);
        f.set(obj, value);
    }

    public static Class<?> GetClass(String name) throws ClassNotFoundException {
        return Utils.class.getClassLoader().loadClass(name);
    }

    public static Object GetFieldValue(String name, String fieldName, Object dst) throws Exception {
        Field field = Utils.GetClass(name).getDeclaredField(fieldName);
        field.setAccessible(true);
        return field.get(dst);
    }

    public static int CountChar(String text, char c) {
        int count = 0;
        for (char t : text.toCharArray()) {
            if (c != t) continue;
            ++count;
        }
        return count;
    }

    public static String ToEntity(int c, int entityType) {
        if (entityType == 10) {
            return "&#" + c + ";";
        }
        if (entityType == 16) {
            return "&#x" + Integer.toHexString(c) + ";";
        }
        if ((hexEntityHelp = (hexEntityHelp + 1) % 2) == 0) {
            return "&#" + c + ";";
        }
        return "&#x" + Integer.toHexString(c) + ";";
    }

    public static String WrapWithCdata(String text, int cap) {
        if (cap < 0 || text.length() < cap) {
            return "<![CDATA[" + text + "]]>";
        }
        int turn = (int)Math.ceil((float)text.length() / (float)cap);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < turn; ++i) {
            int start = i * cap;
            sb.append("<![CDATA[");
            if (i == turn - 1) {
                System.out.printf("%d %d \n", start, text.length());
                sb.append(text, start, text.length());
            } else {
                System.out.printf("%d %d \n", start, start + cap);
                sb.append(text, start, start + cap);
            }
            sb.append("]]>");
        }
        return sb.toString();
    }

    public static String UnicodeEscape(String text) {
        return Utils.StrToUnicode(text, true);
    }

    public static String EscapeText(String text, boolean escapeAll, int entityType) {
        int i;
        StringBuilder buffer = new StringBuilder();
        char[] block = null;
        int last = 0;
        int size = text.length();
        for (i = 0; i < size; ++i) {
            String entity = null;
            int c = text.codePointAt(i);
            switch (c) {
                case 167: {
                    entity = "\u00a7";
                    break;
                }
                case 60: {
                    entity = "&lt;";
                    break;
                }
                case 62: {
                    entity = "&gt;";
                    break;
                }
                case 39: {
                    entity = "&apos;";
                    break;
                }
                case 34: {
                    entity = "&quot;";
                    break;
                }
                case 38: {
                    entity = "&amp;";
                    break;
                }
                case 9: 
                case 10: 
                case 13: {
                    break;
                }
                default: {
                    if (c >= 32 && !escapeAll) break;
                    entity = Utils.ToEntity(c, entityType);
                }
            }
            if (entity != null) {
                if (block == null) {
                    block = text.toCharArray();
                }
                buffer.append(block, last, i - last);
                buffer.append(entity);
                last = i + 1;
                if (Character.isSupplementaryCodePoint(c)) {
                    ++last;
                }
            }
            if (!Character.isSupplementaryCodePoint(c)) continue;
            ++i;
        }
        if (last == 0) {
            return text;
        }
        if (last < size) {
            if (block == null) {
                block = text.toCharArray();
            }
            buffer.append(block, last, i - last);
        }
        String answer = buffer.toString();
        buffer.setLength(0);
        return answer;
    }
}

