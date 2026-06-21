# Projeto Final - Aplicativo Lista de Tarefas (To-Do List) 📱

[cite_start]Este repositório contém o código completo do projeto final desenvolvido para o módulo intemediário do curso de Desenvolvimento Android fornecido pelo Programa **Capacita iRede**[cite: 55]. [cite_start]O aplicativo consiste em um gerenciador de tarefas diárias funcional e estruturado com práticas modernas de desenvolvimento nativo[cite: 57, 58].

---

## 👤 Dados do Aluno e Entrega
* [cite_start]**Nome do Aluno:** Maria Beatriz Vitorino Almeida
* [cite_start]**Matrícula:** a definir
* [cite_start]**Data de Entrega:** 21 de Junho de 2026

---

## 📝 Justificativa da Escolha do Tema
A escolha do tema **To-Do List** foi feita para florecer à ideia que introduzi no projeto final(que era a criação de um protótipo de telas) do módulo básico do curso de desenvolvimento Android do **Capacita iREDE**. [cite_start]Minha intenção foi tirar algumas características do protótipo inicial do papel e aplicá-las em um aplicativo real, empregando os conceitos de arquitetura, banco de dados e persistência local[cite: 55]. Pretendo continuar evoluindo e adicionando novos recursos a este aplicativo durante a minha jornada de estudos até que por fim, ele esteja o mais próximo possível do ideal que criei para ele.

---

## 🎨 Design e Identidade Visual
O design e a interface do aplicativo foram cuidadosamente guiados para manter ao máximo a lógica e a paleta de cores do protótipo desenvolvido no módulo básico do curso, garantindo uma identidade visual consistente, limpa e agradável para o usuário (principalmente para aqueles que gostam da cor rosa >.<).

---

## 🛠️ Tecnologias e Arquitetura Utilizadas
[cite_start]O projeto atende a todos os requisitos técnicos obrigatórios exigidos para o desafio[cite: 67]:
* [cite_start]**Linguagem:** Kotlin (Desenvolvimento Nativo) [cite: 67]
* [cite_start]**Interface Gráfica (UI):** Jetpack Compose para a construção de telas dinâmicas e reativas[cite: 67].
* [cite_start]**Persistência de Dados:** Banco de dados local com Room Database, garantindo que as tarefas fiquem salvas mesmo após fechar o app[cite: 67].
* [cite_start]**Arquitetura:** MVVM (Model-View-ViewModel) para garantir a separação adequada de responsabilidades entre a interface, a lógica de negócios e a camada de dados[cite: 67].

### 📚 Utilização de mais algumas Tecnologias
Para elevar um pouco mais o nível técnico do aplicativo, foram incorporadas as seguintes tecnologias estruturais:
* **Injeção de Dependência com Hilt:** Utilizada para gerenciar automaticamente as instâncias e o ciclo de vida dos componentes do app (como banco de dados e repositórios), tornando o código mais limpo e modular.
* **Gerenciamento Assíncrono com Coroutines:** Implementado para a execução de operações assíncronas em segundo plano, garantindo que tarefas pesadas de leitura e escrita no banco de dados local não causem lentidão ou travamentos na interface do usuário.
* [Conteúdo de Apoio](https://youtube.com/playlist?list=PLPs3nlHFeKTp6tQwIuUoC248VL2aDwbqW&si=dM7gQmFaYOXOYkT0)

---

## 📱 Descrição do Funcionamento do Aplicativo
[cite_start]O aplicativo conta com uma navegação fluida composta por duas telas principais[cite: 67]:

1. [cite_start]**Tela de Listagem (ListScreen):** Exibe de forma organizada todas as tarefas que o usuário cadastrou[cite: 63]. Conta com um botão flutuante de ação para o direcionamento de novos cadastros.
2. [cite_start]**Tela de Cadastro/Detalhes (AddEditScreen):** Contém campos de entrada de texto intuitivos para a digitação do título e da descrição da tarefa[cite: 63]. [cite_start]Ao clicar no botão salvar, as informações são imediatamente validadas e persistidas no banco de dados[cite: 63].

---

## 🔮 Observações e Planos Futuros relevantes
Como parte do meu plano de upgrades contínuos para o app, tenho como metas futuras de implementação:
* **Customização de Temas (Skins):** Permitir que o usuário escolha diferentes temas visuais para o aplicativo de acordo com seu gosto pessoal (como uma interface baseada em estéticas específicas, exemplo: um tema mais fofo ou temas com designs mais radicais e alternativos).
* **Categorização de tarefas** por nível de prioridade ou tags personalizadas.