package lp.Equipa6_Comp2.entity;
import jakarta.persistence.*;

@Entity
@Table(name = "utilizador")
@Inheritance(strategy = InheritanceType.JOINED)
public class Utilizador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String morada;

    @Column(name = "telemovel")
    private int telemovel;

    @Column(name = "tipoUtilizador", nullable = false, length = 20)
    private String tipoUtilizador = "USER"; // valor padrão

    public Utilizador() {}

    // Getters e Setters
    public long getId() { 
    	return id; 
    	}
    public void setId(long id) { 
    	this.id = id; 
    	}

    public String getNome() { 
    	return nome; 
    	}
    public void setNome(String nome) { 
    	this.nome = nome; 
    	}

    public String getEmail() { 
    	return email; 
    	}
    public void setEmail(String email) { 
    	this.email = email; 
    	}

    public String getPassword() { 
    	return password; 
    	}
    public void setPassword(String password) { 
    	this.password = password;
    	}

    public String getMorada() { 
    	return morada; 
    	}
    public void setMorada(String morada) { 
    	this.morada = morada;
    	}

    public int getTelemovel() {
    	return telemovel; 
    	}
    public void setTelemovel(int telemovel) { 
    	this.telemovel = telemovel;
    	}

    public String getTipoUtilizador() { 
    	return tipoUtilizador;
    	}
    public void setTipoUtilizador(String tipoUtilizador) { 
    	this.tipoUtilizador = tipoUtilizador;
    	}

    @Override
    public String toString() {
        return "Utilizador{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", tipoUtilizador='" + tipoUtilizador + '\'' +
                '}';
    }
}
