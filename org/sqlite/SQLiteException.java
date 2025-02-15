/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.sqlite;

import java.sql.SQLException;
import org.sqlite.SQLiteErrorCode;

public class SQLiteException
extends SQLException {
    private SQLiteErrorCode resultCode;

    public SQLiteException(String message, SQLiteErrorCode resultCode) {
        super(message, null, resultCode.code & 0xFF);
        this.resultCode = resultCode;
    }

    public SQLiteErrorCode getResultCode() {
        return this.resultCode;
    }
}

