/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.formdev.flatlaf.demo;

import com.formdev.flatlaf.demo.ScrollablePanel;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Window;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import net.miginfocom.swing.MigLayout;

class OptionPanePanel
extends JPanel {
    private ShowDialogLinkLabel plainShowDialogLabel;
    private ShowDialogLinkLabel errorShowDialogLabel;
    private ShowDialogLinkLabel informationShowDialogLabel;
    private JOptionPane customOptionPane;

    OptionPanePanel() {
        this.initComponents();
        this.customOptionPane.setMessage(new Object[]{"string", "multi-\nline string", new JCheckBox("check box"), new JTextField("text field"), "more text"});
        this.customOptionPane.setOptions(new Object[]{new JCheckBox("check me"), "OK", "Cancel"});
    }

    private void initComponents() {
        JScrollPane scrollPane1 = new JScrollPane();
        ScrollablePanel panel9 = new ScrollablePanel();
        JLabel plainLabel = new JLabel();
        JPanel panel1 = new JPanel();
        JOptionPane plainOptionPane = new JOptionPane();
        this.plainShowDialogLabel = new ShowDialogLinkLabel();
        JLabel errorLabel = new JLabel();
        JPanel panel2 = new JPanel();
        JOptionPane errorOptionPane = new JOptionPane();
        this.errorShowDialogLabel = new ShowDialogLinkLabel();
        JLabel informationLabel = new JLabel();
        JPanel panel3 = new JPanel();
        JOptionPane informationOptionPane = new JOptionPane();
        this.informationShowDialogLabel = new ShowDialogLinkLabel();
        JLabel questionLabel = new JLabel();
        JPanel panel4 = new JPanel();
        JOptionPane questionOptionPane = new JOptionPane();
        ShowDialogLinkLabel questionShowDialogLabel = new ShowDialogLinkLabel();
        JLabel warningLabel = new JLabel();
        JPanel panel5 = new JPanel();
        JOptionPane warningOptionPane = new JOptionPane();
        ShowDialogLinkLabel warningShowDialogLabel = new ShowDialogLinkLabel();
        JLabel inputLabel = new JLabel();
        JPanel panel7 = new JPanel();
        JOptionPane inputOptionPane = new JOptionPane();
        ShowDialogLinkLabel inputShowDialogLabel = new ShowDialogLinkLabel();
        JLabel inputIconLabel = new JLabel();
        JPanel panel8 = new JPanel();
        JOptionPane inputIconOptionPane = new JOptionPane();
        ShowDialogLinkLabel inputIconShowDialogLabel = new ShowDialogLinkLabel();
        JLabel customLabel = new JLabel();
        JPanel panel6 = new JPanel();
        this.customOptionPane = new JOptionPane();
        ShowDialogLinkLabel customShowDialogLabel = new ShowDialogLinkLabel();
        this.setLayout(new BorderLayout());
        scrollPane1.setBorder(BorderFactory.createEmptyBorder());
        panel9.setLayout(new MigLayout("flowy,insets dialog,hidemode 3", "[][][fill]", "[top][top][top][top][top][top][top][top]"));
        plainLabel.setText("Plain");
        panel9.add((Component)plainLabel, "cell 0 0");
        panel1.setBorder(LineBorder.createGrayLineBorder());
        panel1.setLayout(new BorderLayout());
        plainOptionPane.setMessage("Hello world.");
        panel1.add((Component)plainOptionPane, "Center");
        panel9.add((Component)panel1, "cell 1 0");
        this.plainShowDialogLabel.setOptionPane(plainOptionPane);
        this.plainShowDialogLabel.setTitleLabel(plainLabel);
        panel9.add((Component)this.plainShowDialogLabel, "cell 2 0");
        errorLabel.setText("Error");
        panel9.add((Component)errorLabel, "cell 0 1");
        panel2.setBorder(LineBorder.createGrayLineBorder());
        panel2.setLayout(new BorderLayout());
        errorOptionPane.setMessageType(0);
        errorOptionPane.setOptionType(2);
        errorOptionPane.setMessage("Your PC ran into a problem. Buy a new one.");
        panel2.add((Component)errorOptionPane, "Center");
        panel9.add((Component)panel2, "cell 1 1");
        this.errorShowDialogLabel.setTitleLabel(errorLabel);
        this.errorShowDialogLabel.setOptionPane(errorOptionPane);
        panel9.add((Component)this.errorShowDialogLabel, "cell 2 1");
        informationLabel.setText("Information");
        panel9.add((Component)informationLabel, "cell 0 2");
        panel3.setBorder(LineBorder.createGrayLineBorder());
        panel3.setLayout(new BorderLayout());
        informationOptionPane.setMessageType(1);
        informationOptionPane.setOptionType(0);
        informationOptionPane.setMessage("Text with\nmultiple lines\n(use \\n to separate lines)");
        panel3.add((Component)informationOptionPane, "Center");
        panel9.add((Component)panel3, "cell 1 2");
        this.informationShowDialogLabel.setOptionPane(informationOptionPane);
        this.informationShowDialogLabel.setTitleLabel(informationLabel);
        panel9.add((Component)this.informationShowDialogLabel, "cell 2 2");
        questionLabel.setText("Question");
        panel9.add((Component)questionLabel, "cell 0 3");
        panel4.setBorder(LineBorder.createGrayLineBorder());
        panel4.setLayout(new BorderLayout());
        questionOptionPane.setMessageType(3);
        questionOptionPane.setOptionType(1);
        questionOptionPane.setMessage("Answer the question. What question? Don't know. Just writing useless text to make this longer than 80 characters.");
        panel4.add((Component)questionOptionPane, "Center");
        panel9.add((Component)panel4, "cell 1 3");
        questionShowDialogLabel.setOptionPane(questionOptionPane);
        questionShowDialogLabel.setTitleLabel(questionLabel);
        panel9.add((Component)questionShowDialogLabel, "cell 2 3");
        warningLabel.setText("Warning");
        panel9.add((Component)warningLabel, "cell 0 4");
        panel5.setBorder(LineBorder.createGrayLineBorder());
        panel5.setLayout(new BorderLayout());
        warningOptionPane.setMessageType(2);
        warningOptionPane.setOptionType(2);
        warningOptionPane.setMessage("<html>I like <b>bold</b>,<br> and I like <i>italic</i>,<br> and I like to have<br> many lines.<br> Lots of lines.");
        panel5.add((Component)warningOptionPane, "Center");
        panel9.add((Component)panel5, "cell 1 4");
        warningShowDialogLabel.setOptionPane(warningOptionPane);
        warningShowDialogLabel.setTitleLabel(warningLabel);
        panel9.add((Component)warningShowDialogLabel, "cell 2 4");
        inputLabel.setText("Input");
        panel9.add((Component)inputLabel, "cell 0 5");
        panel7.setBorder(LineBorder.createGrayLineBorder());
        panel7.setLayout(new BorderLayout());
        inputOptionPane.setWantsInput(true);
        inputOptionPane.setOptionType(2);
        inputOptionPane.setMessage("Enter whatever you want:");
        panel7.add((Component)inputOptionPane, "Center");
        panel9.add((Component)panel7, "cell 1 5");
        inputShowDialogLabel.setOptionPane(inputOptionPane);
        inputShowDialogLabel.setTitleLabel(inputLabel);
        panel9.add((Component)inputShowDialogLabel, "cell 2 5");
        inputIconLabel.setText("Input + icon");
        panel9.add((Component)inputIconLabel, "cell 0 6");
        panel8.setBorder(LineBorder.createGrayLineBorder());
        panel8.setLayout(new BorderLayout());
        inputIconOptionPane.setMessageType(1);
        inputIconOptionPane.setWantsInput(true);
        inputIconOptionPane.setOptionType(2);
        inputIconOptionPane.setMessage("Enter something:");
        panel8.add((Component)inputIconOptionPane, "Center");
        panel9.add((Component)panel8, "cell 1 6");
        inputIconShowDialogLabel.setTitleLabel(inputIconLabel);
        inputIconShowDialogLabel.setOptionPane(inputIconOptionPane);
        panel9.add((Component)inputIconShowDialogLabel, "cell 2 6");
        customLabel.setText("Custom");
        panel9.add((Component)customLabel, "cell 0 7");
        panel6.setBorder(LineBorder.createGrayLineBorder());
        panel6.setLayout(new BorderLayout());
        this.customOptionPane.setIcon(UIManager.getIcon("Tree.leafIcon"));
        panel6.add((Component)this.customOptionPane, "Center");
        panel9.add((Component)panel6, "cell 1 7");
        customShowDialogLabel.setOptionPane(this.customOptionPane);
        customShowDialogLabel.setTitleLabel(customLabel);
        panel9.add((Component)customShowDialogLabel, "cell 2 7");
        scrollPane1.setViewportView(panel9);
        this.add((Component)scrollPane1, "Center");
    }

    private static class ShowDialogLinkLabel
    extends JLabel {
        private JLabel titleLabel;
        private JOptionPane optionPane;

        ShowDialogLinkLabel() {
            this.setText("<html><a href=\"#\">Show dialog</a></html>");
            this.addMouseListener(new MouseAdapter(){

                @Override
                public void mouseClicked(MouseEvent e) {
                    this.showDialog();
                }
            });
        }

        private void showDialog() {
            Window window = SwingUtilities.windowForComponent(this);
            if (this.optionPane.getWantsInput()) {
                JOptionPane.showInputDialog(window, this.optionPane.getMessage(), this.titleLabel.getText() + " Title", this.optionPane.getMessageType(), this.optionPane.getIcon(), null, null);
            } else {
                JOptionPane.showOptionDialog(window, this.optionPane.getMessage(), this.titleLabel.getText() + " Title", this.optionPane.getOptionType(), this.optionPane.getMessageType(), this.optionPane.getIcon(), this.optionPane.getOptions(), this.optionPane.getInitialValue());
            }
        }

        public JLabel getTitleLabel() {
            return this.titleLabel;
        }

        public void setTitleLabel(JLabel titleLabel) {
            this.titleLabel = titleLabel;
        }

        public JOptionPane getOptionPane() {
            return this.optionPane;
        }

        public void setOptionPane(JOptionPane optionPane) {
            this.optionPane = optionPane;
        }
    }
}

