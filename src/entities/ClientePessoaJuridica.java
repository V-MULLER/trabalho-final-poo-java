package entities;

public class ClientePessoaJuridica extends Cliente {

	private long cnpj;

	private String nomeFantasia;

	public ClientePessoaJuridica() {
		super();
	}

	public ClientePessoaJuridica(String nome, String email, String endereco, long cnpj, String nomeFantasia) {
		super(nome, email, endereco);
		this.cnpj = cnpj;
		this.nomeFantasia = nomeFantasia;
	}

	public long getCnpj() {
		return cnpj;
	}

	public void setCnpj(long cnpj) {
		this.cnpj = cnpj;
	}

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}
	
	//Dá o desconto de 3% ao cliente empresarial
	@Override
	public boolean contratarTransporte(Carga carga) {
		if(!carga.getDestino().getPais().toUpperCase().equals("BRASIL")) {
			return false;
		}else {
			for(int i = 0; i < cargas.size(); i++) {
				if(cargas.get(i).getCodigo() == carga.getCodigo()) {
					return false;
				}
			}
		}
			carga.setValorFrete(carga.getValorFrete() * 0.97);
			return cargas.add(carga);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		sb.append("Nome: ");
		sb.append(getNome() + "\n");
		sb.append("Nome da empresa: ");
		sb.append(getNomeFantasia() + "\n");
		sb.append("Cnpj: ");
		sb.append(getCnpj() + "\n");
		sb.append("Email: ");
		sb.append(getEmail() + "\n");
		sb.append("Endereço: ");
		sb.append(getEndereco());
		
		return sb.toString();
	}
}
