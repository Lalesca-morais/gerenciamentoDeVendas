package service

import connection.Conexao
import java.sql.Connection
import java.sql.SQLException

class ClienteService {
    companion object {
        private var connection = Conexao().fazerConexao()
        fun inserirCliente(nome: String, email: String, cpf: String, endereco: String) {
            try {
                val sql = "INSERT INTO cliente (nome, email, cpf, endereco) " +
                        "VALUES ('$nome', '$email', '$cpf', '$endereco')"
                val statement = connection.createStatement()
                statement.executeUpdate(sql)
                println("Cliente $nome adicionado com sucesso!\n")
            } catch (e: SQLException) {
                e.printStackTrace()
            }
        }
        fun consultaTodosOsCliente() {
            var livrosEncontrados = false
            try {
                val statement = connection.createStatement()
                val resultSet = statement.executeQuery("SELECT * FROM cliente")
                println("=====CLIENTES CADASTRADOS=====")
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
        fun atualizarCliente(id_cliente: Int, email: String, endereco: String) {
            try {
                val sql = "UPDATE cliente SET email ='$email'," +
                        " endereco = '$endereco' WHERE id_cliente = $id_cliente "
                val statement = connection.createStatement()
                statement.executeUpdate(sql)
                println("Cliente com id $id_cliente atualizado com sucesso!")
                statement.close()
            } catch (e: SQLException) {
                e.printStackTrace()
            }
        }
        fun consultarClientePorId(id_cliente: Int) {
            try {
                val statement = connection.createStatement()
                val resultSet = statement.executeQuery("SELECT * FROM cliente WHERE id_cliente = $id_cliente")

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
                    println("Cliente com ID $id_cliente não encontrado, tente novamente")
                }
            } catch (e: SQLException) {
                e.printStackTrace()
            }
        }
        fun deletarCliente(id: Int) {
            try {
                val consultaVendasSql = "SELECT id FROM venda WHERE clienteid = $id"
                val statementConsulta = connection.createStatement()
                val resultadoConsulta = statementConsulta.executeQuery(consultaVendasSql)

                val idsVendasRelacionadas = mutableListOf<Int>()

                while (resultadoConsulta.next()) {
                    val idVendaRelacionada = resultadoConsulta.getInt("id")
                    idsVendasRelacionadas.add(idVendaRelacionada)
                }
                for (idVenda in idsVendasRelacionadas) {
                    val atualizarVendaSql = "UPDATE venda SET clienteid = NULL WHERE id = $idVenda"
                    val statementAtualizacao = connection.createStatement()
                    statementAtualizacao.executeUpdate(atualizarVendaSql)
                }
                val sql = "DELETE FROM cliente WHERE id_cliente = $id"
                val statement = connection.createStatement()
                statement.executeUpdate(sql)
                println("Cliente deletado com sucesso!")

            } catch (e: SQLException) {
                e.printStackTrace()
            }
        }
        fun clientesComEmailZup() {
            val sql = "SELECT id_cliente, nome, email FROM cliente WHERE email LIKE '%zup.com.br'"
            try {
                val statement = connection.createStatement()
                val resultSet = statement.executeQuery(sql)

                println("=====CLIENTES COM E-MAIL ZUP.COM=====\n")
                while (resultSet.next()) {
                    val idCliente = resultSet.getInt("id_cliente")
                    val nome = resultSet.getString("nome")
                    val email = resultSet.getString("email")
                    println("ID: $idCliente | Nome: $nome | Email: $email")
                }
                resultSet.close()
                statement.close()
            } catch (e: SQLException) {
                e.printStackTrace()
            }
        }
    }
}