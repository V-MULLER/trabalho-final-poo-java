package entities;

public class Aeroporto {

	private String codigoIATA;

	private String nome;

	private String pais;

	private double latitude;

	private double longitude;
	
	public Aeroporto() {
	}

	public Aeroporto(String codigoIATA, String nome, String pais, double latitude, double longitude) {
		this.codigoIATA = codigoIATA;
		this.nome = nome;
		this.pais = pais;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public String getCodigoIATA() {
		return codigoIATA;
	}

	public void setCodigoIATA(String codigoIATA) {
		this.codigoIATA = codigoIATA;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Aeroporto: ");
		sb.append(getNome() + "\n");
		sb.append("Código IATA: ");
		sb.append(getCodigoIATA() + "\n");
		sb.append("País: ");
		sb.append(getPais() + "\n");
		sb.append("Latitude: ");
		sb.append(getLatitude() + "\n");
		sb.append("Longitude: ");
		sb.append(getLongitude() + "\n");
		return sb.toString();
	}
}
