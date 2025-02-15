/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.util;

import java.io.IOException;
import java.io.LineNumberReader;
import java.io.Reader;
import java.util.List;

public class Diff {
    public static void readersAsText(Reader r1, String name1, Reader r2, String name2, List diffs) throws IOException {
        LineNumberReader reader1 = new LineNumberReader(r1);
        LineNumberReader reader2 = new LineNumberReader(r2);
        String line1 = reader1.readLine();
        String line2 = reader2.readLine();
        while (line1 != null && line2 != null) {
            if (!line1.equals(line2)) {
                diffs.add("File \"" + name1 + "\" and file \"" + name2 + "\" differ at line " + reader1.getLineNumber() + ":" + "\n" + line1 + "\n========\n" + line2);
                break;
            }
            line1 = reader1.readLine();
            line2 = reader2.readLine();
        }
        if (line1 == null && line2 != null) {
            diffs.add("File \"" + name2 + "\" has extra lines at line " + reader2.getLineNumber() + ":\n" + line2);
        }
        if (line1 != null && line2 == null) {
            diffs.add("File \"" + name1 + "\" has extra lines at line " + reader1.getLineNumber() + ":\n" + line1);
        }
    }
}

