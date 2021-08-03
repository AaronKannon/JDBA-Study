package kannon.aaron.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CursoDAO {

    private String properties;

    public CursoDAO(String properties) {
        this.properties = properties;
    }

    public List<Curso> list() {
        List<Curso> cursos = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getConnection(properties)) {
            String sql = "SELECT * FROM curso";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String duracaoHoras = rs.getString("duracao_horas");

                cursos.add(new Curso(
                        id,
                        nome,
                        duracaoHoras
                ));
            }
        } catch (SQLException e) {
            System.out.println("Listagem de cursos FALHOU!");
            e.printStackTrace();
        }
        return cursos;
    }

    public Curso getById(int id) {
        Curso curso = new Curso();

        try (Connection conn = ConnectionFactory.getConnection(properties)) {
            String sql = "SELECT * FROM curso WHERE id = ?";

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()){
                curso.setId(rs.getInt("id"));
                curso.setNome(rs.getString("nome"));
                curso.setDuracaoHoras(rs.getString("duracao_horas"));
            }

        } catch (SQLException e) {
            System.out.println("Listagem de Cursos FALHOU!");
            e.printStackTrace();
        }

        return curso;
    }

    public void create(Curso Curso) {
        try (Connection conn = ConnectionFactory.getConnection(properties)) {

            String sql = "INSERT INTO curso(nome, duracao_horas) VALUES(?, ?)";

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1 , Curso.getNome());
            stmt.setString(2, Curso.getDuracaoHoras());

            int rowsAffected = stmt.executeUpdate();

            System.out.println("Inserção BEM SUCEDIDA!. Foi adicionada " + rowsAffected + " linha");
        } catch (SQLException e) {
            System.out.println("Inserção FALHOU!");
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        try (Connection conn = ConnectionFactory.getConnection(properties)) {

            //Preparar SQL para deletar uma linha.
            String sql = "DELETE FROM curso WHERE id = ?";

            //Preparar statement com os parâmetros recebidos
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1 , id);

            //Executa delete e armazena o numero de linhas afetadas
            int rowsAffected = stmt.executeUpdate();

            System.out.println("Delete BEM SUCEDIDA! Foi deletada " + rowsAffected + " linha");
        } catch (SQLException e) {
            System.out.println("Delete FALHOU!");
            e.printStackTrace();
        }
    }

    public void update(Curso curso) {
        try (Connection conn = ConnectionFactory.getConnection(properties)) {

            String sql = "UPDATE curso SET nome = ?, duracao_horas = ? WHERE id = ?";

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, curso.getNome());
            stmt.setString(2, curso.getDuracaoHoras());
            stmt.setInt(3, curso.getId());

            int rowsAffected = stmt.executeUpdate();

            System.out.println("Atualização BEM SUCEDIDA! Foi atualizada: " + rowsAffected + " linha");
        } catch (SQLException e) {
            System.out.println("Atualização FALHOU!");
            e.printStackTrace();
        }
    }
}
