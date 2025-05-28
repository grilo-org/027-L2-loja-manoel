Loja do Seu Manoel - API de Empacotamento
Descrição
Esta API foi desenvolvida para automatizar o processo de embalagem dos pedidos da loja de jogos online do Seu Manoel. A API recebe uma lista de pedidos com produtos e suas dimensões, e retorna a melhor forma de empacotá-los nas caixas disponíveis.

Funcionalidades
Recebe pedidos com múltiplos produtos e suas dimensões

Determina a melhor caixa para cada conjunto de produtos

Retorna a lista de caixas usadas e os produtos em cada uma

Identifica produtos que não cabem em nenhuma caixa disponível

Documentação interativa com Swagger UI

Caixas Disponíveis
Caixa	Dimensões (altura × largura × comprimento)
Caixa 1	30 × 40 × 80 cm
Caixa 2	80 × 50 × 40 cm
Caixa 3	50 × 80 × 60 cm
Tecnologias Utilizadas
Java 17

Spring Boot 3.x

Springdoc OpenAPI (Swagger)

JUnit 5 (para testes unitários)

Docker

Pré-requisitos
JDK 17+

Maven 3.6+

Docker (opcional)