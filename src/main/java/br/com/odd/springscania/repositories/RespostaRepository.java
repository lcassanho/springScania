package br.com.odd.springscania.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.odd.springscania.model.Resposta;

@Repository
public interface RespostaRepository extends JpaRepository<Resposta, Long> {

}
