/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.log4j.helpers;

import java.io.InputStream;
import java.io.InterruptedIOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.Properties;
import org.apache.log4j.Level;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.helpers.Loader;
import org.apache.log4j.helpers.LogLog;
import org.apache.log4j.spi.Configurator;
import org.apache.log4j.spi.LoggerRepository;

public class OptionConverter {
    static String DELIM_START = "${";
    static char DELIM_STOP = (char)125;
    static int DELIM_START_LEN = 2;
    static int DELIM_STOP_LEN = 1;

    private OptionConverter() {
    }

    public static String[] concatanateArrays(String[] l, String[] r) {
        int len = l.length + r.length;
        String[] a = new String[len];
        System.arraycopy(l, 0, a, 0, l.length);
        System.arraycopy(r, 0, a, l.length, r.length);
        return a;
    }

    public static String convertSpecialChars(String s) {
        int len = s.length();
        StringBuffer sbuf = new StringBuffer(len);
        int i = 0;
        while (i < len) {
            int c;
            if ((c = s.charAt(i++)) == 92) {
                if ((c = s.charAt(i++)) == 110) {
                    c = 10;
                } else if (c == 114) {
                    c = 13;
                } else if (c == 116) {
                    c = 9;
                } else if (c == 102) {
                    c = 12;
                } else if (c == 8) {
                    c = 8;
                } else if (c == 34) {
                    c = 34;
                } else if (c == 39) {
                    c = 39;
                } else if (c == 92) {
                    c = 92;
                }
            }
            sbuf.append((char)c);
        }
        return sbuf.toString();
    }

    public static String getSystemProperty(String key, String def) {
        try {
            return System.getProperty(key, def);
        } catch (Throwable e) {
            LogLog.debug("Was not allowed to read system property \"" + key + "\".");
            return def;
        }
    }

    public static Object instantiateByKey(Properties props, String key, Class superClass, Object defaultValue) {
        String className = OptionConverter.findAndSubst(key, props);
        if (className == null) {
            LogLog.error("Could not find value for key " + key);
            return defaultValue;
        }
        return OptionConverter.instantiateByClassName(className.trim(), superClass, defaultValue);
    }

    public static boolean toBoolean(String value, boolean dEfault) {
        if (value == null) {
            return dEfault;
        }
        String trimmedVal = value.trim();
        if ("true".equalsIgnoreCase(trimmedVal)) {
            return true;
        }
        if ("false".equalsIgnoreCase(trimmedVal)) {
            return false;
        }
        return dEfault;
    }

    public static int toInt(String value, int dEfault) {
        if (value != null) {
            String s = value.trim();
            try {
                return Integer.valueOf(s);
            } catch (NumberFormatException e) {
                LogLog.error("[" + s + "] is not in proper int form.");
                e.printStackTrace();
            }
        }
        return dEfault;
    }

    public static Level toLevel(String value, Level defaultValue) {
        if (value == null) {
            return defaultValue;
        }
        int hashIndex = (value = value.trim()).indexOf(35);
        if (hashIndex == -1) {
            if ("NULL".equalsIgnoreCase(value)) {
                return null;
            }
            return Level.toLevel(value, defaultValue);
        }
        Level result = defaultValue;
        String clazz = value.substring(hashIndex + 1);
        String levelName = value.substring(0, hashIndex);
        if ("NULL".equalsIgnoreCase(levelName)) {
            return null;
        }
        LogLog.debug("toLevel:class=[" + clazz + "]" + ":pri=[" + levelName + "]");
        try {
            Class customLevel = Loader.loadClass(clazz);
            Class[] paramTypes = new Class[]{String.class, Level.class};
            Method toLevelMethod = customLevel.getMethod("toLevel", paramTypes);
            Object[] params = new Object[]{levelName, defaultValue};
            Object o = toLevelMethod.invoke(null, params);
            result = (Level)o;
        } catch (ClassNotFoundException e) {
            LogLog.warn("custom level class [" + clazz + "] not found.");
        } catch (NoSuchMethodException e) {
            LogLog.warn("custom level class [" + clazz + "]" + " does not have a class function toLevel(String, Level)", e);
        } catch (InvocationTargetException e) {
            if (e.getTargetException() instanceof InterruptedException || e.getTargetException() instanceof InterruptedIOException) {
                Thread.currentThread().interrupt();
            }
            LogLog.warn("custom level class [" + clazz + "]" + " could not be instantiated", e);
        } catch (ClassCastException e) {
            LogLog.warn("class [" + clazz + "] is not a subclass of org.apache.log4j.Level", e);
        } catch (IllegalAccessException e) {
            LogLog.warn("class [" + clazz + "] cannot be instantiated due to access restrictions", e);
        } catch (RuntimeException e) {
            LogLog.warn("class [" + clazz + "], level [" + levelName + "] conversion failed.", e);
        }
        return result;
    }

    public static long toFileSize(String value, long dEfault) {
        if (value == null) {
            return dEfault;
        }
        String s = value.trim().toUpperCase();
        long multiplier = 1L;
        int index = s.indexOf("KB");
        if (index != -1) {
            multiplier = 1024L;
            s = s.substring(0, index);
        } else {
            index = s.indexOf("MB");
            if (index != -1) {
                multiplier = 0x100000L;
                s = s.substring(0, index);
            } else {
                index = s.indexOf("GB");
                if (index != -1) {
                    multiplier = 0x40000000L;
                    s = s.substring(0, index);
                }
            }
        }
        if (s != null) {
            try {
                return Long.valueOf(s) * multiplier;
            } catch (NumberFormatException e) {
                LogLog.error("[" + s + "] is not in proper int form.");
                LogLog.error("[" + value + "] not in expected format.", e);
            }
        }
        return dEfault;
    }

    public static String findAndSubst(String key, Properties props) {
        String value = props.getProperty(key);
        if (value == null) {
            return null;
        }
        try {
            return OptionConverter.substVars(value, props);
        } catch (IllegalArgumentException e) {
            LogLog.error("Bad option value [" + value + "].", e);
            return value;
        }
    }

    public static Object instantiateByClassName(String className, Class superClass, Object defaultValue) {
        if (className != null) {
            try {
                Class classObj = Loader.loadClass(className);
                if (!superClass.isAssignableFrom(classObj)) {
                    LogLog.error("A \"" + className + "\" object is not assignable to a \"" + superClass.getName() + "\" variable.");
                    LogLog.error("The class \"" + superClass.getName() + "\" was loaded by ");
                    LogLog.error("[" + superClass.getClassLoader() + "] whereas object of type ");
                    LogLog.error("\"" + classObj.getName() + "\" was loaded by [" + classObj.getClassLoader() + "].");
                    return defaultValue;
                }
                return classObj.newInstance();
            } catch (ClassNotFoundException e) {
                LogLog.error("Could not instantiate class [" + className + "].", e);
            } catch (IllegalAccessException e) {
                LogLog.error("Could not instantiate class [" + className + "].", e);
            } catch (InstantiationException e) {
                LogLog.error("Could not instantiate class [" + className + "].", e);
            } catch (RuntimeException e) {
                LogLog.error("Could not instantiate class [" + className + "].", e);
            }
        }
        return defaultValue;
    }

    public static String substVars(String val, Properties props) throws IllegalArgumentException {
        StringBuffer sbuf = new StringBuffer();
        int i = 0;
        while (true) {
            int j;
            if ((j = val.indexOf(DELIM_START, i)) == -1) {
                if (i == 0) {
                    return val;
                }
                sbuf.append(val.substring(i, val.length()));
                return sbuf.toString();
            }
            sbuf.append(val.substring(i, j));
            int k = val.indexOf(DELIM_STOP, j);
            if (k == -1) {
                throw new IllegalArgumentException('\"' + val + "\" has no closing brace. Opening brace at position " + j + '.');
            }
            String key = val.substring(j += DELIM_START_LEN, k);
            String replacement = OptionConverter.getSystemProperty(key, null);
            if (replacement == null && props != null) {
                replacement = props.getProperty(key);
            }
            if (replacement != null) {
                String recursiveReplacement = OptionConverter.substVars(replacement, props);
                sbuf.append(recursiveReplacement);
            }
            i = k + DELIM_STOP_LEN;
        }
    }

    public static void selectAndConfigure(InputStream inputStream, String clazz, LoggerRepository hierarchy) {
        Configurator configurator = null;
        if (clazz != null) {
            LogLog.debug("Preferred configurator class: " + clazz);
            configurator = (Configurator)OptionConverter.instantiateByClassName(clazz, Configurator.class, null);
            if (configurator == null) {
                LogLog.error("Could not instantiate configurator [" + clazz + "].");
                return;
            }
        } else {
            configurator = new PropertyConfigurator();
        }
        configurator.doConfigure(inputStream, hierarchy);
    }

    public static void selectAndConfigure(URL url, String clazz, LoggerRepository hierarchy) {
        Configurator configurator = null;
        String filename = url.getFile();
        if (clazz == null && filename != null && filename.endsWith(".xml")) {
            clazz = "org.apache.log4j.xml.DOMConfigurator";
        }
        if (clazz != null) {
            LogLog.debug("Preferred configurator class: " + clazz);
            configurator = (Configurator)OptionConverter.instantiateByClassName(clazz, Configurator.class, null);
            if (configurator == null) {
                LogLog.error("Could not instantiate configurator [" + clazz + "].");
                return;
            }
        } else {
            configurator = new PropertyConfigurator();
        }
        configurator.doConfigure(url, hierarchy);
    }
}

