/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.common;

import javax.xml.stream.Location;

public class InvalidLexicalValueException
extends RuntimeException {
    private Location _location;

    public InvalidLexicalValueException() {
    }

    public InvalidLexicalValueException(String msg) {
        super(msg);
    }

    public InvalidLexicalValueException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public InvalidLexicalValueException(Throwable cause) {
        super(cause);
    }

    public InvalidLexicalValueException(String msg, Location location) {
        super(msg);
        this.setLocation(location);
    }

    public InvalidLexicalValueException(String msg, Throwable cause, Location location) {
        super(msg, cause);
        this.setLocation(location);
    }

    public InvalidLexicalValueException(Throwable cause, Location location) {
        super(cause);
        this.setLocation(location);
    }

    public Location getLocation() {
        return this._location;
    }

    public void setLocation(Location location) {
        this._location = location;
    }
}

