/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package javassist.compiler;

import javassist.compiler.CodeGen;
import javassist.compiler.CompileError;
import javassist.compiler.Lex;
import javassist.compiler.SymbolTable;
import javassist.compiler.SyntaxError;
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
import javassist.compiler.ast.FieldDecl;
import javassist.compiler.ast.InstanceOfExpr;
import javassist.compiler.ast.IntConst;
import javassist.compiler.ast.Keyword;
import javassist.compiler.ast.Member;
import javassist.compiler.ast.MethodDecl;
import javassist.compiler.ast.NewExpr;
import javassist.compiler.ast.Pair;
import javassist.compiler.ast.Stmnt;
import javassist.compiler.ast.StringL;
import javassist.compiler.ast.Symbol;
import javassist.compiler.ast.Variable;

public final class Parser
implements TokenId {
    private Lex lex;
    private static final int[] binaryOpPrecedence = new int[]{0, 0, 0, 0, 1, 6, 0, 0, 0, 1, 2, 0, 2, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0, 4, 0};

    public Parser(Lex lex) {
        this.lex = lex;
    }

    public boolean hasMore() {
        return this.lex.lookAhead() >= 0;
    }

    public ASTList parseMember(SymbolTable tbl) throws CompileError {
        ASTList mem = this.parseMember1(tbl);
        if (mem instanceof MethodDecl) {
            return this.parseMethod2(tbl, (MethodDecl)mem);
        }
        return mem;
    }

    public ASTList parseMember1(SymbolTable tbl) throws CompileError {
        Declarator d;
        ASTList mods = this.parseMemberMods();
        boolean isConstructor = false;
        if (this.lex.lookAhead() == 400 && this.lex.lookAhead(1) == 40) {
            d = new Declarator(344, 0);
            isConstructor = true;
        } else {
            d = this.parseFormalType(tbl);
        }
        if (this.lex.get() != 400) {
            throw new SyntaxError(this.lex);
        }
        String name = isConstructor ? "<init>" : this.lex.getString();
        d.setVariable(new Symbol(name));
        if (isConstructor || this.lex.lookAhead() == 40) {
            return this.parseMethod1(tbl, isConstructor, mods, d);
        }
        return this.parseField(tbl, mods, d);
    }

    private FieldDecl parseField(SymbolTable tbl, ASTList mods, Declarator d) throws CompileError {
        int c;
        ASTree expr = null;
        if (this.lex.lookAhead() == 61) {
            this.lex.get();
            expr = this.parseExpression(tbl);
        }
        if ((c = this.lex.get()) == 59) {
            return new FieldDecl(mods, new ASTList(d, new ASTList(expr)));
        }
        if (c == 44) {
            throw new CompileError("only one field can be declared in one declaration", this.lex);
        }
        throw new SyntaxError(this.lex);
    }

    private MethodDecl parseMethod1(SymbolTable tbl, boolean isConstructor, ASTList mods, Declarator d) throws CompileError {
        if (this.lex.get() != 40) {
            throw new SyntaxError(this.lex);
        }
        ASTList parms = null;
        if (this.lex.lookAhead() != 41) {
            while (true) {
                parms = ASTList.append(parms, this.parseFormalParam(tbl));
                int t = this.lex.lookAhead();
                if (t == 44) {
                    this.lex.get();
                    continue;
                }
                if (t == 41) break;
            }
        }
        this.lex.get();
        d.addArrayDim(this.parseArrayDimension());
        if (isConstructor && d.getArrayDim() > 0) {
            throw new SyntaxError(this.lex);
        }
        ASTList throwsList = null;
        if (this.lex.lookAhead() == 341) {
            this.lex.get();
            while (true) {
                throwsList = ASTList.append(throwsList, this.parseClassType(tbl));
                if (this.lex.lookAhead() != 44) break;
                this.lex.get();
            }
        }
        return new MethodDecl(mods, new ASTList(d, ASTList.make(parms, throwsList, null)));
    }

    public MethodDecl parseMethod2(SymbolTable tbl, MethodDecl md) throws CompileError {
        Stmnt body = null;
        if (this.lex.lookAhead() == 59) {
            this.lex.get();
        } else {
            body = this.parseBlock(tbl);
            if (body == null) {
                body = new Stmnt(66);
            }
        }
        md.sublist(4).setHead(body);
        return md;
    }

    private ASTList parseMemberMods() {
        int t;
        ASTList list = null;
        while ((t = this.lex.lookAhead()) == 300 || t == 315 || t == 332 || t == 331 || t == 330 || t == 338 || t == 335 || t == 345 || t == 342 || t == 347) {
            list = new ASTList(new Keyword(this.lex.get()), list);
        }
        return list;
    }

    private Declarator parseFormalType(SymbolTable tbl) throws CompileError {
        int t = this.lex.lookAhead();
        if (Parser.isBuiltinType(t) || t == 344) {
            this.lex.get();
            int dim = this.parseArrayDimension();
            return new Declarator(t, dim);
        }
        ASTList name = this.parseClassType(tbl);
        int dim = this.parseArrayDimension();
        return new Declarator(name, dim);
    }

    private static boolean isBuiltinType(int t) {
        return t == 301 || t == 303 || t == 306 || t == 334 || t == 324 || t == 326 || t == 317 || t == 312;
    }

    private Declarator parseFormalParam(SymbolTable tbl) throws CompileError {
        Declarator d = this.parseFormalType(tbl);
        if (this.lex.get() != 400) {
            throw new SyntaxError(this.lex);
        }
        String name = this.lex.getString();
        d.setVariable(new Symbol(name));
        d.addArrayDim(this.parseArrayDimension());
        tbl.append(name, d);
        return d;
    }

    public Stmnt parseStatement(SymbolTable tbl) throws CompileError {
        int t = this.lex.lookAhead();
        if (t == 123) {
            return this.parseBlock(tbl);
        }
        if (t == 59) {
            this.lex.get();
            return new Stmnt(66);
        }
        if (t == 400 && this.lex.lookAhead(1) == 58) {
            this.lex.get();
            String label = this.lex.getString();
            this.lex.get();
            return Stmnt.make(76, (ASTree)new Symbol(label), (ASTree)this.parseStatement(tbl));
        }
        if (t == 320) {
            return this.parseIf(tbl);
        }
        if (t == 346) {
            return this.parseWhile(tbl);
        }
        if (t == 311) {
            return this.parseDo(tbl);
        }
        if (t == 318) {
            return this.parseFor(tbl);
        }
        if (t == 343) {
            return this.parseTry(tbl);
        }
        if (t == 337) {
            return this.parseSwitch(tbl);
        }
        if (t == 338) {
            return this.parseSynchronized(tbl);
        }
        if (t == 333) {
            return this.parseReturn(tbl);
        }
        if (t == 340) {
            return this.parseThrow(tbl);
        }
        if (t == 302) {
            return this.parseBreak(tbl);
        }
        if (t == 309) {
            return this.parseContinue(tbl);
        }
        return this.parseDeclarationOrExpression(tbl, false);
    }

    private Stmnt parseBlock(SymbolTable tbl) throws CompileError {
        if (this.lex.get() != 123) {
            throw new SyntaxError(this.lex);
        }
        Stmnt body = null;
        SymbolTable tbl2 = new SymbolTable(tbl);
        while (this.lex.lookAhead() != 125) {
            Stmnt s = this.parseStatement(tbl2);
            if (s == null) continue;
            body = (Stmnt)ASTList.concat(body, new Stmnt(66, (ASTree)s));
        }
        this.lex.get();
        if (body == null) {
            return new Stmnt(66);
        }
        return body;
    }

    private Stmnt parseIf(SymbolTable tbl) throws CompileError {
        Stmnt elsep;
        int t = this.lex.get();
        ASTree expr = this.parseParExpression(tbl);
        Stmnt thenp = this.parseStatement(tbl);
        if (this.lex.lookAhead() == 313) {
            this.lex.get();
            elsep = this.parseStatement(tbl);
        } else {
            elsep = null;
        }
        return new Stmnt(t, expr, new ASTList(thenp, new ASTList(elsep)));
    }

    private Stmnt parseWhile(SymbolTable tbl) throws CompileError {
        int t = this.lex.get();
        ASTree expr = this.parseParExpression(tbl);
        Stmnt body = this.parseStatement(tbl);
        return new Stmnt(t, expr, body);
    }

    private Stmnt parseDo(SymbolTable tbl) throws CompileError {
        int t = this.lex.get();
        Stmnt body = this.parseStatement(tbl);
        if (this.lex.get() != 346 || this.lex.get() != 40) {
            throw new SyntaxError(this.lex);
        }
        ASTree expr = this.parseExpression(tbl);
        if (this.lex.get() != 41 || this.lex.get() != 59) {
            throw new SyntaxError(this.lex);
        }
        return new Stmnt(t, expr, body);
    }

    private Stmnt parseFor(SymbolTable tbl) throws CompileError {
        Stmnt expr1;
        int t = this.lex.get();
        SymbolTable tbl2 = new SymbolTable(tbl);
        if (this.lex.get() != 40) {
            throw new SyntaxError(this.lex);
        }
        if (this.lex.lookAhead() == 59) {
            this.lex.get();
            expr1 = null;
        } else {
            expr1 = this.parseDeclarationOrExpression(tbl2, true);
        }
        ASTree expr2 = this.lex.lookAhead() == 59 ? null : this.parseExpression(tbl2);
        if (this.lex.get() != 59) {
            throw new CompileError("; is missing", this.lex);
        }
        Stmnt expr3 = this.lex.lookAhead() == 41 ? null : this.parseExprList(tbl2);
        if (this.lex.get() != 41) {
            throw new CompileError(") is missing", this.lex);
        }
        Stmnt body = this.parseStatement(tbl2);
        return new Stmnt(t, expr1, new ASTList(expr2, new ASTList(expr3, body)));
    }

    private Stmnt parseSwitch(SymbolTable tbl) throws CompileError {
        int t = this.lex.get();
        ASTree expr = this.parseParExpression(tbl);
        Stmnt body = this.parseSwitchBlock(tbl);
        return new Stmnt(t, expr, body);
    }

    private Stmnt parseSwitchBlock(SymbolTable tbl) throws CompileError {
        if (this.lex.get() != 123) {
            throw new SyntaxError(this.lex);
        }
        SymbolTable tbl2 = new SymbolTable(tbl);
        Stmnt s = this.parseStmntOrCase(tbl2);
        if (s == null) {
            throw new CompileError("empty switch block", this.lex);
        }
        int op = s.getOperator();
        if (op != 304 && op != 310) {
            throw new CompileError("no case or default in a switch block", this.lex);
        }
        Stmnt body = new Stmnt(66, (ASTree)s);
        while (this.lex.lookAhead() != 125) {
            Stmnt s2 = this.parseStmntOrCase(tbl2);
            if (s2 == null) continue;
            int op2 = s2.getOperator();
            if (op2 == 304 || op2 == 310) {
                body = (Stmnt)ASTList.concat(body, new Stmnt(66, (ASTree)s2));
                s = s2;
                continue;
            }
            s = (Stmnt)ASTList.concat(s, new Stmnt(66, (ASTree)s2));
        }
        this.lex.get();
        return body;
    }

    private Stmnt parseStmntOrCase(SymbolTable tbl) throws CompileError {
        int t = this.lex.lookAhead();
        if (t != 304 && t != 310) {
            return this.parseStatement(tbl);
        }
        this.lex.get();
        Stmnt s = t == 304 ? new Stmnt(t, this.parseExpression(tbl)) : new Stmnt(310);
        if (this.lex.get() != 58) {
            throw new CompileError(": is missing", this.lex);
        }
        return s;
    }

    private Stmnt parseSynchronized(SymbolTable tbl) throws CompileError {
        int t = this.lex.get();
        if (this.lex.get() != 40) {
            throw new SyntaxError(this.lex);
        }
        ASTree expr = this.parseExpression(tbl);
        if (this.lex.get() != 41) {
            throw new SyntaxError(this.lex);
        }
        Stmnt body = this.parseBlock(tbl);
        return new Stmnt(t, expr, body);
    }

    private Stmnt parseTry(SymbolTable tbl) throws CompileError {
        this.lex.get();
        Stmnt block = this.parseBlock(tbl);
        ASTList catchList = null;
        while (this.lex.lookAhead() == 305) {
            this.lex.get();
            if (this.lex.get() != 40) {
                throw new SyntaxError(this.lex);
            }
            SymbolTable tbl2 = new SymbolTable(tbl);
            Declarator d = this.parseFormalParam(tbl2);
            if (d.getArrayDim() > 0 || d.getType() != 307) {
                throw new SyntaxError(this.lex);
            }
            if (this.lex.get() != 41) {
                throw new SyntaxError(this.lex);
            }
            Stmnt b = this.parseBlock(tbl2);
            catchList = ASTList.append(catchList, new Pair(d, b));
        }
        Stmnt finallyBlock = null;
        if (this.lex.lookAhead() == 316) {
            this.lex.get();
            finallyBlock = this.parseBlock(tbl);
        }
        return Stmnt.make(343, block, catchList, finallyBlock);
    }

    private Stmnt parseReturn(SymbolTable tbl) throws CompileError {
        int t = this.lex.get();
        Stmnt s = new Stmnt(t);
        if (this.lex.lookAhead() != 59) {
            s.setLeft(this.parseExpression(tbl));
        }
        if (this.lex.get() != 59) {
            throw new CompileError("; is missing", this.lex);
        }
        return s;
    }

    private Stmnt parseThrow(SymbolTable tbl) throws CompileError {
        int t = this.lex.get();
        ASTree expr = this.parseExpression(tbl);
        if (this.lex.get() != 59) {
            throw new CompileError("; is missing", this.lex);
        }
        return new Stmnt(t, expr);
    }

    private Stmnt parseBreak(SymbolTable tbl) throws CompileError {
        return this.parseContinue(tbl);
    }

    private Stmnt parseContinue(SymbolTable tbl) throws CompileError {
        int t = this.lex.get();
        Stmnt s = new Stmnt(t);
        int t2 = this.lex.get();
        if (t2 == 400) {
            s.setLeft(new Symbol(this.lex.getString()));
            t2 = this.lex.get();
        }
        if (t2 != 59) {
            throw new CompileError("; is missing", this.lex);
        }
        return s;
    }

    private Stmnt parseDeclarationOrExpression(SymbolTable tbl, boolean exprList) throws CompileError {
        int i;
        int t = this.lex.lookAhead();
        while (t == 315) {
            this.lex.get();
            t = this.lex.lookAhead();
        }
        if (Parser.isBuiltinType(t)) {
            t = this.lex.get();
            int dim = this.parseArrayDimension();
            return this.parseDeclarators(tbl, new Declarator(t, dim));
        }
        if (t == 400 && (i = this.nextIsClassType(0)) >= 0 && this.lex.lookAhead(i) == 400) {
            ASTList name = this.parseClassType(tbl);
            int dim = this.parseArrayDimension();
            return this.parseDeclarators(tbl, new Declarator(name, dim));
        }
        Stmnt expr = exprList ? this.parseExprList(tbl) : new Stmnt(69, this.parseExpression(tbl));
        if (this.lex.get() != 59) {
            throw new CompileError("; is missing", this.lex);
        }
        return expr;
    }

    private Stmnt parseExprList(SymbolTable tbl) throws CompileError {
        Stmnt expr = null;
        while (true) {
            Stmnt e = new Stmnt(69, this.parseExpression(tbl));
            expr = (Stmnt)ASTList.concat(expr, new Stmnt(66, (ASTree)e));
            if (this.lex.lookAhead() != 44) break;
            this.lex.get();
        }
        return expr;
    }

    private Stmnt parseDeclarators(SymbolTable tbl, Declarator d) throws CompileError {
        int t;
        Stmnt decl = null;
        do {
            decl = (Stmnt)ASTList.concat(decl, new Stmnt(68, (ASTree)this.parseDeclarator(tbl, d)));
            t = this.lex.get();
            if (t != 59) continue;
            return decl;
        } while (t == 44);
        throw new CompileError("; is missing", this.lex);
    }

    private Declarator parseDeclarator(SymbolTable tbl, Declarator d) throws CompileError {
        if (this.lex.get() != 400 || d.getType() == 344) {
            throw new SyntaxError(this.lex);
        }
        String name = this.lex.getString();
        Symbol symbol = new Symbol(name);
        int dim = this.parseArrayDimension();
        ASTree init = null;
        if (this.lex.lookAhead() == 61) {
            this.lex.get();
            init = this.parseInitializer(tbl);
        }
        Declarator decl = d.make(symbol, dim, init);
        tbl.append(name, decl);
        return decl;
    }

    private ASTree parseInitializer(SymbolTable tbl) throws CompileError {
        if (this.lex.lookAhead() == 123) {
            return this.parseArrayInitializer(tbl);
        }
        return this.parseExpression(tbl);
    }

    private ArrayInit parseArrayInitializer(SymbolTable tbl) throws CompileError {
        this.lex.get();
        if (this.lex.lookAhead() == 125) {
            this.lex.get();
            return new ArrayInit(null);
        }
        ASTree expr = this.parseExpression(tbl);
        ArrayInit init = new ArrayInit(expr);
        while (this.lex.lookAhead() == 44) {
            this.lex.get();
            expr = this.parseExpression(tbl);
            ASTList.append(init, expr);
        }
        if (this.lex.get() != 125) {
            throw new SyntaxError(this.lex);
        }
        return init;
    }

    private ASTree parseParExpression(SymbolTable tbl) throws CompileError {
        if (this.lex.get() != 40) {
            throw new SyntaxError(this.lex);
        }
        ASTree expr = this.parseExpression(tbl);
        if (this.lex.get() != 41) {
            throw new SyntaxError(this.lex);
        }
        return expr;
    }

    public ASTree parseExpression(SymbolTable tbl) throws CompileError {
        ASTree left = this.parseConditionalExpr(tbl);
        if (!Parser.isAssignOp(this.lex.lookAhead())) {
            return left;
        }
        int t = this.lex.get();
        ASTree right = this.parseExpression(tbl);
        return AssignExpr.makeAssign(t, left, right);
    }

    private static boolean isAssignOp(int t) {
        return t == 61 || t == 351 || t == 352 || t == 353 || t == 354 || t == 355 || t == 356 || t == 360 || t == 361 || t == 365 || t == 367 || t == 371;
    }

    private ASTree parseConditionalExpr(SymbolTable tbl) throws CompileError {
        ASTree cond = this.parseBinaryExpr(tbl);
        if (this.lex.lookAhead() == 63) {
            this.lex.get();
            ASTree thenExpr = this.parseExpression(tbl);
            if (this.lex.get() != 58) {
                throw new CompileError(": is missing", this.lex);
            }
            ASTree elseExpr = this.parseExpression(tbl);
            return new CondExpr(cond, thenExpr, elseExpr);
        }
        return cond;
    }

    private ASTree parseBinaryExpr(SymbolTable tbl) throws CompileError {
        ASTree expr = this.parseUnaryExpr(tbl);
        int t;
        int p;
        while ((p = this.getOpPrecedence(t = this.lex.lookAhead())) != 0) {
            expr = this.binaryExpr2(tbl, expr, p);
        }
        return expr;
    }

    private ASTree parseInstanceOf(SymbolTable tbl, ASTree expr) throws CompileError {
        int t = this.lex.lookAhead();
        if (Parser.isBuiltinType(t)) {
            this.lex.get();
            int dim = this.parseArrayDimension();
            return new InstanceOfExpr(t, dim, expr);
        }
        ASTList name = this.parseClassType(tbl);
        int dim = this.parseArrayDimension();
        return new InstanceOfExpr(name, dim, expr);
    }

    private ASTree binaryExpr2(SymbolTable tbl, ASTree expr, int prec) throws CompileError {
        int t2;
        int p2;
        int t = this.lex.get();
        if (t == 323) {
            return this.parseInstanceOf(tbl, expr);
        }
        ASTree expr2 = this.parseUnaryExpr(tbl);
        while ((p2 = this.getOpPrecedence(t2 = this.lex.lookAhead())) != 0 && prec > p2) {
            expr2 = this.binaryExpr2(tbl, expr2, p2);
        }
        return BinExpr.makeBin(t, expr, expr2);
    }

    private int getOpPrecedence(int c) {
        if (33 <= c && c <= 63) {
            return binaryOpPrecedence[c - 33];
        }
        if (c == 94) {
            return 7;
        }
        if (c == 124) {
            return 8;
        }
        if (c == 369) {
            return 9;
        }
        if (c == 368) {
            return 10;
        }
        if (c == 358 || c == 350) {
            return 5;
        }
        if (c == 357 || c == 359 || c == 323) {
            return 4;
        }
        if (c == 364 || c == 366 || c == 370) {
            return 3;
        }
        return 0;
    }

    private ASTree parseUnaryExpr(SymbolTable tbl) throws CompileError {
        switch (this.lex.lookAhead()) {
            case 33: 
            case 43: 
            case 45: 
            case 126: 
            case 362: 
            case 363: {
                int t = this.lex.get();
                if (t == 45) {
                    int t2 = this.lex.lookAhead();
                    switch (t2) {
                        case 401: 
                        case 402: 
                        case 403: {
                            this.lex.get();
                            return new IntConst(-this.lex.getLong(), t2);
                        }
                        case 404: 
                        case 405: {
                            this.lex.get();
                            return new DoubleConst(-this.lex.getDouble(), t2);
                        }
                    }
                }
                return Expr.make(t, this.parseUnaryExpr(tbl));
            }
            case 40: {
                return this.parseCast(tbl);
            }
        }
        return this.parsePostfix(tbl);
    }

    private ASTree parseCast(SymbolTable tbl) throws CompileError {
        int t = this.lex.lookAhead(1);
        if (Parser.isBuiltinType(t) && this.nextIsBuiltinCast()) {
            this.lex.get();
            this.lex.get();
            int dim = this.parseArrayDimension();
            if (this.lex.get() != 41) {
                throw new CompileError(") is missing", this.lex);
            }
            return new CastExpr(t, dim, this.parseUnaryExpr(tbl));
        }
        if (t == 400 && this.nextIsClassCast()) {
            this.lex.get();
            ASTList name = this.parseClassType(tbl);
            int dim = this.parseArrayDimension();
            if (this.lex.get() != 41) {
                throw new CompileError(") is missing", this.lex);
            }
            return new CastExpr(name, dim, this.parseUnaryExpr(tbl));
        }
        return this.parsePostfix(tbl);
    }

    private boolean nextIsBuiltinCast() {
        int t;
        int i = 2;
        while ((t = this.lex.lookAhead(i++)) == 91) {
            if (this.lex.lookAhead(i++) == 93) continue;
            return false;
        }
        return this.lex.lookAhead(i - 1) == 41;
    }

    private boolean nextIsClassCast() {
        int i = this.nextIsClassType(1);
        if (i < 0) {
            return false;
        }
        int t = this.lex.lookAhead(i);
        if (t != 41) {
            return false;
        }
        t = this.lex.lookAhead(i + 1);
        return t == 40 || t == 412 || t == 406 || t == 400 || t == 339 || t == 336 || t == 328 || t == 410 || t == 411 || t == 403 || t == 402 || t == 401 || t == 405 || t == 404;
    }

    private int nextIsClassType(int i) {
        int t;
        while (this.lex.lookAhead(++i) == 46) {
            if (this.lex.lookAhead(++i) == 400) continue;
            return -1;
        }
        while ((t = this.lex.lookAhead(i++)) == 91) {
            if (this.lex.lookAhead(i++) == 93) continue;
            return -1;
        }
        return i - 1;
    }

    private int parseArrayDimension() throws CompileError {
        int arrayDim = 0;
        while (this.lex.lookAhead() == 91) {
            ++arrayDim;
            this.lex.get();
            if (this.lex.get() == 93) continue;
            throw new CompileError("] is missing", this.lex);
        }
        return arrayDim;
    }

    private ASTList parseClassType(SymbolTable tbl) throws CompileError {
        ASTList list = null;
        while (true) {
            if (this.lex.get() != 400) {
                throw new SyntaxError(this.lex);
            }
            list = ASTList.append(list, new Symbol(this.lex.getString()));
            if (this.lex.lookAhead() != 46) break;
            this.lex.get();
        }
        return list;
    }

    private ASTree parsePostfix(SymbolTable tbl) throws CompileError {
        int token = this.lex.lookAhead();
        switch (token) {
            case 401: 
            case 402: 
            case 403: {
                this.lex.get();
                return new IntConst(this.lex.getLong(), token);
            }
            case 404: 
            case 405: {
                this.lex.get();
                return new DoubleConst(this.lex.getDouble(), token);
            }
        }
        ASTree expr = this.parsePrimaryExpr(tbl);
        block11: while (true) {
            switch (this.lex.lookAhead()) {
                case 40: {
                    expr = this.parseMethodCall(tbl, expr);
                    continue block11;
                }
                case 91: {
                    if (this.lex.lookAhead(1) == 93) {
                        int dim = this.parseArrayDimension();
                        if (this.lex.get() != 46 || this.lex.get() != 307) {
                            throw new SyntaxError(this.lex);
                        }
                        expr = this.parseDotClass(expr, dim);
                        continue block11;
                    }
                    ASTree index = this.parseArrayIndex(tbl);
                    if (index == null) {
                        throw new SyntaxError(this.lex);
                    }
                    expr = Expr.make(65, expr, index);
                    continue block11;
                }
                case 362: 
                case 363: {
                    int t = this.lex.get();
                    expr = Expr.make(t, null, expr);
                    continue block11;
                }
                case 46: {
                    String str;
                    this.lex.get();
                    int t = this.lex.get();
                    if (t == 307) {
                        expr = this.parseDotClass(expr, 0);
                        continue block11;
                    }
                    if (t == 336) {
                        expr = Expr.make(46, (ASTree)new Symbol(this.toClassName(expr)), (ASTree)new Keyword(t));
                        continue block11;
                    }
                    if (t == 400) {
                        str = this.lex.getString();
                        expr = Expr.make(46, expr, (ASTree)new Member(str));
                        continue block11;
                    }
                    throw new CompileError("missing member name", this.lex);
                }
                case 35: {
                    this.lex.get();
                    int t = this.lex.get();
                    if (t != 400) {
                        throw new CompileError("missing static member name", this.lex);
                    }
                    String str = this.lex.getString();
                    expr = Expr.make(35, (ASTree)new Symbol(this.toClassName(expr)), (ASTree)new Member(str));
                    continue block11;
                }
            }
            break;
        }
        return expr;
    }

    private ASTree parseDotClass(ASTree className, int dim) throws CompileError {
        String cname = this.toClassName(className);
        if (dim > 0) {
            StringBuffer sbuf = new StringBuffer();
            while (dim-- > 0) {
                sbuf.append('[');
            }
            sbuf.append('L').append(cname.replace('.', '/')).append(';');
            cname = sbuf.toString();
        }
        return Expr.make(46, (ASTree)new Symbol(cname), (ASTree)new Member("class"));
    }

    private ASTree parseDotClass(int builtinType, int dim) throws CompileError {
        String cname;
        if (dim > 0) {
            String cname2 = CodeGen.toJvmTypeName(builtinType, dim);
            return Expr.make(46, (ASTree)new Symbol(cname2), (ASTree)new Member("class"));
        }
        switch (builtinType) {
            case 301: {
                cname = "java.lang.Boolean";
                break;
            }
            case 303: {
                cname = "java.lang.Byte";
                break;
            }
            case 306: {
                cname = "java.lang.Character";
                break;
            }
            case 334: {
                cname = "java.lang.Short";
                break;
            }
            case 324: {
                cname = "java.lang.Integer";
                break;
            }
            case 326: {
                cname = "java.lang.Long";
                break;
            }
            case 317: {
                cname = "java.lang.Float";
                break;
            }
            case 312: {
                cname = "java.lang.Double";
                break;
            }
            case 344: {
                cname = "java.lang.Void";
                break;
            }
            default: {
                throw new CompileError("invalid builtin type: " + builtinType);
            }
        }
        return Expr.make(35, (ASTree)new Symbol(cname), (ASTree)new Member("TYPE"));
    }

    private ASTree parseMethodCall(SymbolTable tbl, ASTree expr) throws CompileError {
        int op;
        int token;
        if (expr instanceof Keyword ? (token = ((Keyword)expr).get()) != 339 && token != 336 : !(expr instanceof Symbol) && expr instanceof Expr && (op = ((Expr)expr).getOperator()) != 46 && op != 35) {
            throw new SyntaxError(this.lex);
        }
        return CallExpr.makeCall(expr, this.parseArgumentList(tbl));
    }

    private String toClassName(ASTree name) throws CompileError {
        StringBuffer sbuf = new StringBuffer();
        this.toClassName(name, sbuf);
        return sbuf.toString();
    }

    private void toClassName(ASTree name, StringBuffer sbuf) throws CompileError {
        Expr expr;
        if (name instanceof Symbol) {
            sbuf.append(((Symbol)name).get());
            return;
        }
        if (name instanceof Expr && (expr = (Expr)name).getOperator() == 46) {
            this.toClassName(expr.oprand1(), sbuf);
            sbuf.append('.');
            this.toClassName(expr.oprand2(), sbuf);
            return;
        }
        throw new CompileError("bad static member access", this.lex);
    }

    private ASTree parsePrimaryExpr(SymbolTable tbl) throws CompileError {
        int t = this.lex.get();
        switch (t) {
            case 336: 
            case 339: 
            case 410: 
            case 411: 
            case 412: {
                return new Keyword(t);
            }
            case 400: {
                String name = this.lex.getString();
                Declarator decl = tbl.lookup(name);
                if (decl == null) {
                    return new Member(name);
                }
                return new Variable(name, decl);
            }
            case 406: {
                return new StringL(this.lex.getString());
            }
            case 328: {
                return this.parseNew(tbl);
            }
            case 40: {
                ASTree expr = this.parseExpression(tbl);
                if (this.lex.get() == 41) {
                    return expr;
                }
                throw new CompileError(") is missing", this.lex);
            }
        }
        if (Parser.isBuiltinType(t) || t == 344) {
            int dim = this.parseArrayDimension();
            if (this.lex.get() == 46 && this.lex.get() == 307) {
                return this.parseDotClass(t, dim);
            }
        }
        throw new SyntaxError(this.lex);
    }

    private NewExpr parseNew(SymbolTable tbl) throws CompileError {
        ArrayInit init = null;
        int t = this.lex.lookAhead();
        if (Parser.isBuiltinType(t)) {
            this.lex.get();
            ASTList size = this.parseArraySize(tbl);
            if (this.lex.lookAhead() == 123) {
                init = this.parseArrayInitializer(tbl);
            }
            return new NewExpr(t, size, init);
        }
        if (t == 400) {
            ASTList name = this.parseClassType(tbl);
            t = this.lex.lookAhead();
            if (t == 40) {
                ASTList args = this.parseArgumentList(tbl);
                return new NewExpr(name, args);
            }
            if (t == 91) {
                ASTList size = this.parseArraySize(tbl);
                if (this.lex.lookAhead() == 123) {
                    init = this.parseArrayInitializer(tbl);
                }
                return NewExpr.makeObjectArray(name, size, init);
            }
        }
        throw new SyntaxError(this.lex);
    }

    private ASTList parseArraySize(SymbolTable tbl) throws CompileError {
        ASTList list = null;
        while (this.lex.lookAhead() == 91) {
            list = ASTList.append(list, this.parseArrayIndex(tbl));
        }
        return list;
    }

    private ASTree parseArrayIndex(SymbolTable tbl) throws CompileError {
        this.lex.get();
        if (this.lex.lookAhead() == 93) {
            this.lex.get();
            return null;
        }
        ASTree index = this.parseExpression(tbl);
        if (this.lex.get() != 93) {
            throw new CompileError("] is missing", this.lex);
        }
        return index;
    }

    private ASTList parseArgumentList(SymbolTable tbl) throws CompileError {
        if (this.lex.get() != 40) {
            throw new CompileError("( is missing", this.lex);
        }
        ASTList list = null;
        if (this.lex.lookAhead() != 41) {
            while (true) {
                list = ASTList.append(list, this.parseExpression(tbl));
                if (this.lex.lookAhead() != 44) break;
                this.lex.get();
            }
        }
        if (this.lex.get() != 41) {
            throw new CompileError(") is missing", this.lex);
        }
        return list;
    }
}

