function pesquisarUser() {
			var nome = $("#nameBusca").val();

			if (nome != null && nome.trim() != ' ') {

				$
						.ajax(
								{
									method : "GET",
									url : "buscarPorNome",
									data : "name=" + nome,
									contentType : "application/json; charset=utf-8",
									success : function(response) {
										$('#tabelaresultados > tbody > tr')
												.remove();

										for (let i = 0; i < response.length; i++) {
											$('#tabelaresultados > tbody')
													.append(
															'<tr id='+response[i].id+'><td>'
																	+ response[i].id
																	+ '</td><td>'
																	+ response[i].nome
																	+ '</td><td><button type="button" class="btn btn-primary" onclick="colocarEmEdicao('
																	+ response[i].id
																	+ ')">Ver</button></td><td><button type="button" class="btn btn-danger" onclick="deleteUser('+response[i].id+')">Delete</button></td></tr>');
										}
									}
								}).fail(function(xhr, status, errorThrown) {
							alert("Erro ao Buscar: " + xhr.responseText);
						});
			}

		}
		
		function colocarEmEdicao(id) {
			$.ajax({
				method : "GET",
				url : "buscaruserid",
				data : "idUser=" + id,
				contentType : "application/json; charset=utf-8",
				success : function(response) {
					$("#id").val(response.id);
					$("#nome").val(response.nome);
					$("#idade").val(response.idade);

					$('#modalPesquisarUser').modal('hide');
				}
			}).fail(function(xhr, status, errorThrown) {
				alert("Erro ao buscar usuario por id: " + xhr.responseText);
			});
		}

		function salvarUsuario() {
			var id = $("#id").val();
			var nome = $("#nome").val();
			var idade = $("#idade").val();
			
			if(nome.trim() != ''){
				$.ajax({
					method : "POST",
					url : "salvar",
					data : JSON.stringify({
						nome : nome,
						idade : idade
					}),
					contentType : "application/json; charset=utf-8",
					success : function(response) {

						$("#id").val(response.id);
						alert("Salvo com Sucesso!");
					}
				}).fail(function(xhr, status, errorThrown) {
					alert("Erro ao Salvar: " + xhr.responseText);
				});				
			}
			else{
				alert("Campo nome está vazio")
			}

		}
		
		function deleteUser(id){
			if(confirm('Deseja realmente deletar?')){
				$.ajax({
					method : "DELETE",
					url : "delete",
					data : "idUser= "+id,
					success : function(response) {
						$('#'+id).remove()
						alert(response)
					}
				}).fail(function(xhr, status, errorThrown) {
					alert("Erro ao deletar por id: " + xhr.responseText);
				});			
			}
	
		}
		
		function botaoDeletarDaTela(){
			var id = $("#id").val();
			
			if(id.trim() != ''){
				deleteUser(id);
				document.getElementById('formCadastroUser').reset()
			}
			
		}