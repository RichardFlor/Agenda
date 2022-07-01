/**
 * Confirmação de exclusao de um contato
 */

function confirmar(idContato) {
	let resposta = confirm("Confirma a exclusão deste contato?")

	if (resposta === true) {
		//alert(idContato)
		window.location.href = "delete?idContato=" + idContato
	}
}