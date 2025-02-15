/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core.env;

import java.security.AccessControlException;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.SpringProperties;
import org.springframework.core.convert.support.ConfigurableConversionService;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.ConfigurablePropertyResolver;
import org.springframework.core.env.MissingRequiredPropertiesException;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.Profiles;
import org.springframework.core.env.PropertySource;
import org.springframework.core.env.PropertySourcesPropertyResolver;
import org.springframework.core.env.ReadOnlySystemAttributesMap;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

public abstract class AbstractEnvironment
implements ConfigurableEnvironment {
    public static final String IGNORE_GETENV_PROPERTY_NAME = "spring.getenv.ignore";
    public static final String ACTIVE_PROFILES_PROPERTY_NAME = "spring.profiles.active";
    public static final String DEFAULT_PROFILES_PROPERTY_NAME = "spring.profiles.default";
    protected static final String RESERVED_DEFAULT_PROFILE_NAME = "default";
    protected final Log logger = LogFactory.getLog(this.getClass());
    private final Set<String> activeProfiles = new LinkedHashSet<String>();
    private final Set<String> defaultProfiles = new LinkedHashSet<String>(this.getReservedDefaultProfiles());
    private final MutablePropertySources propertySources;
    private final ConfigurablePropertyResolver propertyResolver;

    public AbstractEnvironment() {
        this(new MutablePropertySources());
    }

    protected AbstractEnvironment(MutablePropertySources propertySources) {
        this.propertySources = propertySources;
        this.propertyResolver = this.createPropertyResolver(propertySources);
        this.customizePropertySources(propertySources);
    }

    protected ConfigurablePropertyResolver createPropertyResolver(MutablePropertySources propertySources) {
        return new PropertySourcesPropertyResolver(propertySources);
    }

    protected final ConfigurablePropertyResolver getPropertyResolver() {
        return this.propertyResolver;
    }

    protected void customizePropertySources(MutablePropertySources propertySources) {
    }

    protected Set<String> getReservedDefaultProfiles() {
        return Collections.singleton(RESERVED_DEFAULT_PROFILE_NAME);
    }

    @Override
    public String[] getActiveProfiles() {
        return StringUtils.toStringArray(this.doGetActiveProfiles());
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    protected Set<String> doGetActiveProfiles() {
        Set<String> set = this.activeProfiles;
        synchronized (set) {
            String profiles;
            if (this.activeProfiles.isEmpty() && StringUtils.hasText(profiles = this.doGetActiveProfilesProperty())) {
                this.setActiveProfiles(StringUtils.commaDelimitedListToStringArray(StringUtils.trimAllWhitespace(profiles)));
            }
            return this.activeProfiles;
        }
    }

    @Nullable
    protected String doGetActiveProfilesProperty() {
        return this.getProperty(ACTIVE_PROFILES_PROPERTY_NAME);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setActiveProfiles(String ... profiles) {
        Assert.notNull((Object)profiles, "Profile array must not be null");
        if (this.logger.isDebugEnabled()) {
            this.logger.debug("Activating profiles " + Arrays.asList(profiles));
        }
        Set<String> set = this.activeProfiles;
        synchronized (set) {
            this.activeProfiles.clear();
            for (String profile : profiles) {
                this.validateProfile(profile);
                this.activeProfiles.add(profile);
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void addActiveProfile(String profile) {
        if (this.logger.isDebugEnabled()) {
            this.logger.debug("Activating profile '" + profile + "'");
        }
        this.validateProfile(profile);
        this.doGetActiveProfiles();
        Set<String> set = this.activeProfiles;
        synchronized (set) {
            this.activeProfiles.add(profile);
        }
    }

    @Override
    public String[] getDefaultProfiles() {
        return StringUtils.toStringArray(this.doGetDefaultProfiles());
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    protected Set<String> doGetDefaultProfiles() {
        Set<String> set = this.defaultProfiles;
        synchronized (set) {
            String profiles;
            if (this.defaultProfiles.equals(this.getReservedDefaultProfiles()) && StringUtils.hasText(profiles = this.doGetDefaultProfilesProperty())) {
                this.setDefaultProfiles(StringUtils.commaDelimitedListToStringArray(StringUtils.trimAllWhitespace(profiles)));
            }
            return this.defaultProfiles;
        }
    }

    @Nullable
    protected String doGetDefaultProfilesProperty() {
        return this.getProperty(DEFAULT_PROFILES_PROPERTY_NAME);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setDefaultProfiles(String ... profiles) {
        Assert.notNull((Object)profiles, "Profile array must not be null");
        Set<String> set = this.defaultProfiles;
        synchronized (set) {
            this.defaultProfiles.clear();
            for (String profile : profiles) {
                this.validateProfile(profile);
                this.defaultProfiles.add(profile);
            }
        }
    }

    @Override
    @Deprecated
    public boolean acceptsProfiles(String ... profiles) {
        Assert.notEmpty((Object[])profiles, "Must specify at least one profile");
        for (String profile : profiles) {
            if (!(StringUtils.hasLength(profile) && profile.charAt(0) == '!' ? !this.isProfileActive(profile.substring(1)) : this.isProfileActive(profile))) continue;
            return true;
        }
        return false;
    }

    @Override
    public boolean acceptsProfiles(Profiles profiles) {
        Assert.notNull((Object)profiles, "Profiles must not be null");
        return profiles.matches(this::isProfileActive);
    }

    protected boolean isProfileActive(String profile) {
        this.validateProfile(profile);
        Set<String> currentActiveProfiles = this.doGetActiveProfiles();
        return currentActiveProfiles.contains(profile) || currentActiveProfiles.isEmpty() && this.doGetDefaultProfiles().contains(profile);
    }

    protected void validateProfile(String profile) {
        if (!StringUtils.hasText(profile)) {
            throw new IllegalArgumentException("Invalid profile [" + profile + "]: must contain text");
        }
        if (profile.charAt(0) == '!') {
            throw new IllegalArgumentException("Invalid profile [" + profile + "]: must not begin with ! operator");
        }
    }

    @Override
    public MutablePropertySources getPropertySources() {
        return this.propertySources;
    }

    @Override
    public Map<String, Object> getSystemProperties() {
        try {
            return System.getProperties();
        } catch (AccessControlException ex) {
            return new ReadOnlySystemAttributesMap(){

                @Override
                @Nullable
                protected String getSystemAttribute(String attributeName) {
                    try {
                        return System.getProperty(attributeName);
                    } catch (AccessControlException ex) {
                        if (AbstractEnvironment.this.logger.isInfoEnabled()) {
                            AbstractEnvironment.this.logger.info("Caught AccessControlException when accessing system property '" + attributeName + "'; its value will be returned [null]. Reason: " + ex.getMessage());
                        }
                        return null;
                    }
                }
            };
        }
    }

    @Override
    public Map<String, Object> getSystemEnvironment() {
        if (this.suppressGetenvAccess()) {
            return Collections.emptyMap();
        }
        try {
            return System.getenv();
        } catch (AccessControlException ex) {
            return new ReadOnlySystemAttributesMap(){

                @Override
                @Nullable
                protected String getSystemAttribute(String attributeName) {
                    try {
                        return System.getenv(attributeName);
                    } catch (AccessControlException ex) {
                        if (AbstractEnvironment.this.logger.isInfoEnabled()) {
                            AbstractEnvironment.this.logger.info("Caught AccessControlException when accessing system environment variable '" + attributeName + "'; its value will be returned [null]. Reason: " + ex.getMessage());
                        }
                        return null;
                    }
                }
            };
        }
    }

    protected boolean suppressGetenvAccess() {
        return SpringProperties.getFlag(IGNORE_GETENV_PROPERTY_NAME);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void merge(ConfigurableEnvironment parent) {
        Object[] parentDefaultProfiles;
        for (PropertySource<?> ps : parent.getPropertySources()) {
            if (this.propertySources.contains(ps.getName())) continue;
            this.propertySources.addLast(ps);
        }
        Object[] parentActiveProfiles = parent.getActiveProfiles();
        if (!ObjectUtils.isEmpty(parentActiveProfiles)) {
            PropertySource<?> ps;
            ps = this.activeProfiles;
            synchronized (ps) {
                Collections.addAll(this.activeProfiles, parentActiveProfiles);
            }
        }
        if (!ObjectUtils.isEmpty(parentDefaultProfiles = parent.getDefaultProfiles())) {
            Set<String> set = this.defaultProfiles;
            synchronized (set) {
                this.defaultProfiles.remove(RESERVED_DEFAULT_PROFILE_NAME);
                Collections.addAll(this.defaultProfiles, parentDefaultProfiles);
            }
        }
    }

    @Override
    public ConfigurableConversionService getConversionService() {
        return this.propertyResolver.getConversionService();
    }

    @Override
    public void setConversionService(ConfigurableConversionService conversionService) {
        this.propertyResolver.setConversionService(conversionService);
    }

    @Override
    public void setPlaceholderPrefix(String placeholderPrefix) {
        this.propertyResolver.setPlaceholderPrefix(placeholderPrefix);
    }

    @Override
    public void setPlaceholderSuffix(String placeholderSuffix) {
        this.propertyResolver.setPlaceholderSuffix(placeholderSuffix);
    }

    @Override
    public void setValueSeparator(@Nullable String valueSeparator) {
        this.propertyResolver.setValueSeparator(valueSeparator);
    }

    @Override
    public void setIgnoreUnresolvableNestedPlaceholders(boolean ignoreUnresolvableNestedPlaceholders) {
        this.propertyResolver.setIgnoreUnresolvableNestedPlaceholders(ignoreUnresolvableNestedPlaceholders);
    }

    @Override
    public void setRequiredProperties(String ... requiredProperties) {
        this.propertyResolver.setRequiredProperties(requiredProperties);
    }

    @Override
    public void validateRequiredProperties() throws MissingRequiredPropertiesException {
        this.propertyResolver.validateRequiredProperties();
    }

    @Override
    public boolean containsProperty(String key) {
        return this.propertyResolver.containsProperty(key);
    }

    @Override
    @Nullable
    public String getProperty(String key) {
        return this.propertyResolver.getProperty(key);
    }

    @Override
    public String getProperty(String key, String defaultValue) {
        return this.propertyResolver.getProperty(key, defaultValue);
    }

    @Override
    @Nullable
    public <T> T getProperty(String key, Class<T> targetType) {
        return this.propertyResolver.getProperty(key, targetType);
    }

    @Override
    public <T> T getProperty(String key, Class<T> targetType, T defaultValue) {
        return this.propertyResolver.getProperty(key, targetType, defaultValue);
    }

    @Override
    public String getRequiredProperty(String key) throws IllegalStateException {
        return this.propertyResolver.getRequiredProperty(key);
    }

    @Override
    public <T> T getRequiredProperty(String key, Class<T> targetType) throws IllegalStateException {
        return this.propertyResolver.getRequiredProperty(key, targetType);
    }

    @Override
    public String resolvePlaceholders(String text) {
        return this.propertyResolver.resolvePlaceholders(text);
    }

    @Override
    public String resolveRequiredPlaceholders(String text) throws IllegalArgumentException {
        return this.propertyResolver.resolveRequiredPlaceholders(text);
    }

    public String toString() {
        return this.getClass().getSimpleName() + " {activeProfiles=" + this.activeProfiles + ", defaultProfiles=" + this.defaultProfiles + ", propertySources=" + this.propertySources + "}";
    }
}

