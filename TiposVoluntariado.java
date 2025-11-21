package lp.Equipa6_Comp2.entity;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tipo_voluntariado")
public class TiposVoluntariado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String descricao;

    // Um tipo de voluntariado pode estar associado a vários programas
    @OneToMany
    @JoinColumn(name = "tipo_voluntariado_id")
    private List<ProgramaVoluntariado> programas = new ArrayList<>();

    public TiposVoluntariado() {
    }

    public TiposVoluntariado(String descricao) {
        this.descricao = descricao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
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
        return "TiposVoluntariado{" +
                "id=" + id +
                ", descricao='" + descricao + '\'' +
                ", nroProgramas=" + programas.size() +
                '}';
    }
}