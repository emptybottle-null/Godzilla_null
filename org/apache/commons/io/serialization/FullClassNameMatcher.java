/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.io.serialization;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import org.apache.commons.io.serialization.ClassNameMatcher;

final class FullClassNameMatcher
implements ClassNameMatcher {
    private final Set<String> classesSet;

    public FullClassNameMatcher(String ... classes) {
        this.classesSet = Collections.unmodifiableSet(new HashSet<String>(Arrays.asList(classes)));
    }

    @Override
    public boolean matches(String className) {
        return this.classesSet.contains(className);
    }
}

