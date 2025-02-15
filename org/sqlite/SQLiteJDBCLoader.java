/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.sqlite;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.util.LinkedList;
import java.util.Properties;
import java.util.UUID;
import org.sqlite.util.OSInfo;
import org.sqlite.util.StringUtils;

public class SQLiteJDBCLoader {
    private static boolean extracted = false;

    public static synchronized boolean initialize() throws Exception {
        if (!extracted) {
            SQLiteJDBCLoader.cleanup();
        }
        SQLiteJDBCLoader.loadSQLiteNativeLibrary();
        return extracted;
    }

    private static File getTempDir() {
        return new File(System.getProperty("org.sqlite.tmpdir", System.getProperty("java.io.tmpdir")));
    }

    static void cleanup() {
        String tempFolder = SQLiteJDBCLoader.getTempDir().getAbsolutePath();
        File dir = new File(tempFolder);
        File[] nativeLibFiles = dir.listFiles(new FilenameFilter(){
            private final String searchPattern = "sqlite-" + SQLiteJDBCLoader.getVersion();

            @Override
            public boolean accept(File dir, String name) {
                return name.startsWith(this.searchPattern) && !name.endsWith(".lck");
            }
        });
        if (nativeLibFiles != null) {
            for (File nativeLibFile : nativeLibFiles) {
                File lckFile = new File(nativeLibFile.getAbsolutePath() + ".lck");
                if (lckFile.exists()) continue;
                try {
                    nativeLibFile.delete();
                } catch (SecurityException e) {
                    System.err.println("Failed to delete old native lib" + e.getMessage());
                }
            }
        }
    }

    @Deprecated
    static boolean getPureJavaFlag() {
        return Boolean.parseBoolean(System.getProperty("sqlite.purejava", "false"));
    }

    @Deprecated
    public static boolean isPureJavaMode() {
        return false;
    }

    public static boolean isNativeMode() throws Exception {
        SQLiteJDBCLoader.initialize();
        return extracted;
    }

    static String md5sum(InputStream input) throws IOException {
        try (BufferedInputStream in = new BufferedInputStream(input);){
            MessageDigest digest = MessageDigest.getInstance("MD5");
            DigestInputStream digestInputStream = new DigestInputStream(in, digest);
            while (digestInputStream.read() >= 0) {
            }
            ByteArrayOutputStream md5out = new ByteArrayOutputStream();
            md5out.write(digest.digest());
            String string = md5out.toString();
            return string;
        }
    }

    private static boolean contentsEquals(InputStream in1, InputStream in2) throws IOException {
        int ch2;
        if (!(in1 instanceof BufferedInputStream)) {
            in1 = new BufferedInputStream(in1);
        }
        if (!(in2 instanceof BufferedInputStream)) {
            in2 = new BufferedInputStream(in2);
        }
        int ch = in1.read();
        while (ch != -1) {
            ch2 = in2.read();
            if (ch != ch2) {
                return false;
            }
            ch = in1.read();
        }
        ch2 = in2.read();
        return ch2 == -1;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private static boolean extractAndLoadLibraryFile(String libFolderForCurrentOS, String libraryFileName, String targetFolder) {
        String nativeLibraryFilePath = libFolderForCurrentOS + "/" + libraryFileName;
        String uuid = UUID.randomUUID().toString();
        String extractedLibFileName = String.format("sqlite-%s-%s-%s", SQLiteJDBCLoader.getVersion(), uuid, libraryFileName);
        String extractedLckFileName = extractedLibFileName + ".lck";
        File extractedLibFile = new File(targetFolder, extractedLibFileName);
        File extractedLckFile = new File(targetFolder, extractedLckFileName);
        try {
            InputStream reader = SQLiteJDBCLoader.class.getResourceAsStream(nativeLibraryFilePath);
            if (!extractedLckFile.exists()) {
                new FileOutputStream(extractedLckFile).close();
            }
            FileOutputStream writer = new FileOutputStream(extractedLibFile);
            try {
                byte[] buffer = new byte[8192];
                int bytesRead = 0;
                while ((bytesRead = reader.read(buffer)) != -1) {
                    writer.write(buffer, 0, bytesRead);
                }
            } finally {
                extractedLibFile.deleteOnExit();
                extractedLckFile.deleteOnExit();
                if (writer != null) {
                    writer.close();
                }
                if (reader != null) {
                    reader.close();
                }
            }
            extractedLibFile.setReadable(true);
            extractedLibFile.setWritable(true, true);
            extractedLibFile.setExecutable(true);
            InputStream nativeIn = SQLiteJDBCLoader.class.getResourceAsStream(nativeLibraryFilePath);
            FileInputStream extractedLibIn = new FileInputStream(extractedLibFile);
            try {
                if (!SQLiteJDBCLoader.contentsEquals(nativeIn, extractedLibIn)) {
                    throw new RuntimeException(String.format("Failed to write a native library file at %s", extractedLibFile));
                }
            } finally {
                if (nativeIn != null) {
                    nativeIn.close();
                }
                if (extractedLibIn != null) {
                    ((InputStream)extractedLibIn).close();
                }
            }
            return SQLiteJDBCLoader.loadNativeLibrary(targetFolder, extractedLibFileName);
        } catch (IOException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    private static boolean loadNativeLibrary(String path, String name) {
        File libPath = new File(path, name);
        if (libPath.exists()) {
            try {
                System.load(new File(path, name).getAbsolutePath());
                return true;
            } catch (UnsatisfiedLinkError e) {
                System.err.println("Failed to load native library:" + name + ". osinfo: " + OSInfo.getNativeLibFolderPathForCurrentOS());
                System.err.println(e);
                return false;
            }
        }
        return false;
    }

    private static void loadSQLiteNativeLibrary() throws Exception {
        String packagePath;
        boolean hasNativeLib;
        if (extracted) {
            return;
        }
        LinkedList<String> triedPaths = new LinkedList<String>();
        String sqliteNativeLibraryPath = System.getProperty("org.sqlite.lib.path");
        String sqliteNativeLibraryName = System.getProperty("org.sqlite.lib.name");
        if (sqliteNativeLibraryName == null && (sqliteNativeLibraryName = System.mapLibraryName("sqlitejdbc")) != null && sqliteNativeLibraryName.endsWith(".dylib")) {
            sqliteNativeLibraryName = sqliteNativeLibraryName.replace(".dylib", ".jnilib");
        }
        if (sqliteNativeLibraryPath != null) {
            if (SQLiteJDBCLoader.loadNativeLibrary(sqliteNativeLibraryPath, sqliteNativeLibraryName)) {
                extracted = true;
                return;
            }
            triedPaths.add(sqliteNativeLibraryPath);
        }
        if (!(hasNativeLib = SQLiteJDBCLoader.hasResource((sqliteNativeLibraryPath = String.format("/%s/native/%s", packagePath = SQLiteJDBCLoader.class.getPackage().getName().replaceAll("\\.", "/"), OSInfo.getNativeLibFolderPathForCurrentOS())) + "/" + sqliteNativeLibraryName)) && OSInfo.getOSName().equals("Mac")) {
            String altName = "libsqlitejdbc.jnilib";
            if (SQLiteJDBCLoader.hasResource(sqliteNativeLibraryPath + "/" + altName)) {
                sqliteNativeLibraryName = altName;
                hasNativeLib = true;
            }
        }
        if (hasNativeLib) {
            String tempFolder = SQLiteJDBCLoader.getTempDir().getAbsolutePath();
            if (SQLiteJDBCLoader.extractAndLoadLibraryFile(sqliteNativeLibraryPath, sqliteNativeLibraryName, tempFolder)) {
                extracted = true;
                return;
            }
            triedPaths.add(sqliteNativeLibraryPath);
        }
        String javaLibraryPath = System.getProperty("java.library.path", "");
        for (String ldPath : javaLibraryPath.split(File.pathSeparator)) {
            if (ldPath.isEmpty()) continue;
            if (SQLiteJDBCLoader.loadNativeLibrary(ldPath, sqliteNativeLibraryName)) {
                extracted = true;
                return;
            }
            triedPaths.add(ldPath);
        }
        extracted = false;
        throw new Exception(String.format("No native library found for os.name=%s, os.arch=%s, paths=[%s]", OSInfo.getOSName(), OSInfo.getArchName(), StringUtils.join(triedPaths, File.pathSeparator)));
    }

    private static boolean hasResource(String path) {
        return SQLiteJDBCLoader.class.getResource(path) != null;
    }

    private static void getNativeLibraryFolderForTheCurrentOS() {
        String osName = OSInfo.getOSName();
        String archName = OSInfo.getArchName();
    }

    public static int getMajorVersion() {
        String[] c = SQLiteJDBCLoader.getVersion().split("\\.");
        return c.length > 0 ? Integer.parseInt(c[0]) : 1;
    }

    public static int getMinorVersion() {
        String[] c = SQLiteJDBCLoader.getVersion().split("\\.");
        return c.length > 1 ? Integer.parseInt(c[1]) : 0;
    }

    public static String getVersion() {
        URL versionFile = SQLiteJDBCLoader.class.getResource("/META-INF/maven/org.xerial/sqlite-jdbc/pom.properties");
        if (versionFile == null) {
            versionFile = SQLiteJDBCLoader.class.getResource("/META-INF/maven/org.xerial/sqlite-jdbc/VERSION");
        }
        String version = "unknown";
        try {
            if (versionFile != null) {
                Properties versionData = new Properties();
                versionData.load(versionFile.openStream());
                version = versionData.getProperty("version", version);
                version = version.trim().replaceAll("[^0-9\\.]", "");
            }
        } catch (IOException e) {
            System.err.println(e);
        }
        return version;
    }
}

