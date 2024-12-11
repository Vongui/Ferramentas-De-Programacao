package br.edu.ifsp.pep.controller;

import br.edu.ifsp.pep.entity.Produto;
import br.edu.ifsp.pep.util.Relatorio;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author aluno
 */
@Named
@RequestScoped
public class ProdutoController {

    public StreamedContent gerarRelatorio() throws FileNotFoundException {

        List<Produto> dados = gerarListaProdutos();

        InputStream is = getClass()
                .getClassLoader()
                .getResourceAsStream("relatorio/produtos.jasper");

        return Relatorio.gerar(is, null, dados);
    }

    private List<Produto> gerarListaProdutos() {

        List<Produto> produtos = new ArrayList<>();
        for (int i = 1; i < 30; i++) {
            produtos.add(new Produto(i, "Produto " + i, Math.random() * 1000, i));
        }
        return produtos;
    }

}
