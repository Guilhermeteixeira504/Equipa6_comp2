package lp.Equipa6_Comp2.service;

import org.springframework.stereotype.Service;

import lp.Equipa6_Comp2.entity.Inscricao;
import lp.Equipa6_Comp2.entity.ProgramaVoluntariado;
import lp.Equipa6_Comp2.entity.Voluntario;
import lp.Equipa6_Comp2.repository.InscricaoRepository;
import lp.Equipa6_Comp2.repository.ProgramaVoluntariadoRepository;
import lp.Equipa6_Comp2.repository.VoluntarioRepository;

import java.util.List;

@Service
public class InscricaoService {
	
	private final InscricaoRepository inscricaoRepository;
	private final ProgramaVoluntariadoRepository programaVoluntariadoRepository;
	private final VoluntarioRepository voluntarioRepository;

	public InscricaoService(InscricaoRepository inscricaoRepository, ProgramaVoluntariadoRepository programaVoluntariadoRepository, VoluntarioRepository voluntarioRepository) {
	    this.inscricaoRepository = inscricaoRepository;
	    this.programaVoluntariadoRepository = programaVoluntariadoRepository;
	    this.voluntarioRepository = voluntarioRepository;
	}

	
	public List<Inscricao> getAllInscricoes(){
		return inscricaoRepository.findAll();
	}
	
	public Inscricao getInscricao(Long id) {
		return inscricaoRepository.findById(id).orElse(null);
	}
	
	public Inscricao createInscricao(Long programaId, Inscricao inscricao) {
		ProgramaVoluntariado p = programaVoluntariadoRepository.findById(programaId).orElseThrow(() -> new RuntimeException("Programa não encontrado"));
		p.addInscricao(inscricao);
		return inscricaoRepository.save(inscricao);
    }
	
	public Inscricao updateInscricao(Long id, Inscricao insc) {
		Inscricao existing = inscricaoRepository.findById(id).orElseThrow(() -> new RuntimeException("Inscrição não encontrada"));
		
		existing.setNHorasRealizadas(insc.getNHorasRealizadas());
		
		return inscricaoRepository.save(existing);
	}
	
	
	public void deleteInscricao(Long id) {
		inscricaoRepository.deleteById(id);
	}


    public Inscricao inscrever(Long programaId, Long voluntarioId) {
    	ProgramaVoluntariado programa = programaVoluntariadoRepository.findById(programaId).orElseThrow(() -> new RuntimeException("Programa não encontrado"));
    	Voluntario voluntario = voluntarioRepository.findById(voluntarioId).orElseThrow(() -> new RuntimeException("Voluntário não encontrado para o utilizador"));
    	
    	Inscricao insc = new Inscricao();
        insc.setPrograma(programa);
        insc.setVoluntario(voluntario);
        insc.setNHorasRealizadas(0);
        
        programa.addInscricao(insc);
        voluntario.addInscricao(insc);
        
        return inscricaoRepository.save(insc);
    }
    
    
}
