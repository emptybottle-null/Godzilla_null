/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package core.c2profile.config;

import core.c2profile.CommandMode;
import org.yaml.snakeyaml.annotation.YamlComment;

public class BasicConfig {
    @YamlComment(Comment="\u5747\u8861Uri \u5982 /upload /login /download")
    public String[] uris = new String[0];
    @YamlComment(Comment="\u5747\u8861Proxy \u5982 http://127.0.0.1:8080  socks5://127.0.0.1:1088")
    public String[] proxys = new String[0];
    @YamlComment(Comment="\u5747\u8861Proxy \u5982 http://127.0.0.1:8080  socks5://127.0.0.1:1088")
    public CommandMode commandMode = CommandMode.EASY;
    @YamlComment(Comment="\u662f\u5426\u4f7f\u7528\u9ed8\u8ba4\u4ee3\u7406")
    public boolean useDefaultProxy = true;
    @YamlComment(Comment="\u662f\u5426\u5f00\u542f\u5747\u8861Uri \u4f1a\u968f\u673a\u4f7f\u7528\u5176\u4e2d\u4efb\u610f\u4e00\u4e2auri")
    public boolean enabledBalanceUris = false;
    @YamlComment(Comment="\u662f\u5426\u5f00\u542f\u5747\u8861Proxy \u4f1a\u968f\u673a\u4f7f\u7528\u5176\u4e2d\u4efb\u610f\u4e00\u4e2aproxy")
    public boolean enabledBalanceProxys = false;
    @YamlComment(Comment="\u662f\u5426\u5f00\u542fhttps\u8bc1\u4e66\u5f3a\u8ba4\u8bc1")
    public boolean enabledHttpsTrusted = true;
    @YamlComment(Comment="\u662f\u5426\u5408\u5e76\u8fd4\u56de\u5305\u7684 \"set-cookie\"")
    public boolean mergeResponseCookie = true;
    @YamlComment(Comment="\u662f\u5426\u5408\u5e76shell\u914d\u7f6e\u9875\u9762\u7684\u8bf7\u6c42\u5934")
    public boolean mergeBasicHeader = true;
    @YamlComment(Comment="\u5173\u95edshell\u540e\u662f\u5426\u6e05\u9664shell\u5728\u670d\u52a1\u5668\u7684\u7f13\u5b58")
    public boolean clearup = false;
}

