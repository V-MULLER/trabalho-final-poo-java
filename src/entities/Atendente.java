package entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import acmeEnum.CargaStatus;

public class Atendente {
	List<Cliente> clientes = new ArrayList<>();

	public Atendente() {

	}

	public boolean cadastrarCliente(Cliente cliente) {
		if (cliente instanceof ClientePessoaFisica) {
			return cadastrarPessoaFisica((ClientePessoaFisica) cliente);
		} else if (cliente instanceof ClientePessoaJuridica) {
			return cadastrarPessoaJuridica((ClientePessoaJuridica) cliente);
		}
		return false;
	}

	private boolean cadastrarPessoaFisica(ClientePessoaFisica cliente) {
		List<ClientePessoaFisica> pf = new ArrayList<>();
		for (int i = 0; i < clientes.size(); i++) {
			if (clientes.get(i) instanceof ClientePessoaFisica) {
				pf.add((ClientePessoaFisica) clientes.get(i));
			}
		}
		for(int i =0; i < pf.size(); i++) {
			if (cliente.getCpf() == pf.get(i).getCpf()) {
				return false;
			}
		}
		return clientes.add(cliente);
	}

	private boolean cadastrarPessoaJuridica(ClientePessoaJuridica cliente) {
		List<ClientePessoaJuridica> pj = new ArrayList<>();
		for (int i = 0; i < clientes.size(); i++) {
			if (clientes.get(i) instanceof ClientePessoaJuridica) {
				pj.add((ClientePessoaJuridica) clientes.get(i));
			}
		}
		for(int i =0; i < pj.size(); i++) {
			if (cliente.getCnpj() == pj.get(i).getCnpj()) {
				return false;
			}
		}
		return clientes.add(cliente);
	}

	public boolean cadastrarTransporte(Cliente cliente, Carga carga) {
		if (cliente instanceof ClientePessoaFisica) {
			return cadastrarTransportePf((ClientePessoaFisica) cliente, carga);
		} else if (cliente instanceof ClientePessoaJuridica) {
			return cadastrarTransportePj((ClientePessoaJuridica) cliente, carga);
		}
		return false;
	}

	private boolean cadastrarTransportePf(ClientePessoaFisica cliente, Carga carga) {
		List<ClientePessoaFisica> pf = new ArrayList<>();
		for (int i = 0; i < clientes.size(); i++) {
			if (clientes.get(i) instanceof ClientePessoaFisica) {
				pf.add((ClientePessoaFisica) clientes.get(i));
			}
		}
		for(int i = 0; i < pf.size(); i++) {
			if (cliente.getCpf() == pf.get(i).getCpf()) {
				return cliente.contratarTransporte(carga);
			}
		}
		return false;
	}

	private boolean cadastrarTransportePj(ClientePessoaJuridica cliente, Carga carga) {
		List<ClientePessoaJuridica> pj = new ArrayList<>();
		
		for (int i = 0; i < clientes.size(); i++) {
			if (clientes.get(i) instanceof ClientePessoaJuridica) {
				pj.add((ClientePessoaJuridica) clientes.get(i));
			}
		}
		for(int i = 0; i < pj.size(); i++) {
			if (cliente.getCnpj() == pj.get(i).getCnpj()) {
				return cliente.contratarTransporte(carga);
			}
		}
		return false;
	}

	public void alterarSituacaoDaCarga(int codigo) {
		Scanner sc = new Scanner(System.in);
		for (int i = 0; i < mostrarTodasAsCargas().size(); i++) {
			if ((mostrarTodasAsCargas().get(i).situacao.equals(CargaStatus.ENTREGUE) && mostrarTodasAsCargas().get(i).getCodigo() == codigo )
					|| (mostrarTodasAsCargas().get(i).situacao.equals(CargaStatus.CANCELADA) && (mostrarTodasAsCargas().get(i).getCodigo() == codigo))) {
				System.out.println("A situação da carga não pode ser mudada. ");
				
			} else {
				// Verifica se o código fornecido é valido
				if (mostrarTodasAsCargas().get(i).codigo == codigo) {
					System.out.println("Diga para qual status você quer mudar a carga: ");
					System.out.printf("1 para: Pendente\n2 para: Em transporte\n3 para: Cancelada\n4 para: Entregue");
					int status = sc.nextInt();

					if (mostrarTodasAsCargas().get(i).situacao.equals(CargaStatus.EM_TRANSPORTE)
							&& status == 1 ){
						System.out.println("A situação da carga em transporte não pode ser mudada "
								+ "para a situação solicitada. ");
					} else {
						// Alterar situação da carga
						switch (status) {
						case 1:
							mostrarTodasAsCargas().get(i).setSituacao(CargaStatus.PENDENTE);
							System.out.println("Situação da carga mudada com sucesso.");
							break;
						case 2:
							mostrarTodasAsCargas().get(i).setSituacao(CargaStatus.EM_TRANSPORTE);
							System.out.println("Situação da carga mudada com sucesso.");
							break;
						case 3:
							mostrarTodasAsCargas().get(i).setSituacao(CargaStatus.CANCELADA);
							System.out.println("Situação da carga mudada com sucesso.");
							break;
						case 4:
							mostrarTodasAsCargas().get(i).setSituacao(CargaStatus.ENTREGUE);
							System.out.println("Situação da carga mudada com sucesso.");
							break;
						}
					}
				}
			}
		}
	}

	public List<Carga> mostrarTodasAsCargas() {
		List<Carga> listaCargas = new ArrayList<>();

		for (int i = 0; i < clientes.size(); i++) {
			for (int j = 0; j < clientes.get(i).cargas.size(); j++) {
				listaCargas.add(clientes.get(i).cargas.get(j));
			}
		}
		if(listaCargas.isEmpty()) {
			return null;
		}

		return listaCargas;
	}

}
