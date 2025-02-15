/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.sqlite.jdbc3;

import java.sql.SQLException;
import java.sql.Savepoint;

public class JDBC3Savepoint
implements Savepoint {
    final int id;
    final String name;

    JDBC3Savepoint(int id) {
        this.id = id;
        this.name = null;
    }

    JDBC3Savepoint(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public int getSavepointId() throws SQLException {
        return this.id;
    }

    @Override
    public String getSavepointName() throws SQLException {
        return this.name == null ? String.format("SQLITE_SAVEPOINT_%s", this.id) : this.name;
    }
}

