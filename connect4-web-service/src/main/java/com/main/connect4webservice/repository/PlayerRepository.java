package com.main.connect4webservice.repository;

import com.main.connect4webservice.domain.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Long> {
}
