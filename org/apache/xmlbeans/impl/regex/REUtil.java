/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.regex;

import java.text.CharacterIterator;
import org.apache.xmlbeans.impl.regex.Match;
import org.apache.xmlbeans.impl.regex.ParseException;
import org.apache.xmlbeans.impl.regex.RegularExpression;

public final class REUtil {
    static final int CACHESIZE = 20;
    static final RegularExpression[] regexCache = new RegularExpression[20];

    private REUtil() {
    }

    static final int composeFromSurrogates(int high, int low) {
        return 65536 + (high - 55296 << 10) + low - 56320;
    }

    static final boolean isLowSurrogate(int ch) {
        return (ch & 0xFC00) == 56320;
    }

    static final boolean isHighSurrogate(int ch) {
        return (ch & 0xFC00) == 55296;
    }

    static final String decomposeToSurrogates(int ch) {
        char[] chs = new char[]{(char)(((ch -= 65536) >> 10) + 55296), (char)((ch & 0x3FF) + 56320)};
        return new String(chs);
    }

    static final String substring(CharacterIterator iterator, int begin, int end) {
        char[] src = new char[end - begin];
        for (int i = 0; i < src.length; ++i) {
            src[i] = iterator.setIndex(i + begin);
        }
        return new String(src);
    }

    static final int getOptionValue(int ch) {
        int ret = 0;
        switch (ch) {
            case 105: {
                ret = 2;
                break;
            }
            case 109: {
                ret = 8;
                break;
            }
            case 115: {
                ret = 4;
                break;
            }
            case 120: {
                ret = 16;
                break;
            }
            case 117: {
                ret = 32;
                break;
            }
            case 119: {
                ret = 64;
                break;
            }
            case 70: {
                ret = 256;
                break;
            }
            case 72: {
                ret = 128;
                break;
            }
            case 88: {
                ret = 512;
                break;
            }
            case 44: {
                ret = 1024;
                break;
            }
        }
        return ret;
    }

    static final int parseOptions(String opts) throws ParseException {
        if (opts == null) {
            return 0;
        }
        int options = 0;
        for (int i = 0; i < opts.length(); ++i) {
            int v = REUtil.getOptionValue(opts.charAt(i));
            if (v == 0) {
                throw new ParseException("Unknown Option: " + opts.substring(i), -1);
            }
            options |= v;
        }
        return options;
    }

    static final String createOptionString(int options) {
        StringBuffer sb = new StringBuffer(9);
        if ((options & 0x100) != 0) {
            sb.append('F');
        }
        if ((options & 0x80) != 0) {
            sb.append('H');
        }
        if ((options & 0x200) != 0) {
            sb.append('X');
        }
        if ((options & 2) != 0) {
            sb.append('i');
        }
        if ((options & 8) != 0) {
            sb.append('m');
        }
        if ((options & 4) != 0) {
            sb.append('s');
        }
        if ((options & 0x20) != 0) {
            sb.append('u');
        }
        if ((options & 0x40) != 0) {
            sb.append('w');
        }
        if ((options & 0x10) != 0) {
            sb.append('x');
        }
        if ((options & 0x400) != 0) {
            sb.append(',');
        }
        return sb.toString().intern();
    }

    static String stripExtendedComment(String regex) {
        int len = regex.length();
        StringBuffer buffer = new StringBuffer(len);
        int offset = 0;
        while (offset < len) {
            char ch;
            if ((ch = regex.charAt(offset++)) == '\t' || ch == '\n' || ch == '\f' || ch == '\r' || ch == ' ') continue;
            if (ch == '#') {
                while (offset < len && (ch = regex.charAt(offset++)) != '\r' && ch != '\n') {
                }
                continue;
            }
            if (ch == '\\' && offset < len) {
                char next = regex.charAt(offset);
                if (next == '#' || next == '\t' || next == '\n' || next == '\f' || next == '\r' || next == ' ') {
                    buffer.append(next);
                    ++offset;
                    continue;
                }
                buffer.append('\\');
                buffer.append(next);
                ++offset;
                continue;
            }
            buffer.append(ch);
        }
        return buffer.toString();
    }

    public static void main(String[] argv) {
        String pattern = null;
        try {
            String options = "";
            String target = null;
            if (argv.length == 0) {
                System.out.println("Error:Usage: java REUtil -i|-m|-s|-u|-w|-X regularExpression String");
                System.exit(0);
            }
            for (int i = 0; i < argv.length; ++i) {
                if (argv[i].length() == 0 || argv[i].charAt(0) != '-') {
                    if (pattern == null) {
                        pattern = argv[i];
                        continue;
                    }
                    if (target == null) {
                        target = argv[i];
                        continue;
                    }
                    System.err.println("Unnecessary: " + argv[i]);
                    continue;
                }
                if (argv[i].equals("-i")) {
                    options = options + "i";
                    continue;
                }
                if (argv[i].equals("-m")) {
                    options = options + "m";
                    continue;
                }
                if (argv[i].equals("-s")) {
                    options = options + "s";
                    continue;
                }
                if (argv[i].equals("-u")) {
                    options = options + "u";
                    continue;
                }
                if (argv[i].equals("-w")) {
                    options = options + "w";
                    continue;
                }
                if (argv[i].equals("-X")) {
                    options = options + "X";
                    continue;
                }
                System.err.println("Unknown option: " + argv[i]);
            }
            RegularExpression reg = new RegularExpression(pattern, options);
            System.out.println("RegularExpression: " + reg);
            Match match = new Match();
            reg.matches(target, match);
            for (int i = 0; i < match.getNumberOfGroups(); ++i) {
                if (i == 0) {
                    System.out.print("Matched range for the whole pattern: ");
                } else {
                    System.out.print("[" + i + "]: ");
                }
                if (match.getBeginning(i) < 0) {
                    System.out.println("-1");
                    continue;
                }
                System.out.print(match.getBeginning(i) + ", " + match.getEnd(i) + ", ");
                System.out.println("\"" + match.getCapturedText(i) + "\"");
            }
        } catch (ParseException pe) {
            if (pattern == null) {
                pe.printStackTrace();
            } else {
                System.err.println("org.apache.xerces.utils.regex.ParseException: " + pe.getMessage());
                String indent = "        ";
                System.err.println(indent + pattern);
                int loc = pe.getLocation();
                if (loc >= 0) {
                    System.err.print(indent);
                    for (int i = 0; i < loc; ++i) {
                        System.err.print("-");
                    }
                    System.err.println("^");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static RegularExpression createRegex(String pattern, String options) throws ParseException {
        RegularExpression re = null;
        int intOptions = REUtil.parseOptions(options);
        RegularExpression[] regularExpressionArray = regexCache;
        synchronized (regexCache) {
            int i;
            for (i = 0; i < 20; ++i) {
                RegularExpression cached = regexCache[i];
                if (cached == null) {
                    i = -1;
                    break;
                }
                if (!cached.equals(pattern, intOptions)) continue;
                re = cached;
                break;
            }
            if (re != null) {
                if (i != 0) {
                    System.arraycopy(regexCache, 0, regexCache, 1, i);
                    REUtil.regexCache[0] = re;
                }
            } else {
                re = new RegularExpression(pattern, options);
                System.arraycopy(regexCache, 0, regexCache, 1, 19);
                REUtil.regexCache[0] = re;
            }
            // ** MonitorExit[var4_4] (shouldn't be in output)
            return re;
        }
    }

    public static boolean matches(String regex, String target) throws ParseException {
        return REUtil.createRegex(regex, null).matches(target);
    }

    public static boolean matches(String regex, String options, String target) throws ParseException {
        return REUtil.createRegex(regex, options).matches(target);
    }

    public static String quoteMeta(String literal) {
        int len = literal.length();
        StringBuffer buffer = null;
        for (int i = 0; i < len; ++i) {
            char ch = literal.charAt(i);
            if (".*+?{[()|\\^$".indexOf(ch) >= 0) {
                if (buffer == null) {
                    buffer = new StringBuffer(i + (len - i) * 2);
                    if (i > 0) {
                        buffer.append(literal.substring(0, i));
                    }
                }
                buffer.append('\\');
                buffer.append(ch);
                continue;
            }
            if (buffer == null) continue;
            buffer.append(ch);
        }
        return buffer != null ? buffer.toString() : literal;
    }

    static void dumpString(String v) {
        for (int i = 0; i < v.length(); ++i) {
            System.out.print(Integer.toHexString(v.charAt(i)));
            System.out.print(" ");
        }
        System.out.println();
    }
}

