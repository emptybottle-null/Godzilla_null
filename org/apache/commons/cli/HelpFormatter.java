/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.cli;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionGroup;
import org.apache.commons.cli.Options;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class HelpFormatter {
    public static final int DEFAULT_WIDTH = 74;
    public static final int DEFAULT_LEFT_PAD = 1;
    public static final int DEFAULT_DESC_PAD = 3;
    public static final String DEFAULT_SYNTAX_PREFIX = "usage: ";
    public static final String DEFAULT_OPT_PREFIX = "-";
    public static final String DEFAULT_LONG_OPT_PREFIX = "--";
    public static final String DEFAULT_LONG_OPT_SEPARATOR = " ";
    public static final String DEFAULT_ARG_NAME = "arg";
    @Deprecated
    public int defaultWidth = 74;
    @Deprecated
    public int defaultLeftPad = 1;
    @Deprecated
    public int defaultDescPad = 3;
    @Deprecated
    public String defaultSyntaxPrefix = "usage: ";
    @Deprecated
    public String defaultNewLine = System.getProperty("line.separator");
    @Deprecated
    public String defaultOptPrefix = "-";
    @Deprecated
    public String defaultLongOptPrefix = "--";
    @Deprecated
    public String defaultArgName = "arg";
    protected Comparator<Option> optionComparator = new OptionComparator();
    private String longOptSeparator = " ";

    public void setWidth(int width) {
        this.defaultWidth = width;
    }

    public int getWidth() {
        return this.defaultWidth;
    }

    public void setLeftPadding(int padding) {
        this.defaultLeftPad = padding;
    }

    public int getLeftPadding() {
        return this.defaultLeftPad;
    }

    public void setDescPadding(int padding) {
        this.defaultDescPad = padding;
    }

    public int getDescPadding() {
        return this.defaultDescPad;
    }

    public void setSyntaxPrefix(String prefix) {
        this.defaultSyntaxPrefix = prefix;
    }

    public String getSyntaxPrefix() {
        return this.defaultSyntaxPrefix;
    }

    public void setNewLine(String newline) {
        this.defaultNewLine = newline;
    }

    public String getNewLine() {
        return this.defaultNewLine;
    }

    public void setOptPrefix(String prefix) {
        this.defaultOptPrefix = prefix;
    }

    public String getOptPrefix() {
        return this.defaultOptPrefix;
    }

    public void setLongOptPrefix(String prefix) {
        this.defaultLongOptPrefix = prefix;
    }

    public String getLongOptPrefix() {
        return this.defaultLongOptPrefix;
    }

    public void setLongOptSeparator(String longOptSeparator) {
        this.longOptSeparator = longOptSeparator;
    }

    public String getLongOptSeparator() {
        return this.longOptSeparator;
    }

    public void setArgName(String name) {
        this.defaultArgName = name;
    }

    public String getArgName() {
        return this.defaultArgName;
    }

    public Comparator<Option> getOptionComparator() {
        return this.optionComparator;
    }

    public void setOptionComparator(Comparator<Option> comparator) {
        this.optionComparator = comparator;
    }

    public void printHelp(String cmdLineSyntax, Options options) {
        this.printHelp(this.getWidth(), cmdLineSyntax, null, options, null, false);
    }

    public void printHelp(String cmdLineSyntax, Options options, boolean autoUsage) {
        this.printHelp(this.getWidth(), cmdLineSyntax, null, options, null, autoUsage);
    }

    public void printHelp(String cmdLineSyntax, String header, Options options, String footer) {
        this.printHelp(cmdLineSyntax, header, options, footer, false);
    }

    public void printHelp(String cmdLineSyntax, String header, Options options, String footer, boolean autoUsage) {
        this.printHelp(this.getWidth(), cmdLineSyntax, header, options, footer, autoUsage);
    }

    public void printHelp(int width, String cmdLineSyntax, String header, Options options, String footer) {
        this.printHelp(width, cmdLineSyntax, header, options, footer, false);
    }

    public void printHelp(int width, String cmdLineSyntax, String header, Options options, String footer, boolean autoUsage) {
        PrintWriter pw = new PrintWriter(System.out);
        this.printHelp(pw, width, cmdLineSyntax, header, options, this.getLeftPadding(), this.getDescPadding(), footer, autoUsage);
        pw.flush();
    }

    public void printHelp(PrintWriter pw, int width, String cmdLineSyntax, String header, Options options, int leftPad, int descPad, String footer) {
        this.printHelp(pw, width, cmdLineSyntax, header, options, leftPad, descPad, footer, false);
    }

    public void printHelp(PrintWriter pw, int width, String cmdLineSyntax, String header, Options options, int leftPad, int descPad, String footer, boolean autoUsage) {
        if (cmdLineSyntax == null || cmdLineSyntax.length() == 0) {
            throw new IllegalArgumentException("cmdLineSyntax not provided");
        }
        if (autoUsage) {
            this.printUsage(pw, width, cmdLineSyntax, options);
        } else {
            this.printUsage(pw, width, cmdLineSyntax);
        }
        if (header != null && header.trim().length() > 0) {
            this.printWrapped(pw, width, header);
        }
        this.printOptions(pw, width, options, leftPad, descPad);
        if (footer != null && footer.trim().length() > 0) {
            this.printWrapped(pw, width, footer);
        }
    }

    public void printUsage(PrintWriter pw, int width, String app, Options options) {
        StringBuffer buff = new StringBuffer(this.getSyntaxPrefix()).append(app).append(DEFAULT_LONG_OPT_SEPARATOR);
        ArrayList<OptionGroup> processedGroups = new ArrayList<OptionGroup>();
        ArrayList<Option> optList = new ArrayList<Option>(options.getOptions());
        if (this.getOptionComparator() != null) {
            Collections.sort(optList, this.getOptionComparator());
        }
        Iterator it = optList.iterator();
        while (it.hasNext()) {
            Option option = (Option)it.next();
            OptionGroup group = options.getOptionGroup(option);
            if (group != null) {
                if (!processedGroups.contains(group)) {
                    processedGroups.add(group);
                    this.appendOptionGroup(buff, group);
                }
            } else {
                this.appendOption(buff, option, option.isRequired());
            }
            if (!it.hasNext()) continue;
            buff.append(DEFAULT_LONG_OPT_SEPARATOR);
        }
        this.printWrapped(pw, width, buff.toString().indexOf(32) + 1, buff.toString());
    }

    private void appendOptionGroup(StringBuffer buff, OptionGroup group) {
        if (!group.isRequired()) {
            buff.append("[");
        }
        ArrayList<Option> optList = new ArrayList<Option>(group.getOptions());
        if (this.getOptionComparator() != null) {
            Collections.sort(optList, this.getOptionComparator());
        }
        Iterator it = optList.iterator();
        while (it.hasNext()) {
            this.appendOption(buff, (Option)it.next(), true);
            if (!it.hasNext()) continue;
            buff.append(" | ");
        }
        if (!group.isRequired()) {
            buff.append("]");
        }
    }

    private void appendOption(StringBuffer buff, Option option, boolean required) {
        if (!required) {
            buff.append("[");
        }
        if (option.getOpt() != null) {
            buff.append(DEFAULT_OPT_PREFIX).append(option.getOpt());
        } else {
            buff.append(DEFAULT_LONG_OPT_PREFIX).append(option.getLongOpt());
        }
        if (option.hasArg() && (option.getArgName() == null || option.getArgName().length() != 0)) {
            buff.append(option.getOpt() == null ? this.longOptSeparator : DEFAULT_LONG_OPT_SEPARATOR);
            buff.append("<").append(option.getArgName() != null ? option.getArgName() : this.getArgName()).append(">");
        }
        if (!required) {
            buff.append("]");
        }
    }

    public void printUsage(PrintWriter pw, int width, String cmdLineSyntax) {
        int argPos = cmdLineSyntax.indexOf(32) + 1;
        this.printWrapped(pw, width, this.getSyntaxPrefix().length() + argPos, this.getSyntaxPrefix() + cmdLineSyntax);
    }

    public void printOptions(PrintWriter pw, int width, Options options, int leftPad, int descPad) {
        StringBuffer sb = new StringBuffer();
        this.renderOptions(sb, width, options, leftPad, descPad);
        pw.println(sb.toString());
    }

    public void printWrapped(PrintWriter pw, int width, String text) {
        this.printWrapped(pw, width, 0, text);
    }

    public void printWrapped(PrintWriter pw, int width, int nextLineTabStop, String text) {
        StringBuffer sb = new StringBuffer(text.length());
        this.renderWrappedTextBlock(sb, width, nextLineTabStop, text);
        pw.println(sb.toString());
    }

    protected StringBuffer renderOptions(StringBuffer sb, int width, Options options, int leftPad, int descPad) {
        String lpad = this.createPadding(leftPad);
        String dpad = this.createPadding(descPad);
        int max = 0;
        ArrayList<StringBuffer> prefixList = new ArrayList<StringBuffer>();
        List<Option> optList = options.helpOptions();
        if (this.getOptionComparator() != null) {
            Collections.sort(optList, this.getOptionComparator());
        }
        for (Option option : optList) {
            StringBuffer optBuf = new StringBuffer();
            if (option.getOpt() == null) {
                optBuf.append(lpad).append("   ").append(this.getLongOptPrefix()).append(option.getLongOpt());
            } else {
                optBuf.append(lpad).append(this.getOptPrefix()).append(option.getOpt());
                if (option.hasLongOpt()) {
                    optBuf.append(',').append(this.getLongOptPrefix()).append(option.getLongOpt());
                }
            }
            if (option.hasArg()) {
                String argName = option.getArgName();
                if (argName != null && argName.length() == 0) {
                    optBuf.append(' ');
                } else {
                    optBuf.append(option.hasLongOpt() ? this.longOptSeparator : DEFAULT_LONG_OPT_SEPARATOR);
                    optBuf.append("<").append(argName != null ? option.getArgName() : this.getArgName()).append(">");
                }
            }
            prefixList.add(optBuf);
            max = optBuf.length() > max ? optBuf.length() : max;
        }
        int x = 0;
        Iterator<Option> it = optList.iterator();
        while (it.hasNext()) {
            StringBuilder optBuf;
            Option option = it.next();
            if ((optBuf = new StringBuilder(((StringBuffer)prefixList.get(x++)).toString())).length() < max) {
                optBuf.append(this.createPadding(max - optBuf.length()));
            }
            optBuf.append(dpad);
            int nextLineTabStop = max + descPad;
            if (option.getDescription() != null) {
                optBuf.append(option.getDescription());
            }
            this.renderWrappedText(sb, width, nextLineTabStop, optBuf.toString());
            if (!it.hasNext()) continue;
            sb.append(this.getNewLine());
        }
        return sb;
    }

    protected StringBuffer renderWrappedText(StringBuffer sb, int width, int nextLineTabStop, String text) {
        int pos = this.findWrapPos(text, width, 0);
        if (pos == -1) {
            sb.append(this.rtrim(text));
            return sb;
        }
        sb.append(this.rtrim(text.substring(0, pos))).append(this.getNewLine());
        if (nextLineTabStop >= width) {
            nextLineTabStop = 1;
        }
        String padding = this.createPadding(nextLineTabStop);
        while (true) {
            if ((pos = this.findWrapPos(text = padding + text.substring(pos).trim(), width, 0)) == -1) {
                sb.append(text);
                return sb;
            }
            if (text.length() > width && pos == nextLineTabStop - 1) {
                pos = width;
            }
            sb.append(this.rtrim(text.substring(0, pos))).append(this.getNewLine());
        }
    }

    private Appendable renderWrappedTextBlock(StringBuffer sb, int width, int nextLineTabStop, String text) {
        try {
            String line;
            BufferedReader in = new BufferedReader(new StringReader(text));
            boolean firstLine = true;
            while ((line = in.readLine()) != null) {
                if (!firstLine) {
                    sb.append(this.getNewLine());
                } else {
                    firstLine = false;
                }
                this.renderWrappedText(sb, width, nextLineTabStop, line);
            }
        } catch (IOException iOException) {
            // empty catch block
        }
        return sb;
    }

    protected int findWrapPos(String text, int width, int startPos) {
        char c;
        int pos = text.indexOf(10, startPos);
        if (pos != -1 && pos <= width) {
            return pos + 1;
        }
        pos = text.indexOf(9, startPos);
        if (pos != -1 && pos <= width) {
            return pos + 1;
        }
        if (startPos + width >= text.length()) {
            return -1;
        }
        for (pos = startPos + width; pos >= startPos && (c = text.charAt(pos)) != ' ' && c != '\n' && c != '\r'; --pos) {
        }
        if (pos > startPos) {
            return pos;
        }
        pos = startPos + width;
        return pos == text.length() ? -1 : pos;
    }

    protected String createPadding(int len) {
        char[] padding = new char[len];
        Arrays.fill(padding, ' ');
        return new String(padding);
    }

    protected String rtrim(String s) {
        int pos;
        if (s == null || s.length() == 0) {
            return s;
        }
        for (pos = s.length(); pos > 0 && Character.isWhitespace(s.charAt(pos - 1)); --pos) {
        }
        return s.substring(0, pos);
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    private static class OptionComparator
    implements Comparator<Option>,
    Serializable {
        private static final long serialVersionUID = 5305467873966684014L;

        private OptionComparator() {
        }

        @Override
        public int compare(Option opt1, Option opt2) {
            return opt1.getKey().compareToIgnoreCase(opt2.getKey());
        }
    }
}

