/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.cli;

import java.util.Collection;
import java.util.Iterator;
import org.apache.commons.cli.UnrecognizedOptionException;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class AmbiguousOptionException
extends UnrecognizedOptionException {
    private static final long serialVersionUID = 5829816121277947229L;
    private final Collection<String> matchingOptions;

    public AmbiguousOptionException(String option, Collection<String> matchingOptions) {
        super(AmbiguousOptionException.createMessage(option, matchingOptions), option);
        this.matchingOptions = matchingOptions;
    }

    public Collection<String> getMatchingOptions() {
        return this.matchingOptions;
    }

    private static String createMessage(String option, Collection<String> matchingOptions) {
        StringBuilder buf = new StringBuilder("Ambiguous option: '");
        buf.append(option);
        buf.append("'  (could be: ");
        Iterator<String> it = matchingOptions.iterator();
        while (it.hasNext()) {
            buf.append("'");
            buf.append(it.next());
            buf.append("'");
            if (!it.hasNext()) continue;
            buf.append(", ");
        }
        buf.append(")");
        return buf.toString();
    }
}

