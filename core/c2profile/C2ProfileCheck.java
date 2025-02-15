/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package core.c2profile;

import core.c2profile.C2Profile;
import core.c2profile.C2ProfileContext;
import core.c2profile.RequestChannelEnum;
import core.c2profile.RequestChannelType;
import core.c2profile.ResponseChannelEnum;
import core.c2profile.ResponseChannelType;
import core.c2profile.exception.UnsupportedOperationException;
import java.io.InputStream;
import java.util.concurrent.atomic.AtomicBoolean;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.introspector.BeanAccess;

public class C2ProfileCheck {
    public static boolean check(InputStream yamlStream) {
        DumperOptions dumperOptions = new DumperOptions();
        dumperOptions.setProcessComments(true);
        dumperOptions.setPrettyFlow(true);
        Yaml yaml = new Yaml(dumperOptions);
        yaml.setBeanAccess(BeanAccess.FIELD);
        C2Profile c2Profile = yaml.loadAs(yamlStream, C2Profile.class);
        C2ProfileContext ctx = new C2ProfileContext();
        ctx.c2Profile = c2Profile;
        C2ProfileCheck.loadRequstChannel(ctx);
        C2ProfileCheck.loadResponseChannel(ctx);
        return false;
    }

    public static void loadRequstChannel(C2ProfileContext ctx) throws UnsupportedOperationException {
        RequestChannelType _r;
        AtomicBoolean flag = new AtomicBoolean(false);
        ctx.c2Profile.request.requestUrlParameters.forEach((k, v) -> {
            if ("@@@CHANNEL".equalsIgnoreCase((String)v)) {
                RequestChannelType _r = new RequestChannelType(RequestChannelEnum.REQUEST_URI_PARAMETER, (String)k);
                if (ctx.requestChannelType != null) {
                    throw new UnsupportedOperationException("\u4fe1\u9053\u91cd\u590d\u5b9a\u4e49 \u5df2\u6709%s \u91cd\u590d%s", ctx.requestChannelType, _r);
                }
                ctx.requestChannelType = _r;
                flag.set(true);
            }
        });
        if ("@@@CHANNEL".equalsIgnoreCase(ctx.c2Profile.request.requestQueryString)) {
            _r = new RequestChannelType(RequestChannelEnum.REQUEST_QUERY_STRING, null);
            if (ctx.requestChannelType != null) {
                throw new UnsupportedOperationException("\u4fe1\u9053\u91cd\u590d\u5b9a\u4e49 \u5df2\u6709%s \u91cd\u590d%s", ctx.requestChannelType, _r);
            }
            ctx.requestChannelType = _r;
            flag.set(true);
        }
        ctx.c2Profile.request.requestHeaders.forEach((k, v) -> {
            if ("@@@CHANNEL".equalsIgnoreCase((String)v)) {
                RequestChannelType _r = new RequestChannelType(RequestChannelEnum.REQUEST_HEADER, (String)k);
                if (ctx.requestChannelType != null) {
                    throw new UnsupportedOperationException("\u4fe1\u9053\u91cd\u590d\u5b9a\u4e49 \u5df2\u6709%s \u91cd\u590d%s", ctx.requestChannelType, _r);
                }
                ctx.requestChannelType = _r;
                flag.set(true);
            }
        });
        ctx.c2Profile.request.requestCookies.forEach((k, v) -> {
            if ("@@@CHANNEL".equalsIgnoreCase((String)v)) {
                RequestChannelType _r = new RequestChannelType(RequestChannelEnum.REQUEST_COOKIE, (String)k);
                if (ctx.requestChannelType != null) {
                    throw new UnsupportedOperationException("\u4fe1\u9053\u91cd\u590d\u5b9a\u4e49 \u5df2\u6709%s \u91cd\u590d%s", ctx.requestChannelType, _r);
                }
                ctx.requestChannelType = _r;
                flag.set(true);
            }
        });
        ctx.c2Profile.request.requestFormParameters.forEach((k, v) -> {
            if ("@@@CHANNEL".equalsIgnoreCase((String)v)) {
                RequestChannelType _r = new RequestChannelType(RequestChannelEnum.REQUEST_POST_FORM_PARAMETER, (String)k);
                if (ctx.requestChannelType != null) {
                    throw new UnsupportedOperationException("\u4fe1\u9053\u91cd\u590d\u5b9a\u4e49 \u5df2\u6709%s \u91cd\u590d%s", ctx.requestChannelType, _r);
                }
                ctx.requestChannelType = _r;
                flag.set(true);
            }
        });
        if ("@@@CHANNEL".equalsIgnoreCase(ctx.c2Profile.request.requestMiddleBody)) {
            _r = new RequestChannelType(RequestChannelEnum.REQUEST_RAW_BODY, null);
            if (ctx.requestChannelType != null) {
                throw new UnsupportedOperationException("\u4fe1\u9053\u91cd\u590d\u5b9a\u4e49 \u5df2\u6709%s \u91cd\u590d%s", ctx.requestChannelType, _r);
            }
            ctx.requestChannelType = _r;
            flag.set(true);
        }
        if (!(ctx.c2Profile.request.enabledRequestBody || RequestChannelEnum.REQUEST_RAW_BODY != ctx.requestChannelType.requestChannelEnum && RequestChannelEnum.REQUEST_POST_FORM_PARAMETER != ctx.requestChannelType.requestChannelEnum)) {
            throw new UnsupportedOperationException("\u4fe1\u9053\u5728\u8bf7\u6c42Body\u5185 \u4f46enabledRequestBody\u5e76\u672a\u5f00\u542f");
        }
        if (!flag.get()) {
            throw new UnsupportedOperationException("\u672a\u5b9a\u4e49\u8bf7\u6c42\u4fe1\u9053");
        }
    }

    public static void loadResponseChannel(C2ProfileContext ctx) throws UnsupportedOperationException {
        AtomicBoolean flag = new AtomicBoolean(false);
        ctx.c2Profile.response.responseHeaders.forEach((k, v) -> {
            if ("@@@CHANNEL".equalsIgnoreCase((String)v)) {
                ResponseChannelType _r = new ResponseChannelType(ResponseChannelEnum.RESPONSE_HEADER, (String)k);
                if (ctx.responseChannelType != null) {
                    throw new UnsupportedOperationException("\u4fe1\u9053\u91cd\u590d\u5b9a\u4e49 \u5df2\u6709%s \u91cd\u590d%s", ctx.requestChannelType, _r);
                }
                ctx.responseChannelType = new ResponseChannelType(ResponseChannelEnum.RESPONSE_HEADER, (String)k);
                flag.set(true);
            }
        });
        ctx.c2Profile.response.responseCookies.forEach((k, v) -> {
            if ("@@@CHANNEL".equalsIgnoreCase((String)v)) {
                ResponseChannelType _r = new ResponseChannelType(ResponseChannelEnum.RESPONSE_COOKIE, (String)k);
                if (ctx.responseChannelType != null) {
                    throw new UnsupportedOperationException("\u4fe1\u9053\u91cd\u590d\u5b9a\u4e49 \u5df2\u6709%s \u91cd\u590d%s", ctx.requestChannelType, _r);
                }
                ctx.responseChannelType = _r;
                flag.set(true);
            }
        });
        if ("@@@CHANNEL".equalsIgnoreCase(ctx.c2Profile.response.responseMiddleBody)) {
            ResponseChannelType _r = new ResponseChannelType(ResponseChannelEnum.RESPONSE_RAW_BODY, null);
            if (ctx.responseChannelType != null) {
                throw new UnsupportedOperationException("\u4fe1\u9053\u91cd\u590d\u5b9a\u4e49 \u5df2\u6709%s \u91cd\u590d%s", ctx.requestChannelType, _r);
            }
            ctx.responseChannelType = _r;
            flag.set(true);
        }
        if (!flag.get()) {
            throw new UnsupportedOperationException("\u672a\u5b9a\u4e49\u8bf7\u6c42\u4fe1\u9053");
        }
    }
}

