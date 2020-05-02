package br.unitins.censohgp.model;

import java.time.LocalDate;

public class Paciente {

	private Integer idpaciente;
	private String nome;
	private String cpf;
	private String rg;
	private SituacaoPaciente idsituacao;
	private Boolean ativo = Boolean.TRUE;
	private String nomeMae;
	private TipoSexo tipoSexo;
	private LocalDate dataNascimento;
	private String observacao;
	private Integer idlocalTransferencia;
	private String numeroProntuario;
	
	public Paciente() {
		
		super();
		
	}
	
	public Paciente(Integer idpaciente, String nome, String cpf, String rg, SituacaoPaciente idsituacao, Boolean ativo,
			String nomeMae, TipoSexo tipoSexo, LocalDate dataNascimento, String observacao,
			Integer idlocalTransferencia, String numeroProntuario) {
		super();
		this.idpaciente = idpaciente;
		this.nome = nome;
		this.cpf = cpf;
		this.rg = rg;
		this.idsituacao = idsituacao;
		this.ativo = ativo;
		this.nomeMae = nomeMae;
		this.tipoSexo = tipoSexo;
		this.dataNascimento = dataNascimento;
		this.observacao = observacao;
		this.idlocalTransferencia = idlocalTransferencia;
		this.numeroProntuario = numeroProntuario;
	}

	@Override
	public Paciente clone() {
		
		try {
			return (Paciente) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			System.out.println("Erro ao clonar.");
		}
		return null;
		
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Paciente other = (Paciente) obj;
		if (ativo == null) {
			if (other.ativo != null)
				return false;
		} else if (!ativo.equals(other.ativo))
			return false;
		if (cpf == null) {
			if (other.cpf != null)
				return false;
		} else if (!cpf.equals(other.cpf))
			return false;
		if (dataNascimento == null) {
			if (other.dataNascimento != null)
				return false;
		} else if (!dataNascimento.equals(other.dataNascimento))
			return false;
		if (idlocalTransferencia == null) {
			if (other.idlocalTransferencia != null)
				return false;
		} else if (!idlocalTransferencia.equals(other.idlocalTransferencia))
			return false;
		if (idpaciente == null) {
			if (other.idpaciente != null)
				return false;
		} else if (!idpaciente.equals(other.idpaciente))
			return false;
		if (idsituacao != other.idsituacao)
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (nomeMae == null) {
			if (other.nomeMae != null)
				return false;
		} else if (!nomeMae.equals(other.nomeMae))
			return false;
		if (numeroProntuario == null) {
			if (other.numeroProntuario != null)
				return false;
		} else if (!numeroProntuario.equals(other.numeroProntuario))
			return false;
		if (observacao == null) {
			if (other.observacao != null)
				return false;
		} else if (!observacao.equals(other.observacao))
			return false;
		if (rg == null) {
			if (other.rg != null)
				return false;
		} else if (!rg.equals(other.rg))
			return false;
		if (tipoSexo != other.tipoSexo)
			return false;
		return true;
	}

	public Integer getIdpaciente() {
		return idpaciente;
	}

	public void setIdpaciente(Integer idpaciente) {
		this.idpaciente = idpaciente;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public SituacaoPaciente getIdsituacao() {
		return idsituacao;
	}

	public void setIdsituacao(SituacaoPaciente idsituacao) {
		this.idsituacao = idsituacao;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public String getNomeMae() {
		return nomeMae;
	}

	public void setNomeMae(String nomeMae) {
		this.nomeMae = nomeMae;
	}

	public TipoSexo getTipoSexo() {
		return tipoSexo;
	}

	public void setTipoSexo(TipoSexo tipoSexo) {
		this.tipoSexo = tipoSexo;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Integer getIdlocalTransferencia() {
		return idlocalTransferencia;
	}

	public void setIdlocalTransferencia(Integer idlocalTransferencia) {
		this.idlocalTransferencia = idlocalTransferencia;
	}

	public String getNumeroProntuario() {
		return numeroProntuario;
	}

	public void setNumeroProntuario(String numeroProntuario) {
		this.numeroProntuario = numeroProntuario;
	}
		
}