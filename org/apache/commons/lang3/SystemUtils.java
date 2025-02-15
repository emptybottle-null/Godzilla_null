/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.lang3;

import java.io.File;
import org.apache.commons.lang3.JavaVersion;
import org.apache.commons.lang3.StringUtils;

public class SystemUtils {
    private static final String OS_NAME_WINDOWS_PREFIX = "Windows";
    private static final String USER_HOME_KEY = "user.home";
    private static final String USER_NAME_KEY = "user.name";
    private static final String USER_DIR_KEY = "user.dir";
    private static final String JAVA_IO_TMPDIR_KEY = "java.io.tmpdir";
    private static final String JAVA_HOME_KEY = "java.home";
    public static final String AWT_TOOLKIT = SystemUtils.getSystemProperty("awt.toolkit");
    public static final String FILE_ENCODING = SystemUtils.getSystemProperty("file.encoding");
    @Deprecated
    public static final String FILE_SEPARATOR = SystemUtils.getSystemProperty("file.separator");
    public static final String JAVA_AWT_FONTS = SystemUtils.getSystemProperty("java.awt.fonts");
    public static final String JAVA_AWT_GRAPHICSENV = SystemUtils.getSystemProperty("java.awt.graphicsenv");
    public static final String JAVA_AWT_HEADLESS = SystemUtils.getSystemProperty("java.awt.headless");
    public static final String JAVA_AWT_PRINTERJOB = SystemUtils.getSystemProperty("java.awt.printerjob");
    public static final String JAVA_CLASS_PATH = SystemUtils.getSystemProperty("java.class.path");
    public static final String JAVA_CLASS_VERSION = SystemUtils.getSystemProperty("java.class.version");
    public static final String JAVA_COMPILER = SystemUtils.getSystemProperty("java.compiler");
    public static final String JAVA_ENDORSED_DIRS = SystemUtils.getSystemProperty("java.endorsed.dirs");
    public static final String JAVA_EXT_DIRS = SystemUtils.getSystemProperty("java.ext.dirs");
    public static final String JAVA_HOME = SystemUtils.getSystemProperty("java.home");
    public static final String JAVA_IO_TMPDIR = SystemUtils.getSystemProperty("java.io.tmpdir");
    public static final String JAVA_LIBRARY_PATH = SystemUtils.getSystemProperty("java.library.path");
    public static final String JAVA_RUNTIME_NAME = SystemUtils.getSystemProperty("java.runtime.name");
    public static final String JAVA_RUNTIME_VERSION = SystemUtils.getSystemProperty("java.runtime.version");
    public static final String JAVA_SPECIFICATION_NAME = SystemUtils.getSystemProperty("java.specification.name");
    public static final String JAVA_SPECIFICATION_VENDOR = SystemUtils.getSystemProperty("java.specification.vendor");
    public static final String JAVA_SPECIFICATION_VERSION = SystemUtils.getSystemProperty("java.specification.version");
    private static final JavaVersion JAVA_SPECIFICATION_VERSION_AS_ENUM = JavaVersion.get(JAVA_SPECIFICATION_VERSION);
    public static final String JAVA_UTIL_PREFS_PREFERENCES_FACTORY = SystemUtils.getSystemProperty("java.util.prefs.PreferencesFactory");
    public static final String JAVA_VENDOR = SystemUtils.getSystemProperty("java.vendor");
    public static final String JAVA_VENDOR_URL = SystemUtils.getSystemProperty("java.vendor.url");
    public static final String JAVA_VERSION = SystemUtils.getSystemProperty("java.version");
    public static final String JAVA_VM_INFO = SystemUtils.getSystemProperty("java.vm.info");
    public static final String JAVA_VM_NAME = SystemUtils.getSystemProperty("java.vm.name");
    public static final String JAVA_VM_SPECIFICATION_NAME = SystemUtils.getSystemProperty("java.vm.specification.name");
    public static final String JAVA_VM_SPECIFICATION_VENDOR = SystemUtils.getSystemProperty("java.vm.specification.vendor");
    public static final String JAVA_VM_SPECIFICATION_VERSION = SystemUtils.getSystemProperty("java.vm.specification.version");
    public static final String JAVA_VM_VENDOR = SystemUtils.getSystemProperty("java.vm.vendor");
    public static final String JAVA_VM_VERSION = SystemUtils.getSystemProperty("java.vm.version");
    @Deprecated
    public static final String LINE_SEPARATOR = SystemUtils.getSystemProperty("line.separator");
    public static final String OS_ARCH = SystemUtils.getSystemProperty("os.arch");
    public static final String OS_NAME = SystemUtils.getSystemProperty("os.name");
    public static final String OS_VERSION = SystemUtils.getSystemProperty("os.version");
    @Deprecated
    public static final String PATH_SEPARATOR = SystemUtils.getSystemProperty("path.separator");
    public static final String USER_COUNTRY = SystemUtils.getSystemProperty("user.country") == null ? SystemUtils.getSystemProperty("user.region") : SystemUtils.getSystemProperty("user.country");
    public static final String USER_DIR = SystemUtils.getSystemProperty("user.dir");
    public static final String USER_HOME = SystemUtils.getSystemProperty("user.home");
    public static final String USER_LANGUAGE = SystemUtils.getSystemProperty("user.language");
    public static final String USER_NAME = SystemUtils.getSystemProperty("user.name");
    public static final String USER_TIMEZONE = SystemUtils.getSystemProperty("user.timezone");
    public static final boolean IS_JAVA_1_1 = SystemUtils.getJavaVersionMatches("1.1");
    public static final boolean IS_JAVA_1_2 = SystemUtils.getJavaVersionMatches("1.2");
    public static final boolean IS_JAVA_1_3 = SystemUtils.getJavaVersionMatches("1.3");
    public static final boolean IS_JAVA_1_4 = SystemUtils.getJavaVersionMatches("1.4");
    public static final boolean IS_JAVA_1_5 = SystemUtils.getJavaVersionMatches("1.5");
    public static final boolean IS_JAVA_1_6 = SystemUtils.getJavaVersionMatches("1.6");
    public static final boolean IS_JAVA_1_7 = SystemUtils.getJavaVersionMatches("1.7");
    public static final boolean IS_JAVA_1_8 = SystemUtils.getJavaVersionMatches("1.8");
    @Deprecated
    public static final boolean IS_JAVA_1_9 = SystemUtils.getJavaVersionMatches("9");
    public static final boolean IS_JAVA_9 = SystemUtils.getJavaVersionMatches("9");
    public static final boolean IS_JAVA_10 = SystemUtils.getJavaVersionMatches("10");
    public static final boolean IS_JAVA_11 = SystemUtils.getJavaVersionMatches("11");
    public static final boolean IS_JAVA_12 = SystemUtils.getJavaVersionMatches("12");
    public static final boolean IS_JAVA_13 = SystemUtils.getJavaVersionMatches("13");
    public static final boolean IS_JAVA_14 = SystemUtils.getJavaVersionMatches("14");
    public static final boolean IS_JAVA_15 = SystemUtils.getJavaVersionMatches("15");
    public static final boolean IS_OS_AIX = SystemUtils.getOsMatchesName("AIX");
    public static final boolean IS_OS_HP_UX = SystemUtils.getOsMatchesName("HP-UX");
    public static final boolean IS_OS_400 = SystemUtils.getOsMatchesName("OS/400");
    public static final boolean IS_OS_IRIX = SystemUtils.getOsMatchesName("Irix");
    public static final boolean IS_OS_LINUX = SystemUtils.getOsMatchesName("Linux") || SystemUtils.getOsMatchesName("LINUX");
    public static final boolean IS_OS_MAC = SystemUtils.getOsMatchesName("Mac");
    public static final boolean IS_OS_MAC_OSX = SystemUtils.getOsMatchesName("Mac OS X");
    public static final boolean IS_OS_MAC_OSX_CHEETAH = SystemUtils.getOsMatches("Mac OS X", "10.0");
    public static final boolean IS_OS_MAC_OSX_PUMA = SystemUtils.getOsMatches("Mac OS X", "10.1");
    public static final boolean IS_OS_MAC_OSX_JAGUAR = SystemUtils.getOsMatches("Mac OS X", "10.2");
    public static final boolean IS_OS_MAC_OSX_PANTHER = SystemUtils.getOsMatches("Mac OS X", "10.3");
    public static final boolean IS_OS_MAC_OSX_TIGER = SystemUtils.getOsMatches("Mac OS X", "10.4");
    public static final boolean IS_OS_MAC_OSX_LEOPARD = SystemUtils.getOsMatches("Mac OS X", "10.5");
    public static final boolean IS_OS_MAC_OSX_SNOW_LEOPARD = SystemUtils.getOsMatches("Mac OS X", "10.6");
    public static final boolean IS_OS_MAC_OSX_LION = SystemUtils.getOsMatches("Mac OS X", "10.7");
    public static final boolean IS_OS_MAC_OSX_MOUNTAIN_LION = SystemUtils.getOsMatches("Mac OS X", "10.8");
    public static final boolean IS_OS_MAC_OSX_MAVERICKS = SystemUtils.getOsMatches("Mac OS X", "10.9");
    public static final boolean IS_OS_MAC_OSX_YOSEMITE = SystemUtils.getOsMatches("Mac OS X", "10.10");
    public static final boolean IS_OS_MAC_OSX_EL_CAPITAN = SystemUtils.getOsMatches("Mac OS X", "10.11");
    public static final boolean IS_OS_MAC_OSX_SIERRA = SystemUtils.getOsMatches("Mac OS X", "10.12");
    public static final boolean IS_OS_MAC_OSX_HIGH_SIERRA = SystemUtils.getOsMatches("Mac OS X", "10.13");
    public static final boolean IS_OS_MAC_OSX_MOJAVE = SystemUtils.getOsMatches("Mac OS X", "10.14");
    public static final boolean IS_OS_MAC_OSX_CATALINA = SystemUtils.getOsMatches("Mac OS X", "10.15");
    public static final boolean IS_OS_MAC_OSX_BIG_SUR = SystemUtils.getOsMatches("Mac OS X", "10.16");
    public static final boolean IS_OS_FREE_BSD = SystemUtils.getOsMatchesName("FreeBSD");
    public static final boolean IS_OS_OPEN_BSD = SystemUtils.getOsMatchesName("OpenBSD");
    public static final boolean IS_OS_NET_BSD = SystemUtils.getOsMatchesName("NetBSD");
    public static final boolean IS_OS_OS2 = SystemUtils.getOsMatchesName("OS/2");
    public static final boolean IS_OS_SOLARIS = SystemUtils.getOsMatchesName("Solaris");
    public static final boolean IS_OS_SUN_OS = SystemUtils.getOsMatchesName("SunOS");
    public static final boolean IS_OS_UNIX = IS_OS_AIX || IS_OS_HP_UX || IS_OS_IRIX || IS_OS_LINUX || IS_OS_MAC_OSX || IS_OS_SOLARIS || IS_OS_SUN_OS || IS_OS_FREE_BSD || IS_OS_OPEN_BSD || IS_OS_NET_BSD;
    public static final boolean IS_OS_WINDOWS = SystemUtils.getOsMatchesName("Windows");
    public static final boolean IS_OS_WINDOWS_2000 = SystemUtils.getOsMatchesName("Windows 2000");
    public static final boolean IS_OS_WINDOWS_2003 = SystemUtils.getOsMatchesName("Windows 2003");
    public static final boolean IS_OS_WINDOWS_2008 = SystemUtils.getOsMatchesName("Windows Server 2008");
    public static final boolean IS_OS_WINDOWS_2012 = SystemUtils.getOsMatchesName("Windows Server 2012");
    public static final boolean IS_OS_WINDOWS_95 = SystemUtils.getOsMatchesName("Windows 95");
    public static final boolean IS_OS_WINDOWS_98 = SystemUtils.getOsMatchesName("Windows 98");
    public static final boolean IS_OS_WINDOWS_ME = SystemUtils.getOsMatchesName("Windows Me");
    public static final boolean IS_OS_WINDOWS_NT = SystemUtils.getOsMatchesName("Windows NT");
    public static final boolean IS_OS_WINDOWS_XP = SystemUtils.getOsMatchesName("Windows XP");
    public static final boolean IS_OS_WINDOWS_VISTA = SystemUtils.getOsMatchesName("Windows Vista");
    public static final boolean IS_OS_WINDOWS_7 = SystemUtils.getOsMatchesName("Windows 7");
    public static final boolean IS_OS_WINDOWS_8 = SystemUtils.getOsMatchesName("Windows 8");
    public static final boolean IS_OS_WINDOWS_10 = SystemUtils.getOsMatchesName("Windows 10");
    public static final boolean IS_OS_ZOS = SystemUtils.getOsMatchesName("z/OS");

    public static String getEnvironmentVariable(String name, String defaultValue) {
        try {
            String value = System.getenv(name);
            return value == null ? defaultValue : value;
        } catch (SecurityException ex) {
            return defaultValue;
        }
    }

    public static String getHostName() {
        return IS_OS_WINDOWS ? System.getenv("COMPUTERNAME") : System.getenv("HOSTNAME");
    }

    public static File getJavaHome() {
        return new File(System.getProperty(JAVA_HOME_KEY));
    }

    public static File getJavaIoTmpDir() {
        return new File(System.getProperty(JAVA_IO_TMPDIR_KEY));
    }

    private static boolean getJavaVersionMatches(String versionPrefix) {
        return SystemUtils.isJavaVersionMatch(JAVA_SPECIFICATION_VERSION, versionPrefix);
    }

    private static boolean getOsMatches(String osNamePrefix, String osVersionPrefix) {
        return SystemUtils.isOSMatch(OS_NAME, OS_VERSION, osNamePrefix, osVersionPrefix);
    }

    private static boolean getOsMatchesName(String osNamePrefix) {
        return SystemUtils.isOSNameMatch(OS_NAME, osNamePrefix);
    }

    private static String getSystemProperty(String property) {
        try {
            return System.getProperty(property);
        } catch (SecurityException ex) {
            return null;
        }
    }

    public static File getUserDir() {
        return new File(System.getProperty(USER_DIR_KEY));
    }

    public static File getUserHome() {
        return new File(System.getProperty(USER_HOME_KEY));
    }

    public static String getUserName() {
        return System.getProperty(USER_NAME_KEY);
    }

    public static String getUserName(String defaultValue) {
        return System.getProperty(USER_NAME_KEY, defaultValue);
    }

    public static boolean isJavaAwtHeadless() {
        return Boolean.TRUE.toString().equals(JAVA_AWT_HEADLESS);
    }

    public static boolean isJavaVersionAtLeast(JavaVersion requiredVersion) {
        return JAVA_SPECIFICATION_VERSION_AS_ENUM.atLeast(requiredVersion);
    }

    public static boolean isJavaVersionAtMost(JavaVersion requiredVersion) {
        return JAVA_SPECIFICATION_VERSION_AS_ENUM.atMost(requiredVersion);
    }

    static boolean isJavaVersionMatch(String version, String versionPrefix) {
        if (version == null) {
            return false;
        }
        return version.startsWith(versionPrefix);
    }

    static boolean isOSMatch(String osName, String osVersion, String osNamePrefix, String osVersionPrefix) {
        if (osName == null || osVersion == null) {
            return false;
        }
        return SystemUtils.isOSNameMatch(osName, osNamePrefix) && SystemUtils.isOSVersionMatch(osVersion, osVersionPrefix);
    }

    static boolean isOSNameMatch(String osName, String osNamePrefix) {
        if (osName == null) {
            return false;
        }
        return osName.startsWith(osNamePrefix);
    }

    static boolean isOSVersionMatch(String osVersion, String osVersionPrefix) {
        if (StringUtils.isEmpty(osVersion)) {
            return false;
        }
        String[] versionPrefixParts = osVersionPrefix.split("\\.");
        String[] versionParts = osVersion.split("\\.");
        for (int i = 0; i < Math.min(versionPrefixParts.length, versionParts.length); ++i) {
            if (versionPrefixParts[i].equals(versionParts[i])) continue;
            return false;
        }
        return true;
    }
}

