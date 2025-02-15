/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package javassist.bytecode;

import java.io.PrintWriter;
import java.util.List;
import javassist.Modifier;
import javassist.bytecode.AccessFlag;
import javassist.bytecode.AnnotationsAttribute;
import javassist.bytecode.AttributeInfo;
import javassist.bytecode.BadBytecode;
import javassist.bytecode.ClassFile;
import javassist.bytecode.CodeAttribute;
import javassist.bytecode.FieldInfo;
import javassist.bytecode.MethodInfo;
import javassist.bytecode.ParameterAnnotationsAttribute;
import javassist.bytecode.SignatureAttribute;
import javassist.bytecode.StackMap;
import javassist.bytecode.StackMapTable;

public class ClassFilePrinter {
    public static void print(ClassFile cf) {
        ClassFilePrinter.print(cf, new PrintWriter(System.out, true));
    }

    public static void print(ClassFile cf, PrintWriter out) {
        int mod = AccessFlag.toModifier(cf.getAccessFlags() & 0xFFFFFFDF);
        out.println("major: " + cf.major + ", minor: " + cf.minor + " modifiers: " + Integer.toHexString(cf.getAccessFlags()));
        out.println(Modifier.toString(mod) + " class " + cf.getName() + " extends " + cf.getSuperclass());
        String[] infs = cf.getInterfaces();
        if (infs != null && infs.length > 0) {
            out.print("    implements ");
            out.print(infs[0]);
            for (int i = 1; i < infs.length; ++i) {
                out.print(", " + infs[i]);
            }
            out.println();
        }
        out.println();
        List<FieldInfo> fields = cf.getFields();
        for (FieldInfo finfo : fields) {
            int acc = finfo.getAccessFlags();
            out.println(Modifier.toString(AccessFlag.toModifier(acc)) + " " + finfo.getName() + "\t" + finfo.getDescriptor());
            ClassFilePrinter.printAttributes(finfo.getAttributes(), out, 'f');
        }
        out.println();
        List<MethodInfo> methods = cf.getMethods();
        for (MethodInfo minfo : methods) {
            int acc = minfo.getAccessFlags();
            out.println(Modifier.toString(AccessFlag.toModifier(acc)) + " " + minfo.getName() + "\t" + minfo.getDescriptor());
            ClassFilePrinter.printAttributes(minfo.getAttributes(), out, 'm');
            out.println();
        }
        out.println();
        ClassFilePrinter.printAttributes(cf.getAttributes(), out, 'c');
    }

    static void printAttributes(List<AttributeInfo> list, PrintWriter out, char kind) {
        if (list == null) {
            return;
        }
        for (AttributeInfo ai : list) {
            if (ai instanceof CodeAttribute) {
                CodeAttribute ca = (CodeAttribute)ai;
                out.println("attribute: " + ai.getName() + ": " + ai.getClass().getName());
                out.println("max stack " + ca.getMaxStack() + ", max locals " + ca.getMaxLocals() + ", " + ca.getExceptionTable().size() + " catch blocks");
                out.println("<code attribute begin>");
                ClassFilePrinter.printAttributes(ca.getAttributes(), out, kind);
                out.println("<code attribute end>");
                continue;
            }
            if (ai instanceof AnnotationsAttribute) {
                out.println("annnotation: " + ai.toString());
                continue;
            }
            if (ai instanceof ParameterAnnotationsAttribute) {
                out.println("parameter annnotations: " + ai.toString());
                continue;
            }
            if (ai instanceof StackMapTable) {
                out.println("<stack map table begin>");
                StackMapTable.Printer.print((StackMapTable)ai, out);
                out.println("<stack map table end>");
                continue;
            }
            if (ai instanceof StackMap) {
                out.println("<stack map begin>");
                ((StackMap)ai).print(out);
                out.println("<stack map end>");
                continue;
            }
            if (ai instanceof SignatureAttribute) {
                SignatureAttribute sa = (SignatureAttribute)ai;
                String sig = sa.getSignature();
                out.println("signature: " + sig);
                try {
                    String s = kind == 'c' ? SignatureAttribute.toClassSignature(sig).toString() : (kind == 'm' ? SignatureAttribute.toMethodSignature(sig).toString() : SignatureAttribute.toFieldSignature(sig).toString());
                    out.println("           " + s);
                } catch (BadBytecode e) {
                    out.println("           syntax error");
                }
                continue;
            }
            out.println("attribute: " + ai.getName() + " (" + ai.get().length + " byte): " + ai.getClass().getName());
        }
    }
}

