/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.js.tree;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.BorderFactory;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import org.fife.rsta.ac.AbstractSourceTree;
import org.fife.rsta.ac.LanguageSupport;
import org.fife.rsta.ac.LanguageSupportFactory;
import org.fife.rsta.ac.js.JavaScriptLanguageSupport;
import org.fife.rsta.ac.js.JavaScriptParser;
import org.fife.rsta.ac.js.tree.JavaScriptOutlineTreeGenerator;
import org.fife.rsta.ac.js.tree.JavaScriptTreeCellRenderer;
import org.fife.rsta.ac.js.tree.JavaScriptTreeNode;
import org.fife.ui.rsyntaxtextarea.DocumentRange;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.RSyntaxUtilities;
import org.mozilla.javascript.ast.AstRoot;

public class JavaScriptOutlineTree
extends AbstractSourceTree {
    private DefaultTreeModel model;
    private RSyntaxTextArea textArea;
    private JavaScriptParser parser;
    private Listener listener;
    static final int PRIORITY_FUNCTION = 1;
    static final int PRIORITY_VARIABLE = 2;

    public JavaScriptOutlineTree() {
        this(false);
    }

    public JavaScriptOutlineTree(boolean sorted) {
        this.setSorted(sorted);
        this.setBorder(BorderFactory.createEmptyBorder(0, 8, 0, 8));
        this.setRootVisible(false);
        this.setCellRenderer(new JavaScriptTreeCellRenderer());
        this.model = new DefaultTreeModel(new DefaultMutableTreeNode("Nothing"));
        this.setModel(this.model);
        this.listener = new Listener();
        this.addTreeSelectionListener(this.listener);
    }

    private void checkForJavaScriptParsing() {
        if (this.parser != null) {
            this.parser.removePropertyChangeListener("AST", this.listener);
            this.parser = null;
        }
        LanguageSupportFactory lsf = LanguageSupportFactory.get();
        LanguageSupport support = lsf.getSupportFor("text/javascript");
        JavaScriptLanguageSupport jls = (JavaScriptLanguageSupport)support;
        this.parser = jls.getParser(this.textArea);
        if (this.parser != null) {
            this.parser.addPropertyChangeListener("AST", this.listener);
            AstRoot ast = this.parser.getAstRoot();
            this.update(ast);
        } else {
            this.update((AstRoot)null);
        }
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
            this.expandPath(path);
        }
    }

    private void gotoElementAtPath(TreePath path) {
        JavaScriptTreeNode jstn;
        int len;
        Object node = path.getLastPathComponent();
        if (node instanceof JavaScriptTreeNode && (len = (jstn = (JavaScriptTreeNode)node).getLength()) > -1) {
            int offs = jstn.getOffset();
            DocumentRange range = new DocumentRange(offs, offs + len);
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
        this.checkForJavaScriptParsing();
    }

    @Override
    public void uninstall() {
        if (this.parser != null) {
            this.parser.removePropertyChangeListener("AST", this.listener);
            this.parser = null;
        }
        if (this.textArea != null) {
            this.textArea.removePropertyChangeListener("RSTA.syntaxStyle", this.listener);
            this.textArea = null;
        }
    }

    private void update(AstRoot ast) {
        JavaScriptOutlineTreeGenerator generator = new JavaScriptOutlineTreeGenerator(this.textArea, ast);
        JavaScriptTreeNode root = generator.getTreeRoot();
        this.model.setRoot(root);
        root.setSorted(this.isSorted());
        this.refresh();
    }

    @Override
    public void updateUI() {
        super.updateUI();
        this.setCellRenderer(new JavaScriptTreeCellRenderer());
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
                JavaScriptOutlineTree.this.checkForJavaScriptParsing();
            } else if ("AST".equals(name)) {
                AstRoot ast = (AstRoot)e.getNewValue();
                JavaScriptOutlineTree.this.update(ast);
            }
        }

        @Override
        public void valueChanged(TreeSelectionEvent e) {
            TreePath newPath;
            if (JavaScriptOutlineTree.this.getGotoSelectedElementOnClick() && (newPath = e.getNewLeadSelectionPath()) != null) {
                JavaScriptOutlineTree.this.gotoElementAtPath(newPath);
            }
        }
    }
}

