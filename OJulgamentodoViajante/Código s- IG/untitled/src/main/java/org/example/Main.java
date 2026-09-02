package org.example;

import java.util.Random;
import java.util.Scanner;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

public class Main {
    public static void main(String[] args) {
        try {
            // Configura saída UTF-8 para acentos e ç
            System.setOut(new PrintStream(System.out, true, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return; // encerra se não suportar UTF-8
        }
        Scanner input = new Scanner(System.in);
        Atributos jogador = new Atributos(); // cria um objeto com os atributos do jogador
        Random rand = new Random(); // aleatoriza a ordem dos eventos

        System.out.println("O JULGAMENTO DO VIAJANTE");
        System.out.println("Seja bem-vindo a um mundo em ruínas. Você é um andarilho órfão que vaga pelas estradas decadentes da");
        System.out.println("Europa medieval, devastada por pestes, fome e guerras entre reinos exaustos. Sem família, sem lar e sem");
        System.out.println("qualquer atributo — Saúde, Honra ou Dinheiro — você sobrevive apenas pelo instinto e pela esperança perdida");
        System.out.println("Em meio ao caos, cada escolha testará sua força e sua alma. Este mundo brutal moldará não apenas seus");
        System.out.println("atributos, mas quem você realmente é… ou quem ainda pode se tornar. Caminhe com cuidado: aqui, até os mais");
        System.out.println("fortes sucumbem, e ninguém sai ileso dessa jornada.");
        System.out.println("");
        System.out.println("INSTRUÇÕES DO JOGO");
        System.out.println("O jogo apresentará um evento por vez, e você deverá escolher um entre três caminhos.");
        System.out.println("Cada decisão altera seus atributos — Saúde, Honra e Dinheiro — de forma independente.");
        System.out.println("Se qualquer um deles chegar a 0, sua jornada acaba. Se algum atingir 100, você conclui sua história.");
        System.out.println("Seus atributos iniciais são: ");
        jogador.exibir();
        System.out.println("________________________");
        System.out.println("");

        int escolhaMenu = 0;

        while (true) {
            System.out.println("====== MENU INICIAL ======");
            System.out.println("1 - Iniciar Jogo");
            System.out.println("2 - Ranking");
            System.out.println("3 - Sair");
            System.out.print("Escolha: ");
            escolhaMenu = input.nextInt();

            if (escolhaMenu == 1) {
                // SAI DO MENU E SEGUE PARA O JOGO
                break;
            } else if (escolhaMenu == 2) {
                // Mostrar ranking real
                RankingDAO.exibirRanking();
            } else if (escolhaMenu == 3) {
                System.out.println("Encerrando o jogo...");
                return; // fecha o main e encerra tudo
            } else {
                System.out.println("Opção inválida. Tente novamente.");
            }
        }
        System.out.println("==========================");

        int ultimoEvento = -1; //grava o último evento para impedir que haja uma repetição consecutiva

        // loop que roda o jogo enquanto os atributos forem válidos
        while (!jogador.gameOver()) {

            int evento;

            // sorteia um evento diferente do anterior
            do {
                evento = rand.nextInt(11) + 1; //gera um número entre e 1 e 9
            } while (evento == ultimoEvento);

            ultimoEvento = evento; // grava o evento atual

            //execução da missão, caso seja a sorteada
            switch (evento) {
                case 1: //Missão: O REFÚGIO ISOLADO
                    System.out.println("O REFÚGIO ISOLADO");
                    System.out.println("Você encontra um pequeno acampamento improvisado abandonado à beira da estrada. ");
                    System.out.println("Há suprimentos úteis visíveis: um saco de feijões secos e um frasco de vinagre. ");
                    System.out.println("No entanto, o cobertor tem manchas escuras suspeitas");
                    System.out.println("1 - Pegar apenas os feijões"); //(+10 Saúde)
                    System.out.println("2 - Pegar feijões e vinagre"); //(+15 Saúde, risco leve)
                    System.out.println("3 - Pegar tudo, incluindo o cobertor"); //(+25 Saúde, -10 Honra, risco grave)
                    System.out.print("Escolha: ");

                    int m1 = input.nextInt(); //faz a leitura da escolha do jogador

                    //faz a modificação nos atributos de acordo com a escolha do jogador
                    if (m1 == 1) {
                        System.out.println("Você pega só o necessário e segue em frente. +10 Saúde.");
                        jogador.addSaude(10);
                    } else if (m1 == 2) {
                        System.out.println("Você arrisca um pouco, mas valeu a pena. +15 Saúde.");
                        jogador.addSaude(15);
                        if (Math.random() < 0.20) {
                            System.out.println("Você foi contaminado pela Peste! -30 Saúde."); //(-30 Saúde)
                            jogador.addSaude(-30);
                        }
                    } else if (m1 == 3) {
                        System.out.println("A ganância traz força e sombras. +25 Saúde, -10 Honra.");
                        jogador.addSaude(25);
                        jogador.addHonra(-10);
                        if (Math.random() < 0.40) {
                            System.out.println("O cobertor estava infectado... Você contraiu a Peste! -30 Saúde."); //(-30 Saúde)
                            jogador.addSaude(-30);
                        }
                    } else {
                        System.out.println("Escolha inválida.");
                    }
                    break;

                case 2://Missão: A VENDA DESESPERADA
                    System.out.println("A VENDA DESESPERADA");
                    System.out.println("Você chega a uma vila, onde um jovem mercador, visivelmente tenso, está vendendo");
                    System.out.println("o último saco de grãos. Ele está pedindo um preço absurdamente alto. Você não tem");
                    System.out.println("dinheiro suficiente.");
                    System.out.println("1 - Tentar roubar o saco"); //(-5 Honra)
                    System.out.println("2 - Implorar por preço menor"); //(pode aceitar ou não)
                    System.out.println("3 - Trabalhar em troca da comida"); //(-5 Saúde, depois +15 Saúde e +5 Dinheiro)
                    System.out.print("Escolha: ");

                    int m2 = input.nextInt();

                    if (m2 == 1) {
                        jogador.addHonra(-5);
                        System.out.println("Você tenta roubar, mas o mercador chama ajuda!");

                        System.out.println("1 - Largar o saco de grãos e fugir"); //(-10 Honra)
                        System.out.println("2 - Lutar com o mercador e os aldeões"); //(+15 Dinheiro, -10 Saúde por ferimentos, -20 Honra)
                        System.out.println("3 - Jogar os grãos no chão e escapar"); //(-15 Honra, +10 Dinheiro)
                        System.out.print("Escolha: ");

                        int cont = input.nextInt();

                        if (cont == 1) {
                            System.out.println("Você foge envergonhado, deixando tudo para trás. –10 Honra.");
                            jogador.addHonra(-10);
                        } else if (cont == 2) {
                            System.out.println("Você vence, mas sai ferido e manchado. +15 Dinheiro, –10 Saúde, –20 Honra.");
                            jogador.addDinheiro(15);
                            jogador.addSaude(-10);
                            jogador.addHonra(-20);
                        } else if (cont == 3) {
                            System.out.println("Você cria confusão e foge com parte do saque. +10 Dinheiro, –15 Honra.");
                            jogador.addHonra(-15);
                            jogador.addDinheiro(10);
                        } else {
                            System.out.println("Escolha inválida.");
                        }

                    } else if (m2 == 2) {
                        if (Math.random() < 0.5) {
                            System.out.println("Sua humildade o convence. Você paga menos e sai nutrido. –10 Dinheiro, +10 Saúde, +5 Honra.");
                            jogador.addSaude(15);
                            jogador.addHonra(5);
                            jogador.addDinheiro(-10);
                        } else {
                            System.out.println("Ele recusa. Você perde tempo.");
                        }

                    } else if (m2 == 3) {
                        System.out.println("Você trabalha até a exaustão, mas é recompensado com comida e algumas moedas. +15 Saúde, +5 Dinheiro, +5 Honra.");
                        jogador.addHonra(5);
                        jogador.addSaude(15);
                        jogador.addDinheiro(5);
                        System.out.println("Você trabalha duro e recebe comida e algumas moedas.");
                    } else {
                        System.out.println("Escolha inválida.");
                    }
                    break;

                case 3:// Missão: A CASA DA ENCRUZILHADA
                    System.out.println("A CASA DA ENCRUZILHADA");
                    System.out.println("Você caminha por uma estrada coberta de névoa. O cheiro de fumaça e podridão se");
                    System.out.println("mistura ao vento. No meio da estrada, há uma pequena casa, com uma cruz de madeira");
                    System.out.println(" torta fincada na porta. A fumaça que sai da chaminé indica que alguém ainda vive ali");
                    System.out.println("vive ali. Ao se aproximar, uma voz fraca chama de dentro: ");
                    System.out.println("- Por favor... entre.. preciso de ajuda...");
                    System.out.println("Você empurra a porta e encontra um velho deitado numa cama, tossindo sangue.");
                    System.out.println("Ao lado dele, uma mesa com um prato de sopa fria e um saco de moedas. Ele o encara com os olhos cansados:");
                    System.out.println("- A peste... está me levando... preciso que leve este dinheiro ao padre da vila... ele saberá o que fazer.");
                    System.out.println("Você olha ao redor. A casa é simples, mas o saco de moedas é pesado.");
                    System.out.println("1 - Cumprir o pedido e levar o dinheiro a padre"); //(+10 Honra, -5 Saúde)
                    System.out.println("2 - Roubar o dinheiro e fugir"); //(+15 Dinheiro, -10 Honra)
                    System.out.println("3 - Mentir para o velho e deixá-lo morrer"); //(-15 Honra)
                    System.out.print("Escolha: ");

                    int m3 = input.nextInt();

                    if (m3 == 1) {
                        System.out.println("Você honra o último desejo do velho, mesmo respirando morte. +10 Honra, –5 Saúde.");
                        jogador.addHonra(10);
                        jogador.addSaude(-5);
                    } else if (m3 == 2) {
                        System.out.println("Você foge com o ouro, deixando a promessa para morrer com ele. +15 Dinheiro, –10 Honra.");
                        jogador.addDinheiro(15);
                        jogador.addHonra(-10);
                    } else if (m3 == 3) {
                        System.out.println("Você oferece palavras vazias e vira as costas. A mentira pesa mais que a peste. –15 Honra.");
                        jogador.addHonra(-15);
                    } else {
                        System.out.println("Escolha inválida.");
                    }
                    break;


                case 4:// Missão: A MÃE E A FEBRE
                    System.out.println("A MÃE E A FEBRE");
                    System.out.println("O sol começa a cair no horizonte quando o viajante ouve um choro abafado vindo da estrada.");
                    System.out.println("Entre as pedras, uma mulher segura um menino pequeno nos braços. O garoto sua, treme e respira");
                    System.out.println("com dificuldade. O cheiro da doença é forte, febre alta, talvez peste. A mulher se dirige à você:");
                    System.out.println("— Por favor... ele está morrendo! Me ajude... por misericórdia!");
                    System.out.println("Você sente o peso da escolha. Se se aproximar demais, pode se contaminar. Mas se virar as costas,");
                    System.out.println("talvez nunca mais durma em paz.");
                    System.out.println("1 - Ajudar a mulher a curar seu filho"); //(+10 Honra, -5 Saúde)
                    System.out.println("2 - Ignorar e seguir sua viagem"); //(-5 Honra)
                    System.out.println("3 - Roubar o alimento dela e fugir"); //(+10 Dinheiro, -20 Honra)
                    System.out.print("Escolha: ");

                    int m4 = input.nextInt();

                    if (m4 == 1) {
                        System.out.println("Você enfrenta a febre para salvar o menino. A coragem custa caro. +10 Honra, –5 Saúde.");
                        jogador.addHonra(10);
                        jogador.addSaude(-5);
                    } else if (m4 == 2) {
                        System.out.println("Você vira o rosto e deixa a dor para trás. O silêncio pesa. –10 Honra.");
                        jogador.addHonra(-10);
                    } else if (m4 == 3) {
                        System.out.println("Você arranca o pão de quem já sofre. A sombra dessa escolha o acompanha. +10 Dinheiro, –20 Honra.");
                        jogador.addDinheiro(10);
                        jogador.addHonra(-20);
                    } else {
                        System.out.println("Escolha inválida.");
                    }
                    break;

                case 5: // Missão: O VELHO FERREIRO
                    System.out.println("O VELHO FERREIRO");
                    System.out.println("Você encontra um velho ferreiro em sua oficina, coberta de ferrugem e cinzas. Ele parece exausto e");
                    System.out.println("precisa terminar uma encomenda importante, mas não tem energia suficiente para concluir.");
                    System.out.println("O cheiro de metal e madeira queimada envolve o local.");
                    System.out.println("1 - Ajudar o ferreiro a terminar a encomenda"); //(-5 Saúde, +10 Honra)
                    System.out.println("2 - Dar-lhe suprimentos para continuar sozinho"); //(-10 Dinheiro, +5 Honra)
                    System.out.println("3 - Ignorar e seguir viagem"); //(-5 Honra)
                    System.out.print("Escolha: ");

                    int m5 = input.nextInt();

                    if (m5 == 1) {
                        System.out.println("Você ajuda o ferreiro com paciência e dedicação. Ele sorri grato. –5 Saúde, +10 Honra.");
                        jogador.addSaude(-5);
                        jogador.addHonra(10);
                    } else if (m5 == 2) {
                        System.out.println("Você entrega suprimentos ao ferreiro. Ele consegue trabalhar sozinho, mas sem sua ajuda. –10 Dinheiro, +5 Honra.");
                        jogador.addDinheiro(-10);
                        jogador.addHonra(5);
                    } else if (m5 == 3) {
                        System.out.println("Você segue viagem, deixando o ferreiro enfrentar o trabalho sozinho. –5 Honra.");
                        jogador.addHonra(-5);
                    } else {
                        System.out.println("Escolha inválida.");
                    }
                    break;


                case 6:// Missão: O CAVALEIRO E A MOEDA MISTERIOSA
                    System.out.println("O CAVALEIRO E A MOEDA MISTERIOSA");
                    System.out.println("Enquanto atravessa um trecho de floresta, um cavaleiro cansado surge entre as árvores. ");
                    System.out.println("Ele joga uma moeda antiga aos seus pés e diz que encontrou o objeto perto de vítimas desaparecidas.");
                    System.out.println("Ele explica que investiga uma série de sumiços e precisa de ajuda em sua busca.");
                    System.out.println("1 - Recusar a ajudar o cavaleiro"); //(-10 Honra)
                    System.out.println("2 - Aceitar a moeda e infomar o cavaleiro"); //(+ 10 Honra, +5 Dinheiro)
                    System.out.println("3 - Pedir mais dinheiro"); //(+10 Dinheiro, -10 Honra)
                    System.out.print("Escolha: ");

                    int m6 = input.nextInt();

                    if (m6 == 1) {
                        System.out.println("Você se afasta da investigação, deixando o cavaleiro sozinho na escuridão. –10 Honra.");
                        jogador.addHonra(-10);
                    } else if (m6 == 2) {
                        System.out.println("Você pega a moeda, compartilha o que sabe e fortalece a busca. +10 Honra, +5 Dinheiro.");
                        jogador.addHonra(10);
                        jogador.addDinheiro(5);
                    } else if (m6 == 3) {
                        System.out.println("O cavaleiro hesita, mas paga. Sua ganância pesa no ar. +10 Dinheiro, –10 Honra.");
                        jogador.addHonra(-10);
                        jogador.addDinheiro(10);
                    } else {
                        System.out.println("Escolha inválida.");
                    }
                    break;

                case 7: // Missão: O MENDIGO CONTAGIADO
                    System.out.println("O MENDIGO CONTAGIADO");
                    System.out.println("Sentado à beira de uma ponte, um mendigo coberto por feridas te chama com a mão trêmula.");
                    System.out.println("A tosse dele é pesada, e manchas negras espalham-se por sua pele.");
                    System.out.println("Mesmo fraco, ele tenta te entregar um pequeno anel de ferro.");
                    System.out.println("\"Por favor... só quero um pouco de água...\"");
                    System.out.println("Ajudá-lo pode te expor à peste, mas ignorá-lo pode manchar sua honra.");
                    System.out.println("1 - Dar água ao mendigo"); // (+10 Honra, -5 Saúde, 30% risco de infecção)
                    System.out.println("2 - Ignorar e seguir caminho"); // (-5 Honra)
                    System.out.println("3 - Pegar o anel e fugir"); // (+10 Dinheiro, -10 Honra)
                    System.out.print("Escolha: ");

                    int m7 = input.nextInt();

                    if (m7 == 1) {
                        System.out.println("Você oferece água ao moribundo, mesmo arriscando a própria vida. A gratidão ");
                        System.out.println("dele é silenciosa. +10 Honra, –5 Saúde.");
                        jogador.addHonra(10);
                        jogador.addSaude(-5);

                        if (Math.random() < 0.30) {
                            System.out.println("Você foi exposto à peste ao tocar no mendigo. -30 Saúde ");
                            jogador.addSaude(-30);
                        }

                    } else if (m7 == 2) {
                        System.out.println("Você desvia o olhar e segue adiante, tentando abafar o peso da culpa. –10 Honra.");
                        jogador.addHonra(-10);

                    } else if (m7 == 3) {
                        System.out.println("Você arranca o anel das mãos dele e parte antes que ele reaja. A riqueza vem, ");
                        System.out.println("mas a vergonha também. +10 Dinheiro, –10 Honra.");
                        jogador.addDinheiro(10);
                        jogador.addHonra(-10);

                    } else {
                        System.out.println("Escolha inválida.");
                    }
                    break;

                case 8: // Missão: A MOÇA FAMINTA
                    System.out.println("A MOÇA FAMINTA");
                    System.out.println("O vento frio corta a estrada vazia enquanto você avança com passos cansados.");
                    System.out.println("À beira do caminho, quase escondida atrás de uma carroça quebrada, uma jovem caída no chão");
                    System.out.println("ergue o rosto quando percebe sua presença. As roupas estão rasgadas, a pele pálida e os olhos");
                    System.out.println("fundos mostram uma fome prolongada.");
                    System.out.println("— Por favor... — ela diz com a voz fraca — não como há dias. Qualquer coisa... qualquer ajuda...");
                    System.out.println("O som do estômago dela roncando ecoa mais alto do que o vento. Ela não tem forças para ficar de pé.");
                    System.out.println("Você sente o peso da decisão: suas próprias reservas não são grandes, mas deixá-la assim parece cruel.");
                    System.out.println("1 - Dar comida a ela");      // (-5 Dinheiro, +5 Honra)
                    System.out.println("2 - Ignorar e seguir viagem"); // (-10 Honra)
                    System.out.println("3 - Ajudá-la a chegar à vila próxima"); // (-5 Saúde, +5 Honra)
                    System.out.print("Escolha: ");

                    int m8 = input.nextInt();

                    if (m8 == 1) {
                        System.out.println("Você entrega sua comida. Ela agradece com os olhos cheios d’água. +5 Honra, -5 Dinheiro.");
                        jogador.addDinheiro(-5);
                        jogador.addHonra(5);

                    } else if (m8 == 2) {
                        System.out.println("Você passa por ela sem olhar para trás. A culpa permanece. -10 Honra.");
                        jogador.addHonra(-10);

                    } else if (m8 == 3) {
                        System.out.println("Você a leva até a vila, gastando suas forças no caminho. +5 Honra, -5 Saúde.");
                        jogador.addSaude(-5);
                        jogador.addHonra(5);

                    } else {
                        System.out.println("Escolha inválida.");
                    }
                    break;

                case 9: // Missão: O Fogo da Purificação
                    System.out.println("O FOGO DA PURIFICAÇÃO");
                    System.out.println("Ao se aproximar de uma pequena vila, você percebe o cheiro de fumaça e ouve gritos que ecoam pelas casas.");
                    System.out.println("Uma multidão tomada pelo medo e pela fúria se reúne diante da igreja local.");
                    System.out.println("Flagelantes, com as costas marcadas por chicotes, erguem símbolos religiosos enquanto aldeões apavorados");
                    System.out.println("cercam um pequeno grupo de acusados: uma curandeira, uma família judia e alguns imigrantes.");
                    System.out.println("Eles tremem enquanto os fanáticos gritam que são culpados por espalhar a Peste.");
                    System.out.println("O Sacerdote da vila observa a cena da porta da igreja — nervoso, hesitante, quase impotente.");
                    System.out.println("Ao notar sua presença, o líder dos fanáticos aponta seu chicote para você:");
                    System.out.println("Estranho! Está conosco ou contra Deus? Ajude-nos a purificar este lugar!");
                    System.out.println("1 - Ajudar os fanáticos");
                    System.out.println("2 - Defender os acusados");
                    System.out.println("3 - Falar com o Sacerdote");
                    System.out.print("Escolha: ");

                    int m9 = input.nextInt();

                    if (m9 == 1) {
                        System.out.println("Você se junta à multidão enfurecida. Os fanáticos te recebem com gritos de aprovação.");
                        System.out.println("O Sacerdote observa com medo — ou resignação.");
                        System.out.println("1 - Liderar o ataque contra os acusados");
                        System.out.println("2 - Usar o caos para saquear as casas deles");
                        System.out.print("Escolha: ");

                        int sub1 = input.nextInt();

                        if (sub1 == 1) {
                            System.out.println("Você empunha uma arma improvisada e avança. Os acusados quase não resistem.");
                            System.out.println("A multidão comemora enquanto o grupo é massacrado.");
                            System.out.println("O Sacerdote, pálido, o chama para dentro da igreja, oferecendo abrigo. -15 Honra, -5 Saúde.");
                            jogador.addHonra(-15);
                            jogador.addSaude(-5); // desgaste da violência
                        } else if (sub1 == 2) {
                            System.out.println("Enquanto o caos toma conta da vila, você invade a casa dos acusados.");
                            System.out.println("Enche seus bolsos com tudo de valor antes que as chamas consumam o resto. +20 Dinheiro, -20 Honra");
                            jogador.addDinheiro(20);
                            jogador.addHonra(-20);
                        } else {
                            System.out.println("Escolha inválida.");
                        }

                    } else if (m9 == 2) {

                        System.out.println("Você ergue a voz contra a multidão:");
                        System.out.println("- Eles são inocentes! ");
                        System.out.println("A doença atinge a todos!");
                        System.out.println("Um silêncio tenso surge até que um aldeão grita:");
                        System.out.println("- HEREGE! Ele os defende!");
                        System.out.println("Em segundos, todos se viram contra você.");
                        System.out.println("1 - Enfrentar a multidão");
                        System.out.println("2 - Tentar dialogar");
                        System.out.print("Escolha: ");
                        int sub2 = input.nextInt();

                        if (sub2 == 1) {
                            System.out.println("Você tenta lutar, mas são dezenas contra um.");
                            System.out.println("Você cai sob golpes, chutes e pedras e não resiste. -100 Saúde");
                            jogador.addSaude(-100); // força game over
                        } else if (sub2 == 2) {
                            System.out.println("Você tenta apelar à razão, mas o medo supera qualquer lógica.");
                            if (Math.random() < 0.15) {
                                System.out.println("Milagrosamente, parte da multidão hesita. A violência se dispersa, mas os acusados fogem.");
                                System.out.println("Você sobrevive, mas o caos continua. +5 Honra");
                                jogador.addHonra(5);
                            } else {
                                System.out.println("A multidão enlouquecida não quer ouvir a verdade.");
                                System.out.println("Você é derrubado, espancado e não resiste. -100 Saúde");
                                jogador.addSaude(-100);
                            }
                        } else {
                            System.out.println("Escolha inválida.");
                        }

                    } else if (m9 == 3) {
                        System.out.println("Você se aproxima da igreja e o Sacerdote imediatamente o puxa para dentro.");
                        System.out.println("Ele respira fundo, trêmulo:");
                        System.out.println("- Eles estão fora de controle… eu não consigo detê-los. Se quiser, pode fugir, não é desonroso");
                        System.out.println("1 - Ajudar o Sacerdote a proteger a igreja");
                        System.out.println("2 - Aceitar o conselho dele e fugir pelos fundos");
                        System.out.print("Escolha: ");
                        int sub3 = input.nextInt();

                        if (sub3 == 1) {
                            System.out.println("Você tranca as portas com o Sacerdote enquanto a multidão enlouquecida massacra os acusados.");
                            System.out.println("Ele, em troca, oferece abrigo, água e comida. -5 Honra, +5 Saúde");
                            jogador.addHonra(-5);
                            jogador.addSaude(+5);
                        } else if (sub3 == 2) {
                            System.out.println("O Sacerdote abre uma porta secreta e sussurra para você fugir.");
                            System.out.println("Você escapa da vila enquanto o caos continua. -10 Honra.");
                            jogador.addHonra(-10);
                        } else {
                            System.out.println("Escolha inválida.");
                        }

                    } else {
                        System.out.println("Escolha inválida.");
                    }
                    break;


                case 10:
                    System.out.println("O LACRE DA PESTE ");
                    System.out.println("Você caminha por uma rua devastada. Casas vazias, silêncio, portas abertas... ");
                    System.out.println("Mas uma chama a atenção: a porta e as janelas estão pregadas por fora, ");
                    System.out.println("e uma enorme CRUZ VERMELHA foi pintada na madeira.");
                    System.out.println("É uma casa de quarentena. Uma família foi trancada aqui dentro para morrer de peste.");
                    System.out.println("Sabe-se de duas coisas: o lugar é mortalmente infeccioso…");
                    System.out.println("…mas também está cheio dos bens da família, intocados por todos até agora.");
                    System.out.println("1 - Não vale o risco.");
                    System.out.println("2 - Mortos não precisam de dinheiro.");
                    System.out.print("Escolha: ");

                    int m10 = input.nextInt();

                    if (m10 == 1) {
                        System.out.println("Você decide preservar sua vida. Você segue seu caminho, respirando fundo. Nenhum atributo foi alterado.");
                    } else if (m10 == 2) {
                        System.out.println("Você força uma janela lateral e entra.");
                        System.out.println("O ar é pesado... úmido... fétido. A peste está por toda parte.");

                        System.out.println("Você enfrenta o risco de contaminação...");

                        if (Math.random() < 0.5) {
                            System.out.println("O destino não sorri para você. A febre e a tosse começam a surgir.");
                            System.out.println("Você foi exposto à Peste Negra. -30 Saúde");
                            jogador.addSaude(-30);
                        } else {
                            System.out.println("Você sai atordoado, mas vivo. A casa estava cheia de bens valiosos.");
                            System.out.println("+20 Dinheiro, -10 Saúde");
                            jogador.addDinheiro(20);
                            jogador.addSaude(-10);
                        }
                    } else {
                        System.out.println("Escolha inválida.");
                    }

                    break;

                case 11:
                    System.out.println("O HOMEM DA CARTOLA");
                    System.out.println("À beira de um lago, um homem de trapos e cartola toca seu ombro.");
                    System.out.println(" - Preciso de um favor... pode me ajudar?");
                    System.out.println("1 - Ignorar o pedido");
                    System.out.println("2 - Recuar e perguntar quem ele é");
                    System.out.println("3 - Confiar e ajudar imediatamente");
                    System.out.print("Escolha: ");
                    int m11 = input.nextInt();

                    if (m11 == 1) {
                        System.out.println("Você se afasta. O homem murmura:");
                        System.out.println("- Quem sabe na próxima. -5 Honra.");
                        jogador.addHonra(-5);
                    } else if (m11 == 2) {
                        System.out.println("- Não tenho nome. Alguns me chamam de Aquele que Retorna.");
                        System.out.println("Vocês conversam... mas algo aparece atrás de vocês.");
                        System.out.println("1 - Enfrentar a criatura");
                        System.out.println("2 - Correr para a floresta");
                        System.out.print("Escolha: ");
                        int subA = input.nextInt();

                        if (subA == 1) {
                            System.out.println("A criatura te ataca e você não resiste. -100 Saúde.");
                            jogador.addSaude(-100);
                        } else if (subA == 2) {
                            System.out.println("Você corre até uma cabana abandonada.Na mesa, há um papel com um enigma.");
                            System.out.println("- Não temo o silêncio... O que realmente me assombra é quando minhas ações encontram palavras.");
                            System.out.println("1 - Culpa");
                            System.out.println("2 - Medo");
                            System.out.println("3 - Consciência");
                            System.out.println("4 - Julgamento");
                            System.out.print("Escolha: ");
                            int enigma = input.nextInt();

                            if (enigma != 4) {
                                System.out.println("A porta se tranca. A cabana pega fogo e você não resiste. -100 Saúde.");
                                jogador.addSaude(-100);
                            } else {
                                System.out.println("O homem aparece e diz que você demorou a acertar.");
                                System.out.println("Ele te entrega um saco de moedas. +5 Honra, +15 Dinheiro.");
                                jogador.addDinheiro(15);
                                jogador.addHonra(5);
                            }
                        }
                    } else if (m11 == 3) {
                        System.out.println("Você aceita ajudar e seguem pela floresta. Sacos de moedas aparecem pelo chão...");
                        System.out.println("1 - Não pegar");
                        System.out.println("2 - Pegar o dinheiro");
                        System.out.print("Escolha: ");
                        int subB = input.nextInt();

                        if (subB == 1) {
                            System.out.println("Você resiste. O homem sorri. Nenhum atributo foi alterado.");
                        } else if (subB == 2) {
                            System.out.println("Você pega as moedas. +10 Dinheiro.");
                            jogador.addDinheiro(10);

                            System.out.println("Um bandido armado aparece e está furioso.");
                            System.out.println("1 - Correr");
                            System.out.println("2 - Lutar");
                            System.out.print("Escolha: ");
                            int subC = input.nextInt();

                            if (subC == 1) {
                                System.out.println("Você foge e abandona o homem. -5 Honra.");
                                jogador.addHonra(-5);
                            } else if (subC == 2) {
                                System.out.println("Você luta...");
                                if (Math.random() < 0.40) {
                                    System.out.println("Mas ele é mais forte e você não resiste. -100 Saúde");
                                    jogador.addSaude(-100);
                                } else {
                                    System.out.println("Você vence, mas se machuca. -10 Saúde, +5 Honra, +10 Dinheiro");
                                    jogador.addHonra(5);
                                    jogador.addDinheiro(10);
                                    jogador.addSaude(-10);
                                }
                            }
                        }
                    } else {
                        System.out.println("Escolha inválida.");
                    }

                    break;


                default:
                    System.out.println("Opção inválida.");
            }
            jogador.exibir(); //exibe os atributos do jogador ao final da missão
            System.out.println("\n-----------------------------------\n"); //divide as missões com um pontilhado
        }

        // ---- FIM DO JOGO ----
        System.out.println("=== FIM DE JOGO ===");
        jogador.exibir();

        String resultadoFinal = "DERROTA"; // Valor padrão

        // 1. VERIFICAÇÃO DE DERROTA (Prioridade: se morreu, morreu)
        if (jogador.getSaude() <= 0 || jogador.getHonra() <= 0 || jogador.getDinheiro() <= 0) {
            System.out.println("Um dos seus atributos chegou a zero. A jornada foi interrompida.");
            resultadoFinal = "DERROTA";
        }

        // 2. VERIFICAÇÃO DOS FINAIS DE VITÓRIA
        else if (jogador.getSaude() >= 100) {
            // CAMINHO DA RESILIÊNCIA
            System.out.println("\n=== FINAL: A LENDA IMORTAL (Saúde Máxima) ===");
            System.out.println("A peste tentou te consumir, a fome tentou te quebrar e o frio tentou te congelar.");
            System.out.println("Mas o seu corpo se tornou uma fortaleza. Você não apenas sobreviveu, você evoluiu.");
            System.out.println("Você caminha pelas estradas infectadas sem medo, pois a morte parece ter desistido de te levar.");
            System.out.println("As pessoas não te veem mais como um viajante, mas como um milagre de carne e osso.");
            System.out.println("Você venceu pela FORÇA VITAL.");
            resultadoFinal = "VITÓRIA (IMORTAL)";

        } else if (jogador.getHonra() >= 100) {
            // CAMINHO DA NOBREZA
            System.out.println("\n=== FINAL: O SANTO DOS CAMINHOS (Honra Máxima) ===");
            System.out.println("Em um mundo mergulhado em trevas, egoísmo e dor, você escolheu ser a luz.");
            System.out.println("Você passou fome para alimentar estranhos. Você arriscou a vida para salvar os doentes.");
            System.out.println("Sua jornada não termina em um trono, mas nos corações daqueles que você salvou.");
            System.out.println("Sua lenda será cantada por bardos por gerações: O Viajante que nunca corrompeu sua alma.");
            System.out.println("Você venceu pela VIRTUDE.");
            resultadoFinal = "VITÓRIA (SANTO)";

        } else if (jogador.getDinheiro() >= 100) {
            // CAMINHO DA RIQUEZA
            System.out.println("\n=== FINAL: O SENHOR DO DESTINO (Riqueza Máxima) ===");
            System.out.println("Você entendeu cedo que, neste mundo cruel, a moral é um luxo e a saúde é negociável.");
            System.out.println("Mas o Ouro... o Ouro é absoluto. Com sua fortuna acumulada, você comprou terras longe da peste.");
            System.out.println("Ergueu muralhas altas, contratou guardas e médicos particulares.");
            System.out.println("Enquanto o mundo queima lá fora, você bebe vinho em sua taça de prata.");
            System.out.println("Você venceu pela ASTÚCIA.");
            resultadoFinal = "VITÓRIA (MAGNATA)";
        }

        // Salva no banco de dados com o resultado específico
        RankingDAO.salvarResultado(resultadoFinal, jogador.getSaude(), jogador.getHonra(), jogador.getDinheiro());

        // ---- MENU FINAL ----
        System.out.println("\nO que deseja fazer agora?");
        System.out.println("1 - Voltar ao menu inicial");
        System.out.println("2 - Encerrar o jogo");
        System.out.print("Escolha: ");

        int escolhaFinal = input.nextInt();

        if (escolhaFinal == 1) {
            System.out.println("Retornando ao menu inicial...\n");
            main(null);   // reinicia o jogo
        } else {
            System.out.println("Encerrando o jogo...");
            // O return aqui fecha o programa
        }
    }
}