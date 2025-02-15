/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package core.ui.component.dialog;

import core.EasyI18N;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import util.Log;
import util.functions;

public class HttpProgressBar
extends JFrame {
    private static final String CURRENT_VALUE_FORMAT = EasyI18N.getI18nString("\u5df2\u5b8c\u6210  %s Mb");
    private static final String MAX_VALUE_FORMAT = EasyI18N.getI18nString("\u5171  %s Mb");
    private final JPanel panel = new JPanel();
    private final JLabel currentValueLabel = new JLabel();
    private final JLabel maxValueLabel = new JLabel();
    private final JProgressBar progressBar;
    private boolean isClose;

    public HttpProgressBar(String title, int MaxValue) {
        this.progressBar = new JProgressBar(0, 0, MaxValue);
        this.panel.add(this.progressBar);
        this.panel.add(this.maxValueLabel);
        this.panel.add(this.currentValueLabel);
        this.maxValueLabel.setText(String.format(MAX_VALUE_FORMAT, String.format("%.4f", Float.valueOf((float)MaxValue / Float.valueOf(1048576.0f).floatValue()))));
        this.currentValueLabel.setText(String.format(CURRENT_VALUE_FORMAT, 0));
        this.add(this.panel);
        this.setTitle(title);
        this.progressBar.setStringPainted(true);
        this.setDefaultCloseOperation(2);
        this.setLocationRelativeTo(null);
        functions.setWindowSize(this, 430, 90);
        this.progressBar.updateUI();
        EasyI18N.installObject(this);
        this.setVisible(true);
    }

    public void setValue(int value) {
        this.progressBar.setValue(value);
        this.currentValueLabel.setText(String.format(CURRENT_VALUE_FORMAT, String.format("%.4f", Float.valueOf((float)this.progressBar.getValue() / Float.valueOf(1048576.0f).floatValue()))));
        Log.log(this.maxValueLabel.getText() + "\t" + this.currentValueLabel.getText(), new Object[0]);
        if (this.progressBar.getMaximum() <= this.progressBar.getValue()) {
            this.close();
        }
    }

    public boolean isClose() {
        return this.isClose;
    }

    public void close() {
        this.isClose = true;
        this.dispose();
    }

    @Override
    public void dispose() {
        this.isClose = true;
        super.dispose();
    }
}

