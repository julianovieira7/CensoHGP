package br.unitins.censohgp.controller;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.censohgp.dao.DAO;
import br.unitins.censohgp.dao.PacienteDAO;
import br.unitins.censohgp.application.Util;
import br.unitins.censohgp.model.Paciente;
import br.unitins.censohgp.model.SituacaoPaciente;
import br.unitins.censohgp.model.TipoSexo;

@Named
@ViewScoped
public class CadastroPacienteController implements Serializable {

	private static final long serialVersionUID = -3687442881189379368L;

		private Paciente paciente;
		
		private List<Paciente> listaPaciente;
		
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
			
			return "cadastropaciente.xhtml?faces-redirect=true";
		}

		public void incluir() {
			if (validarDados()) {
				DAO<Paciente> dao = new PacienteDAO();
				// faz a inclusao no banco de dados
				try {
					dao.create(getPaciente());
					dao.getConnection().commit();
					Util.addMessageInfo("Inclusão realizada com sucesso.");
					limpar();
					listaPaciente = null;
				} catch (SQLException e) {
					dao.rollbackConnection();
					dao.closeConnection();
					Util.addMessageInfo("Erro ao incluir o Usuário no Banco de Dados.");
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
					Util.addMessageInfo("Alteração realizada com sucesso.");
					limpar();
					listaPaciente = null;
				} catch (SQLException e) {
					dao.rollbackConnection();
					dao.closeConnection();
					Util.addMessageInfo("Erro ao alterar o Usuário no Banco de Dados.");
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
				Util.addMessageInfo("Exclusão realizada com sucesso.");
				return true;
			} catch (SQLException e) {
				dao.rollbackConnection();
				Util.addMessageInfo("Erro ao excluir o Produto no Banco de Dados.");
				e.printStackTrace();
				return false;
			} finally {
				dao.closeConnection();
			}
		}

		private boolean validarDados() {
		if (getPaciente().getNome().isBlank()) {
				Util.addMessageWarn("O campo senha deve ser informado.");
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
			}
			return paciente;
		}

		public void setUsuario(Paciente paciente) {
			this.paciente = paciente;
		}
		
		public void limpar() {
			paciente = null;
		}
		
		public TipoSexo[] getListaTipoSexo() {
			return TipoSexo.values();
		}
		public SituacaoPaciente[] getListaSituacao() {
			return SituacaoPaciente.values();
		}
		
	}