enum class Nivel { BASICO, INTERMEDIARIO, AVANCADO }

class Usuario(val nome: String)

data class ConteudoEducacional(val nome: String, val duracao: Int)

data class Formacao(
    val nome: String,
    var conteudos: List<ConteudoEducacional>,
    val nivel: Nivel
) {
    private val _inscritos = mutableListOf<Usuario>()
    val inscritos: List<Usuario> = _inscritos

    fun matricular(vararg usuarios: Usuario) {
        usuarios.forEach {
            if (it !in _inscritos) {
                _inscritos += it
                println("${it.nome} matriculado em $nome!")
            } else {
                println("${it.nome} já está matriculado.")
            }
        }
    }

    fun totalHoras() = conteudos.sumOf { it.duracao }
}

fun main() {
    val kubernets = ConteudoEducacional("Kubernetes", 40)
    val aws = ConteudoEducacional("AWS", 45)
    val docker = ConteudoEducacional("Docker", 32)

    val formacaoDevOps = Formacao(
        nome = "DevOps",
        conteudos = listOf(kubernets, aws, docker),
        nivel = Nivel.INTERMEDIARIO
    )

    val user1 = Usuario("Allysson")
    formacaoDevOps.matricular(user1)

    val user2 = Usuario("Leila")
    formacaoDevOps.matricular(user1, user2)

    println("\n=== Inscritos na formação ${formacaoDevOps.nome} ===")
    formacaoDevOps.inscritos.forEach { println("- ${it.nome}") }

    println("\nDuração total: ${formacaoDevOps.totalHoras()} minutos")
}
