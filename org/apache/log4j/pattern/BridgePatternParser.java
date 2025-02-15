/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.log4j.pattern;

import org.apache.log4j.helpers.PatternConverter;
import org.apache.log4j.helpers.PatternParser;
import org.apache.log4j.pattern.BridgePatternConverter;

public final class BridgePatternParser
extends PatternParser {
    public BridgePatternParser(String conversionPattern) {
        super(conversionPattern);
    }

    public PatternConverter parse() {
        return new BridgePatternConverter(this.pattern);
    }
}

