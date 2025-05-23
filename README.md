#  Spotifei - Projeto

**Autor:** Rafael Levi 
**Ra:** 22.124.057-5

---

## 1. Introdução

O projeto Spotifei é uma plataforma de informações de áudios digitais,  apenas compartilhando informações sobre músicas. Criado em Java com acesso a banco de dados e interface gráfica. 

---
## 2. Funcionalidades Implementadas

### 2.1. Cadastro e Autenticação
- **Cadastrar novo usuário** no banco.
- **Login de usuário** com verificação de email/senha.

### 2.2. Buscar Músicas 
- Buscar por **nome**, **artista** ou **gênero**.
- **Curtidas e Descurtidas**
- Exibe informações detalhadas como nome da música, Artista, Gênero e Descrição.
  
### 2.3. Histórico de Músicas
#### Armazenamento Cíclico (10 posições)
- Guarda a música na próxima posição disponível de `m1` a `m10`.
- Atualiza a coluna `ultima` com o índice mais recente.
- Retorna lista das últimas 10 músicas ordenadas da mais recente para a mais antiga.
- Integrado com `JList` e `DefaultListModel`.

### 2.3. Curtidas e Descurtidas
- **Curtir música:** salva entrada em `curtidas` com `"like" = 1`.
- **Descurtir música:** salva com `deslike = 1`.
- **Anbas quando usadas desmarcão a outra.

### 2.4. Gerenciamento de Playlists
#### Métodos:
- **Criar**, **editar nome**, **excluir** playlists.
- Lista todas as playlists com suas músicas.
- **Adicionar/Remover músicas** de playlists específicas.

---

## 3. Estrutura do Banco de Dados
### Tabela `usuario`
| Coluna        | Tipo     | Descrição                       |
|---------------|----------|---------------------------------|
| nome          | VARCHAR  | Nome da Usuario                 |
| email         | VARCHAR  | Emeil do Usuario                |
| senha         | VARCHAR  | Senha do Usuario                |

### Tabela `musica`
| Coluna        | Tipo     | Descrição                       |
|---------------|----------|---------------------------------|
| nomemusica          | VARCHAR  | Nome da Músicas                 |
| artista         | VARCHAR  | Conpositor da Músicas                |
| genero         | VARCHAR  | genero da Músicas                |
| descrição         | VARCHAR  | descrição da Músicas                |

### Tabela `ultimasmusicas`
| Coluna  | Tipo     | Descrição                         |
|---------|----------|-----------------------------------|
| email   | VARCHAR  | Chave primária                    |
| ultima  | INT      | Índice da última música inserida  |
| m1–m10  | VARCHAR  | Últimas 10 músicas reproduzidas   |

### Tabela `curtidas`
| Coluna    | Tipo     | Descrição                      |
|-----------|----------|--------------------------------|
| email     | VARCHAR  | Identificador do usuário       |
| nomemusica| VARCHAR  | Nome da música                 |
| like      | BOOLEAN  | Marcada como curtida           |
| deslike   | BOOLEAN  | Marcada como descurtida        |

### Tabela `playlist`
| Coluna        | Tipo     | Descrição                       |
|---------------|----------|---------------------------------|
| email         | VARCHAR  | Dono da playlist                |
| nomeplaylist  | VARCHAR  | Nome da playlist                |
| musica        | VARCHAR  | Música associada à playlist     |

---

## 4. Conclusão

Este sistema permite:
- ✔ Cadastrar e autenticar usuários
- ✔ Buscar e visualizar informações de músicas
- ✔ Curtir/descurtir músicas
- ✔ Criar e gerenciar playlists
- ✔ Acompanhar histórico das últimas 10 músicas


