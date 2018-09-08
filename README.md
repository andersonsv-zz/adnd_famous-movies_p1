# Android - Nanodegree - FilmesFamosos - P1

Primeiro projeto do Nanodegree - Android - Filmes Famosos. O projeto está baseados em duas telas: 

- 1) Lista de filmes em grade ordenados por "Mais populares" (Most popular) e "Mais Votados"(Top Rated). Por padrão a classificação é por filmes "Mais Votados" (Top Rated).
- 2) Detalhes do filme com as informações de: imagem de fundo, capa, nome do filme, lançamento, título e sinopse. 

# Objetivos 
> **1)** Após o lançamento, apresentar ao usuário uma disposição em grade dos cartazes de filmes.
> **2)** Permitir que o usuário altere a ordem de classificação por meio de uma configuração: A ordem de classificação pode ser por mais populares ou por melhor classificação.
> **3)** Permitir que o usuário toque em um cartaz de filme e passe para uma tela de detalhes, contendo informações adicionais, tais como:
> **3.1)** Título original;
> **3.2)** Miniatura da imagem do cartaz do filme;
> **3.3)** Uma sinopse (chamada de overview na API);
> **3.4)** Avaliação dos usuários (chamada de vote_average na api);
> **3.5)** Data de lançamento.

# Arquitetura
- Java 8
- Android
- Gradle

# IDE 
- Android Studio (atualizada na última versão)

# Chave de API
  [TheMovieDB](https://www.themoviedb.org) > Gerar a chave.
  Necessário incluir o arquivo no diretório values app/src/main/res/values/apikey.xml, com o conteúdo:
  ```<?xml version="1.0" encoding="utf-8"?>
<resources>
    <string name="moviedb">[chave de TheMovieDB API]</string>
</resources>
```
 
# Fora de escopo 
- Adaptar ao girar o aparelho;
- Adaptação para dispositivos com menor resolução.
- Loader das imagens.