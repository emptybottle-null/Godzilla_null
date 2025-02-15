/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package util;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import util.functions;

public class TemplateEx {
    public static String run(String code) {
        HashMap<String, String> map = new HashMap<String, String>();
        String regex = "\\{[a-zA-Z][a-zA-Z0-9_]*\\}";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(code);
        while (m.find()) {
            String g = m.group(0);
            map.putIfAbsent(g, functions.getRandomString(functions.random(3, 8)));
        }
        for (String key : map.keySet()) {
            code = code.replace(key, (CharSequence)map.get(key));
        }
        return code;
    }
}

