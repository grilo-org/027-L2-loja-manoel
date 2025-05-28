package br.com.lojamanuel.service;

import java.util.*;
import java.util.stream.Collectors;

import br.com.lojamanuel.model.*;
import org.springframework.stereotype.Service;


@Service
public class EmpacotamentoService {

    private final List<Caixa> caixasDisponiveis;

    public EmpacotamentoService() {
        this.caixasDisponiveis = Arrays.asList(
                new Caixa("Caixa 1", new Dimensoes(30, 40, 80)),
                new Caixa("Caixa 2", new Dimensoes(80, 50, 40)),
                new Caixa("Caixa 3", new Dimensoes(50, 80, 60))
        );
    }

    public List<RespostaEmpacotamento> empacotarPedidos(List<Pedido> pedidos) {
        return pedidos.stream()
                .map(this::empacotarPedido)
                .collect(Collectors.toList());
    }

    private RespostaEmpacotamento empacotarPedido(Pedido pedido) {
        RespostaEmpacotamento resposta = new RespostaEmpacotamento();
        resposta.setPedido_id(pedido.getPedido_id());

        List<CaixaUsada> caixasUsadas = new ArrayList<>();
        List<Produto> produtosRestantes = new ArrayList<>(pedido.getProdutos());

        while (!produtosRestantes.isEmpty()) {
            Caixa melhorCaixa = encontrarMelhorCaixa(produtosRestantes);

            if (melhorCaixa == null) {
                CaixaUsada caixa = new CaixaUsada();
                caixa.setProdutos(produtosRestantes.stream()
                        .map(Produto::getProduto_id)
                        .collect(Collectors.toList()));
                caixa.setObservacao("Produto não cabe em nenhuma caixa disponível.");
                caixasUsadas.add(caixa);
                break;
            }

            CaixaUsada caixaUsada = new CaixaUsada();
            caixaUsada.setCaixa_id(melhorCaixa.getCaixa_id());

            List<Produto> produtosNaCaixa = new ArrayList<>();
            Iterator<Produto> iterator = produtosRestantes.iterator();

            while (iterator.hasNext()) {
                Produto produto = iterator.next();
                if (cabeNaCaixa(produto, melhorCaixa)) {
                    produtosNaCaixa.add(produto);
                    iterator.remove();
                }
            }

            caixaUsada.setProdutos(produtosNaCaixa.stream()
                    .map(Produto::getProduto_id)
                    .collect(Collectors.toList()));

            caixasUsadas.add(caixaUsada);
        }

        resposta.setCaixas(caixasUsadas);
        return resposta;
    }

    private Caixa encontrarMelhorCaixa(List<Produto> produtos) {
        return caixasDisponiveis.stream()
                .filter(caixa -> produtos.stream()
                        .anyMatch(produto -> cabeNaCaixa(produto, caixa)))
                .min(Comparator.comparingInt(caixa -> {
                    int volume = caixa.getDimensoes().getAltura() *
                            caixa.getDimensoes().getLargura() *
                            caixa.getDimensoes().getComprimento();
                    return -volume; // Ordena do maior para o menor
                }))
                .orElse(null);
    }

    private boolean cabeNaCaixa(Produto produto, Caixa caixa) {
        Dimensoes dimProduto = produto.getDimensoes();
        Dimensoes dimCaixa = caixa.getDimensoes();

        // Verifica todas as rotações possíveis
        return (dimProduto.getAltura() <= dimCaixa.getAltura() &&
                dimProduto.getLargura() <= dimCaixa.getLargura() &&
                dimProduto.getComprimento() <= dimCaixa.getComprimento())
                || (dimProduto.getAltura() <= dimCaixa.getAltura() &&
                dimProduto.getLargura() <= dimCaixa.getComprimento() &&
                dimProduto.getComprimento() <= dimCaixa.getLargura())
                || (dimProduto.getAltura() <= dimCaixa.getLargura() &&
                dimProduto.getLargura() <= dimCaixa.getAltura() &&
                dimProduto.getComprimento() <= dimCaixa.getComprimento())
                || (dimProduto.getAltura() <= dimCaixa.getLargura() &&
                dimProduto.getLargura() <= dimCaixa.getComprimento() &&
                dimProduto.getComprimento() <= dimCaixa.getAltura())
                || (dimProduto.getAltura() <= dimCaixa.getComprimento() &&
                dimProduto.getLargura() <= dimCaixa.getAltura() &&
                dimProduto.getComprimento() <= dimCaixa.getLargura())
                || (dimProduto.getAltura() <= dimCaixa.getComprimento() &&
                dimProduto.getLargura() <= dimCaixa.getLargura() &&
                dimProduto.getComprimento() <= dimCaixa.getAltura());
    }
}
