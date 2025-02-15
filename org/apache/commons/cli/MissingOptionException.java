/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.cli;

import java.util.Iterator;
import java.util.List;
import org.apache.commons.cli.ParseException;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class MissingOptionException
extends ParseException {
    private static final long serialVersionUID = 8161889051578563249L;
    private List missingOptions;

    public MissingOptionException(String message) {
        super(message);
    }

    public MissingOptionException(List missingOptions) {
        this(MissingOptionException.createMessage(missingOptions));
        this.missingOptions = missingOptions;
    }

    public List getMissingOptions() {
        return this.missingOptions;
    }

    private static String createMessage(List<?> missingOptions) {
        StringBuilder buf = new StringBuilder("Missing required option");
        buf.append(missingOptions.size() == 1 ? "" : "s");
        buf.append(": ");
        Iterator<?> it = missingOptions.iterator();
        while (it.hasNext()) {
            buf.append(it.next());
            if (!it.hasNext()) continue;
            buf.append(", ");
        }
        return buf.toString();
    }
}

