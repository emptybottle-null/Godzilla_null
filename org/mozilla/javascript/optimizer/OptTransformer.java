/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript.optimizer;

import java.util.Map;
import org.mozilla.javascript.Kit;
import org.mozilla.javascript.Node;
import org.mozilla.javascript.NodeTransformer;
import org.mozilla.javascript.ObjArray;
import org.mozilla.javascript.ast.ScriptNode;
import org.mozilla.javascript.optimizer.OptFunctionNode;

class OptTransformer
extends NodeTransformer {
    private Map<String, OptFunctionNode> possibleDirectCalls;
    private ObjArray directCallTargets;

    OptTransformer(Map<String, OptFunctionNode> possibleDirectCalls, ObjArray directCallTargets) {
        this.possibleDirectCalls = possibleDirectCalls;
        this.directCallTargets = directCallTargets;
    }

    @Override
    protected void visitNew(Node node, ScriptNode tree) {
        this.detectDirectCall(node, tree);
        super.visitNew(node, tree);
    }

    @Override
    protected void visitCall(Node node, ScriptNode tree) {
        this.detectDirectCall(node, tree);
        super.visitCall(node, tree);
    }

    private void detectDirectCall(Node node, ScriptNode tree) {
        if (tree.getType() == 109) {
            Node left = node.getFirstChild();
            int argCount = 0;
            Node arg = left.getNext();
            while (arg != null) {
                arg = arg.getNext();
                ++argCount;
            }
            if (argCount == 0) {
                OptFunctionNode.get((ScriptNode)tree).itsContainsCalls0 = true;
            }
            if (this.possibleDirectCalls != null) {
                OptFunctionNode ofn;
                String targetName = null;
                if (left.getType() == 39) {
                    targetName = left.getString();
                } else if (left.getType() == 33) {
                    targetName = left.getFirstChild().getNext().getString();
                } else if (left.getType() == 34) {
                    throw Kit.codeBug();
                }
                if (targetName != null && (ofn = this.possibleDirectCalls.get(targetName)) != null && argCount == ofn.fnode.getParamCount() && !ofn.fnode.requiresActivation() && argCount <= 32) {
                    node.putProp(9, ofn);
                    if (!ofn.isTargetOfDirectCall()) {
                        int index = this.directCallTargets.size();
                        this.directCallTargets.add(ofn);
                        ofn.setDirectTargetIndex(index);
                    }
                }
            }
        }
    }
}

