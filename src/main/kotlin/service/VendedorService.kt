package service

import connection.Conexao
import java.sql.SQLException

class VendedorService {

    private var connection = Conexao().fazerConexao()

    fun inserirVendedor(nome: String, email: String, cpf: String, salario: String) {
        var clientesAdicionados = false
        try {
            val sql = "INSERT INTO vendedor (nome, email, cpf, salario) " +
                    "VALUES ('$nome', '$email', '$cpf', '$salario')"

            val statement = connection.createStatement()
            statement.executeUpdate(sql)

            println("Vendedor $nome adicionado com sucesso!")

            if (!clientesAdicionados) {
                println("Não foi cadastrado nenhum vendedor, verifique se as informações estão corretas!")
            }
        } catch (e: SQLException) {
            e.printStackTrace()
        }
    }

    fun consultaTodosOsVendedor() {
        var vendedoresEncontrados = false
        try {
            val statement = connection.createStatement()
            val resultSet = statement.executeQuery("SELECT * FROM vendedor")

            while (resultSet.next()) {
                vendedoresEncontrados = true
                println(
                    "ID: " + resultSet.getInt("id_vendedor") +
                            " | NOME: " + resultSet.getString("nome") +
                            " | EMAIL: " + resultSet.getString("email") +
                            " | CPF: " + resultSet.getString("cpf") +
                            " | SALÁRIO : " + resultSet.getString("salario")
                )
            }
            if (!vendedoresEncontrados) {
                println("Ainda não foi cadastrado nenhum vendedor.")
            }
        } catch (e: SQLException) {
            e.printStackTrace()
        }
    }

    fun alterarVendedor(nome: String, email: String, cpf: String, salario: String) {
        try {
            val statement = connection.createStatement()
            val rowCount = statement.executeUpdate("\"UPDATE vendedor SET nome = '\" + nome + \"', email = '\" + email +\n" +
                    "                \"', cpf = '\" + cpf + \"', endereco = '\" + salario + \"' WHERE id = \"")

            if (rowCount > 0) {
                println("Vendedor com nome $nome foi alterado com sucesso.")
            } else {
                println("Vendedor com nome $nome não encontrado, verifique se as informações estão corretas! ")
            }
        } catch (e: SQLException) {
            e.printStackTrace()
        }
    }

    fun deletarVendedor(id: Int) {
        try {
            val statement = connection.createStatement()
            val rowCount = statement.executeUpdate("DELETE FROM vendedor WHERE id = $id")
            if (rowCount > 0) {
                println("Vendedor $id foi deletado com sucesso!")
            } else {
                println("Vendedor $id não encontrado!")
            }
        } catch (e: SQLException) {
            e.printStackTrace()
        }
    }

    fun consultarVendedorPorId(id: Int) {
        try {
            val statement = connection.createStatement()
            val resultSet = statement.executeQuery("SELECT * FROM vendedor")
            if (resultSet.next()) {
                println("=====VENDEDOR ENCONTRADO=====")
                println(
                    "ID: " + resultSet.getInt("id_vendedor") +
                            " | NOME: " + resultSet.getString("nome") +
                            " | EMAIL: " + resultSet.getString("email") +
                            " | CPF: " + resultSet.getString("cpf") +
                            " | SALÁRIO : " + resultSet.getString("salario")
                )
            } else {
                println("Vendedor com ID $id não encontrado, tente novamente")
            }
        } catch (e: SQLException) {
            e.printStackTrace()
        }
    }

}