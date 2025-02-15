/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.codec.language.bm;

import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.StringEncoder;
import org.apache.commons.codec.language.bm.NameType;
import org.apache.commons.codec.language.bm.PhoneticEngine;
import org.apache.commons.codec.language.bm.RuleType;

public class BeiderMorseEncoder
implements StringEncoder {
    private PhoneticEngine engine = new PhoneticEngine(NameType.GENERIC, RuleType.APPROX, true);

    @Override
    public Object encode(Object source) throws EncoderException {
        if (!(source instanceof String)) {
            throw new EncoderException("BeiderMorseEncoder encode parameter is not of type String");
        }
        return this.encode((String)source);
    }

    @Override
    public String encode(String source) throws EncoderException {
        if (source == null) {
            return null;
        }
        return this.engine.encode(source);
    }

    public NameType getNameType() {
        return this.engine.getNameType();
    }

    public RuleType getRuleType() {
        return this.engine.getRuleType();
    }

    public boolean isConcat() {
        return this.engine.isConcat();
    }

    public void setConcat(boolean concat) {
        this.engine = new PhoneticEngine(this.engine.getNameType(), this.engine.getRuleType(), concat, this.engine.getMaxPhonemes());
    }

    public void setNameType(NameType nameType) {
        this.engine = new PhoneticEngine(nameType, this.engine.getRuleType(), this.engine.isConcat(), this.engine.getMaxPhonemes());
    }

    public void setRuleType(RuleType ruleType) {
        this.engine = new PhoneticEngine(this.engine.getNameType(), ruleType, this.engine.isConcat(), this.engine.getMaxPhonemes());
    }

    public void setMaxPhonemes(int maxPhonemes) {
        this.engine = new PhoneticEngine(this.engine.getNameType(), this.engine.getRuleType(), this.engine.isConcat(), maxPhonemes);
    }
}

