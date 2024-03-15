package com.main.connect4shared.enums;

/**
 * <p>Java class for gameState.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="gameState">
 *   &lt;restriction base="{<a href="http://www.w3.org/2001/XMLSchema">...</a>}string">
 *     &lt;enumeration value="PLAYER_HUMAN_WON"/>
 *     &lt;enumeration value="PLAYER_COMPUTER_WON"/>
 *     &lt;enumeration value="DRAW"/>
 *     &lt;enumeration value="CONTINUE"/>
 *     &lt;enumeration value="FULL"/>
 *     &lt;enumeration value="INVALID"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 */
public enum GameState {
    PLAYER_HUMAN_WON,
    PLAYER_COMPUTER_WON,
    DRAW,
    CONTINUE,
    FULL,
    INVALID
}
