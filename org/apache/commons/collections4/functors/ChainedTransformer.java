/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4.functors;

import java.io.Serializable;
import java.util.Collection;
import org.apache.commons.collections4.Transformer;
import org.apache.commons.collections4.functors.FunctorUtils;
import org.apache.commons.collections4.functors.NOPTransformer;

public class ChainedTransformer<T>
implements Transformer<T, T>,
Serializable {
    private static final long serialVersionUID = 3514945074733160196L;
    private final Transformer<? super T, ? extends T>[] iTransformers;

    public static <T> Transformer<T, T> chainedTransformer(Transformer<? super T, ? extends T> ... transformers) {
        FunctorUtils.validate(transformers);
        if (transformers.length == 0) {
            return NOPTransformer.nopTransformer();
        }
        return new ChainedTransformer<T>(transformers);
    }

    public static <T> Transformer<T, T> chainedTransformer(Collection<? extends Transformer<? super T, ? extends T>> transformers) {
        if (transformers == null) {
            throw new NullPointerException("Transformer collection must not be null");
        }
        if (transformers.size() == 0) {
            return NOPTransformer.nopTransformer();
        }
        Transformer[] cmds = transformers.toArray(new Transformer[transformers.size()]);
        FunctorUtils.validate(cmds);
        return new ChainedTransformer<T>(false, cmds);
    }

    private ChainedTransformer(boolean clone, Transformer<? super T, ? extends T>[] transformers) {
        this.iTransformers = clone ? FunctorUtils.copy(transformers) : transformers;
    }

    public ChainedTransformer(Transformer<? super T, ? extends T> ... transformers) {
        this(true, transformers);
    }

    @Override
    public T transform(T object) {
        for (Transformer<T, T> transformer : this.iTransformers) {
            object = transformer.transform(object);
        }
        return object;
    }

    public Transformer<? super T, ? extends T>[] getTransformers() {
        return FunctorUtils.copy(this.iTransformers);
    }
}

