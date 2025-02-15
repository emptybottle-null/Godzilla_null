/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package javassist;

import java.util.Map;
import javassist.CannotCompileException;
import javassist.ClassMap;
import javassist.CtClass;
import javassist.CtClassType;
import javassist.CtMember;
import javassist.CtMethod;
import javassist.CtPrimitiveType;
import javassist.Modifier;
import javassist.NotFoundException;
import javassist.bytecode.AccessFlag;
import javassist.bytecode.BadBytecode;
import javassist.bytecode.Bytecode;
import javassist.bytecode.ClassFile;
import javassist.bytecode.MethodInfo;
import javassist.bytecode.SyntheticAttribute;
import javassist.compiler.JvstCodeGen;

class CtNewWrappedMethod {
    private static final String addedWrappedMethod = "_added_m$";

    CtNewWrappedMethod() {
    }

    public static CtMethod wrapped(CtClass returnType, String mname, CtClass[] parameterTypes, CtClass[] exceptionTypes, CtMethod body, CtMethod.ConstParameter constParam, CtClass declaring) throws CannotCompileException {
        CtMethod mt = new CtMethod(returnType, mname, parameterTypes, declaring);
        mt.setModifiers(body.getModifiers());
        try {
            mt.setExceptionTypes(exceptionTypes);
        } catch (NotFoundException e) {
            throw new CannotCompileException(e);
        }
        Bytecode code = CtNewWrappedMethod.makeBody(declaring, declaring.getClassFile2(), body, parameterTypes, returnType, constParam);
        MethodInfo minfo = mt.getMethodInfo2();
        minfo.setCodeAttribute(code.toCodeAttribute());
        return mt;
    }

    static Bytecode makeBody(CtClass clazz, ClassFile classfile, CtMethod wrappedBody, CtClass[] parameters, CtClass returnType, CtMethod.ConstParameter cparam) throws CannotCompileException {
        boolean isStatic = Modifier.isStatic(wrappedBody.getModifiers());
        Bytecode code = new Bytecode(classfile.getConstPool(), 0, 0);
        int stacksize = CtNewWrappedMethod.makeBody0(clazz, classfile, wrappedBody, isStatic, parameters, returnType, cparam, code);
        code.setMaxStack(stacksize);
        code.setMaxLocals(isStatic, parameters, 0);
        return code;
    }

    protected static int makeBody0(CtClass clazz, ClassFile classfile, CtMethod wrappedBody, boolean isStatic, CtClass[] parameters, CtClass returnType, CtMethod.ConstParameter cparam, Bytecode code) throws CannotCompileException {
        String bodyname;
        String desc;
        int stacksize2;
        if (!(clazz instanceof CtClassType)) {
            throw new CannotCompileException("bad declaring class" + clazz.getName());
        }
        if (!isStatic) {
            code.addAload(0);
        }
        int stacksize = CtNewWrappedMethod.compileParameterList(code, parameters, isStatic ? 0 : 1);
        if (cparam == null) {
            stacksize2 = 0;
            desc = CtMethod.ConstParameter.defaultDescriptor();
        } else {
            stacksize2 = cparam.compile(code);
            desc = cparam.descriptor();
        }
        CtNewWrappedMethod.checkSignature(wrappedBody, desc);
        try {
            bodyname = CtNewWrappedMethod.addBodyMethod((CtClassType)clazz, classfile, wrappedBody);
        } catch (BadBytecode e) {
            throw new CannotCompileException(e);
        }
        if (isStatic) {
            code.addInvokestatic(Bytecode.THIS, bodyname, desc);
        } else {
            code.addInvokespecial(Bytecode.THIS, bodyname, desc);
        }
        CtNewWrappedMethod.compileReturn(code, returnType);
        if (stacksize < stacksize2 + 2) {
            stacksize = stacksize2 + 2;
        }
        return stacksize;
    }

    private static void checkSignature(CtMethod wrappedBody, String descriptor) throws CannotCompileException {
        if (!descriptor.equals(wrappedBody.getMethodInfo2().getDescriptor())) {
            throw new CannotCompileException("wrapped method with a bad signature: " + wrappedBody.getDeclaringClass().getName() + '.' + wrappedBody.getName());
        }
    }

    private static String addBodyMethod(CtClassType clazz, ClassFile classfile, CtMethod src) throws BadBytecode, CannotCompileException {
        Map<CtMethod, String> bodies = clazz.getHiddenMethods();
        String bodyname = bodies.get(src);
        if (bodyname == null) {
            while (classfile.getMethod(bodyname = addedWrappedMethod + clazz.getUniqueNumber()) != null) {
            }
            ClassMap map = new ClassMap();
            map.put(src.getDeclaringClass().getName(), clazz.getName());
            MethodInfo body = new MethodInfo(classfile.getConstPool(), bodyname, src.getMethodInfo2(), map);
            int acc = body.getAccessFlags();
            body.setAccessFlags(AccessFlag.setPrivate(acc));
            body.addAttribute(new SyntheticAttribute(classfile.getConstPool()));
            classfile.addMethod(body);
            bodies.put(src, bodyname);
            CtMember.Cache cache = clazz.hasMemberCache();
            if (cache != null) {
                cache.addMethod(new CtMethod(body, clazz));
            }
        }
        return bodyname;
    }

    static int compileParameterList(Bytecode code, CtClass[] params, int regno) {
        return JvstCodeGen.compileParameterList(code, params, regno);
    }

    private static void compileReturn(Bytecode code, CtClass type) {
        if (type.isPrimitive()) {
            CtPrimitiveType pt = (CtPrimitiveType)type;
            if (pt != CtClass.voidType) {
                String wrapper = pt.getWrapperName();
                code.addCheckcast(wrapper);
                code.addInvokevirtual(wrapper, pt.getGetMethodName(), pt.getGetMethodDescriptor());
            }
            code.addOpcode(pt.getReturnOp());
        } else {
            code.addCheckcast(type);
            code.addOpcode(176);
        }
    }
}

