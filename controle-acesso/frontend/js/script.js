// Alternar entre as telas do sistema
function mostrarTela(nomeTela) {
    const telas = document.querySelectorAll(".tela");

    telas.forEach(tela => {
        tela.classList.remove("ativa");
    });

    document.getElementById(nomeTela).classList.add("ativa");
}

// Formulário de cadastro
document.getElementById("formCadastro")
    .addEventListener("submit", function(event) {

        event.preventDefault();

        const nome = document.getElementById("nome").value.trim();
        const senha = document.getElementById("senhaCadastro").value;

        if (!nome || !senha ) {
            return;
        }

        document.getElementById("mensagemCadastro").textContent =
            "Formulário preenchido! Aguardando integração com o back-end.";

        this.reset();
    });

// Formulário de acesso
document.getElementById("formAcesso")
    .addEventListener("submit", function(event) {

        event.preventDefault();

        document.getElementById("mensagemAcesso").textContent =
            "Interface funcionando! A verificação será feita pelo back-end.";

        this.reset();
    });