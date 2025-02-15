/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.reflect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.CharMatcher;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.base.Splitter;
import com.google.common.base.StandardSystemProperty;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import com.google.common.collect.MultimapBuilder;
import com.google.common.collect.SetMultimap;
import com.google.common.collect.Sets;
import com.google.common.io.ByteSource;
import com.google.common.io.CharSource;
import com.google.common.io.Resources;
import com.google.common.reflect.Reflection;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.jar.Attributes;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.Manifest;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.checkerframework.checker.nullness.qual.Nullable;

@Beta
public final class ClassPath {
    private static final Logger logger = Logger.getLogger(ClassPath.class.getName());
    private static final Predicate<ClassInfo> IS_TOP_LEVEL = new Predicate<ClassInfo>(){

        @Override
        public boolean apply(ClassInfo info) {
            return info.className.indexOf(36) == -1;
        }
    };
    private static final Splitter CLASS_PATH_ATTRIBUTE_SEPARATOR = Splitter.on(" ").omitEmptyStrings();
    private static final String CLASS_FILE_NAME_EXTENSION = ".class";
    private final ImmutableSet<ResourceInfo> resources;

    private ClassPath(ImmutableSet<ResourceInfo> resources) {
        this.resources = resources;
    }

    public static ClassPath from(ClassLoader classloader) throws IOException {
        DefaultScanner scanner = new DefaultScanner();
        scanner.scan(classloader);
        return new ClassPath(scanner.getResources());
    }

    public ImmutableSet<ResourceInfo> getResources() {
        return this.resources;
    }

    public ImmutableSet<ClassInfo> getAllClasses() {
        return FluentIterable.from(this.resources).filter(ClassInfo.class).toSet();
    }

    public ImmutableSet<ClassInfo> getTopLevelClasses() {
        return FluentIterable.from(this.resources).filter(ClassInfo.class).filter(IS_TOP_LEVEL).toSet();
    }

    public ImmutableSet<ClassInfo> getTopLevelClasses(String packageName) {
        Preconditions.checkNotNull(packageName);
        ImmutableSet.Builder builder = ImmutableSet.builder();
        for (ClassInfo classInfo : this.getTopLevelClasses()) {
            if (!classInfo.getPackageName().equals(packageName)) continue;
            builder.add(classInfo);
        }
        return builder.build();
    }

    public ImmutableSet<ClassInfo> getTopLevelClassesRecursive(String packageName) {
        Preconditions.checkNotNull(packageName);
        String packagePrefix = packageName + '.';
        ImmutableSet.Builder builder = ImmutableSet.builder();
        for (ClassInfo classInfo : this.getTopLevelClasses()) {
            if (!classInfo.getName().startsWith(packagePrefix)) continue;
            builder.add(classInfo);
        }
        return builder.build();
    }

    @VisibleForTesting
    static String getClassName(String filename) {
        int classNameEnd = filename.length() - CLASS_FILE_NAME_EXTENSION.length();
        return filename.substring(0, classNameEnd).replace('/', '.');
    }

    @VisibleForTesting
    static File toFile(URL url) {
        Preconditions.checkArgument(url.getProtocol().equals("file"));
        try {
            return new File(url.toURI());
        } catch (URISyntaxException e) {
            return new File(url.getPath());
        }
    }

    @VisibleForTesting
    static final class DefaultScanner
    extends Scanner {
        private final SetMultimap<ClassLoader, String> resources = MultimapBuilder.hashKeys().linkedHashSetValues().build();

        DefaultScanner() {
        }

        ImmutableSet<ResourceInfo> getResources() {
            ImmutableSet.Builder builder = ImmutableSet.builder();
            for (Map.Entry entry : this.resources.entries()) {
                builder.add(ResourceInfo.of((String)entry.getValue(), (ClassLoader)entry.getKey()));
            }
            return builder.build();
        }

        @Override
        protected void scanJarFile(ClassLoader classloader, JarFile file) {
            Enumeration<JarEntry> entries = file.entries();
            while (entries.hasMoreElements()) {
                JarEntry entry = entries.nextElement();
                if (entry.isDirectory() || entry.getName().equals("META-INF/MANIFEST.MF")) continue;
                this.resources.get((Object)classloader).add(entry.getName());
            }
        }

        @Override
        protected void scanDirectory(ClassLoader classloader, File directory) throws IOException {
            HashSet<File> currentPath = new HashSet<File>();
            currentPath.add(directory.getCanonicalFile());
            this.scanDirectory(directory, classloader, "", currentPath);
        }

        private void scanDirectory(File directory, ClassLoader classloader, String packagePrefix, Set<File> currentPath) throws IOException {
            File[] files = directory.listFiles();
            if (files == null) {
                logger.warning("Cannot read directory " + directory);
                return;
            }
            for (File f : files) {
                String name = f.getName();
                if (f.isDirectory()) {
                    File deref = f.getCanonicalFile();
                    if (!currentPath.add(deref)) continue;
                    this.scanDirectory(deref, classloader, packagePrefix + name + "/", currentPath);
                    currentPath.remove(deref);
                    continue;
                }
                String resourceName = packagePrefix + name;
                if (resourceName.equals("META-INF/MANIFEST.MF")) continue;
                this.resources.get((Object)classloader).add(resourceName);
            }
        }
    }

    static abstract class Scanner {
        private final Set<File> scannedUris = Sets.newHashSet();

        Scanner() {
        }

        public final void scan(ClassLoader classloader) throws IOException {
            for (Map.Entry entry : Scanner.getClassPathEntries(classloader).entrySet()) {
                this.scan((File)entry.getKey(), (ClassLoader)entry.getValue());
            }
        }

        @VisibleForTesting
        final void scan(File file, ClassLoader classloader) throws IOException {
            if (this.scannedUris.add(file.getCanonicalFile())) {
                this.scanFrom(file, classloader);
            }
        }

        protected abstract void scanDirectory(ClassLoader var1, File var2) throws IOException;

        protected abstract void scanJarFile(ClassLoader var1, JarFile var2) throws IOException;

        private void scanFrom(File file, ClassLoader classloader) throws IOException {
            try {
                if (!file.exists()) {
                    return;
                }
            } catch (SecurityException e) {
                logger.warning("Cannot access " + file + ": " + e);
                return;
            }
            if (file.isDirectory()) {
                this.scanDirectory(classloader, file);
            } else {
                this.scanJar(file, classloader);
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        private void scanJar(File file, ClassLoader classloader) throws IOException {
            JarFile jarFile;
            try {
                jarFile = new JarFile(file);
            } catch (IOException e) {
                return;
            }
            try {
                for (File path : Scanner.getClassPathFromManifest(file, jarFile.getManifest())) {
                    this.scan(path, classloader);
                }
                this.scanJarFile(classloader, jarFile);
            } finally {
                try {
                    jarFile.close();
                } catch (IOException iOException) {}
            }
        }

        @VisibleForTesting
        static ImmutableSet<File> getClassPathFromManifest(File jarFile, @Nullable Manifest manifest) {
            if (manifest == null) {
                return ImmutableSet.of();
            }
            ImmutableSet.Builder builder = ImmutableSet.builder();
            String classpathAttribute = manifest.getMainAttributes().getValue(Attributes.Name.CLASS_PATH.toString());
            if (classpathAttribute != null) {
                for (String path : CLASS_PATH_ATTRIBUTE_SEPARATOR.split(classpathAttribute)) {
                    URL url;
                    try {
                        url = Scanner.getClassPathEntry(jarFile, path);
                    } catch (MalformedURLException e) {
                        logger.warning("Invalid Class-Path entry: " + path);
                        continue;
                    }
                    if (!url.getProtocol().equals("file")) continue;
                    builder.add(ClassPath.toFile(url));
                }
            }
            return builder.build();
        }

        @VisibleForTesting
        static ImmutableMap<File, ClassLoader> getClassPathEntries(ClassLoader classloader) {
            LinkedHashMap<File, ClassLoader> entries = Maps.newLinkedHashMap();
            ClassLoader parent = classloader.getParent();
            if (parent != null) {
                entries.putAll(Scanner.getClassPathEntries(parent));
            }
            for (URL url : Scanner.getClassLoaderUrls(classloader)) {
                File file;
                if (!url.getProtocol().equals("file") || entries.containsKey(file = ClassPath.toFile(url))) continue;
                entries.put(file, classloader);
            }
            return ImmutableMap.copyOf(entries);
        }

        private static ImmutableList<URL> getClassLoaderUrls(ClassLoader classloader) {
            if (classloader instanceof URLClassLoader) {
                return ImmutableList.copyOf(((URLClassLoader)classloader).getURLs());
            }
            if (classloader.equals(ClassLoader.getSystemClassLoader())) {
                return Scanner.parseJavaClassPath();
            }
            return ImmutableList.of();
        }

        @VisibleForTesting
        static ImmutableList<URL> parseJavaClassPath() {
            ImmutableList.Builder urls = ImmutableList.builder();
            for (String entry : Splitter.on(StandardSystemProperty.PATH_SEPARATOR.value()).split(StandardSystemProperty.JAVA_CLASS_PATH.value())) {
                try {
                    try {
                        urls.add(new File(entry).toURI().toURL());
                    } catch (SecurityException e) {
                        urls.add(new URL("file", null, new File(entry).getAbsolutePath()));
                    }
                } catch (MalformedURLException e) {
                    logger.log(Level.WARNING, "malformed classpath entry: " + entry, e);
                }
            }
            return urls.build();
        }

        @VisibleForTesting
        static URL getClassPathEntry(File jarFile, String path) throws MalformedURLException {
            return new URL(jarFile.toURI().toURL(), path);
        }
    }

    @Beta
    public static final class ClassInfo
    extends ResourceInfo {
        private final String className;

        ClassInfo(String resourceName, ClassLoader loader) {
            super(resourceName, loader);
            this.className = ClassPath.getClassName(resourceName);
        }

        public String getPackageName() {
            return Reflection.getPackageName(this.className);
        }

        public String getSimpleName() {
            int lastDollarSign = this.className.lastIndexOf(36);
            if (lastDollarSign != -1) {
                String innerClassName = this.className.substring(lastDollarSign + 1);
                return CharMatcher.digit().trimLeadingFrom(innerClassName);
            }
            String packageName = this.getPackageName();
            if (packageName.isEmpty()) {
                return this.className;
            }
            return this.className.substring(packageName.length() + 1);
        }

        public String getName() {
            return this.className;
        }

        public Class<?> load() {
            try {
                return this.loader.loadClass(this.className);
            } catch (ClassNotFoundException e) {
                throw new IllegalStateException(e);
            }
        }

        @Override
        public String toString() {
            return this.className;
        }
    }

    @Beta
    public static class ResourceInfo {
        private final String resourceName;
        final ClassLoader loader;

        static ResourceInfo of(String resourceName, ClassLoader loader) {
            if (resourceName.endsWith(ClassPath.CLASS_FILE_NAME_EXTENSION)) {
                return new ClassInfo(resourceName, loader);
            }
            return new ResourceInfo(resourceName, loader);
        }

        ResourceInfo(String resourceName, ClassLoader loader) {
            this.resourceName = Preconditions.checkNotNull(resourceName);
            this.loader = Preconditions.checkNotNull(loader);
        }

        public final URL url() {
            URL url = this.loader.getResource(this.resourceName);
            if (url == null) {
                throw new NoSuchElementException(this.resourceName);
            }
            return url;
        }

        public final ByteSource asByteSource() {
            return Resources.asByteSource(this.url());
        }

        public final CharSource asCharSource(Charset charset) {
            return Resources.asCharSource(this.url(), charset);
        }

        public final String getResourceName() {
            return this.resourceName;
        }

        public int hashCode() {
            return this.resourceName.hashCode();
        }

        public boolean equals(Object obj) {
            if (obj instanceof ResourceInfo) {
                ResourceInfo that = (ResourceInfo)obj;
                return this.resourceName.equals(that.resourceName) && this.loader == that.loader;
            }
            return false;
        }

        public String toString() {
            return this.resourceName;
        }
    }
}

