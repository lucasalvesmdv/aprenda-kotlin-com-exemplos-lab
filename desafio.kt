enum class Nivel { BASICO, INTERMEDIARIO, DIFICIL }

data class Usuario(val nome: String, val cpf: String)

data class ConteudoEducacional(val nome: String, val duracao: Int = 60)

data class Formacao(val nome: String, val nivel: Nivel, val conteudos: List<ConteudoEducacional>) {

    val inscritos = mutableSetOf<Usuario>()

    fun matricular(usuario: Usuario) {
        when {
            inscritos.contains(usuario) -> println("Usuário ${usuario.nome} já está matriculado nesta formação.")
            else -> {
                inscritos.add(usuario)
                imprimirMensagemMatricula(usuario)
            }
        }
    }

    fun cancelarMatricula(usuario: Usuario) {
        when {
            inscritos.contains(usuario) -> {
                inscritos.remove(usuario)
                imprimirMensagemCancelamentoMatricula(usuario)
            }
            else -> println("Usuário ${usuario.nome} não está matriculado.")
        }
    }

    private fun imprimirMensagemMatricula(usuario: Usuario) {
        println("Usuário ${usuario.nome} matriculado na formação $nome.")
    }

    private fun imprimirMensagemCancelamentoMatricula(usuario: Usuario) {
        println("Usuário ${usuario.nome} cancelou com sucesso a sua matrícula, na formação $nome.")
    }
}

fun main() {
    val usuario1 = Usuario("João", "12345")
    val usuario2 = Usuario("Maria", "125487")

    val conteudo1 = ConteudoEducacional("Introdução ao Kotlin", 90)
    val conteudo2 = ConteudoEducacional("Programação Orientada a Objetos", 120)

    val formacao = Formacao("Programação Orientada a Objetos", Nivel.INTERMEDIARIO, listOf(conteudo1, conteudo2))

    formacao.matricular(usuario1)
    formacao.matricular(usuario2)
    formacao.matricular(usuario2)  // Tentando matricular o mesmo usuário novamente
    formacao.cancelarMatricula(usuario1)
    println(formacao.inscritos)
}
