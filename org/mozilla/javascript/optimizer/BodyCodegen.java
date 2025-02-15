/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript.optimizer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import org.mozilla.classfile.ClassFileWriter;
import org.mozilla.javascript.CompilerEnvirons;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Kit;
import org.mozilla.javascript.Node;
import org.mozilla.javascript.Token;
import org.mozilla.javascript.ast.FunctionNode;
import org.mozilla.javascript.ast.Jump;
import org.mozilla.javascript.ast.ScriptNode;
import org.mozilla.javascript.optimizer.Codegen;
import org.mozilla.javascript.optimizer.OptFunctionNode;
import org.mozilla.javascript.optimizer.OptRuntime;

class BodyCodegen {
    private static final int JAVASCRIPT_EXCEPTION = 0;
    private static final int EVALUATOR_EXCEPTION = 1;
    private static final int ECMAERROR_EXCEPTION = 2;
    private static final int THROWABLE_EXCEPTION = 3;
    private static final int FINALLY_EXCEPTION = 4;
    private static final int EXCEPTION_MAX = 5;
    private ExceptionManager exceptionManager = new ExceptionManager();
    static final int GENERATOR_TERMINATE = -1;
    static final int GENERATOR_START = 0;
    static final int GENERATOR_YIELD_START = 1;
    ClassFileWriter cfw;
    Codegen codegen;
    CompilerEnvirons compilerEnv;
    ScriptNode scriptOrFn;
    public int scriptOrFnIndex;
    private int savedCodeOffset;
    private OptFunctionNode fnCurrent;
    private static final int MAX_LOCALS = 1024;
    private int[] locals;
    private short firstFreeLocal;
    private short localsMax;
    private int itsLineNumber;
    private boolean hasVarsInRegs;
    private short[] varRegisters;
    private boolean inDirectCallFunction;
    private boolean itsForcedObjectParameters;
    private int enterAreaStartLabel;
    private int epilogueLabel;
    private boolean inLocalBlock;
    private short variableObjectLocal;
    private short popvLocal;
    private short contextLocal;
    private short argsLocal;
    private short operationLocal;
    private short thisObjLocal;
    private short funObjLocal;
    private short itsZeroArgArray;
    private short itsOneArgArray;
    private short generatorStateLocal;
    private boolean isGenerator;
    private int generatorSwitch;
    private int maxLocals = 0;
    private int maxStack = 0;
    private Map<Node, FinallyReturnPoint> finallys;
    private List<Node> literals;

    BodyCodegen() {
    }

    void generateBodyCode() {
        this.isGenerator = Codegen.isGenerator(this.scriptOrFn);
        this.initBodyGeneration();
        if (this.isGenerator) {
            String type = "(" + this.codegen.mainClassSignature + "Lorg/mozilla/javascript/Context;" + "Lorg/mozilla/javascript/Scriptable;" + "Ljava/lang/Object;" + "Ljava/lang/Object;I)Ljava/lang/Object;";
            this.cfw.startMethod(this.codegen.getBodyMethodName(this.scriptOrFn) + "_gen", type, (short)10);
        } else {
            this.cfw.startMethod(this.codegen.getBodyMethodName(this.scriptOrFn), this.codegen.getBodyMethodSignature(this.scriptOrFn), (short)10);
        }
        this.generatePrologue();
        Node treeTop = this.fnCurrent != null ? this.scriptOrFn.getLastChild() : this.scriptOrFn;
        this.generateStatement(treeTop);
        this.generateEpilogue();
        this.cfw.stopMethod((short)(this.localsMax + 1));
        if (this.isGenerator) {
            this.generateGenerator();
        }
        if (this.literals != null) {
            block4: for (int i = 0; i < this.literals.size(); ++i) {
                Node node = this.literals.get(i);
                int type = node.getType();
                switch (type) {
                    case 66: {
                        this.generateObjectLiteralFactory(node, i + 1);
                        continue block4;
                    }
                    case 65: {
                        this.generateArrayLiteralFactory(node, i + 1);
                        continue block4;
                    }
                    default: {
                        Kit.codeBug(Token.typeToName(type));
                    }
                }
            }
        }
    }

    private void generateGenerator() {
        this.cfw.startMethod(this.codegen.getBodyMethodName(this.scriptOrFn), this.codegen.getBodyMethodSignature(this.scriptOrFn), (short)10);
        this.initBodyGeneration();
        short s = this.firstFreeLocal;
        this.firstFreeLocal = (short)(s + 1);
        this.argsLocal = s;
        this.localsMax = this.firstFreeLocal;
        if (this.fnCurrent != null) {
            this.cfw.addALoad(this.funObjLocal);
            this.cfw.addInvoke(185, "org/mozilla/javascript/Scriptable", "getParentScope", "()Lorg/mozilla/javascript/Scriptable;");
            this.cfw.addAStore(this.variableObjectLocal);
        }
        this.cfw.addALoad(this.funObjLocal);
        this.cfw.addALoad(this.variableObjectLocal);
        this.cfw.addALoad(this.argsLocal);
        this.addScriptRuntimeInvoke("createFunctionActivation", "(Lorg/mozilla/javascript/NativeFunction;Lorg/mozilla/javascript/Scriptable;[Ljava/lang/Object;)Lorg/mozilla/javascript/Scriptable;");
        this.cfw.addAStore(this.variableObjectLocal);
        this.cfw.add(187, this.codegen.mainClassName);
        this.cfw.add(89);
        this.cfw.addALoad(this.variableObjectLocal);
        this.cfw.addALoad(this.contextLocal);
        this.cfw.addPush(this.scriptOrFnIndex);
        this.cfw.addInvoke(183, this.codegen.mainClassName, "<init>", "(Lorg/mozilla/javascript/Scriptable;Lorg/mozilla/javascript/Context;I)V");
        this.generateNestedFunctionInits();
        this.cfw.addALoad(this.variableObjectLocal);
        this.cfw.addALoad(this.thisObjLocal);
        this.cfw.addLoadConstant(this.maxLocals);
        this.cfw.addLoadConstant(this.maxStack);
        this.addOptRuntimeInvoke("createNativeGenerator", "(Lorg/mozilla/javascript/NativeFunction;Lorg/mozilla/javascript/Scriptable;Lorg/mozilla/javascript/Scriptable;II)Lorg/mozilla/javascript/Scriptable;");
        this.cfw.add(176);
        this.cfw.stopMethod((short)(this.localsMax + 1));
    }

    private void generateNestedFunctionInits() {
        int functionCount = this.scriptOrFn.getFunctionCount();
        for (int i = 0; i != functionCount; ++i) {
            OptFunctionNode ofn = OptFunctionNode.get(this.scriptOrFn, i);
            if (ofn.fnode.getFunctionType() != 1) continue;
            this.visitFunction(ofn, 1);
        }
    }

    private void initBodyGeneration() {
        this.varRegisters = null;
        if (this.scriptOrFn.getType() == 109) {
            int n;
            this.fnCurrent = OptFunctionNode.get(this.scriptOrFn);
            boolean bl = this.hasVarsInRegs = !this.fnCurrent.fnode.requiresActivation();
            if (this.hasVarsInRegs && (n = this.fnCurrent.fnode.getParamAndVarCount()) != 0) {
                this.varRegisters = new short[n];
            }
            this.inDirectCallFunction = this.fnCurrent.isTargetOfDirectCall();
            if (this.inDirectCallFunction && !this.hasVarsInRegs) {
                Codegen.badTree();
            }
        } else {
            this.fnCurrent = null;
            this.hasVarsInRegs = false;
            this.inDirectCallFunction = false;
        }
        this.locals = new int[1024];
        this.funObjLocal = 0;
        this.contextLocal = 1;
        this.variableObjectLocal = (short)2;
        this.thisObjLocal = (short)3;
        this.localsMax = (short)4;
        this.firstFreeLocal = (short)4;
        this.popvLocal = (short)-1;
        this.argsLocal = (short)-1;
        this.itsZeroArgArray = (short)-1;
        this.itsOneArgArray = (short)-1;
        this.epilogueLabel = -1;
        this.enterAreaStartLabel = -1;
        this.generatorStateLocal = (short)-1;
    }

    private void generatePrologue() {
        String debugVariableName;
        if (this.inDirectCallFunction) {
            int i;
            int directParameterCount = this.scriptOrFn.getParamCount();
            if (this.firstFreeLocal != 4) {
                Kit.codeBug();
            }
            for (i = 0; i != directParameterCount; ++i) {
                this.varRegisters[i] = this.firstFreeLocal;
                this.firstFreeLocal = (short)(this.firstFreeLocal + 3);
            }
            if (!this.fnCurrent.getParameterNumberContext()) {
                this.itsForcedObjectParameters = true;
                for (i = 0; i != directParameterCount; ++i) {
                    short reg = this.varRegisters[i];
                    this.cfw.addALoad(reg);
                    this.cfw.add(178, "java/lang/Void", "TYPE", "Ljava/lang/Class;");
                    int isObjectLabel = this.cfw.acquireLabel();
                    this.cfw.add(166, isObjectLabel);
                    this.cfw.addDLoad(reg + 1);
                    this.addDoubleWrap();
                    this.cfw.addAStore(reg);
                    this.cfw.markLabel(isObjectLabel);
                }
            }
        }
        if (this.fnCurrent != null) {
            this.cfw.addALoad(this.funObjLocal);
            this.cfw.addInvoke(185, "org/mozilla/javascript/Scriptable", "getParentScope", "()Lorg/mozilla/javascript/Scriptable;");
            this.cfw.addAStore(this.variableObjectLocal);
        }
        short s = this.firstFreeLocal;
        this.firstFreeLocal = (short)(s + 1);
        this.argsLocal = s;
        this.localsMax = this.firstFreeLocal;
        if (this.isGenerator) {
            List<Node> targets;
            short s2 = this.firstFreeLocal;
            this.firstFreeLocal = (short)(s2 + 1);
            this.operationLocal = s2;
            this.localsMax = this.firstFreeLocal;
            this.cfw.addALoad(this.thisObjLocal);
            short s3 = this.firstFreeLocal;
            this.firstFreeLocal = (short)(s3 + 1);
            this.generatorStateLocal = s3;
            this.localsMax = this.firstFreeLocal;
            this.cfw.add(192, "org/mozilla/javascript/optimizer/OptRuntime$GeneratorState");
            this.cfw.add(89);
            this.cfw.addAStore(this.generatorStateLocal);
            this.cfw.add(180, "org/mozilla/javascript/optimizer/OptRuntime$GeneratorState", "thisObj", "Lorg/mozilla/javascript/Scriptable;");
            this.cfw.addAStore(this.thisObjLocal);
            if (this.epilogueLabel == -1) {
                this.epilogueLabel = this.cfw.acquireLabel();
            }
            if ((targets = ((FunctionNode)this.scriptOrFn).getResumptionPoints()) != null) {
                this.generateGetGeneratorResumptionPoint();
                this.generatorSwitch = this.cfw.addTableSwitch(0, targets.size() + 0);
                this.generateCheckForThrowOrClose(-1, false, 0);
            }
        }
        if (this.fnCurrent == null && this.scriptOrFn.getRegexpCount() != 0) {
            this.cfw.addALoad(this.contextLocal);
            this.cfw.addInvoke(184, this.codegen.mainClassName, "_reInit", "(Lorg/mozilla/javascript/Context;)V");
        }
        if (this.compilerEnv.isGenerateObserverCount()) {
            this.saveCurrentCodeOffset();
        }
        if (this.hasVarsInRegs) {
            int parmCount = this.scriptOrFn.getParamCount();
            if (parmCount > 0 && !this.inDirectCallFunction) {
                this.cfw.addALoad(this.argsLocal);
                this.cfw.add(190);
                this.cfw.addPush(parmCount);
                int label = this.cfw.acquireLabel();
                this.cfw.add(162, label);
                this.cfw.addALoad(this.argsLocal);
                this.cfw.addPush(parmCount);
                this.addScriptRuntimeInvoke("padArguments", "([Ljava/lang/Object;I)[Ljava/lang/Object;");
                this.cfw.addAStore(this.argsLocal);
                this.cfw.markLabel(label);
            }
            int paramCount = this.fnCurrent.fnode.getParamCount();
            int varCount = this.fnCurrent.fnode.getParamAndVarCount();
            boolean[] constDeclarations = this.fnCurrent.fnode.getParamAndVarConst();
            int firstUndefVar = -1;
            for (int i = 0; i != varCount; ++i) {
                int reg = -1;
                if (i < paramCount) {
                    if (!this.inDirectCallFunction) {
                        reg = this.getNewWordLocal();
                        this.cfw.addALoad(this.argsLocal);
                        this.cfw.addPush(i);
                        this.cfw.add(50);
                        this.cfw.addAStore(reg);
                    }
                } else if (this.fnCurrent.isNumberVar(i)) {
                    reg = this.getNewWordPairLocal(constDeclarations[i]);
                    this.cfw.addPush(0.0);
                    this.cfw.addDStore(reg);
                } else {
                    reg = this.getNewWordLocal(constDeclarations[i]);
                    if (firstUndefVar == -1) {
                        Codegen.pushUndefined(this.cfw);
                        firstUndefVar = reg;
                    } else {
                        this.cfw.addALoad(firstUndefVar);
                    }
                    this.cfw.addAStore(reg);
                }
                if (reg >= 0) {
                    if (constDeclarations[i]) {
                        this.cfw.addPush(0);
                        this.cfw.addIStore(reg + (this.fnCurrent.isNumberVar(i) ? 2 : 1));
                    }
                    this.varRegisters[i] = reg;
                }
                if (!this.compilerEnv.isGenerateDebugInfo()) continue;
                String name = this.fnCurrent.fnode.getParamOrVarName(i);
                String type = this.fnCurrent.isNumberVar(i) ? "D" : "Ljava/lang/Object;";
                int startPC = this.cfw.getCurrentCodeOffset();
                if (reg < 0) {
                    reg = this.varRegisters[i];
                }
                this.cfw.addVariableDescriptor(name, type, startPC, reg);
            }
            return;
        }
        if (this.isGenerator) {
            return;
        }
        if (this.fnCurrent != null) {
            debugVariableName = "activation";
            this.cfw.addALoad(this.funObjLocal);
            this.cfw.addALoad(this.variableObjectLocal);
            this.cfw.addALoad(this.argsLocal);
            this.addScriptRuntimeInvoke("createFunctionActivation", "(Lorg/mozilla/javascript/NativeFunction;Lorg/mozilla/javascript/Scriptable;[Ljava/lang/Object;)Lorg/mozilla/javascript/Scriptable;");
            this.cfw.addAStore(this.variableObjectLocal);
            this.cfw.addALoad(this.contextLocal);
            this.cfw.addALoad(this.variableObjectLocal);
            this.addScriptRuntimeInvoke("enterActivationFunction", "(Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;)V");
        } else {
            debugVariableName = "global";
            this.cfw.addALoad(this.funObjLocal);
            this.cfw.addALoad(this.thisObjLocal);
            this.cfw.addALoad(this.contextLocal);
            this.cfw.addALoad(this.variableObjectLocal);
            this.cfw.addPush(0);
            this.addScriptRuntimeInvoke("initScript", "(Lorg/mozilla/javascript/NativeFunction;Lorg/mozilla/javascript/Scriptable;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;Z)V");
        }
        this.enterAreaStartLabel = this.cfw.acquireLabel();
        this.epilogueLabel = this.cfw.acquireLabel();
        this.cfw.markLabel(this.enterAreaStartLabel);
        this.generateNestedFunctionInits();
        if (this.compilerEnv.isGenerateDebugInfo()) {
            this.cfw.addVariableDescriptor(debugVariableName, "Lorg/mozilla/javascript/Scriptable;", this.cfw.getCurrentCodeOffset(), this.variableObjectLocal);
        }
        if (this.fnCurrent == null) {
            this.popvLocal = this.getNewWordLocal();
            Codegen.pushUndefined(this.cfw);
            this.cfw.addAStore(this.popvLocal);
            int linenum = this.scriptOrFn.getEndLineno();
            if (linenum != -1) {
                this.cfw.addLineNumberEntry((short)linenum);
            }
        } else {
            if (this.fnCurrent.itsContainsCalls0) {
                this.itsZeroArgArray = this.getNewWordLocal();
                this.cfw.add(178, "org/mozilla/javascript/ScriptRuntime", "emptyArgs", "[Ljava/lang/Object;");
                this.cfw.addAStore(this.itsZeroArgArray);
            }
            if (this.fnCurrent.itsContainsCalls1) {
                this.itsOneArgArray = this.getNewWordLocal();
                this.cfw.addPush(1);
                this.cfw.add(189, "java/lang/Object");
                this.cfw.addAStore(this.itsOneArgArray);
            }
        }
    }

    private void generateGetGeneratorResumptionPoint() {
        this.cfw.addALoad(this.generatorStateLocal);
        this.cfw.add(180, "org/mozilla/javascript/optimizer/OptRuntime$GeneratorState", "resumptionPoint", "I");
    }

    private void generateSetGeneratorResumptionPoint(int nextState) {
        this.cfw.addALoad(this.generatorStateLocal);
        this.cfw.addLoadConstant(nextState);
        this.cfw.add(181, "org/mozilla/javascript/optimizer/OptRuntime$GeneratorState", "resumptionPoint", "I");
    }

    private void generateGetGeneratorStackState() {
        this.cfw.addALoad(this.generatorStateLocal);
        this.addOptRuntimeInvoke("getGeneratorStackState", "(Ljava/lang/Object;)[Ljava/lang/Object;");
    }

    private void generateEpilogue() {
        if (this.compilerEnv.isGenerateObserverCount()) {
            this.addInstructionCount();
        }
        if (this.isGenerator) {
            Map<Node, int[]> liveLocals = ((FunctionNode)this.scriptOrFn).getLiveLocals();
            if (liveLocals != null) {
                List<Node> nodes = ((FunctionNode)this.scriptOrFn).getResumptionPoints();
                for (int i = 0; i < nodes.size(); ++i) {
                    Node node = nodes.get(i);
                    int[] live = liveLocals.get(node);
                    if (live == null) continue;
                    this.cfw.markTableSwitchCase(this.generatorSwitch, this.getNextGeneratorState(node));
                    this.generateGetGeneratorLocalsState();
                    for (int j = 0; j < live.length; ++j) {
                        this.cfw.add(89);
                        this.cfw.addLoadConstant(j);
                        this.cfw.add(50);
                        this.cfw.addAStore(live[j]);
                    }
                    this.cfw.add(87);
                    this.cfw.add(167, this.getTargetLabel(node));
                }
            }
            if (this.finallys != null) {
                for (Node n : this.finallys.keySet()) {
                    if (n.getType() != 125) continue;
                    FinallyReturnPoint ret = this.finallys.get(n);
                    this.cfw.markLabel(ret.tableLabel, (short)1);
                    int startSwitch = this.cfw.addTableSwitch(0, ret.jsrPoints.size() - 1);
                    int c = 0;
                    this.cfw.markTableSwitchDefault(startSwitch);
                    for (int i = 0; i < ret.jsrPoints.size(); ++i) {
                        this.cfw.markTableSwitchCase(startSwitch, c);
                        this.cfw.add(167, ret.jsrPoints.get(i));
                        ++c;
                    }
                }
            }
        }
        if (this.epilogueLabel != -1) {
            this.cfw.markLabel(this.epilogueLabel);
        }
        if (this.hasVarsInRegs) {
            this.cfw.add(176);
            return;
        }
        if (this.isGenerator) {
            if (((FunctionNode)this.scriptOrFn).getResumptionPoints() != null) {
                this.cfw.markTableSwitchDefault(this.generatorSwitch);
            }
            this.generateSetGeneratorResumptionPoint(-1);
            this.cfw.addALoad(this.variableObjectLocal);
            this.addOptRuntimeInvoke("throwStopIteration", "(Ljava/lang/Object;)V");
            Codegen.pushUndefined(this.cfw);
            this.cfw.add(176);
        } else if (this.fnCurrent == null) {
            this.cfw.addALoad(this.popvLocal);
            this.cfw.add(176);
        } else {
            this.generateActivationExit();
            this.cfw.add(176);
            int finallyHandler = this.cfw.acquireLabel();
            this.cfw.markHandler(finallyHandler);
            short exceptionObject = this.getNewWordLocal();
            this.cfw.addAStore(exceptionObject);
            this.generateActivationExit();
            this.cfw.addALoad(exceptionObject);
            this.releaseWordLocal(exceptionObject);
            this.cfw.add(191);
            this.cfw.addExceptionHandler(this.enterAreaStartLabel, this.epilogueLabel, finallyHandler, null);
        }
    }

    private void generateGetGeneratorLocalsState() {
        this.cfw.addALoad(this.generatorStateLocal);
        this.addOptRuntimeInvoke("getGeneratorLocalsState", "(Ljava/lang/Object;)[Ljava/lang/Object;");
    }

    private void generateActivationExit() {
        if (this.fnCurrent == null || this.hasVarsInRegs) {
            throw Kit.codeBug();
        }
        this.cfw.addALoad(this.contextLocal);
        this.addScriptRuntimeInvoke("exitActivationFunction", "(Lorg/mozilla/javascript/Context;)V");
    }

    private void generateStatement(Node node) {
        this.updateLineNumber(node);
        int type = node.getType();
        Node child = node.getFirstChild();
        switch (type) {
            case 123: 
            case 128: 
            case 129: 
            case 130: 
            case 132: 
            case 136: {
                if (this.compilerEnv.isGenerateObserverCount()) {
                    this.addInstructionCount(1);
                }
                while (child != null) {
                    this.generateStatement(child);
                    child = child.getNext();
                }
                break;
            }
            case 141: {
                boolean prevLocal = this.inLocalBlock;
                this.inLocalBlock = true;
                short local = this.getNewWordLocal();
                if (this.isGenerator) {
                    this.cfw.add(1);
                    this.cfw.addAStore(local);
                }
                node.putIntProp(2, local);
                while (child != null) {
                    this.generateStatement(child);
                    child = child.getNext();
                }
                this.releaseWordLocal(local);
                node.removeProp(2);
                this.inLocalBlock = prevLocal;
                break;
            }
            case 109: {
                int fnIndex = node.getExistingIntProp(1);
                OptFunctionNode ofn = OptFunctionNode.get(this.scriptOrFn, fnIndex);
                int t = ofn.fnode.getFunctionType();
                if (t == 3) {
                    this.visitFunction(ofn, t);
                    break;
                }
                if (t == 1) break;
                throw Codegen.badTree();
            }
            case 81: {
                this.visitTryCatchFinally((Jump)node, child);
                break;
            }
            case 57: {
                this.cfw.setStackTop((short)0);
                int local = this.getLocalBlockRegister(node);
                int scopeIndex = node.getExistingIntProp(14);
                String name = child.getString();
                child = child.getNext();
                this.generateExpression(child, node);
                if (scopeIndex == 0) {
                    this.cfw.add(1);
                } else {
                    this.cfw.addALoad(local);
                }
                this.cfw.addPush(name);
                this.cfw.addALoad(this.contextLocal);
                this.cfw.addALoad(this.variableObjectLocal);
                this.addScriptRuntimeInvoke("newCatchScope", "(Ljava/lang/Throwable;Lorg/mozilla/javascript/Scriptable;Ljava/lang/String;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;)Lorg/mozilla/javascript/Scriptable;");
                this.cfw.addAStore(local);
                break;
            }
            case 50: {
                this.generateExpression(child, node);
                if (this.compilerEnv.isGenerateObserverCount()) {
                    this.addInstructionCount();
                }
                this.generateThrowJavaScriptException();
                break;
            }
            case 51: {
                if (this.compilerEnv.isGenerateObserverCount()) {
                    this.addInstructionCount();
                }
                this.cfw.addALoad(this.getLocalBlockRegister(node));
                this.cfw.add(191);
                break;
            }
            case 4: 
            case 64: {
                if (!this.isGenerator) {
                    if (child != null) {
                        this.generateExpression(child, node);
                    } else if (type == 4) {
                        Codegen.pushUndefined(this.cfw);
                    } else {
                        if (this.popvLocal < 0) {
                            throw Codegen.badTree();
                        }
                        this.cfw.addALoad(this.popvLocal);
                    }
                }
                if (this.compilerEnv.isGenerateObserverCount()) {
                    this.addInstructionCount();
                }
                if (this.epilogueLabel == -1) {
                    if (!this.hasVarsInRegs) {
                        throw Codegen.badTree();
                    }
                    this.epilogueLabel = this.cfw.acquireLabel();
                }
                this.cfw.add(167, this.epilogueLabel);
                break;
            }
            case 114: {
                if (this.compilerEnv.isGenerateObserverCount()) {
                    this.addInstructionCount();
                }
                this.visitSwitch((Jump)node, child);
                break;
            }
            case 2: {
                this.generateExpression(child, node);
                this.cfw.addALoad(this.contextLocal);
                this.cfw.addALoad(this.variableObjectLocal);
                this.addScriptRuntimeInvoke("enterWith", "(Ljava/lang/Object;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;)Lorg/mozilla/javascript/Scriptable;");
                this.cfw.addAStore(this.variableObjectLocal);
                this.incReferenceWordLocal(this.variableObjectLocal);
                break;
            }
            case 3: {
                this.cfw.addALoad(this.variableObjectLocal);
                this.addScriptRuntimeInvoke("leaveWith", "(Lorg/mozilla/javascript/Scriptable;)Lorg/mozilla/javascript/Scriptable;");
                this.cfw.addAStore(this.variableObjectLocal);
                this.decReferenceWordLocal(this.variableObjectLocal);
                break;
            }
            case 58: 
            case 59: 
            case 60: {
                this.generateExpression(child, node);
                this.cfw.addALoad(this.contextLocal);
                this.cfw.addALoad(this.variableObjectLocal);
                int enumType = type == 58 ? 0 : (type == 59 ? 1 : 2);
                this.cfw.addPush(enumType);
                this.addScriptRuntimeInvoke("enumInit", "(Ljava/lang/Object;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;I)Ljava/lang/Object;");
                this.cfw.addAStore(this.getLocalBlockRegister(node));
                break;
            }
            case 133: {
                if (child.getType() == 56) {
                    this.visitSetVar(child, child.getFirstChild(), false);
                    break;
                }
                if (child.getType() == 156) {
                    this.visitSetConstVar(child, child.getFirstChild(), false);
                    break;
                }
                if (child.getType() == 72) {
                    this.generateYieldPoint(child, false);
                    break;
                }
                this.generateExpression(child, node);
                if (node.getIntProp(8, -1) != -1) {
                    this.cfw.add(88);
                    break;
                }
                this.cfw.add(87);
                break;
            }
            case 134: {
                this.generateExpression(child, node);
                if (this.popvLocal < 0) {
                    this.popvLocal = this.getNewWordLocal();
                }
                this.cfw.addAStore(this.popvLocal);
                break;
            }
            case 131: {
                if (this.compilerEnv.isGenerateObserverCount()) {
                    this.addInstructionCount();
                }
                int label = this.getTargetLabel(node);
                this.cfw.markLabel(label);
                if (!this.compilerEnv.isGenerateObserverCount()) break;
                this.saveCurrentCodeOffset();
                break;
            }
            case 5: 
            case 6: 
            case 7: 
            case 135: {
                if (this.compilerEnv.isGenerateObserverCount()) {
                    this.addInstructionCount();
                }
                this.visitGoto((Jump)node, type, child);
                break;
            }
            case 125: {
                if (!this.isGenerator) break;
                if (this.compilerEnv.isGenerateObserverCount()) {
                    this.saveCurrentCodeOffset();
                }
                this.cfw.setStackTop((short)1);
                short finallyRegister = this.getNewWordLocal();
                int finallyStart = this.cfw.acquireLabel();
                int finallyEnd = this.cfw.acquireLabel();
                this.cfw.markLabel(finallyStart);
                this.generateIntegerWrap();
                this.cfw.addAStore(finallyRegister);
                while (child != null) {
                    this.generateStatement(child);
                    child = child.getNext();
                }
                this.cfw.addALoad(finallyRegister);
                this.cfw.add(192, "java/lang/Integer");
                this.generateIntegerUnwrap();
                FinallyReturnPoint ret = this.finallys.get(node);
                ret.tableLabel = this.cfw.acquireLabel();
                this.cfw.add(167, ret.tableLabel);
                this.releaseWordLocal(finallyRegister);
                this.cfw.markLabel(finallyEnd);
                break;
            }
            case 160: {
                break;
            }
            default: {
                throw Codegen.badTree();
            }
        }
    }

    private void generateIntegerWrap() {
        this.cfw.addInvoke(184, "java/lang/Integer", "valueOf", "(I)Ljava/lang/Integer;");
    }

    private void generateIntegerUnwrap() {
        this.cfw.addInvoke(182, "java/lang/Integer", "intValue", "()I");
    }

    private void generateThrowJavaScriptException() {
        this.cfw.add(187, "org/mozilla/javascript/JavaScriptException");
        this.cfw.add(90);
        this.cfw.add(95);
        this.cfw.addPush(this.scriptOrFn.getSourceName());
        this.cfw.addPush(this.itsLineNumber);
        this.cfw.addInvoke(183, "org/mozilla/javascript/JavaScriptException", "<init>", "(Ljava/lang/Object;Ljava/lang/String;I)V");
        this.cfw.add(191);
    }

    private int getNextGeneratorState(Node node) {
        int nodeIndex = ((FunctionNode)this.scriptOrFn).getResumptionPoints().indexOf(node);
        return nodeIndex + 1;
    }

    private void generateExpression(Node node, Node parent) {
        int type = node.getType();
        block0 : switch (type) {
            case 138: {
                break;
            }
            case 109: {
                if (this.fnCurrent == null && parent.getType() == 136) break;
                int fnIndex = node.getExistingIntProp(1);
                OptFunctionNode ofn = OptFunctionNode.get(this.scriptOrFn, fnIndex);
                int t = ofn.fnode.getFunctionType();
                if (t != 2) {
                    throw Codegen.badTree();
                }
                this.visitFunction(ofn, t);
                break;
            }
            case 39: {
                this.cfw.addALoad(this.contextLocal);
                this.cfw.addALoad(this.variableObjectLocal);
                this.cfw.addPush(node.getString());
                this.addScriptRuntimeInvoke("name", "(Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;Ljava/lang/String;)Ljava/lang/Object;");
                break;
            }
            case 30: 
            case 38: {
                Node child;
                int specialType = node.getIntProp(10, 0);
                if (specialType == 0) {
                    OptFunctionNode target = (OptFunctionNode)node.getProp(9);
                    if (target != null) {
                        this.visitOptimizedCall(node, target, type, child);
                        break;
                    }
                    if (type == 38) {
                        this.visitStandardCall(node, child);
                        break;
                    }
                    this.visitStandardNew(node, child);
                    break;
                }
                this.visitSpecialCall(node, type, specialType, child);
                break;
            }
            case 70: {
                Node child;
                this.generateFunctionAndThisObj(child, node);
                child = child.getNext();
                this.generateCallArgArray(node, child, false);
                this.cfw.addALoad(this.contextLocal);
                this.addScriptRuntimeInvoke("callRef", "(Lorg/mozilla/javascript/Callable;Lorg/mozilla/javascript/Scriptable;[Ljava/lang/Object;Lorg/mozilla/javascript/Context;)Lorg/mozilla/javascript/Ref;");
                break;
            }
            case 40: {
                double num = node.getDouble();
                if (node.getIntProp(8, -1) != -1) {
                    this.cfw.addPush(num);
                    break;
                }
                this.codegen.pushNumberAsObject(this.cfw, num);
                break;
            }
            case 41: {
                this.cfw.addPush(node.getString());
                break;
            }
            case 43: {
                this.cfw.addALoad(this.thisObjLocal);
                break;
            }
            case 63: {
                this.cfw.add(42);
                break;
            }
            case 42: {
                this.cfw.add(1);
                break;
            }
            case 45: {
                this.cfw.add(178, "java/lang/Boolean", "TRUE", "Ljava/lang/Boolean;");
                break;
            }
            case 44: {
                this.cfw.add(178, "java/lang/Boolean", "FALSE", "Ljava/lang/Boolean;");
                break;
            }
            case 48: {
                this.cfw.addALoad(this.contextLocal);
                this.cfw.addALoad(this.variableObjectLocal);
                int i = node.getExistingIntProp(4);
                this.cfw.add(178, this.codegen.mainClassName, this.codegen.getCompiledRegexpName(this.scriptOrFn, i), "Ljava/lang/Object;");
                this.cfw.addInvoke(184, "org/mozilla/javascript/ScriptRuntime", "wrapRegExp", "(Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;Ljava/lang/Object;)Lorg/mozilla/javascript/Scriptable;");
                break;
            }
            case 89: {
                Node child;
                for (Node next = child.getNext(); next != null; next = next.getNext()) {
                    this.generateExpression(child, node);
                    this.cfw.add(87);
                    child = next;
                }
                this.generateExpression(child, node);
                break;
            }
            case 61: 
            case 62: {
                int local = this.getLocalBlockRegister(node);
                this.cfw.addALoad(local);
                if (type == 61) {
                    this.addScriptRuntimeInvoke("enumNext", "(Ljava/lang/Object;)Ljava/lang/Boolean;");
                    break;
                }
                this.cfw.addALoad(this.contextLocal);
                this.addScriptRuntimeInvoke("enumId", "(Ljava/lang/Object;Lorg/mozilla/javascript/Context;)Ljava/lang/Object;");
                break;
            }
            case 65: {
                Node child;
                this.visitArrayLiteral(node, child, false);
                break;
            }
            case 66: {
                Node child;
                this.visitObjectLiteral(node, child, false);
                break;
            }
            case 26: {
                Node child;
                int trueTarget = this.cfw.acquireLabel();
                int falseTarget = this.cfw.acquireLabel();
                int beyond = this.cfw.acquireLabel();
                this.generateIfJump(child, node, trueTarget, falseTarget);
                this.cfw.markLabel(trueTarget);
                this.cfw.add(178, "java/lang/Boolean", "FALSE", "Ljava/lang/Boolean;");
                this.cfw.add(167, beyond);
                this.cfw.markLabel(falseTarget);
                this.cfw.add(178, "java/lang/Boolean", "TRUE", "Ljava/lang/Boolean;");
                this.cfw.markLabel(beyond);
                this.cfw.adjustStackTop(-1);
                break;
            }
            case 27: {
                Node child;
                this.generateExpression(child, node);
                this.addScriptRuntimeInvoke("toInt32", "(Ljava/lang/Object;)I");
                this.cfw.addPush(-1);
                this.cfw.add(130);
                this.cfw.add(135);
                this.addDoubleWrap();
                break;
            }
            case 126: {
                Node child;
                this.generateExpression(child, node);
                this.cfw.add(87);
                Codegen.pushUndefined(this.cfw);
                break;
            }
            case 32: {
                Node child;
                this.generateExpression(child, node);
                this.addScriptRuntimeInvoke("typeof", "(Ljava/lang/Object;)Ljava/lang/String;");
                break;
            }
            case 137: {
                this.visitTypeofname(node);
                break;
            }
            case 106: 
            case 107: {
                this.visitIncDec(node);
                break;
            }
            case 104: 
            case 105: {
                Node child;
                this.generateExpression(child, node);
                this.cfw.add(89);
                this.addScriptRuntimeInvoke("toBoolean", "(Ljava/lang/Object;)Z");
                int falseTarget = this.cfw.acquireLabel();
                if (type == 105) {
                    this.cfw.add(153, falseTarget);
                } else {
                    this.cfw.add(154, falseTarget);
                }
                this.cfw.add(87);
                this.generateExpression(child.getNext(), node);
                this.cfw.markLabel(falseTarget);
                break;
            }
            case 102: {
                Node child;
                Node ifThen = child.getNext();
                Node ifElse = ifThen.getNext();
                this.generateExpression(child, node);
                this.addScriptRuntimeInvoke("toBoolean", "(Ljava/lang/Object;)Z");
                int elseTarget = this.cfw.acquireLabel();
                this.cfw.add(153, elseTarget);
                short stack = this.cfw.getStackTop();
                this.generateExpression(ifThen, node);
                int afterHook = this.cfw.acquireLabel();
                this.cfw.add(167, afterHook);
                this.cfw.markLabel(elseTarget, stack);
                this.generateExpression(ifElse, node);
                this.cfw.markLabel(afterHook);
                break;
            }
            case 21: {
                Node child;
                this.generateExpression(child, node);
                this.generateExpression(child.getNext(), node);
                switch (node.getIntProp(8, -1)) {
                    case 0: {
                        this.cfw.add(99);
                        break block0;
                    }
                    case 1: {
                        this.addOptRuntimeInvoke("add", "(DLjava/lang/Object;)Ljava/lang/Object;");
                        break block0;
                    }
                    case 2: {
                        this.addOptRuntimeInvoke("add", "(Ljava/lang/Object;D)Ljava/lang/Object;");
                        break block0;
                    }
                }
                if (child.getType() == 41) {
                    this.addScriptRuntimeInvoke("add", "(Ljava/lang/CharSequence;Ljava/lang/Object;)Ljava/lang/CharSequence;");
                    break;
                }
                if (child.getNext().getType() == 41) {
                    this.addScriptRuntimeInvoke("add", "(Ljava/lang/Object;Ljava/lang/CharSequence;)Ljava/lang/CharSequence;");
                    break;
                }
                this.cfw.addALoad(this.contextLocal);
                this.addScriptRuntimeInvoke("add", "(Ljava/lang/Object;Ljava/lang/Object;Lorg/mozilla/javascript/Context;)Ljava/lang/Object;");
                break;
            }
            case 23: {
                Node child;
                this.visitArithmetic(node, 107, child, parent);
                break;
            }
            case 22: {
                Node child;
                this.visitArithmetic(node, 103, child, parent);
                break;
            }
            case 24: 
            case 25: {
                Node child;
                this.visitArithmetic(node, type == 24 ? 111 : 115, child, parent);
                break;
            }
            case 9: 
            case 10: 
            case 11: 
            case 18: 
            case 19: 
            case 20: {
                Node child;
                this.visitBitOp(node, type, child);
                break;
            }
            case 28: 
            case 29: {
                Node child;
                this.generateExpression(child, node);
                this.addObjectToDouble();
                if (type == 29) {
                    this.cfw.add(119);
                }
                this.addDoubleWrap();
                break;
            }
            case 150: {
                Node child;
                this.generateExpression(child, node);
                this.addObjectToDouble();
                break;
            }
            case 149: {
                Node child;
                int prop = -1;
                if (child.getType() == 40) {
                    prop = child.getIntProp(8, -1);
                }
                if (prop != -1) {
                    child.removeProp(8);
                    this.generateExpression(child, node);
                    child.putIntProp(8, prop);
                    break;
                }
                this.generateExpression(child, node);
                this.addDoubleWrap();
                break;
            }
            case 14: 
            case 15: 
            case 16: 
            case 17: 
            case 52: 
            case 53: {
                Node child;
                int trueGOTO = this.cfw.acquireLabel();
                int falseGOTO = this.cfw.acquireLabel();
                this.visitIfJumpRelOp(node, child, trueGOTO, falseGOTO);
                this.addJumpedBooleanWrap(trueGOTO, falseGOTO);
                break;
            }
            case 12: 
            case 13: 
            case 46: 
            case 47: {
                Node child;
                int trueGOTO = this.cfw.acquireLabel();
                int falseGOTO = this.cfw.acquireLabel();
                this.visitIfJumpEqOp(node, child, trueGOTO, falseGOTO);
                this.addJumpedBooleanWrap(trueGOTO, falseGOTO);
                break;
            }
            case 33: 
            case 34: {
                Node child;
                this.visitGetProp(node, child);
                break;
            }
            case 36: {
                Node child;
                this.generateExpression(child, node);
                this.generateExpression(child.getNext(), node);
                this.cfw.addALoad(this.contextLocal);
                if (node.getIntProp(8, -1) != -1) {
                    this.addScriptRuntimeInvoke("getObjectIndex", "(Ljava/lang/Object;DLorg/mozilla/javascript/Context;)Ljava/lang/Object;");
                    break;
                }
                this.cfw.addALoad(this.variableObjectLocal);
                this.addScriptRuntimeInvoke("getObjectElem", "(Ljava/lang/Object;Ljava/lang/Object;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;)Ljava/lang/Object;");
                break;
            }
            case 67: {
                Node child;
                this.generateExpression(child, node);
                this.cfw.addALoad(this.contextLocal);
                this.addScriptRuntimeInvoke("refGet", "(Lorg/mozilla/javascript/Ref;Lorg/mozilla/javascript/Context;)Ljava/lang/Object;");
                break;
            }
            case 55: {
                this.visitGetVar(node);
                break;
            }
            case 56: {
                Node child;
                this.visitSetVar(node, child, true);
                break;
            }
            case 8: {
                Node child;
                this.visitSetName(node, child);
                break;
            }
            case 73: {
                Node child;
                this.visitStrictSetName(node, child);
                break;
            }
            case 155: {
                Node child;
                this.visitSetConst(node, child);
                break;
            }
            case 156: {
                Node child;
                this.visitSetConstVar(node, child, true);
                break;
            }
            case 35: 
            case 139: {
                Node child;
                this.visitSetProp(type, node, child);
                break;
            }
            case 37: 
            case 140: {
                Node child;
                this.visitSetElem(type, node, child);
                break;
            }
            case 68: 
            case 142: {
                Node child;
                this.generateExpression(child, node);
                child = child.getNext();
                if (type == 142) {
                    this.cfw.add(89);
                    this.cfw.addALoad(this.contextLocal);
                    this.addScriptRuntimeInvoke("refGet", "(Lorg/mozilla/javascript/Ref;Lorg/mozilla/javascript/Context;)Ljava/lang/Object;");
                }
                this.generateExpression(child, node);
                this.cfw.addALoad(this.contextLocal);
                this.cfw.addALoad(this.variableObjectLocal);
                this.addScriptRuntimeInvoke("refSet", "(Lorg/mozilla/javascript/Ref;Ljava/lang/Object;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;)Ljava/lang/Object;");
                break;
            }
            case 69: {
                Node child;
                this.generateExpression(child, node);
                this.cfw.addALoad(this.contextLocal);
                this.addScriptRuntimeInvoke("refDel", "(Lorg/mozilla/javascript/Ref;Lorg/mozilla/javascript/Context;)Ljava/lang/Object;");
                break;
            }
            case 31: {
                Node child;
                boolean isName = child.getType() == 49;
                this.generateExpression(child, node);
                child = child.getNext();
                this.generateExpression(child, node);
                this.cfw.addALoad(this.contextLocal);
                this.cfw.addPush(isName);
                this.addScriptRuntimeInvoke("delete", "(Ljava/lang/Object;Ljava/lang/Object;Lorg/mozilla/javascript/Context;Z)Ljava/lang/Object;");
                break;
            }
            case 49: {
                Node child;
                for (child = node.getFirstChild(); child != null; child = child.getNext()) {
                    this.generateExpression(child, node);
                }
                this.cfw.addALoad(this.contextLocal);
                this.cfw.addALoad(this.variableObjectLocal);
                this.cfw.addPush(node.getString());
                this.addScriptRuntimeInvoke("bind", "(Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;Ljava/lang/String;)Lorg/mozilla/javascript/Scriptable;");
                break;
            }
            case 54: {
                this.cfw.addALoad(this.getLocalBlockRegister(node));
                break;
            }
            case 71: {
                Node child;
                String special = (String)node.getProp(17);
                this.generateExpression(child, node);
                this.cfw.addPush(special);
                this.cfw.addALoad(this.contextLocal);
                this.cfw.addALoad(this.variableObjectLocal);
                this.addScriptRuntimeInvoke("specialRef", "(Ljava/lang/Object;Ljava/lang/String;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;)Lorg/mozilla/javascript/Ref;");
                break;
            }
            case 77: 
            case 78: 
            case 79: 
            case 80: {
                String signature;
                String methodName;
                Node child;
                int memberTypeFlags = node.getIntProp(16, 0);
                do {
                    this.generateExpression(child, node);
                } while ((child = child.getNext()) != null);
                this.cfw.addALoad(this.contextLocal);
                switch (type) {
                    case 77: {
                        methodName = "memberRef";
                        signature = "(Ljava/lang/Object;Ljava/lang/Object;Lorg/mozilla/javascript/Context;I)Lorg/mozilla/javascript/Ref;";
                        break;
                    }
                    case 78: {
                        methodName = "memberRef";
                        signature = "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Lorg/mozilla/javascript/Context;I)Lorg/mozilla/javascript/Ref;";
                        break;
                    }
                    case 79: {
                        methodName = "nameRef";
                        signature = "(Ljava/lang/Object;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;I)Lorg/mozilla/javascript/Ref;";
                        this.cfw.addALoad(this.variableObjectLocal);
                        break;
                    }
                    case 80: {
                        methodName = "nameRef";
                        signature = "(Ljava/lang/Object;Ljava/lang/Object;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;I)Lorg/mozilla/javascript/Ref;";
                        this.cfw.addALoad(this.variableObjectLocal);
                        break;
                    }
                    default: {
                        throw Kit.codeBug();
                    }
                }
                this.cfw.addPush(memberTypeFlags);
                this.addScriptRuntimeInvoke(methodName, signature);
                break;
            }
            case 146: {
                Node child;
                this.visitDotQuery(node, child);
                break;
            }
            case 75: {
                Node child;
                this.generateExpression(child, node);
                this.cfw.addALoad(this.contextLocal);
                this.addScriptRuntimeInvoke("escapeAttributeValue", "(Ljava/lang/Object;Lorg/mozilla/javascript/Context;)Ljava/lang/String;");
                break;
            }
            case 76: {
                Node child;
                this.generateExpression(child, node);
                this.cfw.addALoad(this.contextLocal);
                this.addScriptRuntimeInvoke("escapeTextValue", "(Ljava/lang/Object;Lorg/mozilla/javascript/Context;)Ljava/lang/String;");
                break;
            }
            case 74: {
                Node child;
                this.generateExpression(child, node);
                this.cfw.addALoad(this.contextLocal);
                this.addScriptRuntimeInvoke("setDefaultNamespace", "(Ljava/lang/Object;Lorg/mozilla/javascript/Context;)Ljava/lang/Object;");
                break;
            }
            case 72: {
                this.generateYieldPoint(node, true);
                break;
            }
            case 159: {
                Node child;
                Node enterWith = child;
                Node with = enterWith.getNext();
                Node leaveWith = with.getNext();
                this.generateStatement(enterWith);
                this.generateExpression(with.getFirstChild(), with);
                this.generateStatement(leaveWith);
                break;
            }
            case 157: {
                Node child;
                Node initStmt = child;
                Node expr = child.getNext();
                this.generateStatement(initStmt);
                this.generateExpression(expr, node);
                break;
            }
            default: {
                throw new RuntimeException("Unexpected node type " + type);
            }
        }
    }

    private void generateYieldPoint(Node node, boolean exprContext) {
        Node child;
        int top = this.cfw.getStackTop();
        int n = this.maxStack = this.maxStack > top ? this.maxStack : top;
        if (this.cfw.getStackTop() != 0) {
            this.generateGetGeneratorStackState();
            for (int i = 0; i < top; ++i) {
                this.cfw.add(90);
                this.cfw.add(95);
                this.cfw.addLoadConstant(i);
                this.cfw.add(95);
                this.cfw.add(83);
            }
            this.cfw.add(87);
        }
        if ((child = node.getFirstChild()) != null) {
            this.generateExpression(child, node);
        } else {
            Codegen.pushUndefined(this.cfw);
        }
        int nextState = this.getNextGeneratorState(node);
        this.generateSetGeneratorResumptionPoint(nextState);
        boolean hasLocals = this.generateSaveLocals(node);
        this.cfw.add(176);
        this.generateCheckForThrowOrClose(this.getTargetLabel(node), hasLocals, nextState);
        if (top != 0) {
            this.generateGetGeneratorStackState();
            for (int i = 0; i < top; ++i) {
                this.cfw.add(89);
                this.cfw.addLoadConstant(top - i - 1);
                this.cfw.add(50);
                this.cfw.add(95);
            }
            this.cfw.add(87);
        }
        if (exprContext) {
            this.cfw.addALoad(this.argsLocal);
        }
    }

    private void generateCheckForThrowOrClose(int label, boolean hasLocals, int nextState) {
        int throwLabel = this.cfw.acquireLabel();
        int closeLabel = this.cfw.acquireLabel();
        this.cfw.markLabel(throwLabel);
        this.cfw.addALoad(this.argsLocal);
        this.generateThrowJavaScriptException();
        this.cfw.markLabel(closeLabel);
        this.cfw.addALoad(this.argsLocal);
        this.cfw.add(192, "java/lang/Throwable");
        this.cfw.add(191);
        if (label != -1) {
            this.cfw.markLabel(label);
        }
        if (!hasLocals) {
            this.cfw.markTableSwitchCase(this.generatorSwitch, nextState);
        }
        this.cfw.addILoad(this.operationLocal);
        this.cfw.addLoadConstant(2);
        this.cfw.add(159, closeLabel);
        this.cfw.addILoad(this.operationLocal);
        this.cfw.addLoadConstant(1);
        this.cfw.add(159, throwLabel);
    }

    private void generateIfJump(Node node, Node parent, int trueLabel, int falseLabel) {
        int type = node.getType();
        Node child = node.getFirstChild();
        switch (type) {
            case 26: {
                this.generateIfJump(child, node, falseLabel, trueLabel);
                break;
            }
            case 104: 
            case 105: {
                int interLabel = this.cfw.acquireLabel();
                if (type == 105) {
                    this.generateIfJump(child, node, interLabel, falseLabel);
                } else {
                    this.generateIfJump(child, node, trueLabel, interLabel);
                }
                this.cfw.markLabel(interLabel);
                child = child.getNext();
                this.generateIfJump(child, node, trueLabel, falseLabel);
                break;
            }
            case 14: 
            case 15: 
            case 16: 
            case 17: 
            case 52: 
            case 53: {
                this.visitIfJumpRelOp(node, child, trueLabel, falseLabel);
                break;
            }
            case 12: 
            case 13: 
            case 46: 
            case 47: {
                this.visitIfJumpEqOp(node, child, trueLabel, falseLabel);
                break;
            }
            default: {
                this.generateExpression(node, parent);
                this.addScriptRuntimeInvoke("toBoolean", "(Ljava/lang/Object;)Z");
                this.cfw.add(154, trueLabel);
                this.cfw.add(167, falseLabel);
            }
        }
    }

    private void visitFunction(OptFunctionNode ofn, int functionType) {
        int fnIndex = this.codegen.getIndex(ofn.fnode);
        this.cfw.add(187, this.codegen.mainClassName);
        this.cfw.add(89);
        this.cfw.addALoad(this.variableObjectLocal);
        this.cfw.addALoad(this.contextLocal);
        this.cfw.addPush(fnIndex);
        this.cfw.addInvoke(183, this.codegen.mainClassName, "<init>", "(Lorg/mozilla/javascript/Scriptable;Lorg/mozilla/javascript/Context;I)V");
        if (functionType == 2) {
            return;
        }
        this.cfw.addPush(functionType);
        this.cfw.addALoad(this.variableObjectLocal);
        this.cfw.addALoad(this.contextLocal);
        this.addOptRuntimeInvoke("initFunction", "(Lorg/mozilla/javascript/NativeFunction;ILorg/mozilla/javascript/Scriptable;Lorg/mozilla/javascript/Context;)V");
    }

    private int getTargetLabel(Node target) {
        int labelId = target.labelId();
        if (labelId == -1) {
            labelId = this.cfw.acquireLabel();
            target.labelId(labelId);
        }
        return labelId;
    }

    private void visitGoto(Jump node, int type, Node child) {
        Node target = node.target;
        if (type == 6 || type == 7) {
            if (child == null) {
                throw Codegen.badTree();
            }
            int targetLabel = this.getTargetLabel(target);
            int fallThruLabel = this.cfw.acquireLabel();
            if (type == 6) {
                this.generateIfJump(child, node, targetLabel, fallThruLabel);
            } else {
                this.generateIfJump(child, node, fallThruLabel, targetLabel);
            }
            this.cfw.markLabel(fallThruLabel);
        } else if (type == 135) {
            if (this.isGenerator) {
                this.addGotoWithReturn(target);
            } else {
                this.inlineFinally(target);
            }
        } else {
            this.addGoto(target, 167);
        }
    }

    private void addGotoWithReturn(Node target) {
        FinallyReturnPoint ret = this.finallys.get(target);
        this.cfw.addLoadConstant(ret.jsrPoints.size());
        this.addGoto(target, 167);
        int retLabel = this.cfw.acquireLabel();
        this.cfw.markLabel(retLabel);
        ret.jsrPoints.add(retLabel);
    }

    private void generateArrayLiteralFactory(Node node, int count) {
        String methodName = this.codegen.getBodyMethodName(this.scriptOrFn) + "_literal" + count;
        this.initBodyGeneration();
        short s = this.firstFreeLocal;
        this.firstFreeLocal = (short)(s + 1);
        this.argsLocal = s;
        this.localsMax = this.firstFreeLocal;
        this.cfw.startMethod(methodName, "(Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;Lorg/mozilla/javascript/Scriptable;[Ljava/lang/Object;)Lorg/mozilla/javascript/Scriptable;", (short)2);
        this.visitArrayLiteral(node, node.getFirstChild(), true);
        this.cfw.add(176);
        this.cfw.stopMethod((short)(this.localsMax + 1));
    }

    private void generateObjectLiteralFactory(Node node, int count) {
        String methodName = this.codegen.getBodyMethodName(this.scriptOrFn) + "_literal" + count;
        this.initBodyGeneration();
        short s = this.firstFreeLocal;
        this.firstFreeLocal = (short)(s + 1);
        this.argsLocal = s;
        this.localsMax = this.firstFreeLocal;
        this.cfw.startMethod(methodName, "(Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;Lorg/mozilla/javascript/Scriptable;[Ljava/lang/Object;)Lorg/mozilla/javascript/Scriptable;", (short)2);
        this.visitObjectLiteral(node, node.getFirstChild(), true);
        this.cfw.add(176);
        this.cfw.stopMethod((short)(this.localsMax + 1));
    }

    private void visitArrayLiteral(Node node, Node child, boolean topLevel) {
        int[] skipIndexes;
        int i;
        int count = 0;
        for (Node cursor = child; cursor != null; cursor = cursor.getNext()) {
            ++count;
        }
        if (!(topLevel || count <= 10 && this.cfw.getCurrentCodeOffset() <= 30000 || this.hasVarsInRegs || this.isGenerator || this.inLocalBlock)) {
            if (this.literals == null) {
                this.literals = new LinkedList<Node>();
            }
            this.literals.add(node);
            String methodName = this.codegen.getBodyMethodName(this.scriptOrFn) + "_literal" + this.literals.size();
            this.cfw.addALoad(this.funObjLocal);
            this.cfw.addALoad(this.contextLocal);
            this.cfw.addALoad(this.variableObjectLocal);
            this.cfw.addALoad(this.thisObjLocal);
            this.cfw.addALoad(this.argsLocal);
            this.cfw.addInvoke(182, this.codegen.mainClassName, methodName, "(Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;Lorg/mozilla/javascript/Scriptable;[Ljava/lang/Object;)Lorg/mozilla/javascript/Scriptable;");
            return;
        }
        if (this.isGenerator) {
            for (i = 0; i != count; ++i) {
                this.generateExpression(child, node);
                child = child.getNext();
            }
            this.addNewObjectArray(count);
            for (i = 0; i != count; ++i) {
                this.cfw.add(90);
                this.cfw.add(95);
                this.cfw.addPush(count - i - 1);
                this.cfw.add(95);
                this.cfw.add(83);
            }
        } else {
            this.addNewObjectArray(count);
            for (i = 0; i != count; ++i) {
                this.cfw.add(89);
                this.cfw.addPush(i);
                this.generateExpression(child, node);
                this.cfw.add(83);
                child = child.getNext();
            }
        }
        if ((skipIndexes = (int[])node.getProp(11)) == null) {
            this.cfw.add(1);
            this.cfw.add(3);
        } else {
            this.cfw.addPush(OptRuntime.encodeIntArray(skipIndexes));
            this.cfw.addPush(skipIndexes.length);
        }
        this.cfw.addALoad(this.contextLocal);
        this.cfw.addALoad(this.variableObjectLocal);
        this.addOptRuntimeInvoke("newArrayLiteral", "([Ljava/lang/Object;Ljava/lang/String;ILorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;)Lorg/mozilla/javascript/Scriptable;");
    }

    private void addLoadPropertyIds(Object[] properties, int count) {
        this.addNewObjectArray(count);
        for (int i = 0; i != count; ++i) {
            this.cfw.add(89);
            this.cfw.addPush(i);
            Object id = properties[i];
            if (id instanceof String) {
                this.cfw.addPush((String)id);
            } else {
                this.cfw.addPush((Integer)id);
                this.addScriptRuntimeInvoke("wrapInt", "(I)Ljava/lang/Integer;");
            }
            this.cfw.add(83);
        }
    }

    private void addLoadPropertyValues(Node node, Node child, int count) {
        if (this.isGenerator) {
            int i;
            for (i = 0; i != count; ++i) {
                int childType = child.getType();
                if (childType == 151 || childType == 152) {
                    this.generateExpression(child.getFirstChild(), node);
                } else {
                    this.generateExpression(child, node);
                }
                child = child.getNext();
            }
            this.addNewObjectArray(count);
            for (i = 0; i != count; ++i) {
                this.cfw.add(90);
                this.cfw.add(95);
                this.cfw.addPush(count - i - 1);
                this.cfw.add(95);
                this.cfw.add(83);
            }
        } else {
            this.addNewObjectArray(count);
            Node child2 = child;
            for (int i = 0; i != count; ++i) {
                this.cfw.add(89);
                this.cfw.addPush(i);
                int childType = child2.getType();
                if (childType == 151 || childType == 152) {
                    this.generateExpression(child2.getFirstChild(), node);
                } else {
                    this.generateExpression(child2, node);
                }
                this.cfw.add(83);
                child2 = child2.getNext();
            }
        }
    }

    private void visitObjectLiteral(Node node, Node child, boolean topLevel) {
        int childType;
        int i;
        Object[] properties = (Object[])node.getProp(12);
        int count = properties.length;
        if (!(topLevel || count <= 10 && this.cfw.getCurrentCodeOffset() <= 30000 || this.hasVarsInRegs || this.isGenerator || this.inLocalBlock)) {
            if (this.literals == null) {
                this.literals = new LinkedList<Node>();
            }
            this.literals.add(node);
            String methodName = this.codegen.getBodyMethodName(this.scriptOrFn) + "_literal" + this.literals.size();
            this.cfw.addALoad(this.funObjLocal);
            this.cfw.addALoad(this.contextLocal);
            this.cfw.addALoad(this.variableObjectLocal);
            this.cfw.addALoad(this.thisObjLocal);
            this.cfw.addALoad(this.argsLocal);
            this.cfw.addInvoke(182, this.codegen.mainClassName, methodName, "(Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;Lorg/mozilla/javascript/Scriptable;[Ljava/lang/Object;)Lorg/mozilla/javascript/Scriptable;");
            return;
        }
        if (this.isGenerator) {
            this.addLoadPropertyValues(node, child, count);
            this.addLoadPropertyIds(properties, count);
            this.cfw.add(95);
        } else {
            this.addLoadPropertyIds(properties, count);
            this.addLoadPropertyValues(node, child, count);
        }
        boolean hasGetterSetters = false;
        Node child2 = child;
        for (i = 0; i != count; ++i) {
            childType = child2.getType();
            if (childType == 151 || childType == 152) {
                hasGetterSetters = true;
                break;
            }
            child2 = child2.getNext();
        }
        if (hasGetterSetters) {
            this.cfw.addPush(count);
            this.cfw.add(188, 10);
            child2 = child;
            for (i = 0; i != count; ++i) {
                this.cfw.add(89);
                this.cfw.addPush(i);
                childType = child2.getType();
                if (childType == 151) {
                    this.cfw.add(2);
                } else if (childType == 152) {
                    this.cfw.add(4);
                } else {
                    this.cfw.add(3);
                }
                this.cfw.add(79);
                child2 = child2.getNext();
            }
        } else {
            this.cfw.add(1);
        }
        this.cfw.addALoad(this.contextLocal);
        this.cfw.addALoad(this.variableObjectLocal);
        this.addScriptRuntimeInvoke("newObjectLiteral", "([Ljava/lang/Object;[Ljava/lang/Object;[ILorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;)Lorg/mozilla/javascript/Scriptable;");
    }

    private void visitSpecialCall(Node node, int type, int specialType, Node child) {
        String callSignature;
        String methodName;
        this.cfw.addALoad(this.contextLocal);
        if (type == 30) {
            this.generateExpression(child, node);
        } else {
            this.generateFunctionAndThisObj(child, node);
        }
        child = child.getNext();
        this.generateCallArgArray(node, child, false);
        if (type == 30) {
            methodName = "newObjectSpecial";
            callSignature = "(Lorg/mozilla/javascript/Context;Ljava/lang/Object;[Ljava/lang/Object;Lorg/mozilla/javascript/Scriptable;Lorg/mozilla/javascript/Scriptable;I)Ljava/lang/Object;";
            this.cfw.addALoad(this.variableObjectLocal);
            this.cfw.addALoad(this.thisObjLocal);
            this.cfw.addPush(specialType);
        } else {
            methodName = "callSpecial";
            callSignature = "(Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Callable;Lorg/mozilla/javascript/Scriptable;[Ljava/lang/Object;Lorg/mozilla/javascript/Scriptable;Lorg/mozilla/javascript/Scriptable;ILjava/lang/String;I)Ljava/lang/Object;";
            this.cfw.addALoad(this.variableObjectLocal);
            this.cfw.addALoad(this.thisObjLocal);
            this.cfw.addPush(specialType);
            String sourceName = this.scriptOrFn.getSourceName();
            this.cfw.addPush(sourceName == null ? "" : sourceName);
            this.cfw.addPush(this.itsLineNumber);
        }
        this.addOptRuntimeInvoke(methodName, callSignature);
    }

    private void visitStandardCall(Node node, Node child) {
        String signature;
        String methodName;
        if (node.getType() != 38) {
            throw Codegen.badTree();
        }
        Node firstArgChild = child.getNext();
        int childType = child.getType();
        if (firstArgChild == null) {
            if (childType == 39) {
                String name = child.getString();
                this.cfw.addPush(name);
                methodName = "callName0";
                signature = "(Ljava/lang/String;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;)Ljava/lang/Object;";
            } else if (childType == 33) {
                Node propTarget = child.getFirstChild();
                this.generateExpression(propTarget, node);
                Node id = propTarget.getNext();
                String property = id.getString();
                this.cfw.addPush(property);
                methodName = "callProp0";
                signature = "(Ljava/lang/Object;Ljava/lang/String;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;)Ljava/lang/Object;";
            } else {
                if (childType == 34) {
                    throw Kit.codeBug();
                }
                this.generateFunctionAndThisObj(child, node);
                methodName = "call0";
                signature = "(Lorg/mozilla/javascript/Callable;Lorg/mozilla/javascript/Scriptable;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;)Ljava/lang/Object;";
            }
        } else if (childType == 39) {
            String name = child.getString();
            this.generateCallArgArray(node, firstArgChild, false);
            this.cfw.addPush(name);
            methodName = "callName";
            signature = "([Ljava/lang/Object;Ljava/lang/String;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;)Ljava/lang/Object;";
        } else {
            int argCount = 0;
            for (Node arg = firstArgChild; arg != null; arg = arg.getNext()) {
                ++argCount;
            }
            this.generateFunctionAndThisObj(child, node);
            if (argCount == 1) {
                this.generateExpression(firstArgChild, node);
                methodName = "call1";
                signature = "(Lorg/mozilla/javascript/Callable;Lorg/mozilla/javascript/Scriptable;Ljava/lang/Object;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;)Ljava/lang/Object;";
            } else if (argCount == 2) {
                this.generateExpression(firstArgChild, node);
                this.generateExpression(firstArgChild.getNext(), node);
                methodName = "call2";
                signature = "(Lorg/mozilla/javascript/Callable;Lorg/mozilla/javascript/Scriptable;Ljava/lang/Object;Ljava/lang/Object;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;)Ljava/lang/Object;";
            } else {
                this.generateCallArgArray(node, firstArgChild, false);
                methodName = "callN";
                signature = "(Lorg/mozilla/javascript/Callable;Lorg/mozilla/javascript/Scriptable;[Ljava/lang/Object;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;)Ljava/lang/Object;";
            }
        }
        this.cfw.addALoad(this.contextLocal);
        this.cfw.addALoad(this.variableObjectLocal);
        this.addOptRuntimeInvoke(methodName, signature);
    }

    private void visitStandardNew(Node node, Node child) {
        if (node.getType() != 30) {
            throw Codegen.badTree();
        }
        Node firstArgChild = child.getNext();
        this.generateExpression(child, node);
        this.cfw.addALoad(this.contextLocal);
        this.cfw.addALoad(this.variableObjectLocal);
        this.generateCallArgArray(node, firstArgChild, false);
        this.addScriptRuntimeInvoke("newObject", "(Ljava/lang/Object;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;[Ljava/lang/Object;)Lorg/mozilla/javascript/Scriptable;");
    }

    private void visitOptimizedCall(Node node, OptFunctionNode target, int type, Node child) {
        Node firstArgChild = child.getNext();
        String className = this.codegen.mainClassName;
        short thisObjLocal = 0;
        if (type == 30) {
            this.generateExpression(child, node);
        } else {
            this.generateFunctionAndThisObj(child, node);
            thisObjLocal = this.getNewWordLocal();
            this.cfw.addAStore(thisObjLocal);
        }
        int beyond = this.cfw.acquireLabel();
        int regularCall = this.cfw.acquireLabel();
        this.cfw.add(89);
        this.cfw.add(193, className);
        this.cfw.add(153, regularCall);
        this.cfw.add(192, className);
        this.cfw.add(89);
        this.cfw.add(180, className, "_id", "I");
        this.cfw.addPush(this.codegen.getIndex(target.fnode));
        this.cfw.add(160, regularCall);
        this.cfw.addALoad(this.contextLocal);
        this.cfw.addALoad(this.variableObjectLocal);
        if (type == 30) {
            this.cfw.add(1);
        } else {
            this.cfw.addALoad(thisObjLocal);
        }
        for (Node argChild = firstArgChild; argChild != null; argChild = argChild.getNext()) {
            int dcp_register = this.nodeIsDirectCallParameter(argChild);
            if (dcp_register >= 0) {
                this.cfw.addALoad(dcp_register);
                this.cfw.addDLoad(dcp_register + 1);
                continue;
            }
            if (argChild.getIntProp(8, -1) == 0) {
                this.cfw.add(178, "java/lang/Void", "TYPE", "Ljava/lang/Class;");
                this.generateExpression(argChild, node);
                continue;
            }
            this.generateExpression(argChild, node);
            this.cfw.addPush(0.0);
        }
        this.cfw.add(178, "org/mozilla/javascript/ScriptRuntime", "emptyArgs", "[Ljava/lang/Object;");
        this.cfw.addInvoke(184, this.codegen.mainClassName, type == 30 ? this.codegen.getDirectCtorName(target.fnode) : this.codegen.getBodyMethodName(target.fnode), this.codegen.getBodyMethodSignature(target.fnode));
        this.cfw.add(167, beyond);
        this.cfw.markLabel(regularCall);
        this.cfw.addALoad(this.contextLocal);
        this.cfw.addALoad(this.variableObjectLocal);
        if (type != 30) {
            this.cfw.addALoad(thisObjLocal);
            this.releaseWordLocal(thisObjLocal);
        }
        this.generateCallArgArray(node, firstArgChild, true);
        if (type == 30) {
            this.addScriptRuntimeInvoke("newObject", "(Ljava/lang/Object;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;[Ljava/lang/Object;)Lorg/mozilla/javascript/Scriptable;");
        } else {
            this.cfw.addInvoke(185, "org/mozilla/javascript/Callable", "call", "(Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;Lorg/mozilla/javascript/Scriptable;[Ljava/lang/Object;)Ljava/lang/Object;");
        }
        this.cfw.markLabel(beyond);
    }

    private void generateCallArgArray(Node node, Node argChild, boolean directCall) {
        int argCount = 0;
        for (Node child = argChild; child != null; child = child.getNext()) {
            ++argCount;
        }
        if (argCount == 1 && this.itsOneArgArray >= 0) {
            this.cfw.addALoad(this.itsOneArgArray);
        } else {
            this.addNewObjectArray(argCount);
        }
        for (int i = 0; i != argCount; ++i) {
            if (!this.isGenerator) {
                this.cfw.add(89);
                this.cfw.addPush(i);
            }
            if (!directCall) {
                this.generateExpression(argChild, node);
            } else {
                int dcp_register = this.nodeIsDirectCallParameter(argChild);
                if (dcp_register >= 0) {
                    this.dcpLoadAsObject(dcp_register);
                } else {
                    this.generateExpression(argChild, node);
                    int childNumberFlag = argChild.getIntProp(8, -1);
                    if (childNumberFlag == 0) {
                        this.addDoubleWrap();
                    }
                }
            }
            if (this.isGenerator) {
                short tempLocal = this.getNewWordLocal();
                this.cfw.addAStore(tempLocal);
                this.cfw.add(192, "[Ljava/lang/Object;");
                this.cfw.add(89);
                this.cfw.addPush(i);
                this.cfw.addALoad(tempLocal);
                this.releaseWordLocal(tempLocal);
            }
            this.cfw.add(83);
            argChild = argChild.getNext();
        }
    }

    private void generateFunctionAndThisObj(Node node, Node parent) {
        int type = node.getType();
        switch (node.getType()) {
            case 34: {
                throw Kit.codeBug();
            }
            case 33: 
            case 36: {
                Node target = node.getFirstChild();
                this.generateExpression(target, node);
                Node id = target.getNext();
                if (type == 33) {
                    String property = id.getString();
                    this.cfw.addPush(property);
                    this.cfw.addALoad(this.contextLocal);
                    this.cfw.addALoad(this.variableObjectLocal);
                    this.addScriptRuntimeInvoke("getPropFunctionAndThis", "(Ljava/lang/Object;Ljava/lang/String;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;)Lorg/mozilla/javascript/Callable;");
                    break;
                }
                this.generateExpression(id, node);
                if (node.getIntProp(8, -1) != -1) {
                    this.addDoubleWrap();
                }
                this.cfw.addALoad(this.contextLocal);
                this.cfw.addALoad(this.variableObjectLocal);
                this.addScriptRuntimeInvoke("getElemFunctionAndThis", "(Ljava/lang/Object;Ljava/lang/Object;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;)Lorg/mozilla/javascript/Callable;");
                break;
            }
            case 39: {
                String name = node.getString();
                this.cfw.addPush(name);
                this.cfw.addALoad(this.contextLocal);
                this.cfw.addALoad(this.variableObjectLocal);
                this.addScriptRuntimeInvoke("getNameFunctionAndThis", "(Ljava/lang/String;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;)Lorg/mozilla/javascript/Callable;");
                break;
            }
            default: {
                this.generateExpression(node, parent);
                this.cfw.addALoad(this.contextLocal);
                this.addScriptRuntimeInvoke("getValueFunctionAndThis", "(Ljava/lang/Object;Lorg/mozilla/javascript/Context;)Lorg/mozilla/javascript/Callable;");
            }
        }
        this.cfw.addALoad(this.contextLocal);
        this.addScriptRuntimeInvoke("lastStoredScriptable", "(Lorg/mozilla/javascript/Context;)Lorg/mozilla/javascript/Scriptable;");
    }

    private void updateLineNumber(Node node) {
        this.itsLineNumber = node.getLineno();
        if (this.itsLineNumber == -1) {
            return;
        }
        this.cfw.addLineNumberEntry((short)this.itsLineNumber);
    }

    private void visitTryCatchFinally(Jump node, Node child) {
        short savedVariableObject = this.getNewWordLocal();
        this.cfw.addALoad(this.variableObjectLocal);
        this.cfw.addAStore(savedVariableObject);
        int startLabel = this.cfw.acquireLabel();
        this.cfw.markLabel(startLabel, (short)0);
        Node catchTarget = node.target;
        Node finallyTarget = node.getFinally();
        int[] handlerLabels = new int[5];
        this.exceptionManager.pushExceptionInfo(node);
        if (catchTarget != null) {
            handlerLabels[0] = this.cfw.acquireLabel();
            handlerLabels[1] = this.cfw.acquireLabel();
            handlerLabels[2] = this.cfw.acquireLabel();
            Context cx = Context.getCurrentContext();
            if (cx != null && cx.hasFeature(13)) {
                handlerLabels[3] = this.cfw.acquireLabel();
            }
        }
        if (finallyTarget != null) {
            handlerLabels[4] = this.cfw.acquireLabel();
        }
        this.exceptionManager.setHandlers(handlerLabels, startLabel);
        if (this.isGenerator && finallyTarget != null) {
            FinallyReturnPoint ret = new FinallyReturnPoint();
            if (this.finallys == null) {
                this.finallys = new HashMap<Node, FinallyReturnPoint>();
            }
            this.finallys.put(finallyTarget, ret);
            this.finallys.put(finallyTarget.getNext(), ret);
        }
        while (child != null) {
            if (child == catchTarget) {
                int catchLabel = this.getTargetLabel(catchTarget);
                this.exceptionManager.removeHandler(0, catchLabel);
                this.exceptionManager.removeHandler(1, catchLabel);
                this.exceptionManager.removeHandler(2, catchLabel);
                this.exceptionManager.removeHandler(3, catchLabel);
            }
            this.generateStatement(child);
            child = child.getNext();
        }
        int realEnd = this.cfw.acquireLabel();
        this.cfw.add(167, realEnd);
        int exceptionLocal = this.getLocalBlockRegister(node);
        if (catchTarget != null) {
            int catchLabel = catchTarget.labelId();
            this.generateCatchBlock(0, savedVariableObject, catchLabel, exceptionLocal, handlerLabels[0]);
            this.generateCatchBlock(1, savedVariableObject, catchLabel, exceptionLocal, handlerLabels[1]);
            this.generateCatchBlock(2, savedVariableObject, catchLabel, exceptionLocal, handlerLabels[2]);
            Context cx = Context.getCurrentContext();
            if (cx != null && cx.hasFeature(13)) {
                this.generateCatchBlock(3, savedVariableObject, catchLabel, exceptionLocal, handlerLabels[3]);
            }
        }
        if (finallyTarget != null) {
            int finallyHandler = this.cfw.acquireLabel();
            int finallyEnd = this.cfw.acquireLabel();
            this.cfw.markHandler(finallyHandler);
            if (!this.isGenerator) {
                this.cfw.markLabel(handlerLabels[4]);
            }
            this.cfw.addAStore(exceptionLocal);
            this.cfw.addALoad(savedVariableObject);
            this.cfw.addAStore(this.variableObjectLocal);
            int finallyLabel = finallyTarget.labelId();
            if (this.isGenerator) {
                this.addGotoWithReturn(finallyTarget);
            } else {
                this.inlineFinally(finallyTarget, handlerLabels[4], finallyEnd);
            }
            this.cfw.addALoad(exceptionLocal);
            if (this.isGenerator) {
                this.cfw.add(192, "java/lang/Throwable");
            }
            this.cfw.add(191);
            this.cfw.markLabel(finallyEnd);
            if (this.isGenerator) {
                this.cfw.addExceptionHandler(startLabel, finallyLabel, finallyHandler, null);
            }
        }
        this.releaseWordLocal(savedVariableObject);
        this.cfw.markLabel(realEnd);
        if (!this.isGenerator) {
            this.exceptionManager.popExceptionInfo();
        }
    }

    private void generateCatchBlock(int exceptionType, short savedVariableObject, int catchLabel, int exceptionLocal, int handler) {
        if (handler == 0) {
            handler = this.cfw.acquireLabel();
        }
        this.cfw.markHandler(handler);
        this.cfw.addAStore(exceptionLocal);
        this.cfw.addALoad(savedVariableObject);
        this.cfw.addAStore(this.variableObjectLocal);
        String exceptionName = this.exceptionTypeToName(exceptionType);
        this.cfw.add(167, catchLabel);
    }

    private String exceptionTypeToName(int exceptionType) {
        if (exceptionType == 0) {
            return "org/mozilla/javascript/JavaScriptException";
        }
        if (exceptionType == 1) {
            return "org/mozilla/javascript/EvaluatorException";
        }
        if (exceptionType == 2) {
            return "org/mozilla/javascript/EcmaError";
        }
        if (exceptionType == 3) {
            return "java/lang/Throwable";
        }
        if (exceptionType == 4) {
            return null;
        }
        throw Kit.codeBug();
    }

    private void inlineFinally(Node finallyTarget, int finallyStart, int finallyEnd) {
        Node fBlock = this.getFinallyAtTarget(finallyTarget);
        fBlock.resetTargets();
        this.exceptionManager.markInlineFinallyStart(fBlock, finallyStart);
        for (Node child = fBlock.getFirstChild(); child != null; child = child.getNext()) {
            this.generateStatement(child);
        }
        this.exceptionManager.markInlineFinallyEnd(fBlock, finallyEnd);
    }

    private void inlineFinally(Node finallyTarget) {
        int finallyStart = this.cfw.acquireLabel();
        int finallyEnd = this.cfw.acquireLabel();
        this.cfw.markLabel(finallyStart);
        this.inlineFinally(finallyTarget, finallyStart, finallyEnd);
        this.cfw.markLabel(finallyEnd);
    }

    private Node getFinallyAtTarget(Node node) {
        Node fBlock;
        if (node == null) {
            return null;
        }
        if (node.getType() == 125) {
            return node;
        }
        if (node != null && node.getType() == 131 && (fBlock = node.getNext()) != null && fBlock.getType() == 125) {
            return fBlock;
        }
        throw Kit.codeBug("bad finally target");
    }

    private boolean generateSaveLocals(Node node) {
        int i;
        int count = 0;
        for (int i2 = 0; i2 < this.firstFreeLocal; ++i2) {
            if (this.locals[i2] == 0) continue;
            ++count;
        }
        if (count == 0) {
            ((FunctionNode)this.scriptOrFn).addLiveLocals(node, null);
            return false;
        }
        this.maxLocals = this.maxLocals > count ? this.maxLocals : count;
        int[] ls = new int[count];
        int s = 0;
        for (i = 0; i < this.firstFreeLocal; ++i) {
            if (this.locals[i] == 0) continue;
            ls[s] = i;
            ++s;
        }
        ((FunctionNode)this.scriptOrFn).addLiveLocals(node, ls);
        this.generateGetGeneratorLocalsState();
        for (i = 0; i < count; ++i) {
            this.cfw.add(89);
            this.cfw.addLoadConstant(i);
            this.cfw.addALoad(ls[i]);
            this.cfw.add(83);
        }
        this.cfw.add(87);
        return true;
    }

    private void visitSwitch(Jump switchNode, Node child) {
        this.generateExpression(child, switchNode);
        short selector = this.getNewWordLocal();
        this.cfw.addAStore(selector);
        for (Jump caseNode = (Jump)child.getNext(); caseNode != null; caseNode = (Jump)caseNode.getNext()) {
            if (caseNode.getType() != 115) {
                throw Codegen.badTree();
            }
            Node test = caseNode.getFirstChild();
            this.generateExpression(test, caseNode);
            this.cfw.addALoad(selector);
            this.addScriptRuntimeInvoke("shallowEq", "(Ljava/lang/Object;Ljava/lang/Object;)Z");
            this.addGoto(caseNode.target, 154);
        }
        this.releaseWordLocal(selector);
    }

    private void visitTypeofname(Node node) {
        int varIndex;
        if (this.hasVarsInRegs && (varIndex = this.fnCurrent.fnode.getIndexForNameNode(node)) >= 0) {
            if (this.fnCurrent.isNumberVar(varIndex)) {
                this.cfw.addPush("number");
            } else if (this.varIsDirectCallParameter(varIndex)) {
                short dcp_register = this.varRegisters[varIndex];
                this.cfw.addALoad(dcp_register);
                this.cfw.add(178, "java/lang/Void", "TYPE", "Ljava/lang/Class;");
                int isNumberLabel = this.cfw.acquireLabel();
                this.cfw.add(165, isNumberLabel);
                short stack = this.cfw.getStackTop();
                this.cfw.addALoad(dcp_register);
                this.addScriptRuntimeInvoke("typeof", "(Ljava/lang/Object;)Ljava/lang/String;");
                int beyond = this.cfw.acquireLabel();
                this.cfw.add(167, beyond);
                this.cfw.markLabel(isNumberLabel, stack);
                this.cfw.addPush("number");
                this.cfw.markLabel(beyond);
            } else {
                this.cfw.addALoad(this.varRegisters[varIndex]);
                this.addScriptRuntimeInvoke("typeof", "(Ljava/lang/Object;)Ljava/lang/String;");
            }
            return;
        }
        this.cfw.addALoad(this.variableObjectLocal);
        this.cfw.addPush(node.getString());
        this.addScriptRuntimeInvoke("typeofName", "(Lorg/mozilla/javascript/Scriptable;Ljava/lang/String;)Ljava/lang/String;");
    }

    private void saveCurrentCodeOffset() {
        this.savedCodeOffset = this.cfw.getCurrentCodeOffset();
    }

    private void addInstructionCount() {
        int count = this.cfw.getCurrentCodeOffset() - this.savedCodeOffset;
        this.addInstructionCount(Math.max(count, 1));
    }

    private void addInstructionCount(int count) {
        this.cfw.addALoad(this.contextLocal);
        this.cfw.addPush(count);
        this.addScriptRuntimeInvoke("addInstructionCount", "(Lorg/mozilla/javascript/Context;I)V");
    }

    private void visitIncDec(Node node) {
        int incrDecrMask = node.getExistingIntProp(13);
        Node child = node.getFirstChild();
        switch (child.getType()) {
            case 55: {
                if (!this.hasVarsInRegs) {
                    Kit.codeBug();
                }
                boolean post = (incrDecrMask & 2) != 0;
                int varIndex = this.fnCurrent.getVarIndex(child);
                short reg = this.varRegisters[varIndex];
                boolean[] constDeclarations = this.fnCurrent.fnode.getParamAndVarConst();
                if (constDeclarations[varIndex]) {
                    if (node.getIntProp(8, -1) != -1) {
                        short offset = this.varIsDirectCallParameter(varIndex) ? (short)1 : 0;
                        this.cfw.addDLoad(reg + offset);
                        if (post) break;
                        this.cfw.addPush(1.0);
                        if ((incrDecrMask & 1) == 0) {
                            this.cfw.add(99);
                            break;
                        }
                        this.cfw.add(103);
                        break;
                    }
                    if (this.varIsDirectCallParameter(varIndex)) {
                        this.dcpLoadAsObject(reg);
                    } else {
                        this.cfw.addALoad(reg);
                    }
                    if (post) {
                        this.cfw.add(89);
                        this.addObjectToDouble();
                        this.cfw.add(88);
                        break;
                    }
                    this.addObjectToDouble();
                    this.cfw.addPush(1.0);
                    if ((incrDecrMask & 1) == 0) {
                        this.cfw.add(99);
                    } else {
                        this.cfw.add(103);
                    }
                    this.addDoubleWrap();
                    break;
                }
                if (node.getIntProp(8, -1) != -1) {
                    short offset = this.varIsDirectCallParameter(varIndex) ? (short)1 : 0;
                    this.cfw.addDLoad(reg + offset);
                    if (post) {
                        this.cfw.add(92);
                    }
                    this.cfw.addPush(1.0);
                    if ((incrDecrMask & 1) == 0) {
                        this.cfw.add(99);
                    } else {
                        this.cfw.add(103);
                    }
                    if (!post) {
                        this.cfw.add(92);
                    }
                    this.cfw.addDStore(reg + offset);
                    break;
                }
                if (this.varIsDirectCallParameter(varIndex)) {
                    this.dcpLoadAsObject(reg);
                } else {
                    this.cfw.addALoad(reg);
                }
                if (post) {
                    this.cfw.add(89);
                }
                this.addObjectToDouble();
                this.cfw.addPush(1.0);
                if ((incrDecrMask & 1) == 0) {
                    this.cfw.add(99);
                } else {
                    this.cfw.add(103);
                }
                this.addDoubleWrap();
                if (!post) {
                    this.cfw.add(89);
                }
                this.cfw.addAStore(reg);
                break;
            }
            case 39: {
                this.cfw.addALoad(this.variableObjectLocal);
                this.cfw.addPush(child.getString());
                this.cfw.addALoad(this.contextLocal);
                this.cfw.addPush(incrDecrMask);
                this.addScriptRuntimeInvoke("nameIncrDecr", "(Lorg/mozilla/javascript/Scriptable;Ljava/lang/String;Lorg/mozilla/javascript/Context;I)Ljava/lang/Object;");
                break;
            }
            case 34: {
                throw Kit.codeBug();
            }
            case 33: {
                Node getPropChild = child.getFirstChild();
                this.generateExpression(getPropChild, node);
                this.generateExpression(getPropChild.getNext(), node);
                this.cfw.addALoad(this.contextLocal);
                this.cfw.addALoad(this.variableObjectLocal);
                this.cfw.addPush(incrDecrMask);
                this.addScriptRuntimeInvoke("propIncrDecr", "(Ljava/lang/Object;Ljava/lang/String;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;I)Ljava/lang/Object;");
                break;
            }
            case 36: {
                Node elemChild = child.getFirstChild();
                this.generateExpression(elemChild, node);
                this.generateExpression(elemChild.getNext(), node);
                this.cfw.addALoad(this.contextLocal);
                this.cfw.addALoad(this.variableObjectLocal);
                this.cfw.addPush(incrDecrMask);
                if (elemChild.getNext().getIntProp(8, -1) != -1) {
                    this.addOptRuntimeInvoke("elemIncrDecr", "(Ljava/lang/Object;DLorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;I)Ljava/lang/Object;");
                    break;
                }
                this.addScriptRuntimeInvoke("elemIncrDecr", "(Ljava/lang/Object;Ljava/lang/Object;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;I)Ljava/lang/Object;");
                break;
            }
            case 67: {
                Node refChild = child.getFirstChild();
                this.generateExpression(refChild, node);
                this.cfw.addALoad(this.contextLocal);
                this.cfw.addALoad(this.variableObjectLocal);
                this.cfw.addPush(incrDecrMask);
                this.addScriptRuntimeInvoke("refIncrDecr", "(Lorg/mozilla/javascript/Ref;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;I)Ljava/lang/Object;");
                break;
            }
            default: {
                Codegen.badTree();
            }
        }
    }

    private static boolean isArithmeticNode(Node node) {
        int type = node.getType();
        return type == 22 || type == 25 || type == 24 || type == 23;
    }

    private void visitArithmetic(Node node, int opCode, Node child, Node parent) {
        int childNumberFlag = node.getIntProp(8, -1);
        if (childNumberFlag != -1) {
            this.generateExpression(child, node);
            this.generateExpression(child.getNext(), node);
            this.cfw.add(opCode);
        } else {
            boolean childOfArithmetic = BodyCodegen.isArithmeticNode(parent);
            this.generateExpression(child, node);
            if (!BodyCodegen.isArithmeticNode(child)) {
                this.addObjectToDouble();
            }
            this.generateExpression(child.getNext(), node);
            if (!BodyCodegen.isArithmeticNode(child.getNext())) {
                this.addObjectToDouble();
            }
            this.cfw.add(opCode);
            if (!childOfArithmetic) {
                this.addDoubleWrap();
            }
        }
    }

    private void visitBitOp(Node node, int type, Node child) {
        int childNumberFlag = node.getIntProp(8, -1);
        this.generateExpression(child, node);
        if (type == 20) {
            this.addScriptRuntimeInvoke("toUint32", "(Ljava/lang/Object;)J");
            this.generateExpression(child.getNext(), node);
            this.addScriptRuntimeInvoke("toInt32", "(Ljava/lang/Object;)I");
            this.cfw.addPush(31);
            this.cfw.add(126);
            this.cfw.add(125);
            this.cfw.add(138);
            this.addDoubleWrap();
            return;
        }
        if (childNumberFlag == -1) {
            this.addScriptRuntimeInvoke("toInt32", "(Ljava/lang/Object;)I");
            this.generateExpression(child.getNext(), node);
            this.addScriptRuntimeInvoke("toInt32", "(Ljava/lang/Object;)I");
        } else {
            this.addScriptRuntimeInvoke("toInt32", "(D)I");
            this.generateExpression(child.getNext(), node);
            this.addScriptRuntimeInvoke("toInt32", "(D)I");
        }
        switch (type) {
            case 9: {
                this.cfw.add(128);
                break;
            }
            case 10: {
                this.cfw.add(130);
                break;
            }
            case 11: {
                this.cfw.add(126);
                break;
            }
            case 19: {
                this.cfw.add(122);
                break;
            }
            case 18: {
                this.cfw.add(120);
                break;
            }
            default: {
                throw Codegen.badTree();
            }
        }
        this.cfw.add(135);
        if (childNumberFlag == -1) {
            this.addDoubleWrap();
        }
    }

    private int nodeIsDirectCallParameter(Node node) {
        int varIndex;
        if (node.getType() == 55 && this.inDirectCallFunction && !this.itsForcedObjectParameters && this.fnCurrent.isParameter(varIndex = this.fnCurrent.getVarIndex(node))) {
            return this.varRegisters[varIndex];
        }
        return -1;
    }

    private boolean varIsDirectCallParameter(int varIndex) {
        return this.fnCurrent.isParameter(varIndex) && this.inDirectCallFunction && !this.itsForcedObjectParameters;
    }

    private void genSimpleCompare(int type, int trueGOTO, int falseGOTO) {
        if (trueGOTO == -1) {
            throw Codegen.badTree();
        }
        switch (type) {
            case 15: {
                this.cfw.add(152);
                this.cfw.add(158, trueGOTO);
                break;
            }
            case 17: {
                this.cfw.add(151);
                this.cfw.add(156, trueGOTO);
                break;
            }
            case 14: {
                this.cfw.add(152);
                this.cfw.add(155, trueGOTO);
                break;
            }
            case 16: {
                this.cfw.add(151);
                this.cfw.add(157, trueGOTO);
                break;
            }
            default: {
                throw Codegen.badTree();
            }
        }
        if (falseGOTO != -1) {
            this.cfw.add(167, falseGOTO);
        }
    }

    private void visitIfJumpRelOp(Node node, Node child, int trueGOTO, int falseGOTO) {
        if (trueGOTO == -1 || falseGOTO == -1) {
            throw Codegen.badTree();
        }
        int type = node.getType();
        Node rChild = child.getNext();
        if (type == 53 || type == 52) {
            this.generateExpression(child, node);
            this.generateExpression(rChild, node);
            this.cfw.addALoad(this.contextLocal);
            this.addScriptRuntimeInvoke(type == 53 ? "instanceOf" : "in", "(Ljava/lang/Object;Ljava/lang/Object;Lorg/mozilla/javascript/Context;)Z");
            this.cfw.add(154, trueGOTO);
            this.cfw.add(167, falseGOTO);
            return;
        }
        int childNumberFlag = node.getIntProp(8, -1);
        int left_dcp_register = this.nodeIsDirectCallParameter(child);
        int right_dcp_register = this.nodeIsDirectCallParameter(rChild);
        if (childNumberFlag != -1) {
            if (childNumberFlag != 2) {
                this.generateExpression(child, node);
            } else if (left_dcp_register != -1) {
                this.dcpLoadAsNumber(left_dcp_register);
            } else {
                this.generateExpression(child, node);
                this.addObjectToDouble();
            }
            if (childNumberFlag != 1) {
                this.generateExpression(rChild, node);
            } else if (right_dcp_register != -1) {
                this.dcpLoadAsNumber(right_dcp_register);
            } else {
                this.generateExpression(rChild, node);
                this.addObjectToDouble();
            }
            this.genSimpleCompare(type, trueGOTO, falseGOTO);
        } else {
            if (left_dcp_register != -1 && right_dcp_register != -1) {
                short stack = this.cfw.getStackTop();
                int leftIsNotNumber = this.cfw.acquireLabel();
                this.cfw.addALoad(left_dcp_register);
                this.cfw.add(178, "java/lang/Void", "TYPE", "Ljava/lang/Class;");
                this.cfw.add(166, leftIsNotNumber);
                this.cfw.addDLoad(left_dcp_register + 1);
                this.dcpLoadAsNumber(right_dcp_register);
                this.genSimpleCompare(type, trueGOTO, falseGOTO);
                if (stack != this.cfw.getStackTop()) {
                    throw Codegen.badTree();
                }
                this.cfw.markLabel(leftIsNotNumber);
                int rightIsNotNumber = this.cfw.acquireLabel();
                this.cfw.addALoad(right_dcp_register);
                this.cfw.add(178, "java/lang/Void", "TYPE", "Ljava/lang/Class;");
                this.cfw.add(166, rightIsNotNumber);
                this.cfw.addALoad(left_dcp_register);
                this.addObjectToDouble();
                this.cfw.addDLoad(right_dcp_register + 1);
                this.genSimpleCompare(type, trueGOTO, falseGOTO);
                if (stack != this.cfw.getStackTop()) {
                    throw Codegen.badTree();
                }
                this.cfw.markLabel(rightIsNotNumber);
                this.cfw.addALoad(left_dcp_register);
                this.cfw.addALoad(right_dcp_register);
            } else {
                this.generateExpression(child, node);
                this.generateExpression(rChild, node);
            }
            if (type == 17 || type == 16) {
                this.cfw.add(95);
            }
            String routine = type == 14 || type == 16 ? "cmp_LT" : "cmp_LE";
            this.addScriptRuntimeInvoke(routine, "(Ljava/lang/Object;Ljava/lang/Object;)Z");
            this.cfw.add(154, trueGOTO);
            this.cfw.add(167, falseGOTO);
        }
    }

    private void visitIfJumpEqOp(Node node, Node child, int trueGOTO, int falseGOTO) {
        if (trueGOTO == -1 || falseGOTO == -1) {
            throw Codegen.badTree();
        }
        short stackInitial = this.cfw.getStackTop();
        int type = node.getType();
        Node rChild = child.getNext();
        if (child.getType() == 42 || rChild.getType() == 42) {
            if (child.getType() == 42) {
                child = rChild;
            }
            this.generateExpression(child, node);
            if (type == 46 || type == 47) {
                int testCode = type == 46 ? 198 : 199;
                this.cfw.add(testCode, trueGOTO);
            } else {
                if (type != 12) {
                    if (type != 13) {
                        throw Codegen.badTree();
                    }
                    int tmp = trueGOTO;
                    trueGOTO = falseGOTO;
                    falseGOTO = tmp;
                }
                this.cfw.add(89);
                int undefCheckLabel = this.cfw.acquireLabel();
                this.cfw.add(199, undefCheckLabel);
                short stack = this.cfw.getStackTop();
                this.cfw.add(87);
                this.cfw.add(167, trueGOTO);
                this.cfw.markLabel(undefCheckLabel, stack);
                Codegen.pushUndefined(this.cfw);
                this.cfw.add(165, trueGOTO);
            }
            this.cfw.add(167, falseGOTO);
        } else {
            int testCode;
            String name;
            Node convertChild;
            int child_dcp_register = this.nodeIsDirectCallParameter(child);
            if (child_dcp_register != -1 && rChild.getType() == 149 && (convertChild = rChild.getFirstChild()).getType() == 40) {
                this.cfw.addALoad(child_dcp_register);
                this.cfw.add(178, "java/lang/Void", "TYPE", "Ljava/lang/Class;");
                int notNumbersLabel = this.cfw.acquireLabel();
                this.cfw.add(166, notNumbersLabel);
                this.cfw.addDLoad(child_dcp_register + 1);
                this.cfw.addPush(convertChild.getDouble());
                this.cfw.add(151);
                if (type == 12) {
                    this.cfw.add(153, trueGOTO);
                } else {
                    this.cfw.add(154, trueGOTO);
                }
                this.cfw.add(167, falseGOTO);
                this.cfw.markLabel(notNumbersLabel);
            }
            this.generateExpression(child, node);
            this.generateExpression(rChild, node);
            switch (type) {
                case 12: {
                    name = "eq";
                    testCode = 154;
                    break;
                }
                case 13: {
                    name = "eq";
                    testCode = 153;
                    break;
                }
                case 46: {
                    name = "shallowEq";
                    testCode = 154;
                    break;
                }
                case 47: {
                    name = "shallowEq";
                    testCode = 153;
                    break;
                }
                default: {
                    throw Codegen.badTree();
                }
            }
            this.addScriptRuntimeInvoke(name, "(Ljava/lang/Object;Ljava/lang/Object;)Z");
            this.cfw.add(testCode, trueGOTO);
            this.cfw.add(167, falseGOTO);
        }
        if (stackInitial != this.cfw.getStackTop()) {
            throw Codegen.badTree();
        }
    }

    private void visitSetName(Node node, Node child) {
        String name = node.getFirstChild().getString();
        while (child != null) {
            this.generateExpression(child, node);
            child = child.getNext();
        }
        this.cfw.addALoad(this.contextLocal);
        this.cfw.addALoad(this.variableObjectLocal);
        this.cfw.addPush(name);
        this.addScriptRuntimeInvoke("setName", "(Lorg/mozilla/javascript/Scriptable;Ljava/lang/Object;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;Ljava/lang/String;)Ljava/lang/Object;");
    }

    private void visitStrictSetName(Node node, Node child) {
        String name = node.getFirstChild().getString();
        while (child != null) {
            this.generateExpression(child, node);
            child = child.getNext();
        }
        this.cfw.addALoad(this.contextLocal);
        this.cfw.addALoad(this.variableObjectLocal);
        this.cfw.addPush(name);
        this.addScriptRuntimeInvoke("strictSetName", "(Lorg/mozilla/javascript/Scriptable;Ljava/lang/Object;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;Ljava/lang/String;)Ljava/lang/Object;");
    }

    private void visitSetConst(Node node, Node child) {
        String name = node.getFirstChild().getString();
        while (child != null) {
            this.generateExpression(child, node);
            child = child.getNext();
        }
        this.cfw.addALoad(this.contextLocal);
        this.cfw.addPush(name);
        this.addScriptRuntimeInvoke("setConst", "(Lorg/mozilla/javascript/Scriptable;Ljava/lang/Object;Lorg/mozilla/javascript/Context;Ljava/lang/String;)Ljava/lang/Object;");
    }

    private void visitGetVar(Node node) {
        if (!this.hasVarsInRegs) {
            Kit.codeBug();
        }
        int varIndex = this.fnCurrent.getVarIndex(node);
        short reg = this.varRegisters[varIndex];
        if (this.varIsDirectCallParameter(varIndex)) {
            if (node.getIntProp(8, -1) != -1) {
                this.dcpLoadAsNumber(reg);
            } else {
                this.dcpLoadAsObject(reg);
            }
        } else if (this.fnCurrent.isNumberVar(varIndex)) {
            this.cfw.addDLoad(reg);
        } else {
            this.cfw.addALoad(reg);
        }
    }

    private void visitSetVar(Node node, Node child, boolean needValue) {
        if (!this.hasVarsInRegs) {
            Kit.codeBug();
        }
        int varIndex = this.fnCurrent.getVarIndex(node);
        this.generateExpression(child.getNext(), node);
        boolean isNumber = node.getIntProp(8, -1) != -1;
        short reg = this.varRegisters[varIndex];
        boolean[] constDeclarations = this.fnCurrent.fnode.getParamAndVarConst();
        if (constDeclarations[varIndex]) {
            if (!needValue) {
                if (isNumber) {
                    this.cfw.add(88);
                } else {
                    this.cfw.add(87);
                }
            }
        } else if (this.varIsDirectCallParameter(varIndex)) {
            if (isNumber) {
                if (needValue) {
                    this.cfw.add(92);
                }
                this.cfw.addALoad(reg);
                this.cfw.add(178, "java/lang/Void", "TYPE", "Ljava/lang/Class;");
                int isNumberLabel = this.cfw.acquireLabel();
                int beyond = this.cfw.acquireLabel();
                this.cfw.add(165, isNumberLabel);
                short stack = this.cfw.getStackTop();
                this.addDoubleWrap();
                this.cfw.addAStore(reg);
                this.cfw.add(167, beyond);
                this.cfw.markLabel(isNumberLabel, stack);
                this.cfw.addDStore(reg + 1);
                this.cfw.markLabel(beyond);
            } else {
                if (needValue) {
                    this.cfw.add(89);
                }
                this.cfw.addAStore(reg);
            }
        } else {
            boolean isNumberVar = this.fnCurrent.isNumberVar(varIndex);
            if (isNumber) {
                if (isNumberVar) {
                    this.cfw.addDStore(reg);
                    if (needValue) {
                        this.cfw.addDLoad(reg);
                    }
                } else {
                    if (needValue) {
                        this.cfw.add(92);
                    }
                    this.addDoubleWrap();
                    this.cfw.addAStore(reg);
                }
            } else {
                if (isNumberVar) {
                    Kit.codeBug();
                }
                this.cfw.addAStore(reg);
                if (needValue) {
                    this.cfw.addALoad(reg);
                }
            }
        }
    }

    private void visitSetConstVar(Node node, Node child, boolean needValue) {
        if (!this.hasVarsInRegs) {
            Kit.codeBug();
        }
        int varIndex = this.fnCurrent.getVarIndex(node);
        this.generateExpression(child.getNext(), node);
        boolean isNumber = node.getIntProp(8, -1) != -1;
        short reg = this.varRegisters[varIndex];
        int beyond = this.cfw.acquireLabel();
        int noAssign = this.cfw.acquireLabel();
        if (isNumber) {
            this.cfw.addILoad(reg + 2);
            this.cfw.add(154, noAssign);
            short stack = this.cfw.getStackTop();
            this.cfw.addPush(1);
            this.cfw.addIStore(reg + 2);
            this.cfw.addDStore(reg);
            if (needValue) {
                this.cfw.addDLoad(reg);
                this.cfw.markLabel(noAssign, stack);
            } else {
                this.cfw.add(167, beyond);
                this.cfw.markLabel(noAssign, stack);
                this.cfw.add(88);
            }
        } else {
            this.cfw.addILoad(reg + 1);
            this.cfw.add(154, noAssign);
            short stack = this.cfw.getStackTop();
            this.cfw.addPush(1);
            this.cfw.addIStore(reg + 1);
            this.cfw.addAStore(reg);
            if (needValue) {
                this.cfw.addALoad(reg);
                this.cfw.markLabel(noAssign, stack);
            } else {
                this.cfw.add(167, beyond);
                this.cfw.markLabel(noAssign, stack);
                this.cfw.add(87);
            }
        }
        this.cfw.markLabel(beyond);
    }

    private void visitGetProp(Node node, Node child) {
        this.generateExpression(child, node);
        Node nameChild = child.getNext();
        this.generateExpression(nameChild, node);
        if (node.getType() == 34) {
            this.cfw.addALoad(this.contextLocal);
            this.cfw.addALoad(this.variableObjectLocal);
            this.addScriptRuntimeInvoke("getObjectPropNoWarn", "(Ljava/lang/Object;Ljava/lang/String;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;)Ljava/lang/Object;");
            return;
        }
        int childType = child.getType();
        if (childType == 43 && nameChild.getType() == 41) {
            this.cfw.addALoad(this.contextLocal);
            this.addScriptRuntimeInvoke("getObjectProp", "(Lorg/mozilla/javascript/Scriptable;Ljava/lang/String;Lorg/mozilla/javascript/Context;)Ljava/lang/Object;");
        } else {
            this.cfw.addALoad(this.contextLocal);
            this.cfw.addALoad(this.variableObjectLocal);
            this.addScriptRuntimeInvoke("getObjectProp", "(Ljava/lang/Object;Ljava/lang/String;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;)Ljava/lang/Object;");
        }
    }

    private void visitSetProp(int type, Node node, Node child) {
        Node objectChild = child;
        this.generateExpression(child, node);
        child = child.getNext();
        if (type == 139) {
            this.cfw.add(89);
        }
        Node nameChild = child;
        this.generateExpression(child, node);
        child = child.getNext();
        if (type == 139) {
            this.cfw.add(90);
            if (objectChild.getType() == 43 && nameChild.getType() == 41) {
                this.cfw.addALoad(this.contextLocal);
                this.addScriptRuntimeInvoke("getObjectProp", "(Lorg/mozilla/javascript/Scriptable;Ljava/lang/String;Lorg/mozilla/javascript/Context;)Ljava/lang/Object;");
            } else {
                this.cfw.addALoad(this.contextLocal);
                this.cfw.addALoad(this.variableObjectLocal);
                this.addScriptRuntimeInvoke("getObjectProp", "(Ljava/lang/Object;Ljava/lang/String;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;)Ljava/lang/Object;");
            }
        }
        this.generateExpression(child, node);
        this.cfw.addALoad(this.contextLocal);
        this.cfw.addALoad(this.variableObjectLocal);
        this.addScriptRuntimeInvoke("setObjectProp", "(Ljava/lang/Object;Ljava/lang/String;Ljava/lang/Object;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;)Ljava/lang/Object;");
    }

    private void visitSetElem(int type, Node node, Node child) {
        boolean indexIsNumber;
        this.generateExpression(child, node);
        child = child.getNext();
        if (type == 140) {
            this.cfw.add(89);
        }
        this.generateExpression(child, node);
        child = child.getNext();
        boolean bl = indexIsNumber = node.getIntProp(8, -1) != -1;
        if (type == 140) {
            if (indexIsNumber) {
                this.cfw.add(93);
                this.cfw.addALoad(this.contextLocal);
                this.cfw.addALoad(this.variableObjectLocal);
                this.addScriptRuntimeInvoke("getObjectIndex", "(Ljava/lang/Object;DLorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;)Ljava/lang/Object;");
            } else {
                this.cfw.add(90);
                this.cfw.addALoad(this.contextLocal);
                this.cfw.addALoad(this.variableObjectLocal);
                this.addScriptRuntimeInvoke("getObjectElem", "(Ljava/lang/Object;Ljava/lang/Object;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;)Ljava/lang/Object;");
            }
        }
        this.generateExpression(child, node);
        this.cfw.addALoad(this.contextLocal);
        this.cfw.addALoad(this.variableObjectLocal);
        if (indexIsNumber) {
            this.addScriptRuntimeInvoke("setObjectIndex", "(Ljava/lang/Object;DLjava/lang/Object;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;)Ljava/lang/Object;");
        } else {
            this.addScriptRuntimeInvoke("setObjectElem", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;)Ljava/lang/Object;");
        }
    }

    private void visitDotQuery(Node node, Node child) {
        this.updateLineNumber(node);
        this.generateExpression(child, node);
        this.cfw.addALoad(this.variableObjectLocal);
        this.addScriptRuntimeInvoke("enterDotQuery", "(Ljava/lang/Object;Lorg/mozilla/javascript/Scriptable;)Lorg/mozilla/javascript/Scriptable;");
        this.cfw.addAStore(this.variableObjectLocal);
        this.cfw.add(1);
        int queryLoopStart = this.cfw.acquireLabel();
        this.cfw.markLabel(queryLoopStart);
        this.cfw.add(87);
        this.generateExpression(child.getNext(), node);
        this.addScriptRuntimeInvoke("toBoolean", "(Ljava/lang/Object;)Z");
        this.cfw.addALoad(this.variableObjectLocal);
        this.addScriptRuntimeInvoke("updateDotQuery", "(ZLorg/mozilla/javascript/Scriptable;)Ljava/lang/Object;");
        this.cfw.add(89);
        this.cfw.add(198, queryLoopStart);
        this.cfw.addALoad(this.variableObjectLocal);
        this.addScriptRuntimeInvoke("leaveDotQuery", "(Lorg/mozilla/javascript/Scriptable;)Lorg/mozilla/javascript/Scriptable;");
        this.cfw.addAStore(this.variableObjectLocal);
    }

    private int getLocalBlockRegister(Node node) {
        Node localBlock = (Node)node.getProp(3);
        int localSlot = localBlock.getExistingIntProp(2);
        return localSlot;
    }

    private void dcpLoadAsNumber(int dcp_register) {
        this.cfw.addALoad(dcp_register);
        this.cfw.add(178, "java/lang/Void", "TYPE", "Ljava/lang/Class;");
        int isNumberLabel = this.cfw.acquireLabel();
        this.cfw.add(165, isNumberLabel);
        short stack = this.cfw.getStackTop();
        this.cfw.addALoad(dcp_register);
        this.addObjectToDouble();
        int beyond = this.cfw.acquireLabel();
        this.cfw.add(167, beyond);
        this.cfw.markLabel(isNumberLabel, stack);
        this.cfw.addDLoad(dcp_register + 1);
        this.cfw.markLabel(beyond);
    }

    private void dcpLoadAsObject(int dcp_register) {
        this.cfw.addALoad(dcp_register);
        this.cfw.add(178, "java/lang/Void", "TYPE", "Ljava/lang/Class;");
        int isNumberLabel = this.cfw.acquireLabel();
        this.cfw.add(165, isNumberLabel);
        short stack = this.cfw.getStackTop();
        this.cfw.addALoad(dcp_register);
        int beyond = this.cfw.acquireLabel();
        this.cfw.add(167, beyond);
        this.cfw.markLabel(isNumberLabel, stack);
        this.cfw.addDLoad(dcp_register + 1);
        this.addDoubleWrap();
        this.cfw.markLabel(beyond);
    }

    private void addGoto(Node target, int jumpcode) {
        int targetLabel = this.getTargetLabel(target);
        this.cfw.add(jumpcode, targetLabel);
    }

    private void addObjectToDouble() {
        this.addScriptRuntimeInvoke("toNumber", "(Ljava/lang/Object;)D");
    }

    private void addNewObjectArray(int size) {
        if (size == 0) {
            if (this.itsZeroArgArray >= 0) {
                this.cfw.addALoad(this.itsZeroArgArray);
            } else {
                this.cfw.add(178, "org/mozilla/javascript/ScriptRuntime", "emptyArgs", "[Ljava/lang/Object;");
            }
        } else {
            this.cfw.addPush(size);
            this.cfw.add(189, "java/lang/Object");
        }
    }

    private void addScriptRuntimeInvoke(String methodName, String methodSignature) {
        this.cfw.addInvoke(184, "org.mozilla.javascript.ScriptRuntime", methodName, methodSignature);
    }

    private void addOptRuntimeInvoke(String methodName, String methodSignature) {
        this.cfw.addInvoke(184, "org/mozilla/javascript/optimizer/OptRuntime", methodName, methodSignature);
    }

    private void addJumpedBooleanWrap(int trueLabel, int falseLabel) {
        this.cfw.markLabel(falseLabel);
        int skip = this.cfw.acquireLabel();
        this.cfw.add(178, "java/lang/Boolean", "FALSE", "Ljava/lang/Boolean;");
        this.cfw.add(167, skip);
        this.cfw.markLabel(trueLabel);
        this.cfw.add(178, "java/lang/Boolean", "TRUE", "Ljava/lang/Boolean;");
        this.cfw.markLabel(skip);
        this.cfw.adjustStackTop(-1);
    }

    private void addDoubleWrap() {
        this.addOptRuntimeInvoke("wrapDouble", "(D)Ljava/lang/Double;");
    }

    private short getNewWordPairLocal(boolean isConst) {
        return this.getNewWordIntern(isConst ? 3 : 2);
    }

    private short getNewWordLocal(boolean isConst) {
        return this.getNewWordIntern(isConst ? 2 : 1);
    }

    private short getNewWordLocal() {
        return this.getNewWordIntern(1);
    }

    private short getNewWordIntern(int count) {
        int i;
        assert (count >= 1 && count <= 3);
        int[] locals = this.locals;
        short result = -1;
        if (count > 1) {
            i = this.firstFreeLocal;
            block0: while (i + count <= 1024) {
                for (int j = 0; j < count; ++j) {
                    if (locals[i + j] == 0) continue;
                    i += j + 1;
                    continue block0;
                }
                result = (short)i;
                break;
            }
        } else {
            result = this.firstFreeLocal;
        }
        if (result != -1) {
            locals[result] = 1;
            if (count > 1) {
                locals[result + 1] = 1;
            }
            if (count > 2) {
                locals[result + 2] = 1;
            }
            if (result == this.firstFreeLocal) {
                for (i = result + count; i < 1024; ++i) {
                    if (locals[i] != 0) continue;
                    this.firstFreeLocal = (short)i;
                    if (this.localsMax < this.firstFreeLocal) {
                        this.localsMax = this.firstFreeLocal;
                    }
                    return result;
                }
            } else {
                return result;
            }
        }
        throw Context.reportRuntimeError("Program too complex (out of locals)");
    }

    private void incReferenceWordLocal(short local) {
        short s = local;
        this.locals[s] = this.locals[s] + 1;
    }

    private void decReferenceWordLocal(short local) {
        short s = local;
        this.locals[s] = this.locals[s] - 1;
    }

    private void releaseWordLocal(short local) {
        if (local < this.firstFreeLocal) {
            this.firstFreeLocal = local;
        }
        this.locals[local] = 0;
    }

    static class FinallyReturnPoint {
        public List<Integer> jsrPoints = new ArrayList<Integer>();
        public int tableLabel = 0;

        FinallyReturnPoint() {
        }
    }

    private class ExceptionManager {
        private LinkedList<ExceptionInfo> exceptionInfo = new LinkedList();

        ExceptionManager() {
        }

        void pushExceptionInfo(Jump node) {
            Node fBlock = BodyCodegen.this.getFinallyAtTarget(node.getFinally());
            ExceptionInfo ei = new ExceptionInfo(node, fBlock);
            this.exceptionInfo.add(ei);
        }

        void addHandler(int exceptionType, int handlerLabel, int startLabel) {
            ExceptionInfo top = this.getTop();
            top.handlerLabels[exceptionType] = handlerLabel;
            top.exceptionStarts[exceptionType] = startLabel;
        }

        void setHandlers(int[] handlerLabels, int startLabel) {
            ExceptionInfo top = this.getTop();
            for (int i = 0; i < handlerLabels.length; ++i) {
                if (handlerLabels[i] == 0) continue;
                this.addHandler(i, handlerLabels[i], startLabel);
            }
        }

        int removeHandler(int exceptionType, int endLabel) {
            ExceptionInfo top = this.getTop();
            if (top.handlerLabels[exceptionType] != 0) {
                int handlerLabel = top.handlerLabels[exceptionType];
                this.endCatch(top, exceptionType, endLabel);
                top.handlerLabels[exceptionType] = 0;
                return handlerLabel;
            }
            return 0;
        }

        void popExceptionInfo() {
            this.exceptionInfo.removeLast();
        }

        void markInlineFinallyStart(Node finallyBlock, int finallyStart) {
            ListIterator<ExceptionInfo> iter = this.exceptionInfo.listIterator(this.exceptionInfo.size());
            while (iter.hasPrevious()) {
                ExceptionInfo ei = iter.previous();
                for (int i = 0; i < 5; ++i) {
                    if (ei.handlerLabels[i] == 0 || ei.currentFinally != null) continue;
                    this.endCatch(ei, i, finallyStart);
                    ei.exceptionStarts[i] = 0;
                    ei.currentFinally = finallyBlock;
                }
                if (ei.finallyBlock != finallyBlock) continue;
                break;
            }
        }

        void markInlineFinallyEnd(Node finallyBlock, int finallyEnd) {
            ListIterator<ExceptionInfo> iter = this.exceptionInfo.listIterator(this.exceptionInfo.size());
            while (iter.hasPrevious()) {
                ExceptionInfo ei = iter.previous();
                for (int i = 0; i < 5; ++i) {
                    if (ei.handlerLabels[i] == 0 || ei.currentFinally != finallyBlock) continue;
                    ei.exceptionStarts[i] = finallyEnd;
                    ei.currentFinally = null;
                }
                if (ei.finallyBlock != finallyBlock) continue;
                break;
            }
        }

        private void endCatch(ExceptionInfo ei, int exceptionType, int catchEnd) {
            int catchEndPC;
            if (ei.exceptionStarts[exceptionType] == 0) {
                throw new IllegalStateException("bad exception start");
            }
            int currentStart = ei.exceptionStarts[exceptionType];
            int currentStartPC = BodyCodegen.this.cfw.getLabelPC(currentStart);
            if (currentStartPC != (catchEndPC = BodyCodegen.this.cfw.getLabelPC(catchEnd))) {
                BodyCodegen.this.cfw.addExceptionHandler(ei.exceptionStarts[exceptionType], catchEnd, ei.handlerLabels[exceptionType], BodyCodegen.this.exceptionTypeToName(exceptionType));
            }
        }

        private ExceptionInfo getTop() {
            return this.exceptionInfo.getLast();
        }

        private class ExceptionInfo {
            Jump node;
            Node finallyBlock;
            int[] handlerLabels;
            int[] exceptionStarts;
            Node currentFinally;

            ExceptionInfo(Jump node, Node finallyBlock) {
                this.node = node;
                this.finallyBlock = finallyBlock;
                this.handlerLabels = new int[5];
                this.exceptionStarts = new int[5];
                this.currentFinally = null;
            }
        }
    }
}

