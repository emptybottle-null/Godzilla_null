/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.util;

import java.util.Comparator;
import java.util.Map;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.PathMatcher;
import org.springframework.util.RouteMatcher;

public class SimpleRouteMatcher
implements RouteMatcher {
    private final PathMatcher pathMatcher;

    public SimpleRouteMatcher(PathMatcher pathMatcher) {
        Assert.notNull((Object)pathMatcher, "PathMatcher is required");
        this.pathMatcher = pathMatcher;
    }

    public PathMatcher getPathMatcher() {
        return this.pathMatcher;
    }

    @Override
    public RouteMatcher.Route parseRoute(String route) {
        return new DefaultRoute(route);
    }

    @Override
    public boolean isPattern(String route) {
        return this.pathMatcher.isPattern(route);
    }

    @Override
    public String combine(String pattern1, String pattern2) {
        return this.pathMatcher.combine(pattern1, pattern2);
    }

    @Override
    public boolean match(String pattern, RouteMatcher.Route route) {
        return this.pathMatcher.match(pattern, route.value());
    }

    @Override
    @Nullable
    public Map<String, String> matchAndExtract(String pattern, RouteMatcher.Route route) {
        if (!this.match(pattern, route)) {
            return null;
        }
        return this.pathMatcher.extractUriTemplateVariables(pattern, route.value());
    }

    @Override
    public Comparator<String> getPatternComparator(RouteMatcher.Route route) {
        return this.pathMatcher.getPatternComparator(route.value());
    }

    private static class DefaultRoute
    implements RouteMatcher.Route {
        private final String path;

        DefaultRoute(String path) {
            this.path = path;
        }

        @Override
        public String value() {
            return this.path;
        }

        public String toString() {
            return this.value();
        }
    }
}

