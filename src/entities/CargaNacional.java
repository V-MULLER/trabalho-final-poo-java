package entities;


public class CargaNacional extends Carga{
	private Double isqn;

	public CargaNacional() {
		super();
	}
	
	public CargaNacional(int codigo, double altura, double largura, double profundidade, double peso, Aeroporto origem,
			Aeroporto destino, Double isqn) {
		super(codigo, altura, largura, profundidade, peso, origem, destino);
		this.isqn = isqn;
		
		super.setValorFrete(getValorFrete() + isqn);
	}

	public Double getIsqn() {
		return isqn;
	}

	public void setIsqn(Double isqn) {
		this.isqn = isqn;
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
		sb.append("ISQN: ");
		sb.append(getIsqn());
		return sb.toString();

	}
}
