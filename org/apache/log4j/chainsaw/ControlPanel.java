/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.log4j.chainsaw;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import org.apache.log4j.chainsaw.ExitAction;
import org.apache.log4j.chainsaw.MyTableModel;

class ControlPanel
extends JPanel {
    private static final Logger LOG = Logger.getLogger(ControlPanel.class);

    ControlPanel(final MyTableModel aModel) {
        this.setBorder(BorderFactory.createTitledBorder("Controls: "));
        GridBagLayout gridbag = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        this.setLayout(gridbag);
        c.ipadx = 5;
        c.ipady = 5;
        c.gridx = 0;
        c.anchor = 13;
        c.gridy = 0;
        JLabel label = new JLabel("Filter Level:");
        gridbag.setConstraints(label, c);
        this.add(label);
        ++c.gridy;
        label = new JLabel("Filter Thread:");
        gridbag.setConstraints(label, c);
        this.add(label);
        ++c.gridy;
        label = new JLabel("Filter Logger:");
        gridbag.setConstraints(label, c);
        this.add(label);
        ++c.gridy;
        label = new JLabel("Filter NDC:");
        gridbag.setConstraints(label, c);
        this.add(label);
        ++c.gridy;
        label = new JLabel("Filter Message:");
        gridbag.setConstraints(label, c);
        this.add(label);
        c.weightx = 1.0;
        c.gridx = 1;
        c.anchor = 17;
        c.gridy = 0;
        Level[] allPriorities = new Level[]{Level.FATAL, Level.ERROR, Level.WARN, Level.INFO, Level.DEBUG, Level.TRACE};
        final JComboBox<Level> priorities = new JComboBox<Level>(allPriorities);
        Level lowest = allPriorities[allPriorities.length - 1];
        priorities.setSelectedItem(lowest);
        aModel.setPriorityFilter(lowest);
        gridbag.setConstraints(priorities, c);
        this.add(priorities);
        priorities.setEditable(false);
        priorities.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent aEvent) {
                aModel.setPriorityFilter((Priority)priorities.getSelectedItem());
            }
        });
        c.fill = 2;
        ++c.gridy;
        final JTextField threadField = new JTextField("");
        threadField.getDocument().addDocumentListener(new DocumentListener(){

            public void insertUpdate(DocumentEvent aEvent) {
                aModel.setThreadFilter(threadField.getText());
            }

            public void removeUpdate(DocumentEvent aEvente) {
                aModel.setThreadFilter(threadField.getText());
            }

            public void changedUpdate(DocumentEvent aEvent) {
                aModel.setThreadFilter(threadField.getText());
            }
        });
        gridbag.setConstraints(threadField, c);
        this.add(threadField);
        ++c.gridy;
        final JTextField catField = new JTextField("");
        catField.getDocument().addDocumentListener(new DocumentListener(){

            public void insertUpdate(DocumentEvent aEvent) {
                aModel.setCategoryFilter(catField.getText());
            }

            public void removeUpdate(DocumentEvent aEvent) {
                aModel.setCategoryFilter(catField.getText());
            }

            public void changedUpdate(DocumentEvent aEvent) {
                aModel.setCategoryFilter(catField.getText());
            }
        });
        gridbag.setConstraints(catField, c);
        this.add(catField);
        ++c.gridy;
        final JTextField ndcField = new JTextField("");
        ndcField.getDocument().addDocumentListener(new DocumentListener(){

            public void insertUpdate(DocumentEvent aEvent) {
                aModel.setNDCFilter(ndcField.getText());
            }

            public void removeUpdate(DocumentEvent aEvent) {
                aModel.setNDCFilter(ndcField.getText());
            }

            public void changedUpdate(DocumentEvent aEvent) {
                aModel.setNDCFilter(ndcField.getText());
            }
        });
        gridbag.setConstraints(ndcField, c);
        this.add(ndcField);
        ++c.gridy;
        final JTextField msgField = new JTextField("");
        msgField.getDocument().addDocumentListener(new DocumentListener(){

            public void insertUpdate(DocumentEvent aEvent) {
                aModel.setMessageFilter(msgField.getText());
            }

            public void removeUpdate(DocumentEvent aEvent) {
                aModel.setMessageFilter(msgField.getText());
            }

            public void changedUpdate(DocumentEvent aEvent) {
                aModel.setMessageFilter(msgField.getText());
            }
        });
        gridbag.setConstraints(msgField, c);
        this.add(msgField);
        c.weightx = 0.0;
        c.fill = 2;
        c.anchor = 13;
        c.gridx = 2;
        c.gridy = 0;
        JButton exitButton = new JButton("Exit");
        exitButton.setMnemonic('x');
        exitButton.addActionListener(ExitAction.INSTANCE);
        gridbag.setConstraints(exitButton, c);
        this.add(exitButton);
        ++c.gridy;
        JButton clearButton = new JButton("Clear");
        clearButton.setMnemonic('c');
        clearButton.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent aEvent) {
                aModel.clear();
            }
        });
        gridbag.setConstraints(clearButton, c);
        this.add(clearButton);
        ++c.gridy;
        final JButton toggleButton = new JButton("Pause");
        toggleButton.setMnemonic('p');
        toggleButton.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent aEvent) {
                aModel.toggle();
                toggleButton.setText(aModel.isPaused() ? "Resume" : "Pause");
            }
        });
        gridbag.setConstraints(toggleButton, c);
        this.add(toggleButton);
    }
}

