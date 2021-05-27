package entities;

import acmeEnum.CargaStatus;

public class Carga {

	protected  int codigo;

	protected double altura;

	protected double largura;

	protected double profundidade;

	protected double peso;

	protected double valorFrete;

	protected CargaStatus situacao;

	protected Aeroporto origem;

	protected Aeroporto destino;

	public Carga() {
	}

	public Carga(int codigo, double altura, double largura, double profundidade, double peso, 
			  Aeroporto origem, Aeroporto destino) {

		this.codigo = codigo;
		this.altura = altura;
		this.largura = largura;
		this.profundidade = profundidade;
		this.peso = peso;
		
		this.situacao = CargaStatus.PENDENTE;
		this.origem = origem;
		this.destino = destino;
		
		//calcula o frete
		double valorBasico = this.altura * this.largura * this.profundidade * this.peso * 10.0;
		double valorDistancia = calcularDistancia()/100.0;
		valorFrete = (valorBasico * valorDistancia);
		
	}

	protected double calcularDistancia() {
		double primeiraLatitudeParaRadiano = Math.toRadians(origem.getLatitude());
		double segundaLatitudeParaRadiano = Math.toRadians(destino.getLatitude());

		double diferencaLongitudes = Math.toRadians(destino.getLongitude() - origem.getLongitude());
		return Math.acos(Math.cos(primeiraLatitudeParaRadiano) * Math.cos(segundaLatitudeParaRadiano)
				* Math.cos(diferencaLongitudes) + Math.sin(primeiraLatitudeParaRadiano) 
				* Math.sin(segundaLatitudeParaRadiano)) * 6371.0;
	}

	public int getCodigo() {
		return codigo;
	}


	public double getAltura() {
		return altura;
	}

	public void setAltura(double altura) {
		this.altura = altura;
	}

	public double getLargura() {
		return largura;
	}

	public void setLargura(double largura) {
		this.largura = largura;
	}

	public double getProfundidade() {
		return profundidade;
	}

	public void setProfundidade(double profundidade) {
		this.profundidade = profundidade;
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}

	public double getValorFrete() {
		return valorFrete;
	}

	public void setValorFrete(double valorFrete) {
		this.valorFrete = valorFrete;
	}

	public CargaStatus getSituacao() {
		return situacao;
	}

	public void setSituacao(CargaStatus situacao) {
		this.situacao = situacao;
	}

	public Aeroporto getOrigem() {
		return origem;
	}

	public void setOrigem(Aeroporto origem) {
		this.origem = origem;
	}

	public Aeroporto getDestino() {
		return destino;
	}

	public void setDestino(Aeroporto destino) {
		this.destino = destino;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Código: ");
		sb.append(codigo + "\n");
		sb.append("Altura: ");
		sb.append(String.format("%.2f", altura) + "\n");
		sb.append("Largura: ");
		sb.append(String.format("%.2f", largura) + "\n");
		sb.append("Profundidade: ");
		sb.append(String.format("%.2f", profundidade) + "\n");
		sb.append("Peso: ");
		sb.append(String.format("%.2f", peso) + "\n");
		sb.append("Valor do frete: ");
		sb.append(String.format("%.2f", valorFrete) + "\n");
		sb.append("Situação da carga: ");
		sb.append(situacao + "\n");
		sb.append("Aeroporto de origem: ");
		sb.append(origem.getNome() + "\n");
		sb.append("Aeroporto de destino: ");
		sb.append(destino.getNome() + "\n");
		return sb.toString();

	}
}
