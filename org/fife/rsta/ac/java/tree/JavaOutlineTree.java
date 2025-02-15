/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.java.tree;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Iterator;
import javax.swing.BorderFactory;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import org.fife.rsta.ac.AbstractSourceTree;
import org.fife.rsta.ac.LanguageSupport;
import org.fife.rsta.ac.LanguageSupportFactory;
import org.fife.rsta.ac.java.JavaLanguageSupport;
import org.fife.rsta.ac.java.JavaParser;
import org.fife.rsta.ac.java.rjc.ast.ASTNode;
import org.fife.rsta.ac.java.rjc.ast.CodeBlock;
import org.fife.rsta.ac.java.rjc.ast.CompilationUnit;
import org.fife.rsta.ac.java.rjc.ast.Field;
import org.fife.rsta.ac.java.rjc.ast.ImportDeclaration;
import org.fife.rsta.ac.java.rjc.ast.LocalVariable;
import org.fife.rsta.ac.java.rjc.ast.Member;
import org.fife.rsta.ac.java.rjc.ast.Method;
import org.fife.rsta.ac.java.rjc.ast.NormalClassDeclaration;
import org.fife.rsta.ac.java.rjc.ast.NormalInterfaceDeclaration;
import org.fife.rsta.ac.java.rjc.ast.Package;
import org.fife.rsta.ac.java.rjc.ast.TypeDeclaration;
import org.fife.rsta.ac.java.tree.AstTreeCellRenderer;
import org.fife.rsta.ac.java.tree.JavaTreeNode;
import org.fife.rsta.ac.java.tree.LocalVarTreeNode;
import org.fife.rsta.ac.java.tree.MemberTreeNode;
import org.fife.rsta.ac.java.tree.TypeDeclarationTreeNode;
import org.fife.ui.rsyntaxtextarea.DocumentRange;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.RSyntaxUtilities;

public class JavaOutlineTree
extends AbstractSourceTree {
    private DefaultTreeModel model;
    private RSyntaxTextArea textArea;
    private JavaParser parser;
    private Listener listener;

    public JavaOutlineTree() {
        this(false);
    }

    public JavaOutlineTree(boolean sorted) {
        this.setSorted(sorted);
        this.setBorder(BorderFactory.createEmptyBorder(0, 8, 0, 8));
        this.setRootVisible(false);
        this.setCellRenderer(new AstTreeCellRenderer());
        this.model = new DefaultTreeModel(new DefaultMutableTreeNode("Nothing"));
        this.setModel(this.model);
        this.listener = new Listener();
        this.addTreeSelectionListener(this.listener);
    }

    private void update(CompilationUnit cu) {
        JavaTreeNode root = new JavaTreeNode("Remove me!", "sourceFileIcon");
        root.setSortable(false);
        if (cu == null) {
            this.model.setRoot(root);
            return;
        }
        Package pkg = cu.getPackage();
        if (pkg != null) {
            String iconName = "packageIcon";
            root.add(new JavaTreeNode(pkg, iconName, false));
        }
        if (!this.getShowMajorElementsOnly()) {
            JavaTreeNode importNode = new JavaTreeNode("Imports", "importRootIcon");
            Iterator<ImportDeclaration> i = cu.getImportIterator();
            while (i.hasNext()) {
                ImportDeclaration idec = i.next();
                JavaTreeNode iNode = new JavaTreeNode(idec, "importIcon");
                importNode.add(iNode);
            }
            root.add(importNode);
        }
        Iterator<TypeDeclaration> i = cu.getTypeDeclarationIterator();
        while (i.hasNext()) {
            TypeDeclaration td = i.next();
            TypeDeclarationTreeNode dmtn = this.createTypeDeclarationNode(td);
            root.add(dmtn);
        }
        this.model.setRoot(root);
        root.setSorted(this.isSorted());
        this.refresh();
    }

    private void checkForJavaParsing() {
        if (this.parser != null) {
            this.parser.removePropertyChangeListener("CompilationUnit", this.listener);
            this.parser = null;
        }
        LanguageSupportFactory lsf = LanguageSupportFactory.get();
        LanguageSupport support = lsf.getSupportFor("text/java");
        JavaLanguageSupport jls = (JavaLanguageSupport)support;
        this.parser = jls.getParser(this.textArea);
        if (this.parser != null) {
            this.parser.addPropertyChangeListener("CompilationUnit", this.listener);
            CompilationUnit cu = this.parser.getCompilationUnit();
            this.update(cu);
        } else {
            this.update((CompilationUnit)null);
        }
    }

    private MemberTreeNode createMemberNode(Member member) {
        MemberTreeNode node = member instanceof CodeBlock ? new MemberTreeNode((CodeBlock)member) : (member instanceof Field ? new MemberTreeNode((Field)member) : new MemberTreeNode((Method)member));
        CodeBlock body = null;
        if (member instanceof CodeBlock) {
            body = (CodeBlock)member;
        } else if (member instanceof Method) {
            body = ((Method)member).getBody();
        }
        if (body != null && !this.getShowMajorElementsOnly()) {
            for (int i = 0; i < body.getLocalVarCount(); ++i) {
                LocalVariable var = body.getLocalVar(i);
                LocalVarTreeNode varNode = new LocalVarTreeNode(var);
                node.add(varNode);
            }
        }
        return node;
    }

    private TypeDeclarationTreeNode createTypeDeclarationNode(TypeDeclaration td) {
        TypeDeclarationTreeNode dmtn;
        block5: {
            block4: {
                dmtn = new TypeDeclarationTreeNode(td);
                if (!(td instanceof NormalClassDeclaration)) break block4;
                NormalClassDeclaration ncd = (NormalClassDeclaration)td;
                for (int j = 0; j < ncd.getChildTypeCount(); ++j) {
                    TypeDeclaration td2 = ncd.getChildType(j);
                    TypeDeclarationTreeNode tdn = this.createTypeDeclarationNode(td2);
                    dmtn.add(tdn);
                }
                Iterator<Member> i = ncd.getMemberIterator();
                while (i.hasNext()) {
                    dmtn.add(this.createMemberNode(i.next()));
                }
                break block5;
            }
            if (!(td instanceof NormalInterfaceDeclaration)) break block5;
            NormalInterfaceDeclaration nid = (NormalInterfaceDeclaration)td;
            for (int j = 0; j < nid.getChildTypeCount(); ++j) {
                TypeDeclaration td2 = nid.getChildType(j);
                TypeDeclarationTreeNode tdn = this.createTypeDeclarationNode(td2);
                dmtn.add(tdn);
            }
            Iterator<Member> i = nid.getMemberIterator();
            while (i.hasNext()) {
                dmtn.add(this.createMemberNode(i.next()));
            }
        }
        return dmtn;
    }

    @Override
    public void expandInitialNodes() {
        int j = 0;
        while (j < this.getRowCount()) {
            this.collapseRow(j++);
        }
        this.expandRow(0);
        for (j = 1; j < this.getRowCount(); ++j) {
            TreePath path = this.getPathForRow(j);
            Object comp = path.getLastPathComponent();
            if (!(comp instanceof TypeDeclarationTreeNode)) continue;
            this.expandPath(path);
        }
    }

    private void gotoElementAtPath(TreePath path) {
        DefaultMutableTreeNode node = (DefaultMutableTreeNode)path.getLastPathComponent();
        Object obj = node.getUserObject();
        if (obj instanceof ASTNode) {
            ASTNode astNode = (ASTNode)obj;
            int start = astNode.getNameStartOffset();
            int end = astNode.getNameEndOffset();
            DocumentRange range = new DocumentRange(start, end);
            RSyntaxUtilities.selectAndPossiblyCenter(this.textArea, range, true);
        }
    }

    @Override
    public boolean gotoSelectedElement() {
        TreePath path = this.getLeadSelectionPath();
        if (path != null) {
            this.gotoElementAtPath(path);
            return true;
        }
        return false;
    }

    @Override
    public void listenTo(RSyntaxTextArea textArea) {
        if (this.textArea != null) {
            this.uninstall();
        }
        if (textArea == null) {
            return;
        }
        this.textArea = textArea;
        textArea.addPropertyChangeListener("RSTA.syntaxStyle", this.listener);
        this.checkForJavaParsing();
    }

    @Override
    public void uninstall() {
        if (this.parser != null) {
            this.parser.removePropertyChangeListener("CompilationUnit", this.listener);
            this.parser = null;
        }
        if (this.textArea != null) {
            this.textArea.removePropertyChangeListener("RSTA.syntaxStyle", this.listener);
            this.textArea = null;
        }
    }

    @Override
    public void updateUI() {
        super.updateUI();
        this.setCellRenderer(new AstTreeCellRenderer());
    }

    private class Listener
    implements PropertyChangeListener,
    TreeSelectionListener {
        private Listener() {
        }

        @Override
        public void propertyChange(PropertyChangeEvent e) {
            String name = e.getPropertyName();
            if ("RSTA.syntaxStyle".equals(name)) {
                JavaOutlineTree.this.checkForJavaParsing();
            } else if ("CompilationUnit".equals(name)) {
                CompilationUnit cu = (CompilationUnit)e.getNewValue();
                JavaOutlineTree.this.update(cu);
            }
        }

        @Override
        public void valueChanged(TreeSelectionEvent e) {
            TreePath newPath;
            if (JavaOutlineTree.this.getGotoSelectedElementOnClick() && (newPath = e.getNewLeadSelectionPath()) != null) {
                JavaOutlineTree.this.gotoElementAtPath(newPath);
            }
        }
    }
}

