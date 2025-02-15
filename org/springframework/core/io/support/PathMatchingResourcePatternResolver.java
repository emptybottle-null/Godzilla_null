/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core.io.support;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.net.JarURLConnection;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.zip.ZipException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.UrlResource;
import org.springframework.core.io.VfsResource;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.io.support.VfsPatternUtils;
import org.springframework.lang.Nullable;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;
import org.springframework.util.PathMatcher;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;

public class PathMatchingResourcePatternResolver
implements ResourcePatternResolver {
    private static final Log logger = LogFactory.getLog(PathMatchingResourcePatternResolver.class);
    @Nullable
    private static Method equinoxResolveMethod;
    private final ResourceLoader resourceLoader;
    private PathMatcher pathMatcher = new AntPathMatcher();

    public PathMatchingResourcePatternResolver() {
        this.resourceLoader = new DefaultResourceLoader();
    }

    public PathMatchingResourcePatternResolver(ResourceLoader resourceLoader) {
        Assert.notNull((Object)resourceLoader, "ResourceLoader must not be null");
        this.resourceLoader = resourceLoader;
    }

    public PathMatchingResourcePatternResolver(@Nullable ClassLoader classLoader) {
        this.resourceLoader = new DefaultResourceLoader(classLoader);
    }

    public ResourceLoader getResourceLoader() {
        return this.resourceLoader;
    }

    @Override
    @Nullable
    public ClassLoader getClassLoader() {
        return this.getResourceLoader().getClassLoader();
    }

    public void setPathMatcher(PathMatcher pathMatcher) {
        Assert.notNull((Object)pathMatcher, "PathMatcher must not be null");
        this.pathMatcher = pathMatcher;
    }

    public PathMatcher getPathMatcher() {
        return this.pathMatcher;
    }

    @Override
    public Resource getResource(String location) {
        return this.getResourceLoader().getResource(location);
    }

    @Override
    public Resource[] getResources(String locationPattern) throws IOException {
        int prefixEnd;
        Assert.notNull((Object)locationPattern, "Location pattern must not be null");
        if (locationPattern.startsWith("classpath*:")) {
            if (this.getPathMatcher().isPattern(locationPattern.substring("classpath*:".length()))) {
                return this.findPathMatchingResources(locationPattern);
            }
            return this.findAllClassPathResources(locationPattern.substring("classpath*:".length()));
        }
        int n = prefixEnd = locationPattern.startsWith("war:") ? locationPattern.indexOf("*/") + 1 : locationPattern.indexOf(58) + 1;
        if (this.getPathMatcher().isPattern(locationPattern.substring(prefixEnd))) {
            return this.findPathMatchingResources(locationPattern);
        }
        return new Resource[]{this.getResourceLoader().getResource(locationPattern)};
    }

    protected Resource[] findAllClassPathResources(String location) throws IOException {
        String path = location;
        if (path.startsWith("/")) {
            path = path.substring(1);
        }
        Set<Resource> result = this.doFindAllClassPathResources(path);
        if (logger.isTraceEnabled()) {
            logger.trace("Resolved classpath location [" + location + "] to resources " + result);
        }
        return result.toArray(new Resource[0]);
    }

    protected Set<Resource> doFindAllClassPathResources(String path) throws IOException {
        Enumeration<URL> resourceUrls;
        LinkedHashSet<Resource> result = new LinkedHashSet<Resource>(16);
        ClassLoader cl = this.getClassLoader();
        Enumeration<URL> enumeration = resourceUrls = cl != null ? cl.getResources(path) : ClassLoader.getSystemResources(path);
        while (resourceUrls.hasMoreElements()) {
            URL url = resourceUrls.nextElement();
            result.add(this.convertClassLoaderURL(url));
        }
        if (!StringUtils.hasLength(path)) {
            this.addAllClassLoaderJarRoots(cl, result);
        }
        return result;
    }

    protected Resource convertClassLoaderURL(URL url) {
        return new UrlResource(url);
    }

    protected void addAllClassLoaderJarRoots(@Nullable ClassLoader classLoader, Set<Resource> result) {
        block11: {
            block10: {
                if (classLoader instanceof URLClassLoader) {
                    try {
                        for (URL url : ((URLClassLoader)classLoader).getURLs()) {
                            try {
                                UrlResource jarResource;
                                UrlResource urlResource = jarResource = "jar".equals(url.getProtocol()) ? new UrlResource(url) : new UrlResource("jar:" + url + "!/");
                                if (!jarResource.exists()) continue;
                                result.add(jarResource);
                            } catch (MalformedURLException ex) {
                                if (!logger.isDebugEnabled()) continue;
                                logger.debug("Cannot search for matching files underneath [" + url + "] because it cannot be converted to a valid 'jar:' URL: " + ex.getMessage());
                            }
                        }
                    } catch (Exception ex) {
                        if (!logger.isDebugEnabled()) break block10;
                        logger.debug("Cannot introspect jar files since ClassLoader [" + classLoader + "] does not support 'getURLs()': " + ex);
                    }
                }
            }
            if (classLoader == ClassLoader.getSystemClassLoader()) {
                this.addClassPathManifestEntries(result);
            }
            if (classLoader != null) {
                try {
                    this.addAllClassLoaderJarRoots(classLoader.getParent(), result);
                } catch (Exception ex) {
                    if (!logger.isDebugEnabled()) break block11;
                    logger.debug("Cannot introspect jar files in parent ClassLoader since [" + classLoader + "] does not support 'getParent()': " + ex);
                }
            }
        }
    }

    protected void addClassPathManifestEntries(Set<Resource> result) {
        block6: {
            try {
                String javaClassPathProperty = System.getProperty("java.class.path");
                for (String path : StringUtils.delimitedListToStringArray(javaClassPathProperty, System.getProperty("path.separator"))) {
                    try {
                        String filePath = new File(path).getAbsolutePath();
                        int prefixIndex = filePath.indexOf(58);
                        if (prefixIndex == 1) {
                            filePath = StringUtils.capitalize(filePath);
                        }
                        filePath = StringUtils.replace(filePath, "#", "%23");
                        UrlResource jarResource = new UrlResource("jar:file:" + filePath + "!/");
                        if (result.contains(jarResource) || this.hasDuplicate(filePath, result) || !jarResource.exists()) continue;
                        result.add(jarResource);
                    } catch (MalformedURLException ex) {
                        if (!logger.isDebugEnabled()) continue;
                        logger.debug("Cannot search for matching files underneath [" + path + "] because it cannot be converted to a valid 'jar:' URL: " + ex.getMessage());
                    }
                }
            } catch (Exception ex) {
                if (!logger.isDebugEnabled()) break block6;
                logger.debug("Failed to evaluate 'java.class.path' manifest entries: " + ex);
            }
        }
    }

    private boolean hasDuplicate(String filePath, Set<Resource> result) {
        if (result.isEmpty()) {
            return false;
        }
        String duplicatePath = filePath.startsWith("/") ? filePath.substring(1) : "/" + filePath;
        try {
            return result.contains(new UrlResource("jar:file:" + duplicatePath + "!/"));
        } catch (MalformedURLException ex) {
            return false;
        }
    }

    protected Resource[] findPathMatchingResources(String locationPattern) throws IOException {
        String rootDirPath = this.determineRootDir(locationPattern);
        String subPattern = locationPattern.substring(rootDirPath.length());
        Resource[] rootDirResources = this.getResources(rootDirPath);
        LinkedHashSet<Resource> result = new LinkedHashSet<Resource>(16);
        for (Resource rootDirResource : rootDirResources) {
            rootDirResource = this.resolveRootDirResource(rootDirResource);
            URL rootDirUrl = rootDirResource.getURL();
            if (equinoxResolveMethod != null && rootDirUrl.getProtocol().startsWith("bundle")) {
                URL resolvedUrl = (URL)ReflectionUtils.invokeMethod(equinoxResolveMethod, null, rootDirUrl);
                if (resolvedUrl != null) {
                    rootDirUrl = resolvedUrl;
                }
                rootDirResource = new UrlResource(rootDirUrl);
            }
            if (rootDirUrl.getProtocol().startsWith("vfs")) {
                result.addAll(VfsResourceMatchingDelegate.findMatchingResources(rootDirUrl, subPattern, this.getPathMatcher()));
                continue;
            }
            if (ResourceUtils.isJarURL(rootDirUrl) || this.isJarResource(rootDirResource)) {
                result.addAll(this.doFindPathMatchingJarResources(rootDirResource, rootDirUrl, subPattern));
                continue;
            }
            result.addAll(this.doFindPathMatchingFileResources(rootDirResource, subPattern));
        }
        if (logger.isTraceEnabled()) {
            logger.trace("Resolved location pattern [" + locationPattern + "] to resources " + result);
        }
        return result.toArray(new Resource[0]);
    }

    protected String determineRootDir(String location) {
        int prefixEnd = location.indexOf(58) + 1;
        int rootDirEnd = location.length();
        while (rootDirEnd > prefixEnd && this.getPathMatcher().isPattern(location.substring(prefixEnd, rootDirEnd))) {
            rootDirEnd = location.lastIndexOf(47, rootDirEnd - 2) + 1;
        }
        if (rootDirEnd == 0) {
            rootDirEnd = prefixEnd;
        }
        return location.substring(0, rootDirEnd);
    }

    protected Resource resolveRootDirResource(Resource original) throws IOException {
        return original;
    }

    protected boolean isJarResource(Resource resource) throws IOException {
        return false;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    protected Set<Resource> doFindPathMatchingJarResources(Resource rootDirResource, URL rootDirURL, String subPattern) throws IOException {
        boolean closeJarFile;
        String rootEntryPath;
        String jarFileUrl;
        JarFile jarFile;
        URLConnection con = rootDirURL.openConnection();
        if (con instanceof JarURLConnection) {
            JarURLConnection jarCon = (JarURLConnection)con;
            ResourceUtils.useCachesIfNecessary(jarCon);
            jarFile = jarCon.getJarFile();
            jarFileUrl = jarCon.getJarFileURL().toExternalForm();
            JarEntry jarEntry = jarCon.getJarEntry();
            rootEntryPath = jarEntry != null ? jarEntry.getName() : "";
            closeJarFile = !jarCon.getUseCaches();
        } else {
            String urlFile = rootDirURL.getFile();
            try {
                int separatorIndex = urlFile.indexOf("*/");
                if (separatorIndex == -1) {
                    separatorIndex = urlFile.indexOf("!/");
                }
                if (separatorIndex != -1) {
                    jarFileUrl = urlFile.substring(0, separatorIndex);
                    rootEntryPath = urlFile.substring(separatorIndex + 2);
                    jarFile = this.getJarFile(jarFileUrl);
                } else {
                    jarFile = new JarFile(urlFile);
                    jarFileUrl = urlFile;
                    rootEntryPath = "";
                }
                closeJarFile = true;
            } catch (ZipException ex) {
                if (logger.isDebugEnabled()) {
                    logger.debug("Skipping invalid jar classpath entry [" + urlFile + "]");
                }
                return Collections.emptySet();
            }
        }
        try {
            if (logger.isTraceEnabled()) {
                logger.trace("Looking for matching resources in jar file [" + jarFileUrl + "]");
            }
            if (StringUtils.hasLength(rootEntryPath) && !rootEntryPath.endsWith("/")) {
                rootEntryPath = rootEntryPath + "/";
            }
            LinkedHashSet<Resource> result = new LinkedHashSet<Resource>(8);
            Enumeration<JarEntry> entries = jarFile.entries();
            while (entries.hasMoreElements()) {
                JarEntry entry = entries.nextElement();
                String entryPath = entry.getName();
                if (!entryPath.startsWith(rootEntryPath)) continue;
                String relativePath = entryPath.substring(rootEntryPath.length());
                if (!this.getPathMatcher().match(subPattern, relativePath)) continue;
                result.add(rootDirResource.createRelative(relativePath));
            }
            LinkedHashSet<Resource> linkedHashSet = result;
            return linkedHashSet;
        } finally {
            if (closeJarFile) {
                jarFile.close();
            }
        }
    }

    protected JarFile getJarFile(String jarFileUrl) throws IOException {
        if (jarFileUrl.startsWith("file:")) {
            try {
                return new JarFile(ResourceUtils.toURI(jarFileUrl).getSchemeSpecificPart());
            } catch (URISyntaxException ex) {
                return new JarFile(jarFileUrl.substring("file:".length()));
            }
        }
        return new JarFile(jarFileUrl);
    }

    protected Set<Resource> doFindPathMatchingFileResources(Resource rootDirResource, String subPattern) throws IOException {
        File rootDir;
        try {
            rootDir = rootDirResource.getFile().getAbsoluteFile();
        } catch (FileNotFoundException ex) {
            if (logger.isDebugEnabled()) {
                logger.debug("Cannot search for matching files underneath " + rootDirResource + " in the file system: " + ex.getMessage());
            }
            return Collections.emptySet();
        } catch (Exception ex) {
            if (logger.isInfoEnabled()) {
                logger.info("Failed to resolve " + rootDirResource + " in the file system: " + ex);
            }
            return Collections.emptySet();
        }
        return this.doFindMatchingFileSystemResources(rootDir, subPattern);
    }

    protected Set<Resource> doFindMatchingFileSystemResources(File rootDir, String subPattern) throws IOException {
        if (logger.isTraceEnabled()) {
            logger.trace("Looking for matching resources in directory tree [" + rootDir.getPath() + "]");
        }
        Set<File> matchingFiles = this.retrieveMatchingFiles(rootDir, subPattern);
        LinkedHashSet<Resource> result = new LinkedHashSet<Resource>(matchingFiles.size());
        for (File file : matchingFiles) {
            result.add(new FileSystemResource(file));
        }
        return result;
    }

    protected Set<File> retrieveMatchingFiles(File rootDir, String pattern) throws IOException {
        if (!rootDir.exists()) {
            if (logger.isDebugEnabled()) {
                logger.debug("Skipping [" + rootDir.getAbsolutePath() + "] because it does not exist");
            }
            return Collections.emptySet();
        }
        if (!rootDir.isDirectory()) {
            if (logger.isInfoEnabled()) {
                logger.info("Skipping [" + rootDir.getAbsolutePath() + "] because it does not denote a directory");
            }
            return Collections.emptySet();
        }
        if (!rootDir.canRead()) {
            if (logger.isInfoEnabled()) {
                logger.info("Skipping search for matching files underneath directory [" + rootDir.getAbsolutePath() + "] because the application is not allowed to read the directory");
            }
            return Collections.emptySet();
        }
        String fullPattern = StringUtils.replace(rootDir.getAbsolutePath(), File.separator, "/");
        if (!pattern.startsWith("/")) {
            fullPattern = fullPattern + "/";
        }
        fullPattern = fullPattern + StringUtils.replace(pattern, File.separator, "/");
        LinkedHashSet<File> result = new LinkedHashSet<File>(8);
        this.doRetrieveMatchingFiles(fullPattern, rootDir, result);
        return result;
    }

    protected void doRetrieveMatchingFiles(String fullPattern, File dir, Set<File> result) throws IOException {
        if (logger.isTraceEnabled()) {
            logger.trace("Searching directory [" + dir.getAbsolutePath() + "] for files matching pattern [" + fullPattern + "]");
        }
        for (File content : this.listDirectory(dir)) {
            String currPath = StringUtils.replace(content.getAbsolutePath(), File.separator, "/");
            if (content.isDirectory() && this.getPathMatcher().matchStart(fullPattern, currPath + "/")) {
                if (!content.canRead()) {
                    if (logger.isDebugEnabled()) {
                        logger.debug("Skipping subdirectory [" + dir.getAbsolutePath() + "] because the application is not allowed to read the directory");
                    }
                } else {
                    this.doRetrieveMatchingFiles(fullPattern, content, result);
                }
            }
            if (!this.getPathMatcher().match(fullPattern, currPath)) continue;
            result.add(content);
        }
    }

    protected File[] listDirectory(File dir) {
        File[] files = dir.listFiles();
        if (files == null) {
            if (logger.isInfoEnabled()) {
                logger.info("Could not retrieve contents of directory [" + dir.getAbsolutePath() + "]");
            }
            return new File[0];
        }
        Arrays.sort(files, Comparator.comparing(File::getName));
        return files;
    }

    static {
        try {
            Class<?> fileLocatorClass = ClassUtils.forName("org.eclipse.core.runtime.FileLocator", PathMatchingResourcePatternResolver.class.getClassLoader());
            equinoxResolveMethod = fileLocatorClass.getMethod("resolve", URL.class);
            logger.trace("Found Equinox FileLocator for OSGi bundle URL resolution");
        } catch (Throwable ex) {
            equinoxResolveMethod = null;
        }
    }

    private static class PatternVirtualFileVisitor
    implements InvocationHandler {
        private final String subPattern;
        private final PathMatcher pathMatcher;
        private final String rootPath;
        private final Set<Resource> resources = new LinkedHashSet<Resource>();

        public PatternVirtualFileVisitor(String rootPath, String subPattern, PathMatcher pathMatcher) {
            this.subPattern = subPattern;
            this.pathMatcher = pathMatcher;
            this.rootPath = rootPath.isEmpty() || rootPath.endsWith("/") ? rootPath : rootPath + "/";
        }

        @Override
        @Nullable
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            String methodName = method.getName();
            if (Object.class == method.getDeclaringClass()) {
                if (methodName.equals("equals")) {
                    return proxy == args[0];
                }
                if (methodName.equals("hashCode")) {
                    return System.identityHashCode(proxy);
                }
            } else {
                if ("getAttributes".equals(methodName)) {
                    return this.getAttributes();
                }
                if ("visit".equals(methodName)) {
                    this.visit(args[0]);
                    return null;
                }
                if ("toString".equals(methodName)) {
                    return this.toString();
                }
            }
            throw new IllegalStateException("Unexpected method invocation: " + method);
        }

        public void visit(Object vfsResource) {
            if (this.pathMatcher.match(this.subPattern, VfsPatternUtils.getPath(vfsResource).substring(this.rootPath.length()))) {
                this.resources.add(new VfsResource(vfsResource));
            }
        }

        @Nullable
        public Object getAttributes() {
            return VfsPatternUtils.getVisitorAttributes();
        }

        public Set<Resource> getResources() {
            return this.resources;
        }

        public int size() {
            return this.resources.size();
        }

        public String toString() {
            return "sub-pattern: " + this.subPattern + ", resources: " + this.resources;
        }
    }

    private static class VfsResourceMatchingDelegate {
        private VfsResourceMatchingDelegate() {
        }

        public static Set<Resource> findMatchingResources(URL rootDirURL, String locationPattern, PathMatcher pathMatcher) throws IOException {
            Object root = VfsPatternUtils.findRoot(rootDirURL);
            PatternVirtualFileVisitor visitor = new PatternVirtualFileVisitor(VfsPatternUtils.getPath(root), locationPattern, pathMatcher);
            VfsPatternUtils.visit(root, visitor);
            return visitor.getResources();
        }
    }
}

