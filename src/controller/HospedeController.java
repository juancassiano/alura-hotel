package controller;

import java.sql.Connection;
import java.sql.Date;

import dao.HospedeDao;
import factory.ConnectionFactory;
import modelo.Hospede;

public class HospedeController {

	private HospedeDao hospedeDao;
	
	public HospedeController() {
		Connection connection = new ConnectionFactory().criarConexao();
		this.hospedeDao = new HospedeDao(connection);
	}
	
	public void cadastrar(Hospede hospede) {
		this.hospedeDao.salvar(hospede);
	}
	
	public Hospede buscarPorNome(String nome) {
		return this.hospedeDao.buscarPorNome(nome);
	}
	
	public void deletar(int id) {
		this.hospedeDao.deletar(id);
	}
	
	 public void alterar(String nome, String sobrenome, Date nasc, String nacionalidade, String tell, Integer id) {
	        this.hospedeDao.alterar(nome, sobrenome, nasc, nacionalidade, tell, id);
	    }
	
}
