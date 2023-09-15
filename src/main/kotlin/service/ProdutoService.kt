package service

import connection.Conexao
import java.sql.SQLException

class ProdutoService {
    companion object {
        private var connection = Conexao().fazerConexao()
        fun inserirProduto(nome: String, preco_unit: String) {
            try {
                val sql = "INSERT INTO produto (nome_produto, preco_unit) " +
                        "VALUES ('$nome', '$preco_unit')"
                val statement = connection.createStatement()
                statement.executeUpdate(sql)
                println("Produto $nome adicionado com sucesso!")

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
                                " | NOME: " + resultSet.getString("nome_produto") +
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
        fun alterarProduto(id: Int, preco_unit: String) {
            try {
                val statement = connection.createStatement()
                val rowCount = statement.executeUpdate(
                    "UPDATE produto SET preco_unit = $preco_unit WHERE id_produto = $id")

                if (rowCount > 0) {
                    println("Produto com o ID $id foi alterado com sucesso.")
                } else {
                    println("Produto com o ID $id não encontrado, verifique se as informações estão corretas! ")
                }
            } catch (e: SQLException) {
                e.printStackTrace()
            }
        }
        fun deletarProduto(id: Int) {
            val statement = connection.createStatement()
            val rowCount = statement.executeUpdate("DELETE FROM produto WHERE id_produto = $id")

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
                val resultSet = statement.executeQuery("SELECT * FROM produto WHERE id_produto = $id")

                if (resultSet.next()) {
                    println("=====PRODUTO ENCONTRADO=====")
                    println(
                        "ID: " + resultSet.getInt("id_produto") +
                                " | NOME: " + resultSet.getString("nome_produto") +
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
}