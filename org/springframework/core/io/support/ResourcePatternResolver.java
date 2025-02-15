/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core.io.support;

import java.io.IOException;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

public interface ResourcePatternResolver
extends ResourceLoader {
    public static final String CLASSPATH_ALL_URL_PREFIX = "classpath*:";

    public Resource[] getResources(String var1) throws IOException;
}

