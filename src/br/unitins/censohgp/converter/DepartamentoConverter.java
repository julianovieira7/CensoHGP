package br.unitins.censohgp.converter;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.inject.Named;

import br.unitins.censohgp.model.Departamento;
import br.unitins.censohgp.model.Departamentos;

@Named
@FacesConverter(value = "DepartamentoConverter", managed = true)
public class DepartamentoConverter implements Converter<Object>{
	
	@Inject
	private Departamentos departamento;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		// TODO Auto-generated method stub
		// se o numero for nulo eu tenho que criar uma instancia e n�o deixar ele ir atoa
		List<Departamento> auxdepartamento = departamento.getDepartamentos();
		if(!(value.equals("null")) && value.trim().length() > 0) {
			try {
				Integer aux = Integer.parseInt(value);
				for (Departamento aux2 : auxdepartamento) {
					if(aux2.getIdlocalTransferencia().equals(aux)) {
						Integer aux3 = auxdepartamento.indexOf(aux2);
						return auxdepartamento.get(aux3);
					}
				}
				//return pacientes.getPacientes().get(aux);
			} catch(NumberFormatException e) {
               throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid theme."));
            }
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		// TODO Auto-generated method stub
		if(value != null) {
			return String.valueOf(((Departamento) value).getIdlocalTransferencia());
		}
		return null;
	}
	

}
