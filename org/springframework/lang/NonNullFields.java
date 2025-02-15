/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.lang;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.annotation.Nonnull;
import javax.annotation.meta.TypeQualifierDefault;

@Target(value={ElementType.PACKAGE})
@Retention(value=RetentionPolicy.RUNTIME)
@Documented
@Nonnull
@TypeQualifierDefault(value={ElementType.FIELD})
public @interface NonNullFields {
}

