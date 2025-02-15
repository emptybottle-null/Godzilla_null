/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.utils;

import com.github.javaparser.utils.Utils;
import java.io.File;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

public final class CodeGenerationUtils {
    private CodeGenerationUtils() {
    }

    public static String getterName(Class<?> type, String name) {
        if (name.startsWith("is") && Boolean.TYPE.equals(type)) {
            return name;
        }
        if (Boolean.TYPE.equals(type)) {
            return "is" + Utils.capitalize(name);
        }
        return "get" + Utils.capitalize(name);
    }

    public static String getterToPropertyName(String getterName) {
        if (getterName.startsWith("is")) {
            return Utils.decapitalize(getterName.substring("is".length()));
        }
        if (getterName.startsWith("get")) {
            return Utils.decapitalize(getterName.substring("get".length()));
        }
        if (getterName.startsWith("has")) {
            return Utils.decapitalize(getterName.substring("has".length()));
        }
        throw new IllegalArgumentException("Unexpected getterName '" + getterName + "'");
    }

    public static String setterName(String fieldName) {
        if (fieldName.startsWith("is")) {
            return "set" + fieldName.substring(2);
        }
        return "set" + Utils.capitalize(fieldName);
    }

    public static String optionalOf(String text, boolean isOptional) {
        if (isOptional) {
            return CodeGenerationUtils.f("Optional.of(%s)", text);
        }
        return "Optional.empty()";
    }

    public static String f(String format, Object ... params) {
        return String.format(format, params);
    }

    public static Path fileInPackageAbsolutePath(String root, String pkg, String file) {
        pkg = CodeGenerationUtils.packageToPath(pkg);
        return Paths.get(root, pkg, file).normalize();
    }

    public static Path fileInPackageAbsolutePath(Path root, String pkg, String file) {
        return CodeGenerationUtils.fileInPackageAbsolutePath(root.toString(), pkg, file);
    }

    public static Path fileInPackageRelativePath(String pkg, String file) {
        pkg = CodeGenerationUtils.packageToPath(pkg);
        return Paths.get(pkg, file).normalize();
    }

    public static String packageToPath(String pkg) {
        return pkg.replace('.', File.separatorChar);
    }

    public static Path packageAbsolutePath(String root, String pkg) {
        pkg = CodeGenerationUtils.packageToPath(pkg);
        return Paths.get(root, pkg).normalize();
    }

    public static Path packageAbsolutePath(Path root, String pkg) {
        return CodeGenerationUtils.packageAbsolutePath(root.toString(), pkg);
    }

    public static Path classLoaderRoot(Class<?> c) {
        try {
            return Paths.get(c.getProtectionDomain().getCodeSource().getLocation().toURI());
        } catch (URISyntaxException e) {
            throw new AssertionError("Bug in JavaParser, please report.", e);
        }
    }

    public static Path mavenModuleRoot(Class<?> c) {
        return CodeGenerationUtils.classLoaderRoot(c).resolve(Paths.get("..", "..")).normalize();
    }

    public static Path subtractPaths(Path full, Path difference) {
        while (difference != null) {
            if (difference.getFileName().equals(full.getFileName())) {
                difference = difference.getParent();
                full = full.getParent();
                continue;
            }
            throw new RuntimeException(CodeGenerationUtils.f("'%s' could not be subtracted from '%s'", difference, full));
        }
        return full;
    }
}

