package com.main.connect4server.models.enums;

/**
 * <p>Java class for responseStatus.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="responseStatus">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="SUCCESS"/>
 *     &lt;enumeration value="ERROR"/>
 *     &lt;enumeration value="PLAYER1_WON"/>
 *     &lt;enumeration value="PLAYER2_WON"/>
 *     &lt;enumeration value="DRAW"/>
 *     &lt;enumeration value="CONTINUE"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 */
public enum ResponseStatus {
    SUCCESS("SUCCESS"),
    ERROR("ERROR"),
    PLAYER_1_WON("PLAYER1_WON"),
    PLAYER_2_WON("PLAYER2_WON"),
    DRAW("DRAW"),
    CONTINUE("CONTINUE");

    private String status;

    ResponseStatus(String status) {
        status = status;
    }

    public static ResponseStatus fromStatus(String status) {
        for (ResponseStatus responseStatus : ResponseStatus.values()) {
            if (responseStatus.status.equals(status)) {
                return responseStatus;
            }
        }
        throw new IllegalArgumentException(status);
    }

    public String getStatus() {
        return status;
    }
}
