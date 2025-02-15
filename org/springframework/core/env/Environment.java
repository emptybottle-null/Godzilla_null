/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core.env;

import org.springframework.core.env.Profiles;
import org.springframework.core.env.PropertyResolver;

public interface Environment
extends PropertyResolver {
    public String[] getActiveProfiles();

    public String[] getDefaultProfiles();

    @Deprecated
    public boolean acceptsProfiles(String ... var1);

    public boolean acceptsProfiles(Profiles var1);
}

