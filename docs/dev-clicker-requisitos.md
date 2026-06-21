# Dev Clicker — Documento de Requisitos

## Como usar este documento

Este documento lista **o que precisa existir e funcionar** em cada etapa do projeto — não como construir. A ideia é que, ao tentar atender cada requisito, você descubra naturalmente o que precisa estudar. Cada bloco indica apenas a **área de conhecimento envolvida**, sem explicar a solução.

Siga a ordem. Não pule etapa nem bloco — cada um pressupõe que o anterior já funciona.

---

## Etapa 0 — Ambiente de Desenvolvimento

### Bloco 0.1 — Projeto Base
- Projeto Java/Spring Boot criado e executando localmente sem erros.
- Conexão com banco de dados PostgreSQL estabelecida e validada.
- Aplicação sobe e derruba sem deixar processos ou conexões presas.

*Área de conhecimento: configuração de projeto Spring, conexão a banco de dados.*

---

## Etapa 1 — V1 (MVP)

### Bloco 1.1 — Identidade e Acesso
- Um visitante consegue criar uma conta informando nome, email e senha.
- O sistema impede cadastro com email já existente.
- A senha nunca é armazenada nem retornada em texto puro.
- Um usuário cadastrado consegue se autenticar com email e senha.
- Tentativa de login com credenciais erradas é rejeitada com mensagem clara.
- Toda ação subsequente do jogo exige que o usuário esteja autenticado.
- Um usuário nunca consegue acessar ou alterar dados de outro usuário.

*Área de conhecimento: autenticação, autorização, segurança de credenciais.*

### Bloco 1.2 — Empresa e Recursos
- Ao se cadastrar, o usuário automaticamente passa a ter uma empresa associada a ele.
- A empresa possui dois recursos: código e dinheiro.
- O usuário consegue consultar o estado atual da sua empresa (recursos, produtos, equipe).
- Os valores de recursos nunca ficam negativos.
- Os valores monetários são tratados com precisão exata (sem erro de arredondamento).

*Área de conhecimento: modelagem de dados, persistência, tipos numéricos para valores monetários.*

### Bloco 1.3 — Produção Manual
- O jogador consegue executar uma ação que gera código manualmente.
- Cada execução da ação tem efeito previsível e consistente (não duplica nem perde valor em caso de chamadas simultâneas).

*Área de conhecimento: design de endpoints, consistência de estado sob concorrência.*

### Bloco 1.4 — Loja de Produtos
- Existe uma lista de produtos disponíveis para compra, cada um com custo (em código) e retorno (dinheiro por minuto).
- O jogador consegue comprar um produto se tiver código suficiente.
- A compra é recusada, com mensagem clara, se o código for insuficiente.
- Ao comprar, o código é descontado e o produto passa a integrar a empresa do jogador.
- O jogador pode possuir múltiplas unidades do mesmo produto.

*Área de conhecimento: regras de negócio, validação de domínio, relacionamento entre entidades.*

### Bloco 1.5 — Contratação de Equipe
- Existe uma lista de profissionais disponíveis para contratação, cada um com custo (em dinheiro) e produção (código por minuto).
- O jogador consegue contratar um profissional se tiver dinheiro suficiente.
- A contratação é recusada, com mensagem clara, se o dinheiro for insuficiente.
- Ao contratar, o dinheiro é descontado e o profissional passa a integrar a equipe da empresa.
- O jogador pode possuir múltiplos profissionais do mesmo tipo.

*Área de conhecimento: regras de negócio, relacionamento entre entidades, transações.*

### Bloco 1.6 — Produção Automática
- Profissionais contratados geram código automaticamente ao longo do tempo, mesmo sem o jogador interagir.
- Produtos comprados geram dinheiro automaticamente ao longo do tempo, mesmo sem o jogador interagir.
- A produção automática continua corretamente mesmo se o jogador ficar offline e voltar depois (sem perder nem duplicar produção).
- A produção é calculada para todas as empresas existentes, não só a de quem está logado no momento.

*Área de conhecimento: processamento em background, cálculo de tempo decorrido, jobs periódicos.*

### Bloco 1.7 — Persistência e Confiabilidade
- Nenhum dado é perdido ao reiniciar a aplicação.
- Uma operação que envolve múltiplas mudanças de estado (ex: comprar produto) nunca fica "pela metade" em caso de erro no meio do processo.
- Erros de regra de negócio retornam uma resposta estruturada e compreensível, nunca um erro técnico cru.

*Área de conhecimento: transações de banco de dados, tratamento de exceções.*

### Bloco 1.8 — Qualidade e Documentação
- Existe uma forma de qualquer pessoa (ou ferramenta) testar todos os endpoints sem precisar ler o código-fonte.
- As regras de negócio centrais (compra, contratação, produção) possuem verificação automatizada de que continuam corretas.
- Existe documentação mínima de como rodar o projeto do zero.

*Área de conhecimento: documentação de API, testes automatizados.*

---

## Etapa 2 — V2 (Progressão e Competição)

### Bloco 2.1 — Upgrades
- Existem melhorias compráveis que alteram a produção base (ex: aumento percentual de produção de código ou dinheiro).
- Um upgrade aplicado afeta corretamente todos os cálculos de produção subsequentes.
- Não é possível comprar o mesmo upgrade único mais de uma vez (quando aplicável).

*Área de conhecimento: modelagem de regras compostas, cálculo de multiplicadores.*

### Bloco 2.2 — Ranking
- Existe uma forma de consultar a posição de uma empresa em relação às demais (ex: por dinheiro total acumulado).
- A listagem de ranking não retorna todos os jogadores de uma vez (suporta grandes volumes sem travar).
- O ranking reflete o estado real e atualizado das empresas no momento da consulta.

*Área de conhecimento: consultas otimizadas, paginação, ordenação.*

### Bloco 2.3 — Conteúdo Expandido
- Novos produtos e cargos estão disponíveis, desbloqueados conforme algum critério de progressão (ex: dinheiro acumulado, nível da empresa).
- O sistema acomoda novo conteúdo sem exigir reescrever as regras já existentes.

*Área de conhecimento: extensibilidade de modelo de dados, regras de desbloqueio.*

### Bloco 2.4 — Performance em Escala
- O sistema continua respondendo de forma aceitável mesmo com um número alto de jogadores e produção automática simultânea.

*Área de conhecimento: performance de consultas, otimização de processamento em lote.*

---

## Etapa 3 — V3 (Sistemas Avançados)

### Bloco 3.1 — Prestígio
- O jogador pode optar por reiniciar sua empresa em troca de um benefício permanente.
- O processo de reset não deixa a empresa em estado inconsistente em caso de falha no meio da operação.
- O benefício de prestígio é cumulativo entre resets.

*Área de conhecimento: transações complexas, lógica de reset controlado.*

### Bloco 3.2 — Eventos de Jogo
- Existem eventos temporários que alteram regras do jogo por um período (ex: bônus de produção).
- A ativação de um evento não exige alterar diretamente o código das regras já existentes — o sistema reage ao evento de forma desacoplada.

*Área de conhecimento: arquitetura orientada a eventos, desacoplamento.*

### Bloco 3.3 — Atualização em Tempo Real (opcional)
- O estado da empresa é atualizado para o jogador sem que ele precise solicitar manualmente uma nova consulta.

*Área de conhecimento: comunicação em tempo real entre servidor e cliente.*

---

## Etapa 4 — V4 (Operação e Entrega)

### Bloco 4.1 — Containerização
- O projeto completo (aplicação + banco de dados) sobe em qualquer máquina com um único comando, sem configuração manual adicional.

*Área de conhecimento: containerização de aplicações.*

### Bloco 4.2 — Integração Contínua
- Toda alteração enviada ao repositório é automaticamente validada (build + testes) antes de ser considerada "pronta".

*Área de conhecimento: pipelines de integração contínua.*

### Bloco 4.3 — Deploy
- O sistema está acessível publicamente, fora da sua máquina local.
- Segredos (senha de banco, chave de autenticação) não estão expostos no código-fonte.

*Área de conhecimento: deploy de aplicações, gestão de variáveis de ambiente e segredos.*

---

## Critério Geral de Avanço de Etapa

Você só avança para a etapa seguinte quando **todos os requisitos da etapa atual estão atendidos e você consegue explicar, com suas próprias palavras, por que cada um funciona** — não só que funciona.
