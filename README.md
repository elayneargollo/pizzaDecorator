# Pizza Decorator

## Especificação de trabalho prático (Decorator)

<p>Objetivo: implementar um sistema para seleção de decorators a serem aplicados em um objeto básico que representa uma pizza. O método decorado será o método "void preparar()" e a implementação do objeto básico (denominado PizzaBasica) deverá exibir: "Preparando a massa + molho + queijo". Todos os decorators deverão exibir: "Adicionando <ingrediente-do-decorator>". Três decorators concretos deverão ser implementados como plugins, identificados no momento da carga (através de instanceof ou casting para o pai de todos os decorators) e exibidos na lista de decorators disponíveis. O usuário irá selecionar os decorators que ele deseja incluir na pizza, podendo realizar múltiplas seleções de um mesmo decorator e redefinir a ordem de aplicação dos decorators. Ao clicar no botão "Preparar", o sistema deverá instanciar a cadeia de decorators selecionados e invocar o método "void preparar()" na ponta desta cadeia.</p>
  
## Tela a ser implementada
  
![Alt text](https://github.com/elayneargollo/pizza/blob/main/tela.png?raw=true "Pizzaria")
  
## INF011-20211-T4
 
Data de entrega: 14/06
