/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.js.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.text.BadLocationException;
import org.fife.rsta.ac.js.IconFactory;
import org.fife.rsta.ac.js.tree.JavaScriptTreeNode;
import org.fife.rsta.ac.js.util.RhinoUtil;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.mozilla.javascript.ast.Assignment;
import org.mozilla.javascript.ast.AstNode;
import org.mozilla.javascript.ast.AstRoot;
import org.mozilla.javascript.ast.ExpressionStatement;
import org.mozilla.javascript.ast.FunctionCall;
import org.mozilla.javascript.ast.FunctionNode;
import org.mozilla.javascript.ast.Name;
import org.mozilla.javascript.ast.NodeVisitor;
import org.mozilla.javascript.ast.ObjectLiteral;
import org.mozilla.javascript.ast.ObjectProperty;
import org.mozilla.javascript.ast.PropertyGet;
import org.mozilla.javascript.ast.VariableDeclaration;
import org.mozilla.javascript.ast.VariableInitializer;

class JavaScriptOutlineTreeGenerator
implements NodeVisitor {
    private JavaScriptTreeNode root;
    private RSyntaxTextArea textArea;
    private JavaScriptTreeNode curScopeTreeNode;
    private Map<String, List<JavaScriptTreeNode>> prototypeAdditions = null;

    JavaScriptOutlineTreeGenerator(RSyntaxTextArea textArea, AstRoot ast) {
        this.textArea = textArea;
        this.root = new JavaScriptTreeNode((AstNode)null);
        if (ast != null) {
            ast.visit(this);
        }
    }

    private void addPrototypeAdditionsToRoot() {
        if (this.prototypeAdditions != null) {
            this.root.refresh();
            block0: for (Map.Entry<String, List<JavaScriptTreeNode>> entry : this.prototypeAdditions.entrySet()) {
                String clazz = entry.getKey();
                for (int i = 0; i < this.root.getChildCount(); ++i) {
                    JavaScriptTreeNode childNode = (JavaScriptTreeNode)this.root.getChildAt(i);
                    String text = childNode.getText(true);
                    if (text == null || !text.startsWith(clazz + "(")) continue;
                    for (JavaScriptTreeNode memberNode : entry.getValue()) {
                        childNode.add(memberNode);
                    }
                    childNode.setIcon(IconFactory.getIcon("default_class"));
                    continue block0;
                }
            }
        }
    }

    private JavaScriptTreeNode createTreeNode(AstNode node) {
        JavaScriptTreeNode tn = new JavaScriptTreeNode(node);
        try {
            int offs = node.getAbsolutePosition();
            tn.setOffset(this.textArea.getDocument().createPosition(offs));
        } catch (BadLocationException ble) {
            ble.printStackTrace();
        }
        return tn;
    }

    private JavaScriptTreeNode createTreeNode(List<AstNode> nodes) {
        JavaScriptTreeNode tn = new JavaScriptTreeNode(nodes);
        try {
            int offs = nodes.get(0).getAbsolutePosition();
            tn.setOffset(this.textArea.getDocument().createPosition(offs));
        } catch (BadLocationException ble) {
            ble.printStackTrace();
        }
        return tn;
    }

    private List<AstNode> getChainedPropertyGetNodes(PropertyGet pg) {
        ArrayList<AstNode> nodes = new ArrayList<AstNode>();
        this.getChainedPropertyGetNodesImpl(pg, nodes);
        return nodes;
    }

    private void getChainedPropertyGetNodesImpl(PropertyGet pg, List<AstNode> nodes) {
        if (pg.getLeft() instanceof PropertyGet) {
            this.getChainedPropertyGetNodesImpl((PropertyGet)pg.getLeft(), nodes);
        } else {
            nodes.add(pg.getLeft());
        }
        nodes.add(pg.getRight());
    }

    public JavaScriptTreeNode getTreeRoot() {
        this.addPrototypeAdditionsToRoot();
        return this.root;
    }

    @Override
    public boolean visit(AstNode node) {
        if (node == null) {
            return false;
        }
        int nodeType = node.getType();
        switch (nodeType) {
            case 136: {
                this.curScopeTreeNode = this.root;
                return true;
            }
            case 109: {
                FunctionNode fn = (FunctionNode)node;
                return this.visitFunction(fn);
            }
            case 122: {
                VariableDeclaration varDec = (VariableDeclaration)node;
                return this.visitVariableDeclaration(varDec);
            }
            case 129: {
                return true;
            }
            case 134: {
                ExpressionStatement exprStmt = (ExpressionStatement)node;
                return this.visitExpressionStatement(exprStmt);
            }
        }
        return false;
    }

    private boolean visitExpressionStatement(ExpressionStatement exprStmt) {
        Assignment assignment;
        AstNode left;
        AstNode expr = exprStmt.getExpression();
        if (expr instanceof Assignment && (left = (assignment = (Assignment)expr).getLeft()) instanceof PropertyGet) {
            PropertyGet pg = (PropertyGet)left;
            List<AstNode> chainedPropertyGetNodes = this.getChainedPropertyGetNodes(pg);
            int count = chainedPropertyGetNodes.size();
            if (count >= 3 && RhinoUtil.isPrototypeNameNode(chainedPropertyGetNodes.get(count - 2))) {
                String clazz = RhinoUtil.getPrototypeClazz(chainedPropertyGetNodes, count - 2);
                AstNode propNode = chainedPropertyGetNodes.get(count - 1);
                String member = ((Name)propNode).getIdentifier();
                JavaScriptTreeNode tn = this.createTreeNode(propNode);
                AstNode propertyValue = assignment.getRight();
                this.visitPrototypeMember(tn, clazz, member, propertyValue);
            } else if (RhinoUtil.isPrototypeNameNode(chainedPropertyGetNodes.get(count - 1))) {
                JavaScriptTreeNode tn = this.createTreeNode(chainedPropertyGetNodes);
                tn.setIcon(IconFactory.getIcon("local_variable"));
                tn.setSortPriority(2);
                this.curScopeTreeNode.add(tn);
                String clazz = RhinoUtil.getPrototypeClazz(chainedPropertyGetNodes, count - 1);
                AstNode rhs = assignment.getRight();
                if (rhs instanceof ObjectLiteral) {
                    tn.setText(clazz + "()");
                    ObjectLiteral value = (ObjectLiteral)rhs;
                    this.visitPrototypeMembers(value, clazz);
                } else if (rhs instanceof FunctionCall) {
                    FunctionCall rhsFunc = (FunctionCall)rhs;
                    AstNode target = rhsFunc.getTarget();
                    if (target instanceof PropertyGet) {
                        pg = (PropertyGet)target;
                        if (RhinoUtil.isSimplePropertyGet(pg, "Object", "create")) {
                            AstNode arg2;
                            tn.setText(clazz + "()");
                            List<AstNode> args = rhsFunc.getArguments();
                            if (args.size() >= 2 && (arg2 = args.get(1)) instanceof ObjectLiteral) {
                                ObjectLiteral descriptorObjLit = (ObjectLiteral)arg2;
                                this.visitPropertyDescriptors(descriptorObjLit, clazz);
                            }
                        } else {
                            tn.setText(clazz + "(???)");
                        }
                    }
                } else {
                    tn.setText(clazz + "(???)");
                }
            } else {
                JavaScriptTreeNode tn = this.createTreeNode(chainedPropertyGetNodes);
                tn.setIcon(IconFactory.getIcon("default_class"));
                tn.setSortPriority(1);
                String clazz = RhinoUtil.getPrototypeClazz(chainedPropertyGetNodes, count);
                AstNode rhs = assignment.getRight();
                if (rhs instanceof ObjectLiteral) {
                    this.curScopeTreeNode.add(tn);
                    tn.setText(clazz + "()");
                    ObjectLiteral value = (ObjectLiteral)rhs;
                    List<ObjectProperty> properties = value.getElements();
                    for (ObjectProperty property : properties) {
                        AstNode propertyKey = property.getLeft();
                        tn = this.createTreeNode(propertyKey);
                        String memberName = RhinoUtil.getPropertyName(propertyKey);
                        AstNode propertyValue = property.getRight();
                        this.visitPrototypeMember(tn, clazz, memberName, propertyValue);
                    }
                } else if (rhs instanceof FunctionCall) {
                    FunctionCall rhsFunc = (FunctionCall)rhs;
                    AstNode target = rhsFunc.getTarget();
                    if (target instanceof PropertyGet) {
                        pg = (PropertyGet)target;
                        if (RhinoUtil.isSimplePropertyGet(pg, "Object", "create")) {
                            AstNode arg2;
                            this.curScopeTreeNode.add(tn);
                            tn.setText(clazz + "()");
                            List<AstNode> args = rhsFunc.getArguments();
                            if (args.size() >= 2 && (arg2 = args.get(1)) instanceof ObjectLiteral) {
                                ObjectLiteral descriptorObjLit = (ObjectLiteral)arg2;
                                this.visitPropertyDescriptors(descriptorObjLit, clazz);
                            }
                        } else if (RhinoUtil.isSimplePropertyGet(pg, "Object", "freeze")) {
                            AstNode arg;
                            this.curScopeTreeNode.add(tn);
                            tn.setText(clazz + "()");
                            List<AstNode> args = rhsFunc.getArguments();
                            if (args.size() == 1 && (arg = args.get(0)) instanceof ObjectLiteral) {
                                tn.setText(clazz + "()");
                                ObjectLiteral value = (ObjectLiteral)arg;
                                this.visitPrototypeMembers(value, clazz);
                            }
                        }
                    } else {
                        tn.setText(clazz + "(???)");
                    }
                } else if (rhs instanceof FunctionNode) {
                    String text = clazz;
                    this.curScopeTreeNode.add(tn);
                    tn.setText(text);
                    this.curScopeTreeNode = tn;
                    ((FunctionNode)rhs).getBody().visit(this);
                    this.curScopeTreeNode = (JavaScriptTreeNode)this.curScopeTreeNode.getParent();
                } else {
                    this.curScopeTreeNode.add(tn);
                    tn.setText(clazz + "(???)");
                }
            }
        }
        return false;
    }

    private void visitPropertyDescriptors(ObjectLiteral descriptorObjLit, String clazz) {
        List<ObjectProperty> descriptors = descriptorObjLit.getElements();
        for (ObjectProperty prop : descriptors) {
            AstNode propertyKey = prop.getLeft();
            AstNode propertyValue = prop.getRight();
            if (!(propertyValue instanceof ObjectLiteral)) continue;
            JavaScriptTreeNode tn = this.createTreeNode(propertyKey);
            String memberName = RhinoUtil.getPropertyName(propertyKey);
            this.visitPropertyDescriptor(tn, clazz, memberName, (ObjectLiteral)propertyValue);
        }
    }

    private void visitPropertyDescriptor(JavaScriptTreeNode tn, String clazz, String memberName, ObjectLiteral propDesc) {
        List<ObjectProperty> propDescProperties = propDesc.getElements();
        for (ObjectProperty propDescProperty : propDescProperties) {
            List<JavaScriptTreeNode> list;
            AstNode propertyKey = propDescProperty.getLeft();
            String propName = RhinoUtil.getPropertyName(propertyKey);
            if (!"value".equals(propName)) continue;
            AstNode propertyValue = propDescProperty.getRight();
            boolean isFunction = propertyValue instanceof FunctionNode;
            String text = memberName;
            if (isFunction) {
                FunctionNode func = (FunctionNode)propertyValue;
                text = text + RhinoUtil.getFunctionArgsString(func);
                tn.setIcon(IconFactory.getIcon("methpub_obj"));
                tn.setSortPriority(1);
            } else {
                tn.setIcon(IconFactory.getIcon("field_public_obj"));
                tn.setSortPriority(2);
            }
            tn.setText(text);
            if (this.prototypeAdditions == null) {
                this.prototypeAdditions = new HashMap<String, List<JavaScriptTreeNode>>();
            }
            if ((list = this.prototypeAdditions.get(clazz)) == null) {
                list = new ArrayList<JavaScriptTreeNode>();
                this.prototypeAdditions.put(clazz, list);
            }
            list.add(tn);
            if (!isFunction) continue;
            JavaScriptTreeNode prevScopeTreeNode = this.curScopeTreeNode;
            this.curScopeTreeNode = tn;
            FunctionNode func = (FunctionNode)propertyValue;
            func.getBody().visit(this);
            this.curScopeTreeNode = prevScopeTreeNode;
        }
    }

    private void visitPrototypeMembers(ObjectLiteral objLiteral, String clazz) {
        List<ObjectProperty> properties = objLiteral.getElements();
        for (ObjectProperty property : properties) {
            AstNode propertyKey = property.getLeft();
            JavaScriptTreeNode tn = this.createTreeNode(propertyKey);
            String memberName = RhinoUtil.getPropertyName(propertyKey);
            AstNode propertyValue = property.getRight();
            this.visitPrototypeMember(tn, clazz, memberName, propertyValue);
        }
    }

    private void visitPrototypeMember(JavaScriptTreeNode tn, String clazz, String memberName, AstNode memberValue) {
        List<JavaScriptTreeNode> list;
        boolean isFunction = memberValue instanceof FunctionNode;
        String text = memberName;
        if (isFunction) {
            FunctionNode func = (FunctionNode)memberValue;
            text = text + RhinoUtil.getFunctionArgsString(func);
            tn.setIcon(IconFactory.getIcon("methpub_obj"));
            tn.setSortPriority(1);
        } else {
            tn.setIcon(IconFactory.getIcon("field_public_obj"));
            tn.setSortPriority(2);
        }
        tn.setText(text);
        if (this.prototypeAdditions == null) {
            this.prototypeAdditions = new HashMap<String, List<JavaScriptTreeNode>>();
        }
        if ((list = this.prototypeAdditions.get(clazz)) == null) {
            list = new ArrayList<JavaScriptTreeNode>();
            this.prototypeAdditions.put(clazz, list);
        }
        list.add(tn);
        if (isFunction) {
            JavaScriptTreeNode prevScopeTreeNode = this.curScopeTreeNode;
            this.curScopeTreeNode = tn;
            FunctionNode func = (FunctionNode)memberValue;
            func.getBody().visit(this);
            this.curScopeTreeNode = prevScopeTreeNode;
        }
    }

    private boolean visitFunction(FunctionNode fn) {
        Name funcName = fn.getFunctionName();
        if (funcName != null) {
            String text = fn.getName() + RhinoUtil.getFunctionArgsString(fn);
            JavaScriptTreeNode tn = this.createTreeNode(funcName);
            tn.setText(text);
            tn.setIcon(IconFactory.getIcon("default_function"));
            tn.setSortPriority(1);
            this.curScopeTreeNode.add(tn);
            this.curScopeTreeNode = tn;
            fn.getBody().visit(this);
            this.curScopeTreeNode = (JavaScriptTreeNode)this.curScopeTreeNode.getParent();
        }
        return false;
    }

    private boolean visitVariableDeclaration(VariableDeclaration varDec) {
        List<VariableInitializer> vars = varDec.getVariables();
        for (VariableInitializer var : vars) {
            String varName;
            Name varNameNode = null;
            AstNode target = var.getTarget();
            switch (target.getType()) {
                case 39: {
                    varNameNode = (Name)target;
                    varName = varNameNode.getIdentifier();
                    break;
                }
                default: {
                    System.out.println("... Unknown var target type: " + target.getClass());
                    varName = "?";
                }
            }
            boolean isFunction = var.getInitializer() instanceof FunctionNode;
            JavaScriptTreeNode tn = this.createTreeNode(varNameNode);
            if (isFunction) {
                FunctionNode func = (FunctionNode)var.getInitializer();
                tn.setText(varName + RhinoUtil.getFunctionArgsString(func));
                tn.setIcon(IconFactory.getIcon("default_class"));
                tn.setSortPriority(1);
                this.curScopeTreeNode.add(tn);
                this.curScopeTreeNode = tn;
                func.getBody().visit(this);
                this.curScopeTreeNode = (JavaScriptTreeNode)this.curScopeTreeNode.getParent();
                continue;
            }
            tn.setText(varName);
            tn.setIcon(IconFactory.getIcon("local_variable"));
            tn.setSortPriority(2);
            this.curScopeTreeNode.add(tn);
        }
        return false;
    }
}

