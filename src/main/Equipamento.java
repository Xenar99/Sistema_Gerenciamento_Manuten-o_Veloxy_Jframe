package main;

public class Equipamento {
	
protected String ID;
protected String Equipamento;
protected String Descricao;
protected String DataAquisicao;
protected String Valor;
protected String DataValidade;


public Equipamento() {
	
}


public Equipamento(String iD, String equipamento, String descricao, String dataAquisicao, String valor,
		String dataValidade) {
	super();
	ID = iD;
	Equipamento = equipamento;
	Descricao = descricao;
	DataAquisicao = dataAquisicao;
	Valor = valor;
	DataValidade = dataValidade;
	
}

public String getID() {
	return ID;
}
public void setID(String iD) {
	ID = iD;
}
public String getEquipamento() {
	return Equipamento;
}
public void setEquipamento(String equipamento) {
	Equipamento = equipamento;
}
public String getDescricao() {
	return Descricao;
}
public void setDescricao(String descricao) {
	Descricao = descricao;
}
public String getDataAquisicao() {
	return DataAquisicao;
}
public void setDataAquisicao(String dataAquisicao) {
	DataAquisicao = dataAquisicao;
}
public String getValor() {
	return Valor;
}
public void setValor(String valor) {
	Valor = valor;
}
public String getDataValidade() {
	return DataValidade;
}
public void setDataValidade(String dataValidade) {
	DataValidade = dataValidade;
}

}