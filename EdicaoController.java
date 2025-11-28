package lp.Equipa6_Comp2.controller;

import org.springframework.web.bind.annotation.*;
import lp.Equipa6_Comp2.entity.Edicao;
import lp.Equipa6_Comp2.service.EdicaoService;
import java.util.List;

@RestController
@RequestMapping("/voluntariado/edicoes")
public class EdicaoController {
	
	private final EdicaoService edicaoService;
	
	public EdicaoController(EdicaoService edicaoService) {
		this.edicaoService = edicaoService;
	}
	
	@GetMapping
	public List<Edicao> getAll() {
		return edicaoService.getAllEdicoes();
	}
	
	@GetMapping("/{id}")
	public Edicao getById(@PathVariable Long id) {
		return edicaoService.getEdicao(id);
	}
	
	@PostMapping
	public Edicao create(@RequestBody Edicao e) {
		return edicaoService.createEdicao(e);
	}
	
	@PutMapping("/{id}")
	public Edicao update(@PathVariable Long id, @RequestBody Edicao e) {
		return edicaoService.updateEdicao(id, e);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		edicaoService.deleteEdicao(id);
	}

}
