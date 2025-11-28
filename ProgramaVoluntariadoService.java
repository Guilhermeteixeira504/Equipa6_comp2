package lp.Equipa6_Comp2.service;

import org.springframework.stereotype.Service;
import lp.Equipa6_Comp2.entity.ProgramaVoluntariado;
import lp.Equipa6_Comp2.repository.ProgramaVoluntariadoRepository;
import java.util.List;

@Service
public class ProgramaVoluntariadoService {
	
	private final ProgramaVoluntariadoRepository programaVoluntariadoRepository;
	
	public ProgramaVoluntariadoService(ProgramaVoluntariadoRepository programaVoluntariadoRepository) {
		this.programaVoluntariadoRepository = programaVoluntariadoRepository;
	}
	
	public List<ProgramaVoluntariado> getAllProgramasVoluntariado(){
		return programaVoluntariadoRepository.findAll();
	}
	
	public ProgramaVoluntariado getProgramaVoluntariado(Long id) {
		return programaVoluntariadoRepository.findById(id).orElse(null);
	}
	
	public ProgramaVoluntariado createProgramaVoluntariado(ProgramaVoluntariado pv) {
		return programaVoluntariadoRepository.save(pv);
	}
	
	public ProgramaVoluntariado updateProgramaVoluntariado(Long id, ProgramaVoluntariado pv) {
		ProgramaVoluntariado existing = programaVoluntariadoRepository.findById(id).orElseThrow(() -> new RuntimeException("Programa de voluntariado não encontrado"));
		existing.setTitulo(pv.getTitulo());
		existing.setDescricao(pv.getDescricao());
		existing.setLocalizacao(pv.getLocalizacao());
		existing.setNumeroVagas(pv.getNumeroVagas());
		existing.setDataInicio(pv.getDataInicio());
		existing.setDataFim(pv.getDataFim());
		existing.setTotalHoras(pv.getTotalHoras());
		existing.setInscricoes(pv.getInscricoes());
		
		return programaVoluntariadoRepository.save(existing);
	}
	
	public void deleteProgramaVoluntariado(Long id) {
		programaVoluntariadoRepository.deleteById(id);
	}

}
