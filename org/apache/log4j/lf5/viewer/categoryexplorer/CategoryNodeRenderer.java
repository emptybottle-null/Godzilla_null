/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.log4j.lf5.viewer.categoryexplorer;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.tree.DefaultTreeCellRenderer;
import org.apache.log4j.lf5.viewer.categoryexplorer.CategoryNode;

public class CategoryNodeRenderer
extends DefaultTreeCellRenderer {
    private static final long serialVersionUID = -6046702673278595048L;
    public static final Color FATAL_CHILDREN = new Color(189, 113, 0);
    protected JCheckBox _checkBox = new JCheckBox();
    protected JPanel _panel = new JPanel();
    protected static ImageIcon _sat = null;

    public CategoryNodeRenderer() {
        this._panel.setBackground(UIManager.getColor("Tree.textBackground"));
        if (_sat == null) {
            String resource = "/org/apache/log4j/lf5/viewer/images/channelexplorer_satellite.gif";
            URL satURL = this.getClass().getResource(resource);
            _sat = new ImageIcon(satURL);
        }
        this.setOpaque(false);
        this._checkBox.setOpaque(false);
        this._panel.setOpaque(false);
        this._panel.setLayout(new FlowLayout(0, 0, 0));
        this._panel.add(this._checkBox);
        this._panel.add(this);
        this.setOpenIcon(_sat);
        this.setClosedIcon(_sat);
        this.setLeafIcon(_sat);
    }

    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {
        CategoryNode node = (CategoryNode)value;
        super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);
        if (row == 0) {
            this._checkBox.setVisible(false);
        } else {
            this._checkBox.setVisible(true);
            this._checkBox.setSelected(node.isSelected());
        }
        String toolTip = this.buildToolTip(node);
        this._panel.setToolTipText(toolTip);
        if (node.hasFatalChildren()) {
            this.setForeground(FATAL_CHILDREN);
        }
        if (node.hasFatalRecords()) {
            this.setForeground(Color.red);
        }
        return this._panel;
    }

    public Dimension getCheckBoxOffset() {
        return new Dimension(0, 0);
    }

    protected String buildToolTip(CategoryNode node) {
        StringBuffer result = new StringBuffer();
        result.append(node.getTitle()).append(" contains a total of ");
        result.append(node.getTotalNumberOfRecords());
        result.append(" LogRecords.");
        result.append(" Right-click for more info.");
        return result.toString();
    }
}

