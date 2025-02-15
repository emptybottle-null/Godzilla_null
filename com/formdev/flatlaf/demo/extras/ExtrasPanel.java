/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.formdev.flatlaf.demo.extras;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.formdev.flatlaf.extras.components.FlatTriStateCheckBox;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;

public class ExtrasPanel
extends JPanel {
    private JLabel label4;
    private JLabel label1;
    private FlatTriStateCheckBox triStateCheckBox1;
    private JLabel triStateLabel1;
    private JLabel label2;
    private JPanel svgIconsPanel;
    private JLabel label3;

    public ExtrasPanel() {
        this.initComponents();
        this.triStateLabel1.setText(this.triStateCheckBox1.getState().toString());
        this.addSVGIcon("actions/copy.svg");
        this.addSVGIcon("actions/colors.svg");
        this.addSVGIcon("actions/execute.svg");
        this.addSVGIcon("actions/suspend.svg");
        this.addSVGIcon("actions/intentionBulb.svg");
        this.addSVGIcon("actions/quickfixOffBulb.svg");
        this.addSVGIcon("objects/abstractClass.svg");
        this.addSVGIcon("objects/abstractMethod.svg");
        this.addSVGIcon("objects/annotationtype.svg");
        this.addSVGIcon("objects/annotationtype.svg");
        this.addSVGIcon("objects/css.svg");
        this.addSVGIcon("objects/javaScript.svg");
        this.addSVGIcon("objects/xhtml.svg");
        this.addSVGIcon("errorDialog.svg");
        this.addSVGIcon("informationDialog.svg");
        this.addSVGIcon("warningDialog.svg");
    }

    private void addSVGIcon(String name) {
        this.svgIconsPanel.add(new JLabel(new FlatSVGIcon("com/formdev/flatlaf/demo/extras/svg/" + name)));
    }

    private void triStateCheckBox1Changed() {
        this.triStateLabel1.setText(this.triStateCheckBox1.getState().toString());
    }

    private void initComponents() {
        this.label4 = new JLabel();
        this.label1 = new JLabel();
        this.triStateCheckBox1 = new FlatTriStateCheckBox();
        this.triStateLabel1 = new JLabel();
        this.label2 = new JLabel();
        this.svgIconsPanel = new JPanel();
        this.label3 = new JLabel();
        this.setLayout(new MigLayout("insets dialog,hidemode 3", "[][][left]", "[]para[][][]"));
        this.label4.setText("Note: Components on this page require the flatlaf-extras library.");
        this.add((Component)this.label4, "cell 0 0 3 1");
        this.label1.setText("TriStateCheckBox:");
        this.add((Component)this.label1, "cell 0 1");
        this.triStateCheckBox1.setText("Three States");
        this.triStateCheckBox1.addActionListener(e -> this.triStateCheckBox1Changed());
        this.add((Component)this.triStateCheckBox1, "cell 1 1");
        this.triStateLabel1.setText("text");
        this.triStateLabel1.setEnabled(false);
        this.add((Component)this.triStateLabel1, "cell 2 1,gapx 30");
        this.label2.setText("SVG Icons:");
        this.add((Component)this.label2, "cell 0 2");
        this.svgIconsPanel.setLayout(new MigLayout("insets 0,hidemode 3", "[fill]", "[grow,center]"));
        this.add((Component)this.svgIconsPanel, "cell 1 2 2 1");
        this.label3.setText("The icons may change colors when switching to another theme.");
        this.add((Component)this.label3, "cell 1 3 2 1");
    }
}

