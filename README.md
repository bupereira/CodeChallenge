# CodeChallenge
Spring Calculator (2.2.6)

### Proposição

##### Descrição  

##### Desenvolvimento de uma REST API que disponibilize funcionalidades básicas de uma calculadora.  

##### Requisitos Funcionais:  

• REST API que expõe as operações de soma, subtracção, multiplicação e divisão.  -> Feito.  
• Suporte para dois operandos apenas (a e b, por simplicidade).  -> Feito, com estes nomes, exatamente.  
• Suporte para *arbitrary precision signed decimal numbers*.  -> Feito.   

##### Requisitos Não Funcionais:  

• Projecto Gradle ou Maven com pelo menos dois módulos — rest e calculator.  -> Feito.  
• Utilização de Spring Boot 2.2.6 como foundation de ambos os módulos.  -> Aqui obtive o aval do Sr. André para utilizar 2.6.2, creio que 2.2.6 foi um erro.  
• Utilização de RabbitMQ e Spring AMQP para comunicação intermódulo.  -> Feito. Ver observação mais abaixo.  
• Configuração via application.properties (default do Spring Boot).  -> Quase não utilizei, não senti necessidade. Mas pus os arquivos lá para que não ficassem faltando.  
• Nenhuma configuração XML (com excepção, eventualmente, da de logging). -> Feito, conforme solicitado. Embora, claro, sendo um projeto Maven, também tenha pom.xml.  
• Versionamento do trabalho em Git.  -> Feito, neste repositório.   

OBSERVAÇÃO: o RabbitMQ deve ser executado à parte. Eu o rodei com o comando
```
docker run --rm --hostname rabbit --name rabbit -p 5672:5672 rabbitmq:3.7.3-alpine
```
Uma vez feito isto com sucesso, é só utilizar o IP local e seu cliente de preferência para executar as operações (todas são GET).
http://127.0.0.1:8080/sum?a=1&b=1 , por exemplo, mostra o JSON de resposta com o ID e o resultado, conforme solicitado.
As outras operações são subtract, multiply e divide, logo estes URLs devem mostrar resultados satisfatórios:  
http://127.0.0.1:8080/subtract?a=1&b=1  
http://127.0.0.1:8080/multiply?a=1&b=1  
http://127.0.0.1:8080/divide?a=1&b=1  

##### Bonus Points (Opcional):  

• Utilização de logback-access para logging do tráfego HTTP (+2).  -> Feito, mas logando para arquivo e console, simultaneamente. O arquivo está sendo salvo no diretório temporário do Java. Em uma máquina Windows, o padrão seria C:\Users\buper\AppData\Local\Temp .  
• Atribuição a cada pedido REST individual de um identificador único e comunicação aos clientes do mesmo  
através de um response header (+3).  -> Feito. Logs mostram o ID designado ao cliente a cada passo.  
• Propagação deste identificador de request através do MDC na comunicação intermódulo e inclusão do
mesmo em cada linha de logging que diga respeito a um pedido HTTP em ambos os módulos (+5).  -> O ID é propagado entre módulos.  

##### DELIVERABLES  

Repositório Git com a solução desenvolvida:  
• Alojado publicamente no GitHub; ou  
• Alojado de forma privada no BitBucket e partilhado connosco; ou  
• Zipado e enviado por email.
