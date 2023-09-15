package view

class MenuView {
    companion object {
        var opcao = 0
        fun MenuPrincipal() {
            println("\nBem vindo ao Gerenciamento de Vendas!\n")
            println("1 - Área para Cliente")
            println("2 - Área para Vendedor")
            println("3 - Área para Venda")
            println("4 - Área para Produto")
            println("5 - Sair.")
            println("Digite a opção desejada!")
            opcao = readln().toIntOrNull() ?: 0
        }
        fun escolhaPrincipal() {
            MenuPrincipal()
            when (opcao) {
                1 -> ClienteView.opcoesCliente()
                2 -> VendedorView.opcoesVendedor()
                3 -> VendaView.opcoesVenda()
                4 -> ProdutoView.opcoesProduto()
                5 -> println("Saindo do sistema...")
                else -> println("Opção inválida, tente novamente!")
            }
        }
    }
}
