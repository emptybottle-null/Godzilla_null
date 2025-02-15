/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.util;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.PathMatcher;
import org.springframework.util.StringUtils;

public class AntPathMatcher
implements PathMatcher {
    public static final String DEFAULT_PATH_SEPARATOR = "/";
    private static final int CACHE_TURNOFF_THRESHOLD = 65536;
    private static final Pattern VARIABLE_PATTERN = Pattern.compile("\\{[^/]+?\\}");
    private static final char[] WILDCARD_CHARS = new char[]{'*', '?', '{'};
    private String pathSeparator;
    private PathSeparatorPatternCache pathSeparatorPatternCache;
    private boolean caseSensitive = true;
    private boolean trimTokens = false;
    @Nullable
    private volatile Boolean cachePatterns;
    private final Map<String, String[]> tokenizedPatternCache = new ConcurrentHashMap<String, String[]>(256);
    final Map<String, AntPathStringMatcher> stringMatcherCache = new ConcurrentHashMap<String, AntPathStringMatcher>(256);

    public AntPathMatcher() {
        this.pathSeparator = DEFAULT_PATH_SEPARATOR;
        this.pathSeparatorPatternCache = new PathSeparatorPatternCache(DEFAULT_PATH_SEPARATOR);
    }

    public AntPathMatcher(String pathSeparator) {
        Assert.notNull((Object)pathSeparator, "'pathSeparator' is required");
        this.pathSeparator = pathSeparator;
        this.pathSeparatorPatternCache = new PathSeparatorPatternCache(pathSeparator);
    }

    public void setPathSeparator(@Nullable String pathSeparator) {
        this.pathSeparator = pathSeparator != null ? pathSeparator : DEFAULT_PATH_SEPARATOR;
        this.pathSeparatorPatternCache = new PathSeparatorPatternCache(this.pathSeparator);
    }

    public void setCaseSensitive(boolean caseSensitive) {
        this.caseSensitive = caseSensitive;
    }

    public void setTrimTokens(boolean trimTokens) {
        this.trimTokens = trimTokens;
    }

    public void setCachePatterns(boolean cachePatterns) {
        this.cachePatterns = cachePatterns;
    }

    private void deactivatePatternCache() {
        this.cachePatterns = false;
        this.tokenizedPatternCache.clear();
        this.stringMatcherCache.clear();
    }

    @Override
    public boolean isPattern(@Nullable String path) {
        if (path == null) {
            return false;
        }
        boolean uriVar = false;
        for (int i = 0; i < path.length(); ++i) {
            char c = path.charAt(i);
            if (c == '*' || c == '?') {
                return true;
            }
            if (c == '{') {
                uriVar = true;
                continue;
            }
            if (c != '}' || !uriVar) continue;
            return true;
        }
        return false;
    }

    @Override
    public boolean match(String pattern, String path) {
        return this.doMatch(pattern, path, true, null);
    }

    @Override
    public boolean matchStart(String pattern, String path) {
        return this.doMatch(pattern, path, false, null);
    }

    protected boolean doMatch(String pattern, @Nullable String path, boolean fullMatch, @Nullable Map<String, String> uriTemplateVariables) {
        String pattDir;
        int pathIdxStart;
        if (path == null || path.startsWith(this.pathSeparator) != pattern.startsWith(this.pathSeparator)) {
            return false;
        }
        String[] pattDirs = this.tokenizePattern(pattern);
        if (fullMatch && this.caseSensitive && !this.isPotentialMatch(path, pattDirs)) {
            return false;
        }
        String[] pathDirs = this.tokenizePath(path);
        int pattIdxStart = 0;
        int pattIdxEnd = pattDirs.length - 1;
        int pathIdxEnd = pathDirs.length - 1;
        for (pathIdxStart = 0; pattIdxStart <= pattIdxEnd && pathIdxStart <= pathIdxEnd && !"**".equals(pattDir = pattDirs[pattIdxStart]); ++pattIdxStart, ++pathIdxStart) {
            if (this.matchStrings(pattDir, pathDirs[pathIdxStart], uriTemplateVariables)) continue;
            return false;
        }
        if (pathIdxStart > pathIdxEnd) {
            if (pattIdxStart > pattIdxEnd) {
                return pattern.endsWith(this.pathSeparator) == path.endsWith(this.pathSeparator);
            }
            if (!fullMatch) {
                return true;
            }
            if (pattIdxStart == pattIdxEnd && pattDirs[pattIdxStart].equals("*") && path.endsWith(this.pathSeparator)) {
                return true;
            }
            for (int i = pattIdxStart; i <= pattIdxEnd; ++i) {
                if (pattDirs[i].equals("**")) continue;
                return false;
            }
            return true;
        }
        if (pattIdxStart > pattIdxEnd) {
            return false;
        }
        if (!fullMatch && "**".equals(pattDirs[pattIdxStart])) {
            return true;
        }
        while (pattIdxStart <= pattIdxEnd && pathIdxStart <= pathIdxEnd && !(pattDir = pattDirs[pattIdxEnd]).equals("**")) {
            if (!this.matchStrings(pattDir, pathDirs[pathIdxEnd], uriTemplateVariables)) {
                return false;
            }
            --pattIdxEnd;
            --pathIdxEnd;
        }
        if (pathIdxStart > pathIdxEnd) {
            for (int i = pattIdxStart; i <= pattIdxEnd; ++i) {
                if (pattDirs[i].equals("**")) continue;
                return false;
            }
            return true;
        }
        while (pattIdxStart != pattIdxEnd && pathIdxStart <= pathIdxEnd) {
            int patIdxTmp = -1;
            for (int i = pattIdxStart + 1; i <= pattIdxEnd; ++i) {
                if (!pattDirs[i].equals("**")) continue;
                patIdxTmp = i;
                break;
            }
            if (patIdxTmp == pattIdxStart + 1) {
                ++pattIdxStart;
                continue;
            }
            int patLength = patIdxTmp - pattIdxStart - 1;
            int strLength = pathIdxEnd - pathIdxStart + 1;
            int foundIdx = -1;
            block6: for (int i = 0; i <= strLength - patLength; ++i) {
                for (int j = 0; j < patLength; ++j) {
                    String subPat = pattDirs[pattIdxStart + j + 1];
                    String subStr = pathDirs[pathIdxStart + i + j];
                    if (!this.matchStrings(subPat, subStr, uriTemplateVariables)) continue block6;
                }
                foundIdx = pathIdxStart + i;
                break;
            }
            if (foundIdx == -1) {
                return false;
            }
            pattIdxStart = patIdxTmp;
            pathIdxStart = foundIdx + patLength;
        }
        for (int i = pattIdxStart; i <= pattIdxEnd; ++i) {
            if (pattDirs[i].equals("**")) continue;
            return false;
        }
        return true;
    }

    private boolean isPotentialMatch(String path, String[] pattDirs) {
        if (!this.trimTokens) {
            int pos = 0;
            for (String pattDir : pattDirs) {
                int skipped = this.skipSeparator(path, pos, this.pathSeparator);
                if ((skipped = this.skipSegment(path, pos += skipped, pattDir)) < pattDir.length()) {
                    return skipped > 0 || pattDir.length() > 0 && this.isWildcardChar(pattDir.charAt(0));
                }
                pos += skipped;
            }
        }
        return true;
    }

    private int skipSegment(String path, int pos, String prefix) {
        int skipped = 0;
        for (int i = 0; i < prefix.length(); ++i) {
            char c = prefix.charAt(i);
            if (this.isWildcardChar(c)) {
                return skipped;
            }
            int currPos = pos + skipped;
            if (currPos >= path.length()) {
                return 0;
            }
            if (c != path.charAt(currPos)) continue;
            ++skipped;
        }
        return skipped;
    }

    private int skipSeparator(String path, int pos, String separator) {
        int skipped = 0;
        while (path.startsWith(separator, pos + skipped)) {
            skipped += separator.length();
        }
        return skipped;
    }

    private boolean isWildcardChar(char c) {
        for (char candidate : WILDCARD_CHARS) {
            if (c != candidate) continue;
            return true;
        }
        return false;
    }

    protected String[] tokenizePattern(String pattern) {
        String[] tokenized = null;
        Boolean cachePatterns = this.cachePatterns;
        if (cachePatterns == null || cachePatterns.booleanValue()) {
            tokenized = this.tokenizedPatternCache.get(pattern);
        }
        if (tokenized == null) {
            tokenized = this.tokenizePath(pattern);
            if (cachePatterns == null && this.tokenizedPatternCache.size() >= 65536) {
                this.deactivatePatternCache();
                return tokenized;
            }
            if (cachePatterns == null || cachePatterns.booleanValue()) {
                this.tokenizedPatternCache.put(pattern, tokenized);
            }
        }
        return tokenized;
    }

    protected String[] tokenizePath(String path) {
        return StringUtils.tokenizeToStringArray(path, this.pathSeparator, this.trimTokens, true);
    }

    private boolean matchStrings(String pattern, String str, @Nullable Map<String, String> uriTemplateVariables) {
        return this.getStringMatcher(pattern).matchStrings(str, uriTemplateVariables);
    }

    protected AntPathStringMatcher getStringMatcher(String pattern) {
        AntPathStringMatcher matcher = null;
        Boolean cachePatterns = this.cachePatterns;
        if (cachePatterns == null || cachePatterns.booleanValue()) {
            matcher = this.stringMatcherCache.get(pattern);
        }
        if (matcher == null) {
            matcher = new AntPathStringMatcher(pattern, this.caseSensitive);
            if (cachePatterns == null && this.stringMatcherCache.size() >= 65536) {
                this.deactivatePatternCache();
                return matcher;
            }
            if (cachePatterns == null || cachePatterns.booleanValue()) {
                this.stringMatcherCache.put(pattern, matcher);
            }
        }
        return matcher;
    }

    @Override
    public String extractPathWithinPattern(String pattern, String path) {
        String[] patternParts = StringUtils.tokenizeToStringArray(pattern, this.pathSeparator, this.trimTokens, true);
        String[] pathParts = StringUtils.tokenizeToStringArray(path, this.pathSeparator, this.trimTokens, true);
        StringBuilder builder = new StringBuilder();
        boolean pathStarted = false;
        for (int segment = 0; segment < patternParts.length; ++segment) {
            String patternPart = patternParts[segment];
            if (patternPart.indexOf(42) <= -1 && patternPart.indexOf(63) <= -1) continue;
            while (segment < pathParts.length) {
                if (pathStarted || segment == 0 && !pattern.startsWith(this.pathSeparator)) {
                    builder.append(this.pathSeparator);
                }
                builder.append(pathParts[segment]);
                pathStarted = true;
                ++segment;
            }
        }
        return builder.toString();
    }

    @Override
    public Map<String, String> extractUriTemplateVariables(String pattern, String path) {
        LinkedHashMap<String, String> variables = new LinkedHashMap<String, String>();
        boolean result = this.doMatch(pattern, path, true, variables);
        if (!result) {
            throw new IllegalStateException("Pattern \"" + pattern + "\" is not a match for \"" + path + "\"");
        }
        return variables;
    }

    @Override
    public String combine(String pattern1, String pattern2) {
        boolean ext2All;
        boolean pattern1ContainsUriVar;
        if (!StringUtils.hasText(pattern1) && !StringUtils.hasText(pattern2)) {
            return "";
        }
        if (!StringUtils.hasText(pattern1)) {
            return pattern2;
        }
        if (!StringUtils.hasText(pattern2)) {
            return pattern1;
        }
        boolean bl = pattern1ContainsUriVar = pattern1.indexOf(123) != -1;
        if (!pattern1.equals(pattern2) && !pattern1ContainsUriVar && this.match(pattern1, pattern2)) {
            return pattern2;
        }
        if (pattern1.endsWith(this.pathSeparatorPatternCache.getEndsOnWildCard())) {
            return this.concat(pattern1.substring(0, pattern1.length() - 2), pattern2);
        }
        if (pattern1.endsWith(this.pathSeparatorPatternCache.getEndsOnDoubleWildCard())) {
            return this.concat(pattern1, pattern2);
        }
        int starDotPos1 = pattern1.indexOf("*.");
        if (pattern1ContainsUriVar || starDotPos1 == -1 || this.pathSeparator.equals(".")) {
            return this.concat(pattern1, pattern2);
        }
        String ext1 = pattern1.substring(starDotPos1 + 1);
        int dotPos2 = pattern2.indexOf(46);
        String file2 = dotPos2 == -1 ? pattern2 : pattern2.substring(0, dotPos2);
        String ext2 = dotPos2 == -1 ? "" : pattern2.substring(dotPos2);
        boolean ext1All = ext1.equals(".*") || ext1.isEmpty();
        boolean bl2 = ext2All = ext2.equals(".*") || ext2.isEmpty();
        if (!ext1All && !ext2All) {
            throw new IllegalArgumentException("Cannot combine patterns: " + pattern1 + " vs " + pattern2);
        }
        String ext = ext1All ? ext2 : ext1;
        return file2 + ext;
    }

    private String concat(String path1, String path2) {
        boolean path1EndsWithSeparator = path1.endsWith(this.pathSeparator);
        boolean path2StartsWithSeparator = path2.startsWith(this.pathSeparator);
        if (path1EndsWithSeparator && path2StartsWithSeparator) {
            return path1 + path2.substring(1);
        }
        if (path1EndsWithSeparator || path2StartsWithSeparator) {
            return path1 + path2;
        }
        return path1 + this.pathSeparator + path2;
    }

    @Override
    public Comparator<String> getPatternComparator(String path) {
        return new AntPatternComparator(path);
    }

    private static class PathSeparatorPatternCache {
        private final String endsOnWildCard;
        private final String endsOnDoubleWildCard;

        public PathSeparatorPatternCache(String pathSeparator) {
            this.endsOnWildCard = pathSeparator + "*";
            this.endsOnDoubleWildCard = pathSeparator + "**";
        }

        public String getEndsOnWildCard() {
            return this.endsOnWildCard;
        }

        public String getEndsOnDoubleWildCard() {
            return this.endsOnDoubleWildCard;
        }
    }

    protected static class AntPatternComparator
    implements Comparator<String> {
        private final String path;

        public AntPatternComparator(String path) {
            this.path = path;
        }

        @Override
        public int compare(String pattern1, String pattern2) {
            PatternInfo info1 = new PatternInfo(pattern1);
            PatternInfo info2 = new PatternInfo(pattern2);
            if (info1.isLeastSpecific() && info2.isLeastSpecific()) {
                return 0;
            }
            if (info1.isLeastSpecific()) {
                return 1;
            }
            if (info2.isLeastSpecific()) {
                return -1;
            }
            boolean pattern1EqualsPath = pattern1.equals(this.path);
            boolean pattern2EqualsPath = pattern2.equals(this.path);
            if (pattern1EqualsPath && pattern2EqualsPath) {
                return 0;
            }
            if (pattern1EqualsPath) {
                return -1;
            }
            if (pattern2EqualsPath) {
                return 1;
            }
            if (info1.isPrefixPattern() && info2.isPrefixPattern()) {
                return info2.getLength() - info1.getLength();
            }
            if (info1.isPrefixPattern() && info2.getDoubleWildcards() == 0) {
                return 1;
            }
            if (info2.isPrefixPattern() && info1.getDoubleWildcards() == 0) {
                return -1;
            }
            if (info1.getTotalCount() != info2.getTotalCount()) {
                return info1.getTotalCount() - info2.getTotalCount();
            }
            if (info1.getLength() != info2.getLength()) {
                return info2.getLength() - info1.getLength();
            }
            if (info1.getSingleWildcards() < info2.getSingleWildcards()) {
                return -1;
            }
            if (info2.getSingleWildcards() < info1.getSingleWildcards()) {
                return 1;
            }
            if (info1.getUriVars() < info2.getUriVars()) {
                return -1;
            }
            if (info2.getUriVars() < info1.getUriVars()) {
                return 1;
            }
            return 0;
        }

        private static class PatternInfo {
            @Nullable
            private final String pattern;
            private int uriVars;
            private int singleWildcards;
            private int doubleWildcards;
            private boolean catchAllPattern;
            private boolean prefixPattern;
            @Nullable
            private Integer length;

            public PatternInfo(@Nullable String pattern) {
                this.pattern = pattern;
                if (this.pattern != null) {
                    this.initCounters();
                    this.catchAllPattern = this.pattern.equals("/**");
                    boolean bl = this.prefixPattern = !this.catchAllPattern && this.pattern.endsWith("/**");
                }
                if (this.uriVars == 0) {
                    this.length = this.pattern != null ? this.pattern.length() : 0;
                }
            }

            protected void initCounters() {
                int pos = 0;
                if (this.pattern != null) {
                    while (pos < this.pattern.length()) {
                        if (this.pattern.charAt(pos) == '{') {
                            ++this.uriVars;
                            ++pos;
                            continue;
                        }
                        if (this.pattern.charAt(pos) == '*') {
                            if (pos + 1 < this.pattern.length() && this.pattern.charAt(pos + 1) == '*') {
                                ++this.doubleWildcards;
                                pos += 2;
                                continue;
                            }
                            if (pos > 0 && !this.pattern.substring(pos - 1).equals(".*")) {
                                ++this.singleWildcards;
                                ++pos;
                                continue;
                            }
                            ++pos;
                            continue;
                        }
                        ++pos;
                    }
                }
            }

            public int getUriVars() {
                return this.uriVars;
            }

            public int getSingleWildcards() {
                return this.singleWildcards;
            }

            public int getDoubleWildcards() {
                return this.doubleWildcards;
            }

            public boolean isLeastSpecific() {
                return this.pattern == null || this.catchAllPattern;
            }

            public boolean isPrefixPattern() {
                return this.prefixPattern;
            }

            public int getTotalCount() {
                return this.uriVars + this.singleWildcards + 2 * this.doubleWildcards;
            }

            public int getLength() {
                if (this.length == null) {
                    this.length = this.pattern != null ? VARIABLE_PATTERN.matcher(this.pattern).replaceAll("#").length() : 0;
                }
                return this.length;
            }
        }
    }

    protected static class AntPathStringMatcher {
        private static final Pattern GLOB_PATTERN = Pattern.compile("\\?|\\*|\\{((?:\\{[^/]+?\\}|[^/{}]|\\\\[{}])+?)\\}");
        private static final String DEFAULT_VARIABLE_PATTERN = "((?s).*)";
        private final String rawPattern;
        private final boolean caseSensitive;
        private final boolean exactMatch;
        @Nullable
        private final Pattern pattern;
        private final List<String> variableNames = new ArrayList<String>();

        public AntPathStringMatcher(String pattern) {
            this(pattern, true);
        }

        public AntPathStringMatcher(String pattern, boolean caseSensitive) {
            this.rawPattern = pattern;
            this.caseSensitive = caseSensitive;
            StringBuilder patternBuilder = new StringBuilder();
            Matcher matcher = GLOB_PATTERN.matcher(pattern);
            int end = 0;
            while (matcher.find()) {
                patternBuilder.append(this.quote(pattern, end, matcher.start()));
                String match = matcher.group();
                if ("?".equals(match)) {
                    patternBuilder.append('.');
                } else if ("*".equals(match)) {
                    patternBuilder.append(".*");
                } else if (match.startsWith("{") && match.endsWith("}")) {
                    int colonIdx = match.indexOf(58);
                    if (colonIdx == -1) {
                        patternBuilder.append(DEFAULT_VARIABLE_PATTERN);
                        this.variableNames.add(matcher.group(1));
                    } else {
                        String variablePattern = match.substring(colonIdx + 1, match.length() - 1);
                        patternBuilder.append('(');
                        patternBuilder.append(variablePattern);
                        patternBuilder.append(')');
                        String variableName = match.substring(1, colonIdx);
                        this.variableNames.add(variableName);
                    }
                }
                end = matcher.end();
            }
            if (end == 0) {
                this.exactMatch = true;
                this.pattern = null;
            } else {
                this.exactMatch = false;
                patternBuilder.append(this.quote(pattern, end, pattern.length()));
                this.pattern = this.caseSensitive ? Pattern.compile(patternBuilder.toString()) : Pattern.compile(patternBuilder.toString(), 2);
            }
        }

        private String quote(String s, int start, int end) {
            if (start == end) {
                return "";
            }
            return Pattern.quote(s.substring(start, end));
        }

        public boolean matchStrings(String str, @Nullable Map<String, String> uriTemplateVariables) {
            Matcher matcher;
            if (this.exactMatch) {
                return this.caseSensitive ? this.rawPattern.equals(str) : this.rawPattern.equalsIgnoreCase(str);
            }
            if (this.pattern != null && (matcher = this.pattern.matcher(str)).matches()) {
                if (uriTemplateVariables != null) {
                    if (this.variableNames.size() != matcher.groupCount()) {
                        throw new IllegalArgumentException("The number of capturing groups in the pattern segment " + this.pattern + " does not match the number of URI template variables it defines, which can occur if capturing groups are used in a URI template regex. Use non-capturing groups instead.");
                    }
                    for (int i = 1; i <= matcher.groupCount(); ++i) {
                        String name = this.variableNames.get(i - 1);
                        String value = matcher.group(i);
                        uriTemplateVariables.put(name, value);
                    }
                }
                return true;
            }
            return false;
        }
    }
}

