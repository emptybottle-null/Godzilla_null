/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.asm.commons;

import java.util.HashMap;
import java.util.Map;
import org.springframework.asm.Type;

public class Method {
    private final String name;
    private final String desc;
    private static final Map DESCRIPTORS = new HashMap();

    public Method(String string, String string2) {
        this.name = string;
        this.desc = string2;
    }

    public Method(String string, Type type, Type[] typeArray) {
        this(string, Type.getMethodDescriptor(type, typeArray));
    }

    public static Method getMethod(String string) throws IllegalArgumentException {
        int n;
        int n2 = string.indexOf(32);
        int n3 = string.indexOf(40, n2) + 1;
        int n4 = string.indexOf(41, n3);
        if (n2 == -1 || n3 == -1 || n4 == -1) {
            throw new IllegalArgumentException();
        }
        String string2 = string.substring(0, n2);
        String string3 = string.substring(n2 + 1, n3 - 1).trim();
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append('(');
        do {
            if ((n = string.indexOf(44, n3)) == -1) {
                stringBuffer.append(Method.map(string.substring(n3, n4).trim()));
                continue;
            }
            stringBuffer.append(Method.map(string.substring(n3, n).trim()));
            n3 = n + 1;
        } while (n != -1);
        stringBuffer.append(')');
        stringBuffer.append(Method.map(string2));
        return new Method(string3, stringBuffer.toString());
    }

    private static String map(String string) {
        if (string.equals("")) {
            return string;
        }
        StringBuffer stringBuffer = new StringBuffer();
        int n = 0;
        while ((n = string.indexOf("[]", n) + 1) > 0) {
            stringBuffer.append('[');
        }
        String string2 = string.substring(0, string.length() - stringBuffer.length() * 2);
        String string3 = (String)DESCRIPTORS.get(string2);
        if (string3 != null) {
            stringBuffer.append(string3);
        } else {
            stringBuffer.append('L');
            if (string2.indexOf(46) < 0) {
                stringBuffer.append("java/lang/" + string2);
            } else {
                stringBuffer.append(string2.replace('.', '/'));
            }
            stringBuffer.append(';');
        }
        return stringBuffer.toString();
    }

    public String getName() {
        return this.name;
    }

    public String getDescriptor() {
        return this.desc;
    }

    public Type getReturnType() {
        return Type.getReturnType(this.desc);
    }

    public Type[] getArgumentTypes() {
        return Type.getArgumentTypes(this.desc);
    }

    public String toString() {
        return this.name + this.desc;
    }

    public boolean equals(Object object) {
        if (!(object instanceof Method)) {
            return false;
        }
        Method method = (Method)object;
        return this.name.equals(method.name) && this.desc.equals(method.desc);
    }

    public int hashCode() {
        return this.name.hashCode() ^ this.desc.hashCode();
    }

    static {
        DESCRIPTORS.put("void", "V");
        DESCRIPTORS.put("byte", "B");
        DESCRIPTORS.put("char", "C");
        DESCRIPTORS.put("double", "D");
        DESCRIPTORS.put("float", "F");
        DESCRIPTORS.put("int", "I");
        DESCRIPTORS.put("long", "J");
        DESCRIPTORS.put("short", "S");
        DESCRIPTORS.put("boolean", "Z");
    }
}

