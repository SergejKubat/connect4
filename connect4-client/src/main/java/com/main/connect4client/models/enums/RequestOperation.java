package com.main.connect4client.models.enums;

import java.io.Serializable;

public enum RequestOperation implements Serializable {
    SIGN_IN,
    SIGN_UP,
    START_MATCH,
    GET_AVAILABLE_ROW,
    SEND_MOVE,
    RECEIVE_MOVE,
}