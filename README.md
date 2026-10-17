# Sistema Shopping

Sistema de gerenciamento de um shopping center em Java, com cadastro de
shoppings, lojas, clientes, veículos e estacionamentos, controle de compras
e um processo completo de visita: estacionar -> comprar -> pagar -> encerrar.

## 👥 Integrantes                                                           

- Anne Gracie: tecnologias externas, funcionalidades, classe shopping e fluxo compra
- Heitor Sena: classe compra e venda, produto                    
- Emanuelly Soares: classes cliente, loja e menu                            
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

- Cadastro do Shopping
- Cadastro de Loja
- Cadastro de Cliente
- Cadastro de Carro
- Cadastro de Estacionamento
- Listagem geral de todos os cadastros
- **Processo completo** (etapa 5): estacionar -> comprar (ou não) -> pagar compra (se houver) + estacionamento-> encerrar, com resumo final do total gasto pelo cliente

## 🏛️ Principais classes

- Shopping       
- Loja           
- Cliente        
- CompraEVenda        
- Produto         
- Estacionamento 
- Carro          
- FluxoCompra    
- Main
- AppTeste       

## 📋 Regras de negócio

- Venda não ocorre quando não existe estoque
- Cálculo da pontuação de acordo com a compra
- Cálculo de desconto definido a partir de 500 reais
- Verificação da disponibilidade do produto
- Cliente paga estacionamento mesmo sem compra

## 🛠️ Tecnologias utilizadas

- **Java** (JDK 21)
- **Maven** (build e gerenciamento de dependências)
- **Gson** (serialização JSON — preparado para importação/exportação de dados)
- **JUnit** (testes)
- **Git / GitHub** (versionamento)
