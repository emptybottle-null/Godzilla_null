/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.ode.events;

import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.Precision;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
enum Transformer {
    UNINITIALIZED{

        protected double transformed(double g) {
            return 0.0;
        }
    }
    ,
    PLUS{

        protected double transformed(double g) {
            return g;
        }
    }
    ,
    MINUS{

        protected double transformed(double g) {
            return -g;
        }
    }
    ,
    MIN{

        protected double transformed(double g) {
            return FastMath.min(-Precision.SAFE_MIN, FastMath.min(-g, g));
        }
    }
    ,
    MAX{

        protected double transformed(double g) {
            return FastMath.max(Precision.SAFE_MIN, FastMath.max(-g, g));
        }
    };


    protected abstract double transformed(double var1);
}

