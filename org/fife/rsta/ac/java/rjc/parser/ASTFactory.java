/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.java.rjc.parser;

import java.io.EOFException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.fife.rsta.ac.java.rjc.ast.AbstractTypeDeclarationNode;
import org.fife.rsta.ac.java.rjc.ast.CodeBlock;
import org.fife.rsta.ac.java.rjc.ast.CompilationUnit;
import org.fife.rsta.ac.java.rjc.ast.EnumBody;
import org.fife.rsta.ac.java.rjc.ast.EnumDeclaration;
import org.fife.rsta.ac.java.rjc.ast.Field;
import org.fife.rsta.ac.java.rjc.ast.FormalParameter;
import org.fife.rsta.ac.java.rjc.ast.ImportDeclaration;
import org.fife.rsta.ac.java.rjc.ast.LocalVariable;
import org.fife.rsta.ac.java.rjc.ast.Method;
import org.fife.rsta.ac.java.rjc.ast.NormalClassDeclaration;
import org.fife.rsta.ac.java.rjc.ast.NormalInterfaceDeclaration;
import org.fife.rsta.ac.java.rjc.ast.Package;
import org.fife.rsta.ac.java.rjc.ast.TypeDeclaration;
import org.fife.rsta.ac.java.rjc.ast.TypeDeclarationContainer;
import org.fife.rsta.ac.java.rjc.lang.Annotation;
import org.fife.rsta.ac.java.rjc.lang.Modifiers;
import org.fife.rsta.ac.java.rjc.lang.Type;
import org.fife.rsta.ac.java.rjc.lang.TypeArgument;
import org.fife.rsta.ac.java.rjc.lang.TypeParameter;
import org.fife.rsta.ac.java.rjc.lexer.Scanner;
import org.fife.rsta.ac.java.rjc.lexer.Token;
import org.fife.rsta.ac.java.rjc.lexer.TokenTypes;
import org.fife.rsta.ac.java.rjc.notices.ParserNotice;

public class ASTFactory
implements TokenTypes {
    private static final boolean DEBUG = false;
    private boolean nextMemberDeprecated;

    private boolean checkDeprecated() {
        boolean deprecated = this.nextMemberDeprecated;
        this.nextMemberDeprecated = false;
        return deprecated;
    }

    private void checkForDuplicateLocalVarNames(CompilationUnit cu, Token lVar, CodeBlock block, Method m) {
        int i;
        String name = lVar.getLexeme();
        boolean found = false;
        for (i = 0; i < block.getLocalVarCount(); ++i) {
            LocalVariable otherLocal = block.getLocalVar(i);
            if (!name.equals(otherLocal.getName())) continue;
            cu.addParserNotice(lVar, "Duplicate local variable: " + name);
            found = true;
            break;
        }
        if (!found) {
            if (block.getParent() != null) {
                this.checkForDuplicateLocalVarNames(cu, lVar, block.getParent(), m);
            } else if (m != null) {
                for (i = 0; i < m.getParameterCount(); ++i) {
                    FormalParameter param = m.getParameter(i);
                    if (!name.equals(param.getName())) continue;
                    cu.addParserNotice(lVar, "Duplicate local variable: " + name);
                    break;
                }
            }
        }
    }

    private Annotation _getAnnotation(CompilationUnit cu, Scanner s) throws IOException {
        s.yylexNonNull(0x4000000, "Annotation expected");
        Type type = this._getType(cu, s);
        if ("Deprecated".equals(type.toString())) {
            this.nextMemberDeprecated = true;
        }
        if (s.yyPeekCheckType() == 0x800001) {
            s.yylex();
            s.eatThroughNextSkippingBlocks(0x800002);
        }
        Annotation a = new Annotation(type);
        return a;
    }

    private CodeBlock _getBlock(CompilationUnit cu, CodeBlock parent, Method m, Scanner s, boolean isStatic) throws IOException {
        return this._getBlock(cu, parent, m, s, isStatic, 1);
    }

    private CodeBlock _getBlock(CompilationUnit cu, CodeBlock parent, Method m, Scanner s, boolean isStatic, int depth) throws IOException {
        ASTFactory.log("Entering _getBlock() (" + depth + ")");
        Token t = s.yylexNonNull(0x800003, "'{' expected");
        CodeBlock block = new CodeBlock(isStatic, s.createOffset(t.getOffset()));
        block.setParent(parent);
        boolean atStatementStart = true;
        block9: while (true) {
            if ((t = s.yylex()) == null) {
                ASTFactory.log("Exiting _getBlock() - eos (" + depth + ")");
                block.setDeclarationEndOffset(s.createOffset(s.getOffset()));
                return block;
            }
            int type = t.getType();
            boolean isFinal = false;
            switch (type) {
                case 0x800003: {
                    s.yyPushback(t);
                    CodeBlock child = this._getBlock(cu, block, m, s, isStatic, depth + 1);
                    block.add(child);
                    atStatementStart = true;
                    continue block9;
                }
                case 0x800004: {
                    block.setDeclarationEndOffset(s.createOffset(t.getOffset()));
                    break block9;
                }
                case 65583: {
                    int offs;
                    t = s.yyPeekNonNull(0x800003, 0x800001, "'{' or '(' expected");
                    if (t.getType() == 0x800001) {
                        s.eatParenPairs();
                    }
                    s.yyPeekNonNull(0x800003, "'{' expected");
                    CodeBlock tryBlock = this._getBlock(cu, block, m, s, isStatic, depth + 1);
                    block.add(tryBlock);
                    while (true) {
                        Type exType;
                        Token var;
                        if (s.yyPeekCheckType() != 65543 || s.yyPeekCheckType(2) != 0x800001) continue block9;
                        s.yylex();
                        s.yylex();
                        boolean multiCatch = false;
                        do {
                            isFinal = false;
                            Token temp = s.yyPeekNonNull(262144, 65554, "Throwable type expected");
                            if (temp.isType(65554)) {
                                isFinal = true;
                                s.yylex();
                            }
                            s.yyPeekNonNull(262144, "Variable declarator expected");
                            exType = this._getType(cu, s);
                            var = s.yylexNonNull(262144, 0x1000015, "Variable declarator expected");
                            multiCatch |= var.isType(0x1000015);
                        } while (var.isType(0x1000015));
                        s.yylexNonNull(0x800002, "')' expected");
                        s.yyPeekNonNull(0x800003, "'{' expected");
                        CodeBlock catchBlock = this._getBlock(cu, block, m, s, false, depth);
                        offs = var.getOffset();
                        if (multiCatch) {
                            exType = new Type("java");
                            exType.addIdentifier("lang", null);
                            exType.addIdentifier("Throwable", null);
                        }
                        LocalVariable localVar = new LocalVariable(s, isFinal, exType, offs, var.getLexeme());
                        this.checkForDuplicateLocalVarNames(cu, var, block, m);
                        catchBlock.addLocalVariable(localVar);
                        block.add(catchBlock);
                    }
                }
                case 65557: 
                case 65586: {
                    int nextType = s.yyPeekCheckType();
                    while (nextType != -1 && nextType != 0x800001) {
                        t = s.yylex();
                        if (t != null) {
                            ParserNotice pn = new ParserNotice(t, "Unexpected token");
                            cu.addParserNotice(pn);
                        }
                        nextType = s.yyPeekCheckType();
                    }
                    if (nextType == 0x800001) {
                        s.eatParenPairs();
                    }
                    if ((nextType = s.yyPeekCheckType()) != 0x800003) continue block9;
                    CodeBlock child = this._getBlock(cu, block, m, s, isStatic, depth + 1);
                    block.add(child);
                    atStatementStart = true;
                    continue block9;
                }
                case 65554: {
                    isFinal = true;
                    t = s.yylexNonNull("Unexpected end of file");
                }
                default: {
                    int nextType;
                    int offs;
                    if (t.isType(0x800007)) {
                        atStatementStart = true;
                        continue block9;
                    }
                    if (atStatementStart && (t.isBasicType() || t.isIdentifier())) {
                        Type varType;
                        s.yyPushback(t);
                        try {
                            varType = this._getType(cu, s, true);
                        } catch (IOException ioe) {
                            s.eatUntilNext(0x800007, 0x800003, 0x800004);
                            atStatementStart = true;
                            continue block9;
                        }
                        if (s.yyPeekCheckType() != 262144) continue block9;
                        while (true) {
                            if ((t = s.yylexNonNull(262144, "Variable name expected (type==" + varType.toString() + ")")) == null) continue block9;
                            int arrayDepth = s.skipBracketPairs();
                            varType.incrementBracketPairCount(arrayDepth);
                            String varDec = varType.toString() + " " + t.getLexeme();
                            ASTFactory.log(">>> Variable -- " + varDec + " (line " + t.getLine() + ")");
                            offs = t.getOffset();
                            String name = t.getLexeme();
                            LocalVariable lVar = new LocalVariable(s, isFinal, varType, offs, name);
                            this.checkForDuplicateLocalVarNames(cu, t, block, m);
                            block.addLocalVariable(lVar);
                            nextType = s.yyPeekCheckType();
                            if (nextType == 0x2000001) {
                                Token temp = s.eatThroughNextSkippingBlocksAndStuffInParens(0x800008, 0x800007);
                                if (temp != null) {
                                    s.yyPushback(temp);
                                }
                                nextType = s.yyPeekCheckType();
                            }
                            if (nextType != 0x800008) {
                                s.eatThroughNextSkippingBlocks(0x800007);
                                continue block9;
                            }
                            s.yylex();
                        }
                    }
                    atStatementStart = false;
                    continue block9;
                }
            }
            break;
        }
        ASTFactory.log("Exiting _getBlock() (" + depth + ")");
        return block;
    }

    private void _getClassBody(CompilationUnit cu, Scanner s, NormalClassDeclaration classDec) throws IOException {
        ASTFactory.log("Entering _getClassBody");
        Token t = s.yylexNonNull(0x800003, "'{' expected");
        classDec.setBodyStartOffset(s.createOffset(t.getOffset()));
        t = s.yylexNonNull("ClassBody expected");
        while (t.getType() != 0x800004) {
            switch (t.getType()) {
                case 0x800007: {
                    break;
                }
                case 65574: {
                    CodeBlock block;
                    Token t2 = s.yyPeekNonNull("'{' or modifier expected");
                    if (t2.isType(0x800003)) {
                        block = this._getBlock(cu, null, null, s, true);
                        classDec.addMember(block);
                        break;
                    }
                    s.yyPushback(t);
                    Modifiers modList = this._getModifierList(cu, s);
                    this._getMemberDecl(cu, s, classDec, modList);
                    break;
                }
                case 0x800003: {
                    s.yyPushback(t);
                    CodeBlock block = this._getBlock(cu, null, null, s, false);
                    classDec.addMember(block);
                    break;
                }
                default: {
                    s.yyPushback(t);
                    Modifiers modList = this._getModifierList(cu, s);
                    this._getMemberDecl(cu, s, classDec, modList);
                }
            }
            try {
                t = s.yylexNonNull("'}' expected (one)");
                classDec.setBodyEndOffset(s.createOffset(t.getOffset()));
            } catch (IOException ioe) {
                classDec.setBodyEndOffset(s.createOffset(s.getOffset()));
                int line = s.getLine();
                int col = s.getColumn();
                ParserNotice pn = new ParserNotice(line, col, 1, "'}' expected (two)");
                cu.addParserNotice(pn);
                break;
            }
        }
        ASTFactory.log("Exiting _getClassBody");
    }

    private TypeDeclaration _getClassOrInterfaceDeclaration(CompilationUnit cu, Scanner s, TypeDeclarationContainer addTo, Modifiers modList) throws IOException {
        AbstractTypeDeclarationNode td;
        ASTFactory.log("Entering _getClassOrInterfaceDeclaration");
        Token t = s.yyPeekNonNull("class, enum, interface or @interface expected");
        if (modList == null) {
            modList = this._getModifierList(cu, s);
        }
        t = s.yylexNonNull("class, enum, interface or @interface expected");
        switch (t.getType()) {
            case 65545: {
                td = this._getNormalClassDeclaration(cu, s, addTo);
                break;
            }
            case 65552: {
                td = this._getEnumDeclaration(cu, s, addTo);
                break;
            }
            case 65564: {
                td = this._getNormalInterfaceDeclaration(cu, s, addTo);
                break;
            }
            case 0x4000000: {
                throw new IOException("AnnotationTypeDeclaration not implemented");
            }
            default: {
                ParserNotice notice = new ParserNotice(t, "class, interface or enum expected");
                cu.addParserNotice(notice);
                td = this._getNormalClassDeclaration(cu, s, addTo);
            }
        }
        td.setModifiers(modList);
        td.setDeprecated(this.checkDeprecated());
        ASTFactory.log("Exiting _getClassOrInterfaceDeclaration");
        return td;
    }

    public CompilationUnit getCompilationUnit(String name, Scanner scanner) {
        CompilationUnit cu = new CompilationUnit(name);
        try {
            ArrayList<Annotation> initialAnnotations = null;
            while (scanner.yyPeekCheckType() == 0x4000000) {
                if (initialAnnotations == null) {
                    initialAnnotations = new ArrayList<Annotation>(1);
                }
                initialAnnotations.add(this._getAnnotation(cu, scanner));
            }
            Token t = scanner.yylex();
            if (t == null) {
                return cu;
            }
            if (t.isType(65568)) {
                t = scanner.yyPeekNonNull("Identifier expected");
                int offs = t.getOffset();
                String qualifiedID = this.getQualifiedIdentifier(scanner);
                Package pkg = new Package(scanner, offs, qualifiedID);
                if (initialAnnotations != null) {
                    initialAnnotations = null;
                }
                cu.setPackage(pkg);
                scanner.yylexNonNull(0x800007, "Semicolon expected");
                t = scanner.yylex();
            }
            block3: while (t != null && t.isType(65561)) {
                boolean isStatic = false;
                StringBuilder buf = new StringBuilder();
                t = scanner.yylexNonNull("Incomplete import statement");
                Token temp = null;
                int offs = 0;
                if (t.isType(65574)) {
                    isStatic = true;
                    t = scanner.yylexNonNull("Incomplete import statement");
                }
                if (!t.isIdentifier()) {
                    cu.addParserNotice(t, "Expected identifier, found: \"" + t.getLexeme() + "\"");
                    scanner.eatThroughNextSkippingBlocks(0x800007);
                    t = scanner.getMostRecentToken();
                } else {
                    offs = t.getOffset();
                    buf.append(t.getLexeme());
                    temp = scanner.yylexNonNull(0x800009, 0x800007, "'.' or ';' expected");
                    while (temp.isType(0x800009)) {
                        temp = scanner.yylexNonNull(262144, 0x1000012, "Identifier or '*' expected");
                        if (!temp.isIdentifier()) {
                            buf.append(".*");
                            temp = scanner.yylex();
                            break;
                        }
                        buf.append('.').append(temp.getLexeme());
                        temp = scanner.yylexNonNull(65561, 0x800009, 0x800007, "'.' or ';' expected");
                        if (!temp.isType(65561)) continue;
                        cu.addParserNotice(temp, "';' expected");
                        t = temp;
                        continue block3;
                    }
                    t = temp;
                }
                if (temp == null || !t.isType(0x800007)) {
                    throw new IOException("Semicolon expected, found " + t);
                }
                ImportDeclaration id = new ImportDeclaration(scanner, offs, buf.toString(), isStatic);
                cu.addImportDeclaration(id);
                t = scanner.yylex();
            }
            if (t == null) {
                return cu;
            }
            scanner.yyPushback(t);
            while (this._getTypeDeclaration(cu, scanner) != null) {
                if (initialAnnotations == null) continue;
                initialAnnotations = null;
            }
        } catch (IOException ioe) {
            Token lastTokenLexed;
            if (ASTFactory.isDebug() && !(ioe instanceof EOFException)) {
                ioe.printStackTrace();
            }
            ParserNotice notice = (lastTokenLexed = scanner.getMostRecentToken()) == null ? new ParserNotice(0, 0, 5, ioe.getMessage()) : new ParserNotice(lastTokenLexed, ioe.getMessage());
            cu.addParserNotice(notice);
        }
        return cu;
    }

    private EnumBody _getEnumBody(CompilationUnit cu, Scanner s, EnumDeclaration enumDec) throws IOException {
        CodeBlock block = this._getBlock(cu, null, null, s, false);
        enumDec.setBodyEndOffset(s.createOffset(block.getNameEndOffset()));
        return null;
    }

    private EnumDeclaration _getEnumDeclaration(CompilationUnit cu, Scanner s, TypeDeclarationContainer addTo) throws IOException {
        Token t = s.yylexNonNull(262144, "Identifier expected");
        String enumName = t.getLexeme();
        EnumDeclaration enumDec = new EnumDeclaration(s, t.getOffset(), enumName);
        enumDec.setPackage(cu.getPackage());
        addTo.addTypeDeclaration(enumDec);
        t = s.yylexNonNull("implements or '{' expected");
        if (t.isType(65560)) {
            ArrayList<Type> implemented = new ArrayList<Type>(1);
            do {
                implemented.add(this._getType(cu, s));
            } while ((t = s.yylex()) != null && t.isType(0x800008));
            if (t != null) {
                s.yyPushback(t);
            }
        } else if (t.isType(0x800003)) {
            s.yyPushback(t);
        }
        this._getEnumBody(cu, s, enumDec);
        return enumDec;
    }

    private List<FormalParameter> _getFormalParameters(CompilationUnit cu, List<Token> tokenList) throws IOException {
        ArrayList<FormalParameter> list = new ArrayList<FormalParameter>(0);
        Scanner s = new Scanner(tokenList);
        Token t = s.yylex();
        if (t == null) {
            return list;
        }
        while (true) {
            boolean isFinal = false;
            if (t.isType(65554)) {
                isFinal = true;
                t = s.yylexNonNull("Type expected");
            }
            ArrayList<Annotation> annotations = null;
            while (t.getType() == 0x4000000) {
                s.yyPushback(t);
                if (annotations == null) {
                    annotations = new ArrayList<Annotation>(1);
                }
                annotations.add(this._getAnnotation(cu, s));
                t = s.yylexNonNull("Type expected");
            }
            s.yyPushback(t);
            Type type = this._getType(cu, s);
            Token temp = s.yylexNonNull("Argument name expected");
            boolean elipsis = false;
            if (temp.isType(0x8000000)) {
                elipsis = true;
                temp = s.yylexNonNull(262144, "Argument name expected");
            }
            type.incrementBracketPairCount(s.skipBracketPairs());
            int offs = temp.getOffset();
            String name = temp.getLexeme();
            FormalParameter param = new FormalParameter(s, isFinal, type, offs, name, annotations);
            list.add(param);
            if (elipsis || (t = s.yylex()) == null) break;
            if (t.getType() != 0x800008) {
                throw new IOException("Comma expected");
            }
            t = s.yylexNonNull("Parameter or ')' expected");
        }
        return list;
    }

    private void _getInterfaceBody(CompilationUnit cu, Scanner s, NormalInterfaceDeclaration iDec) throws IOException {
        ASTFactory.log("Entering _getInterfaceBody");
        Token t = s.yylexNonNull(0x800003, "'{' expected");
        iDec.setBodyStartOffset(s.createOffset(t.getOffset()));
        t = s.yylexNonNull("InterfaceBody expected");
        while (t.getType() != 0x800004) {
            switch (t.getType()) {
                case 0x800007: {
                    break;
                }
                case 0x800003: {
                    s.yyPushback(t);
                    this._getBlock(cu, null, null, s, false);
                    break;
                }
                default: {
                    s.yyPushback(t);
                    Modifiers modList = this._getModifierList(cu, s);
                    this._getInterfaceMemberDecl(cu, s, iDec, modList);
                }
            }
            try {
                t = s.yylexNonNull("'}' expected (one)");
                iDec.setBodyEndOffset(s.createOffset(t.getOffset()));
            } catch (IOException ioe) {
                iDec.setBodyEndOffset(s.createOffset(s.getOffset()));
                int line = s.getLine();
                int col = s.getColumn();
                ParserNotice pn = new ParserNotice(line, col, 1, "'}' expected (two)");
                cu.addParserNotice(pn);
            }
        }
        ASTFactory.log("Exiting _getInterfaceBody");
    }

    private void _getInterfaceMemberDecl(CompilationUnit cu, Scanner s, NormalInterfaceDeclaration iDec, Modifiers modList) throws IOException {
        Token t;
        ASTFactory.log("Entering _getInterfaceMemberDecl");
        ArrayList<Token> tokenList = new ArrayList<Token>(1);
        ArrayList<Token> methodNameAndTypeTokenList = null;
        ArrayList<Token> methodParamsList = null;
        boolean methodDecl = false;
        boolean blockDecl = false;
        boolean varDecl = false;
        block6: while (true) {
            t = s.yylexNonNull("Unexpected end of input");
            switch (t.getType()) {
                case 0x800001: {
                    methodNameAndTypeTokenList = tokenList;
                    methodParamsList = new ArrayList<Token>(1);
                    methodDecl = true;
                    break block6;
                }
                case 0x800003: {
                    blockDecl = true;
                    break block6;
                }
                case 0x2000001: {
                    varDecl = true;
                    s.eatThroughNextSkippingBlocks(0x800007);
                    break block6;
                }
                case 0x800007: {
                    varDecl = true;
                    break block6;
                }
                default: {
                    tokenList.add(t);
                    continue block6;
                }
            }
            break;
        }
        if (varDecl) {
            ASTFactory.log("*** Variable declaration:");
            Scanner tempScanner = new Scanner(tokenList);
            Type type = this._getType(cu, tempScanner);
            Token fieldNameToken = tempScanner.yylexNonNull(262144, "Identifier (field name) expected");
            int bracketPairCount = tempScanner.skipBracketPairs();
            type.incrementBracketPairCount(bracketPairCount);
            Field field = new Field(s, modList, type, fieldNameToken);
            field.setDeprecated(this.checkDeprecated());
            field.setDocComment(s.getLastDocComment());
            ASTFactory.log(field.toString());
            iDec.addMember(field);
        } else if (methodDecl) {
            ASTFactory.log("*** Method declaration:");
            Scanner tempScanner = new Scanner(methodNameAndTypeTokenList);
            Type type = null;
            if (methodNameAndTypeTokenList.size() > 1) {
                if (tempScanner.yyPeekCheckType() == 0x1000003) {
                    this._getTypeParameters(cu, tempScanner);
                    type = this._getType(cu, tempScanner);
                } else {
                    type = this._getType(cu, tempScanner);
                }
            }
            Token methodNameToken = tempScanner.yylexNonNull(262144, "Identifier (method name) expected");
            while (!(t = s.yylexNonNull("Unexpected end of input")).isType(0x800002)) {
                methodParamsList.add(t);
            }
            List<FormalParameter> formalParams = this._getFormalParameters(cu, methodParamsList);
            if (s.yyPeekCheckType() == 0x800005) {
                if (type == null) {
                    throw new IOException("Constructors cannot return array types");
                }
                type.incrementBracketPairCount(s.skipBracketPairs());
            }
            List<String> thrownTypeNames = this.getThrownTypeNames(cu, s);
            t = s.yylexNonNull("'{' or ';' expected");
            if (t.getType() != 0x800007) {
                throw new IOException("';' expected");
            }
            Method m = new Method(s, modList, type, methodNameToken, formalParams, thrownTypeNames);
            m.setDeprecated(this.checkDeprecated());
            m.setDocComment(s.getLastDocComment());
            iDec.addMember(m);
        } else if (blockDecl) {
            if (tokenList.size() < 2) {
                for (int i = tokenList.size() - 1; i >= 0; --i) {
                    s.yyPushback((Token)tokenList.get(i));
                }
                CodeBlock block = this._getBlock(cu, null, null, s, false);
                iDec.addMember(block);
            } else {
                s.yyPushback(t);
                for (int i = tokenList.size() - 1; i >= 0; --i) {
                    s.yyPushback((Token)tokenList.get(i));
                }
                this._getClassOrInterfaceDeclaration(cu, s, iDec, modList);
            }
        }
        ASTFactory.log("Exiting _getInterfaceMemberDecl");
    }

    private void _getMemberDecl(CompilationUnit cu, Scanner s, NormalClassDeclaration classDec, Modifiers modList) throws IOException {
        Token t;
        ASTFactory.log("Entering _getMemberDecl");
        ArrayList<Token> tokenList = new ArrayList<Token>(1);
        ArrayList<Token> methodNameAndTypeTokenList = null;
        ArrayList<Token> methodParamsList = null;
        boolean methodDecl = false;
        boolean blockDecl = false;
        boolean varDecl = false;
        block6: while (true) {
            t = s.yylexNonNull("Unexpected end of input");
            switch (t.getType()) {
                case 0x800001: {
                    methodNameAndTypeTokenList = tokenList;
                    methodParamsList = new ArrayList<Token>(1);
                    methodDecl = true;
                    break block6;
                }
                case 0x800003: {
                    blockDecl = true;
                    break block6;
                }
                case 0x2000001: {
                    varDecl = true;
                    s.eatThroughNextSkippingBlocks(0x800007);
                    break block6;
                }
                case 0x800007: {
                    varDecl = true;
                    break block6;
                }
                default: {
                    tokenList.add(t);
                    continue block6;
                }
            }
            break;
        }
        if (varDecl) {
            ASTFactory.log("*** Variable declaration:");
            Scanner tempScanner = new Scanner(tokenList);
            Type type = this._getType(cu, tempScanner);
            Token fieldNameToken = tempScanner.yylexNonNull(262144, "Identifier (field name) expected");
            int bracketPairCount = tempScanner.skipBracketPairs();
            type.incrementBracketPairCount(bracketPairCount);
            Field field = new Field(s, modList, type, fieldNameToken);
            field.setDeprecated(this.checkDeprecated());
            field.setDocComment(s.getLastDocComment());
            ASTFactory.log(field.toString());
            classDec.addMember(field);
        } else if (methodDecl) {
            ASTFactory.log("*** Method declaration:");
            CodeBlock block = null;
            Scanner tempScanner = new Scanner(methodNameAndTypeTokenList);
            Type type = null;
            if (methodNameAndTypeTokenList.size() > 1) {
                if (tempScanner.yyPeekCheckType() == 0x1000003) {
                    this._getTypeParameters(cu, tempScanner);
                    if (tempScanner.yyPeekCheckType(2) != -1) {
                        type = this._getType(cu, tempScanner);
                    }
                } else {
                    type = this._getType(cu, tempScanner);
                }
            }
            Token methodNameToken = tempScanner.yylexNonNull(262144, "Identifier (method name) expected");
            while (true) {
                if ((t = s.yylexNonNull("Unexpected end of input")).isType(0x4000000)) {
                    methodParamsList.add(t);
                    t = s.yylexNonNull("Unexpected end of input");
                    methodParamsList.add(t);
                    t = s.yylexNonNull("Unexpected end of input");
                    if (t.isType(0x800001)) {
                        methodParamsList.add(t);
                        do {
                            t = s.yylexNonNull("Unexpected end of input");
                            methodParamsList.add(t);
                        } while (!t.isType(0x800002));
                        continue;
                    }
                    if (t.isType(0x4000000)) {
                        s.yyPushback(t);
                        continue;
                    }
                    if (t.isType(0x800002)) break;
                    methodParamsList.add(t);
                    continue;
                }
                if (t.isType(0x800002)) break;
                methodParamsList.add(t);
            }
            List<FormalParameter> formalParams = this._getFormalParameters(cu, methodParamsList);
            if (s.yyPeekCheckType() == 0x800005) {
                if (type == null) {
                    throw new IOException("Constructors cannot return array types");
                }
                type.incrementBracketPairCount(s.skipBracketPairs());
            }
            List<String> thrownTypeNames = this.getThrownTypeNames(cu, s);
            Method m = new Method(s, modList, type, methodNameToken, formalParams, thrownTypeNames);
            m.setDeprecated(this.checkDeprecated());
            m.setDocComment(s.getLastDocComment());
            classDec.addMember(m);
            t = s.yylexNonNull("'{' or ';' expected");
            if (!t.isType(0x800007)) {
                if (t.isType(0x800003)) {
                    s.yyPushback(t);
                    block = this._getBlock(cu, null, m, s, false);
                } else {
                    throw new IOException("'{' or ';' expected");
                }
            }
            m.setBody(block);
        } else if (blockDecl) {
            this.nextMemberDeprecated = false;
            if (tokenList.size() < 2) {
                for (int i = tokenList.size() - 1; i >= 0; --i) {
                    s.yyPushback((Token)tokenList.get(i));
                }
                CodeBlock block = this._getBlock(cu, null, null, s, false);
                classDec.addMember(block);
            } else {
                s.yyPushback(t);
                for (int i = tokenList.size() - 1; i >= 0; --i) {
                    s.yyPushback((Token)tokenList.get(i));
                }
                this._getClassOrInterfaceDeclaration(cu, s, classDec, modList);
            }
        }
        ASTFactory.log("Exiting _getMemberDecl (next== " + s.yyPeek() + ")");
    }

    private Modifiers _getModifierList(CompilationUnit cu, Scanner s) throws IOException {
        Modifiers modList = null;
        Token t = s.yylexNonNull("Unexpected end of input");
        while (true) {
            int modifier;
            if ((modifier = this.isModifier(t)) != -1) {
                if (modList == null) {
                    modList = new Modifiers();
                }
                if (!modList.addModifier(modifier)) {
                    cu.addParserNotice(t, "Duplicate modifier");
                }
            } else if (t.isType(0x4000000)) {
                Token next = s.yyPeekNonNull("Annotation expected");
                s.yyPushback(t);
                if (next.isType(65564)) {
                    return modList;
                }
                if (modList == null) {
                    modList = new Modifiers();
                }
                modList.addAnnotation(this._getAnnotation(cu, s));
            } else {
                s.yyPushback(t);
                return modList;
            }
            t = s.yylexNonNull("Unexpected end of input");
        }
    }

    private NormalClassDeclaration _getNormalClassDeclaration(CompilationUnit cu, Scanner s, TypeDeclarationContainer addTo) throws IOException {
        String className;
        ASTFactory.log("Entering _getNormalClassDeclaration");
        Token t = s.yylexNonNull("Identifier expected");
        if (t.isType(262144)) {
            className = t.getLexeme();
        } else {
            className = "Unknown";
            cu.addParserNotice(new ParserNotice(t, "Class name expected"));
            s.eatUntilNext(65553, 65560, 0x800003);
        }
        NormalClassDeclaration classDec = new NormalClassDeclaration(s, t.getOffset(), className);
        classDec.setPackage(cu.getPackage());
        addTo.addTypeDeclaration(classDec);
        t = s.yylexNonNull("TypeParameters, extends, implements or '{' expected");
        if (t.isType(0x1000003)) {
            s.yyPushback(t);
            List<TypeParameter> typeParams = this._getTypeParameters(cu, s);
            classDec.setTypeParameters(typeParams);
            t = s.yylexNonNull("extends, implements or '{' expected");
        }
        if (t.isType(65553)) {
            classDec.setExtendedType(this._getType(cu, s));
            t = s.yylexNonNull("implements or '{' expected");
        }
        if (t.isType(65560)) {
            do {
                classDec.addImplemented(this._getType(cu, s));
            } while ((t = s.yylex()) != null && t.isType(0x800008));
            if (t != null) {
                s.yyPushback(t);
            }
        } else if (t.isType(0x800003)) {
            s.yyPushback(t);
        }
        this._getClassBody(cu, s, classDec);
        ASTFactory.log("Exiting _getNormalClassDeclaration");
        return classDec;
    }

    private NormalInterfaceDeclaration _getNormalInterfaceDeclaration(CompilationUnit cu, Scanner s, TypeDeclarationContainer addTo) throws IOException {
        String iName;
        Token t = s.yylexNonNull("Identifier expected");
        if (t.isType(262144)) {
            iName = t.getLexeme();
        } else {
            iName = "Unknown";
            cu.addParserNotice(new ParserNotice(t, "Interface name expected"));
            s.eatUntilNext(65553, 0x800003);
        }
        NormalInterfaceDeclaration iDec = new NormalInterfaceDeclaration(s, t.getOffset(), iName);
        iDec.setPackage(cu.getPackage());
        addTo.addTypeDeclaration(iDec);
        t = s.yylexNonNull("TypeParameters, extends or '{' expected");
        if (t.isType(0x1000003)) {
            s.yyPushback(t);
            this._getTypeParameters(cu, s);
            t = s.yylexNonNull("Interface body expected");
        }
        if (t.isType(65553)) {
            do {
                iDec.addExtended(this._getType(cu, s));
            } while ((t = s.yylex()) != null && t.isType(0x800008));
            if (t != null) {
                s.yyPushback(t);
            }
        } else if (t.isType(0x800003)) {
            s.yyPushback(t);
        }
        this._getInterfaceBody(cu, s, iDec);
        return iDec;
    }

    private String getQualifiedIdentifier(Scanner scanner) throws IOException {
        Token t;
        StringBuilder sb = new StringBuilder();
        while ((t = scanner.yylex()).isIdentifier()) {
            sb.append(t.getLexeme());
            t = scanner.yylex();
            if (!t.isType(0x800009)) break;
            sb.append('.');
        }
        scanner.yyPushback(t);
        return sb.toString();
    }

    private List<String> getThrownTypeNames(CompilationUnit cu, Scanner s) throws IOException {
        if (s.yyPeekCheckType() != 65581) {
            return null;
        }
        s.yylex();
        ArrayList<String> list = new ArrayList<String>(1);
        list.add(this.getQualifiedIdentifier(s));
        while (s.yyPeekCheckType() == 0x800008) {
            s.yylex();
            list.add(this.getQualifiedIdentifier(s));
        }
        return list;
    }

    private Type _getType(CompilationUnit cu, Scanner s) throws IOException {
        return this._getType(cu, s, false);
    }

    private Type _getType(CompilationUnit cu, Scanner s, boolean pushbackOnUnexpected) throws IOException {
        ASTFactory.log("Entering _getType()");
        Type type = new Type();
        Token t = s.yylexNonNull("Type expected");
        if (t.isType(65584)) {
            type.addIdentifier(t.getLexeme(), null);
            ASTFactory.log("Exiting _getType(): " + type.toString());
            return type;
        }
        if (t.isBasicType()) {
            int arrayDepth = s.skipBracketPairs();
            type.addIdentifier(t.getLexeme(), null);
            type.setBracketPairCount(arrayDepth);
            ASTFactory.log("Exiting _getType(): " + type.toString());
            return type;
        }
        block3: while (true) {
            switch (t.getType()) {
                case 262144: {
                    List<TypeArgument> typeArgs = null;
                    if (s.yyPeekCheckType() == 0x1000003) {
                        typeArgs = this._getTypeArguments(cu, s);
                    }
                    type.addIdentifier(t.getLexeme(), typeArgs);
                    t = s.yylexNonNull("Unexpected end of input");
                    if (t.isType(0x800009)) {
                        t = s.yylexNonNull("Unexpected end of input");
                        continue block3;
                    }
                    if (t.isType(0x800005)) {
                        s.yyPushback(t);
                        type.setBracketPairCount(s.skipBracketPairs());
                        break block3;
                    }
                    s.yyPushback(t);
                    break block3;
                }
                default: {
                    if (pushbackOnUnexpected) {
                        s.yyPushback(t);
                    }
                    throw new IOException("Expected identifier, found: " + t);
                }
            }
            break;
        }
        ASTFactory.log("Exiting _getType(): " + type.toString());
        return type;
    }

    private TypeArgument _getTypeArgument(CompilationUnit cu, Scanner s) throws IOException {
        TypeArgument typeArg;
        ASTFactory.log("Entering _getTypeArgument()");
        Token t = s.yyPeekNonNull("Type or '?' expected");
        if (t.isType(0x1000006)) {
            s.yylex();
            t = s.yyPeek();
            if (t.getType() != 0x1000002) {
                t = s.yylexNonNull(0x800008, 65553, 65576, "',', super or extends expected");
                switch (t.getType()) {
                    case 0x800008: {
                        typeArg = new TypeArgument(null, 0, null);
                        s.yyPushback(t);
                        break;
                    }
                    case 65553: {
                        Type otherType = this._getType(cu, s);
                        typeArg = new TypeArgument(null, 1, otherType);
                        break;
                    }
                    default: {
                        Type otherType = this._getType(cu, s);
                        typeArg = new TypeArgument(null, 2, otherType);
                        break;
                    }
                }
            } else {
                typeArg = new TypeArgument(null, 0, null);
            }
        } else {
            Type type = this._getType(cu, s);
            typeArg = new TypeArgument(type);
        }
        ASTFactory.log("Exiting _getTypeArgument() : " + typeArg);
        return typeArg;
    }

    private List<TypeArgument> _getTypeArguments(CompilationUnit cu, Scanner s) throws IOException {
        Token t;
        s.increaseTypeArgumentsLevel();
        ASTFactory.log("Entering _getTypeArguments() (" + s.getTypeArgumentsLevel() + ")");
        s.markResetPosition();
        s.yylexNonNull(0x1000003, "'<' expected");
        ArrayList<TypeArgument> typeArgs = new ArrayList<TypeArgument>(1);
        do {
            typeArgs.add(this._getTypeArgument(cu, s));
            t = s.yylexNonNull("',' or '>' expected");
            if (t.getType() == 0x800008 || t.getType() == 0x1000002) continue;
            s.resetToLastMarkedPosition();
            ASTFactory.log("Exiting _getTypeArguments() (" + s.getTypeArgumentsLevel() + ") - NOT TYPE ARGUMENTS (" + t.getLexeme() + ")");
            s.decreaseTypeArgumentsLevel();
            return null;
        } while (t.isType(0x800008));
        ASTFactory.log("Exiting _getTypeArguments() (" + s.getTypeArgumentsLevel() + ")");
        s.decreaseTypeArgumentsLevel();
        s.clearResetPosition();
        return typeArgs;
    }

    private TypeDeclaration _getTypeDeclaration(CompilationUnit cu, Scanner s) throws IOException {
        Token t = s.yylex();
        if (t == null) {
            return null;
        }
        while (t.isType(0x800007)) {
            t = s.yylex();
            if (t != null) continue;
            return null;
        }
        s.yyPushback(t);
        String docComment = s.getLastDocComment();
        TypeDeclaration td = this._getClassOrInterfaceDeclaration(cu, s, cu, null);
        td.setDocComment(docComment);
        return td;
    }

    private TypeParameter _getTypeParameter(CompilationUnit cu, Scanner s) throws IOException {
        ASTFactory.log("Entering _getTypeParameter()");
        Token identifier = s.yylexNonNull(262144, "Identifier expected");
        TypeParameter typeParam = new TypeParameter(identifier);
        if (s.yyPeekCheckType() == 65553) {
            do {
                s.yylex();
                typeParam.addBound(this._getType(cu, s));
            } while (s.yyPeekCheckType() == 0x1000014);
        }
        ASTFactory.log("Exiting _getTypeParameter(): " + typeParam.getName());
        return typeParam;
    }

    private List<TypeParameter> _getTypeParameters(CompilationUnit cu, Scanner s) throws IOException {
        s.increaseTypeArgumentsLevel();
        ASTFactory.log("Entering _getTypeParameters() (" + s.getTypeArgumentsLevel() + ")");
        s.markResetPosition();
        Token t = s.yylexNonNull(0x1000003, "TypeParameters expected");
        ArrayList<TypeParameter> typeParams = new ArrayList<TypeParameter>(1);
        do {
            TypeParameter typeParam = this._getTypeParameter(cu, s);
            typeParams.add(typeParam);
        } while ((t = s.yylexNonNull(0x800008, 0x1000002, "',' or '>' expected")).isType(0x800008));
        ASTFactory.log("Exiting _getTypeParameters() (" + s.getTypeArgumentsLevel() + ")");
        s.decreaseTypeArgumentsLevel();
        return typeParams;
    }

    private static boolean isDebug() {
        return false;
    }

    private int isModifier(Token t) {
        switch (t.getType()) {
            case 65537: 
            case 65554: 
            case 65566: 
            case 65569: 
            case 65570: 
            case 65571: 
            case 65574: 
            case 65575: 
            case 65578: 
            case 65582: 
            case 65585: {
                return t.getType();
            }
        }
        return -1;
    }

    private static void log(String msg) {
    }
}

