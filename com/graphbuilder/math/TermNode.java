/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.graphbuilder.math;

import com.graphbuilder.math.Expression;

public abstract class TermNode
extends Expression {
    protected String name = null;
    protected boolean negate = false;

    public TermNode(String name, boolean negate) {
        this.setName(name);
        this.setNegate(negate);
    }

    public boolean getNegate() {
        return this.negate;
    }

    public void setNegate(boolean b) {
        this.negate = b;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String s) {
        if (s == null) {
            throw new IllegalArgumentException("name cannot be null");
        }
        if (!TermNode.isValidName(s)) {
            throw new IllegalArgumentException("invalid name: " + s);
        }
        this.name = s;
    }

    private static boolean isValidName(String s) {
        if (s.length() == 0) {
            return false;
        }
        char c = s.charAt(0);
        if (c >= '0' && c <= '9' || c == '.' || c == ',' || c == '(' || c == ')' || c == '^' || c == '*' || c == '/' || c == '+' || c == '-' || c == ' ' || c == '\t' || c == '\n') {
            return false;
        }
        for (int i = 1; i < s.length(); ++i) {
            c = s.charAt(i);
            if (c != ',' && c != '(' && c != ')' && c != '^' && c != '*' && c != '/' && c != '+' && c != '-' && c != ' ' && c != '\t' && c != '\n') continue;
            return false;
        }
        return true;
    }
}

