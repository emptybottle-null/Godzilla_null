/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.log4j.pattern;

import org.apache.log4j.pattern.NamePatternConverter;
import org.apache.log4j.spi.LocationInfo;
import org.apache.log4j.spi.LoggingEvent;

public final class ClassNamePatternConverter
extends NamePatternConverter {
    private ClassNamePatternConverter(String[] options) {
        super("Class Name", "class name", options);
    }

    public static ClassNamePatternConverter newInstance(String[] options) {
        return new ClassNamePatternConverter(options);
    }

    public void format(LoggingEvent event, StringBuffer toAppendTo) {
        int initialLength = toAppendTo.length();
        LocationInfo li = event.getLocationInformation();
        if (li == null) {
            toAppendTo.append("?");
        } else {
            toAppendTo.append(li.getClassName());
        }
        this.abbreviate(initialLength, toAppendTo);
    }
}

