package entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Catalogo {
	private Gerente gerente;
	private Atendente atendente;

	public Catalogo() {
		gerente = new Gerente();
		atendente = new Atendente();
	}

	public void menu() {
		Scanner sc = new Scanner(System.in);
		Scanner str = new Scanner(System.in);
		sc.reset();

		System.out.println("Bem vindo a ACMECargas. ");
		System.out.println("Digite 1 caso seja atendente ou 2 caso seja gerente ou 3 para sair. ");
		if (sc.hasNextInt()) {
			int usuario = sc.nextInt();
			if (usuario < 0 || usuario > 3) {
				System.out.println("Dígito inválido. ");
			} else {
				// caso atendente
				while (usuario != 3) {
					if (usuario == 1) {
						int opcao = 0;
						System.out.println("Digite a opção desejada: ");
						System.out.println("1 Para cadastrar cliente.");
						System.out.println("2 Para cadastrar novo transporte de carga. ");
						System.out.println("3 Para alterar situação de uma carga.");
						System.out.println("4 Para consultar todas as cargas cadastradas.");
						System.out.println("5 Para sair e voltar para o menu principal. ");
						if (sc.hasNextInt()) {
							opcao = sc.nextInt();
						} else {
							System.out.println("Erro: Dígito inválido.");
							opcao = 5;

						}
						while (opcao != 5 || opcao > 5 || opcao < 0) {

							switch (opcao) {
							case 1:
								System.out.println("Digite 1 para cliente pessoa física ou 2 para pessoa jurídica: ");
								int tipo = sc.nextInt();
								if (tipo == 1) {
									System.out.println("Diga o nome do cliente: ");
									String nome = str.nextLine();
									System.out.println("Digite o email do cliente: ");
									String email = str.nextLine();
									System.out.println("Digite o endereço do cliente: ");
									String endereco = str.nextLine();
									System.out.println("Digite o cpf do cliente: ");
									long cpf = sc.nextLong();
									// instanciar cliente
									Cliente cliente = new ClientePessoaFisica(nome, email, endereco, cpf);
									boolean verifica = atendente.cadastrarCliente(cliente);
									if (verifica == true) {
										System.out.println("Cliente cadastrado com sucesso. ");
									} else {
										System.out.println("Erro: Cliente existente. ");
									}
								} else if (tipo == 2) {
									System.out.println("Diga o nome do proprietário: ");
									String proprietario = str.nextLine();
									System.out.println("Digite o email da empresa: ");
									String email = str.nextLine();
									System.out.println("Digite o endereço da empresa: ");
									String endereco = str.nextLine();
									System.out.println("Digite o cnpj da empresa: ");
									long cnpj = sc.nextLong();
									System.out.println("Digite o nome fantasia da empresa: ");
									String nomeFantasia = str.nextLine();
									// instanciar cliente
									Cliente cliente = new ClientePessoaJuridica(proprietario, email, endereco, cnpj,
											nomeFantasia);
									boolean verifica = atendente.cadastrarCliente(cliente);
									if (verifica == true) {
										System.out.println("Cliente cadastrado com sucesso. ");
									} else {
										System.out.println("Erro: Cliente existente. ");
									}
								} else {
									System.out.println("Erro: Dígito inválido. ");
								}
								break;
							case 2:
								Aeroporto saida = null;
								Aeroporto chegada = null;
								System.out.println("Digite 1 para cadastrar a carga de um cliente pessoa física"
										+ "ou 2 para cliente pessoa jurídica: ");
								int cliente = sc.nextInt();
								if (cliente == 1) {
									List<ClientePessoaFisica> pf = new ArrayList<>();
									for (int i = 0; i < atendente.clientes.size(); i++) {
										if (atendente.clientes.get(i) instanceof ClientePessoaFisica) {
											pf.add((ClientePessoaFisica) atendente.clientes.get(i));
										}
									}

									// Verificar se existe esse cpf se, não dá erro
									System.out.println("Digite o cpf do cliente: ");
									long cpf = sc.nextLong();
									System.out.println("Digite o código da carga: ");
									int codigo = sc.nextInt();

									sc.reset();
									System.out.println("Digite a altura da carga em metros: ");
									double altura = sc.nextDouble();
									System.out.println("Digite a largura da carga em metros: ");
									double largura = sc.nextDouble();
									System.out.println("Digite a profundidade da carga em metros: ");
									double profundidade = sc.nextDouble();
									System.out.println("Digite o peso da carga em quilos: ");
									double peso = sc.nextDouble();
									System.out.println("Digite o nome do aeroporto de origem: ");
									String origem = str.nextLine();
									System.out.println("Digite o nome do aeroporto de destino: ");
									String destino = str.nextLine();
									// mostrar aeroportos
									System.out.println("Aeroportos cadastrados: ");
									for (int i = 0; i < gerente.consultarAeroportos().size(); i++) {
										System.out.println(gerente.consultarAeroportos().get(i).getNome());
									}

									sc.reset();
									System.out.println("Digite 1 se os aeroportos estiverem cadastrados"
											+ " ou 2 caso não estejam: ");
									int resposta = sc.nextInt();
									if (resposta == 2) {
										System.out.println("Aeroporto(s) inválido(s). ");
										break;
									} else if (resposta == 1) {
										for (int i = 0; i < gerente.consultarAeroportos().size(); i++) {
											if (gerente.consultarAeroportos().get(i).getNome().toUpperCase()
													.equals(origem.toUpperCase())) {
												saida = gerente.consultarAeroportos().get(i);
											} else if (gerente.consultarAeroportos().get(i).getNome().toUpperCase()
													.equals(destino.toUpperCase())) {
												chegada = gerente.consultarAeroportos().get(i);
											}
										}
										ClientePessoaFisica clientePf = null;
										for (int i = 0; i < pf.size(); i++) {
											if (pf.get(i).getCpf() == cpf) {
												clientePf = pf.get(i);
											}
										}
										sc.reset();
										// verificar se carga é nacional ou internacional
										if (saida.getPais().toUpperCase().equals("BRASIL") && chegada.getPais().toUpperCase().equals("BRASIL")){
											System.out.println("Digite o ISQN da carga; ");
											double isqn = sc.nextDouble();

											Carga carga = new CargaNacional(codigo, altura, largura, profundidade, peso,
													saida, chegada, isqn);
											// atendente.cadastrarTransporte(clientePf, carga);
											if (atendente.cadastrarTransporte(clientePf, carga)) {

												System.out.println(carga.toString());
												System.out.println(clientePf.toString());

											} else {
												System.out.println("Erro: cliente não cadastrado.");
											}
										} else {
											System.out.println("Digite a taxa de alfândega: ");
											double taxaAlf = sc.nextDouble();
											System.out.println("Digite o país de origem da carga: ");
											String pais = str.nextLine();

											Carga carga = new CargaInternacional(codigo, altura, largura, profundidade,
													peso, saida, chegada, taxaAlf, pais);
											if (atendente.cadastrarTransporte(clientePf, carga)) {
												System.out.println(carga.toString());
												System.out.println(clientePf.toString());

											} else {
												System.out.println("Erro: Cliente não cadastrado.");
											}
										}
									}
								} else if (cliente == 2) {
									List<ClientePessoaJuridica> pj = new ArrayList<>();
									for (int i = 0; i < atendente.clientes.size(); i++) {
										if (atendente.clientes.get(i) instanceof ClientePessoaJuridica) {
											pj.add((ClientePessoaJuridica) atendente.clientes.get(i));
										}
									}

									// Verificar se existe esse cpf se, não dá erro
									System.out.println("Digite o cnpj da empresa: ");
									long cnpj = sc.nextLong();
									System.out.println("Digite o código da carga: ");
									int codigo = sc.nextInt();

									sc.reset();
									System.out.println("Digite a altura da carga em metros: ");
									double altura = sc.nextDouble();
									System.out.println("Digite a largura da carga em metros: ");
									double largura = sc.nextDouble();
									System.out.println("Digite a profundidade da carga em metros: ");
									double profundidade = sc.nextDouble();
									System.out.println("Digite o peso da carga em quilos: ");
									double peso = sc.nextDouble();
									System.out.println("Digite o nome do aeroporto de origem: ");
									String origem = str.nextLine();
									System.out.println("Digite o nome do aeroporto de destino: ");
									String destino = str.nextLine();
									// mostrar aeroportos
									System.out.println("Aeroportos cadastrados: ");
									for (int i = 0; i < gerente.consultarAeroportos().size(); i++) {
										if (gerente.consultarAeroportos().equals(null)) {
											System.out.println("Não há aeroportos cadastrados. ");
										} else {
											gerente.consultarAeroportos().get(i).getNome();
										}
									}
									sc.reset();
									System.out.println("Digite 1 se os aeroportos estiverem cadastrados"
											+ " ou 2 caso não estejam: ");
									int resposta = sc.nextInt();
									if (resposta == 2) {
										System.out.println("Aeroporto(s) inválido(s). ");
										break;
									} else if (resposta == 1) {
										for (int i = 0; i < gerente.consultarAeroportos().size(); i++) {
											if (gerente.consultarAeroportos().get(i).getNome().toUpperCase()
													.equals(origem)) {
												saida = gerente.consultarAeroportos().get(i);
											} else if (gerente.consultarAeroportos().get(i).getNome().toUpperCase()
													.equals(destino)) {
												chegada = gerente.consultarAeroportos().get(i);
											}
										}
										// variavel vai receber o cliente informado para instancia-lo
										ClientePessoaJuridica clientePj = null;
										for (int i = 0; i < pj.size(); i++) {
											if (pj.get(i).getCnpj() == cnpj) {
												clientePj = pj.get(i);
											}
										}

										// verificar se carga é nacional ou internacional
										if (saida.getPais().toUpperCase().equals("BRASIL") && chegada.getPais().toUpperCase().equals("BRASIL")) {
											System.out.println("Digite o ISQN da carga; ");
											double isqn = sc.nextDouble();

											Carga carga = new CargaNacional(codigo, altura, largura, profundidade, peso,
													saida, chegada, isqn);
											atendente.cadastrarTransporte(clientePj, carga);
											if (atendente.cadastrarTransporte(clientePj, carga)) {
												System.out.println(carga.toString());
												System.out.println(clientePj.toString());
												continue;
											} else {
												System.out.println("Erro: cliente não cadastrado.");
											}
										} else {
											System.out.println("Digite a taxa de alfândega: ");
											double taxaAlf = sc.nextDouble();
											System.out.println("Digite o país de origem da carga: ");
											String pais = str.nextLine();

											Carga carga = new CargaInternacional(codigo, altura, largura, profundidade,
													peso, saida, chegada, taxaAlf, pais);
											if (atendente.cadastrarTransporte(clientePj, carga)) {
												System.out.println(carga.toString());
												System.out.println(clientePj.toString());
												continue;
											} else {
												System.out.println("Erro: Cliente não cadastrado.");
											}
										}
									}

								} else {
									System.out.println("Erro: Dígito inválido.");
								}
								sc.reset();
								str.reset();
								break;
							case 3:
								System.out.println("Digite o código da carga ");
								int codigo = sc.nextInt();
								if (atendente.mostrarTodasAsCargas().equals(null)) {
									System.out.println("Carga não cadastrada. ");
								}
								// Verifica o status e se existe o código
								else {
									atendente.alterarSituacaoDaCarga(codigo);
								}
								break;
							case 4:
								// mostra as cargas
								if (!atendente.clientes.isEmpty()) {
									for (int i = 0; i < atendente.clientes.size(); i++) {
										System.out.println(atendente.clientes.get(i).toString());
										for (int j = 0; j < atendente.clientes.get(i).cargas.size(); j++) {
											System.out.println(atendente.clientes.get(i).cargas.get(j).toString());
										}
									}
								} else {
									System.out.println("Não há cargas cadastradas. ");
								}
								break;
							}
							System.out.println("Digite a opção desejada: ");
							System.out.println("1 Para cadastrar cliente.");
							System.out.println("2 Para cadastrar novo transporte de carga. ");
							System.out.println("3 Para alterar situação de uma carga.");
							System.out.println("4 Para consultar todas as cargas cadastradas.");
							System.out.println("5 Para sair e voltar ao menu principal. ");
							if (sc.hasNextInt()) {
								opcao = sc.nextInt();
							} else {
								System.out.println("Erro: Dígito inválido.");
								opcao = 5;

							}
						}

					}
					// caso gerente
					else if (usuario == 2) {
						sc.reset();
						str.reset();
						int opcao = 0;
						System.out.println("Digite a opção desejada: ");
						System.out.println("1 Para cadastrar cliente.");
						System.out.println("2 Para cadastrar novo transporte de carga. ");
						System.out.println("3 Para alterar situação de uma carga.");
						System.out.println("4 Para consultar todas as cargas cadastradas.");
						System.out.println("5 Cadastrar um aeroporto.");
						System.out.println("6 Para consultar todos os aeroportos cadastrados.");
						System.out.println("7 Para consultar todos os clientes cadastrados.");
						System.out.println("8 Para trace.");
						System.out.println("9 Para sair e voltar ao menu principal. ");
						if (sc.hasNextInt()) {
							opcao = sc.nextInt();
						} else {
							System.out.println("Erro: Dígito inválido.");
							opcao = 9;

						}
						while (opcao != 9 || opcao < 0 || opcao > 9) {
							switch (opcao) {
							// cadastrar cliente
							case 1:
								System.out.println("Digite 1 para cliente pessoa física ou 2 para pessoa jurídica: ");
								int tipo = sc.nextInt();
								if (tipo == 1) {
									System.out.println("Diga o nome do cliente: ");
									String nome = str.nextLine();
									System.out.println("Digite o email do cliente: ");
									String email = str.nextLine();
									System.out.println("Digite o endereço do cliente: ");
									String endereco = str.nextLine();
									System.out.println("Digite o cpf do cliente: ");
									long cpf = sc.nextLong();
									// instanciar cliente
									Cliente cliente = new ClientePessoaFisica(nome, email, endereco, cpf);
									boolean verifica = gerente.cadastrarCliente(cliente);
									if (verifica == true) {
										System.out.println("Cliente cadastrado com sucesso. ");
									} else {
										System.out.println("Erro: Cliente existente. ");
									}
								} else if (tipo == 2) {
									System.out.println("Diga o nome do proprietário: ");
									String proprietario = str.nextLine();
									System.out.println("Digite o email da empresa: ");
									String email = str.nextLine();
									str.reset();
									System.out.println("Digite o endereço da empresa: ");
									String endereco = str.nextLine();
									System.out.println("Digite o cnpj da empresa: ");
									long cnpj = sc.nextLong();
									System.out.println("Digite o nome fantasia da empresa: ");
									String nomeFantasia = str.nextLine();
									// instanciar cliente
									Cliente cliente = new ClientePessoaJuridica(proprietario, email, endereco, cnpj,
											nomeFantasia);
									boolean verifica = gerente.cadastrarCliente(cliente);
									if (verifica == true) {
										System.out.println("Cliente cadastrado com sucesso. ");
									} else {
										System.out.println("Erro: Cliente existente. ");
									}
								} else {
									System.out.println("Erro: Dígito inválido. ");
								}
								break;
							// cadastrar novo transporte
							case 2:
								Aeroporto saida = new Aeroporto();
								Aeroporto chegada = new Aeroporto();
								System.out.println("Digite 1 para cadastrar a carga de um cliente pessoa física"
										+ " ou 2 para cliente pessoa jurídica: ");
								int cliente = sc.nextInt();
								if (cliente == 1) {
									List<ClientePessoaFisica> pf = new ArrayList<>();
									for (int i = 0; i < gerente.clientes.size(); i++) {
										if (gerente.clientes.get(i) instanceof ClientePessoaFisica) {
											pf.add((ClientePessoaFisica) gerente.clientes.get(i));
										}
									}

									// Verificar se existe esse cpf se, não dá erro
									System.out.println("Digite o cpf do cliente: ");
									long cpf = sc.nextLong();
									System.out.println("Digite o código da carga: ");
									int codigo = sc.nextInt();

									sc.reset();
									System.out.println("Digite a altura da carga em metros: ");
									double altura = sc.nextDouble();
									System.out.println("Digite a largura da carga em metros: ");
									double largura = sc.nextDouble();
									System.out.println("Digite a profundidade da carga em metros: ");
									double profundidade = sc.nextDouble();
									System.out.println("Digite o peso da carga em quilos: ");
									double peso = sc.nextDouble();
									System.out.println("Digite o nome do aeroporto de origem: ");
									String origem = str.nextLine();
									System.out.println("Digite o nome do aeroporto de destino: ");
									String destino = str.nextLine();
									// mostrar aeroportos
									System.out.println("Aeroportos cadastrados: ");
									for (int i = 0; i < gerente.consultarAeroportos().size(); i++) {
										System.out.println(gerente.consultarAeroportos().get(i).getNome());
									}

									sc.reset();
									System.out.println("Digite 1 se os aeroportos estiverem cadastrados"
											+ " ou 2 caso não estejam: ");
									int resposta = sc.nextInt();
									if (resposta == 2) {
										System.out.println("Aeroporto(s) inválido(s). ");
										break;
									} else if (resposta == 1) {
										for (int i = 0; i < gerente.consultarAeroportos().size(); i++) {
											if (gerente.consultarAeroportos().get(i).getNome().toUpperCase()
													.equals(origem.toUpperCase())) {
												saida = gerente.consultarAeroportos().get(i);
											} else if (gerente.consultarAeroportos().get(i).getNome().toUpperCase()
													.equals(destino.toUpperCase())) {
												chegada = gerente.consultarAeroportos().get(i);
											}
										}
										ClientePessoaFisica clientePf = null;
										for (int i = 0; i < pf.size(); i++) {
											if (pf.get(i).getCpf() == cpf) {
												clientePf = pf.get(i);
											}
										}
										sc.reset();
										// verificar se carga é nacional ou internacional
										if (saida.getPais().toUpperCase().equals("BRASIL") && chegada.getPais().toUpperCase().equals("BRASIL")) {
											System.out.println("Digite o ISQN da carga; ");
											double isqn = sc.nextDouble();

											Carga carga = new CargaNacional(codigo, altura, largura, profundidade, peso,
													saida, chegada, isqn);
											// atendente.cadastrarTransporte(clientePf, carga);
											if (gerente.cadastrarTransporte(clientePf, carga)) {

												System.out.println(carga.toString());
												System.out.println(clientePf.toString());

											} else {
												System.out.println("Erro: cliente não cadastrado.");
											}
										} else {
											System.out.println("Digite a taxa de alfândega: ");
											double taxaAlf = sc.nextDouble();
											System.out.println("Digite o país de origem da carga: ");
											String pais = str.nextLine();

											Carga carga = new CargaInternacional(codigo, altura, largura, profundidade,
													peso, saida, chegada, taxaAlf, pais);
											if (gerente.cadastrarTransporte(clientePf, carga)) {
												System.out.println(carga.toString());
												System.out.println(clientePf.toString());

											} else {
												System.out.println("Erro: Cliente não cadastrado.");
											}
										}
									}
								} else if (cliente == 2) {
									List<ClientePessoaJuridica> pj = new ArrayList<>();
									for (int i = 0; i < gerente.clientes.size(); i++) {
										if (gerente.clientes.get(i) instanceof ClientePessoaJuridica) {
											pj.add((ClientePessoaJuridica) gerente.clientes.get(i));
										}
									}

									// Verificar se existe esse cnpj se, não dá erro
									System.out.println("Digite o cnpj da empresa: ");
									long cnpj = sc.nextLong();
									System.out.println("Digite o código da carga: ");
									int codigo = sc.nextInt();

									sc.reset();
									System.out.println("Digite a altura da carga em metros: ");
									double altura = sc.nextDouble();
									System.out.println("Digite a largura da carga em metros: ");
									double largura = sc.nextDouble();
									System.out.println("Digite a profundidade da carga em metros: ");
									double profundidade = sc.nextDouble();
									System.out.println("Digite o peso da carga em quilos: ");
									double peso = sc.nextDouble();
									System.out.println("Digite o nome do aeroporto de origem: ");
									String origem = str.nextLine();
									System.out.println("Digite o nome do aeroporto de destino: ");
									String destino = str.nextLine();
									// mostrar aeroportos
									System.out.println("Aeroportos cadastrados: ");
									for (int i = 0; i < gerente.consultarAeroportos().size(); i++) {
										if (gerente.consultarAeroportos().equals(null)) {
											System.out.println("Não há aeroportos cadastrados. ");
										} else {
											System.out.println(gerente.consultarAeroportos().get(i).getNome());
										}
									}
									sc.reset();
									System.out.println("Digite 1 se os aeroportos estiverem cadastrados"
											+ " ou 2 caso não estejam: ");
									int resposta = sc.nextInt();
									if (resposta == 2) {
										System.out.println("Aeroporto(s) inválido(s). ");
										break;
									} else if (resposta == 1) {
										for (int i = 0; i < gerente.aeroportos.size(); i++) {
											if (gerente.aeroportos.get(i).getNome().toUpperCase()
													.equals(origem.toUpperCase())) {
												saida = gerente.aeroportos.get(i);
											} else if (gerente.aeroportos.get(i).getNome().toUpperCase()
													.equals(destino.toUpperCase())) {
												chegada = gerente.aeroportos.get(i);
											}
											else {continue;}
										}
										// variavel vai receber o cliente informado para instancia-lo
										ClientePessoaJuridica clientePj = null;
										for (int i = 0; i < pj.size(); i++) {
											if (pj.get(i).getCnpj() == cnpj) {
												clientePj = pj.get(i);
											}
										}
										
										// verificar se carga é nacional ou internacional
										if (saida.getPais().toUpperCase().equals("BRASIL") && chegada.getPais().toUpperCase().equals("BRASIL")) {
											System.out.println("Digite o ISQN da carga; ");
											double isqn = sc.nextDouble();

											Carga carga = new CargaNacional(codigo, altura, largura, profundidade, peso,
													saida, chegada, isqn);
											gerente.cadastrarTransporte(clientePj, carga);
											if (gerente.cadastrarTransporte(clientePj, carga)) {
												System.out.println(carga.toString());
												System.out.println(clientePj.toString());
												
											} else {
												System.out.println("Erro: cliente não cadastrado.");
											}
										} else {
											System.out.println("Digite a taxa de alfândega: ");
											double taxaAlf = sc.nextDouble();
											System.out.println("Digite o país de origem da carga: ");
											String pais = str.nextLine();

											Carga carga = new CargaInternacional(codigo, altura, largura, profundidade,
													peso, saida, chegada, taxaAlf, pais);
											if (gerente.cadastrarTransporte(clientePj, carga)) {
												System.out.println(carga.toString());
												System.out.println(clientePj.toString());
												
											} else {
												System.out.println("Erro: Cliente não cadastrado.");
											}
										}
									}

								} else {
									System.out.println("Erro: Dígito inválido.");
								}
								sc.reset();
								str.reset();
								break;
							// alterar situação da carga
							case 3:
								System.out.println("Digite o código da carga ");
								int codigo = sc.nextInt();
								if (gerente.mostrarTodasAsCargas().equals(null)) {
									System.out.println("Carga não cadastrada. ");
								}
								// Verifica o status e se existe o código
								else {
									gerente.alterarSituacaoDaCarga(codigo);
								}
								break;
							// consultar todas as cargas
							case 4:
								// mostra as cargas
								if (!gerente.clientes.isEmpty()) {
									for (int i = 0; i < gerente.clientes.size(); i++) {
										System.out.println(gerente.clientes.get(i).toString());
										for (int j = 0; j < gerente.clientes.get(i).cargas.size(); j++) {
											System.out.println(gerente.clientes.get(i).cargas.get(j).toString());
										}
									}
								} else {
									System.out.println("Não há cargas cadastradas. ");
								}
								break;
							// cadastrar aeroporto
							case 5:
								System.out.println("Digite o código IATA do aeroporto: ");
								String iata = str.nextLine();
								System.out.println("Digite o nome do aeroporto: ");
								String nome = str.nextLine();
								System.out.println("Digite o país do aeroporto: ");
								String pais = str.nextLine();
								System.out.println("Digite a latitude do aeroporto: ");
								double latitude = sc.nextDouble();
								System.out.println("Digite a longitude do aeroporto: ");
								double longitude = sc.nextDouble();
								if (gerente.cadastrarAeroporto(new Aeroporto(iata, nome, pais, latitude, longitude))) {
									System.out.println("Aeroporto cadastrado");
									System.out.println();
								} else {
									System.out.println("Erro: Código IATA existente. ");
								}
								break;
							// consultar todos os aeroportos
							case 6:
								if(gerente.aeroportos.isEmpty()) {
									System.out.println("Não há aeroportos cadastrados. ");
								}
								else {	for (Aeroporto aero : gerente.aeroportos) {
										System.out.println(aero.toString());
									}
								}
								break;
							// consultar todos os clientes
							case 7:
								for (Cliente cli : gerente.clientes) {
									System.out.println(cli.toString());
								}
								break;
							// trace
							case 8:
								System.out.println("1. Cadastrar o aeroporto Salgado Filho no Brasil. ");
								gerente.cadastrarAeroporto(new Aeroporto("POA", "Salgado Filho", "Brasil", -29.9935, -51.1754));
								System.out.println("2. Cadastrar o aeroporto Guarulhos no Brasil. ");
								gerente.cadastrarAeroporto(new Aeroporto("GRU", "Guarulhos", "Brasil", -23.4305, -46.4752));
								System.out.println("3. Cadastrar o aeroporto Heathrow na Inglaterra. ");
								gerente.cadastrarAeroporto(new Aeroporto("LHR", "Heathrow", "Inglaterra", 51.4700, -0.4564));
								System.out.println("4. Cadastrar um cliente individual “Maria”. ");
								gerente.cadastrarCliente(new ClientePessoaFisica("Maria", "maria@gmail.com", "Rua Lima e silva 123", 12345));
								System.out.println("5. Cadastrar um cliente empresarial “ACME Corp.” ");
								gerente.cadastrarCliente(new ClientePessoaJuridica("Pedro", "pedro@gmail.com", "Av. Ipiranga 123", 010203, "Pedro Delivery"));
								System.out.println("6. Cadastrar uma carga para “Maria”: dimensões (0,2 ; 0,2; 0,2), peso (1), origem" 
								+ "(Salgado Filho), destino (Guarulhos), ISQN (500,00).");
								gerente.clientes.get(0).contratarTransporte(new CargaNacional(1234, 0.2, 0.2, 0.2, 1.0, gerente.aeroportos.get(0), gerente.aeroportos.get(1), 500.0));
								System.out.println("7. Cadastrar uma carga para “ACME Corp.”: dimensões (1; 1; 1), peso (10), origem"
								+ "(Heathrow), destino (Salgado Filho), taxa de alfândega (1000,00). ");
								gerente.clientes.get(1).contratarTransporte(new CargaInternacional(456, 1.0, 1.0, 1.0, 10.0, gerente.aeroportos.get(2), gerente.aeroportos.get(0), 1000.0, "Inglaterra"));
								System.out.println("8. Alterar a situação da carga da “Maria” para “Entregue”. ");
								gerente.alterarSituacaoDaCarga(1234);
								System.out.println("9. Consultar todos os aeroportos cadastrados. ");
								for (Aeroporto aero : gerente.aeroportos) {
									System.out.println(aero.toString());
								}
								System.out.println("10. Consultar todos os clientes cadastrados. ");
								for (Cliente cli : gerente.clientes) {
									System.out.println(cli.toString());
								}
								System.out.println("11. Consultar as cargas cadastradas com o respectivo cliente e aeroportos. ");
								for(int i = 0; i < gerente.clientes.size(); i++) {
									System.out.println(gerente.clientes.get(i).toString());
									for(int j = 0; j < gerente.clientes.get(i).cargas.size(); j++) {
										System.out.println(gerente.clientes.get(i).cargas.get(j).toString());
									}
								}
								break;
							case 9:
								opcao = 9;
								break;
							}
							//repete o loop
							System.out.println("Digite a opção desejada: ");
							System.out.println("1 Para cadastrar cliente.");
							System.out.println("2 Para cadastrar novo transporte de carga. ");
							System.out.println("3 Para alterar situação de uma carga.");
							System.out.println("4 Para consultar todas as cargas cadastradas.");
							System.out.println("5 Cadastrar um aeroporto.");
							System.out.println("6 Para consultar todos os aeroportos cadastrados.");
							System.out.println("7 Para consultar todos os clientes cadastrados.");
							System.out.println("8 Para trace.");
							System.out.println("9 Para sair e voltar ao menu principal. ");
							if (sc.hasNextInt()) {
								opcao = sc.nextInt();
							} else {
								System.out.println("Erro: Dígito inválido.");
								opcao = 9;
							}
						}
					}
					// Realiza novo loop
					System.out.println("Bem vindo a ACMECargas. ");
					System.out.println("Digite 1 caso seja atendente ou 2 caso seja gerente ou 3 para sair. ");
					if (sc.hasNextInt() || sc.nextInt() > 0 || sc.nextInt() < 3) {
						usuario = sc.nextInt();

					}

				}
				System.out.println("Até breve.");
			}

		} else {
			System.out.println("Dígito inválido.");
		}
	}

}
