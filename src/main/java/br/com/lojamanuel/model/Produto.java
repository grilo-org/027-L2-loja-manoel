package br.com.lojamanuel.model;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Produto  implements Serializable {
    private String produto_id;
    private Dimensoes dimensoes;
}
