package controller;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;

import dao.ReservaDao;
import factory.ConnectionFactory;
import modelo.Reserva;

public class ReservaController {
	 private ReservaDao reservaDao;

	    public ReservaController() {
	        Connection connection = new ConnectionFactory().criarConexao();
	        this.reservaDao = new ReservaDao(connection);
	    }

	    public void cadastrar(Reserva reserva) {
	        this.reservaDao.cadastrar(reserva);
	    }

	    public Reserva buscarPorId(int id) {
	        return reservaDao.buscarReservaPorId(id);
	    }

	    public void deletarReserva(int id) {
	    	reservaDao.deletar(id);
	    }

	    public void alterarReserva(Date dataE, Date dataS, BigDecimal valor, String formaPagamento, Integer id){
	        this.reservaDao.alterar(dataE,dataS,valor,formaPagamento,id);
	    }
	}