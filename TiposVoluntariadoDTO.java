package DTO;



import java.util.List;

import lp.Equipa6_Comp2.entity.ProgramaVoluntariado;

public class TiposVoluntariadoDTO {
    private Long id;
    private String nome;
    private List<ProgramaVoluntariado> programas;
    private ProgramaVoluntariado programa;

    public TiposVoluntariadoDTO() {}

	public TiposVoluntariadoDTO(Long id, String nome, List<ProgramaVoluntariado> programas,
			ProgramaVoluntariado programa) {
		this.id = id;
		this.nome = nome;
		this.programas = programas;
		this.programa = programa;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<ProgramaVoluntariado> getProgramas() {
		return programas;
	}

	public void setProgramas(List<ProgramaVoluntariado> programas) {
		this.programas = programas;
	}

	public ProgramaVoluntariado getPrograma() {
		return programa;
	}

	public void setPrograma(ProgramaVoluntariado programa) {
		this.programa = programa;
	}
	
	

 

   
}
