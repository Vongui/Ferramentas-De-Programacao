/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifsp.pep.converter;

import br.edu.ifsp.pep.dao.ProdutoDAO;
import br.edu.ifsp.pep.modelo.Produto;
import jakarta.enterprise.inject.spi.CDI;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;

/**
 *
 * @author aluno
 */
@FacesConverter(forClass = Produto.class)
public class ProdutoConverter implements Converter<Produto> {

    @Override
    public Produto getAsObject(FacesContext fc, UIComponent uic, String string) {
        
        if (string == null) {
            return null;
        }
        
        ProdutoDAO dao = CDI.current().select(ProdutoDAO.class).get();
        return dao.find(Integer.valueOf(string));
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Produto produto) {
        if (produto == null) {
            return null;
        }
        return produto.getId().toString();
    }

}
