/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package core.c2profile.config;

import org.yaml.snakeyaml.annotation.YamlComment;

public class CoreConfig {
    @YamlComment(Comment="\u662f\u5426\u540c\u6b65\u8bf7\u6c42 \u540c\u4e00\u65f6\u95f4\u53ea\u5141\u8bb8\u4e00\u4e2ahttp\u8bf7\u6c42")
    public boolean requestSync;
    @YamlComment(Comment="\u662f\u5426\u5f00\u542f\u5fc3\u8df3 \u67d0\u4e9b\u7f51\u7ad9\u7684session\u8fc7\u671f\u65f6\u95f4\u6bd4\u8f83\u77ed \u9700\u8981\u5f00\u542f\u52a8\u6001\u5fc3\u8df3")
    public boolean enabledHeartbeat;
    @YamlComment(Comment="\u662f\u5426\u5f00\u542f\u9519\u8bef\u91cd\u8bd5  \u4e00\u822c\u7528\u5728\u8d1f\u8f7d\u5747\u8861")
    public boolean enabledErrRetry;
    @YamlComment(Comment="\u662f\u5426\u5f00\u542f\u64cd\u4f5c\u7f13\u5b58 \u5982\u6587\u4ef6\u7f13\u5b58 \u547d\u4ee4\u6267\u884c\u7f13\u5b58")
    public boolean enabledOperationCache;
    @YamlComment(Comment="\u662f\u5426\u5f00\u542f\u8be6\u7ec6\u65e5\u5fd7")
    public boolean enabledDetailLog;
    @YamlComment(Comment="\u662f\u5426\u5f00\u542f\u4e0a\u5e1d\u6a21\u5f0f")
    public boolean enabledGodMode;
    @YamlComment(Comment="\u9519\u8bef\u91cd\u8bd5\u6700\u5927\u6b21\u6570")
    public int errRetryNum;
    @YamlComment(Comment="\u5fc3\u8df3\u5305\u5ef6\u8fdf ms")
    public long heartbeatSleepTime;
    @YamlComment(Comment="\u8df3\u5305\u6296\u52a8 \u767e\u5206\u6bd4")
    public String heartbeatJitter;
}

