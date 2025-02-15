/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.objenesis.strategy;

import java.io.NotSerializableException;
import java.io.Serializable;
import org.springframework.objenesis.ObjenesisException;
import org.springframework.objenesis.instantiator.ObjectInstantiator;
import org.springframework.objenesis.instantiator.android.AndroidSerializationInstantiator;
import org.springframework.objenesis.instantiator.basic.ObjectInputStreamInstantiator;
import org.springframework.objenesis.instantiator.basic.ObjectStreamClassInstantiator;
import org.springframework.objenesis.instantiator.gcj.GCJSerializationInstantiator;
import org.springframework.objenesis.instantiator.perc.PercSerializationInstantiator;
import org.springframework.objenesis.instantiator.sun.SunReflectionFactorySerializationInstantiator;
import org.springframework.objenesis.strategy.BaseInstantiatorStrategy;
import org.springframework.objenesis.strategy.PlatformDescription;

public class SerializingInstantiatorStrategy
extends BaseInstantiatorStrategy {
    @Override
    public <T> ObjectInstantiator<T> newInstantiatorOf(Class<T> type) {
        if (!Serializable.class.isAssignableFrom(type)) {
            throw new ObjenesisException(new NotSerializableException(type + " not serializable"));
        }
        if (PlatformDescription.JVM_NAME.startsWith("Java HotSpot") || PlatformDescription.isThisJVM("OpenJDK")) {
            if (PlatformDescription.isGoogleAppEngine() && PlatformDescription.SPECIFICATION_VERSION.equals("1.7")) {
                return new ObjectInputStreamInstantiator<T>(type);
            }
            return new SunReflectionFactorySerializationInstantiator<T>(type);
        }
        if (PlatformDescription.JVM_NAME.startsWith("Dalvik")) {
            if (PlatformDescription.isAndroidOpenJDK()) {
                return new ObjectStreamClassInstantiator<T>(type);
            }
            return new AndroidSerializationInstantiator<T>(type);
        }
        if (PlatformDescription.JVM_NAME.startsWith("GNU libgcj")) {
            return new GCJSerializationInstantiator<T>(type);
        }
        if (PlatformDescription.JVM_NAME.startsWith("PERC")) {
            return new PercSerializationInstantiator<T>(type);
        }
        return new SunReflectionFactorySerializationInstantiator<T>(type);
    }
}

