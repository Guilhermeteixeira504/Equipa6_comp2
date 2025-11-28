package lp.Equipa6_Comp2.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "edicao")
public class Edicao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate ano;

    @Column(name = "numero_vagas", nullable = false)
    private int numeroVagas;

    @Column(name = "data_inicio", nullable = false)
    private LocalDate dataInicio;

    @Column(name = "data_fim", nullable = false)
    private LocalDate dataFim;

    // Uma edição tem várias inscrições
    @OneToMany
    @JoinColumn(name = "edicao_id")
    private List<Inscricao> inscricoes = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "programa_id")
    private ProgramaVoluntariado programa;
    
    public Edicao() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getAno() {
        return ano;
    }

    public void setAno(LocalDate ano) {
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

    public void addInscricao(Inscricao i) {
        this.inscricoes.add(i);
    }

    @Override
    public String toString() {
        return "Edicao{" +
                "id=" + id +
                ", ano=" + ano +
                ", numeroVagas=" + numeroVagas +
                ", dataInicio=" + dataInicio +
                ", dataFim=" + dataFim +
                ", nroInscricoes=" + inscricoes.size() +
                '}';
    }
}