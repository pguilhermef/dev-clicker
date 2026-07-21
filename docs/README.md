# Dev Clicker — Console Edition

> Jogo idle/clicker de construção de empresa de tecnologia, desenvolvido em **Java puro**, sem frameworks, sem banco de dados, sem dependências externas.

Este projeto é um laboratório de aprendizado de Java antes da migração para Spring Boot. O objetivo não é o jogo em si — é usar o jogo como contexto para dominar os fundamentos de Java e Orientação a Objetos que sustentam qualquer projeto profissional.

---

## Sumário

- [Filosofia do Projeto](#filosofia-do-projeto)
- [Como Funciona o Jogo](#como-funciona-o-jogo)
- [Arquitetura](#arquitetura)
- [Modelagem](#modelagem)
- [Conteúdo do Jogo](#conteúdo-do-jogo)
- [Habilidades Desenvolvidas](#habilidades-desenvolvidas)
- [Trilha de Estudos](#trilha-de-estudos)
- [Requisitos por Etapa](#requisitos-por-etapa)
- [Como Rodar](#como-rodar)
- [Critério de Conclusão](#critério-de-conclusão)
- [Próximo Passo](#próximo-passo)

---

## Filosofia do Projeto

Este projeto existe por uma razão simples: **não dá pra entender o que um framework faz por você sem antes ter feito na mão**.

O Spring Boot automatiza dezenas de coisas — injeção de dependência, mapeamento de rotas, persistência de dados. Quem vai direto pro framework usa essas ferramentas sem entender o problema que elas resolvem. Quem constrói na mão primeiro entende o porquê de cada abstração.

**Regras do projeto:**

1. Zero frameworks. Zero bibliotecas externas. Só Java.
2. Cada funcionalidade nova deve ensinar um conceito novo.
3. O projeto só avança quando você consegue explicar o que já existe com suas próprias palavras.
4. Nenhuma classe faz mais de uma coisa.

---

## Como Funciona o Jogo

O jogador assume o papel de fundador de uma startup. Começa programando sozinho, gera código, transforma esse código em produtos digitais, os produtos geram dinheiro, o dinheiro contrata profissionais, os profissionais geram mais código automaticamente.

### Loop Principal

```text
Gerar Código (manual)
        ↓
Acumular Código
        ↓
Comprar Produto com Código
        ↓
Produto gera Dinheiro automaticamente
        ↓
Contratar Profissional com Dinheiro
        ↓
Profissional gera Código automaticamente
        ↓
Repetir em escala crescente
```

### Interface no Terminal

```text
╔══════════════════════════════════════╗
║         DEV CLICKER v1.0             ║
╠══════════════════════════════════════╣
║  Empresa : Startup do Guilherme      ║
║  Código  : 150                       ║
║  Dinheiro: $200,00                   ║
╠══════════════════════════════════════╣
║  1. Programar          (+1 código)   ║
║  2. Ver produtos                     ║
║  3. Comprar produto                  ║
║  4. Ver profissionais                ║
║  5. Contratar profissional           ║
║  6. Status da empresa                ║
║  0. Sair                             ║
╚══════════════════════════════════════╝
Escolha: _
```

---

## Arquitetura

O projeto segue separação de responsabilidades desde o início — a mesma lógica que o Spring Boot vai usar depois, só que sem anotações mágicas.

```text
src/main/java/com/devclicker/console/
│
├── Main.java                        ← ponto de entrada, loop do menu
│
├── model/                           ← dados do jogo (o que existe)
│   ├── Player.java
│   ├── Company.java
│   ├── Product.java
│   └── Employee.java
│
├── enums/                           ← tipos fixos do jogo
│   ├── ProductType.java
│   └── EmployeeType.java
│
├── service/                         ← regras de negócio (o que acontece)
│   ├── GameService.java
│   └── ProductionService.java
│
├── exception/                       ← erros de domínio
│   ├── InsufficientCodeException.java
│   └── InsufficientFundsException.java
│
└── ui/                              ← exibição no console
    └── MenuDisplay.java
```

### Por que essa separação?

| Camada | Responsabilidade | Analogia Spring |
|---|---|---|
| `model` | Representar os dados | `@Entity` |
| `service` | Executar as regras | `@Service` |
| `exception` | Representar erros de domínio | `@ExceptionHandler` |
| `ui` | Exibir informações | `@RestController` |
| `Main` | Iniciar e orquestrar | `main()` do Spring Boot |

Quando você migrar pro Spring, vai reconhecer cada uma dessas camadas — o framework só adiciona anotações e automatiza o que você já fez na mão aqui.

---

## Modelagem

### Player
```java
- String name
- Company company
```

### Company
```java
- String name
- Long code                    // recurso 1
- BigDecimal money             // recurso 2 — BigDecimal, nunca double
- List<Product> products
- List<Employee> employees
```

> **Por que `BigDecimal` e não `double`?**
> `double` tem erro de arredondamento em operações decimais. `0.1 + 0.2` em double não é `0.3`. Em valores monetários isso gera bugs sutis e difíceis de rastrear. `BigDecimal` garante precisão exata.

### Product
```java
- ProductType type             // enum: SITE, AUTOMACAO, APLICATIVO
- BigDecimal incomePerMinute
```

### Employee
```java
- EmployeeType type            // enum: ESTAGIARIO, JUNIOR, PLENO
- Integer codePerMinute
```

### ProductType (enum)
```java
SITE        (custo: 100 código,  retorno: $1/rodada)
AUTOMACAO   (custo: 500 código,  retorno: $5/rodada)
APLICATIVO  (custo: 2000 código, retorno: $20/rodada)
```

### EmployeeType (enum)
```java
ESTAGIARIO  (custo: $100,  produção: 1 código/rodada)
JUNIOR      (custo: $500,  produção: 5 código/rodada)
PLENO       (custo: $2000, produção: 20 código/rodada)
```

---

## Conteúdo do Jogo

### Produtos disponíveis

| Produto    | Custo (Código) | Retorno ($/rodada) |
|------------|----------------|--------------------|
| Site       | 100            | 1                  |
| Automação  | 500            | 5                  |
| Aplicativo | 2.000          | 20                 |

### Profissionais disponíveis

| Cargo      | Custo ($) | Produção (código/rodada) |
|------------|-----------|--------------------------|
| Estagiário | 100       | 1                        |
| Júnior     | 500       | 5                        |
| Pleno      | 2.000     | 20                       |

---

## Habilidades Desenvolvidas

### Orientação a Objetos
- **Classes e objetos**: criar modelos, instanciar, usar
- **Encapsulamento**: atributos `private`, getters e setters com propósito real
- **Construtores**: inicializar objetos com dados obrigatórios
- **Herança**: base comum entre `Product` e `Employee`
- **Polimorfismo**: tratar tipos diferentes de forma uniforme
- **Interfaces**: definir contratos entre camadas

### Tipos e Estruturas de Dados
- **Enums**: quando usar enum em vez de String ou constante
- **List e ArrayList**: armazenar e iterar coleções
- **BigDecimal**: trabalhar com dinheiro sem erro de arredondamento
- **String**: manipulação e formatação de texto

### Controle de Fluxo
- **switch/case**: processar escolha do menu
- **while**: loop principal do jogo
- **for-each**: iterar sobre produtos e profissionais
- **if/else**: validar condições de compra e contratação

### Exceções
- Criar exceptions customizadas por domínio
- Lançar exceptions com `throw`
- Capturar com `try/catch`
- Diferença entre checked e unchecked exceptions

### Boas Práticas
- Separação de responsabilidades sem framework
- Métodos pequenos com responsabilidade única
- Nomes descritivos que dispensam comentário
- Não repetir lógica (princípio DRY)
- Código extensível: adicionar novo produto sem reescrever regras existentes

---

## Trilha de Estudos

Cada módulo tem um conceito central e uma entrega prática diretamente no projeto.

### Módulo 1 — Classes e Objetos
**Conceito:** o que é uma classe, o que é um objeto, como um se relaciona com o outro.
**Entrega:** criar as classes `Player` e `Company` com atributos e construtores.
**Meta:** instanciar um `Player` no `Main` e imprimir os dados no console.

### Módulo 2 — Encapsulamento
**Conceito:** por que atributos são `private` e por que getters/setters existem.
**Entrega:** adicionar getters e setters nas classes de modelo.
**Meta:** acessar e modificar dados de `Company` apenas pelos métodos, nunca diretamente.

### Módulo 3 — Enums
**Conceito:** representar um conjunto fixo de valores com tipo seguro.
**Entrega:** criar `ProductType` e `EmployeeType` com custo e retorno embutidos.
**Meta:** consultar o custo de um produto pelo enum sem usar `if` ou `switch`.

### Módulo 4 — Coleções
**Conceito:** armazenar e iterar conjuntos de objetos.
**Entrega:** adicionar `List<Product>` e `List<Employee>` à `Company`.
**Meta:** listar todos os produtos da empresa no console com `for-each`.

### Módulo 5 — Regras de Negócio no Service
**Conceito:** separar o que é dado do que é comportamento.
**Entrega:** criar `GameService` com os métodos `buyProduct` e `hireEmployee`.
**Meta:** compra e contratação funcionando com validação de recursos.

### Módulo 6 — Exceções Customizadas
**Conceito:** representar erros de domínio com tipos próprios.
**Entrega:** criar `InsufficientCodeException` e `InsufficientFundsException`.
**Meta:** tentativa de compra sem recurso lança exception e exibe mensagem clara.

### Módulo 7 — Produção Automática
**Conceito:** cálculo baseado em estado acumulado.
**Entrega:** criar `ProductionService` que calcula código e dinheiro gerados por rodada.
**Meta:** a cada volta do menu, recursos aumentam automaticamente conforme equipe e produtos.

### Módulo 8 — Interface e Polimorfismo
**Conceito:** contratos, herança, comportamento intercambiável.
**Entrega:** criar interface `Producible` implementada por `Product` e `Employee`.
**Meta:** `ProductionService` calcula produção de qualquer `Producible` sem saber o tipo concreto.

---

## Requisitos por Etapa

### Etapa 1 — Projeto rodando
- [ ] Projeto Java criado e compilando sem erros
- [ ] `Main.java` com método `main` executando
- [ ] Menu impresso no console

### Etapa 2 — Modelo de dados
- [ ] Classes `Player`, `Company`, `Product`, `Employee` criadas
- [ ] Enums `ProductType` e `EmployeeType` com custo e retorno
- [ ] `Company` inicializada com código e dinheiro iniciais

### Etapa 3 — Ações do jogador
- [ ] Programar manualmente adiciona código
- [ ] Comprar produto valida código, desconta e adiciona à empresa
- [ ] Contratar profissional valida dinheiro, desconta e adiciona à empresa
- [ ] Ação inválida no menu retorna mensagem de erro sem travar

### Etapa 4 — Validações e exceções
- [ ] Compra sem código lança `InsufficientCodeException`
- [ ] Contratação sem dinheiro lança `InsufficientFundsException`
- [ ] Exceptions capturadas e exibidas como mensagem amigável

### Etapa 5 — Produção automática
- [ ] A cada rodada do menu, profissionais geram código
- [ ] A cada rodada do menu, produtos geram dinheiro
- [ ] Produção calculada com base na lista atual de profissionais e produtos

### Etapa 6 — Polimento
- [ ] Status da empresa exibe recursos, produtos e equipe de forma organizada
- [ ] Nenhuma entrada do usuário quebra o programa
- [ ] Código extensível: novo tipo de produto adicionado sem reescrever `GameService`

---

## Como Rodar

### Pré-requisitos
- Java 21 instalado
- Qualquer IDE (IntelliJ recomendado) ou terminal com `javac`

### Pela IDE
1. Clone o repositório
2. Abra o projeto na IDE
3. Execute a classe `Main.java`

### Pelo Terminal
```bash
# compilar
javac -d out src/main/java/com/devclicker/console/**/*.java

# executar
java -cp out com.devclicker.console.Main
```

---

## Critério de Conclusão

O projeto está concluído quando:

- [ ] Todas as etapas de requisitos estão completas
- [ ] Nenhuma entrada do usuário quebra ou trava o programa
- [ ] Você consegue explicar o que cada classe faz e por que existe
- [ ] Você consegue adicionar um novo tipo de produto sem alterar `GameService`
- [ ] Você consegue explicar a diferença entre `model` e `service` sem consultar anotações

---

## Próximo Passo

Ao concluir este projeto, você terá a base necessária para migrar para o **Dev Clicker Spring Edition** — a versão completa com Spring Boot, PostgreSQL, JWT e API REST.

A migração vai parecer natural porque você já conhece o domínio:

| Console Edition | Spring Edition |
|---|---|
| `model/Company.java` | `@Entity Company.java` |
| `service/GameService.java` | `@Service GameService.java` |
| *(sem equivalente)* | `@Repository CompanyRepository.java` |
| `ui/MenuDisplay.java` | `@RestController CompanyController.java` |
| `Main.java` | `DevClickerApplication.java` |

O Spring não inventa nada novo — ele organiza e automatiza o que você já fez na mão aqui.

---

*Projeto desenvolvido como laboratório de aprendizado de Java — parte da jornada de migração para desenvolvimento backend profissional com Spring Boot.*
