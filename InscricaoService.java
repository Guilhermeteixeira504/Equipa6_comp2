package lp.Equipa6_Comp2.service;

import org.springframework.stereotype.Service;

import lp.Equipa6_Comp2.entity.Inscricao;
import lp.Equipa6_Comp2.repository.InscricaoRepository;

import java.util.List;

@Service
public class InscricaoService {
	
	private final InscricaoRepository inscricaoRepository;

	public InscricaoService(InscricaoRepository inscricaoRepository) {
	    this.inscricaoRepository = inscricaoRepository;
	}

	
	public List<Inscricao> getAllInscricoes(){
		return inscricaoRepository.findAll();
	}
	
	public Inscricao getInscricao(Long id) {
		return inscricaoRepository.findById(id).orElse(null);
	}
	
	public Inscricao createInscricao(Inscricao insc) {
		return inscricaoRepository.save(insc);
	}
	
	public Inscricao updateInscricao(Long id, Inscricao insc) {
		Inscricao existing = inscricaoRepository.findById(id).orElseThrow(() -> new RuntimeException("Inscrição não encontrada"));
		existing.setVoluntario(insc.getVoluntario());
		existing.setPrograma(insc.getPrograma());
		existing.setNHorasRealizadas(insc.getNHorasRealizadas());
		
		return inscricaoRepository.save(existing);
	}
	
	public void deleteInscricao(Long id) {
		inscricaoRepository.deleteById(id);
	}
	public boolean registarHorasVoluntario(Long programaId, Long voluntarioId, int horas) {
        Inscricao inscricao = inscricaoRepository.findByProgramaIdAndVoluntarioId(programaId, voluntarioId);
        
        if (inscricao == null) {
            throw new RuntimeException("Inscrição não encontrada para programa " + programaId + " e voluntário " + voluntarioId);
        }
        if (horas < 0) {
            throw new IllegalArgumentException("Horas não podem ser negativas");
        }
        inscricao.setNHorasRealizadas(horas);

        inscricaoRepository.save(inscricao);
        
        return true;
    }
}
