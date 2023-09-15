import jdk.nashorn.internal.ir.Statement
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import service.ProdutoService
import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class ProdutoServiceTest {
    companion object {
        lateinit var connection: Connection

        @BeforeEach
        fun setUp() {
            connection = DriverManager.getConnection("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1")

            val createTableSql = """
                CREATE TABLE IF NOT EXISTS produto (
                    id_produto INT AUTO_INCREMENT PRIMARY KEY,
                    nome_produto VARCHAR(255),
                    preco_unit VARCHAR(10)
                )
            """
            connection.createStatement().executeUpdate(createTableSql)
        }
    }

    @Test
    fun testInserirProduto() {
        ProdutoService.inserirProduto("Produto A", "10.50")
        ProdutoService.inserirProduto("Produto B", "15.75")

        val resultSet = connection.createStatement().executeQuery("SELECT * FROM produto")
        var produtosEncontrados = 0
        while (resultSet.next()) {
            produtosEncontrados++
        }
        assertEquals(2, produtosEncontrados)
    }

    @Test
    fun testAlterarProduto() {
        ProdutoService.inserirProduto("Produto A", "10.50")
        ProdutoService.alterarProduto(1, "12.00")

        val resultSet = connection.createStatement().executeQuery("SELECT * FROM produto WHERE id_produto = 1")
        assertTrue(resultSet.next())
        assertEquals("12.00", resultSet.getString("preco_unit"))
    }

    @Test
    fun testDeletarProduto() {
        ProdutoService.inserirProduto("Produto A", "10.50")
        ProdutoService.deletarProduto(1)

        val resultSet = connection.createStatement().executeQuery("SELECT * FROM produto WHERE id_produto = 1")
        assertFalse(resultSet.next())
    }

}
