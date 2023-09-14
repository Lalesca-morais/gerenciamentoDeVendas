package service

import connection.Conexao
import java.sql.SQLException

class VendaService {
    private var connection = Conexao().fazerConexao()
    fun inserirVenda(clienteid: Int, vendedorid: Int, produtoid: Int, valortotal: Int) {
        var vendasAdicionadas = false

        try {
            val sql = """
            INSERT INTO venda (clienteid, vendedorid, produtoid, qtd_produto, preco_total)
            SELECT $clienteid, $vendedorid, $produtoid, $valortotal, Produto.preco_unit * $valortotal
            FROM Produto
            WHERE Produto.id_produto = $produtoid
        """
            val statement = connection.createStatement()
            statement.executeUpdate(sql)
            println("Venda adicionada com sucesso!")

            if (!vendasAdicionadas) {
                println("Não foi cadastrada nenhuma venda, verifique se as informações estão corretas!")
            }
        } catch (e: SQLException) {
            e.printStackTrace()
        }
    }
    fun consultaTodasAsVendas() {
        var produtosEncontrados = false
        try {
            val statement = connection.createStatement()
            val resultSet = statement.executeQuery("SELECT * FROM venda")
            while (resultSet.next()) {
                produtosEncontrados = true
                println(
                    "ID: " + resultSet.getInt("id") +
                            " | ID DO CLIENTE: " + resultSet.getString("clienteid") +
                            " | ID DO VENDEDOR: " + resultSet.getString("vendedorid") +
                            " | ID DO PRODUTO: " + resultSet.getString("produtoid") +
                            " | VALOR TOTAL: " + resultSet.getString("valortotal")
                )
            }
            if (!produtosEncontrados) {
                println("Ainda não foi cadastrado nenhuma venda.")
            }
        } catch (e: SQLException) {
            e.printStackTrace()
        }
    }
    fun atualizarVenda(id_venda: Int, qtd_produto: Int) {
        try {
            val verificaVenda = "SELECT COUNT(*) AS total FROM venda WHERE id_venda = $id_venda"
            val statementVerifica = connection.createStatement()
            val resultSetVerifica = statementVerifica.executeQuery(verificaVenda)
            resultSetVerifica.next()
            val totalVendas = resultSetVerifica.getInt("total")
            resultSetVerifica.close()
            statementVerifica.close()

            if (totalVendas == 0) {
                println("Venda com ID $id_venda não encontrada.")
                return
            }
            val sql = "UPDATE venda SET qtd_produto = $qtd_produto, preco_total = qtd_produto * Produto.preco_unit " +
                    "FROM Produto WHERE venda.id_venda = $id_venda AND Produto.id_produto = venda.id_produto"

            val statement = connection.createStatement()
            statement.executeUpdate(sql)
            println("Quantidade do produto na venda com ID $id_venda atualizada com sucesso!")
        } catch (e: SQLException) {
            e.printStackTrace()
        }
    }
    fun deletarVenda(id: Int) {
        val statement = connection.createStatement()
        val rowCount = statement.executeUpdate("DELETE FROM venda WHERE id = $id")

        try {
            if (rowCount > 0) {
                println("Venda $id foi deletada com sucesso!")
            } else {
                println("Venda $id não encontrada!")
            }
        } catch (e: SQLException) {
            e.printStackTrace()
        }
    }
    fun consultarVendaPorId(id: Int) {
        try {
            val statement = connection.createStatement()
            val resultSet = statement.executeQuery("SELECT * FROM venda")

            if (resultSet.next()) {
                println("=====VENDA ENCONTRADA=====")
                println(
                    "ID: " + resultSet.getInt("id") +
                            " | ID DO CLIENTE: " + resultSet.getString("clienteid") +
                            " | ID DO VENDEDOR: " + resultSet.getString("vendedorid") +
                            " | ID DO PRODUTO: " + resultSet.getString("produtoid") +
                            " | VALOR TOTAL: " + resultSet.getString("valortotal")
                )
            } else {
                println("Produto com ID $id não encontrado, tente novamente")
            }
        } catch (e: SQLException) {
            e.printStackTrace()
        }
    }
}