/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.regex;

import java.util.HashMap;
import java.util.Map;
import org.apache.xmlbeans.impl.common.XMLChar;
import org.apache.xmlbeans.impl.regex.RegularExpression;

public class SchemaRegularExpression
extends RegularExpression {
    static final Map knownPatterns = SchemaRegularExpression.buildKnownPatternMap();

    private SchemaRegularExpression(String pattern) {
        super(pattern, "X");
    }

    public static RegularExpression forPattern(String s) {
        SchemaRegularExpression tre = (SchemaRegularExpression)knownPatterns.get(s);
        if (tre != null) {
            return tre;
        }
        return new RegularExpression(s, "X");
    }

    private static Map buildKnownPatternMap() {
        HashMap<String, SchemaRegularExpression> result = new HashMap<String, SchemaRegularExpression>();
        result.put("\\c+", new SchemaRegularExpression("\\c+"){

            @Override
            public boolean matches(String s) {
                return XMLChar.isValidNmtoken(s);
            }
        });
        result.put("\\i\\c*", new SchemaRegularExpression("\\i\\c*"){

            @Override
            public boolean matches(String s) {
                return XMLChar.isValidName(s);
            }
        });
        result.put("[\\i-[:]][\\c-[:]]*", new SchemaRegularExpression("[\\i-[:]][\\c-[:]]*"){

            @Override
            public boolean matches(String s) {
                return XMLChar.isValidNCName(s);
            }
        });
        return result;
    }
}

