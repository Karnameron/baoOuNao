package br.iftm.edu.baoOuNao.domain.model.curtir;

import br.iftm.edu.baoOuNao.domain.model.proposta.Proposta;
import br.iftm.edu.baoOuNao.domain.model.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "curtir")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Curtir {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @ManyToOne
    Usuario usuario;
    @ManyToOne
    Proposta proposta;
}
