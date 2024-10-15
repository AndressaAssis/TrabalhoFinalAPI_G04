package org.serratec.curriculo.repository;


import org.serratec.curriculo.model.Candidato;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidatoRepository extends JpaRepository<Candidato, Long>{
	
 }
