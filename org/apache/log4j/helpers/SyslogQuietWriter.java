/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.log4j.helpers;

import java.io.Writer;
import org.apache.log4j.helpers.QuietWriter;
import org.apache.log4j.spi.ErrorHandler;

public class SyslogQuietWriter
extends QuietWriter {
    int syslogFacility;
    int level;

    public SyslogQuietWriter(Writer writer, int syslogFacility, ErrorHandler eh) {
        super(writer, eh);
        this.syslogFacility = syslogFacility;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setSyslogFacility(int syslogFacility) {
        this.syslogFacility = syslogFacility;
    }

    public void write(String string) {
        super.write("<" + (this.syslogFacility | this.level) + ">" + string);
    }
}

