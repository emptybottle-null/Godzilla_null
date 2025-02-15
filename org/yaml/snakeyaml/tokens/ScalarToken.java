/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.yaml.snakeyaml.tokens;

import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.error.Mark;
import org.yaml.snakeyaml.tokens.Token;

public final class ScalarToken
extends Token {
    private final String value;
    private final boolean plain;
    private final DumperOptions.ScalarStyle style;

    public ScalarToken(String value, Mark startMark, Mark endMark, boolean plain) {
        this(value, plain, startMark, endMark, DumperOptions.ScalarStyle.PLAIN);
    }

    public ScalarToken(String value, boolean plain, Mark startMark, Mark endMark, DumperOptions.ScalarStyle style) {
        super(startMark, endMark);
        this.value = value;
        this.plain = plain;
        if (style == null) {
            throw new NullPointerException("Style must be provided.");
        }
        this.style = style;
    }

    public boolean getPlain() {
        return this.plain;
    }

    public String getValue() {
        return this.value;
    }

    public DumperOptions.ScalarStyle getStyle() {
        return this.style;
    }

    @Override
    public Token.ID getTokenId() {
        return Token.ID.Scalar;
    }
}

