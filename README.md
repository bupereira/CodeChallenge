# CodeChallenge
Spring Calculator (2.2.6)

Proposição

Descrição
Desenvolvimento de uma REST API que disponibilize funcionalidades básicas de uma calculadora.
Requisitos Funcionais:
• REST API que expõe as operações de soma, subtracção, multiplicação e divisão.
• Suporte para dois operandos apenas (a e b, por simplicidade).
• Suporte para arbitraryprecision signed decimal numbers.
Requisitos Não Funcionais:
• Projecto Gradle ou Maven com pelo menos dois módulos — rest e calculator.
• Utilização de Spring Boot 2.2.6 como foundation de ambos os módulos.
• Utilização de RabbitMQ e Spring AMQP para comunicação intermódulo.
• Configuração via application.properties (default do Spring Boot).
• Nenhuma configuração XML (com excepção, eventualmente, da de logging).
• Versionamento do trabalho em Git.
Bonus Points (Opcional):
• Utilização de logback-access para logging do tráfego HTTP (+2).
• Atribuição a cada pedido REST individual de um identificador único e comunicação aos clientes do mesmo
através de um response header (+3).
• Propagação deste identificador de request através do MDC na comunicação intermódulo e inclusão do
mesmo em cada linha de logging que diga respeito a um pedido HTTP em ambos os módulos (+5).
DELIVERABLES
Repositório Git com a solução desenvolvida:
• Alojado publicamente no GitHub; ou
• Alojado de forma privada no BitBucket e partilhado connosco; ou
• Zipado e enviado por email.
