import org.junit.jupiter.api.Test
import java.sql.Connection
import java.sql.ResultSet
import java.sql.Statement
import org.junit.jupiter.api.BeforeAll
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mockito.*
import kotlin.test.assertEquals
import service.VendedorService

class VendedorServiceTest {
    private lateinit var connection: Connection

    @BeforeAll
    fun setUp() {
        connection = mock(Connection::class.java)
        VendedorService.connection = connection
    }
    @Test
    fun testInserirVendedor() {
        val statement = mock(Statement::class.java)
        `when`(connection.createStatement()).thenReturn(statement)
        `when`(statement.executeUpdate(anyString())).thenReturn(1)

        VendedorService.inserirVendedor("John Doe", "john@example.com", "12345678901", "5000.00")

        val expectedSql = "INSERT INTO vendedor (nome, email, cpf, salario) " +
                "VALUES ('John Doe', 'john@example.com', '12345678901', '5000.00')"
        verify(statement).executeUpdate(expectedSql)

        val argumentCaptor = argumentCaptor<String>()
        verify(statement).executeUpdate(argumentCaptor.capture())
        assertEquals("Vendedor John Doe adicionado com sucesso!", argumentCaptor.firstValue)
    }

    @Test
    fun testConsultaTodosOsVendedores() {
        val statement = mock(Statement::class.java)
        val resultSet = mock(ResultSet::class.java)
        `when`(connection.createStatement()).thenReturn(statement)
        `when`(statement.executeQuery(anyString())).thenReturn(resultSet)

        `when`(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false)
        `when`(resultSet.getInt("id_vendedor")).thenReturn(1).thenReturn(2)
        `when`(resultSet.getString("nome")).thenReturn("Vendedor A").thenReturn("Vendedor B")
        `when`(resultSet.getString("email")).thenReturn("a@example.com").thenReturn("b@example.com")
        `when`(resultSet.getString("cpf")).thenReturn("12345678901").thenReturn("98765432101")
        `when`(resultSet.getString("salario")).thenReturn("5000.00").thenReturn("6000.00")

        val vendedores = VendedorService.consultaTodosOsVendedores()

        assertEquals(2, vendedores.size)
    }
}
