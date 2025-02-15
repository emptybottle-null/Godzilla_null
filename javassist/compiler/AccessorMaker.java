/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package javassist.compiler;

import java.util.HashMap;
import java.util.Map;
import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.NotFoundException;
import javassist.bytecode.Bytecode;
import javassist.bytecode.ClassFile;
import javassist.bytecode.ConstPool;
import javassist.bytecode.Descriptor;
import javassist.bytecode.ExceptionsAttribute;
import javassist.bytecode.FieldInfo;
import javassist.bytecode.MethodInfo;
import javassist.bytecode.SyntheticAttribute;
import javassist.compiler.CompileError;

public class AccessorMaker {
    private CtClass clazz;
    private int uniqueNumber;
    private Map<String, Object> accessors;
    static final String lastParamType = "javassist.runtime.Inner";

    public AccessorMaker(CtClass c) {
        this.clazz = c;
        this.uniqueNumber = 1;
        this.accessors = new HashMap<String, Object>();
    }

    public String getConstructor(CtClass c, String desc, MethodInfo orig) throws CompileError {
        String key = "<init>:" + desc;
        String consDesc = (String)this.accessors.get(key);
        if (consDesc != null) {
            return consDesc;
        }
        consDesc = Descriptor.appendParameter(lastParamType, desc);
        ClassFile cf = this.clazz.getClassFile();
        try {
            ConstPool cp = cf.getConstPool();
            ClassPool pool = this.clazz.getClassPool();
            MethodInfo minfo = new MethodInfo(cp, "<init>", consDesc);
            minfo.setAccessFlags(0);
            minfo.addAttribute(new SyntheticAttribute(cp));
            ExceptionsAttribute ea = orig.getExceptionsAttribute();
            if (ea != null) {
                minfo.addAttribute(ea.copy(cp, null));
            }
            CtClass[] params = Descriptor.getParameterTypes(desc, pool);
            Bytecode code = new Bytecode(cp);
            code.addAload(0);
            int regno = 1;
            for (int i = 0; i < params.length; ++i) {
                regno += code.addLoad(regno, params[i]);
            }
            code.setMaxLocals(regno + 1);
            code.addInvokespecial(this.clazz, "<init>", desc);
            code.addReturn(null);
            minfo.setCodeAttribute(code.toCodeAttribute());
            cf.addMethod(minfo);
        } catch (CannotCompileException e) {
            throw new CompileError(e);
        } catch (NotFoundException e) {
            throw new CompileError(e);
        }
        this.accessors.put(key, consDesc);
        return consDesc;
    }

    public String getMethodAccessor(String name, String desc, String accDesc, MethodInfo orig) throws CompileError {
        String key = name + ":" + desc;
        String accName = (String)this.accessors.get(key);
        if (accName != null) {
            return accName;
        }
        ClassFile cf = this.clazz.getClassFile();
        accName = this.findAccessorName(cf);
        try {
            ConstPool cp = cf.getConstPool();
            ClassPool pool = this.clazz.getClassPool();
            MethodInfo minfo = new MethodInfo(cp, accName, accDesc);
            minfo.setAccessFlags(8);
            minfo.addAttribute(new SyntheticAttribute(cp));
            ExceptionsAttribute ea = orig.getExceptionsAttribute();
            if (ea != null) {
                minfo.addAttribute(ea.copy(cp, null));
            }
            CtClass[] params = Descriptor.getParameterTypes(accDesc, pool);
            int regno = 0;
            Bytecode code = new Bytecode(cp);
            for (int i = 0; i < params.length; ++i) {
                regno += code.addLoad(regno, params[i]);
            }
            code.setMaxLocals(regno);
            if (desc == accDesc) {
                code.addInvokestatic(this.clazz, name, desc);
            } else {
                code.addInvokevirtual(this.clazz, name, desc);
            }
            code.addReturn(Descriptor.getReturnType(desc, pool));
            minfo.setCodeAttribute(code.toCodeAttribute());
            cf.addMethod(minfo);
        } catch (CannotCompileException e) {
            throw new CompileError(e);
        } catch (NotFoundException e) {
            throw new CompileError(e);
        }
        this.accessors.put(key, accName);
        return accName;
    }

    public MethodInfo getFieldGetter(FieldInfo finfo, boolean is_static) throws CompileError {
        String fieldName = finfo.getName();
        String key = fieldName + ":getter";
        Object res = this.accessors.get(key);
        if (res != null) {
            return (MethodInfo)res;
        }
        ClassFile cf = this.clazz.getClassFile();
        String accName = this.findAccessorName(cf);
        try {
            ConstPool cp = cf.getConstPool();
            ClassPool pool = this.clazz.getClassPool();
            String fieldType = finfo.getDescriptor();
            String accDesc = is_static ? "()" + fieldType : "(" + Descriptor.of(this.clazz) + ")" + fieldType;
            MethodInfo minfo = new MethodInfo(cp, accName, accDesc);
            minfo.setAccessFlags(8);
            minfo.addAttribute(new SyntheticAttribute(cp));
            Bytecode code = new Bytecode(cp);
            if (is_static) {
                code.addGetstatic(Bytecode.THIS, fieldName, fieldType);
            } else {
                code.addAload(0);
                code.addGetfield(Bytecode.THIS, fieldName, fieldType);
                code.setMaxLocals(1);
            }
            code.addReturn(Descriptor.toCtClass(fieldType, pool));
            minfo.setCodeAttribute(code.toCodeAttribute());
            cf.addMethod(minfo);
            this.accessors.put(key, minfo);
            return minfo;
        } catch (CannotCompileException e) {
            throw new CompileError(e);
        } catch (NotFoundException e) {
            throw new CompileError(e);
        }
    }

    public MethodInfo getFieldSetter(FieldInfo finfo, boolean is_static) throws CompileError {
        String fieldName = finfo.getName();
        String key = fieldName + ":setter";
        Object res = this.accessors.get(key);
        if (res != null) {
            return (MethodInfo)res;
        }
        ClassFile cf = this.clazz.getClassFile();
        String accName = this.findAccessorName(cf);
        try {
            int reg;
            ConstPool cp = cf.getConstPool();
            ClassPool pool = this.clazz.getClassPool();
            String fieldType = finfo.getDescriptor();
            String accDesc = is_static ? "(" + fieldType + ")V" : "(" + Descriptor.of(this.clazz) + fieldType + ")V";
            MethodInfo minfo = new MethodInfo(cp, accName, accDesc);
            minfo.setAccessFlags(8);
            minfo.addAttribute(new SyntheticAttribute(cp));
            Bytecode code = new Bytecode(cp);
            if (is_static) {
                reg = code.addLoad(0, Descriptor.toCtClass(fieldType, pool));
                code.addPutstatic(Bytecode.THIS, fieldName, fieldType);
            } else {
                code.addAload(0);
                reg = code.addLoad(1, Descriptor.toCtClass(fieldType, pool)) + 1;
                code.addPutfield(Bytecode.THIS, fieldName, fieldType);
            }
            code.addReturn(null);
            code.setMaxLocals(reg);
            minfo.setCodeAttribute(code.toCodeAttribute());
            cf.addMethod(minfo);
            this.accessors.put(key, minfo);
            return minfo;
        } catch (CannotCompileException e) {
            throw new CompileError(e);
        } catch (NotFoundException e) {
            throw new CompileError(e);
        }
    }

    private String findAccessorName(ClassFile cf) {
        String accName;
        while (cf.getMethod(accName = "access$" + this.uniqueNumber++) != null) {
        }
        return accName;
    }
}

