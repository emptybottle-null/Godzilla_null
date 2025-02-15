/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package javassist.compiler;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtField;
import javassist.Modifier;
import javassist.NotFoundException;
import javassist.bytecode.FieldInfo;
import javassist.bytecode.MethodInfo;
import javassist.bytecode.Opcode;
import javassist.compiler.CodeGen;
import javassist.compiler.CompileError;
import javassist.compiler.MemberResolver;
import javassist.compiler.NoFieldException;
import javassist.compiler.TokenId;
import javassist.compiler.ast.ASTList;
import javassist.compiler.ast.ASTree;
import javassist.compiler.ast.ArrayInit;
import javassist.compiler.ast.AssignExpr;
import javassist.compiler.ast.BinExpr;
import javassist.compiler.ast.CallExpr;
import javassist.compiler.ast.CastExpr;
import javassist.compiler.ast.CondExpr;
import javassist.compiler.ast.Declarator;
import javassist.compiler.ast.DoubleConst;
import javassist.compiler.ast.Expr;
import javassist.compiler.ast.InstanceOfExpr;
import javassist.compiler.ast.IntConst;
import javassist.compiler.ast.Keyword;
import javassist.compiler.ast.Member;
import javassist.compiler.ast.NewExpr;
import javassist.compiler.ast.StringL;
import javassist.compiler.ast.Symbol;
import javassist.compiler.ast.Variable;
import javassist.compiler.ast.Visitor;

public class TypeChecker
extends Visitor
implements Opcode,
TokenId {
    static final String javaLangObject = "java.lang.Object";
    static final String jvmJavaLangObject = "java/lang/Object";
    static final String jvmJavaLangString = "java/lang/String";
    static final String jvmJavaLangClass = "java/lang/Class";
    protected int exprType;
    protected int arrayDim;
    protected String className;
    protected MemberResolver resolver;
    protected CtClass thisClass;
    protected MethodInfo thisMethod;

    public TypeChecker(CtClass cc, ClassPool cp) {
        this.resolver = new MemberResolver(cp);
        this.thisClass = cc;
        this.thisMethod = null;
    }

    protected static String argTypesToString(int[] types, int[] dims, String[] cnames) {
        StringBuffer sbuf = new StringBuffer();
        sbuf.append('(');
        int n = types.length;
        if (n > 0) {
            int i = 0;
            while (true) {
                TypeChecker.typeToString(sbuf, types[i], dims[i], cnames[i]);
                if (++i >= n) break;
                sbuf.append(',');
            }
        }
        sbuf.append(')');
        return sbuf.toString();
    }

    protected static StringBuffer typeToString(StringBuffer sbuf, int type, int dim, String cname) {
        String s;
        if (type == 307) {
            s = MemberResolver.jvmToJavaName(cname);
        } else if (type == 412) {
            s = "Object";
        } else {
            try {
                s = MemberResolver.getTypeName(type);
            } catch (CompileError e) {
                s = "?";
            }
        }
        sbuf.append(s);
        while (dim-- > 0) {
            sbuf.append("[]");
        }
        return sbuf;
    }

    public void setThisMethod(MethodInfo m) {
        this.thisMethod = m;
    }

    protected static void fatal() throws CompileError {
        throw new CompileError("fatal");
    }

    protected String getThisName() {
        return MemberResolver.javaToJvmName(this.thisClass.getName());
    }

    protected String getSuperName() throws CompileError {
        return MemberResolver.javaToJvmName(MemberResolver.getSuperclass(this.thisClass).getName());
    }

    protected String resolveClassName(ASTList name) throws CompileError {
        return this.resolver.resolveClassName(name);
    }

    protected String resolveClassName(String jvmName) throws CompileError {
        return this.resolver.resolveJvmClassName(jvmName);
    }

    @Override
    public void atNewExpr(NewExpr expr) throws CompileError {
        if (expr.isArray()) {
            this.atNewArrayExpr(expr);
        } else {
            CtClass clazz = this.resolver.lookupClassByName(expr.getClassName());
            String cname = clazz.getName();
            ASTList args = expr.getArguments();
            this.atMethodCallCore(clazz, "<init>", args);
            this.exprType = 307;
            this.arrayDim = 0;
            this.className = MemberResolver.javaToJvmName(cname);
        }
    }

    public void atNewArrayExpr(NewExpr expr) throws CompileError {
        int type = expr.getArrayType();
        ASTList size = expr.getArraySize();
        ASTList classname = expr.getClassName();
        ArrayInit init = expr.getInitializer();
        if (init != null) {
            ((ASTree)init).accept(this);
        }
        if (size.length() > 1) {
            this.atMultiNewArray(type, classname, size);
        } else {
            ASTree sizeExpr = size.head();
            if (sizeExpr != null) {
                sizeExpr.accept(this);
            }
            this.exprType = type;
            this.arrayDim = 1;
            this.className = type == 307 ? this.resolveClassName(classname) : null;
        }
    }

    @Override
    public void atArrayInit(ArrayInit init) throws CompileError {
        for (ASTList list = init; list != null; list = list.tail()) {
            ASTree h = list.head();
            if (h == null) continue;
            h.accept(this);
        }
    }

    protected void atMultiNewArray(int type, ASTList classname, ASTList size) throws CompileError {
        ASTree s;
        int dim = size.length();
        int count = 0;
        while (size != null && (s = size.head()) != null) {
            ++count;
            s.accept(this);
            size = size.tail();
        }
        this.exprType = type;
        this.arrayDim = dim;
        this.className = type == 307 ? this.resolveClassName(classname) : null;
    }

    @Override
    public void atAssignExpr(AssignExpr expr) throws CompileError {
        int op = expr.getOperator();
        ASTree left = expr.oprand1();
        ASTree right = expr.oprand2();
        if (left instanceof Variable) {
            this.atVariableAssign(expr, op, (Variable)left, ((Variable)left).getDeclarator(), right);
        } else {
            Expr e;
            if (left instanceof Expr && (e = (Expr)left).getOperator() == 65) {
                this.atArrayAssign(expr, op, (Expr)left, right);
                return;
            }
            this.atFieldAssign(expr, op, left, right);
        }
    }

    private void atVariableAssign(Expr expr, int op, Variable var, Declarator d, ASTree right) throws CompileError {
        int varType = d.getType();
        int varArray = d.getArrayDim();
        String varClass = d.getClassName();
        if (op != 61) {
            this.atVariable(var);
        }
        right.accept(this);
        this.exprType = varType;
        this.arrayDim = varArray;
        this.className = varClass;
    }

    private void atArrayAssign(Expr expr, int op, Expr array, ASTree right) throws CompileError {
        this.atArrayRead(array.oprand1(), array.oprand2());
        int aType = this.exprType;
        int aDim = this.arrayDim;
        String cname = this.className;
        right.accept(this);
        this.exprType = aType;
        this.arrayDim = aDim;
        this.className = cname;
    }

    protected void atFieldAssign(Expr expr, int op, ASTree left, ASTree right) throws CompileError {
        CtField f = this.fieldAccess(left);
        this.atFieldRead(f);
        int fType = this.exprType;
        int fDim = this.arrayDim;
        String cname = this.className;
        right.accept(this);
        this.exprType = fType;
        this.arrayDim = fDim;
        this.className = cname;
    }

    @Override
    public void atCondExpr(CondExpr expr) throws CompileError {
        this.booleanExpr(expr.condExpr());
        expr.thenExpr().accept(this);
        int type1 = this.exprType;
        int dim1 = this.arrayDim;
        String cname1 = this.className;
        expr.elseExpr().accept(this);
        if (dim1 == 0 && dim1 == this.arrayDim) {
            if (CodeGen.rightIsStrong(type1, this.exprType)) {
                expr.setThen(new CastExpr(this.exprType, 0, expr.thenExpr()));
            } else if (CodeGen.rightIsStrong(this.exprType, type1)) {
                expr.setElse(new CastExpr(type1, 0, expr.elseExpr()));
                this.exprType = type1;
            }
        }
    }

    @Override
    public void atBinExpr(BinExpr expr) throws CompileError {
        int token = expr.getOperator();
        int k = CodeGen.lookupBinOp(token);
        if (k >= 0) {
            if (token == 43) {
                Expr e = this.atPlusExpr(expr);
                if (e != null) {
                    e = CallExpr.makeCall(Expr.make(46, (ASTree)e, (ASTree)new Member("toString")), null);
                    expr.setOprand1(e);
                    expr.setOprand2(null);
                    this.className = jvmJavaLangString;
                }
            } else {
                ASTree left = expr.oprand1();
                ASTree right = expr.oprand2();
                left.accept(this);
                int type1 = this.exprType;
                right.accept(this);
                if (!this.isConstant(expr, token, left, right)) {
                    this.computeBinExprType(expr, token, type1);
                }
            }
        } else {
            this.booleanExpr(expr);
        }
    }

    private Expr atPlusExpr(BinExpr expr) throws CompileError {
        ASTree left = expr.oprand1();
        ASTree right = expr.oprand2();
        if (right == null) {
            left.accept(this);
            return null;
        }
        if (TypeChecker.isPlusExpr(left)) {
            Expr newExpr = this.atPlusExpr((BinExpr)left);
            if (newExpr != null) {
                right.accept(this);
                this.exprType = 307;
                this.arrayDim = 0;
                this.className = "java/lang/StringBuffer";
                return TypeChecker.makeAppendCall(newExpr, right);
            }
        } else {
            left.accept(this);
        }
        int type1 = this.exprType;
        int dim1 = this.arrayDim;
        String cname = this.className;
        right.accept(this);
        if (this.isConstant(expr, 43, left, right)) {
            return null;
        }
        if (type1 == 307 && dim1 == 0 && jvmJavaLangString.equals(cname) || this.exprType == 307 && this.arrayDim == 0 && jvmJavaLangString.equals(this.className)) {
            ASTList sbufClass = ASTList.make(new Symbol("java"), new Symbol("lang"), new Symbol("StringBuffer"));
            NewExpr e = new NewExpr(sbufClass, null);
            this.exprType = 307;
            this.arrayDim = 0;
            this.className = "java/lang/StringBuffer";
            return TypeChecker.makeAppendCall(TypeChecker.makeAppendCall(e, left), right);
        }
        this.computeBinExprType(expr, 43, type1);
        return null;
    }

    private boolean isConstant(BinExpr expr, int op, ASTree left, ASTree right) throws CompileError {
        left = TypeChecker.stripPlusExpr(left);
        right = TypeChecker.stripPlusExpr(right);
        ASTree newExpr = null;
        if (left instanceof StringL && right instanceof StringL && op == 43) {
            newExpr = new StringL(((StringL)left).get() + ((StringL)right).get());
        } else if (left instanceof IntConst) {
            newExpr = ((IntConst)left).compute(op, right);
        } else if (left instanceof DoubleConst) {
            newExpr = ((DoubleConst)left).compute(op, right);
        }
        if (newExpr == null) {
            return false;
        }
        expr.setOperator(43);
        expr.setOprand1(newExpr);
        expr.setOprand2(null);
        newExpr.accept(this);
        return true;
    }

    static ASTree stripPlusExpr(ASTree expr) {
        ASTree cexpr;
        if (expr instanceof BinExpr) {
            BinExpr e = (BinExpr)expr;
            if (e.getOperator() == 43 && e.oprand2() == null) {
                return e.getLeft();
            }
        } else if (expr instanceof Expr) {
            Expr e = (Expr)expr;
            int op = e.getOperator();
            if (op == 35) {
                ASTree cexpr2 = TypeChecker.getConstantFieldValue((Member)e.oprand2());
                if (cexpr2 != null) {
                    return cexpr2;
                }
            } else if (op == 43 && e.getRight() == null) {
                return e.getLeft();
            }
        } else if (expr instanceof Member && (cexpr = TypeChecker.getConstantFieldValue((Member)expr)) != null) {
            return cexpr;
        }
        return expr;
    }

    private static ASTree getConstantFieldValue(Member mem) {
        return TypeChecker.getConstantFieldValue(mem.getField());
    }

    public static ASTree getConstantFieldValue(CtField f) {
        if (f == null) {
            return null;
        }
        Object value = f.getConstantValue();
        if (value == null) {
            return null;
        }
        if (value instanceof String) {
            return new StringL((String)value);
        }
        if (value instanceof Double || value instanceof Float) {
            int token = value instanceof Double ? 405 : 404;
            return new DoubleConst(((Number)value).doubleValue(), token);
        }
        if (value instanceof Number) {
            int token = value instanceof Long ? 403 : 402;
            return new IntConst(((Number)value).longValue(), token);
        }
        if (value instanceof Boolean) {
            return new Keyword((Boolean)value != false ? 410 : 411);
        }
        return null;
    }

    private static boolean isPlusExpr(ASTree expr) {
        if (expr instanceof BinExpr) {
            BinExpr bexpr = (BinExpr)expr;
            int token = bexpr.getOperator();
            return token == 43;
        }
        return false;
    }

    private static Expr makeAppendCall(ASTree target, ASTree arg) {
        return CallExpr.makeCall(Expr.make(46, target, (ASTree)new Member("append")), new ASTList(arg));
    }

    private void computeBinExprType(BinExpr expr, int token, int type1) throws CompileError {
        int type2 = this.exprType;
        if (token == 364 || token == 366 || token == 370) {
            this.exprType = type1;
        } else {
            this.insertCast(expr, type1, type2);
        }
        if (CodeGen.isP_INT(this.exprType) && this.exprType != 301) {
            this.exprType = 324;
        }
    }

    private void booleanExpr(ASTree expr) throws CompileError {
        int op = CodeGen.getCompOperator(expr);
        if (op == 358) {
            BinExpr bexpr = (BinExpr)expr;
            bexpr.oprand1().accept(this);
            int type1 = this.exprType;
            int dim1 = this.arrayDim;
            bexpr.oprand2().accept(this);
            if (dim1 == 0 && this.arrayDim == 0) {
                this.insertCast(bexpr, type1, this.exprType);
            }
        } else if (op == 33) {
            ((Expr)expr).oprand1().accept(this);
        } else if (op == 369 || op == 368) {
            BinExpr bexpr = (BinExpr)expr;
            bexpr.oprand1().accept(this);
            bexpr.oprand2().accept(this);
        } else {
            expr.accept(this);
        }
        this.exprType = 301;
        this.arrayDim = 0;
    }

    private void insertCast(BinExpr expr, int type1, int type2) throws CompileError {
        if (CodeGen.rightIsStrong(type1, type2)) {
            expr.setLeft(new CastExpr(type2, 0, expr.oprand1()));
        } else {
            this.exprType = type1;
        }
    }

    @Override
    public void atCastExpr(CastExpr expr) throws CompileError {
        String cname = this.resolveClassName(expr.getClassName());
        expr.getOprand().accept(this);
        this.exprType = expr.getType();
        this.arrayDim = expr.getArrayDim();
        this.className = cname;
    }

    @Override
    public void atInstanceOfExpr(InstanceOfExpr expr) throws CompileError {
        expr.getOprand().accept(this);
        this.exprType = 301;
        this.arrayDim = 0;
    }

    @Override
    public void atExpr(Expr expr) throws CompileError {
        int token = expr.getOperator();
        ASTree oprand = expr.oprand1();
        if (token == 46) {
            String member = ((Symbol)expr.oprand2()).get();
            if (member.equals("length")) {
                try {
                    this.atArrayLength(expr);
                } catch (NoFieldException nfe) {
                    this.atFieldRead(expr);
                }
            } else if (member.equals("class")) {
                this.atClassObject(expr);
            } else {
                this.atFieldRead(expr);
            }
        } else if (token == 35) {
            String member = ((Symbol)expr.oprand2()).get();
            if (member.equals("class")) {
                this.atClassObject(expr);
            } else {
                this.atFieldRead(expr);
            }
        } else if (token == 65) {
            this.atArrayRead(oprand, expr.oprand2());
        } else if (token == 362 || token == 363) {
            this.atPlusPlus(token, oprand, expr);
        } else if (token == 33) {
            this.booleanExpr(expr);
        } else if (token == 67) {
            TypeChecker.fatal();
        } else {
            oprand.accept(this);
            if (!this.isConstant(expr, token, oprand) && (token == 45 || token == 126) && CodeGen.isP_INT(this.exprType)) {
                this.exprType = 324;
            }
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private boolean isConstant(Expr expr, int op, ASTree oprand) {
        if ((oprand = TypeChecker.stripPlusExpr(oprand)) instanceof IntConst) {
            IntConst c = (IntConst)oprand;
            long v = c.get();
            if (op == 45) {
                v = -v;
            } else {
                if (op != 126) return false;
                v ^= 0xFFFFFFFFFFFFFFFFL;
            }
            c.set(v);
        } else {
            if (!(oprand instanceof DoubleConst)) return false;
            DoubleConst c = (DoubleConst)oprand;
            if (op != 45) return false;
            c.set(-c.get());
        }
        expr.setOperator(43);
        return true;
    }

    @Override
    public void atCallExpr(CallExpr expr) throws CompileError {
        String mname = null;
        CtClass targetClass = null;
        ASTree method = expr.oprand1();
        ASTList args = (ASTList)expr.oprand2();
        if (method instanceof Member) {
            mname = ((Member)method).get();
            targetClass = this.thisClass;
        } else if (method instanceof Keyword) {
            mname = "<init>";
            targetClass = ((Keyword)method).get() == 336 ? MemberResolver.getSuperclass(this.thisClass) : this.thisClass;
        } else if (method instanceof Expr) {
            Expr e = (Expr)method;
            mname = ((Symbol)e.oprand2()).get();
            int op = e.getOperator();
            if (op == 35) {
                targetClass = this.resolver.lookupClass(((Symbol)e.oprand1()).get(), false);
            } else if (op == 46) {
                ASTree target = e.oprand1();
                String classFollowedByDotSuper = TypeChecker.isDotSuper(target);
                if (classFollowedByDotSuper != null) {
                    targetClass = MemberResolver.getSuperInterface(this.thisClass, classFollowedByDotSuper);
                } else {
                    try {
                        target.accept(this);
                    } catch (NoFieldException nfe) {
                        if (nfe.getExpr() != target) {
                            throw nfe;
                        }
                        this.exprType = 307;
                        this.arrayDim = 0;
                        this.className = nfe.getField();
                        e.setOperator(35);
                        e.setOprand1(new Symbol(MemberResolver.jvmToJavaName(this.className)));
                    }
                    if (this.arrayDim > 0) {
                        targetClass = this.resolver.lookupClass(javaLangObject, true);
                    } else if (this.exprType == 307) {
                        targetClass = this.resolver.lookupClassByJvmName(this.className);
                    } else {
                        TypeChecker.badMethod();
                    }
                }
            } else {
                TypeChecker.badMethod();
            }
        } else {
            TypeChecker.fatal();
        }
        MemberResolver.Method minfo = this.atMethodCallCore(targetClass, mname, args);
        expr.setMethod(minfo);
    }

    private static void badMethod() throws CompileError {
        throw new CompileError("bad method");
    }

    static String isDotSuper(ASTree target) {
        ASTree right;
        Expr e;
        if (target instanceof Expr && (e = (Expr)target).getOperator() == 46 && (right = e.oprand2()) instanceof Keyword && ((Keyword)right).get() == 336) {
            return ((Symbol)e.oprand1()).get();
        }
        return null;
    }

    public MemberResolver.Method atMethodCallCore(CtClass targetClass, String mname, ASTList args) throws CompileError {
        int nargs = this.getMethodArgsLength(args);
        int[] types = new int[nargs];
        int[] dims = new int[nargs];
        String[] cnames = new String[nargs];
        this.atMethodArgs(args, types, dims, cnames);
        MemberResolver.Method found = this.resolver.lookupMethod(targetClass, this.thisClass, this.thisMethod, mname, types, dims, cnames);
        if (found == null) {
            String clazz = targetClass.getName();
            String signature = TypeChecker.argTypesToString(types, dims, cnames);
            String msg = mname.equals("<init>") ? "cannot find constructor " + clazz + signature : mname + signature + " not found in " + clazz;
            throw new CompileError(msg);
        }
        String desc = found.info.getDescriptor();
        this.setReturnType(desc);
        return found;
    }

    public int getMethodArgsLength(ASTList args) {
        return ASTList.length(args);
    }

    public void atMethodArgs(ASTList args, int[] types, int[] dims, String[] cnames) throws CompileError {
        int i = 0;
        while (args != null) {
            ASTree a = args.head();
            a.accept(this);
            types[i] = this.exprType;
            dims[i] = this.arrayDim;
            cnames[i] = this.className;
            ++i;
            args = args.tail();
        }
    }

    void setReturnType(String desc) throws CompileError {
        int i = desc.indexOf(41);
        if (i < 0) {
            TypeChecker.badMethod();
        }
        char c = desc.charAt(++i);
        int dim = 0;
        while (c == '[') {
            ++dim;
            c = desc.charAt(++i);
        }
        this.arrayDim = dim;
        if (c == 'L') {
            int j = desc.indexOf(59, i + 1);
            if (j < 0) {
                TypeChecker.badMethod();
            }
            this.exprType = 307;
            this.className = desc.substring(i + 1, j);
        } else {
            this.exprType = MemberResolver.descToType(c);
            this.className = null;
        }
    }

    private void atFieldRead(ASTree expr) throws CompileError {
        this.atFieldRead(this.fieldAccess(expr));
    }

    private void atFieldRead(CtField f) throws CompileError {
        FieldInfo finfo = f.getFieldInfo2();
        String type = finfo.getDescriptor();
        int i = 0;
        int dim = 0;
        char c = type.charAt(i);
        while (c == '[') {
            ++dim;
            c = type.charAt(++i);
        }
        this.arrayDim = dim;
        this.exprType = MemberResolver.descToType(c);
        this.className = c == 'L' ? type.substring(i + 1, type.indexOf(59, i + 1)) : null;
    }

    protected CtField fieldAccess(ASTree expr) throws CompileError {
        if (expr instanceof Member) {
            Member mem = (Member)expr;
            String name = mem.get();
            try {
                CtField f = this.thisClass.getField(name);
                if (Modifier.isStatic(f.getModifiers())) {
                    mem.setField(f);
                }
                return f;
            } catch (NotFoundException e) {
                throw new NoFieldException(name, expr);
            }
        }
        if (expr instanceof Expr) {
            Expr e = (Expr)expr;
            int op = e.getOperator();
            if (op == 35) {
                Member mem = (Member)e.oprand2();
                CtField f = this.resolver.lookupField(((Symbol)e.oprand1()).get(), mem);
                mem.setField(f);
                return f;
            }
            if (op == 46) {
                try {
                    e.oprand1().accept(this);
                } catch (NoFieldException nfe) {
                    if (nfe.getExpr() != e.oprand1()) {
                        throw nfe;
                    }
                    return this.fieldAccess2(e, nfe.getField());
                }
                CompileError err = null;
                try {
                    if (this.exprType == 307 && this.arrayDim == 0) {
                        return this.resolver.lookupFieldByJvmName(this.className, (Symbol)e.oprand2());
                    }
                } catch (CompileError ce) {
                    err = ce;
                }
                ASTree oprnd1 = e.oprand1();
                if (oprnd1 instanceof Symbol) {
                    return this.fieldAccess2(e, ((Symbol)oprnd1).get());
                }
                if (err != null) {
                    throw err;
                }
            }
        }
        throw new CompileError("bad field access");
    }

    private CtField fieldAccess2(Expr e, String jvmClassName) throws CompileError {
        Member fname = (Member)e.oprand2();
        CtField f = this.resolver.lookupFieldByJvmName2(jvmClassName, fname, e);
        e.setOperator(35);
        e.setOprand1(new Symbol(MemberResolver.jvmToJavaName(jvmClassName)));
        fname.setField(f);
        return f;
    }

    public void atClassObject(Expr expr) throws CompileError {
        this.exprType = 307;
        this.arrayDim = 0;
        this.className = jvmJavaLangClass;
    }

    public void atArrayLength(Expr expr) throws CompileError {
        expr.oprand1().accept(this);
        if (this.arrayDim == 0) {
            throw new NoFieldException("length", expr);
        }
        this.exprType = 324;
        this.arrayDim = 0;
    }

    public void atArrayRead(ASTree array, ASTree index) throws CompileError {
        array.accept(this);
        int type = this.exprType;
        int dim = this.arrayDim;
        String cname = this.className;
        index.accept(this);
        this.exprType = type;
        this.arrayDim = dim - 1;
        this.className = cname;
    }

    private void atPlusPlus(int token, ASTree oprand, Expr expr) throws CompileError {
        boolean isPost;
        boolean bl = isPost = oprand == null;
        if (isPost) {
            oprand = expr.oprand2();
        }
        if (oprand instanceof Variable) {
            Declarator d = ((Variable)oprand).getDeclarator();
            this.exprType = d.getType();
            this.arrayDim = d.getArrayDim();
        } else {
            Expr e;
            if (oprand instanceof Expr && (e = (Expr)oprand).getOperator() == 65) {
                this.atArrayRead(e.oprand1(), e.oprand2());
                int t = this.exprType;
                if (t == 324 || t == 303 || t == 306 || t == 334) {
                    this.exprType = 324;
                }
                return;
            }
            this.atFieldPlusPlus(oprand);
        }
    }

    protected void atFieldPlusPlus(ASTree oprand) throws CompileError {
        CtField f = this.fieldAccess(oprand);
        this.atFieldRead(f);
        int t = this.exprType;
        if (t == 324 || t == 303 || t == 306 || t == 334) {
            this.exprType = 324;
        }
    }

    @Override
    public void atMember(Member mem) throws CompileError {
        this.atFieldRead(mem);
    }

    @Override
    public void atVariable(Variable v) throws CompileError {
        Declarator d = v.getDeclarator();
        this.exprType = d.getType();
        this.arrayDim = d.getArrayDim();
        this.className = d.getClassName();
    }

    @Override
    public void atKeyword(Keyword k) throws CompileError {
        this.arrayDim = 0;
        int token = k.get();
        switch (token) {
            case 410: 
            case 411: {
                this.exprType = 301;
                break;
            }
            case 412: {
                this.exprType = 412;
                break;
            }
            case 336: 
            case 339: {
                this.exprType = 307;
                if (token == 339) {
                    this.className = this.getThisName();
                    break;
                }
                this.className = this.getSuperName();
                break;
            }
            default: {
                TypeChecker.fatal();
            }
        }
    }

    @Override
    public void atStringL(StringL s) throws CompileError {
        this.exprType = 307;
        this.arrayDim = 0;
        this.className = jvmJavaLangString;
    }

    @Override
    public void atIntConst(IntConst i) throws CompileError {
        this.arrayDim = 0;
        int type = i.getType();
        this.exprType = type == 402 || type == 401 ? (type == 402 ? 324 : 306) : 326;
    }

    @Override
    public void atDoubleConst(DoubleConst d) throws CompileError {
        this.arrayDim = 0;
        this.exprType = d.getType() == 405 ? 312 : 317;
    }
}

