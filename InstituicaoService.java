package lp.Equipa6_Comp2.service;

import org.springframework.stereotype.Service;
import lp.Equipa6_Comp2.entity.Instituicao;
import lp.Equipa6_Comp2.repository.InstituicaoRepository;
import java.util.List;

@Service
public class InstituicaoService {
	
	private final InstituicaoRepository instituicaoRepository;
	
	public InstituicaoService(InstituicaoRepository instituicaoRepository) {
		this.instituicaoRepository = instituicaoRepository;
	}
	
	public List<Instituicao> getAllInstituicoes(){
		return instituicaoRepository.findAll();
	}
	
	public Instituicao getInstituicao(Long id) {
		return instituicaoRepository.findById(id).orElse(null);
	}
	
	public Instituicao createInstituicao(Instituicao inst) {
		return instituicaoRepository.save(inst);
	}
	
	public Instituicao updateInstituicao(Long id, Instituicao inst) {
		Instituicao existing = instituicaoRepository.findById(id).orElseThrow(() -> new RuntimeException("Instituição não encontrada"));
		existing.setNome(inst.getNome());
		existing.setDataFundacao(inst.getDataFundacao());
		existing.setProgramas(inst.getProgramas());
		
		return instituicaoRepository.save(existing);
	}
	
	public void deleteInstituicao(Long id) {
		instituicaoRepository.deleteById(id);
	}

}
