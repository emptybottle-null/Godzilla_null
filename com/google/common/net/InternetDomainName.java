/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.net;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Ascii;
import com.google.common.base.CharMatcher;
import com.google.common.base.Joiner;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableList;
import com.google.errorprone.annotations.Immutable;
import com.google.thirdparty.publicsuffix.PublicSuffixPatterns;
import com.google.thirdparty.publicsuffix.PublicSuffixType;
import java.util.List;
import org.checkerframework.checker.nullness.qual.Nullable;

@Immutable
@Beta
@GwtCompatible
public final class InternetDomainName {
    private static final CharMatcher DOTS_MATCHER = CharMatcher.anyOf(".\u3002\uff0e\uff61");
    private static final Splitter DOT_SPLITTER = Splitter.on('.');
    private static final Joiner DOT_JOINER = Joiner.on('.');
    private static final int NO_SUFFIX_FOUND = -1;
    private static final int MAX_PARTS = 127;
    private static final int MAX_LENGTH = 253;
    private static final int MAX_DOMAIN_PART_LENGTH = 63;
    private final String name;
    private final ImmutableList<String> parts;
    private final int publicSuffixIndex;
    private final int registrySuffixIndex;
    private static final CharMatcher DASH_MATCHER = CharMatcher.anyOf("-_");
    private static final CharMatcher PART_CHAR_MATCHER = CharMatcher.javaLetterOrDigit().or(DASH_MATCHER);

    InternetDomainName(String name) {
        name = Ascii.toLowerCase(DOTS_MATCHER.replaceFrom((CharSequence)name, '.'));
        if (name.endsWith(".")) {
            name = name.substring(0, name.length() - 1);
        }
        Preconditions.checkArgument(name.length() <= 253, "Domain name too long: '%s':", (Object)name);
        this.name = name;
        this.parts = ImmutableList.copyOf(DOT_SPLITTER.split(name));
        Preconditions.checkArgument(this.parts.size() <= 127, "Domain has too many parts: '%s'", (Object)name);
        Preconditions.checkArgument(InternetDomainName.validateSyntax(this.parts), "Not a valid domain name: '%s'", (Object)name);
        this.publicSuffixIndex = this.findSuffixOfType(Optional.absent());
        this.registrySuffixIndex = this.findSuffixOfType(Optional.of(PublicSuffixType.REGISTRY));
    }

    private int findSuffixOfType(Optional<PublicSuffixType> desiredType) {
        int partsSize = this.parts.size();
        for (int i = 0; i < partsSize; ++i) {
            String ancestorName = DOT_JOINER.join(this.parts.subList(i, partsSize));
            if (InternetDomainName.matchesType(desiredType, Optional.fromNullable(PublicSuffixPatterns.EXACT.get(ancestorName)))) {
                return i;
            }
            if (PublicSuffixPatterns.EXCLUDED.containsKey(ancestorName)) {
                return i + 1;
            }
            if (!InternetDomainName.matchesWildcardSuffixType(desiredType, ancestorName)) continue;
            return i;
        }
        return -1;
    }

    public static InternetDomainName from(String domain) {
        return new InternetDomainName(Preconditions.checkNotNull(domain));
    }

    private static boolean validateSyntax(List<String> parts) {
        int lastIndex = parts.size() - 1;
        if (!InternetDomainName.validatePart(parts.get(lastIndex), true)) {
            return false;
        }
        for (int i = 0; i < lastIndex; ++i) {
            String part = parts.get(i);
            if (InternetDomainName.validatePart(part, false)) continue;
            return false;
        }
        return true;
    }

    private static boolean validatePart(String part, boolean isFinalPart) {
        if (part.length() < 1 || part.length() > 63) {
            return false;
        }
        String asciiChars = CharMatcher.ascii().retainFrom(part);
        if (!PART_CHAR_MATCHER.matchesAllOf(asciiChars)) {
            return false;
        }
        if (DASH_MATCHER.matches(part.charAt(0)) || DASH_MATCHER.matches(part.charAt(part.length() - 1))) {
            return false;
        }
        return !isFinalPart || !CharMatcher.digit().matches(part.charAt(0));
    }

    public ImmutableList<String> parts() {
        return this.parts;
    }

    public boolean isPublicSuffix() {
        return this.publicSuffixIndex == 0;
    }

    public boolean hasPublicSuffix() {
        return this.publicSuffixIndex != -1;
    }

    public InternetDomainName publicSuffix() {
        return this.hasPublicSuffix() ? this.ancestor(this.publicSuffixIndex) : null;
    }

    public boolean isUnderPublicSuffix() {
        return this.publicSuffixIndex > 0;
    }

    public boolean isTopPrivateDomain() {
        return this.publicSuffixIndex == 1;
    }

    public InternetDomainName topPrivateDomain() {
        if (this.isTopPrivateDomain()) {
            return this;
        }
        Preconditions.checkState(this.isUnderPublicSuffix(), "Not under a public suffix: %s", (Object)this.name);
        return this.ancestor(this.publicSuffixIndex - 1);
    }

    public boolean isRegistrySuffix() {
        return this.registrySuffixIndex == 0;
    }

    public boolean hasRegistrySuffix() {
        return this.registrySuffixIndex != -1;
    }

    public InternetDomainName registrySuffix() {
        return this.hasRegistrySuffix() ? this.ancestor(this.registrySuffixIndex) : null;
    }

    public boolean isUnderRegistrySuffix() {
        return this.registrySuffixIndex > 0;
    }

    public boolean isTopDomainUnderRegistrySuffix() {
        return this.registrySuffixIndex == 1;
    }

    public InternetDomainName topDomainUnderRegistrySuffix() {
        if (this.isTopDomainUnderRegistrySuffix()) {
            return this;
        }
        Preconditions.checkState(this.isUnderRegistrySuffix(), "Not under a registry suffix: %s", (Object)this.name);
        return this.ancestor(this.registrySuffixIndex - 1);
    }

    public boolean hasParent() {
        return this.parts.size() > 1;
    }

    public InternetDomainName parent() {
        Preconditions.checkState(this.hasParent(), "Domain '%s' has no parent", (Object)this.name);
        return this.ancestor(1);
    }

    private InternetDomainName ancestor(int levels) {
        return InternetDomainName.from(DOT_JOINER.join(this.parts.subList(levels, this.parts.size())));
    }

    public InternetDomainName child(String leftParts) {
        return InternetDomainName.from(Preconditions.checkNotNull(leftParts) + "." + this.name);
    }

    public static boolean isValid(String name) {
        try {
            InternetDomainName.from(name);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    private static boolean matchesWildcardSuffixType(Optional<PublicSuffixType> desiredType, String domain) {
        List<String> pieces = DOT_SPLITTER.limit(2).splitToList(domain);
        return pieces.size() == 2 && InternetDomainName.matchesType(desiredType, Optional.fromNullable(PublicSuffixPatterns.UNDER.get(pieces.get(1))));
    }

    private static boolean matchesType(Optional<PublicSuffixType> desiredType, Optional<PublicSuffixType> actualType) {
        return desiredType.isPresent() ? desiredType.equals(actualType) : actualType.isPresent();
    }

    public String toString() {
        return this.name;
    }

    public boolean equals(@Nullable Object object) {
        if (object == this) {
            return true;
        }
        if (object instanceof InternetDomainName) {
            InternetDomainName that = (InternetDomainName)object;
            return this.name.equals(that.name);
        }
        return false;
    }

    public int hashCode() {
        return this.name.hashCode();
    }
}

