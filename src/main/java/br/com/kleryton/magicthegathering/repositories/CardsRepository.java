package br.com.kleryton.magicthegathering.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.kleryton.magicthegathering.models.CardsModel;

//Cria um beans para persisti no banco
@Repository
public interface CardsRepository extends JpaRepository<CardsModel, Long> {

}
