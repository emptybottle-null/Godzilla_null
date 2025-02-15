/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.log4j.lf5.viewer;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.apache.log4j.lf5.viewer.LogFactor5Dialog;

public class LogFactor5ErrorDialog
extends LogFactor5Dialog {
    public LogFactor5ErrorDialog(JFrame jframe, String message) {
        super(jframe, "Error", true);
        JButton ok = new JButton("Ok");
        ok.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {
                LogFactor5ErrorDialog.this.hide();
            }
        });
        JPanel bottom = new JPanel();
        bottom.setLayout(new FlowLayout());
        bottom.add(ok);
        JPanel main = new JPanel();
        main.setLayout(new GridBagLayout());
        this.wrapStringOnPanel(message, main);
        this.getContentPane().add((Component)main, "Center");
        this.getContentPane().add((Component)bottom, "South");
        this.show();
    }
}

