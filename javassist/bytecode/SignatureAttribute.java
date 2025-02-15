/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package javassist.bytecode;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javassist.CtClass;
import javassist.bytecode.AttributeInfo;
import javassist.bytecode.BadBytecode;
import javassist.bytecode.ByteArray;
import javassist.bytecode.ConstPool;
import javassist.bytecode.Descriptor;

public class SignatureAttribute
extends AttributeInfo {
    public static final String tag = "Signature";

    SignatureAttribute(ConstPool cp, int n, DataInputStream in) throws IOException {
        super(cp, n, in);
    }

    public SignatureAttribute(ConstPool cp, String signature) {
        super(cp, tag);
        int index = cp.addUtf8Info(signature);
        byte[] bvalue = new byte[]{(byte)(index >>> 8), (byte)index};
        this.set(bvalue);
    }

    public String getSignature() {
        return this.getConstPool().getUtf8Info(ByteArray.readU16bit(this.get(), 0));
    }

    public void setSignature(String sig) {
        int index = this.getConstPool().addUtf8Info(sig);
        ByteArray.write16bit(index, this.info, 0);
    }

    @Override
    public AttributeInfo copy(ConstPool newCp, Map<String, String> classnames) {
        return new SignatureAttribute(newCp, this.getSignature());
    }

    @Override
    void renameClass(String oldname, String newname) {
        String sig = SignatureAttribute.renameClass(this.getSignature(), oldname, newname);
        this.setSignature(sig);
    }

    @Override
    void renameClass(Map<String, String> classnames) {
        String sig = SignatureAttribute.renameClass(this.getSignature(), classnames);
        this.setSignature(sig);
    }

    static String renameClass(String desc, String oldname, String newname) {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put(oldname, newname);
        return SignatureAttribute.renameClass(desc, map);
    }

    static String renameClass(String desc, Map<String, String> map) {
        int j;
        if (map == null) {
            return desc;
        }
        StringBuilder newdesc = new StringBuilder();
        int head = 0;
        int i = 0;
        while ((j = desc.indexOf(76, i)) >= 0) {
            char c;
            StringBuilder nameBuf = new StringBuilder();
            int k = j;
            try {
                while ((c = desc.charAt(++k)) != ';') {
                    nameBuf.append(c);
                    if (c != '<') continue;
                    while ((c = desc.charAt(++k)) != '>') {
                        nameBuf.append(c);
                    }
                    nameBuf.append(c);
                }
            } catch (IndexOutOfBoundsException e) {
                break;
            }
            i = k + 1;
            String name = nameBuf.toString();
            String name2 = map.get(name);
            if (name2 == null) continue;
            newdesc.append(desc.substring(head, j));
            newdesc.append('L');
            newdesc.append(name2);
            newdesc.append(c);
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

    private static boolean isNamePart(int c) {
        return c != 59 && c != 60;
    }

    public static ClassSignature toClassSignature(String sig) throws BadBytecode {
        try {
            return SignatureAttribute.parseSig(sig);
        } catch (IndexOutOfBoundsException e) {
            throw SignatureAttribute.error(sig);
        }
    }

    public static MethodSignature toMethodSignature(String sig) throws BadBytecode {
        try {
            return SignatureAttribute.parseMethodSig(sig);
        } catch (IndexOutOfBoundsException e) {
            throw SignatureAttribute.error(sig);
        }
    }

    public static ObjectType toFieldSignature(String sig) throws BadBytecode {
        try {
            return SignatureAttribute.parseObjectType(sig, new Cursor(), false);
        } catch (IndexOutOfBoundsException e) {
            throw SignatureAttribute.error(sig);
        }
    }

    public static Type toTypeSignature(String sig) throws BadBytecode {
        try {
            return SignatureAttribute.parseType(sig, new Cursor());
        } catch (IndexOutOfBoundsException e) {
            throw SignatureAttribute.error(sig);
        }
    }

    private static ClassSignature parseSig(String sig) throws BadBytecode, IndexOutOfBoundsException {
        Cursor cur = new Cursor();
        TypeParameter[] tp = SignatureAttribute.parseTypeParams(sig, cur);
        ClassType superClass = SignatureAttribute.parseClassType(sig, cur);
        int sigLen = sig.length();
        ArrayList<ClassType> ifArray = new ArrayList<ClassType>();
        while (cur.position < sigLen && sig.charAt(cur.position) == 'L') {
            ifArray.add(SignatureAttribute.parseClassType(sig, cur));
        }
        ClassType[] ifs = ifArray.toArray(new ClassType[ifArray.size()]);
        return new ClassSignature(tp, superClass, ifs);
    }

    private static MethodSignature parseMethodSig(String sig) throws BadBytecode {
        Cursor cur = new Cursor();
        TypeParameter[] tp = SignatureAttribute.parseTypeParams(sig, cur);
        if (sig.charAt(cur.position++) != '(') {
            throw SignatureAttribute.error(sig);
        }
        ArrayList<Type> params = new ArrayList<Type>();
        while (sig.charAt(cur.position) != ')') {
            Type t = SignatureAttribute.parseType(sig, cur);
            params.add(t);
        }
        ++cur.position;
        Type ret = SignatureAttribute.parseType(sig, cur);
        int sigLen = sig.length();
        ArrayList<ObjectType> exceptions = new ArrayList<ObjectType>();
        while (cur.position < sigLen && sig.charAt(cur.position) == '^') {
            ++cur.position;
            ObjectType t = SignatureAttribute.parseObjectType(sig, cur, false);
            if (t instanceof ArrayType) {
                throw SignatureAttribute.error(sig);
            }
            exceptions.add(t);
        }
        Type[] p = params.toArray(new Type[params.size()]);
        ObjectType[] ex = exceptions.toArray(new ObjectType[exceptions.size()]);
        return new MethodSignature(tp, p, ret, ex);
    }

    private static TypeParameter[] parseTypeParams(String sig, Cursor cur) throws BadBytecode {
        ArrayList<TypeParameter> typeParam = new ArrayList<TypeParameter>();
        if (sig.charAt(cur.position) == '<') {
            ++cur.position;
            while (sig.charAt(cur.position) != '>') {
                int nameBegin = cur.position;
                int nameEnd = cur.indexOf(sig, 58);
                ObjectType classBound = SignatureAttribute.parseObjectType(sig, cur, true);
                ArrayList<ObjectType> ifBound = new ArrayList<ObjectType>();
                while (sig.charAt(cur.position) == ':') {
                    ++cur.position;
                    ObjectType t = SignatureAttribute.parseObjectType(sig, cur, false);
                    ifBound.add(t);
                }
                TypeParameter p = new TypeParameter(sig, nameBegin, nameEnd, classBound, ifBound.toArray(new ObjectType[ifBound.size()]));
                typeParam.add(p);
            }
            ++cur.position;
        }
        return typeParam.toArray(new TypeParameter[typeParam.size()]);
    }

    private static ObjectType parseObjectType(String sig, Cursor c, boolean dontThrow) throws BadBytecode {
        int begin = c.position;
        switch (sig.charAt(begin)) {
            case 'L': {
                return SignatureAttribute.parseClassType2(sig, c, null);
            }
            case 'T': {
                int i = c.indexOf(sig, 59);
                return new TypeVariable(sig, begin + 1, i);
            }
            case '[': {
                return SignatureAttribute.parseArray(sig, c);
            }
        }
        if (dontThrow) {
            return null;
        }
        throw SignatureAttribute.error(sig);
    }

    private static ClassType parseClassType(String sig, Cursor c) throws BadBytecode {
        if (sig.charAt(c.position) == 'L') {
            return SignatureAttribute.parseClassType2(sig, c, null);
        }
        throw SignatureAttribute.error(sig);
    }

    private static ClassType parseClassType2(String sig, Cursor c, ClassType parent) throws BadBytecode {
        TypeArgument[] targs;
        char t;
        int start = ++c.position;
        while ((t = sig.charAt(c.position++)) != '$' && t != '<' && t != ';') {
        }
        int end = c.position - 1;
        if (t == '<') {
            targs = SignatureAttribute.parseTypeArgs(sig, c);
            t = sig.charAt(c.position++);
        } else {
            targs = null;
        }
        ClassType thisClass = ClassType.make(sig, start, end, targs, parent);
        if (t == '$' || t == '.') {
            --c.position;
            return SignatureAttribute.parseClassType2(sig, c, thisClass);
        }
        return thisClass;
    }

    private static TypeArgument[] parseTypeArgs(String sig, Cursor c) throws BadBytecode {
        char t;
        ArrayList<TypeArgument> args = new ArrayList<TypeArgument>();
        while ((t = sig.charAt(c.position++)) != '>') {
            TypeArgument ta;
            if (t == '*') {
                ta = new TypeArgument(null, '*');
            } else {
                if (t != '+' && t != '-') {
                    t = ' ';
                    --c.position;
                }
                ta = new TypeArgument(SignatureAttribute.parseObjectType(sig, c, false), t);
            }
            args.add(ta);
        }
        return args.toArray(new TypeArgument[args.size()]);
    }

    private static ObjectType parseArray(String sig, Cursor c) throws BadBytecode {
        int dim = 1;
        while (sig.charAt(++c.position) == '[') {
            ++dim;
        }
        return new ArrayType(dim, SignatureAttribute.parseType(sig, c));
    }

    private static Type parseType(String sig, Cursor c) throws BadBytecode {
        Type t = SignatureAttribute.parseObjectType(sig, c, true);
        if (t == null) {
            t = new BaseType(sig.charAt(c.position++));
        }
        return t;
    }

    private static BadBytecode error(String sig) {
        return new BadBytecode("bad signature: " + sig);
    }

    public static class TypeVariable
    extends ObjectType {
        String name;

        TypeVariable(String sig, int begin, int end) {
            this.name = sig.substring(begin, end);
        }

        public TypeVariable(String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }

        public String toString() {
            return this.name;
        }

        @Override
        void encode(StringBuffer sb) {
            sb.append('T').append(this.name).append(';');
        }
    }

    public static class ArrayType
    extends ObjectType {
        int dim;
        Type componentType;

        public ArrayType(int d, Type comp) {
            this.dim = d;
            this.componentType = comp;
        }

        public int getDimension() {
            return this.dim;
        }

        public Type getComponentType() {
            return this.componentType;
        }

        public String toString() {
            StringBuffer sbuf = new StringBuffer(this.componentType.toString());
            for (int i = 0; i < this.dim; ++i) {
                sbuf.append("[]");
            }
            return sbuf.toString();
        }

        @Override
        void encode(StringBuffer sb) {
            for (int i = 0; i < this.dim; ++i) {
                sb.append('[');
            }
            this.componentType.encode(sb);
        }
    }

    public static class NestedClassType
    extends ClassType {
        ClassType parent;

        NestedClassType(String s, int b, int e, TypeArgument[] targs, ClassType p) {
            super(s, b, e, targs);
            this.parent = p;
        }

        public NestedClassType(ClassType parent, String className, TypeArgument[] args) {
            super(className, args);
            this.parent = parent;
        }

        @Override
        public ClassType getDeclaringClass() {
            return this.parent;
        }
    }

    public static class ClassType
    extends ObjectType {
        String name;
        TypeArgument[] arguments;
        public static ClassType OBJECT = new ClassType("java.lang.Object", null);

        static ClassType make(String s, int b, int e, TypeArgument[] targs, ClassType parent) {
            if (parent == null) {
                return new ClassType(s, b, e, targs);
            }
            return new NestedClassType(s, b, e, targs, parent);
        }

        ClassType(String signature, int begin, int end, TypeArgument[] targs) {
            this.name = signature.substring(begin, end).replace('/', '.');
            this.arguments = targs;
        }

        public ClassType(String className, TypeArgument[] args) {
            this.name = className;
            this.arguments = args;
        }

        public ClassType(String className) {
            this(className, null);
        }

        public String getName() {
            return this.name;
        }

        public TypeArgument[] getTypeArguments() {
            return this.arguments;
        }

        public ClassType getDeclaringClass() {
            return null;
        }

        public String toString() {
            StringBuffer sbuf = new StringBuffer();
            ClassType parent = this.getDeclaringClass();
            if (parent != null) {
                sbuf.append(parent.toString()).append('.');
            }
            return this.toString2(sbuf);
        }

        private String toString2(StringBuffer sbuf) {
            sbuf.append(this.name);
            if (this.arguments != null) {
                sbuf.append('<');
                int n = this.arguments.length;
                for (int i = 0; i < n; ++i) {
                    if (i > 0) {
                        sbuf.append(", ");
                    }
                    sbuf.append(this.arguments[i].toString());
                }
                sbuf.append('>');
            }
            return sbuf.toString();
        }

        @Override
        public String jvmTypeName() {
            StringBuffer sbuf = new StringBuffer();
            ClassType parent = this.getDeclaringClass();
            if (parent != null) {
                sbuf.append(parent.jvmTypeName()).append('$');
            }
            return this.toString2(sbuf);
        }

        @Override
        void encode(StringBuffer sb) {
            sb.append('L');
            this.encode2(sb);
            sb.append(';');
        }

        void encode2(StringBuffer sb) {
            ClassType parent = this.getDeclaringClass();
            if (parent != null) {
                parent.encode2(sb);
                sb.append('$');
            }
            sb.append(this.name.replace('.', '/'));
            if (this.arguments != null) {
                TypeArgument.encode(sb, this.arguments);
            }
        }
    }

    public static abstract class ObjectType
    extends Type {
        public String encode() {
            StringBuffer sb = new StringBuffer();
            this.encode(sb);
            return sb.toString();
        }
    }

    public static class BaseType
    extends Type {
        char descriptor;

        BaseType(char c) {
            this.descriptor = c;
        }

        public BaseType(String typeName) {
            this(Descriptor.of(typeName).charAt(0));
        }

        public char getDescriptor() {
            return this.descriptor;
        }

        public CtClass getCtlass() {
            return Descriptor.toPrimitiveClass(this.descriptor);
        }

        public String toString() {
            return Descriptor.toClassName(Character.toString(this.descriptor));
        }

        @Override
        void encode(StringBuffer sb) {
            sb.append(this.descriptor);
        }
    }

    public static abstract class Type {
        abstract void encode(StringBuffer var1);

        static void toString(StringBuffer sbuf, Type[] ts) {
            for (int i = 0; i < ts.length; ++i) {
                if (i > 0) {
                    sbuf.append(", ");
                }
                sbuf.append(ts[i]);
            }
        }

        public String jvmTypeName() {
            return this.toString();
        }
    }

    public static class TypeArgument {
        ObjectType arg;
        char wildcard;

        TypeArgument(ObjectType a, char w) {
            this.arg = a;
            this.wildcard = w;
        }

        public TypeArgument(ObjectType t) {
            this(t, ' ');
        }

        public TypeArgument() {
            this(null, '*');
        }

        public static TypeArgument subclassOf(ObjectType t) {
            return new TypeArgument(t, '+');
        }

        public static TypeArgument superOf(ObjectType t) {
            return new TypeArgument(t, '-');
        }

        public char getKind() {
            return this.wildcard;
        }

        public boolean isWildcard() {
            return this.wildcard != ' ';
        }

        public ObjectType getType() {
            return this.arg;
        }

        public String toString() {
            if (this.wildcard == '*') {
                return "?";
            }
            String type = this.arg.toString();
            if (this.wildcard == ' ') {
                return type;
            }
            if (this.wildcard == '+') {
                return "? extends " + type;
            }
            return "? super " + type;
        }

        static void encode(StringBuffer sb, TypeArgument[] args) {
            sb.append('<');
            for (int i = 0; i < args.length; ++i) {
                TypeArgument ta = args[i];
                if (ta.isWildcard()) {
                    sb.append(ta.wildcard);
                }
                if (ta.getType() == null) continue;
                ta.getType().encode(sb);
            }
            sb.append('>');
        }
    }

    public static class TypeParameter {
        String name;
        ObjectType superClass;
        ObjectType[] superInterfaces;

        TypeParameter(String sig, int nb, int ne, ObjectType sc, ObjectType[] si) {
            this.name = sig.substring(nb, ne);
            this.superClass = sc;
            this.superInterfaces = si;
        }

        public TypeParameter(String name, ObjectType superClass, ObjectType[] superInterfaces) {
            this.name = name;
            this.superClass = superClass;
            this.superInterfaces = superInterfaces == null ? new ObjectType[0] : superInterfaces;
        }

        public TypeParameter(String name) {
            this(name, null, null);
        }

        public String getName() {
            return this.name;
        }

        public ObjectType getClassBound() {
            return this.superClass;
        }

        public ObjectType[] getInterfaceBound() {
            return this.superInterfaces;
        }

        public String toString() {
            int len;
            StringBuffer sbuf = new StringBuffer(this.getName());
            if (this.superClass != null) {
                sbuf.append(" extends ").append(this.superClass.toString());
            }
            if ((len = this.superInterfaces.length) > 0) {
                for (int i = 0; i < len; ++i) {
                    if (i > 0 || this.superClass != null) {
                        sbuf.append(" & ");
                    } else {
                        sbuf.append(" extends ");
                    }
                    sbuf.append(this.superInterfaces[i].toString());
                }
            }
            return sbuf.toString();
        }

        static void toString(StringBuffer sbuf, TypeParameter[] tp) {
            sbuf.append('<');
            for (int i = 0; i < tp.length; ++i) {
                if (i > 0) {
                    sbuf.append(", ");
                }
                sbuf.append(tp[i]);
            }
            sbuf.append('>');
        }

        void encode(StringBuffer sb) {
            sb.append(this.name);
            if (this.superClass == null) {
                sb.append(":Ljava/lang/Object;");
            } else {
                sb.append(':');
                this.superClass.encode(sb);
            }
            for (int i = 0; i < this.superInterfaces.length; ++i) {
                sb.append(':');
                this.superInterfaces[i].encode(sb);
            }
        }
    }

    public static class MethodSignature {
        TypeParameter[] typeParams;
        Type[] params;
        Type retType;
        ObjectType[] exceptions;

        public MethodSignature(TypeParameter[] tp, Type[] params, Type ret, ObjectType[] ex) {
            this.typeParams = tp == null ? new TypeParameter[]{} : tp;
            this.params = params == null ? new Type[]{} : params;
            this.retType = ret == null ? new BaseType("void") : ret;
            this.exceptions = ex == null ? new ObjectType[]{} : ex;
        }

        public TypeParameter[] getTypeParameters() {
            return this.typeParams;
        }

        public Type[] getParameterTypes() {
            return this.params;
        }

        public Type getReturnType() {
            return this.retType;
        }

        public ObjectType[] getExceptionTypes() {
            return this.exceptions;
        }

        public String toString() {
            StringBuffer sbuf = new StringBuffer();
            TypeParameter.toString(sbuf, this.typeParams);
            sbuf.append(" (");
            Type.toString(sbuf, this.params);
            sbuf.append(") ");
            sbuf.append(this.retType);
            if (this.exceptions.length > 0) {
                sbuf.append(" throws ");
                Type.toString(sbuf, this.exceptions);
            }
            return sbuf.toString();
        }

        public String encode() {
            int i;
            StringBuffer sbuf = new StringBuffer();
            if (this.typeParams.length > 0) {
                sbuf.append('<');
                for (i = 0; i < this.typeParams.length; ++i) {
                    this.typeParams[i].encode(sbuf);
                }
                sbuf.append('>');
            }
            sbuf.append('(');
            for (i = 0; i < this.params.length; ++i) {
                this.params[i].encode(sbuf);
            }
            sbuf.append(')');
            this.retType.encode(sbuf);
            if (this.exceptions.length > 0) {
                for (i = 0; i < this.exceptions.length; ++i) {
                    sbuf.append('^');
                    this.exceptions[i].encode(sbuf);
                }
            }
            return sbuf.toString();
        }
    }

    public static class ClassSignature {
        TypeParameter[] params;
        ClassType superClass;
        ClassType[] interfaces;

        public ClassSignature(TypeParameter[] params, ClassType superClass, ClassType[] interfaces) {
            this.params = params == null ? new TypeParameter[]{} : params;
            this.superClass = superClass == null ? ClassType.OBJECT : superClass;
            this.interfaces = interfaces == null ? new ClassType[]{} : interfaces;
        }

        public ClassSignature(TypeParameter[] p) {
            this(p, null, null);
        }

        public TypeParameter[] getParameters() {
            return this.params;
        }

        public ClassType getSuperClass() {
            return this.superClass;
        }

        public ClassType[] getInterfaces() {
            return this.interfaces;
        }

        public String toString() {
            StringBuffer sbuf = new StringBuffer();
            TypeParameter.toString(sbuf, this.params);
            sbuf.append(" extends ").append(this.superClass);
            if (this.interfaces.length > 0) {
                sbuf.append(" implements ");
                Type.toString(sbuf, this.interfaces);
            }
            return sbuf.toString();
        }

        public String encode() {
            int i;
            StringBuffer sbuf = new StringBuffer();
            if (this.params.length > 0) {
                sbuf.append('<');
                for (i = 0; i < this.params.length; ++i) {
                    this.params[i].encode(sbuf);
                }
                sbuf.append('>');
            }
            this.superClass.encode(sbuf);
            for (i = 0; i < this.interfaces.length; ++i) {
                this.interfaces[i].encode(sbuf);
            }
            return sbuf.toString();
        }
    }

    private static class Cursor {
        int position = 0;

        private Cursor() {
        }

        int indexOf(String s, int ch) throws BadBytecode {
            int i = s.indexOf(ch, this.position);
            if (i < 0) {
                throw SignatureAttribute.error(s);
            }
            this.position = i + 1;
            return i;
        }
    }
}

