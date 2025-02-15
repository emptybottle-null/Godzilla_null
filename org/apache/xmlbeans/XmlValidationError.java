/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans;

import java.util.List;
import javax.xml.namespace.QName;
import javax.xml.stream.Location;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlError;

public class XmlValidationError
extends XmlError {
    public static final int INCORRECT_ELEMENT = 1;
    public static final int ELEMENT_NOT_ALLOWED = 2;
    public static final int ELEMENT_TYPE_INVALID = 3;
    public static final int NIL_ELEMENT = 4;
    public static final int INCORRECT_ATTRIBUTE = 1000;
    public static final int ATTRIBUTE_TYPE_INVALID = 1001;
    public static final int LIST_INVALID = 2000;
    public static final int UNION_INVALID = 3000;
    public static final int UNDEFINED = 10000;
    private QName _fieldQName;
    private QName _offendingQName;
    private SchemaType _expectedSchemaType;
    private List _expectedQNames;
    private int _errorType;
    private SchemaType _badSchemaType;

    private XmlValidationError(String message, int severity, XmlCursor cursor, QName fieldQName, QName offendingQname, SchemaType expectedSchemaType, List expectedQNames, int errorType, SchemaType badSchemaType) {
        super(message, (String)null, severity, cursor);
        this.setFieldQName(fieldQName);
        this.setOffendingQName(offendingQname);
        this.setExpectedSchemaType(expectedSchemaType);
        this.setExpectedQNames(expectedQNames);
        this.setErrorType(errorType);
        this.setBadSchemaType(badSchemaType);
    }

    private XmlValidationError(String code, Object[] args, int severity, XmlCursor cursor, QName fieldQName, QName offendingQname, SchemaType expectedSchemaType, List expectedQNames, int errorType, SchemaType badSchemaType) {
        super(code, args, severity, cursor);
        this.setFieldQName(fieldQName);
        this.setOffendingQName(offendingQname);
        this.setExpectedSchemaType(expectedSchemaType);
        this.setExpectedQNames(expectedQNames);
        this.setErrorType(errorType);
        this.setBadSchemaType(badSchemaType);
    }

    private XmlValidationError(String message, int severity, Location loc, QName fieldQName, QName offendingQname, SchemaType expectedSchemaType, List expectedQNames, int errorType, SchemaType badSchemaType) {
        super(message, (String)null, severity, loc);
        this.setFieldQName(fieldQName);
        this.setOffendingQName(offendingQname);
        this.setExpectedSchemaType(expectedSchemaType);
        this.setExpectedQNames(expectedQNames);
        this.setErrorType(errorType);
        this.setBadSchemaType(badSchemaType);
    }

    private XmlValidationError(String code, Object[] args, int severity, Location loc, QName fieldQName, QName offendingQname, SchemaType expectedSchemaType, List expectedQNames, int errorType, SchemaType badSchemaType) {
        super(code, args, severity, loc);
        this.setFieldQName(fieldQName);
        this.setOffendingQName(offendingQname);
        this.setExpectedSchemaType(expectedSchemaType);
        this.setExpectedQNames(expectedQNames);
        this.setErrorType(errorType);
        this.setBadSchemaType(badSchemaType);
    }

    public static XmlValidationError forCursorWithDetails(String message, String code, Object[] args, int severity, XmlCursor cursor, QName fieldQName, QName offendingQname, SchemaType expectedSchemaType, List expectedQNames, int errorType, SchemaType badSchemaType) {
        if (code == null) {
            return new XmlValidationError(message, severity, cursor, fieldQName, offendingQname, expectedSchemaType, expectedQNames, errorType, badSchemaType);
        }
        return new XmlValidationError(code, args, severity, cursor, fieldQName, offendingQname, expectedSchemaType, expectedQNames, errorType, badSchemaType);
    }

    public static XmlValidationError forLocationWithDetails(String message, String code, Object[] args, int severity, Location location, QName fieldQName, QName offendingQname, SchemaType expectedSchemaType, List expectedQNames, int errorType, SchemaType badSchemaType) {
        if (code == null) {
            return new XmlValidationError(message, severity, location, fieldQName, offendingQname, expectedSchemaType, expectedQNames, errorType, badSchemaType);
        }
        return new XmlValidationError(code, args, severity, location, fieldQName, offendingQname, expectedSchemaType, expectedQNames, errorType, badSchemaType);
    }

    @Override
    public String getMessage() {
        if (this._fieldQName != null) {
            String msg = super.getMessage();
            StringBuffer sb = new StringBuffer(msg.length() + 100);
            sb.append(msg);
            sb.append(" in element ");
            sb.append(this._fieldQName.getLocalPart());
            if (this._fieldQName.getNamespaceURI() != null && this._fieldQName.getNamespaceURI().length() != 0) {
                sb.append('@').append(this._fieldQName.getNamespaceURI());
            }
            return sb.toString();
        }
        return super.getMessage();
    }

    public SchemaType getBadSchemaType() {
        return this._badSchemaType;
    }

    public void setBadSchemaType(SchemaType _badSchemaType) {
        this._badSchemaType = _badSchemaType;
    }

    public int getErrorType() {
        return this._errorType;
    }

    public void setErrorType(int _errorType) {
        this._errorType = _errorType;
    }

    public List getExpectedQNames() {
        return this._expectedQNames;
    }

    public void setExpectedQNames(List _expectedQNames) {
        this._expectedQNames = _expectedQNames;
    }

    public QName getFieldQName() {
        return this._fieldQName;
    }

    public void setFieldQName(QName _fieldQName) {
        this._fieldQName = _fieldQName;
    }

    public QName getOffendingQName() {
        return this._offendingQName;
    }

    public void setOffendingQName(QName _offendingQName) {
        this._offendingQName = _offendingQName;
    }

    public SchemaType getExpectedSchemaType() {
        return this._expectedSchemaType;
    }

    public void setExpectedSchemaType(SchemaType _expectedSchemaType) {
        this._expectedSchemaType = _expectedSchemaType;
    }
}

