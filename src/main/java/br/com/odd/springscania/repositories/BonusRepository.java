package br.com.odd.springscania.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.odd.springscania.model.Bonus;

@Repository
public interface BonusRepository extends JpaRepository<Bonus, Long> {

}
