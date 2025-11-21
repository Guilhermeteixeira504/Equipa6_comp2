package upt.lp.hibernate;

import jakarta.persistence.*;

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


    @Column(name = "nHorasRealizadas")
    private int nHorasRealizadas;

    public Inscricao() {}

    public long getId() {
        return id;
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
