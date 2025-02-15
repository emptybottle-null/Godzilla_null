/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core;

import org.springframework.core.KotlinDetector;
import org.springframework.core.KotlinReflectionParameterNameDiscoverer;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.core.NativeDetector;
import org.springframework.core.PrioritizedParameterNameDiscoverer;
import org.springframework.core.StandardReflectionParameterNameDiscoverer;

public class DefaultParameterNameDiscoverer
extends PrioritizedParameterNameDiscoverer {
    public DefaultParameterNameDiscoverer() {
        if (KotlinDetector.isKotlinReflectPresent() && !NativeDetector.inNativeImage()) {
            this.addDiscoverer(new KotlinReflectionParameterNameDiscoverer());
        }
        this.addDiscoverer(new StandardReflectionParameterNameDiscoverer());
        this.addDiscoverer(new LocalVariableTableParameterNameDiscoverer());
    }
}

