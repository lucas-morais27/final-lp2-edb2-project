# *Trabalho da Unidade 3*
## Linguagem de Programação II e Estruturas de Dados II
## Autores
[Bianca Miranda](https://github.com/Bianca-Mirtes)<br>
[Lucas Morais](https://github.com/lucas-morais27)<br>
[Poliane Brito](https://github.com/iampoliane)
<br>

## Especificação
Para melhor entendimento do objetivo do trabalho, acesse o [link](https://drive.google.com/file/d/1pZ0WzLBBl6FwaZVvyuCQpYy8izPQ3GnF/view?usp=sharing).
<br>

## Linguagem de programação
<img src="https://w7.pngwing.com/pngs/405/878/png-transparent-java-logo-java-runtime-environment-computer-icons-java-platform-standard-edition-java-miscellaneous-text-logo.png" width="180" height="100"/>

## Compilar e executar:

### Utilize a IDE VSCode ou faça as mesmas configurações em outra.

- Pesquise por Configurar Tarefa de Build Padrão
<div align="center">
<img src="https://user-images.githubusercontent.com/86920019/205187140-49f1f31e-c1ef-49fc-b678-65fb28f66d1e.png"/>
</div>

- Nas opções, selecione:
<div align="center">
<img src="https://user-images.githubusercontent.com/86920019/205187138-4236f895-847c-4d04-8581-faa9dfcdd02e.png"/>
</div>

- Na pasta lib e na pasta scr/main, com o botão direito do mouse selecione a opção:
<div align="center">
<img src="https://user-images.githubusercontent.com/86920019/205187148-07dd61a1-f54d-4c36-b107-aa4b4d78a691.png"/>
</div>

- Na IDE, procure pela aba JAVA PROJECTS, selecione a opção Configure Classpath no projeto
<div align="center">
<img src="https://user-images.githubusercontent.com/86920019/205187147-9925d052-ff7a-4c35-bafd-b5f0aa777aa4.png"/>
</div>
Obs.: Caso não encontre essa aba, abra algum arquivo .java do projeto.

- Após abrir as configurações do Classhpath, nas referências de bibliotecas adicione os arquivos .jar localizados na pasta lib
<div align="center">
<img src="https://user-images.githubusercontent.com/86920019/205187143-2f9acc62-cfb0-4212-9921-865836f2d0a2.png"/>
</div>

- Verifique se o arquivo settings.json está como a imagem, caso a linha 7 esteja diferente, corrija.
<div align="center">
<img src="https://user-images.githubusercontent.com/86920019/205192557-d41462e6-fe93-4cb0-b945-b510e700943f.png"/>
</div>

- Pesquise por Adicionar Configuração:
<div align="center">
<img src="https://user-images.githubusercontent.com/86920019/205187137-8ff9b461-e24d-431b-97c6-3c3f3adac165.png"/>
</div>

- Nas opções, selecione Java
<div align="center">
<img src="https://user-images.githubusercontent.com/86920019/205187135-9af49e62-9534-4b8d-bde6-582f14f292cc.png"/>
</div>

- Tente executar para adicionar as configurações automaticamente no launch.json
<div align="center">
<img src="https://user-images.githubusercontent.com/86920019/205192559-1857427e-60c1-4f2c-8e92-c94147eba329.png"/>
</div>
Obs.: Caso não execute, não tem problema, siga o próximo passo.

- No arquivo launch.json, verifique se foi adicionado a linha projectName e se o nome do mainClass foi mudado para 'Main'.
<div align="center">
<img src="https://user-images.githubusercontent.com/86920019/205194823-e62053a9-da33-401f-acba-559d41305123.png"/>
</div>

- Nesse mesmo arquivo, nas configurações, adicione a linha:
```
    "args": "src/arquivos/input1.txt"
```
- Verifique se o arquivo está assim:
<div align="center">
<img src="https://user-images.githubusercontent.com/86920019/205192555-c584f5a4-cebe-4e63-b1f7-4dcf84deea7c.png"/>
</div>
Obs.: O nome do projeto é gerado aleatóriamente, não é necessário alterar. Lembrar da virgula.

- Por último, execute novamente.
