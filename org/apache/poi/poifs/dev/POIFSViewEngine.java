/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.poifs.dev;

import java.io.IOException;
import java.io.LineNumberReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.poifs.dev.POIFSViewable;

public class POIFSViewEngine {
    private static final String _EOL = System.getProperty("line.separator");

    public static List<String> inspectViewable(Object viewable, boolean drilldown, int indentLevel, String indentString) {
        ArrayList<String> objects = new ArrayList<String>();
        if (viewable instanceof POIFSViewable) {
            POIFSViewable inspected = (POIFSViewable)viewable;
            objects.add(POIFSViewEngine.indent(indentLevel, indentString, inspected.getShortDescription()));
            if (drilldown) {
                if (inspected.preferArray()) {
                    Object[] data = inspected.getViewableArray();
                    for (int j = 0; j < data.length; ++j) {
                        objects.addAll(POIFSViewEngine.inspectViewable(data[j], drilldown, indentLevel + 1, indentString));
                    }
                } else {
                    Iterator<Object> iter = inspected.getViewableIterator();
                    while (iter.hasNext()) {
                        objects.addAll(POIFSViewEngine.inspectViewable(iter.next(), drilldown, indentLevel + 1, indentString));
                    }
                }
            }
        } else {
            objects.add(POIFSViewEngine.indent(indentLevel, indentString, viewable.toString()));
        }
        return objects;
    }

    private static String indent(int indentLevel, String indentString, String data) {
        StringBuilder finalBuffer = new StringBuilder();
        StringBuilder indentPrefix = new StringBuilder();
        for (int j = 0; j < indentLevel; ++j) {
            indentPrefix.append(indentString);
        }
        LineNumberReader reader = new LineNumberReader(new StringReader(data));
        try {
            String line = reader.readLine();
            while (line != null) {
                finalBuffer.append((CharSequence)indentPrefix).append(line).append(_EOL);
                line = reader.readLine();
            }
        } catch (IOException e) {
            finalBuffer.append((CharSequence)indentPrefix).append(e.getMessage()).append(_EOL);
        }
        return finalBuffer.toString();
    }
}

