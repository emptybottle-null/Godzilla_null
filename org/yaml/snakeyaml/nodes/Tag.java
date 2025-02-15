/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.yaml.snakeyaml.nodes;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URI;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.yaml.snakeyaml.SecClass;
import org.yaml.snakeyaml.error.YAMLException;
import org.yaml.snakeyaml.util.UriEncoder;

public final class Tag {
    public static final String PREFIX = "tag:yaml.org,2002:";
    public static final Tag YAML = new Tag("tag:yaml.org,2002:yaml");
    public static final Tag MERGE = new Tag("tag:yaml.org,2002:merge");
    public static final Tag SET = new Tag("tag:yaml.org,2002:set");
    public static final Tag PAIRS = new Tag("tag:yaml.org,2002:pairs");
    public static final Tag OMAP = new Tag("tag:yaml.org,2002:omap");
    public static final Tag BINARY = new Tag("tag:yaml.org,2002:binary");
    public static final Tag INT = new Tag("tag:yaml.org,2002:int");
    public static final Tag FLOAT = new Tag("tag:yaml.org,2002:float");
    public static final Tag TIMESTAMP = new Tag("tag:yaml.org,2002:timestamp");
    public static final Tag BOOL = new Tag("tag:yaml.org,2002:bool");
    public static final Tag NULL = new Tag("tag:yaml.org,2002:null");
    public static final Tag STR = new Tag("tag:yaml.org,2002:str");
    public static final Tag SEQ = new Tag("tag:yaml.org,2002:seq");
    public static final Tag MAP = new Tag("tag:yaml.org,2002:map");
    public static final Tag COMMENT = new Tag("tag:yaml.org,2002:comment");
    protected static final Map<Tag, Set<Class<?>>> COMPATIBILITY_MAP = new HashMap();
    private final String value;
    private boolean secondary = false;

    public Tag(String tag) {
        if (tag == null) {
            throw new NullPointerException("Tag must be provided.");
        }
        if (tag.length() == 0) {
            throw new IllegalArgumentException("Tag must not be empty.");
        }
        if (tag.trim().length() != tag.length()) {
            throw new IllegalArgumentException("Tag must not contain leading or trailing spaces.");
        }
        this.value = UriEncoder.encode(tag);
        this.secondary = !tag.startsWith(PREFIX);
    }

    public Tag(Class<? extends Object> clazz) {
        if (clazz == null) {
            throw new NullPointerException("Class for tag must be provided.");
        }
        this.value = PREFIX + UriEncoder.encode(clazz.getName());
    }

    public Tag(URI uri) {
        if (uri == null) {
            throw new NullPointerException("URI for tag must be provided.");
        }
        this.value = uri.toASCIIString();
    }

    public boolean isSecondary() {
        return this.secondary;
    }

    public String getValue() {
        return this.value;
    }

    public boolean startsWith(String prefix) {
        return this.value.startsWith(prefix);
    }

    public String getClassName() {
        if (!this.value.startsWith(PREFIX)) {
            throw new YAMLException("Invalid tag: " + this.value);
        }
        return UriEncoder.decode(this.value.substring(PREFIX.length()));
    }

    public String toString() {
        return this.value;
    }

    public boolean equals(Object obj) {
        if (obj instanceof Tag) {
            return this.value.equals(((Tag)obj).getValue());
        }
        return false;
    }

    public int hashCode() {
        return this.value.hashCode();
    }

    public boolean isCompatible(Class<?> clazz) {
        Set<Class<?>> set = COMPATIBILITY_MAP.get(this);
        if (set != null) {
            return set.contains(clazz);
        }
        return false;
    }

    public boolean matches(Class<? extends Object> clazz) {
        return this.value.equals(PREFIX + clazz.getName());
    }

    static {
        HashSet<Class<BigDecimal>> floatSet = new HashSet<Class<BigDecimal>>();
        floatSet.add(Double.class);
        floatSet.add(Float.class);
        floatSet.add(BigDecimal.class);
        COMPATIBILITY_MAP.put(FLOAT, floatSet);
        HashSet<Class<BigInteger>> intSet = new HashSet<Class<BigInteger>>();
        intSet.add(Integer.class);
        intSet.add(Long.class);
        intSet.add(BigInteger.class);
        COMPATIBILITY_MAP.put(INT, intSet);
        HashSet<Class> timestampSet = new HashSet<Class>();
        timestampSet.add(Date.class);
        try {
            timestampSet.add(SecClass.forName("java.sql.Date"));
            timestampSet.add(SecClass.forName("java.sql.Timestamp"));
        } catch (ClassNotFoundException classNotFoundException) {
            // empty catch block
        }
        COMPATIBILITY_MAP.put(TIMESTAMP, timestampSet);
    }
}

