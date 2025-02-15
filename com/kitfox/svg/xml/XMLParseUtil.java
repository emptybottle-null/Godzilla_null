/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.kitfox.svg.xml;

import com.kitfox.svg.xml.NumberWithUnits;
import com.kitfox.svg.xml.ReadableXMLElement;
import com.kitfox.svg.xml.StyleAttribute;
import java.awt.Toolkit;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

public class XMLParseUtil {
    static final Matcher fpMatch = Pattern.compile("([-+]?((\\d*\\.\\d+)|(\\d+))([eE][+-]?\\d+)?)(\\%|in|cm|mm|pt|pc|px|em|ex)?").matcher("");
    static final Matcher intMatch = Pattern.compile("[-+]?\\d+").matcher("");
    static final Matcher quoteMatch = Pattern.compile("^'|'$").matcher("");

    private XMLParseUtil() {
    }

    public static String getTagText(Element ele) {
        int i;
        NodeList nl = ele.getChildNodes();
        int size = nl.getLength();
        Node node = null;
        for (i = 0; i < size && !((node = nl.item(i)) instanceof Text); ++i) {
        }
        if (i == size || node == null) {
            return null;
        }
        return ((Text)node).getData();
    }

    public static Element getFirstChild(Element root, String name) {
        NodeList nl = root.getChildNodes();
        int size = nl.getLength();
        for (int i = 0; i < size; ++i) {
            Element ele;
            Node node = nl.item(i);
            if (!(node instanceof Element) || !(ele = (Element)node).getTagName().equals(name)) continue;
            return ele;
        }
        return null;
    }

    public static String[] parseStringList(String list) {
        Matcher matchWs = Pattern.compile("[^\\s]+").matcher("");
        matchWs.reset(list);
        LinkedList<String> matchList = new LinkedList<String>();
        while (matchWs.find()) {
            matchList.add(matchWs.group());
        }
        String[] retArr = new String[matchList.size()];
        return matchList.toArray(retArr);
    }

    public static boolean isDouble(String val) {
        fpMatch.reset(val);
        return fpMatch.matches();
    }

    public static double parseDouble(String val) {
        return XMLParseUtil.findDouble(val);
    }

    public static synchronized double findDouble(String val) {
        if (val == null) {
            return 0.0;
        }
        fpMatch.reset(val);
        try {
            if (!fpMatch.find()) {
                return 0.0;
            }
        } catch (StringIndexOutOfBoundsException e) {
            Logger.getLogger("svgSalamandeLogger").log(Level.WARNING, "XMLParseUtil: regex parse problem: '" + val + "'", e);
        }
        val = fpMatch.group(1);
        double retVal = 0.0;
        try {
            float pixPerInch;
            retVal = Double.parseDouble(val);
            try {
                pixPerInch = Toolkit.getDefaultToolkit().getScreenResolution();
            } catch (NoClassDefFoundError err) {
                pixPerInch = 72.0f;
            }
            float inchesPerCm = 0.3936f;
            String units = fpMatch.group(6);
            if ("%".equals(units)) {
                retVal /= 100.0;
            } else if ("in".equals(units)) {
                retVal *= (double)pixPerInch;
            } else if ("cm".equals(units)) {
                retVal *= (double)(0.3936f * pixPerInch);
            } else if ("mm".equals(units)) {
                retVal *= (double)(0.3936f * pixPerInch * 0.1f);
            } else if ("pt".equals(units)) {
                retVal *= (double)(0.013888889f * pixPerInch);
            } else if ("pc".equals(units)) {
                retVal *= (double)(0.16666667f * pixPerInch);
            }
        } catch (Exception exception) {
            // empty catch block
        }
        return retVal;
    }

    public static synchronized double[] parseDoubleList(String list) {
        if (list == null) {
            return null;
        }
        fpMatch.reset(list);
        LinkedList<Double> doubList = new LinkedList<Double>();
        while (fpMatch.find()) {
            String val = fpMatch.group(1);
            doubList.add(Double.valueOf(val));
        }
        double[] retArr = new double[doubList.size()];
        Iterator it = doubList.iterator();
        int idx = 0;
        while (it.hasNext()) {
            retArr[idx++] = (Double)it.next();
        }
        return retArr;
    }

    public static float parseFloat(String val) {
        return XMLParseUtil.findFloat(val);
    }

    public static synchronized float findFloat(String val) {
        if (val == null) {
            return 0.0f;
        }
        fpMatch.reset(val);
        if (!fpMatch.find()) {
            return 0.0f;
        }
        val = fpMatch.group(1);
        float retVal = 0.0f;
        try {
            retVal = Float.parseFloat(val);
            String units = fpMatch.group(6);
            if ("%".equals(units)) {
                retVal /= 100.0f;
            }
        } catch (Exception exception) {
            // empty catch block
        }
        return retVal;
    }

    public static synchronized float[] parseFloatList(String list) {
        if (list == null) {
            return null;
        }
        fpMatch.reset(list);
        LinkedList<Float> floatList = new LinkedList<Float>();
        while (fpMatch.find()) {
            String val = fpMatch.group(1);
            floatList.add(Float.valueOf(val));
        }
        float[] retArr = new float[floatList.size()];
        Iterator it = floatList.iterator();
        int idx = 0;
        while (it.hasNext()) {
            retArr[idx++] = ((Float)it.next()).floatValue();
        }
        return retArr;
    }

    public static int parseInt(String val) {
        if (val == null) {
            return 0;
        }
        int retVal = 0;
        try {
            retVal = Integer.parseInt(val);
        } catch (Exception exception) {
            // empty catch block
        }
        return retVal;
    }

    public static int findInt(String val) {
        if (val == null) {
            return 0;
        }
        intMatch.reset(val);
        if (!intMatch.find()) {
            return 0;
        }
        val = intMatch.group();
        int retVal = 0;
        try {
            retVal = Integer.parseInt(val);
        } catch (Exception exception) {
            // empty catch block
        }
        return retVal;
    }

    public static int[] parseIntList(String list) {
        if (list == null) {
            return null;
        }
        intMatch.reset(list);
        LinkedList<Integer> intList = new LinkedList<Integer>();
        while (intMatch.find()) {
            String val = intMatch.group();
            intList.add(Integer.valueOf(val));
        }
        int[] retArr = new int[intList.size()];
        Iterator it = intList.iterator();
        int idx = 0;
        while (it.hasNext()) {
            retArr[idx++] = (Integer)it.next();
        }
        return retArr;
    }

    public static double parseRatio(String val) {
        if (val == null || val.equals("")) {
            return 0.0;
        }
        if (val.charAt(val.length() - 1) == '%') {
            XMLParseUtil.parseDouble(val.substring(0, val.length() - 1));
        }
        return XMLParseUtil.parseDouble(val);
    }

    public static NumberWithUnits parseNumberWithUnits(String val) {
        if (val == null) {
            return null;
        }
        return new NumberWithUnits(val);
    }

    public static String getAttribString(Element ele, String name) {
        return ele.getAttribute(name);
    }

    public static int getAttribInt(Element ele, String name) {
        String sval = ele.getAttribute(name);
        int val = 0;
        try {
            val = Integer.parseInt(sval);
        } catch (Exception exception) {
            // empty catch block
        }
        return val;
    }

    public static int getAttribIntHex(Element ele, String name) {
        String sval = ele.getAttribute(name);
        int val = 0;
        try {
            val = Integer.parseInt(sval, 16);
        } catch (Exception exception) {
            // empty catch block
        }
        return val;
    }

    public static float getAttribFloat(Element ele, String name) {
        String sval = ele.getAttribute(name);
        float val = 0.0f;
        try {
            val = Float.parseFloat(sval);
        } catch (Exception exception) {
            // empty catch block
        }
        return val;
    }

    public static double getAttribDouble(Element ele, String name) {
        String sval = ele.getAttribute(name);
        double val = 0.0;
        try {
            val = Double.parseDouble(sval);
        } catch (Exception exception) {
            // empty catch block
        }
        return val;
    }

    public static boolean getAttribBoolean(Element ele, String name) {
        String sval = ele.getAttribute(name);
        return sval.toLowerCase().equals("true");
    }

    public static URL getAttribURL(Element ele, String name, URL docRoot) {
        String sval = ele.getAttribute(name);
        try {
            return new URL(docRoot, sval);
        } catch (Exception e) {
            return null;
        }
    }

    public static ReadableXMLElement getElement(Class<?> classType, Element root, String name, URL docRoot) {
        if (root == null) {
            return null;
        }
        if (!ReadableXMLElement.class.isAssignableFrom(classType)) {
            return null;
        }
        NodeList nl = root.getChildNodes();
        int size = nl.getLength();
        for (int i = 0; i < size; ++i) {
            Element ele;
            Node node = nl.item(i);
            if (!(node instanceof Element) || !(ele = (Element)node).getTagName().equals(name)) continue;
            ReadableXMLElement newObj = null;
            try {
                newObj = (ReadableXMLElement)classType.newInstance();
            } catch (Exception e) {
                Logger.getLogger("svgSalamandeLogger").log(Level.WARNING, null, e);
                continue;
            }
            newObj.read(ele, docRoot);
            if (newObj == null) continue;
            return newObj;
        }
        return null;
    }

    public static HashMap<String, ReadableXMLElement> getElementHashMap(Class<?> classType, Element root, String name, String key, URL docRoot) {
        if (root == null) {
            return null;
        }
        if (!ReadableXMLElement.class.isAssignableFrom(classType)) {
            return null;
        }
        HashMap<String, ReadableXMLElement> retMap = new HashMap<String, ReadableXMLElement>();
        NodeList nl = root.getChildNodes();
        int size = nl.getLength();
        for (int i = 0; i < size; ++i) {
            Element ele;
            Node node = nl.item(i);
            if (!(node instanceof Element) || !(ele = (Element)node).getTagName().equals(name)) continue;
            ReadableXMLElement newObj = null;
            try {
                newObj = (ReadableXMLElement)classType.newInstance();
            } catch (Exception e) {
                Logger.getLogger("svgSalamandeLogger").log(Level.WARNING, null, e);
                continue;
            }
            newObj.read(ele, docRoot);
            if (newObj == null) continue;
            String keyVal = XMLParseUtil.getAttribString(ele, key);
            retMap.put(keyVal, newObj);
        }
        return retMap;
    }

    public static HashSet<ReadableXMLElement> getElementHashSet(Class<?> classType, Element root, String name, URL docRoot) {
        if (root == null) {
            return null;
        }
        if (!ReadableXMLElement.class.isAssignableFrom(classType)) {
            return null;
        }
        HashSet<ReadableXMLElement> retSet = new HashSet<ReadableXMLElement>();
        NodeList nl = root.getChildNodes();
        int size = nl.getLength();
        for (int i = 0; i < size; ++i) {
            Element ele;
            Node node = nl.item(i);
            if (!(node instanceof Element) || !(ele = (Element)node).getTagName().equals(name)) continue;
            ReadableXMLElement newObj = null;
            try {
                newObj = (ReadableXMLElement)classType.newInstance();
            } catch (Exception e) {
                Logger.getLogger("svgSalamandeLogger").log(Level.WARNING, null, e);
                continue;
            }
            newObj.read(ele, docRoot);
            if (newObj == null) continue;
            retSet.add(newObj);
        }
        return retSet;
    }

    public static LinkedList<ReadableXMLElement> getElementLinkedList(Class<?> classType, Element root, String name, URL docRoot) {
        if (root == null) {
            return null;
        }
        if (!ReadableXMLElement.class.isAssignableFrom(classType)) {
            return null;
        }
        NodeList nl = root.getChildNodes();
        LinkedList<ReadableXMLElement> elementCache = new LinkedList<ReadableXMLElement>();
        int size = nl.getLength();
        for (int i = 0; i < size; ++i) {
            Element ele;
            Node node = nl.item(i);
            if (!(node instanceof Element) || !(ele = (Element)node).getTagName().equals(name)) continue;
            ReadableXMLElement newObj = null;
            try {
                newObj = (ReadableXMLElement)classType.newInstance();
            } catch (Exception e) {
                Logger.getLogger("svgSalamandeLogger").log(Level.WARNING, null, e);
                continue;
            }
            newObj.read(ele, docRoot);
            elementCache.addLast(newObj);
        }
        return elementCache;
    }

    public static Object[] getElementArray(Class<?> classType, Element root, String name, URL docRoot) {
        if (root == null) {
            return null;
        }
        if (!ReadableXMLElement.class.isAssignableFrom(classType)) {
            return null;
        }
        LinkedList<ReadableXMLElement> elementCache = XMLParseUtil.getElementLinkedList(classType, root, name, docRoot);
        Object[] retArr = (Object[])Array.newInstance(classType, elementCache.size());
        return elementCache.toArray(retArr);
    }

    public static int[] getElementArrayInt(Element root, String name, String attrib) {
        if (root == null) {
            return null;
        }
        NodeList nl = root.getChildNodes();
        LinkedList<Integer> elementCache = new LinkedList<Integer>();
        int size = nl.getLength();
        for (int i = 0; i < size; ++i) {
            Element ele;
            Node node = nl.item(i);
            if (!(node instanceof Element) || !(ele = (Element)node).getTagName().equals(name)) continue;
            String valS = ele.getAttribute(attrib);
            int eleVal = 0;
            try {
                eleVal = Integer.parseInt(valS);
            } catch (Exception exception) {
                // empty catch block
            }
            elementCache.addLast(new Integer(eleVal));
        }
        int[] retArr = new int[elementCache.size()];
        Iterator it = elementCache.iterator();
        int idx = 0;
        while (it.hasNext()) {
            retArr[idx++] = (Integer)it.next();
        }
        return retArr;
    }

    public static String[] getElementArrayString(Element root, String name, String attrib) {
        if (root == null) {
            return null;
        }
        NodeList nl = root.getChildNodes();
        LinkedList<String> elementCache = new LinkedList<String>();
        int size = nl.getLength();
        for (int i = 0; i < size; ++i) {
            Element ele;
            Node node = nl.item(i);
            if (!(node instanceof Element) || !(ele = (Element)node).getTagName().equals(name)) continue;
            String valS = ele.getAttribute(attrib);
            elementCache.addLast(valS);
        }
        String[] retArr = new String[elementCache.size()];
        Iterator it = elementCache.iterator();
        int idx = 0;
        while (it.hasNext()) {
            retArr[idx++] = (String)it.next();
        }
        return retArr;
    }

    public static HashMap<String, StyleAttribute> parseStyle(String styleString) {
        return XMLParseUtil.parseStyle(styleString, new HashMap<String, StyleAttribute>());
    }

    public static HashMap<String, StyleAttribute> parseStyle(String styleString, HashMap<String, StyleAttribute> map) {
        Pattern patSemi = Pattern.compile(";");
        String[] styles = patSemi.split(styleString);
        for (int i = 0; i < styles.length; ++i) {
            int colon;
            if (styles[i].length() == 0 || (colon = styles[i].indexOf(58)) == -1) continue;
            String key = styles[i].substring(0, colon).trim().intern();
            String value = quoteMatch.reset(styles[i].substring(colon + 1).trim()).replaceAll("").intern();
            map.put(key, new StyleAttribute(key, value));
        }
        return map;
    }
}

