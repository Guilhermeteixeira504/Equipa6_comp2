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
    private String nome;

    // Um tipo de voluntariado pode estar associado a vários programas
    @OneToMany(mappedBy = "tipoVoluntariado", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProgramaVoluntariado> programas = new ArrayList<>();
    

    public TiposVoluntariado() {
    }

    public TiposVoluntariado(String nome) {
        this.nome = nome;
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
    public void addPrograma(ProgramaVoluntariado programa) {
        programas.add(programa);
        programa.setTiposVoluntariado(this); 
    }


    @Override
    public String toString() {
        return "TiposVoluntariado{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", nroProgramas=" + programas.size() +
                '}';
    }
}