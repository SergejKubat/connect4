package com.main.connect4shared.enums;

import java.io.Serializable;

/**
 * <p>Java class for requestOperation.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="requestOperation">
 *   &lt;restriction base="{<a href="http://www.w3.org/2001/XMLSchema">...</a>}string">
 *     &lt;enumeration value="SIGN_IN"/>
 *     &lt;enumeration value="SIGN_UP"/>
 *     &lt;enumeration value="GET_AVAILABLE_ROW"/>
 *     &lt;enumeration value="GET_PLAYERS"/>
 *     &lt;enumeration value="SEND_MOVE"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 */
public enum RequestOperation implements Serializable {
    SIGN_IN,
    SIGN_UP,
    GET_AVAILABLE_ROW,
    GET_PLAYERS,
    SEND_MOVE
}
