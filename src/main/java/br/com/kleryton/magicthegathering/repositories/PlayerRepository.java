package br.com.kleryton.magicthegathering.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.kleryton.magicthegathering.models.PlayerModel;

@Repository
public interface PlayerRepository extends JpaRepository<PlayerModel, Long> {

}
