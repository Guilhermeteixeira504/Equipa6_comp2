package lp.Equipa6_Comp2.controller;

import org.springframework.web.bind.annotation.*;

import lp.Equipa6_Comp2.entity.Inscricao;
import lp.Equipa6_Comp2.service.InscricaoService;
import java.util.List;

@RestController
@RequestMapping("/voluntariado/inscricoes")
public class InscricaoController {
	
	private final InscricaoService inscricaoService;
	
	public InscricaoController(InscricaoService inscricaoService) {
	    this.inscricaoService = inscricaoService;
	}
	
	@GetMapping
	public List<Inscricao> getAll() {
		return inscricaoService.getAllInscricoes();
	}
	
	@GetMapping("/{id}")
	public Inscricao getById(@PathVariable Long id) {
		return inscricaoService.getInscricao(id);
	}
	
	@PostMapping
	public Inscricao create(@RequestBody Inscricao insc) {
		return inscricaoService.createInscricao(insc);
	}
	
	@PutMapping("/{id}")
	public Inscricao update(@PathVariable Long id, @RequestBody Inscricao insc) {
		return inscricaoService.updateInscricao(id, insc);
	}
	
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		inscricaoService.deleteInscricao(id);
	}
	@PostMapping("/{programaId}/{voluntarioId}/horas")
	public String registarHoras(
	        @PathVariable Long programaId,
	        @PathVariable Long voluntarioId,
	        @RequestParam int horas) {
	    
	    try {
	        boolean sucesso = inscricaoService.registarHorasVoluntario(programaId, voluntarioId, horas);
	        
	        if (sucesso) {
	            return "Horas registadas com sucesso";
	        } else {
	            return "Erro: Não foi possível registar as horas";
	        }
	        
	    } catch (Exception e) {
	        return "Erro: " + e.getMessage();
	    }
	}

    }
	   
