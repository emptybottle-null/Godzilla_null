/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.io.input;

import java.io.Reader;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.function.IntPredicate;
import org.apache.commons.io.input.AbstractCharacterFilterReader;

public class CharacterSetFilterReader
extends AbstractCharacterFilterReader {
    private static IntPredicate toIntPredicate(Set<Integer> skip) {
        if (skip == null) {
            return SKIP_NONE;
        }
        Set<Integer> unmodifiableSet = Collections.unmodifiableSet(skip);
        return c -> unmodifiableSet.contains(c);
    }

    public CharacterSetFilterReader(Reader reader, Integer ... skip) {
        this(reader, new HashSet<Integer>(Arrays.asList(skip)));
    }

    public CharacterSetFilterReader(Reader reader, Set<Integer> skip) {
        super(reader, CharacterSetFilterReader.toIntPredicate(skip));
    }
}

