import jdk.nashorn.internal.ir.Statement
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.mockito.Mockito.mock
import service.ClienteService
import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import kotlin.test.Test
private lateinit var mockConnection: Connection
private lateinit var mockStatement: Statement
private lateinit var mockPreparedStatement: PreparedStatement
private lateinit var mockResultSet: ResultSet

class ClienteServiceTest {
    companion object {

        lateinit var connection: Connection
        val clienteService = ClienteService()

        @BeforeEach
        fun setUp() {
            mockConnection = mock(Connection::class.java)
            mockStatement = mock(Statement::class.java)
            mockPreparedStatement = mock(PreparedStatement::class.java)
            mockResultSet = mock(ResultSet::class.java)
        }

        @Test
        fun testInserirCliente() {
            ClienteService.inserirCliente("John Doe", "john@example.com", "12345678901", "123 Main St")
            val resultSet = connection.createStatement().executeQuery("SELECT * FROM cliente")

            assertEquals("John Doe", resultSet.getString("nome"))
            assertEquals("john@example.com", resultSet.getString("email"))
            assertEquals("12345678901", resultSet.getString("cpf"))
            assertEquals("123 Main St", resultSet.getString("endereco"))
        }

        @Test
        fun testAtualizarCliente() {
            ClienteService.inserirCliente("John Doe", "john@example.com", "12345678901", "123 Main St")
            ClienteService.atualizarCliente(1, "newemail@example.com", "New Address")

            val resultSet = connection.createStatement().executeQuery("SELECT * FROM cliente WHERE id_cliente = 1")
            assertTrue(resultSet.next())
            assertEquals("newemail@example.com", resultSet.getString("email"))
            assertEquals("New Address", resultSet.getString("endereco"))
        }

        @Test
        fun testDeletarCliente() {
            ClienteService.inserirCliente("John Doe", "john@example.com", "12345678901", "123 Main St")
            ClienteService.deletarCliente(1)

            val resultSet = connection.createStatement().executeQuery("SELECT * FROM cliente WHERE id_cliente = 1")
            assertFalse(resultSet.next())
        }
    }
}