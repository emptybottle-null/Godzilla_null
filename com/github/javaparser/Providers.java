/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser;

import com.github.javaparser.Provider;
import com.github.javaparser.StreamProvider;
import com.github.javaparser.StringProvider;
import com.github.javaparser.utils.Utils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;

public final class Providers {
    public static final Charset UTF8 = Charset.forName("utf-8");

    private Providers() {
    }

    public static Provider provider(Reader reader) {
        return new StreamProvider(Utils.assertNotNull(reader));
    }

    public static Provider provider(InputStream input, Charset encoding) {
        Utils.assertNotNull(input);
        Utils.assertNotNull(encoding);
        try {
            return new StreamProvider(input, encoding.name());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Provider provider(InputStream input) {
        return Providers.provider(input, UTF8);
    }

    public static Provider provider(File file, Charset encoding) throws FileNotFoundException {
        return Providers.provider(new FileInputStream(Utils.assertNotNull(file)), Utils.assertNotNull(encoding));
    }

    public static Provider provider(File file) throws FileNotFoundException {
        return Providers.provider(Utils.assertNotNull(file), UTF8);
    }

    public static Provider provider(Path path, Charset encoding) throws IOException {
        return Providers.provider(Files.newInputStream(Utils.assertNotNull(path), new OpenOption[0]), Utils.assertNotNull(encoding));
    }

    public static Provider provider(Path path) throws IOException {
        return Providers.provider(Utils.assertNotNull(path), UTF8);
    }

    public static Provider provider(String source) {
        return new StringProvider(Utils.assertNotNull(source));
    }

    public static Provider resourceProvider(ClassLoader classLoader, String pathToResource, Charset encoding) throws IOException {
        InputStream resourceAsStream = classLoader.getResourceAsStream(pathToResource);
        if (resourceAsStream == null) {
            throw new IOException("Cannot find " + pathToResource);
        }
        return Providers.provider(resourceAsStream, encoding);
    }

    public static Provider resourceProvider(String pathToResource, Charset encoding) throws IOException {
        ClassLoader classLoader = Provider.class.getClassLoader();
        return Providers.resourceProvider(classLoader, pathToResource, encoding);
    }

    public static Provider resourceProvider(String pathToResource) throws IOException {
        return Providers.resourceProvider(pathToResource, UTF8);
    }

    public static interface PreProcessor {
        public Provider process(Provider var1);
    }
}

