/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ui.search;

import java.util.Vector;
import javax.swing.InputMap;
import javax.swing.KeyStroke;
import javax.swing.text.JTextComponent;
import org.fife.rsta.ui.UIUtil;
import org.fife.rsta.ui.search.FindToolBar;
import org.fife.rsta.ui.search.RegexAwareComboBox;

public class SearchComboBox
extends RegexAwareComboBox<String> {
    private FindToolBar toolBar;

    public SearchComboBox(FindToolBar toolBar, boolean replace) {
        super(replace);
        this.toolBar = toolBar;
        UIUtil.fixComboOrientation(this);
        this.updateTextFieldKeyMap();
    }

    @Override
    public void addItem(String item) {
        int curIndex = this.getIndexOf(item);
        if (curIndex == -1) {
            super.addItem(item);
        } else if (curIndex > 0) {
            this.removeItem(item);
            this.insertItemAt(item, 0);
        }
        this.setSelectedIndex(0);
    }

    private int getIndexOf(String item) {
        for (int i = 0; i < this.dataModel.getSize(); ++i) {
            if (!((String)this.dataModel.getElementAt(i)).equals(item)) continue;
            return i;
        }
        return -1;
    }

    public String getSelectedString() {
        JTextComponent comp = UIUtil.getTextComponent(this);
        return comp.getText();
    }

    public Vector<String> getSearchStrings() {
        int selectedIndex = this.getSelectedIndex();
        if (selectedIndex == -1) {
            this.addItem(this.getSelectedString());
        } else if (selectedIndex > 0) {
            String item = (String)this.getSelectedItem();
            this.removeItem(item);
            this.insertItemAt(item, 0);
            this.setSelectedIndex(0);
        }
        int itemCount = this.getItemCount();
        Vector<String> vector = new Vector<String>(itemCount);
        for (int i = 0; i < itemCount; ++i) {
            vector.add((String)this.getItemAt(i));
        }
        return vector;
    }

    private void updateTextFieldKeyMap() {
        JTextComponent comp = UIUtil.getTextComponent(this);
        InputMap im = comp.getInputMap();
        im.put(KeyStroke.getKeyStroke("ctrl H"), "none");
    }

    @Override
    public void updateUI() {
        super.updateUI();
        if (this.toolBar != null) {
            this.toolBar.searchComboUpdateUICallback(this);
        }
        this.updateTextFieldKeyMap();
    }
}

