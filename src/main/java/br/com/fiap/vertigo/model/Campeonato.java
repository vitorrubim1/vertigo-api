package br.com.fiap.vertigo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;


import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "CAMPEONATO", uniqueConstraints = {
        @UniqueConstraint(name = "UK_NOME_CAMPEONATO", columnNames = "NOME_CAMPEONATO"),
})
public class Campeonato {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_CAMPEONATO")
    @SequenceGenerator(name = "SQ_CAMPEONATO", sequenceName = "SQ_CAMPEONATO", allocationSize = 1)
    @Column(name = "ID_CAMPEONATO")
    private Long id;

    @Column(name = "NOME_CAMPEONATO")
    @NotBlank(message = "O nome do campeonato não pode estar vazío.")
    private String nome;

}
