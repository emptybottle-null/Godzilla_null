/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package core.c2profile;

import java.util.LinkedHashMap;
import org.yaml.snakeyaml.annotation.YamlClass;
import org.yaml.snakeyaml.annotation.YamlComment;

@YamlClass
public class C2Request {
    @YamlComment(Comment="Request \u67e5\u8be2\u5b57\u7b26\u4e32 \u652f\u6301C2\u4fe1\u9053")
    public String requestQueryString = "";
    @YamlComment(Comment="Request Method")
    public String requestMethod = "POST";
    @YamlComment(Comment="\u662f\u5426\u5f00\u542fRequest Body\u5199\u5165")
    public boolean enabledRequestBody;
    @YamlComment(Comment="\u8bf7\u6c42url\u53c2\u6570 \u652f\u6301C2\u4fe1\u9053")
    public LinkedHashMap<String, String> requestUrlParameters;
    @YamlComment(Comment="\u8bf7\u6c42\u8868\u5355\u53c2\u6570 \u652f\u6301C2\u4fe1\u9053")
    public LinkedHashMap<String, String> requestFormParameters;
    @YamlComment(Comment="\u8bf7\u6c42Cookies \u652f\u6301C2\u4fe1\u9053")
    public LinkedHashMap<String, String> requestCookies;
    @YamlComment(Comment="\u8bf7\u6c42\u534f\u8bae\u5934 \u652f\u6301C2\u4fe1\u9053")
    public LinkedHashMap<String, String> requestHeaders = new LinkedHashMap();
    @YamlComment(Comment="\u8bf7\u6c42\u5de6\u8fb9\u8ffd\u52a0\u6570\u636e")
    public byte[] requestLeftBody;
    @YamlComment(Comment="\u8bf7\u6c42\u4e2d\u95f4\u6570\u636e \u652f\u6301C2\u4fe1\u9053")
    public String requestMiddleBody;
    @YamlComment(Comment="\u8bf7\u6c42\u53f3\u8fb9\u8ffd\u52a0\u6570\u636e")
    public byte[] requestRightBody;

    public C2Request() {
        this.requestFormParameters = new LinkedHashMap();
        this.requestUrlParameters = new LinkedHashMap();
        this.requestCookies = new LinkedHashMap();
        this.requestLeftBody = "".getBytes();
        this.requestRightBody = "".getBytes();
    }
}

