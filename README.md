# Desafio Grupo Zap

Projeto android kotlin para demonstrar os conhecimentos técnicos.

A arquitetura usada foi o MVVM com conceitos de Clean, já que foi separado em camadas:
### - Data: 
  Onde fica os serviços de comunicação externa e os seus models para mapear os JSON.
### - Domain:
  Onde fica as chamadas da camada de data e os casos de uso para preparar os dados para view.
### - Presentation:
  Onde fica as regras de apresentação e navegação.
  
 Foi desenvolvido os testes unitários usando Mockk para "mockar" os valores, foi colocado para rodar junto com a esteira de CI configurada no Github Actions,
 ao criar um PR para Main ou Develop esse workflow será executado. Junto com os testes unitários será executado o ktlint que é um analisador de código estático.
  
## Tecnologias usadas:
 - Kotlin
 - Koin
 - Coroutines
 - Retrofit
 - Glide
 - Mockk
 - Ktlint
 - Github Actions
