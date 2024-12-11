package br.edu.ifsp.pep.controller;

import br.edu.ifsp.pep.entity.Produto;
import br.edu.ifsp.pep.util.Relatorio;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.primefaces.model.StreamedContent;

@Named
@RequestScoped
public class ProdutoController {

    
    public StreamedContent gerarRelatorio() throws FileNotFoundException {
        
        List<Produto> dados = gerarListaProdutos();
        File file = new File("/home/aluno/Documentos/Ferramentas_Programacao/Ferramentas-De-Programacao/Ferramentas-De-Programacao/Projeto-Mecanica/br/edu/ifsp/pep/produtos");
        InputStream is = new FileInputStream(file);
        
        return Relatorio.gerar(is, null, dados);
    }
            
    private List<Produto> gerarListaProdutos(){
        List<Produto> produtos = new ArrayList();
        for (int i = 0; i < 30; i++) {
            produtos.add(new Produto(i, "Produto " + i, Math.random()*1000, i*3));
        }
        return produtos;
    }  
          
            
}
