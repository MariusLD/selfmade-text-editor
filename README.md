# Notre éditeur de texte - TP2 d'OMD

Voici le repository concernant le projet collaboratif que nous avons mené Yanis Bouger ainsi que moi-même, Marius Le Douarin, via le Live Share de VSC en utilisant le langage : ![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=java&logoColor=white)

Le projet consiste en la création d'un _**éditeur de texte**_ simplifié en implémentant nous-mêmes les fonctionnalités de base d'un éditeur classique à savoir : la possibilité d'utiliser un curseur, supprimer le texte que nous venons de taper, copier ce que nous souhaitons dans un presse-papier afin de pouvoir le coller n'importe quand, n'importe où, et bien d'autres fonctionnalités...

L'intérêt au travers de ce projet était avant tout d'élaborer les différentes étapes d'organisation du programme, en passant par sa conception, mettre en oeuvre la collaboration entre ses différents objets, ainsi qu'imaginer le patron de conception sur lequel se baser afin de mettre en place une première version du programme grâce au principe _**SOLID**_ et l'étendre pour une seconde version avec quelques fonctionnalités supplémentaires plus poussées. ✅

## ⚙️ La liste des différents raccourcis claviers permettant de tester pleinement les fonctionnalités de l'éditeur de texte ⚙️ 

### Version 1️⃣ et Version 2️⃣ :

#### • Les entrées claviers de base :
- Tous les caractères imprimables permettent d'écrire du texte ✍️
- Les touche Retour/Suppr permettent d'effacer les caractères imprimables un à un ⛔️
- Les flèches directionnelles ⬆️⬇️➡️⬅️ permettent de déplacer le curseur, soit sur une même ligne, soit d'une ligne à l'autre

#### Toutes les commandes qui vont succéder nécessitent de presser la touche Echap en même temps que d'appuyer sur le raccourci ⚠️
#### • Les commandes :
- Les flèches directionnelles ⬆️⬇️➡️⬅️ permettent désormais de sélectionner une partie du texte, ainsi il est possible de plus ou moins aggrandir la zone de sélection en fonction de la flèche choisie
- La touche C permet de copier dans le presse-papier la zone qui a été préalablement sélectionnée 
- La touche V permet de coller à l'endroit où le curseur se situe, ou bien à la place de la zone sélectionnée, le texte qui a été préalablement copié
- La touche X permet à la fois de couper la zone sélectionnée, mais aussi de la sauvegarder dans le presse-papier

### Version 2️⃣ uniquement :
#### Toutes les commandes qui vont succéder nécessitent de presser la touche Echap en même temps que d'appuyer sur le raccourci ⚠️
#### • Les commandes :
- La touche Z permet d'annuler la dernière action qui a été effectuée sur l'éditeur, si elle consistait à coller un bout de texte, on revient l'état précédent sans copie ↩️
- La touche Y à l'inverse permet de rétablir un état après l'avoir annulé ↪️
- La touche E permet de commencer l'enregistrement du script, ainsi toutes les actions qui sont effectuées pendant l'enregistrement sont gardées en mémoire, que ce soit une succession de commandes ou bien d'autres entrées claviers. Pour stopper l'enregistrement il suffit d'appuyer une nouvelle fois sur E sans oublier la touche Echap
- La touche R permet de rejouer le script enregistré, et ce, à l'infini ♾️
