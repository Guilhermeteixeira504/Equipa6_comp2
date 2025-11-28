package lp.Equipa6_Comp2.controller;

import org.springframework.web.bind.annotation.*;
import lp.Equipa6_Comp2.entity.Instituicao;
import lp.Equipa6_Comp2.service.InstituicaoService;
import java.util.List;

@RestController
@RequestMapping("/voluntariado/instituicoes")
public class InstituicaoController {
	
	private final InstituicaoService instituicaoService;
	
	public InstituicaoController(InstituicaoService instituicaoService) {
		this.instituicaoService = instituicaoService;
	}
	
	@GetMapping
	public List<Instituicao> getAll() {
		return instituicaoService.getAllInstituicoes();
	}
	
	@GetMapping("/{id}")
	public Instituicao getById(@PathVariable Long id) {
		return instituicaoService.getInstituicao(id);
	}
	
	@PostMapping
	public Instituicao create(@RequestBody Instituicao inst) {
		return instituicaoService.createInstituicao(inst);
	}
	
	@PutMapping("/{id}")
	public Instituicao update(@PathVariable Long id, @RequestBody Instituicao inst) {
		return instituicaoService.updateInstituicao(id, inst);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		instituicaoService.deleteInstituicao(id);
	}

}
