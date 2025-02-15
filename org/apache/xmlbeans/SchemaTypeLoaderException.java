/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans;

import org.apache.xmlbeans.XmlRuntimeException;

public class SchemaTypeLoaderException
extends XmlRuntimeException {
    private int _code;
    public static final int NO_RESOURCE = 0;
    public static final int WRONG_MAGIC_COOKIE = 1;
    public static final int WRONG_MAJOR_VERSION = 2;
    public static final int WRONG_MINOR_VERSION = 3;
    public static final int WRONG_FILE_TYPE = 4;
    public static final int UNRECOGNIZED_INDEX_ENTRY = 5;
    public static final int WRONG_PROPERTY_TYPE = 6;
    public static final int MALFORMED_CONTENT_MODEL = 7;
    public static final int WRONG_SIMPLE_VARIETY = 8;
    public static final int IO_EXCEPTION = 9;
    public static final int INT_TOO_LARGE = 10;
    public static final int BAD_PARTICLE_TYPE = 11;
    public static final int NOT_WRITEABLE = 12;
    public static final int BAD_HANDLE = 13;
    public static final int NESTED_EXCEPTION = 14;

    public SchemaTypeLoaderException(String message, String name, String handle, int code) {
        super(message + " (" + name + "." + handle + ") - code " + code);
        this._code = code;
    }

    public SchemaTypeLoaderException(String message, String name, String handle, int code, Exception cause) {
        super(message + " (" + name + "." + handle + ") - code " + code);
        this._code = code;
        this.initCause(cause);
    }

    public int getCode() {
        return this._code;
    }
}

