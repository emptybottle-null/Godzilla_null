/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  javax.mail.Address
 *  javax.mail.Authenticator
 *  javax.mail.BodyPart
 *  javax.mail.Message
 *  javax.mail.Message$RecipientType
 *  javax.mail.MessagingException
 *  javax.mail.Multipart
 *  javax.mail.PasswordAuthentication
 *  javax.mail.Session
 *  javax.mail.Transport
 *  javax.mail.internet.AddressException
 *  javax.mail.internet.InternetAddress
 *  javax.mail.internet.InternetHeaders
 *  javax.mail.internet.MimeBodyPart
 *  javax.mail.internet.MimeMessage
 *  javax.mail.internet.MimeMultipart
 *  javax.mail.internet.MimeUtility
 */
package org.apache.log4j.net;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.Date;
import java.util.Properties;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.InternetHeaders;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;
import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.Layout;
import org.apache.log4j.helpers.CyclicBuffer;
import org.apache.log4j.helpers.LogLog;
import org.apache.log4j.helpers.OptionConverter;
import org.apache.log4j.net.DefaultEvaluator;
import org.apache.log4j.spi.LoggingEvent;
import org.apache.log4j.spi.OptionHandler;
import org.apache.log4j.spi.TriggeringEventEvaluator;
import org.apache.log4j.xml.DOMConfigurator;
import org.apache.log4j.xml.UnrecognizedElementHandler;
import org.w3c.dom.Element;

public class SMTPAppender
extends AppenderSkeleton
implements UnrecognizedElementHandler {
    private String to;
    private String cc;
    private String bcc;
    private String from;
    private String replyTo;
    private String subject;
    private String smtpHost;
    private String smtpUsername;
    private String smtpPassword;
    private String smtpProtocol;
    private int smtpPort = -1;
    private boolean smtpDebug = false;
    private int bufferSize = 512;
    private boolean locationInfo = false;
    private boolean sendOnClose = false;
    protected CyclicBuffer cb = new CyclicBuffer(this.bufferSize);
    protected Message msg;
    protected TriggeringEventEvaluator evaluator;

    public SMTPAppender() {
        this(new DefaultEvaluator());
    }

    public SMTPAppender(TriggeringEventEvaluator evaluator) {
        this.evaluator = evaluator;
    }

    public void activateOptions() {
        Session session = this.createSession();
        this.msg = new MimeMessage(session);
        try {
            this.addressMessage(this.msg);
            if (this.subject != null) {
                try {
                    this.msg.setSubject(MimeUtility.encodeText((String)this.subject, (String)"UTF-8", null));
                } catch (UnsupportedEncodingException ex) {
                    LogLog.error("Unable to encode SMTP subject", ex);
                }
            }
        } catch (MessagingException e) {
            LogLog.error("Could not activate SMTPAppender options.", e);
        }
        if (this.evaluator instanceof OptionHandler) {
            ((OptionHandler)((Object)this.evaluator)).activateOptions();
        }
    }

    protected void addressMessage(Message msg) throws MessagingException {
        if (this.from != null) {
            msg.setFrom((Address)this.getAddress(this.from));
        } else {
            msg.setFrom();
        }
        if (this.replyTo != null && this.replyTo.length() > 0) {
            msg.setReplyTo((Address[])this.parseAddress(this.replyTo));
        }
        if (this.to != null && this.to.length() > 0) {
            msg.setRecipients(Message.RecipientType.TO, (Address[])this.parseAddress(this.to));
        }
        if (this.cc != null && this.cc.length() > 0) {
            msg.setRecipients(Message.RecipientType.CC, (Address[])this.parseAddress(this.cc));
        }
        if (this.bcc != null && this.bcc.length() > 0) {
            msg.setRecipients(Message.RecipientType.BCC, (Address[])this.parseAddress(this.bcc));
        }
    }

    protected Session createSession() {
        Properties props = null;
        try {
            props = new Properties(System.getProperties());
        } catch (SecurityException ex) {
            props = new Properties();
        }
        String prefix = "mail.smtp";
        if (this.smtpProtocol != null) {
            props.put("mail.transport.protocol", this.smtpProtocol);
            prefix = "mail." + this.smtpProtocol;
        }
        if (this.smtpHost != null) {
            props.put(prefix + ".host", this.smtpHost);
        }
        if (this.smtpPort > 0) {
            props.put(prefix + ".port", String.valueOf(this.smtpPort));
        }
        Authenticator auth = null;
        if (this.smtpPassword != null && this.smtpUsername != null) {
            props.put(prefix + ".auth", "true");
            auth = new Authenticator(){

                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(SMTPAppender.this.smtpUsername, SMTPAppender.this.smtpPassword);
                }
            };
        }
        Session session = Session.getInstance((Properties)props, auth);
        if (this.smtpProtocol != null) {
            session.setProtocolForAddress("rfc822", this.smtpProtocol);
        }
        if (this.smtpDebug) {
            session.setDebug(this.smtpDebug);
        }
        return session;
    }

    public void append(LoggingEvent event) {
        if (!this.checkEntryConditions()) {
            return;
        }
        event.getThreadName();
        event.getNDC();
        event.getMDCCopy();
        if (this.locationInfo) {
            event.getLocationInformation();
        }
        event.getRenderedMessage();
        event.getThrowableStrRep();
        this.cb.add(event);
        if (this.evaluator.isTriggeringEvent(event)) {
            this.sendBuffer();
        }
    }

    protected boolean checkEntryConditions() {
        if (this.msg == null) {
            this.errorHandler.error("Message object not configured.");
            return false;
        }
        if (this.evaluator == null) {
            this.errorHandler.error("No TriggeringEventEvaluator is set for appender [" + this.name + "].");
            return false;
        }
        if (this.layout == null) {
            this.errorHandler.error("No layout set for appender named [" + this.name + "].");
            return false;
        }
        return true;
    }

    public synchronized void close() {
        this.closed = true;
        if (this.sendOnClose && this.cb.length() > 0) {
            this.sendBuffer();
        }
    }

    InternetAddress getAddress(String addressStr) {
        try {
            return new InternetAddress(addressStr);
        } catch (AddressException e) {
            this.errorHandler.error("Could not parse address [" + addressStr + "].", (Exception)((Object)e), 6);
            return null;
        }
    }

    InternetAddress[] parseAddress(String addressStr) {
        try {
            return InternetAddress.parse((String)addressStr, (boolean)true);
        } catch (AddressException e) {
            this.errorHandler.error("Could not parse address [" + addressStr + "].", (Exception)((Object)e), 6);
            return null;
        }
    }

    public String getTo() {
        return this.to;
    }

    public boolean requiresLayout() {
        return true;
    }

    protected String formatBody() {
        StringBuffer sbuf = new StringBuffer();
        String t = this.layout.getHeader();
        if (t != null) {
            sbuf.append(t);
        }
        int len = this.cb.length();
        for (int i = 0; i < len; ++i) {
            String[] s;
            LoggingEvent event = this.cb.get();
            sbuf.append(this.layout.format(event));
            if (!this.layout.ignoresThrowable() || (s = event.getThrowableStrRep()) == null) continue;
            for (int j = 0; j < s.length; ++j) {
                sbuf.append(s[j]);
                sbuf.append(Layout.LINE_SEP);
            }
        }
        t = this.layout.getFooter();
        if (t != null) {
            sbuf.append(t);
        }
        return sbuf.toString();
    }

    protected void sendBuffer() {
        try {
            MimeBodyPart part;
            String s = this.formatBody();
            boolean allAscii = true;
            for (int i = 0; i < s.length() && allAscii; ++i) {
                allAscii = s.charAt(i) <= '\u007f';
            }
            if (allAscii) {
                part = new MimeBodyPart();
                part.setContent((Object)s, this.layout.getContentType());
            } else {
                try {
                    ByteArrayOutputStream os = new ByteArrayOutputStream();
                    OutputStreamWriter writer = new OutputStreamWriter(MimeUtility.encode((OutputStream)os, (String)"quoted-printable"), "UTF-8");
                    writer.write(s);
                    ((Writer)writer).close();
                    InternetHeaders headers = new InternetHeaders();
                    headers.setHeader("Content-Type", this.layout.getContentType() + "; charset=UTF-8");
                    headers.setHeader("Content-Transfer-Encoding", "quoted-printable");
                    part = new MimeBodyPart(headers, os.toByteArray());
                } catch (Exception ex) {
                    StringBuffer sbuf = new StringBuffer(s);
                    for (int i = 0; i < sbuf.length(); ++i) {
                        if (sbuf.charAt(i) < '\u0080') continue;
                        sbuf.setCharAt(i, '?');
                    }
                    part = new MimeBodyPart();
                    part.setContent((Object)sbuf.toString(), this.layout.getContentType());
                }
            }
            MimeMultipart mp = new MimeMultipart();
            mp.addBodyPart((BodyPart)part);
            this.msg.setContent((Multipart)mp);
            this.msg.setSentDate(new Date());
            Transport.send((Message)this.msg);
        } catch (MessagingException e) {
            LogLog.error("Error occured while sending e-mail notification.", e);
        } catch (RuntimeException e) {
            LogLog.error("Error occured while sending e-mail notification.", e);
        }
    }

    public String getEvaluatorClass() {
        return this.evaluator == null ? null : this.evaluator.getClass().getName();
    }

    public String getFrom() {
        return this.from;
    }

    public String getReplyTo() {
        return this.replyTo;
    }

    public String getSubject() {
        return this.subject;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public void setReplyTo(String addresses) {
        this.replyTo = addresses;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setBufferSize(int bufferSize) {
        this.bufferSize = bufferSize;
        this.cb.resize(bufferSize);
    }

    public void setSMTPHost(String smtpHost) {
        this.smtpHost = smtpHost;
    }

    public String getSMTPHost() {
        return this.smtpHost;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public int getBufferSize() {
        return this.bufferSize;
    }

    public void setEvaluatorClass(String value) {
        this.evaluator = (TriggeringEventEvaluator)OptionConverter.instantiateByClassName(value, TriggeringEventEvaluator.class, this.evaluator);
    }

    public void setLocationInfo(boolean locationInfo) {
        this.locationInfo = locationInfo;
    }

    public boolean getLocationInfo() {
        return this.locationInfo;
    }

    public void setCc(String addresses) {
        this.cc = addresses;
    }

    public String getCc() {
        return this.cc;
    }

    public void setBcc(String addresses) {
        this.bcc = addresses;
    }

    public String getBcc() {
        return this.bcc;
    }

    public void setSMTPPassword(String password) {
        this.smtpPassword = password;
    }

    public void setSMTPUsername(String username) {
        this.smtpUsername = username;
    }

    public void setSMTPDebug(boolean debug) {
        this.smtpDebug = debug;
    }

    public String getSMTPPassword() {
        return this.smtpPassword;
    }

    public String getSMTPUsername() {
        return this.smtpUsername;
    }

    public boolean getSMTPDebug() {
        return this.smtpDebug;
    }

    public final void setEvaluator(TriggeringEventEvaluator trigger) {
        if (trigger == null) {
            throw new NullPointerException("trigger");
        }
        this.evaluator = trigger;
    }

    public final TriggeringEventEvaluator getEvaluator() {
        return this.evaluator;
    }

    public boolean parseUnrecognizedElement(Element element, Properties props) throws Exception {
        if ("triggeringPolicy".equals(element.getNodeName())) {
            Object triggerPolicy = DOMConfigurator.parseElement(element, props, TriggeringEventEvaluator.class);
            if (triggerPolicy instanceof TriggeringEventEvaluator) {
                this.setEvaluator((TriggeringEventEvaluator)triggerPolicy);
            }
            return true;
        }
        return false;
    }

    public final String getSMTPProtocol() {
        return this.smtpProtocol;
    }

    public final void setSMTPProtocol(String val) {
        this.smtpProtocol = val;
    }

    public final int getSMTPPort() {
        return this.smtpPort;
    }

    public final void setSMTPPort(int val) {
        this.smtpPort = val;
    }

    public final boolean getSendOnClose() {
        return this.sendOnClose;
    }

    public final void setSendOnClose(boolean val) {
        this.sendOnClose = val;
    }
}

