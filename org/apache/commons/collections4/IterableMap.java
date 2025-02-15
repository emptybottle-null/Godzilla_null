/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4;

import java.util.Map;
import org.apache.commons.collections4.IterableGet;
import org.apache.commons.collections4.Put;

public interface IterableMap<K, V>
extends Map<K, V>,
Put<K, V>,
IterableGet<K, V> {
}

