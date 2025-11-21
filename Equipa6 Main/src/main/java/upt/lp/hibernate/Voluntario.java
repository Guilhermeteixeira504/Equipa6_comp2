package upt.lp.hibernate;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

    @Entity
    @Table(name = "voluntario")
    @PrimaryKeyJoinColumn(name = "utilizador_id")
      public class Voluntario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	   private long id;
	 @OneToOne
	 @MapsId 
	 @JoinColumn(name = "utilizador_id", nullable = false, unique = true)
	   private Utilizador utilizador;
	 
	 @OneToMany(mappedBy = "voluntario", cascade = CascadeType.ALL, orphanRemoval = true)
	 private List<Inscricao> inscricoes = new ArrayList<>();

	  
	
	public Voluntario() {}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Utilizador getUtilizador() {
		return utilizador;
	}
	public void setUtilizador(Utilizador utilizador) {
		this.utilizador = utilizador;
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
	    String nomeUtil = (utilizador != null ? utilizador.getNome() : "sem-utilizador");
	    int qtd = 0;
	    try {
	    	//se tiver inscrições devolve o tamanho,se não devolve 0
	        qtd = (inscricoes != null ? inscricoes.size() : 0);
	        // não dar erro se o voluntario ainda não estiver guardado e quantidade fica 0
	    } catch (Exception ignore) {
	    }

	    return "Voluntario{" +
	            "id=" + id +
	            ", utilizador=" + nomeUtil +
	            (qtd != 0 ? ", nInscricoes=" + qtd : "") +
	            '}';
	}
}


	
