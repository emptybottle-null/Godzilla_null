/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.codec.language.bm;

import java.util.Collections;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Set;
import org.apache.commons.codec.Resources;
import org.apache.commons.codec.language.bm.NameType;

public class Languages {
    public static final String ANY = "any";
    private static final Map<NameType, Languages> LANGUAGES = new EnumMap<NameType, Languages>(NameType.class);
    private final Set<String> languages;
    public static final LanguageSet NO_LANGUAGES;
    public static final LanguageSet ANY_LANGUAGE;

    public static Languages getInstance(NameType nameType) {
        return LANGUAGES.get((Object)nameType);
    }

    public static Languages getInstance(String languagesResourceName) {
        HashSet<String> ls = new HashSet<String>();
        try (Scanner lsScanner = new Scanner(Resources.getInputStream(languagesResourceName), "UTF-8");){
            boolean inExtendedComment = false;
            while (lsScanner.hasNextLine()) {
                String line = lsScanner.nextLine().trim();
                if (inExtendedComment) {
                    if (!line.endsWith("*/")) continue;
                    inExtendedComment = false;
                    continue;
                }
                if (line.startsWith("/*")) {
                    inExtendedComment = true;
                    continue;
                }
                if (line.length() <= 0) continue;
                ls.add(line);
            }
            Languages languages = new Languages(Collections.unmodifiableSet(ls));
            return languages;
        }
    }

    private static String langResourceName(NameType nameType) {
        return String.format("org/apache/commons/codec/language/bm/%s_languages.txt", nameType.getName());
    }

    private Languages(Set<String> languages) {
        this.languages = languages;
    }

    public Set<String> getLanguages() {
        return this.languages;
    }

    static {
        for (NameType s : NameType.values()) {
            LANGUAGES.put(s, Languages.getInstance(Languages.langResourceName(s)));
        }
        NO_LANGUAGES = new LanguageSet(){

            @Override
            public boolean contains(String language) {
                return false;
            }

            @Override
            public String getAny() {
                throw new NoSuchElementException("Can't fetch any language from the empty language set.");
            }

            @Override
            public boolean isEmpty() {
                return true;
            }

            @Override
            public boolean isSingleton() {
                return false;
            }

            @Override
            public LanguageSet restrictTo(LanguageSet other) {
                return this;
            }

            @Override
            public LanguageSet merge(LanguageSet other) {
                return other;
            }

            public String toString() {
                return "NO_LANGUAGES";
            }
        };
        ANY_LANGUAGE = new LanguageSet(){

            @Override
            public boolean contains(String language) {
                return true;
            }

            @Override
            public String getAny() {
                throw new NoSuchElementException("Can't fetch any language from the any language set.");
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean isSingleton() {
                return false;
            }

            @Override
            public LanguageSet restrictTo(LanguageSet other) {
                return other;
            }

            @Override
            public LanguageSet merge(LanguageSet other) {
                return other;
            }

            public String toString() {
                return "ANY_LANGUAGE";
            }
        };
    }

    public static final class SomeLanguages
    extends LanguageSet {
        private final Set<String> languages;

        private SomeLanguages(Set<String> languages) {
            this.languages = Collections.unmodifiableSet(languages);
        }

        @Override
        public boolean contains(String language) {
            return this.languages.contains(language);
        }

        @Override
        public String getAny() {
            return this.languages.iterator().next();
        }

        public Set<String> getLanguages() {
            return this.languages;
        }

        @Override
        public boolean isEmpty() {
            return this.languages.isEmpty();
        }

        @Override
        public boolean isSingleton() {
            return this.languages.size() == 1;
        }

        @Override
        public LanguageSet restrictTo(LanguageSet other) {
            if (other == NO_LANGUAGES) {
                return other;
            }
            if (other == ANY_LANGUAGE) {
                return this;
            }
            SomeLanguages sl = (SomeLanguages)other;
            HashSet<String> ls = new HashSet<String>(Math.min(this.languages.size(), sl.languages.size()));
            for (String lang : this.languages) {
                if (!sl.languages.contains(lang)) continue;
                ls.add(lang);
            }
            return SomeLanguages.from(ls);
        }

        @Override
        public LanguageSet merge(LanguageSet other) {
            if (other == NO_LANGUAGES) {
                return this;
            }
            if (other == ANY_LANGUAGE) {
                return other;
            }
            SomeLanguages sl = (SomeLanguages)other;
            HashSet<String> ls = new HashSet<String>(this.languages);
            for (String lang : sl.languages) {
                ls.add(lang);
            }
            return SomeLanguages.from(ls);
        }

        public String toString() {
            return "Languages(" + this.languages.toString() + ")";
        }
    }

    public static abstract class LanguageSet {
        public static LanguageSet from(Set<String> langs) {
            return langs.isEmpty() ? NO_LANGUAGES : new SomeLanguages(langs);
        }

        public abstract boolean contains(String var1);

        public abstract String getAny();

        public abstract boolean isEmpty();

        public abstract boolean isSingleton();

        public abstract LanguageSet restrictTo(LanguageSet var1);

        abstract LanguageSet merge(LanguageSet var1);
    }
}

