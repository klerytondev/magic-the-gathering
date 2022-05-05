package br.com.kleryton.magicthegathering.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.kleryton.magicthegathering.models.CardsList;

@Repository
public interface CardsListRepository extends JpaRepository<CardsList, Long>{

}
