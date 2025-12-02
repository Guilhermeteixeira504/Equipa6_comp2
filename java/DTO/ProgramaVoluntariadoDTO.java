package DTO;

import java.time.LocalDate;
import java.util.List;


public class ProgramaVoluntariadoDTO {
	private long id;
	private String titulo;
	private String descricao;
	private String localizacao;
	private int numeroVagas;
	private LocalDate dataInicio;
	private LocalDate dataFim;
	private int totalHoras;
	private List<Long> inscricoesId;
	private List<Long> edicoesId;
	private Long tiposVoluntariadoId;
	private List<Long> instituicoesId;
	
	public ProgramaVoluntariadoDTO() {}

	public ProgramaVoluntariadoDTO(long id, String titulo, String descricao, String localizacao, int numeroVagas,
			LocalDate dataInicio, LocalDate dataFim, int totalHoras, List<Long> inscricoesId, List<Long> edicoesId,
			Long tiposVoluntariadoId, List<Long> instituicoesId) {
		this.id = id;
		this.titulo = titulo;
		this.descricao = descricao;
		this.localizacao = localizacao;
		this.numeroVagas = numeroVagas;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		this.totalHoras = totalHoras;
		this.inscricoesId = inscricoesId;
		this.edicoesId = edicoesId;
		this.tiposVoluntariadoId = tiposVoluntariadoId;
		this.instituicoesId = instituicoesId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getLocalizacao() {
		return localizacao;
	}

	public void setLocalizacao(String localizacao) {
		this.localizacao = localizacao;
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

	public int getTotalHoras() {
		return totalHoras;
	}

	public void setTotalHoras(int totalHoras) {
		this.totalHoras = totalHoras;
	}

	public List<Long> getInscricoesId() {
		return inscricoesId;
	}

	public void setInscricoesId(List<Long> inscricoesId) {
		this.inscricoesId = inscricoesId;
	}

	public List<Long> getEdicoesId() {
		return edicoesId;
	}

	public void setEdicoesId(List<Long> edicoesId) {
		this.edicoesId = edicoesId;
	}

	public Long getTiposVoluntariadoId() {
		return tiposVoluntariadoId;
	}

	public void setTiposVoluntariadoId(Long tiposVoluntariadoId) {
		this.tiposVoluntariadoId = tiposVoluntariadoId;
	}

	public List<Long> getInstituicoesId() {
		return instituicoesId;
	}

	public void setInstituicoesId(List<Long> instituicoesId) {
		this.instituicoesId = instituicoesId;
	}

	
	
}
