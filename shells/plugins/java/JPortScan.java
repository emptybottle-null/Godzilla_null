/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package shells.plugins.java;

import core.annotation.PluginAnnotation;
import java.io.IOException;
import java.io.InputStream;
import shells.plugins.generic.PortScan;
import util.functions;

@PluginAnnotation(payloadName="JavaDynamicPayload", Name="PortScan", DisplayName="\u7aef\u53e3\u626b\u63cf")
public class JPortScan
extends PortScan {
    private static final String CLASS_NAME = "plugin.JPortScan";

    @Override
    public byte[] readPlugin() throws IOException {
        InputStream inputStream = this.getClass().getResourceAsStream(String.format("assets/%s.classs", CLASS_NAME.substring(CLASS_NAME.indexOf(".") + 1)));
        return functions.readInputStreamAutoClose(inputStream);
    }

    @Override
    public String getClassName() {
        return CLASS_NAME;
    }
}

