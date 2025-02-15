/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.kitfox.svg.app.beans;

import java.awt.Rectangle;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ProportionalLayoutPanel
extends JPanel {
    public static final long serialVersionUID = 1L;
    float topMargin;
    float bottomMargin;
    float leftMargin;
    float rightMargin;
    private JPanel jPanel1;

    public ProportionalLayoutPanel() {
        this.initComponents();
    }

    @Override
    public void addNotify() {
        super.addNotify();
        Rectangle rect = this.getBounds();
        JOptionPane.showMessageDialog(this, "" + rect);
    }

    private void initComponents() {
        this.jPanel1 = new JPanel();
        this.setLayout(null);
        this.addComponentListener(new ComponentAdapter(){

            @Override
            public void componentResized(ComponentEvent evt) {
                ProportionalLayoutPanel.this.formComponentResized(evt);
            }

            @Override
            public void componentShown(ComponentEvent evt) {
                ProportionalLayoutPanel.this.formComponentShown(evt);
            }
        });
        this.add(this.jPanel1);
        this.jPanel1.setBounds(80, 90, 280, 160);
    }

    private void formComponentShown(ComponentEvent evt) {
        JOptionPane.showMessageDialog(this, "" + this.getWidth() + ", " + this.getHeight());
    }

    private void formComponentResized(ComponentEvent evt) {
    }
}

