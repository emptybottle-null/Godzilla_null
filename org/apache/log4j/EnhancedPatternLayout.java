/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.log4j;

import org.apache.log4j.Layout;
import org.apache.log4j.helpers.OptionConverter;
import org.apache.log4j.helpers.PatternConverter;
import org.apache.log4j.helpers.PatternParser;
import org.apache.log4j.pattern.BridgePatternConverter;
import org.apache.log4j.pattern.BridgePatternParser;
import org.apache.log4j.spi.LoggingEvent;

public class EnhancedPatternLayout
extends Layout {
    public static final String DEFAULT_CONVERSION_PATTERN = "%m%n";
    public static final String TTCC_CONVERSION_PATTERN = "%r [%t] %p %c %x - %m%n";
    protected final int BUF_SIZE = 256;
    protected final int MAX_CAPACITY = 1024;
    public static final String PATTERN_RULE_REGISTRY = "PATTERN_RULE_REGISTRY";
    private PatternConverter head;
    private String conversionPattern;
    private boolean handlesExceptions;

    public EnhancedPatternLayout() {
        this(DEFAULT_CONVERSION_PATTERN);
    }

    public EnhancedPatternLayout(String pattern) {
        this.conversionPattern = pattern;
        this.head = this.createPatternParser(pattern == null ? DEFAULT_CONVERSION_PATTERN : pattern).parse();
        this.handlesExceptions = this.head instanceof BridgePatternConverter ? !((BridgePatternConverter)this.head).ignoresThrowable() : false;
    }

    public void setConversionPattern(String conversionPattern) {
        this.conversionPattern = OptionConverter.convertSpecialChars(conversionPattern);
        this.head = this.createPatternParser(this.conversionPattern).parse();
        this.handlesExceptions = this.head instanceof BridgePatternConverter ? !((BridgePatternConverter)this.head).ignoresThrowable() : false;
    }

    public String getConversionPattern() {
        return this.conversionPattern;
    }

    protected PatternParser createPatternParser(String pattern) {
        return new BridgePatternParser(pattern);
    }

    public void activateOptions() {
    }

    public String format(LoggingEvent event) {
        StringBuffer buf = new StringBuffer();
        PatternConverter c = this.head;
        while (c != null) {
            c.format(buf, event);
            c = c.next;
        }
        return buf.toString();
    }

    public boolean ignoresThrowable() {
        return !this.handlesExceptions;
    }
}

