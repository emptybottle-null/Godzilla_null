/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package shells.plugins.cshap;

import core.annotation.PluginAnnotation;
import java.io.IOException;
import java.io.InputStream;
import shells.plugins.generic.PortScan;
import util.functions;

@PluginAnnotation(payloadName="CShapDynamicPayload", Name="PortScan", DisplayName="\u7aef\u53e3\u626b\u63cf")
public class CPortScan
extends PortScan {
    private static final String CLASS_NAME = "CProtScan.Run";

    @Override
    public byte[] readPlugin() throws IOException {
        InputStream inputStream = this.getClass().getResourceAsStream(String.format("assets/%s.dll", CLASS_NAME.substring(0, CLASS_NAME.indexOf("."))));
        return functions.readInputStreamAutoClose(inputStream);
    }

    @Override
    public String getClassName() {
        return CLASS_NAME;
    }
}

