package com.main.connect4shared.enums;

import java.io.Serializable;

public enum RequestOperation implements Serializable {
    SIGN_IN,
    SIGN_UP,
    GET_AVAILABLE_ROW,
    GET_PLAYERS,
    SEND_MOVE
}
