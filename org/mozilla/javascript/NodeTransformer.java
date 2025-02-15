/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript;

import java.util.ArrayList;
import java.util.List;
import org.mozilla.javascript.Kit;
import org.mozilla.javascript.Node;
import org.mozilla.javascript.ObjArray;
import org.mozilla.javascript.ScriptRuntime;
import org.mozilla.javascript.ast.AstRoot;
import org.mozilla.javascript.ast.FunctionNode;
import org.mozilla.javascript.ast.Jump;
import org.mozilla.javascript.ast.Scope;
import org.mozilla.javascript.ast.ScriptNode;

public class NodeTransformer {
    private ObjArray loops;
    private ObjArray loopEnds;
    private boolean hasFinally;

    public final void transform(ScriptNode tree) {
        this.transformCompilationUnit(tree);
        for (int i = 0; i != tree.getFunctionCount(); ++i) {
            FunctionNode fn = tree.getFunctionNode(i);
            this.transform(fn);
        }
    }

    private void transformCompilationUnit(ScriptNode tree) {
        this.loops = new ObjArray();
        this.loopEnds = new ObjArray();
        this.hasFinally = false;
        boolean createScopeObjects = tree.getType() != 109 || ((FunctionNode)tree).requiresActivation();
        tree.flattenSymbolTable(!createScopeObjects);
        boolean inStrictMode = tree instanceof AstRoot && ((AstRoot)tree).isInStrictMode();
        this.transformCompilationUnit_r(tree, tree, tree, createScopeObjects, inStrictMode);
    }

    private void transformCompilationUnit_r(ScriptNode tree, Node parent, Scope scope, boolean createScopeObjects, boolean inStrictMode) {
        Node node = null;
        block17: while (true) {
            Scope newScope;
            Node previous = null;
            if (node == null) {
                node = parent.getFirstChild();
            } else {
                previous = node;
                node = node.getNext();
            }
            if (node == null) break;
            int type = node.getType();
            if (createScopeObjects && (type == 129 || type == 132 || type == 157) && node instanceof Scope && (newScope = (Scope)node).getSymbolTable() != null) {
                Node let = new Node(type == 157 ? 158 : 153);
                Node innerLet = new Node(153);
                let.addChildToBack(innerLet);
                for (String name : newScope.getSymbolTable().keySet()) {
                    innerLet.addChildToBack(Node.newString(39, name));
                }
                newScope.setSymbolTable(null);
                Node oldNode = node;
                node = NodeTransformer.replaceCurrent(parent, previous, node, let);
                type = node.getType();
                let.addChildToBack(oldNode);
            }
            switch (type) {
                case 114: 
                case 130: 
                case 132: {
                    this.loops.push(node);
                    this.loopEnds.push(((Jump)node).target);
                    break;
                }
                case 123: {
                    this.loops.push(node);
                    Node leave = node.getNext();
                    if (leave.getType() != 3) {
                        Kit.codeBug();
                    }
                    this.loopEnds.push(leave);
                    break;
                }
                case 81: {
                    Jump jump = (Jump)node;
                    Node finallytarget = jump.getFinally();
                    if (finallytarget == null) break;
                    this.hasFinally = true;
                    this.loops.push(node);
                    this.loopEnds.push(finallytarget);
                    break;
                }
                case 3: 
                case 131: {
                    if (this.loopEnds.isEmpty() || this.loopEnds.peek() != node) break;
                    this.loopEnds.pop();
                    this.loops.pop();
                    break;
                }
                case 72: {
                    ((FunctionNode)tree).addResumptionPoint(node);
                    break;
                }
                case 4: {
                    Node n;
                    boolean isGenerator;
                    boolean bl = isGenerator = tree.getType() == 109 && ((FunctionNode)tree).isGenerator();
                    if (isGenerator) {
                        node.putIntProp(20, 1);
                    }
                    if (!this.hasFinally) break;
                    Node unwindBlock = null;
                    for (int i = this.loops.size() - 1; i >= 0; --i) {
                        Node unwind;
                        n = (Node)this.loops.get(i);
                        int elemtype = n.getType();
                        if (elemtype != 81 && elemtype != 123) continue;
                        if (elemtype == 81) {
                            Node jsrtarget;
                            Jump jsrnode = new Jump(135);
                            jsrnode.target = jsrtarget = ((Jump)n).getFinally();
                            unwind = jsrnode;
                        } else {
                            unwind = new Node(3);
                        }
                        if (unwindBlock == null) {
                            unwindBlock = new Node(129, node.getLineno());
                        }
                        unwindBlock.addChildToBack(unwind);
                    }
                    if (unwindBlock == null) break;
                    Node returnNode = node;
                    Node returnExpr = returnNode.getFirstChild();
                    node = NodeTransformer.replaceCurrent(parent, previous, node, unwindBlock);
                    if (returnExpr == null || isGenerator) {
                        unwindBlock.addChildToBack(returnNode);
                        continue block17;
                    }
                    Node store = new Node(134, returnExpr);
                    unwindBlock.addChildToFront(store);
                    returnNode = new Node(64);
                    unwindBlock.addChildToBack(returnNode);
                    this.transformCompilationUnit_r(tree, store, scope, createScopeObjects, inStrictMode);
                    continue block17;
                }
                case 120: 
                case 121: {
                    Node n;
                    Jump jump = (Jump)node;
                    Jump jumpStatement = jump.getJumpStatement();
                    if (jumpStatement == null) {
                        Kit.codeBug();
                    }
                    int i = this.loops.size();
                    while (true) {
                        if (i == 0) {
                            throw Kit.codeBug();
                        }
                        if ((n = (Node)this.loops.get(--i)) == jumpStatement) break;
                        int elemtype = n.getType();
                        if (elemtype == 123) {
                            Node leave = new Node(3);
                            previous = NodeTransformer.addBeforeCurrent(parent, previous, node, leave);
                            continue;
                        }
                        if (elemtype != 81) continue;
                        Jump tryNode = (Jump)n;
                        Jump jsrFinally = new Jump(135);
                        jsrFinally.target = tryNode.getFinally();
                        previous = NodeTransformer.addBeforeCurrent(parent, previous, node, jsrFinally);
                    }
                    jump.target = type == 120 ? jumpStatement.target : jumpStatement.getContinue();
                    jump.setType(5);
                    break;
                }
                case 38: {
                    this.visitCall(node, tree);
                    break;
                }
                case 30: {
                    this.visitNew(node, tree);
                    break;
                }
                case 153: 
                case 158: {
                    Node child = node.getFirstChild();
                    if (child.getType() == 153) {
                        boolean createWith = tree.getType() != 109 || ((FunctionNode)tree).requiresActivation();
                        node = this.visitLet(createWith, parent, previous, node);
                        break;
                    }
                }
                case 122: 
                case 154: {
                    Node result = new Node(129);
                    Node cursor = node.getFirstChild();
                    while (cursor != null) {
                        Node n = cursor;
                        cursor = cursor.getNext();
                        if (n.getType() == 39) {
                            if (!n.hasChildren()) continue;
                            Node init = n.getFirstChild();
                            n.removeChild(init);
                            n.setType(49);
                            n = new Node(type == 154 ? 155 : 8, n, init);
                        } else if (n.getType() != 158) {
                            throw Kit.codeBug();
                        }
                        Node pop = new Node(133, n, node.getLineno());
                        result.addChildToBack(pop);
                    }
                    node = NodeTransformer.replaceCurrent(parent, previous, node, result);
                    break;
                }
                case 137: {
                    Scope defining = scope.getDefiningScope(node.getString());
                    if (defining == null) break;
                    node.setScope(defining);
                    break;
                }
                case 7: 
                case 32: {
                    Node child = node.getFirstChild();
                    if (type == 7) {
                        while (child.getType() == 26) {
                            child = child.getFirstChild();
                        }
                        if (child.getType() == 12 || child.getType() == 13) {
                            Node first = child.getFirstChild();
                            Node last = child.getLastChild();
                            if (first.getType() == 39 && first.getString().equals("undefined")) {
                                child = last;
                            } else if (last.getType() == 39 && last.getString().equals("undefined")) {
                                child = first;
                            }
                        }
                    }
                    if (child.getType() != 33) break;
                    child.setType(34);
                    break;
                }
                case 8: {
                    if (inStrictMode) {
                        node.setType(73);
                    }
                }
                case 31: 
                case 39: 
                case 155: {
                    String name;
                    Scope defining;
                    Node nameSource;
                    Node n;
                    if (createScopeObjects) break;
                    if (type == 39) {
                        nameSource = node;
                    } else {
                        nameSource = node.getFirstChild();
                        if (nameSource.getType() != 49) {
                            if (type == 31) break;
                            throw Kit.codeBug();
                        }
                    }
                    if (nameSource.getScope() != null || (defining = scope.getDefiningScope(name = nameSource.getString())) == null) break;
                    nameSource.setScope(defining);
                    if (type == 39) {
                        node.setType(55);
                        break;
                    }
                    if (type == 8 || type == 73) {
                        node.setType(56);
                        nameSource.setType(41);
                        break;
                    }
                    if (type == 155) {
                        node.setType(156);
                        nameSource.setType(41);
                        break;
                    }
                    if (type == 31) {
                        n = new Node(44);
                        node = NodeTransformer.replaceCurrent(parent, previous, node, n);
                        break;
                    }
                    throw Kit.codeBug();
                }
            }
            this.transformCompilationUnit_r(tree, node, node instanceof Scope ? (Scope)node : scope, createScopeObjects, inStrictMode);
        }
    }

    protected void visitNew(Node node, ScriptNode tree) {
    }

    protected void visitCall(Node node, ScriptNode tree) {
    }

    protected Node visitLet(boolean createWith, Node parent, Node previous, Node scopeNode) {
        Node result;
        boolean isExpression;
        Node vars = scopeNode.getFirstChild();
        Node body = vars.getNext();
        scopeNode.removeChild(vars);
        scopeNode.removeChild(body);
        boolean bl = isExpression = scopeNode.getType() == 158;
        if (createWith) {
            result = new Node(isExpression ? 159 : 129);
            result = NodeTransformer.replaceCurrent(parent, previous, scopeNode, result);
            ArrayList<Object> list = new ArrayList<Object>();
            Node objectLiteral = new Node(66);
            for (Node v = vars.getFirstChild(); v != null; v = v.getNext()) {
                Node current = v;
                if (current.getType() == 158) {
                    List destructuringNames = (List)current.getProp(22);
                    Node c = current.getFirstChild();
                    if (c.getType() != 153) {
                        throw Kit.codeBug();
                    }
                    body = isExpression ? new Node(89, c.getNext(), body) : new Node(129, new Node(133, c.getNext()), body);
                    if (destructuringNames != null) {
                        list.addAll(destructuringNames);
                        for (int i = 0; i < destructuringNames.size(); ++i) {
                            objectLiteral.addChildToBack(new Node(126, Node.newNumber(0.0)));
                        }
                    }
                    current = c.getFirstChild();
                }
                if (current.getType() != 39) {
                    throw Kit.codeBug();
                }
                list.add(ScriptRuntime.getIndexObject(current.getString()));
                Node init = current.getFirstChild();
                if (init == null) {
                    init = new Node(126, Node.newNumber(0.0));
                }
                objectLiteral.addChildToBack(init);
            }
            objectLiteral.putProp(12, list.toArray());
            Node newVars = new Node(2, objectLiteral);
            result.addChildToBack(newVars);
            result.addChildToBack(new Node(123, body));
            result.addChildToBack(new Node(3));
        } else {
            Scope scopeParent;
            result = new Node(isExpression ? 89 : 129);
            result = NodeTransformer.replaceCurrent(parent, previous, scopeNode, result);
            Node newVars = new Node(89);
            for (Node v = vars.getFirstChild(); v != null; v = v.getNext()) {
                Node current = v;
                if (current.getType() == 158) {
                    Node c = current.getFirstChild();
                    if (c.getType() != 153) {
                        throw Kit.codeBug();
                    }
                    body = isExpression ? new Node(89, c.getNext(), body) : new Node(129, new Node(133, c.getNext()), body);
                    Scope.joinScopes((Scope)current, (Scope)scopeNode);
                    current = c.getFirstChild();
                }
                if (current.getType() != 39) {
                    throw Kit.codeBug();
                }
                Node stringNode = Node.newString(current.getString());
                stringNode.setScope((Scope)scopeNode);
                Node init = current.getFirstChild();
                if (init == null) {
                    init = new Node(126, Node.newNumber(0.0));
                }
                newVars.addChildToBack(new Node(56, stringNode, init));
            }
            if (isExpression) {
                result.addChildToBack(newVars);
                scopeNode.setType(89);
                result.addChildToBack(scopeNode);
                scopeNode.addChildToBack(body);
                if (body instanceof Scope) {
                    scopeParent = ((Scope)body).getParentScope();
                    ((Scope)body).setParentScope((Scope)scopeNode);
                    ((Scope)scopeNode).setParentScope(scopeParent);
                }
            } else {
                result.addChildToBack(new Node(133, newVars));
                scopeNode.setType(129);
                result.addChildToBack(scopeNode);
                scopeNode.addChildrenToBack(body);
                if (body instanceof Scope) {
                    scopeParent = ((Scope)body).getParentScope();
                    ((Scope)body).setParentScope((Scope)scopeNode);
                    ((Scope)scopeNode).setParentScope(scopeParent);
                }
            }
        }
        return result;
    }

    private static Node addBeforeCurrent(Node parent, Node previous, Node current, Node toAdd) {
        if (previous == null) {
            if (current != parent.getFirstChild()) {
                Kit.codeBug();
            }
            parent.addChildToFront(toAdd);
        } else {
            if (current != previous.getNext()) {
                Kit.codeBug();
            }
            parent.addChildAfter(toAdd, previous);
        }
        return toAdd;
    }

    private static Node replaceCurrent(Node parent, Node previous, Node current, Node replacement) {
        if (previous == null) {
            if (current != parent.getFirstChild()) {
                Kit.codeBug();
            }
            parent.replaceChild(current, replacement);
        } else if (previous.next == current) {
            parent.replaceChildAfter(previous, replacement);
        } else {
            parent.replaceChild(current, replacement);
        }
        return replacement;
    }
}

