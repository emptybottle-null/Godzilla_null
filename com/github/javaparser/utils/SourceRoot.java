/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.utils;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseProblemException;
import com.github.javaparser.ParseResult;
import com.github.javaparser.ParseStart;
import com.github.javaparser.ParserConfiguration;
import com.github.javaparser.Problem;
import com.github.javaparser.Providers;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.printer.DefaultPrettyPrinter;
import com.github.javaparser.utils.CodeGenerationUtils;
import com.github.javaparser.utils.Log;
import com.github.javaparser.utils.Utils;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class SourceRoot {
    private final Path root;
    private final Map<Path, ParseResult<CompilationUnit>> cache = new ConcurrentHashMap<Path, ParseResult<CompilationUnit>>();
    private ParserConfiguration parserConfiguration = new ParserConfiguration();
    private Function<CompilationUnit, String> printer = new DefaultPrettyPrinter()::print;
    private static final Pattern JAVA_IDENTIFIER = Pattern.compile("\\p{javaJavaIdentifierStart}\\p{javaJavaIdentifierPart}*");

    public SourceRoot(Path root) {
        Utils.assertNotNull(root);
        if (!Files.isDirectory(root, new LinkOption[0])) {
            throw new IllegalArgumentException("Only directories are allowed as root path: " + root);
        }
        this.root = root.normalize();
        Log.info("New source root at \"%s\"", () -> this.root);
    }

    public SourceRoot(Path root, ParserConfiguration parserConfiguration) {
        this(root);
        this.setParserConfiguration(parserConfiguration);
    }

    public ParseResult<CompilationUnit> tryToParse(String startPackage, String filename, ParserConfiguration configuration) throws IOException {
        Utils.assertNotNull(startPackage);
        Utils.assertNotNull(filename);
        Path relativePath = CodeGenerationUtils.fileInPackageRelativePath(startPackage, filename);
        if (this.cache.containsKey(relativePath)) {
            Log.trace("Retrieving cached %s", () -> relativePath);
            return this.cache.get(relativePath);
        }
        Path path = this.root.resolve(relativePath);
        Log.trace("Parsing %s", () -> path);
        ParseResult<CompilationUnit> result = new JavaParser(configuration).parse(ParseStart.COMPILATION_UNIT, Providers.provider(path, configuration.getCharacterEncoding()));
        result.getResult().ifPresent(cu -> cu.setStorage(path, configuration.getCharacterEncoding()));
        this.cache.put(relativePath, result);
        return result;
    }

    public ParseResult<CompilationUnit> tryToParse(String startPackage, String filename) throws IOException {
        return this.tryToParse(startPackage, filename, this.parserConfiguration);
    }

    public List<ParseResult<CompilationUnit>> tryToParse(String startPackage) throws IOException {
        Utils.assertNotNull(startPackage);
        this.logPackage(startPackage);
        Path path = CodeGenerationUtils.packageAbsolutePath(this.root, startPackage);
        Files.walkFileTree(path, (FileVisitor<? super Path>)new SimpleFileVisitor<Path>(){

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                if (!attrs.isDirectory() && file.toString().endsWith(".java")) {
                    Path relative = SourceRoot.this.root.relativize(file.getParent());
                    SourceRoot.this.tryToParse(relative.toString(), file.getFileName().toString());
                }
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                return SourceRoot.isSensibleDirectoryToEnter(dir) ? FileVisitResult.CONTINUE : FileVisitResult.SKIP_SUBTREE;
            }
        });
        return this.getCache();
    }

    private static boolean isSensibleDirectoryToEnter(Path dir) throws IOException {
        String dirToEnter = dir.getFileName().toString();
        boolean directoryIsAValidJavaIdentifier = JAVA_IDENTIFIER.matcher(dirToEnter).matches();
        if (Files.isHidden(dir) || !directoryIsAValidJavaIdentifier) {
            Log.trace("Not processing directory \"%s\"", () -> dirToEnter);
            return false;
        }
        return true;
    }

    public List<ParseResult<CompilationUnit>> tryToParse() throws IOException {
        return this.tryToParse("");
    }

    public List<ParseResult<CompilationUnit>> tryToParseParallelized(String startPackage) {
        Utils.assertNotNull(startPackage);
        this.logPackage(startPackage);
        Path path = CodeGenerationUtils.packageAbsolutePath(this.root, startPackage);
        ParallelParse parse = new ParallelParse(path, (file, attrs) -> {
            if (!attrs.isDirectory() && file.toString().endsWith(".java")) {
                Path relative = this.root.relativize(file.getParent());
                try {
                    this.tryToParse(relative.toString(), file.getFileName().toString(), this.parserConfiguration);
                } catch (IOException e) {
                    Log.error(e);
                }
            }
            return FileVisitResult.CONTINUE;
        });
        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(parse);
        return this.getCache();
    }

    public List<ParseResult<CompilationUnit>> tryToParseParallelized() {
        return this.tryToParseParallelized("");
    }

    public CompilationUnit parse(String startPackage, String filename) {
        Utils.assertNotNull(startPackage);
        Utils.assertNotNull(filename);
        try {
            ParseResult<CompilationUnit> result = this.tryToParse(startPackage, filename);
            if (result.isSuccessful()) {
                return result.getResult().get();
            }
            throw new ParseProblemException(result.getProblems());
        } catch (IOException e) {
            throw new ParseProblemException(e);
        }
    }

    private FileVisitResult callback(Path absolutePath, ParserConfiguration configuration, Callback callback) throws IOException {
        Path localPath = this.root.relativize(absolutePath);
        Log.trace("Parsing %s", () -> localPath);
        ParseResult<CompilationUnit> result = new JavaParser(configuration).parse(ParseStart.COMPILATION_UNIT, Providers.provider(absolutePath, configuration.getCharacterEncoding()));
        result.getResult().ifPresent(cu -> cu.setStorage(absolutePath, configuration.getCharacterEncoding()));
        switch (callback.process(localPath, absolutePath, result)) {
            case SAVE: {
                result.getResult().ifPresent(cu -> this.save((CompilationUnit)cu, absolutePath));
            }
            case DONT_SAVE: {
                return FileVisitResult.CONTINUE;
            }
            case TERMINATE: {
                return FileVisitResult.TERMINATE;
            }
        }
        throw new AssertionError((Object)"Return an enum defined in SourceRoot.Callback.Result");
    }

    public SourceRoot parse(String startPackage, String filename, ParserConfiguration configuration, Callback callback) throws IOException {
        Utils.assertNotNull(startPackage);
        Utils.assertNotNull(filename);
        Utils.assertNotNull(configuration);
        Utils.assertNotNull(callback);
        this.callback(CodeGenerationUtils.fileInPackageAbsolutePath(this.root, startPackage, filename), configuration, callback);
        return this;
    }

    public SourceRoot parse(String startPackage, String filename, Callback callback) throws IOException {
        this.parse(startPackage, filename, this.parserConfiguration, callback);
        return this;
    }

    public SourceRoot parse(String startPackage, final ParserConfiguration configuration, final Callback callback) throws IOException {
        Utils.assertNotNull(startPackage);
        Utils.assertNotNull(configuration);
        Utils.assertNotNull(callback);
        this.logPackage(startPackage);
        Path path = CodeGenerationUtils.packageAbsolutePath(this.root, startPackage);
        if (Files.exists(path, new LinkOption[0])) {
            Files.walkFileTree(path, (FileVisitor<? super Path>)new SimpleFileVisitor<Path>(){

                @Override
                public FileVisitResult visitFile(Path absolutePath, BasicFileAttributes attrs) throws IOException {
                    if (!attrs.isDirectory() && absolutePath.toString().endsWith(".java")) {
                        return SourceRoot.this.callback(absolutePath, configuration, callback);
                    }
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                    return SourceRoot.isSensibleDirectoryToEnter(dir) ? FileVisitResult.CONTINUE : FileVisitResult.SKIP_SUBTREE;
                }
            });
        }
        return this;
    }

    public SourceRoot parse(String startPackage, Callback callback) throws IOException {
        this.parse(startPackage, this.parserConfiguration, callback);
        return this;
    }

    private void logPackage(String startPackage) {
        if (startPackage.isEmpty()) {
            return;
        }
        Log.info("Parsing package \"%s\"", () -> startPackage);
    }

    public SourceRoot parseParallelized(String startPackage, ParserConfiguration configuration, Callback callback) {
        Utils.assertNotNull(startPackage);
        Utils.assertNotNull(configuration);
        Utils.assertNotNull(callback);
        this.logPackage(startPackage);
        Path path = CodeGenerationUtils.packageAbsolutePath(this.root, startPackage);
        if (Files.exists(path, new LinkOption[0])) {
            ParallelParse parse = new ParallelParse(path, (absolutePath, attrs) -> {
                if (!attrs.isDirectory() && absolutePath.toString().endsWith(".java")) {
                    try {
                        return this.callback(absolutePath, configuration, callback);
                    } catch (IOException e) {
                        Log.error(e);
                    }
                }
                return FileVisitResult.CONTINUE;
            });
            ForkJoinPool pool = new ForkJoinPool();
            pool.invoke(parse);
        }
        return this;
    }

    public SourceRoot parseParallelized(String startPackage, Callback callback) throws IOException {
        return this.parseParallelized(startPackage, this.parserConfiguration, callback);
    }

    public SourceRoot parseParallelized(Callback callback) throws IOException {
        return this.parseParallelized("", this.parserConfiguration, callback);
    }

    public SourceRoot add(String startPackage, String filename, CompilationUnit compilationUnit) {
        Utils.assertNotNull(startPackage);
        Utils.assertNotNull(filename);
        Utils.assertNotNull(compilationUnit);
        Log.trace("Adding new file %s.%s", () -> startPackage, () -> filename);
        Path path = CodeGenerationUtils.fileInPackageRelativePath(startPackage, filename);
        ParseResult<CompilationUnit> parseResult = new ParseResult<CompilationUnit>(compilationUnit, new ArrayList<Problem>(), null);
        this.cache.put(path, parseResult);
        return this;
    }

    public SourceRoot add(CompilationUnit compilationUnit) {
        Utils.assertNotNull(compilationUnit);
        if (!compilationUnit.getStorage().isPresent()) {
            throw new AssertionError((Object)"Files added with this method should have their path set.");
        }
        Path path = compilationUnit.getStorage().get().getPath();
        Log.trace("Adding new file %s", () -> path);
        ParseResult<CompilationUnit> parseResult = new ParseResult<CompilationUnit>(compilationUnit, new ArrayList<Problem>(), null);
        this.cache.put(path, parseResult);
        return this;
    }

    private SourceRoot save(CompilationUnit cu, Path path) {
        return this.save(cu, path, this.parserConfiguration.getCharacterEncoding());
    }

    private SourceRoot save(CompilationUnit cu, Path path, Charset encoding) {
        Utils.assertNotNull(cu);
        Utils.assertNotNull(path);
        cu.setStorage(path, encoding);
        cu.getStorage().get().save(this.printer);
        return this;
    }

    public SourceRoot saveAll(Path root, Charset encoding) {
        Utils.assertNotNull(root);
        Supplier[] supplierArray = new Supplier[2];
        supplierArray[0] = this.cache::size;
        supplierArray[1] = () -> root;
        Log.info("Saving all files (%s) to %s", supplierArray);
        for (Map.Entry<Path, ParseResult<CompilationUnit>> cu : this.cache.entrySet()) {
            Path path = root.resolve(cu.getKey());
            if (!cu.getValue().getResult().isPresent()) continue;
            Log.trace("Saving %s", () -> path);
            this.save(cu.getValue().getResult().get(), path, encoding);
        }
        return this;
    }

    public SourceRoot saveAll(Path root) {
        return this.saveAll(root, this.parserConfiguration.getCharacterEncoding());
    }

    public SourceRoot saveAll() {
        return this.saveAll(this.root);
    }

    public SourceRoot saveAll(Charset encoding) {
        return this.saveAll(this.root, encoding);
    }

    public List<ParseResult<CompilationUnit>> getCache() {
        return new ArrayList<ParseResult<CompilationUnit>>(this.cache.values());
    }

    public List<CompilationUnit> getCompilationUnits() {
        return this.cache.values().stream().filter(ParseResult::isSuccessful).map(p -> (CompilationUnit)p.getResult().get()).collect(Collectors.toList());
    }

    public Path getRoot() {
        return this.root;
    }

    public ParserConfiguration getParserConfiguration() {
        return this.parserConfiguration;
    }

    public SourceRoot setParserConfiguration(ParserConfiguration parserConfiguration) {
        Utils.assertNotNull(parserConfiguration);
        this.parserConfiguration = parserConfiguration;
        return this;
    }

    public SourceRoot setPrinter(Function<CompilationUnit, String> printer) {
        Utils.assertNotNull(printer);
        this.printer = printer;
        return this;
    }

    public Function<CompilationUnit, String> getPrinter() {
        return this.printer;
    }

    public String toString() {
        return "SourceRoot at " + this.root;
    }

    private static class ParallelParse
    extends RecursiveAction {
        private static final long serialVersionUID = 1L;
        private final Path path;
        private final VisitFileCallback callback;

        ParallelParse(Path path, VisitFileCallback callback) {
            this.path = path;
            this.callback = callback;
        }

        @Override
        protected void compute() {
            final ArrayList walks = new ArrayList();
            try {
                Files.walkFileTree(this.path, (FileVisitor<? super Path>)new SimpleFileVisitor<Path>(){

                    @Override
                    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                        if (!SourceRoot.isSensibleDirectoryToEnter(dir)) {
                            return FileVisitResult.SKIP_SUBTREE;
                        }
                        if (!dir.equals(path)) {
                            ParallelParse w = new ParallelParse(dir, callback);
                            w.fork();
                            walks.add(w);
                            return FileVisitResult.SKIP_SUBTREE;
                        }
                        return FileVisitResult.CONTINUE;
                    }

                    @Override
                    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
                        return callback.process(file, attrs);
                    }
                });
            } catch (IOException e) {
                Log.error(e);
            }
            for (ParallelParse w : walks) {
                w.join();
            }
        }

        static interface VisitFileCallback {
            public FileVisitResult process(Path var1, BasicFileAttributes var2);
        }
    }

    @FunctionalInterface
    public static interface Callback {
        public Result process(Path var1, Path var2, ParseResult<CompilationUnit> var3);

        public static enum Result {
            SAVE,
            DONT_SAVE,
            TERMINATE;

        }
    }
}

