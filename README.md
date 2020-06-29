Desafio técnico CalCard / Especificações técnicas.
Backend

Servidor de aplicação: JBoss/Wildfly 10.x
Banco de dados: Postgres 12
Linguagem: Java 8
IDE: Red Hat CodeReady Studio 12.13.0.GA
Frontend

Linguagem: AngularJS e NodeJS
Autenticação: Auth0
Sobre a demanda:

Foi desenvolvida uma API/CRUD/REST para a realização de avaliação de crédito de Pessoas conforme especificação https://github.com/calcardev/technical-evaluation.

O sistema possui autenticação via Auth0 por meio de usuários Google ou Github e também uma forma alternativa de autenticação chamada "Login Técnico". As listagens e Cadastros somente estão disponíveis após a realização da autenticação.

A API/REST foi implemenada com Jackson Annotations (com.fasterxml.jackson.annotation.JsonProperty), sobre essa implementação uma Interface CustomValidator para realizar as validações com base nas restrições definidas nas Annotations.

A arquitetura do Backend está sub-dividida em EJB´s para o DOMain, Service e Repository. A aplicação web REST faz uso de @Inject bem como entre os EJB's. Todos os EJB´s compoem um EAR para deploy no Wildfly 10.x.

Foi gerado o DOCKER e publicado no AWS/EC2 por meio de DOCKER-MACHINE ficando disponível para teste ON-LINE em http://54.224.23.187:3000/.

Analista Responsável: Geferson Buzzello
