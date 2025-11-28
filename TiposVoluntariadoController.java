package lp.Equipa6_Comp2.controller;

import org.springframework.web.bind.annotation.*;
import lp.Equipa6_Comp2.entity.TiposVoluntariado;
import lp.Equipa6_Comp2.service.TiposVoluntariadoService;
import java.util.List;

@RestController
@RequestMapping("/voluntariado/tiposvoluntariados")
public class TiposVoluntariadoController {
	
	private final TiposVoluntariadoService tiposVoluntariadoService;
	
	public TiposVoluntariadoController(TiposVoluntariadoService tiposVoluntariadoService) {
		this.tiposVoluntariadoService = tiposVoluntariadoService;
	}
	
	@GetMapping
	public List<TiposVoluntariado> getAll() {
		return tiposVoluntariadoService.getAllTiposVoluntariados();
	}
	
	@GetMapping("/{id}")
	public TiposVoluntariado getById(@PathVariable Long id) {
		return tiposVoluntariadoService.getTiposVoluntariado(id);
	}
	
	@PostMapping
	public TiposVoluntariado create(@RequestBody TiposVoluntariado tv) {
		return tiposVoluntariadoService.createTiposVoluntariado(tv);
	}
	
	@PutMapping("/{id}")
	public TiposVoluntariado update(@PathVariable Long id, @RequestBody TiposVoluntariado tv) {
		return tiposVoluntariadoService.updateTiposVoluntariado(id, tv);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		tiposVoluntariadoService.deleteTiposVoluntariado(id);
	}

}
