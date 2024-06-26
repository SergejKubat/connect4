package com.main.connect4shared.enums;

import java.io.Serializable;

/**
 * <p>Java class for responseStatus.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="responseStatus">
 *   &lt;restriction base="{<a href="http://www.w3.org/2001/XMLSchema">...</a>}string">
 *     &lt;enumeration value="SUCCESS"/>
 *     &lt;enumeration value="ERROR"/>
 *     &lt;enumeration value="PLAYER_HUMAN_WON"/>
 *     &lt;enumeration value="PLAYER_COMPUTER_WON"/>
 *     &lt;enumeration value="DRAW"/>
 *     &lt;enumeration value="CONTINUE"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 */
public enum ResponseStatus implements Serializable {
    SUCCESS,
    ERROR,
    PLAYER_HUMAN_WON,
    PLAYER_COMPUTER_WON,
    DRAW,
    CONTINUE
}
