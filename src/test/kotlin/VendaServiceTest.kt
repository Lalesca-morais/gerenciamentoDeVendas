import org.junit.jupiter.api.Test
import org.mockito.Mockito.*
import service.VendaService
import java.sql.Connection
import java.sql.ResultSet
import java.sql.Statement
import kotlin.test.assertEquals

class VendaServiceTest {

    @Test
    fun testConsultaTodasAsVendas() {
        val connection = mock(Connection::class.java)
        val statement = mock(Statement::class.java)
        val resultSet = mock(ResultSet::class.java)

        `when`(connection.createStatement()).thenReturn(statement)
        `when`(statement.executeQuery(anyString())).thenReturn(resultSet)

        `when`(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false)
        `when`(resultSet.getInt("id")).thenReturn(1).thenReturn(2)
        `when`(resultSet.getInt("clienteid")).thenReturn(1).thenReturn(2)
        `when`(resultSet.getInt("vendedorid")).thenReturn(3).thenReturn(4)
        `when`(resultSet.getInt("produtoid")).thenReturn(5).thenReturn(6)
        `when`(resultSet.getDouble("valortotal")).thenReturn(10.0).thenReturn(15.0)

        val vendas = VendaService.consultaTodasAsVendas()
        assertEquals(2, vendas.size)
    }
}
