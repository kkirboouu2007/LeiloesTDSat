/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public void cadastrarProduto (ProdutosDTO produto) throws SQLException{
        
        conn = new conectaDAO().connectDB();
        prep = conn.prepareStatement("INSERT INTO produtos(nome, valor, status) VALUES (?, ?, ?)");
        prep.setString(1, produto.getNome());
        prep.setInt(2, produto.getValor());
        prep.setString(3, produto.getStatus());
        prep.executeUpdate();
        
    }
    
    public ArrayList<ProdutosDTO> listarProdutos(){
        try {
            String sql = "SELECT * FROM produtos";
            conn = new conectaDAO().connectDB();
            prep = conn.prepareStatement(sql);
            resultset = prep.executeQuery();
            ProdutosDTO produto = new ProdutosDTO();
            while (resultset.next()) {
                produto.setId(resultset.getInt("id"));
                produto.setNome(resultset.getString("nome"));
                produto.setValor(resultset.getInt("valor"));
                produto.setStatus(resultset.getString("status"));
                
                listagem.add(produto);
            } 
        } catch (SQLException ex) {
            System.getLogger(ProdutosDAO.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return listagem;
    }       
}

