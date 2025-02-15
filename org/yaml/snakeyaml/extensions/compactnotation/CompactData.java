/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.yaml.snakeyaml.extensions.compactnotation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CompactData {
    private String prefix;
    private List<String> arguments = new ArrayList<String>();
    private Map<String, String> properties = new HashMap<String, String>();

    public CompactData(String prefix) {
        this.prefix = prefix;
    }

    public String getPrefix() {
        return this.prefix;
    }

    public Map<String, String> getProperties() {
        return this.properties;
    }

    public List<String> getArguments() {
        return this.arguments;
    }

    public String toString() {
        return "CompactData: " + this.prefix + " " + this.properties;
    }
}

