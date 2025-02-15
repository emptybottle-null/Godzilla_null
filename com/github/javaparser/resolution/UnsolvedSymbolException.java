/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.resolution;

public class UnsolvedSymbolException
extends RuntimeException {
    private String name;
    private String context;
    private Throwable cause;

    public UnsolvedSymbolException(String name) {
        this(name, null, null);
    }

    public UnsolvedSymbolException(String name, String context) {
        this(name, context, null);
    }

    public UnsolvedSymbolException(String name, Throwable cause) {
        this(name, null, cause);
    }

    public UnsolvedSymbolException(String name, String context, Throwable cause) {
        super("Unsolved symbol" + (context != null ? " in " + context : "") + " : " + name, cause);
        this.name = name;
        this.context = context;
        this.cause = cause;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return "UnsolvedSymbolException{context='" + this.context + "', name='" + this.name + "', cause='" + this.cause + "'}";
    }
}

