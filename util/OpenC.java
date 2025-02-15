/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package util;

import core.ApplicationContext;
import core.ui.component.dialog.GOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JCheckBox;

public class OpenC
implements ActionListener {
    private final String keyString;
    private final JCheckBox checkBox;

    public OpenC(String key, JCheckBox checkBox) {
        this.keyString = key;
        this.checkBox = checkBox;
    }

    @Override
    public void actionPerformed(ActionEvent paramActionEvent) {
        if (ApplicationContext.setOpenC(this.keyString, this.checkBox.isSelected())) {
            GOptionPane.showMessageDialog(null, "\u4fee\u6539\u6210\u529f!", "\u63d0\u793a", 1);
        } else {
            GOptionPane.showMessageDialog(null, "\u4fee\u6539\u5931\u8d25!", "\u63d0\u793a", 2);
        }
    }
}

