/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package core.c2profile;

import core.c2profile.C2Request;
import core.c2profile.C2Response;
import core.c2profile.CommandMode;
import core.c2profile.config.BasicConfig;
import core.c2profile.config.CoreConfig;
import core.c2profile.location.ChannelLocationEnum;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Paths;
import java.util.HashMap;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.annotation.YamlClass;
import org.yaml.snakeyaml.annotation.YamlComment;
import org.yaml.snakeyaml.introspector.BeanAccess;

@YamlClass
public class C2Profile {
    public static final String CHANNEL_NAME = "@@@CHANNEL";
    @YamlComment(Comment="\u652f\u6301\u7684Payload")
    public String supportPayload = "ALL";
    @YamlComment(Comment="\u57fa\u7840\u914d\u7f6e")
    public BasicConfig basicConfig = new BasicConfig();
    @YamlComment(Comment="\u6838\u5fc3\u914d\u7f6e")
    public CoreConfig coreConfig = new CoreConfig();
    @YamlComment(Comment="\u4fe1\u9053\u5b9a\u4f4d\u65b9\u5f0f")
    public ChannelLocationEnum channelLocation;
    @YamlComment(Comment="\u9759\u6001\u53d8\u91cf")
    public HashMap staticVars = new HashMap();
    @YamlComment(Comment="Request\u914d\u7f6e")
    public C2Request request;
    @YamlComment(Comment="Response\u914d\u7f6e")
    public C2Response response;
    @YamlComment(Comment="Payload\u914d\u7f6e")
    public HashMap payloadConfigs;
    @YamlComment(Comment="Response\u914d\u7f6e")
    public HashMap pluginConfigs;

    public C2Profile() {
        this.channelLocation = ChannelLocationEnum.FIND;
        this.request = new C2Request();
        this.response = new C2Response();
        this.payloadConfigs = new HashMap();
        this.pluginConfigs = new HashMap();
    }

    public static void main(String[] args) throws Throwable {
        DumperOptions dumperOptions = new DumperOptions();
        dumperOptions.setProcessComments(true);
        dumperOptions.setPrettyFlow(true);
        C2Profile profile = new C2Profile();
        profile.channelLocation = ChannelLocationEnum.SUB;
        profile.basicConfig.mergeResponseCookie = true;
        profile.basicConfig.clearup = true;
        profile.basicConfig.commandMode = CommandMode.EASY;
        profile.basicConfig.useDefaultProxy = true;
        profile.coreConfig.errRetryNum = 100;
        profile.coreConfig.enabledErrRetry = true;
        profile.coreConfig.enabledDetailLog = true;
        profile.request.requestQueryString = CHANNEL_NAME;
        profile.request.enabledRequestBody = false;
        profile.response.responseCode = 403;
        profile.response.responseLeftBody = "<html>".getBytes();
        profile.response.responseRightBody = "</html>".getBytes();
        profile.response.responseMiddleBody = CHANNEL_NAME;
        Yaml yaml = new Yaml(dumperOptions);
        yaml.setBeanAccess(BeanAccess.FIELD);
        Files.write(Paths.get("c2.yaml", new String[0]), yaml.dumpAsMap(profile).getBytes(), new OpenOption[0]);
        System.out.println(yaml.dumpAsMap(profile));
    }
}

