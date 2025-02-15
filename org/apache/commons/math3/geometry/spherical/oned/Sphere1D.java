/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.geometry.spherical.oned;

import java.io.Serializable;
import org.apache.commons.math3.exception.MathUnsupportedOperationException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.geometry.Space;

public class Sphere1D
implements Serializable,
Space {
    private static final long serialVersionUID = 20131218L;

    private Sphere1D() {
    }

    public static Sphere1D getInstance() {
        return LazyHolder.INSTANCE;
    }

    public int getDimension() {
        return 1;
    }

    public Space getSubSpace() throws NoSubSpaceException {
        throw new NoSubSpaceException();
    }

    private Object readResolve() {
        return LazyHolder.INSTANCE;
    }

    public static class NoSubSpaceException
    extends MathUnsupportedOperationException {
        private static final long serialVersionUID = 20140225L;

        public NoSubSpaceException() {
            super(LocalizedFormats.NOT_SUPPORTED_IN_DIMENSION_N, 1);
        }
    }

    private static class LazyHolder {
        private static final Sphere1D INSTANCE = new Sphere1D();

        private LazyHolder() {
        }
    }
}

