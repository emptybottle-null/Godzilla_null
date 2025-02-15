/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.utils;

import com.github.javaparser.ParserConfiguration;
import com.github.javaparser.utils.CollectionStrategy;
import com.github.javaparser.utils.Log;
import com.github.javaparser.utils.ProjectRoot;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

public class ParserCollectionStrategy
implements CollectionStrategy {
    private final ParserConfiguration parserConfiguration;

    public ParserCollectionStrategy() {
        this(new ParserConfiguration());
    }

    public ParserCollectionStrategy(ParserConfiguration parserConfiguration) {
        this.parserConfiguration = parserConfiguration;
    }

    @Override
    public ParserConfiguration getParserConfiguration() {
        return this.parserConfiguration;
    }

    @Override
    public ProjectRoot collect(Path path) {
        final ProjectRoot projectRoot = new ProjectRoot(path, this.parserConfiguration);
        try {
            Files.walkFileTree(path, (FileVisitor<? super Path>)new SimpleFileVisitor<Path>(){
                Path current_root;
                PathMatcher javaMatcher;
                {
                    this.javaMatcher = ParserCollectionStrategy.this.getPathMatcher("glob:**.java");
                }

                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    if (this.javaMatcher.matches(file)) {
                        this.current_root = ParserCollectionStrategy.this.getRoot(file).orElse(null);
                        if (this.current_root != null) {
                            return FileVisitResult.SKIP_SIBLINGS;
                        }
                    }
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                    if (Files.isHidden(dir) || this.current_root != null && dir.startsWith(this.current_root)) {
                        return FileVisitResult.SKIP_SUBTREE;
                    }
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult postVisitDirectory(Path dir, IOException e) throws IOException {
                    if (this.current_root != null && Files.isSameFile(dir, this.current_root)) {
                        projectRoot.addSourceRoot(dir);
                        this.current_root = null;
                    }
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            Log.error(e, "Unable to walk %s", () -> path);
        }
        return projectRoot;
    }
}

