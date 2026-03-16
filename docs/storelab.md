# Projeto de Treino -- Sistema de Gestão de Lojas

## Objetivo

Construir um sistema backend para treinar modelagem de entidades,
relacionamentos complexos e fluxos de cadastro envolvendo múltiplas
entidades relacionadas.

O sistema deve permitir o gerenciamento de **usuários**, **lojas**,
**associações entre usuários e lojas** e **convites para participação em
lojas**.

------------------------------------------------------------------------

# Escopo do Sistema

## Entidades Principais

### Usuário (User)

Representa qualquer pessoa que pode acessar o sistema.

Campos mínimos:

-   id
-   name
-   email
-   password
-   created_at

Regras:

-   Usuários podem existir sem estar associados a nenhuma loja.
-   Um usuário pode participar de várias lojas.

------------------------------------------------------------------------

### Loja (Store)

Representa um negócio dentro do sistema.

Campos mínimos:

-   id
-   name
-   cnpj
-   created_at

Regras:

-   Uma loja pode ter vários donos.
-   Uma loja pode ter vários funcionários.
-   Uma loja deve possuir **pelo menos um dono (OWNER)**.

------------------------------------------------------------------------

### Associação Usuário-Loja (StoreUser / Membership)

Representa a relação entre usuários e lojas.

Campos mínimos:

-   id
-   store_id
-   user_id
-   role
-   created_at

Roles possíveis:

-   OWNER
-   MANAGER
-   SELLER
-   CASHIER

Regras:

-   Um usuário pode ter apenas **uma função por loja**.
-   Um usuário pode participar de **várias lojas**.
-   Uma loja pode ter **vários usuários**.

Constraint recomendada:

UNIQUE (store_id, user_id)

------------------------------------------------------------------------

### Convite para Loja (StoreInvite)

Permite convidar usuários para participar de uma loja.

Campos mínimos:

-   id
-   store_id
-   email
-   role
-   token
-   status
-   expires_at
-   created_at

Status possíveis:

-   PENDING
-   ACCEPTED
-   EXPIRED
-   CANCELLED

Regras:

-   Um convite só pode ser usado uma vez.
-   Convites possuem prazo de expiração.
-   Apenas usuários autorizados podem criar convites.

------------------------------------------------------------------------

# Requisitos Funcionais

## Usuários

RF01 -- O sistema deve permitir cadastro de usuário.

RF02 -- O sistema deve permitir autenticação de usuário.

RF03 -- O sistema deve permitir listar dados do usuário autenticado.

------------------------------------------------------------------------

## Lojas

RF04 -- Usuários autenticados podem criar lojas.

RF05 -- Ao criar uma loja, o usuário criador deve se tornar OWNER da
loja.

RF06 -- Um usuário pode criar várias lojas.

RF07 -- O sistema deve permitir listar lojas de um usuário.

------------------------------------------------------------------------

## Associação Usuário-Loja

RF08 -- O sistema deve permitir associar usuários a uma loja.

RF09 -- O sistema deve permitir listar usuários de uma loja.

RF10 -- O sistema deve permitir alterar o papel (role) de um usuário na
loja.

RF11 -- O sistema deve permitir remover um usuário da loja.

RF12 -- O sistema deve impedir que uma loja fique sem OWNER.

------------------------------------------------------------------------

## Convites

RF13 -- Usuários com permissão podem convidar outros usuários para uma
loja.

RF14 -- Convites devem gerar um token único.

RF15 -- O sistema deve permitir aceitar convites.

RF16 -- Ao aceitar convite, o usuário deve ser associado à loja.

RF17 -- Convites aceitos devem mudar status para ACCEPTED.

RF18 -- Convites expirados não podem ser utilizados.

------------------------------------------------------------------------

# Regras de Negócio

RN01 -- Apenas OWNER ou MANAGER podem convidar usuários.

RN02 -- Apenas OWNER pode promover outro usuário para OWNER.

RN03 -- Apenas OWNER pode remover outro OWNER.

RN04 -- Um usuário não pode ter duas associações na mesma loja.

RN05 -- Uma loja deve sempre possuir pelo menos um OWNER.

RN06 -- Convites só podem ser utilizados uma vez.

------------------------------------------------------------------------

# Checklist de Implementação

## Modelagem

-   [ ] Criar tabela users
-   [ ] Criar tabela stores
-   [ ] Criar tabela store_users
-   [ ] Criar tabela store_invites
-   [ ] Criar chaves estrangeiras
-   [ ] Criar constraint UNIQUE (store_id, user_id)

------------------------------------------------------------------------

## Backend -- Usuários

-   [ ] Endpoint de cadastro de usuário
-   [ ] Endpoint de login/autenticação
-   [ ] Endpoint de consulta de usuário autenticado

------------------------------------------------------------------------

## Backend -- Lojas

-   [ ] Endpoint para criação de loja
-   [ ] Garantir que criador vira OWNER
-   [ ] Endpoint para listar lojas do usuário

------------------------------------------------------------------------

## Backend -- Membership

-   [ ] Endpoint para listar usuários de uma loja
-   [ ] Endpoint para adicionar usuário à loja
-   [ ] Endpoint para alterar role do usuário
-   [ ] Endpoint para remover usuário da loja
-   [ ] Validação para impedir duplicidade de membership

------------------------------------------------------------------------

## Backend -- Convites

-   [ ] Endpoint para criar convite
-   [ ] Geração de token único
-   [ ] Endpoint para aceitar convite
-   [ ] Validação de token
-   [ ] Validação de expiração do convite
-   [ ] Atualização de status do convite

------------------------------------------------------------------------

## Regras de Segurança

-   [ ] Verificar permissões por role
-   [ ] Impedir remoção do último OWNER
-   [ ] Garantir que apenas usuários autorizados criem convites

------------------------------------------------------------------------

## Testes

-   [ ] Testar criação de usuário
-   [ ] Testar criação de loja
-   [ ] Testar associação de usuário à loja
-   [ ] Testar convite
-   [ ] Testar aceitação de convite
-   [ ] Testar regras de permissão

------------------------------------------------------------------------

## Melhorias Opcionais

-   [ ] Auditoria de ações
-   [ ] Histórico de convites
-   [ ] Transferência de ownership
-   [ ] Sistema de permissões mais granular (RBAC)
