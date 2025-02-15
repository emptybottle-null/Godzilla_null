/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.ode.events;

import org.apache.commons.math3.exception.MathInternalError;
import org.apache.commons.math3.ode.events.Transformer;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public enum FilterType {
    TRIGGER_ONLY_DECREASING_EVENTS{

        protected boolean getTriggeredIncreasing() {
            return false;
        }

        protected Transformer selectTransformer(Transformer previous, double g, boolean forward) {
            if (forward) {
                switch (previous) {
                    case UNINITIALIZED: {
                        if (g > 0.0) {
                            return Transformer.MAX;
                        }
                        if (g < 0.0) {
                            return Transformer.PLUS;
                        }
                        return Transformer.UNINITIALIZED;
                    }
                    case PLUS: {
                        if (g >= 0.0) {
                            return Transformer.MIN;
                        }
                        return previous;
                    }
                    case MINUS: {
                        if (g >= 0.0) {
                            return Transformer.MAX;
                        }
                        return previous;
                    }
                    case MIN: {
                        if (g <= 0.0) {
                            return Transformer.MINUS;
                        }
                        return previous;
                    }
                    case MAX: {
                        if (g <= 0.0) {
                            return Transformer.PLUS;
                        }
                        return previous;
                    }
                }
                throw new MathInternalError();
            }
            switch (previous) {
                case UNINITIALIZED: {
                    if (g > 0.0) {
                        return Transformer.MINUS;
                    }
                    if (g < 0.0) {
                        return Transformer.MIN;
                    }
                    return Transformer.UNINITIALIZED;
                }
                case PLUS: {
                    if (g <= 0.0) {
                        return Transformer.MAX;
                    }
                    return previous;
                }
                case MINUS: {
                    if (g <= 0.0) {
                        return Transformer.MIN;
                    }
                    return previous;
                }
                case MIN: {
                    if (g >= 0.0) {
                        return Transformer.PLUS;
                    }
                    return previous;
                }
                case MAX: {
                    if (g >= 0.0) {
                        return Transformer.MINUS;
                    }
                    return previous;
                }
            }
            throw new MathInternalError();
        }
    }
    ,
    TRIGGER_ONLY_INCREASING_EVENTS{

        protected boolean getTriggeredIncreasing() {
            return true;
        }

        protected Transformer selectTransformer(Transformer previous, double g, boolean forward) {
            if (forward) {
                switch (previous) {
                    case UNINITIALIZED: {
                        if (g > 0.0) {
                            return Transformer.PLUS;
                        }
                        if (g < 0.0) {
                            return Transformer.MIN;
                        }
                        return Transformer.UNINITIALIZED;
                    }
                    case PLUS: {
                        if (g <= 0.0) {
                            return Transformer.MAX;
                        }
                        return previous;
                    }
                    case MINUS: {
                        if (g <= 0.0) {
                            return Transformer.MIN;
                        }
                        return previous;
                    }
                    case MIN: {
                        if (g >= 0.0) {
                            return Transformer.PLUS;
                        }
                        return previous;
                    }
                    case MAX: {
                        if (g >= 0.0) {
                            return Transformer.MINUS;
                        }
                        return previous;
                    }
                }
                throw new MathInternalError();
            }
            switch (previous) {
                case UNINITIALIZED: {
                    if (g > 0.0) {
                        return Transformer.MAX;
                    }
                    if (g < 0.0) {
                        return Transformer.MINUS;
                    }
                    return Transformer.UNINITIALIZED;
                }
                case PLUS: {
                    if (g >= 0.0) {
                        return Transformer.MIN;
                    }
                    return previous;
                }
                case MINUS: {
                    if (g >= 0.0) {
                        return Transformer.MAX;
                    }
                    return previous;
                }
                case MIN: {
                    if (g <= 0.0) {
                        return Transformer.MINUS;
                    }
                    return previous;
                }
                case MAX: {
                    if (g <= 0.0) {
                        return Transformer.PLUS;
                    }
                    return previous;
                }
            }
            throw new MathInternalError();
        }
    };


    protected abstract boolean getTriggeredIncreasing();

    protected abstract Transformer selectTransformer(Transformer var1, double var2, boolean var4);
}

