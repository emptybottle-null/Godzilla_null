/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package javassist.compiler;

import javassist.CannotCompileException;
import javassist.CtBehavior;
import javassist.CtClass;
import javassist.CtConstructor;
import javassist.CtField;
import javassist.CtMember;
import javassist.CtMethod;
import javassist.CtPrimitiveType;
import javassist.Modifier;
import javassist.NotFoundException;
import javassist.bytecode.BadBytecode;
import javassist.bytecode.Bytecode;
import javassist.bytecode.CodeAttribute;
import javassist.bytecode.LocalVariableAttribute;
import javassist.compiler.CompileError;
import javassist.compiler.JvstCodeGen;
import javassist.compiler.JvstTypeChecker;
import javassist.compiler.Lex;
import javassist.compiler.MemberResolver;
import javassist.compiler.Parser;
import javassist.compiler.ProceedHandler;
import javassist.compiler.SymbolTable;
import javassist.compiler.ast.ASTList;
import javassist.compiler.ast.ASTree;
import javassist.compiler.ast.CallExpr;
import javassist.compiler.ast.Declarator;
import javassist.compiler.ast.Expr;
import javassist.compiler.ast.FieldDecl;
import javassist.compiler.ast.Member;
import javassist.compiler.ast.MethodDecl;
import javassist.compiler.ast.Stmnt;
import javassist.compiler.ast.Symbol;

public class Javac {
    JvstCodeGen gen;
    SymbolTable stable;
    private Bytecode bytecode;
    public static final String param0Name = "$0";
    public static final String resultVarName = "$_";
    public static final String proceedName = "$proceed";

    public Javac(CtClass thisClass) {
        this(new Bytecode(thisClass.getClassFile2().getConstPool(), 0, 0), thisClass);
    }

    public Javac(Bytecode b, CtClass thisClass) {
        this.gen = new JvstCodeGen(b, thisClass, thisClass.getClassPool());
        this.stable = new SymbolTable();
        this.bytecode = b;
    }

    public Bytecode getBytecode() {
        return this.bytecode;
    }

    public CtMember compile(String src) throws CompileError {
        Parser p = new Parser(new Lex(src));
        ASTList mem = p.parseMember1(this.stable);
        try {
            if (mem instanceof FieldDecl) {
                return this.compileField((FieldDecl)mem);
            }
            CtBehavior cb = this.compileMethod(p, (MethodDecl)mem);
            CtClass decl = cb.getDeclaringClass();
            cb.getMethodInfo2().rebuildStackMapIf6(decl.getClassPool(), decl.getClassFile2());
            return cb;
        } catch (BadBytecode bb) {
            throw new CompileError(bb.getMessage());
        } catch (CannotCompileException e) {
            throw new CompileError(e.getMessage());
        }
    }

    private CtField compileField(FieldDecl fd) throws CompileError, CannotCompileException {
        Declarator d = fd.getDeclarator();
        CtFieldWithInit f = new CtFieldWithInit(this.gen.resolver.lookupClass(d), d.getVariable().get(), this.gen.getThisClass());
        f.setModifiers(MemberResolver.getModifiers(fd.getModifiers()));
        if (fd.getInit() != null) {
            f.setInit(fd.getInit());
        }
        return f;
    }

    private CtBehavior compileMethod(Parser p, MethodDecl md) throws CompileError {
        int mod = MemberResolver.getModifiers(md.getModifiers());
        CtClass[] plist = this.gen.makeParamList(md);
        CtClass[] tlist = this.gen.makeThrowsList(md);
        this.recordParams(plist, Modifier.isStatic(mod));
        md = p.parseMethod2(this.stable, md);
        try {
            if (md.isConstructor()) {
                CtConstructor cons = new CtConstructor(plist, this.gen.getThisClass());
                cons.setModifiers(mod);
                md.accept(this.gen);
                cons.getMethodInfo().setCodeAttribute(this.bytecode.toCodeAttribute());
                cons.setExceptionTypes(tlist);
                return cons;
            }
            Declarator r = md.getReturn();
            CtClass rtype = this.gen.resolver.lookupClass(r);
            this.recordReturnType(rtype, false);
            CtMethod method = new CtMethod(rtype, r.getVariable().get(), plist, this.gen.getThisClass());
            method.setModifiers(mod);
            this.gen.setThisMethod(method);
            md.accept(this.gen);
            if (md.getBody() != null) {
                method.getMethodInfo().setCodeAttribute(this.bytecode.toCodeAttribute());
            } else {
                method.setModifiers(mod | 0x400);
            }
            method.setExceptionTypes(tlist);
            return method;
        } catch (NotFoundException e) {
            throw new CompileError(e.toString());
        }
    }

    public Bytecode compileBody(CtBehavior method, String src) throws CompileError {
        try {
            boolean isVoid;
            CtClass rtype;
            int mod = method.getModifiers();
            this.recordParams(method.getParameterTypes(), Modifier.isStatic(mod));
            if (method instanceof CtMethod) {
                this.gen.setThisMethod((CtMethod)method);
                rtype = ((CtMethod)method).getReturnType();
            } else {
                rtype = CtClass.voidType;
            }
            this.recordReturnType(rtype, false);
            boolean bl = isVoid = rtype == CtClass.voidType;
            if (src == null) {
                Javac.makeDefaultBody(this.bytecode, rtype);
            } else {
                Parser p = new Parser(new Lex(src));
                SymbolTable stb = new SymbolTable(this.stable);
                Stmnt s = p.parseStatement(stb);
                if (p.hasMore()) {
                    throw new CompileError("the method/constructor body must be surrounded by {}");
                }
                boolean callSuper = false;
                if (method instanceof CtConstructor) {
                    callSuper = !((CtConstructor)method).isClassInitializer();
                }
                this.gen.atMethodBody(s, callSuper, isVoid);
            }
            return this.bytecode;
        } catch (NotFoundException e) {
            throw new CompileError(e.toString());
        }
    }

    private static void makeDefaultBody(Bytecode b, CtClass type) {
        int value;
        int op;
        if (type instanceof CtPrimitiveType) {
            CtPrimitiveType pt = (CtPrimitiveType)type;
            op = pt.getReturnOp();
            value = op == 175 ? 14 : (op == 174 ? 11 : (op == 173 ? 9 : (op == 177 ? 0 : 3)));
        } else {
            op = 176;
            value = 1;
        }
        if (value != 0) {
            b.addOpcode(value);
        }
        b.addOpcode(op);
    }

    public boolean recordLocalVariables(CodeAttribute ca, int pc) throws CompileError {
        LocalVariableAttribute va = (LocalVariableAttribute)ca.getAttribute("LocalVariableTable");
        if (va == null) {
            return false;
        }
        int n = va.tableLength();
        for (int i = 0; i < n; ++i) {
            int start = va.startPc(i);
            int len = va.codeLength(i);
            if (start > pc || pc >= start + len) continue;
            this.gen.recordVariable(va.descriptor(i), va.variableName(i), va.index(i), this.stable);
        }
        return true;
    }

    public boolean recordParamNames(CodeAttribute ca, int numOfLocalVars) throws CompileError {
        LocalVariableAttribute va = (LocalVariableAttribute)ca.getAttribute("LocalVariableTable");
        if (va == null) {
            return false;
        }
        int n = va.tableLength();
        for (int i = 0; i < n; ++i) {
            int index = va.index(i);
            if (index >= numOfLocalVars) continue;
            this.gen.recordVariable(va.descriptor(i), va.variableName(i), index, this.stable);
        }
        return true;
    }

    public int recordParams(CtClass[] params, boolean isStatic) throws CompileError {
        return this.gen.recordParams(params, isStatic, "$", "$args", "$$", this.stable);
    }

    public int recordParams(String target, CtClass[] params, boolean use0, int varNo, boolean isStatic) throws CompileError {
        return this.gen.recordParams(params, isStatic, "$", "$args", "$$", use0, varNo, target, this.stable);
    }

    public void setMaxLocals(int max) {
        this.gen.setMaxLocals(max);
    }

    public int recordReturnType(CtClass type, boolean useResultVar) throws CompileError {
        this.gen.recordType(type);
        return this.gen.recordReturnType(type, "$r", useResultVar ? resultVarName : null, this.stable);
    }

    public void recordType(CtClass t) {
        this.gen.recordType(t);
    }

    public int recordVariable(CtClass type, String name) throws CompileError {
        return this.gen.recordVariable(type, name, this.stable);
    }

    public void recordProceed(String target, String method) throws CompileError {
        Parser p = new Parser(new Lex(target));
        final ASTree texpr = p.parseExpression(this.stable);
        final String m = method;
        ProceedHandler h = new ProceedHandler(){

            @Override
            public void doit(JvstCodeGen gen, Bytecode b, ASTList args) throws CompileError {
                ASTree expr = new Member(m);
                if (texpr != null) {
                    expr = Expr.make(46, texpr, expr);
                }
                expr = CallExpr.makeCall(expr, args);
                gen.compileExpr(expr);
                gen.addNullIfVoid();
            }

            @Override
            public void setReturnType(JvstTypeChecker check, ASTList args) throws CompileError {
                ASTree expr = new Member(m);
                if (texpr != null) {
                    expr = Expr.make(46, texpr, expr);
                }
                expr = CallExpr.makeCall(expr, args);
                ((ASTree)expr).accept(check);
                check.addNullIfVoid();
            }
        };
        this.gen.setProceedHandler(h, proceedName);
    }

    public void recordStaticProceed(String targetClass, String method) throws CompileError {
        final String c = targetClass;
        final String m = method;
        ProceedHandler h = new ProceedHandler(){

            @Override
            public void doit(JvstCodeGen gen, Bytecode b, ASTList args) throws CompileError {
                Expr expr = Expr.make(35, (ASTree)new Symbol(c), (ASTree)new Member(m));
                expr = CallExpr.makeCall(expr, args);
                gen.compileExpr(expr);
                gen.addNullIfVoid();
            }

            @Override
            public void setReturnType(JvstTypeChecker check, ASTList args) throws CompileError {
                Expr expr = Expr.make(35, (ASTree)new Symbol(c), (ASTree)new Member(m));
                expr = CallExpr.makeCall(expr, args);
                expr.accept(check);
                check.addNullIfVoid();
            }
        };
        this.gen.setProceedHandler(h, proceedName);
    }

    public void recordSpecialProceed(String target, final String classname, final String methodname, final String descriptor, final int methodIndex) throws CompileError {
        Parser p = new Parser(new Lex(target));
        final ASTree texpr = p.parseExpression(this.stable);
        ProceedHandler h = new ProceedHandler(){

            @Override
            public void doit(JvstCodeGen gen, Bytecode b, ASTList args) throws CompileError {
                gen.compileInvokeSpecial(texpr, methodIndex, descriptor, args);
            }

            @Override
            public void setReturnType(JvstTypeChecker c, ASTList args) throws CompileError {
                c.compileInvokeSpecial(texpr, classname, methodname, descriptor, args);
            }
        };
        this.gen.setProceedHandler(h, proceedName);
    }

    public void recordProceed(ProceedHandler h) {
        this.gen.setProceedHandler(h, proceedName);
    }

    public void compileStmnt(String src) throws CompileError {
        Parser p = new Parser(new Lex(src));
        SymbolTable stb = new SymbolTable(this.stable);
        while (p.hasMore()) {
            Stmnt s = p.parseStatement(stb);
            if (s == null) continue;
            s.accept(this.gen);
        }
    }

    public void compileExpr(String src) throws CompileError {
        ASTree e = Javac.parseExpr(src, this.stable);
        this.compileExpr(e);
    }

    public static ASTree parseExpr(String src, SymbolTable st) throws CompileError {
        Parser p = new Parser(new Lex(src));
        return p.parseExpression(st);
    }

    public void compileExpr(ASTree e) throws CompileError {
        if (e != null) {
            this.gen.compileExpr(e);
        }
    }

    public static class CtFieldWithInit
    extends CtField {
        private ASTree init = null;

        CtFieldWithInit(CtClass type, String name, CtClass declaring) throws CannotCompileException {
            super(type, name, declaring);
        }

        protected void setInit(ASTree i) {
            this.init = i;
        }

        @Override
        protected ASTree getInitAST() {
            return this.init;
        }
    }
}

