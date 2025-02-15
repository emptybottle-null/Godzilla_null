/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.cli;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.ListIterator;
import java.util.Properties;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.MissingArgumentException;
import org.apache.commons.cli.MissingOptionException;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionGroup;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.UnrecognizedOptionException;
import org.apache.commons.cli.Util;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
@Deprecated
public abstract class Parser
implements CommandLineParser {
    protected CommandLine cmd;
    private Options options;
    private List requiredOptions;

    protected void setOptions(Options options) {
        this.options = options;
        this.requiredOptions = new ArrayList(options.getRequiredOptions());
    }

    protected Options getOptions() {
        return this.options;
    }

    protected List getRequiredOptions() {
        return this.requiredOptions;
    }

    protected abstract String[] flatten(Options var1, String[] var2, boolean var3) throws ParseException;

    @Override
    public CommandLine parse(Options options, String[] arguments) throws ParseException {
        return this.parse(options, arguments, null, false);
    }

    public CommandLine parse(Options options, String[] arguments, Properties properties) throws ParseException {
        return this.parse(options, arguments, properties, false);
    }

    @Override
    public CommandLine parse(Options options, String[] arguments, boolean stopAtNonOption) throws ParseException {
        return this.parse(options, arguments, null, stopAtNonOption);
    }

    public CommandLine parse(Options options, String[] arguments, Properties properties, boolean stopAtNonOption) throws ParseException {
        for (Option opt : options.helpOptions()) {
            opt.clearValues();
        }
        for (OptionGroup group : options.getOptionGroups()) {
            group.setSelected(null);
        }
        this.setOptions(options);
        this.cmd = new CommandLine();
        boolean eatTheRest = false;
        if (arguments == null) {
            arguments = new String[]{};
        }
        List<String> tokenList = Arrays.asList(this.flatten(this.getOptions(), arguments, stopAtNonOption));
        ListIterator<String> iterator = tokenList.listIterator();
        while (iterator.hasNext()) {
            String t = iterator.next();
            if ("--".equals(t)) {
                eatTheRest = true;
            } else if ("-".equals(t)) {
                if (stopAtNonOption) {
                    eatTheRest = true;
                } else {
                    this.cmd.addArg(t);
                }
            } else if (t.startsWith("-")) {
                if (stopAtNonOption && !this.getOptions().hasOption(t)) {
                    eatTheRest = true;
                    this.cmd.addArg(t);
                } else {
                    this.processOption(t, iterator);
                }
            } else {
                this.cmd.addArg(t);
                if (stopAtNonOption) {
                    eatTheRest = true;
                }
            }
            if (!eatTheRest) continue;
            while (iterator.hasNext()) {
                String str = iterator.next();
                if ("--".equals(str)) continue;
                this.cmd.addArg(str);
            }
        }
        this.processProperties(properties);
        this.checkRequiredOptions();
        return this.cmd;
    }

    protected void processProperties(Properties properties) throws ParseException {
        if (properties == null) {
            return;
        }
        Enumeration<?> e = properties.propertyNames();
        while (e.hasMoreElements()) {
            boolean selected;
            String option = e.nextElement().toString();
            Option opt = this.options.getOption(option);
            if (opt == null) {
                throw new UnrecognizedOptionException("Default option wasn't defined", option);
            }
            OptionGroup group = this.options.getOptionGroup(opt);
            boolean bl = selected = group != null && group.getSelected() != null;
            if (this.cmd.hasOption(option) || selected) continue;
            String value = properties.getProperty(option);
            if (opt.hasArg()) {
                if (opt.getValues() == null || opt.getValues().length == 0) {
                    try {
                        opt.addValueForProcessing(value);
                    } catch (RuntimeException runtimeException) {}
                }
            } else if (!"yes".equalsIgnoreCase(value) && !"true".equalsIgnoreCase(value) && !"1".equalsIgnoreCase(value)) continue;
            this.cmd.addOption(opt);
            this.updateRequiredOptions(opt);
        }
    }

    protected void checkRequiredOptions() throws MissingOptionException {
        if (!this.getRequiredOptions().isEmpty()) {
            throw new MissingOptionException(this.getRequiredOptions());
        }
    }

    public void processArgs(Option opt, ListIterator<String> iter) throws ParseException {
        while (iter.hasNext()) {
            String str = iter.next();
            if (this.getOptions().hasOption(str) && str.startsWith("-")) {
                iter.previous();
                break;
            }
            try {
                opt.addValueForProcessing(Util.stripLeadingAndTrailingQuotes(str));
            } catch (RuntimeException exp) {
                iter.previous();
                break;
            }
        }
        if (opt.getValues() == null && !opt.hasOptionalArg()) {
            throw new MissingArgumentException(opt);
        }
    }

    protected void processOption(String arg, ListIterator<String> iter) throws ParseException {
        boolean hasOption = this.getOptions().hasOption(arg);
        if (!hasOption) {
            throw new UnrecognizedOptionException("Unrecognized option: " + arg, arg);
        }
        Option opt = (Option)this.getOptions().getOption(arg).clone();
        this.updateRequiredOptions(opt);
        if (opt.hasArg()) {
            this.processArgs(opt, iter);
        }
        this.cmd.addOption(opt);
    }

    private void updateRequiredOptions(Option opt) throws ParseException {
        if (opt.isRequired()) {
            this.getRequiredOptions().remove(opt.getKey());
        }
        if (this.getOptions().getOptionGroup(opt) != null) {
            OptionGroup group = this.getOptions().getOptionGroup(opt);
            if (group.isRequired()) {
                this.getRequiredOptions().remove(group);
            }
            group.setSelected(opt);
        }
    }
}

