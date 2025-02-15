/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core.env;

import java.util.function.Predicate;
import org.springframework.core.env.ProfilesParser;

@FunctionalInterface
public interface Profiles {
    public boolean matches(Predicate<String> var1);

    public static Profiles of(String ... profiles) {
        return ProfilesParser.parse(profiles);
    }
}

