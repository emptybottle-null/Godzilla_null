/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.demo;

import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import org.fife.rsta.ac.demo.DemoRootPane;

public class DemoApp
extends JFrame {
    public DemoApp() {
        this.setRootPane(new DemoRootPane());
        this.setDefaultCloseOperation(3);
        this.setTitle("RSTA Language Support Demo Application");
        this.pack();
    }

    @Override
    public void setVisible(boolean visible) {
        super.setVisible(visible);
        if (visible) {
            ((DemoRootPane)this.getRootPane()).focusTextArea();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            Toolkit.getDefaultToolkit().setDynamicLayout(true);
            new DemoApp().setVisible(true);
        });
    }
}

