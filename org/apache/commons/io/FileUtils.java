/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.io;

import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UncheckedIOException;
import java.math.BigInteger;
import java.net.URL;
import java.net.URLConnection;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.CopyOption;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.Instant;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.chrono.ChronoLocalDate;
import java.time.chrono.ChronoLocalDateTime;
import java.time.chrono.ChronoZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.zip.CRC32;
import java.util.zip.CheckedInputStream;
import java.util.zip.Checksum;
import org.apache.commons.io.Charsets;
import org.apache.commons.io.FileExistsException;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOExceptionList;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.LineIterator;
import org.apache.commons.io.StreamIterator;
import org.apache.commons.io.file.AccumulatorPathVisitor;
import org.apache.commons.io.file.Counters;
import org.apache.commons.io.file.PathUtils;
import org.apache.commons.io.file.StandardDeleteOption;
import org.apache.commons.io.filefilter.FileEqualsFileFilter;
import org.apache.commons.io.filefilter.FileFileFilter;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.io.filefilter.SuffixFileFilter;

public class FileUtils {
    public static final long ONE_KB = 1024L;
    public static final BigInteger ONE_KB_BI = BigInteger.valueOf(1024L);
    public static final long ONE_MB = 0x100000L;
    public static final BigInteger ONE_MB_BI = ONE_KB_BI.multiply(ONE_KB_BI);
    public static final long ONE_GB = 0x40000000L;
    public static final BigInteger ONE_GB_BI = ONE_KB_BI.multiply(ONE_MB_BI);
    public static final long ONE_TB = 0x10000000000L;
    public static final BigInteger ONE_TB_BI = ONE_KB_BI.multiply(ONE_GB_BI);
    public static final long ONE_PB = 0x4000000000000L;
    public static final BigInteger ONE_PB_BI = ONE_KB_BI.multiply(ONE_TB_BI);
    public static final long ONE_EB = 0x1000000000000000L;
    public static final BigInteger ONE_EB_BI = ONE_KB_BI.multiply(ONE_PB_BI);
    public static final BigInteger ONE_ZB = BigInteger.valueOf(1024L).multiply(BigInteger.valueOf(0x1000000000000000L));
    public static final BigInteger ONE_YB = ONE_KB_BI.multiply(ONE_ZB);
    public static final File[] EMPTY_FILE_ARRAY = new File[0];

    private static CopyOption[] addCopyAttributes(CopyOption ... copyOptions) {
        Object[] actual = Arrays.copyOf(copyOptions, copyOptions.length + 1);
        Arrays.sort(actual, 0, copyOptions.length);
        if (Arrays.binarySearch(copyOptions, 0, copyOptions.length, StandardCopyOption.COPY_ATTRIBUTES) >= 0) {
            return copyOptions;
        }
        actual[actual.length - 1] = StandardCopyOption.COPY_ATTRIBUTES;
        return actual;
    }

    public static String byteCountToDisplaySize(BigInteger size) {
        Objects.requireNonNull(size, "size");
        String displaySize = size.divide(ONE_EB_BI).compareTo(BigInteger.ZERO) > 0 ? size.divide(ONE_EB_BI) + " EB" : (size.divide(ONE_PB_BI).compareTo(BigInteger.ZERO) > 0 ? size.divide(ONE_PB_BI) + " PB" : (size.divide(ONE_TB_BI).compareTo(BigInteger.ZERO) > 0 ? size.divide(ONE_TB_BI) + " TB" : (size.divide(ONE_GB_BI).compareTo(BigInteger.ZERO) > 0 ? size.divide(ONE_GB_BI) + " GB" : (size.divide(ONE_MB_BI).compareTo(BigInteger.ZERO) > 0 ? size.divide(ONE_MB_BI) + " MB" : (size.divide(ONE_KB_BI).compareTo(BigInteger.ZERO) > 0 ? size.divide(ONE_KB_BI) + " KB" : size + " bytes")))));
        return displaySize;
    }

    public static String byteCountToDisplaySize(long size) {
        return FileUtils.byteCountToDisplaySize(BigInteger.valueOf(size));
    }

    public static Checksum checksum(File file, Checksum checksum) throws IOException {
        FileUtils.requireExistsChecked(file, "file");
        FileUtils.requireFile(file, "file");
        Objects.requireNonNull(checksum, "checksum");
        try (CheckedInputStream inputStream = new CheckedInputStream(Files.newInputStream(file.toPath(), new OpenOption[0]), checksum);){
            IOUtils.consume(inputStream);
        }
        return checksum;
    }

    public static long checksumCRC32(File file) throws IOException {
        return FileUtils.checksum(file, new CRC32()).getValue();
    }

    public static void cleanDirectory(File directory) throws IOException {
        File[] files = FileUtils.listFiles(directory, null);
        ArrayList<IOException> causeList = new ArrayList<IOException>();
        for (File file : files) {
            try {
                FileUtils.forceDelete(file);
            } catch (IOException ioe) {
                causeList.add(ioe);
            }
        }
        if (!causeList.isEmpty()) {
            throw new IOExceptionList(directory.toString(), causeList);
        }
    }

    private static void cleanDirectoryOnExit(File directory) throws IOException {
        File[] files = FileUtils.listFiles(directory, null);
        ArrayList<IOException> causeList = new ArrayList<IOException>();
        for (File file : files) {
            try {
                FileUtils.forceDeleteOnExit(file);
            } catch (IOException ioe) {
                causeList.add(ioe);
            }
        }
        if (!causeList.isEmpty()) {
            throw new IOExceptionList(causeList);
        }
    }

    /*
     * Exception decompiling
     */
    public static boolean contentEquals(File file1, File file2) throws IOException {
        /*
         * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
         * 
         * org.benf.cfr.reader.util.ConfusedCFRException: Started 2 blocks at once
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.getStartingBlocks(Op04StructuredStatement.java:412)
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.buildNestedBlocks(Op04StructuredStatement.java:487)
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

    /*
     * Exception decompiling
     */
    public static boolean contentEqualsIgnoreEOL(File file1, File file2, String charsetName) throws IOException {
        /*
         * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
         * 
         * org.benf.cfr.reader.util.ConfusedCFRException: Started 2 blocks at once
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.getStartingBlocks(Op04StructuredStatement.java:412)
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.buildNestedBlocks(Op04StructuredStatement.java:487)
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

    public static File[] convertFileCollectionToFileArray(Collection<File> files) {
        return files.toArray(EMPTY_FILE_ARRAY);
    }

    public static void copyDirectory(File srcDir, File destDir) throws IOException {
        FileUtils.copyDirectory(srcDir, destDir, true);
    }

    public static void copyDirectory(File srcDir, File destDir, boolean preserveFileDate) throws IOException {
        FileUtils.copyDirectory(srcDir, destDir, null, preserveFileDate);
    }

    public static void copyDirectory(File srcDir, File destDir, FileFilter filter) throws IOException {
        FileUtils.copyDirectory(srcDir, destDir, filter, true);
    }

    public static void copyDirectory(File srcDir, File destDir, FileFilter filter, boolean preserveFileDate) throws IOException {
        FileUtils.copyDirectory(srcDir, destDir, filter, preserveFileDate, StandardCopyOption.REPLACE_EXISTING);
    }

    public static void copyDirectory(File srcDir, File destDir, FileFilter fileFilter, boolean preserveFileDate, CopyOption ... copyOptions) throws IOException {
        File[] srcFiles;
        FileUtils.requireFileCopy(srcDir, destDir);
        FileUtils.requireDirectory(srcDir, "srcDir");
        FileUtils.requireCanonicalPathsNotEquals(srcDir, destDir);
        ArrayList<String> exclusionList = null;
        String srcDirCanonicalPath = srcDir.getCanonicalPath();
        String destDirCanonicalPath = destDir.getCanonicalPath();
        if (destDirCanonicalPath.startsWith(srcDirCanonicalPath) && (srcFiles = FileUtils.listFiles(srcDir, fileFilter)).length > 0) {
            exclusionList = new ArrayList<String>(srcFiles.length);
            for (File srcFile : srcFiles) {
                File copiedFile = new File(destDir, srcFile.getName());
                exclusionList.add(copiedFile.getCanonicalPath());
            }
        }
        FileUtils.doCopyDirectory(srcDir, destDir, fileFilter, exclusionList, preserveFileDate, preserveFileDate ? FileUtils.addCopyAttributes(copyOptions) : copyOptions);
    }

    public static void copyDirectoryToDirectory(File sourceDir, File destinationDir) throws IOException {
        FileUtils.requireDirectoryIfExists(sourceDir, "sourceDir");
        FileUtils.requireDirectoryIfExists(destinationDir, "destinationDir");
        FileUtils.copyDirectory(sourceDir, new File(destinationDir, sourceDir.getName()), true);
    }

    public static void copyFile(File srcFile, File destFile) throws IOException {
        FileUtils.copyFile(srcFile, destFile, StandardCopyOption.COPY_ATTRIBUTES, StandardCopyOption.REPLACE_EXISTING);
    }

    public static void copyFile(File srcFile, File destFile, boolean preserveFileDate) throws IOException {
        CopyOption[] copyOptionArray;
        if (preserveFileDate) {
            CopyOption[] copyOptionArray2 = new CopyOption[2];
            copyOptionArray2[0] = StandardCopyOption.COPY_ATTRIBUTES;
            copyOptionArray = copyOptionArray2;
            copyOptionArray2[1] = StandardCopyOption.REPLACE_EXISTING;
        } else {
            CopyOption[] copyOptionArray3 = new CopyOption[1];
            copyOptionArray = copyOptionArray3;
            copyOptionArray3[0] = StandardCopyOption.REPLACE_EXISTING;
        }
        FileUtils.copyFile(srcFile, destFile, copyOptionArray);
    }

    public static void copyFile(File srcFile, File destFile, boolean preserveFileDate, CopyOption ... copyOptions) throws IOException {
        FileUtils.copyFile(srcFile, destFile, preserveFileDate ? FileUtils.addCopyAttributes(copyOptions) : copyOptions);
    }

    public static void copyFile(File srcFile, File destFile, CopyOption ... copyOptions) throws IOException {
        FileUtils.requireFileCopy(srcFile, destFile);
        FileUtils.requireFile(srcFile, "srcFile");
        FileUtils.requireCanonicalPathsNotEquals(srcFile, destFile);
        FileUtils.createParentDirectories(destFile);
        FileUtils.requireFileIfExists(destFile, "destFile");
        if (destFile.exists()) {
            FileUtils.requireCanWrite(destFile, "destFile");
        }
        Files.copy(srcFile.toPath(), destFile.toPath(), copyOptions);
        FileUtils.requireEqualSizes(srcFile, destFile, srcFile.length(), destFile.length());
    }

    public static long copyFile(File input, OutputStream output) throws IOException {
        try (InputStream fis = Files.newInputStream(input.toPath(), new OpenOption[0]);){
            long l = IOUtils.copyLarge(fis, output);
            return l;
        }
    }

    public static void copyFileToDirectory(File srcFile, File destDir) throws IOException {
        FileUtils.copyFileToDirectory(srcFile, destDir, true);
    }

    public static void copyFileToDirectory(File sourceFile, File destinationDir, boolean preserveFileDate) throws IOException {
        Objects.requireNonNull(sourceFile, "sourceFile");
        FileUtils.requireDirectoryIfExists(destinationDir, "destinationDir");
        FileUtils.copyFile(sourceFile, new File(destinationDir, sourceFile.getName()), preserveFileDate);
    }

    public static void copyInputStreamToFile(InputStream source, File destination) throws IOException {
        try (InputStream inputStream = source;){
            FileUtils.copyToFile(inputStream, destination);
        }
    }

    public static void copyToDirectory(File sourceFile, File destinationDir) throws IOException {
        Objects.requireNonNull(sourceFile, "sourceFile");
        if (sourceFile.isFile()) {
            FileUtils.copyFileToDirectory(sourceFile, destinationDir);
        } else if (sourceFile.isDirectory()) {
            FileUtils.copyDirectoryToDirectory(sourceFile, destinationDir);
        } else {
            throw new FileNotFoundException("The source " + sourceFile + " does not exist");
        }
    }

    public static void copyToDirectory(Iterable<File> sourceIterable, File destinationDir) throws IOException {
        Objects.requireNonNull(sourceIterable, "sourceIterable");
        for (File src : sourceIterable) {
            FileUtils.copyFileToDirectory(src, destinationDir);
        }
    }

    public static void copyToFile(InputStream inputStream, File file) throws IOException {
        try (FileOutputStream out = FileUtils.openOutputStream(file);){
            IOUtils.copy(inputStream, (OutputStream)out);
        }
    }

    public static void copyURLToFile(URL source, File destination) throws IOException {
        try (InputStream stream = source.openStream();){
            FileUtils.copyInputStreamToFile(stream, destination);
        }
    }

    public static void copyURLToFile(URL source, File destination, int connectionTimeoutMillis, int readTimeoutMillis) throws IOException {
        URLConnection connection = source.openConnection();
        connection.setConnectTimeout(connectionTimeoutMillis);
        connection.setReadTimeout(readTimeoutMillis);
        try (InputStream stream = connection.getInputStream();){
            FileUtils.copyInputStreamToFile(stream, destination);
        }
    }

    public static File createParentDirectories(File file) throws IOException {
        return FileUtils.mkdirs(FileUtils.getParentFile(file));
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    static String decodeUrl(String url) {
        String decoded = url;
        if (url != null && url.indexOf(37) >= 0) {
            int n = url.length();
            StringBuilder buffer = new StringBuilder();
            ByteBuffer bytes = ByteBuffer.allocate(n);
            int i = 0;
            while (i < n) {
                if (url.charAt(i) == '%') {
                    try {
                        do {
                            byte octet = (byte)Integer.parseInt(url.substring(i + 1, i + 3), 16);
                            bytes.put(octet);
                        } while ((i += 3) < n && url.charAt(i) == '%');
                        continue;
                    } catch (RuntimeException runtimeException) {
                    } finally {
                        if (bytes.position() <= 0) continue;
                        bytes.flip();
                        buffer.append(StandardCharsets.UTF_8.decode(bytes).toString());
                        bytes.clear();
                        continue;
                    }
                }
                buffer.append(url.charAt(i++));
            }
            decoded = buffer.toString();
        }
        return decoded;
    }

    public static File delete(File file) throws IOException {
        Objects.requireNonNull(file, "file");
        Files.delete(file.toPath());
        return file;
    }

    public static void deleteDirectory(File directory) throws IOException {
        Objects.requireNonNull(directory, "directory");
        if (!directory.exists()) {
            return;
        }
        if (!FileUtils.isSymlink(directory)) {
            FileUtils.cleanDirectory(directory);
        }
        FileUtils.delete(directory);
    }

    private static void deleteDirectoryOnExit(File directory) throws IOException {
        if (!directory.exists()) {
            return;
        }
        directory.deleteOnExit();
        if (!FileUtils.isSymlink(directory)) {
            FileUtils.cleanDirectoryOnExit(directory);
        }
    }

    public static boolean deleteQuietly(File file) {
        if (file == null) {
            return false;
        }
        try {
            if (file.isDirectory()) {
                FileUtils.cleanDirectory(file);
            }
        } catch (Exception exception) {
            // empty catch block
        }
        try {
            return file.delete();
        } catch (Exception ignored) {
            return false;
        }
    }

    public static boolean directoryContains(File directory, File child) throws IOException {
        FileUtils.requireDirectoryExists(directory, "directory");
        if (child == null) {
            return false;
        }
        if (!directory.exists() || !child.exists()) {
            return false;
        }
        return FilenameUtils.directoryContains(directory.getCanonicalPath(), child.getCanonicalPath());
    }

    private static void doCopyDirectory(File srcDir, File destDir, FileFilter fileFilter, List<String> exclusionList, boolean preserveDirDate, CopyOption ... copyOptions) throws IOException {
        File[] srcFiles = FileUtils.listFiles(srcDir, fileFilter);
        FileUtils.requireDirectoryIfExists(destDir, "destDir");
        FileUtils.mkdirs(destDir);
        FileUtils.requireCanWrite(destDir, "destDir");
        for (File srcFile : srcFiles) {
            File dstFile = new File(destDir, srcFile.getName());
            if (exclusionList != null && exclusionList.contains(srcFile.getCanonicalPath())) continue;
            if (srcFile.isDirectory()) {
                FileUtils.doCopyDirectory(srcFile, dstFile, fileFilter, exclusionList, preserveDirDate, copyOptions);
                continue;
            }
            FileUtils.copyFile(srcFile, dstFile, copyOptions);
        }
        if (preserveDirDate) {
            FileUtils.setLastModified(srcDir, destDir);
        }
    }

    public static void forceDelete(File file) throws IOException {
        Counters.PathCounters deleteCounters;
        Objects.requireNonNull(file, "file");
        try {
            deleteCounters = PathUtils.delete(file.toPath(), PathUtils.EMPTY_LINK_OPTION_ARRAY, StandardDeleteOption.OVERRIDE_READ_ONLY);
        } catch (IOException e) {
            throw new IOException("Cannot delete file: " + file, e);
        }
        if (deleteCounters.getFileCounter().get() < 1L && deleteCounters.getDirectoryCounter().get() < 1L) {
            throw new FileNotFoundException("File does not exist: " + file);
        }
    }

    public static void forceDeleteOnExit(File file) throws IOException {
        Objects.requireNonNull(file, "file");
        if (file.isDirectory()) {
            FileUtils.deleteDirectoryOnExit(file);
        } else {
            file.deleteOnExit();
        }
    }

    public static void forceMkdir(File directory) throws IOException {
        FileUtils.mkdirs(directory);
    }

    public static void forceMkdirParent(File file) throws IOException {
        Objects.requireNonNull(file, "file");
        File parent = FileUtils.getParentFile(file);
        if (parent == null) {
            return;
        }
        FileUtils.forceMkdir(parent);
    }

    public static File getFile(File directory, String ... names) {
        Objects.requireNonNull(directory, "directory");
        Objects.requireNonNull(names, "names");
        File file = directory;
        for (String name : names) {
            file = new File(file, name);
        }
        return file;
    }

    public static File getFile(String ... names) {
        Objects.requireNonNull(names, "names");
        File file = null;
        for (String name : names) {
            file = file == null ? new File(name) : new File(file, name);
        }
        return file;
    }

    private static File getParentFile(File file) {
        return file == null ? null : file.getParentFile();
    }

    public static File getTempDirectory() {
        return new File(FileUtils.getTempDirectoryPath());
    }

    public static String getTempDirectoryPath() {
        return System.getProperty("java.io.tmpdir");
    }

    public static File getUserDirectory() {
        return new File(FileUtils.getUserDirectoryPath());
    }

    public static String getUserDirectoryPath() {
        return System.getProperty("user.home");
    }

    public static boolean isDirectory(File file, LinkOption ... options) {
        return file != null && Files.isDirectory(file.toPath(), options);
    }

    public static boolean isEmptyDirectory(File directory) throws IOException {
        return PathUtils.isEmptyDirectory(directory.toPath());
    }

    public static boolean isFileNewer(File file, ChronoLocalDate chronoLocalDate) {
        return FileUtils.isFileNewer(file, chronoLocalDate, LocalTime.now());
    }

    public static boolean isFileNewer(File file, ChronoLocalDate chronoLocalDate, LocalTime localTime) {
        Objects.requireNonNull(chronoLocalDate, "chronoLocalDate");
        Objects.requireNonNull(localTime, "localTime");
        return FileUtils.isFileNewer(file, chronoLocalDate.atTime(localTime));
    }

    public static boolean isFileNewer(File file, ChronoLocalDateTime<?> chronoLocalDateTime) {
        return FileUtils.isFileNewer(file, chronoLocalDateTime, ZoneId.systemDefault());
    }

    public static boolean isFileNewer(File file, ChronoLocalDateTime<?> chronoLocalDateTime, ZoneId zoneId) {
        Objects.requireNonNull(chronoLocalDateTime, "chronoLocalDateTime");
        Objects.requireNonNull(zoneId, "zoneId");
        return FileUtils.isFileNewer(file, chronoLocalDateTime.atZone(zoneId));
    }

    public static boolean isFileNewer(File file, ChronoZonedDateTime<?> chronoZonedDateTime) {
        Objects.requireNonNull(chronoZonedDateTime, "chronoZonedDateTime");
        return FileUtils.isFileNewer(file, chronoZonedDateTime.toInstant());
    }

    public static boolean isFileNewer(File file, Date date) {
        Objects.requireNonNull(date, "date");
        return FileUtils.isFileNewer(file, date.getTime());
    }

    public static boolean isFileNewer(File file, File reference) {
        FileUtils.requireExists(reference, "reference");
        return FileUtils.isFileNewer(file, FileUtils.lastModifiedUnchecked(reference));
    }

    public static boolean isFileNewer(File file, Instant instant) {
        Objects.requireNonNull(instant, "instant");
        return FileUtils.isFileNewer(file, instant.toEpochMilli());
    }

    public static boolean isFileNewer(File file, long timeMillis) {
        Objects.requireNonNull(file, "file");
        return file.exists() && FileUtils.lastModifiedUnchecked(file) > timeMillis;
    }

    public static boolean isFileOlder(File file, ChronoLocalDate chronoLocalDate) {
        return FileUtils.isFileOlder(file, chronoLocalDate, LocalTime.now());
    }

    public static boolean isFileOlder(File file, ChronoLocalDate chronoLocalDate, LocalTime localTime) {
        Objects.requireNonNull(chronoLocalDate, "chronoLocalDate");
        Objects.requireNonNull(localTime, "localTime");
        return FileUtils.isFileOlder(file, chronoLocalDate.atTime(localTime));
    }

    public static boolean isFileOlder(File file, ChronoLocalDateTime<?> chronoLocalDateTime) {
        return FileUtils.isFileOlder(file, chronoLocalDateTime, ZoneId.systemDefault());
    }

    public static boolean isFileOlder(File file, ChronoLocalDateTime<?> chronoLocalDateTime, ZoneId zoneId) {
        Objects.requireNonNull(chronoLocalDateTime, "chronoLocalDateTime");
        Objects.requireNonNull(zoneId, "zoneId");
        return FileUtils.isFileOlder(file, chronoLocalDateTime.atZone(zoneId));
    }

    public static boolean isFileOlder(File file, ChronoZonedDateTime<?> chronoZonedDateTime) {
        Objects.requireNonNull(chronoZonedDateTime, "chronoZonedDateTime");
        return FileUtils.isFileOlder(file, chronoZonedDateTime.toInstant());
    }

    public static boolean isFileOlder(File file, Date date) {
        Objects.requireNonNull(date, "date");
        return FileUtils.isFileOlder(file, date.getTime());
    }

    public static boolean isFileOlder(File file, File reference) {
        FileUtils.requireExists(reference, "reference");
        return FileUtils.isFileOlder(file, FileUtils.lastModifiedUnchecked(reference));
    }

    public static boolean isFileOlder(File file, Instant instant) {
        Objects.requireNonNull(instant, "instant");
        return FileUtils.isFileOlder(file, instant.toEpochMilli());
    }

    public static boolean isFileOlder(File file, long timeMillis) {
        Objects.requireNonNull(file, "file");
        return file.exists() && FileUtils.lastModifiedUnchecked(file) < timeMillis;
    }

    public static boolean isRegularFile(File file, LinkOption ... options) {
        return file != null && Files.isRegularFile(file.toPath(), options);
    }

    public static boolean isSymlink(File file) {
        return file != null && Files.isSymbolicLink(file.toPath());
    }

    public static Iterator<File> iterateFiles(File directory, IOFileFilter fileFilter, IOFileFilter dirFilter) {
        return FileUtils.listFiles(directory, fileFilter, dirFilter).iterator();
    }

    public static Iterator<File> iterateFiles(File directory, String[] extensions, boolean recursive) {
        try {
            return StreamIterator.iterator(FileUtils.streamFiles(directory, recursive, extensions));
        } catch (IOException e) {
            throw new UncheckedIOException(directory.toString(), e);
        }
    }

    public static Iterator<File> iterateFilesAndDirs(File directory, IOFileFilter fileFilter, IOFileFilter dirFilter) {
        return FileUtils.listFilesAndDirs(directory, fileFilter, dirFilter).iterator();
    }

    public static long lastModified(File file) throws IOException {
        return Files.getLastModifiedTime(Objects.requireNonNull(file.toPath(), "file"), new LinkOption[0]).toMillis();
    }

    public static long lastModifiedUnchecked(File file) {
        try {
            return FileUtils.lastModified(file);
        } catch (IOException e) {
            throw new UncheckedIOException(file.toString(), e);
        }
    }

    public static LineIterator lineIterator(File file) throws IOException {
        return FileUtils.lineIterator(file, null);
    }

    public static LineIterator lineIterator(File file, String charsetName) throws IOException {
        FileInputStream inputStream = null;
        try {
            inputStream = FileUtils.openInputStream(file);
            return IOUtils.lineIterator((InputStream)inputStream, charsetName);
        } catch (IOException | RuntimeException ex) {
            IOUtils.closeQuietly((Closeable)inputStream, ex::addSuppressed);
            throw ex;
        }
    }

    private static AccumulatorPathVisitor listAccumulate(File directory, IOFileFilter fileFilter, IOFileFilter dirFilter) throws IOException {
        boolean isDirFilterSet = dirFilter != null;
        FileEqualsFileFilter rootDirFilter = new FileEqualsFileFilter(directory);
        FileEqualsFileFilter dirPathFilter = isDirFilterSet ? rootDirFilter.or(dirFilter) : rootDirFilter;
        AccumulatorPathVisitor visitor = new AccumulatorPathVisitor(Counters.noopPathCounters(), fileFilter, dirPathFilter);
        Files.walkFileTree(directory.toPath(), Collections.emptySet(), FileUtils.toMaxDepth(isDirFilterSet), visitor);
        return visitor;
    }

    private static File[] listFiles(File directory, FileFilter fileFilter) throws IOException {
        File[] files;
        FileUtils.requireDirectoryExists(directory, "directory");
        File[] fileArray = files = fileFilter == null ? directory.listFiles() : directory.listFiles(fileFilter);
        if (files == null) {
            throw new IOException("Unknown I/O error listing contents of directory: " + directory);
        }
        return files;
    }

    public static Collection<File> listFiles(File directory, IOFileFilter fileFilter, IOFileFilter dirFilter) {
        try {
            AccumulatorPathVisitor visitor = FileUtils.listAccumulate(directory, fileFilter, dirFilter);
            return visitor.getFileList().stream().map(Path::toFile).collect(Collectors.toList());
        } catch (IOException e) {
            throw new UncheckedIOException(directory.toString(), e);
        }
    }

    public static Collection<File> listFiles(File directory, String[] extensions, boolean recursive) {
        try {
            return FileUtils.toList(FileUtils.streamFiles(directory, recursive, extensions));
        } catch (IOException e) {
            throw new UncheckedIOException(directory.toString(), e);
        }
    }

    public static Collection<File> listFilesAndDirs(File directory, IOFileFilter fileFilter, IOFileFilter dirFilter) {
        try {
            AccumulatorPathVisitor visitor = FileUtils.listAccumulate(directory, fileFilter, dirFilter);
            List<Path> list = visitor.getFileList();
            list.addAll(visitor.getDirList());
            return list.stream().map(Path::toFile).collect(Collectors.toList());
        } catch (IOException e) {
            throw new UncheckedIOException(directory.toString(), e);
        }
    }

    private static File mkdirs(File directory) throws IOException {
        if (directory != null && !directory.mkdirs() && !directory.isDirectory()) {
            throw new IOException("Cannot create directory '" + directory + "'.");
        }
        return directory;
    }

    public static void moveDirectory(File srcDir, File destDir) throws IOException {
        FileUtils.validateMoveParameters(srcDir, destDir);
        FileUtils.requireDirectory(srcDir, "srcDir");
        FileUtils.requireAbsent(destDir, "destDir");
        if (!srcDir.renameTo(destDir)) {
            if (destDir.getCanonicalPath().startsWith(srcDir.getCanonicalPath() + File.separator)) {
                throw new IOException("Cannot move directory: " + srcDir + " to a subdirectory of itself: " + destDir);
            }
            FileUtils.copyDirectory(srcDir, destDir);
            FileUtils.deleteDirectory(srcDir);
            if (srcDir.exists()) {
                throw new IOException("Failed to delete original directory '" + srcDir + "' after copy to '" + destDir + "'");
            }
        }
    }

    public static void moveDirectoryToDirectory(File src, File destDir, boolean createDestDir) throws IOException {
        FileUtils.validateMoveParameters(src, destDir);
        if (!destDir.isDirectory()) {
            if (destDir.exists()) {
                throw new IOException("Destination '" + destDir + "' is not a directory");
            }
            if (!createDestDir) {
                throw new FileNotFoundException("Destination directory '" + destDir + "' does not exist [createDestDir=" + false + "]");
            }
            FileUtils.mkdirs(destDir);
        }
        FileUtils.moveDirectory(src, new File(destDir, src.getName()));
    }

    public static void moveFile(File srcFile, File destFile) throws IOException {
        FileUtils.moveFile(srcFile, destFile, StandardCopyOption.COPY_ATTRIBUTES);
    }

    public static void moveFile(File srcFile, File destFile, CopyOption ... copyOptions) throws IOException {
        FileUtils.validateMoveParameters(srcFile, destFile);
        FileUtils.requireFile(srcFile, "srcFile");
        FileUtils.requireAbsent(destFile, null);
        boolean rename = srcFile.renameTo(destFile);
        if (!rename) {
            FileUtils.copyFile(srcFile, destFile, copyOptions);
            if (!srcFile.delete()) {
                FileUtils.deleteQuietly(destFile);
                throw new IOException("Failed to delete original file '" + srcFile + "' after copy to '" + destFile + "'");
            }
        }
    }

    public static void moveFileToDirectory(File srcFile, File destDir, boolean createDestDir) throws IOException {
        FileUtils.validateMoveParameters(srcFile, destDir);
        if (!destDir.exists() && createDestDir) {
            FileUtils.mkdirs(destDir);
        }
        FileUtils.requireExistsChecked(destDir, "destDir");
        FileUtils.requireDirectory(destDir, "destDir");
        FileUtils.moveFile(srcFile, new File(destDir, srcFile.getName()));
    }

    public static void moveToDirectory(File src, File destDir, boolean createDestDir) throws IOException {
        FileUtils.validateMoveParameters(src, destDir);
        if (src.isDirectory()) {
            FileUtils.moveDirectoryToDirectory(src, destDir, createDestDir);
        } else {
            FileUtils.moveFileToDirectory(src, destDir, createDestDir);
        }
    }

    public static FileInputStream openInputStream(File file) throws IOException {
        Objects.requireNonNull(file, "file");
        return new FileInputStream(file);
    }

    public static FileOutputStream openOutputStream(File file) throws IOException {
        return FileUtils.openOutputStream(file, false);
    }

    public static FileOutputStream openOutputStream(File file, boolean append) throws IOException {
        Objects.requireNonNull(file, "file");
        if (file.exists()) {
            FileUtils.requireFile(file, "file");
            FileUtils.requireCanWrite(file, "file");
        } else {
            FileUtils.createParentDirectories(file);
        }
        return new FileOutputStream(file, append);
    }

    public static byte[] readFileToByteArray(File file) throws IOException {
        try (FileInputStream inputStream = FileUtils.openInputStream(file);){
            long fileLength = file.length();
            byte[] byArray = fileLength > 0L ? IOUtils.toByteArray((InputStream)inputStream, fileLength) : IOUtils.toByteArray(inputStream);
            return byArray;
        }
    }

    @Deprecated
    public static String readFileToString(File file) throws IOException {
        return FileUtils.readFileToString(file, Charset.defaultCharset());
    }

    public static String readFileToString(File file, Charset charsetName) throws IOException {
        try (FileInputStream inputStream = FileUtils.openInputStream(file);){
            String string = IOUtils.toString((InputStream)inputStream, Charsets.toCharset(charsetName));
            return string;
        }
    }

    public static String readFileToString(File file, String charsetName) throws IOException {
        return FileUtils.readFileToString(file, Charsets.toCharset(charsetName));
    }

    @Deprecated
    public static List<String> readLines(File file) throws IOException {
        return FileUtils.readLines(file, Charset.defaultCharset());
    }

    public static List<String> readLines(File file, Charset charset) throws IOException {
        try (FileInputStream inputStream = FileUtils.openInputStream(file);){
            List<String> list = IOUtils.readLines((InputStream)inputStream, Charsets.toCharset(charset));
            return list;
        }
    }

    public static List<String> readLines(File file, String charsetName) throws IOException {
        return FileUtils.readLines(file, Charsets.toCharset(charsetName));
    }

    private static void requireAbsent(File file, String name) throws FileExistsException {
        if (file.exists()) {
            throw new FileExistsException(String.format("File element in parameter '%s' already exists: '%s'", name, file));
        }
    }

    private static void requireCanonicalPathsNotEquals(File file1, File file2) throws IOException {
        String canonicalPath = file1.getCanonicalPath();
        if (canonicalPath.equals(file2.getCanonicalPath())) {
            throw new IllegalArgumentException(String.format("File canonical paths are equal: '%s' (file1='%s', file2='%s')", canonicalPath, file1, file2));
        }
    }

    private static void requireCanWrite(File file, String name) {
        Objects.requireNonNull(file, "file");
        if (!file.canWrite()) {
            throw new IllegalArgumentException("File parameter '" + name + " is not writable: '" + file + "'");
        }
    }

    private static File requireDirectory(File directory, String name) {
        Objects.requireNonNull(directory, name);
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException("Parameter '" + name + "' is not a directory: '" + directory + "'");
        }
        return directory;
    }

    private static File requireDirectoryExists(File directory, String name) {
        FileUtils.requireExists(directory, name);
        FileUtils.requireDirectory(directory, name);
        return directory;
    }

    private static File requireDirectoryIfExists(File directory, String name) {
        Objects.requireNonNull(directory, name);
        if (directory.exists()) {
            FileUtils.requireDirectory(directory, name);
        }
        return directory;
    }

    private static void requireEqualSizes(File srcFile, File destFile, long srcLen, long dstLen) throws IOException {
        if (srcLen != dstLen) {
            throw new IOException("Failed to copy full contents from '" + srcFile + "' to '" + destFile + "' Expected length: " + srcLen + " Actual: " + dstLen);
        }
    }

    private static File requireExists(File file, String fileParamName) {
        Objects.requireNonNull(file, fileParamName);
        if (!file.exists()) {
            throw new IllegalArgumentException("File system element for parameter '" + fileParamName + "' does not exist: '" + file + "'");
        }
        return file;
    }

    private static File requireExistsChecked(File file, String fileParamName) throws FileNotFoundException {
        Objects.requireNonNull(file, fileParamName);
        if (!file.exists()) {
            throw new FileNotFoundException("File system element for parameter '" + fileParamName + "' does not exist: '" + file + "'");
        }
        return file;
    }

    private static File requireFile(File file, String name) {
        Objects.requireNonNull(file, name);
        if (!file.isFile()) {
            throw new IllegalArgumentException("Parameter '" + name + "' is not a file: " + file);
        }
        return file;
    }

    private static void requireFileCopy(File source, File destination) throws FileNotFoundException {
        FileUtils.requireExistsChecked(source, "source");
        Objects.requireNonNull(destination, "destination");
    }

    private static File requireFileIfExists(File file, String name) {
        Objects.requireNonNull(file, name);
        return file.exists() ? FileUtils.requireFile(file, name) : file;
    }

    private static void setLastModified(File sourceFile, File targetFile) throws IOException {
        Objects.requireNonNull(sourceFile, "sourceFile");
        FileUtils.setLastModified(targetFile, FileUtils.lastModified(sourceFile));
    }

    private static void setLastModified(File file, long timeMillis) throws IOException {
        Objects.requireNonNull(file, "file");
        if (!file.setLastModified(timeMillis)) {
            throw new IOException(String.format("Failed setLastModified(%s) on '%s'", timeMillis, file));
        }
    }

    public static long sizeOf(File file) {
        FileUtils.requireExists(file, "file");
        return file.isDirectory() ? FileUtils.sizeOfDirectory0(file) : file.length();
    }

    private static long sizeOf0(File file) {
        Objects.requireNonNull(file, "file");
        if (file.isDirectory()) {
            return FileUtils.sizeOfDirectory0(file);
        }
        return file.length();
    }

    public static BigInteger sizeOfAsBigInteger(File file) {
        FileUtils.requireExists(file, "file");
        return file.isDirectory() ? FileUtils.sizeOfDirectoryBig0(file) : BigInteger.valueOf(file.length());
    }

    private static BigInteger sizeOfBig0(File file) {
        Objects.requireNonNull(file, "fileOrDir");
        return file.isDirectory() ? FileUtils.sizeOfDirectoryBig0(file) : BigInteger.valueOf(file.length());
    }

    public static long sizeOfDirectory(File directory) {
        return FileUtils.sizeOfDirectory0(FileUtils.requireDirectoryExists(directory, "directory"));
    }

    private static long sizeOfDirectory0(File directory) {
        Objects.requireNonNull(directory, "directory");
        File[] files = directory.listFiles();
        if (files == null) {
            return 0L;
        }
        long size = 0L;
        for (File file : files) {
            if (!FileUtils.isSymlink(file) && (size += FileUtils.sizeOf0(file)) < 0L) break;
        }
        return size;
    }

    public static BigInteger sizeOfDirectoryAsBigInteger(File directory) {
        return FileUtils.sizeOfDirectoryBig0(FileUtils.requireDirectoryExists(directory, "directory"));
    }

    private static BigInteger sizeOfDirectoryBig0(File directory) {
        Objects.requireNonNull(directory, "directory");
        File[] files = directory.listFiles();
        if (files == null) {
            return BigInteger.ZERO;
        }
        BigInteger size = BigInteger.ZERO;
        for (File file : files) {
            if (FileUtils.isSymlink(file)) continue;
            size = size.add(FileUtils.sizeOfBig0(file));
        }
        return size;
    }

    public static Stream<File> streamFiles(File directory, boolean recursive, String ... extensions) throws IOException {
        IOFileFilter filter = extensions == null ? FileFileFilter.INSTANCE : FileFileFilter.INSTANCE.and(new SuffixFileFilter(FileUtils.toSuffixes(extensions)));
        return PathUtils.walk(directory.toPath(), filter, FileUtils.toMaxDepth(recursive), false, FileVisitOption.FOLLOW_LINKS).map(Path::toFile);
    }

    public static File toFile(URL url) {
        if (url == null || !"file".equalsIgnoreCase(url.getProtocol())) {
            return null;
        }
        String filename = url.getFile().replace('/', File.separatorChar);
        return new File(FileUtils.decodeUrl(filename));
    }

    public static File[] toFiles(URL ... urls) {
        if (IOUtils.length(urls) == 0) {
            return EMPTY_FILE_ARRAY;
        }
        File[] files = new File[urls.length];
        for (int i = 0; i < urls.length; ++i) {
            URL url = urls[i];
            if (url == null) continue;
            if (!"file".equalsIgnoreCase(url.getProtocol())) {
                throw new IllegalArgumentException("Can only convert file URL to a File: " + url);
            }
            files[i] = FileUtils.toFile(url);
        }
        return files;
    }

    private static List<File> toList(Stream<File> stream) {
        return stream.collect(Collectors.toList());
    }

    private static int toMaxDepth(boolean recursive) {
        return recursive ? Integer.MAX_VALUE : 1;
    }

    private static String[] toSuffixes(String ... extensions) {
        Objects.requireNonNull(extensions, "extensions");
        String[] suffixes = new String[extensions.length];
        for (int i = 0; i < extensions.length; ++i) {
            suffixes[i] = "." + extensions[i];
        }
        return suffixes;
    }

    public static void touch(File file) throws IOException {
        Objects.requireNonNull(file, "file");
        if (!file.exists()) {
            FileUtils.openOutputStream(file).close();
        }
        FileUtils.setLastModified(file, System.currentTimeMillis());
    }

    public static URL[] toURLs(File ... files) throws IOException {
        Objects.requireNonNull(files, "files");
        URL[] urls = new URL[files.length];
        for (int i = 0; i < urls.length; ++i) {
            urls[i] = files[i].toURI().toURL();
        }
        return urls;
    }

    private static void validateMoveParameters(File source, File destination) throws FileNotFoundException {
        Objects.requireNonNull(source, "source");
        Objects.requireNonNull(destination, "destination");
        if (!source.exists()) {
            throw new FileNotFoundException("Source '" + source + "' does not exist");
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static boolean waitFor(File file, int seconds) {
        Objects.requireNonNull(file, "file");
        long finishAtMillis = System.currentTimeMillis() + (long)seconds * 1000L;
        boolean wasInterrupted = false;
        try {
            while (!file.exists()) {
                long remainingMillis = finishAtMillis - System.currentTimeMillis();
                if (remainingMillis < 0L) {
                    boolean bl = false;
                    return bl;
                }
                try {
                    Thread.sleep(Math.min(100L, remainingMillis));
                } catch (InterruptedException ignore) {
                    wasInterrupted = true;
                } catch (Exception ex) {
                    break;
                }
            }
        } finally {
            if (wasInterrupted) {
                Thread.currentThread().interrupt();
            }
        }
        return true;
    }

    @Deprecated
    public static void write(File file, CharSequence data) throws IOException {
        FileUtils.write(file, data, Charset.defaultCharset(), false);
    }

    @Deprecated
    public static void write(File file, CharSequence data, boolean append) throws IOException {
        FileUtils.write(file, data, Charset.defaultCharset(), append);
    }

    public static void write(File file, CharSequence data, Charset charset) throws IOException {
        FileUtils.write(file, data, charset, false);
    }

    public static void write(File file, CharSequence data, Charset charset, boolean append) throws IOException {
        FileUtils.writeStringToFile(file, Objects.toString(data, null), charset, append);
    }

    public static void write(File file, CharSequence data, String charsetName) throws IOException {
        FileUtils.write(file, data, charsetName, false);
    }

    public static void write(File file, CharSequence data, String charsetName, boolean append) throws IOException {
        FileUtils.write(file, data, Charsets.toCharset(charsetName), append);
    }

    public static void writeByteArrayToFile(File file, byte[] data) throws IOException {
        FileUtils.writeByteArrayToFile(file, data, false);
    }

    public static void writeByteArrayToFile(File file, byte[] data, boolean append) throws IOException {
        FileUtils.writeByteArrayToFile(file, data, 0, data.length, append);
    }

    public static void writeByteArrayToFile(File file, byte[] data, int off, int len) throws IOException {
        FileUtils.writeByteArrayToFile(file, data, off, len, false);
    }

    public static void writeByteArrayToFile(File file, byte[] data, int off, int len, boolean append) throws IOException {
        try (FileOutputStream out = FileUtils.openOutputStream(file, append);){
            ((OutputStream)out).write(data, off, len);
        }
    }

    public static void writeLines(File file, Collection<?> lines) throws IOException {
        FileUtils.writeLines(file, null, lines, null, false);
    }

    public static void writeLines(File file, Collection<?> lines, boolean append) throws IOException {
        FileUtils.writeLines(file, null, lines, null, append);
    }

    public static void writeLines(File file, Collection<?> lines, String lineEnding) throws IOException {
        FileUtils.writeLines(file, null, lines, lineEnding, false);
    }

    public static void writeLines(File file, Collection<?> lines, String lineEnding, boolean append) throws IOException {
        FileUtils.writeLines(file, null, lines, lineEnding, append);
    }

    public static void writeLines(File file, String charsetName, Collection<?> lines) throws IOException {
        FileUtils.writeLines(file, charsetName, lines, null, false);
    }

    public static void writeLines(File file, String charsetName, Collection<?> lines, boolean append) throws IOException {
        FileUtils.writeLines(file, charsetName, lines, null, append);
    }

    public static void writeLines(File file, String charsetName, Collection<?> lines, String lineEnding) throws IOException {
        FileUtils.writeLines(file, charsetName, lines, lineEnding, false);
    }

    public static void writeLines(File file, String charsetName, Collection<?> lines, String lineEnding, boolean append) throws IOException {
        try (BufferedOutputStream out = new BufferedOutputStream(FileUtils.openOutputStream(file, append));){
            IOUtils.writeLines(lines, lineEnding, (OutputStream)out, charsetName);
        }
    }

    @Deprecated
    public static void writeStringToFile(File file, String data) throws IOException {
        FileUtils.writeStringToFile(file, data, Charset.defaultCharset(), false);
    }

    @Deprecated
    public static void writeStringToFile(File file, String data, boolean append) throws IOException {
        FileUtils.writeStringToFile(file, data, Charset.defaultCharset(), append);
    }

    public static void writeStringToFile(File file, String data, Charset charset) throws IOException {
        FileUtils.writeStringToFile(file, data, charset, false);
    }

    public static void writeStringToFile(File file, String data, Charset charset, boolean append) throws IOException {
        try (FileOutputStream out = FileUtils.openOutputStream(file, append);){
            IOUtils.write(data, (OutputStream)out, charset);
        }
    }

    public static void writeStringToFile(File file, String data, String charsetName) throws IOException {
        FileUtils.writeStringToFile(file, data, charsetName, false);
    }

    public static void writeStringToFile(File file, String data, String charsetName, boolean append) throws IOException {
        FileUtils.writeStringToFile(file, data, Charsets.toCharset(charsetName), append);
    }

    @Deprecated
    public FileUtils() {
    }
}

