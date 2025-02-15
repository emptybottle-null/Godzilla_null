/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package javassist.compiler;

class Token {
    public Token next = null;
    public int tokenId;
    public long longValue;
    public double doubleValue;
    public String textValue;

    Token() {
    }
}

