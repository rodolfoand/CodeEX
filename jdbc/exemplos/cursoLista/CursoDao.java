package br.senai.sp.informatica.mobileb.jdbc.exemplos.cursoLista;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class CursoDao {
	public static CursoDao manager = new CursoDao();

	private CursoDao() {
	}

	private Connection getConnection() throws GerenteException {
		try {
			Class.forName("com.mysql.jdbc.Driver");

			return DriverManager.getConnection("jdbc:mysql://localhost/CursoJava", "root", "senai");
		} catch (ClassNotFoundException ex) {
			throw new GerenteException("Houve problema em localizar o Driver JDBC");
		} catch (SQLException ex) {
			throw new GerenteException("Houve problema ao conectar ao Banco de Dados CursoJava");
		}
	}

	// Insere um novo registro ou Atualiza um registro existente
	public void salvar(Curso obj) throws GerenteException {
		String acao = "";
		Connection con = getConnection();
		try {
			if (obj.getId() == null) {
				acao = "inserção";
				PreparedStatement insereFone = con.prepareStatement("insert into Curso (nome, matricula, descricao) values (?, ?, ?)");
				insereFone.setString(1, obj.getNome());
				insereFone.setDate(2, Date.valueOf(obj.getMatricula()));
				insereFone.setString(3, obj.getDescricao());
				insereFone.execute();
			} else {
				acao = "atualização";
				PreparedStatement atualizaFone = con.prepareStatement("update Curso set nome=?, matricula=?, descricao=? where idcurso=?");
				atualizaFone.setString(1, obj.getNome());
				atualizaFone.setDate(2, Date.valueOf(obj.getMatricula()));
				atualizaFone.setString(3, obj.getDescricao());
				atualizaFone.setInt(4, obj.getId());
				atualizaFone.execute();
			}
		} catch (SQLException ex) {
			throw new GerenteException("Houve problema na " + acao + " do registro");
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				throw new GerenteException("Houve problema no fechamento da conexão");
			}
		}

	}

	// Cria um objeto Telefone extraindo os dados do Resultado da consulta SQL
	private Curso criaObjeto(ResultSet resultado) throws SQLException {
		Curso obj = new Curso();
		obj.setId(resultado.getInt("idcurso"));
		obj.setNome(resultado.getString("nome"));
		obj.setMatricula(resultado.getDate("matricula").toLocalDate());
		obj.setDescricao(resultado.getString("descricao"));
		return obj;
	}

	// Retorna a lista de todos os Telefones existentes no banco de dados
	public List<Curso> listaTodos() throws GerenteException {
		Connection con = getConnection();
		try {			
			List<Curso> lista = new ArrayList<>();
			Statement listaFone = con.createStatement();
			
			ResultSet resposta = listaFone.executeQuery("select * from curso");
			while(resposta.next()) {
				Curso obj = criaObjeto(resposta);
				lista.add(obj);
			}
			
			return lista;
		} catch(SQLException ex) {
			throw new GerenteException("Erro na consulta dos registros");
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				throw new GerenteException("Houve problema no fechamento da conexão");
			}
		}
	}

	public Curso localiza(Integer id) throws GerenteException {
		Connection con = getConnection();
		try {			
			Curso obj = null;
			PreparedStatement localizaFone = con.prepareStatement("select * from curso where idcurso = ?");
			localizaFone.setInt(1, id);

			ResultSet resposta = localizaFone.executeQuery();
			if(resposta.next()) {
				obj = criaObjeto(resposta);
			}
			
			return obj;
		} catch(SQLException ex) {
			throw new GerenteException("Erro na consulta dos registros");
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				throw new GerenteException("Houve problema no fechamento da conexão");
			}
		}
	}

	public void apaga(Integer id) throws GerenteException {
		Connection con = getConnection();
		try {			
			PreparedStatement apagaFone = con.prepareStatement("delete from curso where idcurso = ?");
			apagaFone.setInt(1, id);

			apagaFone.executeUpdate();
		} catch(SQLException ex) {
			throw new GerenteException("Erro na consulta dos registros");
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				throw new GerenteException("Houve problema no fechamento da conexão");
			}
		}
	}

}
