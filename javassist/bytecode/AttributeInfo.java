/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package javassist.bytecode;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import javassist.bytecode.AnnotationDefaultAttribute;
import javassist.bytecode.AnnotationsAttribute;
import javassist.bytecode.BootstrapMethodsAttribute;
import javassist.bytecode.CodeAttribute;
import javassist.bytecode.ConstPool;
import javassist.bytecode.ConstantAttribute;
import javassist.bytecode.DeprecatedAttribute;
import javassist.bytecode.EnclosingMethodAttribute;
import javassist.bytecode.ExceptionsAttribute;
import javassist.bytecode.InnerClassesAttribute;
import javassist.bytecode.LineNumberAttribute;
import javassist.bytecode.LocalVariableAttribute;
import javassist.bytecode.LocalVariableTypeAttribute;
import javassist.bytecode.MethodParametersAttribute;
import javassist.bytecode.NestHostAttribute;
import javassist.bytecode.NestMembersAttribute;
import javassist.bytecode.ParameterAnnotationsAttribute;
import javassist.bytecode.SignatureAttribute;
import javassist.bytecode.SourceFileAttribute;
import javassist.bytecode.StackMap;
import javassist.bytecode.StackMapTable;
import javassist.bytecode.SyntheticAttribute;
import javassist.bytecode.TypeAnnotationsAttribute;

public class AttributeInfo {
    protected ConstPool constPool;
    int name;
    byte[] info;

    protected AttributeInfo(ConstPool cp, int attrname, byte[] attrinfo) {
        this.constPool = cp;
        this.name = attrname;
        this.info = attrinfo;
    }

    protected AttributeInfo(ConstPool cp, String attrname) {
        this(cp, attrname, (byte[])null);
    }

    public AttributeInfo(ConstPool cp, String attrname, byte[] attrinfo) {
        this(cp, cp.addUtf8Info(attrname), attrinfo);
    }

    protected AttributeInfo(ConstPool cp, int n, DataInputStream in) throws IOException {
        this.constPool = cp;
        this.name = n;
        int len = in.readInt();
        this.info = new byte[len];
        if (len > 0) {
            in.readFully(this.info);
        }
    }

    static AttributeInfo read(ConstPool cp, DataInputStream in) throws IOException {
        int name = in.readUnsignedShort();
        String nameStr = cp.getUtf8Info(name);
        char first = nameStr.charAt(0);
        if (first < 'E') {
            if (nameStr.equals("AnnotationDefault")) {
                return new AnnotationDefaultAttribute(cp, name, in);
            }
            if (nameStr.equals("BootstrapMethods")) {
                return new BootstrapMethodsAttribute(cp, name, in);
            }
            if (nameStr.equals("Code")) {
                return new CodeAttribute(cp, name, in);
            }
            if (nameStr.equals("ConstantValue")) {
                return new ConstantAttribute(cp, name, in);
            }
            if (nameStr.equals("Deprecated")) {
                return new DeprecatedAttribute(cp, name, in);
            }
        }
        if (first < 'M') {
            if (nameStr.equals("EnclosingMethod")) {
                return new EnclosingMethodAttribute(cp, name, in);
            }
            if (nameStr.equals("Exceptions")) {
                return new ExceptionsAttribute(cp, name, in);
            }
            if (nameStr.equals("InnerClasses")) {
                return new InnerClassesAttribute(cp, name, in);
            }
            if (nameStr.equals("LineNumberTable")) {
                return new LineNumberAttribute(cp, name, in);
            }
            if (nameStr.equals("LocalVariableTable")) {
                return new LocalVariableAttribute(cp, name, in);
            }
            if (nameStr.equals("LocalVariableTypeTable")) {
                return new LocalVariableTypeAttribute(cp, name, in);
            }
        }
        if (first < 'S') {
            if (nameStr.equals("MethodParameters")) {
                return new MethodParametersAttribute(cp, name, in);
            }
            if (nameStr.equals("NestHost")) {
                return new NestHostAttribute(cp, name, in);
            }
            if (nameStr.equals("NestMembers")) {
                return new NestMembersAttribute(cp, name, in);
            }
            if (nameStr.equals("RuntimeVisibleAnnotations") || nameStr.equals("RuntimeInvisibleAnnotations")) {
                return new AnnotationsAttribute(cp, name, in);
            }
            if (nameStr.equals("RuntimeVisibleParameterAnnotations") || nameStr.equals("RuntimeInvisibleParameterAnnotations")) {
                return new ParameterAnnotationsAttribute(cp, name, in);
            }
            if (nameStr.equals("RuntimeVisibleTypeAnnotations") || nameStr.equals("RuntimeInvisibleTypeAnnotations")) {
                return new TypeAnnotationsAttribute(cp, name, in);
            }
        }
        if (first >= 'S') {
            if (nameStr.equals("Signature")) {
                return new SignatureAttribute(cp, name, in);
            }
            if (nameStr.equals("SourceFile")) {
                return new SourceFileAttribute(cp, name, in);
            }
            if (nameStr.equals("Synthetic")) {
                return new SyntheticAttribute(cp, name, in);
            }
            if (nameStr.equals("StackMap")) {
                return new StackMap(cp, name, in);
            }
            if (nameStr.equals("StackMapTable")) {
                return new StackMapTable(cp, name, in);
            }
        }
        return new AttributeInfo(cp, name, in);
    }

    public String getName() {
        return this.constPool.getUtf8Info(this.name);
    }

    public ConstPool getConstPool() {
        return this.constPool;
    }

    public int length() {
        return this.info.length + 6;
    }

    public byte[] get() {
        return this.info;
    }

    public void set(byte[] newinfo) {
        this.info = newinfo;
    }

    public AttributeInfo copy(ConstPool newCp, Map<String, String> classnames) {
        return new AttributeInfo(newCp, this.getName(), Arrays.copyOf(this.info, this.info.length));
    }

    void write(DataOutputStream out) throws IOException {
        out.writeShort(this.name);
        out.writeInt(this.info.length);
        if (this.info.length > 0) {
            out.write(this.info);
        }
    }

    static int getLength(List<AttributeInfo> attributes) {
        int size = 0;
        for (AttributeInfo attr : attributes) {
            size += attr.length();
        }
        return size;
    }

    static AttributeInfo lookup(List<AttributeInfo> attributes, String name) {
        if (attributes == null) {
            return null;
        }
        for (AttributeInfo ai : attributes) {
            if (!ai.getName().equals(name)) continue;
            return ai;
        }
        return null;
    }

    static synchronized AttributeInfo remove(List<AttributeInfo> attributes, String name) {
        if (attributes == null) {
            return null;
        }
        for (AttributeInfo ai : attributes) {
            if (!ai.getName().equals(name) || !attributes.remove(ai)) continue;
            return ai;
        }
        return null;
    }

    static void writeAll(List<AttributeInfo> attributes, DataOutputStream out) throws IOException {
        if (attributes == null) {
            return;
        }
        for (AttributeInfo attr : attributes) {
            attr.write(out);
        }
    }

    static List<AttributeInfo> copyAll(List<AttributeInfo> attributes, ConstPool cp) {
        if (attributes == null) {
            return null;
        }
        ArrayList<AttributeInfo> newList = new ArrayList<AttributeInfo>();
        for (AttributeInfo attr : attributes) {
            newList.add(attr.copy(cp, null));
        }
        return newList;
    }

    void renameClass(String oldname, String newname) {
    }

    void renameClass(Map<String, String> classnames) {
    }

    static void renameClass(List<AttributeInfo> attributes, String oldname, String newname) {
        if (attributes == null) {
            return;
        }
        for (AttributeInfo ai : attributes) {
            ai.renameClass(oldname, newname);
        }
    }

    static void renameClass(List<AttributeInfo> attributes, Map<String, String> classnames) {
        if (attributes == null) {
            return;
        }
        for (AttributeInfo ai : attributes) {
            ai.renameClass(classnames);
        }
    }

    void getRefClasses(Map<String, String> classnames) {
    }

    static void getRefClasses(List<AttributeInfo> attributes, Map<String, String> classnames) {
        if (attributes == null) {
            return;
        }
        for (AttributeInfo ai : attributes) {
            ai.getRefClasses(classnames);
        }
    }
}

