# Transmusicales - Application Android

One Paragraph of project description goes here

## Contexte

Début décembre 2020 se dérouleront à Rennes les Transmusiales. Cet événement proposera
à ses participants d'assister aux concerts de nombreux artistes dans des salles
mises à disposition par la ville de Rennes.

### Besoins

Pour avoir rapidement accès à des informations concernant les artistes, que cela soit
avant ou pendant les Trans, il serait intéressant pour les participants d'avoir sous
la main une application mobile permettant :

* d'explorer la liste des artistes
* de pouvoir effectuer des recherches sur cette liste
* de pouvoir obtenir davantage d'information en sélectionnant un artiste
* de pouvoir attribuer une note à un artiste
* de visualier la note moyenne de chaque artiste
* de laisser des commentaires public sur les artistes
* d'avoir une carte interactive
* d'avoir la possibilité de lancer les chansons des artistes via Deezer et / ou Spotify.

### Équipe

2 étudiants en M2 MIAGE, parcours DLIS. (Développement des logiciels et intégration de systèmes)

* **Thomas Guessant** - *Développement* - [thomasguessant35](https://github.com/thomasguessant35)

* **Mathieu Le Han** - *Développement* - [mathieulehan](https://github.com/mathieulehan)

### Description des fonctionnalités disponibles et état des lieux.

Ci-dessous la liste des fonctionnalités mises en place, ainsi qu'un état des lieux pour chacune.

## Page d'accueil

Page d'accueil plutôt basique présentant les Transmusicales.

## Menu

Le menu de l'application est un menu utilisant NavigationView.
Il apparaît au toucher sur le hamburger en haut à droite, et disparaît si l'utilisateur
glisse vers la gauche ou touche simplement en-dehors du menu.

## Liste des artistes

La liste des artistes est alimentée par une base de données en temps réel Firebase.
Nous avons implémentée FirebaseRecyclerPagingAdapter afin d'avoir lazy loading des artises, 
les pages se chargeant au fur et à mesure que l'utilisateur descend dans le RecyclerView.
La première salle de chaque artiste est affichée.

## Sélection et affichage d'information supplémentaire sur un artiste

Au toucher sur un artiste de la liste, des informations plus détaillées sur celui-ci apparaissent.

## Intégration de Spotify,  Deezer & Google Maps

Lorsque l'information nécessaire est présente en base, les logos des sociétés citées apparaissent
dans la CardView de l'artiste. Au clic sur l'un d'entre eux, l'application concernée s'ouvre et charge
une chanson ou ouvre une carte géographique.

## Notation des artistes

Une RatingBar permet à un utilisateur de laisser une note à un artiste. Une fois la note soumise,
la note moyenne de cet artiste est actualisée et affichée.

## Recherche

La fonctionnalité de recherche a été développée mais est imparfaite, et fait même planter l'applcation.
C'est possiblement dû au fait que nous utilisions FirebaseRecyclePagingAdpater.

## Commentaires

Dans la liste des artistes, un bouton "Comm" est présent à droite de chaque artiste. Au clic, celui-ci
permet d'accéder à la liste des commentaires publics sur cet artiste. Il est également possible d'ajouter
un commentaire.

## Origine des artistes

L'ensemble des artistes pour lesquels nous avons des données de géolocalisation sont affichés sur une
carte interactive Google Maps.

Pour pouvoir tous les afficher sans consommer trop de mémoire, nous avons utilisé un ClusterManager, 
permettant de regrouper visuellement les artistes proches. L'utilisateur vera s'afficher un artiste
unique une fois qu'il aura suffisamement zoomé, ou bien que l'artiste est très isolé par rapport aux autres.

Au clic sur un artiste, son nom est affiché.