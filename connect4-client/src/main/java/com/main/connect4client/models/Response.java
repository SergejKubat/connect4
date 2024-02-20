package com.main.connect4client.models;

import com.main.connect4client.models.enums.ResponseStatus;

/**
 * <p>Java class for response complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="response">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
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
public class Response {
    private Exception exception;

    private String message;

    private Object result;

    private Object result2;

    private Object result3;

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
     * Gets the value of the message property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the value of the message property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setMessage(String value) {
        this.message = value;
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
     * Gets the value of the result2 property.
     *
     * @return possible object is
     * {@link Object }
     */
    public Object getResult2() {
        return result2;
    }

    /**
     * Sets the value of the result2 property.
     *
     * @param value allowed object is
     *              {@link Object }
     */
    public void setResult2(Object value) {
        this.result2 = value;
    }

    /**
     * Gets the value of the result3 property.
     *
     * @return possible object is
     * {@link Object }
     */
    public Object getResult3() {
        return result3;
    }

    /**
     * Sets the value of the result3 property.
     *
     * @param value allowed object is
     *              {@link Object }
     */
    public void setResult3(Object value) {
        this.result3 = value;
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
