package service

import connection.Conexao
import java.sql.SQLException

class ProdutoService {

    private var connection = Conexao().fazerConexao()

    fun inserirProduto(nome: String, preco_unit: String) {
        var clientesAdicionados = false
        try {
            val sql = "INSERT INTO produto (nome, preco_unit) " +
                    "VALUES ('$nome', '$preco_unit')"
            val statement = connection.createStatement()
            statement.executeUpdate(sql)
            println("Produto $nome adicionado com sucesso!")

            if (!clientesAdicionados) {
                println("Não foi cadastrado nenhum produto, verifique se as informações estão corretas!")
            }
        } catch (e: SQLException) {
            e.printStackTrace()
        }
    }

    fun consultaTodosOsProdutos() {
        var produtosEncontrados = false
        try {
            val statement = connection.createStatement()
            val resultSet = statement.executeQuery("SELECT * FROM produto")
            while (resultSet.next()) {
                produtosEncontrados = true
                println(
                    "ID: " + resultSet.getInt("id_produto") +
                            " | NOME: " + resultSet.getString("nome") +
                            " | PREÇO UNITÁRIO: " + resultSet.getString("preco_unit")
                )
            }
            if (!produtosEncontrados) {
                println("Ainda não foi cadastrado nenhum produto.")
            }
        } catch (e: SQLException) {
            e.printStackTrace()
        }
    }

    fun alterarProduto(nome: String, email: String, cpf: String, endereco: String) {
        try {
            val statement = connection.createStatement()
            val rowCount = statement.executeUpdate("UPDATE produto SET nome = ?, preco_unit = ? WHERE id = ?")

            if (rowCount > 0) {
                println("Produto com nome $nome foi alterado com sucesso.")
            } else {
                println("Produto com nome $nome não encontrado, verifique se as informações estão corretas! ")
            }
        } catch (e: SQLException) {
            e.printStackTrace()
        }
    }

    fun deletarProduto(id: Int) {
        val statement = connection.createStatement()
        val rowCount = statement.executeUpdate("DELETE FROM cliente WHERE id = $id")

        try {
            if (rowCount > 0) {
                println("Produto $id foi deletado com sucesso!")
            } else {
                println("Produto $id não encontrado!")
            }
        } catch (e: SQLException) {
            e.printStackTrace()
        }
    }

    fun consultarProdutoPorId(id: Int) {
        try {
            val statement = connection.createStatement()
            val resultSet = statement.executeQuery("SELECT * FROM produto")

            if (resultSet.next()) {
                println("=====PRODUTO ENCONTRADO=====")
                println(
                    "ID: " + resultSet.getInt("id_produto") +
                            " | NOME: " + resultSet.getString("nome") +
                            " | PREÇO UNITÁRIO: " + resultSet.getString("preco_unit")
                )
            } else {
                println("Produto com ID $id não encontrado, tente novamente")
            }
        } catch (e: SQLException) {
            e.printStackTrace()
        }
    }
}