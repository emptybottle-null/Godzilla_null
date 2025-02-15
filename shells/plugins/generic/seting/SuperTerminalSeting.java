/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package shells.plugins.generic.seting;

import com.jediterm.terminal.ui.JediTermWidget;
import core.Db;
import core.ui.component.dialog.GOptionPane;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import shells.plugins.generic.seting.TerminalSettingsProvider;
import util.Log;
import util.UiFunction;
import util.automaticBindClick;

public class SuperTerminalSeting
extends JPanel {
    private static final String SHOW_STR = "\u4f60\u597d hello \u2764\u2764\u2764";
    private JLabel fontLabel = new JLabel("\u5b57\u4f53: ");
    private JLabel sizeLabel = new JLabel("\u5b57\u4f53\u5927\u5c0f: ");
    private JLabel fontTypeLabel = new JLabel("\u5b57\u4f53\u7c7b\u578b : ");
    private JLabel terminalStyleLabel = new JLabel("\u7ec8\u7aef\u914d\u8272: ");
    private JComboBox<String> fontCombobox = new JComboBox<String>(UiFunction.getAllFontName());
    private JComboBox<Integer> fontSizeCombobox = new JComboBox<String>(UiFunction.getAllFontSize());
    private JComboBox<String> fontTypeComboBox = new JComboBox<String>(UiFunction.getAllFontType());
    private JComboBox<String> terminalStyleComboBox = new JComboBox<String>(TerminalSettingsProvider.getTerminalStyles());
    private JButton saveButton = new JButton("\u4fdd\u5b58\u914d\u7f6e");
    private JediTermWidget jediTerminal;

    public SuperTerminalSeting() {
        super(new BorderLayout());
        this.fontCombobox.setSelectedItem(TerminalSettingsProvider.getFontName());
        this.fontTypeComboBox.setSelectedItem(TerminalSettingsProvider.getFontType());
        this.fontSizeCombobox.setSelectedItem(String.valueOf(TerminalSettingsProvider.getFontSize()));
        this.terminalStyleComboBox.setSelectedItem(TerminalSettingsProvider.getTerminalStyle());
        this.jediTerminal = new JediTermWidget(new TerminalSettingsProvider());
        this.jediTerminal.getTerminal().writeCharacters(SHOW_STR);
        this.jediTerminal.getTerminal().nextLine();
        JPanel topPanel = new JPanel();
        topPanel.add(this.fontLabel);
        topPanel.add(this.fontCombobox);
        topPanel.add(this.fontTypeLabel);
        topPanel.add(this.fontTypeComboBox);
        topPanel.add(this.sizeLabel);
        topPanel.add(this.fontSizeCombobox);
        topPanel.add(this.terminalStyleLabel);
        topPanel.add(this.terminalStyleComboBox);
        topPanel.add(this.saveButton);
        JSplitPane splitPane = new JSplitPane(0);
        splitPane.setTopComponent(topPanel);
        splitPane.setBottomComponent(this.jediTerminal);
        ItemListener listener = e -> {
            this.jediTerminal = new JediTermWidget(new TerminalSettingsProvider(this.terminalStyleComboBox.getSelectedItem().toString()){

                @Override
                public Font getTerminalFont() {
                    return new Font(SuperTerminalSeting.this.fontCombobox.getSelectedItem().toString(), UiFunction.getFontType(SuperTerminalSeting.this.fontTypeComboBox.getSelectedItem().toString()), (int)this.getTerminalFontSize());
                }

                @Override
                public float getTerminalFontSize() {
                    return Integer.parseInt(SuperTerminalSeting.this.fontSizeCombobox.getSelectedItem().toString());
                }
            });
            try {
                this.jediTerminal.getTerminal().writeCharacters(SHOW_STR);
                this.jediTerminal.setSize(1024, 1024);
            } catch (Exception e2) {
                Log.error(e2);
            }
            splitPane.setBottomComponent(this.jediTerminal);
        };
        this.fontCombobox.addItemListener(listener);
        this.fontTypeComboBox.addItemListener(listener);
        this.fontSizeCombobox.addItemListener(listener);
        this.terminalStyleComboBox.addItemListener(listener);
        this.add(splitPane);
        automaticBindClick.bindJButtonClick(this, this);
    }

    private void saveButtonClick(ActionEvent actionEvent) {
        String fontName = this.fontCombobox.getSelectedItem().toString();
        String fontType = this.fontTypeComboBox.getSelectedItem().toString();
        int fontSize = Integer.parseInt(this.fontSizeCombobox.getSelectedItem().toString());
        String terminalStyle = this.terminalStyleComboBox.getSelectedItem().toString();
        if (Db.updateSetingKV("Terminal-FontName", fontName) && Db.updateSetingKV("Terminal-FontType", fontType) && Db.updateSetingKV("Terminal-FontSize", String.valueOf(fontSize)) && Db.updateSetingKV("Terminal-FontStyle", terminalStyle)) {
            GOptionPane.showMessageDialog(this, "\u4fee\u6539\u6210\u529f!", "\u63d0\u793a", 1);
        } else {
            GOptionPane.showMessageDialog(this, "\u4fee\u6539\u5931\u8d25!", "\u63d0\u793a", 2);
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.add(new SuperTerminalSeting());
        frame.setSize(1200, 1200);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(3);
    }
}

