/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.xml.tree;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.net.URL;
import java.util.Map;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTree;
import javax.swing.plaf.basic.BasicLabelUI;
import javax.swing.tree.DefaultTreeCellRenderer;
import org.fife.rsta.ac.xml.tree.XmlTreeNode;
import org.fife.ui.rsyntaxtextarea.RSyntaxUtilities;

class XmlTreeCellRenderer
extends DefaultTreeCellRenderer {
    private Icon elemIcon;
    private String elem;
    private String attr;
    private boolean selected;
    private static final XmlTreeCellUI UI = new XmlTreeCellUI();
    private static final Color ATTR_COLOR = new Color(0x808080);

    public XmlTreeCellRenderer() {
        URL url = this.getClass().getResource("tag.png");
        if (url != null) {
            this.elemIcon = new ImageIcon(url);
        }
        this.setUI(UI);
    }

    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean focused) {
        super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, focused);
        this.selected = sel;
        if (value instanceof XmlTreeNode) {
            XmlTreeNode node = (XmlTreeNode)value;
            this.elem = node.getElement();
            this.attr = node.getMainAttr();
        } else {
            this.attr = null;
            this.elem = null;
        }
        this.setIcon(this.elemIcon);
        return this;
    }

    @Override
    public void updateUI() {
        super.updateUI();
        this.setUI(UI);
    }

    private static class XmlTreeCellUI
    extends BasicLabelUI {
        private XmlTreeCellUI() {
        }

        @Override
        protected void installDefaults(JLabel label) {
        }

        @Override
        protected void paintEnabledText(JLabel l, Graphics g, String s, int textX, int textY) {
            XmlTreeCellRenderer r = (XmlTreeCellRenderer)l;
            Graphics2D g2d = (Graphics2D)g;
            Map<?, ?> hints = RSyntaxUtilities.getDesktopAntiAliasHints();
            if (hints != null) {
                g2d.addRenderingHints(hints);
            }
            g2d.setColor(l.getForeground());
            g2d.drawString(r.elem, textX, textY);
            if (r.attr != null) {
                textX += g2d.getFontMetrics().stringWidth(r.elem + " ");
                if (!r.selected) {
                    g2d.setColor(ATTR_COLOR);
                }
                g2d.drawString(r.attr, textX, textY);
            }
            g2d.dispose();
        }

        @Override
        protected void uninstallDefaults(JLabel label) {
        }
    }
}

