package br.com.lojamanuel.service;


import br.com.lojamanuel.model.Dimensoes;
import br.com.lojamanuel.model.Pedido;
import br.com.lojamanuel.model.Produto;
import br.com.lojamanuel.model.RespostaEmpacotamento;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class EmpacotamentoServiceTest {

    private EmpacotamentoService empacotamentoService;

    @BeforeEach
    void setUp() {
        empacotamentoService = new EmpacotamentoService();
    }

    @Test
    void testEmpacotarPedidoSimples() {
        Pedido pedido = new Pedido(1, Arrays.asList(
                new Produto("PS5", new Dimensoes(40, 10, 25)),
                new Produto("Volante", new Dimensoes(40, 30, 30))
        ));

        List<RespostaEmpacotamento> resposta = empacotamentoService.empacotarPedidos(Arrays.asList(pedido));

        assertEquals(1, resposta.size());
        assertEquals(1, resposta.get(0).getCaixas().size());
        assertEquals("Caixa 2", resposta.get(0).getCaixas().get(0).getCaixa_id());
        assertEquals(2, resposta.get(0).getCaixas().get(0).getProdutos().size());
    }

    @Test
    void testProdutoGrandeDemais() {
        Pedido pedido = new Pedido(1, Arrays.asList(
                new Produto("Cadeira Gamer", new Dimensoes(120, 60, 70))
        ));

        List<RespostaEmpacotamento> resposta = empacotamentoService.empacotarPedidos(Arrays.asList(pedido));

        assertEquals(1, resposta.size());
        assertEquals(1, resposta.get(0).getCaixas().size());
        assertNull(resposta.get(0).getCaixas().get(0).getCaixa_id());
        assertNotNull(resposta.get(0).getCaixas().get(0).getObservacao());
    }
}