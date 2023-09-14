package service

import connection.Conexao
import java.sql.SQLException

class ClienteService {
    companion object {
        private var connection = Conexao().fazerConexao()
        fun inserirCliente(nome: String, email: String, cpf: String, endereco: String) {
            var clientesAdicionados = false
            try {
                val sql = "INSERT INTO cliente (nome, email, cpf, endereco) " +
                        "VALUES ('$nome', '$email', '$cpf', '$endereco')"
                val statement = connection.createStatement()
                statement.executeUpdate(sql)
                println("Cliente $nome adicionado com sucesso!")

                if (!clientesAdicionados) {
                    println("Não foi cadastrado nenhum cliente, tente novamente!")
                }
            } catch (e: SQLException) {
                e.printStackTrace()
            }
        }
        fun consultaTodosOsCliente() {
            var livrosEncontrados = false
            try {
                val statement = connection.createStatement()
                val resultSet = statement.executeQuery("SELECT * FROM cliente")
                while (resultSet.next()) {
                    livrosEncontrados = true
                    println(
                        "ID: " + resultSet.getInt("id_cliente") +
                                " | NOME: " + resultSet.getString("nome") +
                                " | EMAIL: " + resultSet.getString("email") +
                                " | CPF: " + resultSet.getString("cpf") +
                                " | ENDEREÇO: " + resultSet.getString("endereco")
                    )
                }
                if (!livrosEncontrados) {
                    println("Ainda não foi cadastrado nenhum cliente.")
                }
            } catch (e: SQLException) {
                e.printStackTrace()
            }
        }
        fun atualizarCliente(id: Int, email:String, endereco:String) {
            try {
                val sql = "UPDATE cliente SET email ='$email'," +
                        " endereco = '$endereco' WHERE id=$id"
                val statement = connection.createStatement()
                statement.executeUpdate(sql)
                println("Cliente com id $id atualizado com sucesso!")
                statement.close()
            } catch (e: SQLException) {
                e.printStackTrace()
            }
        }
        fun deletarCliente(id: Int) {
            val statement = connection.createStatement()
            val rowCount = statement.executeUpdate("DELETE FROM cliente WHERE id = $id")

            try {
                if (rowCount > 0) {
                    println("Cliente $id foi deletado com sucesso!")
                } else {
                    println("Cliente $id não encontrado!")
                }
            } catch (e: SQLException) {
                e.printStackTrace()
            }
        }
        fun consultarClientePorId(id: Int) {
            try {
                val statement = connection.createStatement()
                val resultSet = statement.executeQuery("SELECT * FROM cliente")

                if (resultSet.next()) {
                    println("=====CLIENTE ENCONTRADO=====")
                    println(
                        "ID: " + resultSet.getInt("id_cliente") +
                                " | NOME: " + resultSet.getString("nome") +
                                " | EMAIL: " + resultSet.getString("email") +
                                " | CPF: " + resultSet.getString("cpf") +
                                " | ENDEREÇO: " + resultSet.getString("endereco")
                    )
                } else {
                    println("Cliente com ID $id não encontrado, tente novamente")
                }
            } catch (e: SQLException) {
                e.printStackTrace()
            }
        }
    }
}