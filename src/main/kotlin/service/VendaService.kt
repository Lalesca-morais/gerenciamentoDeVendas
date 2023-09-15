package service

import connection.Conexao
import java.sql.SQLException

class VendaService {
    companion object {
        private var connection = Conexao().fazerConexao()
        fun adicionarColunaQuantidade() {
            try {
                val sql = """
            ALTER TABLE venda
            ADD quantidade INT;
        """
                val statement = connection.createStatement()
                statement.executeUpdate(sql)
                println("Coluna 'quantidade' adicionada com sucesso à tabela 'venda'!")
            } catch (e: SQLException) {
                e.printStackTrace()
            }
        }
        fun inserirVenda(clienteid: Int?, vendedorid: Int?, produtoid: Int?, valortotal: Double?, quantidade: Int?) {
            try {
                val verificaColuna = """
            SELECT EXISTS (
                SELECT 1
                FROM information_schema.columns
                WHERE table_name = 'venda' AND column_name = 'quantidade'
            );
        """
                val statementVerificaColuna = connection.createStatement()
                val resultSet = statementVerificaColuna.executeQuery(verificaColuna)
                resultSet.next()
                val colunaQuantidadeExiste = resultSet.getBoolean(1)
                if (!colunaQuantidadeExiste) {
                    adicionarColunaQuantidade()
                }
                val sql = """
            INSERT INTO venda (clienteid, vendedorid, produtoid, quantidade, valortotal)
            SELECT $clienteid, $vendedorid, $produtoid, $quantidade, Produto.preco_unit * $quantidade
            FROM Produto
            WHERE Produto.id_produto = $produtoid
        """
                val statement = connection.createStatement()
                statement.executeUpdate(sql)
                println("Venda adicionada com sucesso!")

            } catch (e: SQLException) {
                e.printStackTrace()
            }
        }
        fun consultaTodasAsVendas() {
            var produtosEncontrados = false
            try {
                val statement = connection.createStatement()
                val resultSet = statement.executeQuery("SELECT * FROM venda")
                println("======VENDAS CADASTRADAS======")
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
                val totalVendas = resultSetVerifica.getInt("valortotal")
                resultSetVerifica.close()
                statementVerifica.close()

                if (totalVendas == 0) {
                    println("Venda com ID $id_venda não encontrada.")
                    return
                }
                val sql =
                    "UPDATE venda SET qtd_produto = $qtd_produto, preco_total = qtd_produto * Produto.preco_unit " +
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
        fun listarItensVendidosAcimaDe10() {
            val sql = """
        SELECT Venda.id AS venda, Venda.valortotal AS preco
        FROM Venda
        WHERE valortotal > 10.00
    """
            try {
                val statement = connection.createStatement()
                val resultSet = statement.executeQuery(sql)
                println("=====VENDAS ACIMA DE R$ 10,00=====")
                while (resultSet.next()) {
                    val id = resultSet.getInt("venda")
                    val valortotal = resultSet.getDouble("preco")
                    println("Venda: $id | Preço: $valortotal")
                }
                resultSet.close()
                statement.close()
            } catch (e: SQLException) {
                e.printStackTrace()
            }
        }
    }
}