/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.cli;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import org.apache.commons.cli.AlreadySelectedException;
import org.apache.commons.cli.Option;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class OptionGroup
implements Serializable {
    private static final long serialVersionUID = 1L;
    private final Map<String, Option> optionMap = new LinkedHashMap<String, Option>();
    private String selected;
    private boolean required;

    public OptionGroup addOption(Option option) {
        this.optionMap.put(option.getKey(), option);
        return this;
    }

    public Collection<String> getNames() {
        return this.optionMap.keySet();
    }

    public Collection<Option> getOptions() {
        return this.optionMap.values();
    }

    public void setSelected(Option option) throws AlreadySelectedException {
        if (option == null) {
            this.selected = null;
            return;
        }
        if (this.selected != null && !this.selected.equals(option.getKey())) {
            throw new AlreadySelectedException(this, option);
        }
        this.selected = option.getKey();
    }

    public String getSelected() {
        return this.selected;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    public boolean isRequired() {
        return this.required;
    }

    public String toString() {
        StringBuilder buff = new StringBuilder();
        Iterator<Option> iter = this.getOptions().iterator();
        buff.append("[");
        while (iter.hasNext()) {
            Option option = iter.next();
            if (option.getOpt() != null) {
                buff.append("-");
                buff.append(option.getOpt());
            } else {
                buff.append("--");
                buff.append(option.getLongOpt());
            }
            if (option.getDescription() != null) {
                buff.append(" ");
                buff.append(option.getDescription());
            }
            if (!iter.hasNext()) continue;
            buff.append(", ");
        }
        buff.append("]");
        return buff.toString();
    }
}

