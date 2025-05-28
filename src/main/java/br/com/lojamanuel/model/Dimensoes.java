package br.com.lojamanuel.model;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Dimensoes  implements Serializable {
    private int altura;
    private int largura;
    private int comprimento;

}