package br.com.lojamanuel.controller;

import java.util.List;

import br.com.lojamanuel.model.Pedido;
import br.com.lojamanuel.model.RespostaEmpacotamento;
import br.com.lojamanuel.service.EmpacotamentoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/pedidos")
@Tag(name = "Empacotamento de Pedidos", description = "API para empacotamento de produtos em caixas")
public class PedidoController {

    private final EmpacotamentoService empacotamentoService;

    public PedidoController(EmpacotamentoService empacotamentoService) {
        this.empacotamentoService = empacotamentoService;
    }

    @PostMapping("/empacotar")
    @Operation(summary = "Empacota produtos em caixas disponíveis")
    @ApiResponse(responseCode = "200", description = "Pedidos empacotados com sucesso")
    public ResponseEntity<List<RespostaEmpacotamento>> empacotarPedidos(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Lista de pedidos para empacotar", required = true)
            @RequestBody List<Pedido> pedidos) {
        List<RespostaEmpacotamento> resposta = empacotamentoService.empacotarPedidos(pedidos);
        return ResponseEntity.ok(resposta);
    }
}
