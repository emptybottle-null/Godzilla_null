/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.kitfox.svg.xml;

import com.kitfox.svg.xml.ColorTable;
import com.kitfox.svg.xml.NumberWithUnits;
import com.kitfox.svg.xml.XMLParseUtil;
import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.io.File;
import java.io.Serializable;
import java.net.URI;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StyleAttribute
implements Serializable {
    public static final long serialVersionUID = 0L;
    static final Pattern patternUrl = Pattern.compile("\\s*url\\((.*)\\)\\s*");
    static final Matcher matchFpNumUnits = Pattern.compile("\\s*([-+]?((\\d*\\.\\d+)|(\\d+))([-+]?[eE]\\d+)?)\\s*(px|cm|mm|in|pc|pt|em|ex)\\s*").matcher("");
    String name;
    String stringValue;
    boolean colorCompatable = false;
    boolean urlCompatable = false;

    public StyleAttribute() {
        this(null, null);
    }

    public StyleAttribute(String name) {
        this.name = name;
        this.stringValue = null;
    }

    public StyleAttribute(String name, String stringValue) {
        this.name = name;
        this.stringValue = stringValue;
    }

    public String getName() {
        return this.name;
    }

    public StyleAttribute setName(String name) {
        this.name = name;
        return this;
    }

    public String getStringValue() {
        return this.stringValue;
    }

    public String[] getStringList() {
        return XMLParseUtil.parseStringList(this.stringValue);
    }

    public void setStringValue(String value) {
        this.stringValue = value;
    }

    public boolean getBooleanValue() {
        return this.stringValue.toLowerCase().equals("true");
    }

    public int getIntValue() {
        return XMLParseUtil.findInt(this.stringValue);
    }

    public int[] getIntList() {
        return XMLParseUtil.parseIntList(this.stringValue);
    }

    public double getDoubleValue() {
        return XMLParseUtil.findDouble(this.stringValue);
    }

    public double[] getDoubleList() {
        return XMLParseUtil.parseDoubleList(this.stringValue);
    }

    public float getFloatValue() {
        return XMLParseUtil.findFloat(this.stringValue);
    }

    public float[] getFloatList() {
        return XMLParseUtil.parseFloatList(this.stringValue);
    }

    public float getRatioValue() {
        return (float)XMLParseUtil.parseRatio(this.stringValue);
    }

    public String getUnits() {
        matchFpNumUnits.reset(this.stringValue);
        if (!matchFpNumUnits.matches()) {
            return null;
        }
        return matchFpNumUnits.group(6);
    }

    public NumberWithUnits getNumberWithUnits() {
        return XMLParseUtil.parseNumberWithUnits(this.stringValue);
    }

    public float getFloatValueWithUnits() {
        NumberWithUnits number = this.getNumberWithUnits();
        return StyleAttribute.convertUnitsToPixels(number.getUnits(), number.getValue());
    }

    public static float convertUnitsToPixels(int unitType, float value) {
        float pixPerInch;
        if (unitType == 0 || unitType == 9) {
            return value;
        }
        try {
            pixPerInch = Toolkit.getDefaultToolkit().getScreenResolution();
        } catch (HeadlessException ex) {
            pixPerInch = 72.0f;
        }
        float inchesPerCm = 0.3936f;
        switch (unitType) {
            case 4: {
                return value * pixPerInch;
            }
            case 2: {
                return value * 0.3936f * pixPerInch;
            }
            case 3: {
                return value * 0.1f * 0.3936f * pixPerInch;
            }
            case 7: {
                return value * 0.013888889f * pixPerInch;
            }
            case 8: {
                return value * 0.16666667f * pixPerInch;
            }
        }
        return value;
    }

    public Color getColorValue() {
        return ColorTable.parseColor(this.stringValue);
    }

    public String parseURLFn() {
        Matcher matchUrl = patternUrl.matcher(this.stringValue);
        if (!matchUrl.matches()) {
            return null;
        }
        return matchUrl.group(1);
    }

    public URL getURLValue(URL docRoot) {
        String fragment = this.parseURLFn();
        if (fragment == null) {
            return null;
        }
        try {
            return new URL(docRoot, fragment);
        } catch (Exception e) {
            Logger.getLogger("svgSalamandeLogger").log(Level.WARNING, null, e);
            return null;
        }
    }

    public URL getURLValue(URI docRoot) {
        String fragment = this.parseURLFn();
        if (fragment == null) {
            return null;
        }
        try {
            URI ref = docRoot.resolve(fragment);
            return ref.toURL();
        } catch (Exception e) {
            Logger.getLogger("svgSalamandeLogger").log(Level.WARNING, null, e);
            return null;
        }
    }

    public URI getURIValue() {
        return this.getURIValue(null);
    }

    public URI getURIValue(URI base) {
        try {
            String fragment = this.parseURLFn();
            if (fragment == null) {
                fragment = this.stringValue.replaceAll("\\s+", "");
            }
            if (fragment == null) {
                return null;
            }
            if (Pattern.matches("[a-zA-Z]:!\\\\.*", fragment)) {
                File file = new File(fragment);
                return file.toURI();
            }
            URI uriFrag = new URI(fragment);
            if (uriFrag.isAbsolute()) {
                return uriFrag;
            }
            if (base == null) {
                return uriFrag;
            }
            URI relBase = new URI(null, base.getSchemeSpecificPart(), null);
            URI relUri = relBase.isOpaque() ? new URI(null, base.getSchemeSpecificPart(), uriFrag.getFragment()) : relBase.resolve(uriFrag);
            return new URI(base.getScheme() + ":" + relUri);
        } catch (Exception e) {
            Logger.getLogger("svgSalamandeLogger").log(Level.WARNING, null, e);
            return null;
        }
    }

    public static void main(String[] args) {
        try {
            URI uri = new URI("jar:http://www.kitfox.com/jackal/jackal.jar!/res/doc/about.svg");
            uri = uri.resolve("#myFragment");
            System.err.println(uri.toString());
            uri = new URI("http://www.kitfox.com/jackal/jackal.html");
            uri = uri.resolve("#myFragment");
            System.err.println(uri.toString());
        } catch (Exception e) {
            Logger.getLogger("svgSalamandeLogger").log(Level.WARNING, null, e);
        }
    }
}

