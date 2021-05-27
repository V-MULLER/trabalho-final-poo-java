package entities;

import java.util.ArrayList;
import java.util.List;

public class Cliente {

	private String nome;

	private String email;

	private String endereco;

	protected List<Carga> cargas = new ArrayList<>();
	
	public Cliente() {
	}

	public Cliente(String nome, String email, String endereco) {
		this.nome = nome;
		this.email = email;
		this.endereco = endereco;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	public boolean contratarTransporte(Carga carga) {
		if(!carga.getDestino().getPais().toUpperCase().equals("BRASIL")) {
			return false;
		}
		else {
			for(int i = 0; i < cargas.size(); i++) {
				if(cargas.get(i).getCodigo() == carga.getCodigo()) {
					return false;
				}
			}
		}
		
			return cargas.add(carga);
	}
	
	//fazer metodo toString para retornar a lista de cargas
	public List<Carga> mostrarCargas() {
		return cargas;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("Nome: ");
		sb.append(getNome() + "\n");
		sb.append("Email: ");
		sb.append(getEmail() + "\n");
		sb.append("Endereço: ");
		sb.append(getEndereco() );
		return sb.toString();
	}
}
