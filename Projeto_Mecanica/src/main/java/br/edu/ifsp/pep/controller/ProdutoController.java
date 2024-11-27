package br.edu.ifsp.pep.controller;

import br.edu.ifsp.pep.entity.Produto;
import br.edu.ifsp.pep.util.Relatorio;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.primefaces.model.StreamedContent;

@Named
@RequestScoped
public class ProdutoController {

    
    public StreamedContent gerarRelatorio() {
        
        List<Produto> dados = gerarListaProdutos();
        
        InputStream jasper = getClass().getResourceAsStream("/br/edu/ifsp/pep/relatorio/produtos");
        
        Relatorio.gerar(jasper, null, dados);
        
        
    }
            
    private List<Produto> gerarListaProdutos(){
        List<Produto> produtos = new ArrayList();
        for (int i = 0; i < 30; i++) {
            produtos.add(new Produto(i, "Produto " + i, Math.random()*1000));
        }
        return produtos;
    }  
          
            
}
