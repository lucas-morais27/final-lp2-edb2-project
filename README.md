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

- Pesquise por Adicionar Configuração:
<div align="center">
<img src="https://user-images.githubusercontent.com/86920019/205187137-8ff9b461-e24d-431b-97c6-3c3f3adac165.png"/>
</div>

- Nas opções, selecione Java
<div align="center">
<img src="https://user-images.githubusercontent.com/86920019/205187135-9af49e62-9534-4b8d-bde6-582f14f292cc.png"/>
</div>

- Na pasta lib, com o botão direito do mouse selecione a opção:
<div align="center">
<img src="https://user-images.githubusercontent.com/86920019/205187148-07dd61a1-f54d-4c36-b107-aa4b4d78a691.png"/>
</div>

- Na IDE, procure pela aba JAVA PROJECTS, selecione a opção Configure Classpath no projeto
<div align="center">
<img src="https://user-images.githubusercontent.com/86920019/205187147-9925d052-ff7a-4c35-bafd-b5f0aa777aa4.png"/>
</div>

- Após abrir as configurações do Classhpath, nas referências de bibliotecas adicione os arquivos .jar
<div align="center">
<img src="https://user-images.githubusercontent.com/86920019/205187143-2f9acc62-cfb0-4212-9921-865836f2d0a2.png"/>
</div>

- No arquivo launch.json, nas configurações, adicione a linha:
```
    "args": "src/arquivos/input1.txt"
```
<div align="center">
<img src="https://user-images.githubusercontent.com/86920019/205187141-85f7dbe9-90bb-44a9-a674-4baf82f36aec.png"/>
</div>
Obs.: lembrar da virgula.

- Por último, execute.