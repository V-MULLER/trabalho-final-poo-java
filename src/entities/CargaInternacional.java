package entities;


public class CargaInternacional extends Carga{
	private Double taxaAlfandega;
	private String paisOrigem;

	public CargaInternacional() {
		super();
	}
	
	

	public CargaInternacional(int codigo, double altura, double largura, double profundidade, double peso,
			Aeroporto origem, Aeroporto destino, Double taxaAlfandega, String paisOrigem) {
		super(codigo, altura, largura, profundidade, peso, origem, destino);
		this.taxaAlfandega = taxaAlfandega;
		this.paisOrigem = paisOrigem;
		
		super.setValorFrete(getValorFrete() + taxaAlfandega);
	}

	public Double getTaxaAlfandega() {
		return taxaAlfandega;
	}

	public void setTaxaAlfandega(Double taxaAlfandega) {
		this.taxaAlfandega = taxaAlfandega;
	}

	public String getPaisOrigem() {
		return paisOrigem;
	}

	public void setPaisOrigem(String paisOrigem) {
		this.paisOrigem = paisOrigem;
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
		sb.append("País de origem da carga: ");
		sb.append(paisOrigem + "\n");
		sb.append("Taxa de alfândega: ");
		sb.append(getTaxaAlfandega());
		return sb.toString();

	}
	
}
