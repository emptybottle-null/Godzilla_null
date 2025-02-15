/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.cert.dane.fetcher;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import javax.naming.Binding;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import org.bouncycastle.cert.dane.DANEEntry;
import org.bouncycastle.cert.dane.DANEEntryFetcher;
import org.bouncycastle.cert.dane.DANEEntryFetcherFactory;
import org.bouncycastle.cert.dane.DANEException;

public class JndiDANEFetcherFactory
implements DANEEntryFetcherFactory {
    private static final String DANE_TYPE = "53";
    private List dnsServerList = new ArrayList();
    private boolean isAuthoritative;

    public JndiDANEFetcherFactory usingDNSServer(String string) {
        this.dnsServerList.add(string);
        return this;
    }

    public JndiDANEFetcherFactory setAuthoritative(boolean bl) {
        this.isAuthoritative = bl;
        return this;
    }

    public DANEEntryFetcher build(final String string) {
        final Hashtable<String, String> hashtable = new Hashtable<String, String>();
        hashtable.put("java.naming.factory.initial", "com.sun.jndi.dns.DnsContextFactory");
        hashtable.put("java.naming.authoritative", this.isAuthoritative ? "true" : "false");
        if (this.dnsServerList.size() > 0) {
            StringBuffer stringBuffer = new StringBuffer();
            Iterator iterator = this.dnsServerList.iterator();
            while (iterator.hasNext()) {
                if (stringBuffer.length() > 0) {
                    stringBuffer.append(" ");
                }
                stringBuffer.append("dns://" + iterator.next());
            }
            hashtable.put("java.naming.provider.url", stringBuffer.toString());
        }
        return new DANEEntryFetcher(){

            public List getEntries() throws DANEException {
                ArrayList arrayList = new ArrayList();
                try {
                    InitialDirContext initialDirContext = new InitialDirContext(hashtable);
                    if (string.indexOf("_smimecert.") > 0) {
                        Attributes attributes = initialDirContext.getAttributes(string, new String[]{JndiDANEFetcherFactory.DANE_TYPE});
                        Attribute attribute = attributes.get(JndiDANEFetcherFactory.DANE_TYPE);
                        if (attribute != null) {
                            JndiDANEFetcherFactory.this.addEntries(arrayList, string, attribute);
                        }
                    } else {
                        NamingEnumeration<Binding> namingEnumeration = initialDirContext.listBindings("_smimecert." + string);
                        while (namingEnumeration.hasMore()) {
                            Binding binding = namingEnumeration.next();
                            DirContext dirContext = (DirContext)binding.getObject();
                            String string4 = dirContext.getNameInNamespace().substring(1, dirContext.getNameInNamespace().length() - 1);
                            Attributes attributes = initialDirContext.getAttributes(string4, new String[]{JndiDANEFetcherFactory.DANE_TYPE});
                            Attribute attribute = attributes.get(JndiDANEFetcherFactory.DANE_TYPE);
                            if (attribute == null) continue;
                            String string2 = dirContext.getNameInNamespace();
                            String string3 = string2.substring(1, string2.length() - 1);
                            JndiDANEFetcherFactory.this.addEntries(arrayList, string3, attribute);
                        }
                    }
                    return arrayList;
                } catch (NamingException namingException) {
                    throw new DANEException("Exception dealing with DNS: " + namingException.getMessage(), namingException);
                }
            }
        };
    }

    private void addEntries(List list, String string, Attribute attribute) throws NamingException, DANEException {
        for (int i = 0; i != attribute.size(); ++i) {
            byte[] byArray = (byte[])attribute.get(i);
            if (!DANEEntry.isValidCertificate(byArray)) continue;
            try {
                list.add(new DANEEntry(string, byArray));
                continue;
            } catch (IOException iOException) {
                throw new DANEException("Exception parsing entry: " + iOException.getMessage(), iOException);
            }
        }
    }
}

