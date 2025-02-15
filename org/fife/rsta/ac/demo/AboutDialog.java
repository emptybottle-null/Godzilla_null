/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.demo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.SystemColor;
import java.io.File;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.Spring;
import javax.swing.SpringLayout;
import javax.swing.UIManager;
import javax.swing.border.AbstractBorder;
import org.fife.rsta.ac.demo.DemoApp;
import org.fife.rsta.ac.java.buildpath.JarLibraryInfo;
import org.fife.rsta.ac.java.buildpath.LibraryInfo;
import org.fife.rsta.ac.perl.PerlLanguageSupport;

public class AboutDialog
extends JDialog {
    public AboutDialog(DemoApp parent) {
        super(parent);
        JPanel cp = new JPanel(new BorderLayout());
        Box box = Box.createVerticalBox();
        JPanel box2 = new JPanel();
        box2.setLayout(new BoxLayout(box2, 1));
        box2.setOpaque(true);
        box2.setBackground(Color.white);
        box2.setBorder(new TopBorder());
        JLabel label = new JLabel("Language Support Demo");
        label.setOpaque(true);
        label.setBackground(Color.white);
        Font labelFont = label.getFont();
        label.setFont(labelFont.deriveFont(1, 20.0f));
        this.addLeftAligned(label, box2);
        box2.add(Box.createVerticalStrut(5));
        JTextArea textArea = new JTextArea(6, 60);
        textArea.setFont(labelFont);
        textArea.setText("Version 0.2\n\nDemonstrates basic features of the RSTALanguageSupport library.\nNote that some features for some languages may not work unless your system is set up properly.\nFor example, Java code completion requires a JRE on your PATH, and Perl completion requires the Perl executable to be on your PATH.");
        textArea.setEditable(false);
        textArea.setBackground(Color.white);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setBorder(null);
        box2.add(textArea);
        box.add(box2);
        box.add(Box.createVerticalStrut(5));
        SpringLayout sl = new SpringLayout();
        JPanel temp = new JPanel(sl);
        JLabel perlLabel = new JLabel("Perl install location:");
        File loc = PerlLanguageSupport.getDefaultPerlInstallLocation();
        String text = loc == null ? null : loc.getAbsolutePath();
        JTextField perlField = this.createTextField(text);
        JLabel javaLabel = new JLabel("Java home:");
        String jre = null;
        LibraryInfo info = LibraryInfo.getMainJreJarInfo();
        if (info != null) {
            File jarFile = ((JarLibraryInfo)info).getJarFile();
            jre = jarFile.getParentFile().getParentFile().getAbsolutePath();
        }
        JTextField javaField = this.createTextField(jre);
        if (this.getComponentOrientation().isLeftToRight()) {
            temp.add(perlLabel);
            temp.add(perlField);
            temp.add(javaLabel);
            temp.add(javaField);
        } else {
            temp.add(perlField);
            temp.add(perlLabel);
            temp.add(javaField);
            temp.add(javaLabel);
        }
        AboutDialog.makeSpringCompactGrid(temp, 2, 2, 5, 5, 15, 5);
        box.add(temp);
        box.add(Box.createVerticalGlue());
        cp.add((Component)box, "North");
        JButton okButton = new JButton("OK");
        okButton.addActionListener(e -> this.setVisible(false));
        temp = new JPanel(new BorderLayout());
        temp.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        temp.add((Component)okButton, "After");
        cp.add((Component)temp, "South");
        this.getRootPane().setDefaultButton(okButton);
        this.setTitle("About RSTALanguageSupport Demo");
        this.setContentPane(cp);
        this.setDefaultCloseOperation(2);
        this.setModal(true);
        this.pack();
    }

    private JPanel addLeftAligned(Component toAdd, Container addTo) {
        JPanel temp = new JPanel(new BorderLayout());
        temp.setOpaque(false);
        temp.add(toAdd, "Before");
        addTo.add(temp);
        return temp;
    }

    private JTextField createTextField(String text) {
        JTextField field = new JTextField(text);
        field.setEditable(false);
        field.setBorder(null);
        field.setOpaque(false);
        return field;
    }

    private static SpringLayout.Constraints getConstraintsForCell(int row, int col, Container parent, int cols) {
        SpringLayout layout = (SpringLayout)parent.getLayout();
        Component c = parent.getComponent(row * cols + col);
        return layout.getConstraints(c);
    }

    private static void makeSpringCompactGrid(Container parent, int rows, int cols, int initialX, int initialY, int xPad, int yPad) {
        SpringLayout layout;
        try {
            layout = (SpringLayout)parent.getLayout();
        } catch (ClassCastException cce) {
            System.err.println("The first argument to makeCompactGrid must use SpringLayout.");
            return;
        }
        Spring x = Spring.constant(initialX);
        for (int c = 0; c < cols; ++c) {
            int r;
            Spring width = Spring.constant(0);
            for (r = 0; r < rows; ++r) {
                width = Spring.max(width, AboutDialog.getConstraintsForCell(r, c, parent, cols).getWidth());
            }
            for (r = 0; r < rows; ++r) {
                SpringLayout.Constraints constraints = AboutDialog.getConstraintsForCell(r, c, parent, cols);
                constraints.setX(x);
                constraints.setWidth(width);
            }
            x = Spring.sum(x, Spring.sum(width, Spring.constant(xPad)));
        }
        Spring y = Spring.constant(initialY);
        for (int r = 0; r < rows; ++r) {
            int c;
            Spring height = Spring.constant(0);
            for (c = 0; c < cols; ++c) {
                height = Spring.max(height, AboutDialog.getConstraintsForCell(r, c, parent, cols).getHeight());
            }
            for (c = 0; c < cols; ++c) {
                SpringLayout.Constraints constraints = AboutDialog.getConstraintsForCell(r, c, parent, cols);
                constraints.setY(y);
                constraints.setHeight(height);
            }
            y = Spring.sum(y, Spring.sum(height, Spring.constant(yPad)));
        }
        SpringLayout.Constraints pCons = layout.getConstraints(parent);
        pCons.setConstraint("South", y);
        pCons.setConstraint("East", x);
    }

    private static class TopBorder
    extends AbstractBorder {
        private TopBorder() {
        }

        @Override
        public Insets getBorderInsets(Component c) {
            return this.getBorderInsets(c, new Insets(0, 0, 0, 0));
        }

        @Override
        public Insets getBorderInsets(Component c, Insets insets) {
            insets.right = 5;
            insets.left = 5;
            insets.top = 5;
            insets.bottom = 6;
            return insets;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Color color = UIManager.getColor("controlShadow");
            if (color == null) {
                color = SystemColor.controlShadow;
            }
            g.setColor(color);
            g.drawLine(x, y + height - 1, x + width, y + height - 1);
        }
    }
}

