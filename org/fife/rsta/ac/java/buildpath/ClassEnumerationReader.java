/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.java.buildpath;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class ClassEnumerationReader {
    private ClassEnumerationReader() {
    }

    public static List<String> getClassNames(InputStream in) throws IOException {
        String lastPkg = null;
        ArrayList<String> classNames = new ArrayList<String>();
        try (BufferedReader r = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));){
            String line;
            while ((line = r.readLine()) != null) {
                if ((line = line.trim()).length() == 0 || line.charAt(0) == '#') continue;
                if (line.charAt(0) == '-') {
                    line = line.substring(1).trim();
                    classNames.add(line);
                    int lastDot = line.lastIndexOf(46);
                    lastPkg = line.substring(0, lastDot + 1);
                    continue;
                }
                String className = line;
                if (lastPkg != null) {
                    className = lastPkg + className;
                }
                classNames.add(className);
            }
        }
        return classNames;
    }
}

