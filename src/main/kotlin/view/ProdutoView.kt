package view

import service.ProdutoService

class ProdutoView {
    companion object {
        var opcao = 0
        fun MenuProduto() {
            println("\n=====MENU DO PRODUTO=====\n")
            println("Digite uma das seguinte opções")
            println("1 - Adicinar Produto.")
            println("2 - Deletar Produto.")
            println("3 - Atualizar Produto.")
            println("4 - Listar Produtos.")
            println("5 - Buscar Produto por ID")
            println("6 - Voltar ao menu principal.")
            opcao = readln().toIntOrNull() ?: 0
        }
        fun opcoesProduto() {
            do {
                MenuProduto()
                when (opcao) {
                    1 -> {
                        println("Digite o nome do produto: ")
                        val nomeProduto = readln()
                        println("Digite o preço do produto: ")
                        val precoProduto = readln()
                        ProdutoService.inserirProduto(nomeProduto, precoProduto)
                    }

                    2 -> {
                        println("Digite o ID para deletar um produto: ")
                        val idProduto = readln().toInt()
                        ProdutoService.deletarProduto(idProduto)
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
                        println("Digite o ID do produto que deseja listar: ")
                        val idProduto = readln().toInt()
                        ProdutoService.consultarProdutoPorId(idProduto)
                    }
                    6 -> {
                        println("Voltando ao menu principal...")
                        MenuView.escolhaPrincipal()
                    }
                    else -> println("Opção inválida, tente novamente!")
                }
            }while (opcao != 6)
        }
    }
}