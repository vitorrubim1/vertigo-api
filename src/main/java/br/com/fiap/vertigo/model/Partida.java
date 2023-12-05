package br.com.fiap.vertigo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Partida {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_PARTIDA")
    @SequenceGenerator(name = "SQ_PARTIDA", sequenceName = "SQ_PARTIDA", allocationSize = 1)
    @Column(name = "ID_PARTIDA")
    private Long id;

    @ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(name = "TIMES_PARTIDA", joinColumns = {
            @JoinColumn(name = "ID_PARTIDA", referencedColumnName = "ID_PARTIDA", foreignKey = @ForeignKey(name = "FK_PARTIDA_TIMES"))
    }, inverseJoinColumns = {
            @JoinColumn(name = "ID_TIMES", referencedColumnName = "ID_TIMES", foreignKey = @ForeignKey(name = "FK_TIMES_PARTIDA"))
    })
    private Set<Time> times = new LinkedHashSet<>();

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @NotBlank(message = "O campo nome campeonato não pode estar vazio.")
    @JoinColumn(
            name = "ID_CAMPEONATO",
            referencedColumnName = "ID_CAMPEONATO",
            foreignKey = @ForeignKey(name = "FK_CAMPEONATO_PARTIDA"),
            nullable = false
    )
    private Campeonato nome;

    @Column(name = "DT_PARTIDA")
    @FutureOrPresent(message = "A data tem que ser uma data atual ou uma data futura.")
    @NotNull(message = "A data da partida não pode estar vazío.")
    private LocalDateTime data;

}

