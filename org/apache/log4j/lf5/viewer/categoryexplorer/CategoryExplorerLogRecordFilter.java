/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.log4j.lf5.viewer.categoryexplorer;

import java.util.Enumeration;
import org.apache.log4j.lf5.LogRecord;
import org.apache.log4j.lf5.LogRecordFilter;
import org.apache.log4j.lf5.viewer.categoryexplorer.CategoryExplorerModel;
import org.apache.log4j.lf5.viewer.categoryexplorer.CategoryNode;
import org.apache.log4j.lf5.viewer.categoryexplorer.CategoryPath;

public class CategoryExplorerLogRecordFilter
implements LogRecordFilter {
    protected CategoryExplorerModel _model;

    public CategoryExplorerLogRecordFilter(CategoryExplorerModel model) {
        this._model = model;
    }

    public boolean passes(LogRecord record) {
        CategoryPath path = new CategoryPath(record.getCategory());
        return this._model.isCategoryPathActive(path);
    }

    public void reset() {
        this.resetAllNodes();
    }

    protected void resetAllNodes() {
        Enumeration nodes = this._model.getRootCategoryNode().depthFirstEnumeration();
        while (nodes.hasMoreElements()) {
            CategoryNode current = (CategoryNode)nodes.nextElement();
            current.resetNumberOfContainedRecords();
            this._model.nodeChanged(current);
        }
    }
}

