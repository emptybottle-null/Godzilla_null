/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package core.imp;

import core.shell.ShellEntity;
import javax.swing.JPanel;

public interface Plugin {
    public void init(ShellEntity var1);

    public JPanel getView();
}

