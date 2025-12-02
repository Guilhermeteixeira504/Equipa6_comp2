package lp.Equipa6_Comp2.service;

import org.springframework.stereotype.Service;
import lp.Equipa6_Comp2.entity.Instituicao;
import lp.Equipa6_Comp2.entity.ProgramaVoluntariado;
import lp.Equipa6_Comp2.repository.InstituicaoRepository;
import lp.Equipa6_Comp2.repository.ProgramaVoluntariadoRepository;

import java.util.List;

@Service
public class InstituicaoService {
	
	private final InstituicaoRepository instituicaoRepository;
	private final ProgramaVoluntariadoRepository programaVoluntariadoRepository;
	
	public InstituicaoService(InstituicaoRepository instituicaoRepository, ProgramaVoluntariadoRepository programaVoluntariadoRepository) {
		this.instituicaoRepository = instituicaoRepository;
		this.programaVoluntariadoRepository = programaVoluntariadoRepository;
	}
	
	public List<Instituicao> getAllInstituicoes(){
		return instituicaoRepository.findAll();
	}
	
	public Instituicao getInstituicao(Long id) {
		return instituicaoRepository.findById(id).orElse(null);
	}
	
	public Instituicao createInstituicao(Long programaId, Instituicao inst) {
		ProgramaVoluntariado p = programaVoluntariadoRepository.findById(programaId).orElseThrow(() -> new RuntimeException("Programa não encontrado"));
		p.addInstituicoes(inst);
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
