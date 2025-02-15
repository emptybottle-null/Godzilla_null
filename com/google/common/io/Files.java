/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.io;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Joiner;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.common.collect.TreeTraverser;
import com.google.common.graph.SuccessorsFunction;
import com.google.common.graph.Traverser;
import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.io.ByteProcessor;
import com.google.common.io.ByteSink;
import com.google.common.io.ByteSource;
import com.google.common.io.ByteStreams;
import com.google.common.io.CharSink;
import com.google.common.io.CharSource;
import com.google.common.io.Closer;
import com.google.common.io.FileWriteMode;
import com.google.common.io.LineProcessor;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

@GwtIncompatible
public final class Files {
    private static final int TEMP_DIR_ATTEMPTS = 10000;
    private static final TreeTraverser<File> FILE_TREE_TRAVERSER = new TreeTraverser<File>(){

        @Override
        public Iterable<File> children(File file) {
            return Files.fileTreeChildren(file);
        }

        public String toString() {
            return "Files.fileTreeTraverser()";
        }
    };
    private static final SuccessorsFunction<File> FILE_TREE = new SuccessorsFunction<File>(){

        @Override
        public Iterable<File> successors(File file) {
            return Files.fileTreeChildren(file);
        }
    };

    private Files() {
    }

    @Beta
    public static BufferedReader newReader(File file, Charset charset) throws FileNotFoundException {
        Preconditions.checkNotNull(file);
        Preconditions.checkNotNull(charset);
        return new BufferedReader(new InputStreamReader((InputStream)new FileInputStream(file), charset));
    }

    @Beta
    public static BufferedWriter newWriter(File file, Charset charset) throws FileNotFoundException {
        Preconditions.checkNotNull(file);
        Preconditions.checkNotNull(charset);
        return new BufferedWriter(new OutputStreamWriter((OutputStream)new FileOutputStream(file), charset));
    }

    public static ByteSource asByteSource(File file) {
        return new FileByteSource(file);
    }

    public static ByteSink asByteSink(File file, FileWriteMode ... modes) {
        return new FileByteSink(file, modes);
    }

    public static CharSource asCharSource(File file, Charset charset) {
        return Files.asByteSource(file).asCharSource(charset);
    }

    public static CharSink asCharSink(File file, Charset charset, FileWriteMode ... modes) {
        return Files.asByteSink(file, modes).asCharSink(charset);
    }

    @Beta
    public static byte[] toByteArray(File file) throws IOException {
        return Files.asByteSource(file).read();
    }

    @Deprecated
    @Beta
    public static String toString(File file, Charset charset) throws IOException {
        return Files.asCharSource(file, charset).read();
    }

    @Beta
    public static void write(byte[] from, File to) throws IOException {
        Files.asByteSink(to, new FileWriteMode[0]).write(from);
    }

    @Deprecated
    @Beta
    public static void write(CharSequence from, File to, Charset charset) throws IOException {
        Files.asCharSink(to, charset, new FileWriteMode[0]).write(from);
    }

    @Beta
    public static void copy(File from, OutputStream to) throws IOException {
        Files.asByteSource(from).copyTo(to);
    }

    @Beta
    public static void copy(File from, File to) throws IOException {
        Preconditions.checkArgument(!from.equals(to), "Source %s and destination %s must be different", (Object)from, (Object)to);
        Files.asByteSource(from).copyTo(Files.asByteSink(to, new FileWriteMode[0]));
    }

    @Deprecated
    @Beta
    public static void copy(File from, Charset charset, Appendable to) throws IOException {
        Files.asCharSource(from, charset).copyTo(to);
    }

    @Deprecated
    @Beta
    public static void append(CharSequence from, File to, Charset charset) throws IOException {
        Files.asCharSink(to, charset, FileWriteMode.APPEND).write(from);
    }

    @Beta
    public static boolean equal(File file1, File file2) throws IOException {
        Preconditions.checkNotNull(file1);
        Preconditions.checkNotNull(file2);
        if (file1 == file2 || file1.equals(file2)) {
            return true;
        }
        long len1 = file1.length();
        long len2 = file2.length();
        if (len1 != 0L && len2 != 0L && len1 != len2) {
            return false;
        }
        return Files.asByteSource(file1).contentEquals(Files.asByteSource(file2));
    }

    @Beta
    public static File createTempDir() {
        File baseDir = new File(System.getProperty("java.io.tmpdir"));
        String baseName = System.currentTimeMillis() + "-";
        for (int counter = 0; counter < 10000; ++counter) {
            File tempDir = new File(baseDir, baseName + counter);
            if (!tempDir.mkdir()) continue;
            return tempDir;
        }
        throw new IllegalStateException("Failed to create directory within 10000 attempts (tried " + baseName + "0 to " + baseName + 9999 + ')');
    }

    @Beta
    public static void touch(File file) throws IOException {
        Preconditions.checkNotNull(file);
        if (!file.createNewFile() && !file.setLastModified(System.currentTimeMillis())) {
            throw new IOException("Unable to update modification time of " + file);
        }
    }

    @Beta
    public static void createParentDirs(File file) throws IOException {
        Preconditions.checkNotNull(file);
        File parent = file.getCanonicalFile().getParentFile();
        if (parent == null) {
            return;
        }
        parent.mkdirs();
        if (!parent.isDirectory()) {
            throw new IOException("Unable to create parent directories of " + file);
        }
    }

    @Beta
    public static void move(File from, File to) throws IOException {
        Preconditions.checkNotNull(from);
        Preconditions.checkNotNull(to);
        Preconditions.checkArgument(!from.equals(to), "Source %s and destination %s must be different", (Object)from, (Object)to);
        if (!from.renameTo(to)) {
            Files.copy(from, to);
            if (!from.delete()) {
                if (!to.delete()) {
                    throw new IOException("Unable to delete " + to);
                }
                throw new IOException("Unable to delete " + from);
            }
        }
    }

    @Deprecated
    @Beta
    public static String readFirstLine(File file, Charset charset) throws IOException {
        return Files.asCharSource(file, charset).readFirstLine();
    }

    @Beta
    public static List<String> readLines(File file, Charset charset) throws IOException {
        return Files.asCharSource(file, charset).readLines(new LineProcessor<List<String>>(){
            final List<String> result = Lists.newArrayList();

            @Override
            public boolean processLine(String line) {
                this.result.add(line);
                return true;
            }

            @Override
            public List<String> getResult() {
                return this.result;
            }
        });
    }

    @Deprecated
    @Beta
    @CanIgnoreReturnValue
    public static <T> T readLines(File file, Charset charset, LineProcessor<T> callback) throws IOException {
        return Files.asCharSource(file, charset).readLines(callback);
    }

    @Deprecated
    @Beta
    @CanIgnoreReturnValue
    public static <T> T readBytes(File file, ByteProcessor<T> processor) throws IOException {
        return Files.asByteSource(file).read(processor);
    }

    @Deprecated
    @Beta
    public static HashCode hash(File file, HashFunction hashFunction) throws IOException {
        return Files.asByteSource(file).hash(hashFunction);
    }

    @Beta
    public static MappedByteBuffer map(File file) throws IOException {
        Preconditions.checkNotNull(file);
        return Files.map(file, FileChannel.MapMode.READ_ONLY);
    }

    @Beta
    public static MappedByteBuffer map(File file, FileChannel.MapMode mode) throws IOException {
        return Files.mapInternal(file, mode, -1L);
    }

    @Beta
    public static MappedByteBuffer map(File file, FileChannel.MapMode mode, long size) throws IOException {
        Preconditions.checkArgument(size >= 0L, "size (%s) may not be negative", size);
        return Files.mapInternal(file, mode, size);
    }

    private static MappedByteBuffer mapInternal(File file, FileChannel.MapMode mode, long size) throws IOException {
        Preconditions.checkNotNull(file);
        Preconditions.checkNotNull(mode);
        try (Closer closer = Closer.create();){
            RandomAccessFile raf = closer.register(new RandomAccessFile(file, mode == FileChannel.MapMode.READ_ONLY ? "r" : "rw"));
            FileChannel channel = closer.register(raf.getChannel());
            MappedByteBuffer mappedByteBuffer = channel.map(mode, 0L, size == -1L ? channel.size() : size);
            return mappedByteBuffer;
        }
    }

    @Beta
    public static String simplifyPath(String pathname) {
        Preconditions.checkNotNull(pathname);
        if (pathname.length() == 0) {
            return ".";
        }
        Iterable<String> components = Splitter.on('/').omitEmptyStrings().split(pathname);
        ArrayList<String> path = new ArrayList<String>();
        Iterator<String> iterator = components.iterator();
        block8: while (iterator.hasNext()) {
            String component;
            switch (component = iterator.next()) {
                case ".": {
                    continue block8;
                }
                case "..": {
                    if (path.size() > 0 && !((String)path.get(path.size() - 1)).equals("..")) {
                        path.remove(path.size() - 1);
                        continue block8;
                    }
                    path.add("..");
                    continue block8;
                }
            }
            path.add(component);
        }
        String result = Joiner.on('/').join(path);
        if (pathname.charAt(0) == '/') {
            result = "/" + result;
        }
        while (result.startsWith("/../")) {
            result = result.substring(3);
        }
        if (result.equals("/..")) {
            result = "/";
        } else if ("".equals(result)) {
            result = ".";
        }
        return result;
    }

    @Beta
    public static String getFileExtension(String fullName) {
        Preconditions.checkNotNull(fullName);
        String fileName = new File(fullName).getName();
        int dotIndex = fileName.lastIndexOf(46);
        return dotIndex == -1 ? "" : fileName.substring(dotIndex + 1);
    }

    @Beta
    public static String getNameWithoutExtension(String file) {
        Preconditions.checkNotNull(file);
        String fileName = new File(file).getName();
        int dotIndex = fileName.lastIndexOf(46);
        return dotIndex == -1 ? fileName : fileName.substring(0, dotIndex);
    }

    @Deprecated
    static TreeTraverser<File> fileTreeTraverser() {
        return FILE_TREE_TRAVERSER;
    }

    @Beta
    public static Traverser<File> fileTraverser() {
        return Traverser.forTree(FILE_TREE);
    }

    private static Iterable<File> fileTreeChildren(File file) {
        File[] files;
        if (file.isDirectory() && (files = file.listFiles()) != null) {
            return Collections.unmodifiableList(Arrays.asList(files));
        }
        return Collections.emptyList();
    }

    @Beta
    public static Predicate<File> isDirectory() {
        return FilePredicate.IS_DIRECTORY;
    }

    @Beta
    public static Predicate<File> isFile() {
        return FilePredicate.IS_FILE;
    }

    private static enum FilePredicate implements Predicate<File>
    {
        IS_DIRECTORY{

            @Override
            public boolean apply(File file) {
                return file.isDirectory();
            }

            public String toString() {
                return "Files.isDirectory()";
            }
        }
        ,
        IS_FILE{

            @Override
            public boolean apply(File file) {
                return file.isFile();
            }

            public String toString() {
                return "Files.isFile()";
            }
        };

    }

    private static final class FileByteSink
    extends ByteSink {
        private final File file;
        private final ImmutableSet<FileWriteMode> modes;

        private FileByteSink(File file, FileWriteMode ... modes) {
            this.file = Preconditions.checkNotNull(file);
            this.modes = ImmutableSet.copyOf(modes);
        }

        @Override
        public FileOutputStream openStream() throws IOException {
            return new FileOutputStream(this.file, this.modes.contains((Object)FileWriteMode.APPEND));
        }

        public String toString() {
            return "Files.asByteSink(" + this.file + ", " + this.modes + ")";
        }
    }

    private static final class FileByteSource
    extends ByteSource {
        private final File file;

        private FileByteSource(File file) {
            this.file = Preconditions.checkNotNull(file);
        }

        @Override
        public FileInputStream openStream() throws IOException {
            return new FileInputStream(this.file);
        }

        @Override
        public Optional<Long> sizeIfKnown() {
            if (this.file.isFile()) {
                return Optional.of(this.file.length());
            }
            return Optional.absent();
        }

        @Override
        public long size() throws IOException {
            if (!this.file.isFile()) {
                throw new FileNotFoundException(this.file.toString());
            }
            return this.file.length();
        }

        @Override
        public byte[] read() throws IOException {
            try (Closer closer = Closer.create();){
                FileInputStream in = closer.register(this.openStream());
                byte[] byArray = ByteStreams.toByteArray(in, in.getChannel().size());
                return byArray;
            }
        }

        public String toString() {
            return "Files.asByteSource(" + this.file + ")";
        }
    }
}

