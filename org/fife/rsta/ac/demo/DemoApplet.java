/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.demo;

import javax.swing.JApplet;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import org.fife.rsta.ac.demo.DemoRootPane;

public class DemoApplet
extends JApplet {
    @Override
    public void init() {
        super.init();
        SwingUtilities.invokeLater(() -> {
            String laf = UIManager.getSystemLookAndFeelClassName();
            try {
                UIManager.setLookAndFeel(laf);
            } catch (Exception e) {
                e.printStackTrace();
            }
            this.setRootPane(new DemoRootPane());
        });
    }

    @Override
    public void setVisible(boolean visible) {
        super.setVisible(visible);
        if (visible) {
            ((DemoRootPane)this.getRootPane()).focusTextArea();
        }
    }
}

