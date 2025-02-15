/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package core.c2profile;

import core.EasyI18N;
import core.c2profile.RequestChannelEnum;

public class RequestChannelType {
    public RequestChannelEnum requestChannelEnum;
    public String name;

    public RequestChannelType(RequestChannelEnum requestChannelEnum, String name) {
        this.requestChannelEnum = requestChannelEnum;
        this.name = name;
    }

    public String toString() {
        return EasyI18N.getI18nString("\u901a\u9053\u4f4d\u7f6e: %s Name: %s", new Object[]{this.requestChannelEnum, this.name});
    }
}

