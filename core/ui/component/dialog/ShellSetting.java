/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package core.ui.component.dialog;

import core.ui.MainActivity;
import javax.swing.JDialog;

public class ShellSetting
extends JDialog {
    public ShellSetting(String id) {
        super(MainActivity.getFrame(), "Shell Setting", true);
        core.ui.component.frame.ShellSetting shellSetting = new core.ui.component.frame.ShellSetting(id, "/");
    }
}

