package connection

import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

class Conexao {
    fun fazerConexao(): Connection {
        return try {
            val url = "jdbc:postgresql://localhost:5432/vendas"
            val connection = DriverManager.getConnection(url, "postgres", "1234")

            if (connection != null) {
                println("Conexão deu certo!")
            } else {
                println("Conexão deu errado!")
            }
            connection
        } catch (e: SQLException) {
            e.printStackTrace()
            throw RuntimeException("Erro ao conectar ao banco de dados")
        }
    }

}
