package com.main.connect4webservice.service;

import com.main.connect4webservice.payload.PlayerDto;

import java.util.List;

public interface PlayerService {
    List<PlayerDto> findAll();
}
