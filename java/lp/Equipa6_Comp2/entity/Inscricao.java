package lp.Equipa6_Comp2.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "inscricao")
public class Inscricao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "voluntario_id")
    private Voluntario voluntario;

    @ManyToOne
    @JoinColumn(name = "programa_id")
    private ProgramaVoluntariado programa;
    
    @ManyToOne
    private Edicao edicao;


    @Column(name = "nHorasRealizadas")
    private int nHorasRealizadas;

    public Inscricao() {}

    public long getId() {
        return id;
    }
    public void setId(long id) {
		this.id = id;
	}

	public Voluntario getVoluntario() {
        return voluntario;
    }

    public void setVoluntario(Voluntario voluntario) {
        this.voluntario = voluntario;
    }

    public ProgramaVoluntariado getPrograma() {
        return programa;
    }

    public void setPrograma(ProgramaVoluntariado programa) {
        this.programa = programa;
    }

    public int getNHorasRealizadas() {
        return nHorasRealizadas;
    }
    public void setNHorasRealizadas(int nHorasRealizadas) {
        this.nHorasRealizadas = nHorasRealizadas;
    }
    
    public Edicao getEdicao() {
		return edicao;
	}

	public void setEdicao(Edicao edicao) {
		this.edicao = edicao;
	}

	// condições que verificam se o voluntário ou o programa estão vazios ou não, se não estiverem dá print
    @Override
    public String toString() {
        return "Inscricao{" +
                "id=" + id +
                ", voluntario=" + (voluntario != null ? voluntario.getId() : "null") +
                ", programa=" + (programa != null ? programa.getId() : "null") +
                ", horas=" + nHorasRealizadas +
                '}';
    }
}
