/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.cli;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionGroup;
import org.apache.commons.cli.Util;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class Options
implements Serializable {
    private static final long serialVersionUID = 1L;
    private final Map<String, Option> shortOpts = new LinkedHashMap<String, Option>();
    private final Map<String, Option> longOpts = new LinkedHashMap<String, Option>();
    private final List<Object> requiredOpts = new ArrayList<Object>();
    private final Map<String, OptionGroup> optionGroups = new LinkedHashMap<String, OptionGroup>();

    public Options addOptionGroup(OptionGroup group) {
        if (group.isRequired()) {
            this.requiredOpts.add(group);
        }
        for (Option option : group.getOptions()) {
            option.setRequired(false);
            this.addOption(option);
            this.optionGroups.put(option.getKey(), group);
        }
        return this;
    }

    Collection<OptionGroup> getOptionGroups() {
        return new HashSet<OptionGroup>(this.optionGroups.values());
    }

    public Options addOption(String opt, String description) {
        this.addOption(opt, null, false, description);
        return this;
    }

    public Options addOption(String opt, boolean hasArg, String description) {
        this.addOption(opt, null, hasArg, description);
        return this;
    }

    public Options addOption(String opt, String longOpt, boolean hasArg, String description) {
        this.addOption(new Option(opt, longOpt, hasArg, description));
        return this;
    }

    public Options addRequiredOption(String opt, String longOpt, boolean hasArg, String description) {
        Option option = new Option(opt, longOpt, hasArg, description);
        option.setRequired(true);
        this.addOption(option);
        return this;
    }

    public Options addOption(Option opt) {
        String key = opt.getKey();
        if (opt.hasLongOpt()) {
            this.longOpts.put(opt.getLongOpt(), opt);
        }
        if (opt.isRequired()) {
            if (this.requiredOpts.contains(key)) {
                this.requiredOpts.remove(this.requiredOpts.indexOf(key));
            }
            this.requiredOpts.add(key);
        }
        this.shortOpts.put(key, opt);
        return this;
    }

    public Collection<Option> getOptions() {
        return Collections.unmodifiableCollection(this.helpOptions());
    }

    List<Option> helpOptions() {
        return new ArrayList<Option>(this.shortOpts.values());
    }

    public List getRequiredOptions() {
        return Collections.unmodifiableList(this.requiredOpts);
    }

    public Option getOption(String opt) {
        if (this.shortOpts.containsKey(opt = Util.stripLeadingHyphens(opt))) {
            return this.shortOpts.get(opt);
        }
        return this.longOpts.get(opt);
    }

    public List<String> getMatchingOptions(String opt) {
        opt = Util.stripLeadingHyphens(opt);
        ArrayList<String> matchingOpts = new ArrayList<String>();
        if (this.longOpts.keySet().contains(opt)) {
            return Collections.singletonList(opt);
        }
        for (String longOpt : this.longOpts.keySet()) {
            if (!longOpt.startsWith(opt)) continue;
            matchingOpts.add(longOpt);
        }
        return matchingOpts;
    }

    public boolean hasOption(String opt) {
        return this.shortOpts.containsKey(opt = Util.stripLeadingHyphens(opt)) || this.longOpts.containsKey(opt);
    }

    public boolean hasLongOption(String opt) {
        opt = Util.stripLeadingHyphens(opt);
        return this.longOpts.containsKey(opt);
    }

    public boolean hasShortOption(String opt) {
        opt = Util.stripLeadingHyphens(opt);
        return this.shortOpts.containsKey(opt);
    }

    public OptionGroup getOptionGroup(Option opt) {
        return this.optionGroups.get(opt.getKey());
    }

    public String toString() {
        StringBuilder buf = new StringBuilder();
        buf.append("[ Options: [ short ");
        buf.append(this.shortOpts.toString());
        buf.append(" ] [ long ");
        buf.append(this.longOpts);
        buf.append(" ]");
        return buf.toString();
    }
}

