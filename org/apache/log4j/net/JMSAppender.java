/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  javax.jms.JMSException
 *  javax.jms.Message
 *  javax.jms.ObjectMessage
 *  javax.jms.Topic
 *  javax.jms.TopicConnection
 *  javax.jms.TopicConnectionFactory
 *  javax.jms.TopicPublisher
 *  javax.jms.TopicSession
 */
package org.apache.log4j.net;

import java.io.Serializable;
import java.util.Properties;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicPublisher;
import javax.jms.TopicSession;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NameNotFoundException;
import javax.naming.NamingException;
import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.helpers.LogLog;
import org.apache.log4j.spi.LoggingEvent;

public class JMSAppender
extends AppenderSkeleton {
    String securityPrincipalName;
    String securityCredentials;
    String initialContextFactoryName;
    String urlPkgPrefixes;
    String providerURL;
    String topicBindingName;
    String tcfBindingName;
    String userName;
    String password;
    boolean locationInfo;
    TopicConnection topicConnection;
    TopicSession topicSession;
    TopicPublisher topicPublisher;

    public void setTopicConnectionFactoryBindingName(String tcfBindingName) {
        this.tcfBindingName = tcfBindingName;
    }

    public String getTopicConnectionFactoryBindingName() {
        return this.tcfBindingName;
    }

    public void setTopicBindingName(String topicBindingName) {
        this.topicBindingName = topicBindingName;
    }

    public String getTopicBindingName() {
        return this.topicBindingName;
    }

    public boolean getLocationInfo() {
        return this.locationInfo;
    }

    public void activateOptions() {
        try {
            InitialContext jndi;
            LogLog.debug("Getting initial context.");
            if (this.initialContextFactoryName != null) {
                Properties env = new Properties();
                env.put("java.naming.factory.initial", this.initialContextFactoryName);
                if (this.providerURL != null) {
                    env.put("java.naming.provider.url", this.providerURL);
                } else {
                    LogLog.warn("You have set InitialContextFactoryName option but not the ProviderURL. This is likely to cause problems.");
                }
                if (this.urlPkgPrefixes != null) {
                    env.put("java.naming.factory.url.pkgs", this.urlPkgPrefixes);
                }
                if (this.securityPrincipalName != null) {
                    env.put("java.naming.security.principal", this.securityPrincipalName);
                    if (this.securityCredentials != null) {
                        env.put("java.naming.security.credentials", this.securityCredentials);
                    } else {
                        LogLog.warn("You have set SecurityPrincipalName option but not the SecurityCredentials. This is likely to cause problems.");
                    }
                }
                jndi = new InitialContext(env);
            } else {
                jndi = new InitialContext();
            }
            LogLog.debug("Looking up [" + this.tcfBindingName + "]");
            TopicConnectionFactory topicConnectionFactory = (TopicConnectionFactory)this.lookup(jndi, this.tcfBindingName);
            LogLog.debug("About to create TopicConnection.");
            this.topicConnection = this.userName != null ? topicConnectionFactory.createTopicConnection(this.userName, this.password) : topicConnectionFactory.createTopicConnection();
            LogLog.debug("Creating TopicSession, non-transactional, in AUTO_ACKNOWLEDGE mode.");
            this.topicSession = this.topicConnection.createTopicSession(false, 1);
            LogLog.debug("Looking up topic name [" + this.topicBindingName + "].");
            Topic topic = (Topic)this.lookup(jndi, this.topicBindingName);
            LogLog.debug("Creating TopicPublisher.");
            this.topicPublisher = this.topicSession.createPublisher(topic);
            LogLog.debug("Starting TopicConnection.");
            this.topicConnection.start();
            jndi.close();
        } catch (JMSException e) {
            this.errorHandler.error("Error while activating options for appender named [" + this.name + "].", (Exception)((Object)e), 0);
        } catch (NamingException e) {
            this.errorHandler.error("Error while activating options for appender named [" + this.name + "].", e, 0);
        } catch (RuntimeException e) {
            this.errorHandler.error("Error while activating options for appender named [" + this.name + "].", e, 0);
        }
    }

    protected Object lookup(Context ctx, String name) throws NamingException {
        try {
            return ctx.lookup(name);
        } catch (NameNotFoundException e) {
            LogLog.error("Could not find name [" + name + "].");
            throw e;
        }
    }

    protected boolean checkEntryConditions() {
        String fail = null;
        if (this.topicConnection == null) {
            fail = "No TopicConnection";
        } else if (this.topicSession == null) {
            fail = "No TopicSession";
        } else if (this.topicPublisher == null) {
            fail = "No TopicPublisher";
        }
        if (fail != null) {
            this.errorHandler.error(fail + " for JMSAppender named [" + this.name + "].");
            return false;
        }
        return true;
    }

    public synchronized void close() {
        if (this.closed) {
            return;
        }
        LogLog.debug("Closing appender [" + this.name + "].");
        this.closed = true;
        try {
            if (this.topicSession != null) {
                this.topicSession.close();
            }
            if (this.topicConnection != null) {
                this.topicConnection.close();
            }
        } catch (JMSException e) {
            LogLog.error("Error while closing JMSAppender [" + this.name + "].", e);
        } catch (RuntimeException e) {
            LogLog.error("Error while closing JMSAppender [" + this.name + "].", e);
        }
        this.topicPublisher = null;
        this.topicSession = null;
        this.topicConnection = null;
    }

    public void append(LoggingEvent event) {
        if (!this.checkEntryConditions()) {
            return;
        }
        try {
            ObjectMessage msg = this.topicSession.createObjectMessage();
            if (this.locationInfo) {
                event.getLocationInformation();
            }
            msg.setObject((Serializable)event);
            this.topicPublisher.publish((Message)msg);
        } catch (JMSException e) {
            this.errorHandler.error("Could not publish message in JMSAppender [" + this.name + "].", (Exception)((Object)e), 0);
        } catch (RuntimeException e) {
            this.errorHandler.error("Could not publish message in JMSAppender [" + this.name + "].", e, 0);
        }
    }

    public String getInitialContextFactoryName() {
        return this.initialContextFactoryName;
    }

    public void setInitialContextFactoryName(String initialContextFactoryName) {
        this.initialContextFactoryName = initialContextFactoryName;
    }

    public String getProviderURL() {
        return this.providerURL;
    }

    public void setProviderURL(String providerURL) {
        this.providerURL = providerURL;
    }

    String getURLPkgPrefixes() {
        return this.urlPkgPrefixes;
    }

    public void setURLPkgPrefixes(String urlPkgPrefixes) {
        this.urlPkgPrefixes = urlPkgPrefixes;
    }

    public String getSecurityCredentials() {
        return this.securityCredentials;
    }

    public void setSecurityCredentials(String securityCredentials) {
        this.securityCredentials = securityCredentials;
    }

    public String getSecurityPrincipalName() {
        return this.securityPrincipalName;
    }

    public void setSecurityPrincipalName(String securityPrincipalName) {
        this.securityPrincipalName = securityPrincipalName;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLocationInfo(boolean locationInfo) {
        this.locationInfo = locationInfo;
    }

    protected TopicConnection getTopicConnection() {
        return this.topicConnection;
    }

    protected TopicSession getTopicSession() {
        return this.topicSession;
    }

    protected TopicPublisher getTopicPublisher() {
        return this.topicPublisher;
    }

    public boolean requiresLayout() {
        return false;
    }
}

