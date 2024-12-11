/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifsp.pep;

import br.edu.ifsp.pep.dao.PessoaDAO;
import br.edu.ifsp.pep.dao.VeiculoDAO;
import br.edu.ifsp.pep.entity.Pessoa;
import br.edu.ifsp.pep.entity.Veiculo;

/**
 *
 * @author aluno
 */
public class Teste {
    
    
    
    public static void main(String[] args) {
        PessoaDAO pessoaDAO = new PessoaDAO();
        pessoaDAO.inserir(new Pessoa());
        
        
        VeiculoDAO veiculoDAO = new VeiculoDAO();
        veiculoDAO.inserir(new Veiculo());
        
//        veiculoDAO.inserir(new Pessoa());
        
        
        
    }
    
    
}
