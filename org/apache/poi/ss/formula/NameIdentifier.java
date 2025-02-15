/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.formula;

public class NameIdentifier {
    private final String _name;
    private final boolean _isQuoted;

    public NameIdentifier(String name, boolean isQuoted) {
        this._name = name;
        this._isQuoted = isQuoted;
    }

    public String getName() {
        return this._name;
    }

    public boolean isQuoted() {
        return this._isQuoted;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(64);
        sb.append(this.getClass().getName());
        sb.append(" [");
        if (this._isQuoted) {
            sb.append("'").append(this._name).append("'");
        } else {
            sb.append(this._name);
        }
        sb.append("]");
        return sb.toString();
    }
}

