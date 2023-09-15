package view

import service.VendedorService
class VendedorView {
    companion object {
        var opcao = 0
        fun MenuVendedor() {
            println("\n=====MENU DE VENDEDOR=====\n")
            println("Digite uma das seguinte opções")
            println("1 - Adicinar Vendedor.")
            println("2 - Deletar Vendedor.")
            println("3 - Atualizar Vendedor.")
            println("4 - Listar Vendedores.")
            println("5 - Buscar Vendedor por ID")
            println("6 - Voltar ao menu principal.")
            opcao = readln().toIntOrNull() ?: 0
        }
        fun opcoesVendedor() {
            do {
                MenuVendedor()
                when (opcao) {
                    1 -> {
                        println("Digite o nome do vendedor: ")
                        val nomeVendedor = readln()
                        println("Digite o email do vendedor: ")
                        val emailVendedor = readln()
                        println("Digite o cpf do vendedor: ")
                        val cpfVendedor = readln()
                        println("Digite o salário do vendedor: ")
                        val salarioVendedor = readln()
                        VendedorService.inserirVendedor(
                            nomeVendedor,
                            emailVendedor,
                            cpfVendedor,
                            salarioVendedor)
                    }

                    2 -> {
                        println("Digite o ID para deletar uma vendedor: ")
                        val idVendedor = readln().toInt()
                        VendedorService.deletarVendedor(idVendedor)
                    }

                    3 -> {
                        println("Digite o ID nome do vendedor que deseja atualizar: ")
                        val novoId = readln().toInt()
                        println("Digite o novo nome do vendedor: ")
                        val novoNomeVendedor = readln()
                        println("Digite o novo email do vendedor: ")
                        val novoEmailVendedor = readln()
                        println("Digite o novo cpf do vendedor: ")
                        val novoCpfVendedor = readln()
                        println("Digite o novo salário do vendedor: ")
                        val novoSalarioVendedor = readln()
                        VendedorService.atualizarVendedor(
                            novoId,
                            novoNomeVendedor,
                            novoEmailVendedor,
                            novoCpfVendedor,
                            novoSalarioVendedor
                        )
                    }
                    4 -> VendedorService.consultaTodosOsVendedor()
                    5 -> {
                        println("Digite o ID do vendedor que deseja listar: ")
                        val idVendedor = readln().toInt()
                        VendedorService.consultarVendedorPorId(idVendedor)
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