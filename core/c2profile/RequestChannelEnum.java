/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package core.c2profile;

import core.EasyI18N;
import org.yaml.snakeyaml.annotation.YamlClass;

@YamlClass
public enum RequestChannelEnum {
    REQUEST_QUERY_STRING,
    REQUEST_URI_PARAMETER,
    REQUEST_HEADER,
    REQUEST_COOKIE,
    REQUEST_RAW_BODY,
    REQUEST_POST_FORM_PARAMETER;


    public String toString() {
        switch (this) {
            case REQUEST_QUERY_STRING: {
                return EasyI18N.getI18nString("\u8bf7\u6c42\u67e5\u8be2\u5b57\u7b26\u4e32");
            }
            case REQUEST_URI_PARAMETER: {
                return EasyI18N.getI18nString("\u8bf7\u6c42URI\u53c2\u6570");
            }
            case REQUEST_HEADER: {
                return EasyI18N.getI18nString("\u8bf7\u6c42\u534f\u8bae\u5934");
            }
            case REQUEST_COOKIE: {
                return EasyI18N.getI18nString("\u8bf7\u6c42Cookie");
            }
            case REQUEST_RAW_BODY: {
                return EasyI18N.getI18nString("\u8bf7\u6c42\u4f53");
            }
            case REQUEST_POST_FORM_PARAMETER: {
                return EasyI18N.getI18nString("\u8bf7\u6c42\u8868\u5355\u53c2\u6570");
            }
        }
        return EasyI18N.getI18nString("\u672a\u5b9a\u4e49\u7684\u679a\u4e3e\u9879");
    }
}

