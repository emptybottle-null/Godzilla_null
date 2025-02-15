/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package core.c2profile;

import core.EasyI18N;
import core.c2profile.ResponseChannelEnum;

public class ResponseChannelType {
    public ResponseChannelEnum responseChannelEnum;
    public String name;

    public ResponseChannelType(ResponseChannelEnum responseChannelEnum, String name) {
        this.responseChannelEnum = responseChannelEnum;
        this.name = name;
    }

    public String toString() {
        return EasyI18N.getI18nString("\u901a\u9053\u4f4d\u7f6e: %s Name: %s", new Object[]{this.responseChannelEnum, this.name});
    }
}

