package lp.Equipa6_Comp2.service;

import org.springframework.stereotype.Service;
import lp.Equipa6_Comp2.entity.Edicao;
import lp.Equipa6_Comp2.entity.ProgramaVoluntariado;
import lp.Equipa6_Comp2.repository.EdicaoRepository;
import lp.Equipa6_Comp2.repository.ProgramaVoluntariadoRepository;
import java.util.List;

@Service
public class EdicaoService {
	
	private final EdicaoRepository edicaoRepository;
	private final ProgramaVoluntariadoRepository programaVoluntariadoRepository;
	
	public EdicaoService(EdicaoRepository edicaoRepository,ProgramaVoluntariadoRepository programaVoluntariadoRepository) {
		this.edicaoRepository = edicaoRepository;
		this.programaVoluntariadoRepository = programaVoluntariadoRepository;
	}
	
	public List<Edicao> getAllEdicoes(){
		return edicaoRepository.findAll();
	}
	
	public Edicao getEdicao(Long id) {
		return edicaoRepository.findById(id).orElse(null);
	}
	
	public Edicao createEdicao(Long programaId, Edicao e) {
		ProgramaVoluntariado programa = programaVoluntariadoRepository.findById(programaId).orElseThrow(() -> new RuntimeException("Programa não encontrado"));
		programa.addEdicao(e);
		return edicaoRepository.save(e);
	}
	
	public Edicao updateEdicao(Long id, Edicao e) {
		Edicao existing = edicaoRepository.findById(id).orElseThrow(() -> new RuntimeException("Edição não encontrada"));
		existing.setAno(e.getAno());
		existing.setNumeroVagas(e.getNumeroVagas());
		existing.setDataInicio(e.getDataInicio());
		existing.setDataFim(e.getDataFim());
		existing.setInscricoes(e.getInscricoes());
		existing.setPrograma(e.getPrograma());
		
		return edicaoRepository.save(existing);
	}
	
	public void deleteEdicao(Long id) {
		edicaoRepository.deleteById(id);
	}

}
