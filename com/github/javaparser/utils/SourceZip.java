/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.utils;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseResult;
import com.github.javaparser.ParseStart;
import com.github.javaparser.ParserConfiguration;
import com.github.javaparser.Providers;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.utils.Log;
import com.github.javaparser.utils.Pair;
import com.github.javaparser.utils.Utils;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class SourceZip {
    private final Path zipPath;
    private ParserConfiguration parserConfiguration;

    public SourceZip(Path zipPath) {
        this(zipPath, new ParserConfiguration());
    }

    public SourceZip(Path zipPath, ParserConfiguration configuration) {
        Utils.assertNotNull(zipPath);
        Utils.assertNotNull(configuration);
        this.zipPath = zipPath.normalize();
        this.parserConfiguration = configuration;
        Log.info("New source zip at \"%s\"", () -> this.zipPath);
    }

    public List<Pair<Path, ParseResult<CompilationUnit>>> parse() throws IOException {
        Log.info("Parsing zip at \"%s\"", () -> this.zipPath);
        ArrayList<Pair<Path, ParseResult<CompilationUnit>>> results = new ArrayList<Pair<Path, ParseResult<CompilationUnit>>>();
        this.parse((path, result) -> results.add(new Pair<Path, ParseResult>(path, result)));
        return results;
    }

    public SourceZip parse(Callback callback) throws IOException {
        Log.info("Parsing zip at \"%s\"", () -> this.zipPath);
        JavaParser javaParser = new JavaParser(this.parserConfiguration);
        try (ZipFile zipFile = new ZipFile(this.zipPath.toFile());){
            for (ZipEntry zipEntry : Collections.list(zipFile.entries())) {
                if (zipEntry.isDirectory() || !zipEntry.getName().endsWith(".java")) continue;
                Log.info("Parsing zip entry \"%s\"", () -> entry.getName());
                ParseResult<CompilationUnit> result = javaParser.parse(ParseStart.COMPILATION_UNIT, Providers.provider(zipFile.getInputStream(zipEntry)));
                callback.process(Paths.get(zipEntry.getName(), new String[0]), result);
            }
        }
        return this;
    }

    public Path getZipPath() {
        return this.zipPath;
    }

    public ParserConfiguration getParserConfiguration() {
        return this.parserConfiguration;
    }

    public SourceZip setParserConfiguration(ParserConfiguration parserConfiguration) {
        Utils.assertNotNull(parserConfiguration);
        this.parserConfiguration = parserConfiguration;
        return this;
    }

    @FunctionalInterface
    public static interface Callback {
        public void process(Path var1, ParseResult<CompilationUnit> var2);
    }
}

