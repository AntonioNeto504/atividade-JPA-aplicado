Sistema de Gerenciamento de Cursos Online
Projeto desenvolvido para a Atividade Avaliativa Prática, implementando um sistema de gerenciamento de cursos e instrutores com Spring Boot, usando JDBC e JPA, MySQL com Docker, e testes com H2.
Pré-requisitos

Java 21
Maven
Docker e Docker Compose

Como Executar

Iniciar o MySQL com Docker:
docker-compose up -d


Compilar e executar o projeto:
mvn clean install
mvn spring-boot:run


Testar endpoints (exemplo com cURL):
curl -X POST http://localhost:8080/jdbc/cursos -H "Content-Type: application/json" -d '{"titulo":"Java","duracaoHoras":40.0,"instrutorId":1}'
curl -X GET http://localhost:8080/jpa/cursos/filtro?titulo=Java


Executar testes:
mvn test



Endpoints

JDBC:
POST /jdbc/cursos: Criar curso
GET /jdbc/cursos: Listar cursos
GET /jdbc/cursos/{id}: Buscar curso por ID
PUT /jdbc/cursos/{id}: Atualizar curso
DELETE /jdbc/cursos/{id}: Deletar curso
GET /jdbc/cursos/filtro: Filtrar cursos
POST /jdbc/instrutores: Criar instrutor
GET /jdbc/instrutores: Listar instrutores
GET /jdbc/instrutores/{id}: Buscar instrutor por ID


JPA: Mesmos endpoints, mas com prefixo /jpa.

Comparação entre JDBC e JPA

JDBC: Controle manual de SQL, ideal para consultas complexas e performance otimizada. Requer mais código para mapeamento.
JPA: Abstração ORM, reduz boilerplate, suporta relações bidirecionais. Pode ter overhead em cenários complexos.
Escolha: JDBC para alto controle e performance; JPA para produtividade e manutenção.

Boas Práticas

Uso de consultas parametrizadas no JDBC.
FetchType.LAZY no JPA para evitar carregamento excessivo.
Transações apenas em operações de escrita.
Validações com Bean Validation.
Testes isolados com H2.
