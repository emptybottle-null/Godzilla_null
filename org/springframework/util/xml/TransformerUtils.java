/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.util.xml;

import javax.xml.transform.Transformer;
import org.springframework.util.Assert;

public abstract class TransformerUtils {
    public static final int DEFAULT_INDENT_AMOUNT = 2;

    public static void enableIndenting(Transformer transformer) {
        TransformerUtils.enableIndenting(transformer, 2);
    }

    public static void enableIndenting(Transformer transformer, int indentAmount) {
        Assert.notNull((Object)transformer, "Transformer must not be null");
        if (indentAmount < 0) {
            throw new IllegalArgumentException("Invalid indent amount (must not be less than zero): " + indentAmount);
        }
        transformer.setOutputProperty("indent", "yes");
        try {
            transformer.setOutputProperty("{http://xml.apache.org/xalan}indent-amount", String.valueOf(indentAmount));
        } catch (IllegalArgumentException illegalArgumentException) {
            // empty catch block
        }
    }

    public static void disableIndenting(Transformer transformer) {
        Assert.notNull((Object)transformer, "Transformer must not be null");
        transformer.setOutputProperty("indent", "no");
    }
}

