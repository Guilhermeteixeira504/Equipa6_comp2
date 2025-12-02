package lp.Equipa6_Comp2.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "voluntario")
@PrimaryKeyJoinColumn(name = "utilizador_id")
public class Voluntario extends Utilizador {

    @OneToMany(mappedBy = "voluntario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Inscricao> inscricoes = new ArrayList<>();

    public Voluntario() {
        super();
        this.setTipoUtilizador("voluntario");
    }

    public List<Inscricao> getInscricoes() {
        return inscricoes;
    }

    public void setInscricoes(List<Inscricao> inscricoes) {
        this.inscricoes = inscricoes;
    }

    public void addInscricao(Inscricao inscricao) {
        inscricoes.add(inscricao);
        inscricao.setVoluntario(this);
    }

    public void removeInscricao(Inscricao inscricao) {
        inscricoes.remove(inscricao);
        inscricao.setVoluntario(null);
    }

	
    // se nome de utilizador ou inscrições tiverem algo dá print
    @Override
    public String toString() {
        int qtd = 0;
        try {
            // se tiver inscrições devolve o tamanho,se não devolve 0
            qtd = (inscricoes != null ? inscricoes.size() : 0);
        } catch (Exception ignore) {}

        return "Voluntario{" +
                "id=" + getId() +
                ", nome=" + getNome() +
                (qtd != 0 ? ", nInscricoes=" + qtd : "") +
                '}';
    }
}