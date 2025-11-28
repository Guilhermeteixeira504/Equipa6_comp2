package lp.Equipa6_Comp2.service;

import org.springframework.stereotype.Service;
import lp.Equipa6_Comp2.entity.Edicao;
import lp.Equipa6_Comp2.repository.EdicaoRepository;
import java.util.List;

@Service
public class EdicaoService {
	
	private final EdicaoRepository edicaoRepository;
	
	public EdicaoService(EdicaoRepository edicaoRepository) {
		this.edicaoRepository = edicaoRepository;
	}
	
	public List<Edicao> getAllEdicoes(){
		return edicaoRepository.findAll();
	}
	
	public Edicao getEdicao(Long id) {
		return edicaoRepository.findById(id).orElse(null);
	}
	
	public Edicao createEdicao(Edicao e) {
		return edicaoRepository.save(e);
	}
	
	public Edicao updateEdicao(Long id, Edicao e) {
		Edicao existing = edicaoRepository.findById(id).orElseThrow(() -> new RuntimeException("Edição não encontrada"));
		existing.setAno(e.getAno());
		existing.setNumeroVagas(e.getNumeroVagas());
		existing.setDataInicio(e.getDataInicio());
		existing.setDataFim(e.getDataFim());
		existing.setInscricoes(e.getInscricoes());
		
		return edicaoRepository.save(existing);
	}
	
	public void deleteEdicao(Long id) {
		edicaoRepository.deleteById(id);
	}

}
