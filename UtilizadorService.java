package lp.Equipa6_Comp2.service;

import org.springframework.stereotype.Service;
import lp.Equipa6_Comp2.entity.Utilizador;
import lp.Equipa6_Comp2.repository.UtilizadorRepository;
import java.util.List;

@Service
public class UtilizadorService {
	
	private final UtilizadorRepository utilizadorRepository;
	
	public UtilizadorService(UtilizadorRepository utilizadorRepository) {
		this.utilizadorRepository = utilizadorRepository;
	}
	
	public List<Utilizador> getAllUtilizadores(){
		return utilizadorRepository.findAll();
	}
	
	public Utilizador getUtilizador(Long id) {
		return utilizadorRepository.findById(id).orElse(null);
	}
	
	public Utilizador createUtilizador(Utilizador u) {
		return utilizadorRepository.save(u);
	}
	
	public Utilizador updateUtilizador(Long id, Utilizador u) {
		Utilizador existing = utilizadorRepository.findById(id).orElseThrow(() -> new RuntimeException("Utilizador não encontrado"));
		existing.setNome(u.getNome());
		existing.setEmail(u.getEmail());
		existing.setMorada(u.getMorada());
		existing.setPassword(u.getPassword());
		existing.setTelemovel(u.getTelemovel());
		existing.setTipoUtilizador(u.getTipoUtilizador());
		
		return utilizadorRepository.save(existing);
	}
	
	public void deleteUtilizador(Long id) {
		utilizadorRepository.deleteById(id);
	}

}
