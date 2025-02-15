/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.cli;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.TypeHandler;
import org.apache.commons.cli.Util;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class CommandLine
implements Serializable {
    private static final long serialVersionUID = 1L;
    private final List<String> args = new LinkedList<String>();
    private final List<Option> options = new ArrayList<Option>();

    protected CommandLine() {
    }

    public boolean hasOption(String opt) {
        return this.options.contains(this.resolveOption(opt));
    }

    public boolean hasOption(char opt) {
        return this.hasOption(String.valueOf(opt));
    }

    @Deprecated
    public Object getOptionObject(String opt) {
        try {
            return this.getParsedOptionValue(opt);
        } catch (ParseException pe) {
            System.err.println("Exception found converting " + opt + " to desired type: " + pe.getMessage());
            return null;
        }
    }

    public Object getParsedOptionValue(String opt) throws ParseException {
        String res = this.getOptionValue(opt);
        Option option = this.resolveOption(opt);
        if (option == null || res == null) {
            return null;
        }
        return TypeHandler.createValue(res, option.getType());
    }

    public Object getOptionObject(char opt) {
        return this.getOptionObject(String.valueOf(opt));
    }

    public String getOptionValue(String opt) {
        String[] values = this.getOptionValues(opt);
        return values == null ? null : values[0];
    }

    public String getOptionValue(char opt) {
        return this.getOptionValue(String.valueOf(opt));
    }

    public String[] getOptionValues(String opt) {
        ArrayList<String> values = new ArrayList<String>();
        for (Option option : this.options) {
            if (!opt.equals(option.getOpt()) && !opt.equals(option.getLongOpt())) continue;
            values.addAll(option.getValuesList());
        }
        return values.isEmpty() ? null : values.toArray(new String[values.size()]);
    }

    private Option resolveOption(String opt) {
        opt = Util.stripLeadingHyphens(opt);
        for (Option option : this.options) {
            if (opt.equals(option.getOpt())) {
                return option;
            }
            if (!opt.equals(option.getLongOpt())) continue;
            return option;
        }
        return null;
    }

    public String[] getOptionValues(char opt) {
        return this.getOptionValues(String.valueOf(opt));
    }

    public String getOptionValue(String opt, String defaultValue) {
        String answer = this.getOptionValue(opt);
        return answer != null ? answer : defaultValue;
    }

    public String getOptionValue(char opt, String defaultValue) {
        return this.getOptionValue(String.valueOf(opt), defaultValue);
    }

    public Properties getOptionProperties(String opt) {
        Properties props = new Properties();
        for (Option option : this.options) {
            if (!opt.equals(option.getOpt()) && !opt.equals(option.getLongOpt())) continue;
            List<String> values = option.getValuesList();
            if (values.size() >= 2) {
                props.put(values.get(0), values.get(1));
                continue;
            }
            if (values.size() != 1) continue;
            props.put(values.get(0), "true");
        }
        return props;
    }

    public String[] getArgs() {
        String[] answer = new String[this.args.size()];
        this.args.toArray(answer);
        return answer;
    }

    public List<String> getArgList() {
        return this.args;
    }

    protected void addArg(String arg) {
        this.args.add(arg);
    }

    protected void addOption(Option opt) {
        this.options.add(opt);
    }

    public Iterator<Option> iterator() {
        return this.options.iterator();
    }

    public Option[] getOptions() {
        List<Option> processed = this.options;
        Option[] optionsArray = new Option[processed.size()];
        return processed.toArray(optionsArray);
    }

    public static final class Builder {
        private final CommandLine commandLine = new CommandLine();

        public Builder addOption(Option opt) {
            this.commandLine.addOption(opt);
            return this;
        }

        public Builder addArg(String arg) {
            this.commandLine.addArg(arg);
            return this;
        }

        public CommandLine build() {
            return this.commandLine;
        }
    }
}

