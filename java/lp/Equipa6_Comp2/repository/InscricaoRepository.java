package lp.Equipa6_Comp2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import lp.Equipa6_Comp2.entity.Inscricao;

public interface InscricaoRepository extends JpaRepository<Inscricao, Long> {
	Inscricao findByProgramaIdAndVoluntarioId(Long programaId, Long voluntarioId);
	
}
