/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package core.c2profile;

import java.util.LinkedHashMap;
import org.yaml.snakeyaml.annotation.YamlClass;
import org.yaml.snakeyaml.annotation.YamlComment;

@YamlClass
public class C2Response {
    @YamlComment(Comment="\u54cd\u5e94\u72b6\u6001\u7801")
    public int responseCode = 200;
    @YamlComment(Comment="\u54cd\u5e94\u534f\u8bae\u5934 \u652f\u6301C2\u4fe1\u9053")
    public LinkedHashMap<String, String> responseHeaders = new LinkedHashMap();
    @YamlComment(Comment="\u54cd\u5e94Cookie \u652f\u6301C2\u4fe1\u9053")
    public LinkedHashMap<String, String> responseCookies;
    @YamlComment(Comment="\u54cd\u5e94\u5de6\u8fb9\u8ffd\u52a0\u6570\u636e")
    public byte[] responseLeftBody = "".getBytes();
    @YamlComment(Comment="\u8bf7\u6c42\u4e2d\u95f4\u6570\u636e \u652f\u6301C2\u4fe1\u9053")
    public String responseMiddleBody;
    @YamlComment(Comment="\u54cd\u5e94\u53f3\u8fb9\u8ffd\u52a0\u6570\u636e")
    public byte[] responseRightBody = "".getBytes();

    public C2Response() {
        this.responseCookies = new LinkedHashMap();
    }
}

