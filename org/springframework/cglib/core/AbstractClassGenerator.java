/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.cglib.core;

import java.lang.ref.WeakReference;
import java.security.ProtectionDomain;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import org.springframework.cglib.core.ClassGenerator;
import org.springframework.cglib.core.CodeGenerationException;
import org.springframework.cglib.core.DefaultGeneratorStrategy;
import org.springframework.cglib.core.DefaultNamingPolicy;
import org.springframework.cglib.core.GeneratorStrategy;
import org.springframework.cglib.core.NamingPolicy;
import org.springframework.cglib.core.Predicate;
import org.springframework.cglib.core.internal.Function;
import org.springframework.cglib.core.internal.LoadingCache;

public abstract class AbstractClassGenerator<T>
implements ClassGenerator {
    private static final ThreadLocal CURRENT = new ThreadLocal();
    private static volatile Map<ClassLoader, ClassLoaderData> CACHE = new WeakHashMap<ClassLoader, ClassLoaderData>();
    private static final boolean DEFAULT_USE_CACHE = Boolean.parseBoolean(System.getProperty("cglib.useCache", "true"));
    private GeneratorStrategy strategy = DefaultGeneratorStrategy.INSTANCE;
    private NamingPolicy namingPolicy = DefaultNamingPolicy.INSTANCE;
    private Source source;
    private ClassLoader classLoader;
    private Class contextClass;
    private String namePrefix;
    private Object key;
    private boolean useCache = DEFAULT_USE_CACHE;
    private String className;
    private boolean attemptLoad;

    protected T wrapCachedClass(Class klass) {
        return (T)new WeakReference<Class>(klass);
    }

    protected Object unwrapCachedValue(T cached) {
        return ((WeakReference)cached).get();
    }

    protected AbstractClassGenerator(Source source) {
        this.source = source;
    }

    protected void setNamePrefix(String namePrefix) {
        this.namePrefix = namePrefix;
    }

    protected final String getClassName() {
        return this.className;
    }

    private void setClassName(String className) {
        this.className = className;
    }

    private String generateClassName(Predicate nameTestPredicate) {
        return this.namingPolicy.getClassName(this.namePrefix, this.source.name, this.key, nameTestPredicate);
    }

    public void setClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    public void setContextClass(Class contextClass) {
        this.contextClass = contextClass;
    }

    public void setNamingPolicy(NamingPolicy namingPolicy) {
        if (namingPolicy == null) {
            namingPolicy = DefaultNamingPolicy.INSTANCE;
        }
        this.namingPolicy = namingPolicy;
    }

    public NamingPolicy getNamingPolicy() {
        return this.namingPolicy;
    }

    public void setUseCache(boolean useCache) {
        this.useCache = useCache;
    }

    public boolean getUseCache() {
        return this.useCache;
    }

    public void setAttemptLoad(boolean attemptLoad) {
        this.attemptLoad = attemptLoad;
    }

    public boolean getAttemptLoad() {
        return this.attemptLoad;
    }

    public void setStrategy(GeneratorStrategy strategy) {
        if (strategy == null) {
            strategy = DefaultGeneratorStrategy.INSTANCE;
        }
        this.strategy = strategy;
    }

    public GeneratorStrategy getStrategy() {
        return this.strategy;
    }

    public static AbstractClassGenerator getCurrent() {
        return (AbstractClassGenerator)CURRENT.get();
    }

    public ClassLoader getClassLoader() {
        ClassLoader t = this.classLoader;
        if (t == null) {
            t = this.getDefaultClassLoader();
        }
        if (t == null) {
            t = this.getClass().getClassLoader();
        }
        if (t == null) {
            t = Thread.currentThread().getContextClassLoader();
        }
        if (t == null) {
            throw new IllegalStateException("Cannot determine classloader");
        }
        return t;
    }

    protected abstract ClassLoader getDefaultClassLoader();

    protected ProtectionDomain getProtectionDomain() {
        return null;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Converted monitor instructions to comments
     * Lifted jumps to return sites
     */
    protected Object create(Object key) {
        try {
            ClassLoader loader = this.getClassLoader();
            Map<ClassLoader, ClassLoaderData> cache = CACHE;
            ClassLoaderData data = cache.get(loader);
            if (data == null) {
                Class<AbstractClassGenerator> clazz = AbstractClassGenerator.class;
                // MONITORENTER : org.springframework.cglib.core.AbstractClassGenerator.class
                cache = CACHE;
                data = cache.get(loader);
                if (data == null) {
                    WeakHashMap<ClassLoader, ClassLoaderData> newCache = new WeakHashMap<ClassLoader, ClassLoaderData>(cache);
                    data = new ClassLoaderData(loader);
                    newCache.put(loader, data);
                    CACHE = newCache;
                }
                // MONITOREXIT : clazz
            }
            this.key = key;
            Object obj = data.get(this, this.getUseCache());
            if (!(obj instanceof Class)) return this.nextInstance(obj);
            return this.firstInstance((Class)obj);
        } catch (Error | RuntimeException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new CodeGenerationException(ex);
        }
    }

    /*
     * Exception decompiling
     */
    protected Class generate(ClassLoaderData data) {
        /*
         * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
         * 
         * org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [9[CATCHBLOCK]], but top level block is 3[TRYBLOCK]
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.processEndingBlocks(Op04StructuredStatement.java:435)
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.buildNestedBlocks(Op04StructuredStatement.java:484)
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op03SimpleStatement.createInitialStructuredBlock(Op03SimpleStatement.java:736)
         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:850)
         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisOrWrapFail(CodeAnalyser.java:278)
         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysis(CodeAnalyser.java:201)
         *     at org.benf.cfr.reader.entities.attributes.AttributeCode.analyse(AttributeCode.java:94)
         *     at org.benf.cfr.reader.entities.Method.analyse(Method.java:538)
         *     at org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:1055)
         *     at org.benf.cfr.reader.entities.ClassFile.analyseTop(ClassFile.java:942)
         *     at org.benf.cfr.reader.Driver.doJarVersionTypes(Driver.java:257)
         *     at org.benf.cfr.reader.Driver.doJar(Driver.java:139)
         *     at org.benf.cfr.reader.CfrDriverImpl.analyse(CfrDriverImpl.java:76)
         *     at org.benf.cfr.reader.Main.main(Main.java:54)
         *     at async.DecompilerRunnable.cfrDecompilation(DecompilerRunnable.java:348)
         *     at async.DecompilerRunnable.call(DecompilerRunnable.java:309)
         *     at async.DecompilerRunnable.call(DecompilerRunnable.java:31)
         *     at java.util.concurrent.FutureTask.run(FutureTask.java:266)
         *     at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1149)
         *     at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)
         *     at java.lang.Thread.run(Thread.java:750)
         */
        throw new IllegalStateException("Decompilation failed");
    }

    protected abstract Object firstInstance(Class var1) throws Exception;

    protected abstract Object nextInstance(Object var1) throws Exception;

    protected static class Source {
        String name;

        public Source(String name) {
            this.name = name;
        }
    }

    protected static class ClassLoaderData {
        private final Set<String> reservedClassNames = new HashSet<String>();
        private final LoadingCache<AbstractClassGenerator, Object, Object> generatedClasses;
        private final WeakReference<ClassLoader> classLoader;
        private final Predicate uniqueNamePredicate = new Predicate(){

            @Override
            public boolean evaluate(Object name) {
                return reservedClassNames.contains(name);
            }
        };
        private static final Function<AbstractClassGenerator, Object> GET_KEY = new Function<AbstractClassGenerator, Object>(){

            @Override
            public Object apply(AbstractClassGenerator gen) {
                return gen.key;
            }
        };

        public ClassLoaderData(ClassLoader classLoader) {
            if (classLoader == null) {
                throw new IllegalArgumentException("classLoader == null is not yet supported");
            }
            this.classLoader = new WeakReference<ClassLoader>(classLoader);
            Function<AbstractClassGenerator, Object> load = new Function<AbstractClassGenerator, Object>(){

                @Override
                public Object apply(AbstractClassGenerator gen) {
                    Class klass = gen.generate(this);
                    return gen.wrapCachedClass(klass);
                }
            };
            this.generatedClasses = new LoadingCache<AbstractClassGenerator, Object, Object>(GET_KEY, load);
        }

        public ClassLoader getClassLoader() {
            return (ClassLoader)this.classLoader.get();
        }

        public void reserveName(String name) {
            this.reservedClassNames.add(name);
        }

        public Predicate getUniqueNamePredicate() {
            return this.uniqueNamePredicate;
        }

        public Object get(AbstractClassGenerator gen, boolean useCache) {
            if (!useCache) {
                return gen.generate(this);
            }
            Object cachedValue = this.generatedClasses.get(gen);
            return gen.unwrapCachedValue(cachedValue);
        }
    }
}

