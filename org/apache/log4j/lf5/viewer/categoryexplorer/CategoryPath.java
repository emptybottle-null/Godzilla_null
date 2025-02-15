/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.log4j.lf5.viewer.categoryexplorer;

import java.util.LinkedList;
import java.util.StringTokenizer;
import org.apache.log4j.lf5.viewer.categoryexplorer.CategoryElement;

public class CategoryPath {
    protected LinkedList _categoryElements = new LinkedList();

    public CategoryPath() {
    }

    public CategoryPath(String category) {
        String processedCategory = category;
        if (processedCategory == null) {
            processedCategory = "Debug";
        }
        processedCategory = processedCategory.replace('/', '.');
        processedCategory = processedCategory.replace('\\', '.');
        StringTokenizer st = new StringTokenizer(processedCategory, ".");
        while (st.hasMoreTokens()) {
            String element = st.nextToken();
            this.addCategoryElement(new CategoryElement(element));
        }
    }

    public int size() {
        int count = this._categoryElements.size();
        return count;
    }

    public boolean isEmpty() {
        boolean empty = false;
        if (this._categoryElements.size() == 0) {
            empty = true;
        }
        return empty;
    }

    public void removeAllCategoryElements() {
        this._categoryElements.clear();
    }

    public void addCategoryElement(CategoryElement categoryElement) {
        this._categoryElements.addLast(categoryElement);
    }

    public CategoryElement categoryElementAt(int index) {
        return (CategoryElement)this._categoryElements.get(index);
    }

    public String toString() {
        StringBuffer out = new StringBuffer(100);
        out.append("\n");
        out.append("===========================\n");
        out.append("CategoryPath:                   \n");
        out.append("---------------------------\n");
        out.append("\nCategoryPath:\n\t");
        if (this.size() > 0) {
            for (int i = 0; i < this.size(); ++i) {
                out.append(this.categoryElementAt(i).toString());
                out.append("\n\t");
            }
        } else {
            out.append("<<NONE>>");
        }
        out.append("\n");
        out.append("===========================\n");
        return out.toString();
    }
}

