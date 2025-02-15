/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4.trie;

import java.util.Map;
import org.apache.commons.collections4.trie.AbstractPatriciaTrie;
import org.apache.commons.collections4.trie.analyzer.StringKeyAnalyzer;

public class PatriciaTrie<E>
extends AbstractPatriciaTrie<String, E> {
    private static final long serialVersionUID = 4446367780901817838L;

    public PatriciaTrie() {
        super(new StringKeyAnalyzer());
    }

    public PatriciaTrie(Map<? extends String, ? extends E> m) {
        super(new StringKeyAnalyzer(), m);
    }
}

