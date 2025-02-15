/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.util.POILogger;

public class CommonsLogger
implements POILogger {
    private static final LogFactory _creator = LogFactory.getFactory();
    private Log log;

    @Override
    public void initialize(String cat) {
        this.log = _creator.getInstance(cat);
    }

    @Override
    public void _log(int level, Object obj1) {
        switch (level) {
            case 9: {
                if (!this.log.isFatalEnabled()) break;
                this.log.fatal(obj1);
                break;
            }
            case 7: {
                if (!this.log.isErrorEnabled()) break;
                this.log.error(obj1);
                break;
            }
            case 5: {
                if (!this.log.isWarnEnabled()) break;
                this.log.warn(obj1);
                break;
            }
            case 3: {
                if (!this.log.isInfoEnabled()) break;
                this.log.info(obj1);
                break;
            }
            case 1: {
                if (!this.log.isDebugEnabled()) break;
                this.log.debug(obj1);
                break;
            }
            default: {
                if (!this.log.isTraceEnabled()) break;
                this.log.trace(obj1);
            }
        }
    }

    @Override
    public void _log(int level, Object obj1, Throwable exception) {
        switch (level) {
            case 9: {
                if (!this.log.isFatalEnabled()) break;
                if (obj1 != null) {
                    this.log.fatal(obj1, exception);
                    break;
                }
                this.log.fatal(exception);
                break;
            }
            case 7: {
                if (!this.log.isErrorEnabled()) break;
                if (obj1 != null) {
                    this.log.error(obj1, exception);
                    break;
                }
                this.log.error(exception);
                break;
            }
            case 5: {
                if (!this.log.isWarnEnabled()) break;
                if (obj1 != null) {
                    this.log.warn(obj1, exception);
                    break;
                }
                this.log.warn(exception);
                break;
            }
            case 3: {
                if (!this.log.isInfoEnabled()) break;
                if (obj1 != null) {
                    this.log.info(obj1, exception);
                    break;
                }
                this.log.info(exception);
                break;
            }
            case 1: {
                if (!this.log.isDebugEnabled()) break;
                if (obj1 != null) {
                    this.log.debug(obj1, exception);
                    break;
                }
                this.log.debug(exception);
                break;
            }
            default: {
                if (!this.log.isTraceEnabled()) break;
                if (obj1 != null) {
                    this.log.trace(obj1, exception);
                    break;
                }
                this.log.trace(exception);
            }
        }
    }

    @Override
    public boolean check(int level) {
        switch (level) {
            case 9: {
                return this.log.isFatalEnabled();
            }
            case 7: {
                return this.log.isErrorEnabled();
            }
            case 5: {
                return this.log.isWarnEnabled();
            }
            case 3: {
                return this.log.isInfoEnabled();
            }
            case 1: {
                return this.log.isDebugEnabled();
            }
        }
        return false;
    }
}

