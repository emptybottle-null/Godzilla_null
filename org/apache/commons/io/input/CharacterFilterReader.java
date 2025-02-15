/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.io.input;

import java.io.Reader;
import java.util.function.IntPredicate;
import org.apache.commons.io.input.AbstractCharacterFilterReader;

public class CharacterFilterReader
extends AbstractCharacterFilterReader {
    public CharacterFilterReader(Reader reader, int skip) {
        super(reader, c -> c == skip);
    }

    public CharacterFilterReader(Reader reader, IntPredicate skip) {
        super(reader, skip);
    }
}

