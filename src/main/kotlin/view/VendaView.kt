package view

import service.VendaService

class VendaView {
    companion object {
        var opcao = 0
        fun MenuVenda() {
            println("\n=====MENU DE VENDAS=====\n")
            println("Digite uma das seguinte opções")
            println("1 - Adicinar Venda.")
            println("2 - Deletar Venda.")
            println("3 - Atualizar Venda.")
            println("4 - Listar Vendas.")
            println("5 - Buscar Venda por ID")
            println("6 - Itens que foram vendidos acima de 10,00")
            println("7 - Voltar ao menu principal.")
            opcao = readln().toIntOrNull() ?: 0
        }
        fun opcoesVenda() {
            do {
                MenuVenda()
                when (opcao) {
                    1 -> {
                        println("Digite o ID do cliente da sua venda: ")
                        val idCliente = readln().toInt()
                        println("Digite o ID do vendedor da sua venda: ")
                        val idVendedor = readln().toInt()
                        println("Digite o ID do produto da sua venda: ")
                        val idProduto = readln().toInt()
                        println("Digite o valor total da sua venda: ")
                        val valorTotal = readln().toInt()
                        VendaService.inserirVenda(idCliente, idVendedor, idProduto, valorTotal)
                    }
                    2 -> {
                        println("Digite o ID para deletar uma venda: ")
                        val idVenda = readln().toInt()
                        VendaService.deletarVenda(idVenda)
                    }
                    3 -> {
                        println("Para atualizar, digite o ID da venda: ")
                        val idVenda = readln().toInt()
                        println("Para atualizar, digite o novo valor total: ")
                        val valorTotalVenda = readln().toInt()
                        VendaService.atualizarVenda(idVenda, valorTotalVenda)
                    }
                    4 -> VendaService.consultaTodasAsVendas()
                    5 -> {
                        println("Digite o ID da venda que deseja listar: ")
                        val idVenda = readln().toInt()
                        VendaService.consultarVendaPorId(idVenda)
                    }
                    6 ->{

                    }
                    7 -> {
                        println("Voltando ao menu principal...")
                        MenuView.escolhaPrincipal()
                    }
                    else -> println("Opção inválida, tente novamente!")
                }
            }while (opcao != 6)
        }
    }
}