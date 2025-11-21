package lp.Equipa6_Comp2.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "voluntario")
@PrimaryKeyJoinColumn(name = "id")
public class Voluntario extends Utilizador {

    @OneToMany(mappedBy = "voluntario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Inscricao> inscricoes = new ArrayList<>();

    public Voluntario() {}
   
    public long getId() {
        return super.getId();
    }

    public void setId(long id) {
        super.setId(id);
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
    
	
	// se nome de  utilizador ou inscrições tiverem algo dá print
    @Override
    public String toString() {
        String nomeUtil = (getNome() != null ? getNome() : "sem-utilizador");

        int qtd = 0;
        try {
            qtd = (inscricoes != null ? inscricoes.size() : 0);
        } catch (Exception ignore) {}

        return "Voluntario{" +
                "id=" + getId() +
                ", nome='" + nomeUtil + '\'' +
                (qtd != 0 ? ", nInscricoes=" + qtd : "") +
                '}';
    }
}