package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import modelo.Hospede;

public class HospedeDao {
	private Connection connection;
	
	public HospedeDao(Connection connection) {
		this.connection = connection;
	}
	
	public void salvar(Hospede hospede) {
        String sql = "INSERT INTO hospedes (nome, sobrenome, data_nascimento, nacionalidade, telefone, reserva_id) VALUES (?, ?, ?, ?, ?, ?)";
        
        try(PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
        	pstm.setString(1, hospede.getNome());
            pstm.setString(2, hospede.getSobrenome());
            pstm.setDate(3, Date.valueOf(hospede.getDataNascimento().toLocalDate()));
            pstm.setString(4, hospede.getNacionalidade());
            pstm.setString(5, hospede.getTelefone());
            pstm.setInt(6, hospede.getReservaId());

            pstm.execute();
            
            try (ResultSet rst = pstm.getGeneratedKeys()){
            	if(rst.next()) {
            		hospede.setId(rst.getInt(1));
            	}
            }
        }catch (SQLException e) {
        	System.out.println("Não foi possível salvar o hóspede: " + e.getMessage());
        }
	}
	
	
	public Hospede buscarPorNome(String nome) {
		Hospede hospede = null;
        String sql = "SELECT * FROM hospedes WHERE nome LIKE ?";
       
        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.setString(1, "%" + nome + "%");
            try (ResultSet rs = pstm.executeQuery()) {
                while (rs.next()) {
                    hospede = new Hospede();
                    hospede.setId(rs.getInt("id"));
                    hospede.setNome(rs.getString("nome"));
                    hospede.setSobrenome(rs.getString("sobrenome"));
                    hospede.setDataNascimento(rs.getDate("data_nascimento"));
                    hospede.setNacionalidade(rs.getString("nacionalidade"));
                    hospede.setTelefone(rs.getString("telefone"));
                    hospede.setReservaId(rs.getInt("reserva_id"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hospede;
    }
	

    public void deletar(Integer id) {
    	String sql = "DELETE FROM hospedes WHERE ID = ?";
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setInt(1, id);
            stm.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void alterar(String nome, String sobrenome, Date nasc, String nacionalidade, String tell, Integer id) {
    	String sql = "UPDATE hospedes h SET h.nome = ?, h.sobrenome = ?, h.data_nascimento = ?, h.nacionalidade = ?, h.telefone = ? WHERE ID = ?";
        try (PreparedStatement pstm = connection
                .prepareStatement(sql)) {
            pstm.setString(1, nome);
            pstm.setString(2, sobrenome);
            pstm.setDate(3, nasc);
            pstm.setString(4, nacionalidade);
            pstm.setString(5, tell);
            pstm.setInt(6, id);
            pstm.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}