package br.com.preco_justo.service;

import br.com.preco_justo.domain.Pato.Pato;

import java.util.List;

public class VendaService {

    public static void calculaValorPatos(List<Pato> listaPatos, Boolean desconto) {
        listaPatos.forEach(p -> {
            var numFilhos = p.getFilhos().size();
            var valorDesconto = desconto ? 0.8 : 1;
            Double valorFinal;

            //Pato com 1 filho R$ 50,00;
            //Pato com 2 filhos R$ 25,00;
            //Pato sem filhos R$ 70,00;
            switch(numFilhos) {
                case 0:
                    valorFinal = 70.00;
                    break;
                case 1:
                    valorFinal = 50.00;
                    break;
                default:
                    valorFinal = 25.00;
                    break;
            }

            valorFinal *= valorDesconto;

            p.setValorVenda(valorFinal);

        });
    }
}
