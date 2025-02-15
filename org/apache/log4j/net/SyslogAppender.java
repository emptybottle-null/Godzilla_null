/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.log4j.net;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.Layout;
import org.apache.log4j.helpers.SyslogQuietWriter;
import org.apache.log4j.helpers.SyslogWriter;
import org.apache.log4j.spi.LoggingEvent;

public class SyslogAppender
extends AppenderSkeleton {
    public static final int LOG_KERN = 0;
    public static final int LOG_USER = 8;
    public static final int LOG_MAIL = 16;
    public static final int LOG_DAEMON = 24;
    public static final int LOG_AUTH = 32;
    public static final int LOG_SYSLOG = 40;
    public static final int LOG_LPR = 48;
    public static final int LOG_NEWS = 56;
    public static final int LOG_UUCP = 64;
    public static final int LOG_CRON = 72;
    public static final int LOG_AUTHPRIV = 80;
    public static final int LOG_FTP = 88;
    public static final int LOG_LOCAL0 = 128;
    public static final int LOG_LOCAL1 = 136;
    public static final int LOG_LOCAL2 = 144;
    public static final int LOG_LOCAL3 = 152;
    public static final int LOG_LOCAL4 = 160;
    public static final int LOG_LOCAL5 = 168;
    public static final int LOG_LOCAL6 = 176;
    public static final int LOG_LOCAL7 = 184;
    protected static final int SYSLOG_HOST_OI = 0;
    protected static final int FACILITY_OI = 1;
    static final String TAB = "    ";
    int syslogFacility = 8;
    String facilityStr;
    boolean facilityPrinting = false;
    SyslogQuietWriter sqw;
    String syslogHost;
    private boolean header = false;
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd HH:mm:ss ", Locale.ENGLISH);
    private String localHostname;
    private boolean layoutHeaderChecked = false;

    public SyslogAppender() {
        this.initSyslogFacilityStr();
    }

    public SyslogAppender(Layout layout, int syslogFacility) {
        this.layout = layout;
        this.syslogFacility = syslogFacility;
        this.initSyslogFacilityStr();
    }

    public SyslogAppender(Layout layout, String syslogHost, int syslogFacility) {
        this(layout, syslogFacility);
        this.setSyslogHost(syslogHost);
    }

    public synchronized void close() {
        this.closed = true;
        if (this.sqw != null) {
            try {
                if (this.layoutHeaderChecked && this.layout != null && this.layout.getFooter() != null) {
                    this.sendLayoutMessage(this.layout.getFooter());
                }
                this.sqw.close();
                this.sqw = null;
            } catch (InterruptedIOException e) {
                Thread.currentThread().interrupt();
                this.sqw = null;
            } catch (IOException e) {
                this.sqw = null;
            }
        }
    }

    private void initSyslogFacilityStr() {
        this.facilityStr = SyslogAppender.getFacilityString(this.syslogFacility);
        if (this.facilityStr == null) {
            System.err.println("\"" + this.syslogFacility + "\" is an unknown syslog facility. Defaulting to \"USER\".");
            this.syslogFacility = 8;
            this.facilityStr = "user:";
        } else {
            this.facilityStr = this.facilityStr + ":";
        }
    }

    public static String getFacilityString(int syslogFacility) {
        switch (syslogFacility) {
            case 0: {
                return "kern";
            }
            case 8: {
                return "user";
            }
            case 16: {
                return "mail";
            }
            case 24: {
                return "daemon";
            }
            case 32: {
                return "auth";
            }
            case 40: {
                return "syslog";
            }
            case 48: {
                return "lpr";
            }
            case 56: {
                return "news";
            }
            case 64: {
                return "uucp";
            }
            case 72: {
                return "cron";
            }
            case 80: {
                return "authpriv";
            }
            case 88: {
                return "ftp";
            }
            case 128: {
                return "local0";
            }
            case 136: {
                return "local1";
            }
            case 144: {
                return "local2";
            }
            case 152: {
                return "local3";
            }
            case 160: {
                return "local4";
            }
            case 168: {
                return "local5";
            }
            case 176: {
                return "local6";
            }
            case 184: {
                return "local7";
            }
        }
        return null;
    }

    public static int getFacility(String facilityName) {
        if (facilityName != null) {
            facilityName = facilityName.trim();
        }
        if ("KERN".equalsIgnoreCase(facilityName)) {
            return 0;
        }
        if ("USER".equalsIgnoreCase(facilityName)) {
            return 8;
        }
        if ("MAIL".equalsIgnoreCase(facilityName)) {
            return 16;
        }
        if ("DAEMON".equalsIgnoreCase(facilityName)) {
            return 24;
        }
        if ("AUTH".equalsIgnoreCase(facilityName)) {
            return 32;
        }
        if ("SYSLOG".equalsIgnoreCase(facilityName)) {
            return 40;
        }
        if ("LPR".equalsIgnoreCase(facilityName)) {
            return 48;
        }
        if ("NEWS".equalsIgnoreCase(facilityName)) {
            return 56;
        }
        if ("UUCP".equalsIgnoreCase(facilityName)) {
            return 64;
        }
        if ("CRON".equalsIgnoreCase(facilityName)) {
            return 72;
        }
        if ("AUTHPRIV".equalsIgnoreCase(facilityName)) {
            return 80;
        }
        if ("FTP".equalsIgnoreCase(facilityName)) {
            return 88;
        }
        if ("LOCAL0".equalsIgnoreCase(facilityName)) {
            return 128;
        }
        if ("LOCAL1".equalsIgnoreCase(facilityName)) {
            return 136;
        }
        if ("LOCAL2".equalsIgnoreCase(facilityName)) {
            return 144;
        }
        if ("LOCAL3".equalsIgnoreCase(facilityName)) {
            return 152;
        }
        if ("LOCAL4".equalsIgnoreCase(facilityName)) {
            return 160;
        }
        if ("LOCAL5".equalsIgnoreCase(facilityName)) {
            return 168;
        }
        if ("LOCAL6".equalsIgnoreCase(facilityName)) {
            return 176;
        }
        if ("LOCAL7".equalsIgnoreCase(facilityName)) {
            return 184;
        }
        return -1;
    }

    private void splitPacket(String header, String packet) {
        int byteCount = packet.getBytes().length;
        if (byteCount <= 1019) {
            this.sqw.write(packet);
        } else {
            int split = header.length() + (packet.length() - header.length()) / 2;
            this.splitPacket(header, packet.substring(0, split) + "...");
            this.splitPacket(header, header + "..." + packet.substring(split));
        }
    }

    public void append(LoggingEvent event) {
        String[] s;
        if (!this.isAsSevereAsThreshold(event.getLevel())) {
            return;
        }
        if (this.sqw == null) {
            this.errorHandler.error("No syslog host is set for SyslogAppedender named \"" + this.name + "\".");
            return;
        }
        if (!this.layoutHeaderChecked) {
            if (this.layout != null && this.layout.getHeader() != null) {
                this.sendLayoutMessage(this.layout.getHeader());
            }
            this.layoutHeaderChecked = true;
        }
        String hdr = this.getPacketHeader(event.timeStamp);
        String packet = this.layout == null ? String.valueOf(event.getMessage()) : this.layout.format(event);
        if (this.facilityPrinting || hdr.length() > 0) {
            StringBuffer buf = new StringBuffer(hdr);
            if (this.facilityPrinting) {
                buf.append(this.facilityStr);
            }
            buf.append(packet);
            packet = buf.toString();
        }
        this.sqw.setLevel(event.getLevel().getSyslogEquivalent());
        if (packet.length() > 256) {
            this.splitPacket(hdr, packet);
        } else {
            this.sqw.write(packet);
        }
        if ((this.layout == null || this.layout.ignoresThrowable()) && (s = event.getThrowableStrRep()) != null) {
            for (int i = 0; i < s.length; ++i) {
                if (s[i].startsWith("\t")) {
                    this.sqw.write(hdr + TAB + s[i].substring(1));
                    continue;
                }
                this.sqw.write(hdr + s[i]);
            }
        }
    }

    public void activateOptions() {
        if (this.header) {
            this.getLocalHostname();
        }
        if (this.layout != null && this.layout.getHeader() != null) {
            this.sendLayoutMessage(this.layout.getHeader());
        }
        this.layoutHeaderChecked = true;
    }

    public boolean requiresLayout() {
        return true;
    }

    public void setSyslogHost(String syslogHost) {
        this.sqw = new SyslogQuietWriter(new SyslogWriter(syslogHost), this.syslogFacility, this.errorHandler);
        this.syslogHost = syslogHost;
    }

    public String getSyslogHost() {
        return this.syslogHost;
    }

    public void setFacility(String facilityName) {
        if (facilityName == null) {
            return;
        }
        this.syslogFacility = SyslogAppender.getFacility(facilityName);
        if (this.syslogFacility == -1) {
            System.err.println("[" + facilityName + "] is an unknown syslog facility. Defaulting to [USER].");
            this.syslogFacility = 8;
        }
        this.initSyslogFacilityStr();
        if (this.sqw != null) {
            this.sqw.setSyslogFacility(this.syslogFacility);
        }
    }

    public String getFacility() {
        return SyslogAppender.getFacilityString(this.syslogFacility);
    }

    public void setFacilityPrinting(boolean on) {
        this.facilityPrinting = on;
    }

    public boolean getFacilityPrinting() {
        return this.facilityPrinting;
    }

    public final boolean getHeader() {
        return this.header;
    }

    public final void setHeader(boolean val) {
        this.header = val;
    }

    private String getLocalHostname() {
        if (this.localHostname == null) {
            try {
                InetAddress addr = InetAddress.getLocalHost();
                this.localHostname = addr.getHostName();
            } catch (UnknownHostException uhe) {
                this.localHostname = "UNKNOWN_HOST";
            }
        }
        return this.localHostname;
    }

    private String getPacketHeader(long timeStamp) {
        if (this.header) {
            StringBuffer buf = new StringBuffer(this.dateFormat.format(new Date(timeStamp)));
            if (buf.charAt(4) == '0') {
                buf.setCharAt(4, ' ');
            }
            buf.append(this.getLocalHostname());
            buf.append(' ');
            return buf.toString();
        }
        return "";
    }

    private void sendLayoutMessage(String msg) {
        if (this.sqw != null) {
            String packet = msg;
            String hdr = this.getPacketHeader(new Date().getTime());
            if (this.facilityPrinting || hdr.length() > 0) {
                StringBuffer buf = new StringBuffer(hdr);
                if (this.facilityPrinting) {
                    buf.append(this.facilityStr);
                }
                buf.append(msg);
                packet = buf.toString();
            }
            this.sqw.setLevel(6);
            this.sqw.write(packet);
        }
    }
}

