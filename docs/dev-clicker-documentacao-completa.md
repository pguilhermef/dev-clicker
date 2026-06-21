# Dev Clicker — Documentação Completa do Projeto

> Jogo idle/clicker de construção de empresa de tecnologia, usado como laboratório prático para aprender Java, Spring Boot e arquitetura backend do zero.

---

## 1. Filosofia do Projeto

Este documento não é só um GDD (Game Design Document) — é um **plano de estudos disfarçado de jogo**. A ideia central:

- Você não está aprendendo Java fazendo exercícios soltos no Eclipse. Você está aprendendo Java **porque precisa** para o seu próprio produto funcionar.
- Cada funcionalidade do jogo foi desenhada para forçar o uso de um conceito específico de Spring/Java. Não existe feature "de graça" — toda mecânica nova = um conceito técnico novo.
- A progressão do jogo (V1 → V2 → V3) é também a sua progressão de aprendizado. Não pule fases.
- Como você vem do Power Platform, alguns paralelos vão aparecer ao longo do texto (ex: Service do Spring ≈ uma Cloud Flow do Power Automate que centraliza regra de negócio; Repository ≈ a conexão com o SharePoint/Dataverse).

**Regra de ouro:** se uma funcionalidade nova não te ensina nada que a anterior já não tenha ensinado, ela não entra ainda. Profundidade antes de largura.

---

## 2. Visão Geral do Jogo

### 2.1 Conceito

O jogador assume o papel de fundador de uma startup de tecnologia. Ele começa programando sozinho, gera código, transforma esse código em produtos digitais (sites, automações, apps), os produtos geram dinheiro, o dinheiro contrata profissionais, os profissionais geram mais código automaticamente — e o ciclo se acelera.

### 2.2 Loop Principal (expandido)

```text
Gerar Código (manual ou automático)
        ↓
Acumular Código
        ↓
Investir Código em Produtos  ──┐
        ↓                      │
Produtos geram Dinheiro/min    │
        ↓                      │
Investir Dinheiro em Equipe    │
        ↓                      │
Equipe gera Código/min ────────┘
        ↓
Desbloquear novos Produtos, Cargos e Upgrades
        ↓
Repetir em escala cada vez maior
```

### 2.3 Pilares de Design

1. **Progressão clara**: o jogador sempre sabe qual é o próximo objetivo (próximo produto, próxima contratação).
2. **Automação como recompensa**: contratar gente e comprar produtos existe para reduzir o clique manual — narrativamente isso é literalmente "automatizar seu trabalho", o que reflete o tema do jogo.
3. **Números crescentes, mas legíveis**: sem inflação absurda de números na V1 (isso vem depois, com prestígio/scaling).
4. **Cada sistema = um conceito técnico**: ver mapeamento completo na seção 6.

---

## 3. Arquitetura do Projeto (Backend)

### 3.1 Camadas

```text
Controller   → recebe requisições HTTP, valida entrada, chama o Service
Service      → contém a regra de negócio do jogo (cálculos, validações de domínio)
Repository   → fala com o banco (Spring Data JPA faz isso por você)
Entity       → representa as tabelas do banco (User, Company, Product, Employee)
DTO          → o que trafega na API (nunca exponha a Entity direto pro cliente)
```

Por que separar assim? Porque cada camada tem uma única responsabilidade. Se amanhã você trocar o banco de PostgreSQL pra outro, só a camada Repository é afetada. Se mudar a regra "quanto custa contratar um Pleno", só mexe no Service.

### 3.2 Estrutura de Pastas Sugerida

```text
src/main/java/com/devclicker/
├── controller/
│   ├── AuthController.java
│   ├── CompanyController.java
│   ├── ProductController.java
│   └── EmployeeController.java
├── service/
│   ├── AuthService.java
│   ├── CompanyService.java
│   ├── ProductService.java
│   ├── EmployeeService.java
│   └── ProductionSchedulerService.java
├── repository/
│   ├── UserRepository.java
│   ├── CompanyRepository.java
│   ├── ProductRepository.java
│   └── EmployeeRepository.java
├── entity/
│   ├── User.java
│   ├── Company.java
│   ├── Product.java
│   └── Employee.java
├── dto/
│   ├── request/
│   │   ├── RegisterRequest.java
│   │   ├── LoginRequest.java
│   │   └── PurchaseProductRequest.java
│   └── response/
│       ├── CompanyResponse.java
│       └── AuthResponse.java
├── security/
│   ├── JwtTokenProvider.java
│   ├── JwtAuthFilter.java
│   └── SecurityConfig.java
├── exception/
│   ├── GlobalExceptionHandler.java
│   ├── InsufficientFundsException.java
│   └── ResourceNotFoundException.java
└── DevClickerApplication.java
```

### 3.3 Por que DTO e não a Entity direto?

Se você devolver a Entity `User` na resposta da API, devolve o hash da senha junto sem querer. DTO é a "vitrine" controlada do que sai e do que entra. É uma boa prática que vale a pena fixar desde o módulo 1.

---

## 4. Modelagem de Dados Completa

### 4.1 Diagrama de Relacionamento (texto)

```text
User (1) ───── (1) Company (1) ───── (N) Product
                       │
                       └────── (N) Employee
```

### 4.2 Entities Detalhadas

#### User
```java
id              Long
name            String
email           String (único)
password        String (hash, nunca em texto puro)
createdAt       LocalDateTime
```

#### Company
```java
id              Long
owner           User (OneToOne)
name            String
code            Long      // recurso 1
money           BigDecimal // recurso 2 — usar BigDecimal, não double, pra dinheiro
createdAt       LocalDateTime
products        List<Product>   (OneToMany)
employees       List<Employee>  (OneToMany)
```

> **Nota técnica importante:** use `BigDecimal` para dinheiro, nunca `float`/`double`. Double tem erro de arredondamento e em jogo financeiro isso gera bugs sutis difíceis de rastrear. É uma lição clássica de quem trabalha com dados monetários.

#### Product
```java
id                Long
company           Company (ManyToOne)
type              ProductType (enum: SITE, AUTOMACAO, APLICATIVO, ...)
quantity          Integer   // quantos o jogador possui daquele tipo
incomePerMinute   BigDecimal
purchasedAt       LocalDateTime
```

#### Employee
```java
id                    Long
company               Company (ManyToOne)
type                  EmployeeType (enum: ESTAGIARIO, JUNIOR, PLENO, SENIOR, ...)
quantity              Integer
productionPerMinute   Integer
hiredAt               LocalDateTime
```

### 4.3 Por que `quantity` em vez de criar uma linha por unidade?

Se o jogador comprar 50 Sites, você não quer 50 linhas no banco — quer 1 linha com `quantity = 50`. Isso te obriga a pensar em **agregação de dados** desde cedo, um problema de modelagem bem realista.

---

## 5. Catálogo de Conteúdo do Jogo

### 5.1 Produtos (V1)

| Produto    | Custo (Código) | Retorno ($/min) | Payback (min) |
|------------|-----------------|------------------|----------------|
| Site       | 100              | 1                | 100            |
| Automação  | 500              | 5                | 100            |
| Aplicativo | 2000             | 20               | 100            |

> Payback proposital igual entre os três — na V1 a progressão é linear e previsível. Em V2 isso muda (ver seção 9).

### 5.2 Profissionais (V1)

| Cargo       | Custo ($) | Produção (Código/min) |
|-------------|-----------|--------------------------|
| Estagiário  | 100       | 1                        |
| Júnior      | 500       | 5                        |
| Pleno       | 2000      | 20                       |

### 5.3 Produtos e Cargos Futuros (V2+, conteúdo a desbloquear)

| Produto         | Custo (Código) | Retorno ($/min) |
|-----------------|------------------|-------------------|
| SaaS B2B        | 8000             | 80                |
| Plataforma Mobile | 30000          | 300               |
| IA como Serviço | 100000           | 1000              |

| Cargo    | Custo ($) | Produção (Código/min) |
|----------|-----------|--------------------------|
| Sênior   | 8000      | 80                       |
| Tech Lead| 30000     | 300                      |
| CTO      | 100000    | 1000 (+ bônus de equipe, ver 9.3) |

---

## 6. Mapeamento Feature → Conceito Técnico

Esse é o coração do projeto: cada entrega de jogo existe para fixar um aprendizado.

| Feature do jogo | Conceito técnico ensinado |
|---|---|
| Cadastro de usuário | `@RestController`, DTO de entrada, `@Valid`, Bean Validation |
| Login | Spring Security, geração e validação de JWT |
| Empresa armazenando recursos | `@Entity`, JPA, mapeamento de colunas |
| Clique manual (gerar código) | Endpoint `POST`, atualização de estado, idempotência |
| Compra de produto | Regra de negócio em Service, `@Transactional`, exceptions customizadas |
| Contratação de profissional | Relacionamento `@OneToMany`/`@ManyToOne`, cascade |
| Produção automática | `@Scheduled`, processamento em background, cálculo por delta de tempo |
| Ranking entre jogadores (V2) | Query customizada, `@Query`, ordenação, paginação |
| Loja com upgrades (V2) | Enum + Strategy pattern, cálculo de multiplicadores |
| Prestígio / reset (V3) | Transações complexas, lógica de reset controlado |
| Notificações de eventos (V3) | Eventos do Spring (`ApplicationEventPublisher`), desacoplamento |
| Testes automatizados | JUnit, Mockito, testes de Service e Controller |
| Documentação da API | Swagger / springdoc-openapi |

---

## 7. Trilha de Estudos Detalhada

Cada módulo tem teoria mínima + uma entrega prática **direto no jogo**, não em exercício avulso.

### Módulo 1 — Java Básico
**Estudar:** classes, objetos, encapsulamento, herança, interfaces, Collections, Streams básico.
**Prática:** modelar as classes `User`, `Company`, `Product`, `Employee` como POJOs puros, sem Spring ainda, só pra fixar OOP.
**Meta de saída:** você consegue explicar a diferença entre uma classe e um objeto sem decorar.

### Módulo 2 — Spring Boot Essencial
**Estudar:** `@RestController`, `@Service`, `@Repository`, `@Entity`, injeção de dependência.
**Prática:** subir o primeiro endpoint `GET /health` e depois `POST /users` salvando em memória (sem banco ainda).
**Meta de saída:** entender por que o Spring "injeta" os objetos pra você em vez de você fazer `new` na mão.

### Módulo 3 — Banco de Dados (PostgreSQL)
**Estudar:** tabelas, chaves primárias e estrangeiras, tipos de dados, índices básicos.
**Prática:** subir Postgres via Docker, conectar a aplicação, persistir `User` de verdade.
**Meta de saída:** rodar uma query manual no banco e entender o que o Spring Data está fazendo por baixo.

### Módulo 4 — JPA / Hibernate
**Estudar:** `@OneToMany`, `@ManyToOne`, `cascade`, `fetch` (LAZY vs EAGER).
**Prática:** modelar `Company → Products` e `Company → Employees`, testar o que acontece ao deletar uma Company com cascade ON e OFF.
**Meta de saída:** explicar por que LAZY é o padrão recomendado e quando EAGER causa problema de performance (N+1 query).

### Módulo 5 — Segurança (Spring Security + JWT)
**Estudar:** autenticação vs autorização, hashing de senha (BCrypt), geração/validação de token JWT, filtros de segurança.
**Prática:** implementar `/auth/register` e `/auth/login`, proteger todos os outros endpoints exigindo token.
**Meta de saída:** cada jogador só consegue ver e modificar a própria empresa, nunca a de outro.

### Módulo 6 — Agendamentos (`@Scheduled`)
**Estudar:** processamento periódico, cuidado com tarefas que rodam mais rápido do que terminam, cálculo de produção por tempo decorrido (não por "tick fixo").
**Prática:** implementar o `ProductionSchedulerService` que roda a cada minuto e atualiza código/dinheiro de todas as empresas.
**Meta de saída:** entender por que calcular "quanto tempo passou desde a última atualização" é mais robusto que confiar em um scheduler que nunca atrasa.

### Módulo 7 — Tratamento de Erros e Validação
**Estudar:** `@ExceptionHandler`, `@ControllerAdvice`, Bean Validation (`@NotNull`, `@Email`, etc.), exceptions customizadas.
**Prática:** criar `InsufficientFundsException` (jogador tenta comprar sem dinheiro) e retornar um JSON de erro padronizado.
**Meta de saída:** a API nunca devolve uma stack trace crua pro cliente.

### Módulo 8 — Testes Automatizados
**Estudar:** JUnit 5, Mockito, diferença entre teste unitário e teste de integração.
**Prática:** testar a regra "não pode comprar produto sem código suficiente" isoladamente, sem subir o banco.
**Meta de saída:** quebrar a regra de propósito no código e ver o teste falhar (prova de que o teste realmente testa algo).

### Módulo 9 — Documentação de API
**Estudar:** springdoc-openapi (Swagger).
**Prática:** documentar todos os endpoints da V1, com exemplos de request/response.
**Meta de saída:** alguém de fora consegue usar sua API só lendo o Swagger, sem te perguntar nada.

---

## 8. Contrato de API (V1)

### Autenticação
```text
POST /auth/register
Body: { name, email, password }
Response: 201 Created

POST /auth/login
Body: { email, password }
Response: 200 OK { token, expiresIn }
```

### Empresa
```text
GET /company/me
Header: Authorization: Bearer <token>
Response: { id, name, code, money, products[], employees[] }
```

### Produção Manual
```text
POST /company/me/code
Response: { code: novoValor }
```

### Produtos
```text
GET  /products/catalog
POST /company/me/products
Body: { type: "SITE" }
Response: { code restante, produto adquirido }
```

### Profissionais
```text
GET  /employees/catalog
POST /company/me/employees
Body: { type: "ESTAGIARIO" }
Response: { money restante, profissional contratado }
```

> Todos os endpoints exceto `/auth/*` exigem token JWT válido. Todo erro de negócio (ex: dinheiro insuficiente) retorna `400` com corpo padronizado `{ error, message }`.

---

## 9. Roadmap de Versões

### V1 — MVP (escopo já definido no documento original)
Cadastro, login, recursos, clique manual, compra de produtos, contratação, produção automática.

### V2 — Progressão e Competição
- Upgrades que multiplicam produção (ex: "Café Premium": +10% produção de código).
- Ranking global de empresas (por dinheiro total).
- Novos produtos e cargos (tabela da seção 5.3).
- Paginação e ordenação nas listagens.

### V3 — Sistemas Avançados
- **Prestígio**: jogador pode "resetar" a empresa em troca de um multiplicador permanente (ensina transações complexas e lógica de reset controlado).
- **Eventos de jogo**: bônus temporários, usando `ApplicationEventPublisher` do Spring para desacoplar lógica.
- **WebSocket** (opcional, avançado): atualização em tempo real dos recursos no front, sem precisar dar refresh.

### V4 — Polimento e Deploy
- Dockerizar a aplicação inteira (app + Postgres).
- CI básico (GitHub Actions rodando os testes a cada push).
- Deploy em ambiente real (Render, Railway, ou VPS própria — você já mexe com self-hosted, então essa parte vai ser natural pra você).

---

## 10. Boas Práticas a Seguir Desde o Início

1. **Nunca exponha Entity diretamente na API** — sempre via DTO.
2. **Use `BigDecimal` para dinheiro.**
3. **Toda regra de negócio mora no Service, nunca no Controller.** Controller só recebe, valida formato básico e delega.
4. **Nomeie exceptions pelo problema de domínio**, não genericamente (`InsufficientFundsException`, não `BusinessException`).
5. **Escreva o teste da regra antes de "achar que funciona"** — pelo menos para as regras de compra/contratação, que são o coração do jogo.
6. **Versionamento desde o commit 1** — Git, mensagens de commit descritivas, branch por feature, igual você já faz no DevOps do trabalho.
7. **Variáveis de ambiente para segredos** (senha do banco, segredo do JWT) — nunca hardcoded.

---

## 11. Definição de Pronto — Por Fase

### V1
- [ ] Cadastro e login funcionando com JWT
- [ ] Empresa criada automaticamente no registro
- [ ] Clique manual gera código
- [ ] Compra de produto consome código corretamente
- [ ] Contratação consome dinheiro corretamente
- [ ] Scheduler atualiza recursos automaticamente
- [ ] Erros de negócio retornam JSON padronizado, não stack trace
- [ ] Dados persistem no PostgreSQL
- [ ] Projeto sobe localmente via Docker Compose sem passos manuais extras
- [ ] README explica como rodar do zero

### V2
- [ ] Upgrades implementados e testados
- [ ] Ranking funcionando com paginação
- [ ] Cobertura de teste nas regras de negócio principais (>70% no Service layer)
- [ ] Swagger documentando 100% dos endpoints

### V3
- [ ] Sistema de prestígio sem corromper dados em caso de falha no meio da transação
- [ ] Eventos de jogo desacoplados via Spring Events
- [ ] (Opcional) WebSocket funcionando para atualização em tempo real

---

## 12. Glossário Rápido (para quem vem do low-code)

| Termo Java/Spring | Analogia rápida |
|---|---|
| `@Entity` | A definição de uma tabela/lista, como uma lista do SharePoint ou tabela do Dataverse |
| `@Repository` | A "conexão" com a fonte de dados — o que no Power Automate seria a action de buscar/salvar item |
| `@Service` | Onde a regra de negócio mora — equivalente ao corpo de uma Cloud Flow que decide o que fazer |
| `@Controller` | O "gatilho" exposto para o mundo de fora — como o trigger HTTP de um flow |
| DTO | O formato de dado que entra/sai, como o schema de um JSON em uma action HTTP |
| `@Transactional` | Garante que uma sequência de operações no banco aconteça toda ou nenhuma — não existe meio-termo |
| JWT | Um "crachá" assinado que prova quem é o usuário sem precisar consultar login a cada chamada |

---

*Documento vivo: conforme o projeto avança, esta documentação deve ser atualizada junto — principalmente as seções 5 (catálogo), 8 (contrato de API) e 11 (definição de pronto).*
