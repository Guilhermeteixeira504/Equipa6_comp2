package lp.Equipa6_Comp2.entity;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "instituicao")
public class Instituicao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(name = "data_fundacao", nullable = false)
    private LocalDate dataFundacao;

    // Uma instituição pode ter vários programas de voluntariado
    @OneToMany
    @JoinColumn(name = "instituicao_id") 
    private List<ProgramaVoluntariado> programas = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "programa_id")
    private ProgramaVoluntariado programa;
    
    public Instituicao() {
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

    public LocalDate getDataFundacao() {
        return dataFundacao;
    }

    public void setDataFundacao(LocalDate dataFundacao) {
        this.dataFundacao = dataFundacao;
    }

    public List<ProgramaVoluntariado> getProgramas() {
        return programas;
    }

    public void setProgramas(List<ProgramaVoluntariado> programas) {
        this.programas = programas;
    }

    public void addPrograma(ProgramaVoluntariado p) {
        this.programas.add(p);
    }

    @Override
    public String toString() {
        return "Instituicao{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", dataFundacao=" + dataFundacao +
                ", nroProgramas=" + programas.size() +
                '}';
    }
}

