package entities;

import java.util.ArrayList;
import java.util.List;

public class Gerente extends Atendente {

	protected List<Aeroporto> aeroportos = new ArrayList<>();

	public Gerente() {
		super();
	}

	public boolean cadastrarAeroporto(Aeroporto aeroporto) {
		for(int i=0; i < aeroportos.size(); i++) {
			if(aeroportos.get(i).getCodigoIATA().equals(aeroporto.getCodigoIATA())) {
				return false;
			}
		}
		return aeroportos.add(aeroporto);
	}

	public List<Aeroporto> consultarAeroportos() {
		if(aeroportos.isEmpty()) {
			return null;
		}
		return aeroportos;
	}

	public List<Cliente> consultarTodosClientes() {
		if(clientes.isEmpty()) {
			return null;
		}
		return clientes;
		
	}

}
