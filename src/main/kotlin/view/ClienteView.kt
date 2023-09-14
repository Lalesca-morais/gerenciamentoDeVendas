package view

import service.ClienteService

class ClienteView {


    fun MenuAluno() {
        println("\n=====MENU DO CLIENTE=====\n")
        println("Digite uma das seguinte opções")
        println("1 - Adicinar Cliente.")
        println("2 - Deletar Cliente.")
        println("3 - Atualizar Cliente.")
        println("4 - Listar Clientes.")
        println("5 - Buscar Cliente por ID")
        println("6 - Voltar ao menu principal.")
    }

    fun opcoesAluno() {
        var option = 0
        do {
            if (option != 6) {
                MenuView()
            }
            when (option) {
                1 -> {
                    println("Digite seu nome para inserir um cliente: ")
                    val nomeCliente = readln()
                    println("Digite seu e-mail: ")
                    val emailCliente = readln()
                    println("Digite seu CPF: ")
                    val cpfCliente = readln()
                    println("Digite seu endereço: ")
                    val enderecoCliente = readln()
                    ClienteService.inserirCliente(nomeCliente, emailCliente,cpfCliente,enderecoCliente)
                }

                2 -> {
                    println("Digite o ID para deletar um cliente: ")
                    val idCliente = readln().toInt()
                    ClienteService.deletarCliente(idCliente)
                }

                3 -> {
                    println("Digite o ID para deletar um cliente: ")
                    val idCliente = readln().toInt()
                }

                4 -> {
                    alunoService.listarAlunos()
                }

                5 -> {
                    MenuView().MenuPrincipal()
                }

                else -> {
                    println("Opção inválida, tente novamente!")
                }
            }
        } while (option != 0)
    }
}