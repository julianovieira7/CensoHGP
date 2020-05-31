package br.unitins.censohgp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.unitins.censohgp.model.Paciente;
import br.unitins.censohgp.model.Usuario;

public class UsuarioDAO extends DAO<Usuario> {
//TODO: Adicionar o atributo matr�cula e adicionar os tipo no ENUM TIPO
	public UsuarioDAO(Connection conn) {
		super(conn);
	}

	public UsuarioDAO() {
		super(null);
	}

//	SELECT idusuario, nome, senha, idtipo_usuario, ativo, email, matricula
//	FROM public.usuario;

	public Usuario login(String matricula, String senha) {

		Connection conn = getConnection();

		try {
			PreparedStatement stat = conn.prepareStatement(
					"SELECT " + "  idusuario, " + "  nome, " + "  senha, " + "  idtipo_usuario, " + "  email "
							+ "  matricula, " + "FROM " + "  public.usuario " + "WHERE matricula = ? AND senha = ? ");

			stat.setString(1, matricula);
			stat.setString(2, senha);

			ResultSet rs = stat.executeQuery();

			Usuario usuario = null;

			if (rs.next()) {
				usuario = new Usuario();
				usuario.setId(rs.getInt("idusuario"));
				usuario.setNome(rs.getString("nome"));
				usuario.setSenha(rs.getString("senha"));
				usuario.getTipo().setId(rs.getInt("idtipo_usuario"));
//				usuario.setAtivo(rs.getBoolean("ativo"));
				usuario.setEmail(rs.getString("email"));
				usuario.setMatricula("matricula");
//				como vou setar o idtipo usuario?
			}

			return usuario;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}

//	INSERT INTO public.usuario(
//			idusuario, nome, senha, idtipo_usuario, ativo, email, matricula)
//			VALUES (?, ?, ?, ?, ?, ?, ?);

	@Override
	public void create(Usuario usuario) throws SQLException {

		Connection conn = getConnection();

		PreparedStatement stat = conn.prepareStatement("INSERT INTO " + "public.usuario "
				+ " (nome, senha, idtipo_usuario, email, matricula) " + "VALUES " + " (?, ?, ?, ?, ?) ",
				Statement.RETURN_GENERATED_KEYS);
		stat.setString(1, usuario.getNome());
		stat.setString(2, usuario.getSenha());
		stat.setInt(3, usuario.getTipo().getId());
//		stat.setBoolean(4, usuario.getAtivo());
		stat.setString(4, usuario.getEmail());
		stat.setString(5, usuario.getMatricula());

		stat.execute();

		// obtendo o id gerado pela tabela do banco de dados
		ResultSet rs = stat.getGeneratedKeys();
		rs.next();

//		Integer value = rs.getInt("idusuario");
	}

//	UPDATE public.usuario
//	SET idusuario=?, nome=?, senha=?, idtipo_usuario=?, ativo=?, email=?, matricula=?
//	WHERE <condition>;

	@Override
	public void update(Usuario usuario) throws SQLException {
		Connection conn = getConnection();

		PreparedStatement stat = conn.prepareStatement(
				"UPDATE public.usuario SET " + " nome = ?, " + " senha = ?, " + " idtipo_usuario = ?, " + " ativo = ? "
						+ " email = ? " + " matricula = ? " + "WHERE " + " idusuario = ? ");
		stat.setString(1, usuario.getNome());
		stat.setString(2, usuario.getSenha());
		stat.setInt(3, usuario.getTipo().getId());
		stat.setBoolean(4, usuario.getAtivo());
		stat.setString(5, usuario.getEmail());
		stat.setString(6, usuario.getMatricula());
		stat.setInt(7, usuario.getId());

		stat.execute();

	}

	@Override
	public void delete(int id) throws SQLException {

		Connection conn = getConnection();
		// deletando o usuario
		PreparedStatement stat = conn.prepareStatement("DELETE FROM public.usuario WHERE idusuario = ?");
		stat.setInt(1, id);

		stat.execute();

	}

	@Override
	public List<Usuario> findAll() {
		Connection conn = getConnection();
		if (conn == null)
			return null;

		try {
			PreparedStatement stat = conn.prepareStatement("SELECT " + "  u.idusuario, " + "  u.nome, " + "  u.senha, "
					+ "  u.idtipo_usuario, " + "  u.email" + "  u.matricula" + "FROM " + " usuario u ");
			ResultSet rs = stat.executeQuery();

			List<Usuario> listaUsuario = new ArrayList<Usuario>();

			while (rs.next()) {
				Usuario usuario = new Usuario();
				usuario.setId(rs.getInt("idusuario"));
				usuario.setNome(rs.getString("nome"));
				usuario.setSenha(rs.getString("senha"));
				usuario.getTipo().setId(rs.getInt("idtipo_usuario"));
				usuario.setEmail(rs.getString("email"));
				usuario.setMatricula(rs.getString("matricula"));
				listaUsuario.add(usuario);
			}

			if (listaUsuario.isEmpty())
				return null;
			return listaUsuario;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Usuario> findByNome(String nome) {
		Connection conn = getConnection();
		if (conn == null)
			return null;

		try {
			PreparedStatement stat = conn.prepareStatement("SELECT " + " idusuario, " + "matricula, " + " nome, " + " senha, "
					+ " idtipo_usuario, " + " email " + "  FROM " + " usuario " + "WHERE " + "  nome ilike ? ");

			stat.setString(1, nome == null ? "%" : "%" + nome + "%");
			ResultSet rs = stat.executeQuery();

			List<Usuario> listaUsuario = new ArrayList<Usuario>();

			while (rs.next()) {
				Usuario usuario = new Usuario();
				usuario = new Usuario();
				usuario.setId(rs.getInt("idusuario"));
				usuario.setMatricula(rs.getString("matricula"));
				usuario.setNome(rs.getString("nome"));
				usuario.setSenha(rs.getString("senha"));
//				usuario.getTipo().setId(rs.getInt("idtipo_usuario"));
				usuario.setEmail(rs.getString("email"));

				listaUsuario.add(usuario);

			}

			if (listaUsuario.isEmpty())
				return null;
			return listaUsuario;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Usuario findId(Integer id) {
		Connection conn = getConnection();
		if (conn == null)
			return null;

		try {
			PreparedStatement stat = conn.prepareStatement(
					"SELECT " + "  idusuario, " + "  matricula, " + "  nome, " + "  senha, " + "  idtipo_usuario, "
							+ "  ativo, " + "  email " + "FROM " + "  public.usuario " + "WHERE id = ? ");

			stat.setInt(1, id);

			ResultSet rs = stat.executeQuery();

			Usuario usuario = null;

			if (rs.next()) {
				usuario = new Usuario();
				usuario.setId(rs.getInt("idusuario"));
				usuario.setMatricula("matricula");
				usuario.setNome(rs.getString("nome"));
				usuario.setSenha(rs.getString("senha"));
				usuario.getTipo().setId(rs.getInt("idtipo_usuario"));
				usuario.setAtivo(rs.getBoolean("ativo"));
				usuario.setEmail(rs.getString("email"));

			}

			return usuario;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
