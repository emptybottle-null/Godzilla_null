/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.hash;

import com.google.common.base.Supplier;
import com.google.errorprone.annotations.Immutable;

@Immutable
interface ImmutableSupplier<T>
extends Supplier<T> {
}

