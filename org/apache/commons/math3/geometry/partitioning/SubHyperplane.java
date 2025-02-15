/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.geometry.partitioning;

import org.apache.commons.math3.geometry.Space;
import org.apache.commons.math3.geometry.partitioning.Hyperplane;
import org.apache.commons.math3.geometry.partitioning.Side;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public interface SubHyperplane<S extends Space> {
    public SubHyperplane<S> copySelf();

    public Hyperplane<S> getHyperplane();

    public boolean isEmpty();

    public double getSize();

    @Deprecated
    public Side side(Hyperplane<S> var1);

    public SplitSubHyperplane<S> split(Hyperplane<S> var1);

    public SubHyperplane<S> reunite(SubHyperplane<S> var1);

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    public static class SplitSubHyperplane<U extends Space> {
        private final SubHyperplane<U> plus;
        private final SubHyperplane<U> minus;

        public SplitSubHyperplane(SubHyperplane<U> plus, SubHyperplane<U> minus) {
            this.plus = plus;
            this.minus = minus;
        }

        public SubHyperplane<U> getPlus() {
            return this.plus;
        }

        public SubHyperplane<U> getMinus() {
            return this.minus;
        }

        public Side getSide() {
            if (this.plus != null && !this.plus.isEmpty()) {
                if (this.minus != null && !this.minus.isEmpty()) {
                    return Side.BOTH;
                }
                return Side.PLUS;
            }
            if (this.minus != null && !this.minus.isEmpty()) {
                return Side.MINUS;
            }
            return Side.HYPER;
        }
    }
}

