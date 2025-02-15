/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.xml.tree;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.BorderFactory;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import org.fife.rsta.ac.AbstractSourceTree;
import org.fife.rsta.ac.LanguageSupport;
import org.fife.rsta.ac.LanguageSupportFactory;
import org.fife.rsta.ac.xml.XmlLanguageSupport;
import org.fife.rsta.ac.xml.XmlParser;
import org.fife.rsta.ac.xml.tree.XmlTreeCellRenderer;
import org.fife.rsta.ac.xml.tree.XmlTreeNode;
import org.fife.ui.rsyntaxtextarea.DocumentRange;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.RSyntaxUtilities;

public class XmlOutlineTree
extends AbstractSourceTree {
    private XmlParser parser;
    private XmlEditorListener listener;
    private DefaultTreeModel model;
    private XmlTreeCellRenderer xmlTreeCellRenderer;

    public XmlOutlineTree() {
        this(false);
    }

    public XmlOutlineTree(boolean sorted) {
        this.setSorted(sorted);
        this.setBorder(BorderFactory.createEmptyBorder(0, 8, 0, 8));
        this.setRootVisible(false);
        this.xmlTreeCellRenderer = new XmlTreeCellRenderer();
        this.setCellRenderer(this.xmlTreeCellRenderer);
        this.model = new DefaultTreeModel(new XmlTreeNode("Nothing"));
        this.setModel(this.model);
        this.listener = new XmlEditorListener();
        this.addTreeSelectionListener(this.listener);
    }

    private void checkForXmlParsing() {
        if (this.parser != null) {
            this.parser.removePropertyChangeListener("XmlAST", this.listener);
            this.parser = null;
        }
        LanguageSupportFactory lsf = LanguageSupportFactory.get();
        LanguageSupport support = lsf.getSupportFor("text/xml");
        XmlLanguageSupport xls = (XmlLanguageSupport)support;
        this.parser = xls.getParser(this.textArea);
        if (this.parser != null) {
            this.parser.addPropertyChangeListener("XmlAST", this.listener);
            XmlTreeNode root = this.parser.getAst();
            this.update(root);
        } else {
            this.update((XmlTreeNode)null);
        }
    }

    @Override
    public void expandInitialNodes() {
        this.fastExpandAll(new TreePath(this.getModel().getRoot()), true);
    }

    private void gotoElementAtPath(TreePath path) {
        Object node = path.getLastPathComponent();
        if (node instanceof XmlTreeNode) {
            XmlTreeNode xtn = (XmlTreeNode)node;
            DocumentRange range = new DocumentRange(xtn.getStartOffset(), xtn.getEndOffset());
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
        this.checkForXmlParsing();
    }

    @Override
    public void uninstall() {
        if (this.parser != null) {
            this.parser.removePropertyChangeListener("XmlAST", this.listener);
            this.parser = null;
        }
        if (this.textArea != null) {
            this.textArea.removePropertyChangeListener("RSTA.syntaxStyle", this.listener);
            this.textArea = null;
        }
    }

    private void update(XmlTreeNode root) {
        if (root != null) {
            root = (XmlTreeNode)root.cloneWithChildren();
        }
        this.model.setRoot(root);
        if (root != null) {
            root.setSorted(this.isSorted());
        }
        this.refresh();
    }

    @Override
    public void updateUI() {
        super.updateUI();
        this.xmlTreeCellRenderer = new XmlTreeCellRenderer();
        this.setCellRenderer(this.xmlTreeCellRenderer);
    }

    private class XmlEditorListener
    implements PropertyChangeListener,
    TreeSelectionListener {
        private XmlEditorListener() {
        }

        @Override
        public void propertyChange(PropertyChangeEvent e) {
            String name = e.getPropertyName();
            if ("RSTA.syntaxStyle".equals(name)) {
                XmlOutlineTree.this.checkForXmlParsing();
            } else if ("XmlAST".equals(name)) {
                XmlTreeNode root = (XmlTreeNode)e.getNewValue();
                XmlOutlineTree.this.update(root);
            }
        }

        @Override
        public void valueChanged(TreeSelectionEvent e) {
            TreePath newPath;
            if (XmlOutlineTree.this.getGotoSelectedElementOnClick() && (newPath = e.getNewLeadSelectionPath()) != null) {
                XmlOutlineTree.this.gotoElementAtPath(newPath);
            }
        }
    }
}

