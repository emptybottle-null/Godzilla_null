/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.expression.spel.ast;

import java.util.List;
import java.util.StringJoiner;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.lang.Nullable;
import org.springframework.util.ClassUtils;

abstract class FormatHelper {
    FormatHelper() {
    }

    public static String formatMethodForMessage(String name, List<TypeDescriptor> argumentTypes) {
        StringJoiner sj = new StringJoiner(",", "(", ")");
        for (TypeDescriptor typeDescriptor : argumentTypes) {
            if (typeDescriptor != null) {
                sj.add(FormatHelper.formatClassNameForMessage(typeDescriptor.getType()));
                continue;
            }
            sj.add(FormatHelper.formatClassNameForMessage(null));
        }
        return name + sj.toString();
    }

    public static String formatClassNameForMessage(@Nullable Class<?> clazz) {
        return clazz != null ? ClassUtils.getQualifiedName(clazz) : "null";
    }
}

