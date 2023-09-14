package service

import java.sql.SQLException

class VendaService {

    fun adicionarVenda(id_cliente: Int, id_vendedor: Int, id_produto: Int, qtd_produto: Int) {
        try {
            val sql = """
            INSERT INTO venda (id_cliente, id_vendedor, id_produto, qtd_produto, preco_total)
            SELECT $id_cliente, $id_vendedor, $id_produto, $qtd_produto, Produto.preco_unit * $qtd_produto
            FROM Produto
            WHERE Produto.id_produto = $id_produto
        """
            val statement = connection.createStatement()
            statement.executeUpdate(sql)
            println("Venda adicionada com sucesso!")
        } catch (e: SQLException) {
            e.printStackTrace()
        }
    }

}