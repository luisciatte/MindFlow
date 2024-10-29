<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="../css/header.css">
    <title>MindFlow-Header </title>
</head>
<body>
    <header class="cabecalho">
        <div class="container-cabecalho">
            <div class="logo">
                <a href="#" class="texto-logo">Minha Marca</a>
            </div>
            <nav class="menu-navegacao">
                <ul>
                    <li><a href="#">Início</a></li>
                    <li><a href="#">Serviços</a></li>
                    <li><a href="#">Sobre</a></li>
                    <li><a href="#">Contato</a></li>
                </ul>
            </nav>
            <div class="botoes">
                <a href="login.jsp" class="botao-primario">Entrar</a>
                <a href="cadastro-usuario.jsp" class="botao-secundario">Cadastre-se</a>
            </div>
            <div class="menu-toggle" id="menu-toggle">
                <span></span>
                <span></span>
                <span></span>
            </div>
        </div>
    </header>
    <script>
        const toggle = document.getElementById('menu-toggle');
        const menuNavegacao = document.querySelector('.menu-navegacao');
        
        toggle.addEventListener('click', () => {
            menuNavegacao.classList.toggle('ativo');
            toggle.classList.toggle('ativo');
        });
    </script>
</body>
</html>
