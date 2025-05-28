package br.com.lojamanuel.model;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CaixaUsada  implements Serializable {
    private String caixa_id;
    private List<String> produtos;
    private String observacao;
}
