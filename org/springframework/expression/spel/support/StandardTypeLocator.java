/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.expression.spel.support;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.expression.EvaluationException;
import org.springframework.expression.TypeLocator;
import org.springframework.expression.spel.SpelEvaluationException;
import org.springframework.expression.spel.SpelMessage;
import org.springframework.lang.Nullable;
import org.springframework.util.ClassUtils;

public class StandardTypeLocator
implements TypeLocator {
    @Nullable
    private final ClassLoader classLoader;
    private final List<String> knownPackagePrefixes = new ArrayList<String>(1);

    public StandardTypeLocator() {
        this(ClassUtils.getDefaultClassLoader());
    }

    public StandardTypeLocator(@Nullable ClassLoader classLoader) {
        this.classLoader = classLoader;
        this.registerImport("java.lang");
    }

    public void registerImport(String prefix) {
        this.knownPackagePrefixes.add(prefix);
    }

    public void removeImport(String prefix) {
        this.knownPackagePrefixes.remove(prefix);
    }

    public List<String> getImportPrefixes() {
        return Collections.unmodifiableList(this.knownPackagePrefixes);
    }

    @Override
    public Class<?> findType(String typeName) throws EvaluationException {
        String nameToLookup = typeName;
        try {
            return ClassUtils.forName(nameToLookup, this.classLoader);
        } catch (ClassNotFoundException classNotFoundException) {
            for (String prefix : this.knownPackagePrefixes) {
                try {
                    nameToLookup = prefix + '.' + typeName;
                    return ClassUtils.forName(nameToLookup, this.classLoader);
                } catch (ClassNotFoundException classNotFoundException2) {
                }
            }
            throw new SpelEvaluationException(SpelMessage.TYPE_NOT_FOUND, typeName);
        }
    }
}

