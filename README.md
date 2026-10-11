# Sistema Shopping

Sistema de gerenciamento de um shopping center em Java, com cadastro de
shoppings, lojas, clientes, veículos e estacionamentos, controle de compras
e um processo completo de visita: estacionar -> comprar -> pagar -> encerrar.

## 👥 Integrantes                                                           |

- Anne Gracie: tecnologias externas, organização, funcionalidades, classe shopping 
- Heitor Sena: classe compra, venda, produto e item_transação                      
- Emanuelly Soares: classes cliente, loja e menu interativo                            
- Nicole Simões: classes estacionamento e carro                                      


## 📝 Descrição do problema

Gerenciar a operação de um shopping center envolve controlar várias entidades
que se relacionam entre si: os shoppings e suas lojas, os clientes que     as
visitam, os veículos que ocupam vagas de estacionamento e as          compras
realizadas. Fazer esse controle manualmente (em papel ou planilhas) dificulta
saber quem está no shopping, quais vagas estão ocupadas e o total gasto   por
cada cliente em uma visita.

Este sistema resolve o problema oferecendo cadastro, listagem e um   processo
completo de visita ao shopping pelo terminal.

## ⚙️ Funcionalidades

- Cadastro de Shopping
- Cadastro de Loja
- Cadastro de Cliente
- Cadastro de Carro
- Cadastro de Estacionamento
- Listagem geral de todos os cadastros
- **Processo completo** (etapa 5): estacionar -> comprar -> pagar -> encerrar,
  com resumo final do total gasto pelo cliente

## 🏛️ Principais classes

- Shopping       
- Loja           
- Cliente        
- Compra         
- Venda          
- Produto        
- ItemTransação  
- Estacionamento 
- Carro          
- FluxoCompra    
- Main           

## 📋 Regras de negócio

-
-
-
-
-


## 🛠️ Tecnologias utilizadas

- **Java** (JDK 21)
- **Maven** (build e gerenciamento de dependências)
- **Gson** (serialização JSON — preparado para importação/exportação de dados)
- **JUnit** (testes)
- **Git / GitHub** (versionamento)

## 🔧 Exemplos de utilização

1. Cadastrar um shopping (opção 1):

Nome do shopping: Village Mall
CNPJ: 12.345.678/0001-90
Quantidade de lojas: 200
Shopping "Village Mall" cadastrado com sucesso!
2. Listar tudo (opção 6):

===== RELATÓRIO GERAL =====
-- Shoppings (1) --
Shopping{nome='Village Mall', cnpj='12.345.678/0001-90'}
-- Lojas (2) --
Loja{nome='Livraria Sol', piso='Piso 2'}
...
3. Processo completo (opção 7):

===== PROCESSO COMPLETO =====
Escolha o estacionamento:
1 - Estacionamento{vagas=100}
Número: 1
Carro ABC1D23 estacionado. Vagas livres: 99
Escolha o cliente:
1 - Cliente{nome='Maria', cpf='123.456.789-00'}
Número: 1
Valor da compra: R$ 150.00
Compra paga com sucesso!
Saída registrada. Valor da vaga: R$ 10.0

===== RESUMO DO PROCESSO =====
Cliente: Maria
Loja: Livraria Sol
Compra: R$ 150.0 (PAGA)
Estacionamento: R$ 10.0
TOTAL GASTO: R$ 160.0