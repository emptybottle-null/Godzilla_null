/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ui;

import java.awt.Component;
import java.awt.Image;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.text.JTextComponent;
import org.fife.rsta.ui.DecorativeIconPanel;
import org.fife.rsta.ui.search.AbstractSearchDialog;

public class AssistanceIconPanel
extends DecorativeIconPanel
implements PropertyChangeListener {
    private static String assistanceAvailable;

    public AssistanceIconPanel(JComponent comp) {
        this.init(comp);
    }

    public AssistanceIconPanel(JComponent comp, int iconWidth) {
        super(iconWidth);
        this.init(comp);
    }

    private void init(JComponent comp) {
        if (comp != null) {
            ComponentListener listener = new ComponentListener();
            if (comp instanceof JComboBox) {
                JComboBox combo = (JComboBox)comp;
                Component c = combo.getEditor().getEditorComponent();
                if (c instanceof JTextComponent) {
                    JTextComponent tc = (JTextComponent)c;
                    tc.addFocusListener(listener);
                }
            } else {
                comp.addFocusListener(listener);
            }
            comp.addPropertyChangeListener("AssistanceImage", this);
        }
    }

    static String getAssistanceAvailableText() {
        if (assistanceAvailable == null) {
            assistanceAvailable = AbstractSearchDialog.getString("ContentAssistAvailable");
        }
        return assistanceAvailable;
    }

    @Override
    public void propertyChange(PropertyChangeEvent e) {
        Image img = (Image)e.getNewValue();
        this.setAssistanceEnabled(img);
    }

    public void setAssistanceEnabled(Image img) {
        if (img == null) {
            this.setIcon(null);
            this.setToolTipText(null);
        } else {
            this.setIcon(new ImageIcon(img));
            this.setToolTipText(AssistanceIconPanel.getAssistanceAvailableText());
        }
    }

    private class ComponentListener
    implements FocusListener {
        private ComponentListener() {
        }

        @Override
        public void focusGained(FocusEvent e) {
            AssistanceIconPanel.this.setShowIcon(true);
        }

        @Override
        public void focusLost(FocusEvent e) {
            AssistanceIconPanel.this.setShowIcon(false);
        }
    }
}

