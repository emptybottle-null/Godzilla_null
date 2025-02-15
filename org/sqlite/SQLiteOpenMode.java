/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.sqlite;

public enum SQLiteOpenMode {
    READONLY(1),
    READWRITE(2),
    CREATE(4),
    DELETEONCLOSE(8),
    EXCLUSIVE(16),
    OPEN_URI(64),
    OPEN_MEMORY(128),
    MAIN_DB(256),
    TEMP_DB(512),
    TRANSIENT_DB(1024),
    MAIN_JOURNAL(2048),
    TEMP_JOURNAL(4096),
    SUBJOURNAL(8192),
    MASTER_JOURNAL(16384),
    NOMUTEX(32768),
    FULLMUTEX(65536),
    SHAREDCACHE(131072),
    PRIVATECACHE(262144);

    public final int flag;

    private SQLiteOpenMode(int flag) {
        this.flag = flag;
    }
}

