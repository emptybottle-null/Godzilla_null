/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  com.sun.javadoc.Tag
 */
package org.apache.xmlbeans.impl.jam.annotation;

import com.sun.javadoc.Tag;
import java.util.Enumeration;
import java.util.Properties;
import org.apache.xmlbeans.impl.jam.annotation.JavadocTagParser;
import org.apache.xmlbeans.impl.jam.mutable.MAnnotatedElement;
import org.apache.xmlbeans.impl.jam.mutable.MAnnotation;

public class WhitespaceDelimitedTagParser
extends JavadocTagParser {
    @Override
    public void parse(MAnnotatedElement target, Tag tag) {
        MAnnotation[] anns = this.createAnnotations(target, tag);
        String tagText = tag.text();
        if (tagText == null) {
            return;
        }
        if ((tagText = tagText.trim()).length() == 0) {
            return;
        }
        Properties props = new Properties();
        this.parseAssignments(props, tagText);
        if (props.size() > 0) {
            Enumeration<?> names = props.propertyNames();
            while (names.hasMoreElements()) {
                String name = (String)names.nextElement();
                this.setValue(anns, name, props.getProperty(name));
            }
        } else {
            this.setSingleValueText(anns, tag);
        }
    }

    private void parseAssignments(Properties out, String line) {
        this.getLogger().verbose("PARSING LINE " + line, (Object)this);
        String originalLine = line;
        line = this.removeComments(line);
        while (null != line && -1 != line.indexOf("=")) {
            int keyStart = -1;
            int keyEnd = -1;
            int ind = 0;
            char c = line.charAt(ind);
            while (this.isBlank(c)) {
                c = line.charAt(++ind);
            }
            keyStart = ind;
            while (this.isLegal(line.charAt(ind))) {
                ++ind;
            }
            keyEnd = ind;
            String key = line.substring(keyStart, keyEnd);
            ind = line.indexOf("=");
            if (ind == -1) {
                return;
            }
            ++ind;
            try {
                c = line.charAt(ind);
            } catch (StringIndexOutOfBoundsException ex) {
                ex.printStackTrace();
            }
            while (this.isBlank(c)) {
                c = line.charAt(++ind);
            }
            int valueStart = -1;
            int valueEnd = -1;
            if (c == '\"') {
                valueStart = ++ind;
                while ('\"' != line.charAt(ind)) {
                    if (++ind < line.length()) continue;
                    this.getLogger().verbose("missing double quotes on line " + line, (Object)this);
                }
                valueEnd = ind;
            } else {
                valueStart = ind++;
                while (ind < line.length() && this.isLegal(line.charAt(ind))) {
                    ++ind;
                }
                valueEnd = ind;
            }
            String value = line.substring(valueStart, valueEnd);
            line = ind < line.length() ? line.substring(ind + 1) : null;
            this.getLogger().verbose("SETTING KEY:" + key + " VALUE:" + value, (Object)this);
            out.setProperty(key, value);
        }
    }

    private String removeComments(String value) {
        String result = "";
        int size = value.length();
        String current = value;
        int currentIndex = 0;
        int beginning = current.indexOf("//");
        int doubleQuotesIndex = current.indexOf("\"");
        if (-1 != doubleQuotesIndex && doubleQuotesIndex < beginning) {
            result = value;
        } else {
            while (currentIndex < size && beginning != -1) {
                beginning = value.indexOf("//", currentIndex);
                if (-1 == beginning) continue;
                if (beginning > 0 && value.charAt(beginning - 1) == ':') {
                    currentIndex = beginning + 2;
                    continue;
                }
                int end = value.indexOf(10, beginning);
                if (-1 == end) {
                    end = size;
                }
                result = result + value.substring(currentIndex, beginning).trim() + "\n";
                current = value.substring(end);
                currentIndex = end;
            }
            result = result + current;
        }
        return result.trim();
    }

    private boolean isBlank(char c) {
        return c == ' ' || c == '\t' || c == '\n';
    }

    private boolean isLegal(char c) {
        return !this.isBlank(c) && c != '=';
    }
}

