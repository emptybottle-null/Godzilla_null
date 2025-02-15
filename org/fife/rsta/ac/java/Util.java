/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.text.StringCharacterIterator;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.fife.rsta.ac.java.buildpath.SourceLocation;
import org.fife.rsta.ac.java.classreader.ClassFile;
import org.fife.rsta.ac.java.rjc.ast.CompilationUnit;

public class Util {
    static final Pattern DOC_COMMENT_LINE_HEADER = Pattern.compile("\\s*\\n\\s*\\*");
    static final Pattern LINK_TAG_MEMBER_PATTERN = Pattern.compile("(?:\\w+\\.)*\\w+(?:#\\w+(?:\\([^\\)]*\\))?)?|#\\w+(?:\\([^\\)]*\\))?");
    private static CompilationUnit lastCUFromDisk;
    private static SourceLocation lastCUFileParam;
    private static ClassFile lastCUClassFileParam;

    private Util() {
    }

    private static void appendDocCommentTail(StringBuilder sb, StringBuilder tail) {
        String temp;
        String token;
        StringBuilder params = null;
        StringBuilder returns = null;
        StringBuilder throwsItems = null;
        StringBuilder see = null;
        StringBuilder seeTemp = null;
        StringBuilder since = null;
        StringBuilder author = null;
        StringBuilder version = null;
        StringBuilder unknowns = null;
        boolean inParams = false;
        boolean inThrows = false;
        boolean inReturns = false;
        boolean inSeeAlso = false;
        boolean inSince = false;
        boolean inAuthor = false;
        boolean inVersion = false;
        boolean inUnknowns = false;
        String[] st = tail.toString().split("[ \t\r\n\f]+");
        int i = 0;
        while (i < st.length && (token = st[i++]) != null) {
            if ("@param".equals(token) && i < st.length) {
                token = st[i++];
                if (params == null) {
                    params = new StringBuilder("<b>Parameters:</b><p class='indented'>");
                } else {
                    params.append("<br>");
                }
                params.append("<b>").append(token).append("</b> ");
                inSeeAlso = false;
                inParams = true;
                inReturns = false;
                inThrows = false;
                inSince = false;
                inAuthor = false;
                inVersion = false;
                inUnknowns = false;
                continue;
            }
            if ("@return".equals(token) && i < st.length) {
                if (returns == null) {
                    returns = new StringBuilder("<b>Returns:</b><p class='indented'>");
                }
                inSeeAlso = false;
                inReturns = true;
                inParams = false;
                inThrows = false;
                inSince = false;
                inAuthor = false;
                inVersion = false;
                inUnknowns = false;
                continue;
            }
            if ("@see".equals(token) && i < st.length) {
                if (see == null) {
                    see = new StringBuilder("<b>See Also:</b><p class='indented'>");
                    seeTemp = new StringBuilder();
                } else {
                    if (seeTemp.length() > 0) {
                        temp = seeTemp.substring(0, seeTemp.length() - 1);
                        Util.appendLinkTagText(see, temp);
                    }
                    see.append("<br>");
                    seeTemp.setLength(0);
                }
                inSeeAlso = true;
                inReturns = false;
                inParams = false;
                inThrows = false;
                inSince = false;
                inAuthor = false;
                inVersion = false;
                inUnknowns = false;
                continue;
            }
            if ("@throws".equals(token) || "@exception".equals(token) && i < st.length) {
                token = st[i++];
                if (throwsItems == null) {
                    throwsItems = new StringBuilder("<b>Throws:</b><p class='indented'>");
                } else {
                    throwsItems.append("<br>");
                }
                throwsItems.append("<b>").append(token).append("</b> ");
                inSeeAlso = false;
                inParams = false;
                inReturns = false;
                inThrows = true;
                inSince = false;
                inAuthor = false;
                inVersion = false;
                inUnknowns = false;
                continue;
            }
            if ("@since".equals(token) && i < st.length) {
                if (since == null) {
                    since = new StringBuilder("<b>Since:</b><p class='indented'>");
                }
                inSeeAlso = false;
                inReturns = false;
                inParams = false;
                inThrows = false;
                inSince = true;
                inAuthor = false;
                inVersion = false;
                inUnknowns = false;
                continue;
            }
            if ("@author".equals(token) && i < st.length) {
                if (author == null) {
                    author = new StringBuilder("<b>Author:</b><p class='indented'>");
                } else {
                    author.append("<br>");
                }
                inSeeAlso = false;
                inReturns = false;
                inParams = false;
                inThrows = false;
                inSince = false;
                inAuthor = true;
                inVersion = false;
                inUnknowns = false;
                continue;
            }
            if ("@version".equals(token) && i < st.length) {
                if (version == null) {
                    version = new StringBuilder("<b>Version:</b><p class='indented'>");
                } else {
                    version.append("<br>");
                }
                inSeeAlso = false;
                inReturns = false;
                inParams = false;
                inThrows = false;
                inSince = false;
                inAuthor = false;
                inVersion = true;
                inUnknowns = false;
                continue;
            }
            if (token.startsWith("@") && token.length() > 1) {
                if (unknowns == null) {
                    unknowns = new StringBuilder();
                } else {
                    unknowns.append("</p>");
                }
                unknowns.append("<b>").append(token).append("</b><p class='indented'>");
                inSeeAlso = false;
                inParams = false;
                inReturns = false;
                inThrows = false;
                inSince = false;
                inAuthor = false;
                inVersion = false;
                inUnknowns = true;
                continue;
            }
            if (inParams) {
                params.append(token).append(' ');
                continue;
            }
            if (inReturns) {
                returns.append(token).append(' ');
                continue;
            }
            if (inSeeAlso) {
                seeTemp.append(token).append(' ');
                continue;
            }
            if (inThrows) {
                throwsItems.append(token).append(' ');
                continue;
            }
            if (inSince) {
                since.append(token).append(' ');
                continue;
            }
            if (inAuthor) {
                author.append(token).append(' ');
                continue;
            }
            if (inVersion) {
                version.append(token).append(' ');
                continue;
            }
            if (!inUnknowns) continue;
            unknowns.append(token).append(' ');
        }
        sb.append("<p>");
        if (params != null) {
            sb.append((CharSequence)params).append("</p>");
        }
        if (returns != null) {
            sb.append((CharSequence)returns).append("</p>");
        }
        if (throwsItems != null) {
            sb.append((CharSequence)throwsItems).append("</p>");
        }
        if (see != null) {
            if (seeTemp.length() > 0) {
                temp = seeTemp.substring(0, seeTemp.length() - 1);
                Util.appendLinkTagText(see, temp);
            }
            see.append("<br>");
            sb.append((CharSequence)see).append("</p>");
        }
        if (author != null) {
            sb.append((CharSequence)author).append("</p>");
        }
        if (version != null) {
            sb.append((CharSequence)version).append("</p>");
        }
        if (since != null) {
            sb.append((CharSequence)since).append("</p>");
        }
        if (unknowns != null) {
            sb.append((CharSequence)unknowns).append("</p>");
        }
    }

    private static void appendLinkTagText(StringBuilder appendTo, String linkContent) {
        Matcher m = LINK_TAG_MEMBER_PATTERN.matcher(linkContent = linkContent.trim());
        if (m.find() && m.start() == 0) {
            String match;
            appendTo.append("<a href='");
            String link = match = m.group(0);
            String text = null;
            if (match.length() == linkContent.length()) {
                int pound = match.indexOf(35);
                if (pound == 0) {
                    text = match.substring(1);
                } else if (pound > 0) {
                    String prefix = match.substring(0, pound);
                    if ("java.lang.Object".equals(prefix)) {
                        text = match.substring(pound + 1);
                    }
                } else {
                    text = match;
                }
            } else {
                int offs;
                for (offs = match.length(); offs < linkContent.length() && Character.isWhitespace(linkContent.charAt(offs)); ++offs) {
                }
                if (offs < linkContent.length()) {
                    text = linkContent.substring(offs);
                }
            }
            if (text == null) {
                text = linkContent;
            }
            text = Util.fixLinkText(text);
            appendTo.append(link).append("'>").append(text);
            appendTo.append("</a>");
        } else if (linkContent.startsWith("<a")) {
            appendTo.append(linkContent);
        } else {
            System.out.println("Unmatched linkContent: " + linkContent);
            appendTo.append(linkContent);
        }
    }

    public static String docCommentToHtml(String dc) {
        if (dc == null) {
            return null;
        }
        if (dc.endsWith("*/")) {
            dc = dc.substring(0, dc.length() - 2);
        }
        Matcher m = DOC_COMMENT_LINE_HEADER.matcher(dc);
        dc = m.replaceAll("\n");
        StringBuilder html = new StringBuilder("<html><style> .indented { margin-top: 0px; padding-left: 30pt; } </style><body>");
        StringBuilder tailBuf = null;
        BufferedReader r = new BufferedReader(new StringReader(dc));
        try {
            boolean inPreBlock;
            int offs;
            String line = r.readLine().substring(3);
            line = Util.possiblyStripDocCommentTail(line);
            for (offs = 0; offs < line.length() && Character.isWhitespace(line.charAt(offs)); ++offs) {
            }
            if (offs < line.length()) {
                html.append(line.substring(offs));
            }
            html.append((inPreBlock = Util.isInPreBlock(line, false)) ? (char)'\n' : ' ');
            while ((line = r.readLine()) != null) {
                line = Util.possiblyStripDocCommentTail(line);
                if (tailBuf != null) {
                    tailBuf.append(line).append(' ');
                    continue;
                }
                if (line.trim().startsWith("@")) {
                    tailBuf = new StringBuilder();
                    tailBuf.append(line).append(' ');
                    continue;
                }
                html.append(line);
                inPreBlock = Util.isInPreBlock(line, inPreBlock);
                html.append(inPreBlock ? (char)'\n' : ' ');
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        html = Util.fixDocComment(html);
        if (tailBuf != null) {
            Util.appendDocCommentTail(html, Util.fixDocComment(tailBuf));
        }
        return html.toString();
    }

    public static String forXML(String aText) {
        StringBuilder result = new StringBuilder();
        StringCharacterIterator iterator = new StringCharacterIterator(aText);
        char character = iterator.current();
        while (character != '\uffff') {
            if (character == '<') {
                result.append("&lt;");
            } else if (character == '>') {
                result.append("&gt;");
            } else if (character == '\"') {
                result.append("&quot;");
            } else if (character == '\'') {
                result.append("&#039;");
            } else if (character == '&') {
                result.append("&amp;");
            } else {
                result.append(character);
            }
            character = iterator.next();
        }
        return result.toString();
    }

    private static StringBuilder fixDocComment(StringBuilder text) {
        int closingBrace;
        int index = text.indexOf("{@");
        if (index == -1) {
            return text;
        }
        StringBuilder sb = new StringBuilder();
        int textOffs = 0;
        while ((closingBrace = Util.indexOf('}', text, index + 2)) > -1) {
            sb.append(text, textOffs, index);
            String content = text.substring(index + 2, closingBrace);
            index = textOffs = closingBrace + 1;
            if (content.startsWith("code ")) {
                sb.append("<code>").append(Util.forXML(content.substring(5))).append("</code>");
            } else if (content.startsWith("link ")) {
                sb.append("<code>");
                Util.appendLinkTagText(sb, content.substring(5));
                sb.append("</code>");
            } else if (content.startsWith("linkplain ")) {
                Util.appendLinkTagText(sb, content.substring(10));
            } else if (content.startsWith("literal ")) {
                sb.append(content.substring(8));
            } else {
                sb.append("<code>").append(content).append("</code>");
            }
            if ((index = text.indexOf("{@", index)) > -1) continue;
        }
        if (textOffs < text.length()) {
            sb.append(text.substring(textOffs));
        }
        return sb;
    }

    private static String fixLinkText(String text) {
        if (text.startsWith("#")) {
            return text.substring(1);
        }
        return text.replace('#', '.');
    }

    public static CompilationUnit getCompilationUnitFromDisk(SourceLocation loc, ClassFile cf) {
        if (loc == lastCUFileParam && cf == lastCUClassFileParam) {
            return lastCUFromDisk;
        }
        lastCUFileParam = loc;
        lastCUClassFileParam = cf;
        CompilationUnit cu = null;
        if (loc != null) {
            try {
                cu = loc.getCompilationUnit(cf);
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
        lastCUFromDisk = cu;
        return cu;
    }

    public static final String getUnqualified(String clazz) {
        int dot = clazz.lastIndexOf(46);
        if (dot > -1) {
            clazz = clazz.substring(dot + 1);
        }
        return clazz;
    }

    private static int indexOf(char ch, CharSequence sb, int offs) {
        while (offs < sb.length()) {
            if (ch == sb.charAt(offs)) {
                return offs;
            }
            ++offs;
        }
        return -1;
    }

    public static final boolean isFullyQualified(String str) {
        return str.indexOf(46) > -1;
    }

    private static boolean isInPreBlock(String line, boolean prevValue) {
        int lastPre = line.lastIndexOf("pre>");
        if (lastPre <= 0) {
            return prevValue;
        }
        char prevChar = line.charAt(lastPre - 1);
        if (prevChar == '<') {
            return true;
        }
        if (prevChar == '/' && lastPre >= 2 && line.charAt(lastPre - 2) == '<') {
            return false;
        }
        return prevValue;
    }

    private static String possiblyStripDocCommentTail(String str) {
        if (str.endsWith("*/")) {
            str = str.substring(0, str.length() - 2);
        }
        return str;
    }

    public static final String[] splitOnChar(String str, int ch) {
        int pos;
        ArrayList<String> list = new ArrayList<String>(3);
        int old = 0;
        while ((pos = str.indexOf(ch, old)) > -1) {
            list.add(str.substring(old, pos));
            old = pos + 1;
        }
        list.add(str.substring(old));
        String[] array = new String[list.size()];
        return list.toArray(array);
    }
}

