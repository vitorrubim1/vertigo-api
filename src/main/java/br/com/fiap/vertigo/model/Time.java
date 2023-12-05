package br.com.fiap.vertigo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "TIME", uniqueConstraints = {
        @UniqueConstraint(name = "UK_NOME_TIME", columnNames = "NOME_TIME"),
})
public class Time {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_TIME")
    @SequenceGenerator(name = "SQ_TIME", sequenceName = "SQ_TIME", allocationSize = 1)
    @Column(name = "ID_TIME")
    private Long id;

    @NotBlank(message = "O nome do time não pode estar vazío.")
    @Column(name = "NOME_TIME", nullable = false)
    private String nome;

    @NotBlank(message = "O imagem do time não pode estar vazío.")
    @Column(name = "IMAGEM_TIME")
    private String imagem;

    @NotBlank(message = "O campo vitórias não pode estar vazio.")
    @PositiveOrZero(message = "As vitórias precisa ter um número maior ou igual a zero")
    @Column(name = "VITORIAS_TIME", nullable = false)
    private int vitorias;

    @NotBlank(message = "O campo derrotas não pode estar vazio.")
    @PositiveOrZero(message = "As derrotas precisa ter um número maior ou igual a zero")
    @Column(name = "DERROTAS_TIME", nullable = false)
    private int derrotas;



}
