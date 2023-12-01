## API Booking Hotel
<div style="display: flex;"> <br>
<img align="center", src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white">
<img align="center", src="https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white">
<img align="center", src="https://img.shields.io/badge/Spring_Security-6DB33F?style=for-the-badge&logo=Spring-Security&logoColor=white">  
<img align="center", src="https://img.shields.io/badge/MySQL-005C84?style=for-the-badge&logo=mysql&logoColor=white">
<img align="center", src="https://img.shields.io/badge/json%20web%20tokens-323330?style=for-the-badge&logo=json-web-tokens&logoColor=pink">
<br>
<br>
</div>

### Descrição:
API para a gestão de reserva de hoteis,

### Funcionalidades:
•	Cadastro de usuário;
•	Atualização dos dados do usuário;
•	Vizualização de reservas;
•	Exclusão de usuário;
•	Login com validação de senha e token (JWT);
•	Cria hotel;
•	Busca de hotel por nome e localidade;
•	Pesquisa disponibilidade de reserva de acordo com a data do checkin e chekout desejados;
•	Registro de reserva;
•	Cancelamento de reserva (não se trata de um DELETE de fato, mas sim uma atualização para o status "CANCELADO";
•	Rotas com permissão de acesso dependendo do perfil, "user" ou "admin".

### Dependências:
Spring Boot
MySQL
Lombok
Spring Security

### Variáveis de Ambiente:
No arquivo application.properties, configure banco de dados.

### Rotas:
{{ URL }}: http://localhost:8081/

🔒 : Rotas que requerem autenticação
🔐 : Rotas que requerem autenticação de usuário Admin

##### Rotas de usuário
| Método | Rota                        | Descrição                            | Exemplo Requisição Body                    |
|--------|-----------------------------|-------------------------------------|------------------------------------------|
| POST   | `{{URL}}/users/create`            | Criar usuário                        | `json\n{ "name": string, "email" : string, "password": string }` |
| PUT   | 🔒 `{{URL}}/users/{id}`  | Atualização de dados do usuário     |                |
| GET   | 🔐 `{{URL}}/users/{id}` | Pesquisa de usuário pelo Id         | `json\n{ "idUser": string, "idJob" : string }` |
| GET    | 🔐 `{{URL}}/users/{id}/view_bookings` | Pesquisa reservas do usuário |  |
| DELETE   | 🔐 `{{URL}}/users/{id}` | Exclui usuário do banco de dados         |  |

##### Rota de autenticação de usuário
| Método | Rota             | Descrição                     | Exemplo Requisição Body               |
|--------|------------------|------------------------------|--------------------------------------|
| POST   | `{{URL}}/login/` | Valida e autentica usuário    | `json\n{ "email" : string, "password": string }` |

##### Rotas de hoteis
| Método | Rota                               | Descrição                                                  | Exemplo Requisição Body                                                             |
|--------|------------------------------------|-----------------------------------------------------------|-------------------------------------------------------------------------------------|
| POST   | 🔐 `{{URL}}/hotels/`                    | Criar hotel         | `json\n{ "name": string, "description" : string, "city": string                                                           

### Contribuições:
Ficarei muito feliz caso queira contribuir com o projeto e desde já agradeço =) Clone este repositório, crie seu branch de trabalho e bora lá!
Ao final, abra um Pull Request explicando o problema resolvido ou recurso realizado.
Contato: https://www.linkedin.com/in/glasielle-cirilo-dev-fullstack/
