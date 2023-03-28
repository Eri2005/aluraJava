import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

public class App {
    public static void main(String[] args) throws Exception {

        // Fazer uma conexão HTTP e buscar Top 250 filmes
        // Jogando URL da API em uma variavel
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/MostPopularMovies.json";
        
        // Criando uma URI pegando a variavel URL
        URI endereco = URI.create(url);
        
        // Criando um HttpClient com newHttpClient que é metodo estatico
        // Jogando em uma variavel 'client'
        var client = HttpClient.newHttpClient();

        // Pegando o conteudo da requisição
        var request = HttpRequest.newBuilder(endereco).GET().build();

        // Pegando o resultado
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

        // Pegando o body da requisição
        String body = response.body();

        System.out.println(body);
    }
}
