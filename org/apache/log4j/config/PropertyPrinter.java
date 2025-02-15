/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.log4j.config;

import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Hashtable;
import org.apache.log4j.Appender;
import org.apache.log4j.Category;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.config.PropertyGetter;

public class PropertyPrinter
implements PropertyGetter.PropertyCallback {
    protected int numAppenders = 0;
    protected Hashtable appenderNames = new Hashtable();
    protected Hashtable layoutNames = new Hashtable();
    protected PrintWriter out;
    protected boolean doCapitalize;

    public PropertyPrinter(PrintWriter out) {
        this(out, false);
    }

    public PropertyPrinter(PrintWriter out, boolean doCapitalize) {
        this.out = out;
        this.doCapitalize = doCapitalize;
        this.print(out);
        out.flush();
    }

    protected String genAppName() {
        return "A" + this.numAppenders++;
    }

    protected boolean isGenAppName(String name) {
        if (name.length() < 2 || name.charAt(0) != 'A') {
            return false;
        }
        for (int i = 0; i < name.length(); ++i) {
            if (name.charAt(i) >= '0' && name.charAt(i) <= '9') continue;
            return false;
        }
        return true;
    }

    public void print(PrintWriter out) {
        this.printOptions(out, Logger.getRootLogger());
        Enumeration cats = LogManager.getCurrentLoggers();
        while (cats.hasMoreElements()) {
            this.printOptions(out, (Logger)cats.nextElement());
        }
    }

    protected void printOptions(PrintWriter out, Category cat) {
        String catKey;
        String appenderString;
        Enumeration appenders = cat.getAllAppenders();
        Level prio = cat.getLevel();
        String string = appenderString = prio == null ? "" : prio.toString();
        while (appenders.hasMoreElements()) {
            Appender app = (Appender)appenders.nextElement();
            String name = (String)this.appenderNames.get(app);
            if (name == null) {
                name = app.getName();
                if (name == null || this.isGenAppName(name)) {
                    name = this.genAppName();
                }
                this.appenderNames.put(app, name);
                this.printOptions(out, app, "log4j.appender." + name);
                if (app.getLayout() != null) {
                    this.printOptions(out, app.getLayout(), "log4j.appender." + name + ".layout");
                }
            }
            appenderString = appenderString + ", " + name;
        }
        String string2 = catKey = cat == Logger.getRootLogger() ? "log4j.rootLogger" : "log4j.logger." + cat.getName();
        if (appenderString != "") {
            out.println(catKey + "=" + appenderString);
        }
        if (!cat.getAdditivity() && cat != Logger.getRootLogger()) {
            out.println("log4j.additivity." + cat.getName() + "=false");
        }
    }

    protected void printOptions(PrintWriter out, Logger cat) {
        this.printOptions(out, (Category)cat);
    }

    protected void printOptions(PrintWriter out, Object obj, String fullname) {
        out.println(fullname + "=" + obj.getClass().getName());
        PropertyGetter.getProperties(obj, this, fullname + ".");
    }

    public void foundProperty(Object obj, String prefix, String name, Object value) {
        if (obj instanceof Appender && "name".equals(name)) {
            return;
        }
        if (this.doCapitalize) {
            name = PropertyPrinter.capitalize(name);
        }
        this.out.println(prefix + name + "=" + value.toString());
    }

    public static String capitalize(String name) {
        if (Character.isLowerCase(name.charAt(0)) && (name.length() == 1 || Character.isLowerCase(name.charAt(1)))) {
            StringBuffer newname = new StringBuffer(name);
            newname.setCharAt(0, Character.toUpperCase(name.charAt(0)));
            return newname.toString();
        }
        return name;
    }

    public static void main(String[] args) {
        new PropertyPrinter(new PrintWriter(System.out));
    }
}

