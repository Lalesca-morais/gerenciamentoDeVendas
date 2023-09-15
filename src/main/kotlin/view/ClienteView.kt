package view

import service.ClienteService

class ClienteView {
    companion object {
        var opcao = 0
        fun MenuCliente() {
            println("\n=====MENU DO CLIENTE=====\n")
            println("Digite uma das seguinte opções")
            println("1 - Adicinar Cliente.")
            println("2 - Deletar Cliente.")
            println("3 - Atualizar Cliente.")
            println("4 - Listar Clientes.")
            println("5 - Buscar Cliente por ID")
            println("6 - Buscar cliente com o email zup.com.br")
            println("7 - Voltar ao menu principal.")
            opcao = readln().toIntOrNull() ?: 0
        }
        fun opcoesCliente() {
            do {
                MenuCliente()
                when (opcao) {
                    1 -> {
                        println("Para inserir um cliente, digite o nome: ")
                        val nomeCliente = readln()
                        println("Digite o e-mail: ")
                        val emailCliente = readln()
                        println("Digite o CPF: ")
                        val cpfCliente = readln()
                        println("Digite o endereço: ")
                        val enderecoCliente = readln()
                        ClienteService.inserirCliente(nomeCliente, emailCliente, cpfCliente, enderecoCliente)
                    }
                    2 -> {
                        println("Digite o ID para deletar um cliente: ")
                        val idCliente = readln().toInt()
                        ClienteService.deletarCliente(idCliente)
                    }
                    3 -> {
                        println("Para atualizar, digite o ID do cliente que deseja atualizar: ")
                        val idCliente = readln().toInt()
                        println("Digite o novo e-mail: ")
                        val emailCliente = readln()
                        println("Digite o novo endereço: ")
                        val enderecoCliente = readln()
                        ClienteService.atualizarCliente(idCliente, emailCliente, enderecoCliente)
                    }
                    4 -> ClienteService.consultaTodosOsCliente()
                    5 -> {
                        println("Digite o ID do cliente que deseja listar: ")
                        val idCliente = readln().toInt()
                        ClienteService.consultarClientePorId(idCliente)
                    }
                    6 -> ClienteService.clientesComEmailZup()
                    7 -> {
                        println("Voltando ao menu principal...")
                        MenuView.escolhaPrincipal()
                    }
                    else -> println("Opção inválida, tente novamente!")
                }
            }while (opcao != 7)
        }
    }
}
