/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.config;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.UserType;
import org.apache.xmlbeans.impl.jam.JamClassLoader;
import org.apache.xmlbeans.impl.xb.xmlconfig.Usertypeconfig;

public class UserTypeImpl
implements UserType {
    private QName _name;
    private String _javaName;
    private String _staticHandler;

    static UserTypeImpl newInstance(JamClassLoader loader, Usertypeconfig cfgXO) {
        UserTypeImpl result = new UserTypeImpl();
        result._name = cfgXO.getName();
        result._javaName = cfgXO.getJavaname();
        result._staticHandler = cfgXO.getStaticHandler();
        return result;
    }

    @Override
    public String getJavaName() {
        return this._javaName;
    }

    @Override
    public QName getName() {
        return this._name;
    }

    @Override
    public String getStaticHandler() {
        return this._staticHandler;
    }
}

