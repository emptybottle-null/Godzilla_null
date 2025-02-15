/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.internal.Intrinsics
 */
package org.springframework.core.env;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.core.env.PropertyResolver;

@Metadata(mv={1, 1, 18}, bv={1, 0, 3}, k=2, d1={"\u0000\u0014\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0000\n\u0000\u001a\u0017\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0001H\u0086\u0002\u001a$\u0010\u0004\u001a\u0004\u0018\u0001H\u0005\"\u0006\b\u0000\u0010\u0005\u0018\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0001H\u0086\b\u00a2\u0006\u0002\u0010\u0006\u001a&\u0010\u0007\u001a\u0002H\u0005\"\n\b\u0000\u0010\u0005\u0018\u0001*\u00020\b*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0001H\u0086\b\u00a2\u0006\u0002\u0010\u0006\u00a8\u0006\t"}, d2={"get", "", "Lorg/springframework/core/env/PropertyResolver;", "key", "getProperty", "T", "(Lorg/springframework/core/env/PropertyResolver;Ljava/lang/String;)Ljava/lang/Object;", "getRequiredProperty", "", "spring-core"})
public final class PropertyResolverExtensionsKt {
    @Nullable
    public static final String get(@NotNull PropertyResolver $this$get, @NotNull String key) {
        Intrinsics.checkParameterIsNotNull((Object)$this$get, (String)"$this$get");
        Intrinsics.checkParameterIsNotNull((Object)key, (String)"key");
        return $this$get.getProperty(key);
    }

    public static final /* synthetic */ <T> T getProperty(PropertyResolver $this$getProperty, String key) {
        int $i$f$getProperty = 0;
        Intrinsics.checkParameterIsNotNull((Object)$this$getProperty, (String)"$this$getProperty");
        Intrinsics.checkParameterIsNotNull((Object)key, (String)"key");
        Intrinsics.reifiedOperationMarker((int)4, (String)"T");
        return (T)$this$getProperty.getProperty(key, Object.class);
    }

    public static final /* synthetic */ <T> T getRequiredProperty(PropertyResolver $this$getRequiredProperty, String key) {
        int $i$f$getRequiredProperty = 0;
        Intrinsics.checkParameterIsNotNull((Object)$this$getRequiredProperty, (String)"$this$getRequiredProperty");
        Intrinsics.checkParameterIsNotNull((Object)key, (String)"key");
        Intrinsics.reifiedOperationMarker((int)4, (String)"T");
        Object object = $this$getRequiredProperty.getRequiredProperty(key, Object.class);
        Intrinsics.checkExpressionValueIsNotNull((Object)object, (String)"getRequiredProperty(key, T::class.java)");
        return (T)object;
    }
}

