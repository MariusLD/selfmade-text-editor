# Structure de la V1

## ➤ Application

Dans un premier temps nous avons la classe Application, une classe maitresse de notre programme puisqu'elle effectue la jonction entre notre Fenetre graphique pour l'utilisateur et les intéractions avec l'Editeur de texte. Cette classe possède quelques attributs tels que : 
- Le clipboard, il s'agit d'un String qui servira de zone mémoire lorsqu'une sélection aura été copiée ou coupée, ce clipboard se situe dans la classe Application afin de permettre une flexibilité du champs qui est facilement accessible par un appel de méthode getClipboard() qui renvoie donc la chaîne de caractères, ou bien facilement éditable par sa méthode setClipboard(String).
- Nous avons ensuite l'attribut commandes, il s'agit d'un Map permettant de faire l'association entre les raccourcis claviers tapés et un Callable<Commande>, autrement dit il fait le rapprochement entre une commande demandée par l'utilisateur et une tâche asynchrone qui sera exécutée en interne par un thread annexe, à la manière d'une requête, requête qui se fera via la méthode getCommande(char) qui pour un caractère rentré cherchera dans la Map la Commande correspondante qui sera créée sous forme de nouvelle instance. La méthode getCommandes() permet également de récupérer cette Map. Cette dernière est générée à chaque fois que le programme est lancé par la méthode d'initialisation.
- Suite à cela nous avons la Fenetre, il s'agira purement de ce qui s'ouvrira pour l'utilisateur lorsque la méthode run() sera lancée, l'Application ne possède qu'une seule et unique Fenetre attribuée, qu'il est possible de récupérer par la méthode getFenetre().
- Enfin nous avons l'attribut editeur, qui correspond à l'éditeur lié à nottre Application, puisqu'en effet une application détient un unique Editeur, qui s'occupe de la gestion du buffer de texte qui apparaît sur la Fenetre ainsi que de la gestion de la Selection de texte. Cet Editeur unique est récupérable par la méthode getEditeur().

Ensuite, nous avons la méthode ecrit(char) qui va recevoir l'informatiton d'une touche pressée sans considérer les moments où des commandes sont renttrées avec Echap, et qui va renvoyer l'information à l'Editeur qu'il doit changer son buffer afin que l'affichage soit par la suite le résultat de la touche actionnée par l'utilisateur.
Il existe également quelques "requêtes" sous forme de commandes qui ne sont pas dans la Map puisqu'elles nécessitent un paramètre Direction en entrée qui permet ainsi de savoir dans quel sens on se déplace. La Direction est une énumération entre HAUT/BAS/DROITE/GAUCHE. En fonction dans l'état d'édition dans lequel nous sommes (Echap ou non pressé) nous avons deux méthodes qui peuvent être appelées, soit deplaceCurseur soit deplaceSelection, qui dans un cas va seulement déplacer le curseur en modifiant l'attribut de l'Editeur lié et dans l'autre va modifier la zone de texte sélectionnée qui est également un attribut d'Editeur que nous verrons ensuite. La méthode supprime utilise le même principe avec le paramètre Direction mais dans son cas il s'agit de déterminer dans quelle direction il faut supprimer.
  
  ## ➤ Fenetre
  
  La Fenetre est donc l'interface graphique que nous mentionnions plus tôt qui permet ainsi d'avoir un visuel direction sur les actions effectuées par l'utilisateur.
  Le constructeur de la Fenetre prend comme argument l'application unique qui lui est attribué et qui l'a initialisé afin de pouvoir plus facilement lui envoyer des informations notamment en terme d'évènements reçus, à ce propos la Fenetre dispose de nombreux attributs dont :
  - L'actionneur de type Actionneur, il s'agit de la partie de notre programme qui va capter les données transmises par l'utilisateur directement en écoutant les entrées clavier qu'il effectue. C'est par ce biais que la Fenetre va pouvoir prévenir l'Application lorsqu'une commande a été demandée par l'utilisateur afin qu'elle se charge de générer la commande en conséquence. Nous pouvons récupérer l'Actionneur avec getActionneur() et il est possible de changer le mode de capture de l'Actionneur (que nous expliquerons ensuite dans la partie Actionneur) grâce à la méhode setActionneur(Actionneur). 
  - Elle possède égalementt un attribut textArea qui est une JTextArea c'est-à-dire une classe intégrée dans Java via Java Swing qui permet d'entrer ou afficher du textte de façon simple. C'est une fenêtre de lecture parfaite pour notre programme mais nous avons dû la modifier de telle sorte à ce qu'il ne nous soit pas possible d'éditer à la main la zone de texte, auquel cas les entrées clavier serait interprétrée sans utiliser notre programme. Il est possible de récupérer cette JTextArea avec getTextArea(). Cette textArea est nouvellement générée au lancement de l'application avec la méthode initTextArea().
  - Enfin, elle possède quatre derniers attributs, deux pour la sélection, deux pour le curseur qui sont bien disjoints. Il s'agit d'une option graphique que nous avons ajouté afin de donner de la lisibilité pour l'utilisateur sur ce qu'il fait. Globalement le HighLightPainter permet de définir ce que nous souhaitons dessiner (par exemple une simple barre pour le curseur ou un surlignage pour la sélection) et le Tag est en fin de compte l'endroit où nous souhaitons dessiner. Dans l'exemple "bonj|our" le "|" symbolise le curseur, ce qui est dessiné par le Highliter et le Tag va dire de le peindre en position 4. Il est possible de récupérer chaque Painter et Highliter avec getHighliter(), getCursorPainter(), getSelectionPainter(), getCursorTag(), getCursorTag().
 
 D'un point de vue graphique la Fenetre dispose de méthodes : refreshText() qui permet d'actualiser le texte affiché, que nous appelons dès qu'une modification sur le buffer est effectuée. De même pour refreshSelectionHighligh() et refreshCursorHighlight() lorsqu'un déplacement est effectué avec les flèches directionnelles et show() qui va permetre de display l'interface graphique depuis l'Application lorsqu'on run().

 ## ➤ Actionneur
 
L'actionneur est une classe abstraite et est l'oreille de notre programme, c'est lui qui va écouter les entrées claviers de maniière brute, et qui va switch entre les deux modes d'écoute : soit lorsqu'il s'agit d'un mode d'écoute de commande soit lorsqu'il s'agit d'un mode d'écoute de caractères imprimables. 
Son constructeur prend la Fenetre en argument pour pouvoir agir dessus tout comme la Fenetre avec l'Application.
La méthode getFenetre() permet justement de récupérer cette fenêtre et la méthode changeActionneur(Actionneur) permet d'alterner le mode d'écoute comme nous l'avons expliqué.  
En effet, dans InputActionneur (qui implémente Actionneur et qui est l'Actionneur par défaut) lorsque nous appuyons sur Echap, un call vers setActionneur(Actionneur) dans la Fenetre sera émis qui aura donc pour effet de passer l'Actionneur en CommandeActionneur. Par contre à l'inverse dans CommandeActionneur, si nous relâchons la touche Echap un même call sera émis mais pour changer l'Actionneur en InputActionneur. Différence supplémentaire dans InputActionneur, une méthode de vérification isPrintable(char) vérifie que char est bien un caractère qu'il est possible d'afficher (un caractère impossible à afficher est Space par exemple). La capture des touches pressées et relâchées se fait grâce au KeyEvent. L'Actionneur capte notamment les flèches directionnelles permettant ainsi de définir la Direction qui sera envoyée dans des méthodes telles que deplaceCurseur, deplaceSelection ou bien supprime.

  ## ➤ Editeur
  
  L'Editeur est, comme nous l'avons préalablement mentionné, la partie qui va s'occuper du buffer textuel qui va contenir ce qui sera affiché dans la JTextArea, le curseur et la zone de sélection. Il n'a pas besoin d'envoyer de lui-même des informations à l'Application donc cette fois ci le constructeur ne requiert pas d'avoir l'instance de l'application. Nous avons fait le choix de définir l'attribut texte comme étant un StringBuffer étant donné que ce buffer va changer en permanence, il est donc nécessaire que ce buffer soit très maléable :
- Il est possible d'ajouter du contenu aisément dans ce buffer avec la méthode append() du StringBuffer déjà implémenté que nous retrouvons au travers de la méthode write(char) qui permet d'ajouter le caractère d'entrée dans le buffer et de faire avancer le curseur en conséquence pour qu'il suive le texte. De même il est possible de supprimer un caractère avec la méthode deleteChar(Direction) qui va donc réduire le buffer en enleveant le prochain caractère dans la direction donnée.
- Le curseur est matérialisé par un entier qu'on incrémente ou décrémente avec moveCurseur(Direction) pour déterminer sa position dans le buffer. Il est possible de le récupérer aisément avec la méthode getCurseur().
- L'atribut selection lui est de type Selection qui est caractérisé de la même manière par un int pour définir la position de la borne par rapport au curseur, il est possible de déplacer également cette Selection avec moveSelection(Direction), ou bien de resetSelection() pour annuler la Selection en cours, mais également d'effectuer emptySelection() permettant de déterminer si la sélection existe ou bien si nous ne sommes réduits qu'à un curseur. Quand une sélection a été effectué, il est possible de "l'extraire" du StringBuffer en la copiant grâce à la méthode getSelectedText(), ou bien de réécrire par dessus afin de remplacer la sélection grâce à setSelectedText(String) et enfin de la supprimer totalement (il s'agit d'un remplacement par du vide).
  
  ## ➤ Commande
  
Finalement nous avons la classe abstraite Commande, implémentée par toutes les commandes que nous instancions dans l'Application.
Cette classe prend l'application et l'éditeur dans son construucteur et déclare une méthode execute() qui correspond à l'action que doit effectuer chaque commande sur le texte:StringBuffer de l'editeur et sur le clipboard de l'Application en sachant qu'une Application possède autant de Commande que nous le souhaitons.
Coper/Coller/Couper se trouvent donc dans la Map commandes et la méthode execute() se fait lorsque la clé correspondante est reconnue par l'Actionneur et indirectement l'Application, les commandes DeplacerCurseur/DeplacerSelection/Supprimer s'execute() lorsque la méthode correspondante de l'Application est appelée, ainsi ces commandes ont un attribut direction qui permet de définir dans l'Editeur si il faut plutôt appeler moveCurseur vers la GAUCHE, vers la DROITE etc... Il n'y a donc bien qu'une Direction à chaque fois. Enfin la commande d'écriture appelle systématiquement writeChar(char) de l'Editeur en lui donnant son attribut c que l'Application lui aura renseigné.

# Structure de la V2
  
 Pour cette seconde et dernière version nous avons repris intégralement la struction de la première version comme il a été demandé, tout en ajoutant de nouvelles classes permettant d'implémenter au sein du programme les nouvelles fonctionnalités, à savoir la possilité de undo/redo et l'enregistrement d'un script qui capte les actions effectuées par l'utilisateur le temps de l'enregistrement afin de les effectuer autant de fois que nous le souhaitons par la suite après avoir stoppé cet enregistrement. 
  
Désormais, la classe Applicatiton possède un nouvel attribut memoire de type Memoire, elle est unique pour l'application.
  
 ## ➤ Memoire
  
La Memoire se sert d'une autre classe : Snapshot (que nous aborderons ensuite) afin de sauvegarder des états de l'édition sur l'éditeur de texte. Ces états se découpent en 3 catégories : l'état présent, les états "futurs" qui étaient d'anciens états présents, les états passés qui sont d'anciens états présents, successivement : present, futur, passe. Le premier est modélisé directement par une snapshot c'est-à-dire un unique état, tandis que les deux autres sont modélisés par des Stack. Les Stack sont très utiles dans notre cas pour bénifier du LIFO car ces Stack vont être grandement solicitées. Il est possible de récupérer ces trois attributs avec les méthodes getPasse(), getFutur(), getPresent(). 
Pour enregistrer l'état courant, nous utilisons la méthode sauvegarde(Snapshot) où on recoit une nouvelle Snapshot à définir comme la Snapshot present, en attendant nous prenons la Snapshott present que nous envoyons en haut de la Stack passe pour signaler que c'était l'état précédent, et nous nettoyons la Stack futur pour montrer que nous sommes dans un nouvel état que nous avons pas encore parcouru dans le futur.
La méthode retourPasser() permet de remettre la Snapshot en haut de passe à la place de la Snapshot present, cette dernière va passer au dessus de futur pour y revenir si besoin. Et inversement pour retourFutur() où restaure l'ancienne Snapshot après être revenu en arrière et en mettant la Snapshot present dans la Stack passe.

## ➤ Snapshot
  
La Snapshot est, comme dit plus tôt, un état de la mémoire. En effet, elle va se rappeler des éléments du buffer de l'Editeur afin de pouvoir recharger cet état dans la mémoire lorsque nous en avons besoin avec les méthodes retourPasse() qui appelle la commande Annuler, retourFutur() qui appelle la comamande Retablir. Pour cela rien de plus simple, nous recréons des attributs curseur et texte dans la Snapshot, similaires à ceux de l'éditeur qui auront valeur de l'état présent lorsque la méthode sauvegarde() de Mémoire est appelée.
Nous pouvons récupérer ces champs facilement avec les méthodes getTexte() et getCurseur(), ainsi que cloner la Snapshot avec clone() afin de renvoyer une Snapshot équivalente à celle-ci, utile afin de copier des Snapshot d'une Stack à l'autre de la Memoire. 
  
## ➤ Commande
  
Pour cette version 2, il a fallu adapter le système de commande à nos nouveaux besoins en conservant l'ancienne sructure. Pour cela nous avons créé deux nouvelles interfaces : Scriptable et Sauvegardable. Ces nouvelles interfaces permettent de définir lorsqu'une commande peut soit enregistrer dans le script dont nous parlerons pendant les explications) soit qui peut créer une nouvelle Snapshot avec sauvegarde(Snapshot). Lorsqu'une commande possède une version 2, nous avons remplacé dans l'Application sa mention, on ne fait plus new Couper() mais new Couper_2(). Prenons l'exemple de Ecrire_2() elle implémente à la fois Scriptable et Sauvegardable afin de pouvoir appeler script() et sauvegarde() et étend Ecrire donc bénificie toujours de la même exécution, voici son constructeur et sa méthode execute() : 
```java
public Ecrire_2(Application application, Editeur editeur, char c) {
  super(application, editeur, c);
  script();
}
  
public void execute() {
  super.execute();
  sauvegarde();
}```
  
Cependant il fallait bien évidemment adopter ce genre de structure car Annuler et Retablir sont scriptables et peuvent donc être rejouées, mais ne sont cependant pas Sauvegardable au risque d'obtenir une boucle infinie.
De même pour Enregistrer et RejouerScript qui sont quant à eux ni Sauvegardable ni Scriptable pour éviter une nouvelle fois une boucle infinie.

## ➤ Script
  
L'application possède également un unique Script maintenant, qui possède lui-même une infinité de commandes. En effet, le Script est une nouvelle fonctionnalité permettant d'enregistrer toutes les commandes effectuées dans un certain moment défini par l'utilisateur afin de pouvoir les rejouer.
Ce script possède deux attributs :
- L'attibut commandes qui est en réalité une List où viendront s'ajouter les commandes une à une lorsque ↲
- L'attribut registering sera à true ! En effet cet attribut permet de savoir si le script enregistre ou si il est stoppé.
Ces deux attributs sont récuparables via les méthodes getCommandes() et isRegistering().
Finalement, à la création d'une commande qui implémente Scriptable, nous appelons sa méthode Script, si le script enregistre (qui est activé par un execute de la commande EnregistrerScript) alors elle est ajoutée à la file du Script sinon il ne se passe rien. Lorsque la commande EnregistrerScript() est effectuée de nouveau elle coupe l'enregistrement, nous utilisons alors la méthode setRegistering(boolean) qui indique que l'enregistrement a stoppé.
Quand cet enregistrement a cessé, nous avons alors une liste d'actions en attente qu'il est possible de rejouer à n'importe quel moment et n'importe où dans notre buffer avec la nouvelle commande RejouerScript(), et ce, autant de fois que nous le voulons.
