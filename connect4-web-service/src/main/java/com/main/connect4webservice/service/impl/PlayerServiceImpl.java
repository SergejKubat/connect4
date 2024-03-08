package com.main.connect4webservice.service.impl;

import com.main.connect4webservice.domain.Player;
import com.main.connect4webservice.payload.PlayerDto;
import com.main.connect4webservice.repository.PlayerRepository;
import com.main.connect4webservice.service.PlayerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlayerServiceImpl implements PlayerService {
    private final PlayerRepository playerRepository;

    public PlayerServiceImpl(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Override
    public List<PlayerDto> findAll() {
        return playerRepository.findAll().stream().map(this::mapToPlayerDto).collect(Collectors.toList());
    }

    private PlayerDto mapToPlayerDto(Player player) {
        PlayerDto playerDto = new PlayerDto();

        playerDto.setId(player.getId());
        playerDto.setUsername(player.getUsername());
        playerDto.setWins(player.getWins());
        playerDto.setRegisteredAt((player.getRegisteredAt()));

        return playerDto;
    }
}
