package br.com.lojamanuel.model;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RespostaEmpacotamento implements Serializable {
    private int pedido_id;
    private List<CaixaUsada> caixas;

}