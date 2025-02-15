/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.FileVisitOption;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileAttribute;
import java.util.EnumSet;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

public abstract class FileSystemUtils {
    public static boolean deleteRecursively(@Nullable File root) {
        if (root == null) {
            return false;
        }
        try {
            return FileSystemUtils.deleteRecursively(root.toPath());
        } catch (IOException ex) {
            return false;
        }
    }

    public static boolean deleteRecursively(@Nullable Path root) throws IOException {
        if (root == null) {
            return false;
        }
        if (!Files.exists(root, new LinkOption[0])) {
            return false;
        }
        Files.walkFileTree(root, (FileVisitor<? super Path>)new SimpleFileVisitor<Path>(){

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                Files.delete(file);
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                Files.delete(dir);
                return FileVisitResult.CONTINUE;
            }
        });
        return true;
    }

    public static void copyRecursively(File src, File dest) throws IOException {
        Assert.notNull((Object)src, "Source File must not be null");
        Assert.notNull((Object)dest, "Destination File must not be null");
        FileSystemUtils.copyRecursively(src.toPath(), dest.toPath());
    }

    public static void copyRecursively(final Path src, final Path dest) throws IOException {
        Assert.notNull((Object)src, "Source Path must not be null");
        Assert.notNull((Object)dest, "Destination Path must not be null");
        BasicFileAttributes srcAttr = Files.readAttributes(src, BasicFileAttributes.class, new LinkOption[0]);
        if (srcAttr.isDirectory()) {
            Files.walkFileTree(src, EnumSet.of(FileVisitOption.FOLLOW_LINKS), Integer.MAX_VALUE, (FileVisitor<? super Path>)new SimpleFileVisitor<Path>(){

                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                    Files.createDirectories(dest.resolve(src.relativize(dir)), new FileAttribute[0]);
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    Files.copy(file, dest.resolve(src.relativize(file)), StandardCopyOption.REPLACE_EXISTING);
                    return FileVisitResult.CONTINUE;
                }
            });
        } else if (srcAttr.isRegularFile()) {
            Files.copy(src, dest, new CopyOption[0]);
        } else {
            throw new IllegalArgumentException("Source File must denote a directory or file");
        }
    }
}

