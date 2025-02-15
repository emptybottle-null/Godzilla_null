/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.yaml.snakeyaml.scanner;

import org.yaml.snakeyaml.tokens.Token;

public interface Scanner {
    public boolean checkToken(Token.ID ... var1);

    public Token peekToken();

    public Token getToken();
}

