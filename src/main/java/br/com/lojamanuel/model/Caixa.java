package br.com.lojamanuel.model;


import lombok.*;

import java.io.Serializable;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Caixa implements Serializable {
    private String caixa_id;
    private Dimensoes dimensoes;

}
