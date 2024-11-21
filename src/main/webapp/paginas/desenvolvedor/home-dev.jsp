<%@page import="modelo.enumeracao.status.Status"%>
<%@page import="modelo.entidade.tarefa.Tarefa"%>
<%@page import="modelo.dao.tarefa.TarefaDAOImpl"%>
<%@page import="modelo.dao.tipotarefa.TipoTarefaDAOImpl"%>
<%@page import="modelo.entidade.tipotarefa.TipoTarefa"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/remixicon/3.5.0/remixicon.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/estilos/home.css">
<title>MindFlow</title>
</head>

<body>

	<nav>
		<div class="nav-logo">
			<a
				href="<%=request.getContextPath()%>/paginas/desenvolvedor/home-dev.jsp">
				<img
				src="<%=request.getContextPath()%>/resources/imagens/home/logo.png">
			</a>
		</div>

		<ul class="nav-links">
			<li id="link2" class="link"><a href="#">Descrição</a></li>
			<li id="link1" class="link"><a href="#">Tarefas</a></li>
		</ul>

	</nav>

	<header class="container">
		<div class="content">
			<span class="blur"></span> <span class="blur"></span>
			<h4>Bem Vindo</h4>
			<H1>
				<span>MindFlow</span>
			</H1>
			<p>A MindFlow é uma plataforma online de gerenciamento
				de tarefas que ajuda indivíduos e equipes a organizar, priorizar e
				acompanhar suas atividades de forma prática e eficiente. Com uma
				interface intuitiva e recursos como listas personalizáveis,
				notificações em tempo real e relatórios de progresso, conectamos
				produtividade e simplicidade em um só lugar. Nossa missão é
				transformar sua rotina, permitindo que você alcance seus objetivoss
				com mais foco e organização.</p>
		</div>
		<div class="image">
			<img
				src="<%=request.getContextPath()%>/resources/imagens/home/header.png">
		</div>
	</header>

	<section class="container">

		<h2 class="header">Tarefa(s)</h2>
		<button class ="button" type="button" onclick="openCriarTarefa()">Criar Tarefa</button>
			
			

		<div class="features">
			<%
        		TipoTarefaDAOImpl tipoTarefaDAO = new TipoTarefaDAOImpl();
        		TarefaDAOImpl tarefaDAO = new TarefaDAOImpl();
        		for(TipoTarefa tipoTarefa : tipoTarefaDAO.recuperarTipoTarefas()){
        			
        			Tarefa tarefa = tarefaDAO.recuperarTarefaPorIdUsaurio(tipoTarefa.getTarefa().getIdTarefa());
        	%>
				<div class="card" onclick="openEditarTarefa(<%= tipoTarefa.getIdTipoTarefa() %>)">
					<% 
        	                    	if(tarefa.getStatus() == Status.valueOf("PENDENTE")){
        	        %>
					<span style="background-color: red;"><i
						class="ri-close-circle-line"></i></span>
					<%
        	                    	}
        	        %>
					<% 
        	                    	if(tarefa.getStatus() == Status.valueOf("CONCLUIDO")){
        	        %>
					<span style="background-color: green;"><i
						class="ri-checkbox-circle-line"></i></span>
					<%
        	                    	}
        	        %>
					<% 
        	                    	if(tarefa.getStatus() == Status.valueOf("EM_ANDAMENTO")){
        	        %>
					<span style="background-color: rgb(206, 171, 18);"><i
						class="ri-history-line"></i></span>
					<%
        	                    	}
        	        %>
					<h4><%=tarefa.getNomeTarefa() %></h4>
					<p>
						<%=tarefa.getDescricao() %>
					</p>
					<form action="deletar-tarefa">
						<button type="submit">
							<input type="hidden" value="<%=tipoTarefa.getIdTipoTarefa()%>" name="idTipoTarefa"></input>
        	                <input type="hidden" value="<%=tarefa.getIdTarefa()%>" name="idTarefa"></input>
							<a href="#">Aperta na seta|DELETAR <i class="ri-arrow-right-line"></i></a>
						</button>
					</form>
				</div>
			<%
        		}
        	%>
		</div>

	</section>
	<div id="modal" class="modal-overlay" onclick="closeModal(event)">
		<div class="modal-content" onclick="event.stopPropagation()">
			<div id="modal-body"></div>
		</div>
	</div>
	<script src="<%=request.getContextPath()%>/resources/scripts/home.js"></script>
</body>

</html>