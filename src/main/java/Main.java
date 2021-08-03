import kannon.aaron.dao.Curso;
import kannon.aaron.dao.CursoDAO;
import kannon.aaron.db.ConnectionDB;
import kannon.aaron.db.ConnectionMySQL;
import kannon.aaron.db.ConnectionPostgreSQL;

public class Main {
    public static void main(String[] args) {
        ConnectionDB mysql = new ConnectionMySQL();
        mysql.connectDB();

        ConnectionDB postgres = new ConnectionPostgreSQL();
        postgres.connectDB();

        CursoDAO dao1 = new CursoDAO("mysql.properties");
        CursoDAO dao2 = new CursoDAO("postgresql.properties");

        Curso addCurso = new Curso("Curso de Programação para RPG", "60h");

        // Adicionar (CREATE)
        System.out.println("Banco MySQL");
        dao1.create(addCurso);
        System.out.println("Banco PostgreSQL");
        dao2.create(addCurso);

        // Leitura (READ)
        System.out.println("Banco MySQL");
        dao1.list().stream().forEach(System.out::println);
        System.out.println("Banco PostgreSQL");
        dao2.list().stream().forEach(System.out::println);

        // Atualizar (UPDATE)
        Curso updateCurso = new Curso(3,"Curso de Programação para RPG", "60h");

        System.out.println("Banco MySQL");
        dao1.update(updateCurso);
        System.out.println("Banco PostgreSQL");
        dao2.update(updateCurso);

        // Remover (DELETE)
        System.out.println("Banco MySQL");
        dao1.delete(3);
        System.out.println("Banco PostgreSQL");
        dao2.delete(3);
    }
}
