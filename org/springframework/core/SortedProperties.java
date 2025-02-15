/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.TreeSet;
import org.springframework.lang.Nullable;

class SortedProperties
extends Properties {
    static final String EOL = System.lineSeparator();
    private static final Comparator<Object> keyComparator = Comparator.comparing(String::valueOf);
    private static final Comparator<Map.Entry<Object, Object>> entryComparator = Map.Entry.comparingByKey(keyComparator);
    private final boolean omitComments;

    SortedProperties(boolean omitComments) {
        this.omitComments = omitComments;
    }

    SortedProperties(Properties properties, boolean omitComments) {
        this(omitComments);
        this.putAll(properties);
    }

    @Override
    public void store(OutputStream out, @Nullable String comments) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        super.store(baos, this.omitComments ? null : comments);
        String contents = baos.toString(StandardCharsets.ISO_8859_1.name());
        for (String line : contents.split(EOL)) {
            if (this.omitComments && line.startsWith("#")) continue;
            out.write((line + EOL).getBytes(StandardCharsets.ISO_8859_1));
        }
    }

    @Override
    public void store(Writer writer, @Nullable String comments) throws IOException {
        StringWriter stringWriter = new StringWriter();
        super.store(stringWriter, this.omitComments ? null : comments);
        String contents = stringWriter.toString();
        for (String line : contents.split(EOL)) {
            if (this.omitComments && line.startsWith("#")) continue;
            writer.write(line + EOL);
        }
    }

    @Override
    public void storeToXML(OutputStream out, @Nullable String comments) throws IOException {
        super.storeToXML(out, this.omitComments ? null : comments);
    }

    @Override
    public void storeToXML(OutputStream out, @Nullable String comments, String encoding) throws IOException {
        super.storeToXML(out, this.omitComments ? null : comments, encoding);
    }

    @Override
    public synchronized Enumeration<Object> keys() {
        return Collections.enumeration(this.keySet());
    }

    @Override
    public Set<Object> keySet() {
        TreeSet<Object> sortedKeys = new TreeSet<Object>(keyComparator);
        sortedKeys.addAll(super.keySet());
        return Collections.synchronizedSet(sortedKeys);
    }

    @Override
    public Set<Map.Entry<Object, Object>> entrySet() {
        TreeSet<Map.Entry<Object, Object>> sortedEntries = new TreeSet<Map.Entry<Object, Object>>(entryComparator);
        sortedEntries.addAll(super.entrySet());
        return Collections.synchronizedSet(sortedEntries);
    }
}

