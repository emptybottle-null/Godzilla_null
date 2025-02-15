/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript.optimizer;

import java.util.BitSet;
import java.util.HashMap;
import org.mozilla.javascript.Node;
import org.mozilla.javascript.ObjArray;
import org.mozilla.javascript.ObjToIntMap;
import org.mozilla.javascript.ast.Jump;
import org.mozilla.javascript.optimizer.OptFunctionNode;

class Block {
    private Block[] itsSuccessors;
    private Block[] itsPredecessors;
    private int itsStartNodeIndex;
    private int itsEndNodeIndex;
    private int itsBlockID;
    private BitSet itsLiveOnEntrySet;
    private BitSet itsLiveOnExitSet;
    private BitSet itsUseBeforeDefSet;
    private BitSet itsNotDefSet;
    static final boolean DEBUG = false;
    private static int debug_blockCount;

    Block(int startNodeIndex, int endNodeIndex) {
        this.itsStartNodeIndex = startNodeIndex;
        this.itsEndNodeIndex = endNodeIndex;
    }

    static void runFlowAnalyzes(OptFunctionNode fn, Node[] statementNodes) {
        int i;
        int paramCount = fn.fnode.getParamCount();
        int varCount = fn.fnode.getParamAndVarCount();
        int[] varTypes = new int[varCount];
        for (i = 0; i != paramCount; ++i) {
            varTypes[i] = 3;
        }
        for (i = paramCount; i != varCount; ++i) {
            varTypes[i] = 0;
        }
        Block[] theBlocks = Block.buildBlocks(statementNodes);
        Block.reachingDefDataFlow(fn, statementNodes, theBlocks, varTypes);
        Block.typeFlow(fn, statementNodes, theBlocks, varTypes);
        for (int i2 = paramCount; i2 != varCount; ++i2) {
            if (varTypes[i2] != 1) continue;
            fn.setIsNumberVar(i2);
        }
    }

    private static Block[] buildBlocks(Node[] statementNodes) {
        FatBlock fb;
        int i;
        HashMap<Node, FatBlock> theTargetBlocks = new HashMap<Node, FatBlock>();
        ObjArray theBlocks = new ObjArray();
        int beginNodeIndex = 0;
        block4: for (i = 0; i < statementNodes.length; ++i) {
            switch (statementNodes[i].getType()) {
                case 131: {
                    if (i == beginNodeIndex) continue block4;
                    fb = Block.newFatBlock(beginNodeIndex, i - 1);
                    if (statementNodes[beginNodeIndex].getType() == 131) {
                        theTargetBlocks.put(statementNodes[beginNodeIndex], fb);
                    }
                    theBlocks.add(fb);
                    beginNodeIndex = i;
                    continue block4;
                }
                case 5: 
                case 6: 
                case 7: {
                    fb = Block.newFatBlock(beginNodeIndex, i);
                    if (statementNodes[beginNodeIndex].getType() == 131) {
                        theTargetBlocks.put(statementNodes[beginNodeIndex], fb);
                    }
                    theBlocks.add(fb);
                    beginNodeIndex = i + 1;
                }
            }
        }
        if (beginNodeIndex != statementNodes.length) {
            FatBlock fb2 = Block.newFatBlock(beginNodeIndex, statementNodes.length - 1);
            if (statementNodes[beginNodeIndex].getType() == 131) {
                theTargetBlocks.put(statementNodes[beginNodeIndex], fb2);
            }
            theBlocks.add(fb2);
        }
        for (i = 0; i < theBlocks.size(); ++i) {
            fb = (FatBlock)theBlocks.get(i);
            Node blockEndNode = statementNodes[fb.realBlock.itsEndNodeIndex];
            int blockEndNodeType = blockEndNode.getType();
            if (blockEndNodeType != 5 && i < theBlocks.size() - 1) {
                FatBlock fallThruTarget = (FatBlock)theBlocks.get(i + 1);
                fb.addSuccessor(fallThruTarget);
                fallThruTarget.addPredecessor(fb);
            }
            if (blockEndNodeType != 7 && blockEndNodeType != 6 && blockEndNodeType != 5) continue;
            Node target = ((Jump)blockEndNode).target;
            FatBlock branchTargetBlock = (FatBlock)theTargetBlocks.get(target);
            target.putProp(6, branchTargetBlock.realBlock);
            fb.addSuccessor(branchTargetBlock);
            branchTargetBlock.addPredecessor(fb);
        }
        Block[] result = new Block[theBlocks.size()];
        for (int i2 = 0; i2 < theBlocks.size(); ++i2) {
            FatBlock fb3 = (FatBlock)theBlocks.get(i2);
            Block b = fb3.realBlock;
            b.itsSuccessors = fb3.getSuccessors();
            b.itsPredecessors = fb3.getPredecessors();
            b.itsBlockID = i2;
            result[i2] = b;
        }
        return result;
    }

    private static FatBlock newFatBlock(int startNodeIndex, int endNodeIndex) {
        FatBlock fb = new FatBlock();
        fb.realBlock = new Block(startNodeIndex, endNodeIndex);
        return fb;
    }

    private static String toString(Block[] blockList, Node[] statementNodes) {
        return null;
    }

    private static void reachingDefDataFlow(OptFunctionNode fn, Node[] statementNodes, Block[] theBlocks, int[] varTypes) {
        for (int i = 0; i < theBlocks.length; ++i) {
            theBlocks[i].initLiveOnEntrySets(fn, statementNodes);
        }
        boolean[] visit = new boolean[theBlocks.length];
        boolean[] doneOnce = new boolean[theBlocks.length];
        int vIndex = theBlocks.length - 1;
        boolean needRescan = false;
        visit[vIndex] = true;
        while (true) {
            if (visit[vIndex] || !doneOnce[vIndex]) {
                Block[] pred;
                doneOnce[vIndex] = true;
                visit[vIndex] = false;
                if (theBlocks[vIndex].doReachedUseDataFlow() && (pred = theBlocks[vIndex].itsPredecessors) != null) {
                    for (int i = 0; i < pred.length; ++i) {
                        int index = pred[i].itsBlockID;
                        visit[index] = true;
                        needRescan |= index > vIndex;
                    }
                }
            }
            if (vIndex == 0) {
                if (!needRescan) break;
                vIndex = theBlocks.length - 1;
                needRescan = false;
                continue;
            }
            --vIndex;
        }
        theBlocks[0].markAnyTypeVariables(varTypes);
    }

    private static void typeFlow(OptFunctionNode fn, Node[] statementNodes, Block[] theBlocks, int[] varTypes) {
        boolean[] visit = new boolean[theBlocks.length];
        boolean[] doneOnce = new boolean[theBlocks.length];
        int vIndex = 0;
        boolean needRescan = false;
        visit[vIndex] = true;
        while (true) {
            if (visit[vIndex] || !doneOnce[vIndex]) {
                Block[] succ;
                doneOnce[vIndex] = true;
                visit[vIndex] = false;
                if (theBlocks[vIndex].doTypeFlow(fn, statementNodes, varTypes) && (succ = theBlocks[vIndex].itsSuccessors) != null) {
                    for (int i = 0; i < succ.length; ++i) {
                        int index = succ[i].itsBlockID;
                        visit[index] = true;
                        needRescan |= index < vIndex;
                    }
                }
            }
            if (vIndex == theBlocks.length - 1) {
                if (!needRescan) break;
                vIndex = 0;
                needRescan = false;
                continue;
            }
            ++vIndex;
        }
    }

    private static boolean assignType(int[] varTypes, int index, int type) {
        int prev = varTypes[index];
        int n = index;
        int n2 = varTypes[n] | type;
        varTypes[n] = n2;
        return prev != n2;
    }

    private void markAnyTypeVariables(int[] varTypes) {
        for (int i = 0; i != varTypes.length; ++i) {
            if (!this.itsLiveOnEntrySet.get(i)) continue;
            Block.assignType(varTypes, i, 3);
        }
    }

    private void lookForVariableAccess(OptFunctionNode fn, Node n) {
        switch (n.getType()) {
            case 137: {
                int varIndex = fn.fnode.getIndexForNameNode(n);
                if (varIndex <= -1 || this.itsNotDefSet.get(varIndex)) break;
                this.itsUseBeforeDefSet.set(varIndex);
                break;
            }
            case 106: 
            case 107: {
                Node child = n.getFirstChild();
                if (child.getType() == 55) {
                    int varIndex = fn.getVarIndex(child);
                    if (!this.itsNotDefSet.get(varIndex)) {
                        this.itsUseBeforeDefSet.set(varIndex);
                    }
                    this.itsNotDefSet.set(varIndex);
                    break;
                }
                this.lookForVariableAccess(fn, child);
                break;
            }
            case 56: 
            case 156: {
                Node lhs = n.getFirstChild();
                Node rhs = lhs.getNext();
                this.lookForVariableAccess(fn, rhs);
                this.itsNotDefSet.set(fn.getVarIndex(n));
                break;
            }
            case 55: {
                int varIndex = fn.getVarIndex(n);
                if (this.itsNotDefSet.get(varIndex)) break;
                this.itsUseBeforeDefSet.set(varIndex);
                break;
            }
            default: {
                for (Node child = n.getFirstChild(); child != null; child = child.getNext()) {
                    this.lookForVariableAccess(fn, child);
                }
            }
        }
    }

    private void initLiveOnEntrySets(OptFunctionNode fn, Node[] statementNodes) {
        int listLength = fn.getVarCount();
        this.itsUseBeforeDefSet = new BitSet(listLength);
        this.itsNotDefSet = new BitSet(listLength);
        this.itsLiveOnEntrySet = new BitSet(listLength);
        this.itsLiveOnExitSet = new BitSet(listLength);
        for (int i = this.itsStartNodeIndex; i <= this.itsEndNodeIndex; ++i) {
            Node n = statementNodes[i];
            this.lookForVariableAccess(fn, n);
        }
        this.itsNotDefSet.flip(0, listLength);
    }

    private boolean doReachedUseDataFlow() {
        this.itsLiveOnExitSet.clear();
        if (this.itsSuccessors != null) {
            for (int i = 0; i < this.itsSuccessors.length; ++i) {
                this.itsLiveOnExitSet.or(this.itsSuccessors[i].itsLiveOnEntrySet);
            }
        }
        return this.updateEntrySet(this.itsLiveOnEntrySet, this.itsLiveOnExitSet, this.itsUseBeforeDefSet, this.itsNotDefSet);
    }

    private boolean updateEntrySet(BitSet entrySet, BitSet exitSet, BitSet useBeforeDef, BitSet notDef) {
        int card = entrySet.cardinality();
        entrySet.or(exitSet);
        entrySet.and(notDef);
        entrySet.or(useBeforeDef);
        return entrySet.cardinality() != card;
    }

    private static int findExpressionType(OptFunctionNode fn, Node n, int[] varTypes) {
        switch (n.getType()) {
            case 40: {
                return 1;
            }
            case 30: 
            case 38: 
            case 70: {
                return 3;
            }
            case 33: 
            case 36: 
            case 39: 
            case 43: {
                return 3;
            }
            case 55: {
                return varTypes[fn.getVarIndex(n)];
            }
            case 9: 
            case 10: 
            case 11: 
            case 18: 
            case 19: 
            case 20: 
            case 22: 
            case 23: 
            case 24: 
            case 25: 
            case 27: 
            case 28: 
            case 29: 
            case 106: 
            case 107: {
                return 1;
            }
            case 126: {
                return 3;
            }
            case 12: 
            case 13: 
            case 14: 
            case 15: 
            case 16: 
            case 17: 
            case 26: 
            case 31: 
            case 44: 
            case 45: 
            case 46: 
            case 47: 
            case 52: 
            case 53: 
            case 69: {
                return 3;
            }
            case 32: 
            case 41: 
            case 137: {
                return 3;
            }
            case 42: 
            case 48: 
            case 65: 
            case 66: 
            case 157: {
                return 3;
            }
            case 21: {
                Node child = n.getFirstChild();
                int lType = Block.findExpressionType(fn, child, varTypes);
                int rType = Block.findExpressionType(fn, child.getNext(), varTypes);
                return lType | rType;
            }
            case 102: {
                Node ifTrue = n.getFirstChild().getNext();
                Node ifFalse = ifTrue.getNext();
                int ifTrueType = Block.findExpressionType(fn, ifTrue, varTypes);
                int ifFalseType = Block.findExpressionType(fn, ifFalse, varTypes);
                return ifTrueType | ifFalseType;
            }
            case 8: 
            case 35: 
            case 37: 
            case 56: 
            case 89: 
            case 156: {
                return Block.findExpressionType(fn, n.getLastChild(), varTypes);
            }
            case 104: 
            case 105: {
                Node child = n.getFirstChild();
                int lType = Block.findExpressionType(fn, child, varTypes);
                int rType = Block.findExpressionType(fn, child.getNext(), varTypes);
                return lType | rType;
            }
        }
        return 3;
    }

    private static boolean findDefPoints(OptFunctionNode fn, Node n, int[] varTypes) {
        Node first;
        boolean result = false;
        for (Node next = first = n.getFirstChild(); next != null; next = next.getNext()) {
            result |= Block.findDefPoints(fn, next, varTypes);
        }
        switch (n.getType()) {
            case 106: 
            case 107: {
                if (first.getType() != 55) break;
                int i = fn.getVarIndex(first);
                if (fn.fnode.getParamAndVarConst()[i]) break;
                result |= Block.assignType(varTypes, i, 1);
                break;
            }
            case 56: 
            case 156: {
                Node rValue = first.getNext();
                int theType = Block.findExpressionType(fn, rValue, varTypes);
                int i = fn.getVarIndex(n);
                if (n.getType() == 56 && fn.fnode.getParamAndVarConst()[i]) break;
                result |= Block.assignType(varTypes, i, theType);
                break;
            }
        }
        return result;
    }

    private boolean doTypeFlow(OptFunctionNode fn, Node[] statementNodes, int[] varTypes) {
        boolean changed = false;
        for (int i = this.itsStartNodeIndex; i <= this.itsEndNodeIndex; ++i) {
            Node n = statementNodes[i];
            if (n == null) continue;
            changed |= Block.findDefPoints(fn, n, varTypes);
        }
        return changed;
    }

    private void printLiveOnEntrySet(OptFunctionNode fn) {
    }

    private static class FatBlock {
        private ObjToIntMap successors = new ObjToIntMap();
        private ObjToIntMap predecessors = new ObjToIntMap();
        Block realBlock;

        private FatBlock() {
        }

        private static Block[] reduceToArray(ObjToIntMap map) {
            Block[] result = null;
            if (!map.isEmpty()) {
                result = new Block[map.size()];
                int i = 0;
                ObjToIntMap.Iterator iter = map.newIterator();
                iter.start();
                while (!iter.done()) {
                    FatBlock fb = (FatBlock)iter.getKey();
                    result[i++] = fb.realBlock;
                    iter.next();
                }
            }
            return result;
        }

        void addSuccessor(FatBlock b) {
            this.successors.put(b, 0);
        }

        void addPredecessor(FatBlock b) {
            this.predecessors.put(b, 0);
        }

        Block[] getSuccessors() {
            return FatBlock.reduceToArray(this.successors);
        }

        Block[] getPredecessors() {
            return FatBlock.reduceToArray(this.predecessors);
        }
    }
}

