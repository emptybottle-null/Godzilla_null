/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.yaml.snakeyaml.constructor;

import org.yaml.snakeyaml.nodes.Node;

public interface Construct {
    public Object construct(Node var1);

    public void construct2ndStep(Node var1, Object var2);
}

