const formLogin = document.getElementById("form-Login")


formLogin.addEventListener("submit", (evento) => {

    evento.preventDefault()

    const data = {
        "username": document.getElementById("usuario").value,
        "senha": document.getElementById("senha").value
    }

    console.log(data)


    fetch("http://localhost:8090/login", {

        method: "POST",
        headers: {
            'Content-Type': 'application/json'
        },

        body: JSON.stringify(data)

    }).then(response => {
        if (response.status == 200 /*OK*/) {
            alert("Entrada com sucesso!")
        } else if (response.status == 401 /*Unauthorized*/) {
            alert("Credenciais inválidas!")
        } else if (response.status == 404 /*Not Found*/) {
            alert("Usuário não encontrado!")
        } else {
            console.log(response)
            alert("Ocorreu uma falha")
        }
    }).catch(e => {
        alert("Erro:", e.toString())
    })


})