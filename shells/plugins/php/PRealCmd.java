/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package shells.plugins.php;

import core.annotation.PluginAnnotation;
import java.io.InputStream;
import shells.plugins.generic.RealCmd;
import util.Log;
import util.functions;

@PluginAnnotation(payloadName="PhpDynamicPayload", Name="RealCmd", DisplayName="\u865a\u62df\u7ec8\u7aef")
public class PRealCmd
extends RealCmd {
    private static final String CLASS_NAME = "plugin.RealCmd";

    @Override
    public byte[] readPlugin() {
        byte[] data = null;
        try {
            InputStream inputStream = this.getClass().getResourceAsStream("assets/realCmd.php");
            data = functions.readInputStream(inputStream);
            inputStream.close();
        } catch (Exception e) {
            Log.error(e);
        }
        return data;
    }

    @Override
    public boolean isTryStart() {
        return true;
    }

    @Override
    public String getClassName() {
        return CLASS_NAME;
    }
}

