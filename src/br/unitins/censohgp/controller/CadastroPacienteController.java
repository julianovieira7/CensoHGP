package br.unitins.censohgp.controller;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.censohgp.dao.DAO;
import br.unitins.censohgp.dao.DepartamentoDAO;
import br.unitins.censohgp.dao.PacienteDAO;
import br.unitins.censohgp.dao.PrecaucaoDAO;
import br.unitins.censohgp.dao.SexoDAO;
import br.unitins.censohgp.dao.SituacaoDAO;
import br.unitins.censohgp.application.Util;
import br.unitins.censohgp.model.Departamento;
import br.unitins.censohgp.model.Paciente;
import br.unitins.censohgp.model.Precaucao;
import br.unitins.censohgp.model.Situacao;
import br.unitins.censohgp.model.TipoSexo;
import br.unitins.censohgp.model.TipoUsuario;
import br.unitins.censohgp.model.Sexo;

@Named
@ViewScoped
public class CadastroPacienteController implements Serializable {


		/**
	 * 
	 */
	private static final long serialVersionUID = -6710627767346612233L;
		private Paciente paciente;
		
		private List<Paciente> listaPaciente;
		private List<SelectItem> listaSexo;
		private List<SelectItem> listasituacao;
		private List<SelectItem> listadepartamento;
		private List<SelectItem> listaprecaucao;
		
		public CadastroPacienteController() {
			Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
			flash.keep("pacienteFlash");
			paciente = (Paciente) flash.get("pacienteFlash");
		}
		
		public List<Paciente> getListaPaciente() {
			if (listaPaciente == null) {
				DAO<Paciente> dao = new PacienteDAO();
				listaPaciente = dao.findAll();
				if (listaPaciente == null)
					listaPaciente = new ArrayList<Paciente>();
			} 
			return listaPaciente;
		}
		
		// METODO QUE REDIRECIONA PARA PAGINA DE EDI��O
		public String ver(int id) throws SQLException {
			PacienteDAO dao = new PacienteDAO();
			Paciente paciente = dao.findById(id);
			Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
			flash.put("pacienteFlash", paciente);
			
			return "alterarpaciente.xhtml?faces-redirect=true";
		}

		public void incluir() {
			if (validarDados()) {
				DAO<Paciente> dao = new PacienteDAO();
				// faz a inclusao no banco de dados
				try {
					dao.create(getPaciente());
					dao.getConnection().commit();
					Util.addMessageInfo("Inclus�o realizada com sucesso.");
					limpar();
					listaPaciente = null;
				} catch (SQLException e) {
					dao.rollbackConnection();
					dao.closeConnection();
					Util.addMessageInfo("Erro ao incluir o Paciente no Banco de Dados.");
					e.printStackTrace();
				}
			}
		}
		
		public void alterar() {
			if (validarDados()) {
				DAO<Paciente> dao = new PacienteDAO();
				// faz a alteracao no banco de dados
				try {
					// gerando um hash da senha
//					getUsuario().setSenha(Util.hashSHA256(getUsuario().getSenha()));
					dao.update(getPaciente());
					dao.getConnection().commit();
					Util.addMessageInfo("Altera��o realizada com sucesso.");
					limpar();
					listaPaciente = null;
				} catch (SQLException e) {
					dao.rollbackConnection();
					dao.closeConnection();
					Util.addMessageInfo("Erro ao alterar o Paciente no Banco de Dados.");
					e.printStackTrace();
				}
					
			}
		}
		
		public void excluir() {
			if (excluir(getPaciente()))
				limpar();
		}
		
		public boolean excluir(Paciente paciente) {
			DAO<Paciente> dao = new PacienteDAO();
			// faz a exclusao no banco de dados
			try {
				dao.delete(getPaciente().getIdpaciente());
				dao.getConnection().commit();
				Util.addMessageInfo("Exclus�o realizada com sucesso.");
				return true;
			} catch (SQLException e) {
				dao.rollbackConnection();
				Util.addMessageInfo("Erro ao excluir o Paciente no Banco de Dados.");
				e.printStackTrace();
				return false;
			} finally {
				dao.closeConnection();
			}
		}

		private boolean validarDados() {
		if (getPaciente().getNome().isBlank()) {
				Util.addMessageWarn("O campo Nome deve ser informado.");
				return false;
			}
//			if (getUsuario().getSenha() == null || 
//					getUsuario().getSenha().trim().equals("") ) {
//				Util.addMessageError("O campo senha deve ser informado.");
//				return false;
//			}
			return true;

		}

		
		public Paciente getPaciente() {
			if (paciente == null) {
				paciente = new Paciente();
				paciente.setSituacao(new Situacao());
			}
			return paciente;
		}

		public void setPaciente(Paciente paciente) {
			this.paciente = paciente;
		}
		
		public void limpar() {
			paciente = null;
		}
		
		
		public List<SelectItem> getListaSexo() {
			if(listaSexo == null) {
				listaSexo = new ArrayList<SelectItem>();
				
				DAO<Sexo> dao = new SexoDAO();
				List<Sexo> sexoLista = dao.findAll();
				
				if(sexoLista != null && !sexoLista.isEmpty()) {
					SelectItem item;
					
					for (Sexo sexo : sexoLista) {
						item = new SelectItem(sexo, sexo.getNome());
						listaSexo.add(item);
					}
				}
			}
			
			return listaSexo;
		}

		public List<SelectItem> getListaSituacao() {
			if(listasituacao == null) {
				listasituacao = new ArrayList<SelectItem>();
				
				DAO<Situacao> dao = new SituacaoDAO();
				List<Situacao> situacaoLista = dao.findAll();
				
				if(situacaoLista != null && !situacaoLista.isEmpty()) {
					SelectItem item;
					  
					for (Situacao situacao : situacaoLista) {
						item = new SelectItem(situacao, situacao.getNome());
						listasituacao.add(item);
					}
				}
			}
			
			return listasituacao;
		}
		public List<SelectItem> getListaDepartamento() {
			if(listadepartamento == null) {
				listadepartamento = new ArrayList<SelectItem>();
				
				DepartamentoDAO dao = new DepartamentoDAO();
				List<Departamento> departamentoLista = dao.findDepartamentoPaciente();
				
				if(departamentoLista != null && !departamentoLista.isEmpty()) {
					SelectItem item;
					
					for (Departamento departamento : departamentoLista) {
						item = new SelectItem(departamento, departamento.getNomeDepartamento());
						listadepartamento.add(item);
					}
				}
			}
			
			return listadepartamento;
		}
		
		public List<SelectItem> getlistaprecaucao() {
			if(listaprecaucao == null) {
				listaprecaucao= new ArrayList<SelectItem>();
				
				DAO<Precaucao> dao = new PrecaucaoDAO();
				List<Precaucao> precaucaoLista = dao.findAll();
				
				if(precaucaoLista != null && !precaucaoLista.isEmpty()) {
					SelectItem item;
					
					for (Precaucao precaucao : precaucaoLista) {
						item = new SelectItem(precaucao, precaucao.getNome());
						listaprecaucao.add(item);
					}
				}
			}
			
			return listaprecaucao;
		}
		
		public TipoSexo[] getListaTipoSexo() {
			return TipoSexo.values();
		}

	}