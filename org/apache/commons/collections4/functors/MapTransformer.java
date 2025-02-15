/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4.functors;

import java.io.Serializable;
import java.util.Map;
import org.apache.commons.collections4.Transformer;
import org.apache.commons.collections4.functors.ConstantTransformer;

public final class MapTransformer<I, O>
implements Transformer<I, O>,
Serializable {
    private static final long serialVersionUID = 862391807045468939L;
    private final Map<? super I, ? extends O> iMap;

    public static <I, O> Transformer<I, O> mapTransformer(Map<? super I, ? extends O> map) {
        if (map == null) {
            return ConstantTransformer.nullTransformer();
        }
        return new MapTransformer<I, O>(map);
    }

    private MapTransformer(Map<? super I, ? extends O> map) {
        this.iMap = map;
    }

    @Override
    public O transform(I input) {
        return this.iMap.get(input);
    }

    public Map<? super I, ? extends O> getMap() {
        return this.iMap;
    }
}

