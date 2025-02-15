/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package core.c2profile;

import core.EasyI18N;
import org.yaml.snakeyaml.annotation.YamlClass;

@YamlClass
public enum ResponseChannelEnum {
    RESPONSE_HEADER,
    RESPONSE_COOKIE,
    RESPONSE_RAW_BODY;


    public String toString() {
        switch (this) {
            case RESPONSE_HEADER: {
                return EasyI18N.getI18nString("\u8fd4\u56de\u534f\u8bae\u5934");
            }
            case RESPONSE_COOKIE: {
                EasyI18N.getI18nString("\u8fd4\u56deCookie");
            }
            case RESPONSE_RAW_BODY: {
                EasyI18N.getI18nString("\u8fd4\u56de\u4f53");
            }
        }
        return EasyI18N.getI18nString("\u672a\u5b9a\u4e49\u7684\u679a\u4e3e\u9879");
    }
}

