package br.unitins.censohgp.model;

public class Incidente {
	private Integer idincidente;
	private String nome;
	private String descricao;
	private boolean ativo;

	public Incidente() {
		super();
	}

	public Incidente(Integer idincidente, String nome, String descricao, boolean ativo) {
		super();
		this.idincidente = idincidente;
		this.nome = nome;
		this.descricao = descricao;
		this.ativo = ativo;
	}

	public Integer getIdincidente() {
		return idincidente;
	}

	public void setIdincidente(Integer idincidente) {
		this.idincidente = idincidente;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

}
