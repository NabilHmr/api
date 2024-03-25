## Projet M2 SID – API REST

## Choix

J'ai fait le choix de créer deux projets bien distincts pour les deux parties du projet comme nous l'avons vu en cours. 
En effet, je trouve que cela permet de bien séparer les deux parties et de ne pas mélanger les deux. 
Cela permet de bien séparer le service de la banque et le service du gouvernement.

Le service de la banque est le service qui s'occupe de gérer les demandes de crédit et de les valider ou non.
Le service du gouvernement est le service qui s'occupe de vérifier si la demande de crédit est bien valide ou non.

## Hateoas

J'ai utilisé HATEOAS pour les deux projets. 
En effet, je trouve que cela permet de bien structurer les réponses et de permettre à l'utilisateur de naviguer facilement dans l'API.


## Service de la banque

Mon service de la banque est un service REST qui permet de gérer les demandes de crédit.
Il permet de créer une demande de crédit, de la valider ou non et de récupérer les demandes de crédit.
Si vous voulez créer une demande de crédit, il suffit de faire un POST sur l'URL `/credits` avec un JSON contenant les informations de la demande de crédit.

Ensuite, concernant l'historique des demandes de crédit, il suffit de faire un GET sur l'URL `/credits/{id}/historique` pour récupérer toutes les demandes de crédit.
Cela va renvoyer un JSON contenant toutes les demandes de crédit avec la date de changement de statut de la demande.
L'utilisateur peut également éventuellement savoir quelle est le statut actuel de la demande de crédit en faisant un GET sur l'URL `/credits/{id}/last-action`.
En faisant appel à cette URL, l'utilisateur va récupérer le statut actuel de la demande de crédit.

## Service du gouvernement

Mon service du gouvernement est un service REST qui permet de vérifier si une demande de crédit est valide ou non.
Il comporte une seule méthode et qui est utilisée par le service de la banque pour vérifier si une demande de crédit est valide ou non.
Cette méthode renvoie une réponse positive si la demande de crédit est valide et une réponse négative si elle ne l'est pas.
J'ai surtout utilisé ce service pour montrer l'utilisation d'un circuit breaker.

## Circuit breaker

J'ai utilisé le pattern circuit breaker pour le service de la banque.
En effet, cela permet de gérer les erreurs et de ne pas surcharger le service du gouvernement si celui-ci est en panne.
J'ai mis un circuit breaker sur la méthode de validation d'une demande de crédit. Cela fait appel
au service du gouvernement pour vérifier si la demande est valide ou non. Si elle reçoit
une réponse positive, elle valide la demande de crédit. Sinon, elle la refuse.
Et dans le cas où le service du gouvernement est en panne, le circuit breaker se déclenche et renvoie une erreur grâce
à la méthode fallback que j'ai appelé `fallbackValiderDemandeCredit` qui renvoie une erreur 503
avec un message d'erreur indiquant que le service du gouvernement est indisponible.

## Consul et Resilience4j

J'ai utilisé Consul pour la découverte de services.
En effet, j'ai utilisé Consul pour que le service de la banque puisse trouver le service du gouvernement et vérifier 
si le service du gouvernement est disponible ou non. Cela va de pair avec le circuit breaker que j'ai mis en place qui 
utilise resilience4j pour gérer les erreurs.
Resilience4j permet de gérer les erreurs et de ne pas surcharger le service du gouvernement si celui-ci est en panne.

## Utilisation

Pour lancer les deux projets, il suffit de lancer les deux applications Spring Boot du côté de la banque et du gouvernement.
Il ne faut également pas oublier de lancer Consul pour que les deux services puissent communiquer entre eux. Il faut
donc démarrer votre Docker.

Ensuite, j'ai déjà populer la base de données avec des demandes de crédit et des clients associés à ces demandes
pour que vous puissiez tester les deux services. J'ai inséré 5 demandes de crédit dans la base de données.
Vous pouvez donc tester les deux services en faisant des requêtes sur les deux services en utilisant
comme identifiant de demande de crédit les identifiants de 1 à 5.
Mais vous pouvez également créer de nouvelles demandes de crédit en faisant un POST sur l'URL `/credits` du service de la banque.

Créer tout d'abord un client en faisant un POST sur l'URL `/clients` du service de la banque.

Voici un exemple de body pour créer un client :

```json
{
  "nom":"Votre nom",
  "prenom": "Votre prénom",
  "adresse": "13 rue test"
}
```

Voici un exemple de body pour créer une demande de crédit :

```json
{
  "nom": "Votre nom",
  "prenom": "Votre prenom",
  "adresse": "Votre adresse",
  "dateNaissance": "2001-01-01",
  "emploiActuel": "Developpeur",
  "revenusTroisDernieresAnnees": [10000.0, 55000.0, 45000.0],
  "montantCreditDemande": 1000.0,
  "dureeCreditDemande": 24,
  "client":{
    "id": "id de votre client (c'est un int)" 
  }
}
```

Pour tester le circuit breaker, vous pouvez arrêter le service du gouvernement et essayer de valider une demande de crédit.
Vous verrez que le circuit breaker va se déclencher et renvoyer une erreur 503.
Pour valider une demande de crédit, il suffit de faire un PUT sur l'URL `/credits/{id}/validate` du service de la banque.



