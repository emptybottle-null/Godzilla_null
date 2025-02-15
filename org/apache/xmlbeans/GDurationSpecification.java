/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans;

import java.math.BigDecimal;

public interface GDurationSpecification {
    public boolean isImmutable();

    public int getSign();

    public int getYear();

    public int getMonth();

    public int getDay();

    public int getHour();

    public int getMinute();

    public int getSecond();

    public BigDecimal getFraction();

    public boolean isValid();

    public int compareToGDuration(GDurationSpecification var1);
}

