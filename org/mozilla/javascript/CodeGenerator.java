/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript;

import org.mozilla.javascript.CompilerEnvirons;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Icode;
import org.mozilla.javascript.InterpreterData;
import org.mozilla.javascript.Kit;
import org.mozilla.javascript.Node;
import org.mozilla.javascript.NodeTransformer;
import org.mozilla.javascript.ObjArray;
import org.mozilla.javascript.ObjToIntMap;
import org.mozilla.javascript.RegExpProxy;
import org.mozilla.javascript.ScriptRuntime;
import org.mozilla.javascript.UintMap;
import org.mozilla.javascript.ast.AstRoot;
import org.mozilla.javascript.ast.FunctionNode;
import org.mozilla.javascript.ast.Jump;
import org.mozilla.javascript.ast.ScriptNode;

class CodeGenerator
extends Icode {
    private static final int MIN_LABEL_TABLE_SIZE = 32;
    private static final int MIN_FIXUP_TABLE_SIZE = 40;
    private CompilerEnvirons compilerEnv;
    private boolean itsInFunctionFlag;
    private boolean itsInTryFlag;
    private InterpreterData itsData;
    private ScriptNode scriptOrFn;
    private int iCodeTop;
    private int stackDepth;
    private int lineNumber;
    private int doubleTableTop;
    private ObjToIntMap strings = new ObjToIntMap(20);
    private int localTop;
    private int[] labelTable;
    private int labelTableTop;
    private long[] fixupTable;
    private int fixupTableTop;
    private ObjArray literalIds = new ObjArray();
    private int exceptionTableTop;
    private static final int ECF_TAIL = 1;

    CodeGenerator() {
    }

    public InterpreterData compile(CompilerEnvirons compilerEnv, ScriptNode tree, String encodedSource, boolean returnFunction) {
        this.compilerEnv = compilerEnv;
        new NodeTransformer().transform(tree);
        this.scriptOrFn = returnFunction ? tree.getFunctionNode(0) : tree;
        this.itsData = new InterpreterData(compilerEnv.getLanguageVersion(), this.scriptOrFn.getSourceName(), encodedSource, ((AstRoot)tree).isInStrictMode());
        this.itsData.topLevel = true;
        if (returnFunction) {
            this.generateFunctionICode();
        } else {
            this.generateICodeFromTree(this.scriptOrFn);
        }
        return this.itsData;
    }

    private void generateFunctionICode() {
        this.itsInFunctionFlag = true;
        FunctionNode theFunction = (FunctionNode)this.scriptOrFn;
        this.itsData.itsFunctionType = theFunction.getFunctionType();
        this.itsData.itsNeedsActivation = theFunction.requiresActivation();
        if (theFunction.getFunctionName() != null) {
            this.itsData.itsName = theFunction.getName();
        }
        if (theFunction.isGenerator()) {
            this.addIcode(-62);
            this.addUint16(theFunction.getBaseLineno() & 0xFFFF);
        }
        this.generateICodeFromTree(theFunction.getLastChild());
    }

    private void generateICodeFromTree(Node tree) {
        Object[] tmp;
        this.generateNestedFunctions();
        this.generateRegExpLiterals();
        this.visitStatement(tree, 0);
        this.fixLabelGotos();
        if (this.itsData.itsFunctionType == 0) {
            this.addToken(64);
        }
        if (this.itsData.itsICode.length != this.iCodeTop) {
            tmp = new byte[this.iCodeTop];
            System.arraycopy(this.itsData.itsICode, 0, tmp, 0, this.iCodeTop);
            this.itsData.itsICode = tmp;
        }
        if (this.strings.size() == 0) {
            this.itsData.itsStringTable = null;
        } else {
            this.itsData.itsStringTable = new String[this.strings.size()];
            ObjToIntMap.Iterator iter = this.strings.newIterator();
            iter.start();
            while (!iter.done()) {
                String str = (String)iter.getKey();
                int index = iter.getValue();
                if (this.itsData.itsStringTable[index] != null) {
                    Kit.codeBug();
                }
                this.itsData.itsStringTable[index] = str;
                iter.next();
            }
        }
        if (this.doubleTableTop == 0) {
            this.itsData.itsDoubleTable = null;
        } else if (this.itsData.itsDoubleTable.length != this.doubleTableTop) {
            tmp = new double[this.doubleTableTop];
            System.arraycopy(this.itsData.itsDoubleTable, 0, tmp, 0, this.doubleTableTop);
            this.itsData.itsDoubleTable = tmp;
        }
        if (this.exceptionTableTop != 0 && this.itsData.itsExceptionTable.length != this.exceptionTableTop) {
            tmp = new int[this.exceptionTableTop];
            System.arraycopy(this.itsData.itsExceptionTable, 0, tmp, 0, this.exceptionTableTop);
            this.itsData.itsExceptionTable = tmp;
        }
        this.itsData.itsMaxVars = this.scriptOrFn.getParamAndVarCount();
        this.itsData.itsMaxFrameArray = this.itsData.itsMaxVars + this.itsData.itsMaxLocals + this.itsData.itsMaxStack;
        this.itsData.argNames = this.scriptOrFn.getParamAndVarNames();
        this.itsData.argIsConst = this.scriptOrFn.getParamAndVarConst();
        this.itsData.argCount = this.scriptOrFn.getParamCount();
        this.itsData.encodedSourceStart = this.scriptOrFn.getEncodedSourceStart();
        this.itsData.encodedSourceEnd = this.scriptOrFn.getEncodedSourceEnd();
        if (this.literalIds.size() != 0) {
            this.itsData.literalIds = this.literalIds.toArray();
        }
    }

    private void generateNestedFunctions() {
        int functionCount = this.scriptOrFn.getFunctionCount();
        if (functionCount == 0) {
            return;
        }
        InterpreterData[] array = new InterpreterData[functionCount];
        for (int i = 0; i != functionCount; ++i) {
            FunctionNode fn = this.scriptOrFn.getFunctionNode(i);
            CodeGenerator gen = new CodeGenerator();
            gen.compilerEnv = this.compilerEnv;
            gen.scriptOrFn = fn;
            gen.itsData = new InterpreterData(this.itsData);
            gen.generateFunctionICode();
            array[i] = gen.itsData;
        }
        this.itsData.itsNestedFunctions = array;
    }

    private void generateRegExpLiterals() {
        int N = this.scriptOrFn.getRegexpCount();
        if (N == 0) {
            return;
        }
        Context cx = Context.getContext();
        RegExpProxy rep = ScriptRuntime.checkRegExpProxy(cx);
        Object[] array = new Object[N];
        for (int i = 0; i != N; ++i) {
            String string = this.scriptOrFn.getRegexpString(i);
            String flags = this.scriptOrFn.getRegexpFlags(i);
            array[i] = rep.compileRegExp(cx, string, flags);
        }
        this.itsData.itsRegExpLiterals = array;
    }

    private void updateLineNumber(Node node) {
        int lineno = node.getLineno();
        if (lineno != this.lineNumber && lineno >= 0) {
            if (this.itsData.firstLinePC < 0) {
                this.itsData.firstLinePC = lineno;
            }
            this.lineNumber = lineno;
            this.addIcode(-26);
            this.addUint16(lineno & 0xFFFF);
        }
    }

    private RuntimeException badTree(Node node) {
        throw new RuntimeException(node.toString());
    }

    private void visitStatement(Node node, int initialStackDepth) {
        int type = node.getType();
        switch (type) {
            case 109: {
                int fnIndex = node.getExistingIntProp(1);
                int fnType = this.scriptOrFn.getFunctionNode(fnIndex).getFunctionType();
                if (fnType == 3) {
                    this.addIndexOp(-20, fnIndex);
                } else if (fnType != 1) {
                    throw Kit.codeBug();
                }
                if (this.itsInFunctionFlag) break;
                this.addIndexOp(-19, fnIndex);
                this.stackChange(1);
                this.addIcode(-5);
                this.stackChange(-1);
                break;
            }
            case 123: 
            case 128: 
            case 129: 
            case 130: 
            case 132: {
                this.updateLineNumber(node);
            }
            case 136: {
                Node child;
                while (child != null) {
                    this.visitStatement(child, initialStackDepth);
                    child = child.getNext();
                }
                break;
            }
            case 2: {
                Node child;
                this.visitExpression(child, 0);
                this.addToken(2);
                this.stackChange(-1);
                break;
            }
            case 3: {
                this.addToken(3);
                break;
            }
            case 141: {
                Node child;
                int local = this.allocLocal();
                node.putIntProp(2, local);
                this.updateLineNumber(node);
                for (child = node.getFirstChild(); child != null; child = child.getNext()) {
                    this.visitStatement(child, initialStackDepth);
                }
                this.addIndexOp(-56, local);
                this.releaseLocal(local);
                break;
            }
            case 160: {
                this.addIcode(-64);
                break;
            }
            case 114: {
                Node child;
                this.updateLineNumber(node);
                this.visitExpression(child, 0);
                for (Jump caseNode = (Jump)child.getNext(); caseNode != null; caseNode = (Jump)caseNode.getNext()) {
                    if (caseNode.getType() != 115) {
                        throw this.badTree(caseNode);
                    }
                    Node test = caseNode.getFirstChild();
                    this.addIcode(-1);
                    this.stackChange(1);
                    this.visitExpression(test, 0);
                    this.addToken(46);
                    this.stackChange(-1);
                    this.addGoto(caseNode.target, -6);
                    this.stackChange(-1);
                }
                this.addIcode(-4);
                this.stackChange(-1);
                break;
            }
            case 131: {
                this.markTargetLabel(node);
                break;
            }
            case 6: 
            case 7: {
                Node child;
                Node target = ((Jump)node).target;
                this.visitExpression(child, 0);
                this.addGoto(target, type);
                this.stackChange(-1);
                break;
            }
            case 5: {
                Node target = ((Jump)node).target;
                this.addGoto(target, type);
                break;
            }
            case 135: {
                Node target = ((Jump)node).target;
                this.addGoto(target, -23);
                break;
            }
            case 125: {
                Node child;
                this.stackChange(1);
                int finallyRegister = this.getLocalBlockRef(node);
                this.addIndexOp(-24, finallyRegister);
                this.stackChange(-1);
                while (child != null) {
                    this.visitStatement(child, initialStackDepth);
                    child = child.getNext();
                }
                this.addIndexOp(-25, finallyRegister);
                break;
            }
            case 133: 
            case 134: {
                Node child;
                this.updateLineNumber(node);
                this.visitExpression(child, 0);
                this.addIcode(type == 133 ? -4 : -5);
                this.stackChange(-1);
                break;
            }
            case 81: {
                Node finallyTarget;
                Node child;
                Jump tryNode = (Jump)node;
                int exceptionObjectLocal = this.getLocalBlockRef(tryNode);
                int scopeLocal = this.allocLocal();
                this.addIndexOp(-13, scopeLocal);
                int tryStart = this.iCodeTop;
                boolean savedFlag = this.itsInTryFlag;
                this.itsInTryFlag = true;
                while (child != null) {
                    this.visitStatement(child, initialStackDepth);
                    child = child.getNext();
                }
                this.itsInTryFlag = savedFlag;
                Node catchTarget = tryNode.target;
                if (catchTarget != null) {
                    int catchStartPC = this.labelTable[this.getTargetLabel(catchTarget)];
                    this.addExceptionHandler(tryStart, catchStartPC, catchStartPC, false, exceptionObjectLocal, scopeLocal);
                }
                if ((finallyTarget = tryNode.getFinally()) != null) {
                    int finallyStartPC = this.labelTable[this.getTargetLabel(finallyTarget)];
                    this.addExceptionHandler(tryStart, finallyStartPC, finallyStartPC, true, exceptionObjectLocal, scopeLocal);
                }
                this.addIndexOp(-56, scopeLocal);
                this.releaseLocal(scopeLocal);
                break;
            }
            case 57: {
                Node child;
                int localIndex = this.getLocalBlockRef(node);
                int scopeIndex = node.getExistingIntProp(14);
                String name = child.getString();
                child = child.getNext();
                this.visitExpression(child, 0);
                this.addStringPrefix(name);
                this.addIndexPrefix(localIndex);
                this.addToken(57);
                this.addUint8(scopeIndex != 0 ? 1 : 0);
                this.stackChange(-1);
                break;
            }
            case 50: {
                Node child;
                this.updateLineNumber(node);
                this.visitExpression(child, 0);
                this.addToken(50);
                this.addUint16(this.lineNumber & 0xFFFF);
                this.stackChange(-1);
                break;
            }
            case 51: {
                this.updateLineNumber(node);
                this.addIndexOp(51, this.getLocalBlockRef(node));
                break;
            }
            case 4: {
                Node child;
                this.updateLineNumber(node);
                if (node.getIntProp(20, 0) != 0) {
                    this.addIcode(-63);
                    this.addUint16(this.lineNumber & 0xFFFF);
                    break;
                }
                if (child != null) {
                    this.visitExpression(child, 1);
                    this.addToken(4);
                    this.stackChange(-1);
                    break;
                }
                this.addIcode(-22);
                break;
            }
            case 64: {
                this.updateLineNumber(node);
                this.addToken(64);
                break;
            }
            case 58: 
            case 59: 
            case 60: {
                Node child;
                this.visitExpression(child, 0);
                this.addIndexOp(type, this.getLocalBlockRef(node));
                this.stackChange(-1);
                break;
            }
            case -62: {
                break;
            }
            default: {
                throw this.badTree(node);
            }
        }
        if (this.stackDepth != initialStackDepth) {
            throw Kit.codeBug();
        }
    }

    private void visitExpression(Node node, int contextFlags) {
        int type = node.getType();
        int savedStackDepth = this.stackDepth;
        switch (type) {
            case 109: {
                int fnIndex = node.getExistingIntProp(1);
                FunctionNode fn = this.scriptOrFn.getFunctionNode(fnIndex);
                if (fn.getFunctionType() != 2) {
                    throw Kit.codeBug();
                }
                this.addIndexOp(-19, fnIndex);
                this.stackChange(1);
                break;
            }
            case 54: {
                int localIndex = this.getLocalBlockRef(node);
                this.addIndexOp(54, localIndex);
                this.stackChange(1);
                break;
            }
            case 89: {
                Node child;
                Node lastChild = node.getLastChild();
                for (child = node.getFirstChild(); child != lastChild; child = child.getNext()) {
                    this.visitExpression(child, 0);
                    this.addIcode(-4);
                    this.stackChange(-1);
                }
                this.visitExpression(child, contextFlags & 1);
                break;
            }
            case 138: {
                this.stackChange(1);
                break;
            }
            case 30: 
            case 38: 
            case 70: {
                Node child;
                if (type == 30) {
                    this.visitExpression(child, 0);
                } else {
                    this.generateCallFunAndThis(child);
                }
                int argCount = 0;
                while ((child = child.getNext()) != null) {
                    this.visitExpression(child, 0);
                    ++argCount;
                }
                int callType = node.getIntProp(10, 0);
                if (type != 70 && callType != 0) {
                    this.addIndexOp(-21, argCount);
                    this.addUint8(callType);
                    this.addUint8(type == 30 ? 1 : 0);
                    this.addUint16(this.lineNumber & 0xFFFF);
                } else {
                    if (type == 38 && (contextFlags & 1) != 0 && !this.compilerEnv.isGenerateDebugInfo() && !this.itsInTryFlag) {
                        type = -55;
                    }
                    this.addIndexOp(type, argCount);
                }
                if (type == 30) {
                    this.stackChange(-argCount);
                } else {
                    this.stackChange(-1 - argCount);
                }
                if (argCount <= this.itsData.itsMaxCalleeArgs) break;
                this.itsData.itsMaxCalleeArgs = argCount;
                break;
            }
            case 104: 
            case 105: {
                Node child;
                this.visitExpression(child, 0);
                this.addIcode(-1);
                this.stackChange(1);
                int afterSecondJumpStart = this.iCodeTop;
                int jump = type == 105 ? 7 : 6;
                this.addGotoOp(jump);
                this.stackChange(-1);
                this.addIcode(-4);
                this.stackChange(-1);
                child = child.getNext();
                this.visitExpression(child, contextFlags & 1);
                this.resolveForwardGoto(afterSecondJumpStart);
                break;
            }
            case 102: {
                Node child;
                Node ifThen = child.getNext();
                Node ifElse = ifThen.getNext();
                this.visitExpression(child, 0);
                int elseJumpStart = this.iCodeTop;
                this.addGotoOp(7);
                this.stackChange(-1);
                this.visitExpression(ifThen, contextFlags & 1);
                int afterElseJumpStart = this.iCodeTop;
                this.addGotoOp(5);
                this.resolveForwardGoto(elseJumpStart);
                this.stackDepth = savedStackDepth;
                this.visitExpression(ifElse, contextFlags & 1);
                this.resolveForwardGoto(afterElseJumpStart);
                break;
            }
            case 33: 
            case 34: {
                Node child;
                this.visitExpression(child, 0);
                child = child.getNext();
                this.addStringOp(type, child.getString());
                break;
            }
            case 31: {
                Node child;
                boolean isName = child.getType() == 49;
                this.visitExpression(child, 0);
                child = child.getNext();
                this.visitExpression(child, 0);
                if (isName) {
                    this.addIcode(0);
                } else {
                    this.addToken(31);
                }
                this.stackChange(-1);
                break;
            }
            case 9: 
            case 10: 
            case 11: 
            case 12: 
            case 13: 
            case 14: 
            case 15: 
            case 16: 
            case 17: 
            case 18: 
            case 19: 
            case 20: 
            case 21: 
            case 22: 
            case 23: 
            case 24: 
            case 25: 
            case 36: 
            case 46: 
            case 47: 
            case 52: 
            case 53: {
                Node child;
                this.visitExpression(child, 0);
                child = child.getNext();
                this.visitExpression(child, 0);
                this.addToken(type);
                this.stackChange(-1);
                break;
            }
            case 26: 
            case 27: 
            case 28: 
            case 29: 
            case 32: 
            case 126: {
                Node child;
                this.visitExpression(child, 0);
                if (type == 126) {
                    this.addIcode(-4);
                    this.addIcode(-50);
                    break;
                }
                this.addToken(type);
                break;
            }
            case 67: 
            case 69: {
                Node child;
                this.visitExpression(child, 0);
                this.addToken(type);
                break;
            }
            case 35: 
            case 139: {
                Node child;
                this.visitExpression(child, 0);
                child = child.getNext();
                String property = child.getString();
                child = child.getNext();
                if (type == 139) {
                    this.addIcode(-1);
                    this.stackChange(1);
                    this.addStringOp(33, property);
                    this.stackChange(-1);
                }
                this.visitExpression(child, 0);
                this.addStringOp(35, property);
                this.stackChange(-1);
                break;
            }
            case 37: 
            case 140: {
                Node child;
                this.visitExpression(child, 0);
                child = child.getNext();
                this.visitExpression(child, 0);
                child = child.getNext();
                if (type == 140) {
                    this.addIcode(-2);
                    this.stackChange(2);
                    this.addToken(36);
                    this.stackChange(-1);
                    this.stackChange(-1);
                }
                this.visitExpression(child, 0);
                this.addToken(37);
                this.stackChange(-2);
                break;
            }
            case 68: 
            case 142: {
                Node child;
                this.visitExpression(child, 0);
                child = child.getNext();
                if (type == 142) {
                    this.addIcode(-1);
                    this.stackChange(1);
                    this.addToken(67);
                    this.stackChange(-1);
                }
                this.visitExpression(child, 0);
                this.addToken(68);
                this.stackChange(-1);
                break;
            }
            case 8: 
            case 73: {
                Node child;
                String name = child.getString();
                this.visitExpression(child, 0);
                child = child.getNext();
                this.visitExpression(child, 0);
                this.addStringOp(type, name);
                this.stackChange(-1);
                break;
            }
            case 155: {
                Node child;
                String name = child.getString();
                this.visitExpression(child, 0);
                child = child.getNext();
                this.visitExpression(child, 0);
                this.addStringOp(-59, name);
                this.stackChange(-1);
                break;
            }
            case 137: {
                int index = -1;
                if (this.itsInFunctionFlag && !this.itsData.itsNeedsActivation) {
                    index = this.scriptOrFn.getIndexForNameNode(node);
                }
                if (index == -1) {
                    this.addStringOp(-14, node.getString());
                    this.stackChange(1);
                    break;
                }
                this.addVarOp(55, index);
                this.stackChange(1);
                this.addToken(32);
                break;
            }
            case 39: 
            case 41: 
            case 49: {
                this.addStringOp(type, node.getString());
                this.stackChange(1);
                break;
            }
            case 106: 
            case 107: {
                Node child;
                this.visitIncDec(node, child);
                break;
            }
            case 40: {
                double num = node.getDouble();
                int inum = (int)num;
                if ((double)inum == num) {
                    if (inum == 0) {
                        this.addIcode(-51);
                        if (1.0 / num < 0.0) {
                            this.addToken(29);
                        }
                    } else if (inum == 1) {
                        this.addIcode(-52);
                    } else if ((short)inum == inum) {
                        this.addIcode(-27);
                        this.addUint16(inum & 0xFFFF);
                    } else {
                        this.addIcode(-28);
                        this.addInt(inum);
                    }
                } else {
                    int index = this.getDoubleIndex(num);
                    this.addIndexOp(40, index);
                }
                this.stackChange(1);
                break;
            }
            case 55: {
                if (this.itsData.itsNeedsActivation) {
                    Kit.codeBug();
                }
                int index = this.scriptOrFn.getIndexForNameNode(node);
                this.addVarOp(55, index);
                this.stackChange(1);
                break;
            }
            case 56: {
                Node child;
                if (this.itsData.itsNeedsActivation) {
                    Kit.codeBug();
                }
                int index = this.scriptOrFn.getIndexForNameNode(child);
                child = child.getNext();
                this.visitExpression(child, 0);
                this.addVarOp(56, index);
                break;
            }
            case 156: {
                Node child;
                if (this.itsData.itsNeedsActivation) {
                    Kit.codeBug();
                }
                int index = this.scriptOrFn.getIndexForNameNode(child);
                child = child.getNext();
                this.visitExpression(child, 0);
                this.addVarOp(156, index);
                break;
            }
            case 42: 
            case 43: 
            case 44: 
            case 45: 
            case 63: {
                this.addToken(type);
                this.stackChange(1);
                break;
            }
            case 61: 
            case 62: {
                this.addIndexOp(type, this.getLocalBlockRef(node));
                this.stackChange(1);
                break;
            }
            case 48: {
                int index = node.getExistingIntProp(4);
                this.addIndexOp(48, index);
                this.stackChange(1);
                break;
            }
            case 65: 
            case 66: {
                Node child;
                this.visitLiteral(node, child);
                break;
            }
            case 157: {
                Node child;
                this.visitArrayComprehension(node, child, child.getNext());
                break;
            }
            case 71: {
                Node child;
                this.visitExpression(child, 0);
                this.addStringOp(type, (String)node.getProp(17));
                break;
            }
            case 77: 
            case 78: 
            case 79: 
            case 80: {
                Node child;
                int memberTypeFlags = node.getIntProp(16, 0);
                int childCount = 0;
                do {
                    this.visitExpression(child, 0);
                    ++childCount;
                } while ((child = child.getNext()) != null);
                this.addIndexOp(type, memberTypeFlags);
                this.stackChange(1 - childCount);
                break;
            }
            case 146: {
                Node child;
                this.updateLineNumber(node);
                this.visitExpression(child, 0);
                this.addIcode(-53);
                this.stackChange(-1);
                int queryPC = this.iCodeTop;
                this.visitExpression(child.getNext(), 0);
                this.addBackwardGoto(-54, queryPC);
                break;
            }
            case 74: 
            case 75: 
            case 76: {
                Node child;
                this.visitExpression(child, 0);
                this.addToken(type);
                break;
            }
            case 72: {
                Node child;
                if (child != null) {
                    this.visitExpression(child, 0);
                } else {
                    this.addIcode(-50);
                    this.stackChange(1);
                }
                this.addToken(72);
                this.addUint16(node.getLineno() & 0xFFFF);
                break;
            }
            case 159: {
                Node enterWith = node.getFirstChild();
                Node with = enterWith.getNext();
                this.visitExpression(enterWith.getFirstChild(), 0);
                this.addToken(2);
                this.stackChange(-1);
                this.visitExpression(with.getFirstChild(), 0);
                this.addToken(3);
                break;
            }
            default: {
                throw this.badTree(node);
            }
        }
        if (savedStackDepth + 1 != this.stackDepth) {
            Kit.codeBug();
        }
    }

    private void generateCallFunAndThis(Node left) {
        int type = left.getType();
        switch (type) {
            case 39: {
                String name = left.getString();
                this.addStringOp(-15, name);
                this.stackChange(2);
                break;
            }
            case 33: 
            case 36: {
                Node target = left.getFirstChild();
                this.visitExpression(target, 0);
                Node id = target.getNext();
                if (type == 33) {
                    String property = id.getString();
                    this.addStringOp(-16, property);
                    this.stackChange(1);
                    break;
                }
                this.visitExpression(id, 0);
                this.addIcode(-17);
                break;
            }
            default: {
                this.visitExpression(left, 0);
                this.addIcode(-18);
                this.stackChange(1);
            }
        }
    }

    private void visitIncDec(Node node, Node child) {
        int incrDecrMask = node.getExistingIntProp(13);
        int childType = child.getType();
        switch (childType) {
            case 55: {
                if (this.itsData.itsNeedsActivation) {
                    Kit.codeBug();
                }
                int i = this.scriptOrFn.getIndexForNameNode(child);
                this.addVarOp(-7, i);
                this.addUint8(incrDecrMask);
                this.stackChange(1);
                break;
            }
            case 39: {
                String name = child.getString();
                this.addStringOp(-8, name);
                this.addUint8(incrDecrMask);
                this.stackChange(1);
                break;
            }
            case 33: {
                Node object = child.getFirstChild();
                this.visitExpression(object, 0);
                String property = object.getNext().getString();
                this.addStringOp(-9, property);
                this.addUint8(incrDecrMask);
                break;
            }
            case 36: {
                Node object = child.getFirstChild();
                this.visitExpression(object, 0);
                Node index = object.getNext();
                this.visitExpression(index, 0);
                this.addIcode(-10);
                this.addUint8(incrDecrMask);
                this.stackChange(-1);
                break;
            }
            case 67: {
                Node ref = child.getFirstChild();
                this.visitExpression(ref, 0);
                this.addIcode(-11);
                this.addUint8(incrDecrMask);
                break;
            }
            default: {
                throw this.badTree(node);
            }
        }
    }

    private void visitLiteral(Node node, Node child) {
        int count;
        int type = node.getType();
        Object[] propertyIds = null;
        if (type == 65) {
            count = 0;
            for (Node n = child; n != null; n = n.getNext()) {
                ++count;
            }
        } else if (type == 66) {
            propertyIds = (Object[])node.getProp(12);
            count = propertyIds.length;
        } else {
            throw this.badTree(node);
        }
        this.addIndexOp(-29, count);
        this.stackChange(2);
        while (child != null) {
            int childType = child.getType();
            if (childType == 151) {
                this.visitExpression(child.getFirstChild(), 0);
                this.addIcode(-57);
            } else if (childType == 152) {
                this.visitExpression(child.getFirstChild(), 0);
                this.addIcode(-58);
            } else {
                this.visitExpression(child, 0);
                this.addIcode(-30);
            }
            this.stackChange(-1);
            child = child.getNext();
        }
        if (type == 65) {
            int[] skipIndexes = (int[])node.getProp(11);
            if (skipIndexes == null) {
                this.addToken(65);
            } else {
                int index = this.literalIds.size();
                this.literalIds.add(skipIndexes);
                this.addIndexOp(-31, index);
            }
        } else {
            int index = this.literalIds.size();
            this.literalIds.add(propertyIds);
            this.addIndexOp(66, index);
        }
        this.stackChange(-1);
    }

    private void visitArrayComprehension(Node node, Node initStmt, Node expr) {
        this.visitStatement(initStmt, this.stackDepth);
        this.visitExpression(expr, 0);
    }

    private int getLocalBlockRef(Node node) {
        Node localBlock = (Node)node.getProp(3);
        return localBlock.getExistingIntProp(2);
    }

    private int getTargetLabel(Node target) {
        int label = target.labelId();
        if (label != -1) {
            return label;
        }
        label = this.labelTableTop;
        if (this.labelTable == null || label == this.labelTable.length) {
            if (this.labelTable == null) {
                this.labelTable = new int[32];
            } else {
                int[] tmp = new int[this.labelTable.length * 2];
                System.arraycopy(this.labelTable, 0, tmp, 0, label);
                this.labelTable = tmp;
            }
        }
        this.labelTableTop = label + 1;
        this.labelTable[label] = -1;
        target.labelId(label);
        return label;
    }

    private void markTargetLabel(Node target) {
        int label = this.getTargetLabel(target);
        if (this.labelTable[label] != -1) {
            Kit.codeBug();
        }
        this.labelTable[label] = this.iCodeTop;
    }

    private void addGoto(Node target, int gotoOp) {
        int targetPC;
        int label = this.getTargetLabel(target);
        if (label >= this.labelTableTop) {
            Kit.codeBug();
        }
        if ((targetPC = this.labelTable[label]) != -1) {
            this.addBackwardGoto(gotoOp, targetPC);
        } else {
            int gotoPC = this.iCodeTop;
            this.addGotoOp(gotoOp);
            int top = this.fixupTableTop;
            if (this.fixupTable == null || top == this.fixupTable.length) {
                if (this.fixupTable == null) {
                    this.fixupTable = new long[40];
                } else {
                    long[] tmp = new long[this.fixupTable.length * 2];
                    System.arraycopy(this.fixupTable, 0, tmp, 0, top);
                    this.fixupTable = tmp;
                }
            }
            this.fixupTableTop = top + 1;
            this.fixupTable[top] = (long)label << 32 | (long)gotoPC;
        }
    }

    private void fixLabelGotos() {
        for (int i = 0; i < this.fixupTableTop; ++i) {
            long fixup = this.fixupTable[i];
            int label = (int)(fixup >> 32);
            int jumpSource = (int)fixup;
            int pc = this.labelTable[label];
            if (pc == -1) {
                throw Kit.codeBug();
            }
            this.resolveGoto(jumpSource, pc);
        }
        this.fixupTableTop = 0;
    }

    private void addBackwardGoto(int gotoOp, int jumpPC) {
        int fromPC = this.iCodeTop;
        if (fromPC <= jumpPC) {
            throw Kit.codeBug();
        }
        this.addGotoOp(gotoOp);
        this.resolveGoto(fromPC, jumpPC);
    }

    private void resolveForwardGoto(int fromPC) {
        if (this.iCodeTop < fromPC + 3) {
            throw Kit.codeBug();
        }
        this.resolveGoto(fromPC, this.iCodeTop);
    }

    private void resolveGoto(int fromPC, int jumpPC) {
        int offset = jumpPC - fromPC;
        if (0 <= offset && offset <= 2) {
            throw Kit.codeBug();
        }
        int offsetSite = fromPC + 1;
        if (offset != (short)offset) {
            if (this.itsData.longJumps == null) {
                this.itsData.longJumps = new UintMap();
            }
            this.itsData.longJumps.put(offsetSite, jumpPC);
            offset = 0;
        }
        byte[] array = this.itsData.itsICode;
        array[offsetSite] = (byte)(offset >> 8);
        array[offsetSite + 1] = (byte)offset;
    }

    private void addToken(int token) {
        if (!Icode.validTokenCode(token)) {
            throw Kit.codeBug();
        }
        this.addUint8(token);
    }

    private void addIcode(int icode) {
        if (!Icode.validIcode(icode)) {
            throw Kit.codeBug();
        }
        this.addUint8(icode & 0xFF);
    }

    private void addUint8(int value) {
        if ((value & 0xFFFFFF00) != 0) {
            throw Kit.codeBug();
        }
        int top = this.iCodeTop;
        byte[] array = this.itsData.itsICode;
        if (top == array.length) {
            array = this.increaseICodeCapacity(1);
        }
        array[top] = (byte)value;
        this.iCodeTop = top + 1;
    }

    private void addUint16(int value) {
        if ((value & 0xFFFF0000) != 0) {
            throw Kit.codeBug();
        }
        int top = this.iCodeTop;
        byte[] array = this.itsData.itsICode;
        if (top + 2 > array.length) {
            array = this.increaseICodeCapacity(2);
        }
        array[top] = (byte)(value >>> 8);
        array[top + 1] = (byte)value;
        this.iCodeTop = top + 2;
    }

    private void addInt(int i) {
        int top = this.iCodeTop;
        byte[] array = this.itsData.itsICode;
        if (top + 4 > array.length) {
            array = this.increaseICodeCapacity(4);
        }
        array[top] = (byte)(i >>> 24);
        array[top + 1] = (byte)(i >>> 16);
        array[top + 2] = (byte)(i >>> 8);
        array[top + 3] = (byte)i;
        this.iCodeTop = top + 4;
    }

    private int getDoubleIndex(double num) {
        int index = this.doubleTableTop;
        if (index == 0) {
            this.itsData.itsDoubleTable = new double[64];
        } else if (this.itsData.itsDoubleTable.length == index) {
            double[] na = new double[index * 2];
            System.arraycopy(this.itsData.itsDoubleTable, 0, na, 0, index);
            this.itsData.itsDoubleTable = na;
        }
        this.itsData.itsDoubleTable[index] = num;
        this.doubleTableTop = index + 1;
        return index;
    }

    private void addGotoOp(int gotoOp) {
        int top = this.iCodeTop;
        byte[] array = this.itsData.itsICode;
        if (top + 3 > array.length) {
            array = this.increaseICodeCapacity(3);
        }
        array[top] = (byte)gotoOp;
        this.iCodeTop = top + 1 + 2;
    }

    private void addVarOp(int op, int varIndex) {
        switch (op) {
            case 156: {
                if (varIndex < 128) {
                    this.addIcode(-61);
                    this.addUint8(varIndex);
                    return;
                }
                this.addIndexOp(-60, varIndex);
                return;
            }
            case 55: 
            case 56: {
                if (varIndex < 128) {
                    this.addIcode(op == 55 ? -48 : -49);
                    this.addUint8(varIndex);
                    return;
                }
            }
            case -7: {
                this.addIndexOp(op, varIndex);
                return;
            }
        }
        throw Kit.codeBug();
    }

    private void addStringOp(int op, String str) {
        this.addStringPrefix(str);
        if (Icode.validIcode(op)) {
            this.addIcode(op);
        } else {
            this.addToken(op);
        }
    }

    private void addIndexOp(int op, int index) {
        this.addIndexPrefix(index);
        if (Icode.validIcode(op)) {
            this.addIcode(op);
        } else {
            this.addToken(op);
        }
    }

    private void addStringPrefix(String str) {
        int index = this.strings.get(str, -1);
        if (index == -1) {
            index = this.strings.size();
            this.strings.put(str, index);
        }
        if (index < 4) {
            this.addIcode(-41 - index);
        } else if (index <= 255) {
            this.addIcode(-45);
            this.addUint8(index);
        } else if (index <= 65535) {
            this.addIcode(-46);
            this.addUint16(index);
        } else {
            this.addIcode(-47);
            this.addInt(index);
        }
    }

    private void addIndexPrefix(int index) {
        if (index < 0) {
            Kit.codeBug();
        }
        if (index < 6) {
            this.addIcode(-32 - index);
        } else if (index <= 255) {
            this.addIcode(-38);
            this.addUint8(index);
        } else if (index <= 65535) {
            this.addIcode(-39);
            this.addUint16(index);
        } else {
            this.addIcode(-40);
            this.addInt(index);
        }
    }

    private void addExceptionHandler(int icodeStart, int icodeEnd, int handlerStart, boolean isFinally, int exceptionObjectLocal, int scopeLocal) {
        int top = this.exceptionTableTop;
        int[] table = this.itsData.itsExceptionTable;
        if (table == null) {
            if (top != 0) {
                Kit.codeBug();
            }
            this.itsData.itsExceptionTable = table = new int[12];
        } else if (table.length == top) {
            table = new int[table.length * 2];
            System.arraycopy(this.itsData.itsExceptionTable, 0, table, 0, top);
            this.itsData.itsExceptionTable = table;
        }
        table[top + 0] = icodeStart;
        table[top + 1] = icodeEnd;
        table[top + 2] = handlerStart;
        table[top + 3] = isFinally ? 1 : 0;
        table[top + 4] = exceptionObjectLocal;
        table[top + 5] = scopeLocal;
        this.exceptionTableTop = top + 6;
    }

    private byte[] increaseICodeCapacity(int extraSize) {
        int top = this.iCodeTop;
        int capacity = this.itsData.itsICode.length;
        if (top + extraSize <= capacity) {
            throw Kit.codeBug();
        }
        if (top + extraSize > (capacity *= 2)) {
            capacity = top + extraSize;
        }
        byte[] array = new byte[capacity];
        System.arraycopy(this.itsData.itsICode, 0, array, 0, top);
        this.itsData.itsICode = array;
        return array;
    }

    private void stackChange(int change) {
        if (change <= 0) {
            this.stackDepth += change;
        } else {
            int newDepth = this.stackDepth + change;
            if (newDepth > this.itsData.itsMaxStack) {
                this.itsData.itsMaxStack = newDepth;
            }
            this.stackDepth = newDepth;
        }
    }

    private int allocLocal() {
        int localSlot = this.localTop++;
        if (this.localTop > this.itsData.itsMaxLocals) {
            this.itsData.itsMaxLocals = this.localTop;
        }
        return localSlot;
    }

    private void releaseLocal(int localSlot) {
        --this.localTop;
        if (localSlot != this.localTop) {
            Kit.codeBug();
        }
    }
}

