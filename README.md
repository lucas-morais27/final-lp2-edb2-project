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
1° Nas opções, ir em Terminal > Configurar Tarefa de Build Padrão:
Clicar na opção que aparece:
```
    java (buildArtifact): <nome do projeto>
```

2° Nas opções, ir em Executar > Adicionar Configuração:
Clicar na opção que aparece:
```
    Java
```
3° Clica com o botão direito na pasta libs e clica na opção "Add Folder to Java Source Path";

4° No explorador, vai em Java Projects. Clica no projeto com o botão direito do mouse e em seguida em "Configure Classpath". Nas configurações, em Referenced Libraries, adicione os dois arquivos .jar que estão na pasta libs do projeto;

5° No arquivo settings.json, altere o caminho: 
```
    lib/**/*.jar
```
para
```
    libs/**/*.jar
```

6° Em launch.json adicione abaixo de projectName:
```
    "args": "src/arquivos/input1.txt"
```
7° Clique no botão de executar e depurar da sua IDE;