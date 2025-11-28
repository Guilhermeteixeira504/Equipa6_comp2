package lp.Equipa6_Comp2.controller;

import org.springframework.web.bind.annotation.*;
import lp.Equipa6_Comp2.entity.Utilizador;
import lp.Equipa6_Comp2.service.UtilizadorService;
import java.util.List;

@RestController
@RequestMapping("/voluntariado/utilizadores")
public class UtilizadorController {
	
	private final UtilizadorService utilizadorService;
	
	public UtilizadorController(UtilizadorService utilizadorService) {
		this.utilizadorService = utilizadorService;
	}
	
	@GetMapping
	public List<Utilizador> getAll() {
		return utilizadorService.getAllUtilizadores();
	}
	
	@GetMapping("/{id}")
	public Utilizador getById(@PathVariable Long id) {
		return utilizadorService.getUtilizador(id);
	}
	
	@PostMapping
	public Utilizador create(@RequestBody Utilizador u) {
		return utilizadorService.createUtilizador(u);
	}
	
	@PutMapping("/{id}")
	public Utilizador update(@PathVariable Long id, @RequestBody Utilizador u) {
		return utilizadorService.updateUtilizador(id, u);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		utilizadorService.deleteUtilizador(id);
	}

}
