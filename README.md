# crud-java-angular

## 💻  Como rodar o projeto em um novo computador

✅ Pré-requisitos

Antes de tudo, você precisa ter instalado:

Node.js

Docker e Docker Compose

Java 17+

Maven

Angular CLI

As portas 8090 (postgres), 8091 (backend) e 4200 (frontend) são utilizadas durante o processo.



## 🚀 Instalando crud-java-angular

Para instalar o crud-java-angular, siga estas etapas:

**1. Clone o projeto:**
```bash
git clone https://github.com/carlose060/crud-java-angular.git
```
Isso irá criar uma nova pasta chamada `crud-java-angular` no seu diretório atual, com todos os arquivos do repositório.

**2. Instalar dependências e subir o projeto localmente:**

Navegue até a pasta do projeto com: 

```bash
cd crud-java-angular
```
Suba o conteiner do banco de dados:

```bash
docker-compose up -d
```

Suba o backend:

```bash
cd crud-java && mvn spring-boot:run
```
Para subir o frontend, devemos utilizar outro terminal. Volte até a pasta do projeto em `crud-java-angular` e para instalar as dependencias do frontend:

```bash
cd crud-angular && npm install
```

Por fim, suba o frontend:
```bash
npm run start
```

## ☕ Rodando o projeto

Acesse em:

```
 http://localhost:4200/fornecedores ou http://localhost:4200/empresas
```
