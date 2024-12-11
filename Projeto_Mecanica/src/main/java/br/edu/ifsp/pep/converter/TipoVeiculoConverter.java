package br.edu.ifsp.pep.converter;

import br.edu.ifsp.pep.dao.TipoVeiculoDAO;
import br.edu.ifsp.pep.entity.TipoVeiculo;
import jakarta.enterprise.inject.spi.CDI;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;

@FacesConverter(forClass = TipoVeiculo.class)
public class TipoVeiculoConverter implements Converter<TipoVeiculo>{

    @Override
    public TipoVeiculo getAsObject(FacesContext fc, UIComponent uic, String string) {
        if(string == null)
           return null;
        
        TipoVeiculoDAO dao = CDI.current().select(TipoVeiculoDAO.class).get();
        return dao.buscarPeloCodigo(Long.valueOf(string));
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, TipoVeiculo t) {
        if(t == null)
            return null;
        
        return t.getCodigo().toString();
    }

    
}
