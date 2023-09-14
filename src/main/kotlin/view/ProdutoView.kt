package view

import service.ClienteService
import service.ProdutoService

class ProdutoView {
    fun MenuProduto() {
        println("\n=====MENU DO PRODUTO=====\n")
        println("Digite uma das seguinte opções")
        println("1 - Adicinar Produto.")
        println("2 - Deletar Produto.")
        println("3 - Atualizar Produto.")
        println("4 - Listar Produto.")
        println("5 - Buscar Produto por ID")
        println("6 - Voltar ao menu principal.")
    }
    fun opcoesProduto() {
        var option = 0
        do {
            MenuProduto()
            if (option != 6) {
                MenuView()
            }
            when (option) {
                1 -> {
                    println("Digite o nome para inserir um novo produto: ")
                    val nomeProduto = readln()
                    println("Digite o preço do produto: ")
                    val precoProduto = readln()
                    ProdutoService.inserirProduto(nomeProduto,precoProduto)
                }

                2 -> {
                    println("Digite o ID para deletar um produto: ")
                    val idProduto = readln().toInt()
                    ClienteService.deletarCliente(idProduto)
                }

                3 -> {
                    println("Para atualizar, digite o ID do produto: ")
                    val idProduto = readln().toInt()
                    println("Para atualizar, digite o novo preço unitário do produto: ")
                    val preco_unitProduto = readln()
                    ProdutoService.alterarProduto(idProduto, preco_unitProduto)
                }
                4 -> ProdutoService.consultaTodosOsProdutos()
                5 -> {
                    println("Digite o ID do cliente que deseja listar: ")
                    val idCliente = readln().toInt()
                    ClienteService.consultarClientePorId(idCliente)
                }
                else -> {
                    println("Opção inválida, tente novamente!")
                }
            }
        } while (option != 0)
    }
}