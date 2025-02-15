/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.jediterm.terminal.debug;

import com.jediterm.terminal.debug.DebugBufferType;
import com.jediterm.terminal.ui.TerminalSession;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.Timer;

public class BufferPanel
extends JPanel {
    public BufferPanel(final TerminalSession terminal) {
        super(new BorderLayout());
        final JTextArea area = new JTextArea();
        area.setEditable(false);
        this.add((Component)area, "North");
        DebugBufferType[] choices = DebugBufferType.values();
        final JComboBox<DebugBufferType> chooser = new JComboBox<DebugBufferType>(choices);
        this.add(chooser, "North");
        area.setFont(Font.decode("Monospaced-14"));
        this.add((Component)new JScrollPane(area), "Center");
        class Updater
        implements ActionListener,
        ItemListener {
            private String myLastUpdate = "";

            Updater() {
            }

            void update() {
                DebugBufferType type = (DebugBufferType)((Object)chooser.getSelectedItem());
                String text = terminal.getBufferText(type);
                if (!text.equals(this.myLastUpdate)) {
                    area.setText(text);
                    this.myLastUpdate = text;
                }
            }

            @Override
            public void actionPerformed(ActionEvent e) {
                this.update();
            }

            @Override
            public void itemStateChanged(ItemEvent e) {
                this.update();
            }
        }
        Updater up = new Updater();
        chooser.addItemListener(up);
        Timer timer = new Timer(1000, up);
        timer.setRepeats(true);
        timer.start();
    }
}

