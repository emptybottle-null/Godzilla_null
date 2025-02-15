/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.printer.configuration;

public class Indentation {
    private static final int DEFAULT_SIZE = 4;
    private IndentType type;
    private int size;
    private String formattedIndentation = "";

    public Indentation(IndentType type, int size) {
        this.type = type;
        this.size = size;
        this.format();
    }

    public Indentation(IndentType type) {
        this(type, 4);
    }

    public Indentation setSize(int size) {
        this.size = size;
        this.format();
        return this;
    }

    public int getSize() {
        return this.size;
    }

    public Indentation setType(IndentType type) {
        this.type = type;
        this.format();
        return this;
    }

    public IndentType getType() {
        return this.type;
    }

    public String getIndent() {
        return this.formattedIndentation;
    }

    private void format() {
        StringBuilder indentString = new StringBuilder();
        char indentChar = this.type.car.charValue();
        for (int i = 0; i < this.size; ++i) {
            indentString.append(indentChar);
        }
        this.formattedIndentation = indentString.toString();
    }

    public String toString() {
        return this.type.name() + " size=" + this.size;
    }

    public static enum IndentType {
        SPACES(Character.valueOf(' '), 1),
        TABS(Character.valueOf('\t'), 4),
        TABS_WITH_SPACE_ALIGN(Character.valueOf('\t'), 4);

        private Character car;
        private int width;

        private IndentType(Character c, int width) {
            this.car = c;
            this.width = width;
        }

        public Character getCar() {
            return this.car;
        }

        public int getWidth() {
            return this.width;
        }
    }
}

