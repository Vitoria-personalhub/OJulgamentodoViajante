# O Julgamento do Viajante (em desenvolvimento)

Um jogo de escolhas narrativas imersivo desenvolvido em **JavaFX**, onde cada decisão determina o destino do protagonista em meio a um cenário implacável assolado por uma peste mortal.

---

## 📜 Sobre o Jogo

Em **O Julgamento do Viajante**, o jogador assume o papel de um viajante enfrentando provações severas, dilemas morais e a escassez de recursos. O objetivo é equilibrar três atributos vitais:
* ❤️ **Saúde:** Sua resistência física e imunidade nesse período.
* ⚖️ **Honra:** Seus princípios éticos e morais diante das crises.
* 💰 **Dinheiro:** Suas moedas e recursos para sobreviver ao dia a dia.

---

## 🎮 Mecânicas Principais

* **Sistema de Escolhas:** Missões sequenciais onde cada alternativa altera diretamente os atributos do personagem.
* **Finais Dinâmicos:** A partida se encerra imediatamente se algum atributo zerar (Game Over) ou se o jogador alcançar o ápice de 100 pontos em um atributo específico.
* **Trilha Sonora Dinâmica:** Imersão sonora contínua durante a jornada.
* **Histórico de Partidas (Ranking):** Os resultados e estatísticas finais são salvos automaticamente em um banco de dados **SQLite** (`ranking.db`), permitindo consultar o histórico de desempenho.

---

## 🛠️ Tecnologias Utilizadas

* **Linguagem:** Java
* **IDE:** Intellij
* **Interface Gráfica:** JavaFX
* **Banco de Dados:** SQLite (`sqlite-jdbc`)
* **Áudio:** JavaFX Media

---

## 🚀 Como Executar o Projeto

### Pré-requisitos Obrigatórios:
Para executar este projeto em sua máquina, você precisa ter instalado:
1. **Java Development Kit (JDK):** Versão 17 ou superior.
2. **IntelliJ IDEA:** IDE recomendada para o desenvolvimento e execução correta do projeto JavaFX.
3. **JavaFX SDK:** Baixado em sua máquina para configurar o ambiente gráfico (caso sua JDK não o inclua nativamente).

---

### Passo a Passo para Rodar no IntelliJ IDEA

1. **Clone o repositório:**
   ```bash
   git clone [https://github.com/seu-usuario/OJulgamentodoViajante.git](https://github.com/seu-usuario/OJulgamentodoViajante.git)
