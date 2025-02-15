/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.formdev.flatlaf.demo;

import java.awt.Component;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.MutableComboBoxModel;
import javax.swing.UIManager;
import javax.swing.plaf.basic.BasicComboBoxRenderer;

public class LookAndFeelsComboBox
extends JComboBox<UIManager.LookAndFeelInfo> {
    private final PropertyChangeListener lafListener = this::lafChanged;

    public LookAndFeelsComboBox() {
        this.setRenderer(new BasicComboBoxRenderer(){

            @Override
            public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                value = value != null ? ((UIManager.LookAndFeelInfo)value).getName() : UIManager.getLookAndFeel().getName();
                return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            }
        });
    }

    public void addLookAndFeel(String name, String className) {
        this.getMutableModel().addElement(new UIManager.LookAndFeelInfo(name, className));
    }

    public String getSelectedLookAndFeel() {
        Object sel = this.getSelectedItem();
        return sel instanceof UIManager.LookAndFeelInfo ? ((UIManager.LookAndFeelInfo)sel).getClassName() : null;
    }

    public void setSelectedLookAndFeel(String className) {
        this.setSelectedIndex(this.getIndexOfLookAndFeel(className));
    }

    public void selectedCurrentLookAndFeel() {
        this.setSelectedLookAndFeel(UIManager.getLookAndFeel().getClass().getName());
    }

    public void removeLookAndFeel(String className) {
        int index = this.getIndexOfLookAndFeel(className);
        if (index >= 0) {
            this.getMutableModel().removeElementAt(index);
        }
    }

    public int getIndexOfLookAndFeel(String className) {
        ComboBoxModel model = this.getModel();
        int size = model.getSize();
        for (int i = 0; i < size; ++i) {
            if (!className.equals(((UIManager.LookAndFeelInfo)model.getElementAt(i)).getClassName())) continue;
            return i;
        }
        return -1;
    }

    private MutableComboBoxModel<UIManager.LookAndFeelInfo> getMutableModel() {
        return (MutableComboBoxModel)this.getModel();
    }

    @Override
    public void addNotify() {
        super.addNotify();
        this.selectedCurrentLookAndFeel();
        UIManager.addPropertyChangeListener(this.lafListener);
    }

    @Override
    public void removeNotify() {
        super.removeNotify();
        UIManager.removePropertyChangeListener(this.lafListener);
    }

    void lafChanged(PropertyChangeEvent e) {
        if ("lookAndFeel".equals(e.getPropertyName())) {
            this.selectedCurrentLookAndFeel();
        }
    }
}

