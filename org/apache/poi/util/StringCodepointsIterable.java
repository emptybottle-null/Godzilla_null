/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.util;

import java.util.Iterator;
import org.apache.poi.util.Internal;

@Internal
public class StringCodepointsIterable
implements Iterable<String> {
    private final String string;

    public StringCodepointsIterable(String string) {
        this.string = string;
    }

    @Override
    public Iterator<String> iterator() {
        return new StringCodepointsIterator();
    }

    private class StringCodepointsIterator
    implements Iterator<String> {
        private int index = 0;

        private StringCodepointsIterator() {
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean hasNext() {
            return this.index < StringCodepointsIterable.this.string.length();
        }

        @Override
        public String next() {
            int codePoint = StringCodepointsIterable.this.string.codePointAt(this.index);
            this.index += Character.charCount(codePoint);
            return new String(Character.toChars(codePoint));
        }
    }
}

