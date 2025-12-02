package lp.Equipa6_Comp2.service;

import org.springframework.stereotype.Service;
import lp.Equipa6_Comp2.entity.TiposVoluntariado;
import lp.Equipa6_Comp2.repository.TiposVoluntariadoRepository;
import java.util.List;

@Service
public class TiposVoluntariadoService {
	
	private final TiposVoluntariadoRepository tiposVoluntariadoRepository;
	
	public TiposVoluntariadoService(TiposVoluntariadoRepository tiposVoluntariadoRepository) {
		this.tiposVoluntariadoRepository = tiposVoluntariadoRepository;
	}
	
	public List<TiposVoluntariado> getAllTiposVoluntariados(){
		return tiposVoluntariadoRepository.findAll();
	}
	
	public TiposVoluntariado getTiposVoluntariado(Long id) {
		return tiposVoluntariadoRepository.findById(id).orElse(null);
	}
	
	public TiposVoluntariado createTiposVoluntariado(TiposVoluntariado tv) {
		return tiposVoluntariadoRepository.save(tv);
	}
	
	public TiposVoluntariado updateTiposVoluntariado(Long id, TiposVoluntariado tv) {
		TiposVoluntariado existing = tiposVoluntariadoRepository.findById(id).orElseThrow(() -> new RuntimeException("Tipo de voluntariado não encontrado"));
		existing.setNome(tv.getNome());
		existing.setProgramas(tv.getProgramas());
		
		return tiposVoluntariadoRepository.save(existing);
	}
	
	public void deleteTiposVoluntariado(Long id) {
		tiposVoluntariadoRepository.deleteById(id);
	}

}
