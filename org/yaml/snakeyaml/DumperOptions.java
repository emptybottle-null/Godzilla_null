/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.yaml.snakeyaml;

import java.util.Map;
import java.util.TimeZone;
import org.yaml.snakeyaml.error.YAMLException;
import org.yaml.snakeyaml.serializer.AnchorGenerator;
import org.yaml.snakeyaml.serializer.NumberAnchorGenerator;

public class DumperOptions {
    private ScalarStyle defaultStyle = ScalarStyle.PLAIN;
    private FlowStyle defaultFlowStyle = FlowStyle.AUTO;
    private boolean canonical = false;
    private boolean allowUnicode = true;
    private boolean allowReadOnlyProperties = false;
    private int indent = 2;
    private int indicatorIndent = 0;
    private boolean indentWithIndicator = false;
    private int bestWidth = 80;
    private boolean splitLines = true;
    private LineBreak lineBreak = LineBreak.UNIX;
    private boolean explicitStart = false;
    private boolean explicitEnd = false;
    private TimeZone timeZone = null;
    private int maxSimpleKeyLength = 128;
    private boolean processComments = false;
    private NonPrintableStyle nonPrintableStyle = NonPrintableStyle.BINARY;
    private Version version = null;
    private Map<String, String> tags = null;
    private Boolean prettyFlow = false;
    private AnchorGenerator anchorGenerator = new NumberAnchorGenerator(0);

    public boolean isAllowUnicode() {
        return this.allowUnicode;
    }

    public void setAllowUnicode(boolean allowUnicode) {
        this.allowUnicode = allowUnicode;
    }

    public ScalarStyle getDefaultScalarStyle() {
        return this.defaultStyle;
    }

    public void setDefaultScalarStyle(ScalarStyle defaultStyle) {
        if (defaultStyle == null) {
            throw new NullPointerException("Use ScalarStyle enum.");
        }
        this.defaultStyle = defaultStyle;
    }

    public void setIndent(int indent) {
        if (indent < 1) {
            throw new YAMLException("Indent must be at least 1");
        }
        if (indent > 10) {
            throw new YAMLException("Indent must be at most 10");
        }
        this.indent = indent;
    }

    public int getIndent() {
        return this.indent;
    }

    public void setIndicatorIndent(int indicatorIndent) {
        if (indicatorIndent < 0) {
            throw new YAMLException("Indicator indent must be non-negative.");
        }
        if (indicatorIndent > 9) {
            throw new YAMLException("Indicator indent must be at most Emitter.MAX_INDENT-1: 9");
        }
        this.indicatorIndent = indicatorIndent;
    }

    public int getIndicatorIndent() {
        return this.indicatorIndent;
    }

    public boolean getIndentWithIndicator() {
        return this.indentWithIndicator;
    }

    public void setIndentWithIndicator(boolean indentWithIndicator) {
        this.indentWithIndicator = indentWithIndicator;
    }

    public void setVersion(Version version) {
        this.version = version;
    }

    public Version getVersion() {
        return this.version;
    }

    public void setCanonical(boolean canonical) {
        this.canonical = canonical;
    }

    public boolean isCanonical() {
        return this.canonical;
    }

    public void setPrettyFlow(boolean prettyFlow) {
        this.prettyFlow = prettyFlow;
    }

    public boolean isPrettyFlow() {
        return this.prettyFlow;
    }

    public void setWidth(int bestWidth) {
        this.bestWidth = bestWidth;
    }

    public int getWidth() {
        return this.bestWidth;
    }

    public void setSplitLines(boolean splitLines) {
        this.splitLines = splitLines;
    }

    public boolean getSplitLines() {
        return this.splitLines;
    }

    public LineBreak getLineBreak() {
        return this.lineBreak;
    }

    public void setDefaultFlowStyle(FlowStyle defaultFlowStyle) {
        if (defaultFlowStyle == null) {
            throw new NullPointerException("Use FlowStyle enum.");
        }
        this.defaultFlowStyle = defaultFlowStyle;
    }

    public FlowStyle getDefaultFlowStyle() {
        return this.defaultFlowStyle;
    }

    public void setLineBreak(LineBreak lineBreak) {
        if (lineBreak == null) {
            throw new NullPointerException("Specify line break.");
        }
        this.lineBreak = lineBreak;
    }

    public boolean isExplicitStart() {
        return this.explicitStart;
    }

    public void setExplicitStart(boolean explicitStart) {
        this.explicitStart = explicitStart;
    }

    public boolean isExplicitEnd() {
        return this.explicitEnd;
    }

    public void setExplicitEnd(boolean explicitEnd) {
        this.explicitEnd = explicitEnd;
    }

    public Map<String, String> getTags() {
        return this.tags;
    }

    public void setTags(Map<String, String> tags) {
        this.tags = tags;
    }

    public boolean isAllowReadOnlyProperties() {
        return this.allowReadOnlyProperties;
    }

    public void setAllowReadOnlyProperties(boolean allowReadOnlyProperties) {
        this.allowReadOnlyProperties = allowReadOnlyProperties;
    }

    public TimeZone getTimeZone() {
        return this.timeZone;
    }

    public void setTimeZone(TimeZone timeZone) {
        this.timeZone = timeZone;
    }

    public AnchorGenerator getAnchorGenerator() {
        return this.anchorGenerator;
    }

    public void setAnchorGenerator(AnchorGenerator anchorGenerator) {
        this.anchorGenerator = anchorGenerator;
    }

    public int getMaxSimpleKeyLength() {
        return this.maxSimpleKeyLength;
    }

    public void setMaxSimpleKeyLength(int maxSimpleKeyLength) {
        if (maxSimpleKeyLength > 1024) {
            throw new YAMLException("The simple key must not span more than 1024 stream characters. See https://yaml.org/spec/1.1/#id934537");
        }
        this.maxSimpleKeyLength = maxSimpleKeyLength;
    }

    public void setProcessComments(boolean processComments) {
        this.processComments = processComments;
    }

    public boolean isProcessComments() {
        return this.processComments;
    }

    public NonPrintableStyle getNonPrintableStyle() {
        return this.nonPrintableStyle;
    }

    public void setNonPrintableStyle(NonPrintableStyle style) {
        this.nonPrintableStyle = style;
    }

    public static enum NonPrintableStyle {
        BINARY,
        ESCAPE;

    }

    public static enum Version {
        V1_0(new Integer[]{1, 0}),
        V1_1(new Integer[]{1, 1});

        private Integer[] version;

        private Version(Integer[] version) {
            this.version = version;
        }

        public int major() {
            return this.version[0];
        }

        public int minor() {
            return this.version[1];
        }

        public String getRepresentation() {
            return this.version[0] + "." + this.version[1];
        }

        public String toString() {
            return "Version: " + this.getRepresentation();
        }
    }

    public static enum LineBreak {
        WIN("\r\n"),
        MAC("\r"),
        UNIX("\n");

        private String lineBreak;

        private LineBreak(String lineBreak) {
            this.lineBreak = lineBreak;
        }

        public String getString() {
            return this.lineBreak;
        }

        public String toString() {
            return "Line break: " + this.name();
        }

        public static LineBreak getPlatformLineBreak() {
            String platformLineBreak = System.getProperty("line.separator");
            for (LineBreak lb : LineBreak.values()) {
                if (!lb.lineBreak.equals(platformLineBreak)) continue;
                return lb;
            }
            return UNIX;
        }
    }

    public static enum FlowStyle {
        FLOW(Boolean.TRUE),
        BLOCK(Boolean.FALSE),
        AUTO(null);

        private Boolean styleBoolean;

        private FlowStyle(Boolean flowStyle) {
            this.styleBoolean = flowStyle;
        }

        @Deprecated
        public static FlowStyle fromBoolean(Boolean flowStyle) {
            return flowStyle == null ? AUTO : (flowStyle != false ? FLOW : BLOCK);
        }

        public Boolean getStyleBoolean() {
            return this.styleBoolean;
        }

        public String toString() {
            return "Flow style: '" + this.styleBoolean + "'";
        }
    }

    public static enum ScalarStyle {
        DOUBLE_QUOTED(Character.valueOf('\"')),
        SINGLE_QUOTED(Character.valueOf('\'')),
        LITERAL(Character.valueOf('|')),
        FOLDED(Character.valueOf('>')),
        PLAIN(null);

        private Character styleChar;

        private ScalarStyle(Character style) {
            this.styleChar = style;
        }

        public Character getChar() {
            return this.styleChar;
        }

        public String toString() {
            return "Scalar style: '" + this.styleChar + "'";
        }

        public static ScalarStyle createStyle(Character style) {
            if (style == null) {
                return PLAIN;
            }
            switch (style.charValue()) {
                case '\"': {
                    return DOUBLE_QUOTED;
                }
                case '\'': {
                    return SINGLE_QUOTED;
                }
                case '|': {
                    return LITERAL;
                }
                case '>': {
                    return FOLDED;
                }
            }
            throw new YAMLException("Unknown scalar style character: " + style);
        }
    }
}

