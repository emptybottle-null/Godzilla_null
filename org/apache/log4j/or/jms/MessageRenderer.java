/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  javax.jms.JMSException
 *  javax.jms.Message
 */
package org.apache.log4j.or.jms;

import javax.jms.JMSException;
import javax.jms.Message;
import org.apache.log4j.helpers.LogLog;
import org.apache.log4j.or.ObjectRenderer;

public class MessageRenderer
implements ObjectRenderer {
    public String doRender(Object o) {
        if (o instanceof Message) {
            StringBuffer sbuf = new StringBuffer();
            Message m = (Message)o;
            try {
                sbuf.append("DeliveryMode=");
                switch (m.getJMSDeliveryMode()) {
                    case 1: {
                        sbuf.append("NON_PERSISTENT");
                        break;
                    }
                    case 2: {
                        sbuf.append("PERSISTENT");
                        break;
                    }
                    default: {
                        sbuf.append("UNKNOWN");
                    }
                }
                sbuf.append(", CorrelationID=");
                sbuf.append(m.getJMSCorrelationID());
                sbuf.append(", Destination=");
                sbuf.append(m.getJMSDestination());
                sbuf.append(", Expiration=");
                sbuf.append(m.getJMSExpiration());
                sbuf.append(", MessageID=");
                sbuf.append(m.getJMSMessageID());
                sbuf.append(", Priority=");
                sbuf.append(m.getJMSPriority());
                sbuf.append(", Redelivered=");
                sbuf.append(m.getJMSRedelivered());
                sbuf.append(", ReplyTo=");
                sbuf.append(m.getJMSReplyTo());
                sbuf.append(", Timestamp=");
                sbuf.append(m.getJMSTimestamp());
                sbuf.append(", Type=");
                sbuf.append(m.getJMSType());
            } catch (JMSException e) {
                LogLog.error("Could not parse Message.", e);
            }
            return sbuf.toString();
        }
        return o.toString();
    }
}

