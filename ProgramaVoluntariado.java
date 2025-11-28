package lp.Equipa6_Comp2.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;

@Entity
@Table(name = "programa_voluntariado")
public class ProgramaVoluntariado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String titulo;

    @Column(columnDefinition = "TEXT")
    private String descricao;

    private String localizacao;

    @Column(name = "numero_vagas")
    private int numeroVagas;

    @Column(name = "data_inicio")
    private LocalDate dataInicio;

    @Column(name = "data_fim")
    private LocalDate dataFim;

    @Column(name = "total_horas")
    private int totalHoras;

    @OneToMany(mappedBy = "programa", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Inscricao> inscricoes = new ArrayList<>();
    
    @OneToMany(mappedBy="programa", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Edicao> edicoes = new ArrayList<>();
    
    @OneToMany(mappedBy="programa", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TiposVoluntariado> tiposVolutariado = new ArrayList<>();
    
    @OneToMany(mappedBy="programa", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Instituicao> instituicoes = new ArrayList<>();
    
    @ManyToOne
    @JoinColumn(name = "inscricoes")
    private Inscricao incricao;
    
    @ManyToOne
    @JoinColumn(name = "edicao_id")
    private Edicao edicao;
    
    @ManyToOne
    @JoinColumn(name = "instituicao_id")
    private Instituicao instituicao;

    @ManyToOne
    @JoinColumn(name = "tipo_voluntariado_id")
    private TiposVoluntariado tipoVoluntariado;
	
    public ProgramaVoluntariado() {}

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

    public List<Inscricao> getInscricoes() {
    	return inscricoes; 
    	} 
    public void setInscricoes(List<Inscricao> inscricoes) { 
    	this.inscricoes = inscricoes; 
    	}
    public void addInscricao(Inscricao inscricao) {
        inscricoes.add(inscricao);
        inscricao.setPrograma(this); 
    }
    
    public List<Edicao> getEdicoes() {
		return edicoes;
	}

	public void setEdicoes(List<Edicao> edicoes) {
		this.edicoes = edicoes;
	}

	public List<TiposVoluntariado> getTiposVolutariado() {
		return tiposVolutariado;
	}

	public void setTiposVolutariado(List<TiposVoluntariado> tiposVolutariado) {
		this.tiposVolutariado = tiposVolutariado;
	}
	
	
	public List<Instituicao> getInstituicoes() {
		return instituicoes;
	}

	public void setInstituicoes(List<Instituicao> instituicoes) {
		this.instituicoes = instituicoes;
	}

	@Override
    public String toString() {
        return "ProgramaVoluntariado{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", descricao='" + descricao + '\'' +
                ", localizacao='" + localizacao + '\'' +
                ", numeroVagas=" + numeroVagas +
                ", dataInicio=" + dataInicio +
                ", dataFim=" + dataFim +
                ", totalHoras=" + totalHoras +
                '}';
    }
}
    