# Arquitetura_Hexagonal
## 🏗️ Justificativa da Arquitetura Escolhida (Hexagonal Architecture)

Para o desenvolvimento deste microsserviço de auditoria e tratamento de mensagens falhas (`deadmessages`), optou-se pela utilização da **Arquitetura Hexagonal** (também conhecida como *Ports and Adapters*). 

Abaixo estão os pilares estratégicos e técnicos que justificam essa escolha para o contexto do projeto:

### 1. Independência de Tecnologia e Infraestrutura
O coração deste sistema é a **Regra de Negócio de Auditoria** (determinar a severidade do erro, validar o payload do pedido e preparar o log crítico). Na Arquitetura Hexagonal, essa lógica fica totalmente isolada no *Core* (Domain), protegida de tecnologias externas. 
* Atualmente, consumimos mensagens via **AWS SQS** e persistimos os dados no **H2 Database**. 
* Se amanhã o provedor de nuvem mudar para **Azure Service Bus** ou **Kafka**, ou se o banco de dados evoluir para **PostgreSQL**, o *Core* do sistema permanecerá **intocado**. Apenas criaremos novos *Adapters*.

### 2. Facilidade de Testes (Testabilidade)
Como as regras de auditoria do pedido não possuem dependência direta de conexões de rede, filas da AWS ou drivers de banco de dados, conseguimos realizar testes unitários robustos e extremamente rápidos na camada `Application` e `Core`, utilizando dublês de teste (*Mocks/Stubs*) para as Portas de saída (`Ports`).

### 3. Adaptação ao Fluxo de Eventos (Asynchrony & DLQ)
O sistema foi desenhado para reagir a falhas de integração no fluxo de pedidos. O desacoplamento fornecido pelas **Inbound Ports** (Portas de Entrada) permite que o listener da fila (`DlqConsumerSqsAdapter`) atue apenas como um tradutor do estímulo externo. Ele transforma o evento bruto no formato que o nosso domínio entende, garantindo que flutuações e particularidades do SQS não corrompam a consistência do nosso modelo de dados.

### 4. Sustentabilidade e Manutenção do Código
Separar o projeto em `Application` (Casos de Uso), `Core` (Domínio) e `Infrastructure` (Adapters) impede o surgimento de "código espaguete". O fluxo de dependência aponta sempre para dentro (o isolamento do domínio), facilitando a integração de novos desenvolvedores ao projeto e diminuindo drasticamente a chance de efeitos colaterais ao corrigir bugs de persistência ou de mensageria.
