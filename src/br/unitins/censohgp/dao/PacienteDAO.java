package br.unitins.censohgp.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.unitins.censohgp.model.Paciente;
import br.unitins.censohgp.model.Situacao;
import br.unitins.censohgp.model.Sexo;

public class PacienteDAO extends DAO<Paciente>{

	public PacienteDAO(Connection conn) {
		super(conn);
	}

	public PacienteDAO() {
		super(null);
	}

	@Override
	public void create(Paciente paciente) throws SQLException {

		Connection  conn = getConnection();

		PreparedStatement stat = conn.prepareStatement(
				"INSERT INTO " +
						" paciente " +
						" ( nome, cpf, rg, ativo, nome_mae, data_nascimento, observacao, numero_prontuario) " +
						" VALUES " +
						" (?, ?, ?, ?, ?, ?, ?, ?, ?) ", Statement.RETURN_GENERATED_KEYS);

		stat.setString(1, paciente.getNome());
		stat.setString(2, paciente.getCpf());
		stat.setString(3, paciente.getRg());
		stat.setBoolean(4, paciente.getAtivo());
		stat.setString(5, paciente.getNomeMae());
		Date date = Date.valueOf(paciente.getDataNascimento());
		stat.setDate(6, date);
		stat.setString(7, paciente.getObservacao());
		stat.setString(8, paciente.getNumeroProntuario());
		stat.execute();
		
	}

	@Override
	public void update(Paciente paciente) throws SQLException {
		Connection conn = getConnection();

		PreparedStatement stat = conn.prepareStatement(
				"UPDATE paciente SET " +
						"  nome = ?,"
						+ " cpf = ?,"
						+ " rg = ?,"
						+ " ativo = ?,"
						+ " nome_mae = ?,"
						+ " data_nascimento = ?,"
						+ " observacao = ?,"
						+ " numero_prontuario = ?, " 
						+ " idsituacao = ? " +
						" WHERE " +
				" idpaciente = ? ");
		
		stat.setString(1, paciente.getNome());
		stat.setString(2, paciente.getCpf());
		stat.setString(3, paciente.getRg());
		stat.setBoolean(4, paciente.getAtivo());
		stat.setString(5, paciente.getNomeMae());
		Date date = Date.valueOf(paciente.getDataNascimento());
		stat.setDate(6, date);
		stat.setString(7, paciente.getObservacao());
		stat.setString(8, paciente.getNumeroProntuario());
		stat.setInt(9, paciente.getIdpaciente());
		stat.execute();

	}

	@Override
	public void delete(int id) throws SQLException {

		Connection conn = getConnection();

		SituacaoDAO dao = new SituacaoDAO(conn);
		dao.delete(id);
		
		PreparedStatement stat = conn.prepareStatement("DELETE FROM public.paciente WHERE idpaciente = ?");
		stat.setInt(1, id);

		stat.execute();

	}
	
	public List<Paciente> findByNomeMae(String nomeMae) {
		Connection conn = getConnection();
		if (conn == null) 
			return null;

		try {
			PreparedStatement stat = conn.prepareStatement(
					"SELECT " +
							" idpaciente, "
							+ "nome,"
							+ " cpf,"
							+ " rg,"
							+ " ativo,"
							+ " nome_mae,"
							+ " data_nascimento,"
							+ " observacao,"
							+ " numero_prontuario, " 
							+ "  idsituacao  " +
							"  FROM " +
							"  paciente " +			
							"WHERE " +
					"  nome_mae ilike ? ");

			stat.setString(1, nomeMae == null ? "%" : "%"+nomeMae+"%");
			ResultSet rs = stat.executeQuery();

			List<Paciente> listaPaciente = new ArrayList<Paciente>();

			while(rs.next()) {
				Paciente paciente = new Paciente();
				paciente.setIdpaciente(rs.getInt("idpaciente"));
				paciente.setNome(rs.getString("nome"));
				paciente.setCpf(rs.getString("cpf"));
				paciente.setRg(rs.getString("rg"));
				paciente.setAtivo(rs.getBoolean("ativo"));
				paciente.setNomeMae(rs.getString("nome_mae"));
				paciente.setDataNascimento(rs.getDate("data_nascimento").toLocalDate());
				paciente.setObservacao(rs.getString("observacao"));
				paciente.setNumeroProntuario(rs.getString("numero_prontuario"));
				
				listaPaciente.add(paciente);

				

			}			 			

			if (listaPaciente.isEmpty())
				return null;
			return listaPaciente;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Paciente> findByNome(String nome) {
		Connection conn = getConnection();
		if (conn == null) 
			return null;

		try {
			PreparedStatement stat = conn.prepareStatement(
					"SELECT " +
							" idpaciente, "
							+ " nome, "
							+ " cpf, "
							+ " rg, "
							+ " ativo, "
							+ " nome_mae, "
							+ " data_nascimento, "
							+ " observacao, "
							+ " numero_prontuario, " 
							+ "  idsituacao " +
							"  FROM " +
							"  paciente " +			
							" WHERE " +
					"  nome ilike ? ");

			stat.setString(1, nome == null ? "%" : "%"+nome+"%");
			ResultSet rs = stat.executeQuery();

			List<Paciente> listaPaciente = new ArrayList<Paciente>();

			while(rs.next()) {
				Paciente paciente = new Paciente();
				paciente.setIdpaciente(rs.getInt("idpaciente"));
				paciente.setNome(rs.getString("nome"));
				paciente.setCpf(rs.getString("cpf"));
				paciente.setRg(rs.getString("rg"));
				paciente.setAtivo(rs.getBoolean("ativo"));
				paciente.setNomeMae(rs.getString("nome_mae"));
				paciente.setDataNascimento(rs.getDate("data_nascimento").toLocalDate());
				paciente.setObservacao(rs.getString("observacao"));
				paciente.setNumeroProntuario(rs.getString("numero_prontuario"));
				
				listaPaciente.add(paciente);

				

			}			 			

			if (listaPaciente.isEmpty())
				return null;
			return listaPaciente;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Paciente> findByCodigo(String codigo) {
		Connection conn = getConnection();
		if (conn == null) 
			return null;

		try {
			PreparedStatement stat = conn.prepareStatement(
					"SELECT " +
							" idpaciente, "
							+ "nome,"
							+ " cpf,"
							+ " rg,"
							+ " ativo,"
							+ " nome_mae,"
							+ " data_nascimento,"
							+ " observacao,"
							+ " numero_prontuario, " 
							+ "  idsituacao  " +
							"  FROM " +
							"  paciente " +			
							"WHERE " +
					"  numero_prontuario ilike ? ");

			stat.setString(1, codigo == null ? "%" : "%"+codigo+"%");
			ResultSet rs = stat.executeQuery();

			List<Paciente> listaPaciente = new ArrayList<Paciente>();

			while(rs.next()) {
				Paciente paciente = new Paciente();
				paciente.setIdpaciente(rs.getInt("idpaciente"));
				paciente.setNome(rs.getString("nome"));
				paciente.setCpf(rs.getString("cpf"));
				paciente.setRg(rs.getString("rg"));
				paciente.setAtivo(rs.getBoolean("ativo"));
				paciente.setNomeMae(rs.getString("nome_mae"));
				paciente.setDataNascimento(rs.getDate("data_nascimento").toLocalDate());
				paciente.setObservacao(rs.getString("observacao"));
				paciente.setNumeroProntuario(rs.getString("numero_prontuario"));
				
				listaPaciente.add(paciente);

				

			}			 			

			if (listaPaciente.isEmpty())
				return null;
			return listaPaciente;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Paciente> findByCpf(String cpf) {
		Connection conn = getConnection();
		if (conn == null) 
			return null;

		try {
			PreparedStatement stat = conn.prepareStatement(
					"SELECT " +
							" idpaciente, "
							+ "nome,"
							+ " cpf,"
							+ " rg,"
							+ " ativo,"
							+ " nome_mae,"
							+ " data_nascimento,"
							+ " observacao,"
							+ " numero_prontuario, " 
							+ "  idsituacao  " +
							"  FROM " +
							"  paciente " +			
							"WHERE " +
					"  cpf ilike ? ");

			stat.setString(1, cpf == null ? "%" : "%"+cpf+"%");
			ResultSet rs = stat.executeQuery();

			List<Paciente> listaPaciente = new ArrayList<Paciente>();

			while(rs.next()) {
				Paciente paciente = new Paciente();
				paciente.setIdpaciente(rs.getInt("idpaciente"));
				paciente.setNome(rs.getString("nome"));
				paciente.setCpf(rs.getString("cpf"));
				paciente.setRg(rs.getString("rg"));
				paciente.setAtivo(rs.getBoolean("ativo"));
				paciente.setNomeMae(rs.getString("nome_mae"));
				paciente.setDataNascimento(rs.getDate("data_nascimento").toLocalDate());
				paciente.setObservacao(rs.getString("observacao"));
				paciente.setNumeroProntuario(rs.getString("numero_prontuario"));
				
				listaPaciente.add(paciente);

				

			}			 			

			if (listaPaciente.isEmpty())
				return null;
			return listaPaciente;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public List<Paciente> findAll() {
		Connection conn = getConnection();
		if (conn == null) 
			return null;

		try {
			PreparedStatement stat = conn.prepareStatement(
					"SELECT " +
							" idpaciente, "
							+ "nome,"
							+ " cpf,"
							+ " rg,"
							+ " ativo,"
							+ " nome_mae,"
							+ " idsituacao,"
							+ " data_nascimento,"
							+ " observacao,"
							+ " numero_prontuario  " +
							"FROM " +
							"  paciente ");

			ResultSet rs = stat.executeQuery();

			List<Paciente> listaPaciente = new ArrayList<Paciente>();

			while(rs.next()) {
				Paciente paciente = new Paciente();
				paciente.setIdpaciente(rs.getInt("idpaciente"));
				paciente.setNome(rs.getString("nome"));
				paciente.setCpf(rs.getString("cpf"));
				paciente.setRg(rs.getString("rg"));
				paciente.setAtivo(rs.getBoolean("ativo"));
				paciente.setNomeMae(rs.getString("nome_mae"));
				paciente.setDataNascimento(rs.getDate("data_nascimento").toLocalDate());
				paciente.setObservacao(rs.getString("observacao"));
				paciente.setNumeroProntuario(rs.getString("numero_prontuario"));
				
				listaPaciente.add(paciente);

			}			 			

			if (listaPaciente.isEmpty())
				return null;
			return listaPaciente;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Paciente findById(Integer id) {
		Connection conn = getConnection();
		if (conn == null) 
			return null;

		try {
			PreparedStatement stat = conn.prepareStatement(
					"SELECT " +
							" idpaciente, "
							+ " nome,"
							+ " cpf,"
							+ " rg,"
							+ " ativo,"
							+ " nome_mae,"
							+ " idsituacao,"
							+ " data_nascimento,"
							+ " observacao,"
							+ " numero_prontuario " +
							" FROM " +
							"  paciente "
							+ "  WHERE idpaciente = ? ");

			stat.setInt(1, id);

			ResultSet rs = stat.executeQuery();

			Paciente paciente = null;

			if(rs.next()) {
				paciente = new Paciente();
				paciente.setIdpaciente(rs.getInt("idpaciente"));
				paciente.setNome(rs.getString("nome"));
				paciente.setCpf(rs.getString("cpf"));
				paciente.setRg(rs.getString("rg"));
				paciente.setAtivo(rs.getBoolean("ativo"));
				paciente.setNomeMae(rs.getString("nome_mae"));
				paciente.setDataNascimento(rs.getDate("data_nascimento").toLocalDate());
				paciente.setObservacao(rs.getString("observacao"));
				paciente.setNumeroProntuario(rs.getString("numero_prontuario"));

			}


			return paciente;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}