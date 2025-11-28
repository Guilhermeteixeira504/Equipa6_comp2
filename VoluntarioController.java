package lp.Equipa6_Comp2.controller;

import org.springframework.web.bind.annotation.*;
import lp.Equipa6_Comp2.entity.Voluntario;
import lp.Equipa6_Comp2.service.VoluntarioService;
import java.util.List;

@RestController
@RequestMapping("/voluntariado/voluntarios")
public class VoluntarioController {
	
	private final VoluntarioService voluntarioService;
	
	public VoluntarioController(VoluntarioService voluntarioService) {
		this.voluntarioService = voluntarioService;
	}
	
	@GetMapping
	public List<Voluntario> getAll() {
		return voluntarioService.getAllVoluntarios();
	}
	
	@GetMapping("/{id}")
	public Voluntario getById(@PathVariable Long id) {
		return voluntarioService.getVoluntario(id);
	}
	
	@PostMapping
	public Voluntario create(@RequestBody Voluntario v) {
		return voluntarioService.createVoluntario(v);
	}
	
	@PutMapping("/{id}")
	public Voluntario update(@PathVariable Long id, @RequestBody Voluntario v) {
		return voluntarioService.updateVoluntario(id, v);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		voluntarioService.deleteVoluntario(id);
	}

}
