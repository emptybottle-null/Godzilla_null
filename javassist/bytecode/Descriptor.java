/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package javassist.bytecode;

import java.util.Map;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtPrimitiveType;
import javassist.NotFoundException;

public class Descriptor {
    public static String toJvmName(String classname) {
        return classname.replace('.', '/');
    }

    public static String toJavaName(String classname) {
        return classname.replace('/', '.');
    }

    public static String toJvmName(CtClass clazz) {
        if (clazz.isArray()) {
            return Descriptor.of(clazz);
        }
        return Descriptor.toJvmName(clazz.getName());
    }

    public static String toClassName(String descriptor) {
        String name;
        int arrayDim = 0;
        int i = 0;
        char c = descriptor.charAt(0);
        while (c == '[') {
            ++arrayDim;
            c = descriptor.charAt(++i);
        }
        if (c == 'L') {
            int i2 = descriptor.indexOf(59, i++);
            name = descriptor.substring(i, i2).replace('/', '.');
            i = i2;
        } else if (c == 'V') {
            name = "void";
        } else if (c == 'I') {
            name = "int";
        } else if (c == 'B') {
            name = "byte";
        } else if (c == 'J') {
            name = "long";
        } else if (c == 'D') {
            name = "double";
        } else if (c == 'F') {
            name = "float";
        } else if (c == 'C') {
            name = "char";
        } else if (c == 'S') {
            name = "short";
        } else if (c == 'Z') {
            name = "boolean";
        } else {
            throw new RuntimeException("bad descriptor: " + descriptor);
        }
        if (i + 1 != descriptor.length()) {
            throw new RuntimeException("multiple descriptors?: " + descriptor);
        }
        if (arrayDim == 0) {
            return name;
        }
        StringBuffer sbuf = new StringBuffer(name);
        do {
            sbuf.append("[]");
        } while (--arrayDim > 0);
        return sbuf.toString();
    }

    public static String of(String classname) {
        if (classname.equals("void")) {
            return "V";
        }
        if (classname.equals("int")) {
            return "I";
        }
        if (classname.equals("byte")) {
            return "B";
        }
        if (classname.equals("long")) {
            return "J";
        }
        if (classname.equals("double")) {
            return "D";
        }
        if (classname.equals("float")) {
            return "F";
        }
        if (classname.equals("char")) {
            return "C";
        }
        if (classname.equals("short")) {
            return "S";
        }
        if (classname.equals("boolean")) {
            return "Z";
        }
        return "L" + Descriptor.toJvmName(classname) + ";";
    }

    public static String rename(String desc, String oldname, String newname) {
        int j;
        if (desc.indexOf(oldname) < 0) {
            return desc;
        }
        StringBuffer newdesc = new StringBuffer();
        int head = 0;
        int i = 0;
        while ((j = desc.indexOf(76, i)) >= 0) {
            if (desc.startsWith(oldname, j + 1) && desc.charAt(j + oldname.length() + 1) == ';') {
                newdesc.append(desc.substring(head, j));
                newdesc.append('L');
                newdesc.append(newname);
                newdesc.append(';');
                head = i = j + oldname.length() + 2;
                continue;
            }
            i = desc.indexOf(59, j) + 1;
            if (i >= 1) continue;
            break;
        }
        if (head == 0) {
            return desc;
        }
        int len = desc.length();
        if (head < len) {
            newdesc.append(desc.substring(head, len));
        }
        return newdesc.toString();
    }

    public static String rename(String desc, Map<String, String> map) {
        int k;
        int j;
        if (map == null) {
            return desc;
        }
        StringBuffer newdesc = new StringBuffer();
        int head = 0;
        int i = 0;
        while ((j = desc.indexOf(76, i)) >= 0 && (k = desc.indexOf(59, j)) >= 0) {
            i = k + 1;
            String name = desc.substring(j + 1, k);
            String name2 = map.get(name);
            if (name2 == null) continue;
            newdesc.append(desc.substring(head, j));
            newdesc.append('L');
            newdesc.append(name2);
            newdesc.append(';');
            head = i;
        }
        if (head == 0) {
            return desc;
        }
        int len = desc.length();
        if (head < len) {
            newdesc.append(desc.substring(head, len));
        }
        return newdesc.toString();
    }

    public static String of(CtClass type) {
        StringBuffer sbuf = new StringBuffer();
        Descriptor.toDescriptor(sbuf, type);
        return sbuf.toString();
    }

    private static void toDescriptor(StringBuffer desc, CtClass type) {
        if (type.isArray()) {
            desc.append('[');
            try {
                Descriptor.toDescriptor(desc, type.getComponentType());
            } catch (NotFoundException e) {
                desc.append('L');
                String name = type.getName();
                desc.append(Descriptor.toJvmName(name.substring(0, name.length() - 2)));
                desc.append(';');
            }
        } else if (type.isPrimitive()) {
            CtPrimitiveType pt = (CtPrimitiveType)type;
            desc.append(pt.getDescriptor());
        } else {
            desc.append('L');
            desc.append(type.getName().replace('.', '/'));
            desc.append(';');
        }
    }

    public static String ofConstructor(CtClass[] paramTypes) {
        return Descriptor.ofMethod(CtClass.voidType, paramTypes);
    }

    public static String ofMethod(CtClass returnType, CtClass[] paramTypes) {
        StringBuffer desc = new StringBuffer();
        desc.append('(');
        if (paramTypes != null) {
            int n = paramTypes.length;
            for (int i = 0; i < n; ++i) {
                Descriptor.toDescriptor(desc, paramTypes[i]);
            }
        }
        desc.append(')');
        if (returnType != null) {
            Descriptor.toDescriptor(desc, returnType);
        }
        return desc.toString();
    }

    public static String ofParameters(CtClass[] paramTypes) {
        return Descriptor.ofMethod(null, paramTypes);
    }

    public static String appendParameter(String classname, String desc) {
        int i = desc.indexOf(41);
        if (i < 0) {
            return desc;
        }
        StringBuffer newdesc = new StringBuffer();
        newdesc.append(desc.substring(0, i));
        newdesc.append('L');
        newdesc.append(classname.replace('.', '/'));
        newdesc.append(';');
        newdesc.append(desc.substring(i));
        return newdesc.toString();
    }

    public static String insertParameter(String classname, String desc) {
        if (desc.charAt(0) != '(') {
            return desc;
        }
        return "(L" + classname.replace('.', '/') + ';' + desc.substring(1);
    }

    public static String appendParameter(CtClass type, String descriptor) {
        int i = descriptor.indexOf(41);
        if (i < 0) {
            return descriptor;
        }
        StringBuffer newdesc = new StringBuffer();
        newdesc.append(descriptor.substring(0, i));
        Descriptor.toDescriptor(newdesc, type);
        newdesc.append(descriptor.substring(i));
        return newdesc.toString();
    }

    public static String insertParameter(CtClass type, String descriptor) {
        if (descriptor.charAt(0) != '(') {
            return descriptor;
        }
        return "(" + Descriptor.of(type) + descriptor.substring(1);
    }

    public static String changeReturnType(String classname, String desc) {
        int i = desc.indexOf(41);
        if (i < 0) {
            return desc;
        }
        StringBuffer newdesc = new StringBuffer();
        newdesc.append(desc.substring(0, i + 1));
        newdesc.append('L');
        newdesc.append(classname.replace('.', '/'));
        newdesc.append(';');
        return newdesc.toString();
    }

    public static CtClass[] getParameterTypes(String desc, ClassPool cp) throws NotFoundException {
        if (desc.charAt(0) != '(') {
            return null;
        }
        int num = Descriptor.numOfParameters(desc);
        CtClass[] args = new CtClass[num];
        int n = 0;
        int i = 1;
        while ((i = Descriptor.toCtClass(cp, desc, i, args, n++)) > 0) {
        }
        return args;
    }

    public static boolean eqParamTypes(String desc1, String desc2) {
        if (desc1.charAt(0) != '(') {
            return false;
        }
        int i = 0;
        char c;
        while ((c = desc1.charAt(i)) == desc2.charAt(i)) {
            if (c == ')') {
                return true;
            }
            ++i;
        }
        return false;
    }

    public static String getParamDescriptor(String decl) {
        return decl.substring(0, decl.indexOf(41) + 1);
    }

    public static CtClass getReturnType(String desc, ClassPool cp) throws NotFoundException {
        int i = desc.indexOf(41);
        if (i < 0) {
            return null;
        }
        CtClass[] type = new CtClass[1];
        Descriptor.toCtClass(cp, desc, i + 1, type, 0);
        return type[0];
    }

    public static int numOfParameters(String desc) {
        char c;
        int n = 0;
        int i = 1;
        while ((c = desc.charAt(i)) != ')') {
            while (c == '[') {
                c = desc.charAt(++i);
            }
            if (c == 'L') {
                if ((i = desc.indexOf(59, i) + 1) <= 0) {
                    throw new IndexOutOfBoundsException("bad descriptor");
                }
            } else {
                ++i;
            }
            ++n;
        }
        return n;
    }

    public static CtClass toCtClass(String desc, ClassPool cp) throws NotFoundException {
        CtClass[] clazz = new CtClass[1];
        int res = Descriptor.toCtClass(cp, desc, 0, clazz, 0);
        if (res >= 0) {
            return clazz[0];
        }
        return cp.get(desc.replace('/', '.'));
    }

    private static int toCtClass(ClassPool cp, String desc, int i, CtClass[] args, int n) throws NotFoundException {
        String name;
        int i2;
        int arrayDim = 0;
        char c = desc.charAt(i);
        while (c == '[') {
            ++arrayDim;
            c = desc.charAt(++i);
        }
        if (c == 'L') {
            i2 = desc.indexOf(59, ++i);
            name = desc.substring(i, i2++).replace('/', '.');
        } else {
            CtClass type = Descriptor.toPrimitiveClass(c);
            if (type == null) {
                return -1;
            }
            i2 = i + 1;
            if (arrayDim == 0) {
                args[n] = type;
                return i2;
            }
            name = type.getName();
        }
        if (arrayDim > 0) {
            StringBuffer sbuf = new StringBuffer(name);
            while (arrayDim-- > 0) {
                sbuf.append("[]");
            }
            name = sbuf.toString();
        }
        args[n] = cp.get(name);
        return i2;
    }

    static CtClass toPrimitiveClass(char c) {
        CtClass type = null;
        switch (c) {
            case 'Z': {
                type = CtClass.booleanType;
                break;
            }
            case 'C': {
                type = CtClass.charType;
                break;
            }
            case 'B': {
                type = CtClass.byteType;
                break;
            }
            case 'S': {
                type = CtClass.shortType;
                break;
            }
            case 'I': {
                type = CtClass.intType;
                break;
            }
            case 'J': {
                type = CtClass.longType;
                break;
            }
            case 'F': {
                type = CtClass.floatType;
                break;
            }
            case 'D': {
                type = CtClass.doubleType;
                break;
            }
            case 'V': {
                type = CtClass.voidType;
            }
        }
        return type;
    }

    public static int arrayDimension(String desc) {
        int dim = 0;
        while (desc.charAt(dim) == '[') {
            ++dim;
        }
        return dim;
    }

    public static String toArrayComponent(String desc, int dim) {
        return desc.substring(dim);
    }

    public static int dataSize(String desc) {
        return Descriptor.dataSize(desc, true);
    }

    public static int paramSize(String desc) {
        return -Descriptor.dataSize(desc, false);
    }

    private static int dataSize(String desc, boolean withRet) {
        int n = 0;
        char c = desc.charAt(0);
        if (c == '(') {
            int i = 1;
            while (true) {
                if ((c = desc.charAt(i)) == ')') {
                    c = desc.charAt(i + 1);
                    break;
                }
                boolean array = false;
                while (c == '[') {
                    array = true;
                    c = desc.charAt(++i);
                }
                if (c == 'L') {
                    if ((i = desc.indexOf(59, i) + 1) <= 0) {
                        throw new IndexOutOfBoundsException("bad descriptor");
                    }
                } else {
                    ++i;
                }
                if (!(array || c != 'J' && c != 'D')) {
                    n -= 2;
                    continue;
                }
                --n;
            }
        }
        if (withRet) {
            if (c == 'J' || c == 'D') {
                n += 2;
            } else if (c != 'V') {
                ++n;
            }
        }
        return n;
    }

    public static String toString(String desc) {
        return PrettyPrinter.toString(desc);
    }

    public static class Iterator {
        private String desc;
        private int index;
        private int curPos;
        private boolean param;

        public Iterator(String s) {
            this.desc = s;
            this.curPos = 0;
            this.index = 0;
            this.param = false;
        }

        public boolean hasNext() {
            return this.index < this.desc.length();
        }

        public boolean isParameter() {
            return this.param;
        }

        public char currentChar() {
            return this.desc.charAt(this.curPos);
        }

        public boolean is2byte() {
            char c = this.currentChar();
            return c == 'D' || c == 'J';
        }

        public int next() {
            int nextPos;
            char c;
            if ((c = this.desc.charAt(nextPos = this.index++)) == '(') {
                c = this.desc.charAt(++nextPos);
                this.param = true;
            }
            if (c == ')') {
                ++this.index;
                c = this.desc.charAt(++nextPos);
                this.param = false;
            }
            while (c == '[') {
                c = this.desc.charAt(++nextPos);
            }
            if (c == 'L') {
                if ((nextPos = this.desc.indexOf(59, nextPos) + 1) <= 0) {
                    throw new IndexOutOfBoundsException("bad descriptor");
                }
            } else {
                ++nextPos;
            }
            this.curPos = this.index;
            this.index = nextPos;
            return this.curPos;
        }
    }

    static class PrettyPrinter {
        PrettyPrinter() {
        }

        static String toString(String desc) {
            StringBuffer sbuf = new StringBuffer();
            if (desc.charAt(0) == '(') {
                int pos = 1;
                sbuf.append('(');
                while (desc.charAt(pos) != ')') {
                    if (pos > 1) {
                        sbuf.append(',');
                    }
                    pos = PrettyPrinter.readType(sbuf, pos, desc);
                }
                sbuf.append(')');
            } else {
                PrettyPrinter.readType(sbuf, 0, desc);
            }
            return sbuf.toString();
        }

        static int readType(StringBuffer sbuf, int pos, String desc) {
            char c = desc.charAt(pos);
            int arrayDim = 0;
            while (c == '[') {
                ++arrayDim;
                c = desc.charAt(++pos);
            }
            if (c == 'L') {
                while ((c = desc.charAt(++pos)) != ';') {
                    if (c == '/') {
                        c = '.';
                    }
                    sbuf.append(c);
                }
            } else {
                CtClass t = Descriptor.toPrimitiveClass(c);
                sbuf.append(t.getName());
            }
            while (arrayDim-- > 0) {
                sbuf.append("[]");
            }
            return pos + 1;
        }
    }
}

