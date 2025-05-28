package br.com.lojamanuel.model;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Pedido  implements Serializable {
    private int pedido_id;
    private List<Produto> produtos;
}
