/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.cli;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.Date;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

public class PatternOptionBuilder {
    public static final Class<String> STRING_VALUE = String.class;
    public static final Class<Object> OBJECT_VALUE = Object.class;
    public static final Class<Number> NUMBER_VALUE = Number.class;
    public static final Class<Date> DATE_VALUE = Date.class;
    public static final Class<?> CLASS_VALUE = Class.class;
    public static final Class<FileInputStream> EXISTING_FILE_VALUE = FileInputStream.class;
    public static final Class<File> FILE_VALUE = File.class;
    public static final Class<File[]> FILES_VALUE = File[].class;
    public static final Class<URL> URL_VALUE = URL.class;

    public static Object getValueClass(char ch) {
        switch (ch) {
            case '@': {
                return OBJECT_VALUE;
            }
            case ':': {
                return STRING_VALUE;
            }
            case '%': {
                return NUMBER_VALUE;
            }
            case '+': {
                return CLASS_VALUE;
            }
            case '#': {
                return DATE_VALUE;
            }
            case '<': {
                return EXISTING_FILE_VALUE;
            }
            case '>': {
                return FILE_VALUE;
            }
            case '*': {
                return FILES_VALUE;
            }
            case '/': {
                return URL_VALUE;
            }
        }
        return null;
    }

    public static boolean isValueCode(char ch) {
        return ch == '@' || ch == ':' || ch == '%' || ch == '+' || ch == '#' || ch == '<' || ch == '>' || ch == '*' || ch == '/' || ch == '!';
    }

    public static Options parsePattern(String pattern) {
        int opt = 32;
        boolean required = false;
        Class type = null;
        Options options = new Options();
        for (int i = 0; i < pattern.length(); ++i) {
            int ch = pattern.charAt(i);
            if (!PatternOptionBuilder.isValueCode((char)ch)) {
                if (opt != 32) {
                    Option option = Option.builder(String.valueOf((char)opt)).hasArg(type != null).required(required).type(type).build();
                    options.addOption(option);
                    required = false;
                    type = null;
                    opt = 32;
                }
                opt = ch;
                continue;
            }
            if (ch == 33) {
                required = true;
                continue;
            }
            type = (Class)PatternOptionBuilder.getValueClass((char)ch);
        }
        if (opt != 32) {
            Option option = Option.builder(String.valueOf((char)opt)).hasArg(type != null).required(required).type(type).build();
            options.addOption(option);
        }
        return options;
    }
}

