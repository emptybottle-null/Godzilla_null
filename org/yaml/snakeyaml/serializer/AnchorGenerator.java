/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.yaml.snakeyaml.serializer;

import org.yaml.snakeyaml.nodes.Node;

public interface AnchorGenerator {
    public String nextAnchor(Node var1);
}

