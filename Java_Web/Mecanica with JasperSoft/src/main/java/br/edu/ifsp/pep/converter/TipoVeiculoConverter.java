/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifsp.pep.converter;

import br.edu.ifsp.pep.dao.TipoVeiculoDAO;
import br.edu.ifsp.pep.entity.TipoVeiculo;
import jakarta.enterprise.inject.spi.CDI;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;
import jakarta.inject.Inject;

/**
 *
 * @author aluno
 */
@FacesConverter(forClass = TipoVeiculo.class)
public class TipoVeiculoConverter implements Converter<TipoVeiculo> {
    @Override
    public TipoVeiculo getAsObject(FacesContext fc, UIComponent uic, String string) {
        if (string == null)
            return null;
        
        TipoVeiculoDAO dao = CDI.current().select(TipoVeiculoDAO.class).get();
        return dao.buscarPeloCodigo(Long.valueOf(string));        
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, TipoVeiculo t) {
        return t == null ? null : t.getCodigo().toString();
        
    }
    
}
