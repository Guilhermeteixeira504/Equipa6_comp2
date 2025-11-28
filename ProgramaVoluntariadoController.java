package lp.Equipa6_Comp2.controller;

import org.springframework.web.bind.annotation.*;
import lp.Equipa6_Comp2.entity.ProgramaVoluntariado;
import lp.Equipa6_Comp2.service.ProgramaVoluntariadoService;
import java.util.List;

@RestController
@RequestMapping("/voluntariado/programasvoluntariado")
public class ProgramaVoluntariadoController {
	
	private final ProgramaVoluntariadoService programaVoluntariadoService;
	
	public ProgramaVoluntariadoController(ProgramaVoluntariadoService programaVoluntariadoService) {
		this.programaVoluntariadoService = programaVoluntariadoService;
	}
	
	@GetMapping
	public List<ProgramaVoluntariado> getAll() {
		return programaVoluntariadoService.getAllProgramasVoluntariado();
	}
	
	@GetMapping("/{id}")
	public ProgramaVoluntariado getById(@PathVariable Long id) {
		return programaVoluntariadoService.getProgramaVoluntariado(id);
	}
	
	@PostMapping
	public ProgramaVoluntariado create(@RequestBody ProgramaVoluntariado pv) {
		return programaVoluntariadoService.createProgramaVoluntariado(pv);
	}
	
	@PutMapping("/{id}")
	public ProgramaVoluntariado update(@PathVariable Long id, @RequestBody ProgramaVoluntariado pv) {
		return programaVoluntariadoService.updateProgramaVoluntariado(id, pv);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		programaVoluntariadoService.deleteProgramaVoluntariado(id);
	}

}
