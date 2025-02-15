/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.objenesis.strategy;

import java.io.Serializable;
import org.springframework.objenesis.instantiator.ObjectInstantiator;
import org.springframework.objenesis.instantiator.android.Android10Instantiator;
import org.springframework.objenesis.instantiator.android.Android17Instantiator;
import org.springframework.objenesis.instantiator.android.Android18Instantiator;
import org.springframework.objenesis.instantiator.basic.AccessibleInstantiator;
import org.springframework.objenesis.instantiator.basic.ObjectInputStreamInstantiator;
import org.springframework.objenesis.instantiator.gcj.GCJInstantiator;
import org.springframework.objenesis.instantiator.perc.PercInstantiator;
import org.springframework.objenesis.instantiator.sun.SunReflectionFactoryInstantiator;
import org.springframework.objenesis.instantiator.sun.UnsafeFactoryInstantiator;
import org.springframework.objenesis.strategy.BaseInstantiatorStrategy;
import org.springframework.objenesis.strategy.PlatformDescription;

public class StdInstantiatorStrategy
extends BaseInstantiatorStrategy {
    @Override
    public <T> ObjectInstantiator<T> newInstantiatorOf(Class<T> type) {
        if (PlatformDescription.isThisJVM("Java HotSpot") || PlatformDescription.isThisJVM("OpenJDK")) {
            if (PlatformDescription.isGoogleAppEngine() && PlatformDescription.SPECIFICATION_VERSION.equals("1.7")) {
                if (Serializable.class.isAssignableFrom(type)) {
                    return new ObjectInputStreamInstantiator<T>(type);
                }
                return new AccessibleInstantiator<T>(type);
            }
            return new SunReflectionFactoryInstantiator<T>(type);
        }
        if (PlatformDescription.isThisJVM("Dalvik")) {
            if (PlatformDescription.isAndroidOpenJDK()) {
                return new UnsafeFactoryInstantiator<T>(type);
            }
            if (PlatformDescription.ANDROID_VERSION <= 10) {
                return new Android10Instantiator<T>(type);
            }
            if (PlatformDescription.ANDROID_VERSION <= 17) {
                return new Android17Instantiator<T>(type);
            }
            return new Android18Instantiator<T>(type);
        }
        if (PlatformDescription.isThisJVM("GNU libgcj")) {
            return new GCJInstantiator<T>(type);
        }
        if (PlatformDescription.isThisJVM("PERC")) {
            return new PercInstantiator<T>(type);
        }
        return new UnsafeFactoryInstantiator<T>(type);
    }
}

