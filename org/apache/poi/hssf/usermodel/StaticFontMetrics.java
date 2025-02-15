/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hssf.usermodel;

import java.awt.Font;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import org.apache.poi.hssf.usermodel.FontDetails;
import org.apache.poi.util.POILogFactory;
import org.apache.poi.util.POILogger;

final class StaticFontMetrics {
    private static final POILogger LOGGER = POILogFactory.getLogger(StaticFontMetrics.class);
    private static Properties fontMetricsProps;
    private static final Map<String, FontDetails> fontDetailsMap;

    private StaticFontMetrics() {
    }

    public static synchronized FontDetails getFontDetails(Font font) {
        FontDetails fontDetails;
        if (fontMetricsProps == null) {
            try {
                fontMetricsProps = StaticFontMetrics.loadMetrics();
            } catch (IOException e) {
                throw new RuntimeException("Could not load font metrics", e);
            }
        }
        String fontName = font.getName();
        String fontStyle = "";
        if (font.isPlain()) {
            fontStyle = fontStyle + "plain";
        }
        if (font.isBold()) {
            fontStyle = fontStyle + "bold";
        }
        if (font.isItalic()) {
            fontStyle = fontStyle + "italic";
        }
        String fontHeight = FontDetails.buildFontHeightProperty(fontName);
        String styleHeight = FontDetails.buildFontHeightProperty(fontName + "." + fontStyle);
        if (fontMetricsProps.get(fontHeight) == null && fontMetricsProps.get(styleHeight) != null) {
            fontName = fontName + "." + fontStyle;
        }
        if ((fontDetails = fontDetailsMap.get(fontName)) == null) {
            fontDetails = FontDetails.create(fontName, fontMetricsProps);
            fontDetailsMap.put(fontName, fontDetails);
        }
        return fontDetails;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private static Properties loadMetrics() throws IOException {
        File propFile = null;
        try {
            String propFileName = System.getProperty("font.metrics.filename");
            if (propFileName != null && !(propFile = new File(propFileName)).exists()) {
                LOGGER.log(5, "font_metrics.properties not found at path " + propFile.getAbsolutePath());
                propFile = null;
            }
        } catch (SecurityException e) {
            LOGGER.log(5, "Can't access font.metrics.filename system property", e);
        }
        try (InputStream metricsIn = null;){
            if (propFile != null) {
                metricsIn = new FileInputStream(propFile);
            } else {
                metricsIn = FontDetails.class.getResourceAsStream("/font_metrics.properties");
                if (metricsIn == null) {
                    String err = "font_metrics.properties not found in classpath";
                    throw new IOException(err);
                }
            }
            Properties props = new Properties();
            props.load(metricsIn);
            Properties properties = props;
            return properties;
        }
    }

    static {
        fontDetailsMap = new HashMap<String, FontDetails>();
    }
}

