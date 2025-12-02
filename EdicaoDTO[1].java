package DTO;

import java.time.LocalDate;
import java.util.List;

import lp.Equipa6_Comp2.entity.Inscricao;
import lp.Equipa6_Comp2.entity.ProgramaVoluntariado;

public class EdicaoDTO {
	private long id;
	private int ano;
	private int numeroVagas;
	private LocalDate dataInicio;
	private LocalDate dataFim;
	private List<Inscricao> inscricoes;
	private ProgramaVoluntariado programa;
	public EdicaoDTO() {
		
	}
	public EdicaoDTO(long id, int ano, int numeroVagas, LocalDate dataInicio, LocalDate dataFim,
			List<Inscricao> inscricoes, ProgramaVoluntariado programa) {
		this.id = id;
		this.ano = ano;
		this.numeroVagas = numeroVagas;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		this.inscricoes = inscricoes;
		this.programa = programa;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getAno() {
		return ano;
	}
	public void setAno(int ano) {
		this.ano = ano;
	}
	public int getNumeroVagas() {
		return numeroVagas;
	}
	public void setNumeroVagas(int numeroVagas) {
		this.numeroVagas = numeroVagas;
	}
	public LocalDate getDataInicio() {
		return dataInicio;
	}
	public void setDataInicio(LocalDate dataInicio) {
		this.dataInicio = dataInicio;
	}
	public LocalDate getDataFim() {
		return dataFim;
	}
	public void setDataFim(LocalDate dataFim) {
		this.dataFim = dataFim;
	}
	public List<Inscricao> getInscricoes() {
		return inscricoes;
	}
	public void setInscricoes(List<Inscricao> inscricoes) {
		this.inscricoes = inscricoes;
	}
	public ProgramaVoluntariado getPrograma() {
		return programa;
	}
	public void setPrograma(ProgramaVoluntariado programa) {
		this.programa = programa;
	}
	
	
	
	
	
	

}
