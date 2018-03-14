# ListaEventos-SpringBoot
Projeto Java com Spring Boot, HTML, CSS materialize e BD mysql.

link do projeto executavel:
https://springappeventos.herokuapp.com/eventos



_____ERRO_____
Erro ao excluir evento.
could not execute statement; SQL [n/a]; nested exception is org.hibernate.exception.SQLGrammarException: could not execute statement

Analise:
Erro se encontra referenciado ao banco de dados. Verificar o metodo de deletar @RequestMapping. ("/deletarEventos")
