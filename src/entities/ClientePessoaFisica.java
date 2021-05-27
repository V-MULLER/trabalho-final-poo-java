package entities;

public class ClientePessoaFisica extends Cliente {

	private long cpf;

	public ClientePessoaFisica() {
		super();
	}

	public ClientePessoaFisica(String nome, String email, String endereco, long cpf) {
		super(nome, email, endereco);
		this.cpf = cpf;
	}

	public long getCpf() {
		return cpf;
	}

	public void setCpf(long cpf) {
		this.cpf = cpf;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		sb.append("Nome: ");
		sb.append(getNome() + "\n");
		sb.append("Cpf: ");
		sb.append(getCpf() + "\n");
		sb.append("Email: ");
		sb.append(getEmail() + "\n");
		sb.append("Endereço: ");
		sb.append(getEndereco());
		
		return sb.toString();
	}
}
