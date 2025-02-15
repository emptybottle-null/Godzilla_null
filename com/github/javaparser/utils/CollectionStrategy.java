/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.utils;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseProblemException;
import com.github.javaparser.ParseResult;
import com.github.javaparser.ParserConfiguration;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.utils.Log;
import com.github.javaparser.utils.ProjectRoot;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.util.Optional;
import java.util.function.Supplier;

public interface CollectionStrategy {
    public ParserConfiguration getParserConfiguration();

    public ProjectRoot collect(Path var1);

    default public Optional<Path> getRoot(Path file) {
        try {
            JavaParser javaParser = new JavaParser(this.getParserConfiguration());
            ParseResult<CompilationUnit> parseResult = javaParser.parse(file);
            if (parseResult.isSuccessful()) {
                if (parseResult.getProblems().isEmpty()) {
                    if (parseResult.getResult().isPresent()) {
                        CompilationUnit compilationUnit = parseResult.getResult().get();
                        Optional<CompilationUnit.Storage> storage = compilationUnit.getStorage();
                        if (storage.isPresent()) {
                            Optional<Path> sourceRootPath = storage.map(CompilationUnit.Storage::getSourceRoot);
                            return sourceRootPath;
                        }
                        Log.info("Storage information not present -- an issue with providing a string rather than file reference?", new Supplier[0]);
                    } else {
                        Log.info("Parse result not present", new Supplier[0]);
                    }
                } else {
                    Log.info("There were (%d) problems parsing file: %s", () -> parseResult.getProblems().size(), () -> parseResult.getProblems());
                }
            } else {
                Log.info("Parsing was not successful.", new Supplier[0]);
                Log.info("There were (%d) problems parsing file: %s", () -> parseResult.getProblems().size(), () -> parseResult.getProblems());
            }
        } catch (ParseProblemException e) {
            Log.info("Problem parsing file %s", () -> file);
        } catch (RuntimeException e) {
            Log.info("Could not parse file %s", () -> file);
        } catch (IOException e) {
            Log.info("Could not read file %s", () -> file);
        }
        return Optional.empty();
    }

    default public PathMatcher getPathMatcher(String pattern) {
        return FileSystems.getDefault().getPathMatcher(pattern);
    }
}

