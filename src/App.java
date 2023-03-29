import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.security.Key;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {

        // Fazer uma conexão HTTP e buscar uma lista de filmes mais popular
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/MostPopularMovies.json";
        URI endereco = URI.create(url);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();

        // Extrair só os dados que interessam (titulo, poster, classificação)
        var parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);

        // Exibir e manipular os dados
        for (int i = 0; i < 3; i++) {
            Map<String,String> filme = listaDeFilmes.get(i);
            System.out.println("\u001b[33mTitulo: \u001b[m" + "\u001b[1m" + filme.get("title") + "\u001b[m");
            System.out.println("\u001b[33mImagem: \u001b[m" + "\u001b[1m" + filme.get("image") + "\u001b[m");
            System.out.println("\u001b[33mClassificação: \u001b[m" + "\u001b[1m" + filme.get("imDbRating") + "\u001b[m");

            // Pegando a String da classificação e convertendo para Double 
            double classificacao = Double.parseDouble(filme.get("imDbRating"));

            // Pegando o resultado da conversão e convertendo para inteiro
            int numeroEstrelinhas = (int) classificacao;

            // Criando um for para contar as estrelas
            for (int n = 1; n <= numeroEstrelinhas; n++){
                System.out.print("⭐");
            }
            System.out.println("\n");
        }
    }
}
