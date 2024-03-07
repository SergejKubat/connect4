package com.main.connect4shared.response;

import com.main.connect4shared.enums.ResponseStatus;

import java.io.Serializable;

/**
 * <p>Java class for response complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="response">
 *   &lt;complexContent>
 *     &lt;restriction base="{<a href="http://www.w3.org/2001/XMLSchema">...</a>}anyType">
 *       &lt;sequence>
 *         &lt;element name="exception" type="{http://web/}exception" minOccurs="0"/>
 *         &lt;element name="message" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="result" type="{http://www.w3.org/2001/XMLSchema}anyType" minOccurs="0"/>
 *         &lt;element name="result2" type="{http://www.w3.org/2001/XMLSchema}anyType" minOccurs="0"/>
 *         &lt;element name="result3" type="{http://www.w3.org/2001/XMLSchema}anyType" minOccurs="0"/>
 *         &lt;element name="status" type="{http://web/}responseStatus" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
public class Response implements Serializable {
    private Exception exception;

    private Object result;

    private ResponseStatus status;

    /**
     * Gets the value of the exception property.
     *
     * @return possible object is
     * {@link Exception }
     */
    public Exception getException() {
        return exception;
    }

    /**
     * Sets the value of the exception property.
     *
     * @param value allowed object is
     *              {@link Exception }
     */
    public void setException(Exception value) {
        this.exception = value;
    }

    /**
     * Gets the value of the result property.
     *
     * @return possible object is
     * {@link Object }
     */
    public Object getResult() {
        return result;
    }

    /**
     * Sets the value of the result property.
     *
     * @param value allowed object is
     *              {@link Object }
     */
    public void setResult(Object value) {
        this.result = value;
    }

    /**
     * Gets the value of the status property.
     *
     * @return possible object is
     * {@link ResponseStatus }
     */
    public ResponseStatus getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     *
     * @param value allowed object is
     *              {@link ResponseStatus }
     */
    public void setStatus(ResponseStatus value) {
        this.status = value;
    }
}