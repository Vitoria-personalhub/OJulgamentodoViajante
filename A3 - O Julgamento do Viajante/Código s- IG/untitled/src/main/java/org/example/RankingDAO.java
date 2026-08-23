package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RankingDAO {

    // Salva o resultado da partida
    public static void salvarResultado(String resultado, int saude, int honra, int dinheiro) {
        String sql = "INSERT INTO rc_table (resultado, saude_final, honra_final, dinheiro_final) VALUES (?, ?, ?, ?)";

        try (Connection conn = Database.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, resultado);
            pstmt.setInt(2, saude);
            pstmt.setInt(3, honra);
            pstmt.setInt(4, dinheiro);

            pstmt.executeUpdate();
            System.out.println(">> Resultado salvo no ranking!");

        } catch (SQLException e) {
            System.out.println("Erro ao salvar no ranking: " + e.getMessage());
        }
    }

    // Exibe todo o ranking
    public static void exibirRanking() {
        String sql = "SELECT id, resultado, saude_final, honra_final, dinheiro_final, data_registro FROM rc_table ORDER BY id";

        try (Connection conn = Database.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            System.out.println("\n====== RANKING DE PARTIDAS ======");
            while (rs.next()) {
                System.out.printf("ID: %d | Resultado: %s | Saúde: %d | Honra: %d | Dinheiro: %d | Data: %s%n",
                        rs.getInt("id"),
                        rs.getString("resultado"),
                        rs.getInt("saude_final"),
                        rs.getInt("honra_final"),
                        rs.getInt("dinheiro_final"),
                        rs.getString("data_registro"));
            }
            System.out.println("=================================\n");

        } catch (SQLException e) {
            System.out.println("Erro ao ler o ranking: " + e.getMessage());
        }
    }
}