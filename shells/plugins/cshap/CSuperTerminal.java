/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package shells.plugins.cshap;

import core.annotation.PluginAnnotation;
import core.ui.component.dialog.GOptionPane;
import shells.plugins.generic.RealCmd;
import shells.plugins.generic.SuperTerminal;

@PluginAnnotation(payloadName="CShapDynamicPayload", Name="SuperTerminal", DisplayName="\u8d85\u7ea7\u7ec8\u7aef")
public class CSuperTerminal
extends SuperTerminal {
    @Override
    public RealCmd getRealCmd() {
        RealCmd plugin = (RealCmd)this.shellEntity.getFrame().getPlugin("RealCmd");
        if (plugin != null) {
            return plugin;
        }
        GOptionPane.showMessageDialog(super.getView(), "\u672a\u627e\u5230HttpProxy\u63d2\u4ef6!", "\u63d0\u793a", 0);
        return null;
    }
}

