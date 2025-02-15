/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.dom4j.swing;

import javax.swing.tree.DefaultTreeModel;
import org.dom4j.Document;
import org.dom4j.swing.BranchTreeNode;

public class DocumentTreeModel
extends DefaultTreeModel {
    protected Document document;

    public DocumentTreeModel(Document document) {
        super(new BranchTreeNode(document));
        this.document = document;
    }

    public Document getDocument() {
        return this.document;
    }

    public void setDocument(Document document) {
        this.document = document;
        this.setRoot(new BranchTreeNode(document));
    }
}

