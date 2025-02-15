/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core.env;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.function.Predicate;
import org.springframework.core.env.Profiles;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

final class ProfilesParser {
    private ProfilesParser() {
    }

    static Profiles parse(String ... expressions) {
        Assert.notEmpty((Object[])expressions, "Must specify at least one profile");
        Profiles[] parsed = new Profiles[expressions.length];
        for (int i = 0; i < expressions.length; ++i) {
            parsed[i] = ProfilesParser.parseExpression(expressions[i]);
        }
        return new ParsedProfiles(expressions, parsed);
    }

    private static Profiles parseExpression(String expression) {
        Assert.hasText(expression, () -> "Invalid profile expression [" + expression + "]: must contain text");
        StringTokenizer tokens = new StringTokenizer(expression, "()&|!", true);
        return ProfilesParser.parseTokens(expression, tokens);
    }

    private static Profiles parseTokens(String expression, StringTokenizer tokens) {
        return ProfilesParser.parseTokens(expression, tokens, Context.NONE);
    }

    private static Profiles parseTokens(String expression, StringTokenizer tokens, Context context) {
        ArrayList<Profiles> elements = new ArrayList<Profiles>();
        Operator operator2 = null;
        block14: while (tokens.hasMoreTokens()) {
            String token = tokens.nextToken().trim();
            if (token.isEmpty()) continue;
            switch (token) {
                case "(": {
                    Profiles contents = ProfilesParser.parseTokens(expression, tokens, Context.BRACKET);
                    if (context == Context.INVERT) {
                        return contents;
                    }
                    elements.add(contents);
                    continue block14;
                }
                case "&": {
                    ProfilesParser.assertWellFormed(expression, operator2 == null || operator2 == Operator.AND);
                    operator2 = Operator.AND;
                    continue block14;
                }
                case "|": {
                    ProfilesParser.assertWellFormed(expression, operator2 == null || operator2 == Operator.OR);
                    operator2 = Operator.OR;
                    continue block14;
                }
                case "!": {
                    elements.add(ProfilesParser.not(ProfilesParser.parseTokens(expression, tokens, Context.INVERT)));
                    continue block14;
                }
                case ")": {
                    Profiles merged = ProfilesParser.merge(expression, elements, operator2);
                    if (context == Context.BRACKET) {
                        return merged;
                    }
                    elements.clear();
                    elements.add(merged);
                    operator2 = null;
                    continue block14;
                }
            }
            Profiles value = ProfilesParser.equals(token);
            if (context == Context.INVERT) {
                return value;
            }
            elements.add(value);
        }
        return ProfilesParser.merge(expression, elements, operator2);
    }

    private static Profiles merge(String expression, List<Profiles> elements, @Nullable Operator operator2) {
        ProfilesParser.assertWellFormed(expression, !elements.isEmpty());
        if (elements.size() == 1) {
            return elements.get(0);
        }
        Profiles[] profiles = elements.toArray(new Profiles[0]);
        return operator2 == Operator.AND ? ProfilesParser.and(profiles) : ProfilesParser.or(profiles);
    }

    private static void assertWellFormed(String expression, boolean wellFormed) {
        Assert.isTrue(wellFormed, () -> "Malformed profile expression [" + expression + "]");
    }

    private static Profiles or(Profiles ... profiles) {
        return activeProfile -> Arrays.stream(profiles).anyMatch(ProfilesParser.isMatch(activeProfile));
    }

    private static Profiles and(Profiles ... profiles) {
        return activeProfile -> Arrays.stream(profiles).allMatch(ProfilesParser.isMatch(activeProfile));
    }

    private static Profiles not(Profiles profiles) {
        return activeProfile -> !profiles.matches(activeProfile);
    }

    private static Profiles equals(String profile) {
        return activeProfile -> activeProfile.test(profile);
    }

    private static Predicate<Profiles> isMatch(Predicate<String> activeProfile) {
        return profiles -> profiles.matches(activeProfile);
    }

    private static class ParsedProfiles
    implements Profiles {
        private final Set<String> expressions = new LinkedHashSet<String>();
        private final Profiles[] parsed;

        ParsedProfiles(String[] expressions, Profiles[] parsed) {
            Collections.addAll(this.expressions, expressions);
            this.parsed = parsed;
        }

        @Override
        public boolean matches(Predicate<String> activeProfiles) {
            for (Profiles candidate : this.parsed) {
                if (!candidate.matches(activeProfiles)) continue;
                return true;
            }
            return false;
        }

        public int hashCode() {
            return this.expressions.hashCode();
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (this.getClass() != obj.getClass()) {
                return false;
            }
            ParsedProfiles that = (ParsedProfiles)obj;
            return this.expressions.equals(that.expressions);
        }

        public String toString() {
            return StringUtils.collectionToDelimitedString(this.expressions, " or ");
        }
    }

    private static enum Context {
        NONE,
        INVERT,
        BRACKET;

    }

    private static enum Operator {
        AND,
        OR;

    }
}

